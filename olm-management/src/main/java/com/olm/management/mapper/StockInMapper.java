package com.olm.management.mapper;

import java.util.List;
import com.olm.management.domain.StockIn;

/**
 * 入库Mapper接口
 * 
 * @author cqf
 * @date 2023-09-09
 */
public interface StockInMapper 
{
    /**
     * 查询入库
     * 
     * @param id 入库主键
     * @return 入库
     */
    public StockIn selectStockInById(Long id);

    public StockIn selectMaxOrderId(StockIn stockIn);

    /**
     * 查询入库列表
     * 
     * @param stockIn 入库
     * @return 入库集合
     */
    public List<StockIn> selectStockInList(StockIn stockIn);

    /**
     * 新增入库
     * 
     * @param stockIn 入库
     * @return 结果
     */
    public int insertStockIn(StockIn stockIn);

    /**
     * 修改入库
     * 
     * @param stockIn 入库
     * @return 结果
     */
    public int updateStockIn(StockIn stockIn);

    /**
     * 删除入库
     * 
     * @param id 入库主键
     * @return 结果
     */
    public int deleteStockInById(Long id);

    /**
     * 批量删除入库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockInByIds(Long[] ids);
}
