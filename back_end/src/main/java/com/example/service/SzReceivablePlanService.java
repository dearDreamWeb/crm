package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;
import com.example.entity.request.SzReceivablePlan;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
public interface SzReceivablePlanService {
    ResultVo addszPlan(SzReceivablePlan szPlan);
    ResultVo delszPlan(SzReceivablePlan szPlan);
    ResultVo editszPlan(SzReceivablePlan szPlan);
    ResultVo getszPlan(Integer planId);
    ResultVo listPlan(SzReceivablePlan szPlan);

    ResultVo plan_editOrder(SzOrder szOrder);//改 订单表的回款状态
    ResultVo editPlanInvoice(SzReceivablePlan szPlan);//修改回款计划的状态

}
