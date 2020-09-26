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
public class DeptResp {
    private Integer deptId;
    private String deptName;
    private String deptKey;
    private Integer verion;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private List<Integer> menuIds;
    private List<MenuResp> menus;
}
