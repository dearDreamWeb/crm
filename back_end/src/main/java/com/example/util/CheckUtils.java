package com.example.util;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author: pengjia
 * @date: 2020/9/23
 * @description:参数校验工具类
 */
public class CheckUtils {
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validate(T obj){
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size()>0){
            SysException e = new SysException(ResultEnum.NOT_NULL.getCode(),
                    String.format(constraintViolations.iterator().next().getMessage()));
            throw e;
        }
    }
}
