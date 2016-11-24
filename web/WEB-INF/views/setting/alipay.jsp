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
  <script  type="text/javascript" src="/resources/js/editor/kindeditor.js"></script>
  <script   type="text/javascript" src="/resources/js/editor/zh_CN.js"></script>
</head>
<body>

<div class="container">
  <div class="row">
    <div class="span8">
      <div id="tab"></div>
      <div id="panel" class="" style="padding:10px;">
        <div>
          <form id="J_Form" class="form-horizontal" action="/paySet/saveAli">
            <div class="row">
              <div class="control-group span20">
                <label class="control-label">支付宝账号：</label>
                <div class="controls">
                  <input name="account" type="text" data-tip="{text:'支付宝账号'}"
                         value="${data.account}" class="input-normal control-text" style="width:200px;">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="control-group span20">
                <label class="control-label">支付密钥：</label>
                <div class="controls">
                  <input name="appKey" type="text" data-tip="{text:'支付密钥'}"
                         value="${data.appKey}" class="input-normal control-text" style="width:200px;">
                </div>
              </div>
            </div>

            <div class="row">
              <div class="control-group span20">
                <label class="control-label">商户号：</label>
                <div class="controls">
                  <input name="mchNo" type="text" data-tip="{text:'支付商户号'}"
                         value="${data.mchNo}" class="input-normal control-text" style="width:200px;">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="control-group span20">
                <label class="control-label">APP支付私钥：</label>
                <div class="controls">
                  <input name="rsaKey" type="text" data-tip="{text:'APP支付RSA私钥'}"
                         value="${data.rsaKey}" class="input-normal control-text" style="width:200px;">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="control-group span20">
                <label class="control-label">状态：</label>
                <div class="controls">
                  <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL"
                  ${(empty data.status || data.status=='NORMAL')?'checked':''}>开启</label>&nbsp;&nbsp;&nbsp;
                  <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN"
                  ${data.status=='FROZEN'?'checked':''}>关闭</label>
                </div>
              </div>
            </div>
            <div class="row form-actions ">
              <div class="span13 offset3 ">
                <button type="submit" class="button button-primary">保存</button>
                <button type="reset" class="button" >重置</button>
              </div>
            </div>
          </form>
        </div>
        <div>微信支付</div>
      </div>
    </div>

  </div>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>


<script type="text/javascript">
  BUI.use(['bui/tab','bui/mask',"bui/form"],function(Tab,Mask,Form){
    var tab = new Tab.TabPanel({
      render : '#tab',
      elCls : 'nav-tabs',
      panelContainer : '#panel',//如果内部有容器，那么会跟标签项一一对应，如果没有会自动生成
      //selectedEvent : 'mouseenter',//默认为click,可以更改事件
      autoRender: true,
      children:[
        {title:'支付宝支付',value:'1',selected : true},
        {title:'微信支付',value:'2',loader:{url:'/paySet/weixin'}}
      ]
    });
    tab.setSelected(tab.getItemAt(0));

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

          }
        },'info');
      }
    });
    form.render();
  });

</script>


<body>
</html> 