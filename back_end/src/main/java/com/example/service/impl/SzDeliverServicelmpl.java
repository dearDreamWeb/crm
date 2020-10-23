package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
import com.example.model.mapper.SzDeliverMapper;
import com.example.service.SzDeliverService;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/20
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SzDeliverServicelmpl implements SzDeliverService {
    @Autowired
    private SzDeliverMapper szdeliverMapper;
    @Override
    public ResultVo addszDeliver(SzDeliver szdeliver) {
        System.out.println("【发货单】新增");
        int addszDeliver=szdeliverMapper.addszDeliver(szdeliver);
        if (addszDeliver!=1){
            throw new SysException(ResultEnum.Deliver_ADD_FAIL.getCode(),ResultEnum.Deliver_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addszDeliver);
    }

    @Override
    public ResultVo delszDeliver(SzDeliver szdeliver) {
        System.out.println("【发货单】删除");
        Integer delId = szdeliver.getDelId();
        int delszDeliver=szdeliverMapper.delszDeliver(delId);
        if (delszDeliver!=1){
            throw new SysException(ResultEnum.Deliver_DEL_FAIL.getCode(),ResultEnum.Deliver_DEL_FAIL.getMessage());
        }
        szdeliverMapper.delszDeliver(delId);
        return ResultUtils.response(delszDeliver);
    }

    @Override
    public ResultVo editszDeliver(SzDeliver szdeliver) {
        System.out.println("【发货单】修改");
        SzDeliver deptResp=szdeliverMapper.getszDeliver(szdeliver.getDelId());
        if (deptResp == null){
            throw new SysException(ResultEnum.Deliver_UPDATE_FAIL.getCode(),
                    ResultEnum.Deliver_UPDATE_FAIL.getMessage());
        }
        int editszDeliver = szdeliverMapper.editszDeliver(szdeliver);
        if (editszDeliver != 1){
            throw new SysException(ResultEnum.Deliver_UPDATE_FAIL.getCode(),
                    ResultEnum.Deliver_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editszDeliver);
    }

    @Override
    public ResultVo getszDeliver(Integer delId) {
        System.out.println("【发货单】单条");
        SzDeliver szdeliver=szdeliverMapper.getszDeliver(delId);
        return ResultUtils.response(szdeliver);
    }

    @Override
    public ResultVo listszDeliver(SzDeliver szdeliver) {
        System.out.println("【订单】全部全部...");
        Integer pageNum = szdeliver.getPageNum();
        Integer pageSize = szdeliver.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SzDeliver> deptResps = szdeliverMapper.listszDeliver(szdeliver);
        PageInfo<SzDeliver> list = new PageInfo<>(deptResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo addDelANDOrd(SzDeliver szDeliver) {
        szDeliver.setDelDelivertime(DateUtils.getDate());
        szDeliver.setDelState(1);
        int addDelANDOrd = szdeliverMapper.addDelANDOrd(szDeliver);
        return  ResultUtils.response(addDelANDOrd);
    }
}
