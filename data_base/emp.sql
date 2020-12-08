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

 Date: 09/12/2020 00:05:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `emp_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `emp_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工名称',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工昵称',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` bigint(0) NULL DEFAULT NULL COMMENT '性别（1：男、0：女）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `emp_status` bigint(0) NULL DEFAULT NULL COMMENT '员工状态（1：正常、0：冻结）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` bigint(0) NOT NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `head_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1, 'admin', 'admin', '3163059772@qq.com', 1, '14e1b600b1fd579f47433b88e8d85291', '15197902964', 1, NULL, 1, '2020-12-08 10:56:14', '2020-12-08 10:56:14', 0, 'e8fb5cb1966b8330384c78f0f84bea50', 'http://www.eblog.icu/jaychou.jpg');
INSERT INTO `emp` VALUES (2, 'test', 'test', '3169559754@qq.com', 0, '14e1b600b1fd579f47433b88e8d85291', '18569663653', 1, '123456', 8, '2020-12-07 15:11:28', '2020-12-07 15:11:28', 0, '1a8f79769d97b4ed3343245d5384b9af', NULL);
INSERT INTO `emp` VALUES (3, 'liuleimin', 'liuleimin', '1234564565@qq.com', 1, '14e1b600b1fd579f47433b88e8d85291', '18352525252', 1, NULL, 6, '2020-12-08 13:56:28', '2020-12-08 13:56:28', 0, '41118092044da696d6a1964be9bba8b2', NULL);
INSERT INTO `emp` VALUES (4, 'yang', 'yangqianqian', '1212121212@qq.com', 0, '14e1b600b1fd579f47433b88e8d85291', '15121212122', 1, NULL, 8, '2020-12-08 20:48:11', '2020-12-08 20:48:11', 0, '0ba0f29110b4c75ae01ec5de5d55ea8e', NULL);
INSERT INTO `emp` VALUES (5, 't1', 't1', NULL, NULL, '14e1b600b1fd579f47433b88e8d85291', '12121212121', 1, NULL, 4, '2020-10-19 23:01:58', '2020-10-19 23:01:58', 0, NULL, NULL);
INSERT INTO `emp` VALUES (6, 't22', 't2', NULL, NULL, '14e1b600b1fd579f47433b88e8d85291', '13212121212', 1, NULL, 8, '2020-10-12 20:56:23', '2020-10-12 12:56:22', 0, NULL, NULL);
INSERT INTO `emp` VALUES (7, 't3', 't3', NULL, NULL, '14e1b600b1fd579f47433b88e8d85291', '54646464546', 0, NULL, 1, '2020-09-28 20:11:20', '2020-09-28 20:11:20', 0, NULL, NULL);
INSERT INTO `emp` VALUES (8, 't4', 't4', NULL, NULL, '14e1b600b1fd579f47433b88e8d85291', '98566465686', 0, NULL, 1, '2020-09-28 20:11:40', '2020-09-28 20:11:40', 0, NULL, NULL);
INSERT INTO `emp` VALUES (9, 't555', 't5', NULL, NULL, '14e1b600b1fd579f47433b88e8d85291', '18478764655', 1, NULL, 3, '2020-10-19 22:59:05', '2020-10-19 14:59:06', 0, NULL, NULL);
INSERT INTO `emp` VALUES (10, 'hsadjh', 'asdkjh', NULL, NULL, '14e1b600b1fd579f47433b88e8d85291', '78557565644', 1, NULL, 2, '2020-10-14 22:33:51', '2020-10-14 14:33:53', 0, NULL, NULL);
INSERT INTO `emp` VALUES (11, 'sadsa', 'sadsa', '121212212@qq.com', 1, '14e1b600b1fd579f47433b88e8d85291', '18574564641', 1, 'asadsadasdasd', 5, '2020-10-13 10:41:33', '2020-10-13 02:41:34', 0, NULL, NULL);
INSERT INTO `emp` VALUES (15, 'aaaa', 'aaaasss', '', 1, '7c3d596ed03ab9116c547b0eb678b247', '15151515115', 1, 'aaaaaa', 4, '2020-10-19 22:58:25', '2020-10-19 14:58:26', 0, NULL, NULL);
INSERT INTO `emp` VALUES (16, '去玩儿', '去玩儿ss', '', 0, '14e1b600b1fd579f47433b88e8d85291', '13456789098', 0, '123456', 5, '2020-10-19 23:02:34', '2020-10-19 15:02:35', 0, NULL, NULL);
INSERT INTO `emp` VALUES (17, '销售一号', '销售一号', '', 1, '14e1b600b1fd579f47433b88e8d85291', '18589898888', 1, '123456', 3, '2020-12-07 15:12:53', '2020-12-07 15:12:53', 0, '5144bc79724408dda84eea05d5cc56a2', NULL);
INSERT INTO `emp` VALUES (18, '销售二号', '销售二号', '', 1, '14e1b600b1fd579f47433b88e8d85291', '18564445898', 1, '123456', 3, '2020-12-02 20:32:45', '2020-12-02 20:32:45', 0, '02a945df68b6e2459dea1ee82fa4fdcc', NULL);
INSERT INTO `emp` VALUES (19, '销售三号', '销售三号', '', 1, '14e1b600b1fd579f47433b88e8d85291', '18545454555', 1, '123456', 3, '2020-10-19 23:48:24', '2020-10-19 15:48:25', 0, NULL, NULL);
INSERT INTO `emp` VALUES (20, '销售四号', '销售四号', '', 1, '14e1b600b1fd579f47433b88e8d85291', '18544441111', 1, '123456', 3, '2020-11-25 14:05:22', '2020-11-25 14:05:22', 0, '25603d625d88a8d3eeae927c145ea89d', NULL);
INSERT INTO `emp` VALUES (21, '销售五号', '销售五号', '', 1, '14e1b600b1fd579f47433b88e8d85291', '18546665656', 1, '123456', 3, '2020-11-04 10:09:21', '2020-11-04 10:09:21', 0, 'e96b18f3e6290b815dd0acd7d1ed897d', NULL);
INSERT INTO `emp` VALUES (22, 'ghghg', 'ghg', '', 1, '63ee451939ed580ef3c4b6f0109d1fd0', '18243653251', 0, '123123', 1, '2020-11-26 09:53:01', '2020-11-26 09:53:01', 0, NULL, NULL);
INSERT INTO `emp` VALUES (23, 'wink', '眨眼', 'wink123@163.com', 0, '14e1b600b1fd579f47433b88e8d85291', '17688992233', 1, '123456', 3, '2020-12-07 14:14:09', '2020-12-07 14:14:09', 0, 'a06fd4648a21c9de8e539bb64807585e', NULL);

SET FOREIGN_KEY_CHECKS = 1;
