package com.example.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:异常
 */
@Data
@AllArgsConstructor
public class SysException extends RuntimeException {
    private Integer code;
    private String message;
}
