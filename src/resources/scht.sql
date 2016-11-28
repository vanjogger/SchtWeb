/*
Navicat MySQL Data Transfer

Source Server         : mysql_localhost
Source Server Version : 50533
Source Host           : 127.0.0.1:3306
Source Database       : scht

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2016-11-20 10:30:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_admin
-- ----------------------------
DROP TABLE IF EXISTS `base_admin`;
CREATE TABLE `base_admin` (
  `f_id` varchar(50) NOT NULL,
  `f_loginName` varchar(50) DEFAULT NULL,
  `f_password` varchar(50) DEFAULT NULL,
  `f_roleId` varchar(50) DEFAULT NULL,
  `f_last_login_time` bigint(20) DEFAULT NULL,
  `f_login_ip` varchar(20) DEFAULT NULL,
  `f_login_cnt` decimal(10,0) DEFAULT NULL,
  `f_status` varchar(20) DEFAULT NULL COMMENT 'NORMAL:正常 FROZEN:冻结',
  `f_roleName` varchar(50) DEFAULT NULL,
  `f_realName` varchar(50) DEFAULT NULL,
  `f_type` varchar(10) DEFAULT NULL,
  `f_mobile` varchar(20) DEFAULT NULL,
  `f_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_admin
-- ----------------------------
INSERT INTO `base_admin` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', '1', '1479608989592', '127.0.0.1', '204', 'NORMAL', '系统管理员', '', '1','','');

-- ----------------------------
-- Table structure for base_log
-- ----------------------------
DROP TABLE IF EXISTS `base_log`;
CREATE TABLE `base_log` (
  `f_id` varchar(50) NOT NULL DEFAULT '',
  `f_operateName` varchar(50) DEFAULT NULL,
  `f_operateContent` text,
  `f_logDate` bigint(20) DEFAULT NULL,
  `f_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_log
-- ----------------------------

-- ----------------------------
-- Table structure for base_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_permission`;
CREATE TABLE `base_permission` (
  `f_id` varchar(50) NOT NULL,
  `f_name` varchar(50) DEFAULT NULL,
  `f_url` varchar(500) DEFAULT NULL,
  `f_pId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_permission
-- ----------------------------
INSERT INTO `base_permission` VALUES ('064479683c2e466e96095b514939fc1a', 'nav4', 'nav4', '0');
INSERT INTO `base_permission` VALUES ('0fec761ec8c048ec87fcb54b740678e7', '推广商品管理', 'tgproduct:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('130beba943a648ba92aec15b45f0c965', '订单时限设置', 'orderLimitSet', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('160afc67720648308def4ff8d3b1b309', '连锁商家管理', 'chainshop:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('170b1b23907c4cf2aecbb585e5c7d280', '商品分类管理', 'shop:typelist', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('1a9d2c76469a47bd98babf400ea77786', '五折订单管理', 'wzorder:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('257dcc9a77d645ad90d9b5e66f1b1875', '管理员管理', 'admin:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('271d11d21ea345e48b85084ee04753d1', '订单管理', 'order:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('2e06fff7a40749cab5a8d8278fa4362d', '商家提现记录', 'shoptx:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('2e09d598c40748f294b7c16523b6e727', '公告分类管理', 'notice:typeList', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('336c77cce6044ce59f4c83d905c56de5', 'nav1', 'manage1', '0');
INSERT INTO `base_permission` VALUES ('3baf9c742b6b4c7eab4fb9ee8b5ed660', '商家银行卡管理', 'shopbank:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('3e2fd44482e14227a1e03f230009867e', '公告管理', 'notice:list', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('41f94b18cd2a4d86bb7ffb24d99ff7a3', '推广订单管理', 'tgorder:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('44e16ed5dcf34afb92443573c802ba5d', '商家资金管理', 'shopzj:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('507683305b804084a9887b3fabf24071', '支付信息管理', 'xfxx:list', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('5629bf7d91614a7ba3d5a70ef754347f', '推送设置', 'tssz:list', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('5bd6f1f02e4e4f3685daa53ec068f08d', '广告管理', 'ad:list', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('787ff9f62dfa41649faea66b2d147239', '商品信息管理', 'product:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('821b2b17b12d47109c3bddf248ac56d3', '商品信息管理', 'shopinfo:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('8308433f64fd49cd87a88ff035269674', '五折商品管理', 'wzproduct:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('86032227476949c1aea7a20e1c2669e7', 'nav2', 'nav2:list', '0');
INSERT INTO `base_permission` VALUES ('8aac0a4eb60d4ba69455b1c89c43afeb', '会员信息管理', 'member:list', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_permission` VALUES ('8d7bec9ecdf4443ca177c1c1f6c78e24', '系统设置', 'system', '0');
INSERT INTO `base_permission` VALUES ('8eb1c7558e1e4cba8cc0f35826287d14', '商家资金流水', 'shopflow:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('a623a3a0b73f4e81be8f039a6ce0eb66', '广告位管理', 'adplace:list', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('b86abcdac5264171967bda3d6b759647', '角色管理', 'role:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('d355cb7df9a440cca37e4cfd6a7945e2', 'nav6', 'nav6', '0');
INSERT INTO `base_permission` VALUES ('dde9c27e65594fe6b07bac11817db850', '日志管理', 'log:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('e8e69ce2d3e645269d9d1980fa40ece3', '商品分类管理', 'product:typelist', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('e987735f31b248eeafbdeefcd8b1aaef', 'nav5', 'nav5', '0');
INSERT INTO `base_permission` VALUES ('edb3c278b7794ec5b3bc29776c0b9207', '权限管理', 'permission:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('f1d6413f284f4971ae2b25270d1ee5fe', '提现费率管理', 'txfl:list', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('fdfd8dccc69242c093f51ee9601e2e88', 'nav3', 'nav3', '0');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `f_id` varchar(50) NOT NULL,
  `f_roleName` varchar(50) DEFAULT NULL,
  `f_status` varchar(10) DEFAULT NULL COMMENT '0:正常  1：删除',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', '系统管理员', '0');
INSERT INTO `base_role` VALUES ('8e80c8f8fbe44825b5bff3689e97e9b9', '代理商', '0');

-- ----------------------------
-- Table structure for base_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_role_permission`;
CREATE TABLE `base_role_permission` (
  `f_id` varchar(50) NOT NULL,
  `f_roleId` varchar(50) DEFAULT NULL,
  `f_permissionId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role_permission
-- ----------------------------
INSERT INTO `base_role_permission` VALUES ('0286cac6b14042419e66185eb423b419', '1', '8308433f64fd49cd87a88ff035269674');
INSERT INTO `base_role_permission` VALUES ('094681ffec77403dab2720e7f9252209', '1', 'a623a3a0b73f4e81be8f039a6ce0eb66');
INSERT INTO `base_role_permission` VALUES ('12b2bb2d8a61400fac0ce26d5eaab701', '1', 'f1d6413f284f4971ae2b25270d1ee5fe');
INSERT INTO `base_role_permission` VALUES ('1530b708f7c746e2ac875d49cc3d4374', '1', '787ff9f62dfa41649faea66b2d147239');
INSERT INTO `base_role_permission` VALUES ('1bd89963d92b420e9580749cfe85aa1d', '1', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_role_permission` VALUES ('28f1abe217404e4dad2e0d420b86b8ec', '1', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_role_permission` VALUES ('2bc873a1b43c47c5a275c5b76544f60d', '1', '3e2fd44482e14227a1e03f230009867e');
INSERT INTO `base_role_permission` VALUES ('39bc22a8e00a4dc1bf0ae22dcd9fd864', '1', '8eb1c7558e1e4cba8cc0f35826287d14');
INSERT INTO `base_role_permission` VALUES ('422e4dd5efec41f3bec870a6ac7b120f', '1', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_role_permission` VALUES ('5d66a1d4ff50402c80a2ce8c7b296c91', '1', '507683305b804084a9887b3fabf24071');
INSERT INTO `base_role_permission` VALUES ('6311dc969c464e45b4dc7fd3099ad6ef', '1', '257dcc9a77d645ad90d9b5e66f1b1875');
INSERT INTO `base_role_permission` VALUES ('6b54cb5fb2214c119272bf3597caaf90', '1', '160afc67720648308def4ff8d3b1b309');
INSERT INTO `base_role_permission` VALUES ('86198b7297fb49769cfd7a341b9615c4', '1', '0fec761ec8c048ec87fcb54b740678e7');
INSERT INTO `base_role_permission` VALUES ('90b264569df44fbca969bdd053c11332', '1', '5629bf7d91614a7ba3d5a70ef754347f');
INSERT INTO `base_role_permission` VALUES ('91bdb69194064962946bb090cd56250d', '1', '821b2b17b12d47109c3bddf248ac56d3');
INSERT INTO `base_role_permission` VALUES ('91cf43bf9b8b4076ad5506f28824dc7e', '1', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_role_permission` VALUES ('a05c4babd528469ea2d23f431dad5430', '1', '44e16ed5dcf34afb92443573c802ba5d');
INSERT INTO `base_role_permission` VALUES ('a29753cae6ce444389a071df6a165033', '1', '2e06fff7a40749cab5a8d8278fa4362d');
INSERT INTO `base_role_permission` VALUES ('a3dc70f587c640f7a2543376bac0a9ae', '1', 'dde9c27e65594fe6b07bac11817db850');
INSERT INTO `base_role_permission` VALUES ('a3f1f471de344f0ba8a5d99235f1b8c8', '1', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_role_permission` VALUES ('a85c4078c1e64edc82064d1517218559', '1', '1a9d2c76469a47bd98babf400ea77786');
INSERT INTO `base_role_permission` VALUES ('a89c609501b74ed1932530249c6e0c23', '1', '3baf9c742b6b4c7eab4fb9ee8b5ed660');
INSERT INTO `base_role_permission` VALUES ('b4fef4ce7119469fa6fa2b10b527e120', '1', '41f94b18cd2a4d86bb7ffb24d99ff7a3');
INSERT INTO `base_role_permission` VALUES ('b5ca02ce8af843e8b1c3375081ef4f41', '1', '170b1b23907c4cf2aecbb585e5c7d280');
INSERT INTO `base_role_permission` VALUES ('b9b1ac4c864b4f519cb1190cb5e78fe8', '1', 'edb3c278b7794ec5b3bc29776c0b9207');
INSERT INTO `base_role_permission` VALUES ('ba9d31bea6294fa6bb9280c811238840', '1', '130beba943a648ba92aec15b45f0c965');
INSERT INTO `base_role_permission` VALUES ('ca19faaf0049461ab408f86731f20098', '1', 'e8e69ce2d3e645269d9d1980fa40ece3');
INSERT INTO `base_role_permission` VALUES ('d4d65e21c3744140bf5ab67671178418', '1', '271d11d21ea345e48b85084ee04753d1');
INSERT INTO `base_role_permission` VALUES ('d53f2d8a35e84aa48b392946372d31b3', '1', '5bd6f1f02e4e4f3685daa53ec068f08d');
INSERT INTO `base_role_permission` VALUES ('e208925071ca4c7ead11d500759509f4', '1', '2e09d598c40748f294b7c16523b6e727');
INSERT INTO `base_role_permission` VALUES ('e255267308d14978b5ed0e9d7fc9616b', '1', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_role_permission` VALUES ('e4a83da5d93c4564b7af79a07659ed8b', '1', '8aac0a4eb60d4ba69455b1c89c43afeb');
INSERT INTO `base_role_permission` VALUES ('f0218d38be4342bdb65c98518d7b8d2f', '1', 'b86abcdac5264171967bda3d6b759647');
INSERT INTO `base_role_permission` VALUES ('fd1309269921458f9ece8f94e53b19c8', '1', 'd355cb7df9a440cca37e4cfd6a7945e2');

--- advertPlace
create table base_advert_place(
  f_id varchar(32) PRIMARY key,
  f_title VARCHAR (256) DEFAULT '',
  f_width int DEFAULT 0,
  f_height int DEFAULT 0,
  f_code VARCHAR (32) not null,
  f_create_time bigint DEFAULT 0,
  f_remark varchar(1000) DEFAULT ''
 );

 -- advert
   create table base_advert (
   f_id varchar(32) PRIMARY key,
   f_title varchar(256) DEFAULT '',
   f_url VARCHAR (512) DEFAULT '',
   f_image varchar(512) DEFAULT '',
   f_status varchar(32) not null,
   f_place_id varchar(32) not null,
   f_start_time bigint,
   f_end_time bigint,
   f_create_time bigint,
   f_sort int,
   f_remark VARCHAR (1000) DEFAULT ''
 );

 --notice type
 CREATE TABLE base_notice_type(
 f_id VARCHAR (32) PRIMARY key,
 f_name varchar(256) DEFAULT '',
 f_no VARCHAR (32) DEFAULT '',
 f_sort int DEFAULT 0,
 f_create_time bigint
 );
 -- notice
 CREATE  table base_notice(
  f_id VARCHAR (32) PRIMARY key,
  f_type_id varchar(32) not null,
  f_title varchar(256) DEFAULT '',
  f_content longtext ,
  f_create_time bigint,
  f_sort int,
  f_view_count int
 );

 -- push set
 create table base_push_set(
 f_id varchar(32) PRIMARY key,
 f_app_key varchar(32),
 f_master_secret varchar(64),
 f_status varchar(32)
 );

 -- alipay set
 create table base_ali_pay_set(
 f_id varchar(32) PRIMARY key,
 f_account varchar(256) DEFAULT '',
 f_app_key varchar(32) DEFAULT '',
 f_mch_no varchar(32) DEFAULT '',
 f_rsa_key VARCHAR (512) DEFAULT '',
 f_status varchar(32) DEFAULT ''
 );

 -- weixin pay
 CREATE table base_weixin_pay_set(
 f_id varchar(32) PRIMARY key,
 f_app_id varchar(32) DEFAULT '',
 f_mch_no varchar(32) DEFAULT '',
 f_secret varchar(32) DEFAULT '',
 f_pay_key varchar(32) DEFAULT '',
 f_status varchar(32) DEFAULT ''
 );
-- withdraw set
create table base_withdraw_set(
f_id varchar(32) PRIMARY  key,
f_agent_min varchar(32) DEFAULT '0',
f_agent_rate VARCHAR (32) DEFAULT '0',
f_shop_min varchar(32) DEFAULT '0',
f_shop_rate varchar(32) DEFAULT '0'
);
 --member
 create table base_member(
 f_id varchar(32) PRIMARY key,
 f_account varchar(256) not null,
  f_password  varchar(32) not null,
  f_nick varchar(256) DEFAULT '',
  f_head_icon varchar(512) DEFAULT '',
  f_address varchar(512) DEFAULT  '',
  f_telephone varchar(32) DEFAULT '',
  f_qq varchar(32) DEFAULT  '',
  f_weixin varchar(256) DEFAULT '',
  f_status varchar(32) not null,
  f_create_time bigint
 );
-- 代理商资金表
CREATE TABLE t_agent_money(
f_id varchar(32) primary key,
f_agent_id varchar(32) default '',
f_agent_name varchar(50) default '',
f_avail_amount varchar(20) default '',
f_frozen_amount varchar(20) default '',
f_total_amount varchar(20) default ''
);
-- 商家类型
CREATE TABLE t_shop_type(
  f_id varchar(32) primary key,
  f_name varchar(50) not null,
  f_key varchar(20) default '',
  f_sort int default 0,
  f_icon varchar(255) default '',
  f_status varchar(10) not null
);
--商家
CREATE TABLE t_shop(
  f_id varchar(32) primary key,
  f_name varchar(50) not null,
  f_account varchar(50) default '',
  f_password varchar(50) default '',
  f_shop_type_id varchar(32) default '',
  f_type varchar(20) default '',
  f_icon varchar(255) default '',
  f_status varchar(10) not null,
  f_link_name varchar(50) default '',
  f_link_mobile varchar(20) default '',
  f_link_address varchar(255) default '',
  f_agent_id varchar(32) default '',
  f_remark varchar(1000) default '',
  f_create_time bigint default 0,
  f_lng FLOAT  ,
  f_lnt FLOAT
);
-- 连锁商家
CREATE TABLE t_sub_shop(
  f_id varchar(32) primary key,
  f_shop_id varchar(32) default '',
  f_icon varchar(255) default '',
  f_name varchar(100) not null,
  f_sort int default 0,
  f_link_name varchar(50) default '',
  f_link_mobile varchar(50) default '',
  f_link_address varchar(255) default '',
  f_lng Float default '',
  f_lnt Float default '',
  f_status varchar(10) not null,
  f_remark varchar(1000) default ''
);
--商家资金
CREATE TABLE t_shop_money(
  f_id varchar(32) primary key,
  f_shop_id varchar(32) default '',
  f_shop_name varchar(100) default '',
  f_avail_amount varchar(20) default '',
  f_frozen_amount varchar(20) default '',
  f_total_amount varchar(20) default ''
);
---商家流水
CREATE TABLE t_shop_flow(
f_id varchar(32) primary key,
f_shop_id varchar(100) default '',
f_type varchar(100) default '',
f_amount varchar(100) default '',
f_after_amount varchar(100) default '',
f_create_time bigint default 0
);
--商家提现申请
CREATE TABLE t_shop_with_drawals(
f_id varchar(32) primary key,
f_shop_id varchar(32) default '',
f_status varchar(10) default '',
f_amount varchar(20) default '',
f_bank_name varchar(100) default '',
f_card_no varchar(100) default '',
f_card_name varchar(100) default '',
f_remark varchar(255) DEFAULT '',
f_create_time bigint default 0
);
-- product type

CREATE TABLE t_product_type(
f_id varchar(32) primary key,
f_name varchar(256) not null,
f_key varchar(100) default '',
f_sort int default 0,
f_icon varchar(512) default '',
f_status varchar(100) not null
);
-- product
CREATE TABLE t_product(
f_id varchar(32) primary key,
f_title varchar(512) default '',
f_product_type varchar(32) default '',
f_type_id varchar(32) default '',
f_description varchar(1000) default '',
f_price varchar(32) default '',
f_market_price varchar(32) default '',
f_agent_id varchar(32) default '',
f_images varchar(2000) default '',
f_stock int default 0,
f_sale_count int default 0,
f_virtual_count int default 0,
f_comment_count int default 0,
f_content longtext,
f_status varchar(32) not null,
f_create_time bigint default 0,
f_sort int default 0,
f_self varchar(2) default '',
f_shop_id varchar(32) default ''
);

-- orderLimitSet
create table t_order_limit_set(
  f_id VARCHAR (32) PRIMARY key,
  f_pay_limit int ,
  f_success_limit int
);