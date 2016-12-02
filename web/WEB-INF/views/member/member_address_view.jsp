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
  <form id="J_Form" class="form-horizontal" action="/memberAddress/save">
    <input type="hidden" name="id" value="${dto.id}"/>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">会员名称：</label>
        <div class="controls">
          ${dto.memberName}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">收货人：</label>
        <div class="controls">
          ${dto.name}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">收货人电话：</label>
        <div class="controls">
           ${dto.mobile}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">收获地址：</label>
        <div class="controls">
          ${dto.address}
       </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">详细地址：</label>
        <div class="controls">
          ${dto.details}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">是否默认：</label>
        <div class="controls">
          <c:choose>
            <c:when test="${dto.isDefault eq '1'}">是</c:when>
            <c:otherwise>否</c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>

    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
      <%--  <button type="submit" class="button button-primary">确定</button>--%>
        <button type="button" class="button" onclick="back()">返回</button>
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
              id : 'shop_bankcard_list',
              isClose : true
            });
            top.topManager.reloadPage('shop_bankcard_list');
          }
        },'info');
      }
    });

    form.render();
  });

  function back(){
    top.topManager.openPage({
      id : 'member_address_list',
      isClose : true
    });
    top.topManager.reloadPage('member_address_list');
  }
</script>

</body>
</html> 