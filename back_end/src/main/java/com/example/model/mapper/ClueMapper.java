package com.example.model.mapper;

import com.example.entity.request.ClueReq;
import com.example.entity.response.ClueResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/12
 * @description:
 */
@Repository
public interface ClueMapper {
    int addClue(ClueReq clueReq);
    int delClue(Integer clueId);
    int editClue(ClueReq clueReq);
    ClueResp getClue(Integer clueId);
    List<ClueResp> listClue(ClueReq clueReq);
}
