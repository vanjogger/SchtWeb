<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 页面操作快捷方式</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />  
  <style type="text/css">
    code {
      padding: 0px 4px;
      color: #d14;
      background-color: #f7f7f9;
      border: 1px solid #e1e1e8;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="row">
    <div class="span10">
      <div class="tips tips-large tips-success">
        <span class="x-icon x-icon-success">
          <%--<i class="icon icon-ok icon-white"></i>--%>
        </span>
        <div class="tips-content">
          <h2>信息</h2>
          <p class="auxiliary-text">
            ${msg}：
          </p>
          <p>
            <a class="page-action" data-type="setTitle" title="返回" href="javascript:window.history.go(-1);">返回</a>
          </p>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>

<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script type="text/javascript">
  BUI.use('common/page');
</script>

<body>
</html>  