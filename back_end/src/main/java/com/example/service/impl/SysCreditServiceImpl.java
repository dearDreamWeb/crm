package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.SysCredit;
import com.example.model.repository.CreditRepository;
import com.example.service.SysCreditService;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author: pengjia
 * @date: 2020/10/24
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysCreditServiceImpl implements SysCreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Override
    public ResultVo addCredit(SysCredit sysCredit) {
        SysCredit save = creditRepository.save(sysCredit);
        if (save == null) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(save);
    }

    @Override
    public ResultVo delCredit(Integer creditId) {
        creditRepository.deleteById(creditId);
        return ResultUtils.response("成功");
    }

    @Override
    public ResultVo editCredit(SysCredit sysCredit) {
        Optional<SysCredit> byId = creditRepository.findById(sysCredit.getCreditId());
        if (byId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        SysCredit save = creditRepository.save(sysCredit);
        if (save == null) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(save);
    }

    @Override
    public ResultVo listCredit() {
        List<SysCredit> sysCredits = creditRepository.findAll();
        return ResultUtils.response(sysCredits);
    }
}
