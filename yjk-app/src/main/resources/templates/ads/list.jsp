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
		<h2 class="fl">广告列表</h2>
		<shiro:hasPermission name="ads:add">
	    	<a href="${ctx }/ads/toAdd" target="mainContent" class="fr top_rt_btn add_icon">添加广告</a>
	    </shiro:hasPermission>
	</div>

	<section class="mtb"> 
	    <span class="item_name" style="width:120px;">广告位置：</span>
        <select id="adsPositon" class="select" style="width:120px;">
           <option value="">---全部---</option>
           <c:forEach items="${adsPositons }" var="position">
           		<option value="${position.value }">${position.name }</option>
           </c:forEach>
        </select>
        
	    <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>广告缩略图</th>
		        <th>跳转URL</th>
		        <th>广告位置</th>
		        <th>状态</th>
		        <th>发布时间</th>
		        <th width="220">操作</th>
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
				url:'${ctx}/ads/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					adsPositon : $("#adsPositon").val()
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
		    if("" != obj.adsImg){
		    	tr += "<td><img style='widht:100px;height:60px;' src='${img_base_view_path}" +obj.adsImg+ "' /></td>";
		    }else{
		    	tr += "<td><img style='widht:100px;height:60px;' src='${ctx }/static/images/nopic.jpg'/></td>";
		    }
			
			tr += "<td>" +obj.adsUrl+ "</td>";
			
			//广告位置
			if(1 == obj.adsPositon){
				tr += "<td>首页</td>";
			}else if(2 == obj.adsPositon){
				tr += "<td>列表页</td>";
			}else if(3 == obj.adsPositon){
				tr += "<td>详情页</td>";
			}
			
			if(1 == obj.status){//启用
				tr += "<td><font color='#090'>已启用</font></td>";
			}else{
				tr += "<td><font color='red'>已禁用</font></td>";
			}
			
			tr += "<td>" +obj.pubTime+ "</td>";
			tr += "<td class='listtable'>";
			if(1 == obj.status){//启用
				tr += "<shiro:hasPermission name='ads:status'><a class='btn_delete' href='javascript:void(0);' onclick='changeStatus(2, " +obj.adsPositon+ ", " +obj.adsId+ ");'>禁用</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}else{
				tr += "<shiro:hasPermission name='ads:edit'><a class='btn_modify' href='javascript:void(0);' onclick='edit(" +obj.adsId+ ");'>修改</a>&nbsp;&nbsp;</shiro:hasPermission>";
				tr += "<shiro:hasPermission name='ads:del'><a class='btn_admin' href='javascript:void(0);' onclick='del(" +obj.adsId+ ");'>删除</a>&nbsp;&nbsp;</shiro:hasPermission>";
				tr += "<shiro:hasPermission name='ads:status'><a class='btn_qy' href='javascript:void(0);' onclick='changeStatus(1, " +obj.adsPositon+ "," +obj.adsId+ ");'>启用</a>&nbsp;&nbsp;</shiro:hasPermission>";
			}
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	function edit(id){
    	var url = "${ctx }/ads/toEdit?id=" + id;
    	window.location.href=url;
    }

	function del(id){
	   if(confirm("你确定要删除此广告吗？")){
			$.ajax({
                type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
                url: "${ctx }/ads/del" , //请求哪个服务器
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
	
	function changeStatus(newStatus, adsPositon, id){
	    if(confirm("你确定要启用/禁用此广告吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/ads/updateStatusById" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {'status': newStatus, 'adsPositon': adsPositon, "id": id} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('更改成功!');
	               	 search();
	               }else if("isUsed" == returnedData.result){
	            	   alert('该广告位已存在使用的广告，更改失败!');
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