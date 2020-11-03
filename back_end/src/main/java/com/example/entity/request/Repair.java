package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liuleimin
 * @date: 2020/10/29
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repair {
    private Integer repairId;
    private Integer empId;//接单人
    private Date repairDate;//接单时间
    private String repairProblem;//zhut
    private String repairPersonnel;
    private Date repairAppointment;
    private Date repairActual;
    private String repairGdstae;
    private String repairFault;
    private String repairHfjl;
    private double repairWxfy;
    private String repairSfzb;
    private String repairLxr;
    private String repairsjhm;
    private Integer cusId;
    private Integer productId;
    private Integer orderId;
    private Integer dictId;
    private Integer pageNum;
    private Integer pageSize;
}
