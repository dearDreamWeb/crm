package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueReq {
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
    private Integer empId;
    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;
    private List<Integer> clueIdList;
}
