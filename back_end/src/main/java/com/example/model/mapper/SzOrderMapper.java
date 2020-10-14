package com.example.model.mapper;

import com.example.entity.request.szOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/*1003 yqq*/
@Repository
public interface szOrderMapper {
    int addszOrder(szOrder szorder);//增
    int delszOrder(Integer ordId);//删
    int editszOrder(szOrder szorder);//改
    szOrder queryszOrderById(Integer ordId);//单条
    List<szOrder> listszOrder(szOrder szorder);//全部
}
