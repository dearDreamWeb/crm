package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
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

    @SysLog("添加发货单")
    @PostMapping("/add")
    public ResultVo addszDeliver(@RequestBody SzDeliver szdeliver){
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
    @PostMapping("/addANDord")
    public int addDelANDOrd(SzDeliver szDeliver) {
        return szdeliverService.addDelANDOrd(szDeliver);
    }

}
