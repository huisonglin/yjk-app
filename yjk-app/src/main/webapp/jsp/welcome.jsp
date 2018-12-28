<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>最快租机械后台管理系统</title>
</head>
<body>
<section class="rt_wrap content mCustomScrollbar">
  <div style=" margin:90px auto; width:80%;">
    <div style="height: 100%; overflow: hidden;">
      <div class="dashboard-stats">
        <div class="dashboard-stats-item" style="background-color: #578ebe;">
          <h2 class="m-top-none">${dshCarCount }<span>辆</span></h2>
          <h5>待审核车辆</h5>
        </div>
      </div>
      <div class="dashboard-stats">
        <div class="dashboard-stats-item" style="background-color: #e35b5a;">
          <h2 class="m-top-none">${dfpSellerInfoCount }<span>条</span></h2>
          <h5>待分配卖车信息</h5>
        </div>
      </div>
      <div class="dashboard-stats">
        <div class="dashboard-stats-item" style="background-color: #44b6ae;">
          <h2 class="m-top-none">${dfpBuyerInfoCount }<span>条</span></h2>
          <h5>待分配买车信息</h5>
        </div>
      </div>
      <div class="dashboard-stats">
        <div class="dashboard-stats-item" style="background-color: #8775a7; margin-right: 10px;">
          <h2 class="m-top-none">${zsCarCount }<span>辆</span></h2>
          <h5>在售车辆总数</h5>
        </div>
      </div>
      <div class="dashboard-stats">
        <div class="dashboard-stats-item" style="background-color: #4f5c65; margin-bottom: 0px;">
          <h2 class="m-top-none">${ycjCarCount }<span>辆</span></h2>
          <h5>已成交车辆总数</h5>
        </div>
      </div>
      <div class="dashboard-stats">
        <div class="dashboard-stats-item" style="background-color: #14aae4; margin-bottom: 0px;">
          <h2 class="m-top-none">${storeCount }<span>家</span></h2>
          <h5>门店数</h5>
        </div>
      </div>
    </div>
  </div>
  
  <style type="text/css">
	.dashboard-stats{float:left;width:33.3333%}
	.dashboard-stats-item{position:relative;overflow:hidden;color:#fff;cursor:pointer;height:125px;margin-right:10px;margin-bottom:10px;padding-top:40px}
	.dashboard-stats-item .m-top-none{margin-top:5px;text-align:center}
	.dashboard-stats-item h2{font-size:28px;font-family:inherit;line-height:1.1;font-weight:500}
	.dashboard-stats-item h2 span{font-size:14px;padding-left:5px}
	.dashboard-stats-item h5{font-size:14px;font-family:inherit;margin-top:1px;line-height:1.1;text-align:center}
   </style>
</section>
</body>
</html>