package com.example.service;

import com.example.entity.ResultVo;

/**
 * @author: yangqianqian
 * @date: 2020/11/25
 * @description:
 */
public interface ProductDetailService {
    ResultVo getDetailByProductId(Integer productId);
}
