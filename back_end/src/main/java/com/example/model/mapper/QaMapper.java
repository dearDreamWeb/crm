package com.example.model.mapper;

import com.example.entity.request.Qa;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/20
 * @description:
 */
@Repository
public interface QaMapper {
    List<Qa> listQa(Qa qa);
    Qa getQa(Integer qaId);
    int addQa(Qa qa);
    int updateQa(Qa qa);
    int deleteQa(Integer qaId);
}
