package com.example.entity;

import lombok.Data;

/*
* 杨纤纤
* 09/23
* */
@Data
public class OpLog {
    private Integer id;
    private Data operDate;//日期
    private String content;//内容
    private String method;//请求方法名
    private Integer userId;//操作人
    private String ip;// 操作人ip
    private String title;//标题
}
