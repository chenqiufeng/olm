package com.olm.management.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.olm.common.annotation.Excel;
import com.olm.common.core.domain.BaseEntity;

/**
 * 入库对象 t_in_db
 * 
 * @author cqf
 * @date 2023-09-09
 */
public class StockIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    /** 粮仓id */
    @Excel(name = "粮仓id")
    private Long granaryId;

    /** 粮库id */
    @Excel(name = "粮库id")
    private Long grainDepotId;

    /** 货主 */
    @Excel(name = "货主")
    private String ownerGoods;

    /** 品种 */
    @Excel(name = "品种")
    private String goodsType;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carId;

    /** 首磅(kg) */
    @Excel(name = "首磅(kg)")
    private BigDecimal firstPound;

    /** 次磅(kg) */
    @Excel(name = "次磅(kg)")
    private BigDecimal secondPound;

    /** 净重(kg) */
    @Excel(name = "净重(kg)")
    private BigDecimal netWeight;

    /** 容重 */
    @Excel(name = "容重")
    private BigDecimal unitWeight;

    /** 水分 */
    @Excel(name = "水分")
    private BigDecimal moistureContent;

    /** 生霉粒 */
    @Excel(name = "生霉粒")
    private BigDecimal mildewPer;

    /** 杂质 */
    @Excel(name = "杂质")
    private BigDecimal impurity;

    /** 不完善粒 */
    @Excel(name = "不完善粒")
    private String unsoundKernel;

    /** 色泽气味 */
    @Excel(name = "色泽气味")
    private String colorAndOdor;

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

    public String getDeductionDeduction() {
        return deductionDeduction;
    }

    public void setDeductionDeduction(String deductionDeduction) {
        this.deductionDeduction = deductionDeduction;
    }

    /** 扣量情况(kg) */
    @Excel(name = "扣量情况(kg)")
    private String deductionDeduction;

    /** 入库量 */
    @Excel(name = "入库量")
    private Long inDbInt;

    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    /** 扣量 */
    @Excel(name = "扣量")
    private BigDecimal deduction;

    /** 质检员 */
    @Excel(name = "质检员")
    private String qualityWorker;

    /** 首磅员 */
    @Excel(name = "首磅员")
    private String firstPoundWorker;

    /** 次磅员 */
    @Excel(name = "次磅员")
    private String secondPoundWorker;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String agreementId;

    /** 受理编号 */
    @Excel(name = "受理编号")
    private String registerNumber;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 备注 */
    @Excel(name = "备注")
    private String other;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 所属客户
     */
    private Integer deptId;


    public String getGranaryName() {
        return granaryName;
    }

    public void setGranaryName(String granaryName) {
        this.granaryName = granaryName;
    }

    @Excel(name = "粮仓")
    private String granaryName;

    public String getGrainDepotName() {
        return grainDepotName;
    }

    public void setGrainDepotName(String grainDepotName) {
        this.grainDepotName = grainDepotName;
    }

    /** 粮库id */
    @Excel(name = "粮库")
    private String grainDepotName;


    public Date getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        CreatedTime = createdTime;
    }

    private Date CreatedTime;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
    public void setGoodsType(String goodsType) 
    {
        this.goodsType = goodsType;
    }

    public String getGoodsType() 
    {
        return goodsType;
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
    public void setUnitWeight(BigDecimal unitWeight) 
    {
        this.unitWeight = unitWeight;
    }

    public BigDecimal getUnitWeight() 
    {
        return unitWeight;
    }
    public void setMoistureContent(BigDecimal moistureContent) 
    {
        this.moistureContent = moistureContent;
    }

    public BigDecimal getMoistureContent() 
    {
        return moistureContent;
    }
    public void setMildewPer(BigDecimal mildewPer) 
    {
        this.mildewPer = mildewPer;
    }

    public BigDecimal getMildewPer() 
    {
        return mildewPer;
    }
    public void setImpurity(BigDecimal impurity) 
    {
        this.impurity = impurity;
    }

    public BigDecimal getImpurity() 
    {
        return impurity;
    }
    public void setUnsoundKernel(String unsoundKernel) 
    {
        this.unsoundKernel = unsoundKernel;
    }

    public String getUnsoundKernel() 
    {
        return unsoundKernel;
    }
    public void setColorAndOdor(String colorAndOdor) 
    {
        this.colorAndOdor = colorAndOdor;
    }

    public String getColorAndOdor() 
    {
        return colorAndOdor;
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

    public void setInDbInt(Long inDbInt) 
    {
        this.inDbInt = inDbInt;
    }

    public Long getInDbInt() 
    {
        return inDbInt;
    }

    public void setQualityWorker(String qualityWorker) 
    {
        this.qualityWorker = qualityWorker;
    }

    public String getQualityWorker() 
    {
        return qualityWorker;
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
    public void setRegisterNumber(String registerNumber) 
    {
        this.registerNumber = registerNumber;
    }

    public String getRegisterNumber() 
    {
        return registerNumber;
    }

    public void setOther(String other) 
    {
        this.other = other;
    }

    public String getOther() 
    {
        return other;
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
            .append("goodsType", getGoodsType())
            .append("carId", getCarId())
            .append("firstPound", getFirstPound())
            .append("secondPound", getSecondPound())
            .append("netWeight", getNetWeight())
            .append("unitWeight", getUnitWeight())
            .append("moistureContent", getMoistureContent())
            .append("mildewPer", getMildewPer())
            .append("impurity", getImpurity())
            .append("unsoundKernel", getUnsoundKernel())
            .append("colorAndOdor", getColorAndOdor())
            .append("firstPoundTime", getFirstPoundTime())
            .append("secondPoundTime", getSecondPoundTime())
            .append("orderTime", getOrderTime())
            .append("deductionDeduction", getDeductionDeduction())
            .append("inDbInt", getInDbInt())
            .append("deduction", getDeduction())
            .append("qualityWorker", getQualityWorker())
            .append("firstPoundWorker", getFirstPoundWorker())
            .append("secondPoundWorker", getSecondPoundWorker())
            .append("agreementId", getAgreementId())
            .append("registerNumber", getRegisterNumber())
            .append("status", getStatus())
            .append("other", getOther())
            .toString();
    }
}
