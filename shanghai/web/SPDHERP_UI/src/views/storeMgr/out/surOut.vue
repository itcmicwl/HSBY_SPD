<template>
  <div id="sur-out" class="full-height">
    <div>
      <span>出库库房：</span>
      <el-select v-model="currentStock" class="stock-select" placeholder="请选择房库">
        <el-option v-for="item in ownStockList" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-input v-model="inputSurPkgCode" :disabled="loading" @keyup.enter.native="getSurPkgDetail(inputSurPkgCode)" style="width:250px" clearable placeholder="请录入手术包码" autofocus></el-input>
      <el-button :disabled="canSubmit" @click="handleSubmitClick" type="primary" v-loading="loading" round>出库</el-button>
    </div>

    <el-table ref="mainTable" :data="surPkgList" class="table-style" @row-click="handleTableRowClick" @selection-change="handleSelectionChange" v-loading="loading" highlight-current-row height="outter" border row-key="id">
      <el-table-column :selectable="checkSelectable" type="selection" width="45" reserve-selection> </el-table-column>
      <el-table-column type="index" label="序号" width="45"></el-table-column>
      <el-table-column label="手术包名称" prop="surName" width="150"></el-table-column>
      <el-table-column label="请购科室" prop="deptName"></el-table-column>
      <el-table-column label="请购单号" prop="applyBillId"></el-table-column>
      <el-table-column :formatter="formatDate" label="请购时间" prop="packageDate"></el-table-column>
    </el-table>

    <el-table ref="detailTable" :data="surPkgDetail" class="table-style" v-loading="loading" style="margin-top:10px;" highlight-current-row height="outter" border>
      <el-table-column type="index" label="序号" width="45"></el-table-column>
      <el-table-column label="名称" prop="goodsName"></el-table-column>
      <el-table-column label="规格" prop="goodsSpec"></el-table-column>
      <el-table-column label="批号" prop="batchCode"></el-table-column>
      <el-table-column label="数量" prop="qty"></el-table-column>
      <el-table-column :formatter="formatUniqueKind" label="唯一码管理" prop="uniqueKind"></el-table-column>
      <el-table-column label="唯一码" prop="uniqueCode"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import cloneDeep from "lodash/cloneDeep";
import barcodeUtil from "../../../common/js/barcodeUtil.js";
import moment from "moment";

export default {
  components: {},
  data() {
    return {
      BASE_URL: "/spdHERPService/stockMgr/out/sur",
      BARCODE_PARSE_URL:
        "/spdHERPService/baseData/baseDataMaint/BarcodeParse/parseBarcodeHERP",
      ownStockList: [],
      currentStock: "",
      surPkgList: [],
      surPkgDetail: [],
      currentSurPkg: "",
      mainTableSelection: [],
      loading: false,
      inputSurPkgCode: ""
    };
  },
  computed: {
    currentApplyBillId() {
      if (this.mainTableSelection.length > 0) {
        return this.mainTableSelection[0].applyBillId;
      }
    },
    canSubmit() {
      return !(this.mainTableSelection.length > 0);
    }
  },
  mounted() {
    this.getStockList();
  },
  methods: {
    formatDate(row, column, cellValue) {
      return moment(cellValue).format("YYYY-MM-DD");
    },
    formatUniqueKind(row, column, cellValue) {
      return cellValue === 3 ? "否" : "是";
    },
    checkSelectable(row, index) {
      return this.currentApplyBillId
        ? this.currentApplyBillId === row.applyBillId
          ? true
          : false
        : true;
    },
    getStockList() {
      this.axios
        .get(this.BASE_URL + "/getStockList")
        .then(res => {
          if (res.data.code === 0) {
            this.ownStockList = res.data.data;
            if (this.ownStockList && this.ownStockList.length !== 0) {
              this.currentStock = this.ownStockList[0].id;
            }
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(err => {
          this.$message.error(err);
        });
    },
    // listSurPkg() {
    //   this.axios
    //     .get(`${this.BASE_URL}/surpkg`, {
    //       params: {
    //         stockId: this.currentStock
    //       }
    //     })
    //     .then(res => {
    //       if (res.data.code === 0) {
    //         this.surPkgList = res.data.data.data;
    //         if (this.surPkgList.length === 1) {
    //           this.getSurPkgDetail(this.surPkgList[0].surCode);
    //         }
    //       } else {
    //         this.$message.error(res.data.msg);
    //       }
    //     })
    //     .catch(err => {
    //       this.$message.error(err);
    //     });
    // },
    handleTableRowClick(row, event, column) {
      if (this.currentSurPkg === row.surCode) {
        return;
      }
      this.currentSurPkg = row.surCode;
      this.showSurPkgDetail(row.surCode);
    },
    getSurPkgDetail(surCode) {
      if (!surCode) {
        this.$message.error("请录入手术包码");
        return;
      }
      if (this.surPkgList.find(item => item.surCode === surCode)) {
        this.$message.error("手术包已扫描");
        return;
      }
      this.loading = true;
      this.axios
        .get(`${this.BASE_URL}/surpkg/${surCode}`)
        .then(res => {
          if (res.data.code === 0) {
            const data = res.data.data;
            this.surPkgList.push(data);
            this.$refs.mainTable.toggleRowSelection(data);
            this.showSurPkgDetail(surCode);
            this.inputSurPkgCode = "";
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(err => {
          this.$message.error(err);
        })
        .finally(_ => {
          this.loading = false;
        });
    },
    showSurPkgDetail(surCode) {
      this.currentSurPkg = surCode;
      this.surPkgDetail.splice(0);
      this.surPkgList
        .find(item => item.surCode === surCode)
        .surPkgSubList.forEach(sub => {
          sub.surPkgBatchList.forEach(batch => {
            this.surPkgDetail.push({
              goodsName: sub.goodsName,
              goodsSpec: sub.goodsSpec,
              batchCode: batch.batchCode,
              qty: batch.qty,
              uniqueKind: sub.uniqueKind,
              uniqueCode: batch.uniqueCode
            });
          });
        });
    },
    handleSelectionChange(selection) {
      if (selection) {
        if (new Set(selection.map(item => item.applyBillId)).size > 1) {
          this.$message.error("非同一请购单手术包，无法同时出库");
          if (this.mainTableSelection.length > 0) {
            this.mainTableSelection.forEach(item =>
              this.$refs.mainTable.toggleRowSelection(item)
            );
          } else {
            this.$refs.mainTable.clearSelection();
          }
        } else {
          this.mainTableSelection = selection;
        }
      }
    },
    handleSubmitClick() {
      this.loading = true;
      this.axios
        .post(
          `${this.BASE_URL}/surpkg`,
          this.mainTableSelection.map(item => item.surCode)
        )
        .then(res => {
          if (res.data.code === 0) {
            this.$message.success("生成出库单成功");
            this.surPkgList.splice(0);
            this.surPkgDetail.splice(0);
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(err => {
          this.$message.error(err);
        })
        .finally(_ => {
          this.loading = false;
        });
    }
  }
};
</script>

<style scoped="">
.stock-select {
  margin-bottom: 8px;
  width: 150px;
}

.full-height {
  height: 100%;
}

.table-style {
  height: calc(50% - 25px);
}
</style>

<style>
</style>
