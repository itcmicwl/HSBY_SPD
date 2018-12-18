<template>
  <div id="base-mfrs-info" style="height: 100%;">
    <!--工具条-->
    <el-form :inline="true" :model="filters" class="el-form-item-nomsg">
      <el-form-item label="厂家类型：">
        <el-select v-model="filters.mfrsKind" placeholder="全部" :clearable="true" width="100%">
          <el-option v-for="(value, key) in cjlxDict" :key="key" :label="value" :value="key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="厂家名称：">
        <el-input v-model="filters.mfrsName" placeholder="厂家名称" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="search" @click="getBasMfrsInfoList()" >查询</el-button>
        <el-button type="primary" @click="handleAdd()" >新增</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table :data="basMfrsInfoList" highlight-current-row v-loading.body="loading" style="width: 100%;height:calc(100% - 75px);" border fit height="outter" :row-class-name="tableRowClassName">
      <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
      <el-table-column prop="mfrsName" label="厂家名称" header-align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.mfrsName }}
          <span v-if="scope.row.flag==='0'" class="disabledIcon"></span>
        </template>
      </el-table-column>
      <el-table-column prop="mfrsKind" label="厂家类型" width="80" align="center" :formatter="formatMfrsKind"></el-table-column>
      <el-table-column prop="isThreeInOne" label="是否三证合一" width="90" align="center" :formatter="formatMfrsThreeInOne"></el-table-column>
      <el-table-column prop="remark" label="备注" header-align="center"></el-table-column>
      <el-table-column label="操作" header-align="center" fixed="right" width="140">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)" icon="edit">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)" icon="delete">删除</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <!--工具条-->
    <el-pagination small @size-change="handleSizeChange" style="padding-right: 20px;text-align: right;" @current-change="handleCurrentChange" :current-page="pageNum" layout="total, sizes, prev, pager, next" :page-sizes="[10, 15, 20, 30]" :page-size="pageSize" :total="rTotal">
    </el-pagination>
    <!--编辑界面-->
    <el-dialog :title="modelTitle" :visible.sync="editFormVisible">
      <el-form :model="basMfrsInfo" label-width="120px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
        <el-row>
          <el-form-item label="厂家名称" prop="mfrsName">
            <el-input v-model="basMfrsInfo.mfrsName" placeholder="请输入厂家名称" ></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="9">
            <el-form-item label="厂家类型">
              <el-radio-group v-model="basMfrsInfo.mfrsKind">
                <el-radio v-for="(value, key) in cjlxDict" :key="key" :label="key">
                  {{ value }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="basMfrsInfo.mfrsKind==='2'">
          <el-col>
            <el-form-item label="全国总代理">
              <el-input v-model="basMfrsInfo.agentName" placeholder="请输入全国总代理"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="营业执照注册号">
              <el-input v-model="basMfrsInfo.regCode" placeholder="请输入营业执照注册号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="经营许可证编号">
              <el-input v-model="basMfrsInfo.jyxkCode" placeholder="请输入经营许可证编号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="纳税人识别号">
              <el-input v-model="basMfrsInfo.nsrsbCode" placeholder="请输入纳税人识别号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产许可证">
              <el-input v-model="basMfrsInfo.productCode" placeholder="请输入生产许可证"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注">
          <el-input type="textarea" :row="2" placeholder="请输入备注信息" v-model="basMfrsInfo.remark" ></el-input>
        </el-form-item>
        <el-form-item label="是否三证合一">
          <el-radio-group v-model="basMfrsInfo.isThreeInOne">
            <el-radio label="1">是</el-radio>
            <el-radio label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否启用">
          <el-radio-group v-model="basMfrsInfo.flag" >
            <el-radio-button label="1">启用</el-radio-button>
            <el-radio-button label="0">停用</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="handleDlgConfirm" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      editFormRules: {
        mfrsName: [
          { required: true, message: "请输入名称", trigger: "blur" },
          {
            min: 5,
            max: 300,
            message: "长度在 5 到 500 个字符",
            trigger: "blur"
          }
        ]
      },
      baseURL: "/spdHERPService/baseData/baseDataMaint/BasMfrsInfo",
      cjlxDict: {},
      basMfrsInfoList: [],
      pageNum: 1,
      pageSize: 30,
      rTotal: 0,
      filters: {
        mfrsKind: "",
        mfrsName: ""
      },
      loading: false,
      dlgAddMode: false,
      basMfrsInfo: {},
      modelTitle: "",
      editLoading: false,
      editFormVisible: false
    };
  },
  mounted() {
    this.axios
      .get(this.baseURL + "/getMfrsKind")
      .then(res => {
        this.cjlxDict = {};
        for (let item of res.data.data) {
          this.cjlxDict[item.value] = item.name;
        }
        this.getBasMfrsInfoList();
      })
      .catch(err => {
        this.$msgbox({ title: "错误", type: "error", message: err });
      });
  },
  created() {},
  methods: {
    formatMfrsKind(row, column) {
      if (row.mfrsKind < 1) {
        //  || row.mfrsKind > this.dicts.mfrsKind
        return "Error";
      } else {
        return this.cjlxDict[row.mfrsKind];
      }
    },
    formatMfrsThreeInOne(row, column) {
      return row.isThreeInOne === "1" ? "是" : "否";
    },
    getParams() {
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      };
      if (this.filters.mfrsKind !== "") {
        params.mfrsKind = this.filters.mfrsKind;
      }
      if (this.filters.mfrsName !== "") {
        params.mfrsName = this.filters.mfrsName;
      }
      return params;
    },
    getBasMfrsInfoList() {
      this.axios
        .get(this.baseURL + "/getMfrsInfoList", {
          params: this.getParams()
        })
        .then(res => {
          this.loading = false;
          if (res.data.code === 0) {
            this.basMfrsInfoList = res.data.data.data;
            // this.pageNum = res.data.data.pageNum
            this.rTotal = res.data.data.total;
          } else {
            this.$message.error(res.data.msg);
            this.loading = false;
          }
        })
        .catch(err => {
          this.$msgbox({ title: "错误", type: "error", message: err });
          this.loading = false;
        });
    },
    handleAdd() {
      this.dlgAddMode = true;
      this.basMfrsInfo = this.newBasMfrsInfo();
      this.modelTitle = "新增厂商信息";
      this.editFormVisible = true;
      this.$refs["editForm"].resetFields();
    },
    handleEdit(basMfrsInfo) {
      this.dlgAddMode = false;
      this.basMfrsInfo = JSON.parse(JSON.stringify(basMfrsInfo));
      this.modelTitle = "编辑厂商信息";
      this.editFormVisible = true;
    },
    handleDelete(basMfrsInfo) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(_ => {
        this.axios
          .delete(this.baseURL + "/delBasMfrsInfo", {
            params: {
              id: basMfrsInfo.id,
              version: basMfrsInfo.version
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              const removeIndex = this.basMfrsInfoList.findIndex(
                o => o.id === basMfrsInfo.id
              );
              if (removeIndex > -1) {
                this.basMfrsInfoList.splice(removeIndex, 1);
              }
              this.$message.success("操作成功");
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch(err => {
            this.$msgbox({ title: "错误", type: "error", message: err });
          });
      });
    },
    handleDlgConfirm() {
      this.$refs["editForm"].validate(valid => {
        if (valid) {
          if (this.dlgAddMode) {
            this.addBasMfrsInfo();
          } else {
            this.editBasMfrsInfo();
          }
        } else {
          this.$message.error("数据验证失败，请按提示修改数据。");
        }
      });
    },
    addBasMfrsInfo() {
      this.editLoading = true;
      this.axios
        .post(this.baseURL + "/addBasMfrsInfo", this.basMfrsInfo)
        .then(res => {
          this.editLoading = false;
          if (res.data.code === 0) {
            this.editLoaing = false;
            this.basMfrsInfo.id = res.data.data;
            this.basMfrsInfoList.push(this.basMfrsInfo);
            this.$message.success("操作成功");
            this.editFormVisible = false;
          } else {
            this.editLoaing = false;
            this.$message.error(res.data.msg);
          }
        })
        .catch(err => {
          this.editLoading = false;
          this.$msgbox({ title: "错误", type: "error", message: err });
        });
    },
    editBasMfrsInfo() {
      this.editLoading = true;
      this.axios
        .put(this.baseURL + "/editBasMfrsInfo", this.basMfrsInfo)
        .then(res => {
          this.editLoading = false;
          if (res.data.code === 0) {
            this.editLoaing = false;
            this.basMfrsInfo.version = res.data.data;
            const origBasMfrsInfo = this.basMfrsInfoList.find(
              o => o.id === this.basMfrsInfo.id
            );
            Object.assign(origBasMfrsInfo, this.basMfrsInfo);
            this.$message.success("操作成功");
            this.editFormVisible = false;
          } else {
            this.editLoaing = false;
            this.$message.error(res.data.msg);
          }
        })
        .catch(err => {
          this.editLoading = false;
          this.$msgbox({ title: "错误", type: "error", message: err });
        });
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getBasMfrsInfoList();
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getBasMfrsInfoList();
    },
    tableRowClassName(row, index) {
      return row.flag === "0" ? "row-disabled" : "";
    },
    newBasMfrsInfo() {
      return {
        id: "",
        mfrsKind: "1",
        mfrsName: "",
        agentName: "",
        regCode: "",
        jyxkCode: "",
        nsrsbCode: "",
        productCode: "",
        remark: "",
        isThreeInOne: "0",
        flag: "1",
        version: 0
      };
    }
  }
};
</script>
<style scoped>
.is-error {
  margin-bottom: 22px;
}

.disabledIcon {
  background: url("../../../assets/images/disabled_bg_red.png") no-repeat;
  background-size: 40px 20px;
  display: inline-block;
  width: 45px;
  height: 0;
  padding-top: 30px;
  overflow: hidden;
  position: absolute;
  top: 3px;
  /* right: -10px; */
}
</style>
