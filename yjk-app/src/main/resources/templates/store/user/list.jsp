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
		<h2 class="fl">用户列表</h2>
	</div>

	<section class="mtb">
	   <input type="text" class="textbox textbox_225" placeholder="输入用户昵称..." id="nickName" name="nickName" /> 
	   &nbsp;&nbsp;&nbsp;&nbsp;
	   <span class="item_name" style="width:120px;">所属门店：</span>
       <select id="storeId" class="select" style="width:120px;">
           <option value="">---全部---</option>
           <c:forEach items="${storeList }" var="store">
           		<option value="${store.storeId }">${store.storeName }</option>
           </c:forEach>
       </select>
       
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>用户昵称</th>
		        <th>登录账号</th>
		        <th>状态</th>
		        <th>创建时间</th>
		        <th>所属门店</th>
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
				url:'${ctx}/store/user/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					nickName : $("#nickName").val(),
					storeId : $("#storeId").val()
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
			insetViewData+= "<tr><td colspan='6' bgcolor='#FFFFFF' style='text-align:center;'><font color='red'>无记录...</font></td></tr>"
		}
		$("#mypage > tbody").html(insetViewData);
	}

	function createTR(obj) {
		var tr = "<tr>";
			tr += "<td>" +obj.nickName+ "</td>";
			tr += "<td>" +obj.loginName+ "</td>";
			if(1 == obj.status){//启用
				tr += "<td><font color='#090'>已启用</font></td>";
			}else{
				tr += "<td><font color='red'>已禁用</font></td>";
			}
			tr += "<td>" +obj.createTime+ "</td>";
			tr += "<td>" +obj.storeName+ "</td>";
			
			tr += "<td class='listtable'>";
			if(1 == obj.status){//启用
				tr += "<a class='btn_delete' href='javascript:void(0);' onclick='changeStatus(2, " +obj.userId+ ");'>禁用</a>&nbsp;&nbsp;";
			}else{
				tr += "<a class='btn_qy' href='javascript:void(0);' onclick='changeStatus(1, " +obj.userId+ ");'>启用</a>&nbsp;&nbsp;";
			}
			tr += "<a class='btn_modify' href='javascript:void(0);' onclick='resetPassword(" +obj.userId+ ");'>重置密码</a>&nbsp;&nbsp;";
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	
	function changeStatus(newStatus, id){
		if(confirm("你确定启用/禁用此用户吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/store/user/updateStatusById" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {'status': newStatus, "userId": id} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('更改成功!');
	               	 search();
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
	
	function resetPassword(id){
		if(confirm("你确定重置此用户密码吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/store/user/resetPasswordById" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {"userId": id} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('密码重置成功!\n密码已重置为:123456，请告知相应用户！');
	               	 search();
	               }else{
	               	 alert('密码重置失败!');
	               }
	            },
	            error: function(){
	               //错误处理
	           	   alert('密码重置异常，请重试!');
	            }
	        });
		}
	}
</script>
</body>
</html>