package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Repair;
import com.example.service.RepairService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        Repair vo = repairService.getRepairMsg(repair.getRepairId());
        System.out.println("查询的数据是："+vo);
        vo.setEmpId(repair.getEmpId());
        vo.setRepairGdstate(repair.getRepairGdstate());
        vo.setRepairProblem(repair.getRepairProblem());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        vo.setRepairAppointment(sdf.format(new Date()));
        System.out.println("待修改的数据是："+vo);
        return repairService.updateRepair(vo);
    }

    @SysLog("取消派单")
    @PostMapping("/updateQuxiao")
    public ResultVo updateQuxiao(@RequestBody Repair repair) {
        Repair vo = repairService.getRepairMsg(repair.getRepairId());
        System.out.println("查询的数据是："+vo);
        Repair main = new Repair();
        BeanUtils.copyProperties(vo, main);
        main.setEmpId(null);
        main.setRepairGdstate("未派单");
        System.out.println("待修改的数据是："+main);
        return repairService.updateRepair(main);
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
