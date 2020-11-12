package com.example.model.mapper;

import com.example.entity.response.ClueFollowLogResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/12
 * @description:
 */
@Repository
public interface ClueFollowLogMapper {
    int addClueFollow(ClueFollowLogResp followLogResp);
    List<ClueFollowLogResp> listClueFollow();
}
