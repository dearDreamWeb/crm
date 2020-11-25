package com.example.entity;

import com.example.entity.request.SzDeliverMingxi;
import com.example.entity.response.ProductResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: pengjia
 * @date: 2020/10/27
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    private Integer productDetailId;
    private Integer productId;
    private String productBarCode;
    private ProductResp productResp;

    private SzDeliverMingxi szDeliverMingxi;
}
