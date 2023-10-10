package com.wangashu.sys.controller;


import com.wangashu.model.sys.Ware;
import com.wangashu.result.Result;
import com.wangashu.sys.service.WareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author wangashu
 * @since 2023-10-09
 */
@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {

    @Autowired
    private WareService wareService;

      //查询所有仓库列表
     @GetMapping("findAllList")
     @ApiOperation("查询所有仓库列表")
    public Result findAllList(){
         List<Ware> list = wareService.list();

         return Result.ok(list);

     }




}

