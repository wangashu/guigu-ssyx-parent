package com.wangashu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangashu.model.acl.Permission;
import io.swagger.annotations.ApiOperation;

import java.util.List;

public interface PermissionService extends IService<Permission> {

    //查询所有菜单

    List<Permission> queryAllPermission();


    //递归删除
    void removeChildById(Long id);
}
