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
	
	<script type="text/javascript">
         function showSelectStorePage(){
        	 var openUrl = '${ctx}/seller/info/store/toList';
             var iWidth=800; //弹出窗口的宽度;
       	     var iHeight=800; //弹出窗口的高度;
       	     var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
       	     var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
       	     window.open(openUrl,"门店选择","scrollbars=yes,resizable=yes,height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft); 
         };
    </script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			分配门店
		</h2>
	</div>
	
	<form id="sellerInfoForm" action="${ctx }/seller/info/dealToStore" method="post" onsubmit="return checkData();">
	    <!-- hiden -->
	    <input type="hidden" id="infoId" name="infoId" value="${sellerInfo.infoId }"/>
	    
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
			    <span class="item_name" style="width: 120px;">说明：</span> 
			    <textarea name="remark" rows="2" cols="2" class="textbox" style="width:400px;height:80px;" disabled="disabled">${sellerInfo.remark }</textarea> 
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
			    <span class="item_name" style="width: 120px;">分配门店：</span> 
			    <input type="text" class="textbox textbox_225" id="storeName" value="" readonly />
			    <input type="hidden" id="storeId" name="storeId" value="" />
			    
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="javascript:void(0);" onclick="showSelectStorePage();">选择门店</a>
			</li>
			
			<li>
				<span class="item_name" style="width: 120px;"></span> 
				<input id="submitBtn" type="button" class="link_btn" value="确定分配" />
                
                <span class="item_name" style="width:20px;"></span> 
				<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
			</li>
		</ul>
	</form>

<script type="text/javascript">
     function checkData(){
    	 var storeId = $("#storeId").val();
    	 if(!storeId){
    		 alert("尚未选择分配门店,请选择!");
    		 return false;
    	 }else{
    		 return true;
    	 }
     }
     
	 $(document).ready(function(){
		$('#submitBtn').click(function(){
		   $("#sellerInfoForm").submit();
		});
		
		$('#back').click(function(){
			 location.href = "${ctx }/seller/info/toEdList";
	    });
	 });
</script>
</body>
</html>