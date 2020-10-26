package com.example.controller;

import com.example.entity.ResultVo;
import com.example.service.SysCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/10/24
 * @description:
 */
@RestController
@RequestMapping("/sys_credit")
public class SysCreditController {
    @Autowired
    private SysCreditService creditService;

    @GetMapping("/list")
    public ResultVo listCredit() {
        return creditService.listCredit();
    }
}
