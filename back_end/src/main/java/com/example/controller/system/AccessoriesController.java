package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Accessories;
import com.example.service.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuleimin
 * @date: 2020/11/6
 * @description:
 */
@RestController
@RequestMapping("/accessories")
public class AccessoriesController {
    @Autowired
    private AccessoriesService accessoriesService;

    @SysLog("添加")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Accessories accessories) {
        return accessoriesService.addAccessories(accessories);
    }

    @SysLog("删除")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Accessories accessories) {
        System.out.println("获取的参数是："+accessories);
        return accessoriesService.deleteAccessories(accessories.getAccessId());
    }

    @SysLog("修改")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Accessories accessories) {

        System.out.println("待膝盖的数据是："+accessories);
        return accessoriesService.updateAccessories(accessories);
    }

    @GetMapping
    public ResultVo getDict(@RequestParam("careId")Integer accessId) {
        return accessoriesService.getAccessories(accessId);
    }


    @GetMapping("/list")
    public ResultVo listDict( Accessories accessories) {
        return accessoriesService.listAccessories(accessories);
    }

}
