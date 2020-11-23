package com.example.controller.system;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
import com.example.entity.request.SzDeliverDetails;
import com.example.service.SzDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yangqianqian
 * @date: 2020/10/20
 * @description:
 */
@RestController
@RequestMapping("deliver")
public class SzDeliverController {
    @Autowired
    private SzDeliverService szdeliverService;
    /*
    *  @SysLog("添加订单")
    @PostMapping("/add")
    public ResultVo addszOrder(@RequestBody SzOrder szorder){
        System.out.println("1订单详情："+szorder.getSzOrderDetails());
        System.out.println("订单："+szorder);
        for (SzOrderDetails sz1 : szorder.getSzOrderDetails()) {
            System.out.println("2订单详情："+sz1);
        }
        return szorderService.addszOrder(szorder);
    }
    * */
    @SysLog("添加发货单")
    @PostMapping("/add")
    public ResultVo addszDeliver(@RequestBody SzDeliver szdeliver){
        System.out.println("1.发货单详情："+ szdeliver.getSzDeliverDetails());
        System.out.println("2.发货单："+ szdeliver);
        for (SzDeliverDetails sd1 : szdeliver.getSzDeliverDetails()) {
            System.out.println("3.发货详情" + sd1);
        }
        return szdeliverService.addszDeliver(szdeliver);
    }

    @SysLog("删除发货单")
    @PostMapping("/del")
    public ResultVo delszDeliver(@RequestBody SzDeliver szdeliver){
        return  szdeliverService.delszDeliver(szdeliver);
    }

    @SysLog("修改发货单")
    @PostMapping("/edit")
    public ResultVo editszDeliver(@RequestBody SzDeliver szdeliver){
        return  szdeliverService.editszDeliver(szdeliver);
    }

    @GetMapping//查单条
    public ResultVo getszDeliver(Integer delId){
        return szdeliverService.getszDeliver(delId);
    }

    @GetMapping("list_all")//查全部
    public ResultVo listszOrder(SzDeliver szdeliver){
        return szdeliverService.listszDeliver(szdeliver);
    }

    @SysLog("添加发货单")
    @PostMapping("/addDelANDOrd")
    public ResultVo addDelANDOrd(@RequestBody SzDeliver szDeliver) {
        System.out.println("添加发货单addDelANDOrd");
        return szdeliverService.addDelANDOrd(szDeliver);
    }

}
