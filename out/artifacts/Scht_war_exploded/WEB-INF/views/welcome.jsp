<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 工作台</title>
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
    <div class="detail-page">
      <h2>待审核工作</h2>
      <div class="detail-section">
        <div class="row detail-row">
          <div class="span8">
            <label>待审核会员数：</label><span class="detail-text" ><a href="/verify/listMember">${data.member_num}</a></span>
          </div>
          <div class="span8">
            <label>待审核会员提现申请数：</label><span class="detail-text"><a href="/tx/apply">${data.tx_num}</a></span>
          </div>

        </div>

      </div>

         </div>
  </div>

    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">

      </div>
    </div>

</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>


<script type="text/javascript">
  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form'],function (Tree,Form) {

  });

</script>

</body>
</html> 