package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SzOrder;
import com.example.entity.request.SzOrderDetails;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/21
 * @description:
 */

public interface SzOrderDetailsService {
    List<SzOrderDetails> listOrderANDOrderDet(Integer odetId);
}
