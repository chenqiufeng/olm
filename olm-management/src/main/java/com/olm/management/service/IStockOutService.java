package com.olm.management.service;

import java.util.List;
import com.olm.management.domain.StockOut;

/**
 * 出库Service接口
 * 
 * @author cqf
 * @date 2023-09-10
 */
public interface IStockOutService 
{
    /**
     * 查询出库
     * 
     * @param id 出库主键
     * @return 出库
     */
    public StockOut selectStockOutById(Long id);

    /**
     * 查询出库列表
     * 
     * @param stockOut 出库
     * @return 出库集合
     */
    public List<StockOut> selectStockOutList(StockOut stockOut);

    /**
     * 新增出库
     * 
     * @param stockOut 出库
     * @return 结果
     */
    public int insertStockOut(StockOut stockOut);

    /**
     * 修改出库
     * 
     * @param stockOut 出库
     * @return 结果
     */
    public int updateStockOut(StockOut stockOut);

    /**
     * 批量删除出库
     * 
     * @param ids 需要删除的出库主键集合
     * @return 结果
     */
    public int deleteStockOutByIds(Long[] ids);

    /**
     * 删除出库信息
     * 
     * @param id 出库主键
     * @return 结果
     */
    public int deleteStockOutById(Long id);
}
