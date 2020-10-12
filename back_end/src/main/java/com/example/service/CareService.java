package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Care;

public interface CareService {
    ResultVo addCare(Care care);
    ResultVo updateCare(Care care);
    ResultVo deleteCare(Care care);
    ResultVo listCare(Care care);
    ResultVo getCare(Integer careId);
}
