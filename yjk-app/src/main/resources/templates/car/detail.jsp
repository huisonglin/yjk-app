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
	<script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidator-4.0.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidatorRegex.js"></script>
</head>
<body>
    <div class="basic_hd">
	  <ul>
	    <li><a href="${ctx }/car/toDetail?id=${car.id }" class="active">基本信息</a></li>
	    <li><a href="${ctx }/car/img/info?carId=${car.id }">车辆图片</a></li>
	    <li><a href="${ctx }/car/check/info?carId=${car.id }">车辆检测</a></li>
	  </ul>
	</div>
	
	<div class="page_title">
		<h2 class="fl">
			车源基础信息详情
		</h2>
	</div>
	
	<ul class="ulColumn2">
		<li>
		    <span class="item_name" style="width: 120px;">品牌/车系/车型：</span> 
		    <select class="select" name="brandId" style="width:120px;" disabled="disabled">
	            <option value=''>---请选择品牌---</option>
	            <c:forEach items="${brandList }" var="brand">
	               <option value="${brand.brandId }"
	                  <c:if test="${brand.brandId eq car.brandId}">
	                  	 selected = "selected"
	                  </c:if>
	               >${brand.brandName }</option>
	            </c:forEach>
	        </select>
	        
	        <select class="select" name="modelId" style="width:120px;" disabled="disabled">
	            <c:choose>
	              	<c:when test="${not empty car.id }">
	              		<c:forEach items="${carModelList.parent}" var="pModel">
	              		    <c:forEach items="${pModel.child}" var="model">
		              			<option value="${model.modelId }"
				               		<c:if test="${model.modelId eq car.modelId }">
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
	        
	        <select class="select" name="typeId" style="width:180px;" disabled="disabled">
	            <c:choose>
	              	<c:when test="${not empty car.id }">
	              		<c:forEach items="${carTypeList.parent}" var="pType">
	              		    <c:forEach items="${pType.child}" var="type">
		              			<option value="${type.typeId }"
				               		<c:if test="${type.typeId eq car.typeId }">
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
		    <span class="item_name" style="width: 120px;">名称：</span> 
		    <input type="text" class="textbox textbox_295" name="title" value="${car.title }" disabled="disabled" /> 
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">描述：</span> 
		    <textarea name="description" rows="2" cols="2" class="textbox" style="width:400px;height:80px;" disabled="disabled">${car.description }</textarea> 
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">车辆售价：</span> 
		    <input type="text" class="textbox textbox_225" name="sellPrice" value="${car.sellPrice }" disabled="disabled" />
		    (单位:万元)
		    		    
		    <span class="item_name" style="width: 120px; margin-left:100px;">上牌时间：</span> 
		    <input type="text" class="textbox textbox_225" name="carLicenseTime" value="${car.carLicenseTime }" disabled="disabled" /> 
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">车龄：</span> 
		    <input type="text" class="textbox textbox_225" name="carYear" value="${car.carYear }" disabled="disabled" /> 
		    (单位:年)
		    
		    <span class="item_name" style="width: 120px; margin-left:114px;">表显里程：</span> 
		    <input type="text" class="textbox textbox_225" name="runMileage" value="${car.runMileage }" disabled="disabled" />
		    (单位:万公里)
		</li>
		<li>
		    <span class="item_name" style="width:120px;">实际里程：</span> 
		    <input type="text" class="textbox textbox_225" name="actualMileage" value="${car.actualMileage }" disabled="disabled" />
		    (单位:万公里)
		    
		    <span class="item_name" style="width: 120px; margin-left:86px;">是否4S店保养：</span> 
		    <select class="select" name="maintenanceCondition" style="width:80px;" disabled="disabled">
		    	<option value="1"
		    	    <c:if test="${car.maintenanceCondition eq 1}">
		    	        selected = "selected"
		    	    </c:if>
		    	>是</option>
		    	<option value="2"
		    		<c:if test="${car.maintenanceCondition eq 2}">
		    	        selected = "selected"
		    	    </c:if>
		    	>否</option>
		    </select>
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">年检到期时间：</span> 
		    <input type="text" class="textbox textbox_225" name="annualInspection" value="${car.annualInspection }" disabled="disabled" /> 
		    
		    <span class="item_name" style="width: 120px; margin-left:174px;">交强险到期时间：</span> 
		    <input type="text" class="textbox textbox_225" name="clivta" value="${car.clivta }" disabled="disabled" /> 
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">商业险到期时间：</span> 
		    <input type="text" class="textbox textbox_225" name="commercialInsurance" value="${car.commercialInsurance }" disabled="disabled" /> 
		    
		    <span class="item_name" style="width: 120px; margin-left:175px;">级别：</span> 
		    <select class="select" name="levelId" style="width:120px;" disabled="disabled">
		    	<c:forEach items="${levelList }" var="level">
		    		<option value="${level.id }"
		    			<c:if test="${level.id eq car.levelId }">
		    				seleted = "seleted"
		    			</c:if>
		    		>${level.levelName }</option>
		    	</c:forEach>
		    </select>
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">车辆所在地：</span> 
		    <select class="select" name="provinceCode" style="width:120px;" disabled="disabled">
	           <option value="">---请选择省---</option>
	           <c:forEach items="${provinceList }" var="province">
	           	<option value="${province.areaCode }"
	           		<c:if test="${province.areaCode eq  car.provinceCode}">
	           			selected = selected
	           		</c:if>
	           	>${province.areaName }</option>
	           </c:forEach>
	        </select>
	        
	        <select class="select" name="cityCode" style="width:120px;" disabled="disabled">
	            <c:choose>
	            	<c:when test="${not empty car.id }">
	            	    <c:forEach items="${cityList }" var="city">
	            	       <option value="${city.areaCode }"
	            	           <c:if test="${city.areaCode eq  car.cityCode}">
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
		    <span class="item_name" style="width: 120px;">车辆上牌地：</span> 
		    <select class="select" name="provinceCard" style="width:120px;" disabled="disabled">
	           <option value="">---请选择省---</option>
	           <c:forEach items="${provinceList }" var="province">
	           	<option value="${province.areaCode }"
	           		<c:if test="${province.areaCode eq  car.provinceCard}">
	           			selected = selected
	           		</c:if>
	           	>${province.areaName }</option>
	           </c:forEach>
	        </select>
	        
	        <select class="select" name="cityCard" style="width:120px;" disabled="disabled">
	            <c:choose>
	            	<c:when test="${not empty car.id }">
	            	    <c:forEach items="${cityList }" var="city">
	            	       <option value="${city.areaCode }"
	            	           <c:if test="${city.areaCode eq car.cityCard}">
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
		    <span class="item_name" style="width: 120px;">排量值：</span>
			<input type="text" class="textbox textbox_225" name="displacementValue" value="${car.displacementValue }" disabled="disabled"/> 
			(单位:L)
              
              <span class="item_name" style="width: 120px; margin-left:279px;">座位数：</span>
			  <select name="seatValue" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${seatList }" var="s">
                    <option value="${s.seatValue }"
                        <c:if test="${s.seatValue eq car.seatValue }">
                        	selected = "selected"
                        </c:if>
                    >
                    ${s.seatName }</option>
                 </c:forEach>
              </select>
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">排放标准：</span>
			<select name="emissionStandardId" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${emissionStandardList }" var="e">
                    <option value="${e.id }"
                        <c:if test="${e.id eq car.emissionStandardId }">
                        	selected = "selected"
                        </c:if>
                    >
                    ${e.standardName }</option>
                 </c:forEach>
              </select>
              
              <span class="item_name" style="width: 120px; margin-left:279px;">变速箱：</span>
			  <select name="gearboxId" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${gearboxList }" var="g">
                    <option value="${g.id }"
                    	<c:if test="${g.id eq car.gearboxId }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${g.gearboxName }</option>
                 </c:forEach>
              </select>
		</li>
		<li>
		   <span class="item_name" style="width: 120px;">车身颜色：</span>
		   <select name="exteriorColorId" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${exteriorColorList }" var="color">
                    <option value="${color.id }"
                    	<c:if test="${color.id eq car.exteriorColorId }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${color.colorName }</option>
                 </c:forEach>
              </select>
              
		    <span class="item_name" style="width: 120px; margin-left:279px;">内饰颜色：</span>
			<select name="interiorColorId" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${interiorColorList }" var="color">
                    <option value="${color.id }"
                    	<c:if test="${color.id eq car.interiorColorId }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${color.colorName }</option>
                 </c:forEach>
              </select>
		</li>
		<li>
		   <span class="item_name" style="width: 120px;">燃料类型：</span>
		   <select name="fuelTypeValue" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${fuelTypeList }" var="f">
                    <option value="${f.fuelTypeValue }"
                    	<c:if test="${f.fuelTypeValue eq car.fuelTypeValue }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${f.fuelTypeName }</option>
                 </c:forEach>
              </select>
              
		    <span class="item_name" style="width: 120px; margin-left:279px;">国别：</span>
			<select name="vehicleCountryValue" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${vehicleCountryList }" var="v">
                    <option value="${v.vehicleCountryValue }"
                    	<c:if test="${v.vehicleCountryValue eq car.vehicleCountryValue }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${v.vehicleCountryName }</option>
                 </c:forEach>
              </select>
		</li>
		<li>
		   <span class="item_name" style="width: 120px;">驱动：</span>
		   <select name="driveValue" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${driveList }" var="d">
                    <option value="${d.driveValue }"
                    	<c:if test="${d.driveValue eq car.driveValue }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${d.driveName }</option>
                 </c:forEach>
            </select>
              
		    <span class="item_name" style="width: 120px; margin-left:279px;">车辆使用性质：</span>
			<select name="useByValue" class="select" style="width:120px;" disabled="disabled">
                 <c:forEach items="${useByList }" var="u">
                    <option value="${u.useByValue }"
                    	<c:if test="${u.useByValue eq car.useByValue }">
                        	selected = "selected"
                       </c:if>
                    >
                    ${u.useByName }</option>
                 </c:forEach>
              </select>
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">vin码：</span> 
		    <input type="text" class="textbox textbox_225" name="vin" value="${car.vin }" disabled="disabled" /> 

		    <span class="item_name" style="width: 120px; margin-left:70px;">发动机号：</span> 
		    <input type="text" class="textbox textbox_225" name="engineNo" value="${car.engineNo }" disabled="disabled" />
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">车牌号：</span>
		    <select class="select" name="plateNoBefor" style="width:50px;" disabled="disabled">
		       <option value="${car.plateNoBefor }">${car.plateNoBefor }<option>
		    </select>
		    <input type="text" class="textbox textbox_225" name="plateNo" value="${car.plateNo }" disabled="disabled" /> 
		
			<span class="item_name" style="width: 120px; margin-left:15px;">是否超值：</span> 
		    <select class="select" name="isValue" style="width:80px;" disabled="disabled">
		    	<option value="1"
		    	    <c:if test="${car.isValue eq 1}">
		    	        selected = "selected"
		    	    </c:if>
		    	>是</option>
		    	<option value="2"
		    		<c:if test="${car.isValue eq 2}">
		    	        selected = "selected"
		    	    </c:if>
		    	>否</option>
		    </select>
		</li>
		<li>
		    <span class="item_name" style="width: 120px;">是否准新车：</span> 
		    <select class="select" name="isNew" style="width:80px;" disabled="disabled">
		    	<option value="1"
		    	    <c:if test="${car.isNew eq 1}">
		    	        selected = "selected"
		    	    </c:if>
		    	>是</option>
		    	<option value="2"
		    		<c:if test="${car.isNew eq 2}">
		    	        selected = "selected"
		    	    </c:if>
		    	>否</option>
		    </select>
		
			<span class="item_name" style="width: 120px;">是否急售：</span> 
		    <select class="select" name="isJishou" style="width:80px;" disabled="disabled">
		    	<option value="1"
		    	    <c:if test="${car.isJishou eq 1}">
		    	        selected = "selected"
		    	    </c:if>
		    	>是</option>
		    	<option value="2"
		    		<c:if test="${car.isJishou eq 2}">
		    	        selected = "selected"
		    	    </c:if>
		    	>否</option>
		    </select>
		</li>
		<!-- 审核不通过 -->
		<c:if test="${car.status eq 3 }">
		<li>
		    <span class="item_name" style="width:120px;color:red;font-weight:bold;">审核情况说明：</span>
		    <textarea name="checkRemark" rows="2" cols="2" class="textbox" disabled="disabled" style="width:400px;height:80px;">${car.checkRemark }</textarea> 
		</li>
		</c:if>
		
		
		<li>
			<span class="item_name" style="width: 120px;"></span> 
			<input id="backBtn" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
		</li>
	</ul>
	
<script type="text/javascript">
	$(document).ready(function (){
		$("#backBtn").click(function (){
			location.href = "${ctx }/car/toList";
		});
	});
</script>
</body>
</html>