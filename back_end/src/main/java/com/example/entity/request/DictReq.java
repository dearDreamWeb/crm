package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/10
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictReq {
    private Integer dictId;
    private String dictCode;
    private String dictName;
    private Integer pid;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private String startDate;
    private String endDate;
}
