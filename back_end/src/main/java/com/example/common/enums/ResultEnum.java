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

    PLACEHOLDER(null,null);//占位符，方便快速编写代码（无其他业务作用）

    private Integer code;
    private String message;
}
