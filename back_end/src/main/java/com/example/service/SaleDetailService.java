package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.SaleDetailReq;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
public interface SaleDetailService {
    ResultVo addSaleDetail(SaleDetailReq saleDetailReq);
    ResultVo delSaleDetail(SaleDetailReq saleDetailReq);
    ResultVo editSaleDetail(SaleDetailReq saleDetailReq);
    ResultVo getSaleDetail(SaleDetailReq saleDetailReq);
    ResultVo listSaleDetail(SaleDetailReq saleDetailReq);
}
