package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.ProductReq;
import com.example.entity.response.ProductResp;
import com.example.model.mapper.ProductMapper;
import com.example.service.ProductService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultVo addProduct(ProductReq productReq) {
        CheckUtils.validate(productReq);
        List<ProductReq> list = new ArrayList<>(productReq.getProductCount());
        list.add(productReq);
        productMapper.batchAddProduct(list);
        /*int addProduct = productMapper.addProduct(productReq);
        if (addProduct != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }*/
        return ResultUtils.response("新增成功");
    }

    @Override
    public ResultVo delProduct(ProductReq productReq) {
        Integer productId = productReq.getProductId();
        ProductResp product = productMapper.getProduct(productId);
        if (product == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delProduct = productMapper.delProduct(productId);
        if (delProduct != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delProduct);
    }

    @Override
    public ResultVo editProduct(ProductReq productReq) {
        Integer productId = productReq.getProductId();
        ProductResp product = productMapper.getProduct(productId);
        if (product == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        productReq.setVersion(product.getVersion());
        productReq.setUpdateTime(DateUtils.getDate());
        int editProduct = productMapper.editProduct(productReq);
        if (editProduct != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editProduct);
    }

    @Override
    public ResultVo getProduct(Integer productId) {
        ProductResp product = productMapper.getProduct(productId);
        if (product == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(product);
    }

    @Override
    public ResultVo listProduct(ProductReq productReq) {
        Integer pageNum = productReq.getPageNum();
        Integer pageSize = productReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ProductResp> productResps = productMapper.listProduct(productReq);
        PageInfo<ProductResp> list = new PageInfo<>(productResps);
        return ResultUtils.response(list);
    }
}
