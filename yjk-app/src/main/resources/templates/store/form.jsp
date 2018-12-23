<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<link href="${ctx }/static/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/formValidator4.0.1/validator.css" />
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidator-4.0.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidatorRegex.js"></script>
	<script src="${ctx }/static/js/myArea.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=31feb128e414ec3357de7f246a7493c2"></script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty store.storeId }">
			                  修改门店:
			    </c:when>
			    <c:otherwise>
			                  新增门店:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="storeForm" action="${ctx }/store/addOrUpdate" method="post" onsubmit="return checkData();">
	    <!-- hiden -->
	    <input type="hidden" id="storeId" name="storeId" value="${store.storeId }"/>
	    <c:if test="${not empty banner.bannerId }">
	       <input type="hidden" id="status" name="status" value="${store.status }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
			<li>
			    <div id="fileQueue"></div> 
			    <span class="item_name" style="width:120px;">门头图片：</span>
			    <label class="single_selection">
			         <input type="file" id="upFile" name="upFile" />
		        </label>
		        <input type="hidden" id="storeImg" name="storeImg" value="${store.storeImg }"/>
			</li>
			<li>
			    <span class="item_name" style="width:120px;"></span>
			    <label class="single_selection">
			         <div id="imgViewDiv" style="width:100px;height:60px;">
				         <c:choose>
				         	<c:when test="${not empty store.storeId  and not empty store.storeImg}">
				         		<img src="${img_base_view_path}${store.storeImg }" style="width:100px;height:60px;" />
				         	</c:when>
				         	<c:otherwise>
				         	    <img src="${ctx }/static/images/nopic.jpg" style="width:100px;height:60px;" />
				         	</c:otherwise>
				         </c:choose>
			         </div>
				</label>
                <span style="margin-left:30px; color:red;">您可以上传不小于812*378像素同比例图片，支持Jpg、Png、Gif格式，图片大小不高于1MB</span>
		    </li>
		    <li>
			    <span class="item_name" style="width: 120px;">门店名称：</span> 
			    <input type="text" class="textbox textbox_225" id="storeName" name="storeName" value="${store.storeName }" />
				<span id="storeNameTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">联系电话：</span> 
			    <input type="text" class="textbox textbox_225" id="tel" name="tel" value="${store.tel }" />
			    <span id="telTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">区域选择：</span> 
			    <select class="select" id="province" name="province" onchange="return changeAreaOfDistrict('province', 'city');" onfocus="return changeAreaOfDistrict('province', 'city');" style="width:120px;">
		            <option value="">---请选择省---</option>
		            <c:forEach items="${provinceList }" var="province">
		            	<option value="${province.areaCode }"
		            		<c:if test="${province.areaCode eq store.province }">
		            		    selected = "selected" 
		            		</c:if>
		            	>${province.areaName }</option>
		            </c:forEach>
		        </select>
		        <span id="provinceTip" class="item_name"></span>
		        
		        <select class="select" id="city" name="city" style="width:120px;">
		            <c:choose>
			            <c:when test="${not empty store.storeId }">
			            	<c:forEach items="${cityList }" var="city">
				            	<option value="${city.areaCode }"
				            		<c:if test="${city.areaCode eq store.city }">
				            		    selected = "selected" 
				            		</c:if>
				            	>${city.areaName }</option>
				            </c:forEach>
			            </c:when>
			            <c:otherwise>
			            	<option value="">---请选择市---</option>
			            </c:otherwise>
		            </c:choose>
		        </select>
		        <span id="cityTip" class="item_name"></span>
		        
		        <input type="text" class="textbox textbox_225" id="address" name="address" value="${store.address }" placeholder="请输入详细地址..." />
			    <span id="addressTip" class="item_name"></span>
			    
			    &nbsp;&nbsp;
			    <a href="javascript:void(0);" onclick="getLngAndLat();">获取经纬度</a>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">经度：</span> 
			    <input type="text" id="lng" name="lng" value="${store.lng }" readonly/>
			    
			    <span class="item_name" style="width: 120px;">纬度：</span> 
			    <input type="text" id="lat" name="lat" value="${store.lat }" readonly/>
			</li>
			<li>
		        <span class="item_name" style="width:120px;">状态：</span>
	            <c:forEach items="${storeStatuss }" var="status" varStatus="s">
	               <label class="single_selection">
	               <input type="radio" name="status" value="${status.value }"
	                 <c:choose>
		                 <c:when test="${not empty store.storeId }">
		                     disabled="disabled"
		                     <c:if test="${status.value eq store.status}">
			                     checked="checked"
			                 </c:if>
		                 </c:when>
		                 <c:otherwise>
		                     <c:if test="${s.index == 0}">
			                     checked="checked"
			                 </c:if>
		                 </c:otherwise>
		              </c:choose>
	              />
	              ${status.name }
	              </label>
	           </c:forEach>
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
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	
	function getPoint(addr){
		// 解析地址
		myGeo.getPoint(addr , function(point){
			if (point) {
				$("#lng").val(point.lng);
				$("#lat").val(point.lat);
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, addr);
	}
	
	function getLngAndLat(){
		var province = $("#province option:selected").html();
		var city = $("#city option:selected").html();
		var addr = $("#address").val();
		getPoint(province + city + addr);
	}
</script>
<script type="text/javascript" src="${ctx }/static/js/uploadify/override-fun.js"></script>
<script type="text/javascript">
	 $(document).ready(function(){
		 $('#upFile').uploadify({
	            'swf'      : '${ctx }/static/js/uploadify/uploadify.swf',
	            'uploader' : '${ctx }/common/file/upload/single;jsessionid=<%=request.getSession().getId()%>',
	            'auto'  : true,//是否自动提交
	            'fileObjName':'upFile',
	            'buttonText' : '选择图片...',
	            "fileSizeLimit" : '1024k',//1024k
	            'fileTypeExts' : '*.gif;*.jpg;*.jpeg;*.png;*.bmp;*.ico',
	            'multi'    : false,
	            'method'   :'post',
	            "queueID" : "fileQueue",
	            "formData" : { 'fileMiddleBaseDir': 'store/' },
	            'overrideEvents' : [ 'onDialogClose', 'onUploadError', 'onSelectError'],
	            'onSelect' : uploadify_onSelect,  
	            'onSelectError' : uploadify_onSelectError,  
	            'onUploadError' : uploadify_onUploadError, 
	            'onUploadSuccess' : function(file, data, response) {
	               var result = eval("(" + data + ")");
	               if(result.status){
		               var imgPath = result.fileImgViewBasePath + result.url;
		               $("#imgViewDiv").html("<img src='" + imgPath + "' style='width:100px;height:60px;' />");
			           $("#imgViewDiv").show();
			           
		               $("#storeImg").val(result.url);
	               }else{
	                  	alert(result.message);
	               }
	            },
	            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	                alert("文件上传失败，原因为: " + errorString);
	            }
	        });
		 
		   $('#submitBtn').click(function(){
		      $("#storeForm").submit();
		   });
		   
		   $('#back').click(function(){
			     window.location.href="${ctx }/store/toList";
			});
	 });
	 
	 function checkData(){
		   var lng = $("#lng").val();
		   var lat = $("#lat").val();
		   
		   if(!lng || !lat){
			   alert("你尚未获取经纬度,请确认");
			   return false;
		   }else{
			   return true;
		   }
	   }
</script>

<script type="text/javascript">
	$(document).ready(function (){
		$.formValidator.initConfig({
			formID:"storeForm",
			debug:false,
			submitOnce:true,
			errorFocus:true,
			submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
		});
		
		$("#storeName").formValidator({empty:false,onShow:"请输入门店名称",onFocus:"请输入最多200字符的门店名称",onCorrect:"验证通过",onEmpty:"请输入门店名称"})
					   .inputValidator({min:1,max:200,onError:"门店名称最多200字符，请确认！"})
					   .ajaxValidator({
					        type : "post",
					        dataType:"json",
					        async : true,
					        url : "${ctx }/store/storeNameIsUsed",
					        data:{"storeName":$("#storeName").val()}, 
					        success : function(data){
					            if(data && 'isUsed' == data.result){
					            	return false;
					            }else{
					            	return true;
					            }
					        },
					        buttons: $("#submitBtn"),
					        error: function(jqXHR, textStatus, errorThrown){alert("服务器忙，请重试！");},
					        onError: "门店名称已存在，请更换",
					        onWait:"正在对门店名称进行合法性校验，请稍候..."
					    })
					   <c:if test="${not empty store.storeId}">
					      .defaultPassed()
					   </c:if>;
						   
		$("#tel").formValidator({empty:false,onShow:"请输入联系电话",onFocus:"请输入联系电话",onCorrect:"验证通过",onEmpty:"请输入联系电话"})
					       .inputValidator({min:1,max:20,onError:"请输入联系电话"})
					       .regexValidator({regExp:"^(1\\d{10})|((\\d{3,4}(\\-)?)?\\d{7,8}((\\-)?\\d{1,4})?)$",onError:"联系电话格式不正确"})
						   <c:if test="${not empty store.storeId}">
						      .defaultPassed()
						   </c:if>;
		
		$("#province").formValidator({empty:false,onShow:"请选择门店省",onFocus:"请选择门店省",onCorrect:"验证通过",onEmpty:"请选择门店省"})
							  .inputValidator({min:1,onError:"请选择门店省"})		     
							  <c:if test="${not empty store.storeId}">
						      	.defaultPassed()
						      </c:if>;
						      
		$("#city").formValidator({empty:false,onShow:"请选择门店市",onFocus:"请选择门店市",onCorrect:"验证通过",onEmpty:"请选择门店市"})
						  .inputValidator({min:1,onError:"请选择门店市"})		     
						  <c:if test="${not empty store.storeId}">
					      	.defaultPassed()
					      </c:if>;
					      
	   $("#address").formValidator({empty:false,onShow:"请输入详细地址",onFocus:"请输入详细地址",onCorrect:"验证通过",onEmpty:"请输入详细地址"})
						  .inputValidator({min:1,onError:"请输入详细地址"})		     
						  <c:if test="${not empty store.storeId}">
					      	.defaultPassed()
					      </c:if>;
	});
</script>

</body>
</html>