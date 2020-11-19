package com.example.model.mapper;

import com.example.entity.SanGuest;
import org.springframework.stereotype.Repository;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Repository
public interface SanGuestMapper {
    int addSanGuest(SanGuest sanGuest);
}
