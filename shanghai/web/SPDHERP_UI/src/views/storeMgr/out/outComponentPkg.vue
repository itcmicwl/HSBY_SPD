<template>
  <div id="out-component" class="full-height">
    <el-row class="toolbar" style="padding-bottom: 0px;">
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
    <el-row class="toolbar">
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
        <el-button :disabled="packageGoodsList.length===0" @click="handlePreviewBtnClick" type="info">
          预览{{ outType }}单
        </el-button>
      </el-col>
    </el-row>
    <el-table ref="stockTable" :data="packageGoodsList" class="table-height-main" highlight-current-row border
              row-key="index" height="outter" @row-click="rowClick">
      <!--<el-table-column width="40" align="center">
        <template slot-scope="scope">
          <el-tooltip :content="outType" effect="light">
            <el-button :disabled="scope.row.qty === 0" class="btn-circle" @click="addRowToCache(scope.row)" type="primary" icon="el-icon-check" circle></el-button>
          </el-tooltip>
        </template>
      </el-table-column>-->

      <el-table-column label="操作" width="50px">
        <template slot-scope="scope">
          <div class="icoBtn">
            <i class="fa fa-trash-o fa-lg" @click="delCacheRow(scope.row)" title="删除"
               style="color:red;margin-left:5px;"></i>
          </div>
        </template>
      </el-table-column>

      <el-table-column :label="outType+'数量'" width="60" prop="qty" align="center">
      </el-table-column>
      <el-table-column label="包号" prop="packageId" show-overflow-tooltip align="center" width="100px"></el-table-column>
      <el-table-column label="名称" prop="goodsName" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column label="规格型号" prop="goodsGg" width="200" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column label="批号" width="100" prop="batchCode" align="center"></el-table-column>
      <el-table-column label="单位" width="35" prop="unit" align="center"></el-table-column>
      <!--      <el-table-column label="金额" width="70" align="center">
              <template slot-scope="scope">
                {{ (scope.row.qty * scope.row.inPrice).toFixed(2) || 0 }}
              </template>
            </el-table-column>-->
      <el-table-column label="包装规格" width="60" prop="packetCode" align="center"></el-table-column>
      <el-table-column label="生产厂家" prop="mfrsName" show-overflow-tooltip align="center"></el-table-column>
    </el-table>

    <span>待{{ outType }}商品批次信息</span>
    <el-table :data="multipleSelection" class="table-height-sub" height="outter" highlight-current-row border>
      <!--<el-table-column width="40">
        <template slot-scope="scope">
          <el-tooltip effect="light" content="删除">
            <el-button :disabled="scope.row.qty === 0" class="btn-circle" @click="delCacheRow(scope.row)" type="primary" icon="el-icon-delete" circle></el-button>
          </el-tooltip>
        </template>
      </el-table-column>-->
      <el-table-column type="index" label="序号" width="40" align="center"></el-table-column>
      <el-table-column :label="outType+'数量'" width="120" prop="qty">
      </el-table-column>
      //
      <el-table-column label="名称" min-width="120" prop="goodsName" show-overflow-tooltip></el-table-column>
      //
      <el-table-column label="规格" min-width="120" prop="goodsGg" show-overflow-tooltip></el-table-column>
      <el-table-column label="批号" width="120" prop="batchCode" show-overflow-tooltip></el-table-column>
      <el-table-column label="批次" width="200" prop="goodsBatchId" show-overflow-tooltip></el-table-column>
      <el-table-column label="单位" width="35" prop="unit"></el-table-column>
      <el-table-column label="单价" width="60" prop="inPrice"></el-table-column>
      <el-table-column label="金额" width="70">
        <template slot-scope="scope">
          {{ (scope.row.qty * scope.row.inPrice).toFixed(2) || 0 }}
        </template>
      </el-table-column>
      <!-- <el-table-column label="包装规格" width="60" prop="packetCode"></el-table-column>
       <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>-->
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
        <el-row :gutter="20"
                v-if="outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value">
          <el-col :span="8">
            <!-- <el-form-item label="收货地址" required prop="recAddress">
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
             </el-col>-->
            <el-col :span="6">
              <!--<deliveryAddress :dept-id="currentDeptId" :hos-id="user.corpId" @addressChange="setAddress" style="font-size:12px;line-height:25px;" btn_text="修改"></deliveryAddress>-->
            </el-col>
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="detailTable" :data="outStockBill.temp" highlight-current-row border>
        <el-table-column type="index" label="序号" width="45" align="center"></el-table-column>
        <el-table-column :label="outType+'数量'" prop="qty" width="60"></el-table-column>
        <el-table-column label="名称" prop="goodsName" show-overflow-tooltip></el-table-column>
        <el-table-column label="规格型号" prop="goodsGg" show-overflow-tooltip></el-table-column>
        <el-table-column label="批号" prop="batchCode" width="90"></el-table-column>
        <!-- <el-table-column label="注册证" width="160" prop="certificateCode" align="center">
         <template slot-scope="scope">
         {{ scope.row.certificateCode || '' }}
       </template>
         </el-table-column>-->
        <el-table-column label="单位" width="35" prop="unit"></el-table-column>
        <!--        <el-table-column label="金额" width="50">
                  <template slot-scope="scope">
                    {{ (scope.row.batchqty * scope.row.inPrice).toFixed(2) }}
                  </template>
                </el-table-column>-->
        <el-table-column label="包装规格" width="60" prop="packetCode"></el-table-column>
        <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <!--dlgVisible = false-->
        <el-button @click.native="closeDig">取消</el-button>
        <el-button :loading="confirmBtnLoading" @click="handleDlgConfirm()" type="primary">消耗出库</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import barcodeUtil from '@/common/js/barcodeUtil'
  import {BARCODEKIND, BARCOD_RES_TYPE} from "../../inStockMgr/common/constance";
  import {
    OUTSTOCK_BILL_STATUS,
    OUTSTOCK_BILL_KIND,
    OUTSTOCK_TYPE
  } from "../../../common/js/constance";
  import cloneDeep from "lodash/cloneDeep";

  export default {
    data() {
      return {
        barcodeType: true,
        barcode: '',
        mainBarcode: '',
        fBarcode: '',

        multipleSelection: [],
        currentStock: '',
        currentStockName: '',
        currentDeptName: '',
        currentDeptId: '',
        fillerName: '',
        ownStockList: [],
        packageGoodsList: [],
        outStockBill: {
          packageInfos: []
        },
        outStockType: 10,
        dlgVisible: false,
        confirmBtnLoading: false,
        CKLXDicts: {},
        supList: [],
        filter: "",
        searchBtnLoading: false,
      }
    },
    props: {
      basePkgUrl: {
        type: String,
        required: true
      },
      outStockKind: {
        type: String,
        required: true
      },
      outType: {
        type: String,
        required: true
      }
    },
    computed: {
      OUTSTOCK_TYPE() {
        return OUTSTOCK_TYPE;
      },
      OUTSTOCK_BILL_KIND() {
        return OUTSTOCK_BILL_KIND;
      },
      selectedGoodsKinds() {
        const kinds = new Set(this.outStockBill.packageInfos.map(o => o.goodsId));
        return kinds.size;
      },
      selectedGoodsQuantity() {
        return this.outStockBill.packageInfos.reduce((acc, cur) => acc + cur.qty, 0);
      },
      selectedGoodsAmount() {
        var sumPrice = Number.parseFloat(0, 2);
        this.outStockBill.packageInfos.forEach(res => {
          sumPrice =
            Number.parseFloat(sumPrice, 2) + Number.parseFloat(res.hosPackageBatchDetailLst.reduce((acc, cur) => acc + cur.qty * cur.inPrice, 0)
              .toFixed(2));
        })
        return sumPrice;
      }
    },
    methods: {
      closeDig() {
        this.dlgVisible = false;
        this.outStockBill.temp = [];
      },
      rowClick(row) {
        this.multipleSelection = row.hosPackageBatchDetailLst;
        this.multipleSelection.forEach(o => {
          this.$set(o, "goodsName", row.goodsName);
          this.$set(o, "batchCode", row.batchCode);
          this.$set(o, "goodsGg", row.goodsGg);
          this.$set(o, "unit", row.unit);
        })
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
            .get(this.basePkgUrl + "/getDict", {
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
        if (this.packageGoodsList.find((o => o.packageId === this.barcode))) {
          this.$message.error("不可重复扫码！")
          return;
        }
        var _this = this;
        if (this.barcode.indexOf('B') == 0 || this.barcode.indexOf('b') == 0) {
          this.parseCombinedBarcode(this.barcode, null, this.user.corpId, null).then(res => {
            _this.$refs.mbarcode.$el.children[0].focus();
            _this.$refs.mbarcode.$el.children[0].select();
            if (res.data.code == 0) {
              if (res.data.data != null && res.data.data.packageInfos.length > 0) {
                if (this.packageGoodsList.length > 0) {
                  var temppackageGoodsList = res.data.data.packageInfos;
                  temppackageGoodsList.forEach(ds => {
                    this.packageGoodsList.push(ds);
                  })
                } else {
                  this.packageGoodsList = res.data.data.packageInfos;
                }
              } else {
                _this.$message.error("无此定数包所对应的产品!");
              }
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

      parseCombinedBarcode: function (barcode, slaverBarcode, hosId, provId) {
        var param = {
          hosId: hosId,
          provId: provId,
          //定数包条码
          packageId: barcode,
        };
        return this.axios.post(this.basePkgUrl + "/parseBarcodeCombine", param);
      },

      checkGoods(table, ds) {
        if (table === "first") {

        } else {
          this.multipleSelection.forEach(res => {
            if (res.goodsId === ds.goodsId && res.goodsBatchId === ds.goodsBatchId
              && res.batchCode === ds.batchCode) {
              res.batchqty = res.batchqty + ds.batchqty;
            }
          })
        }
      },
      //goodsList
      newOutStockBill() {
        return {
          outStockKind: this.outStockKind,
          outStockType: this.outStockType,
          outDeptId: this.currentDeptId,
          fillDate: new Date().toLocaleDateString(),
          filler: this.user.ename,
          packageInfos: cloneDeep(this.packageGoodsList),
          temp: this.getTempPkg(cloneDeep(this.packageGoodsList))
        };
      },
      getTempPkg(val) {
        var temp = [];
        var comTemp = [];
        for (var i = 0; i < val.length; i++) {
          if (temp.length != 0) {
            var t = comTemp.find(res => {
              return res.goodsId === val[i].goodsId && res.batchCode === val[i].batchCode;
            })
            if (t) {
              var m = temp.find(res => {
                return res.goodsId === val[i].goodsId && res.batchCode === val[i].batchCode;
              })
              m.qty = m.qty + val[i].qty;
            } else {
              temp.push(val[i]);
              comTemp.push(val[i])
            }
          } else {
            temp.push(val[i]);
            comTemp.push(val[i])
          }
        }
        comTemp = [];
        return temp;
      },
      ngOnInit(val) {
        const sorted = this.groupBy(val, function (item) {
          return [item.goodsId];
        });
      },
      groupBy(array, f) {
        const groups = {};
        array.forEach(function (o) {
          const group = JSON.stringify(f(o));
          groups[group] = groups[group] || [];
          groups[group].push(o);
        });
        return Object.keys(groups).map(function (group) {
          return groups[group];
        });
      },
      handlePreviewBtnClick() {
        this.outStockBill = this.newOutStockBill();
        if (this.outStockBill.packageInfos.length === 0) {
          this.handleError("请选择商品");
          return;
        }
        this.dlgVisible = true;
      },
      handleStockChange() {

      },
      getStockList() {
        return this.axios
          .get(this.basePkgUrl + "/getStockList")
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
      getSupList() {
        return this.axios
          .get('/spdHERPService/stockMgr/out/returnOutSup/getSupList', {
            params: {
              stockid: this.currentStock
            },
          })
          .then(res => {
            if (res.data.code === 0) {
              // 判断查询结果是否为空
              if (res.data.data.length === 0) {
                return;
              }
              this.supList = res.data.data;
              //this.currentDeptId = this.supList[0].id;
            } else {
              this.handleError(res.data.msg);
            }
          })
          .catch(err => {
            this.handleError(err);
          });
      },
      addRowToCache(row) {
        if (this.multipleSelection.length > 0) {
          let item = this.multipleSelection.find(
            item => item.goodsId === row.goodsId && item.goodsBatchId === row.goodsBatchId && item.batchCode === row.batchCode
          );
          if (item) {
            item.batchqty += row.batchqty;
          } else {
            let item = cloneDeep(row);
            this.multipleSelection.push(item);
          }
        } else {
          let item = cloneDeep(row);
          this.multipleSelection.push(item);
        }
      },
      delCacheRow(row) {
        let index = this.packageGoodsList.findIndex(
          item => item.packageId === row.packageId
        );
        if (index >= 0) {
          this.packageGoodsList.splice(index, 1);
        }
        this.multipleSelection = [];
      },
      handleDlgConfirm() {
        this.confirmBtnLoading = true;
        this.axios
          .post(
            this.basePkgUrl + "/saveOutStockBill",
            this.outStockBillData2Post(true)
          )
          .then(res => {
            if (res.data.code === 0) {
              this.confirmBtnLoading = false;
              this.$message.success("出库记账成功");
              this.outStockBill.packageInfos.splice(0);
              this.multipleSelection.splice(0);
              this.packageGoodsList.splice(0);
              this.barcode = '';
              //this.handleSearc hBtn();
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

      outStockBillData2Post(submit) {
        let data = cloneDeep(this.outStockBill);
        delete data.fillDate;
        delete data.filler;

        if (submit) {
          data.status = OUTSTOCK_BILL_STATUS.SUBMIT.value;
        } else {
          data.status = OUTSTOCK_BILL_STATUS.SAVE.value;
        }
        data.outStocId = this.currentStock;
        /*if (this.currentDeptId) {
          data.inDeptId = this.currentDeptId;
          data.inDeptName = this.orgList.find(
            o => o.id === this.currentDeptId
          ).name;
        }*/
        return data;
      },
      getStockGoodsList() {
        return this.axios.get("/spdHERPService/stockMgr/out/returnOut/getStockGoodsList", {
          params: {
            stockId: this.currentStock,
            stocKind: this.outStockType,
            filter: this.filter
          }
        });
      },
      handleSearchBtn() {
        if (!this.filter) {
          return;
        }
        this.packageGoodsList.splice(0);
        this.searchBtnLoading = true;
        this.getStockGoodsList()
          .then(res => {
            if (res.data.code < 0) {
              this.handleError(res.data.msg);
            } else {
              this.rTotal = res.data.data.total;
              this.packageGoodsList = res.data.data.data;
              this.packageGoodsList.forEach(o => {
                this.$set(o, "batchId", "");
                this.$set(o, "batchNo", "");
                this.$set(o, "uniqueCode", "");
                this.$set(o, "selected", {id: ""});
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
      formatUnique(row, column, cellValue) {
        return cellValue == "1" ? "是" : "否";
      },
      handleError(err) {
        return new Promise((resolve, reject) => {
          this.$msgbox({title: "错误", type: "error", message: err})
            .then(action => {
              resolve(action);
            })
            .catch(err => {
              reject(err);
            });
        });
      },
    },
    mounted() {
      this.currentDeptName = this.user.orgName;
      this.fillerName = this.user.ename;
      this.currentDeptId = this.user.orgId;
      this.getStockList();
      this.getCKLXDict();
    }
  }
</script>

<style>
  #out-component.input-barcode {
    height: 24px;
  }

  .table-height-main{
    height: calc(60% - 40px);
  }

  .table-height-sub{
    height: calc(38% - 40px);
  }

  .full-height {
    height: 100%;
  }

  .stock-select {
    margin-bottom: 8px;
    width: 120px;
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
/*  .overflow{
    overflow-y: auto;
  }*/
</style>
