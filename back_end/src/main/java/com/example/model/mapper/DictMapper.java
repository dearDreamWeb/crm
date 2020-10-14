package com.example.model.mapper;

import com.example.entity.request.DictReq;
import com.example.entity.response.DictResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/10
 * @description:
 */
@Repository
public interface DictMapper {
    int addDict(DictReq dictReq);
    int delDict(Integer dictId);
    int editDict(DictReq dictReq);
    DictResp getDict(Integer dictId);
    List<DictResp> listDict(DictReq dictReq);
    List<DictResp> listPidDict(DictReq dictReq);
    List<DictResp> treeDictById(Integer dictId);
    int checkDict(String dictCode);
}
