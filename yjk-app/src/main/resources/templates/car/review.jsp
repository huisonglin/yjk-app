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
	<div class="page_title">
		<h2 class="fl">
			车源审核处理
		</h2>
	</div>
	
	<form action="${ctx }/car/dealReview" method="post" onsubmit="return checkData();">
	<!-- hidden -->
	<input type="hidden" name="carId" value="${carId }" />
	<input type="hidden" name="oldStatus" value="${oldStatus }" />
	<input type="hidden" name="isCheckedSaleon" value="${isCheckedSaleon }" />
	
	<ul class="ulColumn2">
		<li>
		    <span class="item_name" style="width:120px;">审核：</span>
		    
		    <input type="radio" name="status" value="4" checked="checked" />审核通过
		    <input type="radio" name="status" value="3" />审核不通过
		</li>
		<li>
		    <span class="item_name" style="width:120px;">审核情况说明：</span>
		    <textarea id="checkRemark" name="checkRemark" rows="2" cols="2" class="textbox" style="width:400px;height:80px;"></textarea> 
		</li>
		
		<li>
			<span class="item_name" style="width: 120px;"></span> 
			<input type="submit" class="link_btn" value="确认审核" />
			
			<input id="backBtn" type="button" class="link_btn" value="返回" />
		</li>
    </ul>
    </form>
    
<script type="text/javascript">
	$(document).ready(function (){
		$("#backBtn").click(function (){
			history.back();
		});
	});
	
	function checkData(){
		var statusValue =  $("input[name=status]:checked").val();
		if(statusValue && 3 == statusValue){
			var checkRemark =  $("#checkRemark").val();
			if(!checkRemark){
				alert("审核情况说明未输入，请输入！");
				return false;
			}
			
			if(checkRemark.length > 1000){
				alert("审核情况说明最多1000字符，请确认！");
				return false;
			}
			
			return true;
		}
	}
</script>
</body>
</html>