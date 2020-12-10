package com.example.model.mapper;

import com.example.entity.request.CustomerReq;
import com.example.entity.response.CustomerResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/20
 * @description:
 */
@Repository
public interface CustomerMapper {
    int addCustomer(CustomerReq customerReq);
    int delCustomer(Integer cusId);
    int editCustomer(CustomerReq customerReq);
    CustomerResp getCustomer(Integer cusId);
    List<CustomerResp> listCustomer(CustomerReq customerReq);
    int addCustomerAndContacts(CustomerReq customerReq);
    CustomerResp getCustomerByName(String cusName);
    List<CustomerResp> listAll();
    List<CustomerResp> listCustomerByEmpId(@Param("customerReq") CustomerReq customerReq,
                                           @Param("empName") String empName);
    CustomerResp getBySaleId(Integer saleId);

    int newCustomerCount(CustomerReq customerReq);

    CustomerResp listByName(String cusName);
}
