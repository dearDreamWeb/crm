package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Repair;

/**
 * @author: liuleimin
 * @date: 2020/10/29
 * @description:
 */
public interface RepairService {
    ResultVo addRepair(Repair repair);
    ResultVo updateRepair(Repair repairReq);
    ResultVo deleteRepair(Integer repairId);
    ResultVo listRepair (Repair repair );
    ResultVo getRepair (Integer repairId);
}
