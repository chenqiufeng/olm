package com.olm.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olm.management.mapper.StockInMapper;
import com.olm.management.domain.StockIn;
import com.olm.management.service.IStockInService;

/**
 * 入库Service业务层处理
 * 
 * @author cqf
 * @date 2023-09-09
 */
@Service
public class StockInServiceImpl implements IStockInService 
{
    @Autowired
    private StockInMapper stockInMapper;

    /**
     * 查询入库
     * 
     * @param id 入库主键
     * @return 入库
     */
    @Override
    public StockIn selectStockInById(Long id)
    {
        return stockInMapper.selectStockInById(id);
    }

    /**
     * 查询入库列表
     * 
     * @param stockIn 入库
     * @return 入库
     */
    @Override
    public List<StockIn> selectStockInList(StockIn stockIn)
    {
        return stockInMapper.selectStockInList(stockIn);
    }

    /**
     * 新增入库
     * 
     * @param stockIn 入库
     * @return 结果
     */
    @Override
    public int insertStockIn(StockIn stockIn)
    {
        return stockInMapper.insertStockIn(stockIn);
    }

    /**
     * 修改入库
     * 
     * @param stockIn 入库
     * @return 结果
     */
    @Override
    public int updateStockIn(StockIn stockIn)
    {
        return stockInMapper.updateStockIn(stockIn);
    }

    /**
     * 批量删除入库
     * 
     * @param ids 需要删除的入库主键
     * @return 结果
     */
    @Override
    public int deleteStockInByIds(Long[] ids)
    {
        return stockInMapper.deleteStockInByIds(ids);
    }

    /**
     * 删除入库信息
     * 
     * @param id 入库主键
     * @return 结果
     */
    @Override
    public int deleteStockInById(Long id)
    {
        return stockInMapper.deleteStockInById(id);
    }
}
