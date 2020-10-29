package com.example.model.mapper;

import com.example.entity.request.Syslog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/21
 * @description:
 */
@Repository
public interface SyslogMapper {
    List<Syslog> listSyslog(Syslog syslog);
}
