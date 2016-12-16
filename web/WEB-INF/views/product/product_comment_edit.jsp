<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>后台管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="/resources/js/editor/skins/default.css">
  <script language="JavaScript" src="/resources/js/editor/kindeditor.js"></script>
  <script language="JavaScript" src="/resources/js/editor/zh_CN.js"></script>
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/productComment/save">
    <input type="hidden" name="id" value="${dto.id}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商品名称：</label>
        <div class="controls">
            ${dto.productName}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">评论人：</label>
        <div class="controls">
          ${dto.memberName}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">评论时间：</label>
        <div class="controls">
          ${dto.dateStr}
        </div>
      </div>
    </div>


    <div class="row">
      <div class="control-group span20">
        <label class="control-label">评论分数：</label>
        <div class="controls">
          ${dto.grade}
        </div>
      </div>
    </div>


    <div class="row" style="height:100px;">
      <div class="control-group span20">
        <label class="control-label">评论图片：</label>
        <div class="controls">
            <c:forEach items="${list}" var="e">
              <img src="${e}" width="80px" height="80px">;
            </c:forEach>
        </div>
      </div>
    </div>

    <div class="row" style="margin-top: 10px;">
      <div class="control-group span20">
        <label class="control-label">评论内容：</label>
        <div class="controls">
          ${dto.content}
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">状态：</label>
        <div class="controls">
          <c:if test="${dto.status eq '0'}">未回复</c:if>
          <c:if test="${dto.status eq '1'}">已回复</c:if>
        </div>
      </div>
    </div>
    <c:if test="${dto.status == '0'}">
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>回复内容：</label>
          <div class="controls">
            <input type="text" name="replyContent" class="control-text input-large" />
          </div>
        </div>
      </div>
    </c:if>

    <c:if test="${dto.status == '1'}">
      <div class="row">
        <div class="control-group span20">
          <label class="control-label">回复时间：</label>
          <div class="controls">
           ${dto.replayDateStr}
          </div>
        </div>
      </div>

      <div class="row">
        <div class="control-group span20">
          <label class="control-label">回复内容：</label>
          <div class="controls">
              ${dto.replyContent}
          </div>
        </div>
      </div>
    </c:if>


    <div class="row form-actions ">
      <div class="span13 offset3 ">
      <c:if test="${dto.status eq '0'}">
        <button type="submit" class="button button-primary">保存</button>
      </c:if>
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
  BUI.use(['bui/tree','bui/form','bui/uploader'],function (Tree,Form,Uploader) {

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
      id : "product_comment_list",
      isClose : true
    });
    top.topManager.reloadPage("product_comment_list");
  }

</script>

</body>
</html> 