package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Accessories;

/**
 * @author: liuleimin
 * @date: 2020/11/6
 * @description:
 */
public interface AccessoriesService {
    ResultVo addAccessories(Accessories accessories);
    ResultVo updateAccessories(Accessories accessoriesReq);
    ResultVo deleteAccessories(Integer accessId);
    ResultVo listAccessories(Accessories accessories);
    ResultVo getAccessories(Integer accessId);
}
