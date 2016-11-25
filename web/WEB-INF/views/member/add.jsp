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
  <form id="J_Form" class="form-horizontal" action="/admin/member/save">
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>会员账号：</label>
        <div class="controls">
          <input name="account" type="text" data-tip="{text:'请输入会员账号'}"
                 data-rules="{required:true,maxlength:100}" value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>登录密码：</label>
        <div class="controls">
          <input name="password" type="password" data-tip="{text:'请输入登录密码'}"
                 data-rules="{required:true,maxlength:32}" value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">昵称：</label>
        <div class="controls">
          <input name="nick" type="text" data-tip="{text:'请输入会员昵称'}"
                   value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <label class="control-label">头像：</label>
      <div class="span8">
        <div id="J_Uploader">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">联系电话：</label>
        <div class="controls">
          <input name="telephone" type="text" data-tip="{text:'请输入联系电话'}"
                  value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">联系地址：</label>
        <div class="controls">
          <input name="address" type="text" data-tip="{text:'请输入家庭住址'}"
                  value="" class="input-large control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
    <div class="control-group span20">
      <label class="control-label">QQ：</label>
      <div class="controls">
        <input name="qq" type="text" data-tip="{text:'请输入联系QQ'}"
               value="" class="input-normal control-text" style="width:200px;">
      </div>
    </div>
  </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">微信：</label>
        <div class="controls">
          <input name="weixin" type="text" data-tip="{text:'请输入联系微信'}"
                 value="" class="input-normal control-text" style="width:200px;">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">状态：</label>
        <div class="controls">
          <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL" CHECKED>开启</label>&nbsp;&nbsp;&nbsp;
          <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN">冻结</label>
        </div>
      </div>
    </div>
    <input type="hidden" id="headIcon" name="headIcon" />
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
  BUI.use(['bui/tree','bui/form','bui/uploader'],function (Tree,Form,Uploader) {

    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          $("#headIcon").val($("#J_Uploader img").attr("src"));
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
          'success': '<div class="success"><img src="{url}" title="{name}" width="50%;padding:5px;"/></div>',
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
      id : 'member_list',
      isClose : true
    });
    top.topManager.reloadPage('member_list');
  }
</script>

<body>
</html> 