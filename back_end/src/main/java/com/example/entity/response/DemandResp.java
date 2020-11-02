package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandResp {
    private Integer demandId;
    private String demandTitle;
    private Integer demandDegree;
    private String demandContent;
    private Integer isSolve;
    private Integer contactsId;
    private Integer saleId;
    private Integer empId;
    private Integer cusId;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private List<SolutionResp> solutionResps;
}
