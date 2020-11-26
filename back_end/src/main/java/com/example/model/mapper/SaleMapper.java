package com.example.model.mapper;

import com.example.entity.request.SaleReq;
import com.example.entity.response.SaleResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Repository
public interface SaleMapper {
    int addSale(SaleReq saleReq);
    int delSale(Integer saleId);
    int editSale(SaleReq saleReq);
    SaleResp getSale(Integer saleId);
    List<SaleResp> listSale(SaleReq saleReq);
    List<SaleResp> selectSaleAndDemandAndSolution(SaleReq saleReq);
}
