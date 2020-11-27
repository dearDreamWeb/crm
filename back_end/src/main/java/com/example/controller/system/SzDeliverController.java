package com.example.controller.system;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.anno.SysLog;
import com.example.entity.ProductDetail;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
import com.example.entity.request.SzDeliverDetails;
import com.example.entity.request.SzDeliverMingxi;
import com.example.service.SzDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yangqianqian
 * @date: 2020/10/20
 * @description:
 */
@RestController
@RequestMapping("/deliver")
public class SzDeliverController {
    @Autowired
    private SzDeliverService szdeliverService;

    @SysLog("修改产品详表的库存状态")
    @PostMapping("/mx_editProDetail")
    public ResultVo mx_editProDetail(@RequestBody ProductDetail productDetail){
        System.out.println("产品详情id："+productDetail.getProductDetailId());
        System.out.println("修改产品详表的库存状态："+productDetail.getProductDetailState());
        return  szdeliverService.mx_editProDetail(productDetail);
    }

    @SysLog("修改发货单")
    @PostMapping("/mx_editszDeliver")
    public ResultVo mx_editszDeliver(@RequestBody SzDeliver szdeliver){
        /*System.out.println("发货单id："+szdeliver.getDelId());
        System.out.println("物流id："+szdeliver.getDelWuliuid());
        System.out.println("物流公司："+szdeliver.getDelCompany());*/
        return  szdeliverService.mx_editszDeliver(szdeliver);
    }

    @SysLog("添加发货明细")
    @PostMapping("/add_mingxi")
    public ResultVo addszDeliver_mx(@RequestBody SzDeliverMingxi mingxi){
        /*System.out.println("产品详情id:"+mingxi.getProductDetailId());
        System.out.println("发货详情id:"+mingxi.getDdetId());*/
        return szdeliverService.addszDeliver_mx(mingxi);
    }

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

/*    @SysLog("添加发货单")
    @PostMapping("/addDelANDOrd")
    public ResultVo addDelANDOrd(@RequestBody SzDeliver szDeliver) {
        System.out.println("添加发货单addDelANDOrd");
        return szdeliverService.addDelANDOrd(szDeliver);
    }*/

}
