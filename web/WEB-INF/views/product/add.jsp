<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <form id="J_Form" class="form-horizontal" method="post" action="/product/save">
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
            <c:if test="${e.type == '1'}">
            <option value="${e.id}">${e.name}</option>
            </c:if>
          </c:forEach>
        </select>
      </div>
    </div>
  </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">是否自营：</label>
        <div class="controls">
          <label class="radio" for="self1">
            <input ID="self1" type="radio" onclick="shShop(this)" name="self" value="0" ${type=='NORMAL'?'checked':''}>自营商品</label>&nbsp;&nbsp;&nbsp;
          <label class="radio" for="self2">
            <input id="self2" type="radio" onclick="shShop(this)" name="self" value="1"
            ${type!='NORMAL'?'checked':''}
             ${type=='NORMAL'?'DISABLED':''}>商家销售</label>
        </div>
      </div>
    </div>
    <div class="row" id="shops_div" style="display: ${type=='NORMAL'?'none':''};">
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
    <div class="control-group">
      <label class="control-label">所在区域：</label>
      <div class="controls  control-row-auto">
        <select id="t_province" name="t_province" onchange="loadArea(2)">

        </select>
        <select id="t_city"  name="t_city" onchange="loadArea(3)">

        </select>
        <select id="t_district" name="t_district">

        </select>
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
        <label class="control-label">套餐人数：</label>
        <div class="controls">
          <select name="tc">
            <option value="">--套餐人数--</option>
            <c:forEach begin="1" end="10" var="e">
              <option value="${e}">${e}人餐</option>
            </c:forEach>
          </select>
          只对美食类商品有效。
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
        <input type="hidden" name="provinceId" id="provinceId" />
        <input type="hidden" name="provinceName" id="provinceName"/>
        <input type="hidden" name="cityId" id="cityId" />
        <input type="hidden" name="cityName" id="cityName"/>
        <input type="hidden" name="districtId" id="districtId" />
        <input type="hidden" name="districtName"  id="districtName"/>
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
      data:{name:val, wb:'0'}, dataType:"json",
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
          $("#provinceId").val($("#t_province").val());
          $("#provinceName").val($("#t_province").find("option:selected").text());
          $("#cityId").val($("#t_city").val());
          $("#cityName").val($("#t_city").find("option:selected").text());
          $("#districtId").val($("#t_district").val());
          $("#districtName").val($("#t_district").find("option:selected").text());
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
        max: [100, '文件的最大个数不能超过{0}个'],
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

  var defaultDistrict = "371602";
  $(function(){

    if('${sessionScope.ADMIN.provinceId}' != ''){
      loadArea(1,'${sessionScope.ADMIN.provinceId}');
      if('${sessionScope.ADMIN.cityId}' != '') {
        loadArea(2,'${sessionScope.ADMIN.cityId}');
      }
      if('${sessionScope.ADMIN.districtId}' != ''){
        loadArea(3,'${sessionScope.ADMIN.districtId}');
      }

    }else {
      if (defaultDistrict) {
        loadArea(1, defaultDistrict.substring(0, 2) + "0000");
        loadArea(2, defaultDistrict.substring(0, 4) + "00");
        loadArea(3, defaultDistrict);
      } else {
        loadArea(1);
      }
    }

  })

  function loadArea(i,_r){
    var parentId = "";
    if(i==2){
      parentId = $("#t_province").val();
    }else if(i==3){
      parentId = $("#t_city").val();
    }
    if(!parentId && _r){
      if(i == 2) parentId = _r.substring(0,2) + '0000';
      else if(i == 3) parentId = _r.substring(0,4) + '00';
    }
    $.ajax({
      url:"/common/listNationByParentId?r="+Math.random(),
      type:"post",async:false,
      data:{
        lx : i,
        id:parentId
      },
      success:function(res){
        if(res.success){
          var html = "<option value=''>-- 请选择 --</option>";
          $.each(res.data,function(j,n){
            if(_r && n.id==_r) {
              html += "<option value='"+ n.id+"' selected>"+ n.mc+"</option>";
            }else
              html += "<option value='"+ n.id+"'>"+ n.mc+"</option>";
          })
          if(i==1){
            $("#t_province").html(html);
            if('${sessionScope.ADMIN.type}' == '1'){
              $("#t_province").attr("disabled","disabled");
            }
          }else if(i==2){
            $("#t_city").html(html);
            $("#t_district").html("<option value=''>-- 请选择 --</option>");
            if('${sessionScope.ADMIN.type}' == '1'){
              $("#t_city").attr("disabled","disabled");
            }
          }else if(i==3){
            $("#t_district").html(html);
            if('${sessionScope.ADMIN.type}' == '1'){
              $("#t_district").attr("disabled","disabled");
            }
          }
        }
      },
      error:function(){}
    })
  }
</script>

<body>
</html> 