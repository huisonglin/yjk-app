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
    <script src="${ctx }/static/js/myCar.js"></script>
</head>
<body>
	<div class="page_title">
		<h2 class="fl">车源信息列表</h2>
	</div>

	<section class="mtb">
	    <span class="item_name" style="width: 120px;">所属门店：</span>
	    <select class="select" id="storeId" name="storeId" style="width:180px;">
            <option value=''>---请选择门店---</option>
            <c:forEach items="${storeList }" var="store">
            	<option value='${store.storeId }'>${store.storeName }</option>
            </c:forEach>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        
	    <span class="item_name" style="width: 120px;">品牌/车系/车型：</span> 
	    <select class="select" id="brandId" name="brandId" onchange="return searchCarModelList('brandId', 'modelId', 'typeId');" onfocus="return searchCarModelList('brandId', 'modelId', 'typeId');" style="width:120px;">
            <option value=''>---请选择品牌---</option>
            <c:forEach items="${brandList }" var="brand">
               <option value="${brand.brandId }">${brand.brandName }</option>
            </c:forEach>
        </select>
        <select class="select" id="modelId" name="modelId" onchange="return searchCarTypeList('modelId', 'typeId');" onfocus="return searchCarTypeList('modelId', 'typeId');" style="width:120px;">
            <option value=''>---请选择车系---</option>
        </select>
        <select class="select" id="typeId" name="typeId" style="width:180px;">
            <option value=''>---请选择车型---</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <br />
        <br />
        <span class="item_name" style="width: 120px;">状　　态：</span>
        <select class="select" id="status" name="status" style="width:180px;">
            <option value=''>---请选择状态---</option>
            <c:forEach items="${carStatusList }" var="status">
               <option value="${status.value }"
                  <c:if test="${status.value eq delStatus or status.value eq caogaoStatus }">
                  	style="display:none;"
                  </c:if>
               >${status.name }</option>
            </c:forEach>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        
	    <input type="button" value="查询" class="group_btn" id="searchBtn" /> 
    </section>
    <br />
	<table id="mypage" class="table" style="font-size:14px;text-align:center;">
		<thead>
		    <tr>
		        <th style="width:20%;">名称</th>
		        <th style="width:10%;">状态</th>
		        <th style="width:15%;">发布时间</th>
		        <th style="width:10%;">所属门店</th>
		        <th style="width:45%;">操作</th>
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
				url:'${ctx }/car/list',//访问服务器地址
				dataType:'json', //返回类型
				contentType:"application/json",      
				param : {
					on : true,
					page : 1,
					pageCountId : 'pageCount', //后台不需要处理此参数
					storeId : $("#storeId").val(),
					brandId : $("#brandId").val(),
					modelId : $("#modelId").val(),
					typeId : $("#typeId").val(),
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
		if("" != result){
			$.each(result, function(index, items){
				insetViewData += createTR(items);
			});
		}else{
			insetViewData+= "<tr><td colspan='5' bgcolor='#FFFFFF' style='text-align:center;'><font color='red'>无记录...</font></td></tr>"
		}
		$("#mypage > tbody").html(insetViewData);
	}

	function createTR(obj) {
		var tr = "<tr>";
			tr += "<td>" +obj.title+ "</td>";
			
			if(1 == obj.status){
				tr += "<td><font color='#00B38B'>草稿</font></td>";
			}else if(2 == obj.status){
				tr += "<td><font color='#fe990b'>待审核</font></td>";
			}else if(3 == obj.status){
				tr += "<td><font color='#090'>审核未通过</font></td>";
			}else if(4 == obj.status){
				tr += "<td><font color='#000'>审核通过</font></td>";
			}else if(5 == obj.status){
				tr += "<td><font color='#090'>已上架</font></td>";
			}else if(6 == obj.status){
				tr += "<td><font color='red'>已下架</font></td>";
			}else if(7 == obj.status){
				tr += "<td><font color='red'>强制下架</font></td>";
			}else if(8 == obj.status){
				tr += "<td><font color='#090'>已卖出</font></td>";
			}else if(9 == obj.status){
				tr += "<td><font color='red'>已删除</font></td>";
			}else{
				tr += "<td><font color='#090'>其他</font</td>";
			}
			tr += "<td>" +obj.createTime+ "</td>";
			tr += "<td>" +obj.storeName+ "</td>";
			
			tr += "<td class='listtable'>";
			tr += "<a class='btn_qy' href='javascript:void(0);' onclick='showCarBase(" +obj.id+ ");'>基本信息</a>&nbsp;&nbsp;";
			tr += "<a class='btn_modify' href='javascript:void(0);' onclick='showCarImg(" +obj.id+ ");'>车源图片</a>&nbsp;&nbsp;";
			tr += "<a class='btn_admin' href='javascript:void(0);' onclick='showCarCheck(" +obj.id+ ");'>车源检测数据</a>&nbsp;&nbsp;";
			if(2 == obj.status){//待审核
				tr += "<a class='btn_modify' href='javascript:void(0);' onclick='toReview(" +obj.id+ "," +obj.status+ ", " +obj.isCheckedSaleon+ ");'>审核</a>&nbsp;&nbsp;";
			}else if(5 == obj.status){//已上架
				tr += "<a class='btn_qy' href='javascript:void(0);' onclick='forcedSaleOff(" +obj.id+ "," +obj.status+ ");'>强制下架</a>&nbsp;&nbsp;";
			}else if(7 == obj.status){//强制下架
				tr += "<a class='btn_delete' href='javascript:void(0);' onclick='saleOnAgain(" +obj.id+ "," +obj.status+ ");'>再上架</a>&nbsp;&nbsp;";
			}
			tr += "</td>";
			tr += "</tr>";
		return tr;
	}
	
	function showCarBase(id){
		var url = "${ctx }/car/toDetail?id=" + id;
    	window.location.href=url;
	}
	
	function showCarImg(carId){
		var url = "${ctx }/car/img/info?carId=" + carId;
    	window.location.href=url;
	}
	
	function showCarCheck(carId){
		var url = "${ctx }/car/check/info?carId=" + carId;
    	window.location.href=url;
	}
	
	//审核
	function toReview(carId, oldStatus, isCheckedSaleon){
		var url = "${ctx }/car/toReview?carId=" + carId + "&oldStatus=" + oldStatus + "&isCheckedSaleon=" + isCheckedSaleon;
    	window.location.href=url;
	}
	
	//强制下架
	function forcedSaleOff(carId, oldStatus){
		if(confirm("你确定要强制下架此车源吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/car/forcedSaleOff" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {"carId": carId, "oldStatus": oldStatus} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('操作成功!');
	               	 search();
	               }else if("infoChange" == returnedData.result){
	            	   alert('操作失败，车源信息已被修改，请确认后重试！');
	                   search();
	               }else{
	            	   alert('操作失败，请稍后重试!');
	               }
	            },
	            error: function(){
	               //错误处理
	           	   alert('网络异常，请重试!');
	            }
	        });
		}
	}
	
	//再上架
	function saleOnAgain(carId, oldStatus){
		if(confirm("你确定要再上架此车源吗？")){
			$.ajax({
	            type: "get" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	            url: "${ctx }/car/saleOnAgain" , //请求哪个服务器
	            dataType: "json" , //响应数据格式(默认为:html)
	            data: {"carId": carId, "oldStatus":oldStatus} , //传递的参数
	            cache:false,
	            success: function(returnedData){ //"returnedData"为返回结果
	               if("success" == returnedData.result){
	               	 alert('操作成功!');
	               	 search();
	               }else if("infoChange" == returnedData.result){
	            	   alert('操作失败，车源信息已被修改，请确认后重试！');
	                   search();
	               }else{
	            	   alert('操作失败，请稍后重试!');
	               }
	            },
	            error: function(){
	               //错误处理
	           	   alert('网络异常，请重试!');
	            }
	        });
		}
	}
</script>
</body>
</html>