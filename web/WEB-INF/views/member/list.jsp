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
        <label class="control-label">会员账号：</label>
        <div class="controls">
          <input type="text" class="control-text" name="account">
        </div>
      </div>
        <div class="control-group span8">
            <label class="control-label">会员昵称：</label>
            <div class="controls">
                <input type="text" class="control-text" name="nick">
            </div>
        </div>
      <div class="control-group span8">
        <label class="control-label">联系电话：</label>
        <div class="controls">
          <input type="text" class="control-text" name="telephone">
        </div>
      </div>
      <div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls" >
          <select name="status" id="" >
            <option value="">--全部--</option>
            <option value="NORMAL">开启</option>
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
      {title:'头像',dataIndex:'',width:100,renderer:function(value,obj){
        if(obj.headIcon == undefined || obj.headIcon == ''){
          return "<img src='/resources/images/default_user.gif' width='80'/>";
        }
        return  "<img src='"+obj.headIcon+"' width='80'/>";
      }},
      {title:'账号',dataIndex:'account',width:150},
      {title:'昵称',dataIndex:'nick',width:150},
      {title:'联系电话',dataIndex:'telephone',width:150},
      {title:'注册时间',dataIndex:'date',width:150},
      {title:'状态',dataIndex:'status',width:150,renderer:function(value,obj){
        if(obj.status=='NORMAL'){
          return "开启";
        }else{
          return "冻结";
        }
      }},
      {title:'操作',dataIndex:'',width:200,renderer : function(value,obj){
        var editStr =  Search.createLink({ //链接使用 此方式
          id : 'edit' + obj.id,
          title : '编辑',
          text : '编辑',
          href : '/admin/member/find?id='+obj.id
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
    store = Search.createStore('/admin/member/listData');
    gridCfg = Search.createGridCfg(columns,{
      tbar : {
        items : [
          {text : '<i class="icon-plus"></i>添加',btnCls : 'button button-small',handler:function(){
            top.topManager.openPage({id:"member_add",href:"/admin/member/add",title:"添加会员"});
          }}
        ]
      },
      plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
    });

  });

  function updateStatus(id,status){
    var msg = "确定冻结该会员账号吗？";
    if(status=="NORMAL"){
      msg = "确定启动该会员账号吗？";
    }
    BUI.Message.Confirm(msg,function(){
      $.ajax({
        url :"/admin/member/updateStatus",
        type : 'post',
        data : {
          id:id,
          status:status
        },
        success : function(data){
          BUI.Message.Alert(data.msg,function(){
            if(data.success){ //
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
