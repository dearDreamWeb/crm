package com.example.model.mapper;

import com.example.entity.request.DemandReq;
import com.example.entity.response.DemandResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Repository
public interface DemandMapper {
    int addDemand(DemandReq demandReq);
    int delDemand(Integer demandId);
    int editDemand(DemandReq demandReq);
    DemandResp getDemand(Integer demandId);
    List<DemandResp> listDemand(DemandReq demandReq);
    List<DemandResp> getBySaleId(Integer saleId);
}
