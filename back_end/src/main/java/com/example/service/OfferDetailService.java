package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.OfferDetailReq;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
public interface OfferDetailService {
    ResultVo addOfferDetail(OfferDetailReq offerDetailReq);
    ResultVo getOfferDetail(Integer offerDetailId);
}
