package com.olm.management.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.olm.common.annotation.Excel;
import com.olm.common.core.domain.BaseEntity;

/**
 * 客户管理对象 t_customer
 * 
 * @author cqf
 * @date 2023-09-10
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 粮仓id */
    @Excel(name = "粮仓id")
    private Long granaryId;

    /** 粮库id */
    @Excel(name = "粮库id")
    private Long grainDepotId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String company;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 手机号 */
    @Excel(name = "手机号")
    private Long cellphone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String accountInt;

    /** 总额度 */
    @Excel(name = "总额度")
    private BigDecimal totalAmount;

    /** 粮仓级别 0为省属库 1为直属库
1为直属库
 */
    @Excel(name = "粮仓级别")
    private Integer grainLevel;

    /** 操作人 */
    @Excel(name = "操作人")
    private String opearator;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGranaryId(Long granaryId) 
    {
        this.granaryId = granaryId;
    }

    public Long getGranaryId() 
    {
        return granaryId;
    }
    public void setGrainDepotId(Long grainDepotId) 
    {
        this.grainDepotId = grainDepotId;
    }

    public Long getGrainDepotId() 
    {
        return grainDepotId;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setCellphone(Long cellphone) 
    {
        this.cellphone = cellphone;
    }

    public Long getCellphone() 
    {
        return cellphone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAccountInt(String accountInt) 
    {
        this.accountInt = accountInt;
    }

    public String getAccountInt() 
    {
        return accountInt;
    }
    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }
    public void setGrainLevel(Integer grainLevel) 
    {
        this.grainLevel = grainLevel;
    }

    public Integer getGrainLevel() 
    {
        return grainLevel;
    }
    public void setOpearator(String opearator) 
    {
        this.opearator = opearator;
    }

    public String getOpearator() 
    {
        return opearator;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("granaryId", getGranaryId())
            .append("grainDepotId", getGrainDepotId())
            .append("company", getCompany())
            .append("name", getName())
            .append("idcard", getIdcard())
            .append("cellphone", getCellphone())
            .append("address", getAddress())
            .append("accountInt", getAccountInt())
            .append("totalAmount", getTotalAmount())
            .append("grainLevel", getGrainLevel())
            .append("opearator", getOpearator())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
