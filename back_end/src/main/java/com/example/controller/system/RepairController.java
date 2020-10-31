package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Repair;
import com.example.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuleimin
 * @date: 2020/10/29
 * @description:
 */
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @SysLog("添加")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Repair repair) {
        return repairService.addRepair(repair);
    }

    @SysLog("删除")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Repair repair) {
        System.out.println("获取的参数是："+repair);
        return repairService.deleteRepair(repair.getRepairId());
    }

    @SysLog("修改")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Repair repair) {

        System.out.println("待修改的数据是："+repair);
        return repairService.updateRepair(repair);
    }

    @GetMapping
    public ResultVo getDict(@RequestParam("repairId")Integer repairId) {
        return repairService.getRepair(repairId);
    }

    @GetMapping("/list")
    public ResultVo listDict( Repair repair) {
        return repairService.listRepair(repair);
    }
}
