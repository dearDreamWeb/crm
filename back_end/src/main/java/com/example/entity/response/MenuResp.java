package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/26
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResp {
    private Integer menuId;
    private String menuName;
    private Integer pid;
    private String pids;
    private String url;
    private String icon;
    private String perms;
    private Integer menuType;
    private Integer isShow;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private List<Integer> pidList;
    private List<MenuResp> children;
}
