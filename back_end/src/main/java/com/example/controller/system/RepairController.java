package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping
    public ResultVo getDict(@RequestParam("repairId")Integer repairId) {
        return repairService.getRepair(repairId);
    }
}
