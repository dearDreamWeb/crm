package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.DictReq;
import com.example.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/10/10
 * @description:
 */
@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @SysLog("添加字典")
    @PostMapping("/add")
    @CheckPermissions("system:dict:add")
    public ResultVo addDict(@RequestBody DictReq dictReq) {
        return dictService.addDict(dictReq);
    }

    @SysLog("删除字典")
    @PostMapping("/del")
    @CheckPermissions("system:dict:del")
    public ResultVo delDict(@RequestBody DictReq dictReq) {
        return dictService.delDict(dictReq);
    }

    @SysLog("修改字典")
    @PostMapping("/edit")
    @CheckPermissions("system:dict:edit")
    public ResultVo editDict(@RequestBody DictReq dictReq) {
        return dictService.editDict(dictReq);
    }

    @GetMapping
    public ResultVo getDict(@RequestParam("dictId")Integer dictId) {
        return dictService.getDict(dictId);
    }

    @GetMapping("/list")
    @CheckPermissions("system:dict:index")
    public ResultVo listDict(DictReq dictReq) {
        return dictService.listDict(dictReq);
    }

    @GetMapping("/tree")
    public ResultVo treeDict(DictReq dictReq) {
        return dictService.treeDict(dictReq);
    }

    @GetMapping("/get_pid")
    public ResultVo getPid(DictReq dictReq) {
        return dictService.listPidDict(dictReq);
    }

    @GetMapping("/tree_get")
    public ResultVo treeById(@RequestParam("dictId")Integer dictId) {
        return dictService.treeDictById(dictId);
    }
}
