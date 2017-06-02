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
  <form id="J_Form" class="form-horizontal" method="post" action="/question/update" >
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题：</label>
        <div class="controls">
          <input name="title" type="text" data-rules="{required:true}"
                 value="${data.title}" class="input-large control-text" >
        </div>
      </div>
    </div>
    <%--<div class="row" id="shops_div"  >--%>
      <%--<div class="control-group span20">--%>
        <%--<label class="control-label">关联商家：</label>--%>
        <%--<div class="controls control-row4" style="height:150px;">--%>
          <%--<input type="hidden" name="shopId" id="shopId" value="${data.shopId}"/>--%>
          <%--<input type="text" class="input-normal control-text" id="shopName" value="${data.shopName}">--%>
          <%--<input type="button" class="button" onclick="searchShop()" value="检索"/>--%>
          <%--<br/>--%>
          <%--<select id="shops" class="input-large" onchange="selShop(this)" size="6" style="height:115px;">--%>
          <%--</select>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>商家名称：</label>
        <div class="controls">
          <input type="text" name="shopName" class="control-text" value="${data.shopName}"
                  />
          <span class="tip-text">商家名称或出资人姓名。</span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>联系电话：</label>
        <div class="controls">
          <input type="text" name="telephone" class="control-text" value="${data.telephone}"
                  />
          <span class="tip-text">商家或出资人联系电话。</span>
        </div>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">所在区域：</label>
      <div class="controls  control-row-auto">
        <select id="t_province" name="t_province" onchange="loadArea(2)">
        </select>
        <select id="t_city"  name="t_city" onchange="loadArea(3)">

        </select>
        <select id="t_district" name="t_district">

        </select>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题奖励：</label>
        <div class="controls">
          <input type="text" name="money" id="money" class="control-text input-small" value="${data.money}"
                 data-rules="{required:false,regexp:/^\d+(.\d{1,2})?$/}"
                 data-messages="{regexp:'请填写数字，允许保留两位小数'}"/>
          <span class="tip-text">回答正确奖励金额</span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>优惠券奖励：</label>
        <div class="controls">
          <select name="couponId" id="couponId">
            <option value="">--选择优惠券奖励---</option>
            <c:forEach items="${coupons}" var="e">
              <option value="${e.id}" ${e.id == data.couponId?'selected':''}>${e.name}</option>
            </c:forEach>
          </select>
          <span class="tip-text">回答正确奖励优惠券，设置了优惠券后金额奖励失效。</span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题总数：</label>
        <div class="controls">
          <input type="text" name="sumCount" class="control-text input-small"
              value="${data.sumCount}"
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

          <input type="radio" name="status" value="NORMAL" ${data.status=="NORMAL"?'checked':''}/>上架
          <input type="radio" name="status" value="FROZEN" ${data.status=="FROZEN"?'checked':''}/>下架
        </div>
      </div>
    </div>
    <div class="row">
      <label class="control-label"><s>*</s>问题图片：</label>
      <div class="span8">
        <div id="J_Uploader">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span20">
        <label class="control-label"><s>*</s>问题解析：</label>
        <div style="display:inline;height:40px;margin-left: 10px;">
            <textarea name="content" id="content" cols=""
                      rows="" class="textinput" style="width:700px;height:350px;visibility:hidden;"
                      maxlength="1000">${data.content}</textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group span24">
        <label class="control-label">问题答案：</label>
        <div class="panel-body">
          <table class=" table bui-grid-table">
            <tr>
              <th class="center">选项</th>
              <th class="center" style="width:310px;">图片</th>
              <th class="center" style="width:310px;">答案</th>
              <th class="center">是否正确</th>
              <th></th>
            </tr>
            <tbody id="answer">
            <c:forEach items="${data.list}" var="e" varStatus="ei">
              <tr>
                <td><input type="text" class="control-text input-small"
                           value="${e.sort}" data-rules="{required:true}" name="a_sort"/> </td>
                <c:choose>
                  <c:when test="${ei.first}">
                    <td id="icon0">
                      <input id="file" type="file" multiple="multiple" accept="image/*" style="display:none;"/>
                      <img src="${empty e.icon?'/resources/images/default_goods.gif':e.icon}" style="width:80px" onclick="a_icon_id='icon0';return $('#file').click()"/>
                      <input type="hidden" name="a_icon" value="${e.icon}"/>
                    </td>
                  </c:when>
                  <c:otherwise>
                    <td id="icon${ei.count}">
                      <img src="${empty e.icon?'/resources/images/default_goods.gif':e.icon}" style="width:80px" onclick="a_icon_id='icon${ei.count}';return $('#file').click()"/>
                      <input type="hidden" name="a_icon" value="${e.icon}"/>
                    </td>
                  </c:otherwise>
                </c:choose>
                <td><input type="text" class="control-text input-large"
                           value="${e.content}" data-rules="{required:true}" name="a_content"/> </td>
                <td><input type="checkbox" name="a_answer" ${e.answer?'checked':''}/> </td>
                <td>
                  <c:choose>
                    <c:when test="${ei.first}"><a class="button" onclick="addTr(this)">+</a></c:when>
                    <c:otherwise><a class="button" onclick="removeTr(this)">-</a></c:otherwise>
                  </c:choose>
                   </td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="row form-actions ">
      <div class="span13 offset3 ">
        <input type="hidden" name="jsonStr" id="jsonStr"/>
        <input type="hidden" name="icon" id="icon" value="${data.icon}"/>
        <input type="hidden" name="id" value="${data.id}"/>
        <input type="hidden" name="provinceId" id="provinceId" value="${data.provinceId}"/>
        <input type="hidden" name="provinceName" id="provinceName" value="${data.provinceName}"/>
        <input type="hidden" name="cityId" id="cityId" value="${data.cityId}"/>
        <input type="hidden" name="cityName" id="cityName" value="${data.cityName}"/>
        <input type="hidden" name="districtId" id="districtId" value="${data.districtId}"/>
        <input type="hidden" name="districtName"  id="districtName" value="${data.districtName}"/>
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
<script type="text/javascript" src="/resources/js/LocalResizeIMG.js"></script>


