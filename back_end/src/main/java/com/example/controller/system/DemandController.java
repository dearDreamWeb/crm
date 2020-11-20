package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.DemandReq;
import com.example.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@RestController
@RequestMapping("/demand")
public class DemandController {
    @Autowired
    private DemandService demandService;

    @SysLog("新增需求")
    @PostMapping("/add")
    public ResultVo addDemand(@RequestBody DemandReq demandReq,
                              HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return demandService.addDemand(demandReq,token);
    }

    @SysLog("删除需求")
    @PostMapping("/del")
    public ResultVo delDemand(@RequestBody DemandReq demandReq) {
        return demandService.delDemand(demandReq);
    }

    @SysLog("修改需求")
    @PostMapping("/edit")
    public ResultVo editDemand(@RequestBody DemandReq demandReq) {
        return demandService.editDemand(demandReq);
    }

    @GetMapping
    public ResultVo getDemand(DemandReq demandReq) {
        return demandService.getDemand(demandReq);
    }

    @GetMapping("/list")
    public ResultVo listDemand(DemandReq demandReq) {
        return demandService.listDemand(demandReq);
    }

    @GetMapping("/get_by_saleId")
    public ResultVo getBySaleId(@RequestParam("saleId")Integer saleId) {
        return demandService.getBySaleId(saleId);
    }

    @GetMapping("/list_by_sale")
    public ResultVo listBySale(DemandReq demandReq) {
        return demandService.listBySale(demandReq);
    }
}
