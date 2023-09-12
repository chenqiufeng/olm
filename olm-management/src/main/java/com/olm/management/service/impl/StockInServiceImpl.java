package com.olm.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import com.olm.common.core.domain.entity.SysDept;
import com.olm.common.core.domain.model.LoginUser;
import com.olm.common.utils.DateUtils;
import com.olm.common.utils.SecurityUtils;
import com.olm.management.domain.GranaryGrain;
import com.olm.management.mapper.GranaryGrainMapper;
import com.olm.system.mapper.SysDeptMapper;
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
public class StockInServiceImpl implements IStockInService {
    @Autowired
    private StockInMapper stockInMapper;

    @Autowired
    private GranaryGrainMapper granaryGrainMapper;

    /**
     * 查询入库
     *
     * @param id 入库主键
     * @return 入库
     */
    @Override
    public StockIn selectStockInById(Long id) {
        return stockInMapper.selectStockInById(id);
    }

    /**
     * 查询入库列表
     *
     * @param stockIn 入库
     * @return 入库
     */
    @Override
    public List<StockIn> selectStockInList(StockIn stockIn) {
        List<StockIn> stockIns = stockInMapper.selectStockInList(stockIn);
        for (StockIn stockInTemp : stockIns) {
            GranaryGrain granaryGrain = granaryGrainMapper.selectGranaryGrainById(stockInTemp.getGrainDepotId());
            if (!Objects.isNull(granaryGrain)) {
                stockInTemp.setGrainDepotName(granaryGrain.getName());
            }
            granaryGrain = granaryGrainMapper.selectGranaryGrainById(stockInTemp.getGranaryId());
            if (!Objects.isNull(granaryGrain)) {
                stockInTemp.setGranaryName(granaryGrain.getName());
            }

        }
        return stockIns;
    }

    /**
     * 新增入库
     *
     * @param stockIn 入库
     * @return 结果
     */
    @Override
    public int insertStockIn(StockIn stockIn) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        stockIn.setOperator(loginUser.getUsername());
        stockIn.setQualityWorker(loginUser.getUsername());
        stockIn.setFirstPoundWorker(loginUser.getUsername());
        stockIn.setSecondPoundWorker(loginUser.getUsername());
        stockIn.setOrderId(genOrderId(stockIn));
        stockIn.setStatus("6");
        stockIn.setRegisterNumber(stockIn.getOrderId());
        return stockInMapper.insertStockIn(stockIn);
    }

    /**
     * 生成orderId
     * @param stockIn
     * @return
     */
    private String genOrderId(StockIn stockIn) {
        String currentDate = DateUtils.dateTime();
        String orderId = currentDate + stockIn.getGrainDepotId().toString() + stockIn.getGranaryId().toString();
        String currentDateTime = DateUtils.getDate() + " 00:00:00";
        stockIn.setCreatedTime(DateUtils.parseDate(currentDateTime));
        StockIn stockInRes = stockInMapper.selectMaxOrderId(stockIn);
        if (Objects.isNull(stockInRes)) {
            orderId = orderId + "00001";
        } else {
            long orderIdLong = Long.parseLong(stockInRes.getOrderId());
            orderIdLong++;
            orderId = String.valueOf(orderIdLong);
        }
        return orderId;
    }

    /**
     * 修改入库
     *
     * @param stockIn 入库
     * @return 结果
     */
    @Override
    public int updateStockIn(StockIn stockIn) {
        return stockInMapper.updateStockIn(stockIn);
    }

    /**
     * 批量删除入库
     *
     * @param ids 需要删除的入库主键
     * @return 结果
     */
    @Override
    public int deleteStockInByIds(Long[] ids) {
        return stockInMapper.deleteStockInByIds(ids);
    }

    /**
     * 删除入库信息
     *
     * @param id 入库主键
     * @return 结果
     */
    @Override
    public int deleteStockInById(Long id) {
        return stockInMapper.deleteStockInById(id);
    }
}
