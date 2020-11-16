package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Maint;

/**
 * @author: liuleimin
 * @date: 2020/11/16
 * @description:
 */
public interface MaintService {
    ResultVo listMaint(Maint maint);
    ResultVo getMaint(Integer maintId);
}
