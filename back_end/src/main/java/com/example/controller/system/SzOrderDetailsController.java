package com.example.controller.system;

import com.example.entity.request.SzOrderDetails;
import com.example.service.impl.SzOrderDetailsServiceLmpl;
import com.example.service.impl.SzOrderServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/21
 * @description:
 */
@RestController
@RequestMapping("/details")
public class SzOrderDetailsController {
    @Autowired
    private SzOrderDetailsServiceLmpl szOrderServicelmpl;

    @RequestMapping("/all")
    public List<SzOrderDetails> listOrderANDOrderDet(Integer odetId) {
        System.out.println(odetId);
        return szOrderServicelmpl.listOrderANDOrderDet(odetId);
    }

}
