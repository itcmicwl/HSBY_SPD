<!--医院产品出库信心查询-->
<template>
  <div style="height: 100%;" id="hosOutStocGoodsQueryDiv">
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
      <!--工具条-->
      <div style="padding-bottom: 1px;">
        <el-form :inline="true" :model="filters" label-width="100px" ref="goodsName">
          <el-form-item label="单据日期范围：">
            <el-date-picker v-model="filters.sDate" type="daterange" align="right"
                            :unlink-panels="true" range-separator="至" start-placeholder="开始日期"
                            @change="selectDateRangChange" :editable="false" :clearable="false"
                            end-placeholder="结束日期" :picker-options="pickerOptions2" >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="入库科室：">
            <el-select v-model="filters.deptIds" placeholder="所有科室"
                       @change="inDeptChange">
              <el-option v-for="item in userDepts" :key="item.deptId" :label="item.deptName" :value="item.deptId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商品名/规格：">
            <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入供应商名称或规格" ></el-input>
          </el-form-item>
          <el-button :inline="true" type="info" @click="query">查询</el-button>
          <el-button :inline="true" type="info" @click="print">打印</el-button>
          <el-button :inline="true" type="info" @click="exportExcel">导出</el-button>
        </el-form>
      </div>
      <!--列表-->
      <template>
        <el-table ref="thisTable" :data="rowDatas" row-key="id"
                  highlight-current-row v-loading="loading" border fit
                  class="deptgoods-tableheight" :row-class-name="tableRowClassName">
          <el-table-column type="index" align="center" width="50" label="序号"></el-table-column>
          <el-table-column prop="fillDate" header-align="center" align="center" width="80" label="单据日期" >
            <template slot-scope="scope">
              {{ scope.row.fillDate | dateFormat('YYYY-MM-DD') }}
            </template>
          </el-table-column>
          <el-table-column prop="outStockKType" align="center" label="出库方式" :formatter="typeFormatter" width="80"></el-table-column>
          <!-- <el-table-column prop="outStockKind" align="center" label="出库类型" :formatter="kindFormatter"  width="80"></el-table-column>-->
          <el-table-column prop="goodsName" header-align="center" label="商品名称" min-width="120"></el-table-column>
          <el-table-column prop="goodsGg" header-align="center" label="规格型号" min-width="60"></el-table-column>
          <el-table-column prop="certificateCode" header-align="center" label="注册证号" min-width="60"></el-table-column>
          <el-table-column prop="unit" header-align="center" align="center" label="单位" width="40"></el-table-column>
          <el-table-column prop="outDeptName" align="center" label="出库部门" width="120"></el-table-column>
          <el-table-column prop="inDeptName" align="center" label="入库部门" width="80"></el-table-column>
          <el-table-column prop="price" header-align="center" align="center" label="出库单价" width="80"></el-table-column>
          <el-table-column prop="outQty" align="center" label="出库数量" width="80"></el-table-column>
          <el-table-column prop="outAmount" header-align="center" align="center" label="出库金额" width="80"></el-table-column>
        </el-table>
        <!--分业工具栏-->
        <el-col :span="24" class="toolbar pageBar">
          <el-pagination @size-change="handleSizeChange"
                         @current-change="handleCurrentChange" :current-page="page"
                         :page-sizes="[20, 100, 200, 400]" :page-size="pageSize" :total="total"
                         layout="total, sizes, prev, pager, next" style="float:right;" small>
          </el-pagination>
        </el-col>
      </template>
      <outStockQueryPrint ref="purPrintD"></outStockQueryPrint>
    </el-col>
  </div>

