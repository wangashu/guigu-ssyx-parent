package com.wangashu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangashu.mapper.RoleMapper;
import com.wangashu.model.acl.AdminRole;
import com.wangashu.model.acl.Role;
import com.wangashu.result.Result;
import com.wangashu.service.AdminRoleService;
import com.wangashu.service.RoleService;
import com.wangashu.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl  extends ServiceImpl<RoleMapper, Role> implements RoleService {
    //用户角色关系
    @Autowired
    private AdminRoleService adminRoleService;
    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {

         // 获取条件值
        String roleName=roleQueryVo.getRoleName();

        //创建mp操作对象
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper();


        //判断条价值是否为空，不为封装查询条件
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName,roleName);
        }
        //掉用方法实现分页查询
        Page<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        return rolePage;
    }

    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        //1 查询所有角色
        List<Role> allRolesList = baseMapper.selectList(null);

        //2 根据用户id查询用户分配角色列表
        //2.1 根据用户id查询 用户角色关系表 admin_role 查询用户分配角色id列表
        // List<AdminRole>
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        //设置查询条件，根据用户id adminId
        wrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);

        //2.2 通过第一步返回集合，获取所有角色id的列表List<AdminRole> -- List<Long>
        List<Long> roleIdsList =
                adminRoleList.stream()
                        .map(item -> item.getRoleId())
                        .collect(Collectors.toList());

        //2.3 创建新的list集合，用于存储用户配置角色
        List<Role> assignRoleList = new ArrayList<>();

        //2.4 遍历所有角色列表 allRolesList，得到每个角色
        //判断所有角色里面是否包含已经分配角色id，封装到2.3里面新的list集合
        for (Role role:allRolesList) {
            //判断
            if(roleIdsList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }

        //封装到map，返回
        Map<String, Object> result = new HashMap<>();
        //所有角色列表
        result.put("allRolesList",allRolesList);
        //用户分配角色列表
        result.put("assignRoles",assignRoleList);
        return result;
    }

    //为用户分配角色
    @Override
    public void saveAdminRole(Long adminId, Long[] roleId) {

        //删除用户已经分配过的角色id
        LambdaQueryWrapper<AdminRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(wrapper);

        // //2重新分配
        //l / adminId :1roleId: 2 3
        ////遍历多个角色id，得到每个角色id，拿着每个角色id +用户id添加用户角色关系表
       /* for (Long roleId: roleIds
             ) {
            AdminRole adminRole=new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleService.save(adminRole);




        }
*/

       //一次进行 多个
        List<AdminRole> list=new ArrayList<>();
        for (Long roleIds: roleId
             ) {
            AdminRole adminRole=new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleIds);
            adminRoleService.save(adminRole);
            list.add(adminRole);

        }

        adminRoleService.saveBatch(list);
    }
}
