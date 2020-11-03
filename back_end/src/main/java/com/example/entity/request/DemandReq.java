package com.example.entity.request;

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
public class DemandReq {
    private Integer demandId;
    private String demandTitle;
    private String demandDegree;
    private String demandContent;
    private String isSolve;
    private Integer contactsId;
    private Integer saleId;
    private Integer empId;
    private Integer cusId;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;
}
