package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;

/**
 * @author: yangqianqian
 * @date: 2020/10/20
 * @description:
 */
public interface SzDeliverService {
    ResultVo addszDeliver(SzDeliver szdeliver);
    ResultVo delszDeliver(SzDeliver szdeliver);
    ResultVo editszDeliver(SzDeliver szdeliver);
    ResultVo getszDeliver(Integer delId);
    ResultVo listszDeliver(SzDeliver szdeliver);
}
