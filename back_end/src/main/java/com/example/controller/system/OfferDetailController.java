package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.OfferDetailReq;
import com.example.service.OfferDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/11/23
 * @description:
 */
@RestController
@RequestMapping("/offer_detail")
public class OfferDetailController {
    @Autowired
    private OfferDetailService detailService;

    @SysLog("报价详情添加")
    @PostMapping("/add")
    public ResultVo addOfferDetail(@RequestBody OfferDetailReq offerDetailReq) {
        return detailService.addOfferDetail(offerDetailReq);
    }

    @GetMapping("/list")
    public ResultVo listOfferDetail(OfferDetailReq offerDetailReq) {
        return detailService.listOfferDetail(offerDetailReq);
    }

    @GetMapping
    public ResultVo getOfferDetailByOffer(@RequestParam("offerId") Integer offerId) {
        return detailService.getOfferDetailByOffer(offerId);
    }

    @GetMapping("/get_detail")
    public ResultVo getOfferDetail(Integer offerDetailId) {
        return detailService.getOfferDetail(offerDetailId);
    }

    @SysLog("报价详情删除")
    @PostMapping("/del")
    public ResultVo delOfferDetail(@RequestBody OfferDetailReq offerDetailReq) {
        return detailService.delOfferDetail(offerDetailReq);
    }
}
