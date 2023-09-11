package com.olm.management.service;

import java.util.List;
import com.olm.management.domain.GranaryGrain;

/**
 * 粮库和粮仓管理Service接口
 * 
 * @author cqf
 * @date 2023-09-10
 */
public interface IGranaryGrainService 
{
    /**
     * 查询粮库和粮仓管理
     * 
     * @param id 粮库和粮仓管理主键
     * @return 粮库和粮仓管理
     */
    public GranaryGrain selectGranaryGrainById(Long id);

    /**
     * 查询粮库和粮仓管理列表
     * 
     * @param granaryGrain 粮库和粮仓管理
     * @return 粮库和粮仓管理集合
     */
    public List<GranaryGrain> selectGranaryGrainList(GranaryGrain granaryGrain);

    /**
     * 新增粮库和粮仓管理
     * 
     * @param granaryGrain 粮库和粮仓管理
     * @return 结果
     */
    public int insertGranaryGrain(GranaryGrain granaryGrain);

    /**
     * 修改粮库和粮仓管理
     * 
     * @param granaryGrain 粮库和粮仓管理
     * @return 结果
     */
    public int updateGranaryGrain(GranaryGrain granaryGrain);

    /**
     * 批量删除粮库和粮仓管理
     * 
     * @param ids 需要删除的粮库和粮仓管理主键集合
     * @return 结果
     */
    public int deleteGranaryGrainByIds(Long[] ids);

    /**
     * 删除粮库和粮仓管理信息
     * 
     * @param id 粮库和粮仓管理主键
     * @return 结果
     */
    public int deleteGranaryGrainById(Long id);
}
