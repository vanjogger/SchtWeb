<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>${data.name}</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/stylesheets/theme.css" rel="stylesheet" type="text/css" />
  <link href="/resources/stylesheets/premium.css" rel="stylesheet" type="text/css" />

  <script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="theme-blue">
<div class="navbar-fixed-top text-center" style="background-color: #43478e; color:#fff;height:40px; display: block; line-height: 35px;">
  ${data.name}
</div>
<br/>
<%--<div class="panel panel-default">--%>
  <%--<div id="myCarousel" class="carousel slide">--%>
    <%--<!-- 轮播（Carousel）指标 -->--%>
    <%--<ol class="carousel-indicators">--%>
      <%--<li data-target="#myCarousel" data-slide-to="0" class="active"></li>--%>
      <%--<li data-target="#myCarousel" data-slide-to="1"></li>--%>
      <%--<li data-target="#myCarousel" data-slide-to="2"></li>--%>
    <%--</ol>--%>
    <%--<!-- 轮播（Carousel）项目 -->--%>
    <%--<div class="carousel-inner">--%>
      <%--<div class="item active">--%>
        <%--<img src="2.jpg" alt="First slide">--%>
      <%--</div>--%>
      <%--<div class="item">--%>
        <%--<img src="2.jpg" alt="Second slide">--%>
      <%--</div>--%>
      <%--<div class="item">--%>
        <%--<img src="2.jpg" alt="Third slide">--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
<%--</div>--%>

<div class="container-fluid" style="margin-bottom: 50px;">
  <div class="page-header">
    <div class="row ">
      <div class="col-xs-2">
        <img src="${empty data.icon?'/resources/images/default_goods.gif':data.icon}" class="img-label img-responsive "/>
      </div>
      <div class="col-xs-8">
        <h5 style="margin-top:0;">${data.name}</h5>
        <span class="help-block">
          联系方式：${data.linkName} &nbsp;&nbsp;&nbsp;${data.linkMobile}
          <br/>
          联系地址：${data.provinceName}${data.cityName}${data.districtName}${data.linkAddress}
        </span>
      </div>
    </div>
  </div>

  <div class="page-content" style="padding:0;">
    <h3 class="bg-info" style="padding:15px;">店铺介绍</h3>
    <p>
      ${data.remark}
    </p>
  </div>
  <div class="panel-footer navbar-fixed-bottom text-center" style="background-color: #31b0d5; color:#fff; line-height: 35px;">
   <a href="javascript:alert('应用宝下载地址');" style="color:#fff;"> 详细信息</a>
  </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>