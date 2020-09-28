CREATE TABLE `emp` (
`emp_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '员工id',
`emp_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工名称',
`nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工昵称',
`email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '邮箱',
`sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '性别（1：男、0：女）',
`password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
`phone` int(30) NOT NULL COMMENT '电话',
`emp_status` int(2) NOT NULL COMMENT '员工状态（1：正常、0：冻结）',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`version` int(10) NOT NULL COMMENT '乐观锁',
`create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`is_delete` int(2) NOT NULL COMMENT '是否删除（1：是、0：否）',
PRIMARY KEY (`emp_id`) 
);
CREATE TABLE `dept` (
`dept_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色id',
`dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '部门名称',
`dept_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '部门标识',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
PRIMARY KEY (`dept_id`) 
);
CREATE TABLE `menu` (
`menu_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
`menu_name` varchar(30) NOT NULL COMMENT '菜单名称',
`pid` int(10) NULL COMMENT '父级id',
`pids` varchar(30) NULL COMMENT '父级id数组',
`url` varchar(255) NULL COMMENT '前端跳转路由',
`perms` varchar(30) NULL COMMENT '权限标识',
`type` int(2) NULL COMMENT '类型',
`is_show` int(2) NULL COMMENT '是否显示（1：是、0：否）',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
PRIMARY KEY (`menu_id`) 
);
CREATE TABLE `sys_log` (
`log_id` int(10) NOT NULL COMMENT '日志id',
`log_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日志标题',
`user_id` int(10) NULL COMMENT '操作人id',
`method` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法',
`content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
`ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'ip',
`uri` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'uri',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作日志（创建时间）',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
PRIMARY KEY (`log_id`) 
);
CREATE TABLE `activity` (
`activ_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '活动id',
`activ_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动标题',
`content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动内容',
`image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '背景图',
`activ_link` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动链接',
`views` int(10) NULL COMMENT '点击量',
`create_by` varchar(30) NULL COMMENT '创建人',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
PRIMARY KEY (`activ_id`) 
);
CREATE TABLE `clue` (
`clue_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '线索id',
`clue_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线索名称',
`clue_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线索电话',
`clue_status` int(2) NULL COMMENT '线索状态',
`handle_result` int(2) NULL COMMENT '处理结果',
`handle_person` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '处理人',
`remark` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注（可设置模板）',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间（处理时间）',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`activ_id` int(10) NULL COMMENT '活动id',
`emp_id` int(10) NULL COMMENT '负责人编号',
PRIMARY KEY (`clue_id`) 
);
CREATE TABLE `customer` (
`cus_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '客户id',
`cus_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '客户名称',
`
life_cycle` int(2) NULL COMMENT '生命周期',
`first_signing_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '首次签约时间',
`abbrevia` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简称',
`credit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '信用等级',
`settlement` int(2) NULL COMMENT '结款方式',
`company_account` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '对公账号',
`province` int(10) NULL COMMENT '省（关联地址表id）',
`city` int(10) NULL COMMENT '市（关联地址表id）',
`detail_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细地址',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`cus_dict_source` int(10) NULL COMMENT '客户来源（dict）',
`cus_dict_stage` int(10) NULL COMMENT '客户阶段（dict）',
`qualita_id` int(10) NULL COMMENT '定性id',
`grading_id` int(10) NULL COMMENT '定级id',
`ration_id` int(10) NULL COMMENT '定量id',
`emp_id` int(10) NULL COMMENT '负责人id',
PRIMARY KEY (`cus_id`) 
);
CREATE TABLE `qualitative` (
`qualita_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '定性id',
`qualita_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '定性名称',
PRIMARY KEY (`qualita_id`) 
);
CREATE TABLE `grading` (
`grading_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '定级id',
`grading_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '定级名称',
PRIMARY KEY (`grading_id`) 
);
CREATE TABLE `ration` (
`ration_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '定量id',
`expect_signing_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '预计签约日期',
`expect_signing_peice` int(10) NULL COMMENT '预计签约金额',
PRIMARY KEY (`ration_id`) 
);
CREATE TABLE `contacts` (
`contacts_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
`contacts_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '联系人名称',
`contacts_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '联系人类型（决策、财务等）',
`birthday` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生日',
`sex` int(2) NULL COMMENT '性别',
`wechat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '微信',
`qq` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'QQ',
`email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '邮箱',
`remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`contacts_dict_type` int(10) NULL COMMENT '联系人类型',
`cus_id` int(10) NULL COMMENT '客户id',
PRIMARY KEY (`contacts_id`) 
);
CREATE TABLE `follow_log` (
`follow_id` int(10) NOT NULL AUTO_INCREMENT,
`follow_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '跟进标题',
`follow_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '跟进描述',
`follow_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`emp_id` int(10) NULL COMMENT '负责人',
`cus_id` int(10) NULL COMMENT '客户id',
PRIMARY KEY (`follow_id`) 
);
CREATE TABLE `sales_leads` (
`sale_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '机会id',
`sale_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '机会名称',
`sale_status` int(2) NULL COMMENT '销售机会状态',
`discovery_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发现时间',
`star_beacon` int(4) NULL COMMENT '星标',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`is_delete` varchar(255) NULL COMMENT '是否删除（1：是、0：否）',
`sales_dict_status` int(10) NULL COMMENT '销售机会状态',
`sales_dict_type` int(10) NULL COMMENT '销售机会类型',
`sales_dict_source` int(10) NULL COMMENT '销售机会来源',
`sales_dict_stage` int(10) NULL COMMENT '销售机会阶段',
`cus_id` int(10) NULL COMMENT '客户id',
`contacts_id` int(10) NULL COMMENT '客户联系人',
`emp_id` int(10) NULL COMMENT '负责人',
PRIMARY KEY (`sale_id`) 
);
CREATE TABLE `demand` (
`demand_id` int(10) NOT NULL AUTO_INCREMENT,
`demand_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`degree` int(4) NULL,
`content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(10) NULL COMMENT '是否删除（1：是、0：否）',
`contacts_id` int(10) NULL COMMENT '客户需求提供人',
`sale_id` int(10) NULL COMMENT '对应机会',
`emp_id` int(10) NULL COMMENT '所有者（销售人员）',
`is_solve` int(2) NULL COMMENT '需求是否解决（1：是、0：否）',
PRIMARY KEY (`demand_id`) 
);
CREATE TABLE `solution` (
`solu_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
`solu_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方案主题',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提交时间',
`content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方案内容',
`feedback` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '客户反馈',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`version` int(10) NULL COMMENT '乐观锁',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`cus_id` int(10) NULL COMMENT '客户id',
`sale_id` int(10) NULL COMMENT '对应机会',
`solut_dict_status` int(10) NULL COMMENT '解决方案状态',
PRIMARY KEY (`solu_id`) 
);
CREATE TABLE `competitor` (
`com_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
`com_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '竞品名称',
`com_price` decimal(10,2) NULL COMMENT '竞品价格',
`com_ability` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '竞争能力',
`discovery_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发现时间',
`advantage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '优势',
`inferiority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '劣势',
`strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '应对策略',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`cus_id` int(10) NULL COMMENT '客户id',
`sale_id` int(10) NULL COMMENT '销售机会id',
`demand_id` int(10) NULL COMMENT '项目（需求）id',
PRIMARY KEY (`com_id`) 
);
CREATE TABLE `offer` (
`offer_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
`offer_numbers` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '报价单号',
`offer_price` decimal(30,2) NULL COMMENT '报价价格',
`examine_person` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '审核人',
`examine_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
`feedback` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '客户反馈',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`cus_id` int(10) NULL COMMENT '客户id',
`contacts_id` int(10) NULL COMMENT '报价人（客户联系人）',
`sale_id` int(10) NULL COMMENT '销售机会id',
`offer_dict_status` int(10) NULL COMMENT '报价审核状态',
PRIMARY KEY (`offer_id`) 
);
CREATE TABLE `offer_details` (
`off_det_id` int(10) NOT NULL AUTO_INCREMENT,
`off_det_count` int(10) NULL COMMENT '数量',
`off_det_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位',
`unit_price` decimal(10,2) NULL COMMENT '单价',
`amount_money` decimal(10,2) NULL COMMENT '金额',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除（1：是、0：否）',
`offer_id` int(10) NULL COMMENT '报价id',
`contacts_id` int(10) NULL COMMENT '联系人',
`product_id` int(10) NULL COMMENT '产品id',
PRIMARY KEY (`off_det_id`) 
);
CREATE TABLE `product` (
`product_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
`product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '名称',
`product_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品图片',
`product_model` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '型号',
`product_price` decimal(10,2) NULL COMMENT '价格',
`product_cost` decimal(10,2) NULL COMMENT '成本',
`product_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位',
`product_bar_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '序列号',
`product_status` int(2) NULL COMMENT '状态（正常、下架）',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`is_delete` int(2) NULL COMMENT '是否删除',
PRIMARY KEY (`product_id`) 
);
CREATE TABLE `dict` (
`dict_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '字典id',
`dict_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '字典编码',
`dict_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '字典名称',
`pid` int(10) NULL COMMENT '父级编号',
`version` int(10) NULL COMMENT '乐观锁',
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete` int(2) NULL COMMENT '是否删除',
PRIMARY KEY (`dict_id`) 
);
CREATE TABLE `sys_location` (
`loca_id` int(20) NOT NULL AUTO_INCREMENT,
`name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`fid` int(20) NULL,
`language` int(11) NOT NULL,
PRIMARY KEY (`loca_id`) ,
INDEX `location_index` (`loca_id` ASC) USING BTREE
);
