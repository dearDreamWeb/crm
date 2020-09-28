package com.example.aspect;

import com.example.anno.CheckPermissions;
import com.example.common.enums.ResultEnum;
import com.example.entity.ResultVo;
import com.example.entity.response.DeptResp;
import com.example.entity.response.EmpResp;
import com.example.entity.response.MenuResp;
import com.example.model.mapper.EmpMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: pengjia pengjiaLOVE
 * @date: 2020/9/26
 * @description:校验权限的注解的切面
 */
@Aspect
@Component
public class CheckPermissionAspect {
    private static final String ADMIN = "admin";

    @Autowired
    private EmpMapper empMapper;

    @Around("@annotation(CheckPermissions)")
    public Object aroud(ProceedingJoinPoint point, CheckPermissions CheckPermissions) throws Throwable {
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
        EmpResp empByToken = empMapper.getEmpByToken(token);
        if (empByToken == null) {
            return notAuth();
        }
        //判断是否admin角色
        DeptResp dept = empByToken.getDept();
        if (dept != null) {
            String deptKey = dept.getDeptKey();
            if (ADMIN.equals(deptKey)) {
                isAdmin = true;
            }
        }
        if (isAdmin) {
            //用户包含admin角色，享有所有权限，通过
            return point.proceed();
        } else {
            List<MenuResp> menus = empByToken.getMenus();
            if (menus == null || menus.size() <= 0) {
                return notAuth();
            }
            for (MenuResp menu : menus) {
                if (menu != null) {
                    String perms = menu.getPerms();
                    if (requiresPerm.equals(perms)) {
                        isAuth = true;
                    }
                }
            }
        }
        if (!isAuth) {
            return notAuth();
        }
        return point.proceed();
    }

    private ResultVo notAuth() {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(ResultEnum.NOT_AUTH.getCode());
        resultVo.setMessage(ResultEnum.NOT_AUTH.getMessage());
        return resultVo;
    }
}
