package com.wangashu.utils;

import com.wangashu.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {


    public  static List<Permission> bulidPermission(List<Permission> allList){

        List<Permission> tress=new ArrayList<>();

          //遍布所有的菜单集合list集合 ，得到第一层数据，pid=0
        for (Permission  permission:allList) {

            if (permission.getPid()==0){
                permission.setLevel(1);
                //调用方法从第一层开始
                tress.add(findChild(permission,allList));
            }

        }
         return  tress;
    }

    //递归往下找，找子节点
// permission上层节点，从这里往下找/ / aLllist所有菜单

    private static Permission findChild(Permission permission, List<Permission> allList) {


     permission.setChildren(new ArrayList<Permission>());


        for (Permission it: allList) {
            ////当前节点id = pid是否一样
            if (permission.getId().longValue()==it.getPid().longValue()){

          int level=permission.getLevel()+1;
          it.setLevel(level);
          if (permission.getChildren()==null){
              permission.setChildren(new ArrayList<>());

          }

          permission.getChildren().add(findChild(it,allList));
        }
        }
     return  permission;

    }
}

