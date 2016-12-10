<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>管理员列表</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">

  <form id="searchForm" class="form-horizontal">
    <input type="hidden" name="type" value="${type}"/>
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">商品名称：</label>
        <div class="controls">
          <input type="text" class="control-text" name="productName">
        </div>
      </div>
      <div class="control-group span12">
        <label class="control-label">评论时间：</label>
        <div class="controls">
          <input type="text" class="calendar calendar-time" name="startDate"><span> - </span><input name="endDate" type="text" class="calendar calendar-time">
        </div>
      </div>
      <div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls" >
          <select name="status" id="" >
            <option value="">--全部--</option>
            <option value="0">未回复</option>
            <option value="1">已回复</option>
          </select>
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
  var del_url = "/admin/updateStatus";
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
      {title:'评论商品',dataIndex:'productName',width:50},
      {title:'评论人',dataIndex:'memberName',width:120},
      {title:'评论分数',dataIndex:'grade',width:100},
      {title:'评论时间',dataIndex:'dateStr',width:120},
      {title:'状态',dataIndex:'a',width:150,renderer:function(v,o){
        if(o.status == '0'){
          return "未回复";
        }else{
          return "已回复";
        }
      }},
      {title:'操作',dataIndex:'aaa',width:200,renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '查看',
          text : '查看',
          href : '/productComment/beforeEdit?id='+obj.id
        });
        var delStr = " &nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";

        return editStr+delStr ;
      }}
    ];
    store = Search.createStore('/productComment/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [

        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

  });


  function del(id){
    BUI.Message.Confirm("确定删除吗？",function(){
      $.ajax({
        url :"/productComment/delete?r="+Math.random(),
        type : 'post',
        data : {id:id},
        success : function(data){
          BUI.Message.Alert(data.msg);
          if(data.success){ //删除成功
            top.topManager.reloadPage();
          }
        }
      });
    },'question');
  }

  function updateStatus(_id, _status){
    var mes = "确定下架该商品？";
    if(_status == 'NORMAL'){
      MES = "确定上架该商品？"
    }
    BUI.Message.Confirm(mes,function(){
      $.ajax({
        url :"/product/updateStatus?r="+Math.random(),
        type : 'post',
        data : {id:_id, status : _status},
        success : function(data){
          BUI.Message.Alert(data.msg,function(){
          if(data.success){
            top.topManager.reloadPage();
          }
        },'info');
        }
      });
    },'question');
  }
</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
