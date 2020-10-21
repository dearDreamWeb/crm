package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.entity.request.Syslog;
import com.example.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liuleimin
 * @date: 2020/10/21
 * @description:
 */
@RestController
@RequestMapping("/syslog")
public class SyslogController {
    @Autowired
    private SyslogService syslogService;

    @GetMapping
    public ResultVo getSyslog(@RequestParam("logId")Integer logId) {
        return syslogService.getSyslog(logId);
    }

    @GetMapping("/list")
    public ResultVo listDict(Syslog syslog) {
        return syslogService.listSyslog(syslog);
    }
}
