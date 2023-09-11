package com.olm.management.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.olm.common.annotation.Excel;
import com.olm.common.core.domain.BaseEntity;

/**
 * 订单管理对象 t_order
 * 
 * @author cqf
 * @date 2023-09-10
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    /** 订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 粮仓id */
    @Excel(name = "粮仓id")
    private Long granaryId;

    /** 粮库id */
    @Excel(name = "粮库id")
    private Long grainDepotId;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operateId;

    /** 品种 */
    @Excel(name = "品种")
    private String goodsType;

    /** 入库量 */
    @Excel(name = "入库量")
    private Long inDbInt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
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
    public void setOperateId(String operateId) 
    {
        this.operateId = operateId;
    }

    public String getOperateId() 
    {
        return operateId;
    }
    public void setGoodsType(String goodsType) 
    {
        this.goodsType = goodsType;
    }

    public String getGoodsType() 
    {
        return goodsType;
    }
    public void setInDbInt(Long inDbInt) 
    {
        this.inDbInt = inDbInt;
    }

    public Long getInDbInt() 
    {
        return inDbInt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderTime", getOrderTime())
            .append("granaryId", getGranaryId())
            .append("grainDepotId", getGrainDepotId())
            .append("operateId", getOperateId())
            .append("goodsType", getGoodsType())
            .append("inDbInt", getInDbInt())
            .toString();
    }
}
