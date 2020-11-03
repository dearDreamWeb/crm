package com.example.service.impl;

import com.example.entity.request.SzOrderDetails;
import com.example.model.mapper.SzOrderDetailsMapper;
import com.example.model.mapper.SzOrderMapper;
import com.example.service.SzOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/21
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SzOrderDetailsServiceLmpl implements SzOrderDetailsService {
    @Autowired
    SzOrderDetailsMapper szorderDetailsMapper;
    @Autowired
    SzOrderMapper szOrderMapper;

    @Override
    public List<SzOrderDetails> listOrderANDOrderDet(Integer odetId) {
        return szorderDetailsMapper.listOrderANDOrderDet(odetId);
    }

}
