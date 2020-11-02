package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionReq {
    private Integer solutionId;
    private String solutionTitle;
    private Date createTime;
    private String content;
    private String feedback;
    private Integer cusId;
    private Integer demandId;
    private Integer saleId;
    private Integer version;
    private Date updateTime;
    private Integer isDelete;
    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;
}
