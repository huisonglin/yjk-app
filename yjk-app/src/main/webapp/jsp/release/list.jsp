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
		<h2 class="fl">发布列表</h2>
<%-- 		<shiro:hasPermission name="per:add">
			<a href="${ctx }/permission/toAdd" target="mainContent" class="fr top_rt_btn add_icon">添加权限</a>
	    </shiro:hasPermission> --%>
	</div>

	<section class="mtb"> 
	   <input type="text" class="textbox textbox_225" placeholder="输入设备名称/发布人昵称/地址。。。。" id="keyword" name="keyword" /> 
  	    <select class="select" id="infoStatus" name=""infoStatus"" style="width:180px;">
          <option value=''>---请选择展示状态---</option>
            <option value='1'>已下架</option>
          	<option value='2'>已上架</option>
      </select>
      <select class="select" id="infoType" name="infoType" style="width:180px;">
          <option value=''>---请选择发布类型---</option>
          	<option value='1'>出租</option>
          	<option value='2'>求租</option>
      </select>
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>设备名称</th>
		        <th>图片</th>
		        <th>地址</th>
		        <th style="width:200px;">状态</th>
		        <th style="width:200px;">发布类型</th>
		        <th>用户昵称</th>
		        <th>联系号码</th>
		        <th>发布时间</th>
		        <th style="width:220px;">操作</th>
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
				url:'${ctx}/release/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					keyword : $("#keyword").val(),
					infoType : $("#infoType").val(),
					infoStatus : $("#infoStatus").val()
				}
			   //参数列表，其中on 必须开启，page 参数必须存在，其他的都是自定义参数，如果是多条件查询，可以序列化表单，然后增加 page参数
			}
		});
	}
	
	//自定义 回调函数
	function ajaxCallBack(data) {
		var result = data.result;
		
		var insetViewData = ""; //视图数据
		if("" != result){
			$.each(result, function(index, items){
				insetViewData += createTR(items);
			});
		}else{
			insetViewData+= "<tr><td colspan='8' bgcolor='#FFFFFF' style='text-align:center;'><font color='red'>无记录...</font></td></tr>"
		}
		$("#mypage > tbody").html(insetViewData);
	}

	function createTR(obj) {
		var tr = "<tr>";
			tr += "<td>" +obj.deviceName+ "</td>";
			if("" != obj.pics){
				tr += "<td><img style='widht:100px;height:60px;' src='" +obj.pics+ "' /></td>";
			}else{
				tr += "<td><img style='widht:100px;height:60px;' src='http://img.huisonglin.top/FkFypeigTUPq7ck340fVEybMt284' /></td>";
			}

			tr += "<td>" +obj.address+ "</td>";
			if(1 == obj.status){
				tr += "<td>未上架</td>";
			}else if(2 == obj.status){
				tr += "<td>已上架</td>";
			}else{
				tr += "<td></td>";
			}
			if(1 == obj.type){
				tr += "<td>出租</td>";
			}else if(2 == obj.type){
				tr += "<td>求租</td>";
			}
			
			tr += "<td>" +obj.nickName+ "</td>";
			tr += "<td>" +obj.mobile+ "</td>";
			tr += "<td>" +obj.newstime+ "</td>";

			
			tr += "<td class='listtable'>";
/* 			tr += "<shiro:hasPermission name='per:edit'><a class='btn_modify' href='javascript:void(0);' onclick='edit(" +obj.perId+ ");'>修改</a>&nbsp;&nbsp;</shiro:hasPermission>";
			tr += "<shiro:hasPermission name='per:del'><a class='btn_delete' href='javascript:void(0);' onclick='del(" +obj.perId+ ");'>删除</a>&nbsp;&nbsp;</shiro:hasPermission>"; */
			if(1 == obj.status){
				tr += "<shiro:hasPermission name='release:status'><a class='btn_qy' href='javascript:void(0);' onclick='changeStatus("+obj.status+", " +obj.id+ ","+obj.type+");'>上架</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}else{
				tr += "<shiro:hasPermission name='release:status'><a class='btn_delete' href='javascript:void(0);' onclick='changeStatus("+obj.status+"," +obj.id+ ","+obj.type+");'>下架</a>&nbsp;&nbsp;</shiro:hasPermission>";
			} 
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	function edit(id){
    	var url = "${ctx }/permission/toEdit?perId=" + id;
    	window.location.href=url;
    }

	function del(id){
		if(confirm("你确定要删除此权限吗？")){
			$.ajax({
                type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
                url: "${ctx }/permission/del" , //请求哪个服务器
                dataType: "json" , //响应数据格式(默认为:html)
                data: {'perId': id} , //传递的参数
                cache:false,
                success: function(returnedData){ //"returnedData"为返回结果
                   if("success" == returnedData.result){
                   	 alert('删除成功!');
                   	 search();
                   }else if("existRoles" == returnedData.result){
                	   alert('该权限被角色使用中，不可删除，删除失败!');
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
	
	function changeStatus(status,id, type){
		if(confirm("你确定要上架/下架此信息吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/release/changeStatus" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {'id': id, "type": type,"status":status} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('更改成功!');
	               	 search();
	               }else if("existRoles" == returnedData.result){
	            	   alert('该权限被角色使用中，不可修改状态，修改失败!');
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