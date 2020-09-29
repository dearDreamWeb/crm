package com.example.model.mapper;

import com.example.entity.OpLog;
import org.springframework.stereotype.Repository;

/**
 * @author: pengjia
 * @date: 2020/9/28
 * @description:
 */
@Repository
public interface LogMapper {
    int addOpLog(OpLog opLog);
}
