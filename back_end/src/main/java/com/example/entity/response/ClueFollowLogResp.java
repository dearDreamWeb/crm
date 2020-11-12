package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueFollowLogResp {
    private Integer clueFollowId;
    private Integer clueId;
    private String clueFollowTitle;
    private Date clueFollowTime;
    private String clueFollowPerson;
    private String clueFollowContent;
}
