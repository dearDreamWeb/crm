package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.ContactsReq;
import com.example.entity.response.ContactsResp;
import com.example.model.mapper.ContactsMapper;
import com.example.service.ContactsService;
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
 * @date: 2020/10/21
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContactsServiceImpl implements ContactsService {
    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public ResultVo addContacts(ContactsReq contactsReq) {
        CheckUtils.validate(contactsReq);
        int addContacts = contactsMapper.addContacts(contactsReq);
        if (addContacts != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addContacts);
    }

    @Override
    public ResultVo delContacts(ContactsReq contactsReq) {
        Integer contactsId = contactsReq.getContactsId();
        ContactsResp contacts = contactsMapper.getContacts(contactsId);
        if (contacts == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delContacts = contactsMapper.delContacts(contactsId);
        if (delContacts != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delContacts);
    }

    @Override
    public ResultVo editContacts(ContactsReq contactsReq) {
        Integer contactsId = contactsReq.getContactsId();
        ContactsResp contacts = contactsMapper.getContacts(contactsId);
        if (contacts == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        contactsReq.setVersion(contacts.getVersion());
        contactsReq.setUpdateTIme(DateUtils.getDate());
        int editContacts = contactsMapper.editContacts(contactsReq);
        if (editContacts != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editContacts);
    }

    @Override
    public ResultVo getContacts(Integer contactsId) {
        ContactsResp contacts = contactsMapper.getContacts(contactsId);
        return ResultUtils.response(contacts);
    }

    @Override
    public ResultVo listContacts(ContactsReq contactsReq) {
        Integer pageNum = contactsReq.getPageNum();
        Integer pageSize = contactsReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ContactsResp> contactsResps = contactsMapper.listContacts(contactsReq);
        PageInfo<ContactsResp> list = new PageInfo<>(contactsResps);
        return ResultUtils.response(list);
    }
}
