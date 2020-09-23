package com.example.entity;

import lombok.Data;
@Data
public class OpLog {
    private Integer id;
    private Data operData;
    private String content;
    private String method;
    private Integer userld;
    private String ip;
    private String title;
}
