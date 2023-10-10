package com.wangashu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangashu.model.acl.Role;
import com.wangashu.result.Result;
import com.wangashu.service.RoleService;
import com.wangashu.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.List;

@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
@Api(tags = "权限控制")
public class RoleController {

    @Autowired
    private RoleService roleService;




     //获取所有角色 更具用户id查询分配的用户角色

    // 角色 列表 （条件分页查询）
    @ApiOperation("角色列表和角色分页查询")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo){

         // 创建page 对象
         //current 当前页
        // limit 每页显示的记录数
        Page<Role> pageParam=new Page<>(current,limit);

        IPage<Role> pageModel =roleService.selectRolePage(pageParam,roleQueryVo);

         return  Result.ok(pageModel);

    }

     //更具角色id查询
     @ApiOperation("根据角色id查询")
     @GetMapping("get/{id}")
    public  Result get(@PathVariable Long id){
         Role role= roleService.getById(id);
         return  Result.ok(role);
     }

     //添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
  //@RequestBody  接受 json格式数据 封装到 对象 里面
    public Result save(@RequestBody Role role){

        boolean is_success = roleService.save(role);

        if (is_success){
            return  Result.ok(null);
        }else {

            return Result.fail(null);
        }

    //修改角色

    }

    @ApiOperation("修改角色")
    @PutMapping("update")
    public  Result update(@RequestBody Role role){

        boolean is_success = roleService.updateById(role);
        if (is_success){
            return Result.ok(null);
        }else {
            return  Result.fail(null);
        }


    }

    //更具id删除角色
    @ApiOperation("根据id删除角色")
    @DeleteMapping("remove/{id}")
    public  Result delete(@PathVariable Long id){

        boolean is_success = roleService.removeById(id);
        if (is_success){
            return Result.ok(null);
        }else {
            return  Result.fail(null);
        }

    }

    //批量删除

    // JSON 的数组格式 对应的是 java的 集合

    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public  Result batchRemove(@RequestBody List<Long> ids){
        boolean is_success = roleService.removeByIds(ids);
        if (is_success){

            return Result.ok(null);
        }else {

            return Result.fail(null);
        }

    }





}
