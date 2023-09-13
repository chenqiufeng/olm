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
      <el-form-item label="粮库" prop="granary">
        <el-select v-model="queryParams.grainDepotId" placeholder="请选择粮库" clearable  @change="handleGranary">
          <el-option
            v-for="grain in grainList"
            :key="grain.id"
            :label="grain.name"
            :value="grain.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="粮仓" prop="granary">
        <el-select v-model="queryParams.granaryId" placeholder="请选择粮仓" clearable>
          <el-option
            v-for="granary in granaryList"
            :key="granary.id"
            :label="granary.name"
            :value="granary.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="货主" prop="ownerGoods">
        <el-input
          v-model="queryParams.ownerGoods"
          placeholder="请输入货主"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['management:stockOut:add']"
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
          v-hasPermi="['management:stockOut:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['management:stockOut:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockOutList" @selection-change="handleSelectionChange">
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="粮库" align="center" prop="grainDepotName" />
      <el-table-column label="粮仓" align="center" prop="granaryName" />
      <el-table-column label="货主" align="center" prop="ownerGoods" />
      <el-table-column label="首磅(kg) " align="center" prop="firstPound" />
      <el-table-column label="次磅(kg)  " align="center" prop="secondPound" />
      <el-table-column label="净重(kg)" align="center" prop="netWeight" />
      <el-table-column label="出库量" align="center" prop="outDbInt" />
      <el-table-column label="首磅时间" align="center" prop="firstPoundTime" width="180"/>
      <el-table-column label="次磅时间" align="center" prop="secondPoundTime" width="180"/>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.out_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="other" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['management:stockOut:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['management:stockOut:remove']"
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

    <!-- 添加或修改出库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="粮库" prop="grainDepotId">
              <el-select v-model="form.grainDepotId" placeholder="请选择粮库" clearable  @change="handleGranary">
                <el-option
                  v-for="grain in grainList"
                  :key="grain.id"
                  :label="grain.name"
                  :value="grain.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="粮仓" prop="granaryId">
              <el-select v-model="form.granaryId" placeholder="请选择粮仓" clearable>
                <el-option
                  v-for="granary in granaryList"
                  :key="granary.id"
                  :label="granary.name"
                  :value="granary.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="货主" prop="ownerGoods">
              <el-input v-model="form.ownerGoods" placeholder="请输入货主"  />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="车牌号" prop="carId">
              <el-input v-model="form.carId" placeholder="请输入车牌号"   />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="首磅" prop="firstPound">
              <el-input v-model="form.firstPound" placeholder="请输入首磅(kg)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="次磅  " prop="secondPound">
              <el-input v-model="form.secondPound" placeholder="请输入次磅(kg)  " />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="净重" prop="netWeight">
              <el-input v-model="form.netWeight" placeholder="请输入净重(kg)" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="品种" prop="type">
              <el-select v-model="form.type" placeholder="请选择品种">
                <el-option
                  v-for="dict in dict.type.sys_goods_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="首磅时间" prop="firstPoundTime">
              <el-date-picker clearable
                              v-model="form.firstPoundTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择次磅时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="次磅时间" prop="secondPoundTime">
              <el-date-picker clearable
                              v-model="form.secondPoundTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择次磅时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出库量" prop="outDbInt">
              <el-input v-model="form.outDbInt" placeholder="请输入出库量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="other">
          <el-input v-model="form.other" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStockOut, getStockOut, delStockOut, addStockOut, updateStockOut } from "@/api/management/stockOut";
import {listGranary,listGrain} from "@/api/management/GranaryGrain";
export default {
  name: "StockOut",
  dicts: ['sys_goods_type', 'sys_quality_level', 'out_status'],
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
      // 出库表格数据
      stockOutList: [],
      // 粮仓
      granaryList:[],
      // 粮库
      grainList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        operator: null,
        orderId: null,
        granaryId: null,
        grainDepotId: null,
        ownerGoods: null,
        type: null,
        carId: null,
        firstPound: null,
        secondPound: null,
        netWeight: null,
        firstPoundTime: null,
        secondPoundTime: null,
        orderTime: null,
        outDbInt: null,
        outDbWorker: null,
        firstPoundWorker: null,
        secondPoundWorker: null,
        agreementId: null,
        status: null,
        qualityLevel: null,
        other: null,
        time: null,
        granary:null,
        grain:null,
        grainId:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        granaryId: [
          { required: true, message: "粮仓不能为空", trigger: "change" }
        ],
        grainDepotId: [
          { required: true, message: "粮库不能为空", trigger: "change" }
        ],
        firstPound: [
          {
            required: true,
            type: "number",
            message: "请输入正确的数字",
            trigger: ["blur", "change"],
            transform(value) {
              return value ==="" ? 0 : Number(value);
            },
          }
        ],
        secondPound: [
          {
            required: true,
            type: "number",
            message: "请输入正确的数字",
            trigger: ["blur", "change"],
            transform(value) {
              return value ==="" ? 0 : Number(value);
            },
          }
        ],
        goodsType: [
          { required: true, message: "品种不能为空", trigger: "change" }
        ],

        outDbInt: [
          {
            required: true,
            type: "number",
            message: "请输入正确的数字",
            trigger: ["blur", "change"],
            transform(value) {
              return value ==="" ? 0 : Number(value);
            },
          }
        ],

      }
    };
  },
  created() {
    this.getListGrain();
    this.getList();
  },
  methods: {
    // 获取粮库列表
    getListGrain() {
      listGrain().then(response => {
        this.grainList = response.data
      });
    },

    handleGranary(value) {
      let params = {};
      this.granaryList = [];
      this.queryParams.granaryId = null;
      if (value != '') {
        params["parentId"] = value
        listGranary(params).then(response => {
          this.granaryList = response.data
        });
      }
    },
    /** 查询出库列表 */
    getList() {
      this.loading = true;
      listStockOut(this.queryParams).then(response => {
        this.stockOutList = response.rows;
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
        operator: null,
        orderId: null,
        granaryId: null,
        grainDepotId: null,
        ownerGoods: null,
        type: null,
        carId: null,
        firstPound: null,
        secondPound: null,
        netWeight: null,
        firstPoundTime: null,
        secondPoundTime: null,
        orderTime: null,
        outDbInt: null,
        outDbWorker: null,
        firstPoundWorker: null,
        secondPoundWorker: null,
        agreementId: null,
        status: null,
        qualityLevel: null,
        other: null,
        time: null
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
      this.title = "添加出库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStockOut(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出库";
        let params = {};
        params["parentId"] = this.form.grain
        listGranary(params).then(response => {
          this.granaryList = response.data
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (parseFloat(this.form.firstPound) > parseFloat(this.form.secondPound)) {
            this.$message.error("首磅重量不能大于次磅重量");
            return;
          }
          if (this.form.id != null) {
            updateStockOut(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStockOut(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除出库编号为"' + ids + '"的数据项？').then(function() {
        return delStockOut(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('management/stockOut/export', {
        ...this.queryParams
      }, `stockOut_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
