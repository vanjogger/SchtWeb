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
    <input type="hidden" name="orderType" value="${orderType}"/>
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">订单编号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="no">
        </div>
      </div>
      <div class="control-group span8">
        <label class="control-label">会员账号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="memberAccount">
        </div>
      </div>
      <div class="control-group span8">
        <label class="control-label">订单状态：</label>
        <div class="controls">
          <select name="status">
            <option value="">--所有--</option>
            <c:if test="${orderType != 'EXTEND'}">
            <option value="CREATE">待支付</option>
            <option value="PAY">已支付</option>
            <option value="DISPATCH">已发货</option>
            </c:if>
            <c:if test="${orderType == 'EXTEND'}">
              <option value="PAY">待消费</option>
            </c:if>
            <option value="SUCCESS">已完成</option>

            <option value="CLOSE">已关闭</option>

          </select>
        </div>
      </div>
      <div class="control-group span12">
        <label class="control-label">下单时间：</label>
        <div class="controls" >
          <input type="text" class="calendar control-text" name="startTime"/>~
          <input type="text" class="calendar control-text" name="endTime"/>
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
      {title:'订单编号',dataIndex:'no',width:150},
      {title:'会员账号',dataIndex:'memberAccount',width:120 },
      {title:'商家',dataIndex:'s',width:150,renderer:function(v,o){
        return o.shopId ? o.shopName : "自营"
      }},
      {title:'订单金额',dataIndex:'totalMoney',width:100},
      {title:'下单时间',dataIndex:'t',width:120,renderer:function(v,o){
        return o.createDate;
      }},
      {title:'订单状态',dataIndex:'a',width:150,renderer:function(v,o){
        if(o.status == 'CREATE') return '待支付';
        if(o.status == 'PAY') return o.orderType == 'EXTEND'?'待消费':'已支付';
        if(o.status == 'DISPATCH')return '已发货';
        if(o.status == 'SUCCESS') return '已完成';
        if(o.status == 'CLOSE') return '已关闭';
      }},

      {title:'操作',dataIndex:'aaa',width:200,renderer : function(value,obj){
     var delStr = ""
        if(obj.status == 'CLOSE'){
          delStr = " &nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
        }
        var dispatchStr = "";
        if(obj.status == 'PAY' && obj.express == '1'){
          dispatchStr = " &nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"dispatch('"+obj.id+"')\"> 发货 </a>&nbsp;&nbsp;"
        }
        var viewStr = " &nbsp;&nbsp;<a href='/order/find?id="+obj.id+"'>查看</a>&nbsp;&nbsp;";
        return delStr + dispatchStr + viewStr;
      }}
    ];
    store = Search.createStore('/order/listData');
    gridCfg = Search.createGridCfg(columns,{
      <%--tbar : {--%>
        <%--items : [--%>
          <%--{text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){--%>
            <%--top.topManager.openPage({id:"product_type_add",href:"/product/add?type=${type}",title:"新增"});--%>
          <%--}}--%>
        <%--]--%>
      <%--},--%>
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


</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
