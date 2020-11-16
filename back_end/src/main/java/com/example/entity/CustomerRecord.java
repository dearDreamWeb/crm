package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/15
 * @description: 对客户的操作记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRecord {
    private Integer recordId;
    private String recordTitle;
    private String recordType;
    private Date recordTime;
    private String recordContent;
    private Integer cusId;
    private Integer empId;
    /*
    * 客户记录类型：三一客，任务行动，销售机会，订单，回款，计划回款，
    */
}
