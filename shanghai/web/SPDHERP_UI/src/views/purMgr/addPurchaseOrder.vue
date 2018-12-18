<template>
    <el-row id="add-purchase-order" :gutter="10" v-loading="pageLoading" style="height:100%">
            <!--工具条-->
        <el-col :span="24" style="height:100%">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="120px" class="orderInput">
                    <el-col :span="8">
                        <el-form-item label="采购机构">
                            <el-input v-model="purOrder.purchaseComName" readonly placeholder="请购机构" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="采购科室">
                            <el-cascader :options="orgInfo" size="mini" v-model="purDept" :props="orgProps" :show-all-levels="false" :change-on-select="true">
                            </el-cascader>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="收货科室">
                            <el-cascader :options="orgInfo" size="mini" v-model="recDept" :props="orgProps" :show-all-levels="false" :change-on-select="true">
                            </el-cascader>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="集配商">
                            <el-select v-model="purOrder.provId" filterable placeholder="请选择" no-data-text="没有对应的集配商" size="mini" @change="jpChange" :disabled="purOrder.purchaseList.length > 0 || isPurByBuy">
                                <template v-if="ProvsInfo.length > 0">
                                    <el-option v-for="item in ProvsInfo" :key="item.provId" :label="item.cname" :value="item.provId">
                                    </el-option>
                                </template>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="供应商">
                            <el-select v-model="purOrder.subProvId" filterable placeholder="请选择" no-data-text="没有对应的供应商" size="mini" :disabled="purOrder.purchaseList.length > 0 || isPurByBuy">
                                <template v-if="subProvsInfo.length > 0">
                                    <el-option v-for="item in subProvsInfo" :key="item.provId" :label="item.cname" :value="item.provId">
                                    </el-option>
                                </template>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <!-- <el-col :span="8">
                        <el-form-item label="制单时间">
                            <el-date-picker v-model="purOrder.fillDate" readonly type="date" placeholder="选择日期" size="mini">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="制单人">
                            <el-input v-model="purOrder.fillterName" readonly placeholder="制单人" size="mini"></el-input>
                        </el-form-item>
                    </el-col> -->
                    <el-col :span="8">
                        <el-form-item label="采购方式" >
                          <el-select v-model="purOrder.purType" size="mini" placeholder="请选择采购方式" :disabled="pTypeDisable" @change="purKindChange">
                            <el-option v-for="item in purModes"
                              :key="item.value"  :label="item.text" :value="item.value">
                            </el-option>
                          </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="最晚到货日期">
                            <el-date-picker v-model="purOrder.lastArrivalDate" type="date" placeholder="选择日期" :picker-options="dpOption" size="mini">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                     <el-col :span="16">
                        <el-form-item label="备注">
                            <el-input v-model="purOrder.remark" size="mini"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="收货人">
                            {{purOrder.recLinkman}}
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="电话">
                            {{purOrder.recLinkmanPhone}}
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="收货地址">
                            {{purOrder.recAddress}}
                        </el-form-item>
                    </el-col>
                    <el-col :span="2">
                        <el-form-item label-width="0px">
                            <deliveryAddress btn_text="修改" v-on:addressChange="setAddress" :hosId="user.corpId" :deptId="user.orgId"></deliveryAddress>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-col>
            <!--列表-->
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-button-group>
                    <el-button type="primary" class="el-icon-plus" size="mini" @click.native="addManyRow">添加行</el-button>
                </el-button-group>
            </el-col>
            <el-table :data="purOrder.purchaseList" ref="t_orderLst" style="width: 100%;" fit class="t_pur" border>
                <el-table-column label="操作" width="80">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="delRow(scope.row)" type="text">删除</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="rowNumber" label="序号" width="60">
                </el-table-column>
                <el-table-column prop="applyDeptName" label="请购科室" width="150">
                </el-table-column>
                <el-table-column prop="hosGoodsName" label="产品名称" width="250" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="goodsGg" label="产品规格" show-overflow-tooltip>
                </el-table-column>
                 <el-table-column prop="erpCode" label="JDE编码" width="100" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="hosPrice" label="单价" width="80">
                </el-table-column>
                <el-table-column v-if="!isPurByBuy" label="请购数量" width="90">
                    <template slot-scope="scope">
                        <el-input-number placeholder="请输入数量" v-model="scope.row.hosApplyQty" size="mini" :min="0" class="col-input-num" @change="qtyChange($event,scope.row,true)">
                        </el-input-number>
                    </template>
                </el-table-column>
                <el-table-column label="采购数量" width="90">
                    <template slot-scope="scope">
                        <el-input-number placeholder="请输入数量" :disabled="isPurByBuy" v-model="scope.row.hosPurQty" size="mini" :min="0" class="col-input-num" @change="qtyChange($event,scope.row)">
                        </el-input-number>
                    </template>
                </el-table-column>
                <el-table-column prop="hosUnit" label="单位" width="80">
                </el-table-column>
                <el-table-column prop="purAmount" label="含税金额(元)" width="120">
                </el-table-column>
                <el-table-column prop="purTaxAmount" label="不含税金额(元)" width="120">
                </el-table-column>
            </el-table>
            <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                <div style="margin-left:100px;text-align: center;">
                    <el-button :plain="true" v-if="btnV" type="success"  @click="submitPurchase('20')">提交</el-button>
                    <!--<el-button :plain="true" v-if="btnV" type="warning"  @click="submitPurchase('10')">保存</el-button>-->
                    <el-button :plain="true"  type="danger"  @click="cancel">取消</el-button>
                </div>
            </el-col>

            <el-dialog title="请选择商品" @close="onClose" custom-class="d_sltGoods" :visible.sync="goodsListVisible" :close-on-click-modal="false" >
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form>
                        <el-col :span="12">
                            <el-form-item label="请购科室">
                                <el-cascader :options="orgInfo" size="mini" v-model="deptIdTmp" :props="orgProps" :show-all-levels="false"
                                :change-on-select="true" :disabled="purOrder.purchaseList.length > 0" @change="getGoodsInfo(1)">
                                </el-cascader>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="">
                                <el-input placeholder="商品名称、拼音缩写" v-model="filters.goodsName" size="mini" class="input-search">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="4">
                          <el-form-item label="">
                          <el-button type="primary" icon="el-icon-search" v-on:click="getGoodsInfo(1)">查询</el-button>
                          </el-form-item>
                        </el-col>
                    </el-form>
                </el-col>
                <el-table :data="goodsList" ref="sltGoodsT" highlight-current-row v-loading="loading"  fit  border row-key="id" class="t_pur_sltGoods" @selection-change="goodsSltChange">
                    <el-table-column type="selection" :reserve-selection="true" width="30" :selectable="isGoodsCanSlt">
                    </el-table-column>
                    <el-table-column prop="hisCode" label="编码" show-overflow-tooltip width="100">
                    </el-table-column>
                    <el-table-column prop="goodsName" label="名称" width="150" sortable show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="purPrice" label="单价" width="60">
                    </el-table-column>
                    <el-table-column prop="packeage" label="包装" width="60">
                    </el-table-column>
                    <el-table-column prop="goodsGg" label="规格" width="100" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="hisUnit" label="单位" width="100" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="mfrsName" label="生产厂家" show-overflow-tooltip>
                    </el-table-column>
                </el-table>
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="SizeChange" @current-change="CurrentChange" :current-page="goodsPage.page" :page-sizes="[15,30, 50]" :page-size="goodsPage.pageSize" layout="total, sizes, prev, pager, next" :total="goodsPage.total" style="float:right;" mini>
                    </el-pagination>
                </el-col>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="goodsListVisible = false" size="mini">取消</el-button>
                    <el-button type="primary" @click.native="getSltGoods" size="mini">确定</el-button>
                </div>
            </el-dialog>
        </el-col>
    </el-row>
