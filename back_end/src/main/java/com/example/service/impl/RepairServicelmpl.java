package com.example.service.impl;

import com.example.entity.ResultVo;
import com.example.entity.request.Repair;
import com.example.model.mapper.RepairMapper;
import com.example.service.RepairService;
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
