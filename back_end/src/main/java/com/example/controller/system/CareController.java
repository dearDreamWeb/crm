package com.example.controller.system;

import com.example.anno.CheckPermissions;
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
    @CheckPermissions("system:care:add")
    public ResultVo addDict(@RequestBody Care care) {
        return careService.addCare(care);
    }

    @SysLog("删除关怀")
    @PostMapping("/del")
    @CheckPermissions("system:care:del")
    public ResultVo delDict(@RequestBody Care care) {
        return careService.deleteCare(care);
    }

    @SysLog("修改关怀")
    @PostMapping("/update")
    @CheckPermissions("system:care:update")
    public ResultVo editDict(@RequestBody Care care) {
        return careService.updateCare(care);
    }
    @GetMapping("/list")
    @CheckPermissions("system:care:index")
    public ResultVo listDict(Care care) {
        return careService.listCare(care);
    }


}
