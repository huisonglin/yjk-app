<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<link rel="stylesheet" href="${ctx }/static/css/zTree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/js/formValidator4.0.1/validator.css" />
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidator-4.0.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/formValidator4.0.1/formValidatorRegex.js"></script>
    
    <script type="text/javascript">
	</script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty model.id }">
			                  修改机型类型:
			    </c:when>
			    <c:otherwise>
			                  新增机械类型:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="modelForm" action="${ctx }/model/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="id" name="id" value="${model.id }"/>
	    <input type="hidden" id="rolePersStr" name="rolePersStr" />

	    
		<ul class="ulColumn2">
			<li>
			    <span class="item_name" style="width: 120px;">类型名称：</span> 
			    <input type="text" class="textbox textbox_225" id="name" name="name" value="${model.name }" /> 
				<span id="roleNameTip" class="item_name"></span>
			</li>
			<li>
			 <span class="item_name" style="width: 120px;">状态：</span>
				<select name="status" class="select" style="width: 120px;">
					<option value="1" 
					    <c:if test="${model.status == 1 }">
                           selected="selected"
                        </c:if>
					>显示</option>
					<option value="0"
						<c:if test="${model.status == 0 }">
                           selected="selected"
                        </c:if>
					>不显示</option>
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

<script type="text/javascript">
$(function(){
	$('#submitBtn').click(function(){
	     $("#modelForm").submit();
	});
	
	$('#back').click(function(){
	     window.location.href="${ctx }/model/toList";
	});
})
    	
</script>
</body>
</html>