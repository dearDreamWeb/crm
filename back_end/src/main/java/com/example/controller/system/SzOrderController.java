package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;
import com.example.service.SzOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/10
 * @description:
 */
@RestController
@RequestMapping("/order")
public class SzOrderController {
    @Autowired
    private SzOrderService szorderService;
    //@SysLog 日志
    @SysLog("添加订单")
    @PostMapping("/add")
    public ResultVo addszOrder(@RequestBody SzOrder szorder){
        /*szorder.setOrdCreatetime(new Timestamp(System.currentTimeMillis()));*/
        return szorderService.addszOrder(szorder);
    }

    @SysLog("删除订单")
    @PostMapping("/del")
    public ResultVo delszOrder(@RequestBody SzOrder szorder){
        return  szorderService.delszOrder(szorder);
    }

    @SysLog("修改订单")
    @PostMapping("/edit")
    public ResultVo editszOrder(@RequestBody SzOrder szorder){
        return  szorderService.editszOrder(szorder);
    }

    @GetMapping//查单条
    public ResultVo getszOrder(Integer ordId){
        return szorderService.getszOrder(ordId);
    }

    @GetMapping("list_all")//查全部
    public ResultVo listszOrder(SzOrder szorder){
        return szorderService.listszOrder(szorder);
    }
    @GetMapping("/and_all")
    public List<SzOrder> OrdANDDel() {
        return szorderService.OrdANDDel();
    }
}
