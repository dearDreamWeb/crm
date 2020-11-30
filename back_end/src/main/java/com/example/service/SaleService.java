package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.SaleDetailDemand;
import com.example.entity.request.SaleReq;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
public interface SaleService {
    ResultVo addSale(SaleReq saleReq,String token);
    ResultVo delSale(SaleReq saleReq);
    ResultVo editSale(SaleReq saleReq);
    ResultVo getSale(SaleReq saleReq);
    ResultVo listSale(SaleReq saleReq);
    ResultVo addSaleDetailDemand(SaleDetailDemand saleDetailDemand,String token);
    ResultVo editSaleAndDetail(SaleDetailDemand saleDetailDemand);
    ResultVo selectSaleAndDemandAndSolution(SaleReq saleReq);
}
