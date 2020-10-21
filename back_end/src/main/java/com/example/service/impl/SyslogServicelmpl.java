package com.example.service.impl;

import com.example.entity.ResultVo;

import com.example.entity.request.Syslog;
import com.example.entity.response.EmpResp;

import com.example.model.mapper.SyslogMapper;
import com.example.service.SyslogService;
import com.example.util.ResultUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/21
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SyslogServicelmpl implements SyslogService {
    @Autowired
    private SyslogMapper syslogMapper;

    @Override
    public ResultVo getSyslog(Integer logId) {
        Syslog syslog = syslogMapper.getSyslog(logId);
        EmpResp emp = syslog.getEmp();
        Integer empId = emp.getEmpId();
        syslog.setUserId(empId);
        //服务器向客户端发送JSON：测试
        Gson gson = new Gson();
        String json = gson.toJson(syslog);

        return ResultUtils.response(syslog);
    }
    @Override
    public ResultVo listSyslog(Syslog syslog) {

        List<Syslog> list = syslogMapper.listSyslog(syslog);
        return ResultUtils.response(list);
    }
}