</template>
<script>
import util from "@/common/js/util.js";
import deliveryAddress from "@/components/deliveryAddress.vue";
import { mapGetters } from "vuex";
import moment from "moment";
import { BUY_KIND } from "./common/constance";
var Child = {
  template: '<div><p v-for="item in errMsg" v-html="item"></p></div>',
  props: ["errMsg"]
};
export default {
  data() {
    return {
      pageLoading: false,
      opModule: "", //add or EDIT
      isPurByBuy: false, //手工采购 or 请购采购
      purOrder: {
        id: "",
        billId: "",
        purchaseComId: "",
        purchaseComName: "",
        purDeptId: "",
        recOrgId: "",
        recOrgCode: "",
        recOrgName: "",
        purKind: 0,
        purType: 0,
        provId: "",
        subProvId: "",
        provName: "",
        distrRelation: "",
        fillter: "",
        fillterName: "",
        remark: "",
        status: "",
        autitorId: "",
        autitorName: "",
        autitorSumRow: "",
        sumRow: "",
        recAddressId: "",
        recLinkman: "",
        recLinkmanPhone: "",
        recAddress: "",
        fillDate: "",
        lastUpdateDt: "",
        version: "",
        purComCode: "",
        purComErpCode: "",
        purOrgCode: "",
        purOrgCname: "",
        purOrgErpCode: "",
        purOrgScmCode: "",
        recOrgErpCode: "",
        recOrgScmCode: "",
        provCode: "",
        provErpCode: "",
        lastArrivalDate: "",
        purchaseList: []
      },
      purDetail: {
        id: "",
        pid: "",
        billId: "",
        rowNumber: 0,
        applyDeptId: "",
        applyDeptCode: "",
        applyDeptName: "",
        applyBillId: "",
        applyRowNum: 0,
        hosGoodsId: "",
        hosGoodsCode: "",
        hosGoodsName: "",
        hosPrice: 0,
        hosUnit: "",
        hosScale: 1,
        packetCode: 0,
        uniqueKind: 3,
        hosApplyQty: 0,
        applyQty: 0,
        hosPurQty: 0,
        purQty: 0,
        unitQty: 0,
        qty: 0,
        purTax: 0.17, // 税率
        purTaxPrice: 0, // 采购不含税单价
        purTaxAmount: 0, //采购不含税金额
        purAmount: 0, //采购含税金额
        purTaxMoney: 0, //税额
        status: 0,
        provId: "",
        applyDate: "",
        receiveDate: "",
        saleMan: "",
        version: ""
      },
      filters: {
        goodsName: "",
        code: "",
        provId: "",
        prov: {
          name: ""
        }
      },
      goodsPage: {
        total: 0,
        page: 1,
        pageSize: 15
      },
      orgProps: {
        value: "code",
        label: "label"
      },
      applySessionId: "",
      orgInfo: [],
      loading: false,
      goodsList: [], //商品列表
      goodsListVisible: false,
      sltGoodsList: [], //已选中商品
      ProvsInfo: [], //集配商列表
      subProvsInfo: [], //供应商列表
      //filterProvs: []               //过滤后的供应商列表
      purModes: [], //当前用户采购模式,
      pTypeDisable:false,
      btnV: true,
      dpOption: {
        disabledDate(time) {
          return time.getTime() < Date.now();
        }
      }
    };
  },
  components: { deliveryAddress },
  computed: {
    ...mapGetters(["getLoginUser"]),
    purDept: {
      get: function() {
        var res = [];
        if (this.purOrder.purOrgCode) {
          let path = this.purOrder.purOrgCode.split(".");
          if (path.length > 1) {
            var i = 0;
            for (i; i < path.length; i++) {
              let tempArr = path.slice(0, i + 1);
              res.push(tempArr.join("."));
            }
          } else {
            res = path;
          }
        }
        return res;
      },
      set: function(pathArr) {
        let res = { purDeptId: "", purDeptCode: "", purDeptName: "" };
        if (pathArr.length > 0) {
          //res = pathArr[pathArr.length - 1];
          var tmpArr = this.orgInfo.concat();
          for (var i = 0; i < pathArr.length; i++) {
            if (tmpArr) {
              var node = tmpArr.find(item => item.code == pathArr[i]);
              if (node) {
                tmpArr = node.children;
                res.purDeptCode = node.code;
                res.purDeptId = node.id;
                res.purDeptName = node.label;
              }
            }
          }
        }
        this.purOrder.purDeptId = res.purDeptId;
        this.purOrder.purOrgCode = res.purDeptCode;
        this.purOrder.purOrgCname = res.purDeptName;
      }
    },
    recDept: {
      get: function() {
        var res = [];
        if (this.purOrder.recOrgCode) {
          let path = this.purOrder.recOrgCode.split(".");
          if (path.length > 1) {
            var i = 0;
            for (i; i < path.length; i++) {
              let tempArr = path.slice(0, i + 1);
              res.push(tempArr.join("."));
            }
          } else {
            res = path;
          }
        }
        return res;
      },
      set: function(pathArr) {
        let res = { applyDeptId: "", applyDeptCode: "", applyDeptName: "" };
        if (pathArr.length > 0) {
          //res = pathArr[pathArr.length - 1];
          var tmpArr = this.orgInfo.concat();
          for (var i = 0; i < pathArr.length; i++) {
            if (tmpArr) {
              var node = tmpArr.find(item => item.code == pathArr[i]);
              if (node) {
                tmpArr = node.children;
                res.applyDeptCode = node.code;
                res.applyDeptId = node.id;
                res.applyDeptName = node.label;
              }
            }
          }
        }
        this.purOrder.recOrgId = res.applyDeptId;
        this.purOrder.recOrgCode = res.applyDeptCode;
        this.purOrder.recOrgName = res.applyDeptName;
      }
    },
    deptIdTmp: {
      get: function() {
        let res = [];
        if (this.purDetail.applyDeptCode) {
          let path = this.purDetail.applyDeptCode.split(".");
          if (path.length > 1) {
            var i = 0;
            for (i; i < path.length; i++) {
              let tempArr = path.slice(0, i + 1);
              res.push(tempArr.join("."));
            }
          } else {
            res = path;
          }
        }
        return res;
      },
      set: function(pathArr) {
        let res = { applyDeptId: "", applyDeptCode: "", applyDeptName: "" };
        if (pathArr.length > 0) {
          //res = pathArr[pathArr.length - 1];
          var tmpArr = this.orgInfo.concat();
          for (var i = 0; i < pathArr.length; i++) {
            if (tmpArr) {
              var node = tmpArr.find(item => item.code == pathArr[i]);
              if (node) {
                tmpArr = node.children;
                res.applyDeptCode = node.code;
                res.applyDeptId = node.id;
                res.applyDeptName = node.label;
              }
            }
          }
        }
        this.purDetail.applyDeptId = res.applyDeptId;
        this.purDetail.applyDeptCode = res.applyDeptCode;
        this.purDetail.applyDeptName = res.applyDeptName;
        this.filters.applyDeptId = res.applyDeptId;
      }
    },
    buy_kind: {
      get: function() {
        return BUY_KIND;
      }
    }
  },
  methods: {
    getUserPurMode() {
      var _this = this;
      var param = {
        userId: _this.user.userId
      };
      return this.axios.post(
        "/spdHERPService/deptMgr/applyAuditRel/list",
        param
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
      } else {
        _this.$message.error("当前用户未设置采购模式！");
        _this.btnV = false;
      }
      if (_this.opModule == "EDIT" || _this.isPurByBuy) {
          _this.pTypeDisable = true;
          var orderPurType = lstPModes.find(o => o.purMode == _this.purOrder.purType);
          if (!orderPurType) {
            _this.$message.error("当前用户无权限处理此请购类型的单据！");
            _this.btnV = false;
          }
        } else {
          _this.purOrder.purType = lstPModes[0].purMode;
        }
      if (res.length == 0) {
        res = [{ value: 0, text: "无", flag: false }];
      }
      _this.purModes = res;
    },
    buyKindChange() {},
    purKindChange(){
      if(this.pTypeDisable){
        this.purOrder.purchaseList=[];
        this.sltGoodsList=[];
        this.goodsList=[];
        this.$refs.t_orderLst.clearSelection();
      }
    },
    jpChange(jpProvId) {
      if (!this.isPurByBuy && this.opModule != "EDIT") {
        this.purOrder.subProvId = "";
      }
      var provINfo = this.ProvsInfo.find(item => item.provId == jpProvId);
      if (provINfo) {
        this.purOrder.provName = provINfo.cname;
        this.getSubProv(jpProvId);
      }
    },
    setAddress(d) {
      if (d) {
        this.purOrder.recAddressId = d.id;
        this.purOrder.recLinkman = d.lxr;
        this.purOrder.recLinkmanPhone = d.lxrPhone;
        this.purOrder.recAddress = (
          "" +
          d.province +
          d.city +
          d.area +
          d.address
        ).replace(/null/g, "");
      }
    },
    addManyRow() {
      if (!this.purOrder.provId) {
        this.$message.error("请选择集配商!");
        return;
      }
      this.goodsListVisible = true;
      this.getGoodsInfo(1);
    },
    isGoodsCanSlt(goodsInfo) {
      var index = this.purOrder.purchaseList.findIndex(item => {
        return item.hosGoodsId == goodsInfo.id;
      });
      return index < 0;
    },
    getGoodsInfo(pIndex) {
      this.goodsListVisible = true;
      this.goodsPage.page = pIndex;
      this.goodsList = [];
      var param = {
        orderBy: "",
        page: this.goodsPage.page,
        rows: this.goodsPage.pageSize,
        queryObject: {
          goodsName: this.filters.goodsName,
          shortPinyin: this.filters.goodsName,
          code: this.filters.code,
          provId: this.purOrder.subProvId,
          isHisCodeNotNull: "1",
          hosId: this.user.corpId,
          deptId: this.purDetail.applyDeptId,
          purMode: this.purOrder.purType
        }
      };
      this.loading = true;
      this.axios
        .post("/spdHERPService/myGoods/provGoodsHos/getDeptGoodsList", param)
        .then(
          res => {
            this.loading = false;
            if (res.data.code == "0") {
              this.loading = false;
              this.goodsList = res.data.data.data;
              this.goodsPage.total = res.data.data.total;
            }
          },
          err => {
            this.loading = false;
          }
        );
    },
    delRow(pd) {
      var index = this.purOrder.purchaseList.findIndex(item => {
        return item.rowNumber == pd.rowNumber;
      });
      this.purOrder.purchaseList.splice(index, 1);
      this.purOrder.purchaseList.forEach((item, i) => (item.rowNumber = i + 1));
    },
    onClose() {
      this.$refs.sltGoodsT.clearSelection(this.sltGoodsList);
      this.sltGoodsList = [];
      // this.purDetail.applyDeptId = "";
      // this.purDetail.applyDeptCode = "";
      // this.purDetail.applyDeptName = "";
    },
    CurrentChange(val) {
      this.goodsPage.page = val;
      this.getGoodsInfo(this.goodsPage.page);
    },
    SizeChange(val) {
      this.goodsPage.pageSize = val;
      this.getGoodsInfo(this.goodsPage.page);
    },
    goodsSltChange(sltedRows) {
      this.sltGoodsList = sltedRows;
    },
    getSltGoods() {
      if (this.purDetail.applyDeptId == "") {
        this.$message.error("请选择科室!");
        return;
      }
      if (this.sltGoodsList.length > 0) {
        var date = new Date();
        var applyDate = util.formatDate.format(date, "yyyy-MM-dd");
        date.setDate(date.getDate() + 3);
        var receiveDate = util.formatDate.format(date, "yyyy-MM-dd");
        this.sltGoodsList.forEach(item => {
          var index = this.purOrder.purchaseList.findIndex(rowItem => {
            return rowItem.hosGoodsId == item.id;
          });
          if (index < 0) {
            let row = Object.assign({}, this.purDetail);
            row.hosGoodsCode = item.hisCode;
            row.hosGoodsId = item.id;
            row.hosGoodsName = item.hisName;
            row.hosPrice = item.purPrice || 0;
            row.purTax = item.taxRate || 1;
            row.hosScale = item.unitRate || 1;
            row.hosUnit = item.hisUnit;
            row.provId = item.provId; //this.purOrder.subProvId;
            row.applyDate = applyDate;
            row.receiveDate = receiveDate;
            row.packetCode = item.packeage || 0;
            row.uniqueKind = item.uniqueCodeStrategy;
            row.saleMan = item.saleManId;
            row.purTaxPrice = (row.hosPrice / (1 + row.purTax)).toFixed(6);
            this.purOrder.purchaseList.push(row);
            row.rowNumber = this.purOrder.purchaseList.length;
          }
        });
        this.goodsListVisible = false;
      } else {
        this.$message.error("请选择商品!");
      }
    },
    dataChange(val, row, att) {
      if (att == "applyDate") {
        var date = new Date(val);
        date.setDate(date.getDate() + 3);
        row.receiveDate = date.toDateString();
      }
      row[att] = val;
    },
    qtyChange(val, row, flag) {
      if (flag === true) {
        row.hosApplyQty = val;
      }
      row.hosPurQty = val;
      row.purQty = row.hosPurQty * row.hosScale;
      row.applyQty = row.hosApplyQty * row.hosScale;
      row.purAmount = (row.hosPrice * row.hosPurQty).toFixed(2);
      row.purTaxAmount = (row.purTaxPrice * row.hosPurQty).toFixed(2);
      this.setAmount();
    },
    setAmount() {
      var sumAmount = 0,
        sumTaxAmount = 0;
      this.purOrder.purchaseList.forEach((item, index) => {
        sumTaxAmount += Number(item.purTaxAmount);
        sumAmount += Number(item.purAmount);
      });
      this.purOrder.sumAmount = sumAmount.toFixed(2);
      this.purOrder.sumTaxAmount = sumTaxAmount.toFixed(2);
    },
    getOrg() {
      var params = {};
      return this.axios.post("/platformService/sys/org/getOrgsByUnit", params);
    },
    getProv() {
      //var params = { kind: "2" };
      //return this.axios.post('/platformService/bas/company/companysList', params);
      var param = {
        orderBy: "",
        queryObject: { hosId: this.user.corpId },
        page: 0,
        rows: 0
      };
      //return this.axios.post('spdHERP/provManager/hosProvInfo/hosProvInfoVoList', param);
      return this.axios.post(
        "/spdHERPService/provMgr/distriRelation/queryUserRelationCollectorList",
        param
      );
    },
    getSubProv(pId) {
      var _this = this;
      _this.subProvsInfo = [];
      var param = {
        orderBy: "",
        queryObject: {
          hosId: this.user.corpId,
          collectorId: pId,
          isUseSubprov: 1
        },
        page: 0,
        rows: 0
      };
      this.axios
        .post(
          "/spdHERPService/provMgr/distriRelation/queryUserRelationPartnerList",
          param
        )
        .then(
          res => {
            if (res.data.code == 0) {
              _this.subProvsInfo = res.data.data.data;
              _this.subProvsInfo.push({
                provId: _this.purOrder.provId,
                cname: _this.purOrder.provName
              });
              //_this.filters.provIds = _this.subProvsInfo.map(item=>{return item.provId;})
            }
          },
          err => {
            _this.$message.error("获取合作商信息失败！");
          }
        );
    },
    cancel() {
      this.$router.go(-1);
    },
    submitPurchase(status) {
      var _this = this;
      const h = _this.$createElement;
      if(this.purOrder.status == 4){//审核驳回
        this.purOrder.status = 5;//提交审核
      }else{
        this.purOrder.status = status;
      }
      this.setAmount();
      var params = this.purOrder;
      var validInfo = this.checkPurOrder(params);
      if (validInfo.isValidate === false) {
        _this.$msgbox({
          title: "错误",
          type: "error",
          message: h(Child, { props: { errMsg: validInfo.errMsg } })
        });
        return;
      }
      var serviceUrl =
        this.opModule == "ADD"
          ? "/spdHERPService/order/purchase/insertPurchase"
          : "/spdHERPService/order/purchase/updatePurchase";
      this.pageLoading = true;
      this.axios.post(serviceUrl, params).then(
        res => {
          this.pageLoading = false;
          if (res.data.code == "0") {
            if (_this.applySessionId) {
              localStorage.removeItem(_this.applySessionId);
            }
            var billId = res.data.data.billId;
            _this.$msgbox({
              title: "提示",
              message: "操作成功",
              type: "success",
              callback: action => {
                _this.$router.push({
                  path: "basePurchase",
                  query: { billId: billId }
                });
              }
            });
          } else if (res.data.code == "-122") {
            var msg = res.data.validateErrors.fieldErrors.map(x => x.message);
            _this.$msgbox({
              title: "错误",
              type: "error",
              message: h(Child, { props: { errMsg: msg } })
            });
          } else {
            _this.$msgbox({
              title: "提示",
              message: "操作失败",
              type: "error"
            });
          }
        },
        err => {
          this.pageLoading = false;
          this.$msgbox({
            title: "提示",
            message: "操作失败",
            type: "error"
          });
        }
      );
    },
    checkPurOrder(purOrder) {
      var res = { isValidate: true, errMsg: [] };
      if (!purOrder.recAddressId) {
        res.isValidate = false;
        res.errMsg.push("请选择收货地址");
      }
      if (!purOrder.purchaseList || purOrder.purchaseList.length == 0) {
        res.isValidate = false;
        res.errMsg.push("单据明细行不能为空");
      }
      purOrder.purchaseList.forEach((pDetail, index) => {
        if (pDetail.hosPurQty < 1) {
          res.isValidate = false;
          res.errMsg.push(
            `单据明细第${index}行：${pDetail.hosGoodsName}的采购数量不能为空`
          );
        }
      });
      return res;
    },
    getPurchaseInfo(billId) {
      return this.axios.get(
        "/spdHERPService/order/purchase/getPurchase/" + billId
      );
    }
  },
  mounted() {
    var _this = this;
    var billId = _this.$route.query.billId;
    this.purOrder.purType = Number(_this.$route.query.purType);
    var thisDate = new Date();
    thisDate.setDate(thisDate.getDate() + 1);
    _this.purOrder.lastArrivalDate = moment(thisDate).format("YYYY-MM-DD");
    var Methods = [_this.getOrg(), _this.getProv(), _this.getUserPurMode()];
    if (billId) {
      _this.opModule = "EDIT";
      Methods.push(_this.getPurchaseInfo(billId));
    } else {
      _this.opModule = "ADD";
      _this.purOrder.fillterName = _this.getLoginUser.ename;
      _this.purOrder.fillter = _this.getLoginUser.userId;
      _this.purOrder.purchaseComId = _this.getLoginUser.corpId;
      _this.purOrder.purchaseComName = _this.getLoginUser.corpName;
      _this.purOrder.purDeptId = _this.getLoginUser.orgId;
      _this.purOrder.purOrgCode = _this.getLoginUser.orgCode;
      _this.purOrder.purOrgCname = _this.getLoginUser.orgName;
      _this.purOrder.recOrgName = _this.getLoginUser.orgName;
      _this.purOrder.recOrgId = _this.getLoginUser.orgId;
      _this.purOrder.recOrgCode = _this.getLoginUser.orgCode;
      _this.purOrder.fillDate = util.formatDate.format(
        new Date(),
        "yyyy-MM-dd"
      );
      _this.purOrder.status = 0;
      _this.purDetail.applyDeptId = _this.getLoginUser.orgId;
      _this.purDetail.applyDeptCode = _this.getLoginUser.orgCode;
      _this.purDetail.applyDeptName = _this.getLoginUser.orgName;
      var myType = this.$route.query.type;
      _this.isPurByBuy = myType == "purByapply";
      if (_this.isPurByBuy) {
        var sessionId = (_this.applySessionId = this.$route.query.applyInfo);
        var purchase = JSON.parse(LocalStorage.getItem(_this.applySessionId));
        if (!purchase) {
          return;
        }
        _this.purOrder.purKind = purchase.purKind;
        _this.purOrder.provId = purchase.provId;
        _this.purOrder.provName = purchase.provName;
        _this.purOrder.subProvId = purchase.subProvId;
        //_this.purOrder.purType = purchase.purType || 10;
        _this.purOrder.sumRow = purchase.purchaseList.length;
        //_this.deptIdTmp = purchase.purchaseList[0].applyDeptId;
        _this.purDetail.applyDeptId = purchase.recOrgId;
        _this.purDetail.applyDeptCode = purchase.recOrgCode;
        _this.purDetail.applyDeptName = purchase.recOrgName;

        // _this.purOrder.recOrgId = purchase.recOrgId;
        // _this.purOrder.recOrgCode =purchase.recOrgCode;
        // _this.purOrder.recOrgName = purchase.recOrgName;
        var sumAmount = 0,
          sumTaxAmount = 0,
          sumAmount = 0;
        purchase.purchaseList.forEach((item, index) => {
          var purDeatil = {};
          purDeatil.rowNumber = index + 1;
          purDeatil.applyDeptId = item.applyDeptId;
          purDeatil.applyDeptName = item.applyDeptName;
          purDeatil.applyBillId = item.applyBillId;
          purDeatil.applyRowNum = item.applyRowNum;
          purDeatil.hosGoodsId = item.hosGoodsId;
          purDeatil.saleMan = item.saleMan;
          purDeatil.hosGoodsCode = item.hosGoodsCode;
          purDeatil.hosGoodsName = item.hosGoodsName;
          purDeatil.goodsGg = item.goodsGg;
          purDeatil.erpCode = item.erpCode;
          purDeatil.hosPrice = item.hosPrice;
          purDeatil.hosUnit = item.hosUnit;
          purDeatil.hosScale = item.hosScale;
          purDeatil.uniqueKind = item.uniqueKind;
          purDeatil.hosApplyQty = item.hosApplyQty;
          purDeatil.applyQty = purDeatil.hosApplyQty * purDeatil.hosScale;
          purDeatil.hosPurQty = item.hosApplyQty;
          purDeatil.purQty = purDeatil.applyQty;
          purDeatil.purTax = item.purTax || 1;
          purDeatil.lstApply=item.lstApply;
          purDeatil.purTaxPrice = (
            purDeatil.hosPrice /
            (1 + purDeatil.purTax)
          ).toFixed(6);
          purDeatil.purTaxAmount = (
            purDeatil.purTaxPrice * purDeatil.hosPurQty
          ).toFixed(2);
          purDeatil.purAmount = (
            purDeatil.hosPrice * purDeatil.hosPurQty
          ).toFixed(2);
          purDeatil.purTaxMoney = purDeatil.purAmount - purDeatil.purTaxAmount;
          purDeatil.provId = item.provId;
          purDeatil.applyDate = item.applyDate;
          purDeatil.status = 0;
          purDeatil.qty = 0;
          purDeatil.unitQty = 0;
          _this.purOrder.purchaseList.push(purDeatil);
          sumTaxAmount += Number(purDeatil.purTaxAmount);
          sumAmount += Number(purDeatil.purAmount);
        });
        _this.purOrder.sumAmount = sumAmount.toFixed(2);
        _this.purOrder.sumTaxAmount = sumTaxAmount.toFixed(2);
        if (_this.purOrder.provId) {
          _this.getSubProv(_this.purOrder.provId);
        }
      }
    }
    this.pageLoading = true;
    _this.axios.all(Methods).then(
      _this.axios.spread(function(orgInfo, provInfo, purModes, purchase) {
        _this.pageLoading = false;
        if (orgInfo.data.code == "0") {
          _this.orgInfo = orgInfo.data.data.children[0].children;
        }
        if (provInfo.data.code == "0") {
          _this.ProvsInfo = provInfo.data.data.data;
          //_this.filterProvs = _this.ProvsInfo;
        }
        if (purModes.data.code == 0) {
          _this.setPurModes(purModes.data.data);
        }
        if (purchase && purchase.data.code == "0") {
          _this.purOrder = purchase.data.data;
          _this.purDetail.applyDeptId = _this.purOrder.purchaseList[0].applyDeptId;
          _this.purDetail.applyDeptCode = _this.purOrder.purchaseList[0].applyDeptCode;
          _this.purDetail.applyDeptName = _this.purOrder.purchaseList[0].applyDeptName;
          _this.getSubProv(_this.purOrder.provId);
        }
      }),
      err => {
        _this.pageLoading = false;
        _this.$message.error("页面初始化失败,请刷新重试或联系管理员");
      }
    );
  }
};
</script>

