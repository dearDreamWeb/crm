package com.example.model.mapper;

import com.example.entity.request.ContactsReq;
import com.example.entity.response.ContactsResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/20
 * @description:
 */
@Repository
public interface ContactsMapper {
    int addContacts(ContactsReq contactsReq);
    int delContacts(Integer contactsId);
    int editContacts(ContactsReq contactsReq);
    ContactsResp getContacts(Integer contactsId);
    List<ContactsResp> listContacts(ContactsReq contactsReq);
    int addContactsAndCustomer(ContactsReq contactsReq);
    List<ContactsResp> getByCustomerId(Integer customerId);
}
