<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<link rel="stylesheet" href="${ctx }/static/css/zTree/zTreeStyle.css" type="text/css">
	
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
    
    <script type="text/javascript" src="${ctx }/static/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/jquery.ztree.excheck-3.5.js"></script>
    
    <script type="text/javascript">
		<!--
		var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "level"
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =${zTreeStr};
			
		function setCheck() {
			var type = "all"; //"level" or "all"(针对层级 or 所有)
			setting.check.radioType = type;
			$.fn.zTree.init($("#radioTree"), setting, zNodes);
		}
		
		$(document).ready(function(){
			setCheck();
		});
		
		function getCheckedNodes(){
		   var treeObj = $.fn.zTree.getZTreeObj("radioTree");
           var nodes=treeObj.getCheckedNodes(true);
		   
		   var l = nodes.length;
		   if(l == 1){
		      //alert("选中节点的name=" + nodes[0].name + ",id=" + nodes[0].id);
	          window.opener.document.getElementById("parentPerName").value = nodes[0].name;
              window.opener.document.getElementById("parentId").value = nodes[0].id;
              closeWindow();
		   }else{
		      alert("尚未选择选项！");
		   }
		    
		}
		
		function clearValues(){
			window.opener.document.getElementById("parentPerName").value = "";
            window.opener.document.getElementById("parentId").value = "0";
            closeWindow();
		}
		
		function closeWindow(){
        	window.close();
        }
		//-->
	</script>
</head>
<body>
    <div class="form-inline definewidth m20">
        <b>
	          父级权限选择:
		</b>
	</div>

    <ul id="radioTree" class="ztree"></ul>
    
    <br />
    
    <a href="javascript:void(0);" onclick="getCheckedNodes();">选择</a>
    &nbsp;&nbsp;
    <a href="javascript:void(0);" onclick="clearValues();">清空</a>
</body>
</html>