<template>
  <div id="bill-manager-hos" class="full-height">
    <el-row class="full-height">
      <el-col :span="4" class="full-height">
        <div class="inline-display">
          <span class="inline-display">出库库房：</span>
          <el-select v-model="currentStock" class="stock-select" @change="handleStockChange" placeholder="请选择房库">
            <el-option v-for="item in ownStockList" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </div>
        <el-tree :data="orgTree" :expand-on-click-node="false" :indent="25" :props="treeProps" :render-content="renderTreeContent" class="tree-height" @node-click="handleClickTreeNode" highlight-current node-key="id" default-expand-all></el-tree>
      </el-col>

      <el-col :span="20" class="full-height" style="padding-left:10px;">
        <div style="display:block;">
          <div class="inline-display" style="margin-bottom: 5px;">
            <div class="inline-display">
              <span>制单时间：</span>
              <el-date-picker v-model="searchParams.beginDate" class="data-picker" type="date" placeholder="开始日期"></el-date-picker>
              <span>-</span>
              <el-date-picker v-model="searchParams.endDate" class="data-picker" type="date" placeholder="结束日期"></el-date-picker>
            </div>
            <div class="inline-display">
              <el-select v-model="searchParams.status" style="width:120px;" placeholder="出库单状态">
                <el-option v-for="(value, key) in CKDZTDict" :key="key" :label="value" :value="key">
                </el-option>
              </el-select>
            </div>
            <div class="inline-display">
              <el-button :disabled="!currentDeptId || !currentStock" @click="handleSearchBtnClick" type="info" icon="search">
                搜索
              </el-button>
            </div>
            <div class="inline-display" style="margin-left: 5px">
              <el-switch v-model="bAllOrgs" @change="makeTree" active-text="所有科室" inactive-text="部分科室">
              </el-switch>
            </div>
          </div>
        </div>

        <el-table ref="billListTable" :data="billList" class="table-style" highlight-current-row height="outter" border>
          <!-- @row-dblclick="handleRowDblClick" v-loading.body="mainLoading" -->
          <el-table-column type="index" label="序号" width="45" align="center"></el-table-column>
          <el-table-column prop="id" label="出库单号"></el-table-column>
          <el-table-column prop="outDeptName" label="出库机构" show-overflow-tooltip></el-table-column>
          <el-table-column :formatter="typeFormatter" prop="outStockType" label="出库方式">
          </el-table-column>
          <el-table-column :formatter="kindFormatter" prop="outStockKind" label="出库类型">
          </el-table-column>
          <el-table-column :formatter="statusFormatter" prop="status" label="出库单状态">
          </el-table-column>
          <el-table-column prop="goodsCount" label="品种数量"></el-table-column>
          <el-table-column prop="goodsSum" label="商品总数量"></el-table-column>
          <el-table-column prop="amount" label="金额"></el-table-column>
          <el-table-column fixed="right" label="操作" width="50" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button @click.native.prevent="handleRowDblClick(scope.row)" type="primary" size="mini">编辑
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <el-dialog :visible.sync="dlgVisible" title="出库单" width="70%" top="10vh">
      <el-form ref="editForm" :model="currentOutStockBill" class="el-form-item-nomsg" label-width="70px" label-position="left">
        <el-row :gutter="10">
          <el-col :span="6">
            <el-form-item label="出库类型">
              <el-select v-model="currentOutStockBill.outStockKind" disabled>
                <el-option v-for="(value,key) in CKLXDict" :key="key" :label="value" :value="key"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库部门">
              <el-select v-model="currentOutStockBill.outDeptId" disabled placeholder="无">
                <el-option v-for="item in orgsData" :key="item.id" :label="item.ename" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="领用部门">
              <el-select v-model="currentOutStockBill.inDeptId" disabled placeholder="无">
                <el-option v-for="item in orgsData" :key="item.id" :label="item.ename" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="状态">
              <el-input :value="CKDZTDict[currentOutStockBill.status]" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="出库人员">
              <el-input v-model="currentOutStockBill.filler" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="制单时间">
              <el-input v-model="currentOutStockBill.fillDate" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <div class="text-style">
              种类：{{ goodsCount }}，总数量：{{ goodsSum }}，总金额：{{ amount }}
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="收货地址">
              <el-input v-model="currentOutStockBill.recAddress" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人">
              <el-input v-model="currentOutStockBill.recLinkman" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系电话">
              <el-input v-model="currentOutStockBill.recLinkmanPhone" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="currentOutStockBill.outStockKind==3" :gutter="20">
          <el-col :span="8">
            <el-form-item label="费用类别">
              <el-input :value="FYLXDict[currentOutStockBill.costKind]" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="患者姓名">
              <el-input v-model="currentOutStockBill.patientName" disabled></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row v-if="!!currentOutStockBill.auditor && !!currentOutStockBill.accounter" :gutter="10">
          <el-col :span="6">
            <el-form-item label="审核人员">
              <el-input v-model="currentOutStockBill.auditor" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="审核时间">
              <el-input v-model="currentOutStockBill.auditDate" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记帐人员">
              <el-input v-model="currentOutStockBill.accounter" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记帐时间">
              <el-input v-model="currentOutStockBill.accountDate" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="billTable" :data="currentOutStockBill.subList" highlight-current-row border row-key="id">
        <el-table-column type="index" label="序号" width="45" align="center"></el-table-column>
        <el-table-column label="名称" prop="goodsName" width="150" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="规格型号" prop="goodsGg" width="120" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="请领数量" prop="reqQty" align="center">
        </el-table-column>
        <el-table-column label="已发数量" prop="sentQty" align="center">
        </el-table-column>
        <el-table-column label="出库数量" width="60" prop="outQty" class-name="table-col-bg" align="center">
        </el-table-column>
        <el-table-column label="批号" prop="batchCode" align="center">
        </el-table-column>
        <el-table-column label="注册证" prop="certificateCode" align="center">
        </el-table-column>
        <el-table-column :formatter="dateFormatter" label="效期" prop="expdtEndDate" align="center">
        </el-table-column>
        <el-table-column label="单价" prop="price" align="center">
        </el-table-column>
        <el-table-column label="单位" prop="unit" align="center">
        </el-table-column>
        <el-table-column label="金额" align="center">
          <template slot-scope="scope">
            {{ (scope.row.outQty * scope.row.price).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column label="产地" prop="made" align="center">
        </el-table-column>
        <el-table-column label="供应商" prop="provName" align="center" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="生产商" prop="mfrsName" align="center" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="灭菌效期" prop="sterilizationEndDate" align="center" show-overflow-tooltip>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dlgVisible = false">取消</el-button>
        <!-- <el-button  type="primary" :loading="btnLoading" @click="updateOutStockBill(10)" v-if="currentOutStockBill.status === 90">提交</el-button> -->
        <el-button v-if="currentOutStockBill.status === 10" :loading="btnLoading" @click="updateOutStockBill(20)" type="primary">审核</el-button>
        <!-- <el-button  type="primary" :loading="btnLoading" @click="updateOutStockBill(30)" v-if="currentOutStockBill.status === 20">记帐</el-button> -->
        <el-button v-if="[90,10,20].includes(currentOutStockBill.status)" :loading="btnLoading" @click="updateOutStockBill(50)" type="danger">驳回</el-button>
        <!-- <el-button  type="primary" :loading="btnLoading" @click="updateOutStockBill(40)" v-if="[90,10,20].includes(currentOutStockBill.status)">作废</el-button> -->
        <el-button v-if="![90,10,20].includes(currentOutStockBill.status)" @click="print" type="info">打印</el-button>
      </div>

    </el-dialog>
    <billPrint ref="purPrintD"></billPrint>
  </div>
</template>

<script>
import moment from "moment";
import billPrint from "./billPrint";
import cloneDeep from "lodash/cloneDeep";
export default {
  components: { billPrint },
  data() {
    return {
      baseURL: "/spdHERPService/stockMgr/out/billMgr",
      rootNode: {
        id: "-1",
        pid: "",
        // label: this.user.corpName,
        label: "领用部门",
        children: []
      },
      orgsData: [],
      treeProps: {
        children: "children",
        label: "label"
      },

      ownStockList: [],
      currentStock: "",
      currentDeptId: "",
      mainLoading: false,
      btnLoading: false,

      searchParams: {
        beginDate: Date.now() - 7 * 24 * 60 * 60 * 1000,
        endDate: Date.now(),
        status: "10"
      },

      CKLXDict: {},
      CKDZTDict: {},
      CGMSDict: {},
      FYLXDict: { "10": "日常消耗" },

      billList: [],
      dlgVisible: false,
      currentOutStockBill: {
        subList: []
      },
      deptBuySub: [],
      orgTree: [],
      bAllOrgs: false
    };
  },
  computed: {
    // orgTree() {
    //   let orgsData = JSON.parse(JSON.stringify(this.orgsData));
    //   orgsData.forEach(data => {
    //     data.label = data.available ? data.ename + '(' + data.billCount + ')' : data.ename;
    //   });
    //   orgsData.filter(item => item.pid === '' || item.pid === '/').forEach(item => {
    //     item.pid = '-1';
    //   });
    //   return [this.addTreeNode(JSON.parse(JSON.stringify(this.rootNode)), orgsData)];
    // },
    params() {
      let params = JSON.parse(JSON.stringify(this.searchParams));
      params.stockId = this.currentStock;
      params.deptId = this.currentDeptId;
      if (params.beginDate === "") {
        delete params.beginDate;
      }
      if (params.endDate === "") {
        delete params.endDate;
      }
      if (params.goodsName === "") {
        delete params.goodsName;
      }
      return params;
    },
    goodsCount() {
      return new Set(this.currentOutStockBill.subList.map(o => o.goodsId)).size;
    },
    goodsSum() {
      return this.currentOutStockBill.subList.reduce(
        (acc, cur) => acc + cur.outQty,
        0
      );
    },
    amount() {
      let sum = this.currentOutStockBill.subList.reduce(
        (acc, cur) => acc + cur.outQty * cur.price,
        0
      );
      return sum.toFixed(2);
    }
  },
  mounted() {
    this.getStockList()
      .then(result => {
        this.ownStockList = result;
        if (result) {
          this.currentStock = result[0].id;
          this.handleStockChange(this.currentStock);
        }
        this.getDict("CKLX").then(res => {
          this.CKLXDict = res;
        });
        this.getDict("CKDZT").then(res => {
          this.CKDZTDict = res;
          this.CKDZTDict["undone"] = "待处理";
          this.CKDZTDict["all"] = "全部";
        });
        this.getDict("CGMS").then(res => {
          this.CGMSDict = res;
        });
      })
      .catch(err => {
        this.handleError(err);
      });
  },
  methods: {
    dateFormatter(row, column, cellValue) {
      if (!cellValue) {
        return "";
      } else {
        return moment(cellValue).format("YYYY-MM-DD");
      }
    },
    print() {
      // this.$refs.purPrintD.print(this.currentOutStockBill, this.orgsData)
      this.$refs.purPrintD.print(
        this.currentOutStockBill,
        this.orgsData,
        this.deptBuySub,
        this.CKLXDict,
        this.CKDZTDict
      );
    },
    handleStockChange(stockId) {
      this.getOrgList(stockId)
        .then(result => {
          this.orgsData = result;
          this.orgsData.push({
            id: "null",
            ename: "其它",
            available: true,
            billCount: "*",
            pid: ""
          });
          this.makeTree(this.bAllOrgs);
          this.billList.splice(0);
          this.currentDeptId = "";
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    handleClickTreeNode(data, node, treeComponent) {
      if (!data.available) {
        return;
      }
      // treeComponent.tree.store.remove(data)
      if (data.id === this.currentDeptId) {
        return;
      }
      this.currentDeptId = data.id;
      this.billList.splice(0);
      if (data.billCount !== 0) {
        this.mainLoading = true;
        this.getBillList(this.currentDeptId, this.currentStock)
          .then(res => {
            this.billList = res;
            this.mainLoading = false;
          })
          .catch(err => {
            this.handleError(err);
            this.mainLoading = false;
          });
      }
    },
    handleRowDblClick(row, event) {
      this.getBillDetail(row, event)
        .then(res => {
          this.currentOutStockBill = res;
          this.dlgVisible = true;
        })
        .catch(err => {
          this.handleError(err);
        });
      this.getDeptBuySub(row, event)
        .then(res => {
          this.deptBuySub = res;
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    handleSearchBtnClick(event) {
      this.billList.splice(0);
      this.mainLoading = true;
      this.getBillList(this.currentDeptId, this.currentStock)
        .then(res => {
          this.billList = res;
          this.mainLoading = false;
        })
        .catch(err => {
          this.handleError(err);
          this.mainLoading = false;
        });
    },
    updateOutStockBill(status) {
      const that = this;
      let doUpdateOutStockBill = function(status, that) {
        that.btnLoading = true;
        that.axios
          .patch(that.baseURL + "/updateOutStockBill", {
            id: that.currentOutStockBill.id,
            outStockKind: that.currentOutStockBill.outStockKind,
            outStockType: that.currentOutStockBill.outStockType,
            status: status,
            version: that.currentOutStockBill.version
          })
          .then(res => {
            if (res.data.code === 0) {
              const outStock = that.billList.find(
                o => o.id === that.currentOutStockBill.id
              );
              outStock.status = status;
              outStock.version = res.data.data;
              that.dlgVisible = false;
              that.$message.success("修改成功");
              that.currentOutStockBill.status = status;
            } else {
              that.handleError(res.data.msg);
            }
            that.btnLoading = false;
          })
          .catch(err => {
            that.handleError(err);
            that.btnLoading = false;
          });
      };
      if (status === 50) {
        this.$confirm("是否驳回出库单?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            doUpdateOutStockBill(status, that);
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消驳回"
            });
          });
      } else {
        doUpdateOutStockBill(status, that);
      }
    },
    makeTree(bAllOrgs) {
      let addTreeNode = function(node, data) {
        for (let i = data.length - 1; i >= 0; i--) {
          if (data[i].pid === node.id) {
            data[i].children = [];
            node.children.push(JSON.parse(JSON.stringify(data[i])));
            data.splice(i, 1);
          }
        }
        node.children.forEach(o => addTreeNode(o, data));
        return node;
      };
      let delTreeUnusedLeaf = function(data) {
        for (let i = data.length - 1; i >= 0; i--) {
          if (!data[i].available && data[i].children.length === 0) {
            data.splice(i, 1);
            continue;
          }
          if (data[i].children.length > 0) {
            delTreeUnusedLeaf(data[i].children);
          }
        }
      };
      let tempData;
      if (bAllOrgs) {
        tempData = cloneDeep(this.orgsData);
      } else {
        tempData = cloneDeep(
          this.orgsData.filter(
            item =>
              item.billCount === "*" || item.billCount > 0 || !item.available
          )
        );
      }
      tempData.forEach(data => {
        data.label = data.available
          ? data.ename + "(" + data.billCount + ")"
          : data.ename;
      });
      tempData
        .filter(item => item.pid === "/" || item.pid === "")
        .forEach(item => {
          item.pid = "-1";
        });
      const data = [addTreeNode(cloneDeep(this.rootNode), tempData)];
      if (!bAllOrgs) {
        delTreeUnusedLeaf(data);
      }
      this.orgTree = data;
    },
    getOrgList(stockId) {
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getOrgs", {
            params: {
              stockId,
              submitted: true
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              resolve(res.data.data);
            } else {
              reject(res.data.msg);
            }
          });
      });
    },
    getStockList() {
      return new Promise((resolve, reject) => {
        this.axios.get(this.baseURL + "/getStockList").then(res => {
          if (res.data.code === 0) {
            resolve(res.data.data);
          } else {
            reject(res.data.msg);
          }
        });
      });
    },
    getDict(dict) {
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getDict", {
            params: {
              dict
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              let dicts = {};
              for (let item of res.data.data) {
                dicts[item.value] = item.name;
              }
              resolve(dicts);
            } else {
              reject(res.data.msg);
            }
          });
      });
    },
    getBillList(deptId, stockId) {
      let params = {
        deptId,
        stockId,
        beginDate: !this.searchParams.beginDate
          ? ""
          : moment(this.searchParams.beginDate).valueOf(),
        endDate: !this.searchParams.endDate
          ? ""
          : moment(this.searchParams.endDate).valueOf(),
        status: this.searchParams.status
      };
      if (
        params.beginDate &&
        params.endDate &&
        params.beginDate === params.endDate
      ) {
        params.endDate = moment(params.endDate)
          .add(1, "d")
          .valueOf();
      }
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getBillList", {
            params
          })
          .then(res => {
            if (res.data.code === 0) {
              resolve(res.data.data);
            } else {
              reject(res.data.msg);
            }
          })
          .catch(err => {
            reject(err);
          });
      });
    },
    getBillDetail(row, event) {
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getBillDetail", {
            params: {
              id: row.id
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              resolve(res.data.data);
            } else {
              reject(res.data.msg);
            }
          })
          .catch(err => {
            reject(err);
          });
      });
    },
    getDeptBuySub(row, event) {
      return new Promise((resolve, reject) => {
        this.axios
          .get("/spdHERPService/applyMgr/deptBuySub/getVoListByOut", {
            params: {
              id: row.id
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              resolve(res.data.data);
            } else {
              reject(res.data.msg);
            }
          })
          .catch(err => {
            reject(err);
          });
      });
    },
    typeFormatter(row, column, cellValue) {
      return this.CGMSDict[cellValue];
    },
    kindFormatter(row, column, cellValue) {
      return this.CKLXDict[cellValue];
    },
    statusFormatter(row, column, cellValue) {
      return this.CKDZTDict[cellValue];
    },
    handleError(err, loading) {
      return new Promise((resolve, reject) => {
        this.$msgbox({ title: "错误", type: "error", message: err })
          .then(action => {
            if (loading !== null && loading !== undefined) {
              loading = false;
            }
            resolve(action);
          })
          .catch(err => {
            reject(err);
          });
      });
    },
    renderTreeContent(h, { node, data, store }) {
      return data.available ? (
        <span>{node.label}</span>
      ) : (
        <span class="text-grey">{node.label}</span>
      );
    }
  }
};
</script>

