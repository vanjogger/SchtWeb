/*扩展validatebox控件*/
$.extend($.fn.validatebox.defaults.rules, {
    minLength: { // 判断最小长度
        validator: function(value, param){
            return value.length >= param[0];
        },
        message: '最少输入 {0} 个字符。'
    },
    length: {
        validator: function(value, param){
            var len = $.trim(value).length;
            return len >= param[0] && len <= param[1];
        },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function(value){
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '格式不正确,请使用如下格式:020-88888888'
    },
    mobile: {// 验证手机号码
        validator: function(value){
            return /^(13|15|18|17)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    idcard: {// 验证身份证
        validator: function(value){
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function(value){
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    currency: {// 验证货币
        validator: function(value){
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    qq: {// 验证QQ,从10000开始
        validator: function(value){
            return /^[1-9]\d{4,11}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    integer: {// 验证整数
        validator: function(value){
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    chinese: {// 验证中文
        validator: function(value){
            return /^[\u0391-\uFFE5]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function(value){
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    unnormal: {// 验证是否包含空格和非法字符
        validator: function(value){
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    code: {// 验证字母和数字
        validator: function(value){
            return /[a-zA-Z0-9_]+$/i.test(value);
        },
        message: '只能输入字母数字和下划线'
    },
    username: {// 验证用户名
        validator: function(value){
            return /^[a-zA-Z][a-zA-Z0-9_]{2,15}$/i.test(value);
        },
        message: '用户名不合法（必须以字母开头，允许3-16字符，允许字母数字下划线）'
    },
    faxno: {// 验证传真
        validator: function(value){
            //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function(value){
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    ip: {// 验证IP地址
        validator: function(value){
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function(value){
            return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    carNo: {
        validator: function(value){
            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
        },
        message: '车牌号码无效（例：粤J12350）'
    },
    carenergin: {
        validator: function(value){
            return /^[a-zA-Z0-9]{16}$/.test(value);
        },
        message: '发动机型号无效(例：FG6H012345654584)'
    },
    email: {
        validator: function(value){
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的电子邮件账号(例：abc@126.com)'
    },
    msn: {
        validator: function(value){
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same: {
        validator: function(value, param){
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            }
            else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    }
});

/*
 * 扩展datagrid控件
 */
$.extend($.fn.datagrid.methods, {
    addEditor: function(jq, param){
        if (param instanceof Array) {
            $.each(param, function(index, item){
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor;
            });
        }
        else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    removeEditor: function(jq, param){
        if (param instanceof Array) {
            $.each(param, function(index, item){
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        }
        else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    },
    setTitle: function(jq, text){
        //$(jq).parent().parent().parent().find("div.panel-title").text(text);
        $(jq).closest('div.panel.datagrid').find('div.panel-title').text(text);
    },
    readonly: function(jq, param){
        if (param == 'removeToolbar') {
            //从options中删除工具栏配置
            delete $(jq).datagrid('options').toolbar;
            //从缓存中删除工具栏
            $(jq).parent().parent().find("div.datagrid-toolbar").remove();
        }
        else 
            if (param == 'hiddenToolbar') {
                //从缓存中删除工具栏，重新载入后工具栏会再度显示
                $(jq).parent().parent().find("div.datagrid-toolbar").remove();
            //$(jq).parent().prev().remove();
            }
        
        var cols = $(jq).datagrid('options').columns;
        $.each(cols, function(index, col){
            $.each(col, function(i, item){
                if (item.editor) {
                    delete item.editor; //删除编辑列
                }
                if (item.checkbox && index == 0) 
                    $(jq).datagrid('hideColumn', item.field); //隐藏checkbox列
            });
        });
    },
    validate: function(jq){
        var rows = $(jq).datagrid('getRows');
        try {
            $.each(rows, function(index, obj){
                $(jq).datagrid('beginEdit', index);
                if (!$(jq).datagrid('validateRow', index)) {
                    throw false;
                }
                $(jq).datagrid('endEdit', index);
            });
        } 
        catch (e) {
            return e;
        }
        return true;
    },
    endData: function(jq, index){
        return jq.each(function(){
            var opts = $.data(this, "datagrid").options;
            var updatedRows = $.data(this, "datagrid").updatedRows;
            var insertedRows = $.data(this, "datagrid").insertedRows;
            var tr = opts.finder.getTr(this, index);
            var row = opts.finder.getRow(this, index);
            var changed = false;
            var newValues = {};
            tr.find("div.datagrid-editable").each(function(){
                var field = $(this).parent().attr("field");
                var ed = $.data(this, "datagrid.editor");
                var value = ed.actions.getValue(ed.target);
                //alert(value);
                if (row[field] != value) {
                    row[field] = value;
                    changed = true;
                    newValues[field] = value;
                }
            });
            tr.find("input.combo-value").each(function(){
                var field = $(this).parent().parent().parent().attr("field");
                //这里获取select数据
                var value = $(this).val();
                //alert(row[field] != value);
                if (row[field] != value) {
                    row[field] = value;
                    changed = true;
                    newValues[field] = value;
                }
            });
            if (changed) {
                if (getObjectIndex(insertedRows, row) == -1) {
                    if (getObjectIndex(updatedRows, row) == -1) {
                        updatedRows.push(row);
                    }
                }
            }
        });
    },
    autoMergeCells: function(jq, field){
        var rows = $(jq).datagrid("getRows");
        var rowspan = 1;
        var startIndex = 0;
        var maxIndex = rows.length - 1;
        for (var i = 0; i <= maxIndex; i++) {
            if (i < maxIndex) {
                if (rows[i][field] == rows[i + 1][field]) {
                    //如果本字段和下一字段相同，合并行数加1
                    rowspan += 1;
                }
                else {
                    //如果本字段和下一字段不相同
                    if (rowspan > 1) {
                        //如果合并行数大于1，则开始单元格合并
                        $(jq).datagrid("mergeCells", {
                            index: startIndex,
                            field: field,
                            rowspan: rowspan,
                            colspan: 1
                        });
                    }
                    //重置起始行索引
                    startIndex = i + 1;
                    //重置合并单元格计数
                    rowspan = 1;
                }
            }
            else {
                //如果是最大索引，则进行合并单元格操作
                if (rowspan > 1) {
                    //如果合并行数大于1，则开始单元格合并
                    $(jq).datagrid("mergeCells", {
                        index: startIndex,
                        field: field,
                        rowspan: rowspan,
                        colspan: 1
                    });
                }
            }
        }
    }
});

/*扩展dialog控件*/
$.extend($.fn.dialog.methods, {
    init: function(jq, callback, options){
        var opts = $.extend({
            closed: true,
            cache: false,
            modal: true,
            resizable: true,
            buttons: [{
                text: '保存',
                iconCls: "icon-ok",
                handler: function(){
                    callback();
                }
            }, {
                text: '关闭',
                iconCls: "icon-cancel",
                handler: function(){
					$(jq).dialog('close');
                }
            }]
        }, options || {});
        
        $(jq).dialog(opts);
    }
});

function clearValidatebox(){
	$(".validatebox-tip").remove();
	$(".validatebox-invalid").removeClass("validatebox-invalid");
}
