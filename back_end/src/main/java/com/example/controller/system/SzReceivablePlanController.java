package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SzReceivablePlan;
import com.example.service.SzReceivablePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
@RestController
@RequestMapping("/plan")
public class SzReceivablePlanController {
    @Autowired
    private SzReceivablePlanService szReceivablePlanService;

    @SysLog("添加回款计划")
    @PostMapping("/add")
    public ResultVo addszPlan(@RequestBody SzReceivablePlan szplan) {
        System.out.println("添加回款计划："+szplan.toString());
        return szReceivablePlanService.addszPlan(szplan);
    }

    @SysLog("删除回款计划")
    @PostMapping("/del")
    public ResultVo delszPlan(@RequestBody SzReceivablePlan szplan) {
        return szReceivablePlanService.delszPlan(szplan);
    }

    @SysLog("修改回款计划")
    @PostMapping("/edit")
    public ResultVo editszPlan(@RequestBody SzReceivablePlan szplan){
        return szReceivablePlanService.editszPlan(szplan);
    }

    @GetMapping
    public ResultVo getszPlan(Integer planId){
        return szReceivablePlanService.getszPlan(planId);
    }

   @GetMapping("list_all")
    public ResultVo listszPlan(SzReceivablePlan szplan){
        return szReceivablePlanService.listPlan(szplan);
    }


}
