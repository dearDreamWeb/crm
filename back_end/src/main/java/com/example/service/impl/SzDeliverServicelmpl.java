package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
import com.example.entity.request.SzDeliverDetails;
import com.example.model.mapper.SzDeliverDetailsMapper;
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

    @Autowired
    private SzDeliverDetailsMapper deliverDetailsMapper; //addDelANDDdel增

    @Override
    public ResultVo addszDeliver(SzDeliver szdeliver) {
        System.out.println("【发货单】新增");
        szdeliver.setDelState(0);//默认未发
        int i=1;
        List<SzDeliverDetails> details=szdeliver.getSzDeliverDetails();
        szdeliverMapper.addszDeliver(szdeliver);
        Integer delid = szdeliver.getDelId();
        System.out.println("获取到的发货单的主键id是："+delid);
        for (SzDeliverDetails details1 :details){
            details1.setDelId(delid);
            deliverDetailsMapper.addDelANDDdel(details1);
        }
        return ResultUtils.response(delid);
    }
    /*
        //第一步:获取所有的详情信息
        List<SzOrderDetails> details = order.getSzOrderDetails();
        //第二步：调用Mapper方法，新增单条订单
            szorderMapper.addszOrder(order);
        //第三步：获取新增的订单编号
        Integer oid = order.getOrdId();
            System.out.println("主键编号是："+oid);
        //第四步：逐条新增订单详情
            for (SzOrderDetails detail : details) {
            detail.setOrdId(oid);
            //第五步：调用详细的新增方法
            detailsMapper.addOrderANDOrderDet(detail);
        }
            return ResultUtils.response(oid);
    }
    * */
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
