package com.example.model.mapper;

import com.example.entity.request.SzReceivableRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/11/5
 * @description:
 */
//回款记录
@Mapper
@Repository
public interface SzReceivableRecordMapper {
    List<SzReceivableRecord> listPlanANDReco(Integer recoId);
     void addPlanANDReco(List<SzReceivableRecord> list);
}
