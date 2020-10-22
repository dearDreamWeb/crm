package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Complaint;
import com.example.entity.request.Customerkf;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */
public interface CustomerkfService {
    ResultVo addCustomerkf(Customerkf customerkf);
    ResultVo updateCustomerkf(Customerkf customerkfReq);
    ResultVo deleteCustomerkf(Integer customerId);
    ResultVo listCustomerkf(Customerkf customerkf);
    ResultVo getCustomerkf(Integer customerId);
}
