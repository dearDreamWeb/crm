package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SzReceivableRecord;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/11/5
 * @description:
 */
//回款记录
public interface SzReceivableRecordService {
    List<SzReceivableRecord> listPlanANDReco(Integer recoId);
    ResultVo getszReco(Integer recoId);
    ResultVo editszRecoHas(SzReceivableRecord record);
}
