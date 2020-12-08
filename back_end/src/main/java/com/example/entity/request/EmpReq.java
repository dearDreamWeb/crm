package com.example.entity.request;

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
public class EmpReq {
    private Integer empId;
    private String empName;
    private String nickName;
    private String email;
    private Integer sex;
    private String passWord;
    private String phone;
    private Integer empStatus;
    private String token;
    private String headImage;
    private String remark;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer deptId;
    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
}
