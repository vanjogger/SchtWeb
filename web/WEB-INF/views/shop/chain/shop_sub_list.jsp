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
        <label class="control-label">连锁商家名称：</label>
        <div class="controls">
          <input type="text" class="control-text" name="name">
        </div>
      </div>
        <input type="hidden" name="shopId" value="${shopId}"/>

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
              {title:'连锁商家名称',dataIndex:'name',width:150},
            {title:'当前状态',dataIndex:'status',width:80,renderer:function(value,obj){
                if(obj.status=='NORMAL'){
                    return "正常";
                }else{
                    return "冻结";
                }
            }},
              {title:'联系人',dataIndex:'linkName',width:120},
              {title:'联系电话',dataIndex:'linkMobile',width:120},
              {title:'商家图标',dataIndex:'icon',width:200,renderer:function(value,obj){
                 return  "<img src='"+obj.icon+"' style=\"width:80px;height:80px\" />"
              }},
              {title:'操作',dataIndex:'aaa',width:200,renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '编辑',
                          text : '编辑',
                          href : '/chainShop/beforeSubEdit?id='+obj.id+"&shopId=${shopId}"
                        });
                  var updateStr = "";
                  if(obj.status=='NORMAL'){
                      updateStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"frozen('"+obj.id+"')\">冻结</a>&nbsp;&nbsp;";
                  }else if(obj.status=='FROZEN'){
                      updateStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"unfrozen('"+obj.id+"')\">解冻</a>&nbsp;&nbsp;";
                  }
                var  delStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
                return editStr+updateStr+delStr;
              }}
            ];
            store = Search.createStore('/chainShop/listSubData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [
                  {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
                      top.topManager.openPage({id:"chainShop_sub_add",href:"/chainShop/beforeSubEdit?shopId=${shopId}",title:"新增"});
                  }}
                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

  });


    function frozen(id){
        BUI.Message.Confirm("确定冻结吗？",function(){
            $.ajax({
                url :"/chainShop/subshop/frozen?r="+Math.random(),
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

    function unfrozen(id){
        BUI.Message.Confirm("确定解冻吗？",function(){
            $.ajax({
                url :"/chainShop/subshop/unfrozen?r="+Math.random(),
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

    function del(id){
        BUI.Message.Confirm("确定删除吗？",function(){
            $.ajax({
                url :"/chainShop/subshop/deleteSub?r="+Math.random(),
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
