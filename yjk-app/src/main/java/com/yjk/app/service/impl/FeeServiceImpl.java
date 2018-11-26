package com.yjk.app.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import com.yjk.app.common.Constants;
import com.yjk.app.common.OrderStatusEnum;
import com.yjk.app.common.OrderTypeEnum;
import com.yjk.app.common.TemplateEnum;
import com.yjk.app.dao.OrderMapper;
import com.yjk.app.dto.DialingDTO;
import com.yjk.app.dto.DialingRefundDTO;
import com.yjk.app.dto.RefundTemplateInfoVO;
import com.yjk.app.dto.WeiXinRefundDTO;
import com.yjk.app.dto.XcxUnifiedorderDTO;
import com.yjk.app.entity.OrderDO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.FeeService;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.strategy.TemplateMessageStragegy;
import com.yjk.app.util.JSONUtil;
import com.yjk.app.util.PayUtil;
import com.yjk.app.util.R;
import com.yjk.app.util.UuidUtils;
import com.yjk.app.vo.WeiXinRefundVO;
import com.yjk.app.vo.XcxPayPramsVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class FeeServiceImpl implements FeeService{

	@Autowired
	PayUtil payUtil;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	public R dialing(DialingDTO dialingDTO) throws Exception {
		
		Example example =  new Example(OrderDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("infoId", dialingDTO.getInfoId());
		criteria.andEqualTo("status",OrderStatusEnum.PAYMENT.getValue());
		List<OrderDO> orders = orderMapper.selectByExample(example);
		if(orders != null && orders.size() > 0) { //之前已经付过款了
			XcxPayPramsVO xcxPayPramsVO = new XcxPayPramsVO();
			xcxPayPramsVO.setIsNeedPay("false");
			return R.ok().put("data", xcxPayPramsVO);
		}else {
			Example example2 =  new Example(OrderDO.class);
			example2.setOrderByClause("create_time desc limit 1");
			Criteria criteria2 = example2.createCriteria();
			criteria2.andEqualTo("infoId", dialingDTO.getInfoId());
			criteria2.andEqualTo("status",OrderStatusEnum.UNPAID.getValue());
			List<OrderDO> unpaidOrders = orderMapper.selectByExample(example2);
			if(unpaidOrders != null && unpaidOrders.size() > 0) {
				OrderDO unpaidOrderDO = unpaidOrders.get(0);
				String xcxPayPrams = valueOperations.get(Constants.XCX_PAY_PRAMS+unpaidOrderDO.getOrderNo());
				if(xcxPayPrams != null) {
					return R.ok().put("data", JSONUtil.parse(xcxPayPrams, XcxPayPramsVO.class));
				}
			}
			
			OrderDO orderDO = new OrderDO();
			orderDO.setCreateTime(new Date());
			orderDO.setInfoId(dialingDTO.getInfoId());
			String orderNo = UuidUtils.generateOrderNo("NO");
			orderDO.setOrderNo(orderNo);
			orderDO.setMoney(new BigDecimal("0.01"));
			orderDO.setMemberId(dialingDTO.getMemberId());
			orderDO.setPayTime(new Date());
			orderDO.setStatus(OrderStatusEnum.UNPAID.getValue());
			orderDO.setType(OrderTypeEnum.CALL_UP.getValue());
			orderDO.setUpdateTime(new Date());
			orderMapper.insertSelective(orderDO);
			
			XcxUnifiedorderDTO xcxUnifiedorderDTO = new XcxUnifiedorderDTO();
			xcxUnifiedorderDTO.setOpenid(dialingDTO.getOpenId());
			xcxUnifiedorderDTO.setBody("发布出租");
			xcxUnifiedorderDTO.setDetail("找机械,就来云机酷");
			xcxUnifiedorderDTO.setNonce_str(UuidUtils.get32UUID());
			
			xcxUnifiedorderDTO.setOut_trade_no(orderNo);
			xcxUnifiedorderDTO.setSpbill_create_ip("36.5.113.204");
			xcxUnifiedorderDTO.setTotal_fee(orderDO.getMoney());
			

			return payUtil.xcxAccessPayParam(xcxUnifiedorderDTO);
		}

	}
	
	@Autowired
	TemplateMessageStragegy templateMessageStragegy;
	
	/**
	 * 拨打电话退款接口
	 * @throws Exception 
	 */
	public R dialingRefund(DialingRefundDTO dialingRefundDTO) throws Exception {
		
		OrderDO orderDO = orderMapper.selectByPrimaryKey(dialingRefundDTO.getOrderId());
		if(orderDO.getStatus() == OrderStatusEnum.REFUND.getValue()) {
			throw new RRException("您已经退过款项了");
		}
		if(orderDO.getStatus() == OrderStatusEnum.PAYMENT.getValue()) {
			
			WeiXinRefundDTO weiXinRefundDTO = new WeiXinRefundDTO();
			weiXinRefundDTO.setNonce_str(UuidUtils.get32UUID());
			weiXinRefundDTO.setOut_refund_no(UuidUtils.generateOrderNo("TK"));
			String money = orderDO.getMoney().multiply(new BigDecimal("100")).intValue()+"";
			weiXinRefundDTO.setTotalFee(money);
			weiXinRefundDTO.setRefundFee("1");
			weiXinRefundDTO.setOutTradeNo(orderDO.getOrderNo());
			weiXinRefundDTO.setTransactionId(orderDO.getTransactionId());
			WeiXinRefundVO refundByWeiXin = payUtil.refundByWeiXin(weiXinRefundDTO);
			if("SUCCESS".equals(refundByWeiXin.getReturn_code())&&"SUCCESS".equals(refundByWeiXin.getResult_code())) {
				//退款成功
				orderDO.setStatus(OrderStatusEnum.REFUND.getValue());
				orderDO.setRefundTime(new Date());
				orderMapper.updateByPrimaryKeySelective(orderDO);
				
				//发送模板信息
				RefundTemplateInfoVO refundTemplateInfoVO = new RefundTemplateInfoVO();
				refundTemplateInfoVO.setGoodzName("拨打电话");
				refundTemplateInfoVO.setKindlyReminder("找机械，我来云机酷");
				refundTemplateInfoVO.setOpenId(dialingRefundDTO.getOpenId());
				refundTemplateInfoVO.setOrderNo(orderDO.getOrderNo());
				refundTemplateInfoVO.setReason("信息缺乏真实性");
				refundTemplateInfoVO.setRefundMoeny(weiXinRefundDTO.getRefundFee());
				NotifyRequest notifyRequest = new NotifyRequest();
				notifyRequest.setType(TemplateEnum.REFUND.getValue());
				notifyRequest.setRefundTemplateInfoVO(refundTemplateInfoVO);
				templateMessageStragegy.excute(notifyRequest);
				return R.ok();
			}else {
				throw new RRException(refundByWeiXin.getErr_code_des()+"("+refundByWeiXin.getErr_code()+")");
			}
		}else {
			throw new RRException("您还未支付,无法退款");
		}
	}
}
