<template>
  <div id="barcode-rule" style="height: 100%;">
    <!--工具条-->
    <div class="tool-bar">
      <el-input style="width:25%;" v-model="filters.barcode" placeholder="条码"></el-input>
      <el-input style="width:10%;" v-model="filters.barcodeLen" placeholder="条码长度"></el-input>
      <el-button style="margin-left:5px;" type="info" @click="getBarcodeRuleList" icon="search"></el-button>
      <el-button type="primary" @click="handleBtnAdd" >新增</el-button>
    </div>
    <!--列表-->
    <el-table highlight-current-row v-loading.body="loading" :data="barcodeRulesData" style="width: 100%;height: calc(100% - 75px);" border :row-class-name="tableRowClassName" height="outer">
      <el-table-column type="index" label="序号" width="55" align="center"></el-table-column>
      <el-table-column prop="barcode" width="300" label="条码样例" header-align="center">
        <template slot-scope="scope">
          {{ scope.row.barcode }}
          <span v-if="scope.row.flag==='0'" class="disabled-icon"></span>
        </template>
      </el-table-column>
      <el-table-column prop="barcodeLen" label="条码长度" header-align="center" width="60"></el-table-column>
      <el-table-column prop="barleader" label="条码前缀" header-align="center" width="60"></el-table-column>
      <el-table-column prop="sign1Position" header-align="center" width="78" label="特征1码位置"></el-table-column>
      <el-table-column prop="sign1" header-align="center" width="55" label="特征1码"></el-table-column>
      <el-table-column prop="sign2Position" header-align="center" width="78" label="特征2码位置"></el-table-column>
      <el-table-column prop="sign2" header-align="center" width="55" label="特征2码"></el-table-column>
      <el-table-column prop="isMain" :formatter="formatterMain" label="条码类型" header-align="center" width="60"></el-table-column>
      <el-table-column prop="consumablesPosition" header-align="center" width="100" label="耗材条码位置"></el-table-column>
      <el-table-column prop="consumablesLen" header-align="center" width="100" label="耗材条码长度"></el-table-column>
      <el-table-column prop="periodPosition" header-align="center" width="90" label="有效期位置"></el-table-column>
      <el-table-column prop="periodFormat" header-align="center" width="78" label="有效期格式"></el-table-column>
      <el-table-column prop="tracknumPosition" header-align="center" width="78" label="跟踪码位置"></el-table-column>
      <el-table-column prop="tracknumLen" header-align="center" width="78" label="跟踪码长度"></el-table-column>
      <el-table-column prop="productionPosition" header-align="center" width="120" label="生产批号位置"></el-table-column>
      <el-table-column prop="productionLen" header-align="center" width="120" label="生产批号长度"></el-table-column>
      <el-table-column label="操作" width="140" header-align="center" fixed="right">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)" icon="edit">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)" icon="delete">删除</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="float:right" small @size-change="handleSizeChange" @current-change="getBarcodeRuleList" :current-page.sync="pageNum" layout="total, sizes, prev, pager, next" :page-sizes="[10, 15, 20, 30]" :page-size="pageSize" :total="rTotal">
    </el-pagination>

    <!--编辑界面-->
    <el-dialog :title="modelTitle" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="barcodeRule" label-width="90px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
        <el-row>
          <el-col>
            <el-form-item label="条码样例" prop="barcode" class="input-barcode">
              <el-input v-model="barcodeRule.barcode" :blur="getBarcodeLen()"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="条码长度" prop="barcodeLen">
              <el-input v-model.number="barcodeRule.barcodeLen" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="条码前缀" prop="barleader">
              <el-input v-model="barcodeRule.barleader" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="特征1码位置" prop="sign1Position">
              <el-input v-model.number="barcodeRule.sign1Position" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="特征1码" prop="sign1">
              <el-input v-model.number="barcodeRule.sign1" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="特征2码位置" prop="sign2Position">
              <el-input v-model.number="barcodeRule.sign2Position" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="特征2码" prop="sign2">
              <el-input v-model.number="barcodeRule.sign2" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="耗材码位置" prop="consumablesPosition">
              <el-input v-model.number="barcodeRule.consumablesPosition" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="耗材码长度" prop="sign1">
              <el-input v-model.number="barcodeRule.consumablesLen" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="有效期位置" prop="periodPosition">
              <el-input v-model.number="barcodeRule.periodPosition" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="有效期格式" prop="periodFormat">
              <el-input v-model="barcodeRule.periodFormat" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="跟踪码位置" prop="tracknumPosition">
              <el-input v-model.number="barcodeRule.tracknumPosition" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="跟踪码长度" prop="tracknumLen">
              <el-input v-model.number="barcodeRule.tracknumLen" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产批号位置" prop="productionPosition">
              <el-input v-model.number="barcodeRule.productionPosition" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产批号长度" prop="productionLen">
              <el-input v-model.number="barcodeRule.productionLen" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="供应商名称" prop="mfrsId">
              <el-select v-model="barcodeRule.mfrsId" filterable :remote="remoteSearch" placeholder="请输入生产厂家名称" :remote-method="querySearchMfrsAsync" :loading="mfrsSearchLoading" clearable @input="handleFreezeRemote" @change="handleMfrsSelect">
                <el-option v-for="item in mfrsSearchData" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="条码类型">
              <el-radio-group v-model="barcodeRule.isMain" >
                <el-radio-button label="1">主码</el-radio-button>
                <el-radio-button label="0">副码</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否启用">
              <el-radio-group v-model="barcodeRule.flag" >
                <el-radio-button label="1">启用</el-radio-button>
                <el-radio-button label="0">停用</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="handleCancle">取消</el-button>
        <el-button type="primary" @click.native="handleSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import debounce from "throttle-debounce/debounce";

