<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    #Dtop {border-right:#CCCCCC 3px solid;border-bottom:#CCCCCC 3px solid;}
    #Dtop .dbody{border:#333333 1px solid;padding:1px;background:#EBF0F6;}
    #Dtop .dhead{background:#2875B9 url('/resources/images/close.gif') no-repeat right 4px;padding:0 0 0 0;letter-spacing:1px;border-bottom:#333333 1px solid;color:#FFFFFF;line-height:20px;font-weight:bold;cursor:move;}
    #Dtop .dhead span{float:right;cursor:pointer;display:block;width:22px;height:19px;text-align:right;}
    #Dtop .dbox{padding:8px;line-height:200%;}
</style>
<iframe name="upload_back" style="display:none" id="upload_back">

</iframe>
<div style="z-index: 999999; position: absolute; width: 250px; left: 304px; top: 55px; opacity: 1;" id="Dtop">
    <div class="dbody">
        <div class="dhead"><span>
            <div style="float:left;" onclick="javascript:(document.getElementById('upload_file_absolute').innerHTML = '')">
            &nbsp;&nbsp;&nbsp;</div>&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;上传图片
        </div>
        <div class="dbox">
            <form action="/upload/img" enctype="multipart/form-data" method="post" target="upload_back" onsubmit="">
                <input type="hidden" name="id" value="${param.id}">
                <input type="hidden" name="smallImg" value="${param.smallImg}">
                <input type="hidden" name="smallId" value="${param.smallId}"/>
                <input type="hidden" name="smallImgSrc" value="${param.smallImgSrc}"/>
                <table cellpadding="3">
                    <tbody>
                    <tr id="local_url">
                        <td><input type="file" name="upload" id="upthumb"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="Dsubmit" value="上 传" class="btn">&nbsp;&nbsp;
                            <input type="button" onclick="document.getElementById('upload_file_absolute').innerHTML = ''" value="取 消" class="btn">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>