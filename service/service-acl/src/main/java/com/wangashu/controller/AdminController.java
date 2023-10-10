package com.wangashu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangashu.model.acl.Admin;
import com.wangashu.result.Result;
import com.wangashu.service.AdminService;
import com.wangashu.service.RoleService;
import com.wangashu.utils.MD5;
import com.wangashu.vo.acl.AdminQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

     //用户列表
     @Autowired
     private RoleService roleService;

     @ApiOperation("获取用户角色")
     @GetMapping("toAssign/{adminId}")
     public  Result toAssign(@PathVariable Long adminId){
         //返回的map 集合包括两部分数据 所有角色 和用户分配列表

          Map<String,Object> map =roleService.getRoleByAdminId(adminId);
          return  Result.ok(map);

     }


     //参数有用户id 和多个角色id
     @ApiOperation("为用户分配角色")
     @PostMapping("doAssign")
     public  Result doAssign(@RequestParam Long adminId,
                             @RequestParam Long [] roleId){

         //
         roleService.saveAdminRole(adminId,roleId);
         return  Result.ok(null);



     }





    @ApiOperation("用户列表")
    @GetMapping("{current}/{limit}")
    public Result list(@PathVariable Long current,
                       @PathVariable long limit,
                       AdminQueryVo adminQueryVo){

        Page<Admin> adminPage = new Page<Admin>(current,limit);

        IPage<Admin> page =adminService.selectList(adminPage,adminQueryVo);

        return Result.ok(page);


    }

    //id用户查询

    @ApiOperation("更具用户id查询")
    @GetMapping("get/{id}")
    public  Result getId(@PathVariable Long id){
        Admin byId = adminService.getById(id);
        return  Result.ok(byId);
    }

    //添加用户

    @ApiOperation("添加用户")
    @PostMapping("save")
    public  Result save(@RequestBody Admin admin){

        // 获取用户输入的密码
          String password=admin.getPassword();

        // 对用户输入的密码进行加密
        String newPassword = MD5.encrypt(password);
        //再把加密的密码放入
        admin.setPassword(newPassword);
        adminService.save(admin);
        return Result.ok(null);


    }
    //修改用户
    @ApiOperation("修改用户")
    @PutMapping("update")
    public  Result update(@RequestBody Admin admin){

         adminService.updateById(admin);
         return Result.ok(null);
    }

    //id删除
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public  Result removeById(@PathVariable Long id) {

        adminService.removeById(id);
        return Result.ok(null);

    }

    //批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public  Result batchRemove(@RequestBody List<Long> ids) {
        adminService.removeByIds(ids);
        return Result.ok(null);

    }






}
