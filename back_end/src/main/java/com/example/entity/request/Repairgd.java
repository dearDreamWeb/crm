package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liuleimin
 * @date: 2020/10/24
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repairgd {
    private Integer repairId;
    private String repairPerson;
    private Date repairDate;
    private String repairProblem;
    private String repairPersonnel;
    private Date repairAppdate;
    private Date repairActualdate;
    private String repairGdstate;
    private String repairFault;
    private String repairHfjl;
    private double repairWxfy;
    private String repairsfzb;
    private Integer cusId;
    private Integer productId;
    private Integer orderId;
    private Integer inventorySeq;
    private Integer dictId;
}
