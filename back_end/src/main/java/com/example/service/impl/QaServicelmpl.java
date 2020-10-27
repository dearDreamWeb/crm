package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.Qa;
import com.example.model.mapper.QaMapper;
import com.example.service.QaService;
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
 * @date: 2020/10/20
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QaServicelmpl implements QaService {
    @Autowired
    private QaMapper qaMapper;

    @Override
    public ResultVo addQa(Qa qa) {
        CheckUtils.validate(qa);
        qa.setQaData(DateUtils.getDate());
        int addQa = qaMapper.addQa(qa);

        if (addQa != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addQa);
    }

    @Override
    public ResultVo deleteQa(Integer qaId) {
        Qa qa = qaMapper.getQa(qaId);
        if (qa == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int deleteQa = qaMapper.deleteQa(qaId);
        if (deleteQa != 1) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(deleteQa);
    }

    @Override
    public ResultVo listQa(Qa qa) {
        Integer pageNum = qa.getPageNum();
        Integer pageSize = qa.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Qa> empResps = qaMapper.listQa(qa);
        PageInfo<Qa> list = new PageInfo<>(empResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo getQa(Integer qaId) {
        Qa qa = qaMapper.getQa(qaId);
        return ResultUtils.response(qa);
    }

    @Override
    public ResultVo updateQa(Qa qaReq) {
        Qa qa = qaMapper.getQa(qaReq.getQaId());
        if (qa == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int updateQa = qaMapper.updateQa(qaReq);
        if (updateQa != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(updateQa);
    }
}
