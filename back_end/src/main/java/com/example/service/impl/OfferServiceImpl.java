package com.example.service.impl;

import com.example.entity.ResultVo;
import com.example.entity.request.OfferReq;
import com.example.model.mapper.OfferMapper;
import com.example.service.OfferService;
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
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferMapper offerMapper;

    @Override
    public ResultVo addOffer(OfferReq offerReq) {
        return null;
    }

    @Override
    public ResultVo getOffer(Integer offerId) {
        return null;
    }
}
