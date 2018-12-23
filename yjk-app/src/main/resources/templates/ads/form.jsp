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
	<script type="text/javascript" src="${ctx }/static/js/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidator-4.0.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidatorRegex.js"></script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty ads.adsId }">
			                  修改广告:
			    </c:when>
			    <c:otherwise>
			                  新增广告:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="adsForm" action="${ctx }/ads/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="adsId" name="adsId" value="${ads.adsId }"/>
	    <c:if test="${not empty ads.adsId }">
	       <input type="hidden" id="status" name="status" value="${ads.status }"/>
	       <input type="hidden" id="pubId" name="pubId" value="${ads.pubId }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
			<li>
			    <div id="fileQueue"></div> 
			    <span class="item_name" style="width:120px;">广告图片：</span>
			    <label class="single_selection">
			         <input type="file" id="upFile" name="upFile" />
		        </label>
		        <input type="hidden" id="adsImg" name="adsImg" value="${ads.adsImg }"/>
			</li>
			<li>
			    <span class="item_name" style="width:120px;"></span>
			    <label class="single_selection">
			         <div id="adsImgViewDiv" style="width:100px;height:60px;">
				         <c:choose>
						   <c:when test="${not empty ads.adsId and not empty ads.adsImg }">
						   		<img src="${img_base_view_path}${ads.adsImg }" style="width:100px;height:60px;" />
						   </c:when>
						   <c:otherwise>
						   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
						   </c:otherwise>
						 </c:choose>
			         </div>
				</label>
				<span style="margin-left:30px; color:red;">您可以上传不小于812*378像素同比例图片，支持Jpg、Png、Gif格式，图片大小不高于1MB</span>
		    </li>
		    <li>
			    <span class="item_name" style="width: 120px;">跳转URL：</span> 
			    <input id="adsUrl" name="adsUrl" type="text" class="textbox" style="width:400px;" value="${ads.adsUrl }" />
				<span id="adsUrlTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">广告位置：</span> 
			    <select class="select" name="adsPositon" style="width:120px;">
			        <c:forEach items="${adsPositions }" var="position">
		           		<option value="${position.value }"
		           		   <c:if test="${position.value eq ads.adsPositon}">
		           		   		selected="selected"
		           		   </c:if>
		           		>${position.name }</option>
		            </c:forEach>
			    </select>
			</li>
			
			<li>
				<span class="item_name" style="width: 120px;"></span> 
				<input id="submitBtn" type="button" class="link_btn" value="更新/保存" />
				
				<span class="item_name" style="width:20px;"></span> 
				<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
			</li>
		</ul>
	</form>

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
	            "formData" : { 'fileMiddleBaseDir': 'ads/' },
	            'overrideEvents' : [ 'onDialogClose', 'onUploadError', 'onSelectError'],
	            'onSelect' : uploadify_onSelect,  
	            'onSelectError' : uploadify_onSelectError,  
	            'onUploadError' : uploadify_onUploadError, 
	            'onUploadSuccess' : function(file, data, response) {
	               var result = eval("(" + data + ")");
	               if(result.status){
		               var imgPath = result.fileImgViewBasePath + result.url;
		               $("#adsImgViewDiv").html("<img src='" + imgPath + "' style='width:100px;height:60px;' />");
			           $("#adsImgViewDiv").show();
			           
		               $("#adsImg").val(result.url);
	               }else{
	                  	alert(result.message);
	               }
	            },
	            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	                alert("文件上传失败，原因为: " + errorString);
	            }
	        });
		 
		   $('#submitBtn').click(function(){
		      $("#adsForm").submit();
		   });
		   
		   $('#back').click(function(){
			     window.location.href="${ctx }/ads/toList";
		   });
		   
		   $.formValidator.initConfig({
				formID:"adsForm",
				debug:false,
				submitOnce:true,
				errorFocus:true,
				submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
			});
			
			$("#adsUrl").formValidator({empty:true,onShow:"请输入跳转URL",onFocus:"请输入最多200字符的跳转URL",onCorrect:"验证通过",onEmpty:"请输入跳转URL"})
						       .inputValidator({min:1,max:200,onError:"跳转URL最多200字符，请确认！"})
							   .regexValidator({regExp:"^((http|https):\\/\\/)?([\\w-]+\\.)+[\\w-]+(\\/[\\w-.\\/?%&=]*)?$",onError:"跳转URL格式不正确，请确认"})
						       <c:if test="${not empty ads.adsId}">
							      .defaultPassed()
							   </c:if>;
	 });
</script>

</body>
</html>