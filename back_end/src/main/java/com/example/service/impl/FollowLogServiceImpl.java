package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.FollowLogReq;
import com.example.entity.response.FollowLogResp;
import com.example.model.mapper.FollowLogMapper;
import com.example.service.FollowLogService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FollowLogServiceImpl implements FollowLogService {
    @Autowired
    private FollowLogMapper logMapper;

    @Override
    public ResultVo addFollow(FollowLogReq followLogReq) {
        CheckUtils.validate(followLogReq);
        int addFollow = logMapper.addFollow(followLogReq);
        if (addFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addFollow);
    }

    @Override
    public ResultVo delFollow(FollowLogReq followLogReq) {
        Integer followId = followLogReq.getFollowId();
        FollowLogResp follow = logMapper.getFollow(followId);
        if (follow == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delFollow = logMapper.delFollow(followId);
        if (delFollow != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delFollow);
    }

    @Override
    public ResultVo editFollow(FollowLogReq followLogReq) {
        Integer followId = followLogReq.getFollowId();
        FollowLogResp follow = logMapper.getFollow(followId);
        if (follow == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        followLogReq.setVersion(follow.getVersion());
        followLogReq.setUpdateTime(DateUtils.getDate());
        int editFollow = logMapper.editFollow(followLogReq);
        if (editFollow != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editFollow);
    }

    @Override
    public ResultVo getFollow(Integer followId) {
        FollowLogResp follow = logMapper.getFollow(followId);
        if (follow == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(follow);
    }

    @Override
    public ResultVo listFollow(FollowLogReq followLogReq) {
        Integer pageNum = followLogReq.getPageNum();
        Integer pageSize = followLogReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<FollowLogResp> followLogResps = logMapper.listFollow(followLogReq);
        PageInfo<FollowLogResp> list = new PageInfo<>(followLogResps);
        return ResultUtils.response(list);
    }
}
