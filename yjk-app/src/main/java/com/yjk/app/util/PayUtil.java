package com.yjk.app.util;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.GenerateOpenIdDTO;
import com.yjk.app.dto.XcxUnifiedorderDTO;
import com.yjk.app.exception.RRException;
import com.yjk.app.vo.Jscode2SessionVO;
import com.yjk.app.vo.XcxPayPramsVO;

import tk.mybatis.mapper.entity.Config;

@Component
public class PayUtil {

	@Autowired
	WeiXinConfig weiXinConfig;
	/**
	 * 生成小程序支付参数
	 * @param xcxUnifiedorderDTO
	 * @return
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IntrospectionException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public R xcxAccessPayParam(XcxUnifiedorderDTO xcxUnifiedorderDTO) throws Exception {
		xcxUnifiedorderDTO.setTrade_type(weiXinConfig.getTradeType());
		xcxUnifiedorderDTO.setAppid(weiXinConfig.getXcxAppId());
		xcxUnifiedorderDTO.setMch_id(weiXinConfig.getMechId());
		xcxUnifiedorderDTO.setAttach(weiXinConfig.getApikey());
		xcxUnifiedorderDTO.setKey(weiXinConfig.getApikey());
		xcxUnifiedorderDTO.setNotify_url(weiXinConfig.getXcxAccessPayParamUrl()+"/notify");
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getXcxAccessPayParamUrl(), BeanUtils.describe(xcxUnifiedorderDTO));
		return R.ok().put("data", dealResult(result,XcxPayPramsVO.class));
	}
	
	public Jscode2SessionVO xcxAccessOpenId(GenerateOpenIdDTO generateOpenIdDTO)throws Exception {
		generateOpenIdDTO.setAppId(weiXinConfig.getXcxAppId());
		generateOpenIdDTO.setAppSecret(weiXinConfig.getXcxAppSecret());
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getXcxAccessOpenIdUrl(), BeanUtils.describe(generateOpenIdDTO));
		return (Jscode2SessionVO)dealResult(result,Jscode2SessionVO.class);
	}
	
	@SuppressWarnings("unchecked")
	public static  <T> Object dealResult(HttpClientResult result,Class<T> clazz)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		if(result.getCode() == 200) {
			String content = result.getContent();
			R r = JSONUtil.parse(content, R.class);
			Object msg = r.get(R.CODE);
			if(msg.equals(R.SUCCESS)) {
				Object rsg = r.get("data");
				if(rsg != null) {

					Object data = MapOrBeanConvert.convertMap(clazz, (Map<String, String>)rsg);
					return data;
				}else {
					throw new RRException("请求异常");
				}
			}else {
				throw new RRException(r.get(R.MSG).toString());
			}
		}else {
			throw new RRException("代理服务器请求失败");
		}
	}
}
