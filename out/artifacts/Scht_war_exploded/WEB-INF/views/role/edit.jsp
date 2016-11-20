<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 资源文件结构</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/role/save">
    <input type="hidden" name="id" value="${role.id}"/>
    <input type="hidden" name="status" value="${role.status}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>角色名称：</label>
        <div class="controls">
          <input name="roleName" type="text" data-rules="{required:true,maxlength:20}" value="${role.roleName}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>访问权限：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
          <div id="tree"></div>
          <input type="hidden" id="permissions" name="permissions"/>
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

    var tree = new Tree.TreeList({
      render : '#tree',
      nodes : ${data},
      checkType: 'all', //checkType:勾选模式，提供了4中，all,onlyLeaf,none,custom
      showLine : true //显示连接线
    });
    tree.render();

    tree.on('checkedchange',function(ev){
      var checkedNodes = tree.getCheckedNodes();
      var str = '';
      BUI.each(checkedNodes,function(node){
        str += node.id + ',';
      });
      $('.log').text(str);
    });


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
            top.topManager.reloadPage('role_list');
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
      id : 'role_list',
      isClose : true
    });
    top.topManager.reloadPage('role_list');
  }
</script>

<body>
</html> 