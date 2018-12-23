<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/formValidator4.0.1/validator.css" />
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidator-4.0.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidatorRegex.js"></script>
	<script src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx }/static/js/myArea.js"></script>
	<script src="${ctx }/static/js/myCar.js"></script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty sellerInfo.infoId }">
			                  完善卖车信息:
			    </c:when>
			    <c:otherwise>
			                  新增卖车信息:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="sellerInfoForm" action="${ctx }/seller/info/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="infoId" name="infoId" value="${sellerInfo.infoId }"/>
	    
		<ul class="ulColumn2">
		    <li>
			    <span class="item_name" style="width: 120px;">姓名：</span> 
			    <input type="text" class="textbox textbox_225" name="name" value="${sellerInfo.name }" />
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">手机号：</span> 
			    <input type="text" class="textbox textbox_225" id="phone" name="phone" value="${sellerInfo.phone }" />
			    <span id="phoneTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">区域：</span> 
			    <select class="select" id="province" name="province" onchange="return changeAreaOfDistrict('province', 'city');" onfocus="return changeAreaOfDistrict('province', 'city');" style="width:120px;">
		           <option value="">---请选择省---</option>
		           <c:forEach items="${provinceList }" var="province">
		           	<option value="${province.areaCode }"
		           		<c:if test="${province.areaCode ==  sellerInfo.province}">
		           			selected = selected
		           		</c:if>
		           	>${province.areaName }</option>
		           </c:forEach>
		        </select>
		        <select class="select" id="city" name="city" style="width:120px;">
		            <c:choose>
		            	<c:when test="${not empty sellerInfo.infoId }">
		            	    <c:forEach items="${cityList }" var="city">
		            	       <option value="${city.areaCode }"
		            	           <c:if test="${city.areaCode ==  sellerInfo.city}">
					           			selected = selected
					           		</c:if>
		            	       >${city.areaName }</option>
		            	    </c:forEach>
		            	</c:when>
		            	<c:otherwise>
		            		   <option value="">---请选择市---</option>
		            	</c:otherwise>
		            </c:choose>
		        </select>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">品牌/车系/车型：</span> 
			    <select class="select" id="brandId" name="brandId" onchange="return searchCarModelList('brandId', 'modelId', 'typeId');" onfocus="return searchCarModelList('brandId', 'modelId', 'typeId');" style="width:120px;">
		            <option value=''>---请选择品牌---</option>
		            <c:forEach items="${carBrandList }" var="brand">
		               <option value="${brand.brandId }"
		               		<c:if test="${brand.brandId eq sellerInfo.brandId }">
		               		    selected = "selected"
		               		</c:if>
		               >${brand.brandName }</option>
		            </c:forEach>
		        </select>
		        <select class="select" id="modelId" name="modelId" onchange="return searchCarTypeList('modelId', 'typeId');" onfocus="return searchCarTypeList('modelId', 'typeId');" style="width:120px;">
		            <c:choose>
		              	<c:when test="${not empty sellerInfo.infoId }">
		              		<c:forEach items="${carModelList.parent}" var="pModel">
		              		    <c:forEach items="${pModel.child}" var="model">
			              			<option value="${model.modelId }"
					               		<c:if test="${model.modelId eq sellerInfo.modelId }">
					               		    selected = "selected"
					               		</c:if>
					                >${model.modelName }</option>
			              		</c:forEach>
		              		</c:forEach>
		              	</c:when>
		              	<c:otherwise>
		              		<option value=''>---请选择车系---</option>
		              	</c:otherwise>
		            </c:choose>
		        </select>
		        <select class="select" id="typeId" name="typeId" style="width:180px;">
		            <c:choose>
		              	<c:when test="${not empty sellerInfo.infoId }">
		              		<c:forEach items="${carTypeList.parent}" var="pType">
		              		    <c:forEach items="${pType.child}" var="type">
			              			<option value="${type.typeId }"
					               		<c:if test="${type.typeId eq sellerInfo.typeId }">
					               		    selected = "selected"
					               		</c:if>
					                >${type.typeName }</option>
			              		</c:forEach>
		              		</c:forEach>
		              	</c:when>
		              	<c:otherwise>
		              		<option value=''>---请选择车型---</option>
		              	</c:otherwise>
		            </c:choose>
		        </select>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">公里数：</span> 
			    <input type="text" class="textbox textbox_225" id="runMileage" name="runMileage" value="${sellerInfo.runMileage }" />
				(单位:万公里)
				<span id="runMileageTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">上牌时间：</span> 
			    <input type="text" class="textbox textbox_225" id="carLicenseTime" name="carLicenseTime" value="${sellerInfo.carLicenseTime }" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
				<span id="carLicenseTimeTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">排放标准：</span> 
			    <input type="text" class="textbox textbox_225" name="emissionStandard" value="${sellerInfo.emissionStandard }" />
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">其他：</span> 
			    <textarea name="otherInfo" rows="2" cols="2" class="textbox" style="width:400px;height:80px;">${sellerInfo.otherInfo }</textarea> 
			</li>
			
			<li>
				<span class="item_name" style="width: 120px;"></span> 
				<input id="submitBtn" type="button" class="link_btn" value="更新/保存" />
                <span class="item_name" style="width:20px;"></span> 
				<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
			</li>
		</ul>
	</form>

<script type="text/javascript">
	 $(document).ready(function(){
		$('#submitBtn').click(function(){
		   $("#sellerInfoForm").submit();
		});
		
		$('#back').click(function(){
		    location.href = "${ctx }/seller/info/toInitList";
		});
	 });
</script>

<script type="text/javascript">
	$(document).ready(function (){
		$.formValidator.initConfig({
			formID:"sellerInfoForm",
			debug:false,
			submitOnce:true,
			errorFocus:true,
			submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
		});
	    
	    $("#phone").formValidator({empty:false,onShow:"请输入手机号",onFocus:"请输入手机号",onCorrect:"验证通过",onEmpty:"请输入手机号"})
				   .inputValidator({min:11,max:11,onError:"请输入手机号"})
				   .regexValidator({regExp:"^1\\d{10}$",onError:"手机号格式不正确，应为11位的号码"})
				   <c:if test="${not empty sellerInfo.infoId}">
				      .defaultPassed()
				   </c:if>;
	
	    $("#carLicenseTime").formValidator({empty:false,onShow:"请选择上牌时间",onFocus:"请选择上牌时间",onCorrect:"验证通过",onEmpty:"请选择上牌时间"})
						    .inputValidator({min:1,onError:"请选择上牌时间"})	
		                    <c:if test="${not empty sellerInfo.infoId}">
						       .defaultPassed()
						    </c:if>;
				   
	    $("#runMileage").formValidator({empty:false,onShow:"请输入公里数",onFocus:"请输入公里数",onCorrect:"验证通过"})
	               	    .inputValidator({min:1,onError:"公里数必输入，且为浮点数"})
	                    .regexValidator({regExp:"^\\d{1,8}(\\.\\d{1,2})?$",onError:"公里数为浮点数，最多精确到小数点后2位"})
	                    <c:if test="${not empty sellerInfo.infoId}">
				          .defaultPassed()
				        </c:if>;
	});
</script>

</body>
</html>