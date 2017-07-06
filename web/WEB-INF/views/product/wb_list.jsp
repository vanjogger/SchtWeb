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
    <input type="hidden" name="wb" value="1"/>
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">商品名称：</label>
        <div class="controls">
          <input type="text" class="control-text" name="title">
        </div>
      </div>
      <div class="control-group span8">
      <label class="control-label">商品分类：</label>
      <div class="controls">
      <select name="typeId">
      <option value="">所有商品</option>
      <c:forEach items="${typeList}" var="e">
        <c:if test="${e.type == '0'}">
      <option value="${e.id}">${e.name}</option>
        </c:if>
      </c:forEach>
      </select>
      </div>
      </div>
      <div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls" >
          <select name="status" id="" >
            <option value="">--全部--</option>
            <option value="NORMAL">上架</option>
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
  var del_url = "/admin/updateStatus";
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
      {title:'序号',dataIndex:'sort',width:50},
      {title:'商品图标',dataIndex:'icon',width:120,renderer:function(value,obj){
        if(obj.icon){
          return  "<img src='"+obj.icon+"' style=\"width:80px;\" />"
        }else{
          return "<img src='/resources/images/default_goods.gif' width='80'/>";
        }
      }},
      {title:'商品名称',dataIndex:'title',width:150},
      {title:'分类名称',dataIndex:'typeName',width:100},
      {title:'商品价格',dataIndex:'price',width:80},

      {title:'实际销量',dataIndex:'saleCount',width:80},
      {title:'状态',dataIndex:'sort',width:120,renderer:function(v,o){
        return o.status == 'NORMAL'?'上架':'下架';
      }},

      {title:'操作',dataIndex:'aaa',width:200,renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '编辑',
          text : '编辑',
          href : '/product/find?id='+obj.id
        });
        var delStr = " &nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
        var updateStatus = " &nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','NORMAL')\">上架</a>&nbsp;&nbsp;";
        if(obj.status == 'NORMAL'){
          updateStatus = " &nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','FORZEN')\">下架</a>&nbsp;&nbsp;";
        }

        return editStr+delStr + updateStatus;
      }},
      {title:'ID',dataIndex:'id',width:150}
    ];
    store = Search.createStore('/product/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [
          {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
            top.topManager.openPage({id:"product_wb_add",href:"/product/wbadd",title:"新增"});
          }}
        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

  });


  function del(id){
    BUI.Message.Confirm("确定删除吗？",function(){
      $.ajax({
        url :"/product/delete?r="+Math.random(),
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
