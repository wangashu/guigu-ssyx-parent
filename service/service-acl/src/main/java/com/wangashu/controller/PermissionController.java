package com.wangashu.controller;

import com.wangashu.model.acl.Permission;
import com.wangashu.result.Result;
import com.wangashu.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "菜单管理")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

       //查询所有菜单
       @ApiOperation("查询所有菜单")
       @GetMapping("")
      public  Result list(){
           List<Permission> list =permissionService.queryAllPermission();
           return  Result.ok(list);
       }

       //添加菜单
     @ApiOperation("添加菜单")
     @PostMapping("save")
    public  Result save(@RequestBody Permission permission){
           permissionService.save(permission);
           return Result.ok(null);


     }

     //修改菜单
     @ApiOperation("修改菜单")
     @PutMapping("update")
     public  Result update(@RequestBody Permission permission){

           permissionService.updateById(permission);
           return Result.ok(null);

     }

     //递归删除菜单
     @ApiOperation("删除菜单")
     @DeleteMapping("remove/{id}")
     public  Result remove(@PathVariable Long id){
           permissionService.removeChildById(id);
           return Result.ok(null);


     }


}
