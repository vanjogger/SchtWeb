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
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_admin
-- ----------------------------
INSERT INTO `base_admin` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', '1', '1479608989592', '127.0.0.1', '204', 'NORMAL', '系统管理员', '', '1');

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
INSERT INTO `base_log` VALUES ('53262fa09a3342689904a03e6cd4f369', 'admin', '��¼', '1479608989603', '127.0.0.1');

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
INSERT INTO `base_permission` VALUES ('257dcc9a77d645ad90d9b5e66f1b1875', '管理员管理', 'admin:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('8d7bec9ecdf4443ca177c1c1f6c78e24', '系统设置', 'system', '0');
INSERT INTO `base_permission` VALUES ('b86abcdac5264171967bda3d6b759647', '角色管理', 'role:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('dde9c27e65594fe6b07bac11817db850', '日志管理', 'log:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_permission` VALUES ('edb3c278b7794ec5b3bc29776c0b9207', '权限管理', 'permission:list', '8d7bec9ecdf4443ca177c1c1f6c78e24');

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
INSERT INTO `base_role_permission` VALUES ('164ab36818ae48de8235160af9780b1e', '1', '2c7490e50c5f4188bc4809244e2847ae');
INSERT INTO `base_role_permission` VALUES ('1deb5e9ffb664c8fbed37bbfb6a3bb77', '1', '80485bf1cadb4581974a78d4fb8c9d41');
INSERT INTO `base_role_permission` VALUES ('31bc85c5eed94cf886d0d67c1d2f7dee', '1', '3ea5122260f64e6ab054cac728e4be93');
INSERT INTO `base_role_permission` VALUES ('5b5df0cad2984c2098edd40069c6bf88', '1', '9f16688397084d90b67820be21bcfb83');
INSERT INTO `base_role_permission` VALUES ('8a8bfbb7676b4b898a3584b7d9792441', '1', '2493b7205805486f9da01c13db0831dc');
INSERT INTO `base_role_permission` VALUES ('a28d79599ba44536bad5e4c74877a350', '1', 'edb3c278b7794ec5b3bc29776c0b9207');
INSERT INTO `base_role_permission` VALUES ('c487ac6e25a248bd9f18103bb18aeed3', '1', 'dde9c27e65594fe6b07bac11817db850');
INSERT INTO `base_role_permission` VALUES ('e301138e25424120b99add4742d1bab3', '1', '8d7bec9ecdf4443ca177c1c1f6c78e24');
INSERT INTO `base_role_permission` VALUES ('e50d0de96a414d5b9be88d66ce53b9fc', '1', '12b366ac85274c28b8c10364d2856b17');
INSERT INTO `base_role_permission` VALUES ('e7a330361abe4ff189fe7478363e5b99', '1', 'b86abcdac5264171967bda3d6b759647');
INSERT INTO `base_role_permission` VALUES ('ec4ef7b7784542da8d7db4830c3d278e', '1', '257dcc9a77d645ad90d9b5e66f1b1875');
INSERT INTO `base_role_permission` VALUES ('f48756ffaff9410f9f9f7a319af5ab01', '1', 'b0f43c76f9274cc19ee3379de4f9ce4a');
INSERT INTO `base_role_permission` VALUES ('f73d7468057141e288a39f5e3b9a2051', '1', 'ef52c0639d594122b0278e5096da5104');
INSERT INTO `base_role_permission` VALUES ('fbae75f2e8b14218b345c3c6ff738979', '1', '1dab92c627d245dd91522f024c7e60ac');
