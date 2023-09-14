package com.olm.management.service.impl;

import java.util.List;

import com.olm.common.core.domain.entity.SysDept;
import com.olm.common.core.domain.model.LoginUser;
import com.olm.common.utils.SecurityUtils;
import com.olm.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olm.management.mapper.CustomerMapper;
import com.olm.management.domain.Customer;
import com.olm.management.service.ICustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author cqf
 * @date 2023-09-10
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param customer 客户管理
     * @return 客户管理
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        customer.setOpearator(loginUser.getUsername());
        SysDept dept = new SysDept();
        dept.setParentId(0L);
        dept.setDeptName(customer.getCompany());
        dept.setAncestors("100");
        dept.setLeader(customer.getName());
        dept.setPhone(customer.getCellphone());
        dept.setCreateBy(loginUser.getUsername());
        sysDeptMapper.insertDept(dept);
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids)
    {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id)
    {
        return customerMapper.deleteCustomerById(id);
    }
}
