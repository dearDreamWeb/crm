package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.OfferReq;
import com.example.entity.request.SzOrder;
import com.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@RestController
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @SysLog("新增报价")
    @PostMapping("/add")
    public ResultVo addOffer(@RequestBody OfferReq offerReq) {
        return offerService.addOffer(offerReq);
    }

    @GetMapping
    public ResultVo getOffer(Integer offerId) {
        return offerService.getOffer(offerId);
    }

    @GetMapping("/list")
    public ResultVo listOffer(OfferReq offerReq) {
        return offerService.listOffer(offerReq);
    }

    @GetMapping("/list_by_cusId")
    public ResultVo listOfferByCus(Integer cusId) {
        return offerService.listOfferByCus(cusId);
    }

    @GetMapping("/list_page")
    public ResultVo listPageOffer(OfferReq offerReq) {
        return offerService.listPageOffer(offerReq);
    }

    @SysLog("报价转化订单")
    @PostMapping("/turn_order")
    public ResultVo turnOrder(@RequestBody SzOrder szOrder,
                              HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return offerService.turnOrder(szOrder,token);
    }

    @GetMapping("/list_examine")
    public ResultVo listExamine() {
        return offerService.listExamine();
    }

    @SysLog("报价审核")
    @PostMapping("/offer_examine")
    public ResultVo offerExamine(@RequestBody OfferReq offerReq,
                                 HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return offerService.offerExamine(offerReq,token);
    }
}
