package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Qa;

/**
 * @author: liuleimin
 * @date: 2020/10/20
 * @description:
 */
public interface QaService {
    ResultVo addQa(Qa qa);
    ResultVo updateQa(Qa qaReq);
    ResultVo deleteQa(Integer qaId);
    ResultVo listQa(Qa qa);
    ResultVo getQa(Integer qaId);
}
