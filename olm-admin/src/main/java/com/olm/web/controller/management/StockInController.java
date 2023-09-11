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
import com.olm.management.domain.StockIn;
import com.olm.management.service.IStockInService;
import com.olm.common.utils.poi.ExcelUtil;
import com.olm.common.core.page.TableDataInfo;

/**
 * 入库Controller
 * 
 * @author cqf
 * @date 2023-09-09
 */
@RestController
@RequestMapping("/management/stockIn")
public class StockInController extends BaseController
{
    @Autowired
    private IStockInService stockInService;

    /**
     * 查询入库列表
     */
    @PreAuthorize("@ss.hasPermi('management:stockIn:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockIn stockIn)
    {
        startPage();
        List<StockIn> list = stockInService.selectStockInList(stockIn);
        return getDataTable(list);
    }

    /**
     * 导出入库列表
     */
    @PreAuthorize("@ss.hasPermi('management:stockIn:export')")
    @Log(title = "入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockIn stockIn)
    {
        List<StockIn> list = stockInService.selectStockInList(stockIn);
        ExcelUtil<StockIn> util = new ExcelUtil<StockIn>(StockIn.class);
        util.exportExcel(response, list, "入库数据");
    }

    /**
     * 获取入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:stockIn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stockInService.selectStockInById(id));
    }

    /**
     * 新增入库
     */
    @PreAuthorize("@ss.hasPermi('management:stockIn:add')")
    @Log(title = "入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockIn stockIn)
    {
        return toAjax(stockInService.insertStockIn(stockIn));
    }

    /**
     * 修改入库
     */
    @PreAuthorize("@ss.hasPermi('management:stockIn:edit')")
    @Log(title = "入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockIn stockIn)
    {
        return toAjax(stockInService.updateStockIn(stockIn));
    }

    /**
     * 删除入库
     */
    @PreAuthorize("@ss.hasPermi('management:stockIn:remove')")
    @Log(title = "入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stockInService.deleteStockInByIds(ids));
    }
}
