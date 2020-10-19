package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/*
YQQ
1003
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzOrderDetails {
    private Integer odetId;//订单详情编号
    private Integer odetBuynum;//购买数量
    private BigDecimal odetBuymoney;//购买价格
    private Integer ordId;//客户编号
    private Integer proId;//产品编号
}
