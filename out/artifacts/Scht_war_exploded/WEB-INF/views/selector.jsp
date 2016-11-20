<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
<link href="/resources/css/reset.css" rel="stylesheet"
	type="text/css" />
<link href="/resources/css/master.css" rel="stylesheet"
	type="text/css" />
<link href="/resources/css/pop_up.css" rel="stylesheet"
	type="text/css" />
<script src="/resources/js/jquery-1.8.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
//页面加载完成时调用
	$(function() {
		loadData();
	});

	function loadData(){
		$.ajax({
			url:"/common/listQyData?ran="+Math.random(),
			type:"post",
			data:{
				name:$("#searchKey").val()
			},
			success:function(res){
				if(res.success==true){
					fillData(res.data);
				}
			},
			error:function(){

			}
		});
	}

	function fillData(data){
		if(data!=null&data.length>0){
			var html = "";
			$.each(data,function(i,n){
				html += "<tr>";
				html += "<td><input type='radio' name='qyId' value='"+ n.id+"'/></td>";
				html += "<td><span id='name_"+ n.id+"'>"+n.name+"</span></td>";
				html += "</tr>"
			})
			$("#table_list").html(html);
		}
	}


	function SelectOK(){
		var boxes = $("input[name='qyId']");
    	var ids = "";
    	$.each(boxes,function(i,n){
    		if($(this).attr("checked")=="checked"){
				ids += $(this).val()+",";
    		}
    	});
    	if(ids.length>0)
			ids = ids.substr(0,ids.length-1);
    	if(ids==""){
    		alert("请选择数据！");
    		return ;
    	}
		var name = $("#name_"+ids).html();

    	if(parent.selectOK(ids,name)==false)
		{
			return;
		}
		CloseMe();
	}
	//关闭窗口
	function CloseMe()
	{
		try{parent.$('.close').click();}catch(e){};
	}
	 
	 function selectAll(){
	    	var boxs=$("input[name='box']");
	    	var chk = false;
			if($("#all").attr("checked")=="checked"){
				chk = true;
			}
			$.each(boxs,function(i,n){
				$(this).attr("checked",chk);
			});
	    	
	    	
	    }
</script>

</head>

<body>
  <!--pop_con start-->
   <div class="pop_main">              
               
         <span class="span_fl_b clear" style="width:60%;margin:2px 0 0 0;">
                   <input type="text" class="search_input gray_f float_l"   id="searchKey" onkeyup="loadData()"/>
              <b class="search_icon float_r"></b>
         </span>
            <div class="scroll_box" style="height:300px">
          <table width="100%" class="table_con  mar_top8">
			  <thead>
			  	<tr>
					<td></td>
					<td>企业名称</td>
				</tr>
			  </thead>
			  <tbody id="table_list">
			  <%--<c:forEach items="${list}" var="e">
				  <tr>
					  <td><input type="radio" name="qyId" value="${e.id}"/></td>
					  <td><span id="name_${e.id}">${e.name}</span></td>
				  </tr>
			  </c:forEach>--%>
			  </tbody>

          </table>
          </div>
          </div> 
          <!--bottom start-->
          <div class="pop_bot">
               <input type="submit" value="保存" class="pop_btn" onclick="SelectOK()"/>
               <input type="submit" value="关闭" class="pop_btn_r" onclick="CloseMe()"/>
          </div>

</body>
</html>
