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
  <style>
    .control-label{

    }
  </style>
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/pushSet/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label" style="width:150px;">Android AppKey：</label>
        <div class="controls">
          <input name="androidAppKey" type="text" data-tip="{text:'AndroidAppKey'}"
                 value="${data.androidAppKey}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label" style="width:150px;">Android MasterSecret：</label>
        <div class="controls">
          <input name="androidMasterSecret" type="text" data-tip="{text:'Android Master Secret'}"
                 value="${data.androidMasterSecret}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label" style="width:150px;">IOS AppKey：</label>
        <div class="controls">
          <input name="iosAppKey" type="text" data-tip="{text:'IOS AppKey'}"
                 value="${data.iosAppKey}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"  style="width:150px;">IOS MasterSecret：</label>
        <div class="controls">
          <input name="iosMasterSecret" type="text" data-tip="{text:'IOS Master Secret'}"
                 value="${data.iosMasterSecret}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label" style="width:150px;">状态：</label>
        <div class="controls">
          <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL"
          ${(empty data.status || data.status=='NORMAL')?'checked':''}>开启</label>&nbsp;&nbsp;&nbsp;
          <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN"
                  ${data.status=='FROZEN'?'checked':''}>关闭</label>
        </div>
      </div>
    </div>
    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">保存</button>
        <button type="reset" class="button" >重置</button>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>


<script type="text/javascript">


  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/form'],function (Form) {

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

          }
        },'info');
      }
    });

    form.render();
  });


</script>

<body>
</html> 