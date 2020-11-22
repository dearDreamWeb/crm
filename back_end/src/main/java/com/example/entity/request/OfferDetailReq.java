package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDetailReq {
    private Integer offerDetailId;
    private Integer offerDetailCount;
    private String offerDetailUnit;
    private BigDecimal unitPrice;
    private BigDecimal amountMoney;
    private String remark;
    private Integer offerId;
    private Integer productId;
}