<template> 
    <el-row style="height:calc(100% - 35px)" v-show="false">
      <el-col style="height:100%; overflow:auto;" id="printView">
        <div v-for="d in uniqueVoList" :key="d.id">
          <div class="printBarcode"  :key="d.uniqueCode">
            <div class="barDesc">
              &nbsp;&nbsp;名称：{{d.goodsName}}<br/>
              &nbsp;&nbsp;规格：{{d.goodsGg}}<br/>
              &nbsp;&nbsp;效期：{{d.expdtEndDate}}
              &nbsp;&nbsp;批号：{{d.batchCode}}<br/>
            </div>
            <div style="height:120px;">
              <svg :id='"barcode"+d.uniqueCode'></svg>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
</template>
<script>
  import moment from 'moment';
  import $ from 'jquery';
  import '../../common/third/printThis/printThis';
  import '../../common/third/qrcode/jquery.qrcode.min';
  import JsBarcode from 'jsbarcode';
  export default {
    data(){
      return{
        uniqueVoList:[],
        loading: false
      }
    },
    methods:{
      printWym(lstUniqueCodes) {
        this.uniqueVoList = lstUniqueCodes;
        this.$nextTick(function () {
          this.uniqueVoList.forEach(u=>{
            JsBarcode("#barcode"+u.uniqueCode , u.uniqueCode, { height: 50, displayValue: false });
          })
        })
        $("#printView").printThis({
          debug: false,
          importCSS: true,
          imporSttyle: true,
          printContainer: true,
          loadCSS: './static/style/print.css',
          pageTitle: "唯一码",
          removeInline: false,
          printDelay: 333,
          header: null,
          formValues: true
        });
      }
    }
  }
</script>
<style>
  .printBarcode{
    font-size: xx-small;
    color: black;
    margin-bottom: 45px;
  }
  .barDesc{
    margin-left: 9px;
    margin-bottom: -5px;
    height: 55px;
  }
  #uniqueCode .table_content{
    height: 100%
  }
</style>
