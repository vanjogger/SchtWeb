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
  <form id="J_Form" class="form-horizontal" action="/chainShop/saveSub">
    <input type="hidden" name="id" value="${dto.id}"/>
    <input type="hidden" name="shopId" value="${shopId}"/>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>商家名称：</label>
        <div class="controls">
          <input name="name" type="text" data-rules="{required:true,maxlength:20}" value="${dto.name}" class="input-large"/>
        </div>
      </div>
    </div>

    <div class="control-group" style="height:120px;">
      <label class="control-label">商家图标：</label>
      <div class="controls">
        <c:if test="${dto!=null && dto.icon!=''}">
          <img src="${dto.icon}" style="width: 80px;height: 80px;"/>
        </c:if>
        <div id="J_Uploader" style="margin-left: 100px;float:right;">
        </div>
        <input type="hidden" name="icon" id="icon" value="${dto.icon}"/>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">联系人：</label>
        <div class="controls">
          <input name="linkName" type="text" data-rules="{maxlength:50}" value="${dto.linkName}" class="input-large"/>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">联系电话：</label>
        <div class="controls">
          <input name="linkMobile" type="text" data-rules="{maxlength:20}" value="${dto.linkMobile}" class="input-large"/>
        </div>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">地&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
      <div class="controls  control-row-auto">
        <textarea  name="linkAddress" type="text" data-rules="{maxlength:100}" value="${dto.linkAddress}" class="control-row4 input-large"></textarea>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">排&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
        <div class="controls">
          <input name="sort" type="text" data-rules="{maxlength:10,number:true}" value="${dto.sort}" class="input-large"/>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="control-group span20">
        <label class="control-label">商家位置：</label>
        <div class="controls">
          经度：<input type="text" name="lng" id="lng"  class="control-text" style="width:80px;" value="${dto.lng}"/>
          纬度：<input type="text" id="lnt" name="lnt"  class="control-text" style="width:80px;" value="${dto.lnt}"/>
          <input type="button" value="标注位置" id="btnShow"/>
        </div>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">备注：</label>
      <div class="controls  control-row-auto">
        <textarea  name="remark" type="text" data-rules="{maxlength:500}" value="" class="control-row4 input-large">${dto.remark}</textarea>
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

<div class="dmdDialog">
  <div class="dmdBg"></div>
  <div class="demend">
    <a class="dclose" href="javascript:;" onclick="closeDialog();"></a>
    <div class="dform">
      <div id="allmap"></div>
      <div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:250px;" /></div>
      <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
    </div>
    <div class="dsuccess">
      <a class="button button-primary confirmBtn" href="javascript:;" onclick="savePoint();">确定</a>
    </div>
  </div>
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
            top.topManager.openPage({
              id : 'sub${shopId}',
              isClose : true
            });
            top.topManager.reloadPage('sub${shopId}');
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

  $(function(){
    $('#btnShow').on('click',function () {
      $(".dmdDialog").show();
    });
  })
</script>

<script type="text/javascript">
  var xPoint,yPonit;
  var lng = '${dto.lng}';
  var lnt = '${dto.lnt}';
  if(lng==''){
    lng = '117.977485';
  }
  if(lnt==''){
    lnt = '37.388595';
  }
  // 百度地图API功能
  var map = new BMap.Map("allmap");
  map.centerAndZoom(new BMap.Point(lng,lnt), 11);
  map.setCurrentCity("滨州");          // 设置地图显示的城市 此项是必须设置的
  map.enableScrollWheelZoom(true);
  //map.centerAndZoom(new BMap.Point(117.981509,37.386301), 11);
  map.addControl(new BMap.NavigationControl());

  var pt = new BMap.Point(lng, lnt);
  var marker2 = new BMap.Marker(pt);  // 创建标注
  map.addOverlay(marker2);

  //单击获取点击的经纬度
  map.addEventListener("click",function(e){
    xPoint=e.point.lng;
    yPoint=e.point.lat;
    var point = new BMap.Point(e.point.lng, e.point.lat);

    var marker = new BMap.Marker(point);
    map.clearOverlays();
    map.addOverlay(marker);
  });
  function G(id) {
    return document.getElementById(id);
  }

  function closeDialog(){
    $('.dmdDialog').hide();
  }
  function savePoint(){
    $('#lng').val(xPoint);
    $('#lnt').val(yPoint);
    $('.dmdDialog').hide();
  }

  var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
          {"input" : "suggestId"
            ,"location" : map
          });

  ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
    var str = "";
    var _value = e.fromitem.value;
    var value = "";
    if (e.fromitem.index > -1) {
      value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }
    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

    value = "";
    if (e.toitem.index > -1) {
      _value = e.toitem.value;
      value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }
    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
    G("searchResultPanel").innerHTML = str;
  });

  var myValue;
  ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
    var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

    setPlace();
  });

  function setPlace(){
    map.clearOverlays();    //清除地图上所有覆盖物
    function myFun(){
      var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
      xPoint=pp.lng;
      yPoint=pp.lat;
      map.centerAndZoom(pp, 18);
      map.addOverlay(new BMap.Marker(pp));    //添加标注
    }
    var local = new BMap.LocalSearch(map, { //智能搜索
      onSearchComplete: myFun
    });
    local.search(myValue);
  }

</script>

</body>
</html> 