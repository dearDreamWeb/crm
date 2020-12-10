package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
import com.example.entity.request.SzDeliverDetails;
import com.example.entity.request.SzOrder;
import com.example.entity.request.SzOrderDetails;
import com.example.model.mapper.SzDeliverDetailsMapper;
import com.example.model.mapper.SzDeliverMapper;
import com.example.model.mapper.SzOrderDetailsMapper;
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
    @Autowired
    private SzOrderDetailsMapper detailsMapper;
    @Autowired
    private SzDeliverMapper szDeliverMapper;
    @Autowired
    private SzDeliverDetailsMapper szDeliverDetailsMapper;

    @Override
    public ResultVo editOrdState(SzOrder szorder) {
        System.out.println(szorder);
        int i = szorderMapper.editOrdState(szorder);
        return ResultUtils.response(i);
    }

    //一次性新增多个详情
    @Override
    public ResultVo addszOrder(SzOrder order) throws Exception {
        order.setOrdState(0);/*执行中0*/
        order.setOrdDelete(0);/*(删除)否0*/
        order.setOrdStarttime(DateUtils.getDate());/*（订单开始时间）订单创建时间*/
        order.setOrdCreatetime(DateUtils.getDate());/*订单创建时间*/
        int i =1;
        //第一步:获取所有的详情信息
        List<SzOrderDetails> details = order.getSzOrderDetails();
        //第二步：调用Mapper方法，新增单条订单
        szorderMapper.addszOrder(order);
        //第三步：获取新增的订单编号
        Integer oid = order.getOrdId();
        System.out.println("主键编号是："+oid);
        //添加发货单
        SzDeliver deliver=new SzDeliver();
        deliver.setOrdId(oid);
        deliver.setDelState(0);//未发
        deliver.setDelExpecttime(DateUtils.strToDate(DateUtils.nextMonth()));//预计发货时间
        deliver.setDelCreatetime(DateUtils.getDate());//创建时间
        szDeliverMapper.addszDeliver(deliver);
        System.out.println("发货单的订单编号"+deliver.getOrdId());
        System.out.println("deliver"+deliver);

        //第四步：逐条新增订单详情
        for (SzOrderDetails detail : details) {
            detail.setOrdId(oid);
            //第五步：调用详细的新增方法

            /**
             * 订单详情、发货详情的数量
             */
            detailsMapper.addOrderANDOrderDet(detail);
            SzDeliverDetails  szDeliverDetails = new SzDeliverDetails();
            szDeliverDetails.setDdetNum(detail.getOdetBuynum());//发货数量
            szDeliverDetails.setProductId(detail.getProductId());//产品编号
            szDeliverDetails.setDelId(deliver.getDelId());
            System.out.println("Delid："+deliver.getDelId());
            System.out.println(("Deliver："+deliver));
//            //循环外新增的发货单实体对象
            szDeliverDetails.setSzDeliver(deliver);
            //添加发货详情
            szDeliverDetailsMapper.addDelANDDdel(szDeliverDetails);
            System.out.println("业务层的发货详情"+szDeliverDetails);
        }

        return ResultUtils.response(oid);
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

    @Override
    public List<SzOrder> orderAll(Integer ordId) {
        return szorderMapper.orderAll(ordId);
    }

    @Override
    public ResultVo newOrderCount(SzOrder szOrder) {
        int count = szorderMapper.newOrderCount(szOrder);
        return ResultUtils.response(count);
    }

}

