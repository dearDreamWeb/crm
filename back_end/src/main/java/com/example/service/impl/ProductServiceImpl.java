package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ProductDetail;
import com.example.entity.ResultVo;
import com.example.entity.request.ProductReq;
import com.example.entity.response.ProductResp;
import com.example.model.mapper.ProductDetailMapper;
import com.example.model.mapper.ProductMapper;
import com.example.service.ProductService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.MyUtils;
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

    @Autowired
    private ProductDetailMapper detailMapper;

    @Override
    public ResultVo addProduct(ProductReq productReq) {
        CheckUtils.validate(productReq);
        Integer count = productReq.getProductCount();
        List<ProductDetail> list = new ArrayList<>(count);

        if (productReq.getProductId() != null) {
            ProductResp product = productMapper.getProduct(productReq.getProductId());
            productReq.setProductStock(productReq.getProductCount()+product.getProductStock());
            int editProduct = productMapper.editProduct(productReq);
            if (editProduct != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
            for (int i=0;i<count;i++) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductId(productReq.getProductId());
                productDetail.setProductBarCode(MyUtils.getUUID());
                list.add(i,productDetail);
            }
            detailMapper.batchAddProductDetail(list);
        } else {
            productReq.setProductStock(count);
            productReq.setProductStatus(0);
            productMapper.addProduct(productReq);
            Integer productId = productReq.getProductId();
            for (int i=0;i<count;i++) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductId(productId);
                productDetail.setProductBarCode(MyUtils.getUUID());
                list.add(i,productDetail);
            }
            detailMapper.batchAddProductDetail(list);
        }
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
    public ResultVo getProduct1(Integer productId) {
        List<ProductResp> product = productMapper.getProduct1(productId);
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

    @Override
    public ResultVo getProductByName(String productName) {
        ProductResp productByName = productMapper.getProductByName(productName);
        if (productByName != null) {
            return ResultUtils.response(productByName);
        }
        return ResultUtils.response("数据库暂无");
    }
}
