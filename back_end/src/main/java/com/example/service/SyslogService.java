package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Syslog;

/**
 * @author: liuleimin
 * @date: 2020/10/21
 * @description:
 */
public interface SyslogService {
    ResultVo listSyslog(Syslog syslog);
    ResultVo getSyslog(Integer logId);
}
