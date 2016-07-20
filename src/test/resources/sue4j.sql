/*
Navicat MySQL Data Transfer

Source Server         : localhsot
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : sue4j

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-07-20 14:49:29
*/

/*
 * 下面的两行自己加的  用Navicat MySQL到处数据和结构的时候没有这两句
 */
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sue4j` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sue4j`;



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permesssion
-- ----------------------------
DROP TABLE IF EXISTS `permesssion`;
CREATE TABLE `permesssion` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(31) NOT NULL COMMENT '权限名称',
  `permission_sign` varchar(127) NOT NULL COMMENT '权限标识',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述，程序中判断使用，如"user:create"',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permesssion
-- ----------------------------
INSERT INTO `permesssion` VALUES ('1000', '用户新增', 'user:create', null);
INSERT INTO `permesssion` VALUES ('1001', '用户删除', 'user:delete', null);
INSERT INTO `permesssion` VALUES ('1002', '用户修改', 'user:update', null);
INSERT INTO `permesssion` VALUES ('1003', '用户查询', 'user:query', null);
INSERT INTO `permesssion` VALUES ('1004', '会员专属', 'vip', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(31) NOT NULL COMMENT '角色名',
  `role_sign` varchar(127) NOT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1000', 'admin', 'admin', '管理员');
INSERT INTO `role` VALUES ('1001', 'vip_user', 'vip_user', 'vip用户');
INSERT INTO `role` VALUES ('1002', 'normal_user', 'normal_user', '普通用户');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色与权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1000', '1000', '1000');
INSERT INTO `role_permission` VALUES ('1001', '1000', '1001');
INSERT INTO `role_permission` VALUES ('1002', '1000', '1002');
INSERT INTO `role_permission` VALUES ('1003', '1000', '1003');
INSERT INTO `role_permission` VALUES ('1004', '1001', '1004');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` char(64) NOT NULL COMMENT '密码',
  `state` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user  密码全是123456经过sha-256加密后的结果
-- ----------------------------
INSERT INTO `user` VALUES ('1000', 'sue', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '2016-07-17 12:59:08');
INSERT INTO `user` VALUES ('1001', 'vip', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '2016-07-20 12:29:57');
INSERT INTO `user` VALUES ('1002', 'normal', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '2016-07-18 12:30:20');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1000', '1000', '1000');
INSERT INTO `user_role` VALUES ('1001', '1000', '1001');
INSERT INTO `user_role` VALUES ('1002', '1001', '1001');
