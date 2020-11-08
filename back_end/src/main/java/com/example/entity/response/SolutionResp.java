package com.example.entity.response;

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
public class SolutionResp {
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
    private String cusName;
    private String saleName;
    private String demandTitle;
}
