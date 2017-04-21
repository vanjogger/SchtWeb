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
     <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">商城管理</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-user">会员管理</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-product">商品管理</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-supplier">商家管理</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-order">交易管理</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-storage">配置管理</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-certification">系统设置</div></li>
      <li class="nav-item"><div class="nav-item-inner nav-product">外卖管理</div></li>
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
      id:'home_manage',
      homePage:"data_list",
      menu:[{
        text:"工作台",
        items:[
          {id:'data_list', text:'数据统计',href:'/workspace', closeable:true}
        ]
      },{
        text:'广告管理',
        items:[
          <shiro:hasPermission name="adplace:list">
          {id:'ad_place_list', text:'广告位管理',href:'/advertPlace/list', closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="ad:list">
          {id:'advert_list', text:'广告管理',href:'/advert/list',closeable:true},
          </shiro:hasPermission>
        ]},
        {
          text:'公告管理',
          items:[
            <shiro:hasPermission name="notice:typeList">
            {id:'notice_type_list',text:'公告分类',href:'/noticeType/list',closeable:true},
            </shiro:hasPermission>
            <shiro:hasPermission name="notice:list">
            {id:'notice_list',text:'公告管理',href:'/notice/list',closeable:true}
            </shiro:hasPermission>

          ]
      }]
    },{
        id:'member_manage',
        homePage:"member_list",
        menu:[{
          text:'会员管理',
          items:[
            <shiro:hasPermission name="member:list">
            {id:'member_list',text:'会员信息管理',href:'/admin/member/list',closeable:true},
            </shiro:hasPermission>
            <shiro:hasPermission name="memberAddress:list">
            {id:'member_address_list',text:'收货地址管理',href:'/memberAddress/list',closeable:true},
            </shiro:hasPermission>
            <shiro:hasPermission name="memberFlow:list">
            {id:'member_flow_list',text:'会员资金流水',href:'/memberFlow/list',closeable:true}
            </shiro:hasPermission>
          ]},
          {
          text:'有奖问答',
          items:[
            <shiro:hasPermission name="question_set">
            {id:'quest_set',text:'问答设置',href:'/questSet/find',closeable:true},
            </shiro:hasPermission>
            <shiro:hasPermission name="question:list">
            {id:'quest_list',text:'问题管理',href:'/question/list',closeable:true},
            </shiro:hasPermission>
            <shiro:hasPermission name="question:record">
            {id:'quest_record',text:'问答记录',href:'/questRecord/list',closeable:true}
            </shiro:hasPermission>
          ]}
        ]
      },{
      id:'product_manage',
      homePage:"product_list",
      menu:[{
        text:'商品管理',
        items:[
          <shiro:hasPermission name="product:list">
          {id:'product_list',text:'普通商品管理',href:'/product/list?type=NORMAL',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="wzproduct:list">
          {id:'wz_product_list',text:'五折商品管理',href:'/product/list?type=DISCOUNT',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="tgproduct:list">
          {id:'tg_product_list',text:'推广商品管理',href:'/product/list?type=EXTEND',closeable:true}
          </shiro:hasPermission>
        ]},
        {
          text:'商品评论管理',
          items:[
            <shiro:hasPermission name="product:commentlist">
            {id:'product_comment_list',text:'商品评论管理',href:'/productComment/list',closeable:true}
            </shiro:hasPermission>
          ]}
      ]
    },{
      id:'shop_manage',
      homePage:"shop_category_list",
      menu:[{
        text:'商家分类管理',
        items:[
          <shiro:hasPermission name="shop:typelist">
          {id:'shop_category_list',text:'商家分类管理',href:'/shopType/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="shopinfo:list">
          {id:'shop_list',text:'商家信息管理',href:'/shop/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="chainshop:list">
          {id:'chain_shop_list',text:'连锁商家管理',href:'/chainShop/list',closeable:true}
          </shiro:hasPermission>
        ]},{
        text:'商家资金管理',
        items:[
          <shiro:hasPermission name="shopzj:list">
          {id:'shop_zj_list',text:'商家资金管理',href:'/shopMoney/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="shopflow:list">
          {id:'shop_ls_list',text:'商家资金流水',href:'/shopFlow/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="shoptx:list">
          {id:'shop_tx_list',text:'商家提现记录',href:'/shopTx/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="shopbank:list">
          {id:'shop_bankcard_list',text:'商家银行卡管理',href:'/shopBank/list',closeable:true}
          </shiro:hasPermission>
        ]}
      ]
    },{
      id:'trade_manage',
      homePage:"order_list",
      menu:[{
        text:'订单管理',
        items:[
          <shiro:hasPermission name="order:list">
          {id:'order_list',text:'订单管理',href:'/order/list?orderType=NORMAL',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="wzorder:list">
          {id:'wzorder_list',text:'五折订单管理',href:'/order/list?orderType=DISCOUNT',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="tgorder:list">
          {id:'tgorder_list',text:'推广订单管理',href:'/order/list?orderType=EXTEND',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="xfm:list">
          {id:'xfm_list',text:'消费码验证管理',href:'/order/verify',closeable:true}
          </shiro:hasPermission>
        ]}
      ]
    },{
      id:'config_manage',
      homePage:"pay_list",
      menu:[{
        text:'配置管理',
        items:[
          <shiro:hasPermission name="xfxx:list">
          {id:'pay_list',text:'支付信息管理',href:'/paySet/ali',closeable:true},
                </shiro:hasPermission>
          <shiro:hasPermission name="txfl:list">
          {id:'tx_rate_list',text:'提现费率管理',href:'/withdrawSet/find',closeable:true},
                </shiro:hasPermission>
          <shiro:hasPermission name="orderLimitSet">
          {id:'order_limit_list',text:'订单时限设置',href:'/orderLimitSet/find',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="tssz:list">
          {id:'tg_list',text:'推送设置',href:'/pushSet/find',closeable:true},
                </shiro:hasPermission>

          <shiro:hasPermission name="sms:list">
          {id:'sms_list',text:'短信余额查询',href:'/common/querySms',closeable:true}
          </shiro:hasPermission>
        ]},
        {
          text:'代理商资金',
          items:[
            <shiro:hasPermission name="dlszj:list">
            {id:'dlszj_list',text:'代理商资金管理',href:'/agentMoney/list',closeable:true},
            </shiro:hasPermission>
            <shiro:hasPermission name="agentflow:list">
            {id:'agent_flow_list',text:'代理商资金流水',href:'/agentFlow/list',closeable:true}
            </shiro:hasPermission>
          ]}
      ]
    },{
      id:'system',
      homePage:"admin_list",
      menu:[{
        text:'系统管理',
        items:[
          <shiro:hasPermission name="admin:list">
          {id:'admin_list',text:'用户管理',href:'/admin/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="role:list">
          {id:'role_list',text:'角色管理',href:'/role/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="permission:list">
          {id:'permission_list',text:'权限管理',href:'/permission/list',closeable:true},
                </shiro:hasPermission>
          <shiro:hasPermission name="log:list">
          {id:'log_list',text:'日志管理',href:'/log/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="hotkey:list">
          {id:'hot_key_list',text:'热词搜索',href:'/hotkey/list',closeable:true}
          </shiro:hasPermission>
        ]
      }]
    },{
      id:'wb',
      homePage:"product_category_list",
      menu:[{
        text:'外卖管理',
        items:[
          <shiro:hasPermission name="product:typelist">
          {id:'product_category_list',text:'商品分类管理',href:'/productType/list',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="product:wblist">
          {id:'wb_product_list',text:'外卖商品',href:'/product/wbList',closeable:true},
          </shiro:hasPermission>
          <shiro:hasPermission name="order:wblist">
          {id:'wb_order_list',text:'外卖订单',href:'/order/list?wb=1',closeable:true},
          </shiro:hasPermission>
        ]
      }]
    }
    ];
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