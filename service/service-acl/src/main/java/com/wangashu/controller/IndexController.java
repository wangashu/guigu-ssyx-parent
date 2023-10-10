package com.wangashu.controller;


import com.wangashu.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin  //用于解决跨域 问题
public class IndexController {

    // 登录
    @ApiOperation("登录接口")
    @PostMapping("/login")
  public Result login(){
        Map<String,String> map=new HashMap<>();
        map.put("token","token-admin");

        return  Result.ok(map);
    }

    //获取用户信息
    @ApiOperation("获取信息接口")
     @GetMapping("/info")
    public  Result getInof(){
        Map<String,String> map=new HashMap<>();
        map.put("token","admin");
        map.put("avator","https://pic3.zhimg.com/v2-f4cacab46386a7fb655c8405d7f9bedf_720w.webp?source=d16d100b");
        return Result.ok(map);



    }

    //退出
    @ApiOperation("退出接口")
    @PostMapping("/logout")
    public  Result logout(){

        return  Result.ok(null);
    }

}
