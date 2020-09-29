package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.EmpReq;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
public interface EmpService {
    ResultVo addEmp(EmpReq empReq);
    ResultVo delEmp(EmpReq empReq);
    ResultVo editEmp(EmpReq empReq);
    ResultVo getEmp(Integer empId);
    ResultVo listEmp(EmpReq empReq,Integer pageNum,Integer pageSize);
    ResultVo login(EmpReq empReq);
    ResultVo getShowMenuByToken(String token);
    ResultVo pwd(EmpReq empReq);
    ResultVo logout(EmpReq empReq);
}