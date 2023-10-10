package com.wangashu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangashu.model.acl.Admin;
import com.wangashu.vo.acl.AdminQueryVo;

public interface AdminService extends IService<Admin> {
    IPage<Admin> selectList(Page<Admin> adminPage, AdminQueryVo adminQueryVo);
}
