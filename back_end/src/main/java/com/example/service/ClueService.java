package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;

/**
 * @author: pengjia
 * @date: 2020/10/12
 * @description:
 */
public interface ClueService {
    ResultVo addClue(ClueReq clueReq);
    ResultVo delClue(ClueReq clueReq);
    ResultVo editClue(ClueReq clueReq);
    ResultVo getClue(Integer clueId);
    ResultVo listClue(ClueReq clueReq);
}
