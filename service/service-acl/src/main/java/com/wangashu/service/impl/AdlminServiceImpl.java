package com.wangashu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangashu.mapper.AdminMapper;
import com.wangashu.model.acl.Admin;
import com.wangashu.service.AdminService;
import com.wangashu.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdlminServiceImpl  extends ServiceImpl<AdminMapper,Admin> implements AdminService {


    @Override
    public IPage<Admin> selectList(Page<Admin> adminPage, AdminQueryVo adminQueryVo) {
          String username= adminQueryVo.getUsername();
          String name=adminQueryVo.getName();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(username)){

            queryWrapper.eq(Admin::getUsername,username);
        }

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like(Admin::getName,name);
        }
        Page<Admin> pages = baseMapper.selectPage(adminPage, queryWrapper);
        return  pages;


    }
}
