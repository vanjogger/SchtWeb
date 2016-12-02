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
        <label class="control-label">会员名称：</label>
        <div class="controls">
          <input type="text" class="control-text" name="memberName">
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
              {title:'会员名称',dataIndex:'memberName',width:150},
              {title:'收货人',dataIndex:'name',width:150},
              {title:'收获人电话',dataIndex:'mobile',width:150},
              {title:'操作',dataIndex:'aaa',renderer : function(value,obj){
                var editStr =  Search.createLink({ //链接使用 此方式
                          id : 'edit' + obj.id,
                          title : '查看会员收货地址',
                          text : '查看',
                          href : '/memberAddress/beforeEdit?id='+obj.id
                        });
               //var  delStr =  "&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"del('"+obj.id+"')\">删除</a>&nbsp;&nbsp;";
                return editStr;
              }}
            ];
            store = Search.createStore('/memberAddress/listData');
            gridCfg = Search.createGridCfg(columns,{
              tbar : {
                items : [

                ]
              },
              plugins : [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

  });

    function del(id){
        BUI.Message.Confirm("确定删除吗？",function(){
            $.ajax({
                url :"/shopBank/delete?r="+Math.random(),
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
