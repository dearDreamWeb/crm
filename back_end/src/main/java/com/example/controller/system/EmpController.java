package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.entity.request.EmpReq;
import com.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("/add")
    public ResultVo addEmp(EmpReq empReq) {
        return empService.addEmp(empReq);
    }

    @PostMapping("/del")
    public ResultVo delEmp(EmpReq empReq) {
        return empService.delEmp(empReq);
    }

    @PostMapping("/edit")
    public ResultVo editEmp(EmpReq empReq) {
        return empService.editEmp(empReq);
    }

    @GetMapping
    public ResultVo getEmp(@RequestParam("empId")Integer empId) {
        return empService.getEmp(empId);
    }

    @GetMapping("/list")
    public ResultVo listEmp(EmpReq empReq) {
        return empService.listEmp(empReq);
    }

    @PostMapping("/login")
    public ResultVo login(@RequestBody EmpReq empReq) {
        return empService.login(empReq);
    }/*预估phone字段映射类型*/

    @GetMapping("/menu")
    public ResultVo getShowMenuByEmpId(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return empService.getShowMenuByToken(token);
    }

    @PostMapping("/pwd")
    public ResultVo pwd(EmpReq empReq) {
        return empService.pwd(empReq);
    }
}
