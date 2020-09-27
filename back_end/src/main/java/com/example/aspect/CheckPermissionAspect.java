package com.example.aspect;

import com.example.anno.CheckPermissions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/9/26
 * @description:校验权限的注解的切面
 */
@Aspect
@Component
public class CheckPermissionAspect {
    private static final String ADMIN = "admin";



    public Object aroud(ProceedingJoinPoint point, CheckPermissions CheckPermissions) {
        //获取注解上的权限标识
        String requiresPerm = CheckPermissions.value();
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.
                getRequestAttributes()).getRequest();
        String token = request.getHeader("X-Token");
        //默认没有权限
        boolean isAuth = false;
        //默认不是admin角色
        boolean isAdmin = false;
        return null;
    }
}
