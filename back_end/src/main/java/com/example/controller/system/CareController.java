package com.example.controller.system;


import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Care;
import com.example.service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuleimin
 * @date: 2020/10/12
 * @description:
 */
@RestController
@RequestMapping("/care")
public class CareController {
    @Autowired
    private CareService careService;

    @SysLog("添加关怀")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Care care) {
        return careService.addCare(care);
    }

    @SysLog("删除关怀")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Care care) {
        System.out.println("获取的参数是："+care);
        return careService.deleteCare(care.getCareId());
    }

    @SysLog("修改关怀")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Care care) {
        return careService.updateCare(care);
    }

    @GetMapping
    public ResultVo getDict(@RequestParam("careId")Integer careId) {
        return careService.getCare(careId);
    }


    @GetMapping("/list")
    public ResultVo listDict(Care care) {
        return careService.listCare(care);
    }


}
