<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 优惠券</title>
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
  <form id="J_Form" class="form-horizontal" action="/coupon/save">
    <input type="hidden" name="id" value="${dto.id}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>优惠券名称：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:100}"
                 value="${dto.name}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">优惠券金额：</label>
        <div class="controls">
          <input type="text" name="couponMoney" value="${dto.couponMoney}"
                 data-rules="{regexp:/^\d+(.\d{1,2})?$/}"
                 data-messages="{regexp:'请输入金额，允许保留两位小数'}"
                 class="input-small control-text"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">优惠券总数量：</label>
        <div class="controls">
          <input type="text" name="count" value="${dto.count}" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}"
                 class="input-small control-text"/>
        </div>
      </div>
    </div>
    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">保存</button>
        <button type="button" class="button" onclick="back()">返回</button>
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
            back();
          }
        },'info');
      }
    });

    form.render();
  });

  function back(){
    top.topManager.openPage({
      id : 'coupon_list',
      isClose : true
    });
    top.topManager.reloadPage('coupon_list');
  }
</script>

<body>
</html> 