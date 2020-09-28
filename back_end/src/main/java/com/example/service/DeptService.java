package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.DeptReq;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
public interface DeptService {
    ResultVo addDept(DeptReq deptReq);
    ResultVo delDept(DeptReq deptReq);
    ResultVo editDept(DeptReq deptReq);
    ResultVo getDept(Integer deptId);
    ResultVo listDept();
    ResultVo auth(DeptReq deptReq);
}
