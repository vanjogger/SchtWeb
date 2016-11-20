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
  <form id="J_Form" class="form-horizontal" action="/admin/updateSystemPwd">
    <%--<div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>总公司账号：</label>
        <div class="controls">
          <input name="user_name" type="text" data-rules="{required:true,maxlength:20}" value="${user_name}" class="input-normal control-text">
        </div>
      </div>
    </div>--%>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>一级密码：</label>
        <div class="controls">
          <input name="loginPwd" type="password" data-rules="{required:true,maxlength:50}" value="${login_pwd}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>二级密码：</label>
        <div class="controls">
          <input name="payPwd" type="password" data-rules="{required:true,maxlength:50}" value="${pay_pwd}" class="input-normal control-text">
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
              id : 'system_config',
              isClose : true
            });
            top.topManager.reloadPage('system_config');
          }
        },'info');
      }
    });

    form.render();
  });
</script>

</body>
</html> 