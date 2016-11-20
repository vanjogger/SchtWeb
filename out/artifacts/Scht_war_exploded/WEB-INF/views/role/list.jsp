<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>角色列表</title>
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
    var del_url = "/role/updateStatus";
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
              {title:'角色名称',dataIndex:'roleName',width:150},
              {title:'当前状态',dataIndex:'roleName',width:150,renderer:function(value,obj){
                  if(obj.status=='0'){
                      return "正常";
                  }else{
                      return "已删除";
                  }
              }},
              {title:'操作',dataIndex:'',width:200,renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '编辑',
                          text : '编辑',
                          href : '/role/beforeEdit?id='+obj.id
                        });
                  var delStr = "";
                  if(obj.status=='0')
                          delStr = "<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','1')\">删除</a>";

                return editStr+delStr;
              }}
            ];
            store = Search.createStore('/role/listData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [
                  {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
                      top.topManager.openPage({id:"role_edit",href:"/role/beforeEdit",title:"编辑角色"});
                  }}
                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

  });

    function updateStatus(id,status){
        var msg = "确定删除该角色吗？";
        BUI.Message.Confirm(msg,function(){
            $.ajax({
                url :"/role/updateStatus",
                type : 'post',
                data : {id:id,status:status},
                success : function(data){
                    if(data.success){ //删除成功
                        top.topManager.reloadPage();
                    }else{ //删除失败
                        BUI.Message.Alert(data.msg);
                    }
                }
            });
        },'question');
    }
</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
