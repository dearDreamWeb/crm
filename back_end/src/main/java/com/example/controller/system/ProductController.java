package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ProductReq;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @SysLog("产品添加")
    @PostMapping("/add")
    public ResultVo addProduct(@RequestBody ProductReq productReq) {
        return productService.addProduct(productReq);
    }

    @SysLog("产品删除")
    @PostMapping("/del")
    public ResultVo delProduct(@RequestBody ProductReq productReq) {
        return productService.delProduct(productReq);
    }

    @SysLog("产品修改")
    @PostMapping("/edit")
    public ResultVo editProduct(@RequestBody ProductReq productReq) {
        return productService.editProduct(productReq);
    }

    @GetMapping
    public ResultVo getProduct(@RequestParam("productId")Integer productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/list")
    public ResultVo listProduct(ProductReq productReq) {
        return productService.listProduct(productReq);
    }
}
