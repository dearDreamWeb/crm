package com.example.common.config;

import com.example.entity.request.SzOrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class addproplusVo {
    private Integer ordId;//订单编号
    private String ordTheme;//主题
    private BigDecimal ordTotalmoney;//总金额
    private String ordConsignee;//收货人
    private String ordProvince;//省份
    private String ordCity;//市
    private String ordCountry;//区/县
    private String ordDetail;//区/县
    private String ordPhone;//收货手机号
    private Integer cusId;//客户编号
    private List<SzOrderDetails> SzOrderDetails;

}
