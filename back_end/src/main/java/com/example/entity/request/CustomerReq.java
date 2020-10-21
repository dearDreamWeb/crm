package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReq {
    private Integer cusId;
    private String cusName;
    private Integer lifeCycle;
    private Date firstSigningTime;
    private String abbreviation;
    private String credit;
    private Integer settlement;
    private String companyAccount;
    private Integer province;
    private Integer city;
    private String detailAddress;
    private String cusRemark;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private String cusDictSource;
    private String cusDictStage;
    private Integer qualitativeId;
    private Integer gradingId;
    private Integer rationId;
    private Integer empId;
    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;

    public CustomerReq(String cusName, String abbreviation, String cusRemark, String cusDictSource) {
        this.cusName = cusName;
        this.abbreviation = abbreviation;
        this.cusRemark = cusRemark;
        this.cusDictSource = cusDictSource;
    }
}
