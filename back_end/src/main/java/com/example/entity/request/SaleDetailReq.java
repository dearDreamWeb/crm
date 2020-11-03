package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailReq {
    private Integer saleDetailId;
    private Date saleEstimateDate;
    private String salePossibility;
    private BigDecimal saleExpectMoney;
    private String saleStarBeacon;
    private String salePriorLevel;
    private String saleStage;
    private String saleStageRemark;
    private Integer saleId;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
