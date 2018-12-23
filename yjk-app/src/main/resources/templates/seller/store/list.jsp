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
	<script src="${ctx }/static/js/myArea.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.myPagination.js" charset="UTF-8"></script>
</head>
<body>

    <div class="page_title">
		<h2 class="fl">门店列表</h2>
	</div>

	<section class="mtb">
	   <input type="text" class="textbox textbox_225" placeholder="请输入门店名称..." id="storeName" name="storeName" /> 
	   &nbsp;&nbsp;&nbsp;&nbsp;
	   <select class="select" id="province" name="province" onchange="return changeAreaOfDistrict('province', 'city');" onfocus="return changeAreaOfDistrict('province', 'city');" style="width:120px;">
           <option value="">---请选择省---</option>
           <c:forEach items="${provinceList }" var="province">
           		<option value="${province.areaCode }">${province.areaName }</option>
           </c:forEach>
        </select>
        <select class="select" id="city" name="city" style="width:120px;">
            <option value="">---请选择市---</option>
        </select>
        
	   <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th>门店名称</th>
		        <th>电话</th>
		        <th>地址</th>
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
				url:'${ctx}/seller/info/store/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					storeName : $("#storeName").val(),
					province : $("#province").val(),
					city : $("#city").val()
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
			tr += "<td>" +obj.storeName+ "</td>";
			tr += "<td>" +obj.tel+ "</td>";
			tr += "<td>" +obj.provinceName + obj.cityName + obj.address + "</td>";
			tr += "<td>";
			tr += "<a class='link_icon' href='javascript:void(0);' onclick='getSelectToParentPage(" +obj.storeId+ ", " +'"'+obj.storeName+'"'+ ");'>选择</a>&nbsp;&nbsp;";
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	function getSelectToParentPage(storeId, storeName){
		window.opener.document.getElementById("storeId").value = storeId;
        window.opener.document.getElementById("storeName").value = storeName;
        closeWindow();
	}
	
	function closeWindow(){
    	window.close();
    }
</script>
</body>
</html>