package com.example.model.mapper;

import com.example.entity.ProductDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/28
 * @description:
 */
@Repository
public interface ProductDetailMapper {
    int addProductDetail(ProductDetail productDetail);
    int delProductDetail(Integer productDetailId);
    int editProductDetail(ProductDetail productDetail);
    ProductDetail getDetail(Integer productDetailId);
    List<ProductDetail> listDetail();
    void batchAddProductDetail(List<ProductDetail> list);

    List<ProductDetail> getDetailByProductId(Integer productId);
}
