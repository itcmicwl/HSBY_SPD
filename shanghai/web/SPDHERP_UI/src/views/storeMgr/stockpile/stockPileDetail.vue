<template>
  <div id="stocMgr-stockpile-stockpileDetail" style="height:100%;">
    <el-row style="height:35px">
      <el-col>
        <el-form :inline="true" class="el-form-item-nomsg">
          <el-form-item label="库房">
            <el-select v-model="filters.staffStock" @change="stocChange" placeholder="请选择库房">
              <el-option v-for="item in staffStocks" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="供货单位">
            <el-input v-model="filters.provName" placeholder="请输入供货单位"> </el-input>
          </el-form-item>
          <el-form-item label="生产厂商">
            <el-input v-model="filters.mfrsName" placeholder="请输入生产厂商"> </el-input>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="filters.goodsName" placeholder="请输入商品名称或规格"> </el-input>
          </el-form-item>
          <el-button @click="getListStockPileDetail()" type="primary" icon="search">查询</el-button>
          <el-button @click="resetFilter()" type="primary">重置</el-button>
        </el-form>
      </el-col>
    </el-row>
    <el-row style="height:calc(100% - 35px)">
      <el-col :span="4" style="height:100%">
        <el-tree :data="kindsValue" :expand-on-click-node="false" :highlight-current="true" :props="defaultProps" @node-click="handleNodeClick" node-key="id" style="height:calc(100% - 15px)" default-expand-all></el-tree>
      </el-col>
      <el-col :span="20" style="height:100%;padding-left:5px;">
        <el-table :data="goodList" :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}" highlight-current-row height="outter" style="width: 100%;height:calc(100% - 55px)" border fit>
          <el-table-column prop="code" label="商品编号" header-align="center" width="100"></el-table-column>
          <el-table-column prop="goodsName" min-width="250" label="商品名称" header-align="center"></el-table-column>
          <el-table-column prop="goodsGg" width="85" label="商品规格" header-align="center"></el-table-column>
          <el-table-column prop="made" width="65" label="产地" header-align="center"></el-table-column>
          <el-table-column prop="qty" width="65" label="库存数量" header-align="center"></el-table-column>
          <el-table-column prop="price" width="65" label="入库单价" header-align="center"></el-table-column>
          <el-table-column prop="je" width="65" label="库存金额" header-align="center"></el-table-column>
          <el-table-column prop="unit" width="65" label="单位" header-align="center"></el-table-column>
          <el-table-column prop="inTime" width="65" label="入库时间" header-align="center"></el-table-column>
          <el-table-column prop="batchId" width="65" label="入库批次" header-align="center"></el-table-column>
          <el-table-column prop="batchNo" width="65" label="产品批号" header-align="center"></el-table-column>
          <el-table-column prop="expdtEndDate" width="65" label="效期" header-align="center"></el-table-column>
          <el-table-column prop="sterilizationCode" width="65" label="灭菌批号" header-align="center"></el-table-column>
          <el-table-column prop="sterilizationDate" width="65" label="灭菌日期" header-align="center"></el-table-column>
          <el-table-column prop="sterilizationEndDate" width="65" label="灭菌效期" header-align="center"></el-table-column>
          <el-table-column prop="barcode" width="65" label="条码" header-align="center"></el-table-column>
          <el-table-column prop="shelfCode" width="65" label="货位" header-align="center"></el-table-column>
          <el-table-column prop="mfrsName" header-align="center" width="200" label="生产厂商"></el-table-column>
          <el-table-column prop="provName" header-align="center" width="150" label="供应商" show-overflow-tooltip></el-table-column>
        </el-table>
        <el-pagination :current-page.sync="pageNum" :page-size="pageSize" :page-sizes="[10, 20, 30, 50, 100]" :total="rTotal" @current-change="getListStockPileDetail" @size-change="handleSizeChange" small layout="total, sizes, prev, pager, next" style="float:right;">
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
        children: 'children',
        label: 'label',
        id: 'id',
        pId: 'pid',
        code: 'code',
        hosId: 'hosId',
        state: 'state',
      },
      filters: {
        provName: null,
        hosId: null,
        lbsx: null,
        staffStock: null,
        mfrsName: null,
      },
      kindsValue: [],
      staffStocks: [],
      expadoIds: [null],
      goodList: [],
      pageNum: 1,
      pageSize: 30,
      rTotal: 0,
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
        queryObject: {
          stocId: this.filters.staffStock,
          lbsx: this.filters.lbsx,
          provName: this.filters.provName,
          mfrsName: this.filters.mfrsName,
          goodsName: this.filters.goodsName,
        },
        page: this.pageNum,
        rows: this.pageSize,
      };

      return params;
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getListStockPileDetail();
    },
    getKinds: function() {
      this.loading = true;
      let param = { orderBy: '', queryObject: { hosId: this.filters.hosId }, page: 0, rows: 0 };
      this.axios.post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param).then(res => {
        this.kindsValue = res.data.data;
        this.loading = false;
      });
    },
    getStaffStock: function() {
      this.axios.get('/spdHERPService/stockMgr/out/requestOut/getStockList').then(res => {
        this.staffStocks = res.data.data;
        this.loading = false;
        if (this.filters.staffStock == null) {
          if (this.staffStocks.length > 0) {
            this.filters.staffStock = this.staffStocks[0].id;
          } else {
            this.filters.staffStock = '';
          }
        }
      });
    },
    handleNodeClick(data) {
      this.selectedNode = data;
      this.filters.lbsx = data.id;
      this.getListStockPileDetail();
    },
    stocChange(data) {
      this.filters.stocId = data;
      this.getListStockPileDetail();
    },
    resetFilter: function() {
      this.filters.provName = '';
      this.filters.mfrsName = '';
      this.filters.goodsName = '';
      this.getListStockPileDetail();
    },
    getListStockPileDetail: function() {
      // this.pageNum = pIndex;
      this.loading = true;
      var param = this.getParams();

      this.axios.post('/spdHERPService/stockPile/listStockPileDetail', param).then(
        res => {
          this.loading = false;
          if (res.data.code == '0') {
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
    },
  },
};
</script>

<style>
#stocMgr-stockpile-stockpileDetail .el-tree-node__expand-icon {
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

#stocMgr-stockpile-stockpileDetail .el-tree-node__expand-icon:before {
  content: '\F07B';
}

#stocMgr-stockpile-stockpileDetail .el-tree-node__expand-icon.expanded {
  -ms-transform: rotate(360deg);
  transform: rotate(360deg);
}

#stocMgr-stockpile-stockpileDetail .el-tree-node__expand-icon.expanded:before {
  content: '\F115';
}

#stocMgr-stockpile-stockpileDetail .el-tree-node__expand-icon.is-leaf {
  margin-left: 0;
}

#stocMgr-stockpile-stockpileDetail .el-tree-node__expand-icon.is-leaf:before {
  content: '';
}
</style>
