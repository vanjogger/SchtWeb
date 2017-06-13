<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 公告</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="/resources/js/editor/skins/default.css">
  <script language="JavaScript" src="/resources/js/editor/kindeditor.js"></script>
  <script language="JavaScript" src="/resources/js/editor/zh_CN.js"></script>
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal">
    <div class="row">
      <div class="control-group span20">
        <h2>订单信息</h2>
        <div class="row">
        <div class="span10">订单信息：${data.no}</div>
        <div class="span10">下单时间：${data.createDate}</div>
        </div>
        <div class="row">
          <div class="span10">订单金额：<fmt:formatNumber value="${data.totalMoney}" pattern="#0.00"/> </div>
          <div class="span10">订单状态：
            <c:choose>
              <c:when test="${data.status == 'CREATE'}">待支付</c:when>
              <c:when test="${data.status == 'PAY'}">${data.orderType == 'EXTEND'?'待消费':'已支付'}</c:when>
              <c:when test="${data.status == 'RECEIVE'}">商家已接单</c:when>
              <c:when test="${data.status == 'DISPATCH'}">已发货</c:when>
              <c:when test="${data.status == 'SUCCESS'}">已完成</c:when>
              <c:when test="${data.status == 'CLOSE'}">已关闭</c:when>
            </c:choose>
          </div>
        </div>
        <div class="row">
          <div class="span10">订单备注：${data.remark}</div>
        </div>
        <div class="row">
          <div class="span10">买家账号：${data.memberAccount}</div>
        </div>
      </div>
    </div>

    <c:if test="${data.express == '1'}">
      <div class="row">
        <div class="control-group span20">
          <h2>物流信息</h2>
          <div class="row">
            <div class="span10">收件人：${data.userName}</div>
            <div class="span10">联系电话：${data.telephone}</div>
          </div>
          <c:if test="${!empty data.expressName}">
            <div class="row">
              <div class="span10">快递公司：${data.expressName}</div>
              <div class="span10">快递单号：${data.expressNo}</div>
            </div>
          </c:if>
          <c:if test="${!empty data.wbTelephone}">
            <div class="row">
              <div class="span10">快递电话：${data.wbTelephone}</div>
              <div class="span10">快递员：${data.wbName}</div>
            </div>
          </c:if>
          <div class="row">
            <div class="span10">联系地址：${data.address} </div>
          </div>
        </div>
      </div>
    </c:if>

    <div class="row">
      <div class="control-group span20">
        <h2>商品信息</h2>
        <div class="row">
          <div id="grid">
            <div class="bui-simple-grid bui-simple-list bui-grid-border" aria-disabled="false" aria-pressed="false">
              <table class="bui-grid-table" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                  <th class="bui-grid-hd " width="">
                    <div class="bui-grid-hd-inner"><span class="bui-grid-hd-title">图标</span></div>
                  </th>
                  <th class="bui-grid-hd center" width="">
                    <div class="bui-grid-hd-inner"><span class="bui-grid-hd-title">商品名称</span></div>
                  </th>
                  <th class="bui-grid-hd center" width="">
                    <div class="bui-grid-hd-inner"><span class="bui-grid-hd-title">价格 * 数量</span></div>
                  </th>
                  <th class="bui-grid-hd center" width="">
                    <div class="bui-grid-hd-inner"><span class="bui-grid-hd-title">总价格</span></div>
                  </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${data.list}" var="product">
                  <tr class="bui-grid-row bui-grid-row-odd">
                    <td class="bui-grid-cell ">
                      <div class="bui-grid-cell-inner"><span class="bui-grid-cell-text"><img
                              src="${product.productImage}" width="80"></span></div>
                    </td>
                    <td class="bui-grid-cell center">
                      <div class="bui-grid-cell-inner"><span class="bui-grid-cell-text">
                        ${product.productName}
                      </span></div>
                    </td>
                    <td class="bui-grid-cell center">
                      <div class="bui-grid-cell-inner"><span class="bui-grid-cell-text">
                        <fmt:formatNumber value="${product.price}" pattern="#0.00"/> * ${product.amount}
                      </span></div>
                    </td>
                    <td class="bui-grid-cell center">
                      <div class="bui-grid-cell-inner"><span class="bui-grid-cell-text">
                         <fmt:formatNumber value="${product.money}" pattern="#0.00"/>
                      </span></div>
                    </td>
                  </tr>
                </c:forEach>



                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <p>
        <button type="button" class="button" onclick="back()">返回</button>
        </p>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script type="text/javascript">
  function back(){
    var backId = 'order_list';
    if('${data.orderType}' == 'DISCOUNT'){
      backId = 'wzorder_list';
    }else if('${data.orderType}' == 'EXTEND'){
      backId = 'tgorder_list';
    }else if('${data.wb}' == 'true') {
      backId = 'wb_order_list'
    }
    top.topManager.openPage({
      id : backId,
      isClose : true
    });
    top.topManager.reloadPage(backId);
  }
</script>
 </body>
</html> 