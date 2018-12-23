<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<link href="${ctx }/static/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/formValidator4.0.1/validator.css" />
	<link href="${ctx }/static/js/umeditor/themes/default/css/umeditor.min.css" type="text/css" rel="stylesheet">
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidator-4.0.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidatorRegex.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty notice.noticeId }">
			                  修改公告:
			    </c:when>
			    <c:otherwise>
			                  新增公告:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="noticeForm" action="${ctx }/notice/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="noticeId" name="noticeId" value="${notice.noticeId }"/>
	    <c:if test="${not empty notice.noticeId }">
	       <input type="hidden" id="status" name="status" value="${notice.status }"/>
	       <input type="hidden" id="pubId" name="pubId" value="${notice.pubId }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
		    <li>
			    <span class="item_name" style="width: 120px;">标题：</span> 
			    <input id="title" name="title" type="text" class="textbox textbox_225" value="${notice.title }" />
			    <span id="titleTip" class="item_name"></span> 
			</li>
			<li>
			    <div id="fileQueue"></div> 
			    <span class="item_name" style="width:120px;">缩略图：</span>
			    <label class="single_selection">
			         <input type="file" id="upFile" name="upFile" />
		        </label>
		        <input type="hidden" id="titleImg" name="titleImg" value="${notice.titleImg }"/>
			</li>
			<li>
			    <span class="item_name" style="width:120px;"></span>
			    <label class="single_selection">
			         <div id="titleImgViewDiv" style="width:100px;height:60px;">
				         <c:choose>
						   <c:when test="${not empty notice.noticeId and not empty notice.titleImg }">
						   		<img src="${img_base_view_path}${notice.titleImg }" style="width:100px;height:60px;" />
						   </c:when>
						   <c:otherwise>
						   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
						   </c:otherwise>
						 </c:choose>
			         </div>
				</label>
				<span style="margin-left:30px; color:red;">您可以上传不小于130*75像素同比例图片，支持Jpg、Png、Gif格式，图片大小不高于1MB</span>
		    </li>
		    <li>
			    <span class="item_name" style="width: 120px;">类型：</span> 
			    <select class="select" name="type" style="width:120px;">
			        <c:forEach items="${noticeTypes }" var="type">
		           		<option value="${type.value }"
		           		   <c:if test="${type.value eq notice.type}">
		           		   		selected="selected"
		           		   </c:if>
		           		>${type.name }</option>
		            </c:forEach>
			    </select>
			</li>
		    <li>
		        <span class="item_name" style="width:120px;">状态:：</span>
	            <c:forEach items="${noticeStatuss }" var="status" varStatus="s">
	               <label class="single_selection">
	               <input type="radio" name="status" value="${status.value }"
	                 <c:choose>
		                 <c:when test="${not empty notice.noticeId }">
		                     disabled="disabled"
		                     <c:if test="${status.value eq notice.status}">
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
			    <span class="item_name" style="width: 120px;">描述：</span> 
			    <textarea id="description" name="description" rows="2" cols="2" class="textbox" style="width:600px;height:100px;">${notice.description }</textarea> 
			    <span id="descriptionTip" class="item_name"></span> 
			</li>
			<li>
			    <style>
		        	.single_selection {
		        		padding: 6px 0!important;
		        	}
		        	.ulColumn2 li{
		        	   margin:0px!important;
		        	}
		        	
		        	.edui-container{
		        		border:1px solid #139667;
		        	}
		        </style>
			    <span class="item_name" style="width: 120px;">内容：</span>
			    <div class="single_selection">
      		    	<script type="text/plain" id="editor" style="width:800px;height:260px;">${notice.content }</script>
				</div>
				<input id="content" name="content" type="hidden" />
				<span id="contentTip" class="item_name"></span> 
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
     var um = UM.getEditor('editor');

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
	            "formData" : { 'fileMiddleBaseDir': 'notice/' },
	            'overrideEvents' : [ 'onDialogClose', 'onUploadError', 'onSelectError'],
	            'onSelect' : uploadify_onSelect,  
	            'onSelectError' : uploadify_onSelectError,  
	            'onUploadError' : uploadify_onUploadError, 
	            'onUploadSuccess' : function(file, data, response) {
	               var result = eval("(" + data + ")");
	               if(result.status){
		               var imgPath = result.fileImgViewBasePath + result.url;
		               $("#titleImgViewDiv").html("<img src='" + imgPath + "' style='width:100px;height:60px;' />");
			           $("#titleImgViewDiv").show();
			           
		               $("#titleImg").val(result.url);
	               }else{
	                  	alert(result.message);
	               }
	            },
	            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	                alert("文件上传失败，原因为: " + errorString);
	            }
	        });
		 
		   $('#submitBtn').click(function(){
			  var content = um.getContent();
			  
			  if(!content){
				  $("#contentTip").addClass("onError");
	   		      $("#contentTip").html("请输入内容！");
	   		      return;
			  }else if(content.length > 40000){
				  $("#contentTip").addClass("onError");
	   		      $("#contentTip").html("内容最多40000字符，请确认！");
	   		      return;
			  }else{
				  $("#contentTip").html("");
			  }
			  
		      $("#content").val(content);
		      $("#noticeForm").submit();
		   });
		   
		   $('#back').click(function(){
			     window.location.href="${ctx }/notice/toList";
		   });
		   
		   $.formValidator.initConfig({
				formID:"noticeForm",
				debug:false,
				submitOnce:true,
				errorFocus:true,
				submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
			});
			
			$("#title").formValidator({empty:false,onShow:"请输入标题",onFocus:"请输入最多200字符的标题",onCorrect:"验证通过",onEmpty:"请输入标题"})
						       .inputValidator({min:1,max:200,onError:"标题最多200字符，请确认！"})
							   <c:if test="${not empty notice.noticeId}">
							      .defaultPassed()
							   </c:if>;
						 
		   $("#description").formValidator({empty:true,onShow:"请输入描述",onFocus:"请输入最多500字符的描述",onCorrect:"验证通过",onEmpty:"请输入描述"})
		                 .inputValidator({min:1,max:100,onError:"描述最多500字符,请确认"})
						 <c:if test="${not empty notice.noticeId}">
						       .defaultPassed()
						 </c:if>;
	 });
</script>

</body>
</html>