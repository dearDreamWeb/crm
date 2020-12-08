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

 Date: 09/12/2020 00:05:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '父级id',
  `pids` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id数组',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端跳转路由',
  `perms` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `menu_type` bigint(0) NULL DEFAULT NULL COMMENT '类型',
  `is_show` bigint(0) NULL DEFAULT NULL COMMENT '是否显示（1：是、0：否）',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', 0, '0', 'el-icon-s-tools', '#', NULL, 1, 1, 1, '2020-09-28 09:14:29', '2020-09-28 09:14:29', 0);
INSERT INTO `menu` VALUES (2, '用户管理', 1, '1', 'el-icon-user-solid', '/user', NULL, 2, 1, 1, '2020-09-28 09:12:36', '2020-09-28 09:12:36', 0);
INSERT INTO `menu` VALUES (3, '部门管理', 1, '1', 'el-icon-s-cooperation', '/dept', NULL, 2, 1, 1, '2020-09-30 22:45:53', '2020-09-30 22:45:53', 0);
INSERT INTO `menu` VALUES (4, '菜单管理', 1, '1', 'el-icon-menu', '/menu', NULL, 2, 1, 1, '2020-09-28 22:36:16', '2020-09-28 22:36:16', 0);
INSERT INTO `menu` VALUES (5, '用户列表', 2, '1,2', NULL, '#', 'system:user:index', 3, 1, 1, '2020-09-28 22:58:54', '2020-09-28 22:58:54', 0);
INSERT INTO `menu` VALUES (6, '用户添加', 2, '1,2', NULL, '#', 'system:user:add', 3, 1, 1, '2020-09-28 22:59:23', '2020-09-28 22:59:23', 0);
INSERT INTO `menu` VALUES (7, '用户编辑', 2, '1,2', NULL, '#', 'system:user:edit', 3, 1, 1, '2020-09-28 22:59:53', '2020-09-28 22:59:53', 0);
INSERT INTO `menu` VALUES (8, '用户删除', 2, '1,2', NULL, '#', 'system:user:del', 3, 1, 1, '2020-09-28 23:00:17', '2020-09-28 23:00:17', 0);
INSERT INTO `menu` VALUES (9, '部门列表', 3, '1,3', NULL, '#', 'system:dept:index', 3, 1, 1, '2020-09-28 23:07:23', '2020-09-28 23:07:23', 0);
INSERT INTO `menu` VALUES (10, '部门添加', 3, '1,3', NULL, '#', 'system:dept:add', 3, 1, 1, '2020-09-28 23:07:25', '2020-09-28 23:07:25', 0);
INSERT INTO `menu` VALUES (11, '部门编辑', 3, '1,3', NULL, '#', 'system:dept:edit', 3, 1, 1, '2020-09-28 23:07:27', '2020-09-28 23:07:27', 0);
INSERT INTO `menu` VALUES (12, '部门删除', 3, '1,3', NULL, '#', 'system:dept:del', 3, 1, 1, '2020-09-28 23:07:29', '2020-09-28 23:07:29', 0);
INSERT INTO `menu` VALUES (13, '分配权限', 3, '1,3', NULL, '#', 'system:dept:auth', 3, 1, 1, '2020-09-28 23:07:30', '2020-09-28 23:07:30', 0);
INSERT INTO `menu` VALUES (14, '菜单列表', 4, '1,4', NULL, '#', 'system:menu:index', 3, 1, 1, '2020-09-28 23:07:32', '2020-09-28 23:07:32', 0);
INSERT INTO `menu` VALUES (15, '菜单添加', 4, '1,4', NULL, '#', 'system:menu:add', 3, 1, 1, '2020-09-28 23:07:33', '2020-09-28 23:07:33', 0);
INSERT INTO `menu` VALUES (16, '菜单编辑', 4, '1,4', NULL, '#', 'system:menu:edit', 3, 1, 1, '2020-09-28 23:07:35', '2020-09-28 23:07:35', 0);
INSERT INTO `menu` VALUES (17, '菜单删除', 4, '1,4', NULL, '#', 'system:menu:del', 3, 1, 1, '2020-09-28 23:07:38', '2020-09-28 23:07:38', 0);
INSERT INTO `menu` VALUES (18, '售前管理', 0, '0', 'fa fa-address-card', '#', '', 1, 1, 5, '2020-10-16 09:17:27', NULL, 0);
INSERT INTO `menu` VALUES (19, '客户管理', 18, '18', 'fa fa-slack', '/customer', '', 2, 1, 4, '2020-10-16 09:24:25', NULL, 0);
INSERT INTO `menu` VALUES (22, '字典管理', 1, '1', 'fa fa-hospital-o', '/dict', '', 2, 1, 1, '2020-10-12 22:29:50', '2020-10-12 22:29:50', 0);
INSERT INTO `menu` VALUES (23, '字典列表', 22, '1,22', '', '#', 'system:dict:index', 3, 1, 1, '2020-10-12 22:29:54', '2020-10-12 22:29:54', 0);
INSERT INTO `menu` VALUES (24, '字典添加', 22, '1,22', '', '#', 'system:dict:add', 3, 1, 2, '2020-10-12 22:29:58', '2020-10-12 22:29:58', 0);
INSERT INTO `menu` VALUES (25, '字典修改', 22, '1,22', '', '#', 'system:dict:edit', 3, 1, 1, '2020-10-12 22:30:00', '2020-10-12 22:30:00', 0);
INSERT INTO `menu` VALUES (26, '字典删除', 22, '1,22', '', '#', 'system:dict:del', 3, 1, 1, '2020-10-12 22:30:03', '2020-10-12 22:30:03', 0);
INSERT INTO `menu` VALUES (27, '获客管理', 0, '0', 'fa fa-align-left', '#', '', 1, 1, 3, '2020-10-16 09:17:05', NULL, 0);
INSERT INTO `menu` VALUES (28, '活动管理', 27, '27', 'fa fa-group', '/activity', '', 2, 1, 1, '2020-10-12 22:28:59', '2020-10-12 22:28:59', 0);
INSERT INTO `menu` VALUES (30, '售中模块', 0, '0', 'fa fa-youtube-square', '#', '', 1, 1, 1, '2020-10-12 21:45:55', '2020-10-12 21:45:55', 0);
INSERT INTO `menu` VALUES (31, '订单管理', 30, '30', 'fa fa-sitemap', '/order', '', 2, 1, 1, '2020-10-12 22:29:30', '2020-10-12 22:29:30', 0);
INSERT INTO `menu` VALUES (32, '订单列表', 31, '30,31', 'fa fa-align-left', '#', 'system:order:index', 3, 1, 2, '2020-10-12 22:29:45', '2020-10-12 22:29:45', 0);
INSERT INTO `menu` VALUES (33, '活动列表', 28, '27,28', '', '#', 'system:activity:index', 3, 1, 1, '2020-10-16 09:28:31', '2020-10-16 09:28:31', 0);
INSERT INTO `menu` VALUES (34, '活动添加', 28, '27,28', '', '#', 'system:activity:add', 3, 1, 2, '2020-10-17 15:27:03', NULL, 0);
INSERT INTO `menu` VALUES (35, '客户列表', 19, '18,19', '', '#', 'system:customer:index', 3, 1, 2, '2020-10-16 09:27:20', '2020-10-16 09:27:20', 0);
INSERT INTO `menu` VALUES (36, '售后模块', 0, '0', 'fa fa-cc-visa', '#', '', 1, 1, 4, '2020-10-20 15:32:33', '2020-10-20 15:32:33', 0);
INSERT INTO `menu` VALUES (37, '关怀管理', 36, '36', 'fa fa-building', '/care', '', 2, 1, 1, '2020-10-13 15:03:44', '2020-10-13 15:03:44', 0);
INSERT INTO `menu` VALUES (38, '关怀列表', 37, '36,37', 'fa fa-check-square', '', 'system:care:index', 3, 1, 1, '2020-10-13 15:04:39', '2020-10-13 15:04:39', 0);
INSERT INTO `menu` VALUES (39, '线索管理', 27, '27', 'fa fa-modx', '/clue', '', 2, 1, 1, '2020-10-15 08:59:34', '2020-10-15 08:59:34', 0);
INSERT INTO `menu` VALUES (40, '线索列表', 39, '27,39', '', '#', 'system:clue:index', 3, 1, 3, '2020-10-16 09:29:36', '2020-10-16 09:29:36', 0);
INSERT INTO `menu` VALUES (41, '线索添加', 39, '27,39', '', '#', 'system:clue:add', 3, 1, 1, '2020-10-16 09:29:40', '2020-10-16 09:29:40', 0);
INSERT INTO `menu` VALUES (42, '线索修改', 39, '27,39', '', '#', 'system:clue:edit', 3, 1, 1, '2020-10-16 09:29:44', '2020-10-16 09:29:44', 0);
INSERT INTO `menu` VALUES (43, '线索删除', 39, '27,39', '', '#', 'system:clue:del', 3, 1, 1, '2020-10-16 09:29:48', '2020-10-16 09:29:48', 0);
INSERT INTO `menu` VALUES (44, '活动修改', 28, '27,28', '', '#', 'system:activity:edit', 3, 1, 1, '2020-10-16 09:28:50', '2020-10-16 09:28:50', 0);
INSERT INTO `menu` VALUES (45, '活动删除', 28, '27,28', '', '#', 'system:activity:del', 3, 1, 1, '2020-10-16 09:28:54', '2020-10-16 09:28:54', 0);
INSERT INTO `menu` VALUES (46, '活动分配', 28, '27,28', '', '#', 'system:activity:auth', 3, 1, 3, '2020-10-17 15:26:47', NULL, 0);
INSERT INTO `menu` VALUES (47, '客户修改', 19, '18,19', '', '#', 'system:customer:edit', 3, 1, 1, '2020-10-16 09:25:20', '2020-10-16 09:25:20', 0);
INSERT INTO `menu` VALUES (48, '客户添加', 19, '18,19', '', '#', 'system:customer:add', 3, 1, 1, '2020-10-16 09:25:49', '2020-10-16 09:25:49', 0);
INSERT INTO `menu` VALUES (49, '客户删除', 19, '18,19', '', '#', 'system:customer:del', 3, 1, 1, '2020-10-16 09:26:18', '2020-10-16 09:26:18', 0);
INSERT INTO `menu` VALUES (50, '投诉管理', 36, '36', 'fa fa-digg', '/complaint', '', 2, 1, 1, '2020-10-17 15:56:10', '2020-10-17 15:56:10', 0);
INSERT INTO `menu` VALUES (51, '活动详情', 27, '27', 'fa fa-beer', '/activityDetail', '', 2, 1, 2, '2020-10-17 16:19:15', NULL, 0);
INSERT INTO `menu` VALUES (52, '问答库', 36, '36', 'fa fa-whatsapp', '/qa_library', '', 2, 1, 1, '2020-10-20 08:44:05', '2020-10-20 08:44:05', 0);
INSERT INTO `menu` VALUES (53, '日志管理', 1, '1', 'fa fa-sort-amount-asc', '/syslog', '', 2, 1, 1, '2020-10-20 15:26:46', '2020-10-20 15:26:46', 0);
INSERT INTO `menu` VALUES (54, '日志列表', 53, '1,53', '', '#', 'system:log:index', 3, 1, 1, '2020-10-20 15:29:10', '2020-10-20 15:29:10', 0);
INSERT INTO `menu` VALUES (55, '投诉列表', 50, '36,50', '', '#', 'system:complaint:index', 3, 0, 1, '2020-10-20 15:29:47', '2020-10-20 15:29:47', 0);
INSERT INTO `menu` VALUES (56, '问答列表', 52, '36,52', '', '#', 'system:qa:index', 3, 1, 1, '2020-10-20 15:30:18', '2020-10-20 15:30:18', 0);
INSERT INTO `menu` VALUES (57, '活动详情列表', 51, '27,51', '', '#', 'system:ad:index', 3, 1, 1, '2020-10-20 15:31:21', '2020-10-20 15:31:21', 0);
INSERT INTO `menu` VALUES (58, '发货单', 30, '30', 'fa fa-tasks', '/deliver', '', 2, 1, 1, '2020-10-21 11:48:36', '2020-10-21 11:48:36', 0);
INSERT INTO `menu` VALUES (59, '发货列表', 58, '30,58', '', '', 'system:deliver:index', 3, 1, 1, '2020-10-21 11:49:11', '2020-10-21 11:49:11', 0);
INSERT INTO `menu` VALUES (60, '客服管理', 36, '36', 'fa fa-comment', '/cus_kf', '', 2, 1, 2, '2020-10-22 09:03:02', NULL, 0);
INSERT INTO `menu` VALUES (61, '客服列表', 60, '36,60', '', '#', 'system:cuskf:index', 3, 1, 1, '2020-10-22 09:03:30', '2020-10-22 09:03:30', 0);
INSERT INTO `menu` VALUES (62, '报价管理', 18, '18', 'fa fa-bomb', '/offer', '', 2, 1, 3, '2020-11-21 11:31:53', '2020-11-21 11:31:53', 0);
INSERT INTO `menu` VALUES (63, '报价列表', 62, '18,62', '', '#', 'system:baojia:index', 3, 1, 1, '2020-10-23 11:31:06', '2020-10-23 11:31:06', 0);
INSERT INTO `menu` VALUES (64, '客户跟进', 18, '18', 'fa fa-fa', '/follow_up', '', 2, 1, 3, '2020-10-26 16:50:03', '2020-10-26 16:50:03', 0);
INSERT INTO `menu` VALUES (65, '跟进列表', 64, '18,64', '', '#', 'system:follow:index', 3, 1, 2, '2020-10-26 16:50:25', NULL, 0);
INSERT INTO `menu` VALUES (66, '产品管理', 30, '30', 'fa fa-fort-awesome', '/product', '', 2, 1, 1, '2020-10-26 23:47:09', '2020-10-26 23:47:09', 0);
INSERT INTO `menu` VALUES (67, '产品列表', 66, '30,66', '', '', 'system:product:index', 3, 1, 1, '2020-10-26 23:47:48', '2020-10-26 23:47:48', 0);
INSERT INTO `menu` VALUES (68, '计划回款', 30, '30', 'fa fa-diamond', '/plan', '', 2, 1, 1, '2020-10-28 19:16:21', '2020-10-28 19:16:21', 0);
INSERT INTO `menu` VALUES (69, '计划回款列表', 68, '30,68', '', '', 'system:plan:index', 3, 1, 1, '2020-10-28 19:16:51', '2020-10-28 19:16:51', 0);
INSERT INTO `menu` VALUES (70, '维修工单', 36, '36', 'fa fa-group', '/repairorder', '', 2, 1, 1, '2020-10-29 11:08:49', '2020-10-29 11:08:49', 0);
INSERT INTO `menu` VALUES (71, '维单列表', 70, '36,70', '', '', 'system:repairorder:index', 3, 1, 1, '2020-10-29 11:09:25', '2020-10-29 11:09:25', 0);
INSERT INTO `menu` VALUES (72, '框架测试', 1, '1', 'fa fa-cloud', '/vuetify', '', 2, 1, 1, '2020-10-30 09:35:28', '2020-10-30 09:35:28', 0);
INSERT INTO `menu` VALUES (73, '测试列表', 72, '1,72', '', '', 'system:test:index', 3, 1, 1, '2020-10-30 09:35:56', '2020-10-30 09:35:56', 0);
INSERT INTO `menu` VALUES (74, '销售机会', 18, '18', 'fa fa-tasks', '/sale', '', 2, 1, 1, '2020-11-02 16:10:48', '2020-11-02 16:10:48', 0);
INSERT INTO `menu` VALUES (75, '销售机会列表', 74, '18,74', '', '#', 'system:sale:index', 3, 1, 3, '2020-11-02 16:17:05', NULL, 0);
INSERT INTO `menu` VALUES (76, '客户需求', 18, '18', 'fa fa-hand-spock-o', '/demand', '', 2, 1, 1, '2020-11-02 16:22:56', '2020-11-02 16:22:56', 0);
INSERT INTO `menu` VALUES (77, '需求列表', 76, '18,76', '', '', 'system:demand:index', 3, 1, 1, '2020-11-02 16:23:24', '2020-11-02 16:23:24', 0);
INSERT INTO `menu` VALUES (78, '解决方案', 18, '18', 'fa fa-modx', '/solution', '', 2, 1, 1, '2020-11-02 16:24:46', '2020-11-02 16:24:46', 0);
INSERT INTO `menu` VALUES (79, '方案列表', 78, '18,78', '', '', 'system:solution:index', 3, 1, 1, '2020-11-02 16:25:16', '2020-11-02 16:25:16', 0);
INSERT INTO `menu` VALUES (80, '自定义活动', 27, '27', 'fa fa-hand-rock-o', '/custom_activity', '', 2, 1, 1, '2020-11-09 20:57:42', '2020-11-09 20:57:42', 0);
INSERT INTO `menu` VALUES (81, '自定义列表', 80, '27,80', '', '#', 'system:cusacitvity:index', 3, 1, 1, '2020-11-09 21:00:22', '2020-11-09 21:00:22', 0);
INSERT INTO `menu` VALUES (82, '联系人', 18, '18', 'fa fa-address-book', '/contacts', '', 2, 1, 1, '2020-11-10 08:49:38', '2020-11-10 08:49:38', 0);
INSERT INTO `menu` VALUES (83, '联系人列表', 82, '18,82', '', '', 'system:contacts:index', 3, 1, 1, '2020-11-10 08:50:31', '2020-11-10 08:50:31', 0);
INSERT INTO `menu` VALUES (84, '维修总控台', 36, '36', 'fa fa-cube', '/repair_control_desk', '', 2, 1, 1, '2020-11-17 18:37:09', '2020-11-17 18:37:09', 0);
INSERT INTO `menu` VALUES (85, '总控台列表', 84, '36,84', '', '#', 'system:repair_control:index', 3, 1, 1, '2020-11-17 18:38:10', '2020-11-17 18:38:10', 0);

SET FOREIGN_KEY_CHECKS = 1;
