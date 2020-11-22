package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.entity.SanGuest;
import com.example.entity.request.OfferReq;
import com.example.entity.request.SaleDetailReq;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.OfferResp;
import com.example.model.mapper.*;
import com.example.service.OfferService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private SaleDetailMapper detailMapper;

    @Autowired
    private CustomerRecordMapper recordMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SanGuestMapper sanGuestMapper;

    @Override
    public ResultVo addOffer(OfferReq offerReq) {
        CheckUtils.validate(offerReq);
        offerReq.setCreateTime(DateUtils.getDate());
        offerReq.setOfferStatus(0);
        int addOffer = offerMapper.addOffer(offerReq);
        if (addOffer != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        SaleDetailReq bySale = detailMapper.getBySale(offerReq.getSaleId());
        bySale.setSaleStage("商务谈判");
        bySale.setVersion(bySale.getVersion());
        bySale.setUpdateTime(DateUtils.getDate());
        int editSaleDetail = detailMapper.editSaleDetail(bySale);
        if (editSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }

        CustomerResp customerResp = customerMapper.getBySaleId(offerReq.getSaleId());
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("新增报价");
        customerRecord.setRecordType("报价");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setCusId(customerResp.getCusId());
        customerRecord.setEmpId(offerReq.getEmpId());
        customerRecord.setRecordContent("对客户【"+customerResp.getCusName()+
                "制定报价");
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        SanGuest sanGuest = new SanGuest();
        sanGuest.setSanGuestName("方案制定");
        sanGuest.setSanGuestTime(DateUtils.getDate());
        sanGuest.setCusId(customerResp.getCusId());
        sanGuest.setSanGuestEmpId(offerReq.getEmpId());
        int addSanyGuest = sanGuestMapper.addSanGuest(sanGuest);
        if (addSanyGuest != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response(addOffer);
    }

    @Override
    public ResultVo getOffer(Integer offerId) {
        OfferResp offer = offerMapper.getOffer(offerId);
        return ResultUtils.response(offer);
    }

    @Override
    public ResultVo listOffer(OfferReq offerReq) {
        List<OfferResp> offerResps = offerMapper.listOffer(offerReq);
        return ResultUtils.response(offerResps);
    }
}