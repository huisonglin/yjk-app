/**
 * 
 */package com.yjk.app.service.impl;

import java.awt.geom.Point2D;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.net.openssl.OpenSSLImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yjk.app.common.TemplateEnum;
import com.yjk.app.dto.InfoMatchDTO;
import com.yjk.app.dto.PositionDTO;
import com.yjk.app.resolver.PositionHandlerMethodArgumentResolver;
import com.yjk.app.service.InfoMatchingService;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.strategy.TemplateMessageStragegy;
import com.yjk.app.util.PositionUtil;
import com.yjk.app.vo.MatchSuccessVO;
import com.yjk.app.vo.MyListVO;
import com.yjk.common.dao.DeviceMapper;
import com.yjk.common.dao.DeviceRentOutInfoMapper;

import tk.mybatis.mapper.entity.Example;

/** 
* @author : 刘尊亮
* @date 创建时间：2019年2月26日 下午3:15:36 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */
 @Service
public class InfoMatchingServiceImpl implements InfoMatchingService{

	 @Autowired
	 ValueOperations<String, String> valueOperations;
	 
	 @Autowired
	 DeviceMapper deviceMapper;
	 
	 @Autowired
	 TemplateMessageStragegy templateMessageStragegy;
	 
	 @Async
	 public void notifyNeedUser(InfoMatchDTO infoMatchDTO) throws Exception {
		 
			 infoMatchDTO.setType(infoMatchDTO.getType() == 1?2:1);
			 List<MyListVO> suitUsers = deviceMapper.suitUser(infoMatchDTO);
			 if(CollectionUtils.isNotEmpty(suitUsers)) {
				 for (MyListVO myListVO : suitUsers) {
					 String position = valueOperations.get(PositionHandlerMethodArgumentResolver.LAST_POSITION+"_"+myListVO.getMemberId());
						if(position != null) {
							String[] ll = position.split("-");
							if(ll != null) {
					        	PositionDTO pd = new PositionDTO();
					        	pd.setLatitude(Double.parseDouble(ll[1]));
					        	pd.setLongitude(Double.parseDouble(ll[0]));
					            Point2D pointDD = new Point2D.Double(infoMatchDTO.getLongitude(), infoMatchDTO.getLatitude());
					            Point2D pointXD = new Point2D.Double(pd.getLongitude(), pd.getLatitude());
					        	Double distance = PositionUtil.getDistance(pointDD,pointXD)/1000;
					        	if((distance) < 40) { //如果距离需求者的距离小于40KM则通知此用户
					        		//发送模板信息
					        		NotifyRequest notifyRequest = new NotifyRequest();
					        		notifyRequest.setType(TemplateEnum.MATCH_SUCCESS.getValue());
					        		MatchSuccessVO matchSuccessVO = new MatchSuccessVO();
					        		matchSuccessVO.setPublishTime(new Date());
					        		matchSuccessVO.setMemberId(myListVO.getMemberId());
					        		matchSuccessVO.setAddress(infoMatchDTO.getAddress());
					        		matchSuccessVO.setDeviceName(infoMatchDTO.getDeviceName());
					        		matchSuccessVO.setXcxOpenId(myListVO.getXcxOpenId());
					        		matchSuccessVO.setPage((infoMatchDTO.getType() == 1?"/pages/index/index?share_query=/pages/rental_detail/rental_detail&id=" + infoMatchDTO.getInfoId():"/pages/index/index?share_query=/pages/rent_detail/rent_detail&id="+infoMatchDTO.getInfoId()));
					        		matchSuccessVO.setRemark("尊敬的<最快租机械>用户我们检测的在距离您"+formatDouble(distance)+"km的地方，有用户发布的一条<"+(infoMatchDTO.getType()==1?"求租":"出租")+":"+infoMatchDTO.getDeviceName()+">"
					        				+ ",若您感兴趣，请点击下方查看详情");
					        		notifyRequest.setMatchSuccessVO(matchSuccessVO);
					        		templateMessageStragegy.excute(notifyRequest);
					        	}
							}
						}
				}
			 }
		 
	 }
	 
	 
	    /**
	     * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
	     * 应该是这样使用：logger.info(String.format("%.2f", d));
	     * @param d
	     * @return
	     */
	    public static String formatDouble(double d) {
	        return String.format("%.2f", d);
	    }
	    
	    
//	    public static void main(String[] args) {
//	           Point2D pointDD = new Point2D.Double(117.34076, 31.79937);
//	            Point2D pointXD = new Point2D.Double(117.843361, 31.9043977);
//	        	Double distance = PositionUtil.getDistance(pointDD,pointXD)/1000;
//	        	System.out.println(String.format("%.2f", distance));
//		}
}
