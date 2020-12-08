package com.example.controller;

import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.SzOrder;
import com.example.service.ActivityService;
import com.example.service.ClueService;
import com.example.service.CustomerService;
import com.example.service.SzOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/12/3
 * @description:
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ClueService clueService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SzOrderService orderService;

    @GetMapping("/new_clue")
    public ResultVo newClue(ClueReq clueReq) {
        return clueService.newClueCount(clueReq);
    }

    @GetMapping("/new_customer")
    public ResultVo newCustomer(CustomerReq customerReq) {
        return customerService.newCustomerCount(customerReq);
    }

    @GetMapping("/new_page_view")
    public ResultVo newPageView() {
        return activityService.pageViewCount();
    }

    @GetMapping("/new_order")
    public ResultVo newOrder(SzOrder szOrder) {
        return orderService.newOrderCount(szOrder);
    }

    @GetMapping("/clue_count")
    public ResultVo clueCount() {
        return clueService.clueCount();
    }
}
