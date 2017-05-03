

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <form id="J_Form1" class="form-horizontal" action="/paySet/saveWeixin">
        <div class="row">
            <div class="control-group span20">
                <label class="control-label">APPID：</label>
                <div class="controls">
                    <input name="appId" type="text" data-tip="{text:'应用appid'}"
                           value="${data.appId}" class="input-normal control-text" style="width:200px;">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span20">
                <label class="control-label">商户号：</label>
                <div class="controls">
                    <input name="mchNo" type="text" data-tip="{text:'微信商户号'}"
                           value="${data.mchNo}" class="input-normal control-text" style="width:200px;">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span20">
                <label class="control-label">微信Secret：</label>
                <div class="controls">
                    <input name="secret" type="text" data-tip="{text:'微信Secret'}"
                           value="${data.secret}" class="input-normal control-text" style="width:200px;">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span20">
                <label class="control-label">微信支付密钥：</label>
                <div class="controls">
                    <input name="payKey" type="text" data-tip="{text:'微信支付密钥'}"
                           value="${data.payKey}" class="input-normal control-text" style="width:200px;">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span20">
                <label class="control-label">企业付款证书：</label>
                <div id="J_Uploader">
                </div>
                <input type="hidden" name="cerPath" id="cerPath" value="${data.cerPath}"/>
            </div>
        </div>
        <div class="row">
            <div class="control-group span20">
                <label class="control-label">状态：</label>
                <div class="controls">
                    <label class="radio" for="status1"><input ID="status1" type="radio" name="status" value="NORMAL"
                    ${(empty data.status || data.status=='NORMAL')?'checked':''}>开启</label>&nbsp;&nbsp;&nbsp;
                    <label class="radio" for="status2"><input id="status2" type="radio" name="status" value="FROZEN"
                    ${data.status=='FROZEN'?'checked':''}>关闭</label>
                </div>
            </div>
        </div>
        <div class="row form-actions ">
            <div class="span13 offset3 ">
                <button type="submit" class="button button-primary">保存</button>
                <button type="reset" class="button" >重置</button>
            </div>
        </div>
    </form>
　
<script type="text/javascript">
    BUI.use(["bui/form",'bui/uploader'],function(Form,Uploader){
        var form = new Form.HForm({
            srcNode : '#J_Form1',
            submitType:"ajax",
            listeners:{
                beforesubmit:function(){
                    return true;
                }
            },
            callback:function(data){
                BUI.Message.Alert(data.msg,function(){
                    if(data.success){

                    }
                },'info');
            }
        });
        form.render();
        var uploader = new Uploader.Uploader({
            //指定使用主题
            render: '#J_Uploader',
            url: '/upload/buiUpload',

//            queue: {
//                resultTpl:{
//                    'success': '<div class="success">{url}</div>',
//                    'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
//                }
//            },
            rules: {
                //文的类型
//        ext: ['.png,.jpg','文件类型只能为{0}'],
                //上传的最大个数
                max: [1, '文件的最大个数不能超过{0}个'],
                //文件大小的最大值,单位也是kb
                maxSize: [1024, '文件大小不能大于1M']
            },
            isSuccess:function(result){
                if(result && result.url) {
                    $("#cerPath").val(result.url);
                    $("#J_Uploader ul").html('<li class="bui-queue-item bui-queue-item-error bui-queue-item-success"><div class="error"><span title="'+result.url+'">'+result.url+'</span><span class="uploader-error"></span></div> <span class="action"><span class="bui-queue-item-del">删除</span></span></li>');
                }
            }
        }).render();
        if('${data.cerPath}' != ''){
            $("#J_Uploader ul").html('<li class="bui-queue-item bui-queue-item-error bui-queue-item-success"><div class="error"><span title="${data.cerPath}">${data.cerPath}</span><span class="uploader-error"></span></div> <span class="action"><span class="bui-queue-item-del">删除</span></span></li>');

        }
    });

</script>
　