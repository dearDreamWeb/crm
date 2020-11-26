package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResp {
    private Integer saleId;
    private String saleName;
    private String saleStatus;
    private String saleType;
    private String discoveryTime;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer cusId;
    private Integer contactsId;
    private Integer empId;
    private SaleDetailResp saleDetailResp;
    private List<DemandResp> demandResps;

    private String cusName;
    private String empName;

    private String contactsName;
}
