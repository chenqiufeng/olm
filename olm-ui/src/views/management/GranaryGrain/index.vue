<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户" prop="granary">
        <el-select v-model="queryParams.customerId" placeholder="请选择客户" clearable  @change="handleCustomer">
          <el-option
            v-for="customer in customerList"
            :key="customer.deptId"
            :label="customer.deptName"
            :value="customer.deptId"
          />
        </el-select>
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
          v-hasPermi="['management:GranaryGrain:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="GranaryGrainList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="粮仓名称" prop="name" />
      <el-table-column label="所属关系" align="center" prop="parentId" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['management:GranaryGrain:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['management:GranaryGrain:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['management:GranaryGrain:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改粮库和粮仓管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户" prop="granary">
          <el-select v-model="form.customerId" placeholder="请选择客户" clearable  @change="handleCustomer">
            <el-option
              v-for="customer in customerList"
              :key="customer.deptId"
              :label="customer.deptName"
              :value="customer.deptId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="粮库/粮仓" prop="name">
          <el-input v-model="form.name" placeholder="请输入粮仓名称" />
        </el-form-item>
        <el-form-item label="所属关系" prop="parentId">
          <treeselect v-model="form.parentId" :options="GranaryGrainOptions" :normalizer="normalizer" placeholder="请选择所属关系" />
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
import { listGranaryGrain, getGranaryGrain, delGranaryGrain, addGranaryGrain, updateGranaryGrain } from "@/api/management/GranaryGrain";
import {listCustomerAll} from "@/api/management/customer";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listDeptList} from "@/api/system/dept"

export default {
  name: "GranaryGrain",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 粮库和粮仓管理表格数据
      GranaryGrainList: [],
      // 粮库和粮仓管理树选项
      GranaryGrainOptions: [],
      // 客户列表
      customerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        name: null,
        parentId: null,
        customerId:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // name: [
        //   { required: true, message: "粮仓名称不能为空", trigger: "blur" }
        // ],
        // parentId: [
        //   { required: true, message: "所属关系不能为空", trigger: "blur" }
        // ]
      }
    };
  },
  created() {
    this.getCustomerList();
    this.getList();
  },
  methods: {
    // 获取客户列表
    getCustomerList() {
      listDeptList().then(response => {
        this.customerList = response.data
      })
    },
    /** 查询粮库和粮仓管理列表 */
    getList() {
      this.loading = true;
      listGranaryGrain(this.queryParams).then(response => {
        this.GranaryGrainList = this.handleTree(response.data, "id", "parentId");
        this.loading = false;
      });
    },
    /** 转换粮库和粮仓管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
	/** 查询粮库和粮仓管理下拉树结构 */
    getTreeselect() {
      listGranaryGrain().then(response => {
        this.GranaryGrainOptions = [];
        const data = { id: 0, name: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "parentId");
        this.GranaryGrainOptions.push(data);
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
        name: null,
        parentId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    handleCustomer() {

    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.id) {
        this.form.parentId = row.id;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加粮库和粮仓管理";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.parentId;
      }
      getGranaryGrain(row.id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改粮库和粮仓管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGranaryGrain(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGranaryGrain(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除粮库和粮仓管理编号为"' + row.id + '"的数据项？').then(function() {
        return delGranaryGrain(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
