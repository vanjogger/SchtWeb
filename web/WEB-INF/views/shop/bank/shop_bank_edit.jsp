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
  <form id="J_Form" class="form-horizontal" action="/shopBank/save">
    <input type="hidden" name="id" value="${dto.id}"/>
    <input type="hidden" name="shopId" value="${dto.shopId}"/>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商家账号：</label>
        <div class="controls">
          ${dto.shopAccount}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商家名称：</label>
        <div class="controls">
          ${dto.shopName}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">银行名称：</label>
        <div class="controls">
          <input name="yhmc" type="text" data-rules="{maxlength:30}" value="${dto.yhmc}" class="input-normal control-text">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">开户行：</label>
        <div class="controls">
          <input name="khh" type="text" data-rules="{required:true,maxlength:50}" value="${dto.khh}" class="input-normal control-text">
       </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">持卡人名称：</label>
        <div class="controls">
          <input name="ckr" type="text" data-rules="{required:true,maxlength:50}" value="${dto.ckr}" class="input-normal control-text">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">卡号：</label>
        <div class="controls">
          <input name="kh" type="text" data-rules="{required:true,maxlength:50}" value="${dto.kh}" class="input-normal control-text">
        </div>
      </div>
    </div>

    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">确定</button>
        <button type="reset" class="button">重置</button>
      </div>
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