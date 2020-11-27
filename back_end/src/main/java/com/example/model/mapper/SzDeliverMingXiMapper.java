package com.example.model.mapper;

import com.example.entity.request.SzDeliverMingxi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: yangqianqian
 * @date: 2020/11/25
 * @description:
 */
@Mapper
@Repository
public interface SzDeliverMingXiMapper {
    int addszDeliver_mx(SzDeliverMingxi mingxi);
}
