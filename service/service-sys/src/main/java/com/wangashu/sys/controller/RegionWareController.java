package com.wangashu.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangashu.model.sys.RegionWare;
import com.wangashu.result.Result;
import com.wangashu.sys.service.RegionWareService;
import com.wangashu.vo.sys.RegionWareQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author wangashu
 * @since 2023-10-09
 */
@RestController
@RequestMapping("/admin/sys/regionWare")
@Api(tags = "开通区域接口")
@CrossOrigin
public class RegionWareController {

     @Autowired
    private RegionWareService regionWareService;

     //开通区域列表
    @ApiOperation("开通区域列表")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable  Long limit,
                       RegionWareQueryVo regionWareQueryVo){
        Page<RegionWare> pagePrarm = new Page<>(page, limit);
        IPage<RegionWare> pages =regionWareService.selectPageRagionWare(pagePrarm,regionWareQueryVo);

         return Result.ok(page);
    }

    //添加开通区域
     @ApiOperation("添加开通区域")
    @PostMapping("save")
    public  Result addRegionWare(@RequestBody RegionWare regionWare){

        regionWareService.saveRegionWare(regionWare);

        return  Result.ok(null);

     }


}

