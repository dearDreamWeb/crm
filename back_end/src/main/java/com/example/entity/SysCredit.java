package com.example.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: pengjia
 * @date: 2020/10/25
 * @description:
 */
@Entity
@Table(name = "sys_credit", schema = "gradua_project", catalog = "")
public class SysCredit {
    private Integer creditId;
    private String creditCode;
    private String creditName;
    private String creditRemark;

    @Id
    @Column(name = "credit_id")
    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    @Basic
    @Column(name = "credit_code")
    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    @Basic
    @Column(name = "credit_name")
    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    @Basic
    @Column(name = "credit_remark")
    public String getCreditRemark() {
        return creditRemark;
    }

    public void setCreditRemark(String creditRemark) {
        this.creditRemark = creditRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysCredit sysCredit = (SysCredit) o;
        return Objects.equals(creditId, sysCredit.creditId) &&
                Objects.equals(creditCode, sysCredit.creditCode) &&
                Objects.equals(creditName, sysCredit.creditName) &&
                Objects.equals(creditRemark, sysCredit.creditRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditId, creditCode, creditName, creditRemark);
    }
}
