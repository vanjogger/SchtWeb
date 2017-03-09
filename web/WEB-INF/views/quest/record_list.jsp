<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>子公司列表</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">

  <form id="searchForm" class="form-horizontal">
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">问题：</label>
        <div class="controls">
          <input type="text" class="control-text" name="questTitle">
        </div>
      </div>
      <div class="control-group span8">
        <label class="control-label">会员账号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="memberAccount">
        </div>
      </div>

      <div class="span3 offset2">
        <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
      </div>
    </div>
  </form>

  <div class="search-grid-container">
    <div id="grid"></div>
  </div>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script type="text/javascript">
  var store,gridCfg;
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
      {title:'问题',dataIndex:'questTitle',width:350},
      {title:'会员账号',dataIndex:'memberAccount',width:150},
      {title:'参与时间',dataIndex:'dateStr',width:150},
      {title:'问题奖励',dataIndex:'money',width:80},
      {title:'状态',dataIndex:'a',width:80,renderer:function(v,o){
        return o.suc?'回答正确':'回答错误';
      }}
    ];
    store = Search.createStore('/questRecord/listData');
    gridCfg = Search.createGridCfg(columns);

  });

  function deleteData(id){
    var msg = "确定删除该问题吗？";
    BUI.Message.Confirm(msg,function(){
      $.ajax({
        url :"/question/delete",
        type : 'post',
        data : {
          id:id,
          status:status
        },
        success : function(data){
          BUI.Message.Alert(data.msg,function(){
            if(data.success){ //删除成功
              top.topManager.reloadPage();
            }
          });
        }
      });
    },'question');
  }
</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
