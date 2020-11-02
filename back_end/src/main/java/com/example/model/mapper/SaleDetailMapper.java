package com.example.model.mapper;

import com.example.entity.request.SaleDetailReq;
import com.example.entity.response.SaleDetailResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Repository
public interface SaleDetailMapper {
    int addSaleDetail(SaleDetailReq saleDetailReq);
    int delSaleDetail(Integer saleDetailId);
    int editSaleDetail(SaleDetailReq saleDetailReq);
    SaleDetailResp getSaleDetail(Integer saleDetailId);
    List<SaleDetailResp> listSaleDetail(SaleDetailReq saleDetailReq);
}
