package com.olm.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olm.management.mapper.GranaryGrainMapper;
import com.olm.management.domain.GranaryGrain;
import com.olm.management.service.IGranaryGrainService;

/**
 * 粮库和粮仓管理Service业务层处理
 * 
 * @author cqf
 * @date 2023-09-10
 */
@Service
public class GranaryGrainServiceImpl implements IGranaryGrainService 
{
    @Autowired
    private GranaryGrainMapper granaryGrainMapper;

    /**
     * 查询粮库和粮仓管理
     * 
     * @param id 粮库和粮仓管理主键
     * @return 粮库和粮仓管理
     */
    @Override
    public GranaryGrain selectGranaryGrainById(Long id)
    {
        return granaryGrainMapper.selectGranaryGrainById(id);
    }

    /**
     * 查询粮库和粮仓管理列表
     * 
     * @param granaryGrain 粮库和粮仓管理
     * @return 粮库和粮仓管理
     */
    @Override
    public List<GranaryGrain> selectGranaryGrainList(GranaryGrain granaryGrain)
    {
        return granaryGrainMapper.selectGranaryGrainList(granaryGrain);
    }

    /**
     * 新增粮库和粮仓管理
     * 
     * @param granaryGrain 粮库和粮仓管理
     * @return 结果
     */
    @Override
    public int insertGranaryGrain(GranaryGrain granaryGrain)
    {
        return granaryGrainMapper.insertGranaryGrain(granaryGrain);
    }

    /**
     * 修改粮库和粮仓管理
     * 
     * @param granaryGrain 粮库和粮仓管理
     * @return 结果
     */
    @Override
    public int updateGranaryGrain(GranaryGrain granaryGrain)
    {
        return granaryGrainMapper.updateGranaryGrain(granaryGrain);
    }

    /**
     * 批量删除粮库和粮仓管理
     * 
     * @param ids 需要删除的粮库和粮仓管理主键
     * @return 结果
     */
    @Override
    public int deleteGranaryGrainByIds(Long[] ids)
    {
        return granaryGrainMapper.deleteGranaryGrainByIds(ids);
    }

    /**
     * 删除粮库和粮仓管理信息
     * 
     * @param id 粮库和粮仓管理主键
     * @return 结果
     */
    @Override
    public int deleteGranaryGrainById(Long id)
    {
        return granaryGrainMapper.deleteGranaryGrainById(id);
    }
}
