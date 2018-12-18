<template>
    <el-row :gutter="10" id="printContent" class="printArea" v-show="false">
        <el-col :span="24" align="center" style="padding-bottom:10px">
            <span v-if="filters.sDate.length>0"><b>{{ filters.sDate[0] |dateFormat('YYYY-MM-DD')}}至{{ filters.sDate[1] |dateFormat('YYYY-MM-DD')}}
            {{filters.inDeptName}}产品收货查询汇总</b></span>
            <span v-else><b>医院产品收货查询汇总</b></span>
        </el-col>
        
        <table class="gridtable">
            <tr>
                <th width=30>序号</th>
                <th width=150>商品名称</th>
                <th width=40>规格型号</th>
                <th width=40>计量单位</th>
                <th width=40>erp编码</th>
                <th width=40>产地</th>
                <th width=40>收货数量</th>
                <th width=40>单价</th>
                <th width=40>总金额</th>
                <!--<th width=40>供应商</th>-->
            </tr>
            <tr v-for="(item,index) in printDatas" :key="item.goodsId">
                <td align="center">{{ index+1 }}</td>
                <td>{{item.goodsName}}</td>
                <td>{{item.goodsGg}}</td>
                <td>{{item.unit}}</td>
                <td>{{item.erpCode}}</td>
                <td>{{item.made}}</td>
                <td align="center">{{item.receiptCount}}</td>
                <td align="center">{{item.inPrice}}</td>
                <td align="center">{{item.totalAmount}}</td>
                <!--<td>{{item.provName}}</td>-->
            </tr>
        </table>
    </el-row>
</template>

<script>
import $ from 'jquery';
import '../../../common/third/printThis/printThis';
import '../../../common/third/qrcode/jquery.qrcode.min';
export default {
  data(){
      return {
        printDatas: [],
        filters: { 
             sDate:[]
        },
      }
  },
 
  methods:{
        print(datas,filters) {
            this.printDatas = datas;
            this.filters = filters;
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
        }
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