/*
 Navicat Premium Data Transfer

 Source Server         : 1807项目
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 101.201.233.233:3306
 Source Schema         : gradua_project

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 09/12/2020 00:06:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `dept_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `dept_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门标识',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '董事长', 'admin', 1, '2020-09-27 16:31:19', '2020-09-27 16:31:22', 0);
INSERT INTO `dept` VALUES (2, '销售部', 'manager', 1, '2020-09-27 16:33:35', '2020-09-27 16:33:35', 0);
INSERT INTO `dept` VALUES (3, '财务部', 'common', 1, '2020-09-27 16:34:01', '2020-09-27 16:34:01', 0);
INSERT INTO `dept` VALUES (4, '测试部', 'testDept', 1, '2020-10-05 12:25:07', '2020-10-05 12:25:07', 1);
INSERT INTO `dept` VALUES (5, '暗部', 'anbudept', 2, '2020-10-05 16:53:41', '2020-10-05 08:53:43', 0);
INSERT INTO `dept` VALUES (6, 'aa', '12345', 1, '2020-10-12 20:32:14', '2020-10-12 20:32:14', 0);
INSERT INTO `dept` VALUES (7, '开发部', 'development', 1, '2020-10-12 20:58:57', '2020-10-12 20:58:57', 0);

SET FOREIGN_KEY_CHECKS = 1;
