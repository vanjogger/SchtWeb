<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>后台管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <style type="text/css">
    body {
      font-size: 12px;
      line-height: 1.5;
      font-variant: normal;
      font-style: normal;
      font-weight: normal;
      font: 12px/1.5 宋体, Arial, Helvetica, sans-serif;
      font-family: 宋体, Arial, Helvetica, sans-serif;
      margin: 0;
      padding: 0;
      background: url(${request.getContextPath()}/resources/images/login_bg.jpg) repeat-x top #eff0f6;
      color: #6F6F6F;
    }
    table {
      margin: 10px auto;
    }
    input {
      margin: 0px;
      padding: 0px;
      float: left;
    }
    img {
      border: 0px;
    }
    #crm-top {
      height: 280px;
      margin: auto;
      background: url(${request.getContextPath()}/resources/images/login_tbg.jpg) center no-repeat;
    }
    #crm-box-bg {
      margin: auto;
      background: url(${request.getContextPath()}/resources/images/login_bbg.jpg) center top no-repeat;
      overflow: hidden;
    }
    #login-box {
      width: 472px;
      margin: auto;
      background: #F3F3F3;
    }
    #login-box #login_top {
      background: url(${request.getContextPath()}/resources/images/login_box_top.jpg) no-repeat;
      height: 48px;
    }
    #login-box #login_top #version {
      height: 18px;
      background: url(${request.getContextPath()}/resources/images/login_lv_bg.jpg) left no-repeat;
      float: right;
      padding: 15px 20px;
    }
    #login-foot {
      background: url(${request.getContextPath()}/resources/images/login_box_foot.jpg) repeat-x;
      height: 21px;
      padding: 15px 15px;
    }
    #login-foot #Repwd {
      float: left;
      color: #FFF;
    }
    #login-foot #loginbt {
      float: right;
    }
    #login-coypright {
      text-align: center;
      height: 60px;
      line-height: 60px;
      color: #3C6087;
      font-family: Arial, Helvetica, sans-serif;
    }
    #login-coypright a {
      color: #3C6087;
      text-decoration: none;
    }
    input {
      font-weight: normal;
      line-height: 20px;
    }
    input[type="text"],input[type="password"] {
      display: inline-block;
      height: 20px;
      width: 240px;
      padding: 4px 6px;
      margin-bottom: 10px;
      line-height: 20px;
      color: #555555;
      vertical-align: middle;
      -webkit-border-radius: 4px;
      -moz-border-radius: 4px;
      border-radius: 4px;
    }
    input[type="text"],input[type="password"] {
      background-color: #ffffff;
      border: 1px solid #cccccc;
      -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
      -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
      box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
      -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
      -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
      -o-transition: border linear 0.2s, box-shadow linear 0.2s;
      transition: border linear 0.2s, box-shadow linear 0.2s;
    }
    input[type="text"]:focus,input[type="password"]:focus {
      border-color: rgba(82, 168, 236, 0.8);
      outline: 0;
      outline: thin dotted \9;
      /* IE6-9 */
      -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
      rgba(82, 168, 236, 0.6);
      -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
      rgba(82, 168, 236, 0.6);
      box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
      rgba(82, 168, 236, 0.6);
    }
  </style>
  <style id="avalonStyle">
    .avalonHide {
      display: none !important
    }
  </style>
  <style type="text/css">
    .fancybox-margin {
      margin-right: 0px;
    }
  </style>
  <script>
    window.onload=function(){
      var isInMainStatus = window.parent.document.getElementById("isInMainStatus");
      if(isInMainStatus==null){
        return;
      }else if(isInMainStatus.value==1){
        window.parent.location = "${request.getContextPath()}/login";
      }
    };
  </script>
</head>
<body>
<div id="crm-top"></div>
<div id="crm-box-bg">
  <div id="login-box">
    <div id="login_top">
      <div id="version">

      </div>
    </div>
    <form id="login" method="post" onsubmit="return check()"  action="login">
      <table border="0" width="100%" cellpadding="3" cellspacing="3">
        <tbody>
        <tr>
          <td align="right" height="42" width="30%">用户名：</td>
          <td width="70%">
            <input autocomplete="off" name="userName" id="userName" value="admin" onblur="checkData(this,1)" style="width: 200px;font-size:14px;font-family:Arial;" type="text">
            <span id="tip_1" style="color: Red;"></span>
          </td>
        </tr>
        <tr>
          <td align="right" height="42">密&nbsp;码：</td>
          <td>
            <input name="password" id="password" value="1" onblur="checkData(this,2)" style="width: 200px; font-size: 14px; font-family: Arial;" type="password">
            <span id="tip_2" style="color: Red;"></span>
          </td>
        </tr>
        <tr>
          <td align="right" height="42">验证码：</td>
          <td>
            <input name="validateCode" id="validateCode" onblur="checkData(this,3)" style="width: 100px; font-size: 14px; font-family: Arial;" type="text" >
            <img src="/validateCode" id="img_validateCode" width="100" height="26"  onclick="refreshValidateCode()"/>
            <span id="tip_3" style="color: Red;"></span>
            <span  style="color: Red;">${message_login}</span>
          </td>
        </tr>
        </tbody>
      </table>
      <div id="login-foot">
        <div id="loginbt">
          <input id="submit" src="${request.getContextPath()}/resources/images/login-bt.png" style="border-width: 0px;" type="image">
        </div>
      </div>
    </form>
  </div>
</div>
<div id="login-coypright">
  Copyright ©2016,All Rights Reserved.</a>
</div>

<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>

<script type="text/javascript">
  function refreshValidateCode(){
    $("#img_validateCode").attr("src","/validateCode?ran="+Math.random());
  }

  function checkData(e,index){
    if(e){
      if(e.value==""|| e.value=="undefined"){
        $("#tip_"+index).html("不能为空！");
        return false;
      }else{
        $("#tip_"+index).html("");
        return true;
      }
    }else{
      return false;
    }
  }

  function check(){
    var submit = document.getElementById("submit");
    submit.setAttribute("disabled", "disabled");
    var username = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var authcode = document.getElementById("validateCode").value;
    if(username==""||username=="undefined"){
      $("#tip_1").html("用户名不能为空！");
      submit.removeAttribute("disabled");
      return false;
    }else{
      $("#tip_1").html("");
    }
    if(password==""||password=="undefined"){
      $("#tip_2").html("密码不能为空！");
      submit.removeAttribute("disabled");
      return false;
    }else{
      $("#tip_2").html("");
    }
    if(authcode==""||authcode=="undefined"){
      $("#tip_3").html("验证码不能为空！");
      submit.removeAttribute("disabled");
      return false;
    }else{
      $("#tip_3").html("");
    }
    return true;
  }

</script>

</body>
</html>