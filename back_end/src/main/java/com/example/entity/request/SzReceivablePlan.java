package com.example.entity.request;

import com.example.entity.response.EmpResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzReceivablePlan {
    private Integer planId;//回款计划id
    private BigDecimal planMoney;//计划回款金额
    private Date planTime;// 回款时间
    private Integer planPeriod;//回款期次
    private String planCaozuopeople;//操作人
    private Date planCaozuotime;//操作时间
    private Integer planInvoice;//是1否0开具发票
    private Integer planDel;//是1否0删除

    private Integer ordId;
    private Integer empId;
    private SzOrder szOrder;
    private EmpResp empResp;
    private List<SzReceivableRecord> szReceivableRecorde;//回款计划对记录

    private Integer pageNum;
    private Integer pageSize;
}