export default {
  data() {
    return {
      editFormRules: {
        barcode: [
          { required: true, message: "请输入条码样例", trigger: "blur" }
        ],
        barcodeLen: [
          {
            required: true,
            type: "integer",
            message: "请输入条码长度",
            trigger: "blur"
          }
        ],
        barleader: [
          { required: true, message: "请输入条码前缀", trigger: "blur" }
        ]
        /* sign1Position: [
          { required: false, type: 'integer', message: '请正确输入特征1码位置', trigger: 'blur' }
        ],
        sign2Position: [
          { required: false, type: 'integer', message: '请正确输入特征2码位置', trigger: 'blur' }
        ],
        consumablesPosition: [
          { required: false, type: 'integer', message: '请正确输入耗材码位置', trigger: 'blur' }
        ],
        consumablesLen: [
          { required: false, type: 'integer', message: '请正确输入耗材码长度', trigger: 'blur' }
        ],
        periodPosition: [
          { required: false, type: 'integer', message: '请正确输入有效期位置', trigger: 'blur' }
        ],
        tracknumPosition: [
          { required: false, type: 'integer', message: '请正确输入跟踪码位置', trigger: 'blur' }
        ],
        tracknumLen: [
          { required: false, type: 'integer', message: '请正确输入跟踪码长度', trigger: 'blur' }
        ],
        productionPosition: [
          { required: false, type: 'integer', message: '请正确输入生产批号位置', trigger: 'blur' }
        ],
        productionLen: [
          { required: false, type: 'integer', message: '请正确输入生产批号长度', trigger: 'blur' }
        ] */
      },

      baseURL: "/spdHERPService/baseData/baseDataMaint/BarcodeRule",
      filters: {
        barcode: "",
        barcodeLen: ""
      },
      loading: false,
      editLoading: false,
      dlgAddMode: false,
      barcodeRulesData: [],

      barcodeRule: {},
      modelTitle: "",
      editFormVisible: false,

      pageNum: 1,
      pageSize: 30,
      rTotal: 0,

      remoteSearch: true,
      mfrsSearchLoading: false,
      mfrsSearchData: []
    };
  },
  mounted() {
    this.getBarcodeRuleList();
  },
  methods: {
    getParams() {
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      };
      if (this.filters.barcode !== "") {
        params.barcode = this.filters.barcode;
      }
      if (this.filters.barcodeLen !== "") {
        params.barcodeLen = this.filters.barcodeLen;
      }
      return params;
    },
    // 获取列表
    getBarcodeRuleList() {
      this.loading = true;

      this.axios
        .get(this.baseURL + "/getBarcodeRuleList", {
          params: this.getParams()
        })
        .then(
          res => {
            this.loading = false;
            if (res.data.code === 0) {
              this.barcodeRulesData = res.data.data.data;
              // this.pageNum = res.data.data.pageNum
              this.rTotal = res.data.data.total;
            } else {
              this.$message.error(res.data.msg);
            }
          },
          err => {
            this.$message.error(err);
            this.loading = false;
          }
        );
    },
    handleBtnAdd() {
      this.dlgAddMode = true;
      this.barcodeRule = this.newBarcodeRule();
      this.modelTitle = "新增条码规则";
      this.editFormVisible = true;
      this.$refs["editForm"].resetFields();
    },
    handleSubmit() {
      this.$refs["editForm"].validate(valid => {
        if (valid) {
          if (this.dlgAddMode) {
            this.addBarcodeRule();
          } else {
            this.editBarcodeRule();
          }
        } else {
          this.$message.error("数据验证失败，请按提示修改数据。");
        }
      });
    },
    addBarcodeRule() {
      this.editLoading = true;
      this.axios
        .post(this.baseURL + "/addBarcodeRule", this.barcodeRule)
        .then(res => {
          this.editLoading = false;
          if (res.data.code === 0) {
            this.editLoaing = false;
            this.barcodeRule.id = res.data.data;
            this.barcodeRulesData.push(this.barcodeRule);
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
    editBarcodeRule() {
      this.editLoading = true;
      this.axios
        .put(this.baseURL + "/modBarcodeRule", this.barcodeRule)
        .then(res => {
          this.editLoading = false;
          if (res.data.code === 0) {
            this.editLoaing = false;
            this.barcodeRule.version = res.data.data;
            const origBarcodeRule = this.barcodeRulesData.find(
              o => o.id === this.barcodeRule.id
            );
            Object.assign(origBarcodeRule, this.barcodeRule);
            this.$message.success("操作成功");
            this.editFormVisible = false;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(err => {
          this.editLoading = false;
          this.$msgbox({ title: "错误", type: "error", message: err });
        });
    },
    handleEdit(barcodeRule) {
      this.dlgAddMode = false;
      this.barcodeRule = JSON.parse(JSON.stringify(barcodeRule));
      this.modelTitle = "编辑条码规则";
      this.editFormVisible = true;
      this.handleFreezeRemote();
    },
    handleDelete(barcodeRule) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(_ => {
        this.axios
          .delete(this.baseURL + "/delBarcodeRule", {
            params: {
              id: barcodeRule.id,
              version: barcodeRule.version
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              const removeIndex = this.barcodeRulesData.findIndex(
                o => o.id === barcodeRule.id
              );
              if (removeIndex > -1) {
                this.barcodeRulesData.splice(removeIndex, 1);
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
    handleSizeChange(val) {
      this.pageSize = val;
      this.getBarcodeRuleList();
    },
    handleCancle() {
      this.editFormVisible = false;
    },
    formatterMain(row, column) {
      return row.isMain === "1" ? "主码" : "副码";
    },
    tableRowClassName(row, index) {
      return row.flag === "0" ? "row-disabled" : "";
    },
    getBarcodeLen(values) {
      if (this.barcodeRule.barcode) {
        this.barcodeRule.barcodeLen = this.barcodeRule.barcode.length;
        if (this.barcodeRule.barcodeLen >= 2 && !this.barcodeRule.barleader)
          this.barcodeRule.barleader = this.barcodeRule.barcode.substring(0, 2);
      } else {
        this.barcodeRule.barcodeLen = 0;
        this.barcodeRule.barleader = "";
      }
    },
    newBarcodeRule() {
      return {
        id: "",
        barcode: "",
        barcodeLen: "",
        barleader: "",
        consumablesPosition: "",
        consumablesLen: "",
        isMain: "1",
        periodPosition: "",
        periodFormat: "",
        productionPosition: "",
        productionLen: "",
        sign1Position: "",
        sign1: "",
        sign2Position: "",
        sign2: "",
        tracknumPosition: "",
        tracknumLen: "",
        mfrsId: "",
        mfrsName: "",
        flag: "1",
        version: 0
      };
    },

    handleFreezeRemote() {
      this.$set(this, "remoteSearch", false);
      this.$nextTick(function() {
        this.$set(this, "remoteSearch", true);
      });
    },
    handleProvSelect(item) {
      if (item) {
        const mfrs = this.mfrsSearchData.find(o => o.id === item);
        if (mfrs) {
          this.barcodeRule.mfrsName = mfrs.name;
        }
      }
    },
    debounceQuerySearchMfrsAsync: debounce(500, function(queryString) {
      this.axios
        .get(this.baseURL + "/getMfrsList", {
          params: {
            mfrsName: queryString
          }
        })
        .then(res => {
          if (res.data.code >= 0) {
            this.mfrsSearchData = res.data.data;
            this.mfrsSearchLoading = false;
          } else {
            this.$message.error("获取数据失败。" + res.data.msg);
            this.mfrsSearchLoading = false;
          }
        })
        .catch(err => {
          this.handleError(err, this.mfrsSearchLoading);
        });
    }),
    querySearchMfrsAsync(queryString) {
      if (queryString !== "") {
        this.mfrsSearchLoading = true;
        this.debounceQuerySearchMfrsAsync(queryString);
      }
    }
  }
};
</script>
<style>
#barcode-rule .font-vcenter {
  padding-top: 4px;
}

#barcode-rule .is-error {
  margin-bottom: 22px;
}

#barcode-rule .input-barcode {
  border: none;
}

#barcode-rule .disabled-icon {
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

.tool-bar {
  display: inline-block;
  width: 100%;
  margin-bottom: 5px;
}

.inline-display {
  display: inline;
}
</style>
