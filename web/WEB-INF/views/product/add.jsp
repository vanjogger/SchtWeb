<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 公告</title>
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
  <form id="J_Form" class="form-horizontal" action="/product/save">
    <input type="hidden" name="productType" value="${type}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>商品名称：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true,maxlength:100}"
                 value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
    <div class="control-group span20">
      <label class="control-label"><s>*</s>商品分类：</label>
      <div class="controls">
        <select name="typeId">
          <option value="">请选择</option>
          <c:forEach items="${typeList}" var="e">
            <option value="${e.id}">${e.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
  </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">是否自营：</label>
        <div class="controls">
          <label class="radio" for="self1"><input ID="self1" type="radio" onclick="shShop(this)" name="self" value="0" CHECKED>自营商品</label>&nbsp;&nbsp;&nbsp;
          <label class="radio" for="self2"><input id="self2" type="radio" onclick="shShop(this)" name="self" value="1">商家销售</label>
        </div>
      </div>
    </div>
    <div class="row" id="shops_div" style="display: none;">
      <div class="control-group span20">
        <label class="control-label">所属商家：</label>
        <div class="controls control-row4" style="height:150px;">
          <input type="hidden" name="shopId" id="shopId"/>
           <input type="text" class="input-normal control-text" id="shopName">
          <input type="button" class="button" onclick="searchShop()" value="检索"/>
          <br/>
          <select id="shops" class="input-large" onchange="selShop(this)" size="6" style="height:115px;">

          </select>
        </div>

      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>商品售价：</label>
        <div class="controls">
          <input type="text" name="price" class="control-text input-small" data-rules="{required:true,regexp:/^\d+(.\d{1,2})?$/}"
                 data-messages="{regexp:'请填写数字，允许保留两位小数'}"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>市场价格：</label>
        <div class="controls">
          <input type="text" name="marketPrice" class="control-text input-small" data-rules="{required:true,regexp:/^\d+(.\d{1,2})?$/}"
                 data-messages="{regexp:'请填写数字，允许保留两位小数'}"/>
        </div>
      </div>
    </div>
    <c:if test="${type != 'EXTEND'}">
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>商品库存：</label>
          <div class="controls">
            <input type="text" name="stock" class="control-text input-small" data-rules="{required:true,regexp:/^\d+$/}"
                   data-messages="{regexp:'请填写整数'}"/>
          </div>
        </div>
      </div>
    </c:if>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商品虚拟销量：</label>
        <div class="controls">
          <input type="text" name="virtualCount" class="control-text input-small" value="0" data-rules="{regexp:/^\d+$/}"
                 data-messages="{regexp:'请填写整数'}"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商品图片：</label>
          <div id="J_Uploader">

          </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商品简介：</label>
        <div class="controls control-row4">
         <textarea class="control-row4 control-text input-large" name="description"></textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>商品详情：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
            <textarea name="content" id="content" cols=""
                      rows="" class="textinput" style="width:700px;height:350px;visibility:hidden;"
                      maxlength="1000"></textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商品状态：</label>
        <div class="controls">
          <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL" CHECKED>上架</label>&nbsp;&nbsp;&nbsp;
          <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN">下架</label>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">排序：</label>
        <div class="controls">
          <input type="text" name="sort" value="1" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}"
                 class="input-small control-text"/>
        </div>
      </div>
    </div>
    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <input type="hidden" name="images" id="images"/>
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

  function shShop(_n){
    if($(_n).val() == '0'){
      $("#shops_div").hide();
    }else{
      $("#shops_div").show();
    }
  }

  function searchShop(){
    var val = $("#shopName").val();
    $.ajax({
      url:"/shop/ajaxList",
      data:{name:val}, dataType:"json",
      success:function(data){
        if(data.success){
          var list = data.data;
          $("#shops").html('');
          for(var i=0; i < list.length; i++){
            $("#shops").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
          }
        }
      }
    });
  }

  function selShop(_t){
      $("#shopId").val($(_t).val());
    $("#shopName").val($(_t).find("option:selected").text());
  }
  var editor;
  KindEditor.ready(function(K) {
    editor = K.create('textarea[name="content"]', {
      allowFileManager: true
    });
  });

  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form','bui/uploader'],function (Tree,Form,Uploader) {

    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          $("#content").val(editor.html());
          //图片
          var images = $("#J_Uploader img");
          var imgstr = "";
          for(var i = 0; i < images.length; i++) {
            imgstr += $(images[i]).attr("src");
            if(i != images.length - 1){
              imgstr += "|";
            }
          }
          $("#images").val(imgstr);
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
      theme: 'imageView',
      render: '#J_Uploader',
      url: '/upload/buiUpload',
      queue: {
        resultTpl:{
          'success': '<div class="success"><img src="{url}" title="{name}"/></div>',
          'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
        }
      },
      rules: {
        //文的类型
//        ext: ['.png,.jpg','文件类型只能为{0}'],
        //上传的最大个数
        max: [5, '文件的最大个数不能超过{0}个'],
        //文件大小的最大值,单位也是kb
        maxSize: [1024, '文件大小不能大于1M']
      }
    }).render();

  });

  function back(){
    var backId = 'product_list';
    if('${type}' == 'DISCOUNT'){
      backId = 'wz_product_list';
    }else if('${type}' == 'EXTEND'){
      backId = 'tg_product_list';
    }
    top.topManager.openPage({
      id : backId,
      isClose : true
    });
    top.topManager.reloadPage(backId);
  }
</script>

<body>
</html> 