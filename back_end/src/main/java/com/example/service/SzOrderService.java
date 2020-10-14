package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.szOrder;

public interface szOrderService {
    ResultVo addszOrder(szOrder szorder);
    ResultVo delszOrder(szOrder szorder);
    ResultVo editszOrder(szOrder szorder);
    ResultVo queryszOrderById(Integer ordId);
    ResultVo listszOrder(szOrder szorder);
}
