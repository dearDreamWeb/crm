package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsReq {
    private Integer contactsId;
    private String contactsName;
    private Date birthday;
    private Integer sex;
    private String contactsPhone;
    private String wechat;
    private String qq;
    private String email;
    private String remark;
    private Integer version;
    private Date createTime;
    private Date updateTIme;
    private Integer isDelete;
    private String contactsDictType;
    private Integer cusId;
    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;

    public ContactsReq(String contactsName, String contactsPhone, String wechat, String qq) {
        this.contactsName = contactsName;
        this.contactsPhone = contactsPhone;
        this.wechat = wechat;
        this.qq = qq;
    }
}
