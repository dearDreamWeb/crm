package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.szOrder;
import com.example.service.szOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yangqianqian
 * @date: 2020/10/10
 * @description:
 */
@RestController
@RequestMapping("/order")
public class szOrderController {
    @Autowired
    private szOrderService szorderService;

    //@SysLog 日志

    @SysLog("添加订单")
    @PostMapping("/add")
    public ResultVo addszOrder(@RequestBody szOrder szorder){
        return szorderService.addszOrder(szorder);
    }

    @SysLog("删除订单")
    @PostMapping("/del")
    public ResultVo delszOrder(@RequestBody szOrder szorder){
        return  szorderService.delszOrder(szorder);
    }

    @SysLog("修改订单")
    @PostMapping("/edit")
    public ResultVo editszOrder(@RequestBody szOrder szorder){
        return  szorderService.editszOrder(szorder);
    }

    @GetMapping("list")//查单条
    public ResultVo queryszOrderById(Integer ordId){
        return szorderService.queryszOrderById(ordId);
    }

    @GetMapping("list_all")//查全部
    public ResultVo listszOrder(szOrder szorder){
        return szorderService.listszOrder(szorder);
    }
}
