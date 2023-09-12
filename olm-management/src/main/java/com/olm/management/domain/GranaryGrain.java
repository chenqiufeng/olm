package com.olm.management.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.olm.common.annotation.Excel;
import com.olm.common.core.domain.BaseEntity;

/**
 * 粮仓和粮库对应对象 t_granary_grain
 * 
 * @author cqf
 * @date 2023-09-12
 */
public class GranaryGrain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 粮仓名称 */
    @Excel(name = "粮仓名称")
    private String name;

    /** 0为粮仓，非0存父id表示所属关系 */
    @Excel(name = "0为粮仓，非0存父id表示所属关系")
    private Long parentId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("customerId", getCustomerId())
            .toString();
    }
}
