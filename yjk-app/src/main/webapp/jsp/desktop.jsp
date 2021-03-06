<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.yjk.manager.shiro.ShiroDbRealm.ShiroUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut  icon" type="image/x-icon" href="${ctx }/static/images/favicon.ico" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>最快租机械后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
<!--[if lt IE 9]>
<script src="${ctx }/static/js/html5.js"></script>
<![endif]-->
<script src="${ctx }/static/js/jquery.js"></script>
<script src="${ctx }/static/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>
	(function($) {
		$(window).load(function() {
			$("a[rel='load-content']").click(
				function(e) {
					e.preventDefault();
					var url = $(this).attr("href");
					$.get(url, function(data) {
						$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
						//scroll-to appended content 
						$(".content").mCustomScrollbar("scrollTo",
								"h2:last");
					});
		    });

		    $(".content").delegate(
					"a[href='top']",
					"click",
					function(e) {
						e.preventDefault();
						$(".content").mCustomScrollbar("scrollTo",
								$(this).attr("href"));
		    });
		    
		    //add class for the click menu
		    $("#leftNav li dl dd a").click(function (){
				$("#leftNav li dl dd a").removeClass("active");
				$(this).addClass("active");
			});
		});
	})(jQuery);
	
	/*
	function changeFrameHeight(){
		var ifm = document.getElementById("mainContent"); 
		ifm.height = ifm.contentWindow.document.body.scrollHeight + 100;
	}
	
	window.onresize=function(){  
		changeFrameHeight();
	}
	*/
	
	function changePassword(){
 	   var openUrl = "${ctx }/user/toChangePasswordPage";//弹出窗口的url
 	   var iWidth=600; //弹出窗口的宽度;
 	   var iHeight=300; //弹出窗口的高度;
 	   var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
 	   var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
 	   window.open(openUrl,"密码修改","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft); 
    }
</script>
</head>
<body>
	<!--header-->
	<header>
	<h1>
		<img src="${ctx }/static/images/admin_logo.png" />
	</h1>
	<ul class="rt_nav">
		<!-- 
		<li><a href="javascript:void(0);" class="website_icon">站点首页</a></li>
		-->
		<li>
			<a href="javascript:void(0);" class="admin_icon">
			   <% ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal(); %>
		       <%=user.nickName %>
			</a>
		</li>
		<li><a href="javascript:void(0);" onclick="changePassword();" class="set_icon">修改密码</a></li>
		<li><a href="${ctx }/logout" class="quit_icon">安全退出</a></li>
	</ul>
	</header>

	<!--aside nav-->
	<aside class="lt_aside_nav content mCustomScrollbar">
	<h2>
		<a href="${ctx }/welcome" target="mainContent">起始页</a>
	</h2>
	<ul id="leftNav">
	    <!-- 
		<li>
			<dl>
				<dt>系统管理</dt>
				<dd>
					<a href="${ctx }/user/toList" target="mainContent">用户管理</a>
				</dd>
				<dd>
					<a href="${ctx }/role/toList" target="mainContent">角色管理</a>
				</dd>
				<dd>
					<a href="${ctx }/permission/toList" target="mainContent">权限管理</a>
				</dd>
			</dl>
		</li>
		-->
		<c:forEach items="${menus }" var="parent">
		     <li>
				<dl>
					<dt>${parent.perName }</dt>
					<c:forEach items="${parent.childs }" var="child">
						<dd>
							<a href="${ctx }${child.url }" target="mainContent">${child.perName }</a>
						</dd>
					</c:forEach>
				</dl>
			</li>
		</c:forEach>
		 
		<li>
			<p class="btm_infor">© 2018-2019 快租机械 版权所有</p>
		</li>
	</ul>
	</aside>

	<section class="rt_wrap">
		<div id="i_content" class="rt_content" style="height:100%; overflow-y:scroll;">
		    <iframe id="mainContent" name="mainContent"  src="${ctx }/welcome" frameborder="0"  scrolling="no" width="100%" height="4000px"></iframe>
		</div>
	</section>
</body>
</html>