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
		<h2 class="fl">公告列表</h2>
		<shiro:hasPermission name="notice:add">
	    	<a href="${ctx }/notice/toAdd" target="mainContent" class="fr top_rt_btn add_icon">添加公告</a>
		</shiro:hasPermission>
	</div>

	<section class="mtb"> 
	    <span class="item_name" style="width:120px;">类型：</span>
        <select id="type" class="select" style="width:120px;">
           <option value="">---全部---</option>
           <c:forEach items="${noticeTypes }" var="type">
           		<option value="${type.value }">${type.name }</option>
           </c:forEach>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <span class="item_name" style="width:120px;">状态：</span>
        <select id="status" class="select" style="width:120px;">
           <option value="">---全部---</option>
           <c:forEach items="${noticeStatuss }" var="status">
           		<option value="${status.value }">${status.name }</option>
           </c:forEach>
        </select>
        
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>标题</th>
		        <th>缩略图</th>
		        <th>描述</th>
		        <th>类型</th>
		        <th>状态</th>
		        <th>发布时间</th>
		        <th width="230">操作</th>
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
				url:'${ctx}/notice/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					type : $("#type").val(),
					status : $("#status").val()
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
			insetViewData+= "<tr><td colspan='7' bgcolor='#FFFFFF' style='text-align:center;'><font color='red'>无记录...</font></td></tr>"
		}
		$("#mypage > tbody").html(insetViewData);
	}

	function createTR(obj) {
		var tr = "<tr>";
		
			var title = obj.title;
			if(title && title.length > 10){
				title = title.substr(0, 10) + "...";
			}
			tr += "<td>" +title+ "</td>";
		   
		    if('' != obj.titleImg){
		    	tr += "<td><img style='widht:100px;height:60px;' src='${img_base_view_path}" +obj.titleImg+ "' /></td>";
		    }else{
		    	tr += "<td><img style='widht:100px;height:60px;' src='${ctx }/static/images/nopic.jpg'/></td>";
		    }
			
		    var descri = obj.description;
		    if(descri.length > 25){
		    	descri = descri.substring(0, 25) + "...";
		    }
		    tr += "<td>" +descri+ "</td>";
			
			//类型
			if(1 == obj.type){
				tr += "<td>加盟故事</td>";
			}else if(2 == obj.type){
				tr += "<td>媒体报道</td>";
			}else if(3 == obj.type){
				tr += "<td>公司公告</td>";
			}
			
			if(1 == obj.status){//启用
				tr += "<td><font color='#090'>已启用</font</td>";
			}else{
				tr += "<td><font color='red'>已禁用</font</td>";
			}
			
			tr += "<td>" +obj.pubTime+ "</td>";
			tr += "<td class='listtable'>";
			if(1 == obj.status){//启用
				tr += "<shiro:hasPermission name='notice:status'><a class='btn_delete' href='javascript:void(0);' onclick='changeStatus(2, " +obj.noticeId+ ");'>禁用</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}else{
				tr += "<shiro:hasPermission name='notice:edit'><a class='btn_modify' href='javascript:void(0);' onclick='edit(" +obj.noticeId+ ");'>修改</a>&nbsp;&nbsp;</shiro:hasPermission>";
				tr += "<shiro:hasPermission name='notice:del'><a class='btn_admin' href='javascript:void(0);' onclick='del(" +obj.noticeId+ ");'>删除</a>&nbsp;&nbsp;</shiro:hasPermission>";
				tr += "<shiro:hasPermission name='notice:status'><a class='btn_qy' href='javascript:void(0);' onclick='changeStatus(1," +obj.noticeId+ ");'>启用</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	function edit(id){
    	var url = "${ctx }/notice/toEdit?id=" + id;
    	window.location.href=url;
    }

	function del(id){
		if(confirm("你确定要删除此公告吗？")){
			$.ajax({
                type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
                url: "${ctx }/notice/del" , //请求哪个服务器
                dataType: "json" , //响应数据格式(默认为:html)
                data: {'id': id} , //传递的参数
                cache:false,
                success: function(returnedData){ //"returnedData"为返回结果
                   if("success" == returnedData.result){
                   	 alert('删除成功!');
                   	 search();
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
		if(confirm("你确定要启用/禁用此公告吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/notice/updateStatusById" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {'status': newStatus, "id": id} , //传递的参数
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
</script>
</body>
</html>