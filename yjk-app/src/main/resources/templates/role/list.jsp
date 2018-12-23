<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/page.css"></link>
	
	<!--[if lt IE 9]>
	<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.myPagination.js" charset="UTF-8"></script>
</head>
<body>

<div class="page_title">
		<h2 class="fl">角色列表</h2>
		<shiro:hasPermission name="role:add">
			<a href="${ctx }/role/toAdd" target="mainContent" class="fr top_rt_btn add_icon">添加角色</a>
		</shiro:hasPermission>
	</div>

	<section class="mtb"> 
	   <input type="text" class="textbox textbox_225" placeholder="输入角色名称..." id="roleName" name="roleName" /> 
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>角色名称</th>
		        <th>状态</th>
		        <th>创建时间</th>
		        <th>操作</th>
		    </tr>
	    </thead>
		<tbody>
		</tbody>
	</table>
	
	<div id="page"></div>

<script type="text/javascript">
    $(document).ready(function (){
    	$("#i_content", window.parent.document).scrollTop(0);
    	
    	var msg = '${msg}';
    	if("" != msg){
    		alert(msg);
    	}
    	
    	//加载列表数据
        search();
    
    	$('#searchBtn').click(function(){
    		 search();
		});
    });
    
    function search() {
		$("#page").myPagination({
			currPage : 1,
			pageCount : 10,
			pageSize : 5,
			ajax : {
				on:true, //开启状态
				callback:'ajaxCallBack', //回调函数，注，此 ajaxCallBack 函数，必须定义在 $(function() {}); 外面
				url:'${ctx}/role/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					roleName : $("#roleName").val()
				}
			   //参数列表，其中on 必须开启，page 参数必须存在，其他的都是自定义参数，如果是多条件查询，可以序列化表单，然后增加 page参数
			}
		});
	}
	
	//自定义 回调函数
	function ajaxCallBack(data) {
		var result = data.result;
		var insetViewData = ""; //视图数据
		if(result != ""){
			$.each(result, function(index, items){
				insetViewData += createTR(items);
			});
		}else{
			insetViewData+= "<tr><td colspan='4' bgcolor='#FFFFFF' style='text-align:center;'><font color='red'>无记录...</font></td></tr>"
		}
		$("#mypage > tbody").html(insetViewData);
	}

	function createTR(obj) {
		var tr = "<tr>";
			tr += "<td>" +obj.roleName+ "</td>";
			
			if(1 == obj.status){//启用
				tr += "<td><font color='#090'>已启用</font></td>";
			}else{
				tr += "<td><font color='red'>已禁用</font></td>";
			}
			tr += "<td>" +obj.createTime+ "</td>";
			tr += "<td class='listtable'>";
			tr += "<shiro:hasPermission name='role:edit'><a class='btn_modify' href='javascript:void(0);' onclick='edit(" +obj.roleId+ ");'>修改</a>&nbsp;&nbsp;</shiro:hasPermission>";
			tr += "<shiro:hasPermission name='role:del'><a class='btn_delete' href='javascript:void(0);' onclick='del(" +obj.roleId+ ");'>删除</a>&nbsp;&nbsp;</shiro:hasPermission>";
			if(1 == obj.status){//启用
				tr += "<shiro:hasPermission name='role:status'><a class='btn_admin' href='javascript:void(0);' onclick='changeStatus(2, " +obj.roleId+ ");'>禁用</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}else{
				tr += "<shiro:hasPermission name='role:status'><a class='btn_qy' href='javascript:void(0);' onclick='changeStatus(1, " +obj.roleId+ ");'>启用</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	function edit(id){
    	var url = "${ctx }/role/toEdit?roleId=" + id;
    	window.location.href=url;
    }

	function del(id){
		if(confirm("你确定要删除此角色吗？")){
			$.ajax({
                type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
                url: "${ctx }/role/del" , //请求哪个服务器
                dataType: "json" , //响应数据格式(默认为:html)
                data: {'roleId': id} , //传递的参数
                cache:false,
                success: function(returnedData){ //"returnedData"为返回结果
                   if("success" == returnedData.result){
                   	 alert('删除成功!');
                   	 search();
                   }else if("existUsers" == returnedData.result){
                	   alert('该角色被用户使用中，不可删除，删除失败!');
                   }else{
                   	 alert('删除失败!');
                   }
                },
                error: function(){
	               //错误处理
               	   alert('删除异常，请重试!');
                }
            });
	   }
	}
	
	function changeStatus(newStatus, id){
		if(confirm("你确定要启用/禁用此角色吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/role/updateStatusById" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {'status': newStatus, "roleId": id} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('更改成功!');
	               	 search();
	               }else if("existUsers" == returnedData.result){
	            	   alert('该角色被用户使用中，不可修改状态，修改失败!');
	               }else{
	               	 alert('更改失败!');
	               }
	            },
	            error: function(){
	               //错误处理
	           	   alert('更改异常，请重试!');
	            }
	        });
		}
	}
</script>
</body>
</html>