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
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/pushSet/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">AppKey：</label>
        <div class="controls">
          <input name="appKey" type="text" data-tip="{text:'AppKey'}"
                 value="${data.appKey}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">MasterSecret：</label>
        <div class="controls">
          <input name="masterSecret" type="text" data-tip="{text:'Master Secret'}"
                 value="${data.masterSecret}" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">状态：</label>
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