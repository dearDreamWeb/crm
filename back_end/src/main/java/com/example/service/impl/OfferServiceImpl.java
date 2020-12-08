package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.entity.SanGuest;
import com.example.entity.request.*;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.EmpResp;
import com.example.entity.response.OfferResp;
import com.example.model.mapper.*;
import com.example.service.OfferService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SzOrderMapper szOrderMapper;

    @Autowired
    private SzOrderDetailsMapper szOrderDetailsMapper;

    @Autowired
    private SzDeliverMapper szDeliverMapper;

    @Autowired
    private SzDeliverDetailsMapper szDeliverDetailsMapper;

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

    @Override
    public ResultVo listOfferByCus(Integer cusId) {
        List<OfferResp> offerResps = offerMapper.listOfferByCus(cusId);
        return ResultUtils.response(offerResps);
    }

    @Override
    public ResultVo listPageOffer(OfferReq offerReq) {
        Integer pageNum = offerReq.getPageNum();
        Integer pageSize = offerReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<OfferResp> offerResps = offerMapper.listOffer(offerReq);
        PageInfo<OfferResp> list = new PageInfo<>(offerResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo turnOrder(SzOrder szOrder,String token) {
        EmpResp empByToken = empMapper.getEmpByToken(token);
        szOrder.setOrdHead(empByToken.getEmpName());
        szOrder.setOrdStarttime(DateUtils.getDate());
        szOrder.setOrdDelete(0);/*(删除)否0*/
        szOrder.setOrdState(0);
        szOrder.setOrdPlan(0);
        int addszOrder = szOrderMapper.addszOrder(szOrder);
        if (addszOrder != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        List<SzOrderDetails> szOrderDetails = szOrder.getSzOrderDetails();

        //添加发货单
        SzDeliver deliver=new SzDeliver();
        deliver.setOrdId(szOrder.getOrdId());
        deliver.setDelState(0);//未发
        try {
            deliver.setDelExpecttime(DateUtils.strToDate(DateUtils.nextMonth()));//预计发货时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        deliver.setDelCreatetime(DateUtils.getDate());//创建时间
        szDeliverMapper.addszDeliver(deliver);
        System.out.println("发货单的订单编号"+deliver.getOrdId());
        System.out.println("deliver"+deliver);

        /*新增订单详情*/
        for (int i=0;i<szOrderDetails.size();i++) {
            szOrderDetails.get(i).setOrdId(szOrder.getOrdId());
            int addOrderDetail = szOrderDetailsMapper.addOrderDetail(szOrderDetails.get(i));
            if (addOrderDetail != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
            //新增发货详情
            SzDeliverDetails  szDeliverDetails = new SzDeliverDetails();
            szDeliverDetails.setDdetNum(szOrderDetails.get(i).getOdetBuynum());//发货数量
            szDeliverDetails.setProductId(szOrderDetails.get(i).getProductId());//产品编号
            szDeliverDetails.setDelId(deliver.getDelId());
            System.out.println("Delid："+deliver.getDelId());
            System.out.println(("Deliver："+deliver));
//            //循环外新增的发货单实体对象
            szDeliverDetails.setSzDeliver(deliver);
            //添加发货详情
            szDeliverDetailsMapper.addDelANDDdel(szDeliverDetails);
            System.out.println("业务层的发货详情"+szDeliverDetails);

        }
        int editOfferTurn = offerMapper.editOfferTurn(szOrder.getOfferId());
        if (editOfferTurn != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(addszOrder);
    }

    @Override
    public ResultVo listExamine() {
        List<OfferResp> offerResps = offerMapper.listExamine();
        return ResultUtils.response(offerResps);
    }

    @Override
    public ResultVo offerExamine(OfferReq offerReq,String token) {
        EmpResp empByToken = empMapper.getEmpByToken(token);
        OfferResp offer = offerMapper.getOffer(offerReq.getOfferId());
        if (offer == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        offerReq.setOfferStatus(1);
        offerReq.setExaminePerson(empByToken.getEmpName());
        offerReq.setExamineTime(DateUtils.getDate());
        offerReq.setUpdateTime(DateUtils.getDate());
        offerReq.setVersion(offer.getVersion());
        int editOffer = offerMapper.editOffer(offerReq);
        if (editOffer != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }

        return ResultUtils.response(editOffer);
    }
}
