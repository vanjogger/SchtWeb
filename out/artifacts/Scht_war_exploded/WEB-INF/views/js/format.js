/**
 * Created by Administrator on 2016/7/4.
 */
//��ʽ��
var myFormat = {
    formatNumber : function(_v) {
        return parseFloat(_v).toFixed(2);
    },
    formatDateYMDHMS : function(_time){
        var d = new Date(_time);
        return (d.getFullYear() + "-" + myFormat.ultZeroize(d.getMonth() + 1) + "-"
        + myFormat.ultZeroize(d.getDate()) + " " + myFormat.ultZeroize(d.getHours()) + ":"
        + myFormat.ultZeroize(d.getMinutes()) + ":" + myFormat.ultZeroize(d.getSeconds()));
    },
    formatDateYMD: function(_time) {
        var d = new Date(_time);
        return (d.getFullYear() + "-" + myFormat.ultZeroize(d.getMonth() + 1) + "-"
        + myFormat.ultZeroize(d.getDate()));
    },
    ultZeroize : function(v, l) {
        var z = "";
        l = l || 2;
        v = String(v);
        for (var i = 0; i < l - v.length; i++) {
            z += "0";
        }
        return z + v;
    }
}

function uploadImage(fileUploadId, name, viewDiv, width, height,isAppend) {
    $.ajaxFileUpload(
        {
            url: "/upload/uploadImage?_t=" + new Date().getTime(),            //需要链接到服务器地址
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileUploadId,                        //文件选择框的id属性
            dataType:"json",
            success: function (data, status)  //服务器成功响应处理函数
            {
                if (data.success) {
                    if(isAppend){
                        var r = new Date().getTime();
                        var html = "<div id='div_"+r+"' style='float:left;'><img style=\"cursor: pointer\"  src='"+data.data+"' width='" + width + "' height='" + height + "' name='" + name + "'/>" +
                            "<a class=\"fileDlt\" style=\"cusor:hander;\"  onclick=\"deleteImg('"+r+"')\">删除</a><input type='hidden' name='"+name+"' value='"+data.data+"'/></div>";
                        $("#" + viewDiv).append(html);
                    }else{
                        $("#" + viewDiv).val(data.data);
                    }
                    $("#"+fileUploadId).val("");
                } else {
                    alert(data.msg);
                }
            },
            error: function (data, status, e) {
                alert(e);
            }
        }
    );
}


function deleteImg(id){
    $("#div_"+id).remove();
}