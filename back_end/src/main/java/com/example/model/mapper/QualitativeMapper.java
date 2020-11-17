package com.example.model.mapper;

import com.example.entity.Qualitative;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Repository
public interface QualitativeMapper {
    int addQualitative(Qualitative qualitative);
    int editQualitative(Qualitative qualitative);
    List<Qualitative> listQualitative(Qualitative qualitative);
    Qualitative getQualitativeByCusId(Integer cusId);
}
