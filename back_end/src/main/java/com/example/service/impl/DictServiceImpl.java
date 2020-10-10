package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.DictReq;
import com.example.entity.response.DictResp;
import com.example.entity.response.MenuResp;
import com.example.model.mapper.DictMapper;
import com.example.service.DictService;
import com.example.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/10
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public ResultVo addDict(DictReq dictReq) {
        CheckUtils.validate(dictReq);
        int count = dictMapper.checkDict(dictReq.getDictCode());
        if (count != 0) {
            throw new SysException(ResultEnum.DICT_EXIST.getCode(),
                    ResultEnum.DICT_EXIST.getMessage());
        }
        Integer pid = dictReq.getPid();
        if (pid == null) {
            pid = 0;
        }
        dictReq.setPid(pid);
        int addDict = dictMapper.addDict(dictReq);
        if (addDict != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addDict);
    }

    @Override
    public ResultVo delDict(DictReq dictReq) {
        DictResp dict = dictMapper.getDict(dictReq.getDictId());
        if (dict == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int delDict = dictMapper.delDict(dictReq.getDictId());
        if (delDict != 1) {
            throw new SysException(ResultEnum.DICT_DEL_FAIL.getCode(),
                    ResultEnum.DICT_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delDict);
    }

    @Override
    public ResultVo editDict(DictReq dictReq) {
        DictResp dict = dictMapper.getDict(dictReq.getDictId());
        if (dict == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        dictReq.setVersion(dict.getVersion());
        dictReq.setUpdateTime(DateUtils.getDate());
        int editDict = dictMapper.editDict(dictReq);
        if (editDict != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editDict);
    }

    @Override
    public ResultVo getDict(Integer dictId) {
        DictResp dict = dictMapper.getDict(dictId);
        return ResultUtils.response(dict);
    }

    @Override
    public ResultVo listDict(DictReq dictReq) {
        Integer pageNum = dictReq.getPageNum();
        Integer pageSize = dictReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<DictResp> dictResps = dictMapper.listDict(dictReq);
        PageInfo<DictResp> list = new PageInfo<>(dictResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo listPidDict(DictReq dictReq) {
        List<DictResp> dictResps = dictMapper.listPidDict(dictReq);
        return ResultUtils.response(dictResps);
    }

    @Override
    public ResultVo treeDict(DictReq dictReq) {
        List<DictResp> dictResps = dictMapper.listDict(dictReq);
        dictResps = TreeUtils.listToDictTree(dictResps);
        return ResultUtils.response(dictResps);
    }

}
