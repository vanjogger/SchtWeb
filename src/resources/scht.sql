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
INSERT INTO `base_permission` VALUES ('060b6cf9cd5b48ea92f63ad60cc18e5e', '五折订单管理', 'wzorder:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('064479683c2e466e96095b514939fc1a', 'nav4', 'nav4', '0');
INSERT INTO `base_permission` VALUES ('0b21d2bf553244c8b907c06d28f7a6d3', '消费码验证管理', 'xfm:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('0fec761ec8c048ec87fcb54b740678e7', '推广商品管理', 'tgproduct:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('160afc67720648308def4ff8d3b1b309', '连锁商家管理', 'chainshop:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('170b1b23907c4cf2aecbb585e5c7d280', '商品分类管理', 'shop:typelist', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('257dcc9a77d645ad90d9b5e66f1b1875', '管理员管理', 'admin:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('271d11d21ea345e48b85084ee04753d1', '订单管理', 'order:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('2e06fff7a40749cab5a8d8278fa4362d', '商家提现记录', 'shoptx:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('2e09d598c40748f294b7c16523b6e727', '公告分类管理', 'notice:typeList', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('3122cfefde8a499ead1b0a4b6eb9cbf6', '代理商资金管理', 'dlszj:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('336c77cce6044ce59f4c83d905c56de5', 'nav1', 'manage1', '0');
INSERT INTO `base_permission` VALUES ('3baf9c742b6b4c7eab4fb9ee8b5ed660', '商家银行卡管理', 'shopbank:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('3e2fd44482e14227a1e03f230009867e', '公告管理', 'notice:list', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('44e16ed5dcf34afb92443573c802ba5d', '商家资金管理', 'shopzj:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('4c7f4020f4fa4f29807763492b55ae66', '推送记录管理', 'tsjl:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('507683305b804084a9887b3fabf24071', '支付信息管理', 'xfxx:list', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('5629bf7d91614a7ba3d5a70ef754347f', '推送设置', 'tssz:list', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('5bd6f1f02e4e4f3685daa53ec068f08d', '广告管理', 'ad:list', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('787ff9f62dfa41649faea66b2d147239', '商品信息管理', 'product:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('821b2b17b12d47109c3bddf248ac56d3', '商品信息管理', 'shopinfo:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('8308433f64fd49cd87a88ff035269674', '五折商品管理', 'wzproduct:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('86032227476949c1aea7a20e1c2669e7', 'nav2', 'nav2:list', '0');
INSERT INTO `base_permission` VALUES ('8aac0a4eb60d4ba69455b1c89c43afeb', '会员信息管理', 'member:list', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_permission` VALUES ('8cb6306a24384b3cae101f40fc593eef', '推广订单管理', 'tgorder:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('8d7bec9ecdf4443ca177c1c1f6c78e24', '系统设置', 'system', '0');
INSERT INTO `base_permission` VALUES ('8eb1c7558e1e4cba8cc0f35826287d14', '商家资金流水', 'shopflow:list', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_permission` VALUES ('a623a3a0b73f4e81be8f039a6ce0eb66', '广告位管理', 'adplace:list', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_permission` VALUES ('b2684a33a9734895851b47cd679f06e6', '会员收货地址管理', 'memberAddress:list', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_permission` VALUES ('b3760112f7c24e33bf5a4d052bf8701d', '消费码管理', 'xfm:list', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_permission` VALUES ('b86abcdac5264171967bda3d6b759647', '角色管理', 'role:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('c6f1a71c08dd456f87f041cce7d8d1d3', '推广商品管理', 'tgproduct:list', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('d355cb7df9a440cca37e4cfd6a7945e2', 'nav6', 'nav6', '0');
INSERT INTO `base_permission` VALUES ('dde9c27e65594fe6b07bac11817db850', '日志管理', 'log:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('e8e69ce2d3e645269d9d1980fa40ece3', '商品分类管理', 'product:typelist', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_permission` VALUES ('e987735f31b248eeafbdeefcd8b1aaef', 'nav5', 'nav5', '0');
INSERT INTO `base_permission` VALUES ('edb3c278b7794ec5b3bc29776c0b9207', '权限管理', 'permission:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('f1d6413f284f4971ae2b25270d1ee5fe', '提现费率管理', 'txfl:list', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_permission` VALUES ('fdfd8dccc69242c093f51ee9601e2e88', 'nav3', 'nav3', '0');
INSERT INTO `base_permission` VALUES ('fe3a6ca04695478bb5f0619d6fd86564', '推广订单管理', 'tgorder:list', 'e987735f31b248eeafbdeefcd8b1aaef');

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
INSERT INTO `base_role_permission` VALUES ('0568a16b7d5a4627ad8f1e50995d4a61', '8e80c8f8fbe44825b5bff3689e97e9b9', '170b1b23907c4cf2aecbb585e5c7d280');
INSERT INTO `base_role_permission` VALUES ('0c13a6f41d284042aa885e4fac419bb7', '1', '8cb6306a24384b3cae101f40fc593eef');
INSERT INTO `base_role_permission` VALUES ('16411e885079460496130286e14e06c8', '8e80c8f8fbe44825b5bff3689e97e9b9', '5629bf7d91614a7ba3d5a70ef754347f');
INSERT INTO `base_role_permission` VALUES ('16433602516945c385e56ac38e63ff33', '1', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_role_permission` VALUES ('21e0464073e94fd68a4e75421a0da937', '8e80c8f8fbe44825b5bff3689e97e9b9', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_role_permission` VALUES ('2210dae516a14bd6bff03d3cc150764f', '8e80c8f8fbe44825b5bff3689e97e9b9', '3e2fd44482e14227a1e03f230009867e');
INSERT INTO `base_role_permission` VALUES ('226f1a053a3e41668240a7ef4a5d33a5', '8e80c8f8fbe44825b5bff3689e97e9b9', '2e06fff7a40749cab5a8d8278fa4362d');
INSERT INTO `base_role_permission` VALUES ('23ed5a0ee03e4fa597fb2e60fc7b75f0', '1', '160afc67720648308def4ff8d3b1b309');
INSERT INTO `base_role_permission` VALUES ('248e7bc8c3ee4110b7b133cb94fb483e', '1', 'fe3a6ca04695478bb5f0619d6fd86564');
INSERT INTO `base_role_permission` VALUES ('2dc92c1c68e74f239c2b5257f601b392', '1', 'edb3c278b7794ec5b3bc29776c0b9207');
INSERT INTO `base_role_permission` VALUES ('32571db64bf14376ae2fd0edb4bc68fc', '8e80c8f8fbe44825b5bff3689e97e9b9', '0fec761ec8c048ec87fcb54b740678e7');
INSERT INTO `base_role_permission` VALUES ('34968bda7cca4d1184ea2cf828ea2190', '1', '5bd6f1f02e4e4f3685daa53ec068f08d');
INSERT INTO `base_role_permission` VALUES ('3ea448662d584a8ca067bfed89e5e053', '8e80c8f8fbe44825b5bff3689e97e9b9', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_role_permission` VALUES ('40706fcbe07c4f26a5883b9c9fd4da49', '1', '8aac0a4eb60d4ba69455b1c89c43afeb');
INSERT INTO `base_role_permission` VALUES ('43b8a6f436694f0db7ca196872d69b31', '8e80c8f8fbe44825b5bff3689e97e9b9', '8308433f64fd49cd87a88ff035269674');
INSERT INTO `base_role_permission` VALUES ('47900062a25c4757b5c3a0a1cac2840f', '8e80c8f8fbe44825b5bff3689e97e9b9', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_role_permission` VALUES ('4bf900835dd04960a79ce6387d90d5ef', '8e80c8f8fbe44825b5bff3689e97e9b9', 'e8e69ce2d3e645269d9d1980fa40ece3');
INSERT INTO `base_role_permission` VALUES ('4c6d0a7a774a466cbaa7921177decf50', '1', 'b3760112f7c24e33bf5a4d052bf8701d');
INSERT INTO `base_role_permission` VALUES ('4e7435ed72094812bc2e790b0abb2c00', '1', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_role_permission` VALUES ('52fe9d7a461b4bca8251c2096f9d22bd', '1', '170b1b23907c4cf2aecbb585e5c7d280');
INSERT INTO `base_role_permission` VALUES ('53b4aef0a821440db901c1c0dc340ec3', '8e80c8f8fbe44825b5bff3689e97e9b9', '5bd6f1f02e4e4f3685daa53ec068f08d');
INSERT INTO `base_role_permission` VALUES ('576a8fa564964e9e86d7b660a7d0fa60', '8e80c8f8fbe44825b5bff3689e97e9b9', '3baf9c742b6b4c7eab4fb9ee8b5ed660');
INSERT INTO `base_role_permission` VALUES ('5a374d3be3424056b7f202bd4c9da7ca', '1', '3e2fd44482e14227a1e03f230009867e');
INSERT INTO `base_role_permission` VALUES ('5bdb35c8095a4aefb2cc8280e9f5306c', '1', '0b21d2bf553244c8b907c06d28f7a6d3');
INSERT INTO `base_role_permission` VALUES ('5ead4a7a78fb435caa12bcdfebdc8f02', '8e80c8f8fbe44825b5bff3689e97e9b9', '821b2b17b12d47109c3bddf248ac56d3');
INSERT INTO `base_role_permission` VALUES ('616b4e9cea5746df95e1179873b441a2', '8e80c8f8fbe44825b5bff3689e97e9b9', 'f1d6413f284f4971ae2b25270d1ee5fe');
INSERT INTO `base_role_permission` VALUES ('61f62c6be9d949e9ab291cb5a080cb1f', '1', '060b6cf9cd5b48ea92f63ad60cc18e5e');
INSERT INTO `base_role_permission` VALUES ('6754261b3be04e60acd994c8d77821a3', '1', 'b2684a33a9734895851b47cd679f06e6');
INSERT INTO `base_role_permission` VALUES ('6c4a0849ec1d4632804dad69420c0293', '1', '257dcc9a77d645ad90d9b5e66f1b1875');
INSERT INTO `base_role_permission` VALUES ('6e22ca09f3b5409798024c7bedcca783', '1', '0fec761ec8c048ec87fcb54b740678e7');
INSERT INTO `base_role_permission` VALUES ('6e5c3ac129fd48769f829cf80dcdd3ad', '1', '8308433f64fd49cd87a88ff035269674');
INSERT INTO `base_role_permission` VALUES ('71b59693ee744dd6bdd9a0e0220812ae', '8e80c8f8fbe44825b5bff3689e97e9b9', '44e16ed5dcf34afb92443573c802ba5d');
INSERT INTO `base_role_permission` VALUES ('76c2f9f75aec49938370eb7df0d668db', '1', '2e09d598c40748f294b7c16523b6e727');
INSERT INTO `base_role_permission` VALUES ('79205caeecd2416d9d07d86b7fbb47b4', '1', 'c6f1a71c08dd456f87f041cce7d8d1d3');
INSERT INTO `base_role_permission` VALUES ('7a382118040e4aa7b4148ddc89a75f5c', '8e80c8f8fbe44825b5bff3689e97e9b9', 'a623a3a0b73f4e81be8f039a6ce0eb66');
INSERT INTO `base_role_permission` VALUES ('7ee2f9d940c440cd89f8e2fa3ed1e6da', '1', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_role_permission` VALUES ('8511c2b5a5344b5591d8d10687107e2d', '1', '507683305b804084a9887b3fabf24071');
INSERT INTO `base_role_permission` VALUES ('888e56b986cd40da99b3e0e9ffb40cd9', '8e80c8f8fbe44825b5bff3689e97e9b9', '4c7f4020f4fa4f29807763492b55ae66');
INSERT INTO `base_role_permission` VALUES ('8a00060f64cd4b9b9d91af21ff94de79', '1', '8eb1c7558e1e4cba8cc0f35826287d14');
INSERT INTO `base_role_permission` VALUES ('9053644a60c94b6bb04a216222e5298f', '1', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_role_permission` VALUES ('95edac7c28d647fabda66be9b40ea11f', '8e80c8f8fbe44825b5bff3689e97e9b9', '507683305b804084a9887b3fabf24071');
INSERT INTO `base_role_permission` VALUES ('96c1bbe9f0b94246acc9558dfc71fcf2', '1', 'f1d6413f284f4971ae2b25270d1ee5fe');
INSERT INTO `base_role_permission` VALUES ('9adf104dd4f14e3f90578e433078037e', '8e80c8f8fbe44825b5bff3689e97e9b9', '271d11d21ea345e48b85084ee04753d1');
INSERT INTO `base_role_permission` VALUES ('9bc6633e503d41318dc659b68728ead9', '1', 'a623a3a0b73f4e81be8f039a6ce0eb66');
INSERT INTO `base_role_permission` VALUES ('a5bce341f0af416b87e35b82c4ab4550', '1', '821b2b17b12d47109c3bddf248ac56d3');
INSERT INTO `base_role_permission` VALUES ('a8c81a6aeb9042e48e4fd97311fd5fdb', '8e80c8f8fbe44825b5bff3689e97e9b9', 'c6f1a71c08dd456f87f041cce7d8d1d3');
INSERT INTO `base_role_permission` VALUES ('aa509d0a041149a496360b93cc793f16', '1', 'dde9c27e65594fe6b07bac11817db850');
INSERT INTO `base_role_permission` VALUES ('ac1448573d214cb8bebdc29f13f71696', '1', '44e16ed5dcf34afb92443573c802ba5d');
INSERT INTO `base_role_permission` VALUES ('af35ba9d60d14cab8f3919a944d801ef', '1', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_role_permission` VALUES ('b5ddcc3f3b1244e5b4cfc9f7ebae736e', '1', '3122cfefde8a499ead1b0a4b6eb9cbf6');
INSERT INTO `base_role_permission` VALUES ('b65ff6ae9ce349019b96c2a2d79a2bf6', '1', '271d11d21ea345e48b85084ee04753d1');
INSERT INTO `base_role_permission` VALUES ('bacdcae312054c1692da7d584bfa7a15', '1', '4c7f4020f4fa4f29807763492b55ae66');
INSERT INTO `base_role_permission` VALUES ('c066b0fd37374b418613020448322b41', '8e80c8f8fbe44825b5bff3689e97e9b9', '8aac0a4eb60d4ba69455b1c89c43afeb');
INSERT INTO `base_role_permission` VALUES ('c33cac44cb664866a4af021ee360ea5a', '1', 'e8e69ce2d3e645269d9d1980fa40ece3');
INSERT INTO `base_role_permission` VALUES ('c546cf4592ba428bb2c4a0e20253d827', '1', '2e06fff7a40749cab5a8d8278fa4362d');
INSERT INTO `base_role_permission` VALUES ('c7200b266bc34227932272988e47359b', '8e80c8f8fbe44825b5bff3689e97e9b9', '160afc67720648308def4ff8d3b1b309');
INSERT INTO `base_role_permission` VALUES ('c7fc68b631874091928768ccb24a8e30', '8e80c8f8fbe44825b5bff3689e97e9b9', 'b3760112f7c24e33bf5a4d052bf8701d');
INSERT INTO `base_role_permission` VALUES ('d0f2e38249054aaa83c30c7c3afa6234', '1', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_role_permission` VALUES ('d177733519bd4296a27861d7cce33447', '8e80c8f8fbe44825b5bff3689e97e9b9', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_role_permission` VALUES ('d20f5fa76f8d4ef7b5a67e7c505cd667', '8e80c8f8fbe44825b5bff3689e97e9b9', '8eb1c7558e1e4cba8cc0f35826287d14');
INSERT INTO `base_role_permission` VALUES ('d2b3ac8993bb4e56928c0090608cbe98', '1', '787ff9f62dfa41649faea66b2d147239');
INSERT INTO `base_role_permission` VALUES ('dda145e203a7441c90205724207de8b1', '8e80c8f8fbe44825b5bff3689e97e9b9', '2e09d598c40748f294b7c16523b6e727');
INSERT INTO `base_role_permission` VALUES ('df988ab32a02414bb56d2df17d6e1539', '1', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_role_permission` VALUES ('e479ede1f3f54a89b092698ec2810223', '8e80c8f8fbe44825b5bff3689e97e9b9', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_role_permission` VALUES ('eb1040f7e2ea477fa91f82a7ec4aea89', '1', '5629bf7d91614a7ba3d5a70ef754347f');
INSERT INTO `base_role_permission` VALUES ('f71f6e7672824c0986376655e3ed655c', '8e80c8f8fbe44825b5bff3689e97e9b9', '787ff9f62dfa41649faea66b2d147239');
INSERT INTO `base_role_permission` VALUES ('f808b4e271c5429aa7a892d6f831169f', '1', '3baf9c742b6b4c7eab4fb9ee8b5ed660');
INSERT INTO `base_role_permission` VALUES ('fb9ff04b20014df9b3a791ba81e415d5', '8e80c8f8fbe44825b5bff3689e97e9b9', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_role_permission` VALUES ('ff4ddacb14884031b899ab8f91605a5f', '1', 'b86abcdac5264171967bda3d6b759647');



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
CREATE TABLE t_push_set(
f_id varchar(32) primary key,
f_android_app_key varchar(100) default '',
f_android_master_secret varchar(100) default '',
f_ios_app_key varchar(100) default '',
f_ios_master_secret varchar(100) default '',
f_status varchar(100) not null
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
-- 商家银行卡
CREATE TABLE t_shop_bank(
  f_id varchar(32) primary key,
  f_shop_id varchar(32) default '',
  f_yhmc varchar(100) default '',
  f_khh varchar(100) default '',
  f_ckr varchar(100) default '',
  f_kh varchar(100) default ''
);
--会员收获地址
CREATE TABLE t_member_address(
  f_id varchar(32) primary key,
  f_member_id varchar(32) default '',
  f_name varchar(50) not null,
  f_mobile varchar(30) default '',
  f_address varchar(100) default '',
  f_details varchar(100) default '',
  f_is_default varchar(10) default ''
);


-- order
CREATE TABLE t_order(
f_id varchar(32) primary key,
f_no varchar(32) default '',
f_order_type varchar(100) default '',
f_member_id varchar(32) default '',
f_member_account varchar(100) default '',
f_shop_id varchar(32) default '',
f_agent_id varchar(32) default '',
f_create_time bigint default 0,
f_pay_time bigint default 0,
f_dispatch_time bigint default 0,
f_success_time bigint default 0,
f_over_time bigint default 0,
f_limit_time bigint default 0,
f_status varchar(100) not null,
f_total_money varchar(100) default '0',
f_remark varchar(1000) default '',
f_express varchar(32) default '',
f_code varchar(100) default '',
f_user_name varchar(100) default '',
f_address varchar(512) default '',
f_telephone varchar(100) default '',
f_pay_type varchar(32) default '',
f_express_name varchar(100) default '',
f_express_no varchar(100) default '',
f_member_assess varchar(2) default '',
f_shop_assess varchar(2) default ''
);

-- order product
CREATE TABLE t_order_product(
f_id varchar(32) primary key,
f_order_id varchar(32) default '',
f_product_id varchar(32) default '',
f_product_name varchar(512) default '',
f_product_image varchar(512) default '',
f_price varchar(100) default '',
f_amount int default 0,
f_money varchar(100) default '0'
);

--  message record
CREATE TABLE t_message_record(
f_id varchar(32) primary key,
f_telephone varchar(100) default '',
f_message varchar(1000) default '',
f_create_time bigint default 0,
f_status varchar(32) not null,
f_code varchar(32) default '',
f_err_msg varchar(100) default '',
f_ip varchar(32) default ''
);

-- order pay record

CREATE TABLE t_order_pay_record(
f_id varchar(32) primary key,
f_no varchar(32) default '',
f_order_id varchar(32) default '',
f_order_no varchar(32) default '',
f_money varchar(100) default '0',
f_pay_type varchar(32) default '',
f_status varchar(32) not null,
f_create_time bigint default 0,
f_pay_time bigint default 0,
f_trade_no varchar(100) default '',
f_buyer varchar(100) default ''
);

-- 修改t_shop_type
alter table t_shop_type add f_parent_id varchar(32);
alter table t_shop_type add f_is_product varchar(5);
