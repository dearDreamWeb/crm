package com.example.entity.request;

import com.example.entity.response.EmpResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liuleimin
 * @date: 2020/10/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qa {
    private Integer qaId;
    private String qaWtproblem;//标题
    private String qaJjsolve;//答案
    private String qaCustomerfk;//录入人
    private Date qaData;//录入时间
    private String startDate;
    private Integer empId;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
    private EmpResp empResp;

}
