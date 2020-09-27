package com.example.util;

import com.example.entity.response.MenuResp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/23
 * @description:
 */
public class TreeUtils {
    public static List<MenuResp> listToMenuTree(List<MenuResp> list) {
        List<MenuResp> treeList = new ArrayList<>();
        for (MenuResp tree : list) {
            if (tree.getPid() == 0) {
                treeList.add(findChildren(tree,list));
            }
        }
        return treeList;
    }

    private static MenuResp findChildren(MenuResp tree,List<MenuResp> list) {
        for (MenuResp node : list) {
            if (node.getPid().equals(tree.getMenuId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<MenuResp>());
                }
                tree.getChildren().add(findChildren(node,list));
            }
        }
        return tree;
    }
}
