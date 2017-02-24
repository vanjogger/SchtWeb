<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>${data.title}</title>
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
  ${data.title}
</div>
<br/>
<c:if test="${!empty data.images}">
  <c:set var="images" value="${fn:split(data.images,'|')}"/>
  <div class="panel panel-default">
    <div id="myCarousel" class="carousel slide">
      <!-- 轮播（Carousel）指标 -->
      <ol class="carousel-indicators">
        <c:forEach items="${images}" var="e" varStatus="i">
          <li data-target="#myCarousel" data-slide-to="${i.index}" class="${i.first?'active':''}"></li>
        </c:forEach>

      </ol>
      <!-- 轮播（Carousel）项目 -->
      <div class="carousel-inner">
        <c:forEach items="${images}" var="e" varStatus="i">
          <div class="item ${i.first?'active':''}">
            <img src="${e}" alt="${data.title}">
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</c:if>
<div class="container-fluid" style="margin-bottom: 50px; ${!empty data.images?'margin-top:-40px;':''}">
  <div class="page-header">
    <div class="row ">
      <div class="col-xs-8">
        <h5 style="margin-top:0;">
          价格：${data.price}元 <small><del>${data.marketPrice}元</del></small>
        </h5>
        <span class="help-block">
          销量：${data.saleCount + data.virtualCount}<br/>
          库存：${data.stock}<br/>
          评论：${data.commentCount}
        </span>
      </div>
    </div>
  </div>

  <div class="page-content" style="padding:0;">
    <h3 class="bg-info" style="padding:15px;">商品详情</h3>
    <p>
      ${data.content}
    </p>
  </div>
  <div class="panel-footer navbar-fixed-bottom text-center" style="background-color: #31b0d5; color:#fff; line-height: 35px;">
    <a href="javascript:alert('应用宝下载地址');" style="color:#fff;"> 详细信息</a>
  </div>
</div>
<script type="text/javascript">
  $('.carousel').carousel({
    interval: 2000
  })
</script>
</body>
</html>