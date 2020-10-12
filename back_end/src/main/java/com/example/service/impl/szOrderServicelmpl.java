package com.example.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yangqianqian
 * @date: 2020/10/10
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class szOrderServicelmpl {
    
}
