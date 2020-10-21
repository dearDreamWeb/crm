package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.ContactsReq;

/**
 * @author: pengjia
 * @date: 2020/10/21
 * @description:
 */
public interface ContactsService {
    ResultVo addContacts(ContactsReq contactsReq);
    ResultVo delContacts(ContactsReq contactsReq);
    ResultVo editContacts(ContactsReq contactsReq);
    ResultVo getContacts(Integer contactsId);
    ResultVo listContacts(ContactsReq contactsReq);
}
