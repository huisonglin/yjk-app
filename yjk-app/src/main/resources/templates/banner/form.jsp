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
			    <c:when test="${not empty banner.bannerId }">
			                  修改Banner:
			    </c:when>
			    <c:otherwise>
			                  新增Banner:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="bannerForm" action="${ctx }/banner/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="bannerId" name="bannerId" value="${banner.bannerId }"/>
	    <c:if test="${not empty banner.bannerId }">
	       <input type="hidden" id="status" name="status" value="${banner.status }"/>
	       <input type="hidden" id="pubId" name="pubId" value="${banner.pubId }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
			<li>
			    <div id="fileQueue"></div> 
			    <span class="item_name" style="width:120px;">Banner图片：</span>
			    <label class="single_selection">
			         <input type="file" id="upFile" name="upFile" />
		        </label>
		        <input type="hidden" id="imgPath" name="imgPath" value="${banner.imgPath }"/>
			</li>
			<li>
			    <span class="item_name" style="width:120px;"></span>
			    <label class="single_selection">
			         <div id="imgViewDiv" style="width:100px;height:60px;">
				         <c:choose>
						   <c:when test="${not empty banner.bannerId and not empty banner.imgPath }">
						   		<img src="${img_base_view_path}${banner.imgPath }" style="width:100px;height:60px;" />
						   </c:when>
						   <c:otherwise>
						   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
						   </c:otherwise>
						 </c:choose>
			         </div>
				</label>
				<span style="margin-left:30px; color:red;">您可以上传不小于920*400像素同比例图片，支持Jpg、Png、Gif格式，图片大小不高于1MB</span>
		    </li>
		    <li>
			    <span class="item_name" style="width: 120px;">跳转URL：</span> 
			    <input id="jumpUrl" name="jumpUrl" type="text" class="textbox" style="width:400px;" value="${banner.jumpUrl }" />
				<span id="jumpUrlTip" class="item_name"></span> 
			</li>
			<li>
		        <span class="item_name" style="width:120px;">状态：</span>
	            <c:forEach items="${bannerStatus }" var="status" varStatus="s">
	               <label class="single_selection">
	               <input type="radio" name="status" value="${status.value }"
	                 <c:choose>
		                 <c:when test="${not empty banner.bannerId }">
		                     disabled="disabled"
		                     <c:if test="${status.value eq banner.status}">
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
		               $("#imgViewDiv").html("<img src='" + imgPath + "' style='width:100px;height:60px;' />");
			           $("#imgViewDiv").show();
			           
		               $("#imgPath").val(result.url);
	               }else{
	                  	alert(result.message);
	               }
	            },
	            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	                alert("文件上传失败，原因为: " + errorString);
	            }
	        });
		 
		   $('#submitBtn').click(function(){
		      $("#bannerForm").submit();
		   });
		   
		   $('#back').click(function(){
			     window.location.href="${ctx }/banner/toList";
		   });
		   
		   $.formValidator.initConfig({
				formID:"bannerForm",
				debug:false,
				submitOnce:true,
				errorFocus:true,
				submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
			});
			
			$("#jumpUrl").formValidator({empty:true,onShow:"请输入跳转URL",onFocus:"请输入最多200字符的跳转URL",onCorrect:"验证通过",onEmpty:"请输入跳转URL"})
						       .inputValidator({min:1,max:200,onError:"跳转URL最多200字符，请确认！"})
							   .regexValidator({regExp:"^((http|https):\\/\\/)?([\\w-]+\\.)+[\\w-]+(\\/[\\w-.\\/?%&=]*)?$",onError:"跳转URL格式不正确，请确认"})
						       <c:if test="${not empty banner.bannerId}">
							      .defaultPassed()
							   </c:if>;
	 });
</script>

</body>
</html>