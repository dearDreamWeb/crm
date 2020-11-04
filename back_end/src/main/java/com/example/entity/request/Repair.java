package com.example.entity.request;

import com.example.entity.response.CustomerResp;
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
    private String repairAppointment;
    private String repairActual;
    private String repairGdstae;
    private String repairFault;
    private String repairHfjl;
    private Integer repairWxfy;
    private String repairSfzb;
    private String repairLxr;
    private String repairsjhm;
    private Integer cusId;
    private Integer productId;
    private Integer orderId;
    private Integer dictId;
    private Integer pageNum;
    private Integer pageSize;
    private CustomerResp customerResp;
}
