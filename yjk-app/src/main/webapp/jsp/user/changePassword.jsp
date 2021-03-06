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
			修改密码
		</h2>
	</div>
	
	<form id="userForm" action="${ctx }/user/changePasswordByUserId" method="post">
		<ul class="ulColumn2">
			<li>
			    <span class="item_name" style="width: 120px;">原密码：</span> 
			    <input type="password" class="textbox textbox_225" id="oldPassword" name="oldPassword" /> 
			    <span id="oldPasswordTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">新密码：</span> 
			    <input type="password" class="textbox textbox_225" id="newPassword" name="newPassword" />
			    <span id="newPasswordTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">确认新密码：</span> 
			    <input type="password" class="textbox textbox_225" id="againNewPassword" name="againNewPassword" />
			    <span id="againNewPasswordTip" class="item_name"></span>
			</li>
			
			<li>
				<span class="item_name" style="width: 120px;"></span> 
				<input id="submitBtn" type="submit" class="link_btn" value="更新/保存" />
			</li>
		</ul>
	</form>

<script type="text/javascript">
    $(function () {
    	var msg = '${msg}';
    	if("" != msg){
    		alert(msg);
    		window.close();
    	}
    	
		$.formValidator.initConfig({
			formID:"userForm",
			debug:false,
			submitOnce:true,
			errorFocus:true,
			submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
		});
		
		$("#oldPassword").formValidator({empty:false,onShow:"请输入原密码",onFocus:"请输入6-32位原密码",onCorrect:"验证通过",onEmpty:"请输入原密码"})
	                     .inputValidator({min:6,max:32,onError:"原密码为6-32位,请确认"});
		
		$("#newPassword").formValidator({empty:false,onShow:"请输入新密码",onFocus:"请输入6-32位新密码",onCorrect:"验证通过",onEmpty:"请输入新密码"})
                         .inputValidator({min:6,max:32,onError:"新密码为6-32位,请确认"});
		
		$("#againNewPassword").formValidator({onShow:"请再次输入新密码",onFocus:"请再次输入新密码",onCorrect:"2次输入的密码一致"})
                              .compareValidator({desID:"newPassword",operateor:"=",onError:"2次输入的密码不一致,请确认"});
    });
</script>
</body>
</html>