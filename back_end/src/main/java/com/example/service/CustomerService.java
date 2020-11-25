package com.example.service;

import com.example.entity.CusConReq;
import com.example.entity.ResultVo;
import com.example.entity.request.CustomerReq;

/**
 * @author: pengjia
 * @date: 2020/10/21
 * @description:
 */
public interface CustomerService {
    ResultVo addCustomer(CustomerReq customerReq,String token);
    ResultVo delCustomer(CustomerReq customerReq);
    ResultVo editCustomer(CustomerReq customerReq);
    ResultVo getCustomer(Integer cusId);
    ResultVo listCustomer(CustomerReq customerReq,String token);
    ResultVo addCusAndCon(CusConReq cusConReq,String token);
    ResultVo listAll();
    ResultVo insertCusAndCon(CusConReq cusConReq,String token);
}
