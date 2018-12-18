<template>
  <el-row :gutter="10" id="billPrintContent" class="printArea" v-show="false">
    <el-row>
      <el-col :span="24" style="padding-bottom:10px" align="center">
        <!--<span>{{ FYLXDict[currentOutStockBill.costKind ]}} </span>-->
        <span style="font-size:20px"><b>{{ kindFormatter(currentOutStockBill.outStockKind) }}单</b></span>
      </el-col>
      <el-col :span="8">
        <span class="title">出库单号:</span>
        <span>{{ currentOutStockBill.billId }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">出库类型:</span>
        <span>{{ kindFormatter(currentOutStockBill.outStockKind) }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">出库部门:</span>
        <span>{{ currentOutStockBill.outDeptName }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">领用部门:</span>
        <span>{{ inDeptName }}</span>
      </el-col>

      <el-col :span="8">
        <span class="title">状态:</span>
        <span>{{ CKDZTDict[currentOutStockBill.status] }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">出库人员:</span>
        <span>{{ currentOutStockBill.filler }}</span>
      </el-col>

      <el-col :span="8">
        <span class="title">制单时间:</span>
        <span>{{ currentOutStockBill.fillDate }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">费用类别:</span>
        <span>{{ FYLXDict[currentOutStockBill.costKind] }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">患者姓名:</span>
        <span>{{ currentOutStockBill.patientName }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">种类:</span>
        <span>{{ goodsCount }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">总数量:</span>
        <span>{{ goodsSum }}</span>
      </el-col>
      <!-- <el-col :span="8">
                <span class="title">总金额:</span>
                <span>{{ amount }}</span>
            </el-col>-->
    </el-row>

    <el-row v-if="!!currentOutStockBill.auditor && !!currentOutStockBill.accounter">
      <el-col :span="8">
        <span class="title">审核人员:</span>
        <span>{{ currentOutStockBill.auditorName }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">审核时间:</span>
        <span>{{ currentOutStockBill.auditDate }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">记帐人员:</span>
        <span>{{ currentOutStockBill.accounterName }}</span>
      </el-col>
      <el-col :span="8">
        <span class="title">记帐时间:</span>
        <span>{{ currentOutStockBill.accountDate }}</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <svg id="pbarcode"></svg>
      </el-col>
    </el-row>
    <!--表格-->
    <el-row>
      <table class="gridtable">
        <tr>
          <td align="center" colspan="9">出库单明细</td>
        </tr>
        <tr>
          <th width="30">序号</th>
          <th width="250">产品名称</th>
          <th widht="120">规格型号</th>
          <th widht="50">JDE编码</th>
          <th width="50">请领数量</th>
          <th width="50">已发数量</th>
          <th width="50">出库数量</th>
          <th width="50">销售单号</th>
          <th widht="40">单位</th>
        </tr>
        <tr v-for="(item,index) in currentOutStockBill.subList" :key="item.id">
          <td align="center">{{ index+1 }}</td>
          <td>{{ item.goodsName }}</td>
          <td>{{ item.goodsGg }}</td>
          <td align="center">{{ item.erpCode }}</td>
          <td align="center">{{ item.reqQty }}</td>
          <td align="center">{{ item.sentQty }}</td>
          <td align="center">{{ item.outQty }}</td>
          <td align="center">{{ item.saleBillId }}</td>
          <td align="center">{{ item.unit }}</td>
        </tr>
      </table>
    </el-row>

    <el-row>
      <table class="gridtable">
        <tr>
          <td align="center" colspan="7">请购单明细</td>
        </tr>
        <tr>
          <th width="40">序号</th>
          <th width="125">产品名称</th>
          <th width="60">规格</th>
          <th width="60">分类</th>
          <th width="60">请购数量</th>
          <th width="75">已配送数量</th>
          <th width="75">未配送数量</th>
        </tr>
        <tr v-if="item.applyQty-item.sendQty>0" v-for="(item,index) in deptBuySub" :key="item.id">
          <td align="center">{{ index+1 }}</td>
          <td>{{ item.goodsName }}</td>
          <td align="center">{{ item.goodsGg }}</td>
          <td align="center">{{ item.kindName }}</td>
          <td align="center">{{ item.applyQty }}</td>
          <td align="center">{{ item.sendQty }}</td>
          <td align="center">{{ item.applyQty-item.sendQty }}</td>
        </tr>
      </table>
    </el-row>

    <el-row>
      <el-col :span="14">
        <span class="title">收货地址:</span>
        <span>{{ currentOutStockBill.recAddress }}</span>
      </el-col>
      <el-col :span="4">
        <span class="title">联系人:</span>
        <span>{{ currentOutStockBill.recLinkman }}</span>
      </el-col>
      <el-col :span="6">
        <span class="title">联系电话:</span>
        <span>{{ currentOutStockBill.recLinkmanPhone }}</span>
      </el-col>
    </el-row>
  </el-row>
</template>

<script>
import moment from "moment";
import $ from "jquery";
import "../../../common/third/printThis/printThis";
import JsBarcode from "jsbarcode";
export default {
  data() {
    return {
      baseURL: "/spdHERPService/stockMgr/out/billMgr",

      ownStockList: [],
      currentStock: "",
      currentDeptId: "",

      CKLXDict: {},
      CKDZTDict: {},
      CGMSDict: {},
      FYLXDict: { "10": "日常消耗" },

      orgsData: [],
      inDeptName: "", //领用部门
      currentOutStockBill: {
        subList: []
      },
      deptBuySub: []
    };
  },
  computed: {
    goodsCount() {
      return new Set(this.currentOutStockBill.subList.map(o => o.goodsId)).size;
    },
    goodsSum() {
      return this.currentOutStockBill.subList.reduce(
        (acc, cur) => acc + cur.outQty,
        0
      );
    },
    amount() {
      return this.currentOutStockBill.subList.reduce(
        (acc, cur) => acc + cur.outQty * cur.price,
        0
      );
    }
  },
  mounted() {
    // this.getStockList().then(result => {
    //   this.ownStockList = result
    //   if (result) {
    //     this.currentStock = result[0].id
    //   }
    //   this.getDict('CKLX').then(res => {
    //     this.CKLXDict = res
    //   })
    //   this.getDict('CKDZT').then(res => {
    //     this.CKDZTDict = res
    //     this.CKDZTDict['undone'] = '待处理'
    //     this.CKDZTDict['all'] = '全部'
    //   })
    //   this.getDict('CGMS').then(res => {
    //     this.CGMSDict = res
    //   })
    // }).catch(err => {
    //   this.handleError(err)
    // })
  },
  methods: {
    print(thisOutStockBill, orgsData, deptBuySub, CKLXDict, CKDZTDict) {
      JsBarcode("#pbarcode", thisOutStockBill.billId, {
        height: 30,
        displayValue: false
      });
      this.currentOutStockBill = thisOutStockBill;
      this.orgsData = orgsData;
      this.deptBuySub = deptBuySub;
      this.CKLXDict = CKLXDict;
      this.CKDZTDict = CKDZTDict;
      this.getInDeptName(this.currentOutStockBill.inDeptId);
      $("#billPrintContent").printThis({
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
    },

    getInDeptName(orgId) {
      for (let i = 0; i < this.orgsData.length; i++) {
        let item = this.orgsData[i];
        if (item.id == orgId) {
          this.inDeptName = item.ename;
          break;
        }
      }
    },
    getStockList() {
      return new Promise((resolve, reject) => {
        this.axios.get(this.baseURL + "/getStockList").then(res => {
          if (res.data.code === 0) {
            resolve(res.data.data);
          } else {
            reject(res.data.msg);
          }
        });
      });
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
    },
    getBillDetail(billId) {
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getBillDetail", {
            params: {
              id: billId
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              resolve(res.data.data);
            } else {
              reject(res.data.msg);
            }
          })
          .catch(err => {
            reject(err);
          });
      });
    },
    typeFormatter(type) {
      return this.CGMSDict[type];
    },
    kindFormatter(kind) {
      return this.CKLXDict[kind];
    },
    statusFormatter(status) {
      return this.CKDZTDict[status];
    },
    handleError(err, loading) {
      return new Promise((resolve, reject) => {
        this.$msgbox({ title: "错误", type: "error", message: err })
          .then(action => {
            if (loading !== null && loading !== undefined) {
              loading = false;
            }
            resolve(action);
          })
          .catch(err => {
            reject(err);
          });
      });
    }
  }
};
</script>

<style>
#billPrintContent {
  padding: 10px 10px 10px 20px;
}
table.gridtable {
  font-family: verdana, arial, sans-serif;
  font-size: 11px;
  color: #333333;
  border-width: 1px;
  border-color: #666666;
  border-collapse: collapse;
  width: 100%;
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
