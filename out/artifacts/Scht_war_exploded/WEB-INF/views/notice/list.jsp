<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>子公司列表</title>
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
                <label class="control-label">标题：</label>
                <div class="controls">
                    <input type="text" class="control-text" name="title">
                </div>
            </div>
          <%--  <div class="control-group span8">
                <label class="control-label">负责人名称：</label>
                <div class="controls">
                    <input type="text" class="control-text" name="leaderName">
                </div>
            </div>--%>
            <div class="control-group span8">
                <label class="control-label">状态：</label>
                <div class="controls" >
                    <select name="status" id="" >
                        <option value="">--全部--</option>
                        <option value="NORMAL">启动</option>
                        <option value="FROZEN">冻结</option>
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
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
              {title:'公告标题',dataIndex:'title',width:350},
              {title:'创建时间',dataIndex:'timeStr',width:150},
              {title:'状态',dataIndex:'status',width:150,renderer:function(value,obj){
                  if(obj.status=='NORMAL'){
                      return "启动";
                  }else{
                      return "冻结";
                  }
              }},
              {title:'操作',dataIndex:'',width:200,renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '编辑公告',
                          text : '编辑公告',
                          href : '/notice/beforeEdit?id='+obj.id
                        });

                  var updateStr = "";
                  if(obj.status == "NORMAL"){
                      updateStr = "<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','FROZEN')\">冻结</a>";
                  }else{
                      updateStr = "<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','NORMAL')\">启用</a>";
                  }

                return editStr+updateStr;
              }}
            ];
            store = Search.createStore('/notice/listData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [
                  {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
                      top.topManager.openPage({id:"notice_edit",href:"/notice/beforeEdit",title:"新建公告"});
                  }}
                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

  });

    function updateStatus(id,status){
        var msg = "确定冻结该公告吗？";
        if(status=="NORMAL"){
            msg = "确定启动该公告吗？";
        }
        BUI.Message.Confirm(msg,function(){
            $.ajax({
                url :"/notice/updateStatus",
                type : 'post',
                data : {
                    id:id,
                    status:status
                },
                success : function(data){
                    BUI.Message.Alert(data.msg,function(){
                        if(data.success){ //删除成功
                            top.topManager.reloadPage();
                        }
                    });
                }
            });
        },'question');
    }
</script>
<script type="text/javascript" src="/views/js/list.js"></script>
</body>
</html>  
