package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.OfferReq;
import com.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
