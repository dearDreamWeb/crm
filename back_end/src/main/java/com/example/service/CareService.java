package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Care;
/**
 * @author: liuleimin
 * @date: 2020/10/10
 * @description:
 */
public interface CareService {
    ResultVo addCare(Care care);
    ResultVo updateCare(Care careReq);
    ResultVo deleteCare(Integer careId);
    ResultVo listCare(Care care);
    ResultVo getCare(Integer careId);
}
