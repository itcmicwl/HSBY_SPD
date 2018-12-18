<template>
      <el-select v-model="stockIdM" placeholder="请选择" v-loading="loading" @change="sltChange">
            <el-option v-for="item in stockList" :key="item.stockId" :label="item.stockName" :value="item.stockId">
            </el-option>
    </el-select>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      stockList: [],
      stockIdM: null
    };
  },
  props: {
    value: String,
    hosId: String,
    userId: String,
    deptId: String
  },
  methods: {
    getStocsByParam: function() {
      this.loading = true;
      var _this = this;
      this.axios.get(
          `/spdHERPService/deptMgr/stocInfo/userStocks/${this.userId}/${this.hosId}/${this.deptId}`).then(res => {
          _this.stockList = res.data.data;
          if(!_this.stockIdM && Array.isArray(_this.stockList) && _this.stockList.length>0){
            _this.stockIdM = _this.stockList[0].stockId;
            this.sltChange(_this.stockIdM);
          }
           _this.loading = false;
        },err=>{ _this.loading = false;});
    },
    sltChange(stockId) {
      this.$emit("input",stockId);
      //var stockInfo = this.stockList.find(o=>{return o.stockId == stockId;});
      this.$emit("sltChanged", stockId);
    }
  },
  mounted() {
    this.stockIdM = this.value;
    this.getStocsByParam();
  }
};
</script>

