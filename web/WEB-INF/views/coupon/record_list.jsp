<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>优惠券发放记录</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">

  <form id="searchForm" class="form-horizontal">
    <input type="hidden" name="couponId" value="${couponId}"/>
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">会员账号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="memberAccount">
        </div>
      </div>

      <div class="control-group span8">
        <label class="control-label">使用状态：</label>
        <div class="controls" >
          <select name="status" id="" >
            <option value="">--全部--</option>
            <option value="0">未使用</option>
            <option value="1">已使用</option>
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
<script type="text/javascript" src="/WEB-INF/views/js/format.js"></script>
<script type="text/javascript">
  var store,gridCfg;
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [

      {title:'会员账号',dataIndex:'memberAccount',width:150},
      {title:'优惠券名称',dataIndex:'couponName',width:150},
      {title:'优惠券金额',dataIndex:'couponMoney',width:150},
      {title:'领取时间',dataIndex:'dateStr',width:150},
      {title:'状态',dataIndex:'status',width:150,renderer:function(value,obj){
        if(obj.status){
          return "已使用";
        }else{
          return "未使用";
        }
      }},
      {title:'使用时间',dataIndex:'useDate',width:150}
    ];
    store = Search.createStore('/coupon/recordData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {

      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

  });


</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
