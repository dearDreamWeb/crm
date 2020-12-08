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

 Date: 09/12/2020 00:09:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept_menu
-- ----------------------------
DROP TABLE IF EXISTS `dept_menu`;
CREATE TABLE `dept_menu`  (
  `dept_id` bigint(0) NOT NULL,
  `menu_id` bigint(0) NOT NULL,
  PRIMARY KEY (`dept_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_menu
-- ----------------------------
INSERT INTO `dept_menu` VALUES (1, 1);
INSERT INTO `dept_menu` VALUES (1, 2);
INSERT INTO `dept_menu` VALUES (1, 3);
INSERT INTO `dept_menu` VALUES (1, 4);
INSERT INTO `dept_menu` VALUES (1, 5);
INSERT INTO `dept_menu` VALUES (1, 6);
INSERT INTO `dept_menu` VALUES (1, 7);
INSERT INTO `dept_menu` VALUES (1, 8);
INSERT INTO `dept_menu` VALUES (1, 9);
INSERT INTO `dept_menu` VALUES (1, 10);
INSERT INTO `dept_menu` VALUES (1, 11);
INSERT INTO `dept_menu` VALUES (1, 12);
INSERT INTO `dept_menu` VALUES (1, 13);
INSERT INTO `dept_menu` VALUES (1, 14);
INSERT INTO `dept_menu` VALUES (1, 15);
INSERT INTO `dept_menu` VALUES (1, 16);
INSERT INTO `dept_menu` VALUES (1, 17);
INSERT INTO `dept_menu` VALUES (1, 18);
INSERT INTO `dept_menu` VALUES (1, 19);
INSERT INTO `dept_menu` VALUES (1, 22);
INSERT INTO `dept_menu` VALUES (1, 23);
INSERT INTO `dept_menu` VALUES (1, 24);
INSERT INTO `dept_menu` VALUES (1, 25);
INSERT INTO `dept_menu` VALUES (1, 26);
INSERT INTO `dept_menu` VALUES (1, 27);
INSERT INTO `dept_menu` VALUES (1, 28);
INSERT INTO `dept_menu` VALUES (1, 30);
INSERT INTO `dept_menu` VALUES (1, 31);
INSERT INTO `dept_menu` VALUES (1, 32);
INSERT INTO `dept_menu` VALUES (1, 33);
INSERT INTO `dept_menu` VALUES (1, 34);
INSERT INTO `dept_menu` VALUES (1, 35);
INSERT INTO `dept_menu` VALUES (1, 36);
INSERT INTO `dept_menu` VALUES (1, 37);
INSERT INTO `dept_menu` VALUES (1, 38);
INSERT INTO `dept_menu` VALUES (1, 39);
INSERT INTO `dept_menu` VALUES (1, 40);
INSERT INTO `dept_menu` VALUES (1, 41);
INSERT INTO `dept_menu` VALUES (1, 42);
INSERT INTO `dept_menu` VALUES (1, 43);
INSERT INTO `dept_menu` VALUES (1, 44);
INSERT INTO `dept_menu` VALUES (1, 45);
INSERT INTO `dept_menu` VALUES (1, 46);
INSERT INTO `dept_menu` VALUES (1, 47);
INSERT INTO `dept_menu` VALUES (1, 48);
INSERT INTO `dept_menu` VALUES (1, 49);
INSERT INTO `dept_menu` VALUES (1, 50);
INSERT INTO `dept_menu` VALUES (1, 51);
INSERT INTO `dept_menu` VALUES (1, 52);
INSERT INTO `dept_menu` VALUES (1, 53);
INSERT INTO `dept_menu` VALUES (1, 54);
INSERT INTO `dept_menu` VALUES (1, 55);
INSERT INTO `dept_menu` VALUES (1, 56);
INSERT INTO `dept_menu` VALUES (1, 57);
INSERT INTO `dept_menu` VALUES (1, 58);
INSERT INTO `dept_menu` VALUES (1, 59);
INSERT INTO `dept_menu` VALUES (1, 60);
INSERT INTO `dept_menu` VALUES (1, 61);
INSERT INTO `dept_menu` VALUES (1, 62);
INSERT INTO `dept_menu` VALUES (1, 63);
INSERT INTO `dept_menu` VALUES (1, 64);
INSERT INTO `dept_menu` VALUES (1, 65);
INSERT INTO `dept_menu` VALUES (1, 66);
INSERT INTO `dept_menu` VALUES (1, 67);
INSERT INTO `dept_menu` VALUES (1, 68);
INSERT INTO `dept_menu` VALUES (1, 69);
INSERT INTO `dept_menu` VALUES (1, 70);
INSERT INTO `dept_menu` VALUES (1, 71);
INSERT INTO `dept_menu` VALUES (1, 72);
INSERT INTO `dept_menu` VALUES (1, 73);
INSERT INTO `dept_menu` VALUES (1, 74);
INSERT INTO `dept_menu` VALUES (1, 75);
INSERT INTO `dept_menu` VALUES (1, 76);
INSERT INTO `dept_menu` VALUES (1, 77);
INSERT INTO `dept_menu` VALUES (1, 78);
INSERT INTO `dept_menu` VALUES (1, 79);
INSERT INTO `dept_menu` VALUES (1, 80);
INSERT INTO `dept_menu` VALUES (1, 81);
INSERT INTO `dept_menu` VALUES (1, 82);
INSERT INTO `dept_menu` VALUES (1, 83);
INSERT INTO `dept_menu` VALUES (1, 84);
INSERT INTO `dept_menu` VALUES (1, 85);
INSERT INTO `dept_menu` VALUES (2, 18);
INSERT INTO `dept_menu` VALUES (2, 19);
INSERT INTO `dept_menu` VALUES (2, 27);
INSERT INTO `dept_menu` VALUES (2, 28);
INSERT INTO `dept_menu` VALUES (2, 30);
INSERT INTO `dept_menu` VALUES (2, 31);
INSERT INTO `dept_menu` VALUES (2, 32);
INSERT INTO `dept_menu` VALUES (2, 33);
INSERT INTO `dept_menu` VALUES (2, 34);
INSERT INTO `dept_menu` VALUES (2, 35);
INSERT INTO `dept_menu` VALUES (2, 36);
INSERT INTO `dept_menu` VALUES (2, 37);
INSERT INTO `dept_menu` VALUES (2, 38);
INSERT INTO `dept_menu` VALUES (2, 39);
INSERT INTO `dept_menu` VALUES (2, 40);
INSERT INTO `dept_menu` VALUES (2, 41);
INSERT INTO `dept_menu` VALUES (2, 42);
INSERT INTO `dept_menu` VALUES (2, 43);
INSERT INTO `dept_menu` VALUES (2, 44);
INSERT INTO `dept_menu` VALUES (2, 45);
INSERT INTO `dept_menu` VALUES (2, 46);
INSERT INTO `dept_menu` VALUES (2, 47);
INSERT INTO `dept_menu` VALUES (2, 48);
INSERT INTO `dept_menu` VALUES (2, 49);
INSERT INTO `dept_menu` VALUES (2, 50);
INSERT INTO `dept_menu` VALUES (2, 51);
INSERT INTO `dept_menu` VALUES (2, 52);
INSERT INTO `dept_menu` VALUES (2, 55);
INSERT INTO `dept_menu` VALUES (2, 56);
INSERT INTO `dept_menu` VALUES (2, 57);
INSERT INTO `dept_menu` VALUES (2, 58);
INSERT INTO `dept_menu` VALUES (2, 59);
INSERT INTO `dept_menu` VALUES (2, 60);
INSERT INTO `dept_menu` VALUES (2, 61);
INSERT INTO `dept_menu` VALUES (2, 62);
INSERT INTO `dept_menu` VALUES (2, 63);
INSERT INTO `dept_menu` VALUES (2, 64);
INSERT INTO `dept_menu` VALUES (2, 65);
INSERT INTO `dept_menu` VALUES (2, 66);
INSERT INTO `dept_menu` VALUES (2, 67);
INSERT INTO `dept_menu` VALUES (2, 68);
INSERT INTO `dept_menu` VALUES (2, 69);
INSERT INTO `dept_menu` VALUES (2, 70);
INSERT INTO `dept_menu` VALUES (2, 71);
INSERT INTO `dept_menu` VALUES (2, 74);
INSERT INTO `dept_menu` VALUES (2, 75);
INSERT INTO `dept_menu` VALUES (2, 76);
INSERT INTO `dept_menu` VALUES (2, 77);
INSERT INTO `dept_menu` VALUES (2, 78);
INSERT INTO `dept_menu` VALUES (2, 79);
INSERT INTO `dept_menu` VALUES (2, 82);
INSERT INTO `dept_menu` VALUES (2, 83);
INSERT INTO `dept_menu` VALUES (2, 84);
INSERT INTO `dept_menu` VALUES (2, 85);
INSERT INTO `dept_menu` VALUES (3, 30);
INSERT INTO `dept_menu` VALUES (3, 68);
INSERT INTO `dept_menu` VALUES (3, 69);
INSERT INTO `dept_menu` VALUES (4, 1);
INSERT INTO `dept_menu` VALUES (5, 18);
INSERT INTO `dept_menu` VALUES (5, 19);
INSERT INTO `dept_menu` VALUES (7, 1);
INSERT INTO `dept_menu` VALUES (7, 2);
INSERT INTO `dept_menu` VALUES (7, 3);
INSERT INTO `dept_menu` VALUES (7, 4);
INSERT INTO `dept_menu` VALUES (7, 5);
INSERT INTO `dept_menu` VALUES (7, 9);
INSERT INTO `dept_menu` VALUES (7, 14);
INSERT INTO `dept_menu` VALUES (7, 22);
INSERT INTO `dept_menu` VALUES (7, 23);
INSERT INTO `dept_menu` VALUES (7, 30);
INSERT INTO `dept_menu` VALUES (7, 31);
INSERT INTO `dept_menu` VALUES (7, 32);
INSERT INTO `dept_menu` VALUES (7, 36);
INSERT INTO `dept_menu` VALUES (7, 37);
INSERT INTO `dept_menu` VALUES (7, 38);
INSERT INTO `dept_menu` VALUES (7, 50);
INSERT INTO `dept_menu` VALUES (7, 52);
INSERT INTO `dept_menu` VALUES (7, 53);
INSERT INTO `dept_menu` VALUES (7, 54);
INSERT INTO `dept_menu` VALUES (7, 55);
INSERT INTO `dept_menu` VALUES (7, 56);
INSERT INTO `dept_menu` VALUES (7, 58);
INSERT INTO `dept_menu` VALUES (7, 59);
INSERT INTO `dept_menu` VALUES (7, 60);
INSERT INTO `dept_menu` VALUES (7, 61);
INSERT INTO `dept_menu` VALUES (7, 66);
INSERT INTO `dept_menu` VALUES (7, 67);
INSERT INTO `dept_menu` VALUES (7, 68);
INSERT INTO `dept_menu` VALUES (7, 69);
INSERT INTO `dept_menu` VALUES (7, 70);
INSERT INTO `dept_menu` VALUES (7, 71);
INSERT INTO `dept_menu` VALUES (7, 84);
INSERT INTO `dept_menu` VALUES (7, 85);

SET FOREIGN_KEY_CHECKS = 1;
