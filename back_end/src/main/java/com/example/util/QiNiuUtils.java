package com.example.util;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

/**
 * @author: pengjia
 * @date: 2020/9/24
 * @description:集成七牛云，上传图片
 */
public class QiNiuUtils {
    //密钥
    private static final String ACCESS_KEY = "xFxGG0o6rZe129GPodzx77HGwQWz_r4xIm7Gg-ts";
    private static final String SECRET_KEY = "YVsbx4ZeZ4VQINSgN6HFCzN-t7gXodq8-VRQyrsg";
    //存储空间
    private static final String BUCKETNAME = "tr1807gradua";
    //域名
    private static final String DOMAIN = "http://www.eblog.icu/";
    //密钥配置
    private static final Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
    private static Configuration configuration = new Configuration(Region.huanan());
    private static UploadManager uploadManager = new UploadManager(configuration);
    //采用默认策略，只需设置存储空间名
    public static String getUpToken() {
        return auth.uploadToken(BUCKETNAME);
    }

    //上传通用实现
    public static String uploadFile(MultipartFile file) {
        try {
            //文件流转字节流
            byte[] uploadBytes = file.getBytes();
            //获取随机文件名
            String fileName = getRandomCharacterAndNumber(10)+".jpg";
            Response response = uploadManager.put(uploadBytes,fileName,getUpToken());
            //解析上传结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            //外部访问的资源地址
            String resUrl = DOMAIN+putRet.key;
            return resUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getRandomCharacterAndNumber(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2)%2==0?"char":"num";
            if ("char".equalsIgnoreCase(charOrNum)){
                int choice = random.nextInt(2)%2==0?65:97;
                val+=(char)(choice+random.nextInt(26));
                val+=(char)(choice+random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)){
                val+=String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
