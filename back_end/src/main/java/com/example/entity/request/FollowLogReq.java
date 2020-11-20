package com.example.entity.request;

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
public class FollowLogReq {
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
    private Integer saleId;
    private Integer repairId;
    private Integer followPid;
    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;
}
