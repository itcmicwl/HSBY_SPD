<template>
   <el-row :gutter="10" id="hosMonthHighValueConsumeContent" class="printArea"  v-show='false'>
      <el-row>
            <el-col :span="24" align="center" style="padding-bottom:5px;">
                <span><b>{{new Date().getFullYear()}}年{{ applyMonth }}月低值耗材月消耗汇总</b></span>
            </el-col> 
        </el-row>
        <!--表格-->
        <el-row>
            <table class="gridtable">
                <tr>
                    <th width=60>序号</th>
                    <th width=150>物资分类</th>
                    <th width=100>期初金额</th>
                    <th width=100>入库金额</th>
                    <th width=100>出库金额</th>
                    <th width=100>结存金额</th>
                </tr>
                <tr v-for="(item,index) in printDatas" :key="item.id">
                    <td align="center">{{ index+1 }}</td>
                    <td align="center">{{item.goodsClassification}}</td>
                    <td align="center">{{item.initialAmount}}</td>
                    <td align="center">{{item.inStocAmount}}</td>
                    <td align="center">{{item.outStocAmount}}</td>
                    <td align="center">{{item.inventoryAmount}}</td>
                </tr>
            </table>
        </el-row>
   </el-row>
</template>

<script>
import $ from 'jquery';
import '../../../common/third/printThis/printThis';
import '../../../common/third/qrcode/jquery.qrcode.min';
export default {
  data () {
    return {
      printDatas:[],
    }
  },
  props:['applyMonth'],
  computed: {
    
  },
  methods: {
    print(printDatas){
      this.printDatas = printDatas;
      $("#hosMonthHighValueConsumeContent").printThis({
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
    
    
  },
  mounted () {
  }
}
</script>

<style>
    #hosMonthHighValueConsumeContent{
        padding: 10px 10px 10px 10px;
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
