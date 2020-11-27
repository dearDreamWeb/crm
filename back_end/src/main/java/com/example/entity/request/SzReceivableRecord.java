package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzReceivableRecord {
    private Integer recoId;//回款记录编号
    private Integer recoReceivable;//回款状态:未回0 已回1 回款中2
    private String recoCaozuopeople;//处理人
    private Integer recordPlan;//计划回款期次
    private Date timePlan;//计划回款时间
    private Date recoTime;//实际回款时间
    private BigDecimal moneyPlan;//计划回款金额
    private BigDecimal recoMoney;//实际回款金额
    private String recoLiushui;//交易流水号
    private Date recoCreatetime;//创建时间
    private BigDecimal recoHasmoney;//已回款金额
    private Integer planId;//回款计划编号

    private SzReceivablePlan szReceivablePlan;

    @Override
    public String toString() {
        return "SzReceivableRecord{" +
                "recoId=" + recoId +
                ", recoReceivable=" + recoReceivable +
                ", recoCaozuopeople='" + recoCaozuopeople + '\'' +
                ", recordPlan=" + recordPlan +
                ", timePlan=" + timePlan +
                ", recoTime=" + recoTime +
                ", moneyPlan=" + moneyPlan +
                ", recoMoney=" + recoMoney +
                ", recoLiushui='" + recoLiushui + '\'' +
                ", recoCreatetime='" + recoCreatetime + '\'' +
                ", planId=" + planId +
                '}';
    }
}
