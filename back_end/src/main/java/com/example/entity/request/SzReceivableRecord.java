package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzReceivableRecord {
    private Integer recoId;//回款记录编号
    private Integer recoReceivable;//是否回款
    private String recoCaozuopeople;//处理人
    private Date recoTime;//实际回款时间
    private BigDecimal recoMoney;//实际回款金额
    private String recoLiushui;//交易流水号

    private Integer planId;//回款计划编号

    private SzReceivablePlan szReceivablePlan;
}
