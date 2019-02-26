/**
 * 
 */package com.yjk.app.vo;
/** 
* @author : 刘尊亮
* @date 创建时间：2019年2月26日 下午3:46:43 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */
public class Point2D {
	Double X;
	
	Double Y;

	public Point2D() {
		
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public Point2D(Double x, Double y) {
		super();
		X = x;
		Y = y;
	}

	public Double getX() {
		return X;
	}

	public void setX(Double x) {
		X = x;
	}

	public Double getY() {
		return Y;
	}

	public void setY(Double y) {
		Y = y;
	}
	
	
}
