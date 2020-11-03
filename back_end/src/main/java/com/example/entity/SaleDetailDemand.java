package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer empId;
}
