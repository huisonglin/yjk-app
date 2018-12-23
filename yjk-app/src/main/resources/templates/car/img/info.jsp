<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
	<!--[if lt IE 9]>
		<script src="${ctx }/static/js/html5.js"></script>
	<![endif]-->
	<script src="${ctx }/static/js/jquery.js"></script>
</head>
<body>

	<div class="basic_hd">
	  <ul>
	    <li><a href="${ctx }/car/toDetail?id=${carId }">基本信息</a></li>
	    <li><a href="${ctx }/car/img/info?carId=${carId }" class="active">车辆图片</a></li>
	    <li><a href="${ctx }/car/check/info?carId=${carId}">车辆检测</a></li>
	  </ul>
	</div>
	
    <div class="page_title">
		<h2 class="fl">
			车源静图详情
		</h2>
	</div>
	
	<ul class="ulColumn2">
	    <!-- 外观右前 -->
	    <li>
		    <span class="item_name" style="width:120px;">外观右前图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.wgYqImg }">
				   		<img src="${img_base_view_path}${staticImg.wgYqImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">外观右前说明：</span>
		    <input name="wgYqRemark" value="${staticImg.wgYqRemark }" placeholder="默认：优秀车况：无重大事故、无水泡、无火烧，性能部件正常使用"  type="text" class="textbox" style="width:400px;" disabled="disabled" />
            
		</li>
		<!-- 外观正侧 -->
		<li>
			<span class="item_name" style="width:120px;">外观正侧：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.wgZcImg }">
				   		<img src="${img_base_view_path}${staticImg.wgZcImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
		    <span class="item_name" style="width:160px;">外观正侧说明：</span>
		    <input name="wgZcRemark" value="${staticImg.wgZcRemark }" placeholder="默认：漆面保持较好，车身结构无修复，无重大事故" type="text" class="textbox" style="width:400px;" disabled="disabled" />  
            
		</li>
		<!-- 外观正前 -->
		<li>	         
		    <span class="item_name" style="width:120px;">外观正前图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.wgZqImg }">
				   		<img src="${img_base_view_path}${staticImg.wgZqImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">外观正前说明：</span>
		    <input name="wgZqRemark" value="${staticImg.wgZqRemark }" placeholder="默认：前脸完好"  type="text" class="textbox textbox_295" disabled="disabled" />
           </li>
		<!-- 外观正后 -->
		<li>	         
		    <span class="item_name" style="width:120px;">外观正后图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.wgZhImg }">
				   		<img src="${img_base_view_path}${staticImg.wgZhImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">外观正后说明：</span>
		    <input name="wgZhRemark" value="${staticImg.wgZhRemark }" placeholder="默认：左右对称性正常"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 车灯图片 -->
		<li>	         
		    <span class="item_name" style="width:120px;">车灯图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.cdImg }">
				   		<img src="${img_base_view_path}${staticImg.cdImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				</c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">车灯说明：</span>
		    <input name="cdRemark" value="${staticImg.cdRemark }" placeholder="默认：功能完全，使用正常" type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 轮胎 -->
		<li>
		    <span class="item_name" style="width:120px;">轮胎图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.ltImg }">
				   		<img src="${img_base_view_path}${staticImg.ltImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">轮胎说明：</span>
		    <input name="ltRemark" value="${staticImg.ltRemark }" placeholder="默认：轮胎深度正常，胎压正常"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 车内顶棚 -->
		<li>	         
		    <span class="item_name" style="width:120px;">车内顶棚图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.cndpImg }">
				   		<img src="${img_base_view_path}${staticImg.cndpImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">车内顶棚说明：</span>
		    <input name="cndpRemark" value="${staticImg.cndpRemark }" placeholder="默认：无烟熏、污渍痕迹，电控开关使用正常"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 车门 -->
		<li>	         
		    <span class="item_name" style="width:120px;">车门图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.cmImg }">
				   		<img src="${img_base_view_path}${staticImg.cmImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">车门说明：</span>
		    <input name="cmRemark" value="${staticImg.cmRemark }" placeholder="默认：门轴无拆卸痕迹，操控健正常使用"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 密封胶条 -->
		<li>	         
		    <span class="item_name" style="width:120px;">密封胶条图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.mfjtImg }">
				   		<img src="${img_base_view_path}${staticImg.mfjtImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">密封胶条说明：</span>
		    <input name="mfjtRemark" value="${staticImg.mfjtRemark }" placeholder="默认：密封胶条无老化" type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 安全带根部 -->
		<li>	         
		    <span class="item_name" style="width:120px;">安全带根部图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.aqdgbImg }">
				   		<img src="${img_base_view_path}${staticImg.aqdgbImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">安全带根部说明：</span>
		    <input name="aqdgbRemark" value="${staticImg.aqdgbRemark }"  placeholder="默认：无水泡痕迹、无霉斑、无异味，非泡水车" type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 内饰前排 -->
		<li>
		    <span class="item_name" style="width:120px;">内饰前排图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.nsQpImg }">
				   		<img src="${img_base_view_path}${staticImg.nsQpImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">内饰前排说明：</span>
		    <input name="nsQpRemark" value="${staticImg.nsQpRemark }" placeholder="默认：车身内饰干净整洁" type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 内饰后排 -->
		<li>        
		    <span class="item_name" style="width:120px;">内饰后排图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.nsHpImg }">
				   		<img src="${img_base_view_path}${staticImg.nsHpImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">内饰后排说明：</span>
		    <input name="nsHpRemark" value="${staticImg.nsHpRemark }" placeholder="默认：座椅几乎无磨损"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 内饰中控 -->
		<li>        
		    <span class="item_name" style="width:120px;">内饰中控图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.nsZkImg }">
				   		<img src="${img_base_view_path}${staticImg.nsZkImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">内饰中控说明：</span>
		    <input name="nsZkRemark" value="${staticImg.nsZkRemark }" placeholder="默认：安全指示灯正常，气囊的被动安全项正常" type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 仪表盘 -->
		<li>        
		    <span class="item_name" style="width:120px;">仪表盘图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.ybpImg }">
				   		<img src="${img_base_view_path}${staticImg.ybpImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">仪表盘说明：</span>
		    <input name="ybpRemark" value="${staticImg.ybpRemark }" placeholder="默认：当前5.25万公里" type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 变速杆 -->
		<li>        
		    <span class="item_name" style="width:120px;">变速杆图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.bsgImg }">
				   		<img src="${img_base_view_path}${staticImg.bsgImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">变速杆说明：</span>
		    <input name="bsgRemark" value="${staticImg.bsgRemark }"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 油门踏板 -->
		<li>
		    <span class="item_name" style="width:120px;">油门踏板图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.ymtbImg }">
				   		<img src="${img_base_view_path}${staticImg.ymtbImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">油门踏板说明：</span>
		    <input name="ymtbRemark" value="${staticImg.ymtbRemark }"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 发动机舱 -->
		<li>  
		    <span class="item_name" style="width:120px;">发动机舱图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty staticImg.fdjcImg }">
				   		<img src="${img_base_view_path}${staticImg.fdjcImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">发动机舱说明：</span>
		    <input name="fdjcRemark" value="${staticImg.fdjcRemark }" placeholder="默认：油液位及品质正常，油封不泄露，线路管无剥落"   type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
		<!-- 后背箱 -->
		<li>  
		    <span class="item_name" style="width:120px;">后背箱图片：</span>
		    <label class="single_selection">
		        <c:choose>
				   <c:when test="${not empty staticImg.hbxImg }">
				   		<img src="${img_base_view_path}${staticImg.hbxImg }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">后背箱说明：</span>
		    <input name="hbxRemark" value="${staticImg.hbxRemark }" placeholder="默认：备胎坑形状正常，无切割，无变形"  type="text" class="textbox textbox_295" disabled="disabled" />
             
		</li>
	</ul>
	
	<div class="page_title">
		<h2 class="fl">
			车源其他图片详情
		</h2>
	</div>
	<ul class="ulColumn2">
	    <c:forEach items="${dynamicImgList }" var="dynamicImg">
		<li>  
		    <span class="item_name" style="width:120px;">图片：</span>
		    <label class="single_selection">
		         <c:choose>
				   <c:when test="${not empty dynamicImg.imgPath }">
				   		<img src="${img_base_view_path}${dynamicImg.imgPath }" style="width:100px;height:60px;" />
				   </c:when>
				   <c:otherwise>
				   	    <img src="${ctx }/static/images/nopic.jpg" width="100px" height="60px">
				   </c:otherwise>
				 </c:choose>
			</label>
			
			<span class="item_name" style="width:160px;">说明：</span>
		    <input name="fdjcRemark" value="${dynamicImg.remark }" type="text" class="textbox textbox_295" disabled="disabled" />
		</li>
		</c:forEach>
		<c:if test="${empty dynamicImgList }">
		    <li style="color:red;text-align:center;">
				无车源其他图片数据...
			</li>
		</c:if>
	</ul>
	
	<ul class="ulColumn2">
		<li>
			<span class="item_name" style="width: 120px;"></span> 
			<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
		</li>
	</ul>
	
<script type="text/javascript">
	 $(document).ready(function (){
		 $('#back').click(function(){
			 location.href = "${ctx }/car/toList";
	     });
	 });
</script>
</body>
</html>