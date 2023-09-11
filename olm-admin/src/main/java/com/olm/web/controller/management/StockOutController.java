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
import com.olm.management.domain.StockOut;
import com.olm.management.service.IStockOutService;
import com.olm.common.utils.poi.ExcelUtil;
import com.olm.common.core.page.TableDataInfo;

/**
 * 出库Controller
 * 
 * @author cqf
 * @date 2023-09-10
 */
@RestController
@RequestMapping("/management/stockOut")
public class StockOutController extends BaseController
{
    @Autowired
    private IStockOutService stockOutService;

    /**
     * 查询出库列表
     */
    @PreAuthorize("@ss.hasPermi('management:stockOut:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockOut stockOut)
    {
        startPage();
        List<StockOut> list = stockOutService.selectStockOutList(stockOut);
        return getDataTable(list);
    }

    /**
     * 导出出库列表
     */
    @PreAuthorize("@ss.hasPermi('management:stockOut:export')")
    @Log(title = "出库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockOut stockOut)
    {
        List<StockOut> list = stockOutService.selectStockOutList(stockOut);
        ExcelUtil<StockOut> util = new ExcelUtil<StockOut>(StockOut.class);
        util.exportExcel(response, list, "出库数据");
    }

    /**
     * 获取出库详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:stockOut:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stockOutService.selectStockOutById(id));
    }

    /**
     * 新增出库
     */
    @PreAuthorize("@ss.hasPermi('management:stockOut:add')")
    @Log(title = "出库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockOut stockOut)
    {
        return toAjax(stockOutService.insertStockOut(stockOut));
    }

    /**
     * 修改出库
     */
    @PreAuthorize("@ss.hasPermi('management:stockOut:edit')")
    @Log(title = "出库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockOut stockOut)
    {
        return toAjax(stockOutService.updateStockOut(stockOut));
    }

    /**
     * 删除出库
     */
    @PreAuthorize("@ss.hasPermi('management:stockOut:remove')")
    @Log(title = "出库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stockOutService.deleteStockOutByIds(ids));
    }
}
