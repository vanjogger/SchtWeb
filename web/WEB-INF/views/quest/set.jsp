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
  <form id="J_Form" class="form-horizontal" action="/questSet/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">会员每天答题数：</label>
        <div class="controls">
          <input name="dayCount" type="text"
                 data-tip="{text:'请填写会员每天可回答多少问题'}"
                 value="${data.dayCount}" class=" input-large control-text"
                 data-rules="{regexp:/^\d+$/}"
                 data-messages="{regexp:'请填写整数'}">
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