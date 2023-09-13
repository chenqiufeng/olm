package com.olm.management.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.olm.common.annotation.Excel;
import com.olm.common.core.domain.BaseEntity;

/**
 * 出库对象 t_out_db
 * 
 * @author cqf
 * @date 2023-09-10
 */
public class StockOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 工号id */
    @Excel(name = "工号id")
    private String operator;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    /** 粮仓 */
    @Excel(name = "粮仓")
    private Long granaryId;

    /** 粮库 */
    @Excel(name = "粮库")
    private Long grainDepotId;

    /** 货主 */
    @Excel(name = "货主")
    private String ownerGoods;

    /** 品种 */
    @Excel(name = "品种")
    private String type;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String carId;

    /** 首磅(kg)  */
    @Excel(name = "首磅(kg) ")
    private BigDecimal firstPound;

    /** 次磅(kg)   */
    @Excel(name = "次磅(kg)  ")
    private BigDecimal secondPound;

    /** 净重(kg) */
    @Excel(name = "净重(kg)")
    private BigDecimal netWeight;

    /** 首磅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "首磅时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstPoundTime;

    /** 次磅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "次磅时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date secondPoundTime;

    /** 订单生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "订单生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 出库量 */
    @Excel(name = "出库量")
    private BigDecimal outDbInt;

    /** 出库量 */
    @Excel(name = "出库量")
    private String outDbWorker;

    /** 首磅员 */
    @Excel(name = "首磅员")
    private String firstPoundWorker;

    /** 次磅员 */
    @Excel(name = "次磅员")
    private String secondPoundWorker;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String agreementId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 质量级别 */
    @Excel(name = "质量级别")
    private Long qualityLevel;

    /** 备注 */
    @Excel(name = "备注")
    private String other;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    public Date getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        CreatedTime = createdTime;
    }

    public String getGrainDepotName() {
        return grainDepotName;
    }

    public void setGrainDepotName(String grainDepotName) {
        this.grainDepotName = grainDepotName;
    }

    @Excel(name = "粮库")
    private String grainDepotName;

    public String getGranaryName() {
        return granaryName;
    }

    public void setGranaryName(String granaryName) {
        this.granaryName = granaryName;
    }

    @Excel(name = "粮仓")
    private String granaryName;

    private Date CreatedTime;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(String orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    private String orderTimeStart;

    public String getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(String orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    private String orderTimeEnd;
    /**
     * 所属客户
     */
    private Integer deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
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
    public void setOwnerGoods(String ownerGoods) 
    {
        this.ownerGoods = ownerGoods;
    }

    public String getOwnerGoods() 
    {
        return ownerGoods;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCarId(String carId) 
    {
        this.carId = carId;
    }

    public String getCarId() 
    {
        return carId;
    }
    public void setFirstPound(BigDecimal firstPound) 
    {
        this.firstPound = firstPound;
    }

    public BigDecimal getFirstPound() 
    {
        return firstPound;
    }
    public void setSecondPound(BigDecimal secondPound) 
    {
        this.secondPound = secondPound;
    }

    public BigDecimal getSecondPound() 
    {
        return secondPound;
    }
    public void setNetWeight(BigDecimal netWeight) 
    {
        this.netWeight = netWeight;
    }

    public BigDecimal getNetWeight() 
    {
        return netWeight;
    }
    public void setFirstPoundTime(Date firstPoundTime) 
    {
        this.firstPoundTime = firstPoundTime;
    }

    public Date getFirstPoundTime() 
    {
        return firstPoundTime;
    }
    public void setSecondPoundTime(Date secondPoundTime) 
    {
        this.secondPoundTime = secondPoundTime;
    }

    public Date getSecondPoundTime() 
    {
        return secondPoundTime;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setOutDbInt(BigDecimal outDbInt) 
    {
        this.outDbInt = outDbInt;
    }

    public BigDecimal getOutDbInt() 
    {
        return outDbInt;
    }
    public void setOutDbWorker(String outDbWorker) 
    {
        this.outDbWorker = outDbWorker;
    }

    public String getOutDbWorker() 
    {
        return outDbWorker;
    }
    public void setFirstPoundWorker(String firstPoundWorker) 
    {
        this.firstPoundWorker = firstPoundWorker;
    }

    public String getFirstPoundWorker() 
    {
        return firstPoundWorker;
    }
    public void setSecondPoundWorker(String secondPoundWorker) 
    {
        this.secondPoundWorker = secondPoundWorker;
    }

    public String getSecondPoundWorker() 
    {
        return secondPoundWorker;
    }
    public void setAgreementId(String agreementId) 
    {
        this.agreementId = agreementId;
    }

    public String getAgreementId() 
    {
        return agreementId;
    }
    public void setQualityLevel(Long qualityLevel) 
    {
        this.qualityLevel = qualityLevel;
    }

    public Long getQualityLevel() 
    {
        return qualityLevel;
    }
    public void setOther(String other) 
    {
        this.other = other;
    }

    public String getOther() 
    {
        return other;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("operator", getOperator())
            .append("orderId", getOrderId())
            .append("granaryId", getGranaryId())
            .append("grainDepotId", getGrainDepotId())
            .append("ownerGoods", getOwnerGoods())
            .append("type", getType())
            .append("carId", getCarId())
            .append("firstPound", getFirstPound())
            .append("secondPound", getSecondPound())
            .append("netWeight", getNetWeight())
            .append("firstPoundTime", getFirstPoundTime())
            .append("secondPoundTime", getSecondPoundTime())
            .append("orderTime", getOrderTime())
            .append("outDbInt", getOutDbInt())
            .append("outDbWorker", getOutDbWorker())
            .append("firstPoundWorker", getFirstPoundWorker())
            .append("secondPoundWorker", getSecondPoundWorker())
            .append("agreementId", getAgreementId())
            .append("status", getStatus())
            .append("qualityLevel", getQualityLevel())
            .append("other", getOther())
            .append("time", getTime())
            .toString();
    }
}
