package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.EmpReq;
import com.example.service.EmpService;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageInfo;
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

    @SysLog("添加用户")
    @PostMapping("/add")
    @CheckPermissions("system:emp:add")
    public ResultVo addEmp(@RequestBody EmpReq empReq) {
        return empService.addEmp(empReq);
    }

    @SysLog("删除用户")
    @PostMapping("/del")
    @CheckPermissions("system:emp:del")
    public ResultVo delEmp(@RequestBody EmpReq empReq) {
        return empService.delEmp(empReq);
    }

    @SysLog("编辑用户")
    @PostMapping("/edit")
    @CheckPermissions("system:emp:edit")
    public ResultVo editEmp(@RequestBody EmpReq empReq) {
        return empService.editEmp(empReq);
    }

    @GetMapping
    public ResultVo getEmp(@RequestParam("empId")Integer empId) {
        return empService.getEmp(empId);
    }

    @GetMapping("/list")
//    @CheckPermissions("system:emp:index")
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

    @GetMapping("/list_not_auth")
    public ResultVo listNotAuth(@RequestParam("activityId")Integer activityId) {
        return empService.listNotAuthActivity(activityId);
    }

    @PostMapping("/logout")
    public ResultVo logout(@RequestParam("empName")String empName) {
        return empService.logout(empName);
    }

    @GetMapping("/get_personal")
    public ResultVo getEmpByPersonal(@RequestParam("empId")Integer empId) {
        return empService.getEmpByPersonal(empId);
    }
}
