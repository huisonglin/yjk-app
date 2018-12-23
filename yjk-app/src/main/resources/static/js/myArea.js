function changeAreaOfDistrict(id, nextId){
	 var parentCode = $("#"+id).val();
	 if(!parentCode){
	    $("#"+nextId).empty();
	    $("#"+nextId).append("<option value=''>--请选择市--</option>");
	    return false;
	 }
	 
	 jQuery.ajax({
	        type: "post" , //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
	        url: ctx + "/common/area/searchCityOrDistrictList" , //请求哪个服务器
	        dataType: "json" , //响应数据格式(默认为:html)
	        data: {"parentCode":parentCode} , //传递的参数
	        cache:false,
	        success: function(returnedData){ //"returnedData"为返回结果
			    var cityOrDistrictList = returnedData;
				$("#"+nextId).html("");
				
	    		$("#"+nextId).append("<option value=''>---请选择市---</option>");
	    		jQuery.each(cityOrDistrictList, function(index, cityOrDistrict) {
	    	         $("#"+nextId).append("<option value='"+cityOrDistrict.areaCode+"'>"+cityOrDistrict.areaName+"</option>");  
	            });
	        },
	        error: function(){
	            //错误处理
	           	alert('系统异常，请重试!');
	        }
	});
}