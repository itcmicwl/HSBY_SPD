<template>
    <el-row :gutter="10" id="printContent" class="printArea"  v-show='false'>
        <el-col :span="24" align="center" style="padding-bottom:10px" >
            <span><b>{{new Date().getFullYear()}}年{{ fillMonth }}月医院低值耗材月核算</b></span>
        </el-col>
        <br/>
        <table class="gridtable">
            <tr>
                <th width=100>自筹经费</th>
                <th width=100>科室</th>
                <th width=100>财政拨款</th>
                <th width=100>捐赠</th>
                <th width=100>科研经费</th>
                <th width=100>总计</th>
            </tr>
            <tr v-for="item in rowDatas" :key="item.deptId">
                <td>{{item.deptName}}</td>
                <td>{{item.ownExpense}}</td>
                <td>{{item.financialAppropriation}}</td>
                <td>{{item.donation}}</td>
                <td>{{item.researchFee}}</td>
                <td>{{item.total}}</td>
            </tr>
        </table>
    </el-row>
</template>

<script>
import $ from 'jquery';
import '../../../common/third/printThis/printThis';
import '../../../common/third/qrcode/jquery.qrcode.min';
//import '../../common/third/qrcode/qrcode';
//import '../../common/third/qrcode/jquery.qrcode';
import JsBarcode from 'jsbarcode';
export default {
  data(){
      return {
          printOrder:{
              deptName:'',
              ownExpense:'',
              financialAppropriation:'',
              donation:'',
              researchFee:'',
              total:''
          },
        rowDatas: []
      }
  },
  watch:{
        fillMonth:function(newVal,oldVal){
            this.init(newVal);
        }
    },
  props:{fillMonth:String},
  methods:{
        //获取产品列表
        getHighValueConsume() {
            var param = {
                orderBy: "",
                queryObject: {
                    
                    // goodsName: this.filters.goodsName,
                    // hosId:this.hosId,
                    // deptId:this.deptId,
                    fillMonth:this.fillMonth
                }
            };
            this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthLowValueConsume', param).then(res => {
                if (res.data.code == "0") {
                    this.rowDatas = res.data.data.data;
                }else{
                    this.$message.error( res.data.msg);
                }
            }, err => {
            });
        },
        print() {
            $("#printContent").printThis({
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
        init(fillMonth){
            this.getHighValueConsume(fillMonth);
        }
  },
  mounted() {
        this.init(this.fillMonth);
    }
}
</script>
>

<style>
#printContent {
  padding: 10px 10px 10px 10px;
}
table.gridtable {
  font-family: verdana, arial, sans-serif;
  font-size: 11px;
  color: #333333;
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
  text-align: center;
}
</style>