</template>
<script>
import outStockQueryPrint from "./components/outStockQueryPrint";
import moment from "moment";
import $ from "jquery";
import "../../common/third/tableExport/tableExport";
import "../../common/third/tableExport/jquery.base64";
export default {
  components: { outStockQueryPrint },
  data() {
    return {
      baseURL: "/spdHERPService/stockMgr/out/billMgr",
      hosId: "",
      deptId: "",
      hosName: "",
      deptName: "",
      userDepts: [],

      CKLXDict: {}, //出库单类型
      CGMSDict: {}, //出库方式

      pickerOptions2: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      /**过滤条件 */
      filters: {
        sDate: [],
        deptIds: [],
        goodsName: "",
        deptName: ""
      },

      total: 0,
      page: 1,
      pageSize: 20,
      loading: false,
      rowDatas: [],
      printDatas: [],
      tableModel: []
    };
  },
  mounted() {
    (this.deptId = this.user.orgId), //科室Id
      (this.hosId = this.user.corpId); //单位id
    this.hosName = this.user.corpName;
    this.deptName = this.user.orgName;

    // this.getHosOutStocGoodsQuery(1);
    this.getDict("CKLX").then(res => {
      this.CKLXDict = res;
    });
    this.getDict("CGMS").then(res => {
      this.CGMSDict = res;
    });
    /**查询时间默认为 上月25号至本月25号 */
    var nowDate = new Date();
    var thisMonth = nowDate.getMonth() + 1;
    var thisDay = nowDate.getDate();
    var start = null;
    if (thisMonth == 1) {
      start = new Date(nowDate.getFullYear() - 1 + "/" + 12 + "/25 00:00:00");
    } else {
      start = new Date(
        nowDate.getFullYear() + "/" + nowDate.getMonth() + "/25 00:00:00"
      );
    }
    var end = new Date();
    end.setTime(end.getTime() + 3600 * 1000 * 24 * (25 - thisDay));
    this.filters.sDate = [start, end];

    this.getUserDepts();
    this.getHosOutStocGoodsQuery(1);
    this.getPrintData();
  },
  methods: {
    //获取产品列表
    getHosOutStocGoodsQuery: function(pIndex) {
      this.page = pIndex;
      var param = {
        orderBy: "",
        queryObject: {
          goodsName: this.filters.goodsName,
          deptIds: this.filters.deptIds == "" ? [] : [this.filters.deptIds],
          //inDeptId:this.filters.deptIds,
          //fillMonth:this.applyMonth
          /*sDate: this.filters.sDate?this.filters.sDate[0]:null,
                        eDate: this.filters.sDate?this.filters.sDate[1]:null,*/
          sDate: this.filters.sDate
            ? moment(this.filters.sDate[0]).format("YYYY-MM-DD")
            : null,
          eDate: this.filters.sDate
            ? moment(
                moment(this.filters.sDate[1])
                  .add(1, "days")
                  .format("YYYY-MM-DD")
              )
                .subtract(1, "seconds")
                .format("YYYY-MM-DD HH:mm:ss")
            : null,
          hosId: this.hosId
        },
        page: this.page,
        rows: this.pageSize
      };
      this.loading = true;
      this.axios
        .post(
          "/spdHERPService/reportAnalysis/reportStatistics/getHosOutStockGoodsQuery",
          param
        )
        .then(
          res => {
            this.loading = false;
            if (res.data.code == "0") {
              this.rowDatas = res.data.data.data;
              this.total = res.data.data.total;
            } else {
              this.$message.error(res.data.msg);
            }
          },
          err => {
            this.loading = false;
          }
        );
    },
    print: function() {
      this.$refs.purPrintD.print(this.printDatas, this.filters);
    },
    /**获取打印或导出数据 */
    getPrintData: function() {
      var param = {
        orderBy: "",
        queryObject: {
          goodsName: this.filters.goodsName,
          hosId: this.hosId,
          deptIds: this.filters.deptIds == "" ? [] : [this.filters.deptIds],
          //inDeptId:this.filters.deptIds,
          sDate: this.filters.sDate
            ? moment(this.filters.sDate[0]).format("YYYY-MM-DD")
            : null,
          eDate: this.filters.sDate
            ? moment(
                moment(this.filters.sDate[1])
                  .add(1, "days")
                  .format("YYYY-MM-DD")
              )
                .subtract(1, "seconds")
                .format("YYYY-MM-DD HH:mm:ss")
            : null,
          hosId: this.hosId
        }
      };
      this.axios
        .post(
          "/spdHERPService/reportAnalysis/reportStatistics/getHosOutStockGoodsQuery",
          param
        )
        .then(
          res => {
            if (res.data.code == "0") {
              var temp = res.data.data.data;
              for (var item in temp) {
                temp[item].outStockKType = this.CGMSDict[
                  temp[item].outStockKType
                ];
                temp[item].outStockKind = this.CKLXDict[
                  temp[item].outStockKind
                ];
              }
              this.printDatas = temp;
            } else {
              this.$message.error(res.data.msg);
            }
          },
          err => {}
        );
    },
    query: function() {
      this.getHosOutStocGoodsQuery(1);
      this.getPrintData();
    },
    /**导出excel */
    exportExcel: function() {
      let tableHtml =
        "<table><thead><tr><th>序号</th><th>单据日期</th><th>出库方式</th><th>出库类型</th>" +
        "<th>商品名称</th><th>规格型号</th><th>注册证号</th><th>计量单位</th>" +
        "<th>出库部门</th><th>入库部门</th><th>出库数量</th>" +
        "<th>单价</th><th>总金额</th><th>销售单号</th></tr></thead><tbody>";
      if (this.printDatas.length > 0) {
        //this.$refs.purPrintD.print(res.data.data.data,this.filters);
        this.printDatas.forEach(function(item, index) {
          tableHtml +=
            "<tr>" +
            "<td>" +
            (index + 1) +
            "</td>" +
            "<td>" +
            item.fillDate +
            "</td>" +
            "<td>" +
            item.outStockKType +
            "</td>" +
            "<td>" +
            item.outStockKind +
            "</td>" +
            "<td>" +
            item.goodsName +
            "</td>" +
            "<td>" +
            item.goodsGg +
            "</td>" +
            "<td>" +
            item.certificateCode +
            "</td>" +
            "<td>" +
            item.unit +
            "</td>" +
            "<td>" +
            (item.outDeptName ? item.outDeptName : "") +
            "</td>" +
            "<td>" +
            item.inDeptName +
            "</td>" +
            "<td>" +
            item.outQty +
            "</td>" +
            "<td>" +
            item.price +
            "</td>" +
            "<td>" +
            item.outAmount +
            "</td>" +
            "<td>" +
            item.saleBillId +
            "</td>" +
            "</tr>";
        });
        tableHtml += "</tbody></table>";
        $(tableHtml).tableExport({
          type: "excel",
          escape: "false",
          tableName: this.deptName + "收货汇总"
        });
      } else {
        this.$message.error("无数据导出！");
        return false;
      }
    },
    /**获取当前用户对应可见科室 */
    getUserDepts: function() {
      var param = {
        orderBy: "",
        queryObject: {
          userId: this.user.userId
        }
      };
      this.axios
        .post(
          "/spdHERPService/reportAnalysis/reportStatistics/getUserDepts",
          param
        )
        .then(
          res => {
            if (res.data.code == "0") {
              this.userDepts.push({ deptId: "", deptName: "全部" });
              for (let o = 0; o < res.data.data.data.length; o++) {
                this.userDepts.push(res.data.data.data[o]);
              }
              // this.userDepts = res.data.data.data;
            } else {
              this.$message.error(res.data.msg);
            }
          },
          err => {}
        );
    },
    inDeptChange: function(deptId) {
      this.deptId = deptId;
      if (this.userDepts.length > 0) {
        this.deptName = this.userDepts.find((dept, index) => {
          return dept.deptId == deptId;
        }).deptName;
        this.filters.deptName = this.deptName;
      }
      this.$refs.thisTable.clearSelection();
      this.getHosOutStocGoodsQuery(1);
      this.getPrintData();
    },
    /**日期变化事件 */
    selectDateRangChange: function() {
      this.$refs.thisTable.clearSelection();
      this.getHosOutStocGoodsQuery(1);
      this.getPrintData();
    },
    typeFormatter(row, column, cellValue) {
      return this.CGMSDict[cellValue];
    },
    kindFormatter(row, column, cellValue) {
      return this.CKLXDict[cellValue];
    },

    /**行样式 */
    tableRowClassName(row, index) {
      // return (row.monthQty > row.mQtyUpper) ? 'row-disabled-red-nobg' : ''
    },
    /**分业处理事件 */
    handleCurrentChange: function(val) {
      this.page = val;
      this.getHosOutStocGoodsQuery(this.page);
    },
    handleSizeChange: function(val) {
      this.pageSize = val;
      this.getHosOutStocGoodsQuery(this.page);
    },
    getDict(dict) {
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getDict", {
            params: {
              dict
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              let dicts = {};
              for (let item of res.data.data) {
                dicts[item.value] = item.name;
              }
              resolve(dicts);
            } else {
              reject(res.data.msg);
            }
          });
      });
    }
  }
};
</script>
<style>
#hosOutStocGoodsQueryDiv .el-checkbox__label {
  font-size: 12px;
  vertical-align: middle;
}
#hosOutStocGoodsQueryDiv .el-checkbox-group {
  vertical-align: middle;
}
#hosOutStocGoodsQueryDiv .deptgoods-tableheight {
  width: 100%;
  height: calc(100% - 90px);
}
#hosOutStocGoodsQueryDiv .deptGoods {
  border-radius: 6px;
  padding-bottom: 10px;
  padding-top: 12px;
  padding-right: 19px;
  border: 1px solid #d1dbe5;
  padding-left: 0px;
  margin-left: 15px;
  margin-right: 15px;
}
#hosOutStocGoodsQueryDiv .deptGoods .el-row {
  height: 36px;
}

#hosOutStocGoodsQueryDiv .pageBar {
  border-bottom: 1px solid #d1dbe5;
  border-left: 1px solid #d1dbe5;
  border-right: 1px solid #d1dbe5;
}
</style>
