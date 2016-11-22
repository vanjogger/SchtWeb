<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>后台管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header">
  <div class="dl-title"><span class="">后台管理系统</span></div>
  <div class="dl-log">欢迎您，<span class="dl-log-user">${ADMIN.loginName}</span>&nbsp;
    <a href="javascript:void(0);" title="修改密码" class="dl-log-quit" onclick="updatePwd()">修改密码</a>&nbsp;
    <a href="/logout" title="退出系统" class="dl-log-quit">[退出]</a>
  </div>
</div>
<div class="content">
  <div class="dl-main-nav">
    <ul id="J_Nav"  class="nav-list ks-clear">
     <%--<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">企业管理</div></li>--%>
      <%--<li class="nav-item"><div class="nav-item-inner nav-inventory">项目管理</div></li>--%>
      <%--<li class="nav-item"><div class="nav-item-inner nav-storage">财务管理</div></li>--%>

      <li class="nav-item"><div class="nav-item-inner nav-certification">系统设置</div></li>
    </ul>
  </div>
  <ul id="J_NavContent" class="dl-tab-conten">

  </ul>

</div>
<input type="hidden" id="isInMainStatus" value="1">
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>
<script>
  BUI.use('common/main',function(){
    var config = [{
      id:'system',
      homePage:"admin_list",
      menu:[{
        text:'系统管理',
        items:[
          <shiro:hasPermission name="admin:list">
          {id:'admin_list',text:'管理员管理',href:'/admin/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="role:list">
          {id:'role_list',text:'角色管理',href:'/role/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="permission:list">
          {id:'permission_list',text:'权限管理',href:'/permission/list',closeable:true},
                </shiro:hasPermission>
                <shiro:hasPermission name="advertPlace:list">
          {id:'advert_place_list', text:'广告位管理',href:'/advertPlace/list', closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="log:list">
          {id:'log_list',text:'日志管理',href:'/log/list',closeable:true}
          </shiro:hasPermission>
        ]
      }]
    }];
    new PageUtil.MainPage({
      modulesConfig : config
    });
  });


  function updatePwd(){
    top.topManager.openPage({
      id:"update_pwd",
      href:"/admin/beforeUpdatePwd?ran="+Math.random(),
      title:"修改密码"
    });

  }

</script>
</body>
</html>