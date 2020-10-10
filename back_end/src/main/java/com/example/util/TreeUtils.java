package com.example.util;

import com.example.entity.response.DictResp;
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
                treeList.add(findMenuChildren(tree,list));
            }
        }
        return treeList;
    }

    public static List<DictResp> listToDictTree(List<DictResp> list) {
        List<DictResp> treeList = new ArrayList<>();
        for (DictResp tree : list) {
            if (tree.getPid() == 0) {
                treeList.add(findDictChildren(tree,list));
            }
        }
        return treeList;
    }

    /*==============================================================*/

    private static MenuResp findMenuChildren(MenuResp tree,List<MenuResp> list) {
        for (MenuResp node : list) {
            if (node.getPid().equals(tree.getMenuId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<MenuResp>());
                }
                tree.getChildren().add(findMenuChildren(node,list));
            }
        }
        return tree;
    }

    private static DictResp findDictChildren(DictResp tree,List<DictResp> list) {
        for (DictResp node : list) {
            if (node.getPid().equals(tree.getDictId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<DictResp>());
                }
                tree.getChildren().add(findDictChildren(node,list));
            }
        }
        return tree;
    }
}
