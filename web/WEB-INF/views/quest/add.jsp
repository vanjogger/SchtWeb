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
  <form id="J_Form" class="form-horizontal" method="post" enctype="multipart/form-data" action="/question/save" >
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true}"
                 value="" class="input-large control-text" >
        </div>
      </div>
    </div>
    <%--<div class="row" id="shops_div"  >--%>
      <%--<div class="control-group span20">--%>
        <%--<label class="control-label">关联商家：</label>--%>
        <%--<div class="controls control-row4" style="height:150px;">--%>
          <%--<input type="hidden" name="shopId" id="shopId"/>--%>
          <%--<input type="text" class="input-normal control-text" id="shopName">--%>
          <%--<input type="button" class="button" onclick="searchShop()" value="检索"/>--%>
          <%--<br/>--%>
          <%--<select id="shops" class="input-large" onchange="selShop(this)" size="6" style="height:115px;">--%>
          <%--</select>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>商家名称：</label>
        <div class="controls">
          <input type="text" name="shopName" class="control-text"
                />
          <span class="tip-text">商家名称或出资人姓名。</span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>联系电话：</label>
        <div class="controls">
          <input type="text" name="telephone" class="control-text"
                  />
          <span class="tip-text">商家或出资人联系电话。</span>
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
        <label class="control-label"><s>*</s>问题奖励：</label>
        <div class="controls">
          <input type="text" name="money" class="control-text input-small" id="money" value="0"
                 data-rules="{required:false,regexp:/^\d+(.\d{1,2})?$/}"
                 data-messages="{regexp:'请填写数字，允许保留两位小数'}"/>
          <span class="tip-text">回答正确奖励红包金额</span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>优惠券奖励：</label>
        <div class="controls">
         <select name="couponId" id="couponId">
          <option value="">--选择优惠券奖励---</option>
           <c:forEach items="${coupons}" var="e">
             <option value="${e.id}">${e.name}</option>
           </c:forEach>
         </select>
          <span class="tip-text">回答正确奖励优惠券，设置了优惠券后金额奖励失效。</span>
        </div>
      </div>
    </div>
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>问题总数：</label>
          <div class="controls">
            <input type="text" name="sumCount" class="control-text input-small"

                   data-rules="{required:true,regexp:/^\d+$/}"
                   data-messages="{regexp:'请填写整数'}"/>
            <span class="tip-text">一共发布多少道该问题</span>
          </div>
        </div>
      </div>
    <div class="row">
    <div class="control-group span20">
      <label class="control-label"><s>*</s>状态：</label>
      <div class="controls">
        <input type="radio" name="status" value="NORMAL" CHECKED/>上架
        <input type="radio" name="status" value="FROZEN"/>下架
      </div>
    </div>
  </div>
    <div class="row">
      <label class="control-label"><s>*</s>问题图片：</label>
      <div class="span8">
        <div id="J_Uploader">
        </div>
      </div>
    </div>
    <div class="row">
      <label class="control-label"><s>*</s>分享图片：</label>
      <div class="span8">
        <div id="J_Uploader1">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题解析：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
            <textarea name="content" id="content" cols=""
                      rows="" class="textinput" style="width:700px;height:350px;visibility:hidden;"
                      maxlength="1000"></textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span24">
        <label class="control-label">问题答案：</label>
        <div class="panel-body">
          <table class=" table bui-grid-table">
            <tr>
              <th class="center">选项</th>
              <th class="center" style="width:310px;">图片</th>
              <th class="center" style="width:310px;">答案</th>
              <th class="center">是否正确</th>
              <th></th>
            </tr>
            <tbody id="answer">
            <tr>
             <td><input type="text" class="control-text input-small"  data-rules="{required:true}" name="a_sort"/> </td>
              <td id="icon0">
                <input id="file" type="file" multiple="multiple" accept="image/*" style="display:none;"/>
                <img src="/resources/images/default_goods.gif" style="width:80px" onclick="a_icon_id='icon0';return $('#file').click()"/>
                <input type="hidden" name="a_icon"/>
              </td>
              <td><input type="text" class="control-text input-large" data-rules="{required:true}" name="a_content"/> </td>
              <td><input type="checkbox" name="a_answer"/> </td>
              <td><a class="button" onclick="addTr(this)">+</a> </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <input type="hidden" name="jsonStr" id="jsonStr"/>
        <input type="hidden" name="icon" id="icon"/>
        <input type="hidden" name="shareImg" id="shareImg"/>
        <input type="hidden" name="provinceId" id="provinceId" />
        <input type="hidden" name="provinceName" id="provinceName"/>
        <input type="hidden" name="cityId" id="cityId" />
        <input type="hidden" name="cityName" id="cityName"/>
        <input type="hidden" name="districtId" id="districtId" />
        <input type="hidden" name="districtName"  id="districtName"/>
        <button type="submit" class="button button-primary" >保存</button>
        <button type="button" class="button" onclick="back()">返回</button>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script type="text/javascript" src="/resources/js/LocalResizeIMG.js"></script>

