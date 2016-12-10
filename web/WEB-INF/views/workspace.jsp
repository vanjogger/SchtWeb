<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>工作台</title>
  <link rel="stylesheet" href="/resources/css/dpl-min.css" />
  <link rel="stylesheet" href="/resources/css/bui-min.css" />
  <script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
  <script src="/resources/js/highcharts.js" type="text/javascript"></script>
</head>
<body>
<div class="demo-content" style="margin-left: 10px;margin-top: 10px;">

  <!-- 详情页 ================================================== -->
  <div class="row">
    <div class="span24">
      <form name="" action="" method="post" id="detailForm">
        <h3>会员数据</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>今日新注册会员：</label><span class="detail-text">&nbsp;${newMember}</span>
          </div>
          <div class="span8">
            <label>冻结会员：</label><span class="detail-text">&nbsp;${frozenMember}</span>
          </div>
          <div class="span8">
            <label>会员总数：</label><span class="detail-text">&nbsp;${totalMember}</span>
          </div>
        </div>

        <h3>商家数据</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>待审核商家：</label><span class="detail-text">&nbsp;${frozenShop}</span>
          </div>
          <div class="span8">
            <label>商家总数：</label><span class="detail-text">&nbsp;${totalShop}</span>
          </div>
        </div>

        <h3>订单数据</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>待支付订单：</label><span class="detail-text">&nbsp;${createOrder}</span>
          </div>
          <div class="span8">
            <label>待发货订单：</label><span class="detail-text">&nbsp;${payOrder}</span>
          </div>
          <div class="span8">
            <label>订单总数：</label><span class="detail-text">&nbsp;${totalOrder}</span>
          </div>
        </div>

        <h3>商家提现申请</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>待审核商家提现申请：</label><span class="detail-text">&nbsp;${dshTx}</span>
          </div>
          <div class="span8">
            <label>商家提现申请总数：</label><span class="detail-text">&nbsp;${totalTx}</span>
          </div>
        </div>

      </form>
    </div>
  </div>
</div>
  <script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>


</body>
</html>
