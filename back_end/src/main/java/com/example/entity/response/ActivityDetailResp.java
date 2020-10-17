package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/17
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDetailResp {
    private Integer activityDetailId;
    private Integer activityId;
    private Integer empId;
    private String url;
    private Date authDate;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private ActivityResp activityResp;
    private EmpResp empResp;

    private String activityTitle;
    private String empName;
}
