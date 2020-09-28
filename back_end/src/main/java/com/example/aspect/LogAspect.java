package com.example.aspect;

import com.alibaba.fastjson.JSON;
import com.example.anno.SysLog;
import com.example.entity.OpLog;
import com.example.entity.response.EmpResp;
import com.example.model.mapper.EmpMapper;
import com.example.model.mapper.LogMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author: pengjia
 * @date: 2020/9/28
 * @description:记录系统操纵日志
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private LogMapper logMapper;

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint point, SysLog log) throws Throwable {
        Object proceed = point.proceed();//执行方法
        String title = log.value();//获取注解上的数据
        saveSysLog(point,title);//记录日志
        return proceed;
    }

    private void saveSysLog(ProceedingJoinPoint point, String title) {
        OpLog opLog = new OpLog();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        opLog.setMethod(className+"."+methodName+"()");
        //请求的参数
        String param = "";
        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            param = JSON.toJSONString(args[0]);
        }
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        String token = request.getHeader("X-Token");
        EmpResp empResp = empMapper.getEmpByToken(token);
        opLog.setIp(ip);
        opLog.setLogTitle(title);
        opLog.setContent(param);
        opLog.setUserId(empResp.getEmpId());
        logMapper.addOpLog(opLog);
    }

}
