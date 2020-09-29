package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/26
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptReq {
    private Integer deptId;
    private String deptName;
    private String deptKey;
    private Integer verion;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private List<Integer> menuIdList;
}