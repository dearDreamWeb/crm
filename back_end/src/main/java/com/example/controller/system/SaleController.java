package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.SaleDetailDemand;
import com.example.entity.request.SaleReq;
import com.example.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @SysLog("添加销售机会")
    @PostMapping("/add")
    public ResultVo addSale(@RequestBody SaleReq saleReq) {
        return saleService.addSale(saleReq);
    }

    @SysLog("删除销售机会")
    @PostMapping("/del")
    public ResultVo delSale(@RequestBody SaleReq saleReq) {
        return saleService.delSale(saleReq);
    }

    @SysLog("修改销售机会")
    @PostMapping("/edit")
    public ResultVo editSale(@RequestBody SaleReq saleReq) {
        return saleService.editSale(saleReq);
    }

    @GetMapping
    public ResultVo getSale(SaleReq saleReq) {
        return saleService.getSale(saleReq);
    }

    @GetMapping("/list")
    public ResultVo listSale(SaleReq saleReq) {
        return saleService.listSale(saleReq);
    }

    @PostMapping("/add_reunite")
    public ResultVo addSaleDetailDemand(@RequestBody SaleDetailDemand saleDetailDemand) {
        return saleService.addSaleDetailDemand(saleDetailDemand);
    }

    @PostMapping("/sale_edit")
    public ResultVo editAndDetailEdit(@RequestBody SaleDetailDemand saleDetailDemand) {
        return saleService.editSaleAndDetail(saleDetailDemand);
    }
}
