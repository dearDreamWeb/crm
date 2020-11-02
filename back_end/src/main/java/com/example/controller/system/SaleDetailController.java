package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SaleDetailReq;
import com.example.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@RestController
@RequestMapping("/sale_detail")
public class SaleDetailController {
    @Autowired
    private SaleDetailService detailService;

    @SysLog("添加销售机会详情")
    @PostMapping("/add")
    public ResultVo addSaleDetail(@RequestBody SaleDetailReq saleDetailReq) {
        return detailService.addSaleDetail(saleDetailReq);
    }

    @SysLog("删除销售机会详情")
    @PostMapping("/del")
    public ResultVo delSaleDetail(@RequestBody SaleDetailReq saleDetailReq) {
        return detailService.delSaleDetail(saleDetailReq);
    }

    @SysLog("修改销售机会详情")
    @PostMapping("/edit")
    public ResultVo editSaleDetail(@RequestBody SaleDetailReq saleDetailReq) {
        return detailService.editSaleDetail(saleDetailReq);
    }

    @GetMapping
    public ResultVo getSaleDetail(SaleDetailReq saleDetailReq) {
        return detailService.getSaleDetail(saleDetailReq);
    }

    @GetMapping("/list")
    public ResultVo listSaleDetail(SaleDetailReq saleDetailReq) {
        return detailService.listSaleDetail(saleDetailReq);
    }
}
