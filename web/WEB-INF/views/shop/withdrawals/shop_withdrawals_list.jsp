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
</head>
<body>

<div class="container">

  <form id="searchForm" class="form-horizontal">
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">商家账号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="shopAccount">
        </div>
      </div>
        <div class="control-group span8">
            <label class="control-label">商家名称：</label>
            <div class="controls">
                <input type="text" class="control-text" name="shopName">
            </div>
        </div>

      <div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls" >
          <select name="status" id="" >
              <option value="">--全部--</option>
            <option value="0">待审核</option>
            <option value="1">审核通过</option>
            <option value="2">审核拒绝</option>
          </select>
        </div>
      </div>

        <div class="control-group span12">
            <label class="control-label">流水时间：</label>
            <div class="controls">
                <input type="text" class="calendar calendar-time" name="startDate"><span> - </span><input name="endDate" type="text" class="calendar calendar-time">
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
              {title:'商家账号',dataIndex:'shopAccount',width:150},
              {title:'商家名称',dataIndex:'shopName',width:150},
                {title:'当前状态',dataIndex:'status',width:150,renderer:function(value,obj){
                    if(obj.status=='0'){
                        return "待审核";
                    }else if(obj.status=='1'){
                        return "审核通过";
                    }else{
                        return "审核拒绝";
                    }
                }},
              {title:'提现余额',dataIndex:'amount',width:150},
             {title:'申请时间',dataIndex:'dateStr',width:150},
              {title:'操作',dataIndex:'aaa',renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '查看',
                          text : '查看',
                          href : '/shopTx/beforeEdit?id='+obj.id
                        });
                return editStr;
              }}
            ];
            store = Search.createStore('/shopTx/listData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [

                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

  });

</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
