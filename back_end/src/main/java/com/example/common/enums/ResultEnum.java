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

    USER_ADD_FAIL(80001,"用户添加失败"),
    USER_EXIST(80002,"用户已存在"),
    USER_DEL_FAIL(80003,"用户删除失败"),
    USER_UPDATE_FAIL(80004,"用户修改失败"),
    USER_NOT_EXIST(80005,"用户不存在"),
    USER_OR_PASSWORD_ERROR(80000,"用户账号或密码错误"),

    PLACEHOLDER(null,null);//占位符，方便快速编写代码（无其他业务作用）

    private Integer code;
    private String message;
}
