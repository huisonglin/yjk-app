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

	<script type="text/javascript">
         function showSelectParentPerPage(){
	       	 var openUrl = '${ctx}/permission/toParentPermission';
	         var iWidth=320; //弹出窗口的宽度;
	  	     var iHeight=460; //弹出窗口的高度;
	  	     var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	  	     var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	  	     window.open(openUrl,"父级菜单选择","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft); 
         };
    </script>
</head>
<body>
	<div class="page_title">
		<h2 class="fl">
			<c:choose>
				<c:when test="${not empty per.perId }">
			                  修改权限:
			    </c:when>
				<c:otherwise>
			                  新增权限:
			    </c:otherwise>
			</c:choose>
		</h2>
	</div>
	
	<form action="${ctx }/permission/addOrUpdate" method="post">
	    <!-- hiden -->
	    <input type="hidden" id="perId" name="perId" value="${per.perId }"/>
	    <c:if test="${not empty per.perId }">
	       <input type="hidden" id="status" name="status" value="${per.status }"/>
	    </c:if>
	    
		<ul class="ulColumn2">
			<li>
			    <span class="item_name" style="width: 120px;">权限名称：</span> 
			    <input type="text" class="textbox textbox_225" name="perName" value="${per.perName }" /> 
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">权限标识：</span> 
			    <input type="text" class="textbox textbox_225" name="permission" value="${per.permission }" /> 
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">权限类型：</span>
				<select name="perType" class="select" style="width: 120px;">
                  <c:forEach items="${permissionTypeList }" var="permissionType">
                     <option value="${permissionType.value }"
                        <c:if test="${permissionType.value eq per.perType }">
                           selected="selected"
                        </c:if>
                     >
                     ${permissionType.name }</option>
                  </c:forEach>
               </select>
			</li>
			<li>
			    <span class="item_name" style="width: 120px;">URL：</span> 
			    <input type="text" class="textbox textbox_225" name="url" value="${per.url }" /> 
			</li>
			<li>
		        <span class="item_name" style="width:120px;">状态：</span>
		        <c:forEach items="${permissionStatusList }" var="permissionStatus" varStatus="s">
	              <label class="single_selection">
	              <input type="radio" name="status" value="${permissionStatus.value }"
	                 <c:choose>
			               <c:when test="${not empty per.perId }">
			                   disabled="disabled"
			                   <c:if test="${permissionStatus.value eq per.status}">
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
	              ${permissionStatus.name }
	              </label>
	           </c:forEach>
		    </li>
		    <li>
			    <span class="item_name" style="width: 120px;">排序：</span> 
			    <input type="text" class="textbox textbox_225" name="orderNum" value="${per.orderNum }" /> 
			</li>
		    <li>
			    <span class="item_name" style="width: 120px;">父级权限：</span> 
			    <input type="text" class="textbox textbox_225" id="parentPerName" value="${per.parentPerName }" readonly  /> 
			    <input id="parentId" name="parentId" type="hidden" 
			         <c:choose>
					    <c:when test="${not empty per.perId }">
					       value="${per.parentId }"
					    </c:when>
					    <c:otherwise>
					       value="0"
					    </c:otherwise>
					 </c:choose> 
			         />
		         &nbsp;&nbsp;&nbsp;&nbsp;
		         <a href="javascript:void(0);" onclick="showSelectParentPerPage(event);">选择</a>
			</li>
			
			<li>
				<span class="item_name" style="width: 120px;"></span> 
				<input type="submit" class="link_btn" value="更新/保存" />
                <span class="item_name" style="width:20px;"></span> 
				<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
			</li>
		</ul>
	</form>
</body>
</html>