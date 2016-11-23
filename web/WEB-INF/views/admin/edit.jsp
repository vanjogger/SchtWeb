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
  <form id="J_Form" class="form-horizontal" action="/admin/save">
    <input type="hidden" name="id" value="${admin.id}"/>
    <input type="hidden" name="status" value="${admin.status}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>账号：</label>
        <div class="controls">
          <input name="loginName" type="text" data-rules="{required:true,maxlength:20}" value="${admin.loginName}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">所属角色：</label>
        <div class="controls">
          <select  class="input-normal" name="roleId" id="roleId">
            <c:forEach items="${list}" var="e">
              <option value="${e.id}" <c:if test="${e.id eq admin.roleId}">selected</c:if>>${e.roleName}</option>
            </c:forEach>
          </select>
        <input type="hidden" name="roleName" id="roleName" value="${admin.roleName}"/>
       </div>
      </div>
    </div>
     <div class="row">
      <div class="control-group span20">
        <label class="control-label">用户类型：</label>
        <div class="controls">
           <input type="radio" name="type" id="type0" value="0" <c:if test="${admin==null or (admin.type eq '0')}">checked</c:if>>商城管理员
          <input type="radio" name="type" id="type1" value="1"  <c:if test="${admin.type eq '1'}">checked</c:if>>代理商
        </div>
      </div>
    </div>
  <%--  <div class="row">
      <div class="control-group span20">
        <label class="control-label">所属公司：</label>
        <div class="controls">
          <select  class="input-normal" name="subCompanyId" id="subCompanyId">
            <c:forEach items="${companys}" var="e">
              <option value="${e.id}"  <c:if test="${e.id eq data.subCompanyId}">selected</c:if>>${e.companyName}</option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>--%>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">用户姓名：</label>
        <div class="controls">
          <input name="realName" type="text" data-rules="{maxlength:20}" value="${admin.realName}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">联系电话：</label>
        <div class="controls">
          <input name="mobile" type="text" data-rules="{maxlength:20}" value="${admin.mobile}" class="input-normal control-text">
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
        $("#roleName").val($("#roleId").find("option:selected").text());
        return true;
      }
      },
      callback:function(data){
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.openPage({
              id : 'admin_list',
              isClose : true
            });
            top.topManager.reloadPage('admin_list');
          }
        },'info');
      }
    });

    form.render();
  });
</script>

<body>
</html> 