package com.example.service.impl;

import com.example.entity.ResultVo;
import com.example.entity.request.Maint;
import com.example.model.mapper.MaintMapper;
import com.example.service.MaintService;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/11/16
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaintServicelmpl implements MaintService {
    @Autowired
    private MaintMapper maintMapper;
    @Override
    public ResultVo getMaint(Integer maintId) {
        Maint maint = maintMapper.getMaint(maintId);
        return ResultUtils.response(maint);
    }
    @Override
    public ResultVo listMaint(Maint maint) {
        List<Maint> list = maintMapper.listMaint(maint);
        return ResultUtils.response(list);
    }
}
