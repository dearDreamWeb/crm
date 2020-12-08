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

 Date: 09/12/2020 00:06:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp_dept
-- ----------------------------
DROP TABLE IF EXISTS `emp_dept`;
CREATE TABLE `emp_dept`  (
  `emp_id` int(0) NULL DEFAULT NULL,
  `dept_id` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_dept
-- ----------------------------
INSERT INTO `emp_dept` VALUES (1, 1);
INSERT INTO `emp_dept` VALUES (2, 7);
INSERT INTO `emp_dept` VALUES (21, 2);
INSERT INTO `emp_dept` VALUES (17, 2);
INSERT INTO `emp_dept` VALUES (19, 2);
INSERT INTO `emp_dept` VALUES (20, 2);
INSERT INTO `emp_dept` VALUES (18, 2);
INSERT INTO `emp_dept` VALUES (3, 1);
INSERT INTO `emp_dept` VALUES (4, 1);
INSERT INTO `emp_dept` VALUES (23, 3);

SET FOREIGN_KEY_CHECKS = 1;
