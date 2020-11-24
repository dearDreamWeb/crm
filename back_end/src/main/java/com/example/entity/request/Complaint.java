package com.example.entity.request;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.DictResp;
import com.example.entity.response.EmpResp;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
    private Integer complaintId;//投诉编号
    private String complaintZt;//投诉主题
    private String complaintReceptionist;//接待人
    private String complaintClassification;//分类
    private String complaintDescribe;//描述
    private Date complaintData;//时间
    private String complaintComplainants;//投诉人
    private String complaintUrgent;//紧急程度
    private String complaintHandle;//处理过程
    private String complaintHandlegc;//处理结果
    private String complaintCustomerfk;//客户反馈
    private String complaintHfvisit;//回访确认
    private String startDate;
    private String endDate;
    private Integer cusId;//客户id
    private Integer empId;
    private Integer dictId;
    private Integer pageNum;
    private Integer pageSize;
    private CustomerResp customerResp;
    private DictResp dictResp;
    private EmpResp empResp;

}
