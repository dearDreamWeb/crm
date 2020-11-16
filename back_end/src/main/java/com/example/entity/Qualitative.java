package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualitative {
    private Integer qualitativeId;
    private String qualitativeIcon;
    private String qualitativeName;
    private Date qualitativeTime;
    private Integer cusId;
    private Integer qualitationEmpId;
}
