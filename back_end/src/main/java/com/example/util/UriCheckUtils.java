package com.example.util;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Map;
import java.util.Set;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:校验路径
 */
public class UriCheckUtils {
    public static boolean checkUri(String uri, Map<RequestMappingInfo, HandlerMethod> map) {
        boolean uriBoolean = false;
        for (RequestMappingInfo info : map.keySet()){
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns){
                if (uri.equals(url) && !"/error".equals(uri)){
                    uriBoolean = true;
                    break;
                }
            }
        }
        return uriBoolean;
    }
}
