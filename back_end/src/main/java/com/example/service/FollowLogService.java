package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.FollowLogReq;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
public interface FollowLogService {
    ResultVo addFollow(FollowLogReq followLogReq);
    ResultVo delFollow(FollowLogReq followLogReq);
    ResultVo editFollow(FollowLogReq followLogReq);
    ResultVo getFollow(Integer followId);
    ResultVo listFollow(FollowLogReq followLogReq);
}
