package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.OfferReq;
import com.example.entity.request.SzOrder;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
public interface OfferService {
    ResultVo addOffer(OfferReq offerReq);
    ResultVo getOffer(Integer offerId);
    ResultVo listOffer(OfferReq offerReq);
    ResultVo listOfferByCus(Integer cusId);
    ResultVo listPageOffer(OfferReq offerReq);
    ResultVo turnOrder(SzOrder szOrder,String token);

    /**
     * 首页报价审核列表
     * @return
     */
    ResultVo listExamine();

    /**
     * 报价审核
     * @return
     */
    ResultVo offerExamine(OfferReq offerReq,String token);
}
