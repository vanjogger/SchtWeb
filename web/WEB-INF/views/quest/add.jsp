<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title> 公告</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/resources/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/bui-min.css" rel="stylesheet" type="text/css" />
  <link href="/resources/css/page-min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="/resources/js/editor/skins/default.css">
  <script language="JavaScript" src="/resources/js/editor/kindeditor.js"></script>
  <script language="JavaScript" src="/resources/js/editor/zh_CN.js"></script>
</head>
<body>

<div class="container">
  <form id="J_Form" class="form-horizontal" method="post" action="/question/save" >
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true}"
                 value="" class="input-large control-text" >
        </div>
      </div>
    </div>
    <div class="row" id="shops_div"  >
      <div class="control-group span20">
        <label class="control-label">关联商家：</label>
        <div class="controls control-row4" style="height:150px;">
          <input type="hidden" name="shopId" id="shopId"/>
          <input type="text" class="input-normal control-text" id="shopName">
          <input type="button" class="button" onclick="searchShop()" value="检索"/>
          <br/>
          <select id="shops" class="input-large" onchange="selShop(this)" size="6" style="height:115px;">
          </select>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题奖励：</label>
        <div class="controls">
          <input type="text" name="money" class="control-text input-small"
                 data-rules="{required:true,regexp:/^\d+(.\d{1,2})?$/}"
                 data-messages="{regexp:'请填写数字，允许保留两位小数'}"/>
          <span class="tip-text">回答正确奖励金额</span>
        </div>
      </div>
    </div>
      <div class="row">
        <div class="control-group span20">
          <label class="control-label"><s>*</s>问题总数：</label>
          <div class="controls">
            <input type="text" name="sumCount" class="control-text input-small"

                   data-rules="{required:true,regexp:/^\d+$/}"
                   data-messages="{regexp:'请填写整数'}"/>
            <span class="tip-text">一共发布多少道该问题</span>
          </div>
        </div>
      </div>
    <div class="row">
    <div class="control-group span20">
      <label class="control-label"><s>*</s>状态：</label>
      <div class="controls">

        <input type="radio" name="status" value="NORMAL" CHECKED/>上架
        <input type="radio" name="status" value="FROZEN"/>下架
      </div>
    </div>
  </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label">问题答案：</label>
        <div class="panel-body">
          <table class=" table bui-grid-table">
            <tr>
              <th class="center">选项</th>
              <th class="center" style="width:310px;">答案</th>
              <th class="center">是否正确</th>
              <th></th>
            </tr>
            <tbody id="answer">
            <tr>
             <td><input type="text" class="control-text input-small"  data-rules="{required:true}" name="a_sort"/> </td>
              <td><input type="text" class="control-text input-large" data-rules="{required:true}" name="a_content"/> </td>
              <td><input type="checkbox" name="a_answer"/> </td>
              <td><a class="button" onclick="addTr(this)">+</a> </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <%--<div class="row">--%>
      <%--<div class="control-group span20">--%>
        <%--<label class="control-label">商品状态：</label>--%>
        <%--<div class="controls">--%>
          <%--<label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL" CHECKED>上架</label>&nbsp;&nbsp;&nbsp;--%>
          <%--<label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN">下架</label>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
    <%--<div class="row">--%>
      <%--<div class="control-group span20">--%>
        <%--<label class="control-label">排序：</label>--%>
        <%--<div class="controls">--%>
          <%--<input type="text" name="sort" value="1" data-rules="{regexp:/^\d+$/}" data-messages="{regexp:'请输入整数'}"--%>
                 <%--class="input-small control-text"/>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <input type="hidden" name="jsonStr" id="jsonStr"/>
        <button type="submit" class="button button-primary" >保存</button>
        <button type="button" class="button" onclick="back()">返回</button>
      </div>
    </div>
    <div id="hidden_div" class="hide"></div>
  </form>
</div>
<script type="text/javascript" src="/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sea.js"></script>
<script type="text/javascript" src="/resources/js/bui-min.js"></script>
<script type="text/javascript" src="/resources/js/config-min.js"></script>


<script type="text/javascript">
  function addTr(_node){
    var html='<tr><td><input type="text" class="control-text input-small" data-rules="{required:true}" name="a_sort"/> </td>'
            +'<td><input type="text" class="control-text input-large" data-rules="{required:true}" name="a_content"/> </td>'
            +'<td><input type="checkbox" name="a_answer"/> </td>'
           +' <td><a class="button" onclick="removeTr(this)">-</a> </td></tr>';
    $("#answer").append(html);
  }
  function removeTr(_n){
    $(_n).closest("tr").remove();
  }

  function searchShop(){
    var val = $("#shopName").val();
    $.ajax({
      url:"/shop/ajaxList",
      data:{name:val}, dataType:"json",
      success:function(data){
        if(data.success){
          var list = data.data;
          $("#shops").html('');
          for(var i=0; i < list.length; i++){
            $("#shops").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
          }
        }
      }
    });
  }

  function selShop(_t){
    $("#shopId").val($(_t).val());
    $("#shopName").val($(_t).find("option:selected").text());
  }


  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form','bui/uploader'],function (Tree,Form,Uploader) {

    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
           var answers = $("#answer tr");
          var html = [], suc = false;
          for(var i=0; i < answers.length; i++){
            var sort = $(answers[i]).find("input[name='a_sort']").val();
            var content = $(answers[i]).find("input[name='a_content']").val();
            var a ={
              "sort" : sort,
              "content" : content
            };
            if($(answers[i]).find("input[name='a_answer']").is(":checked")) {
              suc = true;
              a["answer"] = "true";
            }else{
              a["answer"] = "false";
            }
            html.push(a);
          }
          if(!suc) {
            BUI.Message.Alert("请设置正确答案",function(){
              return false;
            },'info');
            return false;
          }
          $("#jsonStr").val(JSON.stringify(html));
          return true;
        }
      },
      callback:function(data){
        BUI.Message.Alert(data.msg,function(){
          if(data.success){
            back();
          }
        },'info');
      }
    });
    form.render();

  });

  function back(){
     var backId = "quest_list";
    top.topManager.openPage({
      id : backId,
      isClose : true
    });
    top.topManager.reloadPage(backId);
  }
</script>

<body>
</html> 