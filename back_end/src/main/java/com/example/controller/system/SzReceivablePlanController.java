package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;
import com.example.entity.request.SzReceivablePlan;
import com.example.entity.request.SzReceivableRecord;
import com.example.service.SzOrderService;
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
    /*
    *     @SysLog("添加订单")
    @PostMapping("/add")
    public ResultVo addszOrder(@RequestBody SzOrder szorder){
        System.out.println("1订单详情："+szorder.getSzOrderDetails());
        System.out.println("订单："+szorder);
        for (SzOrderDetails sz1 : szorder.getSzOrderDetails()) {
            System.out.println("2订单详情："+sz1);
        }
        return szorderService.addszOrder(szorder);
        *
        * System.out.println("添加回款计划："+szplan.toString());
        return szReceivablePlanService.addszPlan(szplan);
    }*/

    @SysLog("添加回款计划")
    @PostMapping("/add")
    public ResultVo addszPlan(@RequestBody SzReceivablePlan szplan) {
        System.out.println("1.记录："+szplan.getSzReceivableRecorde());
        System.out.println("666"+szplan.getPlanCaozuopeople());
        System.out.println("2.计划："+ szplan);
        for (SzReceivableRecord r1 : szplan.getSzReceivableRecorde()) {
            System.out.println("3。记录："+r1);
        }
        return szReceivablePlanService.addszPlan(szplan);
    }

    @SysLog("修改订单状态")
    @PostMapping("/plan_editOrder")
    public ResultVo plan_editOrder(@RequestBody SzOrder szOrder){
        System.out.println("...计划回款新增同时进修改订单状态....");
        System.out.println(szOrder);
        System.out.println("状态:"+szOrder.getOrdPlan());
        return szReceivablePlanService.plan_editOrder(szOrder);
    }
    /*
    * @SysLog("修改发货单")
    @PostMapping("/mx_editszDeliver")
    public ResultVo mx_editszDeliver(@RequestBody SzDeliver szdeliver){
        System.out.println("发货单id："+szdeliver.getDelId());
        System.out.println("物流id："+szdeliver.getDelWuliuid());
        System.out.println("物流公司："+szdeliver.getDelCompany());
        return  szdeliverService.mx_editszDeliver(szdeliver);
    }*/

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

    @SysLog("修改回款计划的状态")
    @PostMapping("/editPlanInvoice")
    public ResultVo editPlanInvoice(@RequestBody SzReceivablePlan szplan){
        System.out.println(szplan);
        System.out.println("修改计划回款planId:"+szplan.getPlanId());
        System.out.println("进修改回款计划状态的方法....");
        return szReceivablePlanService.editPlanInvoice(szplan);
    }

}
