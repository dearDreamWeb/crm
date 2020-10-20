package com.example.entity.response;

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
public class ContactsResp {
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
    private CustomerResp customerResp;
}
