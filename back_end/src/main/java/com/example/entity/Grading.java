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
public class Grading {
    private Integer gradingId;
    private String gradingIcon;
    private String gradingName;
    private Date gradingTime;
    private Integer cusId;
    private Integer gradingEmpId;
}
