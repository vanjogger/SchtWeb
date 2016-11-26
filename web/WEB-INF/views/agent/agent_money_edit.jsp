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
  <form id="J_Form" class="form-horizontal" action="/agentMoney/save">
    <input type="hidden" name="id" value="${dto.id}"/>
    <input type="hidden" name="agentId" value="${dto.agentId}"/>
    <input type="hidden" name="agentName" value="${dto.agentName}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">代理商账号：</label>
        <div class="controls">
          ${dto.agentName}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>可用余额：</label>
        <div class="controls">
          <input name="availAmount" type="text" data-rules="{required:true,maxlength:10}" value="${dto.availAmount}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">冻结金额：</label>
        <div class="controls">
          <input name="frozenAmount" type="text" data-rules="{required:true,maxlength:10}" value="${dto.frozenAmount}" class="input-normal control-text">
       </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">累计金额：</label>
        <div class="controls">
          <input name="totalAmount" type="text" data-rules="{required:true,maxlength:10}" value="${dto.totalAmount}" class="input-normal control-text">
        </div>
      </div>
    </div>

    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">调整</button>
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
              id : 'dlszj_list',
              isClose : true
            });
            top.topManager.reloadPage('dlszj_list');
          }
        },'info');
      }
    });

    form.render();
  });
</script>

<body>
</html> 