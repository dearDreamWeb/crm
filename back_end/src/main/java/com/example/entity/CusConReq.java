package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: pengjia
 * @date: 2020/10/21
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CusConReq {
    private Integer clueId;
    private String cusName;
    private String abbreviation;
    private Integer cusDictSource;
    private String cusRemark;
    private Integer empId;
    private String contactsName;
    private String contactsPhone;
    private String qq;
    private String wechat;
}
