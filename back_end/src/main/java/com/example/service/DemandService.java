package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.DemandReq;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
public interface DemandService {
    ResultVo addDemand(DemandReq demandReq);
    ResultVo delDemand(DemandReq demandReq);
    ResultVo editDemand(DemandReq demandReq);
    ResultVo getDemand(DemandReq demandReq);
    ResultVo listDemand(DemandReq demandReq);
}
