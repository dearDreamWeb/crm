package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/17
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDetailReq {
    private Integer activityDetailId;
    private Integer activityId;
    private Integer empId;
    private String url;
    private Date authDate;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private String startDate;
    private String endDate;
    private List<Integer> empIdList;
    private Integer pageNum;
    private Integer pageSize;
}
