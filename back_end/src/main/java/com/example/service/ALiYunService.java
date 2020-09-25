package com.example.service;

import com.aliyun.oss.model.OSSObjectSummary;
import com.example.entity.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/25
 * @description:阿里云对象存储接口
 */
public interface ALiYunService {
    ResultVo upload(MultipartFile file);
    List<OSSObjectSummary> list();
    ResultVo delete(String objectName);
    void exportOssFile(OutputStream os,String objectName) throws IOException;
}
