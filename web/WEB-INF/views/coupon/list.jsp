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
      {title:'优惠券名称',dataIndex:'name'},
      {title:'优惠券面值',dataIndex:'couponMoney',width:100},
      {title:'优惠券总数',dataIndex:'count',width:100},
      {title:'已发放数量',dataIndex:'pushCount',width:100},

      {title:'操作',dataIndex:'aaa', width:150,renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '编辑',
          text : '编辑',
          href : '/coupon/beforeEdit?id='+obj.id
        });
        var recordStr = Search.createLink({
          id:'record' + obj.id,
          title:'发放记录',
          text:'发放记录',
          href:'/coupon/recordList?couponId=' + obj.id
        });
        var  delStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";

        return recordStr + editStr+delStr ;
      }}
    ];
    store = Search.createStore('/coupon/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [
          {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
            top.topManager.openPage({id:"coupon_add",href:"/coupon/beforeEdit",title:"添加优惠券"});
          }}
        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

  });

  function del(id){
    BUI.Message.Confirm("确定删除吗？",function(){
      $.ajax({
        url :"/coupon/delete?r="+Math.random(),
        type : 'post',
        data : {id:id},
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
