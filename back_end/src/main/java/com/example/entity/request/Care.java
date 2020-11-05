package com.example.entity.request;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.DeptResp;
import com.example.entity.response.EmpResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liuleimin
 * @date: 2020/10/10
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Care {
    private Integer careId;
    private String careZt;//关怀主题
    private String carelxrcontacts;//联系人
    private Date careData;
    private String careexecutor;//执行人
    private String carenr;//关怀内容
    private String carecustomerfk;
    private Integer cusId;
    private Integer empId;
    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
    private CustomerResp customerResp;
    private EmpResp empResp;
    private DeptResp deptResp;
}
