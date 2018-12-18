<template>
    <el-row id="in-by-send-edit" v-loading="diaLoging"  style="height:100%">
        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="120px">
                <el-col :span="8">
                    <el-form-item label="配送单号:">
                        <span>{{ inStockBill.outBillId }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="供应商:">
                        <span>{{ inStockBill.outOrgName }}</span>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                    <el-form-item label="入库部门:">
                        <orgTreeSlt v-model="inStockBill.inDeptId" :hosId="this.user.corpId" @change="inDeptChange"></orgTreeSlt>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                    <el-form-item label="入库库房:">
                        <Stock4Slt v-model="inStockBill.inStocId" :hosId="this.user.corpId" :deptId='inStockBill.inDeptId'></Stock4Slt>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="制单时间:">
                        <span>{{ inStockBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="制单人:">
                        <span>{{ inStockBill.fillterName }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                    <el-form-item label="备注:">
                        <!-- <span>{{ inStockBill.memo }}</span> -->
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
        <!-- <el-col :span="3" class="toolbar" style="padding-bottom: 0px; line-height: 25px;">
            <el-switch v-model="barcodeType" active-text="仅主码" inactive-text="主副码" @change="barcodeTypeChange">
            </el-switch>
        </el-col>
        <el-col :span="20" class="toolbar" v-show="barcodeType" style="padding-bottom: 0px;">
            <el-input type="text" class="input-barcode" ref="mbarcode" v-model="barcode" @keyup.native.enter="addBybarcode" autosize placeholder="请录入条形码">
            </el-input>
        </el-col>
        <el-col :span="20" class="toolbar" v-show="!barcodeType" style="padding-bottom: 0px;">
            <el-col :span="10">
                <el-input type="text" class="input-barcode" ref="mainBarcode" v-model="mainBarcode" @keyup.native.enter="barcodeNext" autosize placeholder="请录入条形码主码">
                </el-input>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="10">
                <el-input type="text" class="input-barcode" ref="fbarcode" v-model="fBarcode" @keyup.native.enter="barcodePre" autosize placeholder="请录入条形码副码">
                </el-input>
            </el-col>
        </el-col> -->
        <barcodeResolver :barcodeKind="barcodeKind.CGRK" :hosId="user.corpId"
        :deptId="user.orgId" :userId="user.userId" @resolved="onBarcodeResolved"></barcodeResolver>
        <el-table ref="receListTable" :data="inStockBill.lstDetail"  height="outter"  fit border  class ='inEdit-tableheight' stripe @selection-change="sltRowsChange" row-key="inBillRow" :row-class-name="billTableRowClassName">
            <el-table-column type="selection" width="30" :selectable="canSltRow">
            </el-table-column>
            <el-table-column label="操作" width="60">
                <template slot-scope="scope">
                    <div class="icoBtn">
                        <i class="fa fa-trash-o fa-lg" @click="delRow(scope.row)" title="删除" style="color:red;margin-left:5px;"></i>
                        <i class="fa fa-barcode fa-lg" @click="editBarcode(scope.row)" title="编辑条码" v-if="scope.row.uniqueKind<3"></i>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="序号" prop="inBillRow" width="40">
            </el-table-column>
            <el-table-column label="产品名称" prop="goodsName" min-width="180" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="规格" prop="goodsGg" show-overflow-tooltip min-width="180">
            </el-table-column>
            <el-table-column label="储运条件" prop="stCondition" width="90" :formatter="formateStCondition">
            </el-table-column>
            <el-table-column label="配送数量" prop="outQty" width="90">
            </el-table-column>
            <el-table-column label="入库数量" width="90">
                <template slot-scope="scope">
                    <el-input-number placeholder="请输入数量" v-model="scope.row.inQty" size="mini" :min="0" :max="scope.row.unUsedQty" class="col-input-num" :disabled = "scope.row.uniqueKind<3 || scope.row.unUsedQty == 0" @change="inQtyChange(scope.row)">
                    </el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="单位" prop="unit">
            </el-table-column>
            <el-table-column label="批号" prop="batchCode">
            </el-table-column>
            <el-table-column label="注册证号" width="120">
                <template slot-scope="scope">
                    <el-input placeholder="请输入注册证号" v-model="scope.row.certificateCode" size="mini">
                    </el-input>
                </template>
            </el-table-column>
            <el-table-column label="灭菌批号" prop="sterilizationCode">
            </el-table-column>
            <el-table-column label="灭菌有效日期" width="130">
                <template slot-scope="scope">
                    {{scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>
            <el-table-column label="灭菌日期" width="130">
                <template slot-scope="scope">
                    {{scope.row.sterilizationDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>
            <el-table-column label="有效期" width="130">
                <template slot-scope="scope">
                    {{scope.row.expdtEndDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>
            <el-table-column label="产地" prop="made" width="90">
            </el-table-column>
            <el-table-column label="生产厂家" prop="mfrsName" width="200" show-overflow-tooltip>
            </el-table-column>
        </el-table>
         <el-col :span="20" class="toolbar" style="padding-bottom: 0px;text-align: center;">
            <el-button   @click.native="cancel">取消</el-button>
            <!--<el-button type="primary" @click.native="submitPurconfirm(10)" >保存</el-button>-->
            <el-tooltip placement="top">
              <div slot="content">请查验供货者资质、医疗器械注册证或备案凭证等合格证明文件<br>（冷链产品需核实储运条件应符合产品说明书和标签标示要求）</div>
              <el-button type="primary" @click.native="submitPurconfirm(20)" >查验入库</el-button>
            </el-tooltip>
        </el-col>
        <el-dialog title="条码设置" :visible.sync="barcodeV" :close-on-click-modal="false" >
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="70px" class="el-form-item-nomsg" label-position="left">
                    <el-col :span="7">
                        <el-form-item label="商品名称:">
                            <el-input v-model="UniqueCode.goodsName" :disabled="true" placeholder="商品名称" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="唯一码:">
                            <el-input class="input-barcode-small" ref="sbarcode" v-model="UniqueCode.uniqueCode" placeholder="请录入唯一码"   @keyup.native.enter="addBarCode"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="2">
                        <el-form-item>
                            <el-button type="primary" @click.native="addBarCode">添加</el-button>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-tag type="primary" v-for="item in lstUniqueCode" :key="item.id" :closable="true" :title="item.batchCode" @close="delBarCode(item)">
                    <i class="fa fa-barcode"></i>
                    {{item.uniqueCode}}
                </el-tag>
            </el-col>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="barcodeV = false" >关闭</el-button>
            </div>
        </el-dialog>
    </el-row>
</template>
<script>
import moment from 'moment'
import { DISTRBILL_STATUS } from './common/constance'
import barcodeResolver from './common/barcodeResolver';
import {BARCODEKIND,BARCOD_RES_TYPE} from './common/constance';
import Stock4Slt from '../../components/Stock4Slt'
import orgTreeSlt from '../../components/orgTreeSlt'
import barcodeUtil from '../../common/js/barcodeUtil'
var Child = {
  template: '<div><p v-for="item in errMsg" v-html="item"></p></div>',
  props: ['errMsg']
}
export default {
  data () {
    return {
      wymArray:[],
      opModel: 'ADD',
      barcodeKind:BARCODEKIND,
      diaLoging: false,
      barcodeV: false,
      loading: false,
      stConditions:[],
      distrBillList: [
      ],
      distrBill: {
        id: '',
        billId: '',
        spdCode: '',
        purchaseCompanyId: '',
        purchaseCompanyCode: '',
        purchaseCompanyName: '',
        purchaseCompanyErpCode: '',
        applyOrgId: '',
        applyOrgCode: '',
        applyOrgName: '',
        applyOrgScmCode: '',
        applyOrgErpCode: '',
        recieveOrgId: '',
        recieveOrgCode: '',
        recieveOrgName: '',
        recieveOrgScmCode: '',
        recieveOrgErpCode: '',
        provId: '',
        provCode: '',
        provName: '',
        provErpCode: '',
        filler: '',
        remark: '',
        sumRow: '',
        status: '',
        fillDate: '',
        version: '',
        distrBillList: []
      },
      inStockBill: {
        id: '',
        billId: '',
        inStockKind: 1,
        inStockType: 0,
        outBillId: '',
        outOrgId: '',
        outOrgName: '',
        inOrgId: '',
        inOrgName: '',
        inDeptId: '',
        inDeptName: '',
        inStocId: '',
        sendBillid: '',
        filler: '',
        fillterName: '',
        fillDate: '',
        // out_dept_id: '',
        // outDeptName: '',
        // auditor: '',
        // auditDate: '',
        // accounter: '',
        // accountDate: '',
        status: 0,
        lstDetail: []
      },
      inStockDetail: {},
      constInStockDetail: {
        id: '',
        pid: '',
        billId: '',
        outBillRow: 0,
        inBillRow: 0,
        provId: '',
        provCode: '',
        provName: '',
        goodsId: '',
        goodsName: '',
        goodsGg: '',
        mfrsId: '',
        mfrsName: '',
        made: '',
        packetCode: '',
        isPacket: '',
        uniqueKind: '',
        batchCode: '',
        sterilizationDate: '',
        sterilizationCode: '',
        sterilizationEndDate: '',
        expdtEndDate: '',
        unit: '',
        outQty: 0,
        outPacketQty: '', // 出库包数
        inQty: '', // 入库数量
        intPacketQty: '',
        inPrice: 0, // 入库单价
        masterCode: '', // 主码
        secCode: '', // 副码
        hibcCode: '', // hibc
        version: 0,
        lstBatch: [],
        lstUniqueCode: []
      },
      sltRows: [],
      Batch: {
        id: '',
        pid: '',
        billId: '',
        pRowId: '',
        provId: '',
        goodsId: '',
        goodsBatchId: '',
        inPrice: '',
        inTime: '',
        qty: '',
        version: 0
      },
      UniqueCode: {
        id: '',
        pid: '',
        billId: '',
        goodsName: '',
        pRowId: 0,
        provId: '',
        goodsId: '',
        goodsBatchId: '',
        inPrice: '',
        inTime: '',
        uniqueCode: '',
        version: 0
      },
      lstUniqueCode: [],
      lstBatch: [],
      barcode: '',
      mainBarcode: '',
      fBarcode: '',
      barcodeType: true,
      barcodeMatchV: false,
      barcodeMatch: {
        provId: '',
        hosId: '',
        mfrsCode: ''
      }
    }
  },
  components: {Stock4Slt, orgTreeSlt,barcodeResolver},
  filters: {
    fStatus: function (value) {
      if (value) {
        return DISTRBILL_STATUS[value].text
      } else {
        return ''
      }
    }
  },
  methods: {
    canSltRow(row){
      return row.unUsedQty > 0;
    },
    //字典储运条件
    getStCondition: function () {
        this.loading = true;
        this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'CYTJ'}).then(res => {
            this.loading = false;
            if (res.data.code == "0") {
                this.stConditions = res.data.data;
            }
            else{
				this.$message.error( res.data.msg);
            }
        }, err => {
            this.loading = false;
        });
    },
    formateStCondition(row){
      let _this = this;
      let stCondition = '';
      _this.stConditions.forEach(item=>{
        if(item.val==row.stCondition){
          stCondition=item.ename;
          return false;
        }
      })
      return stCondition;
    },
    delRow (pd) {
      var index = this.inStockBill.lstDetail.findIndex(item => { return item.rowNumber == pd.rowNumber })
      this.inStockBill.lstDetail.splice(index, 1)
      this.inStockBill.lstDetail.forEach((item, i) => item.rowNumber = i + 1)
    },
    inDeptChange (dept) {
      this.inStockBill.inDeptName = dept.ename
    },
    stocChange (stoc) {
      if (stoc.length > 1) {
        this.inStockBill.inStocId = stoc[1]
        // this.inStockBill.inStocCode = stoc.inStocCode;
      }
    },
    getRecBill (recId) {
      var _this = this
      this.axios.get('/spdHERPService/stockMgr/inStock/getById4Instock?id=' + recId).then(res => {
        if (res.data.code == 0) {
          _this.inStockBill = res.data.data
          for (var i = 0; i < _this.inStockBill.lstDetail.length; i++) {
            _this.inQtyChange(_this.inStockBill.lstDetail[i])
          }
        }
      })
    },
    // 获取列表
    getDistrBill (billId) {
      var _this = this
      this.diaLoging = true
      this.axios.get('/spdHERPService/orderMgr/distrBill/unRecdistrBillByBillId/' + billId).then(res => {
        _this.diaLoging = false
        if (res.data.code == '0') {
          _this.distrBill = res.data.data
          _this.convertDistrToRecBill(_this.distrBill)
        }
      }, err => {
        this.diaLoging = false
      })
    },
    convertDistrToRecBill (distBill) {
      if (distBill) {
        this.inStockBill.id = '';
        this.inStockBill.billId = '';
        this.inStockBill.outBillId = distBill.billId;
        this.inStockBill.inStockKind = 1;
        this.inStockBill.outOrgId = distBill.provId;
        this.inStockBill.outOrgName = distBill.provName;
        this.inStockBill.inOrgId = distBill.purchaseCompanyId;
		    this.inStockBill.inStockType =distBill.purType;
        this.inStockBill.inOrgName = distBill.purchaseCompanyName;
        this.inStockBill.inDeptId = distBill.recieveOrgId;
        this.inStockBill.inDeptName = distBill.recieveOrgName;
        this.inStockBill.filler = this.user.userId;
        this.inStockBill.fillterName = this.user.ename;
        this.inStockBill.fillDate = new Date()// moment(new Date()).format("YYYY-MM-DD");
        // this.inStockBill.sendBillid = distBill.billId;
        this.inStockBill.status = 0;
        this.inStockBill.lstDetail = [];
        if (this.distrBill.distrBillList && this.distrBill.distrBillList.length > 0) {
          this.convertDistrLstToRecBillLst(this.distrBill)
        }
      }
    },
    convertDistrLstToRecBillLst (distrBill) {
      var res = [];
      distrBill.distrBillList.forEach(function (item, index) {
        var row = Object.assign({}, this.constRecBillDetail);
        row.id = '';
        row.pid = '';
        row.billId = '';
        row.inBillRow = index + 1;
        row.outBillId = distrBill.billId;
        row.outBillRow = item.rowNumber;
        row.unUsedQty = Number(item.sendQty) - Number(item.receiveQty) - Number(item.rejectedQty);
        // row.purRowNumber = 0;
        // row.purBillDate = item.purBillDate;
        row.provId = distrBill.provId;
        row.provCode = distrBill.provCode;
        row.provName = distrBill.provName;
        row.goodsId = item.hosGoodsId;
        row.goodsName = item.hosGoodsName;
        row.goodsGg = item.goodsGg;
        row.mfrsId = item.goodsMrfsId;
        row.mfrsName = '';
        row.made = item.made;
        row.packetCode = 0; // item.hosGoodsId;
        row.isPacket = '0';
        row.uniqueKind = item.uniqueKind;
        row.batchCode = item.batchCode;
        // row.batchCode = item.isUniqueCode;
        // row.batchNum = item.batchNum;
        row.sterilizationCode = item.sterilizationCode;
        row.certificateCode = item.certificateCode;
        row.sterilizationEndDate = item.sterilizationEndDate;
        row.sterilizationDate = item.sterilizationDate;
        row.expdtEndDate = item.expdtEndDate;
        row.unit = item.hosUnit;
        row.outQty = item.sendQty / item.sendScale;
        // row.outQty = item.hosQty - item.receiveQty / item.sendScale;
        row.outPacketQty = 0;// item.hosQty;
        row.inQty = row.uniqueKind==3 ? row.unUsedQty:0;
        //row.inQty = 0;// row.outQty;
        row.intPacketQty = 0;// 验收结论
        row.inPrice = item.sendPrice || 0;
        row.masterCode = '';
        row.secCode = '';
        row.hibcCode = '';
        row.version = 0;
        row.mfrsName = item.mfrsName;
        row.stCondition=item.stCondition;
        row.lstBatch = [];
        row.lstUniqueCode = [];
        row.lstUniqueCodeTemp= item.distrBillUniqueList;
        res.push(row);
      }, this);
      this.inStockBill.lstDetail = res;

    },
    sltRowsChange (sltRows) {
      this.sltRows = sltRows;
    },
    submitPurconfirm (status) {
      this.diaLoging = true
      const h = this.$createElement
      this.inStockBill.status = status
      var params = Object.assign({}, this.inStockBill)
      for(let row of this.sltRows){
          delete  row.lstUniqueCodeTemp;
      }
      params.lstDetail = this.sltRows;
      var serviceUrl = this.opModel == 'ADD' ? '/spdHERPService/stockMgr/inStock/add' : '/spdHERPService/stockMgr/inStock/update'
      var _this = this
      this.axios.post(serviceUrl, params).then(res => {
        _this.diaLoging = false
        if (res.data.code == '0') {
          _this.$msgbox({ title: '提示', message: '操作成功', type: 'success' })
          var billId = res.data.data.billId
          _this.$msgbox({
            title: '提示',
            message: '操作成功',
            type: 'success',
            callback: action => {
              this.$router.push({ path: 'inByReceive', query: { inStockBillId: billId }})
            }
          })
        } else if (res.data.code == '-122') {
          var msg = res.data.validateErrors.fieldErrors.map(x => x.message)
          _this.$msgbox({
            title: '错误',
            type: 'error',
            message: h(Child, {props: {errMsg: msg}})
          })
        } else {
          this.$msgbox({ title: '提示', message: '操作失败', type: 'error' })
        }
      }, err => {
        this.diaLoging = false
        this.$msgbox({ title: '提示', message: '操作失败', type: 'error' })
      })
    },
    onBarcodeResolved(barKind,data){
      if(barKind == BARCOD_RES_TYPE.ZBM){
        this.checkRow4wym(data);
      }else if(barKind == BARCOD_RES_TYPE.GS1){
        this.setSendGoods(goodsLst);
      }
    },
    checkRow4wym(code){
      let  pipeiFlag =false
      if(this.wymArray.find(u=>{return u==code} )){
        this.$message.error('该唯一码已扫描，且有明细行匹配!')
        return
      }
      for(var i =0;i<this.distrBill.distrBillList.length;i++){
        var item = this.distrBill.distrBillList[i];
        if(item.uniqueKind=='0'){
          var uniqueCode = item.distrBillUniqueList.find(o=>{return o.uniqueCode === code && (Number(item.sendQty) - Number(item.receiveQty) - Number(item.rejectedQty))>0});
          if(uniqueCode){
            var goodsItem = this.inStockBill.lstDetail.find(o=>{return o.outBillRow == item.rowNumber && this.inStockBill.outBillId ==this.distrBill.billId });
            if(goodsItem){
              pipeiFlag =true;
              this.wymArray.push(code);
              goodsItem.inQty++;
              this.inQtyChange(goodsItem);
              var barCode = {
                provId: goodsItem.provId,
                goodsId: goodsItem.goodsId,
                inPrice: goodsItem.inPrice,
                uniqueCode: code
              };
              goodsItem.lstUniqueCode.push(barCode);
              this.$refs.receListTable.toggleRowSelection(goodsItem, true);
            }
          }
        }
        if(pipeiFlag){
          break;
        }
      }
      if(!pipeiFlag){
        this.$message.error('无法识别的唯一码');
      }
    },
    findGoods (barCodeInfo) {
      var res = null
      var flag = false// 是否找到匹配的商品
      for (var i = 0; i < this.inStockBill.lstDetail.length; i++) {
        var item = this.inStockBill.lstDetail[i]
        if (barCodeInfo.goodsId == item.goodsId) {
          flag = true
          if (item.inQty >= 0 && item.inQty < item.outQty) {
            if (item.batchCode && item.batchCode == barCodeInfo.batchNo) {
              res = item
              break// ;
            }
            if (item.expdtEndDate && moment(item.expdtEndDate).format('YYYY-MM-DD') == moment(barCodeInfo.expiredDate).format('YYYY-MM-DD')) {
              res = item
              break
            }
            res = item
            break
          }
        }
      }
      if (!flag) {
        this.barcodeMatch.hosId = this.distrBill.inOrgId// this.distrBill.purchaseCompanyId;
        this.barcodeMatch.provId = this.distrBill.outOrgId
        this.barcodeMatch.mfrsCode = barCodeInfo.goodsNo
        this.barcodeMatchV = true
      }
      return res
    },
    setSendGoods (goodsLst) {
      if (!Array.isArray(goodsLst) || goodsLst.length == 0) {
        this.$message.error('此条码无法解析!')
        return
      }
      var curRow = null
      for (var i = 0; i < goodsLst.length; i++) {
        var curRow = this.findGoods(goodsLst[i])
        if (curRow && curRow.inQty < curRow.outQty) {
          break
        }
      }
      if (curRow) {
        curRow.expdtEndDate = new Date(goodsLst[i].expiredDate)
        curRow.batchCode = goodsLst[i].batchNo
        curRow.masterCode = goodsLst[i].barcode
        curRow.secCode = '' || goodsLst[i].slaverBarcode
        if (curRow.uniqueKind && curRow.uniqueKind == 3) { // 非唯一码管理 直接加数量
          curRow.inQty++
          this.inQtyChange(curRow);
        } else { // 唯一码
          this.inStockDetail = curRow
          // this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length;
          this.lstUniqueCode = this.inStockDetail.lstUniqueCode
          this.UniqueCode = {
            provId: this.inStockBill.outOrgId,
            goodsId: this.inStockDetail.goodsId,
            goodsName: this.inStockDetail.goodsName,
            inPrice: this.inStockDetail.inPrice,
            uniqueCode: ''
          }
          this.barcodeV = true
          this.$nextTick(function () {
            this.$refs.sbarcode.$el.children[0].focus()
            this.$refs.sbarcode.$el.children[0].select()
          })
        }
      }
    },
    barcodeMatchSave () {
      this.barcodeMatchV = false
      if (this.barcodeType) {
        this.addBybarcode()
      } else {
        this.barcodePre()
      }
    },
    editBarcode (row) {
      this.barcodeV = true
      this.inStockDetail = row
      this.lstUniqueCode = row.lstUniqueCode
      this.UniqueCode = {
        provId: this.inStockBill.outOrgId,
        goodsId: row.goodsId,
        goodsName: row.goodsName,
        inPrice:row.inPrice,
        uniqueCode: ''
      }
      this.$nextTick(function () {
        this.$refs.sbarcode.$el.children[0].focus()
        this.$refs.sbarcode.$el.children[0].select()
      })
    },
    addBarCode () {
      var _this = this
      if (!_this.UniqueCode.uniqueCode) {
        return
      }
     _this.checkRow4wym(_this.UniqueCode.uniqueCode);
    },
    delBarCode (barcode) {
      var index = this.inStockDetail.lstUniqueCode.findIndex(item => item.uniqueCode == barcode.uniqueCode)
      this.inStockDetail.lstUniqueCode.splice(index, 1)
      if (this.inStockDetail.uniqueKind != 3) {
        this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length
        this.inQtyChange(this.inStockDetail)
      }
    },
    inQtyChange (row) {
      this.$nextTick(function () {
        if (row.inQty > 0) {
          this.$refs.receListTable.toggleRowSelection(row, true)
        } else {
          this.$refs.receListTable.toggleRowSelection(row, false)
        }
      })
    },
    cancel () {
      this.$router.push({ path: 'inByReceive'})
    },
    billTableRowClassName (row, index) {
      return row.inQty < row.outQty ? 'info-row' : ''
    }
  },
  mounted () {
    var _this = this
    var recId = this.$route.query.recId
    if (recId) {
      this.opModel = 'EDIT'
      this.getRecBill(recId)
      this.getStCondition()
    } else {
      this.opModel = 'ADD'
      var distrBillId = this.$route.query.distrBillId
      this.getDistrBill(distrBillId)
      this.getStCondition()
    }
  }
}

</script>

<style scoped>
.input-barcode {
  border: 2px solid #C4C4C4;
  height: 25px;
  line-height: 25px;
}
</style>

<style>

#in-by-send-edit .el-table .info-row {
  background: #FF99FF;
}

#in-by-send-edit .fa {
    margin-right: 5px;
}

#in-by-send-edit .vue-echarts {
    width: 100%;
    height: 400px;
}

#in-by-send-edit .el-table {
    font-size: 12px;
}

#in-by-send-edit fieldset {
    border-width: 1px;
    border-style: solid;
    border-color: #1fa0ff;
    border-image: initial;
}

#in-by-send-edit .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}

#in-by-send-edit .el-dialog--small {
    width: 70%;
}
#in-by-send-edit .inEdit-tableheight{
    height:  calc(100% - 200px);
}
</style>
