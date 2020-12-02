package com.example.model.mapper;

import com.example.entity.request.SzOrderDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/21
 * @description:
 */
@Mapper
@Repository
public interface SzOrderDetailsMapper {
    List<SzOrderDetails> listOrderANDOrderDet(Integer odetId);
    void addOrderANDOrderDet(SzOrderDetails szOrderDetails);
    int addOrderDetail(SzOrderDetails szOrderDetails);
}
