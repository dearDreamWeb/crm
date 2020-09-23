package com.example.util;

import com.example.common.enums.ResultEnum;
import com.example.entity.ResultVo;

/**
 * @author: pengjia
 * @date: 2020/9/23
 * @description:封装返回工具类
 */
public class ResultUtils {
    public static ResultVo response(Object object){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setData(object);
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setMessage(ResultEnum.SUCCESS.getMessage());
        return resultVo;
    }
}
