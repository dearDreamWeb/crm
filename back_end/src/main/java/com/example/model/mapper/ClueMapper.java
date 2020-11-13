package com.example.model.mapper;

import com.example.entity.request.ClueReq;
import com.example.entity.response.ClueResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    List<ClueResp> listClueByEmpId(@Param("clueReq")ClueReq clueReq,
                                   @Param("empName")String empName);
    int batchEditClueType(ClueReq clueReq);
    List<ClueResp> listCompany();
    int shareClue(@Param("clueId") Integer clueId, @Param("updateTime") Date updateTime);
    int invalidClue(@Param("clueId") Integer clueId, @Param("updateTime") Date updateTime);
}
