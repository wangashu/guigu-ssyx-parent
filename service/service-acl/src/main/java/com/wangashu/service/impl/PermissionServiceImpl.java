package com.wangashu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangashu.mapper.PermissinMapper;
import com.wangashu.model.acl.Permission;
import com.wangashu.service.PermissionService;
import com.wangashu.utils.PermissionHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissinMapper, Permission> implements PermissionService {

    //查询所有菜单

    @Override
    public List<Permission> queryAllPermission() {

        //查询所有菜单集合


        List<Permission> allPermissionList = baseMapper.selectList(null);

        //转换要求数据格式
        List<Permission> result= PermissionHelper.bulidPermission(allPermissionList);

        return result;
    }

     //递归删除菜单
    @Override
    public void removeChildById(Long id) {

         //创建list集合要把所有都要删除的 子菜单 存入进去

         List<Long> idList=new ArrayList<>();
        idList.add(id);
        // //根据当前菜单id，获取当前菜单下面所有子菜单,//如果子菜单下面还有子菜单，都要获取到
        ///重点:递归找当前菜单子菜单

        this.getAllPermissionId(id,idList);
         baseMapper.deleteBatchIds(idList);

    }


    public  void getAllPermissionId(Long id,List<Long> idList ){
        //更具当前id 查询下面子菜单的id
        LambdaQueryWrapper<Permission> wapper=new LambdaQueryWrapper<>();
        wapper.eq(Permission::getPid,id);

        //查询当前菜单下的子菜单
        List<Permission> childList=baseMapper.selectList(wapper);

        //递归查询下面是否还有子菜单
        childList.stream().forEach(item->{

            idList.add(item.getId());
            this.getAllPermissionId(item.getId(),idList);

        });






    }

}
