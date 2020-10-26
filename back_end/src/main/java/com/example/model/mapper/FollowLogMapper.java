package com.example.model.mapper;

import com.example.entity.request.FollowLogReq;
import com.example.entity.response.FollowLogResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Repository
public interface FollowLogMapper {
    int addFollow(FollowLogReq followLogReq);
    int delFollow(Integer followId);
    int editFollow(FollowLogReq followLogReq);
    FollowLogResp getFollow(Integer followId);
    List<FollowLogResp> listFollow(FollowLogReq followLogReq);
}
