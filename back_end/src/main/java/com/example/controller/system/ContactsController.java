package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ContactsReq;
import com.example.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/10/21
 * @description:
 */
@RestController
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    private ContactsService contactsService;

    @SysLog("联系人添加")
    @PostMapping("/add")
    public ResultVo addContacts(@RequestBody ContactsReq contactsReq) {
        return contactsService.addContacts(contactsReq);
    }

    @SysLog("联系人删除")
    @PostMapping("/del")
    public ResultVo delContacts(@RequestBody ContactsReq contactsReq) {
        return contactsService.delContacts(contactsReq);
    }

    @SysLog("联系人修改")
    @PostMapping("/edit")
    public ResultVo editContacts(@RequestBody ContactsReq contactsReq) {
        return contactsService.editContacts(contactsReq);
    }

    @GetMapping
    public ResultVo getContacts(@RequestParam("contactsId")Integer contactsId) {
        return contactsService.getContacts(contactsId);
    }

    @GetMapping("/list")
    public ResultVo listContacts(@RequestBody ContactsReq contactsReq) {
        return contactsService.listContacts(contactsReq);
    }

    @GetMapping("/get_by_cus")
    public ResultVo getByCustomerId(@RequestParam("customerId")Integer customerId) {
        return contactsService.getByCustomerId(customerId);
    }
}
