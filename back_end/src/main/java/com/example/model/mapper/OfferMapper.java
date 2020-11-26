package com.example.model.mapper;

import com.example.entity.request.OfferReq;
import com.example.entity.response.OfferResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Repository
public interface OfferMapper {
    int addOffer(OfferReq offerReq);
    int editOffer(OfferReq offerReq);
    int delOffer(Integer offerId);
    OfferResp getOffer(Integer offerId);
    List<OfferResp> listOffer(OfferReq offerReq);
    List<OfferResp> listOfferByCus(Integer cusId);
}
