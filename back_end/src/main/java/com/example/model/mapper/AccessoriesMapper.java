package com.example.model.mapper;

import com.example.entity.request.Accessories;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/11/6
 * @description:
 */
@Repository
public interface AccessoriesMapper {
    List<Accessories> listAccessories(Accessories accessories);
    Accessories getAccessories(Integer accessId);
    int addAccessories(Accessories accessories);
    int updateAccessories(Accessories accessories);
    int deleteAccessories(Integer accessId);
}
