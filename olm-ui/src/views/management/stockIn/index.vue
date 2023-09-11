<template>

  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="订单编号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单时间">
        <el-date-picker
          v-model="daterangeOrderTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="粮仓">
        <el-cascader
          v-model="selectedGranaryGrain"
          :options="granaryAndGrainOptions"
          @change="handleGranaryChange"
          placeholder="请选择粮库/粮仓"
        ></el-cascader>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['management:stockIn:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['management:stockIn:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['management:stockIn:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['management:stockIn:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockInList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="粮仓id" align="center" prop="granaryId" />
      <el-table-column label="粮库id" align="center" prop="grainDepotId" />
      <el-table-column label="货主" align="center" prop="ownerGoods" />
      <el-table-column label="品种" align="center" prop="goodsType" />
      <el-table-column label="车牌号" align="center" prop="carId" />
      <el-table-column label="首磅(kg)" align="center" prop="firstPound" />
      <el-table-column label="次磅(kg)" align="center" prop="secondPound" />
      <el-table-column label="净重(kg)" align="center" prop="netWeight" />
      <el-table-column label="容重" align="center" prop="unitWeight" />
      <el-table-column label="水分" align="center" prop="moistureContent" />
      <el-table-column label="生霉粒" align="center" prop="mildewPer" />
      <el-table-column label="杂质" align="center" prop="impurity" />
      <el-table-column label="不完善粒" align="center" prop="unsoundKernel" />
      <el-table-column label="色泽气味" align="center" prop="colorAndOdor" />
      <el-table-column label="首磅时间" align="center" prop="firstPoundTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.firstPoundTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="次磅时间" align="center" prop="secondPoundTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.secondPoundTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单生成时间" align="center" prop="orderTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="扣量情况(kg)" align="center" prop="deductionDeduction" />
      <el-table-column label="入库量" align="center" prop="inDbInt" />
      <el-table-column label="扣量" align="center" prop="deduction" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="other" />
      <el-table-column label="操作人" align="center" prop="operator" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['management:stockIn:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['management:stockIn:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改入库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="订单编号" prop="orderId">
              <el-input v-model="form.orderId" placeholder="请输入订单编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="货主" prop="ownerGoods">
              <el-input v-model="form.ownerGoods" placeholder="请输入货主" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="车牌号" prop="carId">
              <el-input v-model="form.carId" placeholder="请输入车牌号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="首磅(kg)" prop="firstPound">
              <el-input v-model="form.firstPound" placeholder="请输入首磅(kg)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="次磅(kg)" prop="secondPound">
              <el-input v-model="form.secondPound" placeholder="请输入次磅(kg)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="净重(kg)" prop="netWeight">
              <el-input v-model="form.netWeight" placeholder="请输入净重(kg)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="容重" prop="unitWeight">
              <el-input v-model="form.unitWeight" placeholder="请输入容重" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="水分">
              <el-input v-model="form.moistureContent" :min-height="192"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生霉粒" prop="mildewPer">
              <el-input v-model="form.mildewPer" placeholder="请输入生霉粒" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="杂质" prop="impurity">
              <el-input v-model="form.impurity" placeholder="请输入杂质" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="不完善粒" prop="unsoundKernel">
              <el-input v-model="form.unsoundKernel" placeholder="请输入不完善粒" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="色泽气味" prop="colorAndOdor">
              <el-input v-model="form.colorAndOdor" placeholder="请输入色泽气味" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="首磅时间" prop="firstPoundTime">
              <el-date-picker clearable
                              v-model="form.firstPoundTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择首磅时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="次磅时间" prop="secondPoundTime">
              <el-date-picker clearable
                              v-model="form.secondPoundTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择次磅时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="扣量情况(kg)" prop="deductionDeduction">
              <el-input v-model="form.deductionDeduction" placeholder="请输入扣量情况(kg)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="入库量" prop="inDbInt">
              <el-input v-model="form.inDbInt" placeholder="请输入入库量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="扣量" prop="deduction">
              <el-input v-model="form.deduction" placeholder="请输入扣量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="质检员" prop="qualityWorker">
              <el-input v-model="form.qualityWorker" placeholder="请输入质检员" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="首磅员" prop="firstPoundWorker">
              <el-input v-model="form.firstPoundWorker" placeholder="请输入首磅员" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="次磅员" prop="secondPoundWorker">
              <el-input v-model="form.secondPoundWorker" placeholder="请输入次磅员" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="合同编号" prop="agreementId">
              <el-input v-model="form.agreementId" placeholder="请输入合同编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="受理编号" prop="registerNumber">
              <el-input v-model="form.registerNumber" placeholder="请输入受理编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="other">
              <el-input v-model="form.other" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStockIn, getStockIn, delStockIn, addStockIn, updateStockIn } from "@/api/management/stockIn";
export default {
  name: "StockIn",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 入库表格数据
      stockInList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 操作人时间范围
      daterangeOrderTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        orderTime: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
        granaryId: [
          { required: true, message: "粮仓id不能为空", trigger: "change" }
        ],
        grainDepotId: [
          { required: true, message: "粮库id不能为空", trigger: "change" }
        ],
        goodsType: [
          { required: true, message: "品种不能为空", trigger: "change" }
        ],
        firstPound: [
          { required: true, message: "首磅(kg)不能为空", trigger: "blur" }
        ],
        orderTime: [
          { required: true, message: "订单生成时间不能为空", trigger: "blur" }
        ],
        inDbInt: [
          { required: true, message: "入库量不能为空", trigger: "blur" }
        ],
        registerNumber: [
          { required: true, message: "受理编号不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        operator: [
          { required: true, message: "操作人不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询入库列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeOrderTime && '' != this.daterangeOrderTime) {
        this.queryParams.params["beginOrderTime"] = this.daterangeOrderTime[0];
        this.queryParams.params["endOrderTime"] = this.daterangeOrderTime[1];
      }
      listStockIn(this.queryParams).then(response => {
        this.stockInList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        orderId: null,
        granaryId: null,
        grainDepotId: null,
        ownerGoods: null,
        goodsType: null,
        carId: null,
        firstPound: null,
        secondPound: null,
        netWeight: null,
        unitWeight: null,
        moistureContent: null,
        mildewPer: null,
        impurity: null,
        unsoundKernel: null,
        colorAndOdor: null,
        firstPoundTime: null,
        secondPoundTime: null,
        orderTime: null,
        deductionDeduction: null,
        inDbInt: null,
        deduction: null,
        qualityWorker: null,
        firstPoundWorker: null,
        secondPoundWorker: null,
        agreementId: null,
        registerNumber: null,
        status: null,
        other: null,
        operator: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeOrderTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加入库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStockIn(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改入库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStockIn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStockIn(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除入库编号为"' + ids + '"的数据项？').then(function() {
        return delStockIn(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('management/stockIn/export', {
        ...this.queryParams
      }, `stockIn_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
