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
  <form id="J_Form" class="form-horizontal" action="/notice/save">
    <input type="hidden" name="id" value="${data.id}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>公告标题：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true,maxlength:100}" value="${data.title}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>公告内容：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
            <textarea name="content" id="content" cols=""
                      rows="" class="textinput" style="width:700px;height:350px;visibility:hidden;"
                      maxlength="1000">${data.content}</textarea>
        </div>
      </div>
    </div>

    <div class="row form-actions actions-bar">
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
  var editor;
  KindEditor.ready(function(K) {
    editor = K.create('textarea[name="content"]', {
      allowFileManager: true
    });
  });

  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form'],function (Tree,Form) {

    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          $("#content").val(editor.html());
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
      id : 'notice_list',
      isClose : true
    });
    top.topManager.reloadPage('notice_list');
  }
</script>

<body>
</html> 