package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResp {
    private Integer salsId;
    private String saleName;
    private Integer saleStatus;
    private Integer saleType;
    private Integer saleSource;
    private Date discoveryTime;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer cusId;
    private Integer contactsId;
    private Integer empId;
    private SaleDetailResp saleDetailResp;
}
