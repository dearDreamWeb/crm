package com.example.entity.request;

import com.example.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yangqianqian
 * @date: 2020/11/21
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzDeliverMingxi {
    private Integer mingxiId;//发货明细编号
    private Integer mingxiState;//状态

    private Integer ddetId;//发货详情编号
    private Integer productDetailId;//产品详情编号-拿序列号

    private SzDeliverDetails szDeliverDetails;//一个发货明细对应一个发货详情
    private ProductDetail  productDetail;//11
}
