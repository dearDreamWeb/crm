package com.example.controller;

import com.example.entity.ResultVo;
import com.example.util.QiNiuUtils;
import com.example.util.ResultUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: pengjia
 * @date: 2020/9/24
 * @description:
 */
@RestController
@RequestMapping("/qiniu")
public class TestQiNiuController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResultVo uploadFile(@RequestParam("file")MultipartFile file) {
        String url = QiNiuUtils.uploadFile(file);
        return ResultUtils.response(url);
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam("fileName")String fileKey) throws QiniuException {
        Response delete = QiNiuUtils.delete(fileKey);
        return ResultUtils.response(delete);
    }

}
