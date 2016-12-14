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
      <div class="control-group span6">
        <label class="control-label"   style="width:90px;">代理商账号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="agentAccount"   style="width:120px;">
        </div>
      </div>

        <div class="control-group span6">
            <label class="control-label" style="width:90px;">代理商名称：</label>
            <div class="controls">
                <input type="text" class="control-text" name="agentName"   style="width:120px;">
            </div>
        </div>

        <div class="control-group span12"  style="width:360px;">
            <label class="control-label"  style="width:90px;">流水时间：</label>
            <div class="controls">
                <input type="text" class="calendar calendar-time" name="startDate"  style="width:110px;"><span> - </span><input name="endDate" type="text"  style="width:110px;" class="calendar calendar-time">
            </div>
        </div>
      <%--<div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls" >
          <select name="status" id="" >
              <option value="">--全部--</option>
            <option value="NORMAL">正常</option>
            <option value="FROZEN">冻结</option>
          </select>
        </div>
      </div>--%>
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
              {title:'代理商账号',dataIndex:'agentAccount',width:100},
              {title:'代理商名称',dataIndex:'agentName',width:100},
              {title:'类型',dataIndex:'type',width:100,renderer:function(value,obj){
                  if(obj.type=='HandFee'){
                      return "手动调整";
                  }else if(obj.type=='TxFee'){
                      return "提现扣费";
                  }else if(obj.type=='OrderFee'){
                      return "订单";
                  }
              }},
              {title:'变动前金额',dataIndex:'beforeAmount',width:150},
              {title:'流水金额',dataIndex:'amount',width:150},
              {title:'变动后金额',dataIndex:'afterAmount',width:150},
              {title:'流水时间',dataIndex:'dateStr',width:150}
        /*{title:'操作',dataIndex:'aaa',renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '调整金额',
                          text : '调整金额',
                          href : '/shopMoney/beforeEdit?id='+obj.id
                        });
                return editStr;
              }}*/
            ];
            store = Search.createStore('/agentFlow/listData');
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
