<!--科室月请购金额、采购金额以及同比、环比升降趋势-->
<template>
   <div id = "deptMonthAverage">
    <el-row>
            <el-col :span="24">
                <el-card :body-style="{ padding: '0px' }" class="bigChart">
                    <el-table :data="tableData" border style="width: 100%;" max-height="260">
                        <el-table-column prop="month" label="月份" align="center" width="80"></el-table-column>
                        <el-table-column prop="reqPurchaseQuantity" label="科室月请购金额"></el-table-column>
                        <el-table-column prop="purchaseQuantity" label="科室月采购金额"> </el-table-column>
                        <el-table-column prop="similarRatio" align="center" label="同比"></el-table-column>
                        <el-table-column prop="ringRatio" align="center" label="环比"></el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
  export default {
    name:"monthPurchaseAmountRatio",
    data() {
      return {
        tableData: [{
           month: '2017年1月',
           reqPurchaseQuantity: '2554',
           purchaseQuantity: '1895',
           similarRatio: '↑',
           ringRatio: '↓',
        }, {
           month: '2017年2月',
           reqPurchaseQuantity: '4554',
           purchaseQuantity: '3895',
           similarRatio: '↑',
           ringRatio: '↓',
        }, {
           month: '2017年3月',
           reqPurchaseQuantity: '7554',
           purchaseQuantity: '6895',
           similarRatio: '↑',
           ringRatio: '↓',
        }, {
           month: '2017年4月',
           reqPurchaseQuantity: '6554',
           purchaseQuantity: '3895',
           similarRatio: '↑',
           ringRatio: '↓',
        }, {
           month: '2017年5月',
           reqPurchaseQuantity: '6554',
           purchaseQuantity: '3895',
           similarRatio: '↑',
           ringRatio: '↓',
        }, {
           month: '2017年6月',
           reqPurchaseQuantity: '6554',
           purchaseQuantity: '3895',
           similarRatio: '↑',
           ringRatio: '↓',
        }]
      }
    },
    methods: {
     
    },
  }
</script>

<style scoped>
  .bigChart{
      margin-top:10px;
  }
  .bigChart .vue-echarts{ 
    width: 100%;
    height: 250px;
  }
  .card_header {
    padding: 8px 20px;
    text-align: center;
} 
</style>
<style>
    #deptMonthAverage .el-table tr {
      height: 33px;
   }
</style>