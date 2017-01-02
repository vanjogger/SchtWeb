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
  <form id="J_Form" class="form-horizontal" action="/shopType/save">
    <input type="hidden" name="id" value="${dto.id}"/>
    <input type="hidden" name="status" value="${dto.status}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>分类名称：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:20}" value="${dto.name}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>分类key：</label>
        <div class="controls">
          <input name="key" type="text" data-rules="{required:true,maxlength:20}" value="${dto.key}" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>上级分类：</label>
        <div class="controls">
          <select name="parentId">
              <option value="0"> --请选择-- </option>
            <c:forEach items="${list}" var="e">
              <option value="${e.id}" <c:if test="${parentId eq e.id}">selected</c:if>>${e.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
     <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>分类排序：</label>
        <div class="controls">
          <input name="sort" type="text" data-rules="{required:true,maxlength:20,number:true}" value="${dto.sort}" class="input-normal control-text">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">是否拉取商品套餐：</label>
        <div class="controls">
          <input type="radio" name="isProduct" value="0" <c:if test="${isProduct eq '0'}">checked</c:if>>否  &nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="isProduct" value="1" <c:if test="${isProduct eq '1'}">checked</c:if>>是
        </div>
      </div>
    </div>


    <div class="row">
      <div class="control-group span20">
        <label class="control-label">分类图标：</label>
        <c:if test="${dto!=null&&dto.icon!=''}">
            <img src="${dto.icon}" id="images" style="width: 100px;height:100px"/>
        </c:if>
          <div id="J_Uploader" style="margin-left: 100px;">
          </div>
          <input type="hidden" name="icon" id="icon" value="${dto.icon}"/>
      </div>
    </div>



    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">保存</button>
        <button type="reset" class="button" onclick="back()">返回</button>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>

<script type="text/javascript">
  BUI.use('common/page'); //页面链接跳转

  BUI.use(['bui/form','bui/uploader'],function (Form,Uploader) {
    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
      beforesubmit:function(){
        if($("#J_Uploader img").length != 0) {
          $("#icon").val($("#J_Uploader img").attr("src"));
        }
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

    var uploader = new Uploader.Uploader({
      //指定使用主题
      render: '#J_Uploader',
      url: '/upload/buiUpload',
      queue: {
        resultTpl: {
          'success': '<div class="success"><img src="{url}" title="{name}" width="100px;padding:5px;"/></div>',
          'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
        }
      },
      rules: {
        //上传的最大个数
        max: [1, '文件的最大个数不能超过{0}个'],
        //文件大小的最大值,单位也是kb
        maxSize: [2048, '文件大小不能大于2M']
      }
    }).render();

  });

  function back(){
    top.topManager.openPage({
      id : 'shop_category_list',
      isClose : true
    });
    top.topManager.reloadPage('shop_category_list');
  }
</script>

</body>
</html> 