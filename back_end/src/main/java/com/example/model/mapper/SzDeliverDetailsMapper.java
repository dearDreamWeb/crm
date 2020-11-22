package com.example.model.mapper;

import com.example.entity.request.SzDeliverDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: yangqianqian
 * @date: 2020/11/21
 * @description:
 */
@Mapper
@Repository
public interface SzDeliverDetailsMapper {
    void addDelANDDdel(SzDeliverDetails szDeliverDetails);
}
