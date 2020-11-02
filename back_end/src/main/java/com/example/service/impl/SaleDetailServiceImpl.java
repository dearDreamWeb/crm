package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SaleDetailReq;
import com.example.entity.response.SaleDetailResp;
import com.example.model.mapper.SaleDetailMapper;
import com.example.service.SaleDetailService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
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
public class SaleDetailServiceImpl implements SaleDetailService {
    @Autowired
    private SaleDetailMapper detailMapper;

    @Override
    public ResultVo addSaleDetail(SaleDetailReq saleDetailReq) {
        CheckUtils.validate(saleDetailReq);
        int addSaleDetail = detailMapper.addSaleDetail(saleDetailReq);
        if (addSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addSaleDetail);
    }

    @Override
    public ResultVo delSaleDetail(SaleDetailReq saleDetailReq) {
        Integer saleDetailId = saleDetailReq.getSaleDetailId();
        if (saleDetailId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delSaleDetail = detailMapper.delSaleDetail(saleDetailId);
        if (delSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delSaleDetail);
    }

    @Override
    public ResultVo editSaleDetail(SaleDetailReq saleDetailReq) {
        Integer saleDetailId = saleDetailReq.getSaleDetailId();
        if (saleDetailId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        SaleDetailResp saleDetail = detailMapper.getSaleDetail(saleDetailId);
        if (saleDetail == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        saleDetailReq.setVersion(saleDetail.getVersion());
        saleDetailReq.setUpdateTime(DateUtils.getDate());
        int editSaleDetail = detailMapper.editSaleDetail(saleDetailReq);
        if (editSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editSaleDetail);
    }

    @Override
    public ResultVo getSaleDetail(SaleDetailReq saleDetailReq) {
        Integer saleDetailId = saleDetailReq.getSaleDetailId();
        SaleDetailResp saleDetail = detailMapper.getSaleDetail(saleDetailId);
        if (saleDetail == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(saleDetail);
    }

    @Override
    public ResultVo listSaleDetail(SaleDetailReq saleDetailReq) {
        List<SaleDetailResp> saleDetailResps = detailMapper.listSaleDetail(saleDetailReq);
        return ResultUtils.response(saleDetailResps);
    }
}
