package com.example.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.example.entity.ResultVo;
import com.example.service.ALiYunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/25
 * @description:阿里云对象存储控制层
 */
@RestController
@RequestMapping("/aliyun")
public class ALiYunController {
    @Autowired
    private ALiYunService aLiYunService;

    @RequestMapping("/upload")
    public ResultVo upload(@RequestParam("file")MultipartFile file) {
        return aLiYunService.upload(file);
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam("fileName")String objectName) {
        return aLiYunService.delete(objectName);
    }

    @RequestMapping("/list")
    public List<OSSObjectSummary> list() {
        return aLiYunService.list();
    }

    @RequestMapping("/download")
    public void download(@RequestParam("fileName")String objectName,
                         HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition","arrachment;fileName="+
                new String(objectName.getBytes(),"ISO-8859-1"));
        aLiYunService.exportOssFile(response.getOutputStream(),objectName);
    }
}
