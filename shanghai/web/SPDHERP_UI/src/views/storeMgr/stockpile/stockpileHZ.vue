<template>
  <div id="stocMgr-stockpile-stockpileHZ" style="height:100%;">
    <el-row style="height:35px">
      <el-col>
        <el-form :inline="true" class="el-form-item-nomsg">
          <el-form-item label="库房">
            <el-select v-model="filters.staffStock" placeholder="请选择库房" @change="stocChange">
              <el-option v-for="item in staffStocks" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="供货单位">
            <el-input v-model="filters.provName" placeholder="请输入供货单位" > </el-input>
          </el-form-item>
          <el-form-item label="生产厂商">
            <el-input v-model="filters.mfrsName" placeholder="请输入生产厂商" > </el-input>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="filters.goodsName" placeholder="请输入商品名称或规格" > </el-input>
          </el-form-item>
          <el-button type="primary" icon="search" @click="getListStockPileHZ(1)">查询</el-button>
          <el-button type="primary" @click="resetFilter()">重置</el-button>
        </el-form>
      </el-col>
    </el-row>
    <el-row style="height:calc(100% - 35px)">
      <el-col :span="4" style="height:100%">
        <el-tree :data="kindsValue" node-key="id" :highlight-current="true" :props="defaultProps" style="height:calc(100% - 15px)" default-expand-all @node-click="handleNodeClick" :expand-on-click-node="false"></el-tree>
      </el-col>
      <el-col :span="20" style="height:100%;padding-left:5px;">
        <el-table highlight-current-row height="outter" :data="goodList" style="width: 100%;height:calc(100% - 26px)" border fit :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
          <el-table-column prop="code" label="商品编号" header-align="center" width="100" ></el-table-column>
          <el-table-column prop="goodsName" min-width="250" label="商品名称" header-align="center" ></el-table-column>
          <el-table-column prop="goodsGg" width="85" label="商品规格" header-align="center" ></el-table-column>
          <el-table-column prop="made" width="65" label="产地" header-align="center" ></el-table-column>
          <el-table-column prop="qty" width="65" label="可用数量" header-align="center" ></el-table-column>
          <el-table-column prop="lockqty" width="65" label="锁定数量" header-align="center" ></el-table-column>

          <el-table-column prop="price" width="65" label="单价" header-align="center" ></el-table-column>
          <el-table-column prop="je" width="65" label="可用金额" header-align="center" ></el-table-column>
          <el-table-column prop="unit" width="65" label="单位" header-align="center" ></el-table-column>
          <el-table-column prop="mfrsName" header-align="center" width="200" label="生产厂商" ></el-table-column>
          <el-table-column prop="provName" header-align="center" width="150" label="供应商" show-overflow-tooltip></el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="getListStockPileHZ" small :current-page.sync="pageNum" :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="rTotal" style="float:right;" small>
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      defaultProps: {
        children: "children",
        label: "label",
        id: "id",
        pId: "pid",
        code: "code",
        hosId: "hosId",
        state: "state"
      },
      filters: {
        provName: null,
        hosId: null,
        lbsx: null,
        staffStock: null,
        mfrsName: null,
        goodsName: null
      },
      kindsValue: [],
      staffStocks: [],
      goodList: [],
      pageNum: 1,
      pageSize: 30,
      rTotal: 0
    };
  },
  mounted() {
    this.filters.hosId = this.user.corpId;
    this.getKinds();
    this.getStaffStock();
  },
  methods: {
    getParams() {
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      };

      return params;
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getListStockPileHZ();
    },
    getKinds: function() {
      this.loading = true;
      let param = {
        orderBy: "",
        queryObject: { hosId: this.filters.hosId },
        page: 0,
        rows: 0
      };
      this.axios
        .post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param)
        .then(res => {
          this.kindsValue = res.data.data;
          this.loading = false;
        });
    },
    getStaffStock: function() {
      this.axios
        .get("/spdHERPService/stockMgr/out/requestOut/getStockList")
        .then(res => {
          this.staffStocks = res.data.data;
          this.loading = false;
          if (this.filters.staffStock == null) {
            if (this.staffStocks.length > 0) {
              this.filters.staffStock = this.staffStocks[0].id;
            } else {
              this.filters.staffStock = "";
            }
          }
        });
    },
    handleNodeClick(data) {
      this.selectedNode = data;
      this.filters.lbsx = data.id;
      this.getListStockPileHZ(1);
    },
    stocChange(data) {
      this.filters.staffStock = data;
      this.getListStockPileHZ(1);
    },
    resetFilter: function() {
      this.filters.provName = "";
      this.filters.mfrsName = "";
      this.filters.goodsName = "";
      this.getListStockPileHZ(1);
    },
    getListStockPileHZ: function(pIndex) {
      // this.pageNum = pIndex;
      this.loading = true;
      var param = {
        orderBy: "",
        queryObject: {
          stocId: this.filters.staffStock,
          lbsx: this.filters.lbsx,
          provName: this.filters.provName,
          mfrsName: this.filters.mfrsName,
          goodsName: this.filters.goodsName
        },
        page: this.pageNum,
        rows: this.pageSize
      };
      this.axios.post("/spdHERPService/stockPile/listStockPileHZ", param).then(
        res => {
          this.loading = false;
          if (res.data.code == "0") {
            this.goodList = res.data.data.data;
            this.rTotal = res.data.data.total;
          } else {
            this.$message.error(res.data.msg);
          }
        },
        err => {
          this.loading = false;
        }
      );
    }
  }
};
</script>

<style>
#stocMgr-stockpile-stockpileHZ .el-tree-node__expand-icon {
  cursor: pointer;
  width: auto;
  height: auto;
  margin-left: 10px;
  border: none;
  -ms-transform: rotate(0);
  transform: rotate(0);
  transition: transform 0.5s ease-in-out;

  display: inline-block;
  font: normal normal normal 14px/1 FontAwesome;
  font-size: inherit;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#stocMgr-stockpile-stockpileHZ .el-tree-node__expand-icon:before {
  content: "\F07B";
}

#stocMgr-stockpile-stockpileHZ .el-tree-node__expand-icon.expanded {
  -ms-transform: rotate(360deg);
  transform: rotate(360deg);
}

#stocMgr-stockpile-stockpileHZ .el-tree-node__expand-icon.expanded:before {
  content: "\F115";
}

#stocMgr-stockpile-stockpileHZ .el-tree-node__expand-icon.is-leaf {
  margin-left: 0;
}

#stocMgr-stockpile-stockpileHZ .el-tree-node__expand-icon.is-leaf:before {
  content: "";
}
</style>
