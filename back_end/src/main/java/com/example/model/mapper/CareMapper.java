package com.example.model.mapper;

import com.example.entity.request.Care;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/10
 * @description:
 */
public interface CareMapper {
    List<Care> listCare(Care care);
    Care getCare(Care careId);
    int addCare(Care care);
    int updateCare(Care care);
    int deleteCare(int careId);
}
