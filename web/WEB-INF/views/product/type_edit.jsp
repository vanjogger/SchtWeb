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
  <form id="J_Form" class="form-horizontal" action="/productType/update">
    <input type="hidden" name="id" value="${data.id}"/>
    <input type="hidden" name="type" value="${data.type}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>分类名称：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:20}" value="${data.name}" class="input-large control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>分类Key：</label>
        <div class="controls">
          <input name="key" type="text" data-rules="{required:true,maxlength:20}" value="${data.key}"
                 data-tip="{text:'不可重复'}"
                 class="input-large control-text">
        </div>
      </div>
    </div>
    <div class="row">
      <label class="control-label">分类图标：</label>
      <div class="span8">
        <div id="J_Uploader">

        </div>
        <input type="hidden" id="icon" name="icon" value="${data.icon}"/>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">状态：</label>
        <div class="controls">
          <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL" ${data.status=='NORMAL'?'checked':''}>正常</label>&nbsp;&nbsp;&nbsp;
          <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN" ${data.status=='FROZEN'?'checked':''}>冻结</label>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>分类排序：</label>
        <div class="controls">
          <input name="sort" type="text" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}"
                 value="${data.sort}" class="input-large control-text">
        </div>
      </div>
    </div>

    <div class="row form-actions">
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
  BUI.use('common/page'); //页面链接跳转

  BUI.use(['bui/form','bui/uploader'],function (Form,Uploader) {
    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          $("#icon").val($('#J_Uploader img').attr("src"));
          return true;
        }
      },
      callback:function(data){
        var pid = 'product_type_list';
        if('${data.type}' == '0')
          pid = 'product_category_list';
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.openPage({
              id : pid,
              isClose : true
            });
            top.topManager.reloadPage(pid);
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

    //图片
    if('${data.icon}' != ''){
      $("#J_Uploader ul").append('<li class="bui-queue-item bui-queue-item-success">' +
              '<div class="success"><img src="${data.icon}" ' +
              'width="50%;padding:5px;"></div> ' +
              '<span class="action"><span class="bui-queue-item-del">删除</span></span></li>');
    }
  });
</script>

<body>
</html> 