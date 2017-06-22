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
  <form id="J_Form" class="form-horizontal" action="/dispatchMember/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>快递员姓名：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:100}"
                 value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>联系电话：</label>
        <div class="controls">
          <input name="telephone" type="text" data-rules="{required:true,maxlength:100}"
                 value="" class="input-normal control-text" style="width:200px;">
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
        <label class="control-label"><s>*</s>联系地址：</label>
        <div class="controls">
          <input name="address" type="text"
                 value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <input type="hidden" name="provinceId" id="provinceId" />
    <input type="hidden" name="provinceName" id="provinceName"/>
    <input type="hidden" name="cityId" id="cityId" />
    <input type="hidden" name="cityName" id="cityName"/>
    <input type="hidden" name="districtId" id="districtId" />
    <input type="hidden" name="districtName"  id="districtName"/>
    <div class="row form-actions ">
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
  });

  function back(){
    top.topManager.openPage({
      id : 'dispatch_list',
      isClose : true
    });
    top.topManager.reloadPage('dispatch_list');
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