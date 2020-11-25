package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.ProductReq;
import com.example.entity.response.ProductResp;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
public interface ProductService {
    ResultVo addProduct(ProductReq productReq);
    ResultVo delProduct(ProductReq productReq);
    ResultVo editProduct(ProductReq productReq);
    ResultVo getProduct(Integer productId);
    ResultVo listProduct(ProductReq productReq);
    ResultVo getProductByName(String productName);
}
