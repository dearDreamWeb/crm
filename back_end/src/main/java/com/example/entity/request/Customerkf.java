package com.example.entity.request;

import com.example.entity.response.CustomerResp;
import com.example.entity.response.EmpResp;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customerkf {
    private Integer customerId;
    private String customerTheme;//主题
    private String customerServicelx;//服务类型
    private String customerServicefs;//服务方式
    private Date customerData;
    private String customerKhcontacts;//客户联系人
    private String customerState;//状态
    private String customerExecutor;//执行人
    private String customerServicenr;//服务内容
    private String customerCustomerfk;//客户反馈
    private Integer cusId;
    private Integer empId;
    private Integer pageNum;
    private Integer pageSize;
    private CustomerResp customerResp;
    private EmpResp empResp;
    private Complaint complaint;
}
