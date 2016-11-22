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
  <form id="J_Form" class="form-horizontal" action="/advertPlace/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>位置名称：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true,maxlength:20}" value="" class="input-large control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>调用代码：</label>
        <div class="controls">
          <input name="code" type="text" data-rules="{required:true,maxlength:20}"
                 value="" class="input-large control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>位置宽度：</label>
        <div class="controls">
          <input name="width" type="text" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}"
                 value="" class="input-small  number-text">px
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>位置高度：</label>
        <div class="controls">
          <input name="height" type="text" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}"
                 value="" class="input-small  number-text">px
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">备注：</label>
        <div class="controls control-row4">
        <textarea  name="remark" class="input-large"></textarea>
        </div>
      </div>
    </div>
    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">保存</button>
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
              id : 'advert_place_list',
              isClose : true
            });
            top.topManager.reloadPage('advert_place_list');
          }
        },'info');
      }
    });

    form.render();
  });
</script>

<body>
</html> 