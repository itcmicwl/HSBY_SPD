<template>
    <el-row v-loading="ploading" element-loading-text="正生成采购单请稍后..." style="height: 100%;">
        <el-row>
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="100px" class="order_view" label-position="left">
                    <el-col :span="8">
                        <el-form-item label="请领库房:">
                            <Stock4Slt v-model="filters.stockId" :hosId="this.user.corpId" :deptId='user.orgId' sSize='mini'></Stock4Slt>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                         <el-form-item label="采购方式:">
                            <el-select v-model="filters.buyKind" size="mini" placeholder="请选择采购方式" :disabled="purModes.length==1" @change="resetAndSearch">
                                <el-option v-for="item in purModes"
                                    :key="item.value"  :label="item.text" :value="item.value"
                                    :disabled="item.flag">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="请领日期:">
                            <el-date-picker v-model="filters.sDate" type="daterange" align="right" placeholder="选择日期范围" :picker-options="pickerOptions2" size="mini">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="请领科室:">
                            <el-select  v-model="filters.buyDeptId" :loading="deptLoading" placeholder="请选择" size='mini' @change="deptChange">
                                <el-option v-for="item in DeptList" :key="item.value" :label="item.ename"
                                :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="请购单号:">
                            <el-input v-model="filters.goodsName" size="mini" placeholder="物资名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8" :offset="2">
                            <el-button type="primary" icon="search" @click="resetAndSearch" size="mini">查询</el-button>
                    </el-col>
                </el-form>
            </el-col>
        </el-row>
        <el-row :gutter="5" style="height: 100%;">
            <el-col :span="5" style="height:calc(100% - 106px);" :loading="provLoading">
                <!-- <colAndProvs :hosId="user.corpId" @provChange="provChange" ></colAndProvs> -->
                <div class="prov_info">
                    <ul class="prov_list">
                        <li v-for="(item,index) in provList" :key="index" @click="provChange(item)"
                        :class="{ 'gyqx_active': filters.provId == item.provId }" :title="'集配商：'+item.colName">
                            <span>{{item.provName}}</span>
                            <span>({{item.goodsKinds}})</span>
                        </li>
                    </ul>
                </div>
            </el-col>
            <el-col :span="19" style="height:calc(100% - 36px);">
                <el-table ref='dt_orderSub' :data="applyDetailList" highlight-current-row border v-loading="sLoading" style="width: 100%;" fit  class="table_sub_content" tooltip-effect="light" @selection-change="sltRows" @current-change="curRChange" @select="subSelect" row-key="goodsId">
                    <el-table-column type="selection" width="30" :selectable="canselect">
                    </el-table-column>                  
                    <el-table-column label="采购数量" sortable width="100">
                      <template slot-scope="scope">
                            <span>{{getGoodsPurQty(scope.row)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsName" label="物资名称" sortable width="150" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="goodsGg" label="规格" width="100" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="unit" label="单位" sortable width="80">
                    </el-table-column>
                    <el-table-column prop="price" label="单价" sortable width="80">
                    </el-table-column>
                    <el-table-column prop="je" label="金额" width="80">
                    </el-table-column>
                    <el-table-column prop="packeage" label="包装规格" sortable width="100">
                    </el-table-column>
                    <el-table-column prop="mfrsName" label="生产厂家" show-overflow-tooltip>
                    </el-table-column>
                </el-table>
                <el-table ref='dt_orderMain' :data="applyMainList" highlight-current-row v-loading="mLoading" style="width: 100%;" fit  class="table_sub_content" tooltip-effect="light" row-key="subId">
                    <el-table-column label="操作" sortable>
                        <template slot-scope="scope">
                            <el-button  @click="rejectDetail(scope.row,30)" type="text">不采购</el-button>
                            <el-button  @click="rejectDetail(scope.row,20)" type="text">驳回</el-button>
                        </template>
                    </el-table-column>                   
                    <el-table-column prop="billId" label="请购单号" sortable>
                    </el-table-column>
                    <el-table-column prop="buyDeptName" label="科室名称" sortable>
                    </el-table-column>
                    <el-table-column prop="fillDate" label="请购时间" sortable>
                        <template slot-scope="scope">{{scope.row.fillDate | dateFormat}}</template>
                    </el-table-column>
                    <el-table-column label="请购数量">
                      <template slot-scope="scope">
                        <el-input-number placeholder="请输入数量" v-model="scope.row.purQty"  :min="0" :max="scope.row.qty" class="col-input-num">
                        </el-input-number>
                    </template>
                    </el-table-column>                   
                </el-table>
                <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;text-align: center;">
                    <el-button type="primary"  @click="buildPurOrder">生成采购单</el-button>
                </el-col>
            </el-col>
        </el-row>
    </el-row>
</template>

<script>
//请领采购
import Vue from "vue";
import colAndProvs from "@/components/colAndProvs";
import Stock4Slt from "@/components/Stock4Slt";
import moment from "moment";
import { BUY_KIND } from "./common/constance";
var Child = {
  template:
    '<div><p v-for="item in errMsg" v-html="item"></p><p v-if="vGoOn">是否忽略以上物资，继续生成！</p></div>',
  props: ["errMsg", "vGoOn"]
};
export default {
  data() {
    return {
      filters: {
        sDate: [],
        goodsName: "",
        colId: "",
        colName: "",
        provId: "",
        provIdArr: [],
        stockId: "",
        buyDeptId: "",
        buyKind: 0
      },
      provLoading:false,
      deptLoading:false,
      sLoading: false,
      mLoading: 0,
      ploading: false,
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
      sltGoods: [],
      applyDetailList: [],
      applyMainList: [],
      provList: [],
      DeptList: [],
      purModes: []
    };
  },
  components: { colAndProvs, Stock4Slt },
  computed: {
    buy_kind: {
      get: function() {
        return BUY_KIND;
      }
    }
  },
  methods: {
    canselect(){
      return this.mLoading == 0;
    },
    getGoodsPurQty(row){
      if(Array.isArray(row.lstMain) && row.lstMain.length>0){
        var qty = row.lstMain.map(o=>o.purQty).reduce((pre,cur)=>{
          return pre+cur;
        });
        row.qty = qty;
        return qty;
      }else{
        return row.qty;
      }
    },    
    deptChange(deptId) {
      this.getProvList();
    },
    provChange(provInfo) {
      //this.isActive = provInfo.provId;
      this.filters.colId = provInfo.colId;
      this.filters.colName = provInfo.colName;
      this.filters.provId = provInfo.provId;
      this.filters.provIdArr = provInfo.subProvId;
      this.search();
    },
    search() {
      var _this = this;
      this.applyMainList = [];
      this.applyDetailList = [];
      this.$refs.dt_orderSub.clearSelection();
      this.$refs.dt_orderMain.clearSelection();
      if (this.filters.provIdArr.length == 0) {
        return;
      }
      if (!this.filters.stockId) {
        return;
      }
      if (!this.filters.buyKind || this.filters.buyKind == 0) {
        return;
      }
      var param = {
        goodsName: this.filters.goodsName,
        stockId: this.filters.stockId,
        buyDeptId: this.filters.buyDeptId,
        provIdArr: this.filters.provIdArr,
        sDate: this.filters.sDate[0],
        eDate: this.filters.sDate[1],
        hosId: this.user.corpId,
        subState: 10,
        buyKind: this.filters.buyKind,
        sourceWarehouseId: this.user.orgId
      };
      this.sLoading = true;
      this.axios
        .post("/spdHERPService/applyMgr/deptBuySub/getDetailsVo4Pur", param)
        .then(
          res => {
            _this.sLoading = false;
            var applyDetailList = res.data.data;
            if (applyDetailList.length > 0) {
              applyDetailList.forEach(item => {
                item.whQty = Number(item.qty) - Number(item.storeQty);
              });
              _this.applyDetailList = applyDetailList;
              //_this.getMainList(_this.applyDetailList[0].goodsId);
              _this.getAllMainLst();
            }
          },
          err => {
            _this.sLoading = false;
          }
        );
    },
    subSelect(selection, row) {
      this.applyMainList = row.lstMain;      
    },    
    curRChange(row) {
      if(row){
          this.applyMainList = row.lstMain;
      }
    }, 
    sltRows(rows) {
      this.sltGoods = rows;
    },
    getAllMainLst() {
      var _this = this;
      this.mLoading = _this.applyDetailList.length;
      _this.applyDetailList.forEach(item => {
        _this.MainListApi(item.goodsId).then(
          res => {
            _this.mLoading--;
            if (res.data.code == 0) {
              item.lstMain = res.data.data;
            }
          },
          err => {_this.mLoading--;}
        );
      });
    },
    // getMainList(goodsId) {
    //   var _this = this;     
    //   this.MainListApi(goodsId).then(
    //     res => {
    //       _this.applyMainList = res.data.data;
    //     },
    //     err => {
    //     }
    //   );
    // },
    MainListApi(goodsId) {
      var param = {
        goodsId: goodsId,
        provIdArr: this.filters.provIdArr,
        hosId: this.user.corpId,
        sDate: this.filters.sDate[0],
        eDate: this.filters.sDate[1],
        subState: 10,
        buyKind: this.filters.buyKind,
        buyDeptId: this.filters.buyDeptId
      };
      return this.axios.post(
        "/spdHERPService/applyMgr/deptBuyMain/getMainBillVo4pur",
        param
      );
    },
    notPur(goodsId) {
      //不采购 批量操作
      var _this = this;
      this.MainListApi(goodsId).then(
        res => {
          var lst = res.data.data;
          if (lst) {
            var params = [];
            lst.foreach(item => {
              var param = {
                billId: item.billId,
                id: item.subId,
                subState: 30,
                warehouseDealMan: _this.user.userId
              };
              params.push(params);
            });
            _this.changeState(goodsId, params);
          }
        },
        err => {}
      );
    },
    rejectDetail(row, status) {
      var param = {
        billId: row.billId,
        rowNumber: row.rowNumber,
        subState: status || 30,
        warehouseDealMan: this.user.userId
      };
      this.changeState(row.goodsId, [param]);
    },
    changeState(goodsId, param) {
      var _this = this;
      _this.axios
        .post("/spdHERPService/applyMgr/deptBuySub/dealBill4Pur", param)
        .then(
          res => {
            if (res.data.code == 0) {
              _this.$message({ message: "操作成功！", type: "success" });
              _this.getProvList();
              _this.search();
            } else {
              _this.$message({ message: "操作失败！", type: "error" });
            }
          },
          err => {
            _this.$message({ message: "操作失败！", type: "error" });
          }
        );
    },
    buildPurOrder() {
      var _this = this;
      this.ploading = true;
      var errMsg = [];
      const h = _this.$createElement;
      var methods = [];
      if (this.sltGoods.length == 0) {
        this.$message.error("请选择要采购的物资");
        _this.ploading = false;
        return;
      }
      if (errMsg.length > 0) {
        _this.ploading = false;
        _this.$msgbox({
          title: "错误",
          type: "error",
          message: h(Child, { props: { errMsg: errMsg } })
        });
        return false;
      }
      _this.creatPurBill(this.sltGoods);
    },    
    creatPurBill(sltGoods) {
      var _this = this;
      var org = this.DeptList.find(item => {
        return (item.id == _this.filters.buyDeptId);
      });
      var purOrder = {
        provId: this.filters.colId,
        provName: this.filters.colName,
        subProvId: this.filters.provId,
        recOrgId: org.id,
        recOrgCode: org.code,
        recOrgName: org.ename,
        status: "0",
        purKind: 1,
        purchaseList: []
      };
      var purExt=[];
      for (var j = 0; j < sltGoods.length; j++) {
        var goodsInfo =sltGoods[j];
        if(goodsInfo.qty <= 0){
          continue;
        }
        var purDetail = {
          applyDeptId: org.id,
          applyDeptCode: org.code,
          applyDeptName: org.ename,
          applyBillId: goodsInfo.lstMain.length==1?goodsInfo.lstMain[0].billId:'merge',
          applyRowNum: goodsInfo.lstMain.length==1?goodsInfo.lstMain[0].rowNumber:0,
          hosGoodsId: goodsInfo.goodsId,
          hosGoodsCode: goodsInfo.code,
          hosGoodsName: goodsInfo.goodsName,
          goodsGg:goodsInfo.goodsGg,
          erpCode:goodsInfo.erpCode,
          hosPrice: goodsInfo.price,
          hosUnit: goodsInfo.unit,
          hosScale: goodsInfo.unitRate,
          hosApplyQty: goodsInfo.qty,
          purTax: goodsInfo.taxRate || 0.17, // 税率
          saleMan: goodsInfo.salemanId,
          status: 0,
          uniqueKind: goodsInfo.uniqueKind,
          provId: _this.filters.provId,
          lstApply:goodsInfo.lstMain.filter(o=>o.purQty>0).map(o=>{return {applyBillId:o.billId,applyBillRow:o.rowNumber,goodsId:o.goodsId,qty:o.purQty,unit:o.unit}})
        };
        purOrder.purchaseList.push(purDetail);
      }
        _this.ploading = false;
        _this.setPurBill(purOrder);
    },
    setPurBill(purOrder) {
      var _this = this;
      if (purOrder.purchaseList.length > 0) {
        var sessionId =
          _this.user.corpId +
          _this.user.orgId +
          moment(new Date()).format("YYYYMMDDHHmmss");
        LocalStorage.setItem(sessionId, JSON.stringify(purOrder));
        _this.$router.push({
          path: "addPurchase",
          query: {
            type: "purByapply",
            applyInfo: sessionId,
            purType: _this.filters.buyKind
          }
        });
      }
    },
    getProvList() {
      var _this = this;
      if (!this.filters.buyKind || this.filters.buyKind == 0) {
        return;
      }
      if (!this.filters.buyDeptId) {
        return;
      }
      this.provLoading = true;
      var param = {
        goodsName: this.filters.goodsName,
        sDate: this.filters.sDate[0],
        eDate: this.filters.sDate[1],
        hosId: this.user.corpId,
        buyDeptId: this.filters.buyDeptId,
        subState: 10,
        buyKind: this.filters.buyKind,
        sourceWarehouseId: this.user.orgId
      };
      this.axios
        .post("/spdHERPService/applyMgr/deptBuySub/getProvQGList", param)
        .then(
          res => {
            _this.provLoading = false;
            _this.provList = res.data.data;
            if (res.data.code == 0) {
              _this.filters.provId = _this.provList[0].provId;
              _this.provChange(_this.provList[0]);
            }
          },
          err => {_this.provLoading = false;}
        );
    },
    getBuyDeptList() {
      var _this = this;
      if (!this.filters.buyKind || this.filters.buyKind == 0) {
        return;
      }
      var param = {
        //goodsName: this.filters.goodsName,
        sDate: this.filters.sDate[0],
        eDate: this.filters.sDate[1],
        hosId: this.user.corpId,
        buyKind: this.filters.buyKind,
        sourceWarehouseId: this.user.orgId
      };
      this.deptLoading = true;
      this.axios
        .post("/spdHERPService/applyMgr/deptBuyMain/getBuyDeptList", param)
        .then(
          res => {
               _this.deptLoading = false;
            if (res.data.code == 0) {
              _this.DeptList = res.data.data;
              if (_this.DeptList.length > 0) {
                _this.filters.buyDeptId = _this.DeptList[0].id;
                _this.getProvList();
              }
            }
          },
          err => {_this.deptLoading = false}
        );
    },
    getUserPurMode() {
      var _this = this;
      var param = {
        userId: _this.user.userId
      };
      this.axios.post("/spdHERPService/deptMgr/applyAuditRel/list", param).then(
        res => {
          if (res.data.code == 0 && res.data.data.length > 0) {
            _this.setPurModes(res.data.data);
            _this.getBuyDeptList();
          } else {
            _this.setPurModes(0);
            _this.$message.error("当前用户未设置采购模式！");
          }
        },
        err => {}
      );
    },
    setPurModes(lstPModes) {
      var _this = this;
      var res = [];
      if (Array.isArray(lstPModes) && lstPModes.length > 0) {
        lstPModes.forEach(item => {
          var buyKind = _this.buy_kind.find(o => {
            return o.value == item.purMode;
          });
          if (buyKind) {
            res.push(buyKind);
          }
        });
      }

      if (res.length == 0) {
        res = [{ value: 0, text: "无", flag: false }];
      }
        _this.purModes = res.sort(function(a,b){return a.value - b.value});
        _this.filters.buyKind = _this.purModes[0].value;
    },
    resetAndSearch(){
        this.DeptList=[];
        this.provList=[];
        this.applyMainList = [];
        this.applyDetailList = [];
        this.$refs.dt_orderSub.clearSelection();
        this.$refs.dt_orderMain.clearSelection();
        this.filters.provId="";
        this.buyDeptId="-1";
        this.getBuyDeptList();
    }
  },
  mounted() {
    var end = new Date();
    var start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
    this.filters.sDate = [start, end];
    //this.filters.buyKind =Number(this.$route.query.buyKind);
    this.filters.buyKind=10;
    this.getUserPurMode();
  }
};
</script>

<style>
.send-type {
  float: right;
}
.table_sub_content {
  font-size: 12px;
  height: calc(48% - 35px);
  overflow-y: auto;
}
.prov_list {
  list-style-type: none;
  padding: 0px;
  margin: 0px;
}
.prov_list li {
  height: 25px;
  line-height: 25px;
  padding: 0px 0px 0px 10px;
  cursor: pointer;
  border-bottom: 1px solid #ccc;
}
.prov_info {
  height: 100%;
  width: 100%;
  border: 1px solid #ccc;
  overflow: auto;
}
.prov_list li:hover {
  background-color: #ccc;
}
</style>
