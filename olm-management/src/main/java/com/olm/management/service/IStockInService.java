package com.olm.management.service;

import java.util.List;
import com.olm.management.domain.StockIn;

/**
 * 入库Service接口
 * 
 * @author cqf
 * @date 2023-09-09
 */
public interface IStockInService 
{
    /**
     * 查询入库
     * 
     * @param id 入库主键
     * @return 入库
     */
    public StockIn selectStockInById(Long id);

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
     * 批量删除入库
     * 
     * @param ids 需要删除的入库主键集合
     * @return 结果
     */
    public int deleteStockInByIds(Long[] ids);

    /**
     * 删除入库信息
     * 
     * @param id 入库主键
     * @return 结果
     */
    public int deleteStockInById(Long id);
}
