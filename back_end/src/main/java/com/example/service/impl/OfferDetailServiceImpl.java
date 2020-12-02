package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.controller.WebSocketController;
import com.example.entity.ResultVo;
import com.example.entity.request.OfferDetailReq;
import com.example.entity.response.OfferDetailResp;
import com.example.model.mapper.OfferDetailMapper;
import com.example.model.mapper.OfferMapper;
import com.example.service.OfferDetailService;
import com.example.util.CheckUtils;
import com.example.util.ResultUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OfferDetailServiceImpl implements OfferDetailService {
    @Autowired
    private OfferDetailMapper detailMapper;

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private WebSocketController webSocketService;

    @Override
    public ResultVo addOfferDetail(OfferDetailReq offerDetailReq) {
        CheckUtils.validate(offerDetailReq);
        Integer offerDetailId = offerDetailReq.getOfferDetailId();
        OfferDetailResp offerDetail = detailMapper.getOfferDetail(offerDetailId);
        if (offerDetail != null) {
            int editOfferDetail = detailMapper.editOfferDetail(offerDetailReq);
            if (editOfferDetail != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
            checkSwitchValue(offerDetailReq.getSwitchValue(),offerDetailReq.getOfferId());
        } else {
            int addOfferDetail = detailMapper.addOfferDetail(offerDetailReq);
            if (addOfferDetail != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
            checkSwitchValue(offerDetailReq.getSwitchValue(),offerDetailReq.getOfferId());

        }
        return ResultUtils.response("新增成功");
    }

    @Override
    public ResultVo getOfferDetail(Integer offerDetailId) {
        OfferDetailResp offerDetail = detailMapper.getOfferDetail(offerDetailId);
        return ResultUtils.response(offerDetail);
    }

    @Override
    public ResultVo listOfferDetail(OfferDetailReq offerDetailReq) {
        List<OfferDetailResp> offerDetailResps = detailMapper.listOfferDetail(offerDetailReq);
        return ResultUtils.response(offerDetailResps);
    }

    @Override
    public ResultVo getOfferDetailByOffer(Integer offerId) {
        List<OfferDetailResp> offerDetailByOffer = detailMapper.getOfferDetailByOffer(offerId);
        return ResultUtils.response(offerDetailByOffer);
    }

    @Override
    public ResultVo delOfferDetail(OfferDetailReq offerDetailReq) {
        Integer offerDetailId = offerDetailReq.getOfferDetailId();
        OfferDetailResp offerDetail = detailMapper.getOfferDetail(offerDetailId);
        if (offerDetail == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delOfferDetail = detailMapper.delOfferDetail(offerDetailId);
        if (delOfferDetail != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delOfferDetail);
    }

    public void checkSwitchValue(boolean switchValue,Integer offerId) {
        if (switchValue) {
            int offerStatus = offerMapper.editOfferStatus(offerId);
            if (offerStatus != 1) {
                throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                        ResultEnum.DATA_UPDATE_FAIL.getMessage());
            }
        }
        Map map = new HashMap();
        map.put("message","你有一条新的报价待审核！！！");
        map.put("offerId",offerId);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        webSocketService.sendOneMessage("admin",json);
    }
}
