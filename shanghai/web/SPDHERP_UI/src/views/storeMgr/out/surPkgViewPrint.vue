<template>
  <el-row :gutter="10" id="surPrintContent" class="printArea" v-show='false'>
    <el-row>
      <el-col :span="24" style="padding-bottom:10px" align="center">
        <span  style="font-size:20px"><b>手术包({{ surInfoPrintView.surCode }})</b></span><br>
      </el-col>
      <el-col :span="8">
        <span class="title">请购单号:</span>
        <span>{{ surInfoPrintView.applyBillId }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">源单号:</span>
        <span>{{ surInfoPrintView.sickerName }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">入院编号:</span>
        <span>{{ surInfoPrintView.inHosCode }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">手术排班号:</span>
        <span>{{ surInfoPrintView.surScheduleNo }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">打包人:</span>
        <span>{{ surInfoPrintView.packer }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">打包日期:</span>
        <span>{{ surInfoPrintView.packageDate | dateFormat('YYYY-MM-DD')}}</span>
      </el-col>
      <el-col :span="24">
        <svg id="p_barcode"></svg>
      </el-col>
    </el-row>
    <el-row>
      <table class="gridtable">
        <tr>
          <th width=250>产品名称</th>
          <th widht=150>规格</th>
          <th widht=100>唯一码</th>
          <th width=80>产地</th>
          <th width=80>数量</th>
          <th width=40>单位</th>
          <th width=150>生产厂商</th>
        </tr>
        <tr v-for="item in surInfoPrintView.pkgListDis">
          <td>{{item.goodsName}}</td>
          <td>{{item.goodsGg}}</td>
          <td>{{item.uniqueCode}}</td>
          <td>{{item.made}}</td>
          <td align='center'>{{item.qty}}</td>
          <td align='center'>{{item.unit}}</td>
          <td align='center'>{{item.mfrsName}}</td>
        </tr>
      </table>
    </el-row>
  </el-row>
</template>

<script>
  import moment from 'moment';
  import $ from 'jquery';
  import '../../../common/third/printThis/printThis';
  import '../../../common/third/qrcode/jquery.qrcode.min';
  import JsBarcode from 'jsbarcode';
  export default {
    components: {
    },
    data() {
      return {
        surInfoPrintView:{},
      }
    },
    mounted() {
      var pSurCode = this.$route.query.surCode;
      this.init(pSurCode);
    },
    watch:{
      pSurCode:function(newVal,oldVal){
        this.init(newVal);
      }
    },
    props:{pSurCode:String},
    methods: {
      print(val) {
        this.surInfoPrintView = val;
        $("#surPrintContent").printThis({
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
      }
    },
  }
</script>

<style>
  #surPrintContent{
    padding: 10px 10px 10px 20px;
  }
  table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
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
