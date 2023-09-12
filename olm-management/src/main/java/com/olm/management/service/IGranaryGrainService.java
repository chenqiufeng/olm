package com.olm.management.service;

import java.util.List;
import com.olm.management.domain.GranaryGrain;

/**
 * 粮仓和粮库对应Service接口
 * 
 * @author cqf
 * @date 2023-09-12
 */
public interface IGranaryGrainService 
{
    /**
     * 查询粮仓和粮库对应
     * 
     * @param id 粮仓和粮库对应主键
     * @return 粮仓和粮库对应
     */
    public GranaryGrain selectGranaryGrainById(Long id);

    /**
     * 查询粮仓和粮库对应列表
     * 
     * @param granaryGrain 粮仓和粮库对应
     * @return 粮仓和粮库对应集合
     */
    public List<GranaryGrain> selectGranaryGrainList(GranaryGrain granaryGrain);

    /**
     * 新增粮仓和粮库对应
     * 
     * @param granaryGrain 粮仓和粮库对应
     * @return 结果
     */
    public int insertGranaryGrain(GranaryGrain granaryGrain);

    /**
     * 修改粮仓和粮库对应
     * 
     * @param granaryGrain 粮仓和粮库对应
     * @return 结果
     */
    public int updateGranaryGrain(GranaryGrain granaryGrain);

    /**
     * 批量删除粮仓和粮库对应
     * 
     * @param ids 需要删除的粮仓和粮库对应主键集合
     * @return 结果
     */
    public int deleteGranaryGrainByIds(Long[] ids);

    /**
     * 删除粮仓和粮库对应信息
     * 
     * @param id 粮仓和粮库对应主键
     * @return 结果
     */
    public int deleteGranaryGrainById(Long id);
}
