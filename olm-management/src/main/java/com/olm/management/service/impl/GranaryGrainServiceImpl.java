package com.olm.management.service.impl;

import java.util.List;

import com.olm.common.core.domain.entity.SysDept;
import com.olm.common.core.domain.model.LoginUser;
import com.olm.common.utils.SecurityUtils;
import com.olm.management.domain.Customer;
import com.olm.management.mapper.CustomerMapper;
import com.olm.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olm.management.mapper.GranaryGrainMapper;
import com.olm.management.domain.GranaryGrain;
import com.olm.management.service.IGranaryGrainService;

/**
 * 粮仓和粮库对应Service业务层处理
 * 
 * @author cqf
 * @date 2023-09-12
 */
@Service
public class GranaryGrainServiceImpl implements IGranaryGrainService 
{
    @Autowired
    private GranaryGrainMapper granaryGrainMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询粮仓和粮库对应
     * 
     * @param id 粮仓和粮库对应主键
     * @return 粮仓和粮库对应
     */
    @Override
    public GranaryGrain selectGranaryGrainById(Long id)
    {
        return granaryGrainMapper.selectGranaryGrainById(id);
    }

    /**
     * 查询粮仓和粮库对应列表
     * 
     * @param granaryGrain 粮仓和粮库对应
     * @return 粮仓和粮库对应
     */
    @Override
    public List<GranaryGrain> selectGranaryGrainList(GranaryGrain granaryGrain)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        granaryGrain.setCustomerId(loginUser.getDeptId());
        List<GranaryGrain> granaryGrainList = granaryGrainMapper.selectGranaryGrainList(granaryGrain);
        for (GranaryGrain granaryGrainTemp: granaryGrainList) {
            SysDept sysDept = sysDeptMapper.selectDeptById(granaryGrainTemp.getCustomerId());
            granaryGrainTemp.setCustomerName(sysDept.getDeptName());
        }
        return granaryGrainList;
    }

    /**
     * 新增粮仓和粮库对应
     * 
     * @param granaryGrain 粮仓和粮库对应
     * @return 结果
     */
    @Override
    public int insertGranaryGrain(GranaryGrain granaryGrain)
    {
        return granaryGrainMapper.insertGranaryGrain(granaryGrain);
    }

    /**
     * 修改粮仓和粮库对应
     * 
     * @param granaryGrain 粮仓和粮库对应
     * @return 结果
     */
    @Override
    public int updateGranaryGrain(GranaryGrain granaryGrain)
    {
        return granaryGrainMapper.updateGranaryGrain(granaryGrain);
    }

    /**
     * 批量删除粮仓和粮库对应
     * 
     * @param ids 需要删除的粮仓和粮库对应主键
     * @return 结果
     */
    @Override
    public int deleteGranaryGrainByIds(Long[] ids)
    {
        return granaryGrainMapper.deleteGranaryGrainByIds(ids);
    }

    /**
     * 删除粮仓和粮库对应信息
     * 
     * @param id 粮仓和粮库对应主键
     * @return 结果
     */
    @Override
    public int deleteGranaryGrainById(Long id)
    {
        return granaryGrainMapper.deleteGranaryGrainById(id);
    }
}
