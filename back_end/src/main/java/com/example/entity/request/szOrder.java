package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/*
YQQ
1002
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class szOrder {
    private Integer ordId;//订单编号
    private String ordHead;//负责人
    private BigDecimal ordTotalmoney;//总金额
    private Data ordCreatetime;// 创建时间
    private Data ordDealtime;//成交时间
    private Data ordFahuotime;//发货时间
    private BigDecimal ordHuikuanmoney;//回款金额
    private String ordConsignee;//收货人
    private String ordProvince;//省份
    private String ordCity;//市
    private String ordCountry;//区/县
    private Integer ordPhone;//收货手机号
    private Integer cusId;//客户编号
    private Integer offerId;//报价编号
}
