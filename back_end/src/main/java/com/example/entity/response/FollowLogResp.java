package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowLogResp {
    private Integer followId;
    private String followTitle;
    private Integer followCategory;
    private String followContent;
    private String followStartTime;
    private String followEndTime;
    private String followType;
    private String followTypeIcon;
    private String intention;
    private String followHandleResult;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer empId;
    private Integer cusId;
    private Integer repairId;
    private Integer followPid;
    private EmpResp empResp;
    private CustomerResp customerResp;
}
