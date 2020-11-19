package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SolutionReq;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
public interface SolutionService {
    ResultVo addSolution(SolutionReq solutionReq,String token);
    ResultVo delSolution(SolutionReq solutionReq);
    ResultVo editSolution(SolutionReq solutionReq);
    ResultVo getSolution(SolutionReq solutionReq);
    ResultVo listSolution(SolutionReq solutionReq);
    ResultVo listByCus(SolutionReq solutionReq);
}
