package com.example.model.mapper;

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
}
