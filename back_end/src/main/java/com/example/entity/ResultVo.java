package com.example.entity;

import lombok.Data;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:返回类
 */
@Data
public class ResultVo<T> {
    private Integer code;//状态码
    private String message;//提示信息
    private T data;//响应数据
}
