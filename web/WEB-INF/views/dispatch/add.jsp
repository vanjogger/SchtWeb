<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <form id="J_Form" class="form-horizontal" action="/dispatchMember/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>快递员姓名：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:100}"
                 value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>联系电话：</label>
        <div class="controls">
          <input name="telephone" type="text" data-rules="{required:true,maxlength:100}"
                 value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>联系地址：</label>
        <div class="controls">
          <input name="address" type="text"
                 value="" class="input-normal control-text" style="width:200px;">
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
      id : 'dispatch_list',
      isClose : true
    });
    top.topManager.reloadPage('dispatch_list');
  }
</script>

<body>
</html> 