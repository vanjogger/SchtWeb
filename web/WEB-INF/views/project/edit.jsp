<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>查看</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="">


    <div class="row">
      <div class="control-group span20">
        <label class="control-label">姓名：</label>
        <div class="controls">
          ${data.name}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">联系电话：</label>
        <div class="controls">
          ${data.telephone}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">店铺名称：</label>
        <div class="controls">
          ${data.shopName}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">店铺地址：</label>
        <div class="controls">
          ${data.shopAddress}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">项目名称：</label>
        <div class="controls">
          ${data.projectName}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">简介：</label>
        <div class="controls">
          ${data.remark}
        </div>
      </div>
    </div>


    <div class="row form-actions actions-bar">
      <%--<div class="span13 offset3 ">--%>
        <%--<button type="submit" class="button button-primary">确定</button>--%>
        <%--<button type="reset" class="button">返回</button>--%>
      <%--</div>--%>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>

<script type="text/javascript">
  BUI.use('common/page'); //页面链接跳转

  BUI.use('bui/form',function (Form) {
    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
      beforesubmit:function(){
        return true;
      }
      },
      callback:function(data){
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.openPage({
              id : 'shop_bankcard_list',
              isClose : true
            });
            top.topManager.reloadPage('shop_bankcard_list');
          }
        },'info');
      }
    });

    form.render();
  });
</script>

<body>
</html> 