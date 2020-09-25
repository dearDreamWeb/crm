package com.example.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.example.common.enums.ResultEnum;
import com.example.entity.ResultVo;
import com.example.service.ALiYunService;
import com.example.util.ResultUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/25
 * @description:阿里云对象存储实现类
 */
@Service
public class ALiYunServiceImpl implements ALiYunService {
    private static final String[] IMAGE_TYPE = new String[]{".bmp",".jpg","jpeg",".gif","","png"};
    private static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    private static final String ACCESSKEYID = "LTAI4G3eapSb6RAXDFDJMCaX";
    private static final String ACCESSKEYSECRET = "G0CdG3aoWsv8aslngvtxPtBiUc9RWC";
    private static final String BUCKETNAME = "enia";
    private static final String URLPREFIX = "http://gradua.eblog.icu/";

    @Bean
    private OSS ossClient() {
        return new OSSClient(ENDPOINT,ACCESSKEYID,ACCESSKEYSECRET);
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public ResultVo upload(MultipartFile file) {
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {
            return ResultUtils.response("error");
        }
        String fileName = file.getOriginalFilename();
        String filePath = getFilePath(fileName);
        try {
            ossClient().putObject(BUCKETNAME,filePath,new ByteArrayInputStream(file.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.response("error");
        }
        return ResultUtils.response(URLPREFIX + filePath);
    }

    /**
     * 获取列表
     * @return
     */
    @Override
    public List<OSSObjectSummary> list() {
        final int maxKeys = 200;
        ObjectListing listing = ossClient().listObjects(new ListObjectsRequest(BUCKETNAME));
        List<OSSObjectSummary> sums = listing.getObjectSummaries();
        return sums;
    }

    /**
     * 删除文件
     * @param objectName
     * @return
     */
    @Override
    public ResultVo delete(String objectName) {
        ossClient().deleteObject(BUCKETNAME,objectName);
        return ResultUtils.response(ResultEnum.SUCCESS);
    }

    /**
     * 下载文件
     * @param os
     * @param objectName
     * @throws IOException
     */
    @Override
    public void exportOssFile(OutputStream os, String objectName) throws IOException {
        OSSObject ossObject = ossClient().getObject(BUCKETNAME,objectName);
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lentgth = 0;
        while ((lentgth = in.read(buffer)) != -1) {
            out.write(buffer,0,lentgth);
        }
        if (out != null) {
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }

    /**
     * 生成文件名
     * @param sourceFileName
     * @return
     */
    private String getFilePath(String sourceFileName) {
        return RandomUtils.nextInt(100,9999)+"."+
                org.apache.commons.lang3.StringUtils.substringAfterLast(sourceFileName,".");
    }
}
