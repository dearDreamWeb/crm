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

    //根据id联查 （发货单页面用到的）
    List<ProductDetail> getDetailByProductId(Integer productId);
    //发货时 新增发货明细 同时 修改 产品详情的库存状态
    int mx_editProDetail(Integer id);

}
