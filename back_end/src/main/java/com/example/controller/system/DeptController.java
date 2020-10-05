package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.DeptReq;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @SysLog("添加部门")
    @PostMapping("/add")
    public ResultVo addDept(@RequestBody DeptReq deptReq) {
        return deptService.addDept(deptReq);
    }

    @SysLog("删除部门")
    @PostMapping("/del")
    public ResultVo delDept(@RequestBody DeptReq deptReq) {
        return deptService.delDept(deptReq);
    }

    @SysLog("编辑部门")
    @PostMapping("/edit")
    public ResultVo editDept(@RequestBody DeptReq deptReq) {
        return deptService.editDept(deptReq);
    }

    @GetMapping
    public ResultVo getDept(Integer deptId) {
        return deptService.getDept(deptId);
    }

    @GetMapping("/list")
    public ResultVo listDept(DeptReq deptReq) {
        return deptService.listDept(deptReq);
    }

    @GetMapping("/list_all")
    public ResultVo listAll(DeptReq deptReq) {
        return deptService.listDept(deptReq);
    }

    @SysLog("角色授权")
    @PostMapping("/auth")
    public ResultVo auth(DeptReq deptReq) {
        return deptService.auth(deptReq);
    }
}
