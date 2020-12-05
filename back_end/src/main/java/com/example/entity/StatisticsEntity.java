package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: pengjia
 * @date: 2020/12/4
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsEntity {
    private String name;
    private Integer value;
}
