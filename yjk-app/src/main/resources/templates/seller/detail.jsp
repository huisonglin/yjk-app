<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
	<script src="${ctx }/static/js/myArea.js"></script>
	<script src="${ctx }/static/js/myCar.js"></script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			卖车信息详情
		</h2>
	</div>
	
	<ul class="ulColumn2">
	    <li>
		    <span class="item_name" style="width: 120px;">姓名：</span> 
		    <input type="text" class="textbox textbox_225" name="name" value="${sellerInfo.name }" disabled="disabled" />
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">手机号：</span> 
		    <input type="text" class="textbox textbox_225" name="phone" value="${sellerInfo.phone }" disabled="disabled" />
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">区域：</span> 
		    <select class="select" id="province" name="province" onchange="return changeAreaOfDistrict('province', 'city');" onfocus="return changeAreaOfDistrict('province', 'city');" style="width:120px;" disabled="disabled">
	           <option value="">---请选择省---</option>
	           <c:forEach items="${provinceList }" var="province">
	           	<option value="${province.areaCode }"
	           		<c:if test="${province.areaCode ==  sellerInfo.province}">
	           			selected = selected
	           		</c:if>
	           	>${province.areaName }</option>
	           </c:forEach>
	        </select>
	        <select class="select" id="city" name="city" style="width:120px;" disabled="disabled">
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
		    <select class="select" id="brandId" name="brandId" onchange="return searchCarModelList('brandId', 'modelId', 'typeId');" onfocus="return searchCarModelList('brandId', 'modelId', 'typeId');" style="width:120px;" disabled="disabled">
	            <option value=''>---请选择品牌---</option>
	            <c:forEach items="${carBrandList }" var="brand">
	               <option value="${brand.brandId }"
	               		<c:if test="${brand.brandId eq sellerInfo.brandId }">
	               		    selected = "selected"
	               		</c:if>
	               >${brand.brandName }</option>
	            </c:forEach>
	        </select>
	        <select class="select" id="modelId" name="modelId" onchange="return searchCarTypeList('modelId', 'typeId');" onfocus="return searchCarTypeList('modelId', 'typeId');" style="width:120px;" disabled="disabled">
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
	        <select class="select" id="typeId" name="typeId" style="width:180px;" disabled="disabled">
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
		    <input type="text" class="textbox textbox_225" name="runMileage" value="${sellerInfo.runMileage }" disabled="disabled" />
		    (单位:万公里)
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">上牌时间：</span> 
		    <input type="text" class="textbox textbox_225" name="carLicenseTime" value="${sellerInfo.carLicenseTime }" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" disabled="disabled" />
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">排放标准：</span> 
		    <input type="text" class="textbox textbox_225" name="emissionStandard" value="${sellerInfo.emissionStandard }" disabled="disabled" />
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">其他：</span> 
		    <textarea name="otherInfo" rows="2" cols="2" class="textbox" style="width:400px;height:80px;" disabled="disabled">${sellerInfo.otherInfo }</textarea> 
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">分配到门店：</span> 
		    <input type="text" class="textbox textbox_225" value="${sellerInfo.storeName }" disabled="disabled" />
		    <span class="item_name" style="width: 150px;">分配到门店时间：</span>
		    <input type="text" class="textbox textbox_225" value="${sellerInfo.toStoreTime }" disabled="disabled" />
		</li>
		<c:if test="${sellerInfo.status eq 4 or sellerInfo.status eq 5 or sellerInfo.status eq 6 or sellerInfo.status eq 7}">
			<li>
			    <span class="item_name" style="width: 120px;">分配到业务员：</span> 
			    <input type="text" class="textbox textbox_225" value="${sellerInfo.storeUserName }" disabled="disabled" />
			    <span class="item_name" style="width: 150px;">分配到业务员时间：</span>
		    <input type="text" class="textbox textbox_225" value="${sellerInfo.toStoreUserTime }" disabled="disabled" />
			</li>
		</c:if>
		
		<li>
			<span class="item_name" style="width:20px;"></span> 
			<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
		</li>
	</ul>

<script type="text/javascript">
	 $(document).ready(function(){
		$('#back').click(function(){
		   history.go(-1);
		});
	 });
</script>

</body>
</html>