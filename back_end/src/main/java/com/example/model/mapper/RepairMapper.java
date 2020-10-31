package com.example.model.mapper;

import com.example.entity.request.Repair;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/29
 * @description:
 */
@Repository
public interface RepairMapper {
    List<Repair> listRepair(Repair repair);
    Repair getRepair(Integer repairId);
    int addRepair(Repair repair);
    int updateRepair(Repair repair);
    int deleteRepair(Integer repairId);
}
