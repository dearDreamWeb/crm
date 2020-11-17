package com.example.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:状态枚举
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(20000,"成功"),
    FAIL(50000,"失败"),
    URL_ERROR(40004,"路径错误"),
    NOT_NULL(90000,"参数不能为空"),
    ACCOUNT_NOT_ACTIVATE(90001,"该账号已被禁用"),
    FILE_NOT_EXITED(90002,"文件不存在"),
    NOT_AUTH(90003,"您没有该功能权限"),
    ACCOUNT_NOT_LOGIN(90004,"该账号不能登录"),

    DATA_ADD_FAIL(10001,"数据添加失败"),
    DATA_EXIST(10002,"数据已存在"),
    DATA_DEL_FAIL(10003,"数据删除失败"),
    DATA_UPDATE_FAIL(10004,"数据更新失败"),
    DATA_NOT_EXIST(10005,"数据不存在"),

    USER_ADD_FAIL(80001,"用户添加失败"),
    USER_EXIST(80002,"用户已存在"),
    USER_DEL_FAIL(80003,"用户删除失败"),
    USER_UPDATE_FAIL(80004,"用户修改失败"),
    USER_NOT_EXIST(80005,"用户不存在"),
    USER_OR_PASSWORD_ERROR(80000,"用户账号或密码错误"),

    DEPT_ADD_FAIL(80006,"部门添加失败"),
    DEPT_EXIST(80007,"部门已存在"),
    DEPT_DEL_FAIL(80008,"部门删除失败"),
    DEPT_UPDATE_FAIL(80009,"部门修改失败"),
    DEPT_AUTH_FAIL(80010,"部门授权失败"),
    DEPT_NOT_EXIST(80011,"部门不存在"),

    MENU_ADD_FAIL(80012,"菜单添加失败"),
    MENU_EXIST(80013,"菜单已存在"),
    MENU_DEL_FAIL(80014,"菜单删除失败"),
    MENU_UPDATE_FAIL(80015,"菜单修改失败"),
    MENU_NOT_EXIST(80016,"菜单不存在"),
    MENU_PID_NOT_SELF(80017,"父级id不能是自己"),

    DICT_ADD_FAIL(80018,"字典添加失败"),
    DICT_EXIST(80019,"字典已存在"),
    DICT_DEL_FAIL(80020,"字典删除失败"),
    DICT_UPDATE_FAIL(80021,"字典更新失败"),
    DICT_NOT_EXIST(80022,"字典不存在"),
    DICT_PID_NOT_SELF(8002,"字典父级id不能是自己"),

    ACTIVITY_ADD_FAIL(80023,"活动添加失败"),
    ACTIVITY_EXIST(80024,"活动已存在"),
    ACTIVITY_DEL_FAIL(80025,"活动删除失败"),
    ACTIVITY_UPDATE_FAIL(80026,"活动更新失败"),
    ACTIVITY_NOT_EXIST(80027,"活动不存在"),

    //订单
    ORDER_ADD_FAIL(40001,"订单添加失败"),
    ORDER_EXIST(40001,"订单已存在"),
    ORDER_DEL_FAIL(40003,"订单删除失败"),
    ORDER_UPDATE_FAIL(40004,"订单更新失败"),
    ORDER_NOT_EXIST(40005,"订单不存在"),

    //发货单
    Deliver_ADD_FAIL(40006,"发货单添加失败"),
    Deliver_EXIST(40007,"发货单已存在"),
    Deliver_DEL_FAIL(40008,"发货单删除失败"),
    Deliver_UPDATE_FAIL(40009,"发货单更新失败"),
    Deliver_NOT_EXIST(40010,"发货单不存在"),

    //计划回款
    ReceivablePlan_ADD_FAIL(40011,"计划回款添加失败"),
    ReceivablePlan_EXIST(40012,"计划回款已存在"),
    ReceivablePlan_DEL_FAIL(40013,"计划回款删除失败"),
    ReceivablePlan_UPDATE_FAIL(40014,"计划回款更新失败"),
    ReceivablePlan_NOT_EXIST(40015,"计划回款不存在"),

    //回款记录
    ReceivableRecord_ADD_FAIL(40011,"回款记录添加失败"),
    ReceivableRecord_EXIST(40012,"回款记录已存在"),
    ReceivableRecord_DEL_FAIL(40013,"回款记录删除失败"),
    ReceivableRecord_UPDATE_FAIL(40014,"回款记录更新失败"),
    ReceivableRecord_NOT_EXIST(40015,"回款记录不存在"),

    CLUE_NOT_BE_EDIT(80033,"您不能修改此条线索"),
    CLUE_NOT_BE_DEL(80034,"您不能删除此条线索"),
    CLUE_NOT_BE_SHARE(80037,"您不能共享此条线索"),
    CLUE_SHARE_ERROR(80038,"共享线索失败"),

    CLUE_TRANSFER_NOT_OWN(80035,"转换对象不能是自己"),

    ACTIVITY_DATE_ERROR(80036,"结束日期不能小于开始日期"),

    PLACEHOLDER(null,null);//占位符，方便快速编写代码（无其他业务作用）

    private Integer code;
    private String message;
}
