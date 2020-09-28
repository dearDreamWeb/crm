package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.entity.request.DeptReq;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public ResultVo addDept(DeptReq deptReq) {
        return deptService.addDept(deptReq);
    }

    @PostMapping("/del")
    public ResultVo delDept(DeptReq deptReq) {
        return deptService.delDept(deptReq);
    }

    @PostMapping("/edit")
    public ResultVo editDept(DeptReq deptReq) {
        return deptService.editDept(deptReq);
    }

    @GetMapping
    public ResultVo getDept(Integer deptId) {
        return deptService.getDept(deptId);
    }

    @GetMapping("/list")
    public ResultVo listDept() {
        return deptService.listDept();
    }

    @PostMapping("/auth")
    public ResultVo auth(DeptReq deptReq) {
        return deptService.auth(deptReq);
    }
}
