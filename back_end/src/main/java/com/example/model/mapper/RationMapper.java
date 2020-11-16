package com.example.model.mapper;

import com.example.entity.Ration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Repository
public interface RationMapper {
    int addRation(Ration ration);
    List<Ration> listRation(Ration ration);
}
