package com.example.model.mapper;

import com.example.entity.request.SzDeliver;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/19
 * @description:
 */
@Mapper
@Repository
public interface SzDeliverMapper {
    int addszDeliver(SzDeliver szdeliver);//增
    int delszDeliver(Integer delId);//删
    int editszDeliver(SzDeliver szdeliver);//改
    List<SzDeliver> getszDeliver(Integer delId);//单条
    List<SzDeliver> listszDeliver(SzDeliver szdeliver);
    int addDelANDOrd(SzDeliver szDeliver);//发货单和订单新增
}
