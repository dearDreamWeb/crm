package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDetailResp {
    private Integer offerDetailId;
    private Integer offerDetailCount;
    private String productName;
    private String productBrand;
    private String productModel;
    private BigDecimal productPrice;
    private BigDecimal amountMoney;
    private String remark;
    private Integer offerId;
    private Integer productId;
    private List<ProductResp> productResps;
}
