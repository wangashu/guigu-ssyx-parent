package com.wangashu.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangashu.exception.SsyxException;
import com.wangashu.model.sys.RegionWare;
import com.wangashu.result.ResultCodeEnum;
import com.wangashu.sys.mapper.RegionWareMapper;
import com.wangashu.sys.service.RegionWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangashu.vo.sys.RegionWareQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author wangashu
 * @since 2023-10-09
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    @Override
    public IPage<RegionWare> selectPageRagionWare(Page<RegionWare> pagePrarm, RegionWareQueryVo regionWareQueryVo) {
         //获取查询条件
        String keyword=regionWareQueryVo.getKeyword();
        //封装查询条件
        //根据区域名称或者仓库名称查询
        LambdaQueryWrapper<RegionWare> wrapper  =new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            wrapper.like(RegionWare::getRegionName,keyword).or().like(RegionWare::getRegionName,keyword);

        }
        IPage<RegionWare> pages = baseMapper.selectPage(pagePrarm, wrapper);
        return pages;

    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {

        //查询是否开通区域
        LambdaQueryWrapper<RegionWare> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(RegionWare::getRegionId,regionWare.getRegionId());

        Integer count = baseMapper.selectCount(wrapper);
        if (count>0){
                //手动 抛出异常
            throw  new SsyxException(ResultCodeEnum.REGION_OPEN);
        }


    }
}
