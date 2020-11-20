package com.example.model.mapper;

import com.example.entity.request.OfferDetailReq;
import com.example.entity.response.OfferDetailResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Repository
public interface OfferDetailMapper {
    int addOfferDetail(OfferDetailReq offerDetailReq);
    int editOfferDetail(OfferDetailReq offerDetailReq);
    int delOfferDetail(Integer offerDetailId);
    OfferDetailResp getOfferDetail(Integer offerDetailId);
    List<OfferDetailResp> listOfferDetail(OfferDetailReq offerDetailReq);
}
