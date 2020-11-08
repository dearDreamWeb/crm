package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.Accessories;
import com.example.model.mapper.AccessoriesMapper;
import com.example.service.AccessoriesService;
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
 * @date: 2020/11/6
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccessoriesServicelmpl implements AccessoriesService {
    @Autowired
    private AccessoriesMapper accessoriesMapper;

    @Override
    public ResultVo addAccessories(Accessories accessories) {
        CheckUtils.validate(accessories);
        int addAccessories = accessoriesMapper.addAccessories(accessories);
        if (addAccessories != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addAccessories);
    }

    @Override
    public ResultVo deleteAccessories(Integer accessId) {
        Accessories accessories = accessoriesMapper.getAccessories(accessId);
        if (accessories == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int deleteAccessories = accessoriesMapper.deleteAccessories(accessId);
        if (deleteAccessories != 1) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(deleteAccessories);
    }

    @Override
    public ResultVo listAccessories(Accessories accessories) {
        Integer pageNum = accessories.getPageNum();
        Integer pageSize = accessories.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Accessories> cares = accessoriesMapper.listAccessories(accessories);
        PageInfo<Accessories> list = new PageInfo<>(cares);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo getAccessories(Integer accessId) {
        Accessories accessories = accessoriesMapper.getAccessories(accessId);
        return ResultUtils.response(accessories);
    }

    @Override
    public ResultVo updateAccessories(Accessories accessoriesReq) {
        Accessories accessories = accessoriesMapper.getAccessories(accessoriesReq.getAccessId());
        if (accessories == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int updateAccessories = accessoriesMapper.updateAccessories(accessoriesReq);
        if (updateAccessories != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(updateAccessories);
    }
}
