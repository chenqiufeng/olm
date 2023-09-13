package com.olm.management.service.impl;

import java.util.List;
import java.util.Objects;

import com.olm.common.core.domain.model.LoginUser;
import com.olm.common.utils.DateUtils;
import com.olm.common.utils.SecurityUtils;
import com.olm.management.domain.GranaryGrain;
import com.olm.management.domain.StockIn;
import com.olm.management.mapper.GranaryGrainMapper;
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
public class StockOutServiceImpl implements IStockOutService {
    @Autowired
    private StockOutMapper stockOutMapper;

    @Autowired
    private GranaryGrainMapper granaryGrainMapper;


    /**
     * 查询出库
     *
     * @param id 出库主键
     * @return 出库
     */
    @Override
    public StockOut selectStockOutById(Long id) {
        return stockOutMapper.selectStockOutById(id);
    }

    /**
     * 查询出库列表
     *
     * @param stockOut 出库
     * @return 出库
     */
    @Override
    public List<StockOut> selectStockOutList(StockOut stockOut) {
        if (!Objects.isNull(stockOut.getOrderTimeStart())) {
            String orderTimeStart = stockOut.getOrderTimeStart()+  " 00:00:00";
            stockOut.setOrderTimeStart(orderTimeStart);
        }
        if (!Objects.isNull(stockOut.getOrderTimeEnd())) {
            String orderTimeEnd = stockOut.getOrderTimeEnd()+  " 23:59:59";
            stockOut.setOrderTimeEnd(orderTimeEnd);
        }
        List<StockOut> stockOuts = stockOutMapper.selectStockOutList(stockOut);
        for (StockOut stockOutTemp : stockOuts) {
            GranaryGrain granaryGrain = granaryGrainMapper.selectGranaryGrainById(stockOutTemp.getGrainDepotId());
            if (!Objects.isNull(granaryGrain)) {
                stockOutTemp.setGrainDepotName(granaryGrain.getName());
            }
            granaryGrain = granaryGrainMapper.selectGranaryGrainById(stockOutTemp.getGranaryId());
            if (!Objects.isNull(granaryGrain)) {
                stockOutTemp.setGranaryName(granaryGrain.getName());
            }
        }
        return stockOuts;
    }

    /**
     * 新增出库
     *
     * @param stockOut 出库
     * @return 结果
     */
    @Override
    public int insertStockOut(StockOut stockOut) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        stockOut.setOperator(loginUser.getUsername());
        stockOut.setFirstPoundWorker(loginUser.getUsername());
        stockOut.setSecondPoundWorker(loginUser.getUsername());
        stockOut.setOrderId(genOrderId(stockOut));
        stockOut.setStatus("4");
        stockOut.setDeptId(loginUser.getDeptId().intValue());
        return stockOutMapper.insertStockOut(stockOut);
    }

    /**
     * 生成orderId
     *
     * @param stockOut
     * @return
     */
    private String genOrderId(StockOut stockOut) {
        String currentDate = DateUtils.dateTime();
        String orderId = currentDate + stockOut.getGrainDepotId().toString() + stockOut.getGranaryId().toString();
        String currentDateTime = DateUtils.getDate() + " 00:00:00";
        stockOut.setCreatedTime(DateUtils.parseDate(currentDateTime));
        StockOut stockOutRes = stockOutMapper.selectMaxOrderId(stockOut);
        if (Objects.isNull(stockOutRes)) {
            orderId = orderId + "00001";
        } else {
            long orderIdLong = Long.parseLong(stockOutRes.getOrderId());
            orderIdLong++;
            orderId = String.valueOf(orderIdLong);
        }
        return orderId;
    }

    /**
     * 修改出库
     *
     * @param stockOut 出库
     * @return 结果
     */
    @Override
    public int updateStockOut(StockOut stockOut) {
        return stockOutMapper.updateStockOut(stockOut);
    }

    /**
     * 批量删除出库
     *
     * @param ids 需要删除的出库主键
     * @return 结果
     */
    @Override
    public int deleteStockOutByIds(Long[] ids) {
        return stockOutMapper.deleteStockOutByIds(ids);
    }

    /**
     * 删除出库信息
     *
     * @param id 出库主键
     * @return 结果
     */
    @Override
    public int deleteStockOutById(Long id) {
        return stockOutMapper.deleteStockOutById(id);
    }
}
