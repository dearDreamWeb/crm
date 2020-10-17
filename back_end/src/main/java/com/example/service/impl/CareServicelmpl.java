package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.Care;

import com.example.model.mapper.CareMapper;

import com.example.service.CareService;
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
 * @author: liuleimin
 * @date: 2020/10/12
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CareServicelmpl implements CareService {
    @Autowired
    private CareMapper careMapper;

    @Override
    public ResultVo addCare(Care care) {
        CheckUtils.validate(care);
        int addCare = careMapper.addCare(care);
        if (addCare != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addCare);
    }

    @Override
    public ResultVo deleteCare(Integer careId) {
        Care care = careMapper.getCare(careId);
        if (care == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int deleteCare = careMapper.deleteCare(careId);
        if (deleteCare != 1) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(deleteCare);
    }

    @Override
    public ResultVo listCare(Care care) {
        Integer pageNum = care.getPageNum();
        Integer pageSize = care.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Care> empResps = careMapper.listCare(care);
        PageInfo<Care> list = new PageInfo<>(empResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo getCare(Integer careId) {
        Care care = careMapper.getCare(careId);
        return ResultUtils.response(care);
    }

    @Override
    public ResultVo updateCare(Care careReq) {
        Care care = careMapper.getCare(careReq.getCareId());
        if (care == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
//        care.setCarelxrcontacts(care.getCarelxrcontacts());
//        care.setCareData(DateUtils.getDate());
        int updateCare = careMapper.updateCare(careReq);
        if (updateCare != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(updateCare);
    }
}

