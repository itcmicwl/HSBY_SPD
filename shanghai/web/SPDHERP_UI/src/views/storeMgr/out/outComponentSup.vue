<template>
  <div id="out-component" class="full-height">
    <el-row>
      <div class="inline-display" style="width:100%">
        <div class="inline-display">
          <el-tooltip :disabled="filter.length>2" effect="light" content="请最少输入3个字符" placement="bottom-start">
            <el-input ref="filter" v-model="filter" @keyup.native.enter="handleSearchBtn" placeholder="请输入商品名称、规格"></el-input>
          </el-tooltip>
        </div>
        <el-button :loading="searchBtnLoading" @click="handleSearchBtn" icon="search" style="margin-left:5px; width:60px">{{ searchBtnLoading ? '' : '搜索商品' }}</el-button>
        <div class="inline-display" style="margin-left:5px;">
          <label @click="handleLabelClick">副码：</label>
          <el-checkbox v-model="hasSlaverBarcode"></el-checkbox>
        </div>
        <div :class="[hasSlaverBarcode?'slaver-barcode-input':'barcode-input']" class="inline-display">
          <el-input ref="masterBarcode" v-model="masterBarcode" @keyup.native.enter="handleMasterBarcodeEnter" placeholder="请录入条码主码"></el-input>
        </div>
        <div v-if="hasSlaverBarcode" :class="[hasSlaverBarcode?'slaver-barcode-input':'']" class="inline-display">
          <el-input ref="slaverBarcode" v-model="slaverBarcode" @keyup.native.enter="handleSlaverBarcodeEnter" placeholder="请录入条码副码"></el-input>
        </div>
        <el-switch v-model="outStockType" :active-text="OUTSTOCK_TYPE.PURCHASE.text" :active-value="OUTSTOCK_TYPE.PURCHASE.value" :inactive-text="OUTSTOCK_TYPE.PREORDER.text" :inactive-value="OUTSTOCK_TYPE.PREORDER.value" class="switch" @change="handleTypeChange" inactive-color="#13ce66"></el-switch>
        <span class="inline-display">{{ outType }}库房：</span>
        <el-select v-model="currentStock" class="stock-select" @change="handleStockChange" placeholder="请选择房库">
          <el-option v-for="item in ownStockList" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
        <div class="inline-display" v-if="outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value">
          <span class="inline-display" style="margin-left:10px;">{{ outType }}至：</span>
          <el-select v-model="currentDeptId" class="stock-select" placeholder="请选择供应商">
            <el-option v-for="item in supList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </div>
        <div class="inline-display">
          <el-button :disabled="multipleSelection.length===0" @click="handlePreviewBtnClick" type="info">
            预览{{ outType }}单
          </el-button>
        </div>
      </div>
    </el-row>

    <el-table ref="stockTable" :data="stockGoodsList" class="table-height" highlight-current-row border row-key="goodsId" height="outter">
      <el-table-column width="40">
        <template slot-scope="scope">
          <el-tooltip :content="outType" effect="light">
            <el-button :disabled="scope.row.qty === 0" class="btn-circle" @click="addRowToCache(scope.row)" type="primary" icon="el-icon-check" circle></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column :label="outType+'数量'" width="120">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.qty" :disabled="scope.row.avlQty === 0 || scope.row.isUnique !== '3'" :max="scope.row.isUnique !== '3' ? 1 : Math.min(scope.row.avlQty, scope.row.selected.qty)" :min="0" @change="handleInputNumberChange(scope.row)" style="width:100%;"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="可退" width="60" prop="avlQty"></el-table-column>
      <el-table-column label="名称" prop="goodsName" show-overflow-tooltip></el-table-column>
      <el-table-column label="规格型号" prop="goodsGg" width="100" show-overflow-tooltip></el-table-column>
      <el-table-column :formatter="formatUnique" label="唯一码" width="55" prop="isUnique"></el-table-column>
      <el-table-column label="批号&批次&唯一码" width="300">
        <template slot-scope="scope">
          <el-select v-model="scope.row.selected.id" @change="changeBatchId(scope.row)" clearable style="width:100%">
            <el-option v-for="item in batchNoIdUniqueCodeList[scope.row.goodsId]" :key="item.id" :label="item.label" :value="item.id"></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="单位" width="35" prop="unit"></el-table-column>
      <el-table-column label="单价" width="60" prop="price"></el-table-column>
      <el-table-column label="金额" width="70">
        <template slot-scope="scope">
          {{ (scope.row.qty * scope.row.price).toFixed(2) || 0 }}
        </template>
      </el-table-column>
      <el-table-column label="包装规格" width="60" prop="pkg"></el-table-column>
      <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>
    </el-table>
    <span>待{{ outType }}商品</span>
    <el-table :data="multipleSelection" class="table-height" height="outter" highlight-current-row border>
      <el-table-column width="40">
        <template slot-scope="scope">
          <el-tooltip effect="light" content="删除">
            <el-button :disabled="scope.row.qty === 0" class="btn-circle" @click="delCacheRow(scope.row)" type="primary" icon="el-icon-delete" circle></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column :label="outType+'数量'" width="120">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.qty" :disabled="scope.row.avlQty === 0 || scope.row.isUnique !== '3'" :max="scope.row.isUnique !== '3' ? 1 : scope.row.avlQty" :min="0" style="width:100%;"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="可退" width="60" prop="avlQty"></el-table-column>
      <el-table-column label="名称" min-width="120" prop="goodsName" show-overflow-tooltip></el-table-column>
      <el-table-column label="规格型号" prop="goodsGg" min-width="150" show-overflow-tooltip></el-table-column>
      <el-table-column label="批号" width="120" prop="batchNo"></el-table-column>
      <el-table-column label="批次" width="120" prop="batchId" show-overflow-tooltip></el-table-column>
      <el-table-column label="唯一码" width="140" prop="uniqueCode"></el-table-column>
      <el-table-column label="单位" width="35" prop="unit"></el-table-column>
      <el-table-column label="单价" width="60" prop="price"></el-table-column>
      <el-table-column label="金额" width="70">
        <template slot-scope="scope">
          {{ (scope.row.qty * scope.row.price).toFixed(2) || 0 }}
        </template>
      </el-table-column>
      <el-table-column label="包装规格" width="60" prop="pkg"></el-table-column>
      <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>
    </el-table>

    <el-dialog :title="'预览'+outType+'单'" :visible.sync="dlgVisible" width="70%" top="10vh">
      <el-form ref="editForm" :model="outStockBill" class="el-form-item-nomsg" label-width="70px">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="出库类型">
              <el-select v-model="outStockBill.outStockKind" placeholder="请选择出库类型" disabled>
                <el-option v-for="(value,key) in CKLXDicts" :key="key" :label="value" :value="key"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="领用部门">
              <el-select v-model="outStockBill.outDeptId" disabled>
                <el-option v-for="item in supList" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item :label="outType+'人员'">
              <el-input v-model="outStockBill.filler" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="制单时间">
              <el-input v-model="outStockBill.fillDate" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <div class="text-style">
              种类：{{ selectedGoodsKinds }}，总数量：{{ selectedGoodsQuantity }}，总金额：{{ selectedGoodsAmount }}
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20" v-if="outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value">
          <el-col :span="8">
            <el-form-item label="收货地址" required prop="recAddress">
              <el-input v-model="outStockBill.recAddress" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人" required prop="recLinkman">
              <el-input v-model="outStockBill.recLinkman" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-col :span="18">
              <el-form-item label="联系电话" required prop="recLinkmanPhone">
                <el-input v-model="outStockBill.recLinkmanPhone" disabled></el-input>
              </el-form-item>
            </el-col>
            <!--<el-col :span="6">
              <deliveryAddress :dept-id="currentDeptId" :hos-id="user.corpId" @addressChange="setAddress" style="font-size:12px;line-height:25px;" btn_text="修改"></deliveryAddress>
            </el-col>-->
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="detailTable" :data="outStockBill.goodsList" highlight-current-row border>
        <el-table-column type="index" label="序号" width="45" align="center"></el-table-column>
        <el-table-column :label="outType+'数量'" prop="qty" width="60"></el-table-column>
        <el-table-column label="名称" prop="goodsName" show-overflow-tooltip></el-table-column>
        <el-table-column label="规格型号" prop="goodsGg" show-overflow-tooltip></el-table-column>
        <el-table-column label="批号" prop="batchNo"></el-table-column>
        <el-table-column label="注册证" width="200" prop="certificateCode" align="center">
          <template slot-scope="scope">
            {{ scope.row.selected.certificateCode || '' }}
          </template>
        </el-table-column>
        <el-table-column label="批次" prop="batchId" show-overflow-tooltip></el-table-column>
        <el-table-column label="唯一码" prop="uniqueCode"></el-table-column>
        <el-table-column label="单位" width="35" prop="unit"></el-table-column>
        <el-table-column label="单价" width="40" prop="price"></el-table-column>
        <el-table-column label="金额" width="50">
          <template slot-scope="scope">
            {{ (scope.row.qty * scope.row.price).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column label="包装规格" width="60" prop="pkg"></el-table-column>
        <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>
        <el-table-column :formatter="formatUnique" label="唯一码管理" prop="isUnique"></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dlgVisible = false">取消</el-button>
        <el-button :loading="confirmBtnLoading" @click="handleDlgConfirm()" type="primary">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//import deliveryAddress from '../../../components/deliveryAddress.vue';
import { OUTSTOCK_BILL_STATUS, OUTSTOCK_BILL_KIND, OUTSTOCK_TYPE } from '../../../common/js/constance';
import barcodeUtil from '../../../common/js/barcodeUtil.js';
import cloneDeep from 'lodash/cloneDeep';
import moment from 'moment';

export default {
  components: {
    //deliveryAddress,
  },
  props: {
    baseUrl: {
      type: String,
      required: true,
    },
    baseUrlSup: {
      type: String,
      required: true,
    },
    outStockKind: {
      type: String,
      required: true,
    },
    outType: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      ownStockList: [],
      currentStock: '',
      currentDeptId: '',
      searchBtnLoading: false,
      confirmBtnLoading: false,

      stockGoodsList: [],
      multipleSelection: [],
      batchNoIdUniqueCodeList: [],
      uniqueCodeList: [],

      hasSlaverBarcode: false,
      masterBarcode: '',
      slaverBarcode: '',
      parseBarcodeResultSet: '',

      dlgVisible: false,
      outStockBill: {
        goodsList: [],
      },
      supList:[],
      supInfo:{},
      CKLXDicts: {},

      outStockType: 20,



      filter: '',

      pageNum: 1,
      pageSize: 0,
      rTotal: 0,
    };
  },
  computed: {
    OUTSTOCK_TYPE() {
      return OUTSTOCK_TYPE;
    },
    OUTSTOCK_BILL_KIND() {
      return OUTSTOCK_BILL_KIND;
    },
    selectedGoodsKinds() {
      const kinds = new Set(this.outStockBill.goodsList.map(o => o.goodsId));
      return kinds.size;
    },
    selectedGoodsQuantity() {
      return this.outStockBill.goodsList.reduce((acc, cur) => acc + cur.qty, 0);
    },
    selectedGoodsAmount() {
      return this.outStockBill.goodsList.reduce((acc, cur) => acc + cur.qty * cur.price, 0).toFixed(2);
    },
  },
  watch: {
    supList: function(oldValue, newValue){
      if(!this.currentDeptId && newValue){
        this.currentDeptId = this.supList[0].id;
      }
    }
  },
  mounted() {
    //this.getSupList();
    this.getStockList();
    this.getCKLXDict();
  },
  methods: {
    handleSearchBtn() {
      if ((this.outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && this.outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value) && (!this.currentStock || !this.currentDeptId)) {
        this.handleError('未选择' + this.outType + '库房或收货科室。');
        return;
      }
      if (!this.filter) {
        return;
      }
      this.stockGoodsList.splice(0);
      this.searchBtnLoading = true;
      this.getStockGoodsList()
        .then(res => {
          if (res.data.code < 0) {
            this.handleError(res.data.msg);
          } else {
            this.rTotal = res.data.data.total;
            this.stockGoodsList = res.data.data.data;
            this.stockGoodsList.forEach(o => {
              this.$set(o, 'batchId', '');
              this.$set(o, 'batchNo', '');
              this.$set(o, 'uniqueCode', '');
              this.$set(o, 'selected', { id: '' });
            });
            this.getUniqueCodeList();
          }
          this.searchBtnLoading = false;
        })
        .catch(err => {
          this.searchBtnLoading = false;
          this.handleError(err);
        });
    },
    handleTypeChange() {
      this.multipleSelection.splice(0);
      this.handleSearchBtn();
    },
    handleStockChange() {
      this.multipleSelection.splice(0);
      this.handleSearchBtn();
    },
    handleLabelClick() {
      if (this.stockGoodsList.length !== 0) {
        this.hasSlaverBarcode = !this.hasSlaverBarcode;
      }
    },
    handleMasterBarcodeEnter() {
      if (this.hasSlaverBarcode) {
        this.$refs['slaverBarcode'].$el.children[0].focus();
      } else {
        this.handleSlaverBarcodeEnter();
      }
    },
    handleSlaverBarcodeEnter() {
      if ((this.outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && this.outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value) && (!this.currentStock || !this.currentDeptId)) {
        this.handleError('未选择' + this.outType + '库房或收货科室。');
        return;
      }
      if (this.masterBarcode === '' && this.slaverBarcode === '') {
        return;
      }
      this.stockGoodsList.splice(0);
      this.searchBtnLoading = true;
      const masterBarcode = this.masterBarcode;
      this.getBarcodeParseResult()
        .then(res => {
          this.stockGoodsList = res.stockpile;
          this.stockGoodsList.forEach(o => {
            this.$set(o, 'batchId', '');
            this.$set(o, 'batchNo', '');
            this.$set(o, 'uniqueCode', '');
            this.$set(o, 'selected', { id: '' });
          });
          this.getUniqueCodeList().then(() => {
            // 唯一码情况
            if (barcodeUtil.validBarcodeUnique(masterBarcode)) {
              this.stockGoodsList.forEach(item => {
                item.selected = cloneDeep(
                  this.batchNoIdUniqueCodeList[item.goodsId].find((element, index, array) => element.uniqueCode === masterBarcode)
                );
                if (item.selected) {
                  item.price = item.selected.price;
                  item.batchId = item.selected.batchId;
                  item.batchNo = item.selected.batchNo;
                  item.uniqueCode = item.selected.uniqueCode;
                  this.addRowToCache(item);
                }
              });
            } else if (barcodeUtil.validBarcodePackage(masterBarcode)) {
              //定数包情况
              let pkgGoods = res.barcodeParseResults;
              pkgGoods.forEach(pkgGoodsItem => {
                let item = this.stockGoodsList.find((element, index, array) => element.goodsId === pkgGoodsItem.goodsId);
                item.selected = cloneDeep(
                  this.batchNoIdUniqueCodeList[item.goodsId].find((element, index, array) => {
                    return (
                      pkgGoodsItem.price === element.price &&
                      (pkgGoodsItem.batchNo === element.batchNo || (!pkgGoodsItem.batchNo && !element.batchNo)) &&
                      pkgGoodsItem.batchId === element.batchId &&
                      (pkgGoodsItem.uniqueCode === element.uniqueCode || (!pkgGoodsItem.uniqueCode && !element.uniqueCode)) &&
                      ((!pkgGoodsItem.expiredDate && !element.expiredDate) ||
                        moment(pkgGoodsItem.expiredDate).format('YYYY-MM-DD') === moment(element.expiredDate).format('YYYY-MM-DD'))
                    );
                  })
                );
                if (item.selected) {
                  item.qty = pkgGoodsItem.qty;
                  item.price = item.selected.price;
                  item.batchId = item.selected.batchId;
                  item.batchNo = item.selected.batchNo;
                  item.uniqueCode = item.selected.uniqueCode;
                  this.addRowToCache(item);
                }
              });
            } else {
              // 其它条码情况
              let barcodeResult = res.barcodeParseResults;
              barcodeResult.forEach(goodsItem => {
                let item = this.stockGoodsList.find((element, index, array) => element.goodsId === goodsItem.goodsId);
                item.selected = cloneDeep(
                  this.batchNoIdUniqueCodeList[item.goodsId].find((element, index, array) => {
                    return (
                      (goodsItem.batchNo === element.batchNo || (!!goodsItem.batchNo && !!element.batchNo)) &&
                      ((!goodsItem.expiredDate && !element.expiredDate) ||
                        moment(goodsItem.expiredDate).format('YYYY-MM-DD') === moment(element.expiredDate).format('YYYY-MM-DD')) &&
                      (goodsItem.uniqueCode === element.uniqueCode || (!goodsItem.uniqueCode && !element.uniqueCode))
                    );
                  })
                );
                if(item.selected){
                  item.qty = item.selected.qty;
                  item.price = item.selected.price;
                  item.batchId = item.selected.batchId;
                  item.batchNo = item.selected.batchNo;
                  item.uniqueCode = item.selected.uniqueCode;
                  this.addRowToCache(item);
                }
              });
            }
          });
          this.searchBtnLoading = false;
          this.masterBarcode = '';
          this.slaverBarcode = '';
        })
        .catch(err => {
          this.handleError(err);
          this.searchBtnLoading = false;
        });
    },
    handlePreviewBtnClick() {
      this.outStockBill = this.newOutStockBill();
      if (this.combineDuplicateGoods()) {
        this.handleError('所选商品有重复项');
        return;
      }
      // 判断领用部门是否为''
      if ((this.outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && this.outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value) && this.outStockBill.outDeptId.length === 0) {
        this.handleError('请选择领用部门');
        return;
      }
      if (this.outStockBill.goodsList.some(o => !o.selected || o.selected.id === '')) {
        this.handleError('所选商品未选择批号/批次/唯一码。');
        return;
      }
      if (this.outStockBill.goodsList.some(o => o.qty > Math.min(o.avlQty, o.selected.qty, o.undoneQty))) {
        this.handleError('所选商品数量错误。');
        return;
      }
      if (this.outStockBill.goodsList.length === 0) {
        this.handleError('请选择商品');
        return;
      }
      this.dlgVisible = true;
    },
    handleDlgConfirm() {
      // 验证商品
      if (
        (this.outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && this.outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value) &&
        (!this.outStockBill.recAddressId ||
        !this.outStockBill.recLinkman ||
        !this.outStockBill.recLinkmanPhone ||
        !this.outStockBill.recAddress)
      ) {
        this.handleError('请选择收货地址');
        return;
      }
      this.confirmBtnLoading = true;
      this.axios
        .post(this.baseUrl + '/saveOutStockBill', this.outStockBillData2Post(true))
        .then(res => {
          if (res.data.code === 0) {
            this.$message.success('提交' + this.outType + '单成功');
            this.outStockBill.goodsList.splice(0);
            this.multipleSelection.splice(0);
            this.handleSearchBtn();
          } else {
            this.handleError(res.data.msg);
          }
          this.confirmBtnLoading = false;
        })
        .catch(err => {
          this.handleError(err);
          this.confirmBtnLoading = false;
        });
      this.dlgVisible = false;
    },
    getStockList() {
      return this.axios
        .get(this.baseUrl + '/getStockList')
        .then(res => {
          if (res.data.code === 0) {
            let result = res.data.data;
            this.ownStockList = result;
            if (result && result.length !== 0) {
              this.currentStock = result[0].id;
            }
            this.getSupList();
          } else {
            this.handleError(res.data.msg);
          }
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    handleInputNumberChange(row) {
      if (row.qty > 0) {
        if (!row.selected.id) {
          row.selected = cloneDeep(
            this.batchNoIdUniqueCodeList[row.goodsId].find((element, index, array) => element.qty === row.undoneQty) ||
              this.batchNoIdUniqueCodeList[row.goodsId][0]
          );
          row.price = row.selected.price;
          row.batchId = row.selected.batchId;
          row.batchNo = row.selected.batchNo;
          row.uniqueCode = row.selected.uniqueCode;
        }
        // this.$refs['stockTable'].toggleRowSelection(row, true);
      } else {
        row.selected = { id: '' };
        // this.$refs['stockTable'].toggleRowSelection(row, false);
      }
    },
    setAddress(d) {
      this.outStockBill.recAddressId = d.id;
      this.outStockBill.recLinkman = d.lxr;
      this.outStockBill.recLinkmanPhone = d.lxrPhone;
      this.outStockBill.recAddress = ('' + d.province + d.city + d.area + d.address).replace(/null/g, '');
    },
    changeBatchId(row) {
      if (row.selected.id === '') {
        return;
      }
      row.selected = cloneDeep(this.batchNoIdUniqueCodeList[row.goodsId].find(o => o.id === row.selected.id));
      row.price = row.selected.price;
      row.batchId = row.selected.batchId;
      row.batchNo = row.selected.batchNo;
      row.uniqueCode = row.selected.uniqueCode;
      // row.qty = row.isUnique !== '3' ? 1 : Math.min(row.qty, row.selected.qty);
      row.qty = Math.min(row.qty, row.selected.qty);
    },
    formatUnique(row, column, cellValue) {
      return cellValue !== '3' ? '是' : '否';
    },
    getStockGoodsList() {
      return this.axios.get(this.baseUrlSup + '/getStockGoodsListSup', {
        params: {
          stockId: this.currentStock,
          stocKind: this.outStockType,
          filter: this.filter,
          provId:this.currentDeptId
        },
      });
    },
    getUniqueCodeList() {
      let distinctGoodsId = new Set(this.stockGoodsList.map(item => item.goodsId));
      if (distinctGoodsId.size > 0) {
        return this.getUniqueCode(distinctGoodsId)
          .then(res => {
            if (res.data.code === 0) {
              this.uniqueCodeList = JSON.parse(JSON.stringify(res.data.data));
              for (let goodsId of distinctGoodsId) {
                let goodsList = res.data.data.filter(o => o.goodsId === goodsId);
                this.$set(this.batchNoIdUniqueCodeList, goodsId, [...goodsList]);
                for (let item of this.batchNoIdUniqueCodeList[goodsId]) {
                  item.label = 'ID:' + item.id;
                  if (item.batchNo) {
                    item.label += ',' + item.batchNo + '|' + item.batchId;
                  } else {
                    item.label += ',' + item.batchId;
                  }
                  if (item.uniqueCode) {
                    item.label += '|' + item.uniqueCode;
                  }
                  item.label += ', 数量:' + item.qty;
                }
                this.batchNoIdUniqueCodeList[goodsId].sort((a, b) => Number(a.id) - Number(b.id));
              }
            } else {
              this.handleError(res.data.msg);
            }
          })
          .catch(err => {
            this.handleError(err);
          });
      }
    },
    getUniqueCode(goodsId) {
      let params = {
        stockId: this.currentStock,
        buyKind: this.outStockType,
        goodsId,
      };
      return this.axios.post(this.baseUrl + '/getUniqueCodeList', params);
    },
    getSupList() {
      return this.axios
        .get(this.baseUrlSup + '/getSupList',{
          params:{
            stockid:this.currentStock
          },
        })
        .then(res => {
          if (res.data.code === 0) {
            // 判断查询结果是否为空
            if (res.data.data.length === 0) {
              return;
            }
            this.supList = res.data.data;
            this.currentDeptId = this.supList[0].id;
          } else {
            this.handleError(res.data.msg);
          }
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    getBarcodeParseResult() {
      let params = {
        stockId: this.currentStock,
        stocKind: this.outStockType,
        masterBarcode: this.masterBarcode,
        slaverBarcode: this.slaverBarcode,
      };
      return new Promise((resolve, reject) => {
        this.axios.get(this.baseUrl + '/parseBarcode', { params }).then(res => {
          if (res.data.code === 0) {
            if(res.data.data.stockpile.length > 0){
              resolve(res.data.data);
            } else {
              reject('未找到相应商品');
            }
          } else {
            reject(res.data.msg);
          }
        });
      });
    },
    getCKLXDict() {
      this.getDict('CKLX')
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
          .get(this.baseUrl + '/getDict', {
            params: {
              dict,
            },
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
    newOutStockBill() {
      var ctip = this.currentDeptId;
      this.supInfo = this.supList.find(item=>{return item.id===ctip});
      return {
        outStockKind: this.outStockKind,
        outStockType: this.outStockType,
        outDeptId: this.currentDeptId,
        fillDate: new Date().toLocaleDateString(),
        filler: this.user.ename,
        recAddressId: this.supInfo.id,
        recAddress: this.supInfo.address,
        recLinkman: this.supInfo.linkman,
        recLinkmanPhone: this.supInfo.contactWay,
        goodsList: cloneDeep(this.multipleSelection),
      };
    },
    outStockBillData2Post(submit) {
      let data = cloneDeep(this.outStockBill);
      for (let goods of data.goodsList) {
        goods.stockTableId = goods.selected.id;
        goods.certificateCode = goods.selected.certificateCode;
        delete goods.avlQty;
        delete goods.selected;
      }
      delete data.fillDate;
      delete data.filler;

      if (submit) {
        data.status = OUTSTOCK_BILL_STATUS.SUBMIT.value;
      } else {
        data.status = OUTSTOCK_BILL_STATUS.SAVE.value;
      }

      data.outStocId = this.currentStock;
      if(this.currentDeptId){
        data.inDeptId = this.currentDeptId;
        data.inDeptName = this.supList.find(o => o.id === this.currentDeptId).name;
      }
      data.st="100";//二级库退货出库标识，区别一级库出库
      return data;
    },
    combineDuplicateGoods() {
      let goodsLableSet = new Set();
      for (let good of this.outStockBill.goodsList) {
        goodsLableSet.add(good.selected.id);
      }
      if (this.outStockBill.goodsList.length > goodsLableSet.size) {
        let goodsList = [];
        for (let goodsLabel of goodsLableSet) {
          let sumQty = this.outStockBill.goodsList.filter(item => item.selected.id === goodsLabel).reduce((acc, cur) => acc + cur.qty, 0);
          let goodsInfo = this.outStockBill.goodsList.find(item => item.selected.id === goodsLabel);
          goodsInfo.qty = sumQty;
          goodsList.push(JSON.parse(JSON.stringify(goodsInfo)));
        }
        this.outStockBill.goodsList = goodsList;
        return true;
      } else {
        return false;
      }
    },
    addRowToCache(row) {
      let item = this.multipleSelection.find(o => o.selected.id === row.selected.id);
      if (item) {
        item.qty += row.qty;
        item.qty = Math.min(item.qty, item.selected.qty);
      } else {
        item = cloneDeep(row);
        item.avlQty = item.selected.qty;
        this.multipleSelection.push(item);
      }
    },
    delCacheRow(row) {
      let index = this.multipleSelection.findIndex(item => item.selected.id === row.selected.id);
      if (index >= 0) {
        this.multipleSelection.splice(index, 1);
      }
    },
    handleError(err) {
      return new Promise((resolve, reject) => {
        this.$msgbox({ title: '错误', type: 'error', message: err })
          .then(action => {
            resolve(action);
          })
          .catch(err => {
            reject(err);
          });
      });
    },
  },
};
</script>

<style scoped="">
.full-height {
  height: 100%;
}

.stock-select {
  margin-bottom: 8px;
  width: 120px;
}

.table-height {
  height: calc(50% - 40px);
}

.barcode-input {
  width: 20%;
}

.slaver-barcode-input {
  width: 10%;
}

.barcode-input-margin {
  margin: 5px;
}

.inline-display {
  display: inline-block;
}

.text-style {
  padding-left: 20px;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  padding-top: 2px;
}

.switch {
  font-size: 12px;
  line-height: 16px;
  padding: 0 10px;
}

.btn-circle {
  padding: 2px;
}
</style>
