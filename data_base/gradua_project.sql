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

 Date: 09/12/2020 00:03:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accessories
-- ----------------------------
DROP TABLE IF EXISTS `accessories`;
CREATE TABLE `accessories`  (
  `access_id` int(0) NOT NULL COMMENT '编号',
  `access_repairman` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人',
  `access_sjcharge` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际收费',
  `repair_id` int(0) NULL DEFAULT NULL,
  `pjbh` int(0) NULL DEFAULT NULL,
  `access_shul` int(0) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`access_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `activity_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动内容',
  `activity_link` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动链接',
  `views` bigint(0) NULL DEFAULT NULL COMMENT '点击量',
  `create_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_detail
-- ----------------------------
DROP TABLE IF EXISTS `activity_detail`;
CREATE TABLE `activity_detail`  (
  `activity_detail_id` int(0) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(0) NOT NULL,
  `emp_id` bigint(0) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专属链接',
  `auth_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `version` int(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`activity_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for care
-- ----------------------------
DROP TABLE IF EXISTS `care`;
CREATE TABLE `care`  (
  `care_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `care_carezt` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关怀主题',
  `care_lxrcontacts` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `care_date` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `care_executor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人',
  `care_carenr` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关怀内容',
  `care_customerfk` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户反馈',
  `cus_id` int(0) NULL DEFAULT NULL,
  `emp_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`care_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for clue
-- ----------------------------
DROP TABLE IF EXISTS `clue`;
CREATE TABLE `clue`  (
  `clue_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '线索id',
  `clue_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线索名称',
  `clue_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线索电话',
  `clue_status` bigint(0) NULL DEFAULT NULL COMMENT '线索状态（1.已处理、0.未处理）',
  `clue_type` bigint(0) NULL DEFAULT NULL COMMENT '线索类型（1：公司，0：个人）',
  `handle_result` bigint(0) NULL DEFAULT NULL COMMENT '处理结果（1.有效、0.无效）',
  `handle_person` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `remark` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注（可设置模板）',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间（处理时间）',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `activity_id` bigint(0) NULL DEFAULT NULL COMMENT '活动id',
  `emp_id` bigint(0) NULL DEFAULT NULL COMMENT '负责人编号',
  PRIMARY KEY (`clue_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for clue_follow_log
-- ----------------------------
DROP TABLE IF EXISTS `clue_follow_log`;
CREATE TABLE `clue_follow_log`  (
  `clue_follow_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `clue_id` int(0) NULL DEFAULT NULL COMMENT '线索id',
  `clue_follow_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作标题',
  `clue_follow_time` datetime(0) NULL DEFAULT NULL COMMENT '线索操作时间',
  `clue_follow_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `clue_follow_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`clue_follow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for competitor
-- ----------------------------
DROP TABLE IF EXISTS `competitor`;
CREATE TABLE `competitor`  (
  `com_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `com_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '竞品名称',
  `com_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '竞品价格',
  `com_ability` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '竞争能力',
  `discovery_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发现时间',
  `advantage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优势',
  `inferiority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '劣势',
  `strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应对策略',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(0) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT NULL COMMENT '是否删除（1：是、0：否）',
  `cus_id` bigint(0) NULL DEFAULT NULL COMMENT '客户id',
  `sale_id` bigint(0) NULL DEFAULT NULL COMMENT '销售机会id',
  `demand_id` bigint(0) NULL DEFAULT NULL COMMENT '项目（需求）id',
  PRIMARY KEY (`com_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `complaint_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `complaint_complaintzt` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投诉主题',
  `complaint_receptionist` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接待人',
  `complaint_classification` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `complaint_describe` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `complaint_data` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `complaint_complainants` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投诉人',
  `complaint_urgent` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急程度',
  `complaint_handle` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理过程',
  `complaint_handlegc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  `complaint_customerfk` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户反馈',
  `complaint_hfvisit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回访',
  `cus_id` int(0) NULL DEFAULT NULL,
  `dict_id` int(0) NULL DEFAULT NULL,
  `emp_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts`  (
  `contacts_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `contacts_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人名称',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` bigint(0) NULL DEFAULT 2 COMMENT '性别',
  `contacts_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机号\r\n',
  `wechat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `contacts_dict_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人类型',
  `cus_id` bigint(0) NULL DEFAULT NULL COMMENT '客户id',
  PRIMARY KEY (`contacts_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cus_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `cus_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `life_cycle` bigint(0) NULL DEFAULT 1 COMMENT '生命周期（1.潜在，2.签约，3.重复，4.失效）',
  `first_signing_time` datetime(0) NULL DEFAULT NULL COMMENT '首次签约时间',
  `abbreviation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `credit_id` bigint(0) NULL DEFAULT NULL COMMENT '信用等级(sys_credit)',
  `settlement` bigint(0) NULL DEFAULT NULL COMMENT '结款方式',
  `company_account` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对公账号',
  `province` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `area` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `detail_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `cus_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `cus_dict_source` bigint(0) NULL DEFAULT NULL COMMENT '客户来源（dict）',
  `cus_dict_stage` bigint(0) NULL DEFAULT NULL COMMENT '客户阶段（dict）',
  `sany_guest_id` int(0) NULL DEFAULT NULL COMMENT '三一客节点',
  `qualitative_id` bigint(0) NULL DEFAULT NULL COMMENT '定性id',
  `grading_id` bigint(0) NULL DEFAULT NULL COMMENT '定级id',
  `ration_id` bigint(0) NULL DEFAULT NULL COMMENT '定量id',
  `emp_id` bigint(0) NULL DEFAULT NULL COMMENT '负责人id',
  PRIMARY KEY (`cus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_record
-- ----------------------------
DROP TABLE IF EXISTS `customer_record`;
CREATE TABLE `customer_record`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `record_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录标题',
  `record_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录类型',
  `record_time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `record_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录内容',
  `cus_id` int(0) NULL DEFAULT NULL COMMENT '客户',
  `emp_id` int(0) NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_service
-- ----------------------------
DROP TABLE IF EXISTS `customer_service`;
CREATE TABLE `customer_service`  (
  `customer_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `customer_theme` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `customer_servicelx` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务类型',
  `customer_servicefs` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务方式',
  `customer_data` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `customer_khcontacts` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户联系人',
  `customer_state` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `customer_executor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人',
  `customer_servicenr` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务内容',
  `customer_customerfk` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户反馈',
  `cus_id` int(0) NULL DEFAULT NULL,
  `emp_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`  (
  `demand_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `demand_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求主题',
  `demand_degree` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重要程度',
  `demand_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求内容',
  `is_solve` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '需求是否解决（1：是、0：否）',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `contacts_id` bigint(0) NULL DEFAULT NULL COMMENT '客户需求提供人',
  `sale_id` bigint(0) NULL DEFAULT NULL COMMENT '对应机会',
  `emp_id` bigint(0) NULL DEFAULT NULL COMMENT '所有者（销售人员）',
  `cus_id` bigint(0) NULL DEFAULT NULL COMMENT '客户id',
  PRIMARY KEY (`demand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for dept_menu
-- ----------------------------
DROP TABLE IF EXISTS `dept_menu`;
CREATE TABLE `dept_menu`  (
  `dept_id` bigint(0) NOT NULL,
  `menu_id` bigint(0) NOT NULL,
  PRIMARY KEY (`dept_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `dict_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '父级编号',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for emp_dept
-- ----------------------------
DROP TABLE IF EXISTS `emp_dept`;
CREATE TABLE `emp_dept`  (
  `emp_id` int(0) NULL DEFAULT NULL,
  `dept_id` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for follow_log
-- ----------------------------
DROP TABLE IF EXISTS `follow_log`;
CREATE TABLE `follow_log`  (
  `follow_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `follow_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进标题',
  `follow_category` bigint(0) NULL DEFAULT NULL COMMENT '跟进类别1：待办2：记录',
  `follow_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进描述',
  `follow_start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进开始日期',
  `follow_end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进结束日期',
  `follow_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `follow_type_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型图标',
  `intention` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户意向',
  `follow_handle_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进结果',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `emp_id` bigint(0) NULL DEFAULT NULL COMMENT '所有者',
  `sale_id` bigint(0) NULL DEFAULT NULL COMMENT '机会id',
  `repair_id` bigint(0) NULL DEFAULT NULL COMMENT '维修工单id',
  `follow_pid` bigint(0) NULL DEFAULT NULL COMMENT '上级跟进日志id',
  PRIMARY KEY (`follow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grading
-- ----------------------------
DROP TABLE IF EXISTS `grading`;
CREATE TABLE `grading`  (
  `grading_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '定级id',
  `grading_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定级icon',
  `grading_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定级名称',
  `grading_time` datetime(0) NULL DEFAULT NULL COMMENT '定级时间',
  `cus_id` int(0) NULL DEFAULT NULL COMMENT '客户',
  `grading_emp_id` int(0) NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`grading_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for maintenance
-- ----------------------------
DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE `maintenance`  (
  `maint_id` int(0) NOT NULL COMMENT '编号',
  `maint_time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `maint_fzrperson` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `maint_operation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `maint_jlrecord` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '记录',
  PRIMARY KEY (`maint_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for offer
-- ----------------------------
DROP TABLE IF EXISTS `offer`;
CREATE TABLE `offer`  (
  `offer_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `offer_theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价主题',
  `offer_numbers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价单号',
  `offer_status` bigint(0) NULL DEFAULT NULL COMMENT '报价审核状态1：已；0：未；2：请求审核',
  `offer_price` decimal(30, 2) NULL DEFAULT NULL COMMENT '报价价格',
  `examine_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `examine_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` int(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `contacts_id` bigint(0) NULL DEFAULT NULL COMMENT '报价人（客户联系人）',
  `sale_id` bigint(0) NULL DEFAULT NULL COMMENT '销售机会id',
  `emp_id` bigint(0) NULL DEFAULT NULL COMMENT '指定人',
  `is_turn` bigint(0) NULL DEFAULT 0 COMMENT '是否转为订单1.是，0.否',
  PRIMARY KEY (`offer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for offer_detail
-- ----------------------------
DROP TABLE IF EXISTS `offer_detail`;
CREATE TABLE `offer_detail`  (
  `offer_detail_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `offer_detail_count` bigint(0) NULL DEFAULT NULL COMMENT '数量',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `product_brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `product_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `amount_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `offer_id` bigint(0) NULL DEFAULT NULL COMMENT '报价id',
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`offer_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `product_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品图片',
  `product_model` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `product_brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `product_stock` int(0) NULL DEFAULT NULL COMMENT '库存',
  `product_size` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '尺寸',
  `product_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `product_status` int(0) NULL DEFAULT 0 COMMENT '状态（0正常、1下架）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(0) NULL DEFAULT 0 COMMENT '1是0否删除',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_detail
-- ----------------------------
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail`  (
  `product_detail_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '详情id',
  `product_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `product_bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `product_detail_state` int(0) NULL DEFAULT 0 COMMENT '库存状态（在库0出库1）',
  PRIMARY KEY (`product_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa
-- ----------------------------
DROP TABLE IF EXISTS `qa`;
CREATE TABLE `qa`  (
  `qa_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `qa_wtproblem` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `qa_jjsolve` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案',
  `qa_customerfk` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入人',
  `qa_data` datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
  `emp_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`qa_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qualitative
-- ----------------------------
DROP TABLE IF EXISTS `qualitative`;
CREATE TABLE `qualitative`  (
  `qualitative_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '定性id',
  `qualitative_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定性icon',
  `qualitative_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定性名称',
  `qualitative_time` datetime(0) NULL DEFAULT NULL COMMENT '定性日期',
  `cus_id` int(0) NULL DEFAULT NULL COMMENT '客户',
  `qualitative_emp_id` int(0) NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`qualitative_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ration
-- ----------------------------
DROP TABLE IF EXISTS `ration`;
CREATE TABLE `ration`  (
  `ration_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '定量id',
  `ration_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定量图标',
  `ration_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定量名称',
  `ration_time` datetime(0) NULL DEFAULT NULL COMMENT '定量确定时间',
  `expect_signing_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预计签约日期',
  `expect_signing_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '预计签约金额',
  `expect_signing_number` int(0) NULL DEFAULT NULL COMMENT '预计销量',
  `cus_id` int(0) NULL DEFAULT NULL COMMENT '客户',
  `ration_emp_id` int(0) NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`ration_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repair_order
-- ----------------------------
DROP TABLE IF EXISTS `repair_order`;
CREATE TABLE `repair_order`  (
  `repair_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `repair_person` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接单人',
  `repair_receiving_time` datetime(0) NULL DEFAULT NULL COMMENT '接单时间',
  `repair_problem` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题描述',
  `repair_personnel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人员',
  `repair_appointment` datetime(0) NULL DEFAULT NULL COMMENT '约定上门时间',
  `repair_actual` datetime(0) NULL DEFAULT NULL COMMENT '实际上门时间',
  `repair_gdstate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工单状态',
  `repair_fault` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '故障描述',
  `repair_hfjl` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回访记录',
  `repair_wxfy` decimal(20, 2) NULL DEFAULT NULL COMMENT '维修费用',
  `repair_sfzb` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否在保',
  `cus_id` int(0) NULL DEFAULT NULL,
  `product_id` int(0) NULL DEFAULT NULL,
  `order_id` int(0) NULL DEFAULT NULL,
  `inventory_seq` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_id` int(0) NULL DEFAULT NULL,
  `repair_lxr` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_id` int(0) NULL DEFAULT NULL,
  `dept_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `sale_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sale_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机会名称',
  `sale_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机会状态',
  `sale_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机会类型',
  `discovery_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发现时间',
  `version` bigint(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `is_delete` bigint(0) NULL DEFAULT 0,
  `cus_id` bigint(0) NULL DEFAULT NULL,
  `contacts_id` bigint(0) NULL DEFAULT NULL,
  `emp_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`sale_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sale_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_detail`;
CREATE TABLE `sale_detail`  (
  `sale_detail_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '销售机会详情id',
  `sale_estimate_date` date NULL DEFAULT NULL COMMENT '预计签单日期',
  `sale_possibility` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可能性',
  `sale_expect_money` decimal(30, 2) NULL DEFAULT NULL COMMENT '预期金额',
  `sale_star_beacon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '星标',
  `sale_prior_level` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优先级别',
  `sale_stage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阶段',
  `sale_stage_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阶段备注',
  `sale_id` bigint(0) NULL DEFAULT NULL,
  `version` bigint(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` bigint(0) NULL DEFAULT 0,
  PRIMARY KEY (`sale_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for san_guest
-- ----------------------------
DROP TABLE IF EXISTS `san_guest`;
CREATE TABLE `san_guest`  (
  `san_guest_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '三一id',
  `san_guest_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三一名称',
  `san_guest_time` datetime(0) NULL DEFAULT NULL COMMENT '三一确定时间',
  `cus_id` int(0) NULL DEFAULT NULL COMMENT '客户名称',
  `san_guest_emp_id` int(0) NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`san_guest_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for solution
-- ----------------------------
DROP TABLE IF EXISTS `solution`;
CREATE TABLE `solution`  (
  `solution_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `solution_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案主题',
  `create_time` date NULL DEFAULT NULL COMMENT '提交时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案内容',
  `feedback` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户反馈',
  `version` bigint(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` bigint(0) NULL DEFAULT 0 COMMENT '是否删除（1：是、0：否）',
  `cus_id` bigint(0) NULL DEFAULT NULL COMMENT '客户id',
  `sale_id` bigint(0) NULL DEFAULT NULL COMMENT '对应机会',
  `demand_id` bigint(0) NULL DEFAULT NULL COMMENT '需求id',
  PRIMARY KEY (`solution_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_credit
-- ----------------------------
DROP TABLE IF EXISTS `sys_credit`;
CREATE TABLE `sys_credit`  (
  `credit_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '信用等级id',
  `credit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `credit_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `credit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`credit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_location
-- ----------------------------
DROP TABLE IF EXISTS `sys_location`;
CREATE TABLE `sys_location`  (
  `loca_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fid` bigint(0) NULL DEFAULT NULL,
  `language` bigint(0) NOT NULL,
  PRIMARY KEY (`loca_id`) USING BTREE,
  INDEX `location_index`(`loca_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `log_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志标题',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '操作人id',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `uri` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'uri',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作日志（创建时间）',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1330 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_deliver
-- ----------------------------
DROP TABLE IF EXISTS `sz_deliver`;
CREATE TABLE `sz_deliver`  (
  `del_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '发货单编号',
  `del_wuliuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `del_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司',
  `del_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货人',
  `del_state` int(0) NULL DEFAULT NULL COMMENT '状态（0未发/1已发）',
  `del_createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_delete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除 0否1是',
  `del_province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `del_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `del_country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '县/区',
  `del_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `del_expecttime` datetime(0) NULL DEFAULT NULL COMMENT '预计发货时间',
  `del_actualtime` datetime(0) NULL DEFAULT NULL COMMENT '实际发货时间',
  `ord_id` int(0) NULL DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`del_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5014 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_deliver_details
-- ----------------------------
DROP TABLE IF EXISTS `sz_deliver_details`;
CREATE TABLE `sz_deliver_details`  (
  `ddet_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '发货详情编号',
  `ddet_num` int(0) NULL DEFAULT NULL COMMENT '发货数量',
  `del_id` int(0) NULL DEFAULT NULL COMMENT '发货单编号',
  `product_id` int(0) NULL DEFAULT NULL COMMENT '产品编号',
  PRIMARY KEY (`ddet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7021 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_deliver_mingxi
-- ----------------------------
DROP TABLE IF EXISTS `sz_deliver_mingxi`;
CREATE TABLE `sz_deliver_mingxi`  (
  `mingxi_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '发货明细编号',
  `mingxi_state` int(0) NULL DEFAULT NULL COMMENT '状态',
  `ddet_id` int(0) NULL DEFAULT NULL COMMENT '发货详情编号',
  `product_detail_id` int(0) NULL DEFAULT NULL COMMENT '产品详情编号（拿序列号）',
  PRIMARY KEY (`mingxi_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6050 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_inventory_water
-- ----------------------------
DROP TABLE IF EXISTS `sz_inventory_water`;
CREATE TABLE `sz_inventory_water`  (
  `water_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '仓库流水表编号',
  `water_state` int(0) NULL DEFAULT NULL COMMENT '状态（入库/出库)',
  `seq_id` int(0) NULL DEFAULT NULL COMMENT '产品序列编号',
  PRIMARY KEY (`water_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_invoice
-- ----------------------------
DROP TABLE IF EXISTS `sz_invoice`;
CREATE TABLE `sz_invoice`  (
  `invo_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '开票编号',
  `invo_time` datetime(0) NULL DEFAULT NULL COMMENT '开票时间',
  `invo_drawer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票处理人',
  `invo_money` decimal(19, 2) NULL DEFAULT NULL COMMENT '开票金额',
  `invo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票商户名称（本公司）',
  `ord_id` int(0) NULL DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`invo_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_order
-- ----------------------------
DROP TABLE IF EXISTS `sz_order`;
CREATE TABLE `sz_order`  (
  `ord_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单编号\r\n',
  `ord_theme` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `ord_state` int(0) NULL DEFAULT 0 COMMENT '订单状态 执行中0 已完成1',
  `ord_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `ord_totalmoney` decimal(19, 2) NULL DEFAULT NULL COMMENT '总金额',
  `ord_starttime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `ord_dealtime` datetime(0) NULL DEFAULT NULL COMMENT '成交时间',
  `ord_fahuotime` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `ord_huikuanmoney` decimal(19, 2) NULL DEFAULT NULL COMMENT '回款金额',
  `ord_consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `ord_province` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `ord_city` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `ord_country` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `ord_detail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `ord_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货手机号',
  `ord_createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `ord_delete` int(0) NULL DEFAULT 0 COMMENT '是1否0删除',
  `ord_plan` int(0) NULL DEFAULT NULL COMMENT '是1否0添加计划回款',
  `cus_id` int(0) NULL DEFAULT NULL COMMENT '客户编号',
  `offer_id` int(0) NULL DEFAULT NULL COMMENT '报价编号',
  PRIMARY KEY (`ord_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1014 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_order_details
-- ----------------------------
DROP TABLE IF EXISTS `sz_order_details`;
CREATE TABLE `sz_order_details`  (
  `odet_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单详情编号',
  `odet_buynum` int(0) NULL DEFAULT NULL COMMENT '购买数量',
  `odet_buymoney` decimal(19, 2) NULL DEFAULT NULL COMMENT '购买价格',
  `ord_id` int(0) NULL DEFAULT NULL COMMENT '订单编号',
  `product_id` int(0) NULL DEFAULT NULL COMMENT '产品编号',
  PRIMARY KEY (`odet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2021 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_receivable_plan
-- ----------------------------
DROP TABLE IF EXISTS `sz_receivable_plan`;
CREATE TABLE `sz_receivable_plan`  (
  `plan_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '回款计划编号',
  `plan_money` decimal(19, 2) NULL DEFAULT NULL COMMENT '计划回款金额',
  `plan_time` datetime(0) NULL DEFAULT NULL COMMENT '计划回款时间',
  `plan_period` int(0) NULL DEFAULT 1 COMMENT '计划回款期次',
  `plan_caozuopeople` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人(负责人)',
  `plan_caozuotime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `plan_invoice` int(0) NULL DEFAULT NULL COMMENT '状态0执行中1已完成',
  `plan_del` int(0) NULL DEFAULT 0 COMMENT '是1否0删除',
  `ord_id` int(0) NULL DEFAULT NULL COMMENT '订单编号',
  `emp_id` int(0) NULL DEFAULT NULL COMMENT '员工编号',
  PRIMARY KEY (`plan_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3013 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_receivable_record
-- ----------------------------
DROP TABLE IF EXISTS `sz_receivable_record`;
CREATE TABLE `sz_receivable_record`  (
  `reco_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '回款记录编号',
  `reco_receivable` int(0) NULL DEFAULT NULL COMMENT '回款状态:未回0 已回1 回款中2',
  `reco_caozuopeople` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `reco_time` datetime(0) NULL DEFAULT NULL COMMENT '实际回款时间',
  `reco_money` decimal(19, 2) NULL DEFAULT NULL COMMENT '实际回款金额',
  `reco_liushui` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易流水号',
  `record_plan` int(0) NULL DEFAULT NULL COMMENT '计划回款期次',
  `time_plan` datetime(0) NULL DEFAULT NULL COMMENT '计划回款时间',
  `money_plan` decimal(19, 2) NULL DEFAULT NULL COMMENT '计划回款金额',
  `plan_id` int(0) NULL DEFAULT NULL COMMENT '回款计划编号',
  `reco_createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `reco_hasmoney` decimal(19, 2) NULL DEFAULT NULL COMMENT '已回款金额',
  PRIMARY KEY (`reco_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4017 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_refund
-- ----------------------------
DROP TABLE IF EXISTS `sz_refund`;
CREATE TABLE `sz_refund`  (
  `refu_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '退货编号',
  `refu_theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `refu_tomoney` decimal(19, 2) NULL DEFAULT NULL COMMENT '应退款',
  `refu_time` datetime(0) NULL DEFAULT NULL COMMENT '退货时间',
  `refu_state` int(0) NULL DEFAULT NULL COMMENT '状态（执行中/已完成）',
  `ord_id` int(0) NULL DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`refu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sz_refund_details
-- ----------------------------
DROP TABLE IF EXISTS `sz_refund_details`;
CREATE TABLE `sz_refund_details`  (
  `rdet_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '退货详情编号',
  `rdet_why` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退货原因',
  `rdet_num` int(0) NULL DEFAULT NULL COMMENT '退货数量',
  `rdet_hasmoney` decimal(19, 2) NULL DEFAULT NULL COMMENT '已退款',
  `rdet_intonum` int(0) NULL DEFAULT NULL COMMENT '已入库数量',
  `refu_id` int(0) NULL DEFAULT NULL COMMENT '退货单编号',
  `seq_id` int(0) NULL DEFAULT NULL COMMENT '产品序列编号',
  PRIMARY KEY (`rdet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `phone` bigint(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
