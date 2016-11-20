<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 宏图爱管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/permission/save">
    <input type="hidden" name="id" value="${permission.id}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>权限名称：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:20}" value="${permission.name}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>上级菜单：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
          <select id="pId" name="pId">
            <option value="0">--请选择--</option>
            <c:forEach items="${list}" var="e">
                <option value="${e.id}" <c:if test="${e.id  eq permission.pId}">selected</c:if>>${e.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
    <div class="row" style="margin-top: 10px;">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>权限码：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
          <input name="url" type="text" data-rules="{required:true,maxlength:20}" value="${permission.url}" class="input-normal control-text">
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
  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form'],function (Tree,Form) {


    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
         /* var checkedNodes = tree.getCheckedNodes();
          var str = '';
          BUI.each(checkedNodes,function(node){
            str += node.id + ',';
          });
          $("#permissions").val(str);*/
          return true;
        }
      },
      callback:function(data){
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.openPage({
              id : 'permission_list',
              isClose : true
            });
            top.topManager.reloadPage('permission_list');
          }
        },'info');
      }
    });

    form.render();
  });

  /*BUI.use('bui/form',function (Form) {
    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          var checkedNodes = tree.getCheckedNodes();
          var str = '';
          BUI.each(checkedNodes,function(node){
            str += node.id + ',';
          });
          $("#permissions").val(str);
          return true;
        }
      },
      callback:function(data){
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.openPage({
              id : 'role_list',
              isClose : true
            });
            top.topManager.reloadPage('admin_list');
          }
        },'info');
      }
    });

    form.render();
  });*/

  function back(){
    top.topManager.openPage({
      id : 'permission_list',
      isClose : true
    });
    top.topManager.reloadPage('permission_list');
  }
</script>

<body>
</html> 