<script type="text/javascript">



var icon_i=1;
  function addTr(_node){
    var t_id = "icon" + icon_i;
    var html='<tr><td><input type="text" class="control-text input-small" data-rules="{required:true}" name="a_sort"/> </td>'
                    +'<td id="'+t_id+'">'
           +'<img src="/resources/images/default_goods.gif" style="width:80px" ' +
            'onclick="a_icon_id=\''+t_id+'\';return $(\'#file\').click()"/>'+
            '<input type="hidden" name="a_icon"/>  </td>'
            +'<td><input type="text" class="control-text input-large" data-rules="{required:true}" name="a_content"/> </td>'
            +'<td><input type="checkbox" name="a_answer"/> </td>'
           +' <td><a class="button" onclick="removeTr(this)">-</a> </td></tr>';
    icon_i++;
    $("#answer").append(html);
  }
  function removeTr(_n){
    $(_n).closest("tr").remove();
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
          $("#icon").val($("#J_Uploader img").attr("src"));
          $("#shareImg").val($("#J_Uploader1 img").attr("src"));
          $("#content").val(editor.html());
          $("#provinceId").val($("#t_province").val());
          $("#provinceName").val($("#t_province").find("option:selected").text());
          $("#cityId").val($("#t_city").val());
          $("#cityName").val($("#t_city").find("option:selected").text());
          $("#districtId").val($("#t_district").val());
          $("#districtName").val($("#t_district").find("option:selected").text());
          if($("#money").val() == '' &&$("#couponId").val() == '') {
            BUI.Message.Alert("请设置奖励金额或奖励优惠券",function(){
              return false;
            },'info');
            return false;
          }
           var answers = $("#answer tr");
          var html = [], suc = false;
          for(var i=0; i < answers.length; i++){
            var sort = $(answers[i]).find("input[name='a_sort']").val();
            var content = $(answers[i]).find("input[name='a_content']").val();
            var a ={
              "sort" : sort,
              "content" : content,
              "icon" : $(answers[i]).find("input[name='a_icon']").val()
            };
            if($(answers[i]).find("input[name='a_answer']").is(":checked")) {
              suc = true;
              a["answer"] = "true";
            }else{
              a["answer"] = "false";
            }
            html.push(a);
          }
          if(!suc) {
            BUI.Message.Alert("请设置正确答案",function(){
              return false;
            },'info');
            return false;
          }
          $("#jsonStr").val(JSON.stringify(html));
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
        resultTpl:{
          'success': '<div class="success"><img src="{url}" title="{name}" width="100%;padding:5px;"/></div>',
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
    var uploader1 = new Uploader.Uploader({
      //指定使用主题
      render: '#J_Uploader1',
      url: '/upload/buiUpload',
      queue: {
        resultTpl:{
          'success': '<div class="success"><img src="{url}" title="{name}" width="100%;padding:5px;"/></div>',
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
     var backId = "quest_list";
    top.topManager.openPage({
      id : backId,
      isClose : true
    });
    top.topManager.reloadPage(backId);
  }


//  function uploadLogo(_node){
//    var fileUploadId = $(_node).attr("id");
//    $.ajaxFileUpload(
//            {
//              url: "/upload/uploadImage?_t=" + new Date().getTime(),            //需要链接到服务器地址
//              secureuri: false,//是否启用安全提交，默认为false
//              fileElementId: fileUploadId,                        //文件选择框的id属性
////              dataType: 'json',                                     //服务器返回的格式，可以是json, xml
//              type:'post',
//              success: function (data, status)  //服务器成功响应处理函数
//              {
//                alert(JSON.stringify(data));
//              },
//              error: function (data, status, e) {
//                alert("上传失败");
//              }
//            }
//    );
//  }

  var a_icon_id;
  $(document).ready(function(){
    $('input:file').localResizeIMG({
      width: 300,
      height:300,
      quality: 1,
      success: function (result) {
        $.ajax({
          url: '/upload/uploadImg',
          type: 'post',
          data: {image: result.clearBase64},
          dataType: 'json',
          success: function (data) {
            if(data.success){
                $("#" + a_icon_id).find("img").attr('src', data.data);
              $("#" + a_icon_id).find("input[name='a_icon']").val(data.data);
            } else {
              alert("上传图片出错");
            }
          }
        });
      }
    });
  });


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