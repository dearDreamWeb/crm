package com.example.entity.response;

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
public class CustomerResp {
    private Integer cusId;
    private String cusName;
    private Integer lifeCycle;
    private Integer sanyGuestId;
    private Date firstSigningTime;
    private String abbreviation;
    private Integer creditId;
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
    private Integer cusDictSource;
    private String cusDictStage;
    private Integer qualitativeId;
    private Integer gradingId;
    private Integer rationId;
    private Integer empId;
    private String empName;
    private EmpResp empResp;
    private String cusDictSourceName;
}
