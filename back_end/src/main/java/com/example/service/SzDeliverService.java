package com.example.service;

import com.example.entity.ProductDetail;
import com.example.entity.ResultVo;
import com.example.entity.request.SzDeliver;
import com.example.entity.request.SzDeliverMingxi;

/**
 * @author: yangqianqian
 * @date: 2020/10/20
 * @description:
 */
public interface SzDeliverService {
    ResultVo addszDeliver(SzDeliver szdeliver);
    ResultVo delszDeliver(SzDeliver szdeliver);
    ResultVo editszDeliver(SzDeliver szdeliver);
    ResultVo getszDeliver(Integer delId);
    ResultVo listszDeliver(SzDeliver szdeliver);

    ResultVo addszDeliver_mx(SzDeliverMingxi mingxi);//增 发货明细
    ResultVo mx_editszDeliver(SzDeliver szdeliver);//改 发货单物流id物流公司发货状态
    ResultVo mx_editProDetail(Integer id);//改 产品详表的库存状态


    /*ResultVo addDelANDOrd(SzDeliver szDeliver);//发货单和订单新增*/
}
