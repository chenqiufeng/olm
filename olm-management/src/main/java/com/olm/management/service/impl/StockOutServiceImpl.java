package com.olm.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olm.management.mapper.StockOutMapper;
import com.olm.management.domain.StockOut;
import com.olm.management.service.IStockOutService;

/**
 * 出库Service业务层处理
 * 
 * @author cqf
 * @date 2023-09-10
 */
@Service
public class StockOutServiceImpl implements IStockOutService 
{
    @Autowired
    private StockOutMapper stockOutMapper;

    /**
     * 查询出库
     * 
     * @param id 出库主键
     * @return 出库
     */
    @Override
    public StockOut selectStockOutById(Long id)
    {
        return stockOutMapper.selectStockOutById(id);
    }

    /**
     * 查询出库列表
     * 
     * @param stockOut 出库
     * @return 出库
     */
    @Override
    public List<StockOut> selectStockOutList(StockOut stockOut)
    {
        return stockOutMapper.selectStockOutList(stockOut);
    }

    /**
     * 新增出库
     * 
     * @param stockOut 出库
     * @return 结果
     */
    @Override
    public int insertStockOut(StockOut stockOut)
    {
        return stockOutMapper.insertStockOut(stockOut);
    }

    /**
     * 修改出库
     * 
     * @param stockOut 出库
     * @return 结果
     */
    @Override
    public int updateStockOut(StockOut stockOut)
    {
        return stockOutMapper.updateStockOut(stockOut);
    }

    /**
     * 批量删除出库
     * 
     * @param ids 需要删除的出库主键
     * @return 结果
     */
    @Override
    public int deleteStockOutByIds(Long[] ids)
    {
        return stockOutMapper.deleteStockOutByIds(ids);
    }

    /**
     * 删除出库信息
     * 
     * @param id 出库主键
     * @return 结果
     */
    @Override
    public int deleteStockOutById(Long id)
    {
        return stockOutMapper.deleteStockOutById(id);
    }
}
