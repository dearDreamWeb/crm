package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;
import com.example.model.mapper.SzOrderMapper;
import com.example.service.SzOrderService;
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
 * @date: 2020/10/10
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SzOrderServicelmpl implements SzOrderService {
    @Autowired
    private SzOrderMapper szorderMapper;

    @Override
    public ResultVo addszOrder(SzOrder szorder) {
        System.out.println("【订单】新增新增");
        szorder.setOrdDelete(0);/*新增时该字段默认为0*/
        int addszOrder=szorderMapper.addszOrder(szorder);
        if (addszOrder!=1){
            throw new SysException(ResultEnum.ORDER_ADD_FAIL.getCode(),ResultEnum.ORDER_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addszOrder);
    }

    @Override
    public ResultVo delszOrder(SzOrder szorder) {
        System.out.println("【订单】删除删除");
        Integer ordId = szorder.getOrdId();
        int delszOrder=szorderMapper.delszOrder(ordId);
        if (delszOrder!=1){
            throw new SysException(ResultEnum.ORDER_DEL_FAIL.getCode(),ResultEnum.ORDER_DEL_FAIL.getMessage());
        }
        szorderMapper.delszOrder(ordId);
        return ResultUtils.response(delszOrder);
    }
    @Override
    public ResultVo editszOrder(SzOrder szorder) {
        System.out.println("【订单】修改修改...");
        SzOrder deptResp = szorderMapper.getszOrder(szorder.getOrdId());
        if (deptResp == null) {
            throw new SysException(ResultEnum.ORDER_UPDATE_FAIL.getCode(),
                    ResultEnum.ORDER_UPDATE_FAIL.getMessage());
        }
        /*szorder.setOrdStarttime(DateUtils.getDate());*/
        int editszOrder = szorderMapper.editszOrder(szorder);
        if (editszOrder != 1) {
            throw new SysException(ResultEnum.ORDER_UPDATE_FAIL.getCode(),
                    ResultEnum.ORDER_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editszOrder);
    }

    @Override
    public ResultVo getszOrder(Integer ordId) {
        System.out.println("【订单】单条单条...");
        SzOrder szorder=szorderMapper.getszOrder(ordId);
        return ResultUtils.response(szorder);
    }
    @Override
    public ResultVo listszOrder(SzOrder szorder) {
        System.out.println("【订单】全部全部...");
        Integer pageNum = szorder.getPageNum();
        Integer pageSize = szorder.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SzOrder> deptResps = szorderMapper.listszOrder(szorder);
        PageInfo<SzOrder> list = new PageInfo<>(deptResps);
        return ResultUtils.response(list);
    }

    @Override
    public List<SzOrder> OrdANDDel() {
        return szorderMapper.OrdANDDel();
    }
}
