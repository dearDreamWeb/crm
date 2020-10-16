package com.example.model.mapper;

import com.example.entity.request.Care;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/10
 * @description:
 */
@Repository
public interface CareMapper {
    List<Care> listCare(Care care);
    Care getCare(Integer careId);
    int addCare(Care care);
    int updateCare(Care care);
    int deleteCare(Integer careId);
}
