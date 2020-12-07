package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;

import java.util.List;

public interface SzOrderService {
    ResultVo addszOrder(SzOrder szorder) throws Exception;
    ResultVo delszOrder(SzOrder szorder);
    ResultVo editszOrder(SzOrder szorder);
    ResultVo getszOrder(Integer ordId);
    ResultVo listszOrder(SzOrder szorder);
    List<SzOrder> OrdANDDel();
    List<SzOrder> orderAll(Integer ordId);//详情全部
    ResultVo newOrderCount(SzOrder szOrder);
    ResultVo editOrdState(SzOrder szorder);//回款完成后修改订单状态
}
