package com.wangashu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangashu.model.acl.Role;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface RoleMapper extends BaseMapper<Role> {


}
