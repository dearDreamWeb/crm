package com.example.model.mapper;

import com.example.entity.request.MenuReq;
import com.example.entity.response.MenuResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Repository
public interface MenuMapper {
    int addMenu(MenuReq menuReq);
    int delMenu(Integer menuId);
    int editMenu(MenuReq menuReq);
    MenuResp getMenu(Integer menuId);
    List<MenuResp> listMenu(MenuReq menuReq);
    int checkMenu(String url);
}
