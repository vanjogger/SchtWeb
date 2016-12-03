<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>工作台</title>
  <link rel="stylesheet" href="/resources/wk/css/bootstrap.min.css" />
  <link rel="stylesheet" href="/resources/wk/css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="/resources/wk/css/fullcalendar.css" />
  <link rel="stylesheet" href="/resources/wk/css/matrix-style.css" />
  <link rel="stylesheet" href="/resources/wk/css/matrix-media.css" />
  <link href="/resources/wk/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" href="/resources/wk/css/jquery.gritter.css" />
  <script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
  <script src="/resources/js/highchart/highcharts.js" type="text/javascript"></script>
  <script src="/views/js/bar.js" type="text/javascript"></script>
</head>
<body>

<div id="content" style="margin-left: 0px;">

  <!--Action boxes-->
  <div class="container-fluid">
    <div class="quick-actions_homepage">
      <ul class="quick-actions">
        <li class="bg_lb"> <a href="javascript:void(0);" onclick="toMenu('/admin/yla/list?lx=1','会员')"> <i class="icon-dashboard"></i> <span class="label label-important">${ylaCnt}</span> 新增会员数 </a> </li>
        <%--<li class="bg_lg span3"> <a href="charts.html"> <i class="icon-signal"></i> Charts</a> </li>--%>
        <li class="bg_ly"> <a href="javascript:void(0);" onclick="toMenu('/admin/yla/list?lx=2','订单')"> <i class="icon-inbox"></i><span class="label label-important">${zxCnt}</span>  订单总数 </a> </li>
        <li class="bg_lo"> <a href="javascript:void(0);" onclick="toMenu('/admin/yla/list?lx=3','订单')"> <i class="icon-th"></i><span class="label label-important">${ssCnt}</span>  待发货订单数</a> </li>
        <%--  <li class="bg_ls"> <a href="javascript:void(0);" onclick="toMenu('/admin/cl/list','材料递交')"> <i class="icon-fullscreen"></i><span class="label label-important">${clCnt}</span>  材料递交申请</a> </li>
       <li class="bg_lo"> <a href="javascript:void(0);" onclick="toMenu('/admin/message/list','留言申请')"> <i class="icon-tint"></i><span class="label label-important">${messageCnt}</span>  留言申请</a> </li>
    <li class="bg_ls"> <a href="buttons.html"> <i class="icon-tint"></i> Buttons</a> </li>
       <li class="bg_lb"> <a href="interface.html"> <i class="icon-pencil"></i>Elements</a> </li>
       <li class="bg_lg"> <a href="calendar.html"> <i class="icon-calendar"></i> Calendar</a> </li>--%>
        <li style="float:left;">
          今日数据
        </li>
      </ul>
    </div>
    <!--End-Action boxes-->

    <!--Chart-box-->
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>排行榜</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="span6">最近一周订单数趋势图
              <div class="chart" id="chart"></div>
            </div>
            <div class="span6" style="width:150px;float:right;">

              Top10商品



              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    </div></div>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
  <script type="text/javascript">
    BUI.use('common/page'); //页面链接跳转
    $(function(){
      //加载柱状图
      loadBar();

      loadDailyCount();
    });
    function loadBar(){
      $.ajax({
        url:"/admin/initBarData?r="+Math.random(),
        type:"post",
        success: function (res) {
          if(res.success){
            initBar("#chart",res.data);
          }
        },
        error:function(){}
      })
    }
    function loadDailyCount(){
      $.ajax({
        url:"/admin/loadDailyCnt?r="+Math.random(),
        type:"post",
        success:function(res){
          if(res.success){
            $("#day").html(res.data.day);
            $("#week").html(res.data.week);
            $("#long").html(res.data.long);
          }
        },
        error:function(){}
      })
    }
    function toMenu(url,title){
      top.topManager.openPage({id:"workspace_"+title, href:url,title:title});
    }
  </script>

</body>
</html>
