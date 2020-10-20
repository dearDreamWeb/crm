package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueResp {
    private Integer clueId;
    private String clueName;
    private String cluePhone;
    private Integer clueStatus;
    private Integer clueType;
    private Integer handleResult;
    private String handlePerson;
    private String remark;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer activityId;
    private ActivityResp activityResp;
    private Integer empId;
    private EmpResp empResp;
}
