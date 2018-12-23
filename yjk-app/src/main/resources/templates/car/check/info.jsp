<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
<!--
[if lt IE 9]>
	<script src="${ctx }/static/js/html5.js"></script>
<![endif]
-->
<script src="${ctx }/static/js/jquery.js"></script>

<script type="text/javascript">
$(function() {
	//初始化
	var infoBox = $(".info-box");
	for(var i = 0; i < infoBox.length; i++){
		var rows = $(this).find('.row');
		for(var j = 0; j < rows.length; j++){
			var isExist = $(rows[j]).find(".qi_radio").length;
			if(1 == isExist){
				$(rows[j]).find(".cb-enable").addClass("selected");
				$(rows[j]).find(".cb-disable").removeClass("selected");
			}else{
				$(rows[j]).find(".cb-enable").removeClass("selected");
				$(rows[j]).find(".cb-disable").addClass("selected");
			}
		}
	}
});
</script>
</head>
<body>
    <div class="basic_hd">
	  <ul>
	    <li><a href="${ctx }/car/toDetail?id=${carId }">基本信息</a></li>
	    <li><a href="${ctx }/car/img/info?carId=${carId }">车辆图片</a></li>
	    <li><a href="${ctx }/car/check/info?carId=${carId }" class="active">车辆检测</a></li>
	  </ul>
	</div>
	
	<section>
	<div class="info-box">
		<ul class="side-nav" id="J_m_nav">
			<c:forEach items="${firstCategoryList }" var="firstCategory"  varStatus="varstatus">
				<li
					<c:if test="${firstCategory.id eq bigCategoryId}">
						class="active"
					</c:if>
				>
					<a href="${ctx }/car/check/info?carId=${carId}&bigCategoryId=${firstCategory.id}&categoryCode=${firstCategory.categoryCode}" class="li${varstatus.index+1}">
						${firstCategory.categoryName }
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<!-- check remark begin-->
		<c:forEach items="${firstCategoryList }" var="firstCategory">
			<c:if test="${firstCategory.id eq bigCategoryId }">
			    <span style="font-size:18px;">
					<b>${firstCategory.categoryName }</b>整体情况说明：
				</span>
			</c:if>
		</c:forEach>
	    
		<ul class="ulColumn2">
			<li>
				<span class="item_name" style="width: 120px;">说明：</span>
				<input type="text" class="textbox" style="width:500px;" name="remark" value="${checkRemark.remark }" 
				    <c:choose>
				       <c:when test="${categoryCode eq 'QDJC'}">
				       		placeholder="发动机启动正常，怠速平稳无抖动，变速器换挡平顺，功能正常。"
				       </c:when>
				       <c:when test="${categoryCode eq 'CYGNJC'}">
				       		placeholder="经检测，电子控制系统、功能开关灯光系统等无异常，可正常使用。"
				       </c:when>
				       <c:when test="${categoryCode eq 'MSCDPG'}">
				       		placeholder="经检测局部有钣金修复。"
				       </c:when>
				       <c:when test="${categoryCode eq 'HXBJJC'}">
				            placeholder="经检测，刹车系统、被动系统、指示灯系统正常。"
				       </c:when>
				       <c:when test="${categoryCode eq 'SGPC'}">
				            placeholder="车体骨架结构无变形、无扭曲、无更换、无烧焊、无褶皱；无火烧痕迹，无水泡痕迹。"
				       </c:when>
				       <c:otherwise>
				            placeholder=""
				       </c:otherwise>
				    </c:choose>
				/>
			</li>
		</ul>
		<!-- check remark end-->

		<c:forEach items="${carCheckList }" var="carCheck">
			<h3 class="infor_title">
				${carCheck.categoryName }
				<div class="tit-btn onoff">
					<a class="checkspn fl" href="javascript:void(0);">全部设为：</a> 
					<a class="checkall checkall_1 cb-enable" href="javascript:void(0);">有</a>
					<a class="checkall checkall_0 cb-disable selected" href="javascript:void(0);">无</a>
				</div>
			</h3>
			<div class="details">
				<c:forEach items="${carCheck.checkDetails }" var="checkDetail">
				<dl class="row">
					<dt class="tit">
						${checkDetail.checkName }
					</dt>
					<dd class="opt">
						<div class="onoff">
                			<a class="cb-enable" href="javascript:void(0);">有</a>
							<a class="cb-disable selected" href="javascript:void(0);">无</a>
						</div>
					</dd>
					
					<c:forEach items="${checkRelationList }" var="checkRelation">
					<!-- yes pic -->
					<c:if test="${checkDetail.id eq checkRelation.checkId and  'CTFGJ' eq carCheck.categoryCode}">
					    <!-- hidden-->
					    <input id="exist_${checkDetail.id }" value="${checkDetail.id }" type="hidden" />
					    
						<div class="qi_radio">
							<c:forEach items="${carCheckRelationTypes }" var="type" varStatus="s">
					        	 <label class="single_selection"> 
									<input type="radio" name="checkTypeRemark" value="${type.value }"
									    <c:if test="${type.value eq checkRelation.checkTypeRemark}">
									    	checked = "checked"
									    </c:if>
									>${type.name }
								 </label>
					        </c:forEach>
					        
					        <br />
					        <span>描述:</span> 
						 	${checkRelation.checkTextRemark }
						 	
							<div class="qi_pic">
								<div class="tits">缺陷图片</div>
								<div class="upload_img">
									<c:choose>
									   <c:when test="${not empty checkRelation.checkImgRemark }">
									   		<img src="${img_base_view_path }${checkRelation.checkImgRemark}" width="133px" height="94px">
									   </c:when>
									   <c:otherwise>
									   	    <img src="${ctx }/static/images/nopic.jpg" width="133px" height="94px">
									   </c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:if>
					<!-- no pic -->
					<c:if test="${checkDetail.id eq checkRelation.checkId and  'CTFGJ' ne carCheck.categoryCode}">
					   <!-- hidden-->
					   <input id="exist_${checkDetail.id }" value="${checkDetail.id }" type="hidden" />
					    
					   <div class="qi_radio">
						 <span>描述:</span> 
						 ${checkRelation.checkTextRemark }
					   </div>
					</c:if>
					</c:forEach>
				</dl>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<section>
	
	<ul class="ulColumn2">
		<li>
			<span class="item_name" style="width: 120px;"></span> 
			<input id="back" type="button" class="link_btn" style="background:#666; border:1px solid #666;" value="返回" />
		</li>
	</ul>
	
<script type="text/javascript">
   $(document).ready(function (){
	   $("#i_content", window.parent.document).scrollTop(0);
	   
	   $('#back').click(function(){
			 location.href = "${ctx }/car/toList";
	   });
   });
</script>
</body>
</html>
