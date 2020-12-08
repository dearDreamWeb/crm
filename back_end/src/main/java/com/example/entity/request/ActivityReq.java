package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/11
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityReq {
    private Integer activityId;
    private String activityTitle;
    private String content;
    private String activityLink;
    private Integer views;
    private String createBy;
    private String startTime;//活动开始时间
    private String endTime;//活动结束时间
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private List<Integer> empIdList;

    private Integer pageNum;
    private Integer pageSize;


}
