package com.example.service;

import com.example.entity.Grading;
import com.example.entity.Qualitative;
import com.example.entity.Ration;
import com.example.entity.ResultVo;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description: 定性，定级，定量；三定接口
 */
public interface SanYiKeService {
    ResultVo addRation(Ration ration,String token);
    ResultVo addGrading(Grading grading,String token);
    ResultVo addQualitative(Qualitative qualitative,String token);
}