<style lang="scss">

#add-purchase-order .d_sltGoods{
  .el-dialog__body{
    height: 300px;
    .t_pur_sltGoods{
      font-size: 12px;
      height: calc(100% - 50px);
    }
  }
}
#add-purchase-order .t_pur {
  font-size: 12px;
  height: calc(100% - 210px);
  overflow-y: auto;
}

#add-purchase-order .el-dialog--small {
  width: 60%;
}

#add-purchase-order .orderInput .el-form-item input {
  /*BORDER-TOP-STYLE: none;
    BORDER-RIGHT-STYLE: none;
    BORDER-LEFT-STYLE: none;
    BORDER-BOTTOM-STYLE: solid;*/
  width: 200px;
}

#add-purchase-order .provListInfo {
  width: 97%;
  height: 300px;
  max-height: 400px;
  padding: 3px;
  background-color: #eef1f6;
  border: 1px solid #bfcbd9;
}

#add-purchase-order .provListCls {
  margin-top: 8px;
  padding: 3px;
  height: 86%;
  overflow: auto;
  background-color: #fff;
}

#add-purchase-order .provListCls ul {
  list-style: none;
  margin: 0px;
  padding: 0;
}

#add-purchase-order .provListCls ul li {
  cursor: pointer;
}
</style>
