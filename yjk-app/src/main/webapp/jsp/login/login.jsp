<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC “-//W3C//DTD XHTML 1.0 Transitional//EN” “http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd”>
<html xmlns=”http://www.w3.org/1999/xhtml”>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<link rel="shortcut  icon" type="image/x-icon" href="${ctx }/images/favicon.ico" media="screen" />
<title>最快租机械后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/base.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/login.css" />
<script src="${ctx }/static/js/jquery.js"></script>
</head>
<body>
<div class="superloginA"></div>
<div class="loginBox">
  <div style="text-align:center;">
    <div class="logo"><img src="${ctx }/static/images/logo_login.png"/></div>
  </div>
  <div style="text-align:center;">
    <div class="loginMain">
      <div class="tabwrap">
        <form id="loginForm" action="${ctx }/login" method="post" onsubmit="return submitFrom();">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="title">用户名：</td>
              <td><input type="text" placeholder="账号" class="form-control txt" id="username" name="username" value="${username }" onfocus="clearError();"/></td>
            </tr>
            <tr>
              <td class="title">密   码：</td>
              <td><input type="password" placeholder="密码" class="form-control txt" id="password" name="password" onfocus="clearError();" /></td>
            </tr>
            <tr>
              <td class="title">验证码：</td>
              <td><input type="text" placeholder="验证码" maxlength="4" class="form-control txt txt2" id="captcha" name="captcha" onfocus="clearError();">
                <span class="yzm"><img id="img_captcha" src="${ctx}/servlet/captchaCode" onclick="refreshCaptcha();" height="40" /></span></td>
            </tr>
            <tr class="errortd" style="display:none" id="error">
              <td>&nbsp;</td>
              <td><i class="ico-error"></i><span class="errorword">用户名或密码错误，请重新输入！</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><input type="submit" class="loginbtn" value="登录"  />
                <input type="reset" class="resetbtn" value="重置" onclick="location.href='loginb.html'" /></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="footer">Copyright © 2016-2017 snncar  All Rights Reserved.</div>
<script type="text/javascript">
	$(document).ready(function (){
    	var msg = '${msg}';
    	if("" != msg){
    		$("#error").html('<td>&nbsp;</td><td><i class="ico-error"></i><span class="errorword">'+msg+'</span></td>').show();
    	}
    	
    	//session过期跳转问题
		if (window != top){
			top.location.href = location.href; 
		}
	});
	
    var _captcha_id = "#img_captcha";
    function refreshCaptcha() {
        $(_captcha_id).attr("src","${ctx}/servlet/captchaCode?t=" + Math.random());
    }
    
    function clearError(){
    	$("#error").html("").hide();
    }
    
    function submitFrom(){
    	var username = $("#username").val().trim();
    	var password = $("#password").val().trim();
    	var captcha = $("#captcha").val().trim();
    	if(!username || !password){
			$("#error").html('<td>&nbsp;</td><td><i class="ico-error"></i><span class="errorword">账号或密码输入不完整，请确认！'+
			'</span></td>').show();
    		return false;
    	}
    	if(!captcha){
			$("#error").html('<td>&nbsp;</td><td><i class="ico-error"></i><span class="errorword">验证码未输入，请确认！'+
			'</span></td>').show();
    		return false;
    	}
    	
        $("#loginForm").submit();
    }
</script>
</body>
</html>