package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.szOrder;
import com.example.model.mapper.szOrderMapper;
import com.example.service.szOrderService;
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
public class szOrderServicelmpl implements szOrderService {
    @Autowired
    private szOrderMapper szorderMapper;

    @Override
    public ResultVo addszOrder(szOrder szorder) {
        System.out.println("【订单】新增新增");
        int addszOrder=szorderMapper.addszOrder(szorder);
        if (addszOrder!=1){
            throw new SysException(ResultEnum.ORDER_ADD_FAIL.getCode(),ResultEnum.ORDER_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addszOrder);
    }

    @Override
    public ResultVo delszOrder(szOrder szorder) {
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
    public ResultVo editszOrder(szOrder szorder) {
        System.out.println("【订单】修改修改...");
        int editszOrder=szorderMapper.editszOrder(szorder);
        return null;
    }

    @Override
    public ResultVo queryszOrderById(Integer ordId) {
        System.out.println("【订单】单条单条...");
        /*Integer ordId = deptReq.getDeptId();*/
        szOrder szorder=szorderMapper.queryszOrderById(ordId);
        return ResultUtils.response(szorder);
    }
    @Override
    public ResultVo listszOrder(szOrder szorder) {
        System.out.println("【订单】全部全部...");
        /*List<szOrder> list=
        return szorderMapper.listszOrder(szorder);*/

       return null;
    }
}
