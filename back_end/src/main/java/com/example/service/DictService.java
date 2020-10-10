package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.DictReq;

/**
 * @author: pengjia
 * @date: 2020/10/10
 * @description:
 */
public interface DictService {
    ResultVo addDict(DictReq dictReq);
    ResultVo delDict(DictReq dictReq);
    ResultVo editDict(DictReq dictReq);
    ResultVo getDict(Integer dictId);
    ResultVo listDict(DictReq dictReq);
}
