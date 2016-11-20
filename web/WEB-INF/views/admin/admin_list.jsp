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
    <div class="row">
      <div class="control-group span8">
        <label class="control-label">登录名：</label>
        <div class="controls">
          <input type="text" class="control-text" name="loginName">
        </div>
      </div>

      <div class="control-group span8">
        <label class="control-label">状态：</label>
        <div class="controls" >
          <select name="status" id="" >
              <option value="">--全部--</option>
            <option value="NORMAL">正常</option>
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
    var del_url = "/admin/updateStatus";
  BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {
    var  columns = [
              {title:'登录名',dataIndex:'loginName',width:150},
              {title:'所属角色',dataIndex:'roleName',width:80},
              {title:'真实姓名',dataIndex:'realName',width:120},
              {title:'最近登录时间',dataIndex:'formatLoginTime',width:130},
              {title:'登录次数',dataIndex:'loginCnt',width:80},
              {title:'状态',dataIndex:'status',width:60,renderer:function(value,obj){
                if(obj.status=='NORMAL'){
                  return "正常";
                }else if(obj.status=='FROZEN'){
                  return "已冻结";
                }
              }},
              {title:'操作',dataIndex:'aaa',width:200,renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '编辑',
                          text : '编辑',
                          href : '/admin/beforeEdit?id='+obj.id
                        });
                  var updateStr = "<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','FROZEN')\">冻结</a>";
                  if(obj.status=='FROZEN'){
                      updateStr = "<a href=\"javascript:void(0);\" onclick=\"updateStatus('"+obj.id+"','NORMAL')\">解冻</a>";
                  }
                  var delStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
                  var pwdStr =  Search.createLink({ //链接使用 此方式
                      id : 'pwd' + obj.id,
                      title : '修改密码',
                      text : '修改密码',
                      href : '/admin/beforeChangePwd?id='+obj.id
                  });
                return editStr+updateStr+delStr+pwdStr;
              }}
            ];
            store = Search.createStore('/admin/listData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [
                  {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:function(){
                      top.topManager.openPage({id:"admin_add",href:"/admin/beforeAdd",title:"新增管理员"});
                  }}
                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

  });

    function updateStatus(id,status){
        var msg = "确定冻结该账号吗？";
        if(status=='NORMAL')
            msg = "确定解冻改账号吗？";
        BUI.Message.Confirm(msg,function(){
            $.ajax({
                url :"/admin/updateStatus",
                type : 'post',
                data : {id:id,status:status},
                success : function(data){
                    BUI.Message.Alert(data.msg);
                    if(data.success){ //删除成功
                        top.topManager.reloadPage();
                    }
                }
            });
        },'question');
    }
    function del(id){
        BUI.Message.Confirm("确定删除该管理员吗？",function(){
            $.ajax({
                url :"/admin/delete?r="+Math.random(),
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
