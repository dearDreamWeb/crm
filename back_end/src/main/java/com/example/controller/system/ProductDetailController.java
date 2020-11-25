package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangqianqian
 * @date: 2020/11/25
 * @description:
 */
@RestController
@RequestMapping("/product_detail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService detailService;
    @GetMapping("/get_by_productId")
    public ResultVo getDetail1(@RequestParam("productId")Integer productId){
        return detailService.getDetailByProductId(productId);
    }

}
