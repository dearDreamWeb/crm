package com.example.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.common.enums.ResultEnum;
import com.example.util.UriCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:拦截器，根据token，校验权限。查询校验token，增删改校验权限
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private WebApplicationContext applicationContext;

    private static final String ADMIN = "admin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //路径匹配
        RequestMappingHandlerMapping mapping = applicationContext
                .getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        boolean uriBoolean = UriCheckUtils.checkUri(uri,map);
        if (!uriBoolean){
            return uriError(response);
        }
        return true;
    }

    /**
     * 路径匹配错误
     * @param response
     * @return
     * @throws IOException
     */
    private boolean uriError(HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        jsonObject.put("code",ResultEnum.URL_ERROR.getCode());
        jsonObject.put("message",ResultEnum.URL_ERROR.getMessage());
        String result = jsonObject.toString();
        writer.write(result);
        writer.close();
        return false;
    }

    /**
     * 没有权限，返回的信息
     * @param response
     * @return
     * @throws IOException
     */
    private boolean notAuth(HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        jsonObject.put("code", ResultEnum.NOT_AUTH.getCode());
        jsonObject.put("message",ResultEnum.NOT_AUTH.getMessage());
        String result = jsonObject.toString();
        writer.write(result);
        writer.close();
        return false;
    }
}
