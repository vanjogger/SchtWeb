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
INSERT INTO `base_permission` VALUES ('bd3ebf40286f46a085c1f29983bb0f44', '商品评论管理', 'product:commentlist', 'fdfd8dccc69242c093f51ee9601e2e88');
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
INSERT INTO `base_role_permission` VALUES ('05f172ffdf5347ad95fdf2be758899c7', '1', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_role_permission` VALUES ('07625459fa9c477581245c226421619e', '1', '2e06fff7a40749cab5a8d8278fa4362d');
INSERT INTO `base_role_permission` VALUES ('0acf5586b31b47218d823b64b2ecd937', '1', '3122cfefde8a499ead1b0a4b6eb9cbf6');
INSERT INTO `base_role_permission` VALUES ('11509eb17f29427fa6ec0907a1fef8f4', '1', 'b3760112f7c24e33bf5a4d052bf8701d');
INSERT INTO `base_role_permission` VALUES ('11ce7bf85921443b88fd45a057269af6', '1', '0b21d2bf553244c8b907c06d28f7a6d3');
INSERT INTO `base_role_permission` VALUES ('14b4da208b0141b192782c2851d6c816', '1', '5629bf7d91614a7ba3d5a70ef754347f');
INSERT INTO `base_role_permission` VALUES ('16411e885079460496130286e14e06c8', '8e80c8f8fbe44825b5bff3689e97e9b9', '5629bf7d91614a7ba3d5a70ef754347f');
INSERT INTO `base_role_permission` VALUES ('18b30b28d3a64cafbef61b081c5a92ce', '1', '170b1b23907c4cf2aecbb585e5c7d280');
INSERT INTO `base_role_permission` VALUES ('19971acf55344567bee48abbe284f605', '1', '060b6cf9cd5b48ea92f63ad60cc18e5e');
INSERT INTO `base_role_permission` VALUES ('19ddb265a5c4429a98462eae31e6b823', '1', '5bd6f1f02e4e4f3685daa53ec068f08d');
INSERT INTO `base_role_permission` VALUES ('1ae8a0c6dc1842aba1d9e4048b3a639d', '1', '257dcc9a77d645ad90d9b5e66f1b1875');
INSERT INTO `base_role_permission` VALUES ('1e0e486ac746488498808161cb977a31', '1', '787ff9f62dfa41649faea66b2d147239');
INSERT INTO `base_role_permission` VALUES ('1eb5a0064f2c46949b80246713dc8c89', '1', 'c6f1a71c08dd456f87f041cce7d8d1d3');
INSERT INTO `base_role_permission` VALUES ('2054b009d16644b7941cf3185c99ba93', '1', 'e8e69ce2d3e645269d9d1980fa40ece3');
INSERT INTO `base_role_permission` VALUES ('216b53f040734a82ac03831713a2aaac', '1', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_role_permission` VALUES ('21e0464073e94fd68a4e75421a0da937', '8e80c8f8fbe44825b5bff3689e97e9b9', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_role_permission` VALUES ('2210dae516a14bd6bff03d3cc150764f', '8e80c8f8fbe44825b5bff3689e97e9b9', '3e2fd44482e14227a1e03f230009867e');
INSERT INTO `base_role_permission` VALUES ('226f1a053a3e41668240a7ef4a5d33a5', '8e80c8f8fbe44825b5bff3689e97e9b9', '2e06fff7a40749cab5a8d8278fa4362d');
INSERT INTO `base_role_permission` VALUES ('22975144eb99462aac8de75b77dcc850', '1', 'fe3a6ca04695478bb5f0619d6fd86564');
INSERT INTO `base_role_permission` VALUES ('27019ca6f0304d9b8123ab387d260368', '1', '3e2fd44482e14227a1e03f230009867e');
INSERT INTO `base_role_permission` VALUES ('32571db64bf14376ae2fd0edb4bc68fc', '8e80c8f8fbe44825b5bff3689e97e9b9', '0fec761ec8c048ec87fcb54b740678e7');
INSERT INTO `base_role_permission` VALUES ('3ea448662d584a8ca067bfed89e5e053', '8e80c8f8fbe44825b5bff3689e97e9b9', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_role_permission` VALUES ('41dc778b9270410a8f3cf8aeaabc15ff', '1', 'bd3ebf40286f46a085c1f29983bb0f44');
INSERT INTO `base_role_permission` VALUES ('43b8a6f436694f0db7ca196872d69b31', '8e80c8f8fbe44825b5bff3689e97e9b9', '8308433f64fd49cd87a88ff035269674');
INSERT INTO `base_role_permission` VALUES ('47900062a25c4757b5c3a0a1cac2840f', '8e80c8f8fbe44825b5bff3689e97e9b9', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_role_permission` VALUES ('48ca5886a76c4e05b7cb9dd38b7b6cb1', '1', '2e09d598c40748f294b7c16523b6e727');
INSERT INTO `base_role_permission` VALUES ('4bf900835dd04960a79ce6387d90d5ef', '8e80c8f8fbe44825b5bff3689e97e9b9', 'e8e69ce2d3e645269d9d1980fa40ece3');
INSERT INTO `base_role_permission` VALUES ('4c3ca47ea9ed4cc9b7170c5e773f560e', '1', '8308433f64fd49cd87a88ff035269674');
INSERT INTO `base_role_permission` VALUES ('53b4aef0a821440db901c1c0dc340ec3', '8e80c8f8fbe44825b5bff3689e97e9b9', '5bd6f1f02e4e4f3685daa53ec068f08d');
INSERT INTO `base_role_permission` VALUES ('572be31a945b490094ef873ad33bb1cb', '1', '8aac0a4eb60d4ba69455b1c89c43afeb');
INSERT INTO `base_role_permission` VALUES ('574ddad117de4ed3b8437df2113af937', '1', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_role_permission` VALUES ('576a8fa564964e9e86d7b660a7d0fa60', '8e80c8f8fbe44825b5bff3689e97e9b9', '3baf9c742b6b4c7eab4fb9ee8b5ed660');
INSERT INTO `base_role_permission` VALUES ('5ead4a7a78fb435caa12bcdfebdc8f02', '8e80c8f8fbe44825b5bff3689e97e9b9', '821b2b17b12d47109c3bddf248ac56d3');
INSERT INTO `base_role_permission` VALUES ('604f1409613f448fa789922f918fc0e1', '1', 'fdfd8dccc69242c093f51ee9601e2e88');
INSERT INTO `base_role_permission` VALUES ('616b4e9cea5746df95e1179873b441a2', '8e80c8f8fbe44825b5bff3689e97e9b9', 'f1d6413f284f4971ae2b25270d1ee5fe');
INSERT INTO `base_role_permission` VALUES ('63515d34aa2a4937bb67d3f8abcd188c', '1', 'edb3c278b7794ec5b3bc29776c0b9207');
INSERT INTO `base_role_permission` VALUES ('6a9629946a714644a01c7f6675b3d878', '1', '8cb6306a24384b3cae101f40fc593eef');
INSERT INTO `base_role_permission` VALUES ('6ef68b6f6bab414dbea1fc9474b58224', '1', '507683305b804084a9887b3fabf24071');
INSERT INTO `base_role_permission` VALUES ('715916e40ff84256adb86a163ea76808', '1', '271d11d21ea345e48b85084ee04753d1');
INSERT INTO `base_role_permission` VALUES ('71b0f9ef8f124566890bba5d6c2f87e1', '1', '336c77cce6044ce59f4c83d905c56de5');
INSERT INTO `base_role_permission` VALUES ('71b59693ee744dd6bdd9a0e0220812ae', '8e80c8f8fbe44825b5bff3689e97e9b9', '44e16ed5dcf34afb92443573c802ba5d');
INSERT INTO `base_role_permission` VALUES ('7a382118040e4aa7b4148ddc89a75f5c', '8e80c8f8fbe44825b5bff3689e97e9b9', 'a623a3a0b73f4e81be8f039a6ce0eb66');
INSERT INTO `base_role_permission` VALUES ('85fcf21d0650445a8d13ec1b0926d889', '1', '064479683c2e466e96095b514939fc1a');
INSERT INTO `base_role_permission` VALUES ('888e56b986cd40da99b3e0e9ffb40cd9', '8e80c8f8fbe44825b5bff3689e97e9b9', '4c7f4020f4fa4f29807763492b55ae66');
INSERT INTO `base_role_permission` VALUES ('907210a6f05c482c8aa7dbc968c8898c', '1', 'b2684a33a9734895851b47cd679f06e6');
INSERT INTO `base_role_permission` VALUES ('9158a723af2b4ebabf34d0f12cff969a', '1', 'a623a3a0b73f4e81be8f039a6ce0eb66');
INSERT INTO `base_role_permission` VALUES ('95edac7c28d647fabda66be9b40ea11f', '8e80c8f8fbe44825b5bff3689e97e9b9', '507683305b804084a9887b3fabf24071');
INSERT INTO `base_role_permission` VALUES ('9adf104dd4f14e3f90578e433078037e', '8e80c8f8fbe44825b5bff3689e97e9b9', '271d11d21ea345e48b85084ee04753d1');
INSERT INTO `base_role_permission` VALUES ('a78b378a02fc44aebaea6ba7735a3f08', '1', '4c7f4020f4fa4f29807763492b55ae66');
INSERT INTO `base_role_permission` VALUES ('a8c81a6aeb9042e48e4fd97311fd5fdb', '8e80c8f8fbe44825b5bff3689e97e9b9', 'c6f1a71c08dd456f87f041cce7d8d1d3');
INSERT INTO `base_role_permission` VALUES ('b42ace5adb494adfa29b144f2c4042ba', '1', '160afc67720648308def4ff8d3b1b309');
INSERT INTO `base_role_permission` VALUES ('baab142cafca4b68ba4791fb8bf795eb', '1', 'dde9c27e65594fe6b07bac11817db850');
INSERT INTO `base_role_permission` VALUES ('c066b0fd37374b418613020448322b41', '8e80c8f8fbe44825b5bff3689e97e9b9', '8aac0a4eb60d4ba69455b1c89c43afeb');
INSERT INTO `base_role_permission` VALUES ('c515625f6a774b9da84b7b3b54d90322', '1', '3baf9c742b6b4c7eab4fb9ee8b5ed660');
INSERT INTO `base_role_permission` VALUES ('c7200b266bc34227932272988e47359b', '8e80c8f8fbe44825b5bff3689e97e9b9', '160afc67720648308def4ff8d3b1b309');
INSERT INTO `base_role_permission` VALUES ('c784f5ee0afb416b978bc95f0b5f2b4e', '1', 'b86abcdac5264171967bda3d6b759647');
INSERT INTO `base_role_permission` VALUES ('c7fc68b631874091928768ccb24a8e30', '8e80c8f8fbe44825b5bff3689e97e9b9', 'b3760112f7c24e33bf5a4d052bf8701d');
INSERT INTO `base_role_permission` VALUES ('d177733519bd4296a27861d7cce33447', '8e80c8f8fbe44825b5bff3689e97e9b9', 'd355cb7df9a440cca37e4cfd6a7945e2');
INSERT INTO `base_role_permission` VALUES ('d20f5fa76f8d4ef7b5a67e7c505cd667', '8e80c8f8fbe44825b5bff3689e97e9b9', '8eb1c7558e1e4cba8cc0f35826287d14');
INSERT INTO `base_role_permission` VALUES ('d343181e73414ff499566ff17f8214b4', '1', 'e987735f31b248eeafbdeefcd8b1aaef');
INSERT INTO `base_role_permission` VALUES ('d6bed3d24b5a46b5bbf957fe1aeb1ec3', '1', '0fec761ec8c048ec87fcb54b740678e7');
INSERT INTO `base_role_permission` VALUES ('dc2cfa12796a441f95fed1b48ee1f44a', '1', '821b2b17b12d47109c3bddf248ac56d3');
INSERT INTO `base_role_permission` VALUES ('dda145e203a7441c90205724207de8b1', '8e80c8f8fbe44825b5bff3689e97e9b9', '2e09d598c40748f294b7c16523b6e727');
INSERT INTO `base_role_permission` VALUES ('e3a673020ff64184b0e06e0b42291f94', '1', 'f1d6413f284f4971ae2b25270d1ee5fe');
INSERT INTO `base_role_permission` VALUES ('e479ede1f3f54a89b092698ec2810223', '8e80c8f8fbe44825b5bff3689e97e9b9', '86032227476949c1aea7a20e1c2669e7');
INSERT INTO `base_role_permission` VALUES ('f666dc79692745ff8955e8636d150f03', '1', '44e16ed5dcf34afb92443573c802ba5d');
INSERT INTO `base_role_permission` VALUES ('f71f6e7672824c0986376655e3ed655c', '8e80c8f8fbe44825b5bff3689e97e9b9', '787ff9f62dfa41649faea66b2d147239');
INSERT INTO `base_role_permission` VALUES ('f8f9577a567d4df29cbdbca1ff66c4a0', '1', '8eb1c7558e1e4cba8cc0f35826287d14');
INSERT INTO `base_role_permission` VALUES ('fb9ff04b20014df9b3a791ba81e415d5', '8e80c8f8fbe44825b5bff3689e97e9b9', '064479683c2e466e96095b514939fc1a');



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
f_member_id varchar(32) default '',
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

--修改t_shop列明
alter table t_shop change f_lnt  f_lat float;
alter table t_shop add f_code varchar(20);
alter table t_shop add f_province_id varchar(50);
alter table t_shop add f_province_name varchar(50);
alter table t_shop add f_city_id varchar(50);
alter table t_shop add f_city_name varchar(50);
alter table t_shop add f_district_id varchar(50);
alter table t_shop add f_district_name varchar(50);

--商品评论表
CREATE TABLE t_product_comment(
f_id varchar(32) primary key,
f_product_id varchar(50) default '',
f_product_name varchar(100) default '',
f_order_id varchar(50) default '',
f_shop_id varchar(50) default '',
f_grade varchar(20) default '',
f_content text null ,
f_create_time bigint default 0,
f_status varchar(10) default '',
f_reply_id varchar(50) default '',
f_reply_content text null ,
f_reply_time bigint default 0
);

---  地区表
 CREATE TABLE t_nation (
  f_id  varchar(11) primary key,
  f_mc  varchar(40)  default '',
  f_lx  varchar(40)   default ''
);

INSERT INTO `t_nation` VALUES ('450126', '宾阳县', '3');
INSERT INTO `t_nation` VALUES ('370602', '芝罘区', '3');
INSERT INTO `t_nation` VALUES ('411024', '鄢陵县', '3');
INSERT INTO `t_nation` VALUES ('210500', '本溪市', '2');
INSERT INTO `t_nation` VALUES ('440403', '斗门区', '3');
INSERT INTO `t_nation` VALUES ('370205', '四方区', '3');
INSERT INTO `t_nation` VALUES ('440233', '新丰县', '3');
INSERT INTO `t_nation` VALUES ('450924', '兴业县', '3');
INSERT INTO `t_nation` VALUES ('520101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542330', '仁布县', '3');
INSERT INTO `t_nation` VALUES ('652826', '焉耆回族自治县', '3');
INSERT INTO `t_nation` VALUES ('430700', '常德市', '2');
INSERT INTO `t_nation` VALUES ('542335', '吉隆县', '3');
INSERT INTO `t_nation` VALUES ('522423', '黔西县', '3');
INSERT INTO `t_nation` VALUES ('370406', '山亭区', '3');
INSERT INTO `t_nation` VALUES ('330327', '苍南县', '3');
INSERT INTO `t_nation` VALUES ('130203', '路北区', '3');
INSERT INTO `t_nation` VALUES ('230623', '林甸县', '3');
INSERT INTO `t_nation` VALUES ('445202', '榕城区', '3');
INSERT INTO `t_nation` VALUES ('632626', '玛多县', '3');
INSERT INTO `t_nation` VALUES ('610622', '延川县', '3');
INSERT INTO `t_nation` VALUES ('421302', '曾都区', '3');
INSERT INTO `t_nation` VALUES ('152527', '太仆寺旗', '3');
INSERT INTO `t_nation` VALUES ('621027', '镇原县', '3');
INSERT INTO `t_nation` VALUES ('231084', '宁安市', '3');
INSERT INTO `t_nation` VALUES ('469026', '屯昌县', '3');
INSERT INTO `t_nation` VALUES ('430527', '绥宁县', '3');
INSERT INTO `t_nation` VALUES ('510108', '成华区', '3');
INSERT INTO `t_nation` VALUES ('341302', '埇桥区', '3');
INSERT INTO `t_nation` VALUES ('210282', '普兰店市', '3');
INSERT INTO `t_nation` VALUES ('231085', '穆棱市', '3');
INSERT INTO `t_nation` VALUES ('500236', '奉节县', '3');
INSERT INTO `t_nation` VALUES ('513335', '巴塘县', '3');
INSERT INTO `t_nation` VALUES ('533321', '泸水县', '3');
INSERT INTO `t_nation` VALUES ('410329', '伊川县', '3');
INSERT INTO `t_nation` VALUES ('421182', '武穴市', '3');
INSERT INTO `t_nation` VALUES ('440802', '赤坎区', '3');
INSERT INTO `t_nation` VALUES ('411400', '商丘市', '2');
INSERT INTO `t_nation` VALUES ('410600', '鹤壁市', '2');
INSERT INTO `t_nation` VALUES ('542500', '阿里地区', '2');
INSERT INTO `t_nation` VALUES ('530828', '澜沧拉祜族自治县', '3');
INSERT INTO `t_nation` VALUES ('150785', '根河市', '3');
INSERT INTO `t_nation` VALUES ('620200', '嘉峪关市', '2');
INSERT INTO `t_nation` VALUES ('652823', '尉犁县', '3');
INSERT INTO `t_nation` VALUES ('130107', '井陉矿区', '3');
INSERT INTO `t_nation` VALUES ('330400', '嘉兴市', '2');
INSERT INTO `t_nation` VALUES ('230281', '讷河市', '3');
INSERT INTO `t_nation` VALUES ('440804', '坡头区', '3');
INSERT INTO `t_nation` VALUES ('140400', '长治市', '2');
INSERT INTO `t_nation` VALUES ('341203', '颍东区', '3');
INSERT INTO `t_nation` VALUES ('120221', '宁河县', '3');
INSERT INTO `t_nation` VALUES ('220801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('441721', '阳西县', '3');
INSERT INTO `t_nation` VALUES ('320922', '滨海县', '3');
INSERT INTO `t_nation` VALUES ('320405', '戚墅堰区', '3');
INSERT INTO `t_nation` VALUES ('360802', '吉州区', '3');
INSERT INTO `t_nation` VALUES ('350102', '鼓楼区', '3');
INSERT INTO `t_nation` VALUES ('210604', '振安区', '3');
INSERT INTO `t_nation` VALUES ('231002', '东安区', '3');
INSERT INTO `t_nation` VALUES ('420204', '下陆区', '3');
INSERT INTO `t_nation` VALUES ('431229', '靖州苗族侗族自治县', '3');
INSERT INTO `t_nation` VALUES ('420506', '夷陵区', '3');
INSERT INTO `t_nation` VALUES ('330302', '鹿城区', '3');
INSERT INTO `t_nation` VALUES ('653223', '皮山县', '3');
INSERT INTO `t_nation` VALUES ('341100', '滁州市', '2');
INSERT INTO `t_nation` VALUES ('131022', '固安县', '3');
INSERT INTO `t_nation` VALUES ('620825', '庄浪县', '3');
INSERT INTO `t_nation` VALUES ('530181', '安宁市', '3');
INSERT INTO `t_nation` VALUES ('450324', '全州县', '3');
INSERT INTO `t_nation` VALUES ('542527', '措勤县', '3');
INSERT INTO `t_nation` VALUES ('610323', '岐山县', '3');
INSERT INTO `t_nation` VALUES ('430621', '岳阳县', '3');
INSERT INTO `t_nation` VALUES ('510901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610304', '陈仓区', '3');
INSERT INTO `t_nation` VALUES ('469033', '乐东黎族自治县', '3');
INSERT INTO `t_nation` VALUES ('532926', '南涧彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('340300', '蚌埠市', '2');
INSERT INTO `t_nation` VALUES ('532928', '永平县', '3');
INSERT INTO `t_nation` VALUES ('513223', '茂县', '3');
INSERT INTO `t_nation` VALUES ('441203', '鼎湖区', '3');
INSERT INTO `t_nation` VALUES ('622922', '康乐县', '3');
INSERT INTO `t_nation` VALUES ('350121', '闽侯县', '3');
INSERT INTO `t_nation` VALUES ('211302', '双塔区', '3');
INSERT INTO `t_nation` VALUES ('350105', '马尾区', '3');
INSERT INTO `t_nation` VALUES ('130902', '新华区', '3');
INSERT INTO `t_nation` VALUES ('371502', '东昌府区', '3');
INSERT INTO `t_nation` VALUES ('150222', '固阳县', '3');
INSERT INTO `t_nation` VALUES ('420200', '黄石市', '2');
INSERT INTO `t_nation` VALUES ('632126', '互助土族自治县', '3');
INSERT INTO `t_nation` VALUES ('513434', '越西县', '3');
INSERT INTO `t_nation` VALUES ('330108', '滨江区', '3');
INSERT INTO `t_nation` VALUES ('511701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230715', '红星区', '3');
INSERT INTO `t_nation` VALUES ('350481', '永安市', '3');
INSERT INTO `t_nation` VALUES ('411523', '新县', '3');
INSERT INTO `t_nation` VALUES ('440901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542225', '琼结县', '3');
INSERT INTO `t_nation` VALUES ('331125', '云和县', '3');
INSERT INTO `t_nation` VALUES ('350182', '长乐市', '3');
INSERT INTO `t_nation` VALUES ('350122', '连江县', '3');
INSERT INTO `t_nation` VALUES ('310101', '黄浦区', '3');
INSERT INTO `t_nation` VALUES ('540124', '曲水县', '3');
INSERT INTO `t_nation` VALUES ('320103', '白下区', '3');
INSERT INTO `t_nation` VALUES ('140981', '原平市', '3');
INSERT INTO `t_nation` VALUES ('620121', '永登县', '3');
INSERT INTO `t_nation` VALUES ('211223', '西丰县', '3');
INSERT INTO `t_nation` VALUES ('420682', '老河口市', '3');
INSERT INTO `t_nation` VALUES ('211005', '弓长岭区', '3');
INSERT INTO `t_nation` VALUES ('420625', '谷城县', '3');
INSERT INTO `t_nation` VALUES ('630123', '湟源县', '3');
INSERT INTO `t_nation` VALUES ('210411', '顺城区', '3');
INSERT INTO `t_nation` VALUES ('321181', '丹阳市', '3');
INSERT INTO `t_nation` VALUES ('310113', '宝山区', '3');
INSERT INTO `t_nation` VALUES ('511523', '江安县', '3');
INSERT INTO `t_nation` VALUES ('431103', '冷水滩区', '3');
INSERT INTO `t_nation` VALUES ('340121', '长丰县', '3');
INSERT INTO `t_nation` VALUES ('620826', '静宁县', '3');
INSERT INTO `t_nation` VALUES ('431028', '安仁县', '3');
INSERT INTO `t_nation` VALUES ('130633', '易县', '3');
INSERT INTO `t_nation` VALUES ('370203', '市北区', '3');
INSERT INTO `t_nation` VALUES ('331126', '庆元县', '3');
INSERT INTO `t_nation` VALUES ('610824', '靖边县', '3');
INSERT INTO `t_nation` VALUES ('411302', '宛城区', '3');
INSERT INTO `t_nation` VALUES ('513429', '布拖县', '3');
INSERT INTO `t_nation` VALUES ('370682', '莱阳市', '3');
INSERT INTO `t_nation` VALUES ('360428', '都昌县', '3');
INSERT INTO `t_nation` VALUES ('431128', '新田县', '3');
INSERT INTO `t_nation` VALUES ('422802', '利川市', '3');
INSERT INTO `t_nation` VALUES ('430801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360824', '新干县', '3');
INSERT INTO `t_nation` VALUES ('431300', '娄底市', '2');
INSERT INTO `t_nation` VALUES ('430201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440600', '佛山市', '2');
INSERT INTO `t_nation` VALUES ('654301', '阿勒泰市', '3');
INSERT INTO `t_nation` VALUES ('220502', '东昌区', '3');
INSERT INTO `t_nation` VALUES ('450703', '钦北区', '3');
INSERT INTO `t_nation` VALUES ('530821', '宁洱哈尼族彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('130104', '桥西区', '3');
INSERT INTO `t_nation` VALUES ('511324', '仪陇县', '3');
INSERT INTO `t_nation` VALUES ('131181', '冀州市', '3');
INSERT INTO `t_nation` VALUES ('530700', '丽江市', '2');
INSERT INTO `t_nation` VALUES ('152529', '正镶白旗', '3');
INSERT INTO `t_nation` VALUES ('440606', '顺德区', '3');
INSERT INTO `t_nation` VALUES ('520322', '桐梓县', '3');
INSERT INTO `t_nation` VALUES ('530630', '水富县', '3');
INSERT INTO `t_nation` VALUES ('430100', '长沙市', '2');
INSERT INTO `t_nation` VALUES ('210104', '大东区', '3');
INSERT INTO `t_nation` VALUES ('411623', '商水县', '3');
INSERT INTO `t_nation` VALUES ('410481', '舞钢市', '3');
INSERT INTO `t_nation` VALUES ('320101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411503', '平桥区', '3');
INSERT INTO `t_nation` VALUES ('652824', '若羌县', '3');
INSERT INTO `t_nation` VALUES ('130300', '秦皇岛市', '2');
INSERT INTO `t_nation` VALUES ('330109', '萧山区', '3');
INSERT INTO `t_nation` VALUES ('610926', '平利县', '3');
INSERT INTO `t_nation` VALUES ('640425', '彭阳县', '3');
INSERT INTO `t_nation` VALUES ('321111', '润州区', '3');
INSERT INTO `t_nation` VALUES ('150800', '巴彦淖尔市', '2');
INSERT INTO `t_nation` VALUES ('210112', '东陵区', '3');
INSERT INTO `t_nation` VALUES ('653227', '民丰县', '3');
INSERT INTO `t_nation` VALUES ('530501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140200', '大同市', '2');
INSERT INTO `t_nation` VALUES ('371083', '乳山市', '3');
INSERT INTO `t_nation` VALUES ('320382', '邳州市', '3');
INSERT INTO `t_nation` VALUES ('430600', '岳阳市', '2');
INSERT INTO `t_nation` VALUES ('340103', '庐阳区', '3');
INSERT INTO `t_nation` VALUES ('511724', '大竹县', '3');
INSERT INTO `t_nation` VALUES ('330683', '嵊州市', '3');
INSERT INTO `t_nation` VALUES ('320111', '浦口区', '3');
INSERT INTO `t_nation` VALUES ('210702', '古塔区', '3');
INSERT INTO `t_nation` VALUES ('430400', '衡阳市', '2');
INSERT INTO `t_nation` VALUES ('530381', '宣威市', '3');
INSERT INTO `t_nation` VALUES ('230300', '鸡西市', '2');
INSERT INTO `t_nation` VALUES ('511423', '洪雅县', '3');
INSERT INTO `t_nation` VALUES ('360829', '安福县', '3');
INSERT INTO `t_nation` VALUES ('150202', '东河区', '3');
INSERT INTO `t_nation` VALUES ('410804', '马村区', '3');
INSERT INTO `t_nation` VALUES ('500222', '綦江县', '3');
INSERT INTO `t_nation` VALUES ('522600', '黔东南苗族侗族自治州', '2');
INSERT INTO `t_nation` VALUES ('510100', '成都市', '2');
INSERT INTO `t_nation` VALUES ('610429', '旬邑县', '3');
INSERT INTO `t_nation` VALUES ('150925', '凉城县', '3');
INSERT INTO `t_nation` VALUES ('350825', '连城县', '3');
INSERT INTO `t_nation` VALUES ('500101', '万州区', '3');
INSERT INTO `t_nation` VALUES ('469027', '澄迈县', '3');
INSERT INTO `t_nation` VALUES ('420981', '应城市', '3');
INSERT INTO `t_nation` VALUES ('370903', '岱岳区', '3');
INSERT INTO `t_nation` VALUES ('520102', '南明区', '3');
INSERT INTO `t_nation` VALUES ('440923', '电白县', '3');
INSERT INTO `t_nation` VALUES ('110115', '大兴区', '3');
INSERT INTO `t_nation` VALUES ('610829', '吴堡县', '3');
INSERT INTO `t_nation` VALUES ('140123', '娄烦县', '3');
INSERT INTO `t_nation` VALUES ('450721', '灵山县', '3');
INSERT INTO `t_nation` VALUES ('442101', '市区', '3');
INSERT INTO `t_nation` VALUES ('430821', '慈利县', '3');
INSERT INTO `t_nation` VALUES ('520121', '开阳县', '3');
INSERT INTO `t_nation` VALUES ('500200', '县', '2');
INSERT INTO `t_nation` VALUES ('620111', '红古区', '3');
INSERT INTO `t_nation` VALUES ('450107', '西乡塘区', '3');
INSERT INTO `t_nation` VALUES ('140428', '长子县', '3');
INSERT INTO `t_nation` VALUES ('350400', '三明市', '2');
INSERT INTO `t_nation` VALUES ('440205', '曲江区', '3');
INSERT INTO `t_nation` VALUES ('520200', '六盘水市', '2');
INSERT INTO `t_nation` VALUES ('140828', '夏县', '3');
INSERT INTO `t_nation` VALUES ('430681', '汨罗市', '3');
INSERT INTO `t_nation` VALUES ('320826', '涟水县', '3');
INSERT INTO `t_nation` VALUES ('320801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320106', '鼓楼区', '3');
INSERT INTO `t_nation` VALUES ('210803', '西市区', '3');
INSERT INTO `t_nation` VALUES ('621121', '通渭县', '3');
INSERT INTO `t_nation` VALUES ('341623', '利辛县', '3');
INSERT INTO `t_nation` VALUES ('511621', '岳池县', '3');
INSERT INTO `t_nation` VALUES ('130227', '迁西县', '3');
INSERT INTO `t_nation` VALUES ('450521', '合浦县', '3');
INSERT INTO `t_nation` VALUES ('410603', '山城区', '3');
INSERT INTO `t_nation` VALUES ('610925', '岚皋县', '3');
INSERT INTO `t_nation` VALUES ('441283', '高要市', '3');
INSERT INTO `t_nation` VALUES ('522201', '铜仁市', '3');
INSERT INTO `t_nation` VALUES ('331121', '青田县', '3');
INSERT INTO `t_nation` VALUES ('510723', '盐亭县', '3');
INSERT INTO `t_nation` VALUES ('532329', '武定县', '3');
INSERT INTO `t_nation` VALUES ('310105', '长宁区', '3');
INSERT INTO `t_nation` VALUES ('120103', '河西区', '3');
INSERT INTO `t_nation` VALUES ('410523', '汤阴县', '3');
INSERT INTO `t_nation` VALUES ('610600', '延安市', '2');
INSERT INTO `t_nation` VALUES ('510700', '绵阳市', '2');
INSERT INTO `t_nation` VALUES ('140429', '武乡县', '3');
INSERT INTO `t_nation` VALUES ('370686', '栖霞市', '3');
INSERT INTO `t_nation` VALUES ('131126', '故城县', '3');
INSERT INTO `t_nation` VALUES ('441702', '江城区', '3');
INSERT INTO `t_nation` VALUES ('230126', '巴彦县', '3');
INSERT INTO `t_nation` VALUES ('410184', '新郑市', '3');
INSERT INTO `t_nation` VALUES ('510106', '金牛区', '3');
INSERT INTO `t_nation` VALUES ('440513', '潮阳区', '3');
INSERT INTO `t_nation` VALUES ('540127', '墨竹工卡县', '3');
INSERT INTO `t_nation` VALUES ('341021', '歙县', '3');
INSERT INTO `t_nation` VALUES ('310103', '卢湾区', '3');
INSERT INTO `t_nation` VALUES ('441301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('520111', '花溪区', '3');
INSERT INTO `t_nation` VALUES ('310118', '青浦区', '3');
INSERT INTO `t_nation` VALUES ('540125', '堆龙德庆县', '3');
INSERT INTO `t_nation` VALUES ('411081', '禹州市', '3');
INSERT INTO `t_nation` VALUES ('210503', '溪湖区', '3');
INSERT INTO `t_nation` VALUES ('330381', '瑞安市', '3');
INSERT INTO `t_nation` VALUES ('361128', '鄱阳县', '3');
INSERT INTO `t_nation` VALUES ('140729', '灵石县', '3');
INSERT INTO `t_nation` VALUES ('140824', '稷山县', '3');
INSERT INTO `t_nation` VALUES ('360921', '奉新县', '3');
INSERT INTO `t_nation` VALUES ('500234', '开县', '3');
INSERT INTO `t_nation` VALUES ('410701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330382', '乐清市', '3');
INSERT INTO `t_nation` VALUES ('130821', '承德县', '3');
INSERT INTO `t_nation` VALUES ('371728', '东明县', '3');
INSERT INTO `t_nation` VALUES ('152201', '乌兰浩特市', '3');
INSERT INTO `t_nation` VALUES ('341602', '谯城区', '3');
INSERT INTO `t_nation` VALUES ('130701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('331081', '温岭市', '3');
INSERT INTO `t_nation` VALUES ('140481', '潞城市', '3');
INSERT INTO `t_nation` VALUES ('130125', '行唐县', '3');
INSERT INTO `t_nation` VALUES ('610401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370829', '嘉祥县', '3');
INSERT INTO `t_nation` VALUES ('533422', '德钦县', '3');
INSERT INTO `t_nation` VALUES ('510114', '新都区', '3');
INSERT INTO `t_nation` VALUES ('210423', '清原满族自治县', '3');
INSERT INTO `t_nation` VALUES ('330200', '宁波市', '2');
INSERT INTO `t_nation` VALUES ('610112', '未央区', '3');
INSERT INTO `t_nation` VALUES ('440883', '吴川市', '3');
INSERT INTO `t_nation` VALUES ('450124', '马山县', '3');
INSERT INTO `t_nation` VALUES ('120109', '大港区', '3');
INSERT INTO `t_nation` VALUES ('310107', '普陀区', '3');
INSERT INTO `t_nation` VALUES ('150200', '包头市', '2');
INSERT INTO `t_nation` VALUES ('331102', '莲都区', '3');
INSERT INTO `t_nation` VALUES ('150204', '青山区', '3');
INSERT INTO `t_nation` VALUES ('659004', '五家渠市', '3');
INSERT INTO `t_nation` VALUES ('632802', '德令哈市', '3');
INSERT INTO `t_nation` VALUES ('320831', '金湖县', '3');
INSERT INTO `t_nation` VALUES ('440981', '高州市', '3');
INSERT INTO `t_nation` VALUES ('652101', '吐鲁番市', '3');
INSERT INTO `t_nation` VALUES ('510824', '苍溪县', '3');
INSERT INTO `t_nation` VALUES ('320505', '虎丘区', '3');
INSERT INTO `t_nation` VALUES ('653131', '塔什库尔干塔吉克自治县', '3');
INSERT INTO `t_nation` VALUES ('610403', '杨凌区', '3');
INSERT INTO `t_nation` VALUES ('320721', '赣榆县', '3');
INSERT INTO `t_nation` VALUES ('623022', '卓尼县', '3');
INSERT INTO `t_nation` VALUES ('430524', '隆回县', '3');
INSERT INTO `t_nation` VALUES ('370214', '城阳区', '3');
INSERT INTO `t_nation` VALUES ('210105', '皇姑区', '3');
INSERT INTO `t_nation` VALUES ('120101', '和平区', '3');
INSERT INTO `t_nation` VALUES ('361123', '玉山县', '3');
INSERT INTO `t_nation` VALUES ('130101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('231004', '爱民区', '3');
INSERT INTO `t_nation` VALUES ('445321', '新兴县', '3');
INSERT INTO `t_nation` VALUES ('621122', '陇西县', '3');
INSERT INTO `t_nation` VALUES ('370983', '肥城市', '3');
INSERT INTO `t_nation` VALUES ('140601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('522700', '黔南布依族苗族自治州', '2');
INSERT INTO `t_nation` VALUES ('140926', '静乐县', '3');
INSERT INTO `t_nation` VALUES ('231224', '庆安县', '3');
INSERT INTO `t_nation` VALUES ('341103', '南谯区', '3');
INSERT INTO `t_nation` VALUES ('130533', '威县', '3');
INSERT INTO `t_nation` VALUES ('371001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('350627', '南靖县', '3');
INSERT INTO `t_nation` VALUES ('370285', '莱西市', '3');
INSERT INTO `t_nation` VALUES ('150700', '呼伦贝尔市', '2');
INSERT INTO `t_nation` VALUES ('310120', '奉贤区', '3');
INSERT INTO `t_nation` VALUES ('370104', '槐荫区', '3');
INSERT INTO `t_nation` VALUES ('513227', '小金县', '3');
INSERT INTO `t_nation` VALUES ('210421', '抚顺县', '3');
INSERT INTO `t_nation` VALUES ('231083', '海林市', '3');
INSERT INTO `t_nation` VALUES ('320324', '睢宁县', '3');
INSERT INTO `t_nation` VALUES ('422825', '宣恩县', '3');
INSERT INTO `t_nation` VALUES ('341802', '宣州区', '3');
INSERT INTO `t_nation` VALUES ('530924', '镇康县', '3');
INSERT INTO `t_nation` VALUES ('420702', '梁子湖区', '3');
INSERT INTO `t_nation` VALUES ('321101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542427', '索县', '3');
INSERT INTO `t_nation` VALUES ('610125', '户县', '3');
INSERT INTO `t_nation` VALUES ('140932', '偏关县', '3');
INSERT INTO `t_nation` VALUES ('422823', '巴东县', '3');
INSERT INTO `t_nation` VALUES ('220183', '德惠市', '3');
INSERT INTO `t_nation` VALUES ('441226', '德庆县', '3');
INSERT INTO `t_nation` VALUES ('230900', '七台河市', '2');
INSERT INTO `t_nation` VALUES ('410802', '解放区', '3');
INSERT INTO `t_nation` VALUES ('341881', '宁国市', '3');
INSERT INTO `t_nation` VALUES ('340101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220582', '集安市', '3');
INSERT INTO `t_nation` VALUES ('650104', '新市区', '3');
INSERT INTO `t_nation` VALUES ('150826', '杭锦后旗', '3');
INSERT INTO `t_nation` VALUES ('130324', '卢龙县', '3');
INSERT INTO `t_nation` VALUES ('632324', '河南蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('370685', '招远市', '3');
INSERT INTO `t_nation` VALUES ('341801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370703', '寒亭区', '3');
INSERT INTO `t_nation` VALUES ('130730', '怀来县', '3');
INSERT INTO `t_nation` VALUES ('440103', '荔湾区', '3');
INSERT INTO `t_nation` VALUES ('410811', '山阳区', '3');
INSERT INTO `t_nation` VALUES ('450108', '良庆区', '3');
INSERT INTO `t_nation` VALUES ('350601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230822', '桦南县', '3');
INSERT INTO `t_nation` VALUES ('510504', '龙马潭区', '3');
INSERT INTO `t_nation` VALUES ('231123', '逊克县', '3');
INSERT INTO `t_nation` VALUES ('652701', '博乐市', '3');
INSERT INTO `t_nation` VALUES ('230400', '鹤岗市', '2');
INSERT INTO `t_nation` VALUES ('510626', '罗江县', '3');
INSERT INTO `t_nation` VALUES ('211382', '凌源市', '3');
INSERT INTO `t_nation` VALUES ('341422', '无为县（变更）', '3');
INSERT INTO `t_nation` VALUES ('141021', '曲沃县', '3');
INSERT INTO `t_nation` VALUES ('511381', '阆中市', '3');
INSERT INTO `t_nation` VALUES ('370811', '任城区', '3');
INSERT INTO `t_nation` VALUES ('340122', '肥东县', '3');
INSERT INTO `t_nation` VALUES ('321088', '江都市', '3');
INSERT INTO `t_nation` VALUES ('211200', '铁岭市', '2');
INSERT INTO `t_nation` VALUES ('540122', '当雄县', '3');
INSERT INTO `t_nation` VALUES ('130581', '南宫市', '3');
INSERT INTO `t_nation` VALUES ('140202', '城区', '3');
INSERT INTO `t_nation` VALUES ('630122', '湟中县', '3');
INSERT INTO `t_nation` VALUES ('450404', '蝶山区', '3');
INSERT INTO `t_nation` VALUES ('500229', '城口县', '3');
INSERT INTO `t_nation` VALUES ('220201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620981', '玉门市', '3');
INSERT INTO `t_nation` VALUES ('451302', '兴宾区', '3');
INSERT INTO `t_nation` VALUES ('330922', '嵊泗县', '3');
INSERT INTO `t_nation` VALUES ('469031', '昌江黎族自治县', '3');
INSERT INTO `t_nation` VALUES ('520422', '普定县', '3');
INSERT INTO `t_nation` VALUES ('520100', '贵阳市', '2');
INSERT INTO `t_nation` VALUES ('370832', '梁山县', '3');
INSERT INTO `t_nation` VALUES ('350622', '云霄县', '3');
INSERT INTO `t_nation` VALUES ('451001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('340403', '田家庵区', '3');
INSERT INTO `t_nation` VALUES ('350501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('431021', '桂阳县', '3');
INSERT INTO `t_nation` VALUES ('371200', '莱芜市', '2');
INSERT INTO `t_nation` VALUES ('371622', '阳信县', '3');
INSERT INTO `t_nation` VALUES ('310114', '嘉定区', '3');
INSERT INTO `t_nation` VALUES ('130626', '定兴县', '3');
INSERT INTO `t_nation` VALUES ('450325', '兴安县', '3');
INSERT INTO `t_nation` VALUES ('340701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('340802', '迎江区', '3');
INSERT INTO `t_nation` VALUES ('632322', '尖扎县', '3');
INSERT INTO `t_nation` VALUES ('371521', '阳谷县', '3');
INSERT INTO `t_nation` VALUES ('361100', '上饶市', '2');
INSERT INTO `t_nation` VALUES ('431127', '蓝山县', '3');
INSERT INTO `t_nation` VALUES ('510802', '市中区', '3');
INSERT INTO `t_nation` VALUES ('410302', '老城区', '3');
INSERT INTO `t_nation` VALUES ('532627', '广南县', '3');
INSERT INTO `t_nation` VALUES ('513324', '九龙县', '3');
INSERT INTO `t_nation` VALUES ('430408', '蒸湘区', '3');
INSERT INTO `t_nation` VALUES ('511181', '峨眉山市', '3');
INSERT INTO `t_nation` VALUES ('430500', '邵阳市', '2');
INSERT INTO `t_nation` VALUES ('420581', '宜都市', '3');
INSERT INTO `t_nation` VALUES ('421102', '黄州区', '3');
INSERT INTO `t_nation` VALUES ('450800', '贵港市', '2');
INSERT INTO `t_nation` VALUES ('411725', '确山县', '3');
INSERT INTO `t_nation` VALUES ('140500', '晋城市', '2');
INSERT INTO `t_nation` VALUES ('610802', '榆阳区', '3');
INSERT INTO `t_nation` VALUES ('440116', '萝岗区', '3');
INSERT INTO `t_nation` VALUES ('341226', '颍上县', '3');
INSERT INTO `t_nation` VALUES ('230108', '平房区', '3');
INSERT INTO `t_nation` VALUES ('610329', '麟游县', '3');
INSERT INTO `t_nation` VALUES ('340501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('321302', '宿城区', '3');
INSERT INTO `t_nation` VALUES ('140121', '清徐县', '3');
INSERT INTO `t_nation` VALUES ('542121', '昌都县', '3');
INSERT INTO `t_nation` VALUES ('542133', '边坝县', '3');
INSERT INTO `t_nation` VALUES ('433122', '泸溪县', '3');
INSERT INTO `t_nation` VALUES ('210801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620822', '灵台县', '3');
INSERT INTO `t_nation` VALUES ('450900', '玉林市', '2');
INSERT INTO `t_nation` VALUES ('320829', '洪泽县', '3');
INSERT INTO `t_nation` VALUES ('150428', '喀喇沁旗', '3');
INSERT INTO `t_nation` VALUES ('150301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('429021', '神农架林区', '3');
INSERT INTO `t_nation` VALUES ('610628', '富县', '3');
INSERT INTO `t_nation` VALUES ('511722', '宣汉县', '3');
INSERT INTO `t_nation` VALUES ('440303', '罗湖区', '3');
INSERT INTO `t_nation` VALUES ('441422', '大埔县', '3');
INSERT INTO `t_nation` VALUES ('341204', '颍泉区', '3');
INSERT INTO `t_nation` VALUES ('513337', '稻城县', '3');
INSERT INTO `t_nation` VALUES ('330803', '衢江区', '3');
INSERT INTO `t_nation` VALUES ('620622', '古浪县', '3');
INSERT INTO `t_nation` VALUES ('230229', '克山县', '3');
INSERT INTO `t_nation` VALUES ('430412', '南岳区', '3');
INSERT INTO `t_nation` VALUES ('371625', '博兴县', '3');
INSERT INTO `t_nation` VALUES ('511025', '资中县', '3');
INSERT INTO `t_nation` VALUES ('522300', '黔西南布依族苗族自治州', '2');
INSERT INTO `t_nation` VALUES ('430104', '岳麓区', '3');
INSERT INTO `t_nation` VALUES ('330424', '海盐县', '3');
INSERT INTO `t_nation` VALUES ('331127', '景宁畲族自治县', '3');
INSERT INTO `t_nation` VALUES ('430221', '株洲县', '3');
INSERT INTO `t_nation` VALUES ('445300', '云浮市', '2');
INSERT INTO `t_nation` VALUES ('231081', '绥芬河市', '3');
INSERT INTO `t_nation` VALUES ('150722', '莫力达瓦达斡尔族自治旗', '3');
INSERT INTO `t_nation` VALUES ('370921', '宁阳县', '3');
INSERT INTO `t_nation` VALUES ('421000', '荆州市', '2');
INSERT INTO `t_nation` VALUES ('330300', '温州市', '2');
INSERT INTO `t_nation` VALUES ('230524', '饶河县', '3');
INSERT INTO `t_nation` VALUES ('140725', '寿阳县', '3');
INSERT INTO `t_nation` VALUES ('610124', '周至县', '3');
INSERT INTO `t_nation` VALUES ('130682', '定州市', '3');
INSERT INTO `t_nation` VALUES ('140425', '平顺县', '3');
INSERT INTO `t_nation` VALUES ('511321', '南部县', '3');
INSERT INTO `t_nation` VALUES ('450421', '苍梧县', '3');
INSERT INTO `t_nation` VALUES ('620000', '甘肃省', '1');
INSERT INTO `t_nation` VALUES ('621023', '华池县', '3');
INSERT INTO `t_nation` VALUES ('330726', '浦江县', '3');
INSERT INTO `t_nation` VALUES ('350502', '鲤城区', '3');
INSERT INTO `t_nation` VALUES ('370500', '东营市', '2');
INSERT INTO `t_nation` VALUES ('522627', '天柱县', '3');
INSERT INTO `t_nation` VALUES ('420505', '猇亭区', '3');
INSERT INTO `t_nation` VALUES ('152500', '锡林郭勒盟', '2');
INSERT INTO `t_nation` VALUES ('610928', '旬阳县', '3');
INSERT INTO `t_nation` VALUES ('370687', '海阳市', '3');
INSERT INTO `t_nation` VALUES ('510122', '双流县', '3');
INSERT INTO `t_nation` VALUES ('510781', '江油市', '3');
INSERT INTO `t_nation` VALUES ('361025', '乐安县', '3');
INSERT INTO `t_nation` VALUES ('130528', '宁晋县', '3');
INSERT INTO `t_nation` VALUES ('230321', '鸡东县', '3');
INSERT INTO `t_nation` VALUES ('150922', '化德县', '3');
INSERT INTO `t_nation` VALUES ('433124', '花垣县', '3');
INSERT INTO `t_nation` VALUES ('411527', '淮滨县', '3');
INSERT INTO `t_nation` VALUES ('330900', '舟山市', '2');
INSERT INTO `t_nation` VALUES ('610203', '印台区', '3');
INSERT INTO `t_nation` VALUES ('500228', '梁平县', '3');
INSERT INTO `t_nation` VALUES ('510704', '游仙区', '3');
INSERT INTO `t_nation` VALUES ('610201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('532628', '富宁县', '3');
INSERT INTO `t_nation` VALUES ('341101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371422', '宁津县', '3');
INSERT INTO `t_nation` VALUES ('441602', '源城区', '3');
INSERT INTO `t_nation` VALUES ('320301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320481', '溧阳市', '3');
INSERT INTO `t_nation` VALUES ('110105', '朝阳区', '3');
INSERT INTO `t_nation` VALUES ('410122', '中牟县', '3');
INSERT INTO `t_nation` VALUES ('420324', '竹溪县', '3');
INSERT INTO `t_nation` VALUES ('522726', '独山县', '3');
INSERT INTO `t_nation` VALUES ('350582', '晋江市', '3');
INSERT INTO `t_nation` VALUES ('370105', '天桥区', '3');
INSERT INTO `t_nation` VALUES ('654027', '特克斯县', '3');
INSERT INTO `t_nation` VALUES ('140826', '绛县', '3');
INSERT INTO `t_nation` VALUES ('341521', '寿县', '3');
INSERT INTO `t_nation` VALUES ('620122', '皋兰县', '3');
INSERT INTO `t_nation` VALUES ('653124', '泽普县', '3');
INSERT INTO `t_nation` VALUES ('520301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('141127', '岚县', '3');
INSERT INTO `t_nation` VALUES ('430903', '赫山区', '3');
INSERT INTO `t_nation` VALUES ('450403', '万秀区', '3');
INSERT INTO `t_nation` VALUES ('512081', '简阳市', '3');
INSERT INTO `t_nation` VALUES ('420801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230902', '新兴区', '3');
INSERT INTO `t_nation` VALUES ('210921', '阜新蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('360401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('150784', '额尔古纳市', '3');
INSERT INTO `t_nation` VALUES ('331003', '黄岩区', '3');
INSERT INTO `t_nation` VALUES ('360924', '宜丰县', '3');
INSERT INTO `t_nation` VALUES ('120100', '市辖区', '2');
INSERT INTO `t_nation` VALUES ('632323', '泽库县', '3');
INSERT INTO `t_nation` VALUES ('232700', '大兴安岭地区', '2');
INSERT INTO `t_nation` VALUES ('140881', '永济市', '3');
INSERT INTO `t_nation` VALUES ('340703', '狮子山区', '3');
INSERT INTO `t_nation` VALUES ('640300', '吴忠市', '2');
INSERT INTO `t_nation` VALUES ('131000', '廊坊市', '2');
INSERT INTO `t_nation` VALUES ('130600', '保定市', '2');
INSERT INTO `t_nation` VALUES ('510411', '仁和区', '3');
INSERT INTO `t_nation` VALUES ('340700', '铜陵市', '2');
INSERT INTO `t_nation` VALUES ('530425', '易门县', '3');
INSERT INTO `t_nation` VALUES ('130532', '平乡县', '3');
INSERT INTO `t_nation` VALUES ('654225', '裕民县', '3');
INSERT INTO `t_nation` VALUES ('230704', '友好区', '3');
INSERT INTO `t_nation` VALUES ('140603', '平鲁区', '3');
INSERT INTO `t_nation` VALUES ('211381', '北票市', '3');
INSERT INTO `t_nation` VALUES ('513331', '白玉县', '3');
INSERT INTO `t_nation` VALUES ('640424', '泾源县', '3');
INSERT INTO `t_nation` VALUES ('340801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('331021', '玉环县', '3');
INSERT INTO `t_nation` VALUES ('341324', '泗县', '3');
INSERT INTO `t_nation` VALUES ('542200', '山南地区', '2');
INSERT INTO `t_nation` VALUES ('511623', '邻水县', '3');
INSERT INTO `t_nation` VALUES ('510311', '沿滩区', '3');
INSERT INTO `t_nation` VALUES ('130281', '遵化市', '3');
INSERT INTO `t_nation` VALUES ('510812', '朝天区', '3');
INSERT INTO `t_nation` VALUES ('450105', '江南区', '3');
INSERT INTO `t_nation` VALUES ('620503', '麦积区', '3');
INSERT INTO `t_nation` VALUES ('350123', '罗源县', '3');
INSERT INTO `t_nation` VALUES ('430321', '湘潭县', '3');
INSERT INTO `t_nation` VALUES ('370000', '山东省', '1');
INSERT INTO `t_nation` VALUES ('150121', '土默特左旗', '3');
INSERT INTO `t_nation` VALUES ('211481', '兴城市', '3');
INSERT INTO `t_nation` VALUES ('341824', '绩溪县', '3');
INSERT INTO `t_nation` VALUES ('350881', '漳平市', '3');
INSERT INTO `t_nation` VALUES ('511821', '名山县', '3');
INSERT INTO `t_nation` VALUES ('451002', '右江区', '3');
INSERT INTO `t_nation` VALUES ('431223', '辰溪县', '3');
INSERT INTO `t_nation` VALUES ('210502', '平山区', '3');
INSERT INTO `t_nation` VALUES ('141031', '隰县', '3');
INSERT INTO `t_nation` VALUES ('431003', '苏仙区', '3');
INSERT INTO `t_nation` VALUES ('411681', '项城市', '3');
INSERT INTO `t_nation` VALUES ('445200', '揭阳市', '2');
INSERT INTO `t_nation` VALUES ('445122', '饶平县', '3');
INSERT INTO `t_nation` VALUES ('360728', '定南县', '3');
INSERT INTO `t_nation` VALUES ('431221', '中方县', '3');
INSERT INTO `t_nation` VALUES ('411321', '南召县', '3');
INSERT INTO `t_nation` VALUES ('610402', '秦都区', '3');
INSERT INTO `t_nation` VALUES ('621100', '定西市', '2');
INSERT INTO `t_nation` VALUES ('320323', '铜山县', '3');
INSERT INTO `t_nation` VALUES ('350725', '政和县', '3');
INSERT INTO `t_nation` VALUES ('320925', '建湖县', '3');
INSERT INTO `t_nation` VALUES ('610726', '宁强县', '3');
INSERT INTO `t_nation` VALUES ('371082', '荣成市', '3');
INSERT INTO `t_nation` VALUES ('620123', '榆中县', '3');
INSERT INTO `t_nation` VALUES ('640202', '大武口区', '3');
INSERT INTO `t_nation` VALUES ('530321', '马龙县', '3');
INSERT INTO `t_nation` VALUES ('622923', '永靖县', '3');
INSERT INTO `t_nation` VALUES ('320583', '昆山市', '3');
INSERT INTO `t_nation` VALUES ('210181', '新民市', '3');
INSERT INTO `t_nation` VALUES ('220303', '铁东区', '3');
INSERT INTO `t_nation` VALUES ('522229', '松桃苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('360300', '萍乡市', '2');
INSERT INTO `t_nation` VALUES ('530822', '墨江哈尼族自治县', '3');
INSERT INTO `t_nation` VALUES ('632621', '玛沁县', '3');
INSERT INTO `t_nation` VALUES ('371701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('621202', '武都区', '3');
INSERT INTO `t_nation` VALUES ('440300', '深圳市', '2');
INSERT INTO `t_nation` VALUES ('441821', '佛冈县', '3');
INSERT INTO `t_nation` VALUES ('542325', '萨迦县', '3');
INSERT INTO `t_nation` VALUES ('530826', '江城哈尼族彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('130283', '迁安市', '3');
INSERT INTO `t_nation` VALUES ('150105', '赛罕区', '3');
INSERT INTO `t_nation` VALUES ('330212', '鄞州区', '3');
INSERT INTO `t_nation` VALUES ('530827', '孟连傣族拉祜族佤族自治县', '3');
INSERT INTO `t_nation` VALUES ('350926', '柘荣县', '3');
INSERT INTO `t_nation` VALUES ('620725', '山丹县', '3');
INSERT INTO `t_nation` VALUES ('141100', '吕梁市', '2');
INSERT INTO `t_nation` VALUES ('130632', '安新县', '3');
INSERT INTO `t_nation` VALUES ('511325', '西充县', '3');
INSERT INTO `t_nation` VALUES ('611022', '丹凤县', '3');
INSERT INTO `t_nation` VALUES ('451400', '崇左市', '2');
INSERT INTO `t_nation` VALUES ('420300', '十堰市', '2');
INSERT INTO `t_nation` VALUES ('653125', '莎车县', '3');
INSERT INTO `t_nation` VALUES ('230124', '方正县', '3');
INSERT INTO `t_nation` VALUES ('330322', '洞头县', '3');
INSERT INTO `t_nation` VALUES ('640205', '惠农区', '3');
INSERT INTO `t_nation` VALUES ('140423', '襄垣县', '3');
INSERT INTO `t_nation` VALUES ('652723', '温泉县', '3');
INSERT INTO `t_nation` VALUES ('640323', '盐池县', '3');
INSERT INTO `t_nation` VALUES ('450701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('331022', '三门县', '3');
INSERT INTO `t_nation` VALUES ('370283', '平度市', '3');
INSERT INTO `t_nation` VALUES ('231182', '五大连池市', '3');
INSERT INTO `t_nation` VALUES ('152531', '多伦县', '3');
INSERT INTO `t_nation` VALUES ('220381', '公主岭市', '3');
INSERT INTO `t_nation` VALUES ('440106', '天河区', '3');
INSERT INTO `t_nation` VALUES ('130129', '赞皇县', '3');
INSERT INTO `t_nation` VALUES ('360313', '湘东区', '3');
INSERT INTO `t_nation` VALUES ('610632', '黄陵县', '3');
INSERT INTO `t_nation` VALUES ('140402', '城区', '3');
INSERT INTO `t_nation` VALUES ('340123', '肥西县', '3');
INSERT INTO `t_nation` VALUES ('330411', '秀洲区', '3');
INSERT INTO `t_nation` VALUES ('230306', '城子河区', '3');
INSERT INTO `t_nation` VALUES ('220211', '丰满区', '3');
INSERT INTO `t_nation` VALUES ('450702', '钦南区', '3');
INSERT INTO `t_nation` VALUES ('150423', '巴林右旗', '3');
INSERT INTO `t_nation` VALUES ('510304', '大安区', '3');
INSERT INTO `t_nation` VALUES ('210401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620722', '民乐县', '3');
INSERT INTO `t_nation` VALUES ('140623', '右玉县', '3');
INSERT INTO `t_nation` VALUES ('500113', '巴南区', '3');
INSERT INTO `t_nation` VALUES ('654322', '富蕴县', '3');
INSERT INTO `t_nation` VALUES ('542326', '拉孜县', '3');
INSERT INTO `t_nation` VALUES ('411104', '召陵区', '3');
INSERT INTO `t_nation` VALUES ('371427', '夏津县', '3');
INSERT INTO `t_nation` VALUES ('230224', '泰来县', '3');
INSERT INTO `t_nation` VALUES ('210911', '细河区', '3');
INSERT INTO `t_nation` VALUES ('411403', '睢阳区', '3');
INSERT INTO `t_nation` VALUES ('532500', '红河哈尼族彝族自治州', '2');
INSERT INTO `t_nation` VALUES ('640105', '西夏区', '3');
INSERT INTO `t_nation` VALUES ('210102', '和平区', '3');
INSERT INTO `t_nation` VALUES ('441426', '平远县', '3');
INSERT INTO `t_nation` VALUES ('120111', '西青区', '3');
INSERT INTO `t_nation` VALUES ('420600', '襄阳市', '2');
INSERT INTO `t_nation` VALUES ('411602', '川汇区', '3');
INSERT INTO `t_nation` VALUES ('230182', '双城市', '3');
INSERT INTO `t_nation` VALUES ('542300', '日喀则地区', '2');
INSERT INTO `t_nation` VALUES ('411322', '方城县', '3');
INSERT INTO `t_nation` VALUES ('230781', '铁力市', '3');
INSERT INTO `t_nation` VALUES ('610404', '渭城区', '3');
INSERT INTO `t_nation` VALUES ('530621', '鲁甸县', '3');
INSERT INTO `t_nation` VALUES ('445221', '揭东县', '3');
INSERT INTO `t_nation` VALUES ('513334', '理塘县', '3');
INSERT INTO `t_nation` VALUES ('542126', '察雅县', '3');
INSERT INTO `t_nation` VALUES ('211000', '辽阳市', '2');
INSERT INTO `t_nation` VALUES ('370900', '泰安市', '2');
INSERT INTO `t_nation` VALUES ('150725', '陈巴尔虎旗', '3');
INSERT INTO `t_nation` VALUES ('620802', '崆峒区', '3');
INSERT INTO `t_nation` VALUES ('130131', '平山县', '3');
INSERT INTO `t_nation` VALUES ('141030', '大宁县', '3');
INSERT INTO `t_nation` VALUES ('511700', '达州市', '2');
INSERT INTO `t_nation` VALUES ('230207', '碾子山区', '3');
INSERT INTO `t_nation` VALUES ('210283', '庄河市', '3');
INSERT INTO `t_nation` VALUES ('360981', '丰城市', '3');
INSERT INTO `t_nation` VALUES ('110229', '延庆县', '3');
INSERT INTO `t_nation` VALUES ('513427', '宁南县', '3');
INSERT INTO `t_nation` VALUES ('510600', '德阳市', '2');
INSERT INTO `t_nation` VALUES ('230000', '黑龙江省', '1');
INSERT INTO `t_nation` VALUES ('610422', '三原县', '3');
INSERT INTO `t_nation` VALUES ('530300', '曲靖市', '2');
INSERT INTO `t_nation` VALUES ('371202', '莱城区', '3');
INSERT INTO `t_nation` VALUES ('150626', '乌审旗', '3');
INSERT INTO `t_nation` VALUES ('450326', '永福县', '3');
INSERT INTO `t_nation` VALUES ('450226', '三江侗族自治县', '3');
INSERT INTO `t_nation` VALUES ('210323', '岫岩满族自治县', '3');
INSERT INTO `t_nation` VALUES ('420881', '钟祥市', '3');
INSERT INTO `t_nation` VALUES ('370321', '桓台县', '3');
INSERT INTO `t_nation` VALUES ('610528', '富平县', '3');
INSERT INTO `t_nation` VALUES ('310112', '闵行区', '3');
INSERT INTO `t_nation` VALUES ('230109', '松北区', '3');
INSERT INTO `t_nation` VALUES ('330501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411600', '周口市', '2');
INSERT INTO `t_nation` VALUES ('610700', '汉中市', '2');
INSERT INTO `t_nation` VALUES ('110102', '西城区', '3');
INSERT INTO `t_nation` VALUES ('350801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330304', '瓯海区', '3');
INSERT INTO `t_nation` VALUES ('420322', '郧西县', '3');
INSERT INTO `t_nation` VALUES ('232703', '新林区', '3');
INSERT INTO `t_nation` VALUES ('230605', '红岗区', '3');
INSERT INTO `t_nation` VALUES ('522722', '荔波县', '3');
INSERT INTO `t_nation` VALUES ('530421', '江川县', '3');
INSERT INTO `t_nation` VALUES ('230203', '建华区', '3');
INSERT INTO `t_nation` VALUES ('341182', '明光市', '3');
INSERT INTO `t_nation` VALUES ('530301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320322', '沛县', '3');
INSERT INTO `t_nation` VALUES ('542222', '扎囊县', '3');
INSERT INTO `t_nation` VALUES ('522636', '丹寨县', '3');
INSERT INTO `t_nation` VALUES ('330329', '泰顺县', '3');
INSERT INTO `t_nation` VALUES ('220622', '靖宇县', '3');
INSERT INTO `t_nation` VALUES ('510801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330122', '桐庐县', '3');
INSERT INTO `t_nation` VALUES ('411727', '汝南县', '3');
INSERT INTO `t_nation` VALUES ('610200', '铜川市', '2');
INSERT INTO `t_nation` VALUES ('131028', '大厂回族自治县', '3');
INSERT INTO `t_nation` VALUES ('411700', '驻马店市', '2');
INSERT INTO `t_nation` VALUES ('150221', '土默特右旗', '3');
INSERT INTO `t_nation` VALUES ('513333', '色达县', '3');
INSERT INTO `t_nation` VALUES ('130631', '望都县', '3');
INSERT INTO `t_nation` VALUES ('520181', '清镇市', '3');
INSERT INTO `t_nation` VALUES ('141029', '乡宁县', '3');
INSERT INTO `t_nation` VALUES ('330800', '衢州市', '2');
INSERT INTO `t_nation` VALUES ('430611', '君山区', '3');
INSERT INTO `t_nation` VALUES ('360735', '石城县', '3');
INSERT INTO `t_nation` VALUES ('632724', '治多县', '3');
INSERT INTO `t_nation` VALUES ('430181', '浏阳市', '3');
INSERT INTO `t_nation` VALUES ('152202', '阿尔山市', '3');
INSERT INTO `t_nation` VALUES ('141001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('152530', '正蓝旗', '3');
INSERT INTO `t_nation` VALUES ('652925', '新和县', '3');
INSERT INTO `t_nation` VALUES ('420500', '宜昌市', '2');
INSERT INTO `t_nation` VALUES ('542128', '左贡县', '3');
INSERT INTO `t_nation` VALUES ('421200', '咸宁市', '2');
INSERT INTO `t_nation` VALUES ('320800', '淮安市', '2');
INSERT INTO `t_nation` VALUES ('650103', '沙依巴克区', '3');
INSERT INTO `t_nation` VALUES ('620702', '甘州区', '3');
INSERT INTO `t_nation` VALUES ('341600', '亳州市', '2');
INSERT INTO `t_nation` VALUES ('610727', '略阳县', '3');
INSERT INTO `t_nation` VALUES ('152501', '二连浩特市', '3');
INSERT INTO `t_nation` VALUES ('440305', '南山区', '3');
INSERT INTO `t_nation` VALUES ('530622', '巧家县', '3');
INSERT INTO `t_nation` VALUES ('542627', '朗县', '3');
INSERT INTO `t_nation` VALUES ('140721', '榆社县', '3');
INSERT INTO `t_nation` VALUES ('320402', '天宁区', '3');
INSERT INTO `t_nation` VALUES ('511526', '珙县', '3');
INSERT INTO `t_nation` VALUES ('350505', '泉港区', '3');
INSERT INTO `t_nation` VALUES ('411426', '夏邑县', '3');
INSERT INTO `t_nation` VALUES ('411025', '襄城县', '3');
INSERT INTO `t_nation` VALUES ('340200', '芜湖市', '2');
INSERT INTO `t_nation` VALUES ('441625', '东源县', '3');
INSERT INTO `t_nation` VALUES ('130303', '山海关区', '3');
INSERT INTO `t_nation` VALUES ('410526', '滑县', '3');
INSERT INTO `t_nation` VALUES ('431202', '鹤城区', '3');
INSERT INTO `t_nation` VALUES ('350901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220103', '宽城区', '3');
INSERT INTO `t_nation` VALUES ('522622', '黄平县', '3');
INSERT INTO `t_nation` VALUES ('330503', '南浔区', '3');
INSERT INTO `t_nation` VALUES ('340207', '鸠江区', '3');
INSERT INTO `t_nation` VALUES ('610426', '永寿县', '3');
INSERT INTO `t_nation` VALUES ('510921', '蓬溪县', '3');
INSERT INTO `t_nation` VALUES ('320102', '玄武区', '3');
INSERT INTO `t_nation` VALUES ('150501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140821', '临猗县', '3');
INSERT INTO `t_nation` VALUES ('450902', '玉州区', '3');
INSERT INTO `t_nation` VALUES ('320600', '南通市', '2');
INSERT INTO `t_nation` VALUES ('512000', '资阳市', '2');
INSERT INTO `t_nation` VALUES ('230506', '宝山区', '3');
INSERT INTO `t_nation` VALUES ('511001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370402', '市中区', '3');
INSERT INTO `t_nation` VALUES ('341601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('654226', '和布克赛尔蒙古自治县', '3');
INSERT INTO `t_nation` VALUES ('610581', '韩城市', '3');
INSERT INTO `t_nation` VALUES ('510725', '梓潼县', '3');
INSERT INTO `t_nation` VALUES ('150623', '鄂托克前旗', '3');
INSERT INTO `t_nation` VALUES ('411001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371302', '兰山区', '3');
INSERT INTO `t_nation` VALUES ('542328', '谢通门县', '3');
INSERT INTO `t_nation` VALUES ('320304', '九里区', '3');
INSERT INTO `t_nation` VALUES ('230708', '美溪区', '3');
INSERT INTO `t_nation` VALUES ('371624', '沾化县', '3');
INSERT INTO `t_nation` VALUES ('520103', '云岩区', '3');
INSERT INTO `t_nation` VALUES ('370304', '博山区', '3');
INSERT INTO `t_nation` VALUES ('320381', '新沂市', '3');
INSERT INTO `t_nation` VALUES ('361026', '宜黄县', '3');
INSERT INTO `t_nation` VALUES ('654025', '新源县', '3');
INSERT INTO `t_nation` VALUES ('152224', '突泉县', '3');
INSERT INTO `t_nation` VALUES ('141125', '柳林县', '3');
INSERT INTO `t_nation` VALUES ('610327', '陇县', '3');
INSERT INTO `t_nation` VALUES ('341125', '定远县', '3');
INSERT INTO `t_nation` VALUES ('431322', '新化县', '3');
INSERT INTO `t_nation` VALUES ('320501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('532626', '丘北县', '3');
INSERT INTO `t_nation` VALUES ('150622', '准格尔旗', '3');
INSERT INTO `t_nation` VALUES ('469002', '琼海市', '3');
INSERT INTO `t_nation` VALUES ('130926', '肃宁县', '3');
INSERT INTO `t_nation` VALUES ('450204', '柳南区', '3');
INSERT INTO `t_nation` VALUES ('430522', '新邵县', '3');
INSERT INTO `t_nation` VALUES ('340311', '淮上区', '3');
INSERT INTO `t_nation` VALUES ('371727', '定陶县', '3');
INSERT INTO `t_nation` VALUES ('622924', '广河县', '3');
INSERT INTO `t_nation` VALUES ('433130', '龙山县', '3');
INSERT INTO `t_nation` VALUES ('511723', '开江县', '3');
INSERT INTO `t_nation` VALUES ('320281', '江阴市', '3');
INSERT INTO `t_nation` VALUES ('610430', '淳化县', '3');
INSERT INTO `t_nation` VALUES ('610114', '阎良区', '3');
INSERT INTO `t_nation` VALUES ('350213', '翔安区', '3');
INSERT INTO `t_nation` VALUES ('350503', '丰泽区', '3');
INSERT INTO `t_nation` VALUES ('130421', '邯郸县', '3');
INSERT INTO `t_nation` VALUES ('532625', '马关县', '3');
INSERT INTO `t_nation` VALUES ('220421', '东丰县', '3');
INSERT INTO `t_nation` VALUES ('341225', '阜南县', '3');
INSERT INTO `t_nation` VALUES ('640324', '同心县', '3');
INSERT INTO `t_nation` VALUES ('611023', '商南县', '3');
INSERT INTO `t_nation` VALUES ('431230', '通道侗族自治县', '3');
INSERT INTO `t_nation` VALUES ('513436', '美姑县', '3');
INSERT INTO `t_nation` VALUES ('500111', '双桥区', '3');
INSERT INTO `t_nation` VALUES ('150125', '武川县', '3');
INSERT INTO `t_nation` VALUES ('130822', '兴隆县', '3');
INSERT INTO `t_nation` VALUES ('140927', '神池县', '3');
INSERT INTO `t_nation` VALUES ('220523', '辉南县', '3');
INSERT INTO `t_nation` VALUES ('370211', '黄岛区', '3');
INSERT INTO `t_nation` VALUES ('130801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130925', '盐山县', '3');
INSERT INTO `t_nation` VALUES ('341821', '郎溪县', '3');
INSERT INTO `t_nation` VALUES ('211121', '大洼县', '3');
INSERT INTO `t_nation` VALUES ('511323', '蓬安县', '3');
INSERT INTO `t_nation` VALUES ('513321', '康定县', '3');
INSERT INTO `t_nation` VALUES ('450722', '浦北县', '3');
INSERT INTO `t_nation` VALUES ('340203', '弋江区', '3');
INSERT INTO `t_nation` VALUES ('370681', '龙口市', '3');
INSERT INTO `t_nation` VALUES ('340302', '龙子湖区', '3');
INSERT INTO `t_nation` VALUES ('410204', '鼓楼区', '3');
INSERT INTO `t_nation` VALUES ('620422', '会宁县', '3');
INSERT INTO `t_nation` VALUES ('410381', '偃师市', '3');
INSERT INTO `t_nation` VALUES ('440784', '鹤山市', '3');
INSERT INTO `t_nation` VALUES ('511528', '兴文县', '3');
INSERT INTO `t_nation` VALUES ('610324', '扶风县', '3');
INSERT INTO `t_nation` VALUES ('632801', '格尔木市', '3');
INSERT INTO `t_nation` VALUES ('530523', '龙陵县', '3');
INSERT INTO `t_nation` VALUES ('340405', '八公山区', '3');
INSERT INTO `t_nation` VALUES ('120225', '蓟县', '3');
INSERT INTO `t_nation` VALUES ('510681', '广汉市', '3');
INSERT INTO `t_nation` VALUES ('220100', '长春市', '2');
INSERT INTO `t_nation` VALUES ('431281', '洪江市', '3');
INSERT INTO `t_nation` VALUES ('610825', '定边县', '3');
INSERT INTO `t_nation` VALUES ('341503', '裕安区', '3');
INSERT INTO `t_nation` VALUES ('340825', '太湖县', '3');
INSERT INTO `t_nation` VALUES ('510182', '彭州市', '3');
INSERT INTO `t_nation` VALUES ('130230', '唐海县', '3');
INSERT INTO `t_nation` VALUES ('450512', '铁山港区', '3');
INSERT INTO `t_nation` VALUES ('341523', '舒城县', '3');
INSERT INTO `t_nation` VALUES ('431381', '冷水江市', '3');
INSERT INTO `t_nation` VALUES ('440512', '濠江区', '3');
INSERT INTO `t_nation` VALUES ('620502', '秦州区', '3');
INSERT INTO `t_nation` VALUES ('430923', '安化县', '3');
INSERT INTO `t_nation` VALUES ('140431', '沁源县', '3');
INSERT INTO `t_nation` VALUES ('140829', '平陆县', '3');
INSERT INTO `t_nation` VALUES ('542400', '那曲地区', '2');
INSERT INTO `t_nation` VALUES ('420503', '伍家岗区', '3');
INSERT INTO `t_nation` VALUES ('441900', '东莞市', '2');
INSERT INTO `t_nation` VALUES ('410323', '新安县', '3');
INSERT INTO `t_nation` VALUES ('431382', '涟源市', '3');
INSERT INTO `t_nation` VALUES ('431224', '溆浦县', '3');
INSERT INTO `t_nation` VALUES ('350623', '漳浦县', '3');
INSERT INTO `t_nation` VALUES ('131024', '香河县', '3');
INSERT INTO `t_nation` VALUES ('230921', '勃利县', '3');
INSERT INTO `t_nation` VALUES ('130624', '阜平县', '3');
INSERT INTO `t_nation` VALUES ('510822', '青川县', '3');
INSERT INTO `t_nation` VALUES ('522200', '铜仁地区', '2');
INSERT INTO `t_nation` VALUES ('231283', '海伦市', '3');
INSERT INTO `t_nation` VALUES ('152923', '额济纳旗', '3');
INSERT INTO `t_nation` VALUES ('522225', '思南县', '3');
INSERT INTO `t_nation` VALUES ('440100', '广州市', '2');
INSERT INTO `t_nation` VALUES ('469006', '万宁市', '3');
INSERT INTO `t_nation` VALUES ('230710', '五营区', '3');
INSERT INTO `t_nation` VALUES ('150523', '开鲁县', '3');
INSERT INTO `t_nation` VALUES ('610202', '王益区', '3');
INSERT INTO `t_nation` VALUES ('451025', '靖西县', '3');
INSERT INTO `t_nation` VALUES ('659002', '阿拉尔市', '3');
INSERT INTO `t_nation` VALUES ('441521', '海丰县', '3');
INSERT INTO `t_nation` VALUES ('330783', '东阳市', '3');
INSERT INTO `t_nation` VALUES ('350721', '顺昌县', '3');
INSERT INTO `t_nation` VALUES ('411728', '遂平县', '3');
INSERT INTO `t_nation` VALUES ('451101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320682', '如皋市', '3');
INSERT INTO `t_nation` VALUES ('410222', '通许县', '3');
INSERT INTO `t_nation` VALUES ('321324', '泗洪县', '3');
INSERT INTO `t_nation` VALUES ('532323', '牟定县', '3');
INSERT INTO `t_nation` VALUES ('220182', '榆树市', '3');
INSERT INTO `t_nation` VALUES ('522729', '长顺县', '3');
INSERT INTO `t_nation` VALUES ('411102', '源汇区', '3');
INSERT INTO `t_nation` VALUES ('500100', '市辖区', '2');
INSERT INTO `t_nation` VALUES ('450329', '资源县', '3');
INSERT INTO `t_nation` VALUES ('341002', '屯溪区', '3');
INSERT INTO `t_nation` VALUES ('520221', '水城县', '3');
INSERT INTO `t_nation` VALUES ('320104', '秦淮区', '3');
INSERT INTO `t_nation` VALUES ('230709', '金山屯区', '3');
INSERT INTO `t_nation` VALUES ('620100', '兰州市', '2');
INSERT INTO `t_nation` VALUES ('360702', '章贡区', '3');
INSERT INTO `t_nation` VALUES ('150100', '呼和浩特市', '2');
INSERT INTO `t_nation` VALUES ('450205', '柳北区', '3');
INSERT INTO `t_nation` VALUES ('140101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440102', '东山区', '3');
INSERT INTO `t_nation` VALUES ('130182', '藁城市', '3');
INSERT INTO `t_nation` VALUES ('429000', '省直辖行政单位', '2');
INSERT INTO `t_nation` VALUES ('130200', '唐山市', '2');
INSERT INTO `t_nation` VALUES ('140522', '阳城县', '3');
INSERT INTO `t_nation` VALUES ('440515', '澄海区', '3');
INSERT INTO `t_nation` VALUES ('370831', '泗水县', '3');
INSERT INTO `t_nation` VALUES ('370200', '青岛市', '2');
INSERT INTO `t_nation` VALUES ('371501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('350925', '周宁县', '3');
INSERT INTO `t_nation` VALUES ('510522', '合江县', '3');
INSERT INTO `t_nation` VALUES ('610525', '澄城县', '3');
INSERT INTO `t_nation` VALUES ('211221', '铁岭县', '3');
INSERT INTO `t_nation` VALUES ('330105', '拱墅区', '3');
INSERT INTO `t_nation` VALUES ('510903', '船山区', '3');
INSERT INTO `t_nation` VALUES ('130725', '尚义县', '3');
INSERT INTO `t_nation` VALUES ('451301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230305', '梨树区', '3');
INSERT INTO `t_nation` VALUES ('441826', '连南瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('120107', '塘沽区', '3');
INSERT INTO `t_nation` VALUES ('450502', '海城区', '3');
INSERT INTO `t_nation` VALUES ('652800', '巴音郭楞蒙古自治州', '2');
INSERT INTO `t_nation` VALUES ('350124', '闽清县', '3');
INSERT INTO `t_nation` VALUES ('340827', '望江县', '3');
INSERT INTO `t_nation` VALUES ('231221', '望奎县', '3');
INSERT INTO `t_nation` VALUES ('360800', '吉安市', '2');
INSERT INTO `t_nation` VALUES ('130531', '广宗县', '3');
INSERT INTO `t_nation` VALUES ('510321', '荣县', '3');
INSERT INTO `t_nation` VALUES ('410825', '温县', '3');
INSERT INTO `t_nation` VALUES ('330481', '海宁市', '3');
INSERT INTO `t_nation` VALUES ('532525', '石屏县', '3');
INSERT INTO `t_nation` VALUES ('110228', '密云县', '3');
INSERT INTO `t_nation` VALUES ('371121', '五莲县', '3');
INSERT INTO `t_nation` VALUES ('421124', '英山县', '3');
INSERT INTO `t_nation` VALUES ('140781', '介休市', '3');
INSERT INTO `t_nation` VALUES ('520203', '六枝特区', '3');
INSERT INTO `t_nation` VALUES ('652928', '阿瓦提县', '3');
INSERT INTO `t_nation` VALUES ('440811', '麻章区', '3');
INSERT INTO `t_nation` VALUES ('430482', '常宁市', '3');
INSERT INTO `t_nation` VALUES ('620721', '肃南裕固族自治县', '3');
INSERT INTO `t_nation` VALUES ('230502', '尖山区', '3');
INSERT INTO `t_nation` VALUES ('130603', '北市区', '3');
INSERT INTO `t_nation` VALUES ('410303', '西工区', '3');
INSERT INTO `t_nation` VALUES ('231225', '明水县', '3');
INSERT INTO `t_nation` VALUES ('360826', '泰和县', '3');
INSERT INTO `t_nation` VALUES ('220521', '通化县', '3');
INSERT INTO `t_nation` VALUES ('513226', '金川县', '3');
INSERT INTO `t_nation` VALUES ('450400', '梧州市', '2');
INSERT INTO `t_nation` VALUES ('510501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320107', '下关区', '3');
INSERT INTO `t_nation` VALUES ('211004', '宏伟区', '3');
INSERT INTO `t_nation` VALUES ('652200', '哈密地区', '2');
INSERT INTO `t_nation` VALUES ('231181', '北安市', '3');
INSERT INTO `t_nation` VALUES ('653023', '阿合奇县', '3');
INSERT INTO `t_nation` VALUES ('370724', '临朐县', '3');
INSERT INTO `t_nation` VALUES ('340600', '淮北市', '2');
INSERT INTO `t_nation` VALUES ('510402', '东区', '3');
INSERT INTO `t_nation` VALUES ('361024', '崇仁县', '3');
INSERT INTO `t_nation` VALUES ('530127', '嵩明县', '3');
INSERT INTO `t_nation` VALUES ('532621', '文山县', '3');
INSERT INTO `t_nation` VALUES ('150901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610902', '汉滨区', '3');
INSERT INTO `t_nation` VALUES ('469036', '琼中黎族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('150581', '霍林郭勒市', '3');
INSERT INTO `t_nation` VALUES ('420000', '湖北省', '1');
INSERT INTO `t_nation` VALUES ('361002', '临川区', '3');
INSERT INTO `t_nation` VALUES ('632100', '海东地区', '2');
INSERT INTO `t_nation` VALUES ('630000', '青海省', '1');
INSERT INTO `t_nation` VALUES ('410202', '龙亭区', '3');
INSERT INTO `t_nation` VALUES ('370700', '潍坊市', '2');
INSERT INTO `t_nation` VALUES ('321282', '靖江市', '3');
INSERT INTO `t_nation` VALUES ('320705', '新浦区', '3');
INSERT INTO `t_nation` VALUES ('110200', '县', '2');
INSERT INTO `t_nation` VALUES ('340826', '宿松县', '3');
INSERT INTO `t_nation` VALUES ('340301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('511529', '屏山县', '3');
INSERT INTO `t_nation` VALUES ('430202', '荷塘区', '3');
INSERT INTO `t_nation` VALUES ('230404', '南山区', '3');
INSERT INTO `t_nation` VALUES ('231282', '肇东市', '3');
INSERT INTO `t_nation` VALUES ('320500', '苏州市', '2');
INSERT INTO `t_nation` VALUES ('210113', '沈北新区', '3');
INSERT INTO `t_nation` VALUES ('411627', '太康县', '3');
INSERT INTO `t_nation` VALUES ('620821', '泾川县', '3');
INSERT INTO `t_nation` VALUES ('510400', '攀枝花市', '2');
INSERT INTO `t_nation` VALUES ('522228', '沿河土家族自治县', '3');
INSERT INTO `t_nation` VALUES ('441581', '陆丰市', '3');
INSERT INTO `t_nation` VALUES ('320000', '江苏省', '1');
INSERT INTO `t_nation` VALUES ('610924', '紫阳县', '3');
INSERT INTO `t_nation` VALUES ('330281', '余姚市', '3');
INSERT INTO `t_nation` VALUES ('350700', '南平市', '2');
INSERT INTO `t_nation` VALUES ('140224', '灵丘县', '3');
INSERT INTO `t_nation` VALUES ('421281', '赤壁市', '3');
INSERT INTO `t_nation` VALUES ('340225', '无为县', '3');
INSERT INTO `t_nation` VALUES ('530121', '呈贡县', '3');
INSERT INTO `t_nation` VALUES ('150921', '卓资县', '3');
INSERT INTO `t_nation` VALUES ('513231', '阿坝县', '3');
INSERT INTO `t_nation` VALUES ('321081', '仪征市', '3');
INSERT INTO `t_nation` VALUES ('310110', '杨浦区', '3');
INSERT INTO `t_nation` VALUES ('150600', '鄂尔多斯市', '2');
INSERT INTO `t_nation` VALUES ('511602', '广安区', '3');
INSERT INTO `t_nation` VALUES ('360782', '南康市', '3');
INSERT INTO `t_nation` VALUES ('210224', '长海县', '3');
INSERT INTO `t_nation` VALUES ('371722', '单县', '3');
INSERT INTO `t_nation` VALUES ('450803', '港南区', '3');
INSERT INTO `t_nation` VALUES ('330482', '平湖市', '3');
INSERT INTO `t_nation` VALUES ('623023', '舟曲县', '3');
INSERT INTO `t_nation` VALUES ('350922', '古田县', '3');
INSERT INTO `t_nation` VALUES ('500225', '大足县', '3');
INSERT INTO `t_nation` VALUES ('360601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('141025', '古县', '3');
INSERT INTO `t_nation` VALUES ('410724', '获嘉县', '3');
INSERT INTO `t_nation` VALUES ('510101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370113', '长清区', '3');
INSERT INTO `t_nation` VALUES ('130127', '高邑县', '3');
INSERT INTO `t_nation` VALUES ('632127', '化隆回族自治县', '3');
INSERT INTO `t_nation` VALUES ('430529', '城步苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('321182', '扬中市', '3');
INSERT INTO `t_nation` VALUES ('421222', '通城县', '3');
INSERT INTO `t_nation` VALUES ('320611', '港闸区', '3');
INSERT INTO `t_nation` VALUES ('441402', '梅江区', '3');
INSERT INTO `t_nation` VALUES ('320981', '东台市', '3');
INSERT INTO `t_nation` VALUES ('441623', '连平县', '3');
INSERT INTO `t_nation` VALUES ('431126', '宁远县', '3');
INSERT INTO `t_nation` VALUES ('621226', '礼县', '3');
INSERT INTO `t_nation` VALUES ('210600', '丹东市', '2');
INSERT INTO `t_nation` VALUES ('445323', '云安县', '3');
INSERT INTO `t_nation` VALUES ('152222', '科尔沁右翼中旗', '3');
INSERT INTO `t_nation` VALUES ('430211', '天元区', '3');
INSERT INTO `t_nation` VALUES ('371626', '邹平县', '3');
INSERT INTO `t_nation` VALUES ('130124', '栾城县', '3');
INSERT INTO `t_nation` VALUES ('130229', '玉田县', '3');
INSERT INTO `t_nation` VALUES ('532501', '个旧市', '3');
INSERT INTO `t_nation` VALUES ('533103', '潞西市', '3');
INSERT INTO `t_nation` VALUES ('652828', '和硕县', '3');
INSERT INTO `t_nation` VALUES ('140722', '左权县', '3');
INSERT INTO `t_nation` VALUES ('350902', '蕉城区', '3');
INSERT INTO `t_nation` VALUES ('321311', '宿豫区', '3');
INSERT INTO `t_nation` VALUES ('421023', '监利县', '3');
INSERT INTO `t_nation` VALUES ('130901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330000', '浙江省', '1');
INSERT INTO `t_nation` VALUES ('429004', '仙桃市', '3');
INSERT INTO `t_nation` VALUES ('610827', '米脂县', '3');
INSERT INTO `t_nation` VALUES ('440605', '南海区', '3');
INSERT INTO `t_nation` VALUES ('220181', '九台市', '3');
INSERT INTO `t_nation` VALUES ('511401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('659003', '图木舒克市', '3');
INSERT INTO `t_nation` VALUES ('431222', '沅陵县', '3');
INSERT INTO `t_nation` VALUES ('431022', '宜章县', '3');
INSERT INTO `t_nation` VALUES ('350206', '湖里区', '3');
INSERT INTO `t_nation` VALUES ('320507', '相城区', '3');
INSERT INTO `t_nation` VALUES ('130130', '无极县', '3');
INSERT INTO `t_nation` VALUES ('330600', '绍兴市', '2');
INSERT INTO `t_nation` VALUES ('451202', '金城江区', '3');
INSERT INTO `t_nation` VALUES ('230307', '麻山区', '3');
INSERT INTO `t_nation` VALUES ('620321', '永昌县', '3');
INSERT INTO `t_nation` VALUES ('420921', '孝昌县', '3');
INSERT INTO `t_nation` VALUES ('542123', '贡觉县', '3');
INSERT INTO `t_nation` VALUES ('330402', '秀城区', '3');
INSERT INTO `t_nation` VALUES ('450322', '临桂县', '3');
INSERT INTO `t_nation` VALUES ('522326', '望谟县', '3');
INSERT INTO `t_nation` VALUES ('131102', '桃城区', '3');
INSERT INTO `t_nation` VALUES ('532328', '元谋县', '3');
INSERT INTO `t_nation` VALUES ('222401', '延吉市', '3');
INSERT INTO `t_nation` VALUES ('469030', '白沙黎族自治县', '3');
INSERT INTO `t_nation` VALUES ('371326', '平邑县', '3');
INSERT INTO `t_nation` VALUES ('370403', '薛城区', '3');
INSERT INTO `t_nation` VALUES ('210404', '望花区', '3');
INSERT INTO `t_nation` VALUES ('632123', '乐都县', '3');
INSERT INTO `t_nation` VALUES ('530829', '西盟佤族自治县', '3');
INSERT INTO `t_nation` VALUES ('610722', '城固县', '3');
INSERT INTO `t_nation` VALUES ('150727', '新巴尔虎右旗', '3');
INSERT INTO `t_nation` VALUES ('141181', '孝义市', '3');
INSERT INTO `t_nation` VALUES ('130501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410928', '濮阳县', '3');
INSERT INTO `t_nation` VALUES ('130628', '高阳县', '3');
INSERT INTO `t_nation` VALUES ('513323', '丹巴县', '3');
INSERT INTO `t_nation` VALUES ('150824', '乌拉特中旗', '3');
INSERT INTO `t_nation` VALUES ('310117', '松江区', '3');
INSERT INTO `t_nation` VALUES ('320114', '雨花台区', '3');
INSERT INTO `t_nation` VALUES ('230303', '恒山区', '3');
INSERT INTO `t_nation` VALUES ('430424', '衡东县', '3');
INSERT INTO `t_nation` VALUES ('522424', '金沙县', '3');
INSERT INTO `t_nation` VALUES ('411721', '西平县', '3');
INSERT INTO `t_nation` VALUES ('150724', '鄂温克族自治旗', '3');
INSERT INTO `t_nation` VALUES ('522223', '玉屏侗族自治县', '3');
INSERT INTO `t_nation` VALUES ('430626', '平江县', '3');
INSERT INTO `t_nation` VALUES ('510724', '安县', '3');
INSERT INTO `t_nation` VALUES ('350783', '建瓯市', '3');
INSERT INTO `t_nation` VALUES ('340601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('421224', '通山县', '3');
INSERT INTO `t_nation` VALUES ('361029', '东乡县', '3');
INSERT INTO `t_nation` VALUES ('320921', '响水县', '3');
INSERT INTO `t_nation` VALUES ('120114', '武清区', '3');
INSERT INTO `t_nation` VALUES ('410102', '中原区', '3');
INSERT INTO `t_nation` VALUES ('441423', '丰顺县', '3');
INSERT INTO `t_nation` VALUES ('542621', '林芝县', '3');
INSERT INTO `t_nation` VALUES ('360822', '吉水县', '3');
INSERT INTO `t_nation` VALUES ('371522', '莘县', '3');
INSERT INTO `t_nation` VALUES ('451222', '天峨县', '3');
INSERT INTO `t_nation` VALUES ('370883', '邹城市', '3');
INSERT INTO `t_nation` VALUES ('210727', '义县', '3');
INSERT INTO `t_nation` VALUES ('150926', '察哈尔右翼前旗', '3');
INSERT INTO `t_nation` VALUES ('360105', '湾里区', '3');
INSERT INTO `t_nation` VALUES ('542624', '墨脱县', '3');
INSERT INTO `t_nation` VALUES ('210624', '宽甸满族自治县', '3');
INSERT INTO `t_nation` VALUES ('410581', '林州市', '3');
INSERT INTO `t_nation` VALUES ('420922', '大悟县', '3');
INSERT INTO `t_nation` VALUES ('411624', '沈丘县', '3');
INSERT INTO `t_nation` VALUES ('130530', '新河县', '3');
INSERT INTO `t_nation` VALUES ('370902', '泰山区', '3');
INSERT INTO `t_nation` VALUES ('131121', '枣强县', '3');
INSERT INTO `t_nation` VALUES ('371329', '临沭县', '3');
INSERT INTO `t_nation` VALUES ('440982', '化州市', '3');
INSERT INTO `t_nation` VALUES ('420100', '武汉市', '2');
INSERT INTO `t_nation` VALUES ('230112', '阿城区', '3');
INSERT INTO `t_nation` VALUES ('420202', '黄石港区', '3');
INSERT INTO `t_nation` VALUES ('610326', '眉县', '3');
INSERT INTO `t_nation` VALUES ('451423', '龙州县', '3');
INSERT INTO `t_nation` VALUES ('511802', '雨城区', '3');
INSERT INTO `t_nation` VALUES ('360983', '高安市', '3');
INSERT INTO `t_nation` VALUES ('500106', '沙坪坝区', '3');
INSERT INTO `t_nation` VALUES ('340521', '当涂县', '3');
INSERT INTO `t_nation` VALUES ('140727', '祁县', '3');
INSERT INTO `t_nation` VALUES ('511923', '平昌县', '3');
INSERT INTO `t_nation` VALUES ('410106', '上街区', '3');
INSERT INTO `t_nation` VALUES ('341700', '池州市', '2');
INSERT INTO `t_nation` VALUES ('450127', '横县', '3');
INSERT INTO `t_nation` VALUES ('610521', '华县', '3');
INSERT INTO `t_nation` VALUES ('361130', '婺源县', '3');
INSERT INTO `t_nation` VALUES ('542623', '米林县', '3');
INSERT INTO `t_nation` VALUES ('340202', '镜湖区', '3');
INSERT INTO `t_nation` VALUES ('410482', '汝州市', '3');
INSERT INTO `t_nation` VALUES ('450224', '融安县', '3');
INSERT INTO `t_nation` VALUES ('652100', '吐鲁番地区', '2');
INSERT INTO `t_nation` VALUES ('622900', '临夏回族自治州', '2');
INSERT INTO `t_nation` VALUES ('360202', '昌江区', '3');
INSERT INTO `t_nation` VALUES ('430426', '祁东县', '3');
INSERT INTO `t_nation` VALUES ('330203', '海曙区', '3');
INSERT INTO `t_nation` VALUES ('330784', '永康市', '3');
INSERT INTO `t_nation` VALUES ('370100', '济南市', '2');
INSERT INTO `t_nation` VALUES ('140928', '五寨县', '3');
INSERT INTO `t_nation` VALUES ('451425', '天等县', '3');
INSERT INTO `t_nation` VALUES ('370303', '张店区', '3');
INSERT INTO `t_nation` VALUES ('511303', '高坪区', '3');
INSERT INTO `t_nation` VALUES ('411625', '郸城县', '3');
INSERT INTO `t_nation` VALUES ('210311', '千山区', '3');
INSERT INTO `t_nation` VALUES ('430601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210281', '瓦房店市', '3');
INSERT INTO `t_nation` VALUES ('340402', '大通区', '3');
INSERT INTO `t_nation` VALUES ('371700', '菏泽市', '2');
INSERT INTO `t_nation` VALUES ('611000', '商洛市', '2');
INSERT INTO `t_nation` VALUES ('445224', '惠来县', '3');
INSERT INTO `t_nation` VALUES ('511781', '万源市', '3');
INSERT INTO `t_nation` VALUES ('141028', '吉县', '3');
INSERT INTO `t_nation` VALUES ('370830', '汶上县', '3');
INSERT INTO `t_nation` VALUES ('230601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360521', '分宜县', '3');
INSERT INTO `t_nation` VALUES ('653100', '喀什地区', '2');
INSERT INTO `t_nation` VALUES ('360481', '瑞昌市', '3');
INSERT INTO `t_nation` VALUES ('542622', '工布江达县', '3');
INSERT INTO `t_nation` VALUES ('321000', '扬州市', '2');
INSERT INTO `t_nation` VALUES ('361121', '上饶县', '3');
INSERT INTO `t_nation` VALUES ('431121', '祁阳县', '3');
INSERT INTO `t_nation` VALUES ('130930', '孟村回族自治县', '3');
INSERT INTO `t_nation` VALUES ('430103', '天心区', '3');
INSERT INTO `t_nation` VALUES ('210321', '台安县', '3');
INSERT INTO `t_nation` VALUES ('360926', '铜鼓县', '3');
INSERT INTO `t_nation` VALUES ('640104', '兴庆区', '3');
INSERT INTO `t_nation` VALUES ('140502', '城区', '3');
INSERT INTO `t_nation` VALUES ('520381', '赤水市', '3');
INSERT INTO `t_nation` VALUES ('654003', '奎屯市', '3');
INSERT INTO `t_nation` VALUES ('430726', '石门县', '3');
INSERT INTO `t_nation` VALUES ('320723', '灌云县', '3');
INSERT INTO `t_nation` VALUES ('520114', '小河区', '3');
INSERT INTO `t_nation` VALUES ('623024', '迭部县', '3');
INSERT INTO `t_nation` VALUES ('321201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('451029', '田林县', '3');
INSERT INTO `t_nation` VALUES ('330702', '婺城区', '3');
INSERT INTO `t_nation` VALUES ('211201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410182', '荥阳市', '3');
INSERT INTO `t_nation` VALUES ('130304', '北戴河区', '3');
INSERT INTO `t_nation` VALUES ('611001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('350600', '漳州市', '2');
INSERT INTO `t_nation` VALUES ('130121', '井陉县', '3');
INSERT INTO `t_nation` VALUES ('320706', '海州区', '3');
INSERT INTO `t_nation` VALUES ('350128', '平潭县', '3');
INSERT INTO `t_nation` VALUES ('420602', '襄城区', '3');
INSERT INTO `t_nation` VALUES ('120200', '县', '2');
INSERT INTO `t_nation` VALUES ('130924', '海兴县', '3');
INSERT INTO `t_nation` VALUES ('350181', '福清市', '3');
INSERT INTO `t_nation` VALUES ('621021', '庆城县', '3');
INSERT INTO `t_nation` VALUES ('511421', '仁寿县', '3');
INSERT INTO `t_nation` VALUES ('430122', '望城县', '3');
INSERT INTO `t_nation` VALUES ('120108', '汉沽区', '3');
INSERT INTO `t_nation` VALUES ('421122', '红安县', '3');
INSERT INTO `t_nation` VALUES ('370212', '崂山区', '3');
INSERT INTO `t_nation` VALUES ('130728', '怀安县', '3');
INSERT INTO `t_nation` VALUES ('532529', '红河县', '3');
INSERT INTO `t_nation` VALUES ('130406', '峰峰矿区', '3');
INSERT INTO `t_nation` VALUES ('330204', '江东区', '3');
INSERT INTO `t_nation` VALUES ('610721', '南郑县', '3');
INSERT INTO `t_nation` VALUES ('230702', '伊春区', '3');
INSERT INTO `t_nation` VALUES ('623000', '甘南藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('511011', '东兴区', '3');
INSERT INTO `t_nation` VALUES ('370124', '平阴县', '3');
INSERT INTO `t_nation` VALUES ('450423', '蒙山县', '3');
INSERT INTO `t_nation` VALUES ('340803', '大观区', '3');
INSERT INTO `t_nation` VALUES ('620523', '甘谷县', '3');
INSERT INTO `t_nation` VALUES ('522725', '瓮安县', '3');
INSERT INTO `t_nation` VALUES ('420526', '兴山县', '3');
INSERT INTO `t_nation` VALUES ('150500', '通辽市', '2');
INSERT INTO `t_nation` VALUES ('510923', '大英县', '3');
INSERT INTO `t_nation` VALUES ('440101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('350303', '涵江区', '3');
INSERT INTO `t_nation` VALUES ('361001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220221', '永吉县', '3');
INSERT INTO `t_nation` VALUES ('150621', '达拉特旗', '3');
INSERT INTO `t_nation` VALUES ('532900', '大理白族自治州', '2');
INSERT INTO `t_nation` VALUES ('530323', '师宗县', '3');
INSERT INTO `t_nation` VALUES ('440114', '花都区', '3');
INSERT INTO `t_nation` VALUES ('340603', '相山区', '3');
INSERT INTO `t_nation` VALUES ('340822', '怀宁县', '3');
INSERT INTO `t_nation` VALUES ('610582', '华阴市', '3');
INSERT INTO `t_nation` VALUES ('331002', '椒江区', '3');
INSERT INTO `t_nation` VALUES ('610303', '金台区', '3');
INSERT INTO `t_nation` VALUES ('140109', '万柏林区', '3');
INSERT INTO `t_nation` VALUES ('441501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210681', '东港市', '3');
INSERT INTO `t_nation` VALUES ('341800', '宣城市', '2');
INSERT INTO `t_nation` VALUES ('620104', '西固区', '3');
INSERT INTO `t_nation` VALUES ('440983', '信宜市', '3');
INSERT INTO `t_nation` VALUES ('410421', '宝丰县', '3');
INSERT INTO `t_nation` VALUES ('620621', '民勤县', '3');
INSERT INTO `t_nation` VALUES ('320902', '亭湖区', '3');
INSERT INTO `t_nation` VALUES ('530922', '云县', '3');
INSERT INTO `t_nation` VALUES ('110100', '市辖区', '2');
INSERT INTO `t_nation` VALUES ('210505', '南芬区', '3');
INSERT INTO `t_nation` VALUES ('150403', '元宝山区', '3');
INSERT INTO `t_nation` VALUES ('511100', '乐山市', '2');
INSERT INTO `t_nation` VALUES ('411222', '陕县', '3');
INSERT INTO `t_nation` VALUES ('360724', '上犹县', '3');
INSERT INTO `t_nation` VALUES ('150103', '回民区', '3');
INSERT INTO `t_nation` VALUES ('220282', '桦甸市', '3');
INSERT INTO `t_nation` VALUES ('511826', '芦山县', '3');
INSERT INTO `t_nation` VALUES ('421024', '江陵县', '3');
INSERT INTO `t_nation` VALUES ('140921', '定襄县', '3');
INSERT INTO `t_nation` VALUES ('621123', '渭源县', '3');
INSERT INTO `t_nation` VALUES ('652327', '吉木萨尔县', '3');
INSERT INTO `t_nation` VALUES ('440222', '始兴县', '3');
INSERT INTO `t_nation` VALUES ('360701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130527', '南和县', '3');
INSERT INTO `t_nation` VALUES ('150928', '察哈尔右翼后旗', '3');
INSERT INTO `t_nation` VALUES ('522301', '兴义市', '3');
INSERT INTO `t_nation` VALUES ('632823', '天峻县', '3');
INSERT INTO `t_nation` VALUES ('440404', '金湾区', '3');
INSERT INTO `t_nation` VALUES ('130534', '清河县', '3');
INSERT INTO `t_nation` VALUES ('451030', '西林县', '3');
INSERT INTO `t_nation` VALUES ('220402', '龙山区', '3');
INSERT INTO `t_nation` VALUES ('513426', '会东县', '3');
INSERT INTO `t_nation` VALUES ('610424', '乾县', '3');
INSERT INTO `t_nation` VALUES ('650300', '石河子市', '2');
INSERT INTO `t_nation` VALUES ('140122', '阳曲县', '3');
INSERT INTO `t_nation` VALUES ('450331', '荔蒲县', '3');
INSERT INTO `t_nation` VALUES ('310000', '上海市', '1');
INSERT INTO `t_nation` VALUES ('140830', '芮城县', '3');
INSERT INTO `t_nation` VALUES ('441781', '阳春市', '3');
INSERT INTO `t_nation` VALUES ('441901', '东莞市市辖区', '3');
INSERT INTO `t_nation` VALUES ('422828', '鹤峰县', '3');
INSERT INTO `t_nation` VALUES ('130928', '吴桥县', '3');
INSERT INTO `t_nation` VALUES ('340208', '三山区', '3');
INSERT INTO `t_nation` VALUES ('420114', '蔡甸区', '3');
INSERT INTO `t_nation` VALUES ('520201', '钟山区', '3');
INSERT INTO `t_nation` VALUES ('451123', '富川瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('500116', '江津区', '3');
INSERT INTO `t_nation` VALUES ('320924', '射阳县', '3');
INSERT INTO `t_nation` VALUES ('411000', '许昌市', '2');
INSERT INTO `t_nation` VALUES ('654221', '额敏县', '3');
INSERT INTO `t_nation` VALUES ('360322', '上栗县', '3');
INSERT INTO `t_nation` VALUES ('513230', '壤塘县', '3');
INSERT INTO `t_nation` VALUES ('431125', '江永县', '3');
INSERT INTO `t_nation` VALUES ('140901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320584', '吴江市', '3');
INSERT INTO `t_nation` VALUES ('360222', '浮梁县', '3');
INSERT INTO `t_nation` VALUES ('371428', '武城县', '3');
INSERT INTO `t_nation` VALUES ('654325', '青河县', '3');
INSERT INTO `t_nation` VALUES ('230803', '向阳区', '3');
INSERT INTO `t_nation` VALUES ('330602', '越城区', '3');
INSERT INTO `t_nation` VALUES ('530901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530721', '玉龙纳西族自治县', '3');
INSERT INTO `t_nation` VALUES ('370725', '昌乐县', '3');
INSERT INTO `t_nation` VALUES ('520123', '修文县', '3');
INSERT INTO `t_nation` VALUES ('130302', '海港区', '3');
INSERT INTO `t_nation` VALUES ('360827', '遂川县', '3');
INSERT INTO `t_nation` VALUES ('530101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610831', '子洲县', '3');
INSERT INTO `t_nation` VALUES ('130522', '临城县', '3');
INSERT INTO `t_nation` VALUES ('420115', '江夏区', '3');
INSERT INTO `t_nation` VALUES ('140301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210202', '中山区', '3');
INSERT INTO `t_nation` VALUES ('440800', '湛江市', '2');
INSERT INTO `t_nation` VALUES ('522631', '黎平县', '3');
INSERT INTO `t_nation` VALUES ('451227', '巴马瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('532528', '元阳县', '3');
INSERT INTO `t_nation` VALUES ('220000', '吉林省', '1');
INSERT INTO `t_nation` VALUES ('130404', '复兴区', '3');
INSERT INTO `t_nation` VALUES ('530400', '玉溪市', '2');
INSERT INTO `t_nation` VALUES ('320582', '张家港市', '3');
INSERT INTO `t_nation` VALUES ('131001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370783', '寿光市', '3');
INSERT INTO `t_nation` VALUES ('130123', '正定县', '3');
INSERT INTO `t_nation` VALUES ('210201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440200', '韶关市', '2');
INSERT INTO `t_nation` VALUES ('130181', '辛集市', '3');
INSERT INTO `t_nation` VALUES ('230111', '呼兰区', '3');
INSERT INTO `t_nation` VALUES ('710000', '台湾省', '1');
INSERT INTO `t_nation` VALUES ('511601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('513430', '金阳县', '3');
INSERT INTO `t_nation` VALUES ('623026', '碌曲县', '3');
INSERT INTO `t_nation` VALUES ('371481', '乐陵市', '3');
INSERT INTO `t_nation` VALUES ('410108', '惠济区', '3');
INSERT INTO `t_nation` VALUES ('542129', '芒康县', '3');
INSERT INTO `t_nation` VALUES ('350100', '福州市', '2');
INSERT INTO `t_nation` VALUES ('450601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('522324', '晴隆县', '3');
INSERT INTO `t_nation` VALUES ('500237', '巫山县', '3');
INSERT INTO `t_nation` VALUES ('141102', '离石区', '3');
INSERT INTO `t_nation` VALUES ('340621', '濉溪县', '3');
INSERT INTO `t_nation` VALUES ('522624', '三穗县', '3');
INSERT INTO `t_nation` VALUES ('340100', '合肥市', '2');
INSERT INTO `t_nation` VALUES ('411201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410225', '兰考县', '3');
INSERT INTO `t_nation` VALUES ('530325', '富源县', '3');
INSERT INTO `t_nation` VALUES ('610204', '耀州区', '3');
INSERT INTO `t_nation` VALUES ('230700', '伊春市', '2');
INSERT INTO `t_nation` VALUES ('4419', '长平镇', '3');
INSERT INTO `t_nation` VALUES ('621026', '宁县', '3');
INSERT INTO `t_nation` VALUES ('533100', '德宏傣族景颇族自治州', '2');
INSERT INTO `t_nation` VALUES ('640401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('420104', '硚口区', '3');
INSERT INTO `t_nation` VALUES ('441802', '清城区', '3');
INSERT INTO `t_nation` VALUES ('152502', '锡林浩特市', '3');
INSERT INTO `t_nation` VALUES ('370600', '烟台市', '2');
INSERT INTO `t_nation` VALUES ('150822', '磴口县', '3');
INSERT INTO `t_nation` VALUES ('632723', '称多县', '3');
INSERT INTO `t_nation` VALUES ('340322', '五河县', '3');
INSERT INTO `t_nation` VALUES ('440783', '开平市', '3');
INSERT INTO `t_nation` VALUES ('445301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130804', '鹰手营子矿区', '3');
INSERT INTO `t_nation` VALUES ('620105', '安宁区', '3');
INSERT INTO `t_nation` VALUES ('211322', '建平县', '3');
INSERT INTO `t_nation` VALUES ('522635', '麻江县', '3');
INSERT INTO `t_nation` VALUES ('520423', '镇宁布依族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('450422', '藤县', '3');
INSERT INTO `t_nation` VALUES ('370611', '福山区', '3');
INSERT INTO `t_nation` VALUES ('320300', '徐州市', '2');
INSERT INTO `t_nation` VALUES ('361022', '黎川县', '3');
INSERT INTO `t_nation` VALUES ('511300', '南充市', '2');
INSERT INTO `t_nation` VALUES ('532930', '洱源县', '3');
INSERT INTO `t_nation` VALUES ('411324', '镇平县', '3');
INSERT INTO `t_nation` VALUES ('321284', '姜堰市', '3');
INSERT INTO `t_nation` VALUES ('341300', '宿州市', '2');
INSERT INTO `t_nation` VALUES ('441601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('433127', '永顺县', '3');
INSERT INTO `t_nation` VALUES ('350603', '龙文区', '3');
INSERT INTO `t_nation` VALUES ('520329', '余庆县', '3');
INSERT INTO `t_nation` VALUES ('222424', '汪清县', '3');
INSERT INTO `t_nation` VALUES ('522633', '从江县', '3');
INSERT INTO `t_nation` VALUES ('610526', '蒲城县', '3');
INSERT INTO `t_nation` VALUES ('140702', '榆次区', '3');
INSERT INTO `t_nation` VALUES ('411381', '邓州市', '3');
INSERT INTO `t_nation` VALUES ('652324', '玛纳斯县', '3');
INSERT INTO `t_nation` VALUES ('360302', '安源区', '3');
INSERT INTO `t_nation` VALUES ('411301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220403', '西安区', '3');
INSERT INTO `t_nation` VALUES ('371526', '高唐县', '3');
INSERT INTO `t_nation` VALUES ('220104', '朝阳区', '3');
INSERT INTO `t_nation` VALUES ('350527', '金门县', '3');
INSERT INTO `t_nation` VALUES ('410403', '卫东区', '3');
INSERT INTO `t_nation` VALUES ('350203', '思明区', '3');
INSERT INTO `t_nation` VALUES ('450500', '北海市', '2');
INSERT INTO `t_nation` VALUES ('411425', '虞城县', '3');
INSERT INTO `t_nation` VALUES ('500119', '南川区', '3');
INSERT INTO `t_nation` VALUES ('440514', '潮南区', '3');
INSERT INTO `t_nation` VALUES ('140621', '山阴县', '3');
INSERT INTO `t_nation` VALUES ('350500', '泉州市', '2');
INSERT INTO `t_nation` VALUES ('652302', '阜康市', '3');
INSERT INTO `t_nation` VALUES ('360825', '永丰县', '3');
INSERT INTO `t_nation` VALUES ('441427', '蕉岭县', '3');
INSERT INTO `t_nation` VALUES ('530302', '麒麟区', '3');
INSERT INTO `t_nation` VALUES ('451402', '江洲区', '3');
INSERT INTO `t_nation` VALUES ('530602', '昭阳区', '3');
INSERT INTO `t_nation` VALUES ('542429', '巴青县', '3');
INSERT INTO `t_nation` VALUES ('653024', '乌恰县', '3');
INSERT INTO `t_nation` VALUES ('140225', '浑源县', '3');
INSERT INTO `t_nation` VALUES ('610126', '高陵县', '3');
INSERT INTO `t_nation` VALUES ('450881', '桂平市', '3');
INSERT INTO `t_nation` VALUES ('500231', '垫江县', '3');
INSERT INTO `t_nation` VALUES ('220322', '梨树县', '3');
INSERT INTO `t_nation` VALUES ('371500', '聊城市', '2');
INSERT INTO `t_nation` VALUES ('210904', '太平区', '3');
INSERT INTO `t_nation` VALUES ('450102', '兴宁区', '3');
INSERT INTO `t_nation` VALUES ('371702', '牡丹区', '3');
INSERT INTO `t_nation` VALUES ('140302', '城区', '3');
INSERT INTO `t_nation` VALUES ('231024', '东宁县', '3');
INSERT INTO `t_nation` VALUES ('440203', '武江区', '3');
INSERT INTO `t_nation` VALUES ('445281', '普宁市', '3');
INSERT INTO `t_nation` VALUES ('441701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320482', '金坛市', '3');
INSERT INTO `t_nation` VALUES ('341323', '灵璧县', '3');
INSERT INTO `t_nation` VALUES ('230225', '甘南县', '3');
INSERT INTO `t_nation` VALUES ('450301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530723', '华坪县', '3');
INSERT INTO `t_nation` VALUES ('410923', '南乐县', '3');
INSERT INTO `t_nation` VALUES ('340304', '禹会区', '3');
INSERT INTO `t_nation` VALUES ('410301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('420606', '樊城区', '3');
INSERT INTO `t_nation` VALUES ('532923', '祥云县', '3');
INSERT INTO `t_nation` VALUES ('640500', '中卫市', '2');
INSERT INTO `t_nation` VALUES ('542124', '类乌齐县', '3');
INSERT INTO `t_nation` VALUES ('530927', '沧源佤族自治县', '3');
INSERT INTO `t_nation` VALUES ('542221', '乃东县', '3');
INSERT INTO `t_nation` VALUES ('330727', '磐安县', '3');
INSERT INTO `t_nation` VALUES ('469001', '五指山市', '3');
INSERT INTO `t_nation` VALUES ('530322', '陆良县', '3');
INSERT INTO `t_nation` VALUES ('533324', '贡山独龙族怒族自治县', '3');
INSERT INTO `t_nation` VALUES ('542423', '比如县', '3');
INSERT INTO `t_nation` VALUES ('652323', '呼图壁县', '3');
INSERT INTO `t_nation` VALUES ('430300', '湘潭市', '2');
INSERT INTO `t_nation` VALUES ('150303', '海南区', '3');
INSERT INTO `t_nation` VALUES ('341282', '界首市', '3');
INSERT INTO `t_nation` VALUES ('513433', '冕宁县', '3');
INSERT INTO `t_nation` VALUES ('511524', '长宁县', '3');
INSERT INTO `t_nation` VALUES ('330822', '常山县', '3');
INSERT INTO `t_nation` VALUES ('610624', '安塞县', '3');
INSERT INTO `t_nation` VALUES ('610111', '灞桥区', '3');
INSERT INTO `t_nation` VALUES ('210882', '大石桥市', '3');
INSERT INTO `t_nation` VALUES ('411501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330326', '平阳县', '3');
INSERT INTO `t_nation` VALUES ('610502', '临渭区', '3');
INSERT INTO `t_nation` VALUES ('440308', '盐田区', '3');
INSERT INTO `t_nation` VALUES ('210123', '康平县', '3');
INSERT INTO `t_nation` VALUES ('110107', '石景山区', '3');
INSERT INTO `t_nation` VALUES ('230200', '齐齐哈尔市', '2');
INSERT INTO `t_nation` VALUES ('530628', '彝良县', '3');
INSERT INTO `t_nation` VALUES ('520302', '红花岗区', '3');
INSERT INTO `t_nation` VALUES ('431122', '东安县', '3');
INSERT INTO `t_nation` VALUES ('640501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210682', '凤城市', '3');
INSERT INTO `t_nation` VALUES ('610725', '勉县', '3');
INSERT INTO `t_nation` VALUES ('141033', '蒲县', '3');
INSERT INTO `t_nation` VALUES ('130185', '鹿泉市', '3');
INSERT INTO `t_nation` VALUES ('361127', '余干县', '3');
INSERT INTO `t_nation` VALUES ('450225', '融水苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('210301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130705', '宣化区', '3');
INSERT INTO `t_nation` VALUES ('620401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('511825', '天全县', '3');
INSERT INTO `t_nation` VALUES ('350625', '长泰县', '3');
INSERT INTO `t_nation` VALUES ('513431', '昭觉县', '3');
INSERT INTO `t_nation` VALUES ('230805', '东风区', '3');
INSERT INTO `t_nation` VALUES ('522701', '都匀市', '3');
INSERT INTO `t_nation` VALUES ('110108', '海淀区', '3');
INSERT INTO `t_nation` VALUES ('522626', '岑巩县', '3');
INSERT INTO `t_nation` VALUES ('340604', '烈山区', '3');
INSERT INTO `t_nation` VALUES ('530113', '东川区', '3');
INSERT INTO `t_nation` VALUES ('469038', '南沙群岛', '3');
INSERT INTO `t_nation` VALUES ('410803', '中站区', '3');
INSERT INTO `t_nation` VALUES ('210403', '东洲区', '3');
INSERT INTO `t_nation` VALUES ('621224', '康县', '3');
INSERT INTO `t_nation` VALUES ('141081', '侯马市', '3');
INSERT INTO `t_nation` VALUES ('532823', '勐腊县', '3');
INSERT INTO `t_nation` VALUES ('210212', '旅顺口区', '3');
INSERT INTO `t_nation` VALUES ('350125', '永泰县', '3');
INSERT INTO `t_nation` VALUES ('360881', '井冈山市', '3');
INSERT INTO `t_nation` VALUES ('532522', '蒙自县', '3');
INSERT INTO `t_nation` VALUES ('610631', '黄龙县', '3');
INSERT INTO `t_nation` VALUES ('621001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320502', '沧浪区', '3');
INSERT INTO `t_nation` VALUES ('421121', '团风县', '3');
INSERT INTO `t_nation` VALUES ('421221', '嘉鱼县', '3');
INSERT INTO `t_nation` VALUES ('130827', '宽城满族自治县', '3');
INSERT INTO `t_nation` VALUES ('451121', '昭平县', '3');
INSERT INTO `t_nation` VALUES ('230223', '依安县', '3');
INSERT INTO `t_nation` VALUES ('420116', '黄陂区', '3');
INSERT INTO `t_nation` VALUES ('522426', '纳雍县', '3');
INSERT INTO `t_nation` VALUES ('130900', '沧州市', '2');
INSERT INTO `t_nation` VALUES ('522425', '织金县', '3');
INSERT INTO `t_nation` VALUES ('513200', '阿坝藏族羌族自治州', '2');
INSERT INTO `t_nation` VALUES ('140728', '平遥县', '3');
INSERT INTO `t_nation` VALUES ('370103', '市中区', '3');
INSERT INTO `t_nation` VALUES ('533124', '陇川县', '3');
INSERT INTO `t_nation` VALUES ('220602', '八道江区', '3');
INSERT INTO `t_nation` VALUES ('450332', '恭城瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('511801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('430781', '津市市', '3');
INSERT INTO `t_nation` VALUES ('441323', '惠东县', '3');
INSERT INTO `t_nation` VALUES ('360423', '武宁县', '3');
INSERT INTO `t_nation` VALUES ('431027', '桂东县', '3');
INSERT INTO `t_nation` VALUES ('469039', '中沙群岛的岛礁及其海域', '3');
INSERT INTO `t_nation` VALUES ('410201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('420802', '东宝区', '3');
INSERT INTO `t_nation` VALUES ('210901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210521', '本溪满族自治县', '3');
INSERT INTO `t_nation` VALUES ('320982', '大丰市', '3');
INSERT INTO `t_nation` VALUES ('320211', '滨湖区', '3');
INSERT INTO `t_nation` VALUES ('410505', '殷都区', '3');
INSERT INTO `t_nation` VALUES ('350581', '石狮市', '3');
INSERT INTO `t_nation` VALUES ('421301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('510124', '郫县', '3');
INSERT INTO `t_nation` VALUES ('210211', '甘井子区', '3');
INSERT INTO `t_nation` VALUES ('654223', '沙湾县', '3');
INSERT INTO `t_nation` VALUES ('310109', '虹口区', '3');
INSERT INTO `t_nation` VALUES ('211011', '太子河区', '3');
INSERT INTO `t_nation` VALUES ('230100', '哈尔滨市', '2');
INSERT INTO `t_nation` VALUES ('110116', '怀柔区', '3');
INSERT INTO `t_nation` VALUES ('440823', '遂溪县', '3');
INSERT INTO `t_nation` VALUES ('370202', '市南区', '3');
INSERT INTO `t_nation` VALUES ('210881', '盖州市', '3');
INSERT INTO `t_nation` VALUES ('130729', '万全县', '3');
INSERT INTO `t_nation` VALUES ('542524', '日土县', '3');
INSERT INTO `t_nation` VALUES ('340201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320412', '武进区', '3');
INSERT INTO `t_nation` VALUES ('140524', '陵川县', '3');
INSERT INTO `t_nation` VALUES ('350626', '东山县', '3');
INSERT INTO `t_nation` VALUES ('140602', '朔城区', '3');
INSERT INTO `t_nation` VALUES ('130429', '永年县', '3');
INSERT INTO `t_nation` VALUES ('610113', '雁塔区', '3');
INSERT INTO `t_nation` VALUES ('370125', '济阳县', '3');
INSERT INTO `t_nation` VALUES ('350302', '城厢区', '3');
INSERT INTO `t_nation` VALUES ('231281', '安达市', '3');
INSERT INTO `t_nation` VALUES ('150821', '五原县', '3');
INSERT INTO `t_nation` VALUES ('330801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('450321', '阳朔县', '3');
INSERT INTO `t_nation` VALUES ('231003', '阳明区', '3');
INSERT INTO `t_nation` VALUES ('532801', '景洪市', '3');
INSERT INTO `t_nation` VALUES ('532523', '屏边苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('433123', '凤凰县', '3');
INSERT INTO `t_nation` VALUES ('620623', '天祝藏族自治县', '3');
INSERT INTO `t_nation` VALUES ('532524', '建水县', '3');
INSERT INTO `t_nation` VALUES ('340321', '怀远县', '3');
INSERT INTO `t_nation` VALUES ('371201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('654021', '伊宁县', '3');
INSERT INTO `t_nation` VALUES ('532925', '弥渡县', '3');
INSERT INTO `t_nation` VALUES ('330127', '淳安县', '3');
INSERT INTO `t_nation` VALUES ('310230', '崇明县', '3');
INSERT INTO `t_nation` VALUES ('542329', '白朗县', '3');
INSERT INTO `t_nation` VALUES ('320602', '崇川区', '3');
INSERT INTO `t_nation` VALUES ('532331', '禄丰县', '3');
INSERT INTO `t_nation` VALUES ('411726', '泌阳县', '3');
INSERT INTO `t_nation` VALUES ('532326', '大姚县', '3');
INSERT INTO `t_nation` VALUES ('341524', '金寨县', '3');
INSERT INTO `t_nation` VALUES ('430822', '桑植县', '3');
INSERT INTO `t_nation` VALUES ('231001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140600', '朔州市', '2');
INSERT INTO `t_nation` VALUES ('130403', '丛台区', '3');
INSERT INTO `t_nation` VALUES ('650201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542625', '波密县', '3');
INSERT INTO `t_nation` VALUES ('230102', '道里区', '3');
INSERT INTO `t_nation` VALUES ('441200', '肇庆市', '2');
INSERT INTO `t_nation` VALUES ('211122', '盘山县', '3');
INSERT INTO `t_nation` VALUES ('320900', '盐城市', '2');
INSERT INTO `t_nation` VALUES ('450101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('513329', '新龙县', '3');
INSERT INTO `t_nation` VALUES ('310100', '市辖区', '2');
INSERT INTO `t_nation` VALUES ('652328', '木垒哈萨克自治县', '3');
INSERT INTO `t_nation` VALUES ('653121', '疏附县', '3');
INSERT INTO `t_nation` VALUES ('511101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('632221', '门源回族自治县', '3');
INSERT INTO `t_nation` VALUES ('530900', '临沧市', '2');
INSERT INTO `t_nation` VALUES ('440881', '廉江市', '3');
INSERT INTO `t_nation` VALUES ('360425', '永修县', '3');
INSERT INTO `t_nation` VALUES ('450305', '七星区', '3');
INSERT INTO `t_nation` VALUES ('130529', '巨鹿县', '3');
INSERT INTO `t_nation` VALUES ('330723', '武义县', '3');
INSERT INTO `t_nation` VALUES ('350900', '宁德市', '2');
INSERT INTO `t_nation` VALUES ('220105', '二道区', '3');
INSERT INTO `t_nation` VALUES ('350823', '上杭县', '3');
INSERT INTO `t_nation` VALUES ('430382', '韶山市', '3');
INSERT INTO `t_nation` VALUES ('441500', '汕尾市', '2');
INSERT INTO `t_nation` VALUES ('350724', '松溪县', '3');
INSERT INTO `t_nation` VALUES ('230401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('451024', '德保县', '3');
INSERT INTO `t_nation` VALUES ('232701', '加格达奇区', '3');
INSERT INTO `t_nation` VALUES ('652700', '博尔塔拉蒙古自治州', '2');
INSERT INTO `t_nation` VALUES ('511622', '武胜县', '3');
INSERT INTO `t_nation` VALUES ('330483', '桐乡市', '3');
INSERT INTO `t_nation` VALUES ('623025', '玛曲县', '3');
INSERT INTO `t_nation` VALUES ('542132', '洛隆县', '3');
INSERT INTO `t_nation` VALUES ('330522', '长兴县', '3');
INSERT INTO `t_nation` VALUES ('653122', '疏勒县', '3');
INSERT INTO `t_nation` VALUES ('110101', '东城区', '3');
INSERT INTO `t_nation` VALUES ('410328', '洛宁县', '3');
INSERT INTO `t_nation` VALUES ('210922', '彰武县', '3');
INSERT INTO `t_nation` VALUES ('621124', '临洮县', '3');
INSERT INTO `t_nation` VALUES ('150783', '扎兰屯市', '3');
INSERT INTO `t_nation` VALUES ('510524', '叙永县', '3');
INSERT INTO `t_nation` VALUES ('610425', '礼泉县', '3');
INSERT INTO `t_nation` VALUES ('510115', '温江区', '3');
INSERT INTO `t_nation` VALUES ('341722', '石台县', '3');
INSERT INTO `t_nation` VALUES ('361181', '德兴市', '3');
INSERT INTO `t_nation` VALUES ('510682', '什邡市', '3');
INSERT INTO `t_nation` VALUES ('411200', '三门峡市', '2');
INSERT INTO `t_nation` VALUES ('620701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('632223', '海晏县', '3');
INSERT INTO `t_nation` VALUES ('230381', '虎林市', '3');
INSERT INTO `t_nation` VALUES ('140723', '和顺县', '3');
INSERT INTO `t_nation` VALUES ('621002', '西峰区', '3');
INSERT INTO `t_nation` VALUES ('130100', '石家庄市', '2');
INSERT INTO `t_nation` VALUES ('430203', '芦淞区', '3');
INSERT INTO `t_nation` VALUES ('120223', '静海县', '3');
INSERT INTO `t_nation` VALUES ('370503', '河口区', '3');
INSERT INTO `t_nation` VALUES ('621126', '岷县', '3');
INSERT INTO `t_nation` VALUES ('411521', '罗山县', '3');
INSERT INTO `t_nation` VALUES ('430722', '汉寿县', '3');
INSERT INTO `t_nation` VALUES ('210106', '铁西区', '3');
INSERT INTO `t_nation` VALUES ('410305', '涧西区', '3');
INSERT INTO `t_nation` VALUES ('632122', '民和回族土族自治县', '3');
INSERT INTO `t_nation` VALUES ('370181', '章丘市', '3');
INSERT INTO `t_nation` VALUES ('230123', '依兰县', '3');
INSERT INTO `t_nation` VALUES ('211404', '南票区', '3');
INSERT INTO `t_nation` VALUES ('610627', '甘泉县', '3');
INSERT INTO `t_nation` VALUES ('500114', '黔江区', '3');
INSERT INTO `t_nation` VALUES ('542232', '错那县', '3');
INSERT INTO `t_nation` VALUES ('430724', '临澧县', '3');
INSERT INTO `t_nation` VALUES ('230603', '龙凤区', '3');
INSERT INTO `t_nation` VALUES ('152524', '苏尼特右旗', '3');
INSERT INTO `t_nation` VALUES ('350802', '新罗区', '3');
INSERT INTO `t_nation` VALUES ('220284', '磐石市', '3');
INSERT INTO `t_nation` VALUES ('530701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130623', '涞水县', '3');
INSERT INTO `t_nation` VALUES ('451228', '都安瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('620900', '酒泉市', '2');
INSERT INTO `t_nation` VALUES ('640106', '金凤区', '3');
INSERT INTO `t_nation` VALUES ('340303', '蚌山区', '3');
INSERT INTO `t_nation` VALUES ('542327', '昂仁县', '3');
INSERT INTO `t_nation` VALUES ('441881', '英德市', '3');
INSERT INTO `t_nation` VALUES ('431225', '会同县', '3');
INSERT INTO `t_nation` VALUES ('440902', '茂南区', '3');
INSERT INTO `t_nation` VALUES ('441882', '连州市', '3');
INSERT INTO `t_nation` VALUES ('350681', '龙海市', '3');
INSERT INTO `t_nation` VALUES ('542223', '贡嘎县', '3');
INSERT INTO `t_nation` VALUES ('330825', '龙游县', '3');
INSERT INTO `t_nation` VALUES ('210905', '清河门区', '3');
INSERT INTO `t_nation` VALUES ('450323', '灵川县', '3');
INSERT INTO `t_nation` VALUES ('421202', '咸安区', '3');
INSERT INTO `t_nation` VALUES ('500112', '渝北区', '3');
INSERT INTO `t_nation` VALUES ('220204', '船营区', '3');
INSERT INTO `t_nation` VALUES ('350401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('611025', '镇安县', '3');
INSERT INTO `t_nation` VALUES ('330225', '象山县', '3');
INSERT INTO `t_nation` VALUES ('420822', '沙洋县', '3');
INSERT INTO `t_nation` VALUES ('441700', '阳江市', '2');
INSERT INTO `t_nation` VALUES ('530500', '保山市', '2');
INSERT INTO `t_nation` VALUES ('632521', '共和县', '3');
INSERT INTO `t_nation` VALUES ('450700', '钦州市', '2');
INSERT INTO `t_nation` VALUES ('652300', '昌吉回族自治州', '2');
INSERT INTO `t_nation` VALUES ('130523', '内丘县', '3');
INSERT INTO `t_nation` VALUES ('130733', '崇礼县', '3');
INSERT INTO `t_nation` VALUES ('130323', '抚宁县', '3');
INSERT INTO `t_nation` VALUES ('440523', '南澳县', '3');
INSERT INTO `t_nation` VALUES ('620403', '平川区', '3');
INSERT INTO `t_nation` VALUES ('451200', '河池市', '2');
INSERT INTO `t_nation` VALUES ('131101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('211204', '清河区', '3');
INSERT INTO `t_nation` VALUES ('350781', '邵武市', '3');
INSERT INTO `t_nation` VALUES ('653126', '叶城县', '3');
INSERT INTO `t_nation` VALUES ('330283', '奉化市', '3');
INSERT INTO `t_nation` VALUES ('371000', '威海市', '2');
INSERT INTO `t_nation` VALUES ('429006', '天门市', '3');
INSERT INTO `t_nation` VALUES ('230405', '兴安区', '3');
INSERT INTO `t_nation` VALUES ('532502', '开远市', '3');
INSERT INTO `t_nation` VALUES ('360124', '进贤县', '3');
INSERT INTO `t_nation` VALUES ('640122', '贺兰县', '3');
INSERT INTO `t_nation` VALUES ('341001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('450921', '容县', '3');
INSERT INTO `t_nation` VALUES ('430802', '永定区', '3');
INSERT INTO `t_nation` VALUES ('441723', '阳东县', '3');
INSERT INTO `t_nation` VALUES ('522625', '镇远县', '3');
INSERT INTO `t_nation` VALUES ('411522', '光山县', '3');
INSERT INTO `t_nation` VALUES ('520425', '紫云苗族布依族自治县', '3');
INSERT INTO `t_nation` VALUES ('210302', '铁东区', '3');
INSERT INTO `t_nation` VALUES ('140724', '昔阳县', '3');
INSERT INTO `t_nation` VALUES ('350304', '荔城区', '3');
INSERT INTO `t_nation` VALUES ('320581', '常熟市', '3');
INSERT INTO `t_nation` VALUES ('420821', '京山县', '3');
INSERT INTO `t_nation` VALUES ('632824', '芒崖', '3');
INSERT INTO `t_nation` VALUES ('140822', '万荣县', '3');
INSERT INTO `t_nation` VALUES ('420205', '铁山区', '3');
INSERT INTO `t_nation` VALUES ('130802', '双桥区', '3');
INSERT INTO `t_nation` VALUES ('431102', '零陵区', '3');
INSERT INTO `t_nation` VALUES ('130133', '赵县', '3');
INSERT INTO `t_nation` VALUES ('430723', '澧县', '3');
INSERT INTO `t_nation` VALUES ('441225', '封开县', '3');
INSERT INTO `t_nation` VALUES ('350427', '沙县', '3');
INSERT INTO `t_nation` VALUES ('440402', '香洲区', '3');
INSERT INTO `t_nation` VALUES ('441223', '广宁县', '3');
INSERT INTO `t_nation` VALUES ('131025', '大城县', '3');
INSERT INTO `t_nation` VALUES ('450304', '象山区', '3');
INSERT INTO `t_nation` VALUES ('440781', '台山市', '3');
INSERT INTO `t_nation` VALUES ('141129', '中阳县', '3');
INSERT INTO `t_nation` VALUES ('131122', '武邑县', '3');
INSERT INTO `t_nation` VALUES ('620902', '肃州区', '3');
INSERT INTO `t_nation` VALUES ('220681', '临江市', '3');
INSERT INTO `t_nation` VALUES ('320504', '金阊区', '3');
INSERT INTO `t_nation` VALUES ('420901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320311', '泉山区', '3');
INSERT INTO `t_nation` VALUES ('340504', '雨山区', '3');
INSERT INTO `t_nation` VALUES ('420112', '东西湖区', '3');
INSERT INTO `t_nation` VALUES ('320303', '云龙区', '3');
INSERT INTO `t_nation` VALUES ('210101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210711', '太和区', '3');
INSERT INTO `t_nation` VALUES ('450901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440104', '越秀区', '3');
INSERT INTO `t_nation` VALUES ('540123', '尼木县', '3');
INSERT INTO `t_nation` VALUES ('370613', '莱山区', '3');
INSERT INTO `t_nation` VALUES ('320201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410922', '清丰县', '3');
INSERT INTO `t_nation` VALUES ('341721', '东至县', '3');
INSERT INTO `t_nation` VALUES ('450600', '防城港市', '2');
INSERT INTO `t_nation` VALUES ('130732', '赤城县', '3');
INSERT INTO `t_nation` VALUES ('130301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('120105', '河北区', '3');
INSERT INTO `t_nation` VALUES ('431024', '嘉禾县', '3');
INSERT INTO `t_nation` VALUES ('341023', '黟县', '3');
INSERT INTO `t_nation` VALUES ('150104', '玉泉区', '3');
INSERT INTO `t_nation` VALUES ('620521', '清水县', '3');
INSERT INTO `t_nation` VALUES ('451122', '钟山县', '3');
INSERT INTO `t_nation` VALUES ('530626', '绥江县', '3');
INSERT INTO `t_nation` VALUES ('431302', '娄星区', '3');
INSERT INTO `t_nation` VALUES ('141023', '襄汾县', '3');
INSERT INTO `t_nation` VALUES ('410781', '卫辉市', '3');
INSERT INTO `t_nation` VALUES ('230714', '乌伊岭区', '3');
INSERT INTO `t_nation` VALUES ('430224', '茶陵县', '3');
INSERT INTO `t_nation` VALUES ('320200', '无锡市', '2');
INSERT INTO `t_nation` VALUES ('632500', '海南藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('441622', '龙川县', '3');
INSERT INTO `t_nation` VALUES ('370786', '昌邑市', '3');
INSERT INTO `t_nation` VALUES ('320683', '通州市', '3');
INSERT INTO `t_nation` VALUES ('460106', '龙华区', '3');
INSERT INTO `t_nation` VALUES ('330185', '临安市', '3');
INSERT INTO `t_nation` VALUES ('421001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140800', '运城市', '2');
INSERT INTO `t_nation` VALUES ('610724', '西乡县', '3');
INSERT INTO `t_nation` VALUES ('371426', '平原县', '3');
INSERT INTO `t_nation` VALUES ('520323', '绥阳县', '3');
INSERT INTO `t_nation` VALUES ('130634', '曲阳县', '3');
INSERT INTO `t_nation` VALUES ('370306', '周村区', '3');
INSERT INTO `t_nation` VALUES ('341423', '含山县', '3');
INSERT INTO `t_nation` VALUES ('370323', '沂源县', '3');
INSERT INTO `t_nation` VALUES ('370881', '曲阜市', '3');
INSERT INTO `t_nation` VALUES ('440401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330802', '柯城区', '3');
INSERT INTO `t_nation` VALUES ('140622', '应县', '3');
INSERT INTO `t_nation` VALUES ('621101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('341500', '六安市', '2');
INSERT INTO `t_nation` VALUES ('623001', '合作市', '3');
INSERT INTO `t_nation` VALUES ('440608', '高明区', '3');
INSERT INTO `t_nation` VALUES ('640181', '灵武市', '3');
INSERT INTO `t_nation` VALUES ('371122', '莒县', '3');
INSERT INTO `t_nation` VALUES ('450481', '岑溪市', '3');
INSERT INTO `t_nation` VALUES ('420607', '襄州区', '3');
INSERT INTO `t_nation` VALUES ('371324', '苍山县', '3');
INSERT INTO `t_nation` VALUES ('340000', '安徽省', '1');
INSERT INTO `t_nation` VALUES ('445102', '湘桥区', '3');
INSERT INTO `t_nation` VALUES ('450300', '桂林市', '2');
INSERT INTO `t_nation` VALUES ('500241', '秀山土家族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('230901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411628', '鹿邑县', '3');
INSERT INTO `t_nation` VALUES ('220200', '吉林市', '2');
INSERT INTO `t_nation` VALUES ('542231', '隆子县', '3');
INSERT INTO `t_nation` VALUES ('210400', '抚顺市', '2');
INSERT INTO `t_nation` VALUES ('150602', '东胜区', '3');
INSERT INTO `t_nation` VALUES ('140581', '高平市', '3');
INSERT INTO `t_nation` VALUES ('341421', '庐江县', '3');
INSERT INTO `t_nation` VALUES ('360403', '浔阳区', '3');
INSERT INTO `t_nation` VALUES ('330502', '吴兴区', '3');
INSERT INTO `t_nation` VALUES ('500233', '忠县', '3');
INSERT INTO `t_nation` VALUES ('130402', '邯山区', '3');
INSERT INTO `t_nation` VALUES ('120115', '宝坻区', '3');
INSERT INTO `t_nation` VALUES ('211001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('341126', '凤阳县', '3');
INSERT INTO `t_nation` VALUES ('451324', '金秀瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('522401', '毕节市', '3');
INSERT INTO `t_nation` VALUES ('340406', '潘集区', '3');
INSERT INTO `t_nation` VALUES ('451229', '大化瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('433126', '古丈县', '3');
INSERT INTO `t_nation` VALUES ('420501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371312', '河东区', '3');
INSERT INTO `t_nation` VALUES ('350524', '安溪县', '3');
INSERT INTO `t_nation` VALUES ('411424', '柘城县', '3');
INSERT INTO `t_nation` VALUES ('370901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371726', '鄄城县', '3');
INSERT INTO `t_nation` VALUES ('150201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('469028', '临高县', '3');
INSERT INTO `t_nation` VALUES ('510800', '广元市', '2');
INSERT INTO `t_nation` VALUES ('340104', '蜀山区', '3');
INSERT INTO `t_nation` VALUES ('210381', '海城市', '3');
INSERT INTO `t_nation` VALUES ('130703', '桥西区', '3');
INSERT INTO `t_nation` VALUES ('430401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360722', '信丰县', '3');
INSERT INTO `t_nation` VALUES ('532327', '永仁县', '3');
INSERT INTO `t_nation` VALUES ('230521', '集贤县', '3');
INSERT INTO `t_nation` VALUES ('370826', '微山县', '3');
INSERT INTO `t_nation` VALUES ('230501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('511502', '翠屏区', '3');
INSERT INTO `t_nation` VALUES ('411082', '长葛市', '3');
INSERT INTO `t_nation` VALUES ('420302', '茅箭区', '3');
INSERT INTO `t_nation` VALUES ('140925', '宁武县', '3');
INSERT INTO `t_nation` VALUES ('370404', '峄城区', '3');
INSERT INTO `t_nation` VALUES ('130681', '涿州市', '3');
INSERT INTO `t_nation` VALUES ('320411', '新北区', '3');
INSERT INTO `t_nation` VALUES ('530426', '峨山彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('420107', '青山区', '3');
INSERT INTO `t_nation` VALUES ('340503', '花山区', '3');
INSERT INTO `t_nation` VALUES ('441400', '梅州市', '2');
INSERT INTO `t_nation` VALUES ('632300', '黄南藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('130923', '东光县', '3');
INSERT INTO `t_nation` VALUES ('410702', '红旗区', '3');
INSERT INTO `t_nation` VALUES ('431301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370305', '临淄区', '3');
INSERT INTO `t_nation` VALUES ('150102', '新城区', '3');
INSERT INTO `t_nation` VALUES ('371101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320503', '平江区', '3');
INSERT INTO `t_nation` VALUES ('510922', '射洪县', '3');
INSERT INTO `t_nation` VALUES ('150302', '海勃湾区', '3');
INSERT INTO `t_nation` VALUES ('610322', '凤翔县', '3');
INSERT INTO `t_nation` VALUES ('542525', '革吉县', '3');
INSERT INTO `t_nation` VALUES ('530625', '永善县', '3');
INSERT INTO `t_nation` VALUES ('411323', '西峡县', '3');
INSERT INTO `t_nation` VALUES ('130826', '丰宁满族自治县', '3');
INSERT INTO `t_nation` VALUES ('230421', '萝北县', '3');
INSERT INTO `t_nation` VALUES ('433100', '湘西土家族苗族自治州', '2');
INSERT INTO `t_nation` VALUES ('321023', '宝应县', '3');
INSERT INTO `t_nation` VALUES ('542332', '定结县', '3');
INSERT INTO `t_nation` VALUES ('340400', '淮南市', '2');
INSERT INTO `t_nation` VALUES ('622925', '和政县', '3');
INSERT INTO `t_nation` VALUES ('430304', '岳塘区', '3');
INSERT INTO `t_nation` VALUES ('340828', '岳西县', '3');
INSERT INTO `t_nation` VALUES ('361122', '广丰县', '3');
INSERT INTO `t_nation` VALUES ('211224', '昌图县', '3');
INSERT INTO `t_nation` VALUES ('421126', '蕲春县', '3');
INSERT INTO `t_nation` VALUES ('350101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130428', '肥乡县', '3');
INSERT INTO `t_nation` VALUES ('331023', '天台县', '3');
INSERT INTO `t_nation` VALUES ('460101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('511921', '通江县', '3');
INSERT INTO `t_nation` VALUES ('654000', '伊犁哈萨克自治州', '2');
INSERT INTO `t_nation` VALUES ('410322', '孟津县', '3');
INSERT INTO `t_nation` VALUES ('321301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220382', '双辽市', '3');
INSERT INTO `t_nation` VALUES ('410183', '新密市', '3');
INSERT INTO `t_nation` VALUES ('530800', '普洱市', '2');
INSERT INTO `t_nation` VALUES ('321283', '泰兴市', '3');
INSERT INTO `t_nation` VALUES ('440112', '黄埔区', '3');
INSERT INTO `t_nation` VALUES ('450981', '北流市', '3');
INSERT INTO `t_nation` VALUES ('420601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('120000', '天津市', '1');
INSERT INTO `t_nation` VALUES ('411402', '梁园区', '3');
INSERT INTO `t_nation` VALUES ('460107', '琼山区', '3');
INSERT INTO `t_nation` VALUES ('513328', '甘孜县', '3');
INSERT INTO `t_nation` VALUES ('150801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('231102', '爱辉区', '3');
INSERT INTO `t_nation` VALUES ('530524', '昌宁县', '3');
INSERT INTO `t_nation` VALUES ('430501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('532301', '楚雄市', '3');
INSERT INTO `t_nation` VALUES ('520326', '务川仡佬族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('350602', '芗城区', '3');
INSERT INTO `t_nation` VALUES ('652325', '奇台县', '3');
INSERT INTO `t_nation` VALUES ('431001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('510181', '都江堰市', '3');
INSERT INTO `t_nation` VALUES ('131002', '安次区', '3');
INSERT INTO `t_nation` VALUES ('410602', '鹤山区', '3');
INSERT INTO `t_nation` VALUES ('230202', '龙沙区', '3');
INSERT INTO `t_nation` VALUES ('370322', '高青县', '3');
INSERT INTO `t_nation` VALUES ('331024', '仙居县', '3');
INSERT INTO `t_nation` VALUES ('451225', '罗城仫佬族自治县', '3');
INSERT INTO `t_nation` VALUES ('450603', '防城区', '3');
INSERT INTO `t_nation` VALUES ('141024', '洪洞县', '3');
INSERT INTO `t_nation` VALUES ('650107', '达坂城区', '3');
INSERT INTO `t_nation` VALUES ('130582', '沙河市', '3');
INSERT INTO `t_nation` VALUES ('140107', '杏花岭区', '3');
INSERT INTO `t_nation` VALUES ('441303', '惠阳区', '3');
INSERT INTO `t_nation` VALUES ('659000', '自治区直辖县级行政区划', '2');
INSERT INTO `t_nation` VALUES ('621102', '安定区', '3');
INSERT INTO `t_nation` VALUES ('522323', '普安县', '3');
INSERT INTO `t_nation` VALUES ('500235', '云阳县', '3');
INSERT INTO `t_nation` VALUES ('520327', '凤冈县', '3');
INSERT INTO `t_nation` VALUES ('330103', '下城区', '3');
INSERT INTO `t_nation` VALUES ('210700', '锦州市', '2');
INSERT INTO `t_nation` VALUES ('150205', '石拐区', '3');
INSERT INTO `t_nation` VALUES ('340823', '枞阳县', '3');
INSERT INTO `t_nation` VALUES ('632826', '大柴旦', '3');
INSERT INTO `t_nation` VALUES ('542338', '岗巴县', '3');
INSERT INTO `t_nation` VALUES ('411328', '唐河县', '3');
INSERT INTO `t_nation` VALUES ('522629', '剑河县', '3');
INSERT INTO `t_nation` VALUES ('130921', '沧县', '3');
INSERT INTO `t_nation` VALUES ('542233', '浪卡子县', '3');
INSERT INTO `t_nation` VALUES ('542324', '定日县', '3');
INSERT INTO `t_nation` VALUES ('110106', '丰台区', '3');
INSERT INTO `t_nation` VALUES ('350921', '霞浦县', '3');
INSERT INTO `t_nation` VALUES ('441224', '怀集县', '3');
INSERT INTO `t_nation` VALUES ('410700', '新乡市', '2');
INSERT INTO `t_nation` VALUES ('152922', '阿拉善右旗', '3');
INSERT INTO `t_nation` VALUES ('430423', '衡山县', '3');
INSERT INTO `t_nation` VALUES ('511400', '眉山市', '2');
INSERT INTO `t_nation` VALUES ('361125', '横峰县', '3');
INSERT INTO `t_nation` VALUES ('542626', '察隅县', '3');
INSERT INTO `t_nation` VALUES ('141082', '霍州市', '3');
INSERT INTO `t_nation` VALUES ('620402', '白银区', '3');
INSERT INTO `t_nation` VALUES ('330921', '岱山县', '3');
INSERT INTO `t_nation` VALUES ('440282', '南雄市', '3');
INSERT INTO `t_nation` VALUES ('370802', '市中区', '3');
INSERT INTO `t_nation` VALUES ('532324', '南华县', '3');
INSERT INTO `t_nation` VALUES ('652722', '精河县', '3');
INSERT INTO `t_nation` VALUES ('340500', '马鞍山市', '2');
INSERT INTO `t_nation` VALUES ('410704', '凤泉区', '3');
INSERT INTO `t_nation` VALUES ('511702', '通川区', '3');
INSERT INTO `t_nation` VALUES ('331122', '缙云县', '3');
INSERT INTO `t_nation` VALUES ('131182', '深州市', '3');
INSERT INTO `t_nation` VALUES ('232722', '塔河县', '3');
INSERT INTO `t_nation` VALUES ('650202', '独山子区', '3');
INSERT INTO `t_nation` VALUES ('330303', '龙湾区', '3');
INSERT INTO `t_nation` VALUES ('360502', '渝水区', '3');
INSERT INTO `t_nation` VALUES ('610921', '汉阴县', '3');
INSERT INTO `t_nation` VALUES ('652900', '阿克苏地区', '2');
INSERT INTO `t_nation` VALUES ('130982', '任丘市', '3');
INSERT INTO `t_nation` VALUES ('320404', '钟楼区', '3');
INSERT INTO `t_nation` VALUES ('211401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('451221', '南丹县', '3');
INSERT INTO `t_nation` VALUES ('540101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370634', '长岛县', '3');
INSERT INTO `t_nation` VALUES ('230606', '大同区', '3');
INSERT INTO `t_nation` VALUES ('230422', '绥滨县', '3');
INSERT INTO `t_nation` VALUES ('360721', '赣县', '3');
INSERT INTO `t_nation` VALUES ('640522', '海原县', '3');
INSERT INTO `t_nation` VALUES ('361126', '弋阳县', '3');
INSERT INTO `t_nation` VALUES ('620300', '金昌市', '2');
INSERT INTO `t_nation` VALUES ('421123', '罗田县', '3');
INSERT INTO `t_nation` VALUES ('610830', '清涧县', '3');
INSERT INTO `t_nation` VALUES ('141000', '临汾市', '2');
INSERT INTO `t_nation` VALUES ('370502', '东营区', '3');
INSERT INTO `t_nation` VALUES ('430800', '张家界市', '2');
INSERT INTO `t_nation` VALUES ('230110', '香坊区', '3');
INSERT INTO `t_nation` VALUES ('130630', '涞源县', '3');
INSERT INTO `t_nation` VALUES ('370827', '鱼台县', '3');
INSERT INTO `t_nation` VALUES ('430581', '武冈市', '3');
INSERT INTO `t_nation` VALUES ('150900', '乌兰察布市', '2');
INSERT INTO `t_nation` VALUES ('610116', '长安区', '3');
INSERT INTO `t_nation` VALUES ('140802', '盐湖区', '3');
INSERT INTO `t_nation` VALUES ('620301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('653128', '岳普湖县', '3');
INSERT INTO `t_nation` VALUES ('211003', '文圣区', '3');
INSERT INTO `t_nation` VALUES ('510301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411423', '宁陵县', '3');
INSERT INTO `t_nation` VALUES ('130602', '新市区', '3');
INSERT INTO `t_nation` VALUES ('652829', '博湖县', '3');
INSERT INTO `t_nation` VALUES ('140000', '山西省', '1');
INSERT INTO `t_nation` VALUES ('330324', '永嘉县', '3');
INSERT INTO `t_nation` VALUES ('330102', '上城区', '3');
INSERT INTO `t_nation` VALUES ('340721', '铜陵县', '3');
INSERT INTO `t_nation` VALUES ('451027', '凌云县', '3');
INSERT INTO `t_nation` VALUES ('650105', '水磨沟区', '3');
INSERT INTO `t_nation` VALUES ('450109', '邕宁区', '3');
INSERT INTO `t_nation` VALUES ('320803', '楚州区', '3');
INSERT INTO `t_nation` VALUES ('210603', '振兴区', '3');
INSERT INTO `t_nation` VALUES ('510811', '元坝区', '3');
INSERT INTO `t_nation` VALUES ('411421', '民权县', '3');
INSERT INTO `t_nation` VALUES ('430422', '衡南县', '3');
INSERT INTO `t_nation` VALUES ('620921', '金塔县', '3');
INSERT INTO `t_nation` VALUES ('230231', '拜泉县', '3');
INSERT INTO `t_nation` VALUES ('421201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('632721', '玉树县', '3');
INSERT INTO `t_nation` VALUES ('220822', '通榆县', '3');
INSERT INTO `t_nation` VALUES ('411122', '临颍县', '3');
INSERT INTO `t_nation` VALUES ('410506', '龙安区', '3');
INSERT INTO `t_nation` VALUES ('360828', '万安县', '3');
INSERT INTO `t_nation` VALUES ('210703', '凌河区', '3');
INSERT INTO `t_nation` VALUES ('610929', '白河县', '3');
INSERT INTO `t_nation` VALUES ('511827', '宝兴县', '3');
INSERT INTO `t_nation` VALUES ('230707', '新青区', '3');
INSERT INTO `t_nation` VALUES ('231201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210100', '沈阳市', '2');
INSERT INTO `t_nation` VALUES ('451401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('512022', '乐至县', '3');
INSERT INTO `t_nation` VALUES ('310200', '县', '2');
INSERT INTO `t_nation` VALUES ('430225', '炎陵县', '3');
INSERT INTO `t_nation` VALUES ('220323', '伊通满族自治县', '3');
INSERT INTO `t_nation` VALUES ('520303', '汇川区', '3');
INSERT INTO `t_nation` VALUES ('510503', '纳溪区', '3');
INSERT INTO `t_nation` VALUES ('371311', '罗庄区', '3');
INSERT INTO `t_nation` VALUES ('211103', '兴隆台区', '3');
INSERT INTO `t_nation` VALUES ('140201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360925', '靖安县', '3');
INSERT INTO `t_nation` VALUES ('130126', '灵寿县', '3');
INSERT INTO `t_nation` VALUES ('370784', '安丘市', '3');
INSERT INTO `t_nation` VALUES ('513400', '凉山彝族自治州', '2');
INSERT INTO `t_nation` VALUES ('630103', '城中区', '3');
INSERT INTO `t_nation` VALUES ('330624', '新昌县', '3');
INSERT INTO `t_nation` VALUES ('522227', '德江县', '3');
INSERT INTO `t_nation` VALUES ('511500', '宜宾市', '2');
INSERT INTO `t_nation` VALUES ('530000', '云南省', '1');
INSERT INTO `t_nation` VALUES ('341221', '临泉县', '3');
INSERT INTO `t_nation` VALUES ('431023', '永兴县', '3');
INSERT INTO `t_nation` VALUES ('511422', '彭山县', '3');
INSERT INTO `t_nation` VALUES ('210204', '沙河口区', '3');
INSERT INTO `t_nation` VALUES ('522226', '印江土家族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('620201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('222406', '和龙市', '3');
INSERT INTO `t_nation` VALUES ('510183', '邛崃市', '3');
INSERT INTO `t_nation` VALUES ('430421', '衡阳县', '3');
INSERT INTO `t_nation` VALUES ('500104', '大渡口区', '3');
INSERT INTO `t_nation` VALUES ('632625', '久治县', '3');
INSERT INTO `t_nation` VALUES ('140108', '尖草坪区', '3');
INSERT INTO `t_nation` VALUES ('350322', '仙游县', '3');
INSERT INTO `t_nation` VALUES ('420222', '阳新县', '3');
INSERT INTO `t_nation` VALUES ('210103', '沈河区', '3');
INSERT INTO `t_nation` VALUES ('320830', '盱眙县', '3');
INSERT INTO `t_nation` VALUES ('440700', '江门市', '2');
INSERT INTO `t_nation` VALUES ('640521', '中宁县', '3');
INSERT INTO `t_nation` VALUES ('542100', '昌都地区', '2');
INSERT INTO `t_nation` VALUES ('621000', '庆阳市', '2');
INSERT INTO `t_nation` VALUES ('530328', '沾益县', '3');
INSERT INTO `t_nation` VALUES ('640200', '石嘴山市', '2');
INSERT INTO `t_nation` VALUES ('330523', '安吉县', '3');
INSERT INTO `t_nation` VALUES ('522224', '石阡县', '3');
INSERT INTO `t_nation` VALUES ('361028', '资溪县', '3');
INSERT INTO `t_nation` VALUES ('441827', '清新县', '3');
INSERT INTO `t_nation` VALUES ('150424', '林西县', '3');
INSERT INTO `t_nation` VALUES ('422827', '来凤县', '3');
INSERT INTO `t_nation` VALUES ('640402', '原州区', '3');
INSERT INTO `t_nation` VALUES ('430302', '雨湖区', '3');
INSERT INTO `t_nation` VALUES ('321102', '京口区', '3');
INSERT INTO `t_nation` VALUES ('430200', '株洲市', '2');
INSERT INTO `t_nation` VALUES ('542227', '措美县', '3');
INSERT INTO `t_nation` VALUES ('419001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210200', '大连市', '2');
INSERT INTO `t_nation` VALUES ('430124', '宁乡县', '3');
INSERT INTO `t_nation` VALUES ('430381', '湘乡市', '3');
INSERT INTO `t_nation` VALUES ('620824', '华亭县', '3');
INSERT INTO `t_nation` VALUES ('232704', '呼中区', '3');
INSERT INTO `t_nation` VALUES ('410500', '安阳市', '2');
INSERT INTO `t_nation` VALUES ('360602', '月湖区', '3');
INSERT INTO `t_nation` VALUES ('350800', '龙岩市', '2');
INSERT INTO `t_nation` VALUES ('350428', '将乐县', '3');
INSERT INTO `t_nation` VALUES ('370800', '济宁市', '2');
INSERT INTO `t_nation` VALUES ('150400', '赤峰市', '2');
INSERT INTO `t_nation` VALUES ('430702', '武陵区', '3');
INSERT INTO `t_nation` VALUES ('640400', '固原市', '2');
INSERT INTO `t_nation` VALUES ('360727', '龙南县', '3');
INSERT INTO `t_nation` VALUES ('522325', '贞丰县', '3');
INSERT INTO `t_nation` VALUES ('451100', '贺州市', '2');
INSERT INTO `t_nation` VALUES ('500103', '渝中区', '3');
INSERT INTO `t_nation` VALUES ('210304', '立山区', '3');
INSERT INTO `t_nation` VALUES ('530111', '官渡区', '3');
INSERT INTO `t_nation` VALUES ('420900', '孝感市', '2');
INSERT INTO `t_nation` VALUES ('450223', '鹿寨县', '3');
INSERT INTO `t_nation` VALUES ('350425', '大田县', '3');
INSERT INTO `t_nation` VALUES ('150421', '阿鲁科尔沁旗', '3');
INSERT INTO `t_nation` VALUES ('320401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('520400', '安顺市', '2');
INSERT INTO `t_nation` VALUES ('140222', '天镇县', '3');
INSERT INTO `t_nation` VALUES ('410711', '牧野区', '3');
INSERT INTO `t_nation` VALUES ('530623', '盐津县', '3');
INSERT INTO `t_nation` VALUES ('530124', '富民县', '3');
INSERT INTO `t_nation` VALUES ('420529', '五峰土家族自治县', '3');
INSERT INTO `t_nation` VALUES ('220623', '长白朝鲜族自治县', '3');
INSERT INTO `t_nation` VALUES ('150927', '察哈尔右翼中旗', '3');
INSERT INTO `t_nation` VALUES ('610527', '白水县', '3');
INSERT INTO `t_nation` VALUES ('513432', '喜德县', '3');
INSERT INTO `t_nation` VALUES ('450328', '龙胜各族自治县', '3');
INSERT INTO `t_nation` VALUES ('371321', '沂南县', '3');
INSERT INTO `t_nation` VALUES ('230302', '鸡冠区', '3');
INSERT INTO `t_nation` VALUES ('361030', '广昌县', '3');
INSERT INTO `t_nation` VALUES ('410223', '尉氏县', '3');
INSERT INTO `t_nation` VALUES ('130724', '沽源县', '3');
INSERT INTO `t_nation` VALUES ('440903', '茂港区', '3');
INSERT INTO `t_nation` VALUES ('610330', '凤县', '3');
INSERT INTO `t_nation` VALUES ('430121', '长沙县', '3');
INSERT INTO `t_nation` VALUES ('210781', '凌海市', '3');
INSERT INTO `t_nation` VALUES ('500232', '武隆县', '3');
INSERT INTO `t_nation` VALUES ('610701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('421002', '沙市区', '3');
INSERT INTO `t_nation` VALUES ('130132', '元氏县', '3');
INSERT INTO `t_nation` VALUES ('510823', '剑阁县', '3');
INSERT INTO `t_nation` VALUES ('210811', '老边区', '3');
INSERT INTO `t_nation` VALUES ('650000', '新疆维吾尔自治区', '1');
INSERT INTO `t_nation` VALUES ('440111', '白云区', '3');
INSERT INTO `t_nation` VALUES ('410901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('350305', '秀屿区', '3');
INSERT INTO `t_nation` VALUES ('520325', '道真仡佬族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('630101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('341621', '涡阳县', '3');
INSERT INTO `t_nation` VALUES ('141121', '文水县', '3');
INSERT INTO `t_nation` VALUES ('540126', '达孜县', '3');
INSERT INTO `t_nation` VALUES ('640502', '沙坡头区', '3');
INSERT INTO `t_nation` VALUES ('350000', '福建省', '1');
INSERT INTO `t_nation` VALUES ('230103', '南岗区', '3');
INSERT INTO `t_nation` VALUES ('451281', '宜州市', '3');
INSERT INTO `t_nation` VALUES ('621225', '西和县', '3');
INSERT INTO `t_nation` VALUES ('130208', '丰润区', '3');
INSERT INTO `t_nation` VALUES ('450200', '柳州市', '2');
INSERT INTO `t_nation` VALUES ('361023', '南丰县', '3');
INSERT INTO `t_nation` VALUES ('140931', '保德县', '3');
INSERT INTO `t_nation` VALUES ('350521', '惠安县', '3');
INSERT INTO `t_nation` VALUES ('150526', '扎鲁特旗', '3');
INSERT INTO `t_nation` VALUES ('410101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320113', '栖霞区', '3');
INSERT INTO `t_nation` VALUES ('371523', '茌平县', '3');
INSERT INTO `t_nation` VALUES ('130322', '昌黎县', '3');
INSERT INTO `t_nation` VALUES ('450221', '柳江县', '3');
INSERT INTO `t_nation` VALUES ('622927', '积石山保安族东乡族撒拉族自治县', '3');
INSERT INTO `t_nation` VALUES ('542228', '洛扎县', '3');
INSERT INTO `t_nation` VALUES ('653129', '伽师县', '3');
INSERT INTO `t_nation` VALUES ('360101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542229', '加查县', '3');
INSERT INTO `t_nation` VALUES ('150402', '红山区', '3');
INSERT INTO `t_nation` VALUES ('532600', '文山壮族苗族自治州', '2');
INSERT INTO `t_nation` VALUES ('500115', '长寿区', '3');
INSERT INTO `t_nation` VALUES ('411526', '潢川县', '3');
INSERT INTO `t_nation` VALUES ('532927', '巍山彝族回族自治县', '3');
INSERT INTO `t_nation` VALUES ('321002', '广陵区', '3');
INSERT INTO `t_nation` VALUES ('130102', '长安区', '3');
INSERT INTO `t_nation` VALUES ('130183', '晋州市', '3');
INSERT INTO `t_nation` VALUES ('530423', '通海县', '3');
INSERT INTO `t_nation` VALUES ('220700', '松原市', '2');
INSERT INTO `t_nation` VALUES ('431000', '郴州市', '2');
INSERT INTO `t_nation` VALUES ('653201', '和田市', '3');
INSERT INTO `t_nation` VALUES ('652222', '巴里坤哈萨克自治县', '3');
INSERT INTO `t_nation` VALUES ('360104', '青云谱区', '3');
INSERT INTO `t_nation` VALUES ('341024', '祁门县', '3');
INSERT INTO `t_nation` VALUES ('430703', '鼎城区', '3');
INSERT INTO `t_nation` VALUES ('441624', '和平县', '3');
INSERT INTO `t_nation` VALUES ('370481', '滕州市', '3');
INSERT INTO `t_nation` VALUES ('410300', '洛阳市', '2');
INSERT INTO `t_nation` VALUES ('430223', '攸县', '3');
INSERT INTO `t_nation` VALUES ('220621', '抚松县', '3');
INSERT INTO `t_nation` VALUES ('610104', '莲湖区', '3');
INSERT INTO `t_nation` VALUES ('360922', '万载县', '3');
INSERT INTO `t_nation` VALUES ('411330', '桐柏县', '3');
INSERT INTO `t_nation` VALUES ('522322', '兴仁县', '3');
INSERT INTO `t_nation` VALUES ('430624', '湘阴县', '3');
INSERT INTO `t_nation` VALUES ('370601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('430623', '华容县', '3');
INSERT INTO `t_nation` VALUES ('530624', '大关县', '3');
INSERT INTO `t_nation` VALUES ('620924', '阿克塞哈萨克族自治县', '3');
INSERT INTO `t_nation` VALUES ('610821', '神木县', '3');
INSERT INTO `t_nation` VALUES ('500107', '九龙坡区', '3');
INSERT INTO `t_nation` VALUES ('211422', '建昌县', '3');
INSERT INTO `t_nation` VALUES ('411525', '固始县', '3');
INSERT INTO `t_nation` VALUES ('321281', '兴化市', '3');
INSERT INTO `t_nation` VALUES ('230402', '向阳区', '3');
INSERT INTO `t_nation` VALUES ('411724', '正阳县', '3');
INSERT INTO `t_nation` VALUES ('220800', '白城市', '2');
INSERT INTO `t_nation` VALUES ('530103', '盘龙区', '3');
INSERT INTO `t_nation` VALUES ('360732', '兴国县', '3');
INSERT INTO `t_nation` VALUES ('511800', '雅安市', '2');
INSERT INTO `t_nation` VALUES ('640100', '银川市', '2');
INSERT INTO `t_nation` VALUES ('620500', '天水市', '2');
INSERT INTO `t_nation` VALUES ('469000', '省直辖县级行政单位', '2');
INSERT INTO `t_nation` VALUES ('441523', '陆河县', '3');
INSERT INTO `t_nation` VALUES ('451022', '田东县', '3');
INSERT INTO `t_nation` VALUES ('510401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130800', '承德市', '2');
INSERT INTO `t_nation` VALUES ('150502', '科尔沁区', '3');
INSERT INTO `t_nation` VALUES ('441825', '连山壮族瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('652301', '昌吉市', '3');
INSERT INTO `t_nation` VALUES ('131127', '景县', '3');
INSERT INTO `t_nation` VALUES ('420117', '新洲区', '3');
INSERT INTO `t_nation` VALUES ('370405', '台儿庄区', '3');
INSERT INTO `t_nation` VALUES ('230304', '滴道区', '3');
INSERT INTO `t_nation` VALUES ('320203', '南长区', '3');
INSERT INTO `t_nation` VALUES ('511721', '达县', '3');
INSERT INTO `t_nation` VALUES ('513300', '甘孜藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('542226', '曲松县', '3');
INSERT INTO `t_nation` VALUES ('130524', '柏乡县', '3');
INSERT INTO `t_nation` VALUES ('632525', '贵南县', '3');
INSERT INTO `t_nation` VALUES ('513423', '盐源县', '3');
INSERT INTO `t_nation` VALUES ('440113', '番禺区', '3');
INSERT INTO `t_nation` VALUES ('370923', '东平县', '3');
INSERT INTO `t_nation` VALUES ('341522', '霍邱县', '3');
INSERT INTO `t_nation` VALUES ('610901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610702', '汉台区', '3');
INSERT INTO `t_nation` VALUES ('410327', '宜阳县', '3');
INSERT INTO `t_nation` VALUES ('231121', '嫩江县', '3');
INSERT INTO `t_nation` VALUES ('360430', '彭泽县', '3');
INSERT INTO `t_nation` VALUES ('130435', '曲周县', '3');
INSERT INTO `t_nation` VALUES ('520401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320305', '贾汪区', '3');
INSERT INTO `t_nation` VALUES ('130204', '古冶区', '3');
INSERT INTO `t_nation` VALUES ('442000', '中山市', '2');
INSERT INTO `t_nation` VALUES ('150101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('520321', '遵义县', '3');
INSERT INTO `t_nation` VALUES ('511900', '巴中市', '2');
INSERT INTO `t_nation` VALUES ('513336', '乡城县', '3');
INSERT INTO `t_nation` VALUES ('653224', '洛浦县', '3');
INSERT INTO `t_nation` VALUES ('140726', '太谷县', '3');
INSERT INTO `t_nation` VALUES ('341321', '砀山县', '3');
INSERT INTO `t_nation` VALUES ('230227', '富裕县', '3');
INSERT INTO `t_nation` VALUES ('210726', '黑山县', '3');
INSERT INTO `t_nation` VALUES ('530923', '永德县', '3');
INSERT INTO `t_nation` VALUES ('360982', '樟树市', '3');
INSERT INTO `t_nation` VALUES ('430481', '耒阳市', '3');
INSERT INTO `t_nation` VALUES ('610100', '西安市', '2');
INSERT INTO `t_nation` VALUES ('440307', '龙岗区', '3');
INSERT INTO `t_nation` VALUES ('220524', '柳河县', '3');
INSERT INTO `t_nation` VALUES ('460200', '三亚市', '2');
INSERT INTO `t_nation` VALUES ('441201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610729', '留坝县', '3');
INSERT INTO `t_nation` VALUES ('532300', '楚雄彝族自治州', '2');
INSERT INTO `t_nation` VALUES ('511102', '市中区', '3');
INSERT INTO `t_nation` VALUES ('150625', '杭锦旗', '3');
INSERT INTO `t_nation` VALUES ('532929', '云龙县', '3');
INSERT INTO `t_nation` VALUES ('520382', '仁怀市', '3');
INSERT INTO `t_nation` VALUES ('230624', '杜尔伯特蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('370684', '蓬莱市', '3');
INSERT INTO `t_nation` VALUES ('542127', '八宿县', '3');
INSERT INTO `t_nation` VALUES ('410103', '二七区', '3');
INSERT INTO `t_nation` VALUES ('441302', '惠城区', '3');
INSERT INTO `t_nation` VALUES ('370126', '商河县', '3');
INSERT INTO `t_nation` VALUES ('510104', '锦江区', '3');
INSERT INTO `t_nation` VALUES ('650108', '东山区', '3');
INSERT INTO `t_nation` VALUES ('341401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('520421', '平坝县', '3');
INSERT INTO `t_nation` VALUES ('520402', '西秀区', '3');
INSERT INTO `t_nation` VALUES ('140303', '矿区', '3');
INSERT INTO `t_nation` VALUES ('360203', '珠山区', '3');
INSERT INTO `t_nation` VALUES ('533300', '怒江傈僳族自治州', '2');
INSERT INTO `t_nation` VALUES ('511425', '青神县', '3');
INSERT INTO `t_nation` VALUES ('420323', '竹山县', '3');
INSERT INTO `t_nation` VALUES ('430603', '云溪区', '3');
INSERT INTO `t_nation` VALUES ('320124', '溧水县', '3');
INSERT INTO `t_nation` VALUES ('411702', '驿城区', '3');
INSERT INTO `t_nation` VALUES ('141101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360427', '星子县', '3');
INSERT INTO `t_nation` VALUES ('445222', '揭西县', '3');
INSERT INTO `t_nation` VALUES ('140411', '郊区', '3');
INSERT INTO `t_nation` VALUES ('632722', '杂多县', '3');
INSERT INTO `t_nation` VALUES ('140501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140227', '大同县', '3');
INSERT INTO `t_nation` VALUES ('341400', '巢湖市', '2');
INSERT INTO `t_nation` VALUES ('510113', '青白江区', '3');
INSERT INTO `t_nation` VALUES ('152528', '镶黄旗', '3');
INSERT INTO `t_nation` VALUES ('654023', '霍城县', '3');
INSERT INTO `t_nation` VALUES ('520328', '湄潭县', '3');
INSERT INTO `t_nation` VALUES ('411224', '卢氏县', '3');
INSERT INTO `t_nation` VALUES ('130635', '蠡县', '3');
INSERT INTO `t_nation` VALUES ('511902', '巴州区', '3');
INSERT INTO `t_nation` VALUES ('610331', '太白县', '3');
INSERT INTO `t_nation` VALUES ('411329', '新野县', '3');
INSERT INTO `t_nation` VALUES ('371482', '禹城市', '3');
INSERT INTO `t_nation` VALUES ('210422', '新宾满族自治县', '3');
INSERT INTO `t_nation` VALUES ('130224', '滦南县', '3');
INSERT INTO `t_nation` VALUES ('542523', '噶尔县', '3');
INSERT INTO `t_nation` VALUES ('632224', '刚察县', '3');
INSERT INTO `t_nation` VALUES ('533400', '迪庆藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('152200', '兴安盟', '2');
INSERT INTO `t_nation` VALUES ('513326', '道孚县', '3');
INSERT INTO `t_nation` VALUES ('522400', '毕节地区', '2');
INSERT INTO `t_nation` VALUES ('350628', '平和县', '3');
INSERT INTO `t_nation` VALUES ('220723', '乾安县', '3');
INSERT INTO `t_nation` VALUES ('513422', '木里藏族自治县', '3');
INSERT INTO `t_nation` VALUES ('450401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('341201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360622', '余江县', '3');
INSERT INTO `t_nation` VALUES ('222403', '敦化市', '3');
INSERT INTO `t_nation` VALUES ('350201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('140300', '阳泉市', '2');
INSERT INTO `t_nation` VALUES ('511126', '夹江县', '3');
INSERT INTO `t_nation` VALUES ('410104', '管城回族区', '3');
INSERT INTO `t_nation` VALUES ('610927', '镇坪县', '3');
INSERT INTO `t_nation` VALUES ('341004', '徽州区', '3');
INSERT INTO `t_nation` VALUES ('370882', '兖州市', '3');
INSERT INTO `t_nation` VALUES ('440803', '霞山区', '3');
INSERT INTO `t_nation` VALUES ('140701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('340881', '桐城市', '3');
INSERT INTO `t_nation` VALUES ('130726', '蔚县', '3');
INSERT INTO `t_nation` VALUES ('420683', '枣阳市', '3');
INSERT INTO `t_nation` VALUES ('510502', '江阳区', '3');
INSERT INTO `t_nation` VALUES ('420528', '长阳土家族自治县', '3');
INSERT INTO `t_nation` VALUES ('532924', '宾川县', '3');
INSERT INTO `t_nation` VALUES ('620103', '七里河区', '3');
INSERT INTO `t_nation` VALUES ('310119', '南汇区', '3');
INSERT INTO `t_nation` VALUES ('320105', '建邺区', '3');
INSERT INTO `t_nation` VALUES ('330205', '江北区', '3');
INSERT INTO `t_nation` VALUES ('110112', '通州区', '3');
INSERT INTO `t_nation` VALUES ('130621', '满城县', '3');
INSERT INTO `t_nation` VALUES ('440115', '南沙区', '3');
INSERT INTO `t_nation` VALUES ('371103', '岚山区', '3');
INSERT INTO `t_nation` VALUES ('320282', '宜兴市', '3');
INSERT INTO `t_nation` VALUES ('542336', '聂拉木县', '3');
INSERT INTO `t_nation` VALUES ('220724', '扶余县', '3');
INSERT INTO `t_nation` VALUES ('230206', '富拉尔基区', '3');
INSERT INTO `t_nation` VALUES ('360102', '东湖区', '3');
INSERT INTO `t_nation` VALUES ('150923', '商都县', '3');
INSERT INTO `t_nation` VALUES ('532325', '姚安县', '3');
INSERT INTO `t_nation` VALUES ('370284', '胶南市', '3');
INSERT INTO `t_nation` VALUES ('130922', '青县', '3');
INSERT INTO `t_nation` VALUES ('370782', '诸城市', '3');
INSERT INTO `t_nation` VALUES ('520222', '盘县', '3');
INSERT INTO `t_nation` VALUES ('450804', '覃塘区', '3');
INSERT INTO `t_nation` VALUES ('542331', '康马县', '3');
INSERT INTO `t_nation` VALUES ('130423', '临漳县', '3');
INSERT INTO `t_nation` VALUES ('522328', '安龙县', '3');
INSERT INTO `t_nation` VALUES ('230523', '宝清县', '3');
INSERT INTO `t_nation` VALUES ('231025', '林口县', '3');
INSERT INTO `t_nation` VALUES ('211081', '灯塔市', '3');
INSERT INTO `t_nation` VALUES ('510623', '中江县', '3');
INSERT INTO `t_nation` VALUES ('341501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542428', '班戈县', '3');
INSERT INTO `t_nation` VALUES ('130430', '邱县', '3');
INSERT INTO `t_nation` VALUES ('654028', '尼勒克县', '3');
INSERT INTO `t_nation` VALUES ('610823', '横山县', '3');
INSERT INTO `t_nation` VALUES ('341822', '广德县', '3');
INSERT INTO `t_nation` VALUES ('230621', '肇州县', '3');
INSERT INTO `t_nation` VALUES ('150723', '鄂伦春自治旗', '3');
INSERT INTO `t_nation` VALUES ('429005', '潜江市', '3');
INSERT INTO `t_nation` VALUES ('360111', '青山湖区', '3');
INSERT INTO `t_nation` VALUES ('542424', '聂荣县', '3');
INSERT INTO `t_nation` VALUES ('640302', '利通区', '3');
INSERT INTO `t_nation` VALUES ('411722', '上蔡县', '3');
INSERT INTO `t_nation` VALUES ('360501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530326', '会泽县', '3');
INSERT INTO `t_nation` VALUES ('522702', '福泉市', '3');
INSERT INTO `t_nation` VALUES ('230128', '通河县', '3');
INSERT INTO `t_nation` VALUES ('632600', '果洛藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('210300', '鞍山市', '2');
INSERT INTO `t_nation` VALUES ('450000', '广西壮族自治区', '1');
INSERT INTO `t_nation` VALUES ('210111', '苏家屯区', '3');
INSERT INTO `t_nation` VALUES ('430102', '芙蓉区', '3');
INSERT INTO `t_nation` VALUES ('610923', '宁陕县', '3');
INSERT INTO `t_nation` VALUES ('210601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370705', '奎文区', '3');
INSERT INTO `t_nation` VALUES ('510303', '贡井区', '3');
INSERT INTO `t_nation` VALUES ('230804', '前进区', '3');
INSERT INTO `t_nation` VALUES ('231005', '西安区', '3');
INSERT INTO `t_nation` VALUES ('421022', '公安县', '3');
INSERT INTO `t_nation` VALUES ('330500', '湖州市', '2');
INSERT INTO `t_nation` VALUES ('420281', '大冶市', '3');
INSERT INTO `t_nation` VALUES ('370112', '历城区', '3');
INSERT INTO `t_nation` VALUES ('140902', '忻府区', '3');
INSERT INTO `t_nation` VALUES ('411100', '漯河市', '2');
INSERT INTO `t_nation` VALUES ('510300', '自贡市', '2');
INSERT INTO `t_nation` VALUES ('350103', '台江区', '3');
INSERT INTO `t_nation` VALUES ('330700', '金华市', '2');
INSERT INTO `t_nation` VALUES ('542421', '那曲县', '3');
INSERT INTO `t_nation` VALUES ('441401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230622', '肇源县', '3');
INSERT INTO `t_nation` VALUES ('350981', '福安市', '3');
INSERT INTO `t_nation` VALUES ('440882', '雷州市', '3');
INSERT INTO `t_nation` VALUES ('370101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('441324', '龙门县', '3');
INSERT INTO `t_nation` VALUES ('511424', '丹棱县', '3');
INSERT INTO `t_nation` VALUES ('150123', '和林格尔县', '3');
INSERT INTO `t_nation` VALUES ('140424', '屯留县', '3');
INSERT INTO `t_nation` VALUES ('230604', '让胡路区', '3');
INSERT INTO `t_nation` VALUES ('420701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410185', '登封市', '3');
INSERT INTO `t_nation` VALUES ('140700', '晋中市', '2');
INSERT INTO `t_nation` VALUES ('440900', '茂名市', '2');
INSERT INTO `t_nation` VALUES ('350624', '诏安县', '3');
INSERT INTO `t_nation` VALUES ('632822', '都兰县', '3');
INSERT INTO `t_nation` VALUES ('652901', '阿克苏市', '3');
INSERT INTO `t_nation` VALUES ('440604', '禅城区', '3');
INSERT INTO `t_nation` VALUES ('341003', '黄山区', '3');
INSERT INTO `t_nation` VALUES ('150300', '乌海市', '2');
INSERT INTO `t_nation` VALUES ('652926', '拜城县', '3');
INSERT INTO `t_nation` VALUES ('522634', '雷山县', '3');
INSERT INTO `t_nation` VALUES ('131123', '武强县', '3');
INSERT INTO `t_nation` VALUES ('360723', '大余县', '3');
INSERT INTO `t_nation` VALUES ('441300', '惠州市', '2');
INSERT INTO `t_nation` VALUES ('130205', '开平区', '3');
INSERT INTO `t_nation` VALUES ('320901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('445302', '云城区', '3');
INSERT INTO `t_nation` VALUES ('430922', '桃江县', '3');
INSERT INTO `t_nation` VALUES ('220722', '长岭县', '3');
INSERT INTO `t_nation` VALUES ('411103', '郾城区', '3');
INSERT INTO `t_nation` VALUES ('360729', '全南县', '3');
INSERT INTO `t_nation` VALUES ('510000', '四川省', '1');
INSERT INTO `t_nation` VALUES ('371327', '莒南县', '3');
INSERT INTO `t_nation` VALUES ('410425', '郏县', '3');
INSERT INTO `t_nation` VALUES ('350301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620421', '靖远县', '3');
INSERT INTO `t_nation` VALUES ('350423', '清流县', '3');
INSERT INTO `t_nation` VALUES ('654224', '托里县', '3');
INSERT INTO `t_nation` VALUES ('140110', '晋源区', '3');
INSERT INTO `t_nation` VALUES ('420626', '保康县', '3');
INSERT INTO `t_nation` VALUES ('441424', '五华县', '3');
INSERT INTO `t_nation` VALUES ('520113', '白云区', '3');
INSERT INTO `t_nation` VALUES ('610730', '佛坪县', '3');
INSERT INTO `t_nation` VALUES ('410822', '博爱县', '3');
INSERT INTO `t_nation` VALUES ('320703', '连云区', '3');
INSERT INTO `t_nation` VALUES ('371323', '沂水县', '3');
INSERT INTO `t_nation` VALUES ('330901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('650200', '克拉玛依市', '2');
INSERT INTO `t_nation` VALUES ('360121', '南昌县', '3');
INSERT INTO `t_nation` VALUES ('500243', '彭水苗族土家族自治县', '3');
INSERT INTO `t_nation` VALUES ('510821', '旺苍县', '3');
INSERT INTO `t_nation` VALUES ('140825', '新绛县', '3');
INSERT INTO `t_nation` VALUES ('371100', '日照市', '2');
INSERT INTO `t_nation` VALUES ('340711', '郊区', '3');
INSERT INTO `t_nation` VALUES ('140106', '迎泽区', '3');
INSERT INTO `t_nation` VALUES ('140321', '平定县', '3');
INSERT INTO `t_nation` VALUES ('130825', '隆化县', '3');
INSERT INTO `t_nation` VALUES ('140823', '闻喜县', '3');
INSERT INTO `t_nation` VALUES ('451322', '象州县', '3');
INSERT INTO `t_nation` VALUES ('341124', '全椒县', '3');
INSERT INTO `t_nation` VALUES ('430725', '桃源县', '3');
INSERT INTO `t_nation` VALUES ('341702', '贵池区', '3');
INSERT INTO `t_nation` VALUES ('130625', '徐水县', '3');
INSERT INTO `t_nation` VALUES ('610102', '新城区', '3');
INSERT INTO `t_nation` VALUES ('230184', '五常市', '3');
INSERT INTO `t_nation` VALUES ('640422', '西吉县', '3');
INSERT INTO `t_nation` VALUES ('220106', '绿园区', '3');
INSERT INTO `t_nation` VALUES ('152921', '阿拉善左旗', '3');
INSERT INTO `t_nation` VALUES ('511302', '顺庆区', '3');
INSERT INTO `t_nation` VALUES ('150701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320400', '常州市', '2');
INSERT INTO `t_nation` VALUES ('361129', '万年县', '3');
INSERT INTO `t_nation` VALUES ('350924', '寿宁县', '3');
INSERT INTO `t_nation` VALUES ('542323', '江孜县', '3');
INSERT INTO `t_nation` VALUES ('440229', '翁源县', '3');
INSERT INTO `t_nation` VALUES ('511024', '威远县', '3');
INSERT INTO `t_nation` VALUES ('511000', '内江市', '2');
INSERT INTO `t_nation` VALUES ('410703', '卫滨区', '3');
INSERT INTO `t_nation` VALUES ('320621', '海安县', '3');
INSERT INTO `t_nation` VALUES ('422826', '咸丰县', '3');
INSERT INTO `t_nation` VALUES ('431226', '麻阳苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('130105', '新华区', '3');
INSERT INTO `t_nation` VALUES ('330201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371300', '临沂市', '2');
INSERT INTO `t_nation` VALUES ('441284', '四会市', '3');
INSERT INTO `t_nation` VALUES ('420203', '西塞山区', '3');
INSERT INTO `t_nation` VALUES ('370102', '历下区', '3');
INSERT INTO `t_nation` VALUES ('330211', '镇海区', '3');
INSERT INTO `t_nation` VALUES ('410105', '金水区', '3');
INSERT INTO `t_nation` VALUES ('341322', '萧县', '3');
INSERT INTO `t_nation` VALUES ('421381', '广水市', '3');
INSERT INTO `t_nation` VALUES ('530401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('430511', '北塔区', '3');
INSERT INTO `t_nation` VALUES ('510683', '绵竹市', '3');
INSERT INTO `t_nation` VALUES ('350104', '仓山区', '3');
INSERT INTO `t_nation` VALUES ('371524', '东阿县', '3');
INSERT INTO `t_nation` VALUES ('622901', '临夏市', '3');
INSERT INTO `t_nation` VALUES ('441621', '紫金县', '3');
INSERT INTO `t_nation` VALUES ('410205', '禹王台区', '3');
INSERT INTO `t_nation` VALUES ('620723', '临泽县', '3');
INSERT INTO `t_nation` VALUES ('350784', '建阳市', '3');
INSERT INTO `t_nation` VALUES ('230828', '汤原县', '3');
INSERT INTO `t_nation` VALUES ('130223', '滦县', '3');
INSERT INTO `t_nation` VALUES ('341823', '泾县', '3');
INSERT INTO `t_nation` VALUES ('320811', '清浦区', '3');
INSERT INTO `t_nation` VALUES ('131082', '三河市', '3');
INSERT INTO `t_nation` VALUES ('511521', '宜宾县', '3');
INSERT INTO `t_nation` VALUES ('150823', '乌拉特前旗', '3');
INSERT INTO `t_nation` VALUES ('652922', '温宿县', '3');
INSERT INTO `t_nation` VALUES ('340221', '芜湖县', '3');
INSERT INTO `t_nation` VALUES ('211102', '双台子区', '3');
INSERT INTO `t_nation` VALUES ('422800', '恩施土家族苗族自治州', '2');
INSERT INTO `t_nation` VALUES ('532624', '麻栗坡县', '3');
INSERT INTO `t_nation` VALUES ('230403', '工农区', '3');
INSERT INTO `t_nation` VALUES ('310108', '闸北区', '3');
INSERT INTO `t_nation` VALUES ('653225', '策勒县', '3');
INSERT INTO `t_nation` VALUES ('370521', '垦利县', '3');
INSERT INTO `t_nation` VALUES ('410900', '濮阳市', '2');
INSERT INTO `t_nation` VALUES ('542426', '申扎县', '3');
INSERT INTO `t_nation` VALUES ('361102', '信州区', '3');
INSERT INTO `t_nation` VALUES ('410882', '沁阳市', '3');
INSERT INTO `t_nation` VALUES ('130481', '武安市', '3');
INSERT INTO `t_nation` VALUES ('320722', '东海县', '3');
INSERT INTO `t_nation` VALUES ('350723', '光泽县', '3');
INSERT INTO `t_nation` VALUES ('520300', '遵义市', '2');
INSERT INTO `t_nation` VALUES ('152223', '扎赉特旗', '3');
INSERT INTO `t_nation` VALUES ('632700', '玉树藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('330521', '德清县', '3');
INSERT INTO `t_nation` VALUES ('321323', '泗阳县', '3');
INSERT INTO `t_nation` VALUES ('540000', '西藏自治区', '1');
INSERT INTO `t_nation` VALUES ('510129', '大邑县', '3');
INSERT INTO `t_nation` VALUES ('360323', '芦溪县', '3');
INSERT INTO `t_nation` VALUES ('320681', '启东市', '3');
INSERT INTO `t_nation` VALUES ('640121', '永宁县', '3');
INSERT INTO `t_nation` VALUES ('441600', '河源市', '2');
INSERT INTO `t_nation` VALUES ('211301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610115', '临潼区', '3');
INSERT INTO `t_nation` VALUES ('621221', '成县', '3');
INSERT INTO `t_nation` VALUES ('331004', '路桥区', '3');
INSERT INTO `t_nation` VALUES ('320601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('430204', '石峰区', '3');
INSERT INTO `t_nation` VALUES ('231202', '北林区', '3');
INSERT INTO `t_nation` VALUES ('211202', '银州区', '3');
INSERT INTO `t_nation` VALUES ('220503', '二道江区', '3');
INSERT INTO `t_nation` VALUES ('370828', '金乡县', '3');
INSERT INTO `t_nation` VALUES ('500109', '北碚区', '3');
INSERT INTO `t_nation` VALUES ('530925', '双江拉祜族佤族布朗族傣族自治县', '3');
INSERT INTO `t_nation` VALUES ('141032', '永和县', '3');
INSERT INTO `t_nation` VALUES ('522730', '龙里县', '3');
INSERT INTO `t_nation` VALUES ('420703', '华容区', '3');
INSERT INTO `t_nation` VALUES ('650106', '头屯河区', '3');
INSERT INTO `t_nation` VALUES ('510421', '米易县', '3');
INSERT INTO `t_nation` VALUES ('430105', '开福区', '3');
INSERT INTO `t_nation` VALUES ('210124', '法库县', '3');
INSERT INTO `t_nation` VALUES ('431002', '北湖区', '3');
INSERT INTO `t_nation` VALUES ('341000', '黄山市', '2');
INSERT INTO `t_nation` VALUES ('420103', '江汉区', '3');
INSERT INTO `t_nation` VALUES ('620525', '张家川回族自治县', '3');
INSERT INTO `t_nation` VALUES ('620600', '武威市', '2');
INSERT INTO `t_nation` VALUES ('610222', '宜君县', '3');
INSERT INTO `t_nation` VALUES ('211421', '绥中县', '3');
INSERT INTO `t_nation` VALUES ('431101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620724', '高台县', '3');
INSERT INTO `t_nation` VALUES ('341200', '阜阳市', '2');
INSERT INTO `t_nation` VALUES ('653101', '喀什市', '3');
INSERT INTO `t_nation` VALUES ('511822', '荥经县', '3');
INSERT INTO `t_nation` VALUES ('632522', '同德县', '3');
INSERT INTO `t_nation` VALUES ('140922', '五台县', '3');
INSERT INTO `t_nation` VALUES ('532322', '双柏县', '3');
INSERT INTO `t_nation` VALUES ('611026', '柞水县', '3');
INSERT INTO `t_nation` VALUES ('522428', '赫章县', '3');
INSERT INTO `t_nation` VALUES ('130201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('430406', '雁峰区', '3');
INSERT INTO `t_nation` VALUES ('542521', '普兰县', '3');
INSERT INTO `t_nation` VALUES ('341181', '天长市', '3');
INSERT INTO `t_nation` VALUES ('511525', '高县', '3');
INSERT INTO `t_nation` VALUES ('620922', '瓜州县', '3');
INSERT INTO `t_nation` VALUES ('360281', '乐平市', '3');
INSERT INTO `t_nation` VALUES ('640201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('513338', '得荣县', '3');
INSERT INTO `t_nation` VALUES ('440511', '金平区', '3');
INSERT INTO `t_nation` VALUES ('410200', '开封市', '2');
INSERT INTO `t_nation` VALUES ('430405', '珠晖区', '3');
INSERT INTO `t_nation` VALUES ('530502', '隆阳区', '3');
INSERT INTO `t_nation` VALUES ('450122', '武鸣县', '3');
INSERT INTO `t_nation` VALUES ('431025', '临武县', '3');
INSERT INTO `t_nation` VALUES ('620101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('421127', '黄梅县', '3');
INSERT INTO `t_nation` VALUES ('610826', '绥德县', '3');
INSERT INTO `t_nation` VALUES ('620823', '崇信县', '3');
INSERT INTO `t_nation` VALUES ('140521', '沁水县', '3');
INSERT INTO `t_nation` VALUES ('522727', '平塘县', '3');
INSERT INTO `t_nation` VALUES ('410503', '北关区', '3');
INSERT INTO `t_nation` VALUES ('532532', '河口瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('152522', '阿巴嘎旗', '3');
INSERT INTO `t_nation` VALUES ('360801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410502', '文峰区', '3');
INSERT INTO `t_nation` VALUES ('120102', '河东区', '3');
INSERT INTO `t_nation` VALUES ('431129', '江华瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('341102', '琅琊区', '3');
INSERT INTO `t_nation` VALUES ('330781', '兰溪市', '3');
INSERT INTO `t_nation` VALUES ('440507', '龙湖区', '3');
INSERT INTO `t_nation` VALUES ('652822', '轮台县', '3');
INSERT INTO `t_nation` VALUES ('211281', '调兵山市', '3');
INSERT INTO `t_nation` VALUES ('632825', '冷湖', '3');
INSERT INTO `t_nation` VALUES ('140930', '河曲县', '3');
INSERT INTO `t_nation` VALUES ('445121', '潮安县', '3');
INSERT INTO `t_nation` VALUES ('220604', '江源区', '3');
INSERT INTO `t_nation` VALUES ('422822', '建始县', '3');
INSERT INTO `t_nation` VALUES ('350982', '福鼎市', '3');
INSERT INTO `t_nation` VALUES ('410927', '台前县', '3');
INSERT INTO `t_nation` VALUES ('210782', '北镇市', '3');
INSERT INTO `t_nation` VALUES ('410601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('222402', '图们市', '3');
INSERT INTO `t_nation` VALUES ('360402', '庐山区', '3');
INSERT INTO `t_nation` VALUES ('150521', '科尔沁左翼中旗', '3');
INSERT INTO `t_nation` VALUES ('110117', '平谷区', '3');
INSERT INTO `t_nation` VALUES ('232721', '呼玛县', '3');
INSERT INTO `t_nation` VALUES ('520424', '关岭布依族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('340800', '安庆市', '2');
INSERT INTO `t_nation` VALUES ('130225', '乐亭县', '3');
INSERT INTO `t_nation` VALUES ('542301', '日喀则市', '3');
INSERT INTO `t_nation` VALUES ('652929', '柯坪县', '3');
INSERT INTO `t_nation` VALUES ('130828', '围场满族蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('320206', '惠山区', '3');
INSERT INTO `t_nation` VALUES ('320115', '江宁区', '3');
INSERT INTO `t_nation` VALUES ('430281', '醴陵市', '3');
INSERT INTO `t_nation` VALUES ('460000', '海南省', '1');
INSERT INTO `t_nation` VALUES ('360500', '新余市', '2');
INSERT INTO `t_nation` VALUES ('130903', '运河区', '3');
INSERT INTO `t_nation` VALUES ('460100', '海口市', '2');
INSERT INTO `t_nation` VALUES ('140882', '河津市', '3');
INSERT INTO `t_nation` VALUES ('130823', '平泉县', '3');
INSERT INTO `t_nation` VALUES ('440304', '福田区', '3');
INSERT INTO `t_nation` VALUES ('420624', '南漳县', '3');
INSERT INTO `t_nation` VALUES ('231100', '黑河市', '2');
INSERT INTO `t_nation` VALUES ('371400', '德州市', '2');
INSERT INTO `t_nation` VALUES ('532622', '砚山县', '3');
INSERT INTO `t_nation` VALUES ('451422', '宁明县', '3');
INSERT INTO `t_nation` VALUES ('410203', '顺河回族区', '3');
INSERT INTO `t_nation` VALUES ('540102', '城关区', '3');
INSERT INTO `t_nation` VALUES ('152526', '西乌珠穆沁旗', '3');
INSERT INTO `t_nation` VALUES ('420102', '江岸区', '3');
INSERT INTO `t_nation` VALUES ('469007', '东方市', '3');
INSERT INTO `t_nation` VALUES ('130000', '河北省', '1');
INSERT INTO `t_nation` VALUES ('130434', '魏县', '3');
INSERT INTO `t_nation` VALUES ('522628', '锦屏县', '3');
INSERT INTO `t_nation` VALUES ('420525', '远安县', '3');
INSERT INTO `t_nation` VALUES ('440183', '增城市', '3');
INSERT INTO `t_nation` VALUES ('350583', '南安市', '3');
INSERT INTO `t_nation` VALUES ('431123', '双牌县', '3');
INSERT INTO `t_nation` VALUES ('411502', '浉河区', '3');
INSERT INTO `t_nation` VALUES ('650121', '乌鲁木齐县', '3');
INSERT INTO `t_nation` VALUES ('610431', '武功县', '3');
INSERT INTO `t_nation` VALUES ('632222', '祁连县', '3');
INSERT INTO `t_nation` VALUES ('620102', '城关区', '3');
INSERT INTO `t_nation` VALUES ('210402', '新抚区', '3');
INSERT INTO `t_nation` VALUES ('533323', '福贡县', '3');
INSERT INTO `t_nation` VALUES ('330701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410727', '封丘县', '3');
INSERT INTO `t_nation` VALUES ('411701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330282', '慈溪市', '3');
INSERT INTO `t_nation` VALUES ('430407', '石鼓区', '3');
INSERT INTO `t_nation` VALUES ('610300', '宝鸡市', '2');
INSERT INTO `t_nation` VALUES ('340222', '繁昌县', '3');
INSERT INTO `t_nation` VALUES ('120104', '南开区', '3');
INSERT INTO `t_nation` VALUES ('532623', '西畴县', '3');
INSERT INTO `t_nation` VALUES ('230230', '克东县', '3');
INSERT INTO `t_nation` VALUES ('450503', '银海区', '3');
INSERT INTO `t_nation` VALUES ('510900', '遂宁市', '2');
INSERT INTO `t_nation` VALUES ('370302', '淄川区', '3');
INSERT INTO `t_nation` VALUES ('330401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230811', '郊区', '3');
INSERT INTO `t_nation` VALUES ('220701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370612', '牟平区', '3');
INSERT INTO `t_nation` VALUES ('370281', '胶州市', '3');
INSERT INTO `t_nation` VALUES ('310115', '浦东新区', '3');
INSERT INTO `t_nation` VALUES ('530825', '镇沅彝族哈尼族拉祜族自治县', '3');
INSERT INTO `t_nation` VALUES ('445100', '潮州市', '2');
INSERT INTO `t_nation` VALUES ('230600', '大庆市', '2');
INSERT INTO `t_nation` VALUES ('220281', '蛟河市', '3');
INSERT INTO `t_nation` VALUES ('371424', '临邑县', '3');
INSERT INTO `t_nation` VALUES ('350821', '长汀县', '3');
INSERT INTO `t_nation` VALUES ('230129', '延寿县', '3');
INSERT INTO `t_nation` VALUES ('520112', '乌当区', '3');
INSERT INTO `t_nation` VALUES ('510131', '蒲江县', '3');
INSERT INTO `t_nation` VALUES ('652924', '沙雅县', '3');
INSERT INTO `t_nation` VALUES ('411121', '舞阳县', '3');
INSERT INTO `t_nation` VALUES ('530702', '古城区', '3');
INSERT INTO `t_nation` VALUES ('150422', '巴林左旗', '3');
INSERT INTO `t_nation` VALUES ('211300', '朝阳市', '2');
INSERT INTO `t_nation` VALUES ('150000', '内蒙古自治区', '1');
INSERT INTO `t_nation` VALUES ('440232', '乳源瑶族自治县', '3');
INSERT INTO `t_nation` VALUES ('632128', '循化撒拉族自治县', '3');
INSERT INTO `t_nation` VALUES ('140923', '代县', '3');
INSERT INTO `t_nation` VALUES ('530629', '威信县', '3');
INSERT INTO `t_nation` VALUES ('654201', '塔城市', '3');
INSERT INTO `t_nation` VALUES ('430811', '武陵源区', '3');
INSERT INTO `t_nation` VALUES ('321202', '海陵区', '3');
INSERT INTO `t_nation` VALUES ('140801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('532932', '鹤庆县', '3');
INSERT INTO `t_nation` VALUES ('360421', '九江县', '3');
INSERT INTO `t_nation` VALUES ('340223', '南陵县', '3');
INSERT INTO `t_nation` VALUES ('220721', '前郭尔罗斯蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('500102', '涪陵区', '3');
INSERT INTO `t_nation` VALUES ('420902', '孝南区', '3');
INSERT INTO `t_nation` VALUES ('441421', '梅县', '3');
INSERT INTO `t_nation` VALUES ('321003', '邗江区', '3');
INSERT INTO `t_nation` VALUES ('652827', '和静县', '3');
INSERT INTO `t_nation` VALUES ('150124', '清水河县', '3');
INSERT INTO `t_nation` VALUES ('222400', '延边朝鲜族自治州', '2');
INSERT INTO `t_nation` VALUES ('520000', '贵州省', '1');
INSERT INTO `t_nation` VALUES ('330703', '金东区', '3');
INSERT INTO `t_nation` VALUES ('621228', '两当县', '3');
INSERT INTO `t_nation` VALUES ('510322', '富顺县', '3');
INSERT INTO `t_nation` VALUES ('652223', '伊吾县', '3');
INSERT INTO `t_nation` VALUES ('511132', '峨边彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('130723', '康保县', '3');
INSERT INTO `t_nation` VALUES ('611002', '商州区', '3');
INSERT INTO `t_nation` VALUES ('632623', '甘德县', '3');
INSERT INTO `t_nation` VALUES ('610629', '洛川县', '3');
INSERT INTO `t_nation` VALUES ('230826', '桦川县', '3');
INSERT INTO `t_nation` VALUES ('360424', '修水县', '3');
INSERT INTO `t_nation` VALUES ('340102', '瑶海区', '3');
INSERT INTO `t_nation` VALUES ('513229', '马尔康县', '3');
INSERT INTO `t_nation` VALUES ('654002', '伊宁市', '3');
INSERT INTO `t_nation` VALUES ('220422', '东辽县', '3');
INSERT INTO `t_nation` VALUES ('421125', '浠水县', '3');
INSERT INTO `t_nation` VALUES ('440000', '广东省', '1');
INSERT INTO `t_nation` VALUES ('150206', '白云矿区', '3');
INSERT INTO `t_nation` VALUES ('511527', '筠连县', '3');
INSERT INTO `t_nation` VALUES ('360733', '会昌县', '3');
INSERT INTO `t_nation` VALUES ('331100', '丽水市', '2');
INSERT INTO `t_nation` VALUES ('610481', '兴平市', '3');
INSERT INTO `t_nation` VALUES ('370301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('653001', '阿图什市', '3');
INSERT INTO `t_nation` VALUES ('542526', '改则县', '3');
INSERT INTO `t_nation` VALUES ('230833', '抚远县', '3');
INSERT INTO `t_nation` VALUES ('441801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410402', '新华区', '3');
INSERT INTO `t_nation` VALUES ('620501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230407', '兴山区', '3');
INSERT INTO `t_nation` VALUES ('410306', '吉利区', '3');
INSERT INTO `t_nation` VALUES ('371621', '惠民县', '3');
INSERT INTO `t_nation` VALUES ('500227', '璧山县', '3');
INSERT INTO `t_nation` VALUES ('533325', '兰坪白族普米族自治县', '3');
INSERT INTO `t_nation` VALUES ('210900', '阜新市', '2');
INSERT INTO `t_nation` VALUES ('430721', '安乡县', '3');
INSERT INTO `t_nation` VALUES ('330903', '普陀区', '3');
INSERT INTO `t_nation` VALUES ('510722', '三台县', '3');
INSERT INTO `t_nation` VALUES ('370785', '高密市', '3');
INSERT INTO `t_nation` VALUES ('654324', '哈巴河县', '3');
INSERT INTO `t_nation` VALUES ('331181', '龙泉市', '3');
INSERT INTO `t_nation` VALUES ('150401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('532530', '金平苗族瑶族傣族自治县', '3');
INSERT INTO `t_nation` VALUES ('222404', '珲春市', '3');
INSERT INTO `t_nation` VALUES ('210504', '明山区', '3');
INSERT INTO `t_nation` VALUES ('130433', '馆陶县', '3');
INSERT INTO `t_nation` VALUES ('513435', '甘洛县', '3');
INSERT INTO `t_nation` VALUES ('610423', '泾阳县', '3');
INSERT INTO `t_nation` VALUES ('450821', '平南县', '3');
INSERT INTO `t_nation` VALUES ('220300', '四平市', '2');
INSERT INTO `t_nation` VALUES ('130803', '双滦区', '3');
INSERT INTO `t_nation` VALUES ('410307', '洛龙区', '3');
INSERT INTO `t_nation` VALUES ('230104', '道外区', '3');
INSERT INTO `t_nation` VALUES ('610623', '子长县', '3');
INSERT INTO `t_nation` VALUES ('532531', '绿春县', '3');
INSERT INTO `t_nation` VALUES ('530126', '石林彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('430981', '沅江市', '3');
INSERT INTO `t_nation` VALUES ('150223', '达尔罕茂明安联合旗', '3');
INSERT INTO `t_nation` VALUES ('140181', '古交市', '3');
INSERT INTO `t_nation` VALUES ('410221', '杞县', '3');
INSERT INTO `t_nation` VALUES ('510703', '涪城区', '3');
INSERT INTO `t_nation` VALUES ('220702', '宁江区', '3');
INSERT INTO `t_nation` VALUES ('341122', '来安县', '3');
INSERT INTO `t_nation` VALUES ('451223', '凤山县', '3');
INSERT INTO `t_nation` VALUES ('220882', '大安市', '3');
INSERT INTO `t_nation` VALUES ('321203', '高港区', '3');
INSERT INTO `t_nation` VALUES ('150525', '奈曼旗', '3');
INSERT INTO `t_nation` VALUES ('410522', '安阳县', '3');
INSERT INTO `t_nation` VALUES ('230712', '汤旺河区', '3');
INSERT INTO `t_nation` VALUES ('150203', '昆都仑区', '3');
INSERT INTO `t_nation` VALUES ('431321', '双峰县', '3');
INSERT INTO `t_nation` VALUES ('361000', '抚州市', '2');
INSERT INTO `t_nation` VALUES ('361027', '金溪县', '3');
INSERT INTO `t_nation` VALUES ('331000', '台州市', '2');
INSERT INTO `t_nation` VALUES ('500238', '巫溪县', '3');
INSERT INTO `t_nation` VALUES ('410324', '栾川县', '3');
INSERT INTO `t_nation` VALUES ('460108', '美兰区', '3');
INSERT INTO `t_nation` VALUES ('430521', '邵东县', '3');
INSERT INTO `t_nation` VALUES ('230716', '上甘岭区', '3');
INSERT INTO `t_nation` VALUES ('451023', '平果县', '3');
INSERT INTO `t_nation` VALUES ('610801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('451226', '环江毛南族自治县', '3');
INSERT INTO `t_nation` VALUES ('130984', '河间市', '3');
INSERT INTO `t_nation` VALUES ('230713', '带岭区', '3');
INSERT INTO `t_nation` VALUES ('230127', '木兰县', '3');
INSERT INTO `t_nation` VALUES ('210802', '站前区', '3');
INSERT INTO `t_nation` VALUES ('141128', '方山县', '3');
INSERT INTO `t_nation` VALUES ('653221', '和田县', '3');
INSERT INTO `t_nation` VALUES ('610000', '陕西省', '1');
INSERT INTO `t_nation` VALUES ('150726', '新巴尔虎左旗', '3');
INSERT INTO `t_nation` VALUES ('620801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('421083', '洪湖市', '3');
INSERT INTO `t_nation` VALUES ('350629', '华安县', '3');
INSERT INTO `t_nation` VALUES ('440400', '珠海市', '2');
INSERT INTO `t_nation` VALUES ('510601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410100', '郑州市', '2');
INSERT INTO `t_nation` VALUES ('500223', '潼南县', '3');
INSERT INTO `t_nation` VALUES ('610822', '府谷县', '3');
INSERT INTO `t_nation` VALUES ('440704', '江海区', '3');
INSERT INTO `t_nation` VALUES ('150122', '托克托县', '3');
INSERT INTO `t_nation` VALUES ('320302', '鼓楼区', '3');
INSERT INTO `t_nation` VALUES ('520122', '息烽县', '3');
INSERT INTO `t_nation` VALUES ('650203', '克拉玛依区', '3');
INSERT INTO `t_nation` VALUES ('440500', '汕头市', '2');
INSERT INTO `t_nation` VALUES ('532922', '漾濞彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('530601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610500', '渭南市', '2');
INSERT INTO `t_nation` VALUES ('330902', '定海区', '3');
INSERT INTO `t_nation` VALUES ('542224', '桑日县', '3');
INSERT INTO `t_nation` VALUES ('370401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('361101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('321011', '维扬区', '3');
INSERT INTO `t_nation` VALUES ('360823', '峡江县', '3');
INSERT INTO `t_nation` VALUES ('510184', '崇州市', '3');
INSERT INTO `t_nation` VALUES ('211400', '葫芦岛市', '2');
INSERT INTO `t_nation` VALUES ('511301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('420106', '武昌区', '3');
INSERT INTO `t_nation` VALUES ('430602', '岳阳楼区', '3');
INSERT INTO `t_nation` VALUES ('513221', '汶川县', '3');
INSERT INTO `t_nation` VALUES ('451102', '八步区', '3');
INSERT INTO `t_nation` VALUES ('131124', '饶阳县', '3');
INSERT INTO `t_nation` VALUES ('451323', '武宣县', '3');
INSERT INTO `t_nation` VALUES ('140100', '太原市', '2');
INSERT INTO `t_nation` VALUES ('440501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('321300', '宿迁市', '2');
INSERT INTO `t_nation` VALUES ('542334', '亚东县', '3');
INSERT INTO `t_nation` VALUES ('440306', '宝安区', '3');
INSERT INTO `t_nation` VALUES ('522427', '威宁彝族回族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('653226', '于田县', '3');
INSERT INTO `t_nation` VALUES ('460201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411528', '息县', '3');
INSERT INTO `t_nation` VALUES ('361021', '南城县', '3');
INSERT INTO `t_nation` VALUES ('451021', '田阳县', '3');
INSERT INTO `t_nation` VALUES ('120113', '北辰区', '3');
INSERT INTO `t_nation` VALUES ('810000', '香港特别行政区', '1');
INSERT INTO `t_nation` VALUES ('371725', '郓城县', '3');
INSERT INTO `t_nation` VALUES ('371721', '曹县', '3');
INSERT INTO `t_nation` VALUES ('350701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('469035', '保亭黎族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('320802', '清河区', '3');
INSERT INTO `t_nation` VALUES ('220821', '镇赉县', '3');
INSERT INTO `t_nation` VALUES ('420527', '秭归县', '3');
INSERT INTO `t_nation` VALUES ('650100', '乌鲁木齐市', '2');
INSERT INTO `t_nation` VALUES ('440224', '仁化县', '3');
INSERT INTO `t_nation` VALUES ('522422', '大方县', '3');
INSERT INTO `t_nation` VALUES ('522601', '凯里市', '3');
INSERT INTO `t_nation` VALUES ('500105', '江北区', '3');
INSERT INTO `t_nation` VALUES ('210303', '铁西区', '3');
INSERT INTO `t_nation` VALUES ('130702', '桥东区', '3');
INSERT INTO `t_nation` VALUES ('533423', '维西傈僳族自治县', '3');
INSERT INTO `t_nation` VALUES ('511901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('632800', '海西蒙古族藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('513322', '泸定县', '3');
INSERT INTO `t_nation` VALUES ('150782', '牙克石市', '3');
INSERT INTO `t_nation` VALUES ('350200', '厦门市', '2');
INSERT INTO `t_nation` VALUES ('370201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('469025', '定安县', '3');
INSERT INTO `t_nation` VALUES ('532901', '大理市', '3');
INSERT INTO `t_nation` VALUES ('130500', '邢台市', '2');
INSERT INTO `t_nation` VALUES ('350300', '莆田市', '2');
INSERT INTO `t_nation` VALUES ('371421', '陵县', '3');
INSERT INTO `t_nation` VALUES ('320804', '淮阴区', '3');
INSERT INTO `t_nation` VALUES ('411622', '西华县', '3');
INSERT INTO `t_nation` VALUES ('330183', '富阳市', '3');
INSERT INTO `t_nation` VALUES ('130426', '涉县', '3');
INSERT INTO `t_nation` VALUES ('341502', '金安区', '3');
INSERT INTO `t_nation` VALUES ('130401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('500000', '重庆市', '1');
INSERT INTO `t_nation` VALUES ('469003', '儋州市', '3');
INSERT INTO `t_nation` VALUES ('450922', '陆川县', '3');
INSERT INTO `t_nation` VALUES ('522222', '江口县', '3');
INSERT INTO `t_nation` VALUES ('230522', '友谊县', '3');
INSERT INTO `t_nation` VALUES ('150825', '乌拉特后旗', '3');
INSERT INTO `t_nation` VALUES ('340421', '凤台县', '3');
INSERT INTO `t_nation` VALUES ('419000', '济源市', '2');
INSERT INTO `t_nation` VALUES ('340602', '杜集区', '3');
INSERT INTO `t_nation` VALUES ('230208', '梅里斯达斡尔族区', '3');
INSERT INTO `t_nation` VALUES ('445201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130683', '安国市', '3');
INSERT INTO `t_nation` VALUES ('321183', '句容市', '3');
INSERT INTO `t_nation` VALUES ('654321', '布尔津县', '3');
INSERT INTO `t_nation` VALUES ('652123', '托克逊县', '3');
INSERT INTO `t_nation` VALUES ('420502', '西陵区', '3');
INSERT INTO `t_nation` VALUES ('331123', '遂昌县', '3');
INSERT INTO `t_nation` VALUES ('621201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('654024', '巩留县', '3');
INSERT INTO `t_nation` VALUES ('621125', '漳县', '3');
INSERT INTO `t_nation` VALUES ('530424', '华宁县', '3');
INSERT INTO `t_nation` VALUES ('130432', '广平县', '3');
INSERT INTO `t_nation` VALUES ('510107', '武侯区', '3');
INSERT INTO `t_nation` VALUES ('320724', '灌南县', '3');
INSERT INTO `t_nation` VALUES ('350212', '同安区', '3');
INSERT INTO `t_nation` VALUES ('211403', '龙港区', '3');
INSERT INTO `t_nation` VALUES ('370282', '即墨市', '3');
INSERT INTO `t_nation` VALUES ('420303', '张湾区', '3');
INSERT INTO `t_nation` VALUES ('500226', '荣昌县', '3');
INSERT INTO `t_nation` VALUES ('610427', '彬县', '3');
INSERT INTO `t_nation` VALUES ('632523', '贵德县', '3');
INSERT INTO `t_nation` VALUES ('320623', '如东县', '3');
INSERT INTO `t_nation` VALUES ('371402', '德城区', '3');
INSERT INTO `t_nation` VALUES ('513428', '普格县', '3');
INSERT INTO `t_nation` VALUES ('130700', '张家口市', '2');
INSERT INTO `t_nation` VALUES ('360321', '莲花县', '3');
INSERT INTO `t_nation` VALUES ('610501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('431200', '怀化市', '2');
INSERT INTO `t_nation` VALUES ('411500', '信阳市', '2');
INSERT INTO `t_nation` VALUES ('131003', '广阳区', '3');
INSERT INTO `t_nation` VALUES ('511501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('411621', '扶沟县', '3');
INSERT INTO `t_nation` VALUES ('653200', '和田地区', '2');
INSERT INTO `t_nation` VALUES ('533421', '香格里拉县', '3');
INSERT INTO `t_nation` VALUES ('500230', '丰都县', '3');
INSERT INTO `t_nation` VALUES ('371081', '文登市', '3');
INSERT INTO `t_nation` VALUES ('653222', '墨玉县', '3');
INSERT INTO `t_nation` VALUES ('653127', '麦盖提县', '3');
INSERT INTO `t_nation` VALUES ('130627', '唐县', '3');
INSERT INTO `t_nation` VALUES ('220581', '梅河口市', '3');
INSERT INTO `t_nation` VALUES ('610800', '榆林市', '2');
INSERT INTO `t_nation` VALUES ('140322', '盂县', '3');
INSERT INTO `t_nation` VALUES ('350923', '屏南县', '3');
INSERT INTO `t_nation` VALUES ('421087', '松滋市', '3');
INSERT INTO `t_nation` VALUES ('370522', '利津县', '3');
INSERT INTO `t_nation` VALUES ('140221', '阳高县', '3');
INSERT INTO `t_nation` VALUES ('140226', '左云县', '3');
INSERT INTO `t_nation` VALUES ('431124', '道县', '3');
INSERT INTO `t_nation` VALUES ('350421', '明溪县', '3');
INSERT INTO `t_nation` VALUES ('222426', '安图县', '3');
INSERT INTO `t_nation` VALUES ('350211', '集美区', '3');
INSERT INTO `t_nation` VALUES ('511133', '马边彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('141123', '兴县', '3');
INSERT INTO `t_nation` VALUES ('371602', '滨城区', '3');
INSERT INTO `t_nation` VALUES ('331001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('370702', '潍城区', '3');
INSERT INTO `t_nation` VALUES ('511600', '广安市', '2');
INSERT INTO `t_nation` VALUES ('230703', '南岔区', '3');
INSERT INTO `t_nation` VALUES ('341222', '太和县', '3');
INSERT INTO `t_nation` VALUES ('220401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('340702', '铜官山区', '3');
INSERT INTO `t_nation` VALUES ('350111', '晋安区', '3');
INSERT INTO `t_nation` VALUES ('510132', '新津县', '3');
INSERT INTO `t_nation` VALUES ('451031', '隆林各族自治县', '3');
INSERT INTO `t_nation` VALUES ('610621', '延长县', '3');
INSERT INTO `t_nation` VALUES ('420113', '汉南区', '3');
INSERT INTO `t_nation` VALUES ('330100', '杭州市', '2');
INSERT INTO `t_nation` VALUES ('652927', '乌什县', '3');
INSERT INTO `t_nation` VALUES ('131125', '安平县', '3');
INSERT INTO `t_nation` VALUES ('530100', '昆明市', '2');
INSERT INTO `t_nation` VALUES ('451026', '那坡县', '3');
INSERT INTO `t_nation` VALUES ('130425', '大名县', '3');
INSERT INTO `t_nation` VALUES ('320506', '吴中区', '3');
INSERT INTO `t_nation` VALUES ('451201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('141026', '安泽县', '3');
INSERT INTO `t_nation` VALUES ('621024', '合水县', '3');
INSERT INTO `t_nation` VALUES ('510112', '龙泉驿区', '3');
INSERT INTO `t_nation` VALUES ('650102', '天山区', '3');
INSERT INTO `t_nation` VALUES ('410400', '平顶山市', '2');
INSERT INTO `t_nation` VALUES ('421100', '黄冈市', '2');
INSERT INTO `t_nation` VALUES ('411626', '淮阳县', '3');
INSERT INTO `t_nation` VALUES ('630102', '城东区', '3');
INSERT INTO `t_nation` VALUES ('430525', '洞口县', '3');
INSERT INTO `t_nation` VALUES ('230406', '东山区', '3');
INSERT INTO `t_nation` VALUES ('150721', '阿荣旗', '3');
INSERT INTO `t_nation` VALUES ('654326', '吉木乃县', '3');
INSERT INTO `t_nation` VALUES ('220302', '铁西区', '3');
INSERT INTO `t_nation` VALUES ('140105', '小店区', '3');
INSERT INTO `t_nation` VALUES ('420800', '荆门市', '2');
INSERT INTO `t_nation` VALUES ('451321', '忻城县', '3');
INSERT INTO `t_nation` VALUES ('650205', '乌尔禾区', '3');
INSERT INTO `t_nation` VALUES ('610625', '志丹县', '3');
INSERT INTO `t_nation` VALUES ('152221', '科尔沁右翼前旗', '3');
INSERT INTO `t_nation` VALUES ('360821', '吉安县', '3');
INSERT INTO `t_nation` VALUES ('230706', '翠峦区', '3');
INSERT INTO `t_nation` VALUES ('513228', '黑水县', '3');
INSERT INTO `t_nation` VALUES ('130727', '阳原县', '3');
INSERT INTO `t_nation` VALUES ('451421', '扶绥县', '3');
INSERT INTO `t_nation` VALUES ('450103', '青秀区', '3');
INSERT INTO `t_nation` VALUES ('371623', '无棣县', '3');
INSERT INTO `t_nation` VALUES ('441502', '城区', '3');
INSERT INTO `t_nation` VALUES ('530125', '宜良县', '3');
INSERT INTO `t_nation` VALUES ('450327', '灌阳县', '3');
INSERT INTO `t_nation` VALUES ('411221', '渑池县', '3');
INSERT INTO `t_nation` VALUES ('450602', '港口区', '3');
INSERT INTO `t_nation` VALUES ('530122', '晋宁县', '3');
INSERT INTO `t_nation` VALUES ('450501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230503', '岭东区', '3');
INSERT INTO `t_nation` VALUES ('330682', '上虞市', '3');
INSERT INTO `t_nation` VALUES ('511522', '南溪县', '3');
INSERT INTO `t_nation` VALUES ('440801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('420101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('621200', '陇南市', '2');
INSERT INTO `t_nation` VALUES ('130521', '邢台县', '3');
INSERT INTO `t_nation` VALUES ('431227', '新晃侗族自治县', '3');
INSERT INTO `t_nation` VALUES ('130207', '丰南区', '3');
INSERT INTO `t_nation` VALUES ('120116', '滨海新区', '3');
INSERT INTO `t_nation` VALUES ('622921', '临夏县', '3');
INSERT INTO `t_nation` VALUES ('130601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('321112', '丹徒区', '3');
INSERT INTO `t_nation` VALUES ('421101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('542422', '嘉黎县', '3');
INSERT INTO `t_nation` VALUES ('370683', '莱州市', '3');
INSERT INTO `t_nation` VALUES ('330824', '开化县', '3');
INSERT INTO `t_nation` VALUES ('410800', '焦作市', '2');
INSERT INTO `t_nation` VALUES ('210522', '桓仁满族自治县', '3');
INSERT INTO `t_nation` VALUES ('230701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371525', '冠县', '3');
INSERT INTO `t_nation` VALUES ('510525', '古蔺县', '3');
INSERT INTO `t_nation` VALUES ('420201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('440105', '海珠区', '3');
INSERT INTO `t_nation` VALUES ('211324', '喀喇沁左翼蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('621025', '正宁县', '3');
INSERT INTO `t_nation` VALUES ('350504', '洛江区', '3');
INSERT INTO `t_nation` VALUES ('610922', '石泉县', '3');
INSERT INTO `t_nation` VALUES ('510500', '泸州市', '2');
INSERT INTO `t_nation` VALUES ('350782', '武夷山市', '3');
INSERT INTO `t_nation` VALUES ('140924', '繁峙县', '3');
INSERT INTO `t_nation` VALUES ('230101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('430701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('150524', '库伦旗', '3');
INSERT INTO `t_nation` VALUES ('530422', '澄江县', '3');
INSERT INTO `t_nation` VALUES ('632321', '同仁县', '3');
INSERT INTO `t_nation` VALUES ('513425', '会理县', '3');
INSERT INTO `t_nation` VALUES ('340111', '包河区', '3');
INSERT INTO `t_nation` VALUES ('533102', '瑞丽市', '3');
INSERT INTO `t_nation` VALUES ('141027', '浮山县', '3');
INSERT INTO `t_nation` VALUES ('511725', '渠县', '3');
INSERT INTO `t_nation` VALUES ('410181', '巩义市', '3');
INSERT INTO `t_nation` VALUES ('330621', '绍兴县', '3');
INSERT INTO `t_nation` VALUES ('659001', '石河子市', '3');
INSERT INTO `t_nation` VALUES ('140212', '新荣区', '3');
INSERT INTO `t_nation` VALUES ('411300', '南阳市', '2');
INSERT INTO `t_nation` VALUES ('210602', '元宝区', '3');
INSERT INTO `t_nation` VALUES ('141126', '石楼县', '3');
INSERT INTO `t_nation` VALUES ('320700', '连云港市', '2');
INSERT INTO `t_nation` VALUES ('532526', '弥勒县', '3');
INSERT INTO `t_nation` VALUES ('431100', '永州市', '2');
INSERT INTO `t_nation` VALUES ('620522', '秦安县', '3');
INSERT INTO `t_nation` VALUES ('522327', '册亨县', '3');
INSERT INTO `t_nation` VALUES ('320100', '南京市', '2');
INSERT INTO `t_nation` VALUES ('513424', '德昌县', '3');
INSERT INTO `t_nation` VALUES ('220203', '龙潭区', '3');
INSERT INTO `t_nation` VALUES ('610523', '大荔县', '3');
INSERT INTO `t_nation` VALUES ('430101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('350824', '武平县', '3');
INSERT INTO `t_nation` VALUES ('211402', '连山区', '3');
INSERT INTO `t_nation` VALUES ('140223', '广灵县', '3');
INSERT INTO `t_nation` VALUES ('650101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('610122', '蓝田县', '3');
INSERT INTO `t_nation` VALUES ('430503', '大祥区', '3');
INSERT INTO `t_nation` VALUES ('230500', '双鸭山市', '2');
INSERT INTO `t_nation` VALUES ('321100', '镇江市', '2');
INSERT INTO `t_nation` VALUES ('231101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130502', '桥东区', '3');
INSERT INTO `t_nation` VALUES ('220122', '农安县', '3');
INSERT INTO `t_nation` VALUES ('341202', '颍州区', '3');
INSERT INTO `t_nation` VALUES ('320125', '高淳县', '3');
INSERT INTO `t_nation` VALUES ('410527', '内黄县', '3');
INSERT INTO `t_nation` VALUES ('230882', '富锦市', '3');
INSERT INTO `t_nation` VALUES ('630104', '城西区', '3');
INSERT INTO `t_nation` VALUES ('230801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220112', '双阳区', '3');
INSERT INTO `t_nation` VALUES ('422801', '恩施市', '3');
INSERT INTO `t_nation` VALUES ('652201', '哈密市', '3');
INSERT INTO `t_nation` VALUES ('220301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('211101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('341402', '居巢区', '3');
INSERT INTO `t_nation` VALUES ('451000', '百色市', '2');
INSERT INTO `t_nation` VALUES ('330182', '建德市', '3');
INSERT INTO `t_nation` VALUES ('442001', '中山市市辖区', '3');
INSERT INTO `t_nation` VALUES ('469034', '陵水黎族自治县', '3');
INSERT INTO `t_nation` VALUES ('350424', '宁化县', '3');
INSERT INTO `t_nation` VALUES ('420984', '汉川市', '3');
INSERT INTO `t_nation` VALUES ('130526', '任县', '3');
INSERT INTO `t_nation` VALUES ('331101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('433125', '保靖县', '3');
INSERT INTO `t_nation` VALUES ('130636', '顺平县', '3');
INSERT INTO `t_nation` VALUES ('513222', '理县', '3');
INSERT INTO `t_nation` VALUES ('130684', '高碑店市', '3');
INSERT INTO `t_nation` VALUES ('321200', '泰州市', '2');
INSERT INTO `t_nation` VALUES ('340811', '宜秀区', '3');
INSERT INTO `t_nation` VALUES ('654200', '塔城地区', '2');
INSERT INTO `t_nation` VALUES ('652122', '鄯善县', '3');
INSERT INTO `t_nation` VALUES ('411282', '灵宝市', '3');
INSERT INTO `t_nation` VALUES ('522623', '施秉县', '3');
INSERT INTO `t_nation` VALUES ('430502', '双清区', '3');
INSERT INTO `t_nation` VALUES ('632121', '平安县', '3');
INSERT INTO `t_nation` VALUES ('510701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360830', '永新县', '3');
INSERT INTO `t_nation` VALUES ('231226', '绥棱县', '3');
INSERT INTO `t_nation` VALUES ('530600', '昭通市', '2');
INSERT INTO `t_nation` VALUES ('440607', '三水区', '3');
INSERT INTO `t_nation` VALUES ('360725', '崇义县', '3');
INSERT INTO `t_nation` VALUES ('220500', '通化市', '2');
INSERT INTO `t_nation` VALUES ('530129', '寻甸回族彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('140525', '泽州县', '3');
INSERT INTO `t_nation` VALUES ('451481', '凭祥市', '3');
INSERT INTO `t_nation` VALUES ('130321', '青龙满族自治县', '3');
INSERT INTO `t_nation` VALUES ('650204', '白碱滩区', '3');
INSERT INTO `t_nation` VALUES ('140624', '怀仁县', '3');
INSERT INTO `t_nation` VALUES ('140426', '黎城县', '3');
INSERT INTO `t_nation` VALUES ('511113', '金口河区', '3');
INSERT INTO `t_nation` VALUES ('522723', '贵定县', '3');
INSERT INTO `t_nation` VALUES ('410782', '辉县市', '3');
INSERT INTO `t_nation` VALUES ('530128', '禄劝彝族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('140929', '岢岚县', '3');
INSERT INTO `t_nation` VALUES ('410926', '范县', '3');
INSERT INTO `t_nation` VALUES ('150207', '九原区', '3');
INSERT INTO `t_nation` VALUES ('533122', '梁河县', '3');
INSERT INTO `t_nation` VALUES ('620423', '景泰县', '3');
INSERT INTO `t_nation` VALUES ('230201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('522728', '罗甸县', '3');
INSERT INTO `t_nation` VALUES ('610103', '碑林区', '3');
INSERT INTO `t_nation` VALUES ('320202', '崇安区', '3');
INSERT INTO `t_nation` VALUES ('610522', '潼关县', '3');
INSERT INTO `t_nation` VALUES ('130722', '张北县', '3');
INSERT INTO `t_nation` VALUES ('360900', '宜春市', '2');
INSERT INTO `t_nation` VALUES ('430301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371581', '临清市', '3');
INSERT INTO `t_nation` VALUES ('430902', '资阳区', '3');
INSERT INTO `t_nation` VALUES ('500118', '永川区', '3');
INSERT INTO `t_nation` VALUES ('150902', '集宁区', '3');
INSERT INTO `t_nation` VALUES ('450802', '港北区', '3');
INSERT INTO `t_nation` VALUES ('542125', '丁青县', '3');
INSERT INTO `t_nation` VALUES ('511824', '石棉县', '3');
INSERT INTO `t_nation` VALUES ('450201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('371401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360803', '青原区', '3');
INSERT INTO `t_nation` VALUES ('410821', '修武县', '3');
INSERT INTO `t_nation` VALUES ('230204', '铁锋区', '3');
INSERT INTO `t_nation` VALUES ('610602', '宝塔区', '3');
INSERT INTO `t_nation` VALUES ('522731', '惠水县', '3');
INSERT INTO `t_nation` VALUES ('620524', '武山县', '3');
INSERT INTO `t_nation` VALUES ('513232', '若尔盖县', '3');
INSERT INTO `t_nation` VALUES ('341825', '旌德县', '3');
INSERT INTO `t_nation` VALUES ('360901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('511304', '嘉陵区', '3');
INSERT INTO `t_nation` VALUES ('130108', '裕华区', '3');
INSERT INTO `t_nation` VALUES ('440204', '浈江区', '3');
INSERT INTO `t_nation` VALUES ('230301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320116', '六合区', '3');
INSERT INTO `t_nation` VALUES ('440201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('640000', '宁夏回族自治区', '1');
INSERT INTO `t_nation` VALUES ('320205', '锡山区', '3');
INSERT INTO `t_nation` VALUES ('653022', '阿克陶县', '3');
INSERT INTO `t_nation` VALUES ('510726', '北川羌族自治县', '3');
INSERT INTO `t_nation` VALUES ('610900', '安康市', '2');
INSERT INTO `t_nation` VALUES ('371322', '郯城县', '3');
INSERT INTO `t_nation` VALUES ('110109', '门头沟区', '3');
INSERT INTO `t_nation` VALUES ('513224', '松潘县', '3');
INSERT INTO `t_nation` VALUES ('321084', '高邮市', '3');
INSERT INTO `t_nation` VALUES ('321001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('820000', '澳门特别行政区', '1');
INSERT INTO `t_nation` VALUES ('230800', '佳木斯市', '2');
INSERT INTO `t_nation` VALUES ('420381', '丹江口市', '3');
INSERT INTO `t_nation` VALUES ('410728', '长垣县', '3');
INSERT INTO `t_nation` VALUES ('130983', '黄骅市', '3');
INSERT INTO `t_nation` VALUES ('140427', '壶关县', '3');
INSERT INTO `t_nation` VALUES ('430901', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('330328', '文成县', '3');
INSERT INTO `t_nation` VALUES ('440703', '蓬江区', '3');
INSERT INTO `t_nation` VALUES ('131128', '阜城县', '3');
INSERT INTO `t_nation` VALUES ('110103', '崇文区', '3');
INSERT INTO `t_nation` VALUES ('445381', '罗定市', '3');
INSERT INTO `t_nation` VALUES ('130637', '博野县', '3');
INSERT INTO `t_nation` VALUES ('610101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('640101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130929', '献县', '3');
INSERT INTO `t_nation` VALUES ('441800', '清远市', '2');
INSERT INTO `t_nation` VALUES ('610728', '镇巴县', '3');
INSERT INTO `t_nation` VALUES ('350403', '三元区', '3');
INSERT INTO `t_nation` VALUES ('140827', '垣曲县', '3');
INSERT INTO `t_nation` VALUES ('654022', '察布查尔锡伯自治县', '3');
INSERT INTO `t_nation` VALUES ('410883', '孟州市', '3');
INSERT INTO `t_nation` VALUES ('222405', '龙井市', '3');
INSERT INTO `t_nation` VALUES ('440701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('421003', '荆州区', '3');
INSERT INTO `t_nation` VALUES ('652923', '库车县', '3');
INSERT INTO `t_nation` VALUES ('371328', '蒙阴县', '3');
INSERT INTO `t_nation` VALUES ('320903', '盐都区', '3');
INSERT INTO `t_nation` VALUES ('420111', '洪山区', '3');
INSERT INTO `t_nation` VALUES ('530427', '新平彝族傣族自治县', '3');
INSERT INTO `t_nation` VALUES ('110114', '昌平区', '3');
INSERT INTO `t_nation` VALUES ('510727', '平武县', '3');
INSERT INTO `t_nation` VALUES ('210701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('230382', '密山市', '3');
INSERT INTO `t_nation` VALUES ('341701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('500108', '南岸区', '3');
INSERT INTO `t_nation` VALUES ('230221', '龙江县', '3');
INSERT INTO `t_nation` VALUES ('421223', '崇阳县', '3');
INSERT INTO `t_nation` VALUES ('150429', '宁城县', '3');
INSERT INTO `t_nation` VALUES ('431026', '汝城县', '3');
INSERT INTO `t_nation` VALUES ('420325', '房县', '3');
INSERT INTO `t_nation` VALUES ('530921', '凤庆县', '3');
INSERT INTO `t_nation` VALUES ('530522', '腾冲县', '3');
INSERT INTO `t_nation` VALUES ('511123', '犍为县', '3');
INSERT INTO `t_nation` VALUES ('360200', '景德镇市', '2');
INSERT INTO `t_nation` VALUES ('361124', '铅山县', '3');
INSERT INTO `t_nation` VALUES ('320701', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320585', '太仓市', '3');
INSERT INTO `t_nation` VALUES ('320321', '丰县', '3');
INSERT INTO `t_nation` VALUES ('141182', '汾阳市', '3');
INSERT INTO `t_nation` VALUES ('610301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320923', '阜宁县', '3');
INSERT INTO `t_nation` VALUES ('350205', '海沧区', '3');
INSERT INTO `t_nation` VALUES ('510105', '青羊区', '3');
INSERT INTO `t_nation` VALUES ('621222', '文县', '3');
INSERT INTO `t_nation` VALUES ('4409111', '东莞', '3');
INSERT INTO `t_nation` VALUES ('440705', '新会区', '3');
INSERT INTO `t_nation` VALUES ('330106', '西湖区', '3');
INSERT INTO `t_nation` VALUES ('340404', '谢家集区', '3');
INSERT INTO `t_nation` VALUES ('510904', '安居区', '3');
INSERT INTO `t_nation` VALUES ('421300', '随州市', '2');
INSERT INTO `t_nation` VALUES ('420105', '汉阳区', '3');
INSERT INTO `t_nation` VALUES ('130525', '隆尧县', '3');
INSERT INTO `t_nation` VALUES ('421181', '麻城市', '3');
INSERT INTO `t_nation` VALUES ('360700', '赣州市', '2');
INSERT INTO `t_nation` VALUES ('511402', '东坡区', '3');
INSERT INTO `t_nation` VALUES ('220501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360681', '贵溪市', '3');
INSERT INTO `t_nation` VALUES ('410423', '鲁山县', '3');
INSERT INTO `t_nation` VALUES ('451224', '东兰县', '3');
INSERT INTO `t_nation` VALUES ('451424', '大新县', '3');
INSERT INTO `t_nation` VALUES ('230722', '嘉荫县', '3');
INSERT INTO `t_nation` VALUES ('152523', '苏尼特左旗', '3');
INSERT INTO `t_nation` VALUES ('530402', '红塔区', '3');
INSERT INTO `t_nation` VALUES ('420301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('141002', '尧都区', '3');
INSERT INTO `t_nation` VALUES ('611021', '洛南县', '3');
INSERT INTO `t_nation` VALUES ('370300', '淄博市', '2');
INSERT INTO `t_nation` VALUES ('230705', '西林区', '3');
INSERT INTO `t_nation` VALUES ('610630', '宜川县', '3');
INSERT INTO `t_nation` VALUES ('500240', '石柱土家族自治县', '3');
INSERT INTO `t_nation` VALUES ('530926', '耿马傣族佤族自治县', '3');
INSERT INTO `t_nation` VALUES ('411202', '湖滨区', '3');
INSERT INTO `t_nation` VALUES ('500224', '铜梁县', '3');
INSERT INTO `t_nation` VALUES ('632821', '乌兰县', '3');
INSERT INTO `t_nation` VALUES ('530724', '宁蒗彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('371423', '庆云县', '3');
INSERT INTO `t_nation` VALUES ('340502', '金家庄区', '3');
INSERT INTO `t_nation` VALUES ('130424', '成安县', '3');
INSERT INTO `t_nation` VALUES ('140211', '南郊区', '3');
INSERT INTO `t_nation` VALUES ('654323', '福海县', '3');
INSERT INTO `t_nation` VALUES ('330101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('110104', '宣武区', '3');
INSERT INTO `t_nation` VALUES ('623021', '临潭县', '3');
INSERT INTO `t_nation` VALUES ('441202', '端州区', '3');
INSERT INTO `t_nation` VALUES ('610400', '咸阳市', '2');
INSERT INTO `t_nation` VALUES ('350429', '泰宁县', '3');
INSERT INTO `t_nation` VALUES ('440785', '恩平市', '3');
INSERT INTO `t_nation` VALUES ('620700', '张掖市', '2');
INSERT INTO `t_nation` VALUES ('410000', '河南省', '1');
INSERT INTO `t_nation` VALUES ('350402', '梅列区', '3');
INSERT INTO `t_nation` VALUES ('330881', '江山市', '3');
INSERT INTO `t_nation` VALUES ('532931', '剑川县', '3');
INSERT INTO `t_nation` VALUES ('411101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530521', '施甸县', '3');
INSERT INTO `t_nation` VALUES ('431081', '资兴市', '3');
INSERT INTO `t_nation` VALUES ('130824', '滦平县', '3');
INSERT INTO `t_nation` VALUES ('360734', '寻乌县', '3');
INSERT INTO `t_nation` VALUES ('371203', '钢城区', '3');
INSERT INTO `t_nation` VALUES ('621227', '徽县', '3');
INSERT INTO `t_nation` VALUES ('440825', '徐闻县', '3');
INSERT INTO `t_nation` VALUES ('350722', '浦城县', '3');
INSERT INTO `t_nation` VALUES ('500117', '合川区', '3');
INSERT INTO `t_nation` VALUES ('610302', '渭滨区', '3');
INSERT INTO `t_nation` VALUES ('410902', '华龙区', '3');
INSERT INTO `t_nation` VALUES ('371102', '东港区', '3');
INSERT INTO `t_nation` VALUES ('410326', '汝阳县', '3');
INSERT INTO `t_nation` VALUES ('420982', '安陆市', '3');
INSERT INTO `t_nation` VALUES ('610828', '佳县', '3');
INSERT INTO `t_nation` VALUES ('370982', '新泰市', '3');
INSERT INTO `t_nation` VALUES ('450405', '长洲区', '3');
INSERT INTO `t_nation` VALUES ('511823', '汉源县', '3');
INSERT INTO `t_nation` VALUES ('210203', '西岗区', '3');
INSERT INTO `t_nation` VALUES ('131100', '衡水市', '2');
INSERT INTO `t_nation` VALUES ('653130', '巴楚县', '3');
INSERT INTO `t_nation` VALUES ('411481', '永城市', '3');
INSERT INTO `t_nation` VALUES ('530802', '思茅区', '3');
INSERT INTO `t_nation` VALUES ('130535', '临西县', '3');
INSERT INTO `t_nation` VALUES ('230903', '桃山区', '3');
INSERT INTO `t_nation` VALUES ('610428', '长武县', '3');
INSERT INTO `t_nation` VALUES ('511922', '南江县', '3');
INSERT INTO `t_nation` VALUES ('360726', '安远县', '3');
INSERT INTO `t_nation` VALUES ('450302', '秀峰区', '3');
INSERT INTO `t_nation` VALUES ('654300', '阿勒泰地区', '2');
INSERT INTO `t_nation` VALUES ('450202', '城中区', '3');
INSERT INTO `t_nation` VALUES ('513233', '红原县', '3');
INSERT INTO `t_nation` VALUES ('542425', '安多县', '3');
INSERT INTO `t_nation` VALUES ('450923', '博白县', '3');
INSERT INTO `t_nation` VALUES ('150929', '四子王旗', '3');
INSERT INTO `t_nation` VALUES ('130721', '宣化县', '3');
INSERT INTO `t_nation` VALUES ('500242', '酉阳土家族苗族自治县', '3');
INSERT INTO `t_nation` VALUES ('410611', '淇滨区', '3');
INSERT INTO `t_nation` VALUES ('450681', '东兴市', '3');
INSERT INTO `t_nation` VALUES ('370704', '坊子区', '3');
INSERT INTO `t_nation` VALUES ('341301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220600', '白山市', '2');
INSERT INTO `t_nation` VALUES ('511112', '五通桥区', '3');
INSERT INTO `t_nation` VALUES ('211282', '开原市', '3');
INSERT INTO `t_nation` VALUES ('130629', '容城县', '3');
INSERT INTO `t_nation` VALUES ('150425', '克什克腾旗', '3');
INSERT INTO `t_nation` VALUES ('430523', '邵阳县', '3');
INSERT INTO `t_nation` VALUES ('420504', '点军区', '3');
INSERT INTO `t_nation` VALUES ('220202', '昌邑区', '3');
INSERT INTO `t_nation` VALUES ('542522', '札达县', '3');
INSERT INTO `t_nation` VALUES ('450125', '上林县', '3');
INSERT INTO `t_nation` VALUES ('652801', '库尔勒市', '3');
INSERT INTO `t_nation` VALUES ('360902', '袁州区', '3');
INSERT INTO `t_nation` VALUES ('150430', '敖汉旗', '3');
INSERT INTO `t_nation` VALUES ('654026', '昭苏县', '3');
INSERT INTO `t_nation` VALUES ('350526', '德化县', '3');
INSERT INTO `t_nation` VALUES ('230505', '四方台区', '3');
INSERT INTO `t_nation` VALUES ('420321', '郧县', '3');
INSERT INTO `t_nation` VALUES ('211321', '朝阳县', '3');
INSERT INTO `t_nation` VALUES ('533123', '盈江县', '3');
INSERT INTO `t_nation` VALUES ('330226', '宁海县', '3');
INSERT INTO `t_nation` VALUES ('150522', '科尔沁左翼后旗', '3');
INSERT INTO `t_nation` VALUES ('360122', '新建县', '3');
INSERT INTO `t_nation` VALUES ('141124', '临县', '3');
INSERT INTO `t_nation` VALUES ('341424', '和县', '3');
INSERT INTO `t_nation` VALUES ('430682', '临湘市', '3');
INSERT INTO `t_nation` VALUES ('360100', '南昌市', '2');
INSERT INTO `t_nation` VALUES ('430900', '益阳市', '2');
INSERT INTO `t_nation` VALUES ('430111', '雨花区', '3');
INSERT INTO `t_nation` VALUES ('510422', '盐边县', '3');
INSERT INTO `t_nation` VALUES ('211021', '辽阳县', '3');
INSERT INTO `t_nation` VALUES ('411002', '魏都区', '3');
INSERT INTO `t_nation` VALUES ('445101', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('231223', '青冈县', '3');
INSERT INTO `t_nation` VALUES ('513437', '雷波县', '3');
INSERT INTO `t_nation` VALUES ('621022', '环县', '3');
INSERT INTO `t_nation` VALUES ('623027', '夏河县', '3');
INSERT INTO `t_nation` VALUES ('150426', '翁牛特旗', '3');
INSERT INTO `t_nation` VALUES ('450203', '鱼峰区', '3');
INSERT INTO `t_nation` VALUES ('420923', '云梦县', '3');
INSERT INTO `t_nation` VALUES ('512021', '安岳县', '3');
INSERT INTO `t_nation` VALUES ('360730', '宁都县', '3');
INSERT INTO `t_nation` VALUES ('420700', '鄂州市', '2');
INSERT INTO `t_nation` VALUES ('433101', '吉首市', '3');
INSERT INTO `t_nation` VALUES ('371724', '巨野县', '3');
INSERT INTO `t_nation` VALUES ('511681', '华蓥市', '3');
INSERT INTO `t_nation` VALUES ('130503', '桥西区', '3');
INSERT INTO `t_nation` VALUES ('500110', '万盛区', '3');
INSERT INTO `t_nation` VALUES ('411303', '卧龙区', '3');
INSERT INTO `t_nation` VALUES ('341723', '青阳县', '3');
INSERT INTO `t_nation` VALUES ('460105', '秀英区', '3');
INSERT INTO `t_nation` VALUES ('540100', '拉萨市', '2');
INSERT INTO `t_nation` VALUES ('150702', '海拉尔区', '3');
INSERT INTO `t_nation` VALUES ('621223', '宕昌县', '3');
INSERT INTO `t_nation` VALUES ('211002', '白塔区', '3');
INSERT INTO `t_nation` VALUES ('150624', '鄂托克旗', '3');
INSERT INTO `t_nation` VALUES ('360103', '西湖区', '3');
INSERT INTO `t_nation` VALUES ('360201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('210902', '海州区', '3');
INSERT INTO `t_nation` VALUES ('130431', '鸡泽县', '3');
INSERT INTO `t_nation` VALUES ('350822', '永定县', '3');
INSERT INTO `t_nation` VALUES ('530428', '元江哈尼族彝族傣族自治县', '3');
INSERT INTO `t_nation` VALUES ('511111', '沙湾区', '3');
INSERT INTO `t_nation` VALUES ('610328', '千阳县', '3');
INSERT INTO `t_nation` VALUES ('370501', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530112', '西山区', '3');
INSERT INTO `t_nation` VALUES ('360731', '于都县', '3');
INSERT INTO `t_nation` VALUES ('542122', '江达县', '3');
INSERT INTO `t_nation` VALUES ('211100', '盘锦市', '2');
INSERT INTO `t_nation` VALUES ('150981', '丰镇市', '3');
INSERT INTO `t_nation` VALUES ('331082', '临海市', '3');
INSERT INTO `t_nation` VALUES ('522630', '台江县', '3');
INSERT INTO `t_nation` VALUES ('210800', '营口市', '2');
INSERT INTO `t_nation` VALUES ('210903', '新邱区', '3');
INSERT INTO `t_nation` VALUES ('141130', '交口县', '3');
INSERT INTO `t_nation` VALUES ('652825', '且末县', '3');
INSERT INTO `t_nation` VALUES ('232702', '松岭区', '3');
INSERT INTO `t_nation` VALUES ('620400', '白银市', '2');
INSERT INTO `t_nation` VALUES ('211303', '龙城区', '3');
INSERT INTO `t_nation` VALUES ('530902', '临翔区', '3');
INSERT INTO `t_nation` VALUES ('532800', '西双版纳傣族自治州', '2');
INSERT INTO `t_nation` VALUES ('431201', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('150627', '伊金霍洛旗', '3');
INSERT INTO `t_nation` VALUES ('410404', '石龙区', '3');
INSERT INTO `t_nation` VALUES ('410325', '嵩县', '3');
INSERT INTO `t_nation` VALUES ('360923', '上高县', '3');
INSERT INTO `t_nation` VALUES ('120112', '津南区', '3');
INSERT INTO `t_nation` VALUES ('230125', '宾县', '3');
INSERT INTO `t_nation` VALUES ('430921', '南县', '3');
INSERT INTO `t_nation` VALUES ('150802', '临河区', '3');
INSERT INTO `t_nation` VALUES ('340824', '潜山县', '3');
INSERT INTO `t_nation` VALUES ('410721', '新乡县', '3');
INSERT INTO `t_nation` VALUES ('620982', '敦煌市', '3');
INSERT INTO `t_nation` VALUES ('371002', '环翠区', '3');
INSERT INTO `t_nation` VALUES ('350525', '永春县', '3');
INSERT INTO `t_nation` VALUES ('370801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('360123', '安义县', '3');
INSERT INTO `t_nation` VALUES ('150924', '兴和县', '3');
INSERT INTO `t_nation` VALUES ('130184', '新乐市', '3');
INSERT INTO `t_nation` VALUES ('532822', '勐海县', '3');
INSERT INTO `t_nation` VALUES ('220400', '辽源市', '2');
INSERT INTO `t_nation` VALUES ('350702', '延平区', '3');
INSERT INTO `t_nation` VALUES ('130706', '下花园区', '3');
INSERT INTO `t_nation` VALUES ('130400', '邯郸市', '2');
INSERT INTO `t_nation` VALUES ('131026', '文安县', '3');
INSERT INTO `t_nation` VALUES ('542600', '林芝地区', '2');
INSERT INTO `t_nation` VALUES ('411327', '社旗县', '3');
INSERT INTO `t_nation` VALUES ('210114', '于洪区', '3');
INSERT INTO `t_nation` VALUES ('542333', '仲巴县', '3');
INSERT INTO `t_nation` VALUES ('450801', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('220102', '南关区', '3');
INSERT INTO `t_nation` VALUES ('231222', '兰西县', '3');
INSERT INTO `t_nation` VALUES ('632622', '班玛县', '3');
INSERT INTO `t_nation` VALUES ('140430', '沁县', '3');
INSERT INTO `t_nation` VALUES ('230602', '萨尔图区', '3');
INSERT INTO `t_nation` VALUES ('451300', '来宾市', '2');
INSERT INTO `t_nation` VALUES ('370400', '枣庄市', '2');
INSERT INTO `t_nation` VALUES ('654202', '乌苏市', '3');
INSERT INTO `t_nation` VALUES ('230183', '尚志市', '3');
INSERT INTO `t_nation` VALUES ('130927', '南皮县', '3');
INSERT INTO `t_nation` VALUES ('371600', '滨州市', '2');
INSERT INTO `t_nation` VALUES ('210122', '辽中县', '3');
INSERT INTO `t_nation` VALUES ('410304', '廛河回族区', '3');
INSERT INTO `t_nation` VALUES ('131081', '霸州市', '3');
INSERT INTO `t_nation` VALUES ('450100', '南宁市', '2');
INSERT INTO `t_nation` VALUES ('440281', '乐昌市', '3');
INSERT INTO `t_nation` VALUES ('451028', '乐业县', '3');
INSERT INTO `t_nation` VALUES ('610626', '吴起县', '3');
INSERT INTO `t_nation` VALUES ('622926', '东乡族自治县', '3');
INSERT INTO `t_nation` VALUES ('231124', '孙吴县', '3');
INSERT INTO `t_nation` VALUES ('441823', '阳山县', '3');
INSERT INTO `t_nation` VALUES ('410725', '原阳县', '3');
INSERT INTO `t_nation` VALUES ('451381', '合山市', '3');
INSERT INTO `t_nation` VALUES ('632725', '囊谦县', '3');
INSERT INTO `t_nation` VALUES ('231000', '牡丹江市', '2');
INSERT INTO `t_nation` VALUES ('370523', '广饶县', '3');
INSERT INTO `t_nation` VALUES ('360429', '湖口县', '3');
INSERT INTO `t_nation` VALUES ('620302', '金川区', '3');
INSERT INTO `t_nation` VALUES ('540121', '林周县', '3');
INSERT INTO `t_nation` VALUES ('330681', '诸暨市', '3');
INSERT INTO `t_nation` VALUES ('610723', '洋县', '3');
INSERT INTO `t_nation` VALUES ('411729', '新蔡县', '3');
INSERT INTO `t_nation` VALUES ('210804', '鲅鱼圈区', '3');
INSERT INTO `t_nation` VALUES ('522230', '万山特区', '3');
INSERT INTO `t_nation` VALUES ('150404', '松山区', '3');
INSERT INTO `t_nation` VALUES ('530102', '五华区', '3');
INSERT INTO `t_nation` VALUES ('430528', '新宁县', '3');
INSERT INTO `t_nation` VALUES ('140421', '长治县', '3');
INSERT INTO `t_nation` VALUES ('330601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('530722', '永胜县', '3');
INSERT INTO `t_nation` VALUES ('360400', '九江市', '2');
INSERT INTO `t_nation` VALUES ('141122', '交城县', '3');
INSERT INTO `t_nation` VALUES ('130981', '泊头市', '3');
INSERT INTO `t_nation` VALUES ('120106', '红桥区', '3');
INSERT INTO `t_nation` VALUES ('420704', '鄂城区', '3');
INSERT INTO `t_nation` VALUES ('340401', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('130604', '南市区', '3');
INSERT INTO `t_nation` VALUES ('410411', '湛河区', '3');
INSERT INTO `t_nation` VALUES ('445322', '郁南县', '3');
INSERT INTO `t_nation` VALUES ('152525', '东乌珠穆沁旗', '3');
INSERT INTO `t_nation` VALUES ('542322', '南木林县', '3');
INSERT INTO `t_nation` VALUES ('340323', '固镇县', '3');
INSERT INTO `t_nation` VALUES ('150304', '乌达区', '3');
INSERT INTO `t_nation` VALUES ('371601', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('320204', '北塘区', '3');
INSERT INTO `t_nation` VALUES ('411281', '义马市', '3');
INSERT INTO `t_nation` VALUES ('630100', '西宁市', '2');
INSERT INTO `t_nation` VALUES ('220283', '舒兰市', '3');
INSERT INTO `t_nation` VALUES ('330104', '江干区', '3');
INSERT INTO `t_nation` VALUES ('341525', '霍山县', '3');
INSERT INTO `t_nation` VALUES ('370781', '青州市', '3');
INSERT INTO `t_nation` VALUES ('341022', '休宁县', '3');
INSERT INTO `t_nation` VALUES ('140311', '郊区', '3');
INSERT INTO `t_nation` VALUES ('652303', '米泉市', '3');
INSERT INTO `t_nation` VALUES ('130622', '清苑县', '3');
INSERT INTO `t_nation` VALUES ('469005', '文昌市', '3');
INSERT INTO `t_nation` VALUES ('371723', '成武县', '3');
INSERT INTO `t_nation` VALUES ('511002', '市中区', '3');
INSERT INTO `t_nation` VALUES ('469037', '西沙群岛', '3');
INSERT INTO `t_nation` VALUES ('220802', '洮北区', '3');
INSERT INTO `t_nation` VALUES ('520330', '习水县', '3');
INSERT INTO `t_nation` VALUES ('512002', '雁江区', '3');
INSERT INTO `t_nation` VALUES ('220881', '洮南市', '3');
INSERT INTO `t_nation` VALUES ('420804', '掇刀区', '3');
INSERT INTO `t_nation` VALUES ('330206', '北仑区', '3');
INSERT INTO `t_nation` VALUES ('330110', '余杭区', '3');
INSERT INTO `t_nation` VALUES ('513325', '雅江县', '3');
INSERT INTO `t_nation` VALUES ('632200', '海北藏族自治州', '2');
INSERT INTO `t_nation` VALUES ('440184', '从化市', '3');
INSERT INTO `t_nation` VALUES ('450123', '隆安县', '3');
INSERT INTO `t_nation` VALUES ('420582', '当阳市', '3');
INSERT INTO `t_nation` VALUES ('410726', '延津县', '3');
INSERT INTO `t_nation` VALUES ('140900', '忻州市', '2');
INSERT INTO `t_nation` VALUES ('640423', '隆德县', '3');
INSERT INTO `t_nation` VALUES ('310106', '静安区', '3');
INSERT INTO `t_nation` VALUES ('513332', '石渠县', '3');
INSERT INTO `t_nation` VALUES ('511028', '隆昌县', '3');
INSERT INTO `t_nation` VALUES ('310104', '徐汇区', '3');
INSERT INTO `t_nation` VALUES ('522732', '三都水族自治县', '3');
INSERT INTO `t_nation` VALUES ('130638', '雄县', '3');
INSERT INTO `t_nation` VALUES ('542430', '尼玛县', '3');
INSERT INTO `t_nation` VALUES ('330421', '嘉善县', '3');
INSERT INTO `t_nation` VALUES ('330782', '义乌市', '3');
INSERT INTO `t_nation` VALUES ('510302', '自流井区', '3');
INSERT INTO `t_nation` VALUES ('513225', '九寨沟县', '3');
INSERT INTO `t_nation` VALUES ('360426', '德安县', '3');
INSERT INTO `t_nation` VALUES ('150781', '满洲里市', '3');
INSERT INTO `t_nation` VALUES ('632624', '达日县', '3');
INSERT INTO `t_nation` VALUES ('530824', '景谷傣族彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('411326', '淅川县', '3');
INSERT INTO `t_nation` VALUES ('411723', '平舆县', '3');
INSERT INTO `t_nation` VALUES ('130731', '涿鹿县', '3');
INSERT INTO `t_nation` VALUES ('130427', '磁县', '3');
INSERT INTO `t_nation` VALUES ('140203', '矿区', '3');
INSERT INTO `t_nation` VALUES ('420583', '枝江市', '3');
INSERT INTO `t_nation` VALUES ('513401', '西昌市', '3');
INSERT INTO `t_nation` VALUES ('610524', '合阳县', '3');
INSERT INTO `t_nation` VALUES ('411524', '商城县', '3');
INSERT INTO `t_nation` VALUES ('420684', '宜城市', '3');
INSERT INTO `t_nation` VALUES ('230904', '茄子河区', '3');
INSERT INTO `t_nation` VALUES ('232723', '漠河县', '3');
INSERT INTO `t_nation` VALUES ('450330', '平乐县', '3');
INSERT INTO `t_nation` VALUES ('371325', '费县', '3');
INSERT INTO `t_nation` VALUES ('110111', '房山区', '3');
INSERT INTO `t_nation` VALUES ('320684', '海门市', '3');
INSERT INTO `t_nation` VALUES ('110000', '北京市', '1');
INSERT INTO `t_nation` VALUES ('632524', '兴海县', '3');
INSERT INTO `t_nation` VALUES ('630121', '大通回族土族自治县', '3');
INSERT INTO `t_nation` VALUES ('410823', '武陟县', '3');
INSERT INTO `t_nation` VALUES ('141022', '翼城县', '3');
INSERT INTO `t_nation` VALUES ('510403', '西区', '3');
INSERT INTO `t_nation` VALUES ('640381', '青铜峡市', '3');
INSERT INTO `t_nation` VALUES ('411023', '许昌县', '3');
INSERT INTO `t_nation` VALUES ('131023', '永清县', '3');
INSERT INTO `t_nation` VALUES ('360781', '瑞金市', '3');
INSERT INTO `t_nation` VALUES ('231200', '绥化市', '2');
INSERT INTO `t_nation` VALUES ('640221', '平罗县', '3');
INSERT INTO `t_nation` VALUES ('410422', '叶县', '3');
INSERT INTO `t_nation` VALUES ('210213', '金州区', '3');
INSERT INTO `t_nation` VALUES ('513327', '炉霍县', '3');
INSERT INTO `t_nation` VALUES ('141034', '汾西县', '3');
INSERT INTO `t_nation` VALUES ('511322', '营山县', '3');
INSERT INTO `t_nation` VALUES ('411325', '内乡县', '3');
INSERT INTO `t_nation` VALUES ('410224', '开封县', '3');
INSERT INTO `t_nation` VALUES ('152900', '阿拉善盟', '2');
INSERT INTO `t_nation` VALUES ('510603', '旌阳区', '3');
INSERT INTO `t_nation` VALUES ('350426', '尤溪县', '3');
INSERT INTO `t_nation` VALUES ('653000', '克孜勒苏柯尔克孜自治州', '2');
INSERT INTO `t_nation` VALUES ('341622', '蒙城县', '3');
INSERT INTO `t_nation` VALUES ('511129', '沐川县', '3');
INSERT INTO `t_nation` VALUES ('411422', '睢县', '3');
INSERT INTO `t_nation` VALUES ('421081', '石首市', '3');
INSERT INTO `t_nation` VALUES ('520324', '正安县', '3');
INSERT INTO `t_nation` VALUES ('450621', '上思县', '3');
INSERT INTO `t_nation` VALUES ('450222', '柳城县', '3');
INSERT INTO `t_nation` VALUES ('130103', '桥东区', '3');
INSERT INTO `t_nation` VALUES ('230205', '昂昂溪区', '3');
INSERT INTO `t_nation` VALUES ('513330', '德格县', '3');
INSERT INTO `t_nation` VALUES ('632726', '曲麻莱县', '3');
INSERT INTO `t_nation` VALUES ('542337', '萨嘎县', '3');
INSERT INTO `t_nation` VALUES ('522632', '榕江县', '3');
INSERT INTO `t_nation` VALUES ('410621', '浚县', '3');
INSERT INTO `t_nation` VALUES ('511124', '井研县', '3');
INSERT INTO `t_nation` VALUES ('130202', '路南区', '3');
INSERT INTO `t_nation` VALUES ('510521', '泸县', '3');
INSERT INTO `t_nation` VALUES ('331124', '松阳县', '3');
INSERT INTO `t_nation` VALUES ('321322', '沭阳县', '3');
INSERT INTO `t_nation` VALUES ('430000', '湖南省', '1');
INSERT INTO `t_nation` VALUES ('512001', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('620923', '肃北蒙古族自治县', '3');
INSERT INTO `t_nation` VALUES ('640301', '市辖区', '3');
INSERT INTO `t_nation` VALUES ('410211', '金明区', '3');
INSERT INTO `t_nation` VALUES ('441322', '博罗县', '3');
INSERT INTO `t_nation` VALUES ('371425', '齐河县', '3');
INSERT INTO `t_nation` VALUES ('310116', '金山区', '3');
INSERT INTO `t_nation` VALUES ('120110', '东丽区', '3');
INSERT INTO `t_nation` VALUES ('230881', '同江市', '3');
INSERT INTO `t_nation` VALUES ('530627', '镇雄县', '3');
INSERT INTO `t_nation` VALUES ('611024', '山阳县', '3');
INSERT INTO `t_nation` VALUES ('130128', '深泽县', '3');
INSERT INTO `t_nation` VALUES ('350430', '建宁县', '3');
INSERT INTO `t_nation` VALUES ('510121', '金堂县', '3');
INSERT INTO `t_nation` VALUES ('431228', '芷江侗族自治县', '3');
INSERT INTO `t_nation` VALUES ('620800', '平凉市', '2');
INSERT INTO `t_nation` VALUES ('410881', '济源市', '3');
INSERT INTO `t_nation` VALUES ('653123', '英吉沙县', '3');
INSERT INTO `t_nation` VALUES ('530324', '罗平县', '3');
INSERT INTO `t_nation` VALUES ('530823', '景东彝族自治县', '3');
INSERT INTO `t_nation` VALUES ('360000', '江西省', '1');
INSERT INTO `t_nation` VALUES ('620602', '凉州区', '3');
INSERT INTO `t_nation` VALUES ('410622', '淇县', '3');
INSERT INTO `t_nation` VALUES ('370213', '李沧区', '3');
INSERT INTO `t_nation` VALUES ('630105', '城北区', '3');
INSERT INTO `t_nation` VALUES ('230711', '乌马河区', '3');
INSERT INTO `t_nation` VALUES ('450311', '雁山区', '3');
INSERT INTO `t_nation` VALUES ('110113', '顺义区', '3');
INSERT INTO `t_nation` VALUES ('441481', '兴宁市', '3');
INSERT INTO `t_nation` VALUES ('450303', '叠彩区', '3');
INSERT INTO `t_nation` VALUES ('210000', '辽宁省', '1');
INSERT INTO `t_nation` VALUES ('360600', '鹰潭市', '2');
INSERT INTO `t_nation` VALUES ('532527', '泸西县', '3');

-- push record

CREATE TABLE t_push_record(
f_id varchar(32) primary key,
f_type varchar(100) default '',
f_target_type varchar(100) default '',
f_member_id varchar(32) default '',
f_shop_id varchar(32) default '',
f_title varchar(512) default '',
f_content varchar(1000) default '',
f_target_id varchar(32) default '',
f_create_time bigint default 0
);

alter table t_message_record add f_type varchar(10);

alter table t_product_comment add f_images varchar(1000);

alter table t_shop_flow add f_before_amount varchar(20);


CREATE TABLE t_agent_flow(
f_id varchar(32) primary key,
f_agent_id varchar(32) default '',
f_agent_account varchar(50) default '',
f_type varchar(10) default '',
f_before_amount varchar(20) default '',
f_amount varchar(20) default '',
f_after_amount varchar(20) default '',
f_create_time bigint default 0
);

