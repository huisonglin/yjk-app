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
		<!--
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes = ${zTreeStr};
		
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("checkTree"),
			type = { "Y":"p" + "s", "N":"p" + "s"};
			
			zTree.setting.check.chkboxType = type;
		}
		
		
		$(document).ready(function(){
			$.fn.zTree.init($("#checkTree"), setting, zNodes);
			setCheck();
		});
		
		function getCheckedNodes(){
		   var treeObj = $.fn.zTree.getZTreeObj("checkTree");
           var nodes=treeObj.getCheckedNodes(true);
		   var v = "";
           for(var i=0;i<nodes.length;i++){
             v = v + nodes[i].id + ";"; //获取选中节点的值
           }
		   return v;
		}
		//-->
	</script>
</head>
<body>
    <div class="page_title">
		<h2 class="fl">
			<c:choose>
			    <c:when test="${not empty role.roleId }">
			                  修改角色:
			    </c:when>
			    <c:otherwise>
			                  新增角色:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form id="roleForm" action="${ctx }/role/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="roleId" name="roleId" value="${role.roleId }"/>
	    <input type="hidden" id="rolePersStr" name="rolePersStr" />
	    <c:if test="${not empty role.roleId }">
	       <input type="hidden" id="status" name="status" value="${role.status }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
			<li>
			    <span class="item_name" style="width: 120px;">角色名称：</span> 
			    <input type="text" class="textbox textbox_225" id="roleName" name="roleName" value="${role.roleName }" /> 
				<span id="roleNameTip" class="item_name"></span>
			</li>
			<li>
		        <span class="item_name" style="width:120px;">状态：</span>
	            <c:forEach items="${roleStatusList }" var="roleStatus" varStatus="s">
	              <label class="single_selection">
	              <input type="radio" name="status" value="${roleStatus.value }"
	                 <c:choose>
			               <c:when test="${not empty role.roleId }">
			                   disabled="disabled"
			                   <c:if test="${roleStatus.value eq role.status}">
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
	              ${roleStatus.name }
	              </label>
	           </c:forEach>
		    </li>
		    <li>
			    <span class="item_name" style="width: 120px;">说明：</span> 
			    <textarea id="remark" name="remark" rows="2" cols="2" class="textbox" style="width:200px;height:100px;">${role.remark }</textarea> 
				<span id="remarkTip" class="item_name"></span>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">所具有权限：</span>
			    <div class="single_selection">
			    	<ul id="checkTree" class="ztree"></ul>
			    </div>
			    <span id="rolePersStrTip" class="item_name"></span>
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
		     var rolePersStr = getCheckedNodes();
		     
		     
		     if(!rolePersStr){
		    	 $("#rolePersStrTip").addClass("onError");
   		    	 $("#rolePersStrTip").html("请选择角色所具有的权限！");
		    	 return;
		     }else{
		     	$("#rolePersStrTip").html("");
		     }
		     
		     $("#rolePersStr").val(rolePersStr);
		     $("#roleForm").submit();
		});
    	
    	$('#back').click(function(){
		     window.location.href="${ctx }/role/toList";
		});
		
		$.formValidator.initConfig({
			formID:"roleForm",
			debug:false,
			submitOnce:true,
			errorFocus:true,
			submitAfterAjaxPrompt:'当前有数据正在进行服务器端校验，请稍候...'
		});
		
		$("#roleName").formValidator({empty:false,onShow:"请输入角色名称",onFocus:"请输入最多20字符的角色名称",onCorrect:"验证通过",onEmpty:"请输入角色名称"})
					       .inputValidator({min:1,max:20,onError:"角色名称最多20字符，请确认！"})
						   <c:if test="${not empty role.roleId}">
						      .defaultPassed()
						   </c:if>;
					 
	   $("#remark").formValidator({empty:true,onShow:"请输入说明",onFocus:"请输入说明",onCorrect:"验证通过",onEmpty:"请输入说明"})
	                 .inputValidator({min:1,max:100,onError:"说明最多100字符,请确认"})
					 <c:if test="${not empty role.roleId}">
					       .defaultPassed()
					 </c:if>;
    });
</script>
</body>
</html>