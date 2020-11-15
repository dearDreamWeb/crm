package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/15
 * @description:
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
}
