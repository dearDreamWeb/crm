package com.example.service.impl;

import com.example.entity.ResultVo;
import com.example.entity.request.OfferDetailReq;
import com.example.model.mapper.OfferDetailMapper;
import com.example.service.OfferDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OfferDetailServiceImpl implements OfferDetailService {
    @Autowired
    private OfferDetailMapper detailMapper;

    @Override
    public ResultVo addOfferDetail(OfferDetailReq offerDetailReq) {
        return null;
    }

    @Override
    public ResultVo getOfferDetail(Integer offerDetailId) {
        return null;
    }
}
