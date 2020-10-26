package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.SysCredit;

/**
 * @author: pengjia
 * @date: 2020/10/24
 * @description:
 */
public interface SysCreditService {
    ResultVo addCredit(SysCredit sysCredit);
    ResultVo delCredit(Integer creditId);
    ResultVo editCredit(SysCredit sysCredit);
    ResultVo listCredit();
}
