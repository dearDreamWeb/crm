package com.example.entity.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
public class Care {
    private Integer careId;
    private String careZt;//关怀主题
    private String carelxrcontacts;//联系人
    private Date careData;
    private String careexecutor;//执行人
    private String carenr;//关怀内容
    private String carecustomerfk;
    private Integer cusId;

}
