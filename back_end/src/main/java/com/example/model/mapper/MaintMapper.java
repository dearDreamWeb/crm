package com.example.model.mapper;

import com.example.entity.request.Maint;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/11/16
 * @description:
 */
@Repository
public interface MaintMapper {
    List<Maint> listMaint(Maint maint);
    Maint getMaint(Integer maintId);
}
