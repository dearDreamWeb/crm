package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.Repair;
import com.example.model.mapper.RepairMapper;
import com.example.service.RepairService;
import com.example.util.CheckUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public ResultVo addRepair(Repair repair) {
        CheckUtils.validate(repair);
        int addRepair = repairMapper.addRepair(repair);
        if (addRepair != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addRepair);
    }

    @Override
    public ResultVo deleteRepair(Integer repairId) {
        Repair repair = repairMapper.getRepair(repairId);
        if (repair == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int deleteRepair = repairMapper.deleteRepair(repairId);
        if (deleteRepair != 1) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(deleteRepair);
    }

    @Override
    public ResultVo updateRepair(Repair repairReq) {
        Repair repair = repairMapper.getRepair(repairReq.getRepairId());
        if (repair == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int updateRepair = repairMapper.updateRepair(repairReq);
        if (updateRepair != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(updateRepair);
    }

    @Override
    public ResultVo listRepair(Repair repair) {
        Integer pageNum = repair.getPageNum();
        Integer pageSize = repair.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Repair> repairs = repairMapper.listRepair(repair);
        PageInfo<Repair> list = new PageInfo<>(repairs);
        return ResultUtils.response(list);
    }
    @Override
    public ResultVo getRepair(Integer repairId) {
        Repair repair = repairMapper.getRepair(repairId);
        return ResultUtils.response(repair);
    }
}
