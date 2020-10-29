package com.example.entity.request;

import com.example.entity.response.EmpResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liuleimin
 * @date: 2020/10/21
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Syslog {
    private Integer logId;
    private String logTitle;//日志标题
    private Integer userId;//操作人id
    private String logMethod;//方法
    private String logContent;//操作内容
    private String logIp;//
    private String logUri;//
    private Date createTime;//时间
    private String startDate;
    private String endDate;
}
