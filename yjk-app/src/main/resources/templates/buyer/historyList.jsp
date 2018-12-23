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
	<!-- layer begin -->
	<link rel="stylesheet" href="${ctx }/static/js/layer/css/layui.css" media="all">
	<link rel="stylesheet" href="${ctx }/static/js/layer/css/global.css" media="all">
	<script src="${ctx }/static/js/layer/layer.js"></script>
	<!-- layer end -->
</head>
<body>

	<div class="page_title">
		<h2 class="fl">历史购车信息列表</h2>
	</div>

	<section class="mtb">
	   <input type="text" class="textbox textbox_225" placeholder="请输入手机号..." id="phone" name="phone" /> 
	   
	   <span class="item_name" style="width:120px;">信息来源：</span>
       <select id="from" class="select" style="width:120px;">
           <option value="">---全部---</option>
           <c:forEach items="${fromList }" var="from">
           		<option value="${from.value }">${from.name }</option>
           </c:forEach>
       </select>
        
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>姓名</th>
		        <th>手机号</th>
		        <th>地域</th>
		        <th>来源</th>
		        <th>状态</th>
		        <th>时间</th>
		        <th style="width:180px;">操作</th>
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
    		layer.alert(msg);
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
				url:'${ctx}/buyer/info/historyList',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					phone : $("#phone").val(),
					from : $("#from").val()
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
		    tr += "<td>" +obj.name+ "</td>";
		    tr += "<td>" +obj.phone+ "</td>";
			tr += "<td>" +obj.provinceName+ "" +obj.cityName+ "</td>";
			//来源
			if(1 == obj.from){
				tr += "<td>平台</td>";
			}else if(2 == obj.from){
				tr += "<td>门店</td>";
			}
			//状态
			if(1 == obj.status){
				tr += "<td><font color='#fe990b'>待完善信息</font></td>";
			}else if(2 == obj.status){
				tr += "<td><font color='#090'>待分配门店</font></td>";
			}else if(3 == obj.status){
				tr += "<td><font color='#000'>待分配业务员</font></td>";
			}else if(4 == obj.status){
				tr += "<td><font color='#fe990b'>待联系</font></td>";
			}else if(5 == obj.status){
				tr += "<td><font color='#090'>待跟踪</font></td>";
			}else if(6 == obj.status){
				tr += "<td><font color='red'>未完成交易</font></td>";
			}else if(7 == obj.status){
				tr += "<td><font color='#090'>已成功买车</font></td>";
			}else{
				tr += "<td><font color='#000'>不详</font></td>";
			}
			
			tr += "<td>" +obj.updateTime+ "</td>";
			
			tr += "<td class='listtable'>";
			tr += "<a class='btn_qy' href='javascript:void(0);' onclick='view(" +obj.infoId+ ");'>查看</a>&nbsp;&nbsp;";
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}

	
	//详情
	function view(id){
		location.href = "${ctx }/buyer/info/toDetail?id=" + id;
	}
</script>
</body>
</html>