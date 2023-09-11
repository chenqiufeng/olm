package com.olm.management.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.olm.common.annotation.Excel;
import com.olm.common.core.domain.TreeEntity;

/**
 * 粮库和粮仓管理对象 t_granary_grain
 * 
 * @author cqf
 * @date 2023-09-10
 */
public class GranaryGrain extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 粮仓名称 */
    @Excel(name = "粮仓名称")
    private String name;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parentId", getParentId())
            .toString();
    }
}
