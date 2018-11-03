package com.yjk.app.resolver;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.yjk.app.annotation.Position;
import com.yjk.app.dto.PositionDTO;
import com.yjk.app.exception.RRException;
import com.yjk.app.util.JwtUtils;


@Component
public class PositionHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
	
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	@Autowired
	JwtUtils jwtUtils;
	
	public static  String LON = "longitude";
	
	public static  String LAT = "latitude";
	
	public static  String LAST_POSITION = "last_position"; //上次的经纬度

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(PositionDTO.class) && parameter.hasParameterAnnotation(Position.class);
	}
	

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
		Object lon = request.getParameter(LON);
		Object lat = request.getParameter(LAT);
        //获取用户ID
        //Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        //获取用户凭证
		String userId = null;
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }
        if(token != null) {
        	 userId = jwtUtils.getClaimByToken(token).getSubject();
        }
		if(lon == null || lat == null||"4.9E-324".equalsIgnoreCase(String.valueOf(lon))||"4.9E-324".equalsIgnoreCase(String.valueOf(lat))
				||Double.valueOf(lat.toString())==0.0||Double.valueOf(lon.toString())==0.0) {
			if(userId != null) {
				String position = valueOperations.get(LAST_POSITION+"_"+userId);
				if(position != null) {
					String[] ll = position.split("-");
					if(ll != null) {
			        	PositionDTO pd = new PositionDTO();
			        	pd.setLatitude(Double.parseDouble(ll[0]));
			        	pd.setLongitude(Double.parseDouble(ll[1]));
			        	return pd;
					}
				}else {
					throw new RRException("定位失败，请检查是否开启定位");
				}
			}else {
				throw new RRException("定位失败，请检查是否开启定位");
			}	
		}else {
	        if(userId != null) {
	        	//将本次经纬度存入redis中
	        	valueOperations.set(LAST_POSITION+"_"+userId, lon.toString()+"-"+lat.toString());
	        }
        	PositionDTO position = new PositionDTO();
        	position.setLatitude(Double.parseDouble(lat.toString()));
        	position.setLongitude(Double.parseDouble(lon.toString()));
        	return position;

		}
		return null;
	}

}
