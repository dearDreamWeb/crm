package com.example.common.exception;

import com.example.entity.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:统一捕获自定义异常类
 */
@ControllerAdvice
public class ExceptionCatch {
    @ExceptionHandler(SysException.class)
    @ResponseBody
    public ResultVo customException(SysException e){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(e.getCode());
        resultVo.setMessage(e.getMessage());
        return resultVo;
    }
}
