package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;

public interface SzOrderService {
    ResultVo addszOrder(SzOrder szorder);
    ResultVo delszOrder(SzOrder szorder);
    ResultVo editszOrder(SzOrder szorder);
    ResultVo getszOrder(Integer ordId);
    ResultVo listszOrder(SzOrder szorder);
}