<style scoped="">
.tree-height {
  height: calc(100% - 54px);
  margin-top: 2px;
}

.table-style {
  height: calc(100% - 52px);
}

.full-height {
  height: 100%;
}

.inline-display {
  display: inline-block;
}

.data-picker {
  width: 125px;
}

.stock-select {
  margin-bottom: 8px;
  width: calc(100% - 67px);
}

.el-form-item__error {
  display: block;
}

.barcode-input {
  width: 232px;
}

.slaver-barcode-input {
  width: 112px;
}

.text-style {
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  padding-top: 2px;
}
</style>

<style>
#bill-manager-hos .el-tree {
  border: 1px solid #ebeef5;
}

#bill-manager-hos .el-tree-node__expand-icon {
  cursor: pointer;
  width: auto;
  height: auto;
  margin-left: 10px;
  border: none;
  -ms-transform: rotate(0);
  transform: rotate(0);
  transition: transform 0.5s ease-in-out;

  display: inline-block;
  font: normal normal normal 14px/1 FontAwesome !important;
  font-size: inherit;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#bill-manager-hos .el-tree-node__expand-icon:before {
  content: "\F07B";
}

#bill-manager-hos .el-tree-node__expand-icon.expanded {
  -ms-transform: rotate(360deg);
  transform: rotate(360deg);
}

#bill-manager-hos .el-tree-node__expand-icon.expanded:before {
  content: "\F115";
}

#bill-manager-hos .el-tree-node__expand-icon.is-leaf {
  margin-left: 0;
}

#bill-manager-hos .el-tree-node__expand-icon.is-leaf:before {
  content: "";
}

#bill-manager-hos .table-col-bg {
  background-color: cadetblue;
}

#bill-manager-hos .text-grey {
  color: darkgrey;
}
</style>
