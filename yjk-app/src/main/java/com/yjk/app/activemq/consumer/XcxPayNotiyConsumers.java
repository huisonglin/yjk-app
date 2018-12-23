package com.yjk.app.activemq.consumer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.yjk.app.common.Constants;
import com.yjk.app.common.OrderStatusEnum;
import com.yjk.app.common.TemplateEnum;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.strategy.TemplateMessageStragegy;
import com.yjk.app.util.JSONUtil;
import com.yjk.app.vo.XcxPayNotifyInfoVO;
import com.yjk.common.dao.MemberMapper;
import com.yjk.common.dao.OrderMapper;
import com.yjk.common.entity.MemberDO;
import com.yjk.common.entity.OrderDO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Component
public class XcxPayNotiyConsumers {

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	TemplateMessageStragegy templateMessageStragegy;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	MemberMapper memberMapper;
	
	@SuppressWarnings("unchecked")
	@JmsListener(destination="${wx.xcx.xcxNotifyQueueName}")
	public void consumer(String message) throws Exception {		
		XcxPayNotifyInfoVO xcxPayNotifyInfoVO = JSONUtil.parse(message, XcxPayNotifyInfoVO.class);
		Example example = new Example(OrderDO.class);
		example.selectProperties("id","status","orderNo","memberId");
 		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("orderNo", xcxPayNotifyInfoVO.getOut_trade_no());
		List<OrderDO> orders = orderMapper.selectByExample(example);
		if(orders != null && orders.size() > 0) {
			OrderDO orderDO = orders.get(0);
			if(orderDO.getStatus() != OrderStatusEnum.UNPAID.getValue()) {
				return;
			}else {
				orderDO.setUpdateTime(new Date());
				orderDO.setPayTime(new Date());
				orderDO.setStatus(OrderStatusEnum.PAYMENT.getValue());
				orderDO.setTransactionId(xcxPayNotifyInfoVO.getTransaction_id());
				orderMapper.updateByPrimaryKeySelective(orderDO);
				MemberDO memberDO = memberMapper.selectByPrimaryKey(orderDO.getMemberId());
				Integer remainCallCount = memberDO.getRemainCallCount();
				if(remainCallCount == null) {
					memberDO.setRemainCallCount(1);
				}else {
					memberDO.setRemainCallCount(memberDO.getRemainCallCount()+1);
				}
				memberMapper.updateByPrimaryKeySelective(memberDO);
				
			}
		}
		
		redisTemplate.delete(Constants.XCX_PAY_PRAMS+xcxPayNotifyInfoVO.getOut_trade_no()); //删除支付参数
		/**
		 * 发送模板消息
		 */
		NotifyRequest request = new NotifyRequest();
		request.setType(TemplateEnum.PAY.getValue());
		request.setXcxPayNotifyInfoVO(xcxPayNotifyInfoVO);
		templateMessageStragegy.excute(request);
		
	}
}
