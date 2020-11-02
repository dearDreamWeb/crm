package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.DemandReq;
import com.example.entity.response.DemandResp;
import com.example.model.mapper.DemandMapper;
import com.example.service.DemandService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemandServiceImpl implements DemandService {
    @Autowired
    private DemandMapper demandMapper;

    @Override
    public ResultVo addDemand(DemandReq demandReq) {
        CheckUtils.validate(demandReq);
        int addDemand = demandMapper.addDemand(demandReq);
        if (addDemand != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addDemand);
    }

    @Override
    public ResultVo delDemand(DemandReq demandReq) {
        Integer demandId = demandReq.getDemandId();
        DemandResp demand = demandMapper.getDemand(demandId);
        if (demand == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delDemand = demandMapper.delDemand(demandId);
        if (delDemand != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delDemand);
    }

    @Override
    public ResultVo editDemand(DemandReq demandReq) {
        Integer demandId = demandReq.getDemandId();
        DemandResp demand = demandMapper.getDemand(demandId);
        if (demand == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        demandReq.setVersion(demand.getVersion());
        demandReq.setUpdateTime(DateUtils.getDate());
        int editDemand = demandMapper.editDemand(demandReq);
        if (editDemand != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editDemand);
    }

    @Override
    public ResultVo getDemand(DemandReq demandReq) {
        Integer demandId = demandReq.getDemandId();
        DemandResp demand = demandMapper.getDemand(demandId);
        if (demand == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(demand);
    }

    @Override
    public ResultVo listDemand(DemandReq demandReq) {
        Integer pageNum = demandReq.getPageNum();
        Integer pageSize = demandReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<DemandResp> demandResps = demandMapper.listDemand(demandReq);
        PageInfo<DemandResp> list = new PageInfo<>(demandResps);
        return ResultUtils.response(list);
    }
}
