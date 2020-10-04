package com.example.model.mapper;

import com.example.entity.request.szOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/*1003 yqq*/
@Repository
public interface szOrderMapper {
    List<szOrder> szOrderList();//全部
    szOrder queryazOrderById(int ordId);//单条
    int addszOrder(szOrder szOrder);//增
    int updateszOrder(szOrder szOrder);//改
    int deleteszOrder(int ordId);//删
}
