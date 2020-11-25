package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ProductDetail;
import com.example.entity.ResultVo;
import com.example.model.mapper.ProductDetailMapper;
import com.example.service.ProductDetailService;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/11/25
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductDetailServiceImpl implements ProductDetailService{
    @Autowired
    ProductDetailMapper detailMapper;

    @Override
    public ResultVo getDetailByProductId(Integer productId) {
        System.out.println("....进...");
        List<ProductDetail> details = detailMapper.getDetailByProductId(productId);
        if (details == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(details);
    }
}
