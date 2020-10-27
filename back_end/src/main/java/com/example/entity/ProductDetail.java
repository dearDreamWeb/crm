package com.example.entity;

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
}
