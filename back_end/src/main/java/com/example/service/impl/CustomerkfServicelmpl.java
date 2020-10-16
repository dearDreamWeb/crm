package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;

import com.example.entity.request.Customerkf;
import com.example.model.mapper.CustomerkfMapper;
import com.example.service.CustomerkfService;
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
 * @date: 2020/10/14
 * @description:
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerkfServicelmpl implements CustomerkfService {
    @Autowired
    private CustomerkfMapper customerkfMapper;

    @Override
    public ResultVo addCustomerkf(Customerkf customerkf) {
        CheckUtils.validate(customerkf);
        int addCustomerkf = customerkfMapper.addCustomerkf(customerkf);
        if (addCustomerkf != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addCustomerkf);
    }


    @Override
    public ResultVo deleteCustomerkf(Customerkf CustomerkfReq) {
        Customerkf customerkf = customerkfMapper.getCustomerkf(CustomerkfReq.getCustomerId());
        if (customerkf == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int deleteCustomerkf = customerkfMapper.deleteCustomerkf(customerkf.getCustomerId());
        if (deleteCustomerkf != 1) {
            throw new SysException(ResultEnum.DICT_DEL_FAIL.getCode(),
                    ResultEnum.DICT_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(deleteCustomerkf);
    }

    @Override
    public ResultVo listCustomerkf(Customerkf customerkf) {
        Integer pageNum = customerkf.getPageNum();
        Integer pageSize = customerkf.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Customerkf> empResps = customerkfMapper.listCustomerkf(customerkf);
        PageInfo<Customerkf> list = new PageInfo<>(empResps);
        return ResultUtils.response(list);
    }

    @Override

    public ResultVo getCustomerkf(Integer customerId) {
        Customerkf customerkf = customerkfMapper.getCustomerkf(customerId);
        return ResultUtils.response(customerkf);
    }



    @Override
    public ResultVo updateCustomerkf(Customerkf CustomerkfReq) {
        Customerkf customerkf = customerkfMapper.getCustomerkf(CustomerkfReq.getCustomerId());
        if (customerkf == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        customerkf.setCustomerCustomerfk(customerkf.getCustomerCustomerfk());
        customerkf.setCustomerData(DateUtils.getDate());
        int updateCustomerkf = customerkfMapper.updateCustomerkf(customerkf);
        if (updateCustomerkf != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(updateCustomerkf(customerkf));
    }


}

