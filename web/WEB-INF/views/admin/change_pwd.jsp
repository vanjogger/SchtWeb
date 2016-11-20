<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>修改密码</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
  <style type="text/css">
    code {
      padding: 0px 4px;
      color: #d14;
      background-color: #f7f7f9;
      border: 1px solid #e1e1e8;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="row">
    <form id="J_Form" class="form-horizontal span24" >
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>管理员：</label>
          <div class="controls">
            <input type="hidden" id="id" name="id" value="${data.id}"/>
            ${data.loginName}
          </div>
        </div>

      </div>
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>请输入新密码：</label>
          <div class="controls">
            <input name="new_pwd" id="new_pwd" type="password" maxlength="20" class="input-normal control-text">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>请输入确认密码：</label>
          <div class="controls">
            <input name="new_pwd1" id="new_pwd1" type="password"  maxlength="20"  class="input-normal control-text">
          </div>
        </div>
      </div>
      <div class="row form-actions actions-bar">
        <div class="span13 offset3 ">
          <button type="button" class="button button-primary" onclick="save()">保存</button>
          <button type="reset" class="button">重置</button>
        </div>
      </div>
    </form>
  </div>


</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script type="text/javascript">
  BUI.use('common/page');
</script>
<script type="text/javascript">
  BUI.use('bui/form',function (Form) {
    var form = new Form.HForm({
      srcNode : '#J_Form'
    });

    form.render();
  });

  function save(){

    var pwd1 = $("#new_pwd").val();
    var pwd2 = $("#new_pwd1").val();
    if(pwd1==""){
      BUI.Message.Alert('请输入新密码！','info');
      return ;
    }
    if(pwd1!=pwd2){
      BUI.Message.Alert('新密码和确认密码要一致！','info');
      return ;
    }
    $.ajax({
      url:"/admin/changePwd?ran="+Math.random(),
      type:"post",
      data:{
        id:$("#id").val(),
        pwd:$("#new_pwd").val()
      },
      success:function(res){
        BUI.Message.Alert(res.msg,function(){
          if(res.success){
            top.topManager.openPage({
              id : 'admin_list',
              isClose : true
            });
            top.topManager.reloadPage('admin_list');
          }
        },'info');
      },
      error:function(){}
    });

  }

</script>

<body>
</html>