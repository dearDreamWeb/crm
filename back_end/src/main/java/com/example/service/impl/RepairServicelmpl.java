package com.example.service.impl;

import com.example.entity.ResultVo;
import com.example.entity.request.Repair;
import com.example.model.mapper.RepairMapper;
import com.example.service.RepairService;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: liuleimin
 * @date: 2020/10/29
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RepairServicelmpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;

    @Override
    public ResultVo listRepair(Repair repair) {
        return null;
    }

    @Override
    public ResultVo getRepair(Integer repairId) {
        Repair repair = repairMapper.getRepair(repairId);
        return ResultUtils.response(repair);
    }
}
