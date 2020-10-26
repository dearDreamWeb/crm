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
    private String followContent;
    private Date followTime;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer empId;
    private Integer cusId;
}
