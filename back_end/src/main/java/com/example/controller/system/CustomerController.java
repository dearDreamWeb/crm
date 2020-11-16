package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.CusConReq;
import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.entity.request.CustomerReq;
import com.example.service.CustomerService;
import com.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/10/21
 * @description:
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmpService empService;

    @SysLog("客户添加")
    @PostMapping("/add")
    public ResultVo addCustomer(@RequestBody CustomerReq customerReq,
                                HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return customerService.addCustomer(customerReq,token);
    }

    @SysLog("客户删除")
    @PostMapping("/del")
    public ResultVo delCustomer(@RequestBody CustomerReq customerReq) {
        return customerService.delCustomer(customerReq);
    }

    @SysLog("客户修改")
    @PostMapping("/edit")
    public ResultVo editCustomer(@RequestBody CustomerReq customerReq) {
        return customerService.editCustomer(customerReq);
    }

    @GetMapping
    public ResultVo getCustomer(@RequestParam("cusId")Integer cusId) {
        return customerService.getCustomer(cusId);
    }

    @GetMapping("/list")
    public ResultVo listCustomer(CustomerReq customerReq,
                                 HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return customerService.listCustomer(customerReq,token);
    }

    @PostMapping("/add_cus_con")
    public ResultVo addCusAndCon(@RequestBody CusConReq cusConReq, HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return customerService.addCusAndCon(cusConReq,token);
    }

    @GetMapping("/list_all")
    public ResultVo listAll() {
        return customerService.listAll();
    }
}