<script type="text/javascript">

  var icon_i=100;
  function addTr(_node){
    var t_id = "icon" + icon_i;
    var html='<tr><td><input type="text" class="control-text input-small" data-rules="{required:true}" name="a_sort"/> </td>'
            +'<td id="'+t_id+'">'
            +'<img src="/resources/images/default_goods.gif" style="width:80px" ' +
            'onclick="a_icon_id=\''+t_id+'\';return $(\'#file\').click()"/>'+
            '<input type="hidden" name="a_icon"/>  </td>'
            +'<td><input type="text" class="control-text input-large" data-rules="{required:true}" name="a_content"/> </td>'
            +'<td><input type="checkbox" name="a_answer"/> </td>'
            +' <td><a class="button" onclick="removeTr(this)">-</a> </td></tr>';
    icon_i++;
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
  var editor;
  KindEditor.ready(function(K) {
    editor = K.create('textarea[name="content"]', {
      allowFileManager: true
    });
  });

  BUI.use('common/page'); //页面链接跳转
  BUI.use(['bui/tree','bui/form','bui/uploader'],function (Tree,Form,Uploader) {

    var form = new Form.HForm({
      srcNode : '#J_Form',
      submitType:"ajax",
      listeners:{
        beforesubmit:function(){
          $("#icon").val($("#J_Uploader img").attr("src"));
          $("#content").val(editor.html());
          $("#provinceId").val($("#t_province").val());
          $("#provinceName").val($("#t_province").find("option:selected").text());
          $("#cityId").val($("#t_city").val());
          $("#cityName").val($("#t_city").find("option:selected").text());
          $("#districtId").val($("#t_district").val());
          $("#districtName").val($("#t_district").find("option:selected").text());
          if($("#money").val() == '' &&$("#couponId").val() == '') {
            BUI.Message.Alert("请设置奖励金额或奖励优惠券",function(){
              return false;
            },'info');
            return false;
          }
          var answers = $("#answer tr");
          var html = [], suc = false;
          for(var i=0; i < answers.length; i++){
            var sort = $(answers[i]).find("input[name='a_sort']").val();
            var content = $(answers[i]).find("input[name='a_content']").val();
            var a ={
              "sort" : sort,
              "content" : content,
              "icon" : $(answers[i]).find("input[name='a_icon']").val()
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
    var uploader = new Uploader.Uploader({
      //指定使用主题
      render: '#J_Uploader',
      url: '/upload/buiUpload',
      queue: {
        resultTpl:{
          'success': '<div class="success"><img src="{url}" title="{name}" width="100%;padding:5px;"/></div>',
          'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
        }
      },
      rules: {
        //上传的最大个数
        max: [1, '文件的最大个数不能超过{0}个'],
        //文件大小的最大值,单位也是kb
        maxSize: [2048, '文件大小不能大于2M']
      }
    }).render();
    if('${data.icon}' != ''){
    //图片
    $("#J_Uploader ul").append('<li class="bui-queue-item bui-queue-item-success">' +
            '<div class="success"><img src="${data.icon}" ' +
            'width="100%;padding:5px;"></div> ' +
            '<span class="action"><span class="bui-queue-item-del">删除</span></span></li>');
    }
  });

  function back(){
    var backId = "quest_list";
    top.topManager.openPage({
      id : backId,
      isClose : true
    });
    top.topManager.reloadPage(backId);
  }

//  searchShop();

  var a_icon_id;
  $(document).ready(function(){
    $('input:file').localResizeIMG({
      width: 300,
      height:300,
      quality: 1,
      success: function (result) {
        $.ajax({
          url: '/upload/uploadImg',
          type: 'post',
          data: {image: result.clearBase64},
          dataType: 'json',
          success: function (data) {
            if(data.success){
              $("#" + a_icon_id).find("img").attr('src', data.data);
              $("#" + a_icon_id).find("input[name='a_icon']").val(data.data);
            } else {
              alert("上传图片出错");
            }
          }
        });
      }
    });
  });

  $(function(){
    loadArea(1);
    var cId = $("#cityId").val();
    if(cId!='')
      loadArea(2);
    var dId = $("#districtId").val();
    if(dId!='')
      loadArea(3);
  })

  function loadArea(i){
    var parentId = "";
    if(i==2){
      parentId = $("#t_province").val();
      if(parentId=='')
        parentId = $("#provinceId").val();
    }else if(i==3){
      parentId = $("#t_city").val();
      if(parentId=='')
        parentId = $("#cityId").val();
    }
    $.ajax({
      url:"/common/listNationByParentId?r="+Math.random(),
      type:"post",
      data:{
        lx : i,
        id:parentId
      },
      success:function(res){
        if(res.success){
          var html = "<option value=''>-- 请选择 --</option>";
          $.each(res.data,function(j,n){
            html += "<option value='"+ n.id+"'";
            var sel = "";
            var pId = $("#provinceId").val();
            var cId = $("#cityId").val();
            var dId = $("#districtId").val();
            if(i==1){
              if(n.id==pId)
                sel = " selected ";
            }else if(i==2){
              if(n.id==cId)
                sel = " selected ";
            }else if(i==3){
              if(n.id==dId)
                sel = " selected ";
            }
            html += sel + ">";
            html += n.mc;
            html += "</option>"
          })
          if(i==1){
            $("#t_province").html(html);
          }else if(i==2){
            $("#t_city").html(html);
          }else if(i==3){
            $("#t_district").html(html);
          }
        }
      },
      error:function(){}
    })
  }
</script>

<body>
</html> 