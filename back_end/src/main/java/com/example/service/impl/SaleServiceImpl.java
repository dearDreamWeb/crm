package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SaleReq;
import com.example.entity.response.SaleResp;
import com.example.model.mapper.SaleMapper;
import com.example.service.SaleService;
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
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleMapper saleMapper;

    @Override
    public ResultVo addSale(SaleReq saleReq) {
        CheckUtils.validate(saleReq);
        int addSale = saleMapper.addSale(saleReq);
        if (addSale != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addSale);
    }

    @Override
    public ResultVo delSale(SaleReq saleReq) {
        Integer saleId = saleReq.getSaleId();
        if (saleId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delSale = saleMapper.delSale(saleId);
        if (delSale != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delSale);
    }

    @Override
    public ResultVo editSale(SaleReq saleReq) {
        Integer saleId = saleReq.getSaleId();
        if (saleId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        SaleResp sale = saleMapper.getSale(saleId);
        if (sale == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        saleReq.setVersion(sale.getVersion());
        saleReq.setUpdateTime(DateUtils.getDate());
        int editSale = saleMapper.editSale(saleReq);
        if (editSale != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editSale);
    }

    @Override
    public ResultVo getSale(SaleReq saleReq) {
        Integer saleId = saleReq.getSaleId();
        SaleResp sale = saleMapper.getSale(saleId);
        if (sale == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(sale);
    }

    @Override
    public ResultVo listSale(SaleReq saleReq) {
        Integer pageNum = saleReq.getPageNum();
        Integer pageSize = saleReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SaleResp> saleResps = saleMapper.listSale(saleReq);
        PageInfo<SaleResp> list = new PageInfo<>(saleResps);
        return ResultUtils.response(list);
    }
}
