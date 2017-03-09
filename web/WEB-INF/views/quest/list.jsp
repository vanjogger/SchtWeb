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
          <input type="text" class="control-text" name="title">
        </div>
      </div>
       <div class="control-group span8">
            <label class="control-label">关联商家：</label>
            <div class="controls">
                <input type="text" class="control-text" name="shopName">
            </div>
        </div>
      <div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls">
          <select name="status">
            <option value="">所有问题</option>
            <option value="NOMAL">上架</option>
            <option value="FROZEN">下架</option>
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
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
      {title:'问题',dataIndex:'title',width:350},
      {title:'关联商家',dataIndex:'shopName',width:150},
      {title:'问题数量',dataIndex:'sumCount',width:80},
      {title:'已参数数量',dataIndex:'count',width:80},
      {title:'问题奖励',dataIndex:'money',width:80},
      {title:'状态',dataIndex:'a',width:80,renderer:function(v,o){
        return o.status== 'FROZEN'?'下架':'上架';
      }},
      {title:'发布时间',dataIndex:'dateStr',width:150},
      {title:'操作',dataIndex:'',renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '编辑问题',
          text : '编辑问题',
          href : '/question/find?id='+obj.id
        });

        var updateStr = "";
          updateStr = "<a href=\"javascript:void(0);\" onclick=\"deleteData('"+obj.id+"')\">删除</a>";
        return editStr+updateStr;
      }}
    ];
    store = Search.createStore('/question/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [
          {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
            top.topManager.openPage({id:"notice_edit",href:"/question/add",title:"添加问题"});
          }}
        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

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
