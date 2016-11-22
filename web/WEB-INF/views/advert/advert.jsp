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
  <div class="row">
  <div class="control-group span8">
  <label class="control-label">广告名称：</label>
  <div class="controls">
  <input type="text" class="control-text input-normal" name="title">
  </div>
  </div>

  <div class="control-group span8">
  <label class="control-label">状态：</label>
  <div class="controls" >
  <select name="status" id="" >
  <option value="">--全部--</option>
  <option value="NORMAL">正常</option>
  <option value="FROZEN">关闭</option>
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
  var del_url="/advert/deleteAll";
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
      {title:'序号',dataIndex:'sort',width:50},
      {title:'图片',dataIndex:'i',width:100,renderer:function(value,obj){
        return "<img src='"+obj.image+"' width='80'/>";
      }},
      {title:'广告名称',dataIndex:'title',width:'auto'},
      {title:'时间期限',dataIndex:'', width:300,renderer : function(value,obj){
        return obj.startDate + " ~ " + obj.endDate;
      }},
      {title:'状态',dataIndex:'', width:100,renderer : function(value,obj){
        return obj.status=='NORMAL'?'开启':'关闭';
      }},
      {title:'操作',dataIndex:'aaa',width:100,renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '编辑',
          text : '编辑',
          href : '/advert/find?id='+obj.id
        });
        var delStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
        return editStr+delStr;
      }}
    ];
    store = Search.createStore('/advert/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [
          {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
            top.topManager.openPage({id:"advert_place_add",href:"/advert/add",title:"新增广告位"});
          }}
        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });
  });

  function del(id){
    BUI.Message.Confirm("确定删除该广告吗？",function(){
      $.ajax({
        url :"/advert/delete?r="+Math.random(),
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

</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
