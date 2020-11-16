package com.example.model.mapper;

import com.example.entity.Grading;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Repository
public interface GradingMapper {
    int addGrading(Grading grading);
    List<Grading> listGrading(Grading grading);
}
