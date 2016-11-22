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
  <form id="J_Form" class="form-horizontal" action="/advert/update">
    <input type="hidden" name="id" value="${data.id}">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>广告名称：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true,maxlength:20}" value="${data.title}" class="input-large control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>广告位置：</label>
        <div class="controls">
          <select name="placeId" data-rules="{required:true}" onchange="sSize(this)">
            <option value="">请选择</option>
            <c:forEach items="${places}" var="e">
              <option value="${e.id}" ${data.placeId==e.id?'selected':''} data-width="${e.width}" data-height="${e.height}">${e.title}</option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">广告位大小：</label>
        <div class="controls">
          <input id="size" type="text" readonly value="" class="input-normal control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <label class="control-label"><s>*</s>广告图片：</label>
      <div class="span8">
        <div id="J_Uploader">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">广告链接：</label>
        <div class="controls">
          <input name="url" type="text" value="${data.url}" class="input-large control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>有效时间段：</label>
        <div class="controls">
          <input id="startTime" type="text" value="${data.startDate}" data-rules="{required:true}" class="calendar">~
          <input id="endTime" type="text" value="${data.endDate}" data-rules="{required:true}" class="calendar">
        </div>
      </div>
    </div>
    　<div class="row">
    <div class="control-group span20">
      <label class="control-label">状态：</label>
      <div class="controls">
        <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL" ${data.status=='NORMAL'?'checked':''}>开启</label>&nbsp;&nbsp;&nbsp;
        <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN" ${data.status=='FROZEN'?'checked':''}>关闭</label>
      </div>
    </div>
  </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">备注：</label>
        <div class="controls control-row4">
          <textarea  name="remark" class="input-large">${data.remark}</textarea>
        </div>
      </div>
    </div>
    　<div class="row">
    <div class="control-group span20">
      <label class="control-label">排序：</label>
      <div class="controls">
        <input name="sort" class="input-small control-text" value="${data.sort}" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}">
      </div>
    </div>
  </div>
    <input type="hidden" name="image" id="image" value="${data.image}"/>
    <input type="hidden" name="startTime" id="sTime" value="${data.startTime}"/>
    <input type="hidden" name="endTime" id="eTime" value="${data.endTime}"/>
    <div class="row form-actions actions-bar">
      <div class="span13 offset3 ">
        <button type="submit" class="button button-primary">保存</button>
        <button type="reset" class="button">重置</button>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>

<script type="text/javascript">
  function sSize(_n){
    if($(_n).val() != '') {
      $("#size").val($(_n).find("option:selected").attr("data-width") + " X " + $(_n).find("option:selected").attr("data-height"));
    }else{
      $("#size").val('');
    }
  }
  sSize($("select[name='placeId']"));

  BUI.use('common/page'); //页面链接跳转

  BUI.use(['bui/form','bui/uploader'],function (Form,Uploader) {
    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          if($("#J_Uploader img").length == 0) {
            BUI.Message.Alert("请上传广告图片",function(){},'info');
            return false;
          }
          $("#image").val($("#J_Uploader img").attr("src"));
          $("#sTime").val(BUI.Date.parse($("#startTime").val(),'yyyy-MM-dd').getTime());
          $("#eTime").val(BUI.Date.parse($("#endTime").val(),'yyyy-MM-dd').getTime());
          return true;
        }
      },
      callback:function(data){
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.openPage({
              id : 'advert_list',
              isClose : true
            });
            top.topManager.reloadPage('advert_list');
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
        //文件大小的最小值,这个单位是kb
        minSize: [10, '文件的大小不能小于{0}KB'],
        //文件大小的最大值,单位也是kb
        maxSize: [2048, '文件大小不能大于2M']
      }
    }).render();
    //图片
    $("#J_Uploader ul").append('<li class="bui-queue-item bui-queue-item-success">' +
            '<div class="success"><img src="${data.image}" ' +
            'width="100%;padding:5px;"></div> ' +
            '<span class="action"><span class="bui-queue-item-del">删除</span></span></li>');

  });


</script>

<body>
</html> 