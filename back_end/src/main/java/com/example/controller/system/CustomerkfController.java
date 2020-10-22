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

    @SysLog("添加")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Customerkf customerkf) {
        return customerkfService.addCustomerkf(customerkf);
    }

    @SysLog("删除")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Customerkf customerkf) {
        return customerkfService.deleteCustomerkf(customerkf.getCustomerId());
    }

    @SysLog("修改")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Customerkf customerkf) {
        return customerkfService.updateCustomerkf(customerkf);
    }

    @GetMapping
    public ResultVo getDict(@RequestParam("customerId")Integer customerId) {
        return customerkfService.getCustomerkf(customerId);
    }
    @GetMapping("/list")
    public ResultVo listDict(Customerkf customerkf) {
        return customerkfService.listCustomerkf(customerkf);
    }
}
