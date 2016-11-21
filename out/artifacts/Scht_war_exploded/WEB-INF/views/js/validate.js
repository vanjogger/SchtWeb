/**
 * Created by Administrator on 2016/7/4.
 */
//����Ƿ�Ϊfloat����
function checkFloat(_params) {
    var reg = /^(-|\+)?\d+\.\d*$/;
    return checkInt(_params) || reg.test(_params);
}
//����Ƿ�Ϊ��Ǯ����
function checkMoney(_params) {
    var reg = /^(-?\d+)(\.\d{0,2})?$/;     //С����ʽ
    return reg.test(_params);
}

//��������Ǯ����
function checkInputMoney(_input, _tipId) {
    var tipNode = $("#" + _tipId);
    var isNum = checkMoney(_input.value);
    if (!isNum) {
        tipNode.css("color", "Red");
    } else {
        tipNode.css("color", "Gray");
    }
}
//����Ƿ�Ϊemail����
function checkEmail(_params) {
    var t = /^([a-zA-Z0-9]+[_|\\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return t.test(_params);
}
//����Ƿ���������
function checkSZ(_params) {
    var t = /^\d+$/;
    return t.test(_params);
}
//����Ƿ�Ϊ�ֻ���11λ
function checkMobile(_params) {
    var t = /^1[0-9]{10}$/;
    return t.test(_params);
}
function checkTelephone(_params) {
    var t = /(\d{3}-|\d{4}-)?(\d{8}|\d{7})?/;
    return t.test(_params);
}
//����˺��Ƿ�Ϊ��ĸ�����»������
function checkAccount(_params) {
    var t = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;
    return t.test(_params);
}

//����Ƿ�Ϊ���ַ�
function checkIsNull(_params) {
    return _params.replace(/\s/g, '') == '';
}

function LTrim(str) {
    var i;
    for (i = 0; i < str.length; i++) {
        if (str.charAt(i) != " " && str.charAt(i) != " ")
            break;
    }
    str = str.substring(i, str.length);
    return str;
}
function RTrim(str) {
    var i;
    for (i = str.length - 1; i >= 0; i--) {
        if (str.charAt(i) != " " && str.charAt(i) != " ")
            break;
    }
    str = str.substring(0, i + 1);
    return str;
}
function Trim(str) {
    return LTrim(RTrim(str));
}
