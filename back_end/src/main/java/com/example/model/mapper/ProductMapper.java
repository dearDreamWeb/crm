package com.example.model.mapper;

import com.example.entity.request.ProductReq;
import com.example.entity.response.ProductResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Repository
public interface ProductMapper {
    int addProduct(ProductReq productReq);
    int delProduct(Integer productId);
    int editProduct(ProductReq productReq);
    ProductResp getProduct(Integer productId);
    List<ProductResp> listProduct(ProductReq productReq);
    void batchAddProduct(List<ProductReq> list);
    ProductResp getProductByName(String productName);
    List<ProductResp> getProduct1(Integer productId);
}
