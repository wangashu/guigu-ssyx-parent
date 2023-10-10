package com.wangashu.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangashu.model.sys.RegionWare;
import com.wangashu.vo.sys.RegionWareQueryVo;

/**
 * <p>
 * 城市仓库关联表 服务类
 * </p>
 *
 * @author wangashu
 * @since 2023-10-09
 */
public interface RegionWareService extends IService<RegionWare> {

    IPage<RegionWare> selectPageRagionWare(Page<RegionWare> pagePrarm, RegionWareQueryVo regionWareQueryVo);

    void saveRegionWare(RegionWare regionWare);
}
