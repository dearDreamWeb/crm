package com.example.model.mapper;

import com.example.entity.request.SzOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/*1003 yqq*/
@Repository
public interface SzOrderMapper {
    int addszOrder(SzOrder szorder);//增
    int delszOrder(Integer ordId);//删
    int editszOrder(SzOrder szorder);//改
    SzOrder getszOrder(Integer ordId);//单条
    List<SzOrder> listszOrder(SzOrder szorder);//全部
}
