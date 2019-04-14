/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.10@root
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 192.168.1.10:3306
 Source Schema         : spring-boot-shiro-demo

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 14/04/2019 10:24:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for operator_permission
-- ----------------------------
DROP TABLE IF EXISTS `operator_permission`;
CREATE TABLE `operator_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `permission_name` varchar(50) NOT NULL COMMENT '权限名称',
  `permission_key` varchar(50) NOT NULL DEFAULT '' COMMENT '权限键值',
  `permission_url` varchar(100) DEFAULT NULL COMMENT '权限URL',
  `parent_id` int(11) NOT NULL COMMENT '父节点ID',
  `sort_id` int(4) DEFAULT NULL COMMENT '排序',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_permission_key`(`permission_key`)
) AUTO_INCREMENT = 1;

-- ----------------------------
-- Records of operator_permission
-- ----------------------------
INSERT INTO `operator_permission` VALUES (10000, '系统管理', 'sysManage', NULL, 0, 1, '2018-11-06 12:08:03', '2018-11-06 12:08:03');
INSERT INTO `operator_permission` VALUES (10100, '用户管理', 'user:list', 'user/user_list.html', 10000, 1, '2018-11-19 15:18:30', '2018-11-19 15:18:30');
INSERT INTO `operator_permission` VALUES (10101, '添加用户', 'user:add', NULL, 10100, 1, '2018-11-06 12:08:03', '2018-11-06 12:08:03');
INSERT INTO `operator_permission` VALUES (10102, '编辑用户', 'user:edit', NULL, 10100, 1, '2018-11-06 12:08:03', '2018-11-06 12:08:03');
INSERT INTO `operator_permission` VALUES (10103, '禁用用户', 'user:disable', NULL, 10100, 1, '2018-11-06 12:08:03', '2018-11-06 12:08:03');
INSERT INTO `operator_permission` VALUES (10104, '启用用户', 'user:enable', NULL, 10100, 1, '2018-11-06 12:08:03', '2018-11-06 12:08:03');
INSERT INTO `operator_permission` VALUES (10105, '重置密码', 'user:resetPwd', NULL, 10100, 1, '2018-11-06 12:08:03', '2018-11-06 12:08:03');

-- ----------------------------
-- Table structure for operator_role
-- ----------------------------
DROP TABLE IF EXISTS `operator_role`;
CREATE TABLE `operator_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_key` varchar(50) NOT NULL DEFAULT '' COMMENT '角色键值',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_role_key`(`role_key`)
) AUTO_INCREMENT = 1;

-- ----------------------------
-- Records of operator_role
-- ----------------------------
INSERT INTO `operator_role` VALUES (1, '管理员', 'ADMIN', '2018-11-08 11:40:35', '2018-08-14 16:36:38');
INSERT INTO `operator_role` VALUES (4, '财务', 'FINANCE', '2018-11-08 11:19:03', '2018-11-08 11:19:03');
INSERT INTO `operator_role` VALUES (5, '运营', 'OPERATOR', '2018-11-08 11:19:36', '2018-11-08 11:19:36');
INSERT INTO `operator_role` VALUES (6, '开发', 'DEV', '2018-11-08 11:19:48', '2018-11-08 11:19:48');

-- ----------------------------
-- Table structure for operator_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `operator_role_permission`;
CREATE TABLE `operator_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `idx_role_id`(`role_id`)
) AUTO_INCREMENT = 1;

-- ----------------------------
-- Records of operator_role_permission
-- ----------------------------
INSERT INTO `operator_role_permission` VALUES (4760, 1, 10000, '2019-04-04 19:00:57');
INSERT INTO `operator_role_permission` VALUES (4761, 1, 10100, '2019-04-04 19:00:57');
INSERT INTO `operator_role_permission` VALUES (4762, 1, 10101, '2019-04-04 19:00:57');
INSERT INTO `operator_role_permission` VALUES (4763, 1, 10102, '2019-04-04 19:00:57');
INSERT INTO `operator_role_permission` VALUES (4764, 1, 10103, '2019-04-04 19:00:57');
INSERT INTO `operator_role_permission` VALUES (4765, 1, 10104, '2019-04-04 19:00:57');
INSERT INTO `operator_role_permission` VALUES (4766, 1, 10105, '2019-04-04 19:00:57');

-- ----------------------------
-- Table structure for operator_user
-- ----------------------------
DROP TABLE IF EXISTS `operator_user`;
CREATE TABLE `operator_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 (0--无效，1--有效,2--禁用)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_account`(`account`)
) AUTO_INCREMENT = 1;

-- ----------------------------
-- Records of operator_user
-- ----------------------------
INSERT INTO `operator_user` VALUES (1, 'admin', '管理员', 'a15edc8949be09ebba06a507f25f338a', 1, '2019-01-15 19:06:48', '2018-08-14 16:36:38');

-- ----------------------------
-- Table structure for operator_user_role
-- ----------------------------
DROP TABLE IF EXISTS `operator_user_role`;
CREATE TABLE `operator_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` int(11) NOT NULL COMMENT '管理员ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `idx_user_id`(`user_id`)
) AUTO_INCREMENT = 1;

-- ----------------------------
-- Records of operator_user_role
-- ----------------------------
INSERT INTO `operator_user_role` VALUES (1, 1, 1, '2018-08-14 16:36:38');

SET FOREIGN_KEY_CHECKS = 1;
