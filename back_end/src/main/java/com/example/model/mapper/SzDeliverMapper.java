package com.example.model.mapper;

import com.example.entity.request.SzDeliver;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/19
 * @description:
 */
@Repository
public interface SzDeliverMapper {
    int addszDeliver(SzDeliver szdeliver);//增
    int delszDeliver(Integer delId);//删
    int editszDeliver(SzDeliver szdeliver);//改
    SzDeliver getszDeliver(Integer delId);//单条
    List<SzDeliver> listszDeliver(SzDeliver szdeliver);
}
