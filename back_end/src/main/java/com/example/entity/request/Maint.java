package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liuleimin
 * @date: 2020/11/16
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maint {
    private Integer maintId;
    private Date maintrq;
    private String maintfzr;
    private String maintcz;
    private String maintjl;
}
