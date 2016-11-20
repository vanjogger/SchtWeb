<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>日志列表</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">
    <div class="row" style="width:1100px;">
        <form id="searchForm" class="form-horizontal span24">
                <div class="control-group span7">
                    <label class="control-label">操作人：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="name">
                    </div>
                </div>

                <div class="control-group span12">
                    <label class="control-label">日志时间：</label>
                    <div class="controls">
                        <input type="text" class="calendar calendar-time" name="startDate"><span> - </span><input name="endDate" type="text" class="calendar calendar-time">
                    </div>
                </div>
                <div class="span3 offset2">
                    <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
                </div>
        </form>
    </div>
  <div class="search-grid-container">
    <div id="grid"></div>
  </div>
</div>

<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script type="text/javascript">
    var store,gridCfg;
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form','bui/calendar'],function (Search,Page,Grid,Overlay,Form,Calendar) {
    var  columns = [
              {title:'操作人',dataIndex:'operateName',width:150},
              {title:'操作时间',dataIndex:'date',width:150},
              {title:'登录IP',dataIndex:'ip',width:150},
              {title:'操作日志',dataIndex:'operateContent',width:250}
            ];
            store = Search.createStore('/log/listData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [

                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });
      var datepicker = new Calendar.DatePicker({
          trigger:'.calendar-time',
          showTime:true,
          autoRender : true
      });
  });
</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
