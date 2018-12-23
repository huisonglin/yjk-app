//"品牌、车系、车型"联动之选择品牌,改变车系的值
function searchCarModelList(id, nextId, nextNextId){
     var brand = $("#"+id).val();
	 if(!brand){
	    $("#"+nextId).empty();
	    $("#"+nextId).append("<option value=''>---请选择车系---</option>");
		$("#"+nextNextId).empty();
	    $("#"+nextNextId).append("<option value=''>---请选择车型---</option>");
	    return false;
	 }
	 
	 jQuery.ajax({
            type:"post", //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
            url:ctx+"/common/car/searchCarModelList", //请求哪个服务器
            dataType:"json", //响应数据格式(默认为:html)
            data:{"brandId":brand},
            cache:false,
            success:function(data){
    		    var parents = data.parent;
    			$("#"+nextId).html("");
    			
    		    $("#"+nextId).append("<option value=''>---请选择车系---</option>");
    		    jQuery.each(parents, function(index, parent) {
    	             $("#"+nextId).append("<option value='' disabled='disabled' style='font-weight:bold;font-size:16px;'>"+parent.modelName+"</option>");
    	             
    	             var childs = parent.child;
    	             jQuery.each(childs, function(index, child) {
        	             $("#"+nextId).append("<option value='" +child.modelId+ "'>"+child.modelName+"</option>");  
                     });
                });
    		    
    		    $("#"+nextNextId).empty();
    	        $("#"+nextNextId).append("<option value=''>---请选择车型---</option>");
            },
            error: function(){
                //错误处理
               	alert('系统异常，请重试!');
            }
        });
}

//"品牌、车系、车型"联动之选择车系,改变车型的值
function searchCarTypeList(id, nextId){
     var model = $("#"+id).val();
	 if(!model){
	    $("#"+nextId).empty();
	    $("#"+nextId).append("<option value=''>---请选择车型---</option>");
	    return false;
	 }
	 
	 jQuery.ajax({
            type:"post", //异步请求的类型(请求方式若为post,只需改变这个参数为post即可！)
            url:ctx+"/common/car/searchCarTypeList", //请求哪个服务器
            dataType:"json",//响应数据格式(默认为:html)
            data:{"modelId":model}, //传递的参数
            cache:false,
            success:function(data){
    		    var parents = data.parent;
    			$("#"+nextId).html("");
    			
        		$("#"+nextId).append("<option value=''>---请选择车型---</option>");
        		jQuery.each(parents, function(index, parent) {
        			$("#"+nextId).append("<option value='' disabled='disabled' style='font-weight:bold;font-size:16px;'>"+parent.typeName+"</option>");
   	             
	   	             var childs = parent.child;
	   	             jQuery.each(childs, function(index, child) {
	       	             $("#"+nextId).append("<option value='" +child.typeId+ "'>"+child.typeName+"</option>");  
	                 });
                });
            },
            error: function(){
                //错误处理
               	alert('系统异常，请重试!');
            }
        });
}