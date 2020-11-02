package com.example.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.request.ProductReq;
import com.example.entity.request.SzOrder;
import com.example.entity.request.SzOrderDetails;
import com.example.model.mapper.SzOrderDetailsMapper;
import com.example.model.mapper.SzOrderMapper;
import com.example.service.SzOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/21
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SzOrderDetailsServiceLmpl implements SzOrderDetailsService {
    @Autowired
    SzOrderDetailsMapper szorderDetailsMapper;
    @Autowired
    SzOrderMapper szOrderMapper;

    @Override
    public List<SzOrderDetails> listOrderANDOrderDet(Integer odetId) {
        return szorderDetailsMapper.listOrderANDOrderDet(odetId);
    }

    @Override
    public void addOrderANDOrderDet(List products, SzOrder szOrder) {
        List<ProductReq> addproplus = szOrder.getAddproplus();
//先遍历再得
        for (int i =0;i<addproplus.size();i++){
            ProductReq productReq=new ProductReq();
            productReq.setProductId(szOrder.getOrdId());
            productReq.setProductNumber(addproplus.size());
            addproplus.add(i,productReq);
        }
//        szorderDetailsMapper.addOrderANDOrderDet();
        /*JSONArray json = JSONArray.parseArray(String.valueOf(products));
        //int orderId = szOrderMapper.addszOrder(szOrder);
        SzOrderDetails szOrderDetails = null;
        JSONObject ob = null;
        ProductReq productt = null;
        for (int i = 0; i < json.size(); i++) {
            ob = (JSONObject) json.get(i);//得到json对象
            Integer odetBuynum = ob.getInteger("productNumber");
            Integer productId = ob.getInteger("productId");
            BigDecimal odetBuymoney = ob.getBigDecimal("productPrice");
            //szOrderDetails.setOrdId(orderId);
            szOrderDetails.setOdetBuynum(odetBuynum);
            szOrderDetails.setProductId(productId);
            szOrderDetails.setOdetBuymoney(odetBuymoney);
            //szorderDetailsMapper.addOrderANDOrderDet(szOrderDetails);
            }*/
    }
}
