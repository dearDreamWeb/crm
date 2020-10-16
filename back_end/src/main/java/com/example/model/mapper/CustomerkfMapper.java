package com.example.model.mapper;

import com.example.entity.request.Customerkf;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */
@Repository
public interface CustomerkfMapper {
    List<Customerkf> listCustomerkf(Customerkf customerkf);
    Customerkf getCustomerkf(Integer customerId);
    int addCustomerkf(Customerkf customerkf);
    int updateCustomerkf(Customerkf customerkf);
    int deleteCustomerkf(int customerId);
}
