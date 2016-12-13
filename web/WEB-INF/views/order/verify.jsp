<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>  </title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/order/findByCode">

    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>请输入消费码：</label>
        <div class="controls">
          <input name="code" type="text" data-rules="{required:true,maxlength:20}"
                 id="code" class="input-normal control-text">
        </div>
      </div>
    </div>


    <div class="row form-actions">
      <div class="span13 offset3 ">
        <button type="button" onclick="verifyOrder()" class="button button-primary">查询</button>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>


<script type="text/javascript">
  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form'],function (Tree,Form) {

  });

  function verifyOrder(){
    var code = $("#code").val();
    if(code.replace(/\s/g,'') == '') {
      BUI.Message.Alert("请输入消费码",function(){},'info');
      return;
    }
     top.topManager.openPage({
       id:'verify_order',
       href:'/order/findByCode?code=' + code,
       title:'消费码验证'
     });

  }



</script>

<body>
</html> 