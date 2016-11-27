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
  <form id="J_Form" class="form-horizontal" action="/shopTx/save">
    <input type="hidden" name="id" value="${dto.id}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商家名称：</label>
        <div class="controls">
          ${dto.shopName}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商家账号：</label>
        <div class="controls">
          ${dto.shopAccount}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>可用余额：</label>
        <div class="controls">
          ${availAmount}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">申请提现金额：</label>
        <div class="controls">
           ${dto.amount}
       </div>
      </div>
    </div>
    <div class="row">
    <div class="control-group span20">
      <label class="control-label">提现银行：</label>
      <div class="controls">
        ${dto.bankName}
      </div>
    </div>
  </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">持卡人：</label>
        <div class="controls">
          ${dto.cardName}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">卡号：</label>
        <div class="controls">
          ${dto.cardNo}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">当前状态：</label>
        <div class="controls">
          <c:if test="${dto.status eq '0'}">待审核</c:if>
          <c:if test="${dto.status eq '1'}">审核通过</c:if>
          <c:if test="${dto.status eq '2'}">审核拒绝</c:if>
        </div>
      </div>
    </div>

    <c:if test="${dto.status eq '0'}">
      <div class="row">
        <div class="control-group span20">
          <label class="control-label">请选择操作：</label>
          <div class="controls">
            <select name="status" id="status">
              <option value="1">审核通过</option>
              <option value="2">审核拒绝</option>
            </select>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="control-group span20">
          <label class="control-label">备注：</label>
          <div class="controls">
            <input name="remark" type="text" data-rules="{maxlength:50}"   class="input-large"/>
          </div>
        </div>
      </div>
    </c:if>

    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">确定</button>
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
            back();
          }
        },'info');
      }
    });

    form.render();
  });

  function back(){
    top.topManager.openPage({
      id : 'shop_tx_list',
      isClose : true
    });
    top.topManager.reloadPage('shop_tx_list');
  }
</script>

<body>
</html> 