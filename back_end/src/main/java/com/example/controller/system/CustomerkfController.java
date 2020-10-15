package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Customerkf;
import com.example.service.CustomerkfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */
@RestController
@RequestMapping("/customerkf")
public class CustomerkfController {
    @Autowired
    private CustomerkfService customerkfService;

    @SysLog("添加关怀")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Customerkf customerkf) {
        return customerkfService.addCustomerkf(customerkf);
    }

    @SysLog("删除关怀")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Customerkf customerkf) {
        return customerkfService.deleteCustomerkf(customerkf);
    }

    @SysLog("修改关怀")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Customerkf customerkf) {
        return customerkfService.updateCustomerkf(customerkf);
    }

    @GetMapping("/list")
    public ResultVo listDict(Customerkf customerkf) {
        return customerkfService.listCustomerkf(customerkf);
    }
}
