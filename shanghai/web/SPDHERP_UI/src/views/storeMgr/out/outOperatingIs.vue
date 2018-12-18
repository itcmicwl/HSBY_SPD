<template>
  <!--<el-row  id="operatingIS" style="  height:100%">
    <el-row :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px;">
        <el-form label-width="120px">
          <el-col :span="6">
            <el-form-item label="出库科室:">
              <span>{{currentDeptName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库库房:">
              <span>{{currentStockName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="登记人:">
              <span>{{fillerName}}</span>
            </el-form-item>
          </el-col>
        </el-form>
    </el-row>
    <el-row :span="24" class="toolbar">
      <el-col :span="3" class="toolbar" style="padding-bottom: 0px; line-height: 25px;">
        <el-switch v-model="barcodeType" active-text="仅主码" inactive-text="主副码" @change="barcodeTypeChange">
        </el-switch>
      </el-col>
      <el-col :span="18" class="toolbar" v-show="barcodeType" style="padding-bottom: 0px;">
        <el-input type="text" class="input-barcode" ref="mbarcode" v-model="barcode"
                  @keyup.native.enter="barcodeResolve" autosize placeholder="请录入条形码">
        </el-input>
      </el-col>
      <el-col :span="18" class="toolbar" v-show="!barcodeType" style="padding-bottom: 0px;">
        <el-col :span="11">
          <el-input type="text" class="input-barcode" ref="mainBarcode" v-model="mainBarcode"
                    @keyup.native.enter="barcodeNext" autosize placeholder="请录入条形码主码">
          </el-input>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-input type="text" class="input-barcode" ref="fbarcode" v-model="fBarcode" @keyup.native.enter="barcodePre"
                    autosize placeholder="请录入条形码副码">
          </el-input>
        </el-col>
      </el-col>
      <el-col :span="2" class="toolbar" align="right" style="padding-top:2px">
        <el-button :disabled="operatingInfo.length===0" @click="handlePreviewBtnClick" type="info">
          预览出库单
        </el-button>
      </el-col>
    </el-row>
    <el-row style="height:calc(100% - 80px);" :gutter="10" v-loading='loading'>
      <el-col :span="7" style="height:100%">
        <el-table ref="operatingMain" :data="operatingInfo" style="width: 100%;" fit class="table_content"
                  row-key="id" border height="outer" @row-click="rowClick"  highlight-current-row>
          <el-table-column label="操作" width="40px">
            <template slot-scope="scope">
              <div class="icoBtn">
                <i class="fa fa-trash-o fa-lg" @click="delCacheRow(scope.row)" title="删除"
                   style="color:red;margin-left:5px;"></i>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="手术包号" prop="surCode" show-overflow-tooltip align="center" width="100px"></el-table-column>
          <el-table-column label="名称" prop="surName" show-overflow-tooltip align="center" width="90px"></el-table-column>
          <el-table-column label="患者姓名" prop="sickerName" show-overflow-tooltip align="center">
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="17" style="height:100%">
        <el-table ref="operatingSub" :data="operatingGoodsList" style="width: 100%;" fit border height="outer" class="table_content" row-key="index" highlight-current-row>
          <el-table-column type="index" label="序号" width="45"></el-table-column>
          <el-table-column label="名称" prop="goodsName" width="180"></el-table-column>
          <el-table-column label="唯一码" prop="uniqueCode" width="110"></el-table-column>
          <el-table-column label="规格型号" prop="goodsGg" width="160" show-overflow-tooltip></el-table-column>
          <el-table-column label="单位" width="35" prop="unit" align="center"></el-table-column>
          <el-table-column label="数量" width="50" prop="qty" align="center"></el-table-column>
          <el-table-column label="供应商" prop="provName" show-overflow-tooltip align="center"></el-table-column>
          <el-table-column label="生产厂家" prop="mfrsName" show-overflow-tooltip align="center"></el-table-column>
        </el-table>
      </el-col>
    </el-row>
    &lt;!&ndash;扫码输入病人姓名&ndash;&gt;
    <el-dialog title="患者信息" :visible.sync="dialogFormVisible" width="500px">
      <el-form :model="form">
        <el-form-item label="请输入患者姓名">
          <el-input v-model="operatingMain.sickerName"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
    &lt;!&ndash;预览出库单&ndash;&gt;
    <el-dialog title="预览出库单" :visible.sync="dlgVisible" width="70%" top="10vh">
      <el-form ref="editForm" :model="outStockBill" class="el-form-item-nomsg" label-width="70px">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="出库类型">
              <el-input v-model="OUTSTOCK_BILL_KIND.SURGERY.text" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
          <el-form-item label="出库人员">
            <el-input v-model="outStockBill.filler" disabled></el-input>
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="制单时间">
              <el-input v-model="outStockBill.fillDate" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="detailTable" :data="outStockBill.outStockLists" highlight-current-row border>
        <el-table-column type="index" label="序号" width="40" align="center"></el-table-column>
        <el-table-column label="名称" prop="goodsName" show-overflow-tooltip></el-table-column>
        <el-table-column label="手术包号" prop="sourceBillId" show-overflow-tooltip></el-table-column>
        <el-table-column label="规格型号" prop="goodsGg" show-overflow-tooltip></el-table-column>
        <el-table-column label="批号" prop="batchCode" width="100"></el-table-column>
        <el-table-column label="注册证" width="210" prop="certificateCode" align="center"></el-table-column>
        <el-table-column label="单位" width="35" prop="unit" align="center"></el-table-column>
        <el-table-column label="出库数量" prop="outQty" width="60" align="center"></el-table-column>
        <el-table-column label="金额" width="60" prop="sumPrice" align="center"></el-table-column>
        <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="closeDig">取消</el-button>
        <el-button :loading="confirmBtnLoading" @click="handleDlgConfirm()" type="primary">领用出库</el-button>
      </div>
    </el-dialog>
  </el-row>-->
  <el-tabs id="in-by-operator-out-list" type="border-card" v-model="showTab"  style="height:98%">
    <el-tab-pane label="领用登记" name="pkgRec" style="height:100%">
      <el-row  id="operatingIS" style="height:100%">
        <el-row :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px;">
          <el-form label-width="120px">
            <el-col :span="6">
              <el-form-item label="出库科室:">
                <span>{{currentDeptName}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="出库库房:">
                <span>{{currentStockName}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="登记人:">
                <span>{{fillerName}}</span>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
        <el-row :span="24" class="toolbar">
          <el-col :span="3" class="toolbar" style="padding-bottom: 0px; line-height: 25px;">
            <el-switch v-model="barcodeType" active-text="仅主码" inactive-text="主副码" @change="barcodeTypeChange">
            </el-switch>
          </el-col>
          <el-col :span="18" class="toolbar" v-show="barcodeType" style="padding-bottom: 0px;">
            <el-input type="text" class="input-barcode" ref="mbarcode" v-model="barcode"
                      @keyup.native.enter="barcodeResolve" autosize placeholder="请录入条形码">
            </el-input>
          </el-col>
          <el-col :span="18" class="toolbar" v-show="!barcodeType" style="padding-bottom: 0px;">
            <el-col :span="11">
              <el-input type="text" class="input-barcode" ref="mainBarcode" v-model="mainBarcode"
                        @keyup.native.enter="barcodeNext" autosize placeholder="请录入条形码主码">
              </el-input>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-input type="text" class="input-barcode" ref="fbarcode" v-model="fBarcode" @keyup.native.enter="barcodePre"
                        autosize placeholder="请录入条形码副码">
              </el-input>
            </el-col>
          </el-col>
          <el-col :span="2" class="toolbar" align="right" style="padding-top:2px">
            <el-button :disabled="operatingInfo.length===0" @click="handlePreviewBtnClick" type="info">
              预览出库单
            </el-button>
          </el-col>
        </el-row>
        <el-row style="height:calc(100% - 80px);" :gutter="10" v-loading='loading'>
          <el-col :span="7" style="height:100%">
            <el-table ref="operatingMain" :data="operatingInfo" style="width: 100%;" fit class="table_content"
                      row-key="id" border height="outer" @row-click="rowClick"  highlight-current-row>
              <el-table-column label="操作" width="40px">
                <template slot-scope="scope">
                  <div class="icoBtn">
                    <i class="fa fa-trash-o fa-lg" @click="delCacheRow(scope.row)" title="删除"
                       style="color:red;margin-left:5px;"></i>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="手术包号" prop="surCode" show-overflow-tooltip align="center" width="100px"></el-table-column>
              <el-table-column label="名称" prop="surName" show-overflow-tooltip align="center" width="90px"></el-table-column>
              <el-table-column label="患者姓名" prop="sickerName" show-overflow-tooltip align="center">
              </el-table-column>
            </el-table>
          </el-col>
          <el-col :span="17" style="height:100%">
            <el-table ref="operatingSub" :data="operatingGoodsList" style="width: 100%;" fit border height="outer" class="table_content" row-key="index" highlight-current-row>
              <el-table-column type="index" label="序号" width="45"></el-table-column>
              <el-table-column label="名称" prop="goodsName" width="180"></el-table-column>
              <el-table-column label="唯一码" prop="uniqueCode" width="110"></el-table-column>
              <el-table-column label="规格型号" prop="goodsGg" width="160" show-overflow-tooltip></el-table-column>
              <el-table-column label="单位" width="35" prop="unit" align="center"></el-table-column>
              <el-table-column label="数量" width="50" prop="qty" align="center"></el-table-column>
              <el-table-column label="供应商" prop="provName" show-overflow-tooltip align="center"></el-table-column>
              <el-table-column label="生产厂家" prop="mfrsName" show-overflow-tooltip align="center"></el-table-column>
            </el-table>
          </el-col>
        </el-row>
        <!--扫码输入病人姓名-->
        <el-dialog title="患者信息" :visible.sync="dialogFormVisible" width="500px">
          <el-form :model="form">
            <el-form-item label="请输入患者姓名">
              <el-input v-model="operatingMain.sickerName"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
          </div>
        </el-dialog>
        <!--预览出库单-->
        <el-dialog title="预览出库单" :visible.sync="dlgVisible" width="70%" top="10vh">
          <el-form ref="editForm" :model="outStockBill" class="el-form-item-nomsg" label-width="70px">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="出库类型">
                  <el-input v-model="OUTSTOCK_BILL_KIND.SURGERY.text" disabled></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="出库人员">
                  <el-input v-model="outStockBill.filler" disabled></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="制单时间">
                  <el-input v-model="outStockBill.fillDate" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-table ref="detailTable" :data="outStockBill.outStockLists" highlight-current-row border>
            <el-table-column type="index" label="序号" width="40" align="center"></el-table-column>
            <el-table-column label="名称" prop="goodsName" show-overflow-tooltip></el-table-column>
            <el-table-column label="手术包号" prop="sourceBillId" show-overflow-tooltip></el-table-column>
            <el-table-column label="规格型号" prop="goodsGg" show-overflow-tooltip></el-table-column>
            <el-table-column label="批号" prop="batchCode" width="100"></el-table-column>
            <el-table-column label="注册证" width="210" prop="certificateCode" align="center"></el-table-column>
            <el-table-column label="单位" width="35" prop="unit" align="center"></el-table-column>
            <el-table-column label="出库数量" prop="outQty" width="60" align="center"></el-table-column>
            <el-table-column label="金额" width="60" prop="sumPrice" align="center"></el-table-column>
            <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>
          </el-table>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="closeDig">取消</el-button>
            <el-button :loading="confirmBtnLoading" @click="handleDlgConfirm()" type="primary">领用出库</el-button>
          </div>
        </el-dialog>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="手术包打印" name="purconfirmLst" style="height: 100%">
      <el-row id="operatingPrint" style="height:100%" v-show="surMainPt">
        <el-row>
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
          <el-form :inline="true" label-width="90px" ref="searchForm" v-model="sFilters">
            <el-form-item label="手术排班号：">
              <el-input v-model="sFilters.surScheduleNo" placeholder="排班号"  @keyup.enter.native="getSurPkgInfo"></el-input>
            </el-form-item>
            <el-form-item label="患者姓名：">
              <el-input v-model="sFilters.sickerName" placeholder="姓名"></el-input>
            </el-form-item>
<!--              <el-form-item label="打包时间">
                <el-date-picker v-model="sFilters.startDate" class="data-picker" type="date" placeholder="开始日期" ></el-date-picker>
                <span>-</span>
                <el-date-picker v-model="sFilters.endDate" class="data-picker" type="date" placeholder="结束日期" ></el-date-picker>
              </el-form-item>-->
            <el-form-item>
              <el-button type="primary" v-on:click="getSurPkgInfo()">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
        <el-row style="height:calc(100% - 80px);" :gutter="10" v-loading='pLoading'>
          <el-col :span="24"style="height: 100%">
            <el-table ref="operatingPrint" :data="surInfoPrint" style="width: 100%;" fit class="table_content"
                      row-key="index" border height="outer"  highlight-current-row>
              <el-table-column type="index" align="center"  width="40" label="序号"></el-table-column>
              <el-table-column label="手术包号" prop="surCode" show-overflow-tooltip align="center" width="100px"></el-table-column>
              <el-table-column label="名称" prop="surName" show-overflow-tooltip align="center" width="90px"></el-table-column>
              <el-table-column label="患者姓名" prop="sickerName" show-overflow-tooltip align="center">
              </el-table-column>
              <el-table-column label="手术排班号" prop="surScheduleNo" show-overflow-tooltip align="center">
              </el-table-column>
              <el-table-column label="入院编号" prop="inHosCode" show-overflow-tooltip align="center">
              </el-table-column>
              <el-table-column label="状态" prop="status" :formatter="statusFil" show-overflow-tooltip align="center">
              </el-table-column>
              <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                  <el-button  @click="surgeryView(scope.row)" type="text">查看</el-button>
                 <!-- <el-button v-if="scope.row.status == 10"  @click="editPurchase(scope.row.billId)" type="text">编辑</el-button>
                  <el-button  v-if="scope.row.status == 10" @click="delPurchase(scope.row)" type="text">删除</el-button>-->
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-row>
      <el-row id="operatingPrintList" v-show="!surMainPt" style="height: 100%">
        <el-col :span="24" id="outStockPrintView"style="height: 100%">
          <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="120px">
              <el-col :span="6">
                <el-form-item label="请购单号:">
                  <span>{{ surInfoPrintView.applyBillId }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="手术包号:">
                  <span>{{ surInfoPrintView.surCode }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="患者姓名:">
                  <span>{{ surInfoPrintView.sickerName }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="入院编号:">
                  <span>{{ surInfoPrintView.inHosCode }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="手术排班号:">
                  <span>{{ surInfoPrintView.surScheduleNo }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="打包人:">
                  <span>{{ surInfoPrintView.packer }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="打包日期:">
                  <span>{{ surInfoPrintView.packageDate | dateFormat('YYYY-MM-DD')}}</span>
                </el-form-item>
              </el-col>
            </el-form>
          </el-col>
          <template>
            <el-table :data="surInfoPrintView.pkgListDis" style="width: 100%;" fit border  class ='outView-tableheight' stripe>
              <el-table-column type="index" label="序号" width="45"></el-table-column>
              <el-table-column label="名称" prop="goodsName" width="180"></el-table-column>
              <el-table-column label="唯一码" prop="uniqueCode" width="110"></el-table-column>
              <el-table-column label="规格型号" prop="goodsGg" width="160" show-overflow-tooltip></el-table-column>
              <el-table-column label="单位" width="35" prop="unit" align="center"></el-table-column>
              <el-table-column label="数量" width="50" prop="qty" align="center"></el-table-column>
              <el-table-column label="供应商" prop="provName" show-overflow-tooltip></el-table-column>
              <el-table-column label="产地" prop="made" show-overflow-tooltip width="100px"></el-table-column>
              <el-table-column label="生产厂家" prop="mfrsName" show-overflow-tooltip></el-table-column>
            </el-table>
          </template>
          <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;text-align: center;">
            <el-button :plain="true" type="danger"  @click="goBack">返回</el-button>
            <el-button type="info"  @click="printSur">打印</el-button>
          </el-col>
          <surPkgViewPrint ref="surPrintD" :pSurCode = "surInfoPrintView.surCode" ></surPkgViewPrint>
        </el-col>
      </el-row>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import deliveryAddress from "../../../components/deliveryAddress.vue";
import {
  OUTSTOCK_BILL_STATUS,
  OUTSTOCK_BILL_KIND,
  OUTSTOCK_TYPE, SURGERY_MAIN_STATUS
} from "../../../common/js/constance";
import barcodeUtil from "../../../common/js/barcodeUtil.js";
import cloneDeep from "lodash/cloneDeep";
import moment from "moment";
import JsBarcode from 'jsbarcode';
import $ from 'jquery';
import surPkgViewPrint from "./surPkgViewPrint";

export default {
  components: {
    surPkgViewPrint
  },
  data() {
    return {
      barcodeType: true,
      barcode: '',
      mainBarcode: '',
      fBarcode: '',

      baseURL:'/spdHERPService/surgeryPkg/out',
      dialogFormVisible:false,
      dlgVisible:false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      showTab:'pkgRec',
      loading:false,
      pLoading:false,
      confirmBtnLoading:false,
      currentDeptName:'',
      fillerName:'',
      currentStockName:'',
      currentDeptId:'',
      ownStockList:[],
      operatingMain:{},
      operatingInfo:[],
      operatingGoodsList:[],
      operatingGoodsBatch:[],
      outStockBill:{},
      CKLXDicts: {},

      sFilters: {
        surScheduleNo: '',//手术排班号
        startDate: Date.now() - 7 * 24 * 60 * 60 * 1000,
        endDate: Date.now(),
        sickerName: '',
      },
      surInfoPrint:[],
      surInfoPrintView:{},
      surMainPt:true,
      total: 0,
      page: 1,
      pageSize: 30,
    }
  },
  computed: {
    OUTSTOCK_BILL_KIND() {
      return OUTSTOCK_BILL_KIND;
    },
  },
  mounted() {
    this.getCKLXDict(),
    this.getStockList();
    this.currentDeptName = this.user.orgName;
    this.fillerName = this.user.ename;
    this.currentDeptId = this.user.orgId;
  },
  methods: {
    barcodeTypeChange(val) {
      this.$nextTick(function () {
        if (val) {
          this.$refs.mbarcode.$el.children[0].focus()
          this.$refs.mbarcode.$el.children[0].select()
        } else {
          this.$refs.mainBarcode.$el.children[0].focus()
          this.$refs.mainBarcode.$el.children[0].select()
        }
      })
    },
    barcodeResolve() {
      if (this.operatingInfo.find((o => o.surCode === this.barcode))) {
        this.$message.error("不可重复扫码！")
        return;
      }
      var _this = this;
      if (this.barcode.indexOf('A') == 0 || this.barcode.indexOf('a') == 0) {
        this.parseCombinedBarcode(this.barcode, null, this.user.corpId).then(res => {
          _this.$refs.mbarcode.$el.children[0].focus();
          _this.$refs.mbarcode.$el.children[0].select();
          if (res.data.code == 0) {
            if (res.data.data != null) {
              this.operatingMain = res.data.data;
              this.operatingGoodsList = this.operatingMain.pkgListDis;
              this.operatingInfo.push(this.operatingMain);
            } else {
              _this.$message.error("无此手术包所对应的产品!");
            }
            this.dialogFormVisible = true;
          }else {
            _this.$message.error("无此手术包所对应的产品!");
          }
        });
      } else {
        this.$message.error("输入条码无效！");
      }
    },

    barcodeNext() {
      this.fBarcode = ''
      this.$refs.fbarcode.$el.children[0].focus()
      this.$refs.fbarcode.$el.children[0].select()
    },
    barcodePre() {
      this.parseCombinedBarcode(this.mainBarcode, this.fBarcode, this.distrBill.purchaseCompanyId, this.distrBill.provId).then(res => {
        var goodsLst = res.data.data
        this.setSendGoods(goodsLst)
        this.$refs.mainBarcode.$el.children[0].focus()
        this.$refs.mainBarcode.$el.children[0].select()
        this.mainBarcode = ''
        this.fBarcode = ''
      })
    },
    parseCombinedBarcode: function (barcode, slaverBarcode, hosId) {
      var param = {
        hosId: hosId,
        //手术包条码
        surCode: barcode,
        curStockId:this.currentStock,
        status:20,//手术包请购入库
      };
      return this.axios.post(this.baseURL + "/getSurveryInfo", param);
    },
    rowClick(row){
      this.operatingGoodsList = row.pkgListDis;
    },
    delCacheRow(row){
      let index = this.operatingInfo.findIndex(item => item.surCode === row.surCode);
      if (index >= 0) {
        this.operatingInfo.splice(index, 1);
      }
    },
    handleDlgConfirm(){
      this.confirmBtnLoading=true;
      this.axios.post(this.baseURL + "/submitOutBill", this.outStockBill).then(res=>{
          if (res.data.code === 0) {
            this.outStockBill ={};
            this.operatingInfo.splice(0);
            this.operatingGoodsList.splice(0);
            this.$message.success("提交出库单成功!");
            this.dlgVisible =false;
          }else {
            this.handleError(res.data.msg);
          }
        this.confirmBtnLoading = false;
      }).catch(err => {
        this.handleError(err);
        this.confirmBtnLoading = false;
      });
    },
    closeDig(){
      this.dlgVisible = false;
      this.outStockBill={};
    },
    handlePreviewBtnClick(){
      var param = {
        outStockKind: OUTSTOCK_BILL_KIND.SURGERY.value,
        outStockType: OUTSTOCK_TYPE.PREORDER.value,
        outOrgId:this.user.corpId,
        outOrgName:this.user.corpName,
        outDeptId:this.user.orgId,
        outDeptName:this.user.orgName,
        outStocId:this.currentStock,
        surgeryPkgVoList:this.operatingInfo
      };
      this.axios.post(this.baseURL + "/convertOutBill", param).then(
        res=>{
          if (res.data.code === 0) {
            this.outStockBill = res.data.data;
            this.dlgVisible = true;
          }else {
            this.handleError(res.data.msg);
          }
        }
      ).catch(err => {
        this.handleError(err);
      });
    },
    getStockList() {
      return this.axios
        .get("/spdHERPService/stockMgr/out/packageOut/getStockList")
        .then(res => {
          if (res.data.code === 0) {
            let result = res.data.data;
            this.ownStockList = result;
            if (result && result.length !== 0) {
              this.currentStock = result[0].id;
              this.currentStockName = result[0].name;
            }
            //this.getSupList();
          } else {
            this.handleError(res.data.msg);
          }
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    getCKLXDict() {
      this.getDict("CKLX")
        .then(res => {
          this.CKLXDicts = res;
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    getDict(dict) {
      let dicts = {};
      return new Promise((resolve, reject) => {
        this.axios
          .get("/spdHERPService/stockMgr/out/packageOut/getDict", {
            params: {
              dict
            }
          })
          .then(res => {
            if (res.data.code === 0) {
              for (let item of res.data.data) {
                dicts[item.value] = item.name;
              }
              resolve(dicts);
            } else {
              reject(res.data.msg);
            }
          })
          .catch(err => {
            reject(err);
          });
      });
    },
    //打印
    getSurPkgInfo(){
      if(this.sFilters.surScheduleNo){
        var param = {
          hosId: this.user.corpId,
          curStockId:this.currentStock,
          sickerName:this.sFilters.sickerName,
          surScheduleNo:this.sFilters.surScheduleNo,
        };
        this.axios.post(this.baseURL + "/getSurveryInfoPrint", param).then(res=>{
          if (res.data.code == 0) {
            this.surInfoPrint = res.data.data;
          }else {
            this.$message.error("查询失败！");
          }
        });
      }else {
        this.$message.error("手术排班号不能为空!");
      }
    },
    statusFil(row){
      return SURGERY_MAIN_STATUS[row.status].text;
    },
    surgeryView(row){
      this.surMainPt = false;
      this.surInfoPrintView = row;
    },
    goBack(){
      this.surMainPt = true;
      this.surInfoPrintView={};
    },
    printSur(){
      this.$refs.surPrintD.print(this.surInfoPrintView);
    },
    handleError(err) {
      return new Promise((resolve, reject) => {
        this.$msgbox({ title: "错误", type: "error", message: err })
          .then(action => {
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
  #operatingIS .table_content{
    height: 90%
  }
  #in-by-operator-out-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
  }
  #in-by-operator-out-list .page-main .page-content{
    overflow-y: hidden;
  }
  .page-main .page-content {
    height: 100%;
    overflow-y: hidden;
  }
  .table_content{
    font-size: 12px;
    /*height: calc(100% - 50px);*/
    height: 100%;
    overflow-y: auto;
  }
  #operatingPrintList .outView-tableheight{
    height: calc(100% - 150px);
  }
</style>
