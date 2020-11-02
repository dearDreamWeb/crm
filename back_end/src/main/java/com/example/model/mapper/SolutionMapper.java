package com.example.model.mapper;

import com.example.entity.request.SolutionReq;
import com.example.entity.response.SolutionResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Repository
public interface SolutionMapper {
    int addSolution(SolutionReq solutionReq);
    int delSolution(Integer solutionId);
    int editSolution(SolutionReq solutionReq);
    SolutionResp getSolution(Integer solutionId);
    List<SolutionResp> listSolution(SolutionReq solutionReq);
}
