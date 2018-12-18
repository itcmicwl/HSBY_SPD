<template>
  <el-row :gutter="10" id="printContent" class="printArea" v-show="false">
    <el-row>
      <el-col :span="24" align="center" style="padding-bottom:20px;font-size:20px;">
        <span v-if="filters.sDate.length>0"><b>{{ filters.sDate[0] |dateFormat('YYYY-MM-DD') }}至{{ filters.sDate[1] |dateFormat('YYYY-MM-DD') }}医院产品出库信息</b></span>
        <span v-else><b>医院产品出库信息</b></span>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12" style="padding-bottom:10px">
        <span>请购科室：{{ filters.deptName==''? '所有科室':filters.deptName }}</span>
      </el-col>
      <el-col :span="12" style="padding-bottom:10px">
        <span>配送商：国药集团上海医疗器械有限公司</span>
      </el-col>
      <el-col :span="12" style="padding-bottom:10px">
        <span>地址：江杨南路888号</span>
      </el-col>
      <el-col :span="12" style="padding-bottom:10px">
        <span>电话：021-51699722</span>
      </el-col>
      <el-col :span="12" style="padding-bottom:10px">
        <span>时间：{{ new Date() |dateFormat('YYYY-MM-DD') }}</span>
      </el-col>
    </el-row>
    <table class="gridtable">
      <tr>
        <th width="100">单据日期</th>
        <th width="100">商品名称</th>
        <!--<th width=150>出库类型</th>
                <th width=150>出库方式</th>-->
        <th width="100">规格型号</th>
        <th width="40" align="center">单位</th>
        <th width="60">出库科室</th>
        <th width="60">入库科室</th>
        <th width="40">单价</th>
        <th width="40">数量</th>
        <th width="40">金额</th>
      </tr>
      <tr v-for="item in rowDatas" :key="item.id">
        <td>{{ item.fillDate|dateFormat('YYYY-MM-DD') }}</td>
        <td>{{ item.goodsName }}</td>
        <!--<td>{{item.outStockKType}}</td>
                <td>{{item.outStockKind}}</td>-->
        <td>{{ item.goodsGg }}</td>
        <td align="center">{{ item.unit }}</td>
        <!--<td>{{item.outDeptName.indexOf('设备科')>0?'设备科':item.outDeptName}}</td>-->
        <td>设备科</td>
        <td>{{ item.inDeptName }}</td>
        <td>{{ item.price }}</td>
        <td>{{ item.outQty }}</td>
        <td>{{ item.outAmount }}</td>
      </tr>
    </table>
  </el-row>
</template>

<script>
import $ from "jquery";
import "../../../common/third/printThis/printThis";
import "../../../common/third/qrcode/jquery.qrcode.min";
export default {
  data() {
    return {
      printOrder: {
        deptName: "",
        ownExpense: "",
        financialAppropriation: "",
        donation: "",
        researchFee: "",
        total: ""
      },
      rowDatas: [],
      /**过滤条件 */
      filters: {
        sDate: [],
        deptIds: [],
        goodsName: "",
        deptName: ""
      }
    };
  },

  methods: {
    print(datas, filters) {
      this.rowDatas = datas;
      this.filters = filters;
      $("#printContent").printThis({
        debug: false,
        importCSS: true,
        importStyle: true,
        printContainer: true,
        loadCSS: "./static/style/print.css",
        pageTitle: this.user.corpName,
        removeInline: false,
        printDelay: 333,
        header: null,
        formValues: true
      });
    }
  }
};
</script>
>

<style scoped>
#printContent {
  padding: 10px 10px 10px 10px;
}
table.gridtable {
  font-family: verdana, arial, sans-serif;
  font-size: 10px;
  color: #333333;
  border-width: 1px;
  border-color: #666666;
  border-collapse: collapse;
}
table.gridtable th {
  border-width: 1px;
  border-left: 0px;
  border-right: 0;
  padding: 5px;
  border-style: solid;
  border-color: #666666;
  background-color: #dedede;
  text-align: left;
  height: 30px;
}
table.gridtable td {
  border-top: 1px;
  border-left: 0px;
  border-right: 0;
  border-bottom: 1px solid #000;
  padding: 1px;
  border-style: solid;
  border-color: #666666;
  background-color: #ffffff;
  text-align: left;
}
</style>
