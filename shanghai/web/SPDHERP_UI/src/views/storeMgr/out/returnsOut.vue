<template>
  <div id="store-out-transferout" class="full-height">
    <el-row>
      <div class="inline-display" style="width:100%">
        <span class="inline-display">出库库房：</span>
        <el-select v-model="currentStock" placeholder="请选择房库" class="stock-select" @change="handleStockChange">
          <el-option v-for="item in ownStockList" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select>
        <span class="inline-display" style="margin-left:10px;"> 目标科室：</span>
        <el-select v-model="currentDeptId" placeholder="请选择部门" class="stock-select" @change="handleDeptChange">
          <el-option v-for="item in orgList" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select>
        <el-switch v-model="outStockType" inactive-color="#13ce66" :active-value="OUTSTOCK_TYPE.PURCHASE.value" :inactive-value="OUTSTOCK_TYPE.PREORDER.value" :active-text="OUTSTOCK_TYPE.PURCHASE.text" :inactive-text="OUTSTOCK_TYPE.PREORDER.text">
        </el-switch>
        <el-button :loading="searchBtnLoading" icon="search" style="margin-left:10px;" @click="handleSearchBtn">搜索库存</el-button>
        <div class="inline-display" style="margin-left:30px;">
          <label @click="handleLabelClick">副码：</label>
          <el-checkbox v-model="hasSlaverBarcode" :disabled="stockGoodsList.length===0"></el-checkbox>
        </div>
        <div class="inline-display input-barcode-small" :class="[hasSlaverBarcode?'slaver-barcode-input':'barcode-input']">
          <el-input v-model="masterBarcode" placeholder="请录入条码主码" ref="masterBarcode" @keyup.native.enter="handleMasterBarcodeEnter" :disabled="stockGoodsList.length===0">
          </el-input>
        </div>
        <div class="inline-display input-barcode-small" v-if="hasSlaverBarcode" :class="[hasSlaverBarcode?'slaver-barcode-input':'']">
          <el-input v-model="slaverBarcode" placeholder="请录入条码副码" ref="slaverBarcode" @keyup.native.enter="handleSlaverBarcodeEnter" :disabled="stockGoodsList.length===0">
          </el-input>
        </div>
        <div class="inline-display">
          <el-button type="info" :disabled="multipleSelection.length===0" @click="handlePreviewBtnClick">
            预览出库单
          </el-button>
        </div>
      </div>
    </el-row>

    <el-table :data="stockGoodsList" highlight-current-row ref="stockTable" border row-key="goodsId" @selection-change="handleSelectionChange" height="outter" class="table-height">
      <el-table-column type="selection" width="35" :reserve-selection="false" :selectable="checkSelectable" fixed></el-table-column>
      <el-table-column type="index" label="序号" width="35" align="center" fixed></el-table-column>
      <el-table-column type="index" label="操作" width="35" align="center" fixed>
        <template slot-scope="scope">
          <el-tooltip effect="dark" content="复制当前行" placement="top">
            <el-button type="text" @click="handleClkCopyLine(scope.row)" :disabled="scope.row.avlQty === 0">
              <i class="fa fa-files-o" aria-hidden="true"></i>
            </el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="出库数量" fixed width="120">
        <template slot-scope="scope">
          <el-input-number style="width:100%;" v-model="scope.row.qty" :min="0" :max="scope.row.isUnique !== '3' ? 1 : scope.row.avlQty" :disabled="scope.row.avlQty === 0 || scope.row.isUnique !== '3'" @change="handleInputNumberChange(scope.row)"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="可发" width="35" prop="avlQty">
      </el-table-column>
      <el-table-column label="名称" prop="goodsName" show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="规格型号" prop="goodsGg" width="100" show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="唯一码管理" width="75" prop="isUnique" :formatter="formatUnique">
      </el-table-column>
      <el-table-column label="批号&批次&唯一码" width="300">
        <template slot-scope="scope">
          <el-select :ref="'selector-'+scope.row.bsId" v-model="scope.row.selected.id" @change="changeBatchId(scope.row)" clearable style="width:100%">
            <el-option v-for="item in batchNoIdUniqueCodeList[scope.row.goodsId]" :key="item.id" :label="item.label" :value="item.id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="单位" width="35" prop="unit">
      </el-table-column>
      <el-table-column label="单价" width="40" prop="price">
      </el-table-column>
      <el-table-column label="金额" width="50">
        <template slot-scope="scope">
          {{ scope.row.qty * scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column label="包装规格" width="60" prop="pkg">
      </el-table-column>
      <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip>
      </el-table-column>
    </el-table>

    <el-dialog title="预览出库单" :visible.sync="dlgVisible">
      <el-form :model="outStockBill" label-width="70px" ref="editForm" class="el-form-item-nomsg">
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
                <el-option v-for="item in orgList" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">

          </el-col>
        </el-row>
        <el-row :gutter="20">
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
          <el-col :span="8">
            <div class="text-style">
              种类：{{ selectedGoodsKinds }}，总数量：{{ selectedGoodsQuantity }}，总金额：{{ selectedGoodsAmount }}
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
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
            <el-col :span="6">
              <deliveryAddress style="font-size:12px;line-height:25px;" btn_text="修改" @addressChange="setAddress" :hos-id="user.corpId" :dept-id="user.orgId"></deliveryAddress>
            </el-col>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="outStockBill.goodsList" highlight-current-row ref="detailTable" border>
        <el-table-column type="index" label="序号" width="35" align="center"></el-table-column>
        <el-table-column label="出库数量" prop="qty" width="60"></el-table-column>
        <el-table-column label="名称" prop="goodsName" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="规格型号" prop="goodsGg" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="唯一码管理" prop="isUnique" :formatter="formatUnique">
        </el-table-column>
        <el-table-column label="批号" prop="batchNo">
        </el-table-column>
        <el-table-column label="注册证" prop="certificateCode" align="center">
          <template slot-scope="scope">
            <el-input placeholder="请输入注册证号" v-model="scope.row.certificateCode" size="mini">
            </el-input>
          </template>
        </el-table-column>
        <el-table-column label="批次" prop="batchId">
        </el-table-column>
        <el-table-column label="唯一码" prop="uniqueCode">
        </el-table-column>
        <el-table-column label="单位" width="35" prop="unit">
        </el-table-column>
        <el-table-column label="单价" width="40" prop="price">
        </el-table-column>
        <el-table-column label="金额" width="50">
          <template slot-scope="scope">
            {{ scope.row.qty * scope.row.price }}
          </template>
        </el-table-column>
        <el-table-column label="包装规格" width="60" prop="pkg">
        </el-table-column>
        <el-table-column label="生产厂家" width="80" prop="mfrsName" show-overflow-tooltip>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dlgVisible = false">取消</el-button>
        <el-button type="primary" @click="handleDlgConfirm()" :loading="confirmBtnLoading">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// TODO: 部门请领关系
import deliveryAddress from "../../../components/deliveryAddress.vue";
import {
  OUTSTOCK_BILL_STATUS,
  OUTSTOCK_BILL_KIND,
  OUTSTOCK_TYPE
} from "../../../common/js/constance";

export default {
  components: {
    deliveryAddress
  },
  data() {
    return {
      baseURL: "/spdHERPService/stockMgr/out/transferOut",
      BARCODE_PARSE_URL:
        "/spdHERPService/baseData/baseDataMaint/BarcodeParse/parseBarcodeHERP",
      ownStockList: [],
      currentStock: "",
      currentDeptId: "",
      searchBtnLoading: false,
      confirmBtnLoading: false,

      stockGoodsList: [],
      multipleSelection: [],
      batchNoIdUniqueCodeList: [],
      uniqueCodeList: [],
      orgList: [],

      hasSlaverBarcode: false,
      masterBarcode: "",
      slaverBarcode: "",
      parseBarcodeResultSet: "",

      dlgVisible: false,
      outStockBill: {
        goodsList: []
      },
      CKLXDicts: {},

      outStockType: 10
    };
  },
  computed: {
    OUTSTOCK_TYPE() {
      return OUTSTOCK_TYPE;
    },
    selectedGoodsKinds() {
      const kinds = new Set(this.outStockBill.goodsList.map(o => o.goodsId));
      return kinds.size;
    },
    selectedGoodsQuantity() {
      return this.outStockBill.goodsList.reduce((acc, cur) => acc + cur.qty, 0);
    },
    selectedGoodsAmount() {
      return this.outStockBill.goodsList.reduce(
        (acc, cur) => acc + cur.qty * cur.price,
        0
      );
    }
  },
  mounted() {
    this.getStockList()
      .then(result => {
        this.ownStockList = result;
        if (result && result.length !== 0) {
          this.currentStock = result[0].id;
        } else {
          return;
        }
        this.getOrgList().then(_ => {
          this.handleSearchBtn();
        });
        this.getCKLXDict();
      })
      .catch(err => {
        this.handleError(err);
      });
  },
  methods: {
    handleSearchBtn() {
      this.stockGoodsList.splice(0);
      this.searchBtnLoading = true;
      this.getStockGoodsList()
        .then(res => {
          this.stockGoodsList = res;
          this.stockGoodsList.forEach(o => {
            this.$set(o, "qty", 0);
            this.$set(o, "batchId", "");
            this.$set(o, "batchNo", "");
            this.$set(o, "uniqueCode", "");
            this.$set(o, "selected", { id: "" });
          });
          this.getUniqueCodeList();
          this.searchBtnLoading = false;
        })
        .catch(err => {
          this.searchBtnLoading = false;
          this.handleError(err);
        });
    },
    handleStockChange(val) {
      this.stockGoodsList.splice(0);
    },
    handleDeptChange(val) {
      this.stockGoodsList.splice(0);
    },
    handleLabelClick() {
      if (this.stockGoodsList.length !== 0) {
        this.hasSlaverBarcode = !this.hasSlaverBarcode;
      }
    },
    handleMasterBarcodeEnter() {
      if (this.hasSlaverBarcode) {
        this.$refs["slaverBarcode"].$el.children[0].focus();
      } else {
        this.handleSlaverBarcodeEnter();
      }
    },
    handleSlaverBarcodeEnter() {
      if (this.masterBarcode === "" && this.slaverBarcode === "") {
        return;
      }
      this.getBarcodeParseResult()
        .then(res => {
          this.parseBarcodeResultSet = new Set(
            res.map(item => {
              return {
                goodsId: item.goodsId,
                batchNo: item.batchNo,
                trackNo: item.trackNo
              };
            })
          );
          this.changeRequestGoodsQty(this.parseBarcodeResultSet);
          this.masterBarcode = "";
          this.slaverBarcode = "";
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    changeRequestGoodsQty(data) {
      let sum1 = 0;
      let sum2 = 0;
      let sum3 = 0;
      let rowAdded = false;
      for (let item of data) {
        if (rowAdded) {
          break;
        }
        const rows = this.stockGoodsList.filter(
          o => o.goodsId === item.goodsId
        );
        if (item.trackNo) {
          // 条码解析有跟踪码/唯一码
          for (let row of rows) {
            sum1 += row.qty;
            if (sum1 >= row.avlQty) {
              // 商品总出库数量大于可发数量
              this.handleError(
                '"' + row.goodsName + '"' + " 总出库数超过库存数量"
              );
              return;
            }
            if (row.qty === 0) {
              row.qty = 1;
              row.selected = this.batchNoIdUniqueCodeList[row.goodsId].find(
                o => o.uniqueCode === item.trackNo
              );
              rowAdded = true;
              this.$refs["stockTable"].toggleRowSelection(row, true);
              break;
            } else if (row.qty === 1) {
              if (
                rows.filter(o => o.bsId === row.bsId).some(o => o.qty === 0)
              ) {
                continue;
              }
              let copiedRow = this.handleClkCopyLine(row);
              copiedRow.selected = JSON.parse(
                JSON.stringify(
                  this.batchNoIdUniqueCodeList[copiedRow.goodsId].find(
                    o => o.uniqueCode === item.trackNo
                  )
                )
              );
              copiedRow.qty = 1;
              rowAdded = true;
              this.$refs["stockTable"].toggleRowSelection(copiedRow, true);
              break;
            } else {
              this.handleError("出库数量不能大于1");
              return;
            }
          }
        } else if (item.batchNo) {
          // 条码解析有批号
          let rowsWithBatchNo = rows.filter(o => o.batchNo === item.batchNo); // 表中已有带此批号的行
          if (rowsWithBatchNo.length > 0) {
            for (let row of rowsWithBatchNo) {
              sum2 += row.qty;
              if (sum2 >= row.avlQty) {
                this.handleError(
                  '"' + row.goodsName + '"' + " 总出库数超过库存数量"
                );
                return;
              }
              if (row.qty >= row.selected.qty) {
                if (
                  rowsWithBatchNo
                    .filter(o => o.bsId === row.bsId)
                    .some(o => o.qty < o.selected.qty)
                ) {
                  continue;
                }
                let copiedRow = this.handleClkCopyLine(row);
                copiedRow.selected = JSON.parse(
                  JSON.stringify(
                    this.batchNoIdUniqueCodeList[copiedRow.goodsId][0]
                  )
                );
                copiedRow.qty += 1;
                rowAdded = true;
                this.$refs["stockTable"].toggleRowSelection(copiedRow, true);
                break;
              }
              row.qty += 1;
              rowAdded = true;
              this.$refs["stockTable"].toggleRowSelection(row, true);
              break;
            }
          } else {
            for (let row of rows) {
              sum2 += row.qty;
              if (sum2 >= row.avlQty) {
                this.handleError(
                  '"' + row.goodsName + '"' + " 总出库数超过库存数量"
                );
                return;
              }
              row.qty += 1;
              row.selected = JSON.parse(
                JSON.stringify(this.batchNoIdUniqueCodeList[row.goodsId][0])
              );
              rowAdded = true;
              this.$refs["stockTable"].toggleRowSelection(row, true);
              break;
            }
          }
        } else {
          // 条码解板无批号，无跟踪码
          for (let row of rows) {
            sum3 += row.qty;
            if (sum3 >= row.avlQty) {
              this.handleError(
                '"' + row.goodsName + '"' + " 总出库数超过库存数量"
              );
              return;
            }
            row.qty += 1;
            rowAdded = true;
            this.$refs["stockTable"].toggleRowSelection(row, true);
            break;
          }
        }
      }
      if (!rowAdded) {
        this.$message.error("未找到条码对应商品。");
      }
    },
    handlePreviewBtnClick() {
      this.outStockBill = this.newOutStockBill();
      this.combineDuplicateGoods();
      //判断领用部门是否为''
      if (this.outStockBill.outDeptId.length === 0) {
        this.handleError("请选择目标科室");
        return;
      }
      if (this.checkGoodsQtyWrong(this.outStockBill.goodsList)) {
        this.handleError(
          "所选商品数量总和大于库存发货数量，或同一请购商品数量大于待发货数量。"
        );
        return;
      }
      if (
        this.outStockBill.goodsList.some(
          o => !o.selected || o.selected.id === ""
        )
      ) {
        this.handleError("所选商品未选择批号/批次/唯一码。");
        return;
      }
      if (
        this.outStockBill.goodsList.some(
          o => o.qty > Math.min(o.avlQty, o.selected.qty, o.undoneQty)
        )
      ) {
        this.handleError("所选商品数量错误。");
        return;
      }
      if (this.outStockBill.goodsList.length === 0) {
        this.handleError("请选择商品");
        return;
      }
      this.dlgVisible = true;
    },
    handleDlgConfirm() {
      // 验证商品
      if (
        !this.outStockBill.recAddressId ||
        !this.outStockBill.recLinkman ||
        !this.outStockBill.recLinkmanPhone ||
        !this.outStockBill.recAddress
      ) {
        this.handleError("请选择收货地址");
        return;
      }
      this.confirmBtnLoading = true;
      this.axios
        .post(
          this.baseURL + "/saveOutStockBill",
          this.outStockBillData2Post(true)
        )
        .then(res => {
          if (res.data.code === 0) {
            this.$message.success("保存出库单成功");
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
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    checkSelectable(row, index) {
      return row.qty !== 0;
    },
    handleClkCopyLine(row) {
      let index = this.stockGoodsList.findIndex(o => o.bsId === row.bsId);
      let item = JSON.parse(JSON.stringify(row));
      item.qty = 0;
      item.batchId = "";
      item.batchNo = "";
      item.uniqueCode = "";
      item.selected = { id: "" };
      this.stockGoodsList.splice(index + 1, 0, item);
      return this.stockGoodsList[index + 1];
    },
    handleInputNumberChange(row) {
      this.$nextTick(() => {
        if (row.qty > 0) {
          row.selected = this.batchNoIdUniqueCodeList[row.goodsId][0];
          this.$refs["stockTable"].toggleRowSelection(row, true);
        } else {
          row.selected = { id: "" };
          this.$refs["stockTable"].toggleRowSelection(row, false);
        }
      });
    },
    setAddress(d) {
      this.outStockBill.recAddressId = d.id;
      this.outStockBill.recLinkman = d.lxr;
      this.outStockBill.recLinkmanPhone = d.lxrPhone;
      this.outStockBill.recAddress = (
        "" +
        d.province +
        d.city +
        d.area +
        d.address
      ).replace(/null/g, "");
    },
    changeBatchId(row) {
      if (row.selected.id === "") {
        return;
      }
      row.selected = JSON.parse(
        JSON.stringify(
          this.batchNoIdUniqueCodeList[row.goodsId].find(
            o => o.id === row.selected.id
          )
        )
      );
      row.batchId = row.selected.batchId;
      row.batchNo = row.selected.batchNo;
      row.uniqueCode = row.selected.uniqueCode;
    },
    formatUnique(row, column, cellValue) {
      return cellValue !== "3" ? "是" : "否";
    },
    getStockGoodsList() {
      return new Promise((resolve, reject) => {
        this.axios
          .get(this.baseURL + "/getStockGoodsList", {
            params: {
              stockId: this.currentStock,
              stocKind: this.outStockType
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
    getUniqueCodeList() {
      let distinctGoodsId = new Set(
        this.stockGoodsList.map(item => item.goodsId)
      );
      if (distinctGoodsId.size > 0) {
        this.getUniqueCode(distinctGoodsId)
          .then(res => {
            this.uniqueCodeList = JSON.parse(JSON.stringify(res));
            for (let goodsId of distinctGoodsId) {
              let goodsList = res.filter(o => o.goodsId === goodsId);
              this.$set(this.batchNoIdUniqueCodeList, goodsId, [...goodsList]);
              for (let item of this.batchNoIdUniqueCodeList[goodsId]) {
                item.label = "ID：" + item.id;
                if (item.batchNo) {
                  item.label += "，" + item.batchNo + "|" + item.batchId;
                } else {
                  item.label += "，" + item.batchId;
                }
                if (item.uniqueCode) {
                  item.label += "|" + item.uniqueCode;
                }
                item.label += ", 数量:" + item.qty;
              }
              this.batchNoIdUniqueCodeList[goodsId].sort(
                (a, b) => Number(a.id) - Number(b.id)
              );
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
        goodsId
      };
      return new Promise((resolve, reject) => {
        this.axios
          .post(this.baseURL + "/getUniqueCodeList", params)
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
    getOrgList() {
      return this.axios
        .get(this.baseURL + "/getOrgList")
        .then(res => {
          if (res.data.code === 0) {
            //判断查询结果是否为空
            if (res.data.data.length === 0) {
              return;
            }
            this.orgList = res.data.data;
            this.currentDeptId = this.orgList[0].id;
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
        masterBarcode: this.masterBarcode,
        slaverBarcode: this.slaverBarcode
      };
      return new Promise((resolve, reject) => {
        this.axios.get(this.BARCODE_PARSE_URL, { params }).then(res => {
          if (res.data.code === 0) {
            resolve(res.data.data);
          } else {
            reject(res.data.msg);
          }
        });
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
          .get(this.baseURL + "/getDict", {
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
    newOutStockBill() {
      return {
        outStockKind: OUTSTOCK_BILL_KIND.RETURNS.value,
        outStockType: this.outStockType,
        outDeptId: this.currentDeptId,
        fillDate: new Date().toLocaleDateString(),
        filler: this.user.ename,
        recAddressId: this.outStockBill.recAddressId,
        recAddress: this.outStockBill.recAddress,
        recLinkman: this.outStockBill.recLinkman,
        recLinkmanPhone: this.outStockBill.recLinkmanPhone,
        goodsList: JSON.parse(JSON.stringify(this.multipleSelection))
      };
    },
    outStockBillData2Post(submit) {
      let data = JSON.parse(JSON.stringify(this.outStockBill));
      for (let goods of data.goodsList) {
        goods.stockTableId = goods.selected.id;
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
      data.inDeptId = this.currentDeptId;
      data.inDeptName = this.orgList.find(
        o => o.id === this.currentDeptId
      ).name;
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
          let sumQty = this.outStockBill.goodsList
            .filter(item => item.selected.id === goodsLabel)
            .reduce((acc, cur) => acc + cur.qty, 0);
          let goodsInfo = this.outStockBill.goodsList.find(
            item => item.selected.id === goodsLabel
          );
          goodsInfo.qty = sumQty;
          goodsList.push(JSON.parse(JSON.stringify(goodsInfo)));
        }
        this.outStockBill.goodsList = goodsList;
        return true;
      } else {
        return false;
      }
    },
    checkGoodsQtyWrong(data) {
      const kinds = new Set(data.map(item => item.goodsId));
      for (let item of kinds) {
        let goods = data.filter(o => o.goodsId === item);
        const sum = goods.reduce((acc, cur) => acc + cur.qty, 0);
        if (sum > goods[0].avlQty) {
          return true;
        }
      }
      return false;
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

<style scoped>
.full-height {
  height: 100%;
}

.stock-select {
  margin-bottom: 8px;
  width: 120px;
}

.table-height {
  height: calc(100% - 45px);
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
</style>

<style>
#store-out-transferout .el-dialog--small {
  width: 70%;
}
</style>
