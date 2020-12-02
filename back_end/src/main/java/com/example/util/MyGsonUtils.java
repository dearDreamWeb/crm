package com.example.util;

import com.google.gson.Gson;

/**
 * @author: pengjia
 * @date: 2020/12/2
 * @description:
 */
public class MyGsonUtils {
    public static String initGson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
}
