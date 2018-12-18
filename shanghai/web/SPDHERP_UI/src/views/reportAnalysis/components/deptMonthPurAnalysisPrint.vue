<template>
   <el-row :gutter="10" id="billPrintContent" class="printArea" v-show="false">
      <el-row>
            <el-col :span="24" style="padding-bottom:10px" align="center" >
                <span><b>{{ orgName}} </b> &nbsp;</span>
                <span><b>{{new Date().getFullYear()}}年{{ applyMonth }}月请购产品分析</b></span>
            </el-col> 
        </el-row>
        <!--表格-->
        <el-row>
            <table class="gridtable">
                <tr>
                    <th width=40>序号</th>
                    <th width=150>产品名称</th>
                    <th width=150>规格型号</th>
                    <th width=60>单位</th>
                    <th width=60>请购月份</th>
                    <th widht=80>月请购数</th>
                    <th widht=80>请购上限</th>
                </tr>
                <tr v-for="(item,index) in goods" :key="item.id">
                    <td align="center">{{ index+1 }}</td>
                    <td>{{item.goodsName}}</td>
                    <td align="center">{{item.goodsGg}}</td>
                    <td align="center">{{item.unit}}</td>
                    <td align="center">{{item.fillYearMonth}}</td>
                    <td align="center">{{item.monthQty}}</td>
                    <td>{{item.mQtyUpper}}</td>
                </tr>
            </table>
        </el-row>
   </el-row>
</template>

<script>
import moment from 'moment';
import $ from 'jquery';
import '../../../common/third/printThis/printThis';
export default {
  data () {
    return {
      goods:[],
      applyMonth:'',
      orgName:''
    }
  },
  computed: {
    
  },
  methods: {
    print(goods,applyMonth,orgName){
        this.goods=goods;
        this.applyMonth=applyMonth;
        this.orgName = orgName;
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
    
    
  },
  mounted () {
    
  }
}
</script>

<style>
    #billPrintContent{
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
