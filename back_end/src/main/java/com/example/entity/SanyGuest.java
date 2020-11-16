package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description: 客户三一客的节点推进
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanyGuest {
    private Integer sanyGuestId;
    private String sanyGuestName;
    private Date sanyGuestTime;
    private Integer cusId;
    private Integer sanyGuestEmpId;
}
