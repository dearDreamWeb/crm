package com.example.model.mapper;

import com.example.entity.request.SzOrder;
import com.example.entity.request.SzReceivablePlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
@Repository
public interface SzReceivablePlanMapper {
    int addszPlan(SzReceivablePlan szPlan);
    int delszPlan(Integer planId);
    int editszPlan(SzReceivablePlan szPlan);
    SzReceivablePlan getszPlan(Integer ordId);
    List<SzReceivablePlan> listszPlan(SzReceivablePlan szPlan);
    int plan_editOrder(SzOrder szOrder);//改
    int editPlanInvoice(SzReceivablePlan szPlan);//修改回款计划的状态

}
