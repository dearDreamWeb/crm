package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.szOrder;

public interface szOrderService {
    ResultVo listszOrder(szOrder szOrder);
    ResultVo queryszOrderById(szOrder szOrder);
    ResultVo addszOrder(szOrder szOrder);
    ResultVo updateszOrder(szOrder szOrder);
    ResultVo deleteszOrder(szOrder szOrder);
}
