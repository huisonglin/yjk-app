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
		<h2 class="fl">二级机械型号列表</h2>
			<a href="${ctx }/twoStageModel/toAdd?modelId=${pid}" target="mainContent" class="fr top_rt_btn add_icon">添加机械型号</a>
<%-- 		<shiro:hasPermission name="per:add">
			<a href="${ctx }/permission/toAdd" target="mainContent" class="fr top_rt_btn add_icon">添加权限</a>
	    </shiro:hasPermission> --%>
	</div>

	<section class="mtb"> 
	   <input type="text" class="textbox textbox_225" placeholder="请输入型号名称" id="keyword" name="keyword" /> 
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
	   <input type="button" value="返回上级" class="group_btn" id="back" /> 
    </section> 
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th style="width:200px;">型号名称</th>
		        <th style="width:100px;">状态</th>
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
    	$('#back').click(function(){
   	     window.location.href="${ctx }/model/toList";
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
				url:'${ctx}/twoStageModel/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
 					keyword : $("#keyword").val() ,
 					pid:'${pid}'
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
	
	function edit(id){
		var url = "${ctx }/twoStageModel/toEdit?twoStageModelId=" + id;
		window.location.href=url;
	}
	
	function detail(id){
		var url = "${ctx }/spec/toList?pid=" + id;
		window.location.href=url;
	}
	
	function createTR(obj) {
		var tr = "<tr>";
			tr += "<td>" +obj.name+ "</td>";
			if(obj.status == 1){
				tr += "<td>显示</td>";
			}else{
				tr += "<td>隐藏</td>";
			}
			
			tr += "<td class='listtable'>";
			tr += "<a class='btn_modify' href='javascript:void(0);' onclick='edit(" +obj.id+ ");'>修改</a>&nbsp;&nbsp;";
			tr += "<a class='btn_modify' href='javascript:void(0);' onclick='detail(" +obj.id+ ");'>查看规格</a>&nbsp;&nbsp;";
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}


</script>
</body>
</html>