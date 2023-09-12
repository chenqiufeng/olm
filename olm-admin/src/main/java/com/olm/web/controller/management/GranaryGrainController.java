package com.olm.web.controller.management;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.olm.common.annotation.Log;
import com.olm.common.core.controller.BaseController;
import com.olm.common.core.domain.AjaxResult;
import com.olm.common.enums.BusinessType;
import com.olm.management.domain.GranaryGrain;
import com.olm.management.service.IGranaryGrainService;
import com.olm.common.utils.poi.ExcelUtil;

/**
 * 粮库和粮仓管理Controller
 * 
 * @author cqf
 * @date 2023-09-10
 */
@RestController
@RequestMapping("/management/GranaryGrain")
public class GranaryGrainController extends BaseController
{
    @Autowired
    private IGranaryGrainService granaryGrainService;

    /**
     * 查询粮库和粮仓管理列表
     */
    @PreAuthorize("@ss.hasPermi('management:GranaryGrain:list')")
    @GetMapping("/list")
    public AjaxResult list(GranaryGrain granaryGrain)
    {
        List<GranaryGrain> list = granaryGrainService.selectGranaryGrainList(granaryGrain);
        return success(list);
    }

    /**
     * 导出粮库和粮仓管理列表
     */
    @PreAuthorize("@ss.hasPermi('management:GranaryGrain:export')")
    @Log(title = "粮库和粮仓管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GranaryGrain granaryGrain)
    {
        List<GranaryGrain> list = granaryGrainService.selectGranaryGrainList(granaryGrain);
        ExcelUtil<GranaryGrain> util = new ExcelUtil<GranaryGrain>(GranaryGrain.class);
        util.exportExcel(response, list, "粮库和粮仓管理数据");
    }

    /**
     * 获取粮库和粮仓管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:GranaryGrain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(granaryGrainService.selectGranaryGrainById(id));
    }

    /**
     * 新增粮库和粮仓管理
     */
    @PreAuthorize("@ss.hasPermi('management:GranaryGrain:add')")
    @Log(title = "粮库和粮仓管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GranaryGrain granaryGrain)
    {
        return toAjax(granaryGrainService.insertGranaryGrain(granaryGrain));
    }

    /**
     * 修改粮库和粮仓管理
     */
    @PreAuthorize("@ss.hasPermi('management:GranaryGrain:edit')")
    @Log(title = "粮库和粮仓管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GranaryGrain granaryGrain)
    {
        return toAjax(granaryGrainService.updateGranaryGrain(granaryGrain));
    }

    /**
     * 删除粮库和粮仓管理
     */
    @PreAuthorize("@ss.hasPermi('management:GranaryGrain:remove')")
    @Log(title = "粮库和粮仓管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(granaryGrainService.deleteGranaryGrainByIds(ids));
    }

    @GetMapping("/all")
    public AjaxResult list()
    {
        //List<GranaryGrain> list = granaryGrainService.selectAll();
        return null;
    }

    /**
     * 查询所有粮仓
     * @param granaryGrain
     * @return
     */
    @GetMapping("/listGranary")
    public AjaxResult listGranary(GranaryGrain granaryGrain)
    {
        List<GranaryGrain> list = granaryGrainService.selectGranaryGrainList(granaryGrain);
        return success(list);
    }

    /**
     * 查询所有粮库
     * @param granaryGrain
     * @return
     */
    @GetMapping("/listGrain")
    public AjaxResult listGrain(GranaryGrain granaryGrain)
    {
        granaryGrain.setParentId(0L);
        List<GranaryGrain> list = granaryGrainService.selectGranaryGrainList(granaryGrain);
        return success(list);
    }
}
