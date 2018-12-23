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
</head>
<body>

<div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty storeManager.userId }">
			                  修改门店管理员:
			    </c:when>
			    <c:otherwise>
			                  新增门店管理员:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="userForm" action="${ctx }/store/storeManager" method="post">
	   <!-- hiden -->
	    <input type="hidden" id="userId" name="userId" value="${storeManager.userId }"/>
	    <input type="hidden" id="storeId" name="storeId" value="${storeId }"/>
	    <c:if test="${not empty storeManager.userId }">
	       <input type="hidden" id="userIdentity" name="userIdentity" value="${storeManager.userIdentity }"/>
	       <input type="hidden" id="status" name="status" value="${storeManager.status }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
			<li>
			    <span class="item_name" style="width: 120px;">用户昵称：</span> 
			    <input type="text" class="textbox textbox_225" id="nickName" name="nickName" value="${storeManager.nickName }" /> 
			    <span id="nickNameTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">登录账号：</span> 
			    <input type="text" class="textbox textbox_225" id="loginName" name="loginName" value="${storeManager.loginName }" />
			    <span id="loginNameTip" class="item_name"></span>
			</li>
			
			<c:choose>
                <c:when test="${not empty storeManager.userId }">
                    <input id="password" name="password" type="hidden" value="${storeManager.password }" />
                </c:when>
                <c:otherwise>
                   <li>
			    	<span class="item_name" style="width: 120px;">登录密码：</span> 
                    <input id="password" name="password" type="password" class="textbox textbox_225" />
                    <span id="passwordTip" class="item_name"></span>
                   </li>
                </c:otherwise>
            </c:choose>
             
		    <li>
			    <span class="item_name" style="width: 120px;">说明：</span> 
			    <textarea id="remark" name="remark" rows="2" cols="2" class="textbox" style="width:300px;height:100px;">${storeManager.remark }</textarea> 
				<span id="remarkTip" class="item_name"></span>
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
    $(function () {
    	$('#submitBtn').click(function(){
		     $("#userForm").submit();
		});
    	
    	$('#back').click(function(){
		     window.location.href="${ctx }/store/toList";
		});
    	
		$.formValidator.initConfig({
			formID:"userForm",
			debug:false,
			submitOnce:true,
			errorFocus:true,
			submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
		});
		
		$("#nickName").formValidator({empty:false,onShow:"请输入用户昵称",onFocus:"请输入最多12字符的用户名",onCorrect:"验证通过",onEmpty:"请输入用户名"})
					       .inputValidator({min:1,max:12,onError:"用户昵称最多12字符，请确认！"})
						   <c:if test="${not empty user.userId}">
						      .defaultPassed()
						   </c:if>;
		
		$("#loginName").formValidator({empty:false,onShow:"请输入登录账号",onFocus:"登录账号由数字、26个英文字母或者特殊字符组成(5-25个字符)",onCorrect:"验证通过"})
                       .inputValidator({min:5,max:25,onError:"登录账号由数字、26个英文字母或者特殊字符组成(5-25个字符)"})
                       .regexValidator({regExp:"^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~\\_\\-\\+\\=\\\\\|]{5,25}$",onError:"登录账号由数字、26个英文字母或者特殊字符组成(5-25个字符)"})
					   .ajaxValidator({
					        type : "post",
					        dataType:"json",
					        async : true,
					        url : "${ctx }/store/loginNameIsUsed",
					        data:{"loginName":$("#loginName").val()}, 
					        success : function(data){
					            if(data && 'isUsed' == data.result){
					            	return false;
					            }else{
					            	return true;
					            }
					        },
					        buttons: $("#submitBtn"),
					        error: function(jqXHR, textStatus, errorThrown){alert("服务器忙，请重试！");},
					        onError: "登录账号已被注册，请更换",
					        onWait:"正在对登录账号进行合法性校验，请稍候..."
					    })
                       <c:if test="${not empty user.userId}">
					      .defaultPassed()
					   </c:if>;
	   
	   $("#password").formValidator({empty:false,onShow:"请输入登录密码",onFocus:"请输入6-32位登录密码",onCorrect:"验证通过",onEmpty:"请输入登录密码"})
	                 .inputValidator({min:6,max:32,onError:"登录密码为6-32位,请确认"})
					 <c:if test="${not empty user.userId}">
					       .defaultPassed()
					 </c:if>;
	  
	   $("#remark").formValidator({empty:true,onShow:"请输入说明",onFocus:"请输入说明",onCorrect:"验证通过",onEmpty:"请输入说明"})
	                 .inputValidator({min:1,max:100,onError:"说明最多100字符,请确认"})
					 <c:if test="${not empty user.userId}">
					       .defaultPassed()
					 </c:if>;
    });
</script>
</body>
</html>