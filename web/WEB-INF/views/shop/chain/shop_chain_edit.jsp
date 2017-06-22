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
  <style type="text/css">
    #allmap{height:400px;width:100%;margin-top:30px;}
    #r-result{width:400px;;margin-top:-425px;}
    .dmdDialog{z-index:999;display:none;}
    .dmdDialog .dmdBg{height:100%;width:100%;position:fixed;left:0;top:0;background:#000;filter:alpha(opacity=30);-moz-opacity:0.3;-khtml-opacity:0.3;opacity: 0.3;z-index:0;}
    .dmdDialog .demend{overflow:hidden;border:1px solid #999;background:#fff;position:relative;z-index:0;position:fixed;left:50%;top:50%;margin-left:-300px;margin-top:-250px;height:500px;width:600px;color:#333;}
    .dmdDialog .demend .dclose{display:inline-block;height:25px;width:25px;float:right;margin:0;background:url("/resources/images/demendclose.png") no-repeat scroll center top;background-size:25px auto;transition:none;border-radius:100%;}
    .dmdDialog .demend .dform{height:400px;position:relative;}
    .dmdDialog .demend .confirmBtn{display:block;width:80px;margin:18px auto 0;}

  </style>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wbkGHzVq5wGEdIkdePDDDU9Y"></script>
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" action="/chainShop/update">
    <input type="hidden" name="id" value="${dto.id}"/>
    <input type="hidden" name="provinceId" id="provinceId" value="${dto.provinceId}"/>
    <input type="hidden" name="provinceName" id="provinceName" value="${dto.provinceName}"/>
    <input type="hidden" name="cityId" id="cityId" value="${dto.cityId}"/>
    <input type="hidden" name="cityName" id="cityName" value="${dto.cityName}"/>
    <input type="hidden" name="districtId" id="districtId" value="${dto.districtId}"/>
    <input type="hidden" name="districtName" id="districtName" value="${dto.districtName}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>链接名称：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:20}" value="${dto.name}" class="input-large"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>链接副标题：</label>
        <div class="controls">
          <input name="subName" type="text"   value="${dto.subName}" class="input-large"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>关联商家账号：</label>
        <div class="controls">
          <input name="account" type="text"  data-tip="{text:'关联商家，可不填'}" value="${dto.account}" class="input-large"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>跳转链接：</label>
        <div class="controls">
          <input name="url" type="text"  data-tip="{text:'跳转链接，可不填'}" value="${dto.url}" class="input-large"/>
        </div>
      </div>
    </div>
    <div class="row">
      <label class="control-label">头像：</label>
      <div class="span8">
        <div id="J_Uploader">
        </div>
      </div>
      <input type="hidden" name="icon" id="icon" value="${dto.icon}"/>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">链接Code：</label>
        <div class="controls">
          <input name="code" type="text"    value="${dto.code}" class="input-large"/>

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

    <div class="control-group">
      <label class="control-label">备注：</label>
      <div class="controls  control-row-auto">
        <textarea  name="remark" type="text" data-rules="{maxlength:500}" value="" class="control-row4 input-large">${dto.remark}</textarea>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">状态：</label>
      <div class="controls  control-row-auto">
        <input type="radio" name="status" value="NORMAL" ${dto.status == 'NORMAL'?'checked':''}/>开启
        <input type="radio" name="status" value="FROZEN" ${dto.status == 'FROZEN'?'checked':''}/>关闭
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">排序：</label>
      <div class="controls  control-row-auto">
        <input type="text" class="control-text input-normal" name="sort" value="${dto.sort}"  data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请填写整数'}"/>
      </div>
    </div>
    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">保存</button>
        <button type="reset" class="button">重置</button>
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

  BUI.use(['bui/form','bui/uploader'],function (Form,Uploader) {
    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          if($("#J_Uploader img").length != 0) {
            $("#icon").val($("#J_Uploader img").attr("src"));
          }
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
            top.topManager.openPage({
              id : 'chain_shop_list',
              isClose : true
            });
            top.topManager.reloadPage('chain_shop_list');
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
        //文件大小的最小值,这个单位是kb
        //文件大小的最大值,单位也是kb
        maxSize: [2048, '文件大小不能大于2M']
      }
    }).render();
//图片
    if('${dto.icon}' != ''){
      $("#J_Uploader ul").append('<li class="bui-queue-item bui-queue-item-success">' +
              '<div class="success"><img src="${dto.icon}" ' +
              'width="50%;padding:5px;"></div> ' +
              '<span class="action"><span class="bui-queue-item-del">删除</span></span></li>');
    }
  });

  $(function(){
    if($("#provinceId").val()){
      loadArea(1,$("#provinceId").val());
      if($("#cityId").val()) {
        loadArea(2,$("#cityId").val());
      }
      if($("#districtId").val()){
        loadArea(3,$("#districtId").val());
      }
    }else{
      loadArea(1);
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


</body>
</html> 