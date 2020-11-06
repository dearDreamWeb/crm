package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/3
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailDemand {
    private Integer saleId;
    private String saleName;
    private String saleStatus;
    private Integer cusId;
    private Integer contactsId;
    private String saleSource;
    private String discoveryTime;
    private String saleType;
    private String saleStarBeacon;
    private String salePriorLevel;
    private String demandContent;
    private Date saleEstimateDate;
    private BigDecimal saleExpectMoney;
    private String salePossibility;
    private String saleStage;
    private Integer empId;
}
