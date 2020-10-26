package com.example.model.repository;

import com.example.entity.SysCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: pengjia
 * @date: 2020/10/24
 * @description:
 */
@Repository
public interface CreditRepository extends JpaRepository<SysCredit,Integer> {
}
