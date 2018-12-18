<template>
  <el-dialog title="发票明细查看" :visible.sync="sendShow" :close-on-click-modal="false" width="90%">
    <el-row :gutter="10" style="height:100%;">
        <el-table :data="invoiceDetail" v-loading="detailLoading" style="width: 100%;" fit class="t_ygsendView">
            <el-table-column prop="fpmxbh" label="发票明细编号" width="160">
            </el-table-column>
            <el-table-column prop="sfwpsfp" label="是否无配送发票" width="150">
            </el-table-column>
            <el-table-column prop="wpsfpsm" label="无配送发票说明" width="180" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="sfch" label="是否冲红">
            </el-table-column>
            <el-table-column prop="hctbdm" label="耗材统编代码" width="90">
            </el-table-column>
            <el-table-column prop="yybddm" label="医院本地代码" width="90">
            </el-table-column>    
            <el-table-column prop="ggxhsm" label="规格型号说明">
            </el-table-column>
            <el-table-column prop="glmxbh" label="关联明细编号">
            </el-table-column>
            <el-table-column prop="xsddh" label="销售订单号" >
            </el-table-column>
            <el-table-column prop="scrq" label="生产日期" >
            </el-table-column>  
             <el-table-column prop="yxrq" label="有效日期" >
            </el-table-column>  
             <el-table-column prop="spsl" label="商品数量" >
            </el-table-column>  
            <el-table-column prop="wsdj" label="无税单价" >
            </el-table-column>
            <el-table-column prop="hsdj" label="含税单价" >
            </el-table-column> 
            <el-table-column prop="sl" label="税率" >
            </el-table-column> 
            <el-table-column prop="se" label="税额" >
            </el-table-column> 
            <el-table-column prop="hsje" label="含税金额" >
            </el-table-column> 
            <el-table-column prop="pfj" label="批发价" >
            </el-table-column> 
            <el-table-column prop="lsj" label="零售价" >
            </el-table-column>   
             <el-table-column prop="zczh" label="注册证号" >
            </el-table-column>               
        </el-table>
    </el-row>
    <div slot="footer" class="dialog-footer">
        <el-button @click="closeMe()" >关闭</el-button>
    </div>
</el-dialog>       
   
</template>
<script>
//import {invoiceBill}  from '@/config/mockData';
export default {
  data() {
    return {
      detailLoading: false,
      invoiceDetail: [],
      sltedRows:[]
    };
  },
  props: ["billid","sendShow"],
  watch: {
    billid(curVal, oldVal) {
      this.getSendBill(curVal);
    }
  },
  methods: {
    sltChange(rows) {
      this.sltedRows = rows;
    },
    getSendBill(billId) {
      if(!billId){return false;}
      this.detailLoading = true;
      var _this = this;
      this.axios.get("/spdHERPService/yg/invoice/getInvoiceDetailByBillId/"+billId).then(
          res => {
            _this.detailLoading = false;
            if (res.data.code == "0") {
              _this.invoiceDetail = res.data.data.detail;
            }
          },
          err => {
            _this.detailLoading = false;
          }
        );
    },    
    closeMe(){
      this.$emit("viewClose",false);
    }
  },
  mounted() {   
  }
};
</script>

<style>
.t_ygsendView {
  font-size: 12px;
  height: calc(100% - 100px);
  overflow-y: auto;
}
</style>
