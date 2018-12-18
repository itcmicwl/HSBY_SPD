<template>
  <el-row :gutter="10" id="billPrintContent" class="printArea" v-show='false'>
    <el-row>
      <el-col :span="24" style="padding-bottom:10px" align="center">
        <span  style="font-size:20px"><b>盘点单</b></span><br>
      </el-col>

      <el-col :span="8">
        <span class="title">盘点单号:</span>
        <span>{{ spdInventorys.id }}</span>
      </el-col>

      <el-col :span="8">
        <span class="title">医院名称:</span>
        <span>{{ spdInventorys.hosName }}</span>
      </el-col>

      <el-col :span="8">
        <span class="title">当前状态:</span>
        <span>{{ spdInventorys.state | state }}</span>
      </el-col>

      <el-col :span="8">
        <span class="title">操作人:</span>
        <span>{{ spdInventorys.operationer}}</span>
      </el-col>

      <el-col :span="8">
        <span class="title">操作时间:</span>
        <span>{{ spdInventorys.operationtime}}</span>
      </el-col>

      <el-col :span="24">
        <svg id="p_barcode"></svg>
      </el-col>
    </el-row>

    <el-row>
      <table class="gridtable">
        <tr>
          <!--<th width=40>序号</th>-->
          <th width=210>产品名称</th>
          <th widht=140>规格</th>
          <th width=50>批号</th>
          <th width=60>jde编码</th>
          <th width=40>库存数量</th>
          <th width=30>单位</th>
          <th width=130>货位</th>

        </tr>
        <tr v-for="item in spdInventoryList" :key="item.id">
          <!--<td align='center'>{{item.rowNum}}</td>-->
          <td align="center">{{item.goodsName}}</td>
          <td align="center">{{item.goodsGg}}</td>
          <td align="center">{{item.batchCode}}</td>
          <td align="center">{{item.erpCode}}</td>
          <td align='center'>{{item.stockSupplyQty}}</td>
          <td align='center'>{{item.unit}}</td>
          <td align='center'>{{item.goodsStockLocation}}</td>
        </tr>
      </table>
    </el-row>
  </el-row>
</template>

<script>
  import moment from 'moment';
  import {INVENTORY_STATUS} from "../../common/js/constance";
  import JsBarcode from 'jsbarcode';
  import $ from 'jquery';
  import '../../common/third/printThis/printThis'
  import '../../common/third/qrcode/jquery.qrcode.min'
  export default {
     data() {
       return {
         spdInventorys:{},
         spdInventoryList:[],
       }
     },
     filters: {
       state: function (value) {
         if (value) {
           return INVENTORY_STATUS[value].text;
         } else {
           return 0;
         }
       },
     },
     watch:{
       pBillId:function(newVal,oldVal){
         this.init(newVal);
       }
     },
     props:{pBillId:String},
     methods: {
       goBack() {
         this.$router.go(-1);
       },
       print(spdList) {
         this.spdInventorys = spdList.spdInventorys;
         this.spdInventoryList = spdList.spdInventoryList;
         $("#billPrintContent").printThis({
           debug: false,
           importCSS: true,
           importStyle: true,
           printContainer: true,
           loadCSS: './static/style/print.css',
           pageTitle: this.user.corpName,
           removeInline: false,
           printDelay: 333,
           header: null,
           formValues: true
         });
       },
       init(billId){
         if (billId) {
           JsBarcode("#p_barcode", billId, { height: 50, displayValue: false });
         }
       },
     },
    mounted() {
      var billId = this.$route.query.id;
      this.init(billId);
    }
   }
</script>

<style>
  #billPrintContent{
    padding: 10px 10px 10px 20px;
  }
  table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:5px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
  }
  table.gridtable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #dedede;
  }
  table.gridtable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
  }
</style>
