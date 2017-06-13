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
      {title:'姓名',dataIndex:'name',width:100},
      {title:'电话',dataIndex:'telephone',width:200},
      {title:'地址',dataIndex:'address'},

      {title:'操作',dataIndex:'aaa',renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '编辑',
          text : '编辑',
          href : '/dispatchMember/find?id='+obj.id
        });
        var  delStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
        return editStr+delStr;
      }}
    ];
    store = Search.createStore('/dispatchMember/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [
          {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
            top.topManager.openPage({id:"hotkey_add",href:"/dispatchMember/add",title:"添加快递员"});
          }}
        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

  });

  function del(id){
    BUI.Message.Confirm("确定删除吗？",function(){
      $.ajax({
        url :"/dispatchMember/delete?r="+Math.random(),
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
