package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.entity.response.ActivityResp;
import com.example.entity.response.ClueFollowLogResp;
import com.example.entity.response.ClueResp;
import com.example.entity.response.EmpResp;
import com.example.model.mapper.ActivityMapper;
import com.example.model.mapper.ClueFollowLogMapper;
import com.example.model.mapper.ClueMapper;
import com.example.model.mapper.EmpMapper;
import com.example.service.ClueService;
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
 * @date: 2020/10/12
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ClueFollowLogMapper clueFollowLogMapper;

    @Override
    public ResultVo addClue(ClueReq clueReq) {
        CheckUtils.validate(clueReq);
        ClueFollowLogResp clueFollowLogResp = new ClueFollowLogResp();
        clueReq.setClueStatus(0);
        Integer activityId = clueReq.getActivityId();
        Integer empId = empMapper.getEmpIdByName(clueReq.getHandlePerson());
        if (activityId == null) {
            clueReq.setEmpId(empId);
            clueReq.setActivityId(0);
        }
        ActivityResp activity = activityMapper.getActivity(clueReq.getActivityId());
        int addClue = clueMapper.addClue(clueReq);
        if (addClue != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        clueFollowLogResp.setClueId(activity.getActivityId());
        clueFollowLogResp.setClueFollowTitle("新增线索，水鱼来了");
        clueFollowLogResp.setClueFollowContent("水鱼通过【"+activity.getActivityTitle()+"】提交");
        clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
        int addClueFollow = clueFollowLogMapper.addClueFollow(clueFollowLogResp);
        if (addClueFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addClue);
    }

    @Override
    public ResultVo delClue(ClueReq clueReq,String token) {
        Integer clueId = clueReq.getClueId();
        ClueResp clue = clueMapper.getClue(clueId);
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        Integer empId = clue.getEmpId();
        if (empId != 0) {
            throw new SysException(ResultEnum.CLUE_NOT_BE_DEL.getCode(),
                    ResultEnum.CLUE_NOT_BE_DEL.getMessage());
        }
        EmpResp empResp = clue.getEmpResp();
        if (empResp == null && !empResp.getToken().equals(token)) {
            throw new SysException(ResultEnum.CLUE_NOT_BE_DEL.getCode(),
                    ResultEnum.CLUE_NOT_BE_DEL.getMessage());
        }
        int delClue = clueMapper.delClue(clueId);
        if (delClue != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delClue);
    }

    @Override
    public ResultVo editClue(ClueReq clueReq) {
        ClueResp clue = clueMapper.getClue(clueReq.getClueId());
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        clueReq.setVersion(clue.getVersion());
        clueReq.setUpdateTime(DateUtils.getDate());
        int editClue = clueMapper.editClue(clueReq);
        if (editClue != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editClue);
    }

    @Override
    public ResultVo getClue(Integer clueId,String token) {
        ClueResp clue = clueMapper.getClue(clueId);
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        EmpResp empResp = empMapper.getEmpByToken(token);
        if (empResp == null && !empResp.getToken().equals(token)) {
            throw new SysException(ResultEnum.CLUE_NOT_BE_EDIT.getCode(),
                    ResultEnum.CLUE_NOT_BE_EDIT.getMessage());
        }
        Integer empId = empResp.getEmpId();
        if (empResp.getEmpName().equals("admin")){
            return ResultUtils.response(clue);
        }
        if (empId != clue.getEmpId()) {
            throw new SysException(ResultEnum.CLUE_NOT_BE_EDIT.getCode(),
                    ResultEnum.CLUE_NOT_BE_EDIT.getMessage());
        }
        return ResultUtils.response(clue);
    }

    @Override
    public ResultVo listClue(ClueReq clueReq,String empName) {
        Integer pageNum = clueReq.getPageNum();
        Integer pageSize = clueReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ClueResp> clueResps;
        if (empName.equals("admin")) {
            clueResps = clueMapper.listClue(clueReq);
        } else {
            clueResps = clueMapper.listClueByEmpId(clueReq,empName);
        }
        PageInfo<ClueResp> list = new PageInfo<>(clueResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo getClueDetail(Integer clueId) {
        ClueResp clue = clueMapper.getClue(clueId);
        return ResultUtils.response(clue);
    }

    @Override
    public ResultVo batchEditClueType(ClueReq clueReq) {
        ClueFollowLogResp clueFollowLogResp = new ClueFollowLogResp();
        for (int i=0;i<clueReq.getClueIdList().size();i++) {
            clueFollowLogResp.setClueId(clueReq.getClueIdList().get(i));
            clueFollowLogResp.setClueFollowTitle("确认线索类型");
            clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
            clueFollowLogResp.setClueFollowPerson(clueReq.getHandlePerson());
            clueFollowLogResp.setClueFollowContent("由【"+clueReq.getHandlePerson()+
                    "】确认该线索有效且类型为【"+clueHandleResultFormat(clueReq.getClueType())+"】");
            int addClueFollow = clueFollowLogMapper.addClueFollow(clueFollowLogResp);
            if (addClueFollow != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        }
        if (clueReq.getClueIdList().size() == 0) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int batchEditClueType = clueMapper.batchEditClueType(clueReq);
        if (batchEditClueType == 0) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(batchEditClueType);
    }

    @Override
    public ResultVo listCompany() {
        List<ClueResp> clueResps = clueMapper.listCompany();
        return ResultUtils.response(clueResps);
    }

    @Override
    public ResultVo transferCustomer(ClueReq clueReq,String token) {
        EmpResp empByToken = empMapper.getEmpByToken(token);
        if (clueReq.getTransferId() != empByToken.getEmpId()) {
            throw new SysException(ResultEnum.CLUE_TRANSFER_NOT_OWN.getCode(),
                    ResultEnum.CLUE_TRANSFER_NOT_OWN.getMessage());
        }
        Integer clueId = clueReq.getClueId();
        ClueResp clue = clueMapper.getClue(clueId);
        if (clueId == null || clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        clueReq.setVersion(clue.getVersion());
        clueReq.setUpdateTime(DateUtils.getDate());
        int editClue = clueMapper.editClue(clueReq);
        if (editClue != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }

        EmpResp emp = empMapper.getEmp(clueReq.getEmpId());
        ClueFollowLogResp clueFollowLogResp = new ClueFollowLogResp();
        clueFollowLogResp.setClueId(clueId);
        clueFollowLogResp.setClueFollowTitle("线索转移");
        clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
        clueFollowLogResp.setClueFollowPerson(empByToken.getEmpName());
        clueFollowLogResp.setClueFollowContent("该线索被【"+empByToken.getEmpName()+
                "】转移给【"+emp.getEmpName()+"】");
        clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
        int addClueFollow = clueFollowLogMapper.addClueFollow(clueFollowLogResp);
        if (addClueFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response(editClue);
    }

    @Override
    public ResultVo shareClue(Integer clueId,String token) {
        ClueResp clue = clueMapper.getClue(clueId);
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        EmpResp empByToken = empMapper.getEmpByToken(token);
        if (empByToken.getEmpId() != clue.getEmpId()) {
            throw new SysException(ResultEnum.CLUE_NOT_BE_SHARE.getCode(),
                    ResultEnum.CLUE_NOT_BE_SHARE.getMessage());
        }
        int shareClue = clueMapper.shareClue(clueId,DateUtils.getDate());
        if (shareClue != 1) {
            throw new SysException(ResultEnum.CLUE_SHARE_ERROR.getCode(),
                    ResultEnum.CLUE_SHARE_ERROR.getMessage());
        }

        ClueFollowLogResp clueFollowLogResp = new ClueFollowLogResp();
        clueFollowLogResp.setClueId(clueId);
        clueFollowLogResp.setClueFollowTitle("线索共享");
        clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
        clueFollowLogResp.setClueFollowPerson(empByToken.getEmpName());
        clueFollowLogResp.setClueFollowContent("【"+empByToken.getEmpName()+"】将此线索共享至线索池");
        int addClueFollow = clueFollowLogMapper.addClueFollow(clueFollowLogResp);
        if (addClueFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(shareClue);
    }

    @Override
    public ResultVo invalidClue(Integer clueId, String token) {
        ClueResp clue = clueMapper.getClue(clueId);
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        EmpResp empByToken = empMapper.getEmpByToken(token);
        if (empByToken.getEmpId() != clue.getEmpId()) {
            throw new SysException(ResultEnum.CLUE_NOT_BE_SHARE.getCode(),
                    ResultEnum.CLUE_NOT_BE_SHARE.getMessage());
        }
        int invalidClue = clueMapper.invalidClue(clueId, DateUtils.getDate());
        if (invalidClue != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        ClueFollowLogResp clueFollowLogResp = new ClueFollowLogResp();
        clueFollowLogResp.setClueId(clueId);
        clueFollowLogResp.setClueFollowTitle("线索无效");
        clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
        clueFollowLogResp.setClueFollowPerson(empByToken.getEmpName());
        clueFollowLogResp.setClueFollowContent("该线索被【"+empByToken.getEmpName()+
                "设置为无效线索");
        int addClueFollow = clueFollowLogMapper.addClueFollow(clueFollowLogResp);
        if (addClueFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(invalidClue);
    }

    @Override
    public ResultVo getClueFollow(Integer clueId) {
        List<ClueFollowLogResp> clueFollowLogResps = clueFollowLogMapper.listClueFollow(clueId);
        return ResultUtils.response(clueFollowLogResps);
    }

    @Override
    public ResultVo newClueCount(ClueReq clueReq) {
        int count = clueMapper.newClueCount(clueReq);
        return ResultUtils.response(count);
    }

    @Override
    public ResultVo clueCount() {
        int clueCount = clueMapper.clueCount();
        return ResultUtils.response(clueCount);
    }

    public static String clueHandleResultFormat(Integer clueHandleResult) {
        if (clueHandleResult != null && clueHandleResult == 1) {
            return "公司线索";
        } else {
            return "个人线索";
        }
    }

}
