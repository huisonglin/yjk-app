package com.yjk.app.dto;

import java.math.BigDecimal;

public class ReleasePurchaseInfoDTO {

	//名称
	private String name;
	//二级机型ID
	private BigDecimal twoStageModeId;
	//机型ID
	private Long modeId;
	//规格ID
	private Long specId;
	//求购地点
	private String adress;
	//纬度
	private Double latitude;
	//成色要求
	private String useDegree;
	//经度
	private Double longitude;
	//期望价格
	private BigDecimal expectedPrice;
	//备注
	private String remark;
	//1手动发布 2语音发布
	private Integer type;
	//语音内容
	private String voice;

}
