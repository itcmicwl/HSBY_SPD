<template>
    <el-row id="in-by-dept-apply-edit-sm" v-loading="diaLoging" style="height:100%">
        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="120px">
                <el-col :span="8">
                    <el-form-item label="出库单号:">
                        <span>{{ inStockBill.outBillId }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="医院：">
                        <span>{{ inStockBill.outOrgName }}</span>
                    </el-form-item>
                </el-col>
                <!-- <el-col :span="8">
                    <el-form-item label="入库机构:">
                        <span>{{ inStockBill.inOrgName }}</span>
                    </el-form-item>
                </el-col> -->
                <el-col :span="8">
                   <el-form-item label="入库库房:">
                    <orgStock  v-model="inStockBill.inStocId" :hosId="this.user.corpId" :userId="this.user.userId"
                    :deptId="this.user.orgId" @sltChanged="stockChange"></orgStock>
                   </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="入库部门:">
                        <span>{{ inStockBill.inDeptName }}</span>
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
              <el-col :span="8" v-if="inStockBill.remark!=null">
                <el-form-item label="备注:">
                  <span>{{ inStockBill.remark }}</span>
                </el-form-item>
              </el-col>
            </el-form>
        </el-row>
        <barcodeResolver :barcodeKind="barcodeKind.QGRK" :hosId="user.corpId"
        :deptId="user.orgId" @resolved="onBarcodeResolved"></barcodeResolver>
        <el-table ref="receListTable" :data="inStockBill.lstDetail" style="width: 100%;" fit height="outer" class="mytable" stripe border @selection-change="sltRowsChange" row-key="inBillRow">
            <el-table-column type="selection" :selectable="checkSelectable" disabled='true' width="30">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <div class="icoBtn">
                        <i class="fa fa-trash-o fa-lg" @click="delRow(scope.row)" title="删除" style="color:red;margin-left:5px;"></i>
                        <i class="fa fa-barcode fa-lg" @click="editBarcode(scope.row)" title="编辑条码" v-if="scope.row.uniqueKind!=3"></i>
                        <i class="fa fa-file-image-o fa-lg" @click="showGoodsImg(scope.row)" v-if="scope.row.imgAvailable"></i>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="序号" prop="inBillRow" width="60">
            </el-table-column>
            <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="generalName" min-idth="30" label="商品俗称"  width="100" show-overflow-tooltip header-align="center" >
            </el-table-column>
            <el-table-column label="出库数量" prop="outQty" width="90">
            </el-table-column>
            <el-table-column label="未入库数量" prop="unrecieveQty" width="90">
            </el-table-column>
            <el-table-column label="出库包数" prop="outPacketQty" width="90" v-if="inStockBill.inStockType =='10'">
							<template slot-scope="scope">
								<div v-if="scope.row.isPacket == '1'">{{scope.row.outPacketQty}}
								</div>
							</template>
            </el-table-column>
            <el-table-column label="入库数量" width="90">
                <template slot-scope="scope">
                    <!-- <el-input-number placeholder="请输入数量" v-model="scope.row.inQty" size="mini" :min="0" :max="scope.row.unrecieveQty" class="col-input-num" :disabled="inStockBill.inStockType =='20'"  @change="inQtyChange(scope.row)"> -->
											<el-input-number placeholder="请输入数量" v-model="scope.row.inQty" size="mini" :min="0" :max="scope.row.unrecieveQty" class="col-input-num" :disabled="!(inStockBill.isPacket =='1' || (inStockBill.isPacket =='0' && inStockBill.uniqueKind !='3'))"  @change="inQtyChange(scope.row)">
                    </el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="入库包数" width="90" v-if="inStockBill.inStockType =='10'">
                <template slot-scope="scope">
                    <!-- <el-input-number placeholder="请输入数量" v-model="scope.row.intPacketQty" size="mini" :min="0" :disabled = "scope.row.intPacketQty>=scope.row.outPacketQty||scope.row.isPacket==0" class="col-input-num"  @change="inQtyChange(scope.row)"> -->
										<div v-if="scope.row.isPacket == '1'">
												<el-input-number v-model="scope.row.intPacketQty" size="mini" :min="0" :disabled = true class="col-input-num"  @change="inQtyChange(scope.row)">
												</el-input-number>
										</div>
                </template>
            </el-table-column>
            <el-table-column label="单位" prop="unit">
            </el-table-column>
            <el-table-column label="入库货位" width="90">
                <template slot-scope="scope">
                   <el-select  v-model="scope.row.shelfId"  filterable placeholder="请输入关键词">
                      <el-option  v-for="item in scope.row.shelfLst"  :key="item.shelfCode"  :label="item.shelfCode"  :value="item.shelfCode">
                      </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column label="批号" prop="batchCode" width="110">
            </el-table-column>
            <el-table-column label="灭菌批号" prop="sterilizationCode">
            </el-table-column>
            <el-table-column label="灭菌有效日期" width="90">
                <template slot-scope="scope">
                    {{scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>
            <el-table-column label="灭菌日期" width="90">
                <template slot-scope="scope">
                    {{scope.row.sterilizationDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>
            <el-table-column label="有效期" width="90">
                <template slot-scope="scope">
                    {{scope.row.expdtEndDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>

            <el-table-column label="产地" prop="made" width="90">
            </el-table-column>
						<el-table-column label="是否定数包" prop="isPacket" width="90">
							<template slot-scope="scope">
								<div v-if="scope.row.isPacket == '1'">是</div>
								<div v-else>否</div>
							</template>
            </el-table-column>
						<el-table-column label="包规格" prop="packetCode" width="90">
            </el-table-column>
						<el-table-column label="是否唯一码" prop="uniqueKind" width="90">
							<template slot-scope="scope">
								<div v-if="scope.row.uniqueKind != 3">是</div>
								<div v-else>否</div>
							</template>
            </el-table-column>

            <el-table-column prop="goodsDesc" min-idth="30" label="商品描述" header-align="center" >
            </el-table-column>
        </el-table>
         <el-col :span="20" class="toolbar" style="padding-bottom: 0px;text-align: center;">
            <el-button   @click.native="cancel">取消</el-button>
            <!-- <el-button type="primary" :disabled="sltRows.length ==0" @click.native="submitPurconfirm(10)" >保存</el-button> -->
            <el-button type="primary" :disabled="sltRows.length ==0" @click.native="submitPurconfirm(20)" >提交</el-button>
        </el-col>
        <el-dialog title="条码设置" :visible.sync="barcodeV" :close-on-click-modal="false" >
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="70px" class="el-form-item-nomsg" label-position="left">
                    <el-col :span="7">
                        <el-form-item label="商品名称:">
                            <el-input v-model="UniqueCode.goodsName" :disabled="true" placeholder="商品名称" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <el-form-item label="唯一码:">
                            <el-input class="input-barcode-small" ref="sbarcode" :disabled="true" v-model="UniqueCode.uniqueCode" placeholder="请录入唯一码"   @keyup.native.enter="addBarCode"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item>
                            <el-button type="primary" @click.native="addBarCode"  :disabled="true">添加</el-button>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-tag type="primary" v-for="item in lstUniqueCode" :key="item.id" :closable="false"   :title="item.batchCode" @close="delBarCode(item)">
                    <i class="fa fa-barcode"></i>
                    {{item.uniqueCode}}
                </el-tag>
            </el-col>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="barcodeV = false" >关闭</el-button>
            </div>
        </el-dialog>
        <el-dialog title="商品照片" :visible.sync="dlgGoodsImgVisible" v-loading="dlgGoodsImgLoading">
        <el-row>
          <el-col class="pic-border" :span="8" v-for="(url, index) in goodsImgs.origUrls" :key="index">
            <el-tooltip placement="right" effect="light" :open-delay="300">
              <div slot="content">
                <img class="tooltip-pic" src="" :orig-src="goodsImgs.origUrls[index]" :ref="'img'+index">
              </div>
              <img :src="goodsImgs.tUrls[index]" class="pic" @mouseenter="imgMouseEnter($event, index)">
            </el-tooltip>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="dlgGoodsImgVisible = false">关闭</el-button>
        </div>
      </el-dialog>
    </el-row>
</template>
<script>
import moment from "moment";
import barcodeResolver from "./common/barcodeResolver";
import {
  DISTRBILL_STATUS,
	BARCODEKIND,
	BARCOD_RES_TYPE
} from "./common/constance";
import orgStock from "../../components/orgStock";
import barcodeUtil from "../../common/js/barcodeUtil";
export default {
  data() {
    return {
      dlgGoodsImgVisible: false,
      barcodeKind: BARCODEKIND,
      goodsImgs: {
        id: "",
        hosGoodsId: "",
        origUrls: Array(6).fill(""),
        tUrls: Array(6).fill("")
      },
      dlgGoodsImgLoading: false,
      picURLPrefix: "/upload/",

      opModel: "ADD",
      diaLoging: false,
      barcodeV: false,
      loading: false,
      lstOutsubVo: [],
      distrBill: {
        id: "",
        billId: "",
        spdCode: "",
        purchaseCompanyId: "",
        purchaseCompanyCode: "",
        purchaseCompanyName: "",
        purchaseCompanyErpCode: "",
        applyOrgId: "",
        applyOrgCode: "",
        applyOrgName: "",
        applyOrgScmCode: "",
        applyOrgErpCode: "",
        recieveOrgId: "",
        recieveOrgCode: "",
        recieveOrgName: "",
        recieveOrgScmCode: "",
        recieveOrgErpCode: "",
        provId: "",
        provCode: "",
        provName: "",
        provErpCode: "",
        filler: "",
        remark: "",
        sumRow: "",
        status: "",
        fillDate: "",
        version: "",
        lstOutsubVo: []
      },
      inStockBill: {
        id: "",
        inStocId: null,
        inStocCode: null,
        billId: "",
        inStockKind: 2, // 请领入库
        outBillId: "",
        outOrgId: "",
        outOrgName: "",
        inOrgId: "",
        inOrgName: "",
        inDeptId: "",
        inDeptName: "",
        sendBillid: "",
        filler: "",
        fillterName: "",
        fillDate: "",
        // out_dept_id: '',
        // outDeptName: '',
        // auditor: '',
        // auditDate: '',
        // accounter: '',
        // accountDate: '',
				status: 0,
				barcodeList: [],
        lstDetail: []
      },
			inStockDetail: {},
      constInStockDetail: {
        id: "",
        pid: "",
        billId: "",
        outBillRow: 0,
        inBillRow: 0,
        provId: "",
        provCode: "",
        provName: "",
        goodsId: "",
        goodsName: "",
        goodsGg: "",
        mfrsId: "",
        mfrsName: "",
        made: "",
        packetCode: "",
        isPacket: "",
        uniqueKind: "",
        batchCode: "",
        sterilizationDate: "",
        sterilizationCode: "",
        sterilizationEndDate: "",
        shelfId:"",
        expdtEndDate: "",
        unit: "",
        outQty: 0,
        outPacketQty: "", // 出库包数
				usedQty: 0,
        inQty: "", // 入库数量
        intPacketQty: "",
        inPrice: 0, // 入库单价
        masterCode: "", // 主码
        secCode: "", // 副码
        hibcCode: "", // hibc
        version: 0,
        lstBatch: [],
        lstUniqueCode: []
      },
      sltRows: [],
      Batch: {
        id: "",
        pid: "",
        billId: "",
        pRowId: "",
        provId: "",
        goodsId: "",
        goodsBatchId: "",
        inPrice: "",
        inTime: "",
        qty: "",
        version: 0
      },
      UniqueCode: {
        id: "",
        pid: "",
        billId: "",
        goodsName: "",
        pRowId: 0,
        provId: "",
        goodsId: "",
        goodsBatchId: "",
        inPrice: "",
        inTime: "",
        uniqueCode: "",
        version: 0
      },
      lstUniqueCode: [],
      lstBatch: [],
      barcode: "",
      mainBarcode: "",
      fBarcode: "",
      barcodeType: true,
      barcodeMatchV: false,
      wymArray: [],
      ssbArray: [],
      barcodeMatch: {
        provId: "",
        hosId: "",
        mfrsCode: ""
      }
    };
  },
  components: { orgStock, barcodeResolver },
  filters: {
    fStatus: function(value) {
      if (value) {
        return DISTRBILL_STATUS[value].text;
      } else {
        return "";
      }
    }
  },
  methods: {
    getRecBill(recId) {
      var _this = this;
      this.axios
        .get("/spdHERPService/stockMgr/inStock/getById?id=" + recId)
        .then(res => {
          if (res.data.code == 0) {
            _this.inStockBill = res.data.data;
            for (var i = 0; i < _this.inStockBill.lstDetail.length; i++) {
              _this.inQtyChange(_this.inStockBill.lstDetail[i]);
            }
          }
        });
		},
		checkSelectable(row, index){
			if(row.unrecieveQty > 0){
				return 1;
			} else {
				return 0;
			}
		},
    convertDistrToRecBill(distBill) {
      // outStockBill
      if (distBill) {
        this.inStockBill.inStockType = distBill.outStockType;
        this.inStockBill.id = "";
        this.inStockBill.billId = "";
        this.inStockBill.outBillId = distBill.billId;
        this.inStockBill.inStockKind = 2;
        this.inStockBill.outOrgId = distBill.outOrgId; // 医院
        this.inStockBill.outOrgName = distBill.outOrgName; // 医院
        this.inStockBill.inOrgId = distBill.inOrgId;

        this.inStockBill.inOrgName = distBill.outOrgName;
        this
        this.inStockBill.inDeptId = distBill.inDeptId;
        this.inStockBill.inDeptName = distBill.inDeptName;
        this.inStockBill.outDeptId = distBill.outDeptId;
        this.inStockBill.outDeptName = distBill.outDeptName;
        this.inStockBill.filler = this.user.userId;
        this.inStockBill.fillterName = this.user.ename;
        this.inStockBill.fillDate = new Date(); // moment(new Date()).format("YYYY-MM-DD");
        // this.inStockBill.sendBillid = distBill.billId;
        this.inStockBill.status = 0;
        this.inStockBill.remark=distBill.remark;
        this.inStockBill.sourceBillId = distBill.sourceBillId;
        this.inStockBill.lstDetail = [];
        if (
          this.distrBill.lstOutsubVo &&
          this.distrBill.lstOutsubVo.length > 0
        ) {
          this.convertDistrLstToRecBillLst(this.distrBill);
        }
      }
    },
    convertDistrLstToRecBillLst(distrBill) {
      var res = [];
      var _this =this;
      distrBill.lstOutsubVo.forEach(function(item, index) {
        var row = Object.assign({}, this.constRecBillDetail);
        row.id = "";
        row.pid = "";
        row.billId = "";
        row.inBillRow = index + 1;
        row.outBillId = distrBill.billId;
        row.outBillRow = item.outBillRow;
				row.usedQty = item.usedQty || 0;
        row.unrecieveQty = Number(item.outQty) - Number(item.usedQty);
        row.provId = item.provId;
        row.provCode = item.provCode;
        row.provName = item.provName;
        row.goodsId = item.goodsId;
        row.goodsName = item.goodsName;
        row.goodsGg = item.goodsGg;
        row.mfrsId = item.mfrsId;
        row.mfrsName = item.mfrsName;
        row.made = item.made;
        row.packetCode = item.packetCode;
        row.isPacket = item.isPacket;
        row.uniqueKind = item.isUnique;
        row.batchCode = item.batchCode;
        row.sterilizationCode = item.sterilizationCode;
        row.sterilizationEndDate = item.sterilizationEndDate;
        row.sterilizationDate = item.sterilizationDate;
        row.expdtEndDate = item.expdtEndDate;
        row.unit = item.unit;
				row.outQty = item.outQty;

				row.outPacketQty = Number(item.outQty)/Number(item.packetCode);

        //row.outPacketQty = item.outPacketQty;
        //row.inQty = 0;

				// 定数包，唯一码
				if(item.isPacket =='1' || (item.isPacket =='0' && item.isUnique !='3')){
					row.inQty = 0;
				} else {
					row.inQty = item.outQty;
				}
				//手术包出库备注不为空
				if(this.distrBill.remark){
          row.inQty = 0;
        }

        row.intPacketQty = 0;
        row.outQty = item.outQty;
        row.inPrice = item.price || 0;
        row.masterCode = "";
        row.secCode = "";
        row.hibcCode = "";
        row.version = 0;
        row.shelfInfo=item.shelfInfo;
        /*var shelfLst = item.shelfInfo.split(',').map(o=>{var arr = o.split('|'); return {stockId:arr[0],shelfCode:arr[1]}});*/
        var shelfLst = item.shelfInfo ? item.shelfInfo.split(',').map(o=>{var arr = o.split('|'); return {stockId:arr[0],shelfCode:arr[1]}}):[];
        row.shelfLst = shelfLst;
        //row.shelfId = row.shelfLst[0].shelfCode || "";
        row.lstBatch = [];
        row.lstUniqueCode = [];
        row.lstBatchTemp = item.lstOutBatch; // 存在比出库少数量入库的情况，扫码时通过批次唯一码（定数包无唯一码）从该temp中取，然后push到正式lstBatch和lstUniqueCode
        row.lstUniqueCodeTemp = item.lstOutUniqueCode;
        row.dsArrs = []; // 每一行添加临时定数包数组，提交时delete

        row.generalName = item.generalName;
        row.goodsDesc = item.goodsDesc;
        row.imgAvailable = item.imgAvailable;

				if(row.unrecieveQty > 0){
        	res.push(row);
				}
      }, this);
      this.inStockBill.lstDetail = res;
      this.stockChange();
    },
    stockChange(){
      var _this =this;
      if(this.inStockBill.inStocId && this.inStockBill.lstDetail.length>0){
        this.inStockBill.lstDetail.forEach(item=>{
          if(item.shelfInfo){
            let arr = item.shelfInfo.split(',').map(o=>{var arr = o.split('|'); return {stockId:arr[0],shelfCode:arr[1]}});
            item.shelfLst = arr.filter(o=>o.stockId == _this.inStockBill.inStocId);
            item.shelfId = item.shelfLst[0].shelfCode || "";
          }
        });
      }
    },
    sltRowsChange(sltRows) {
      this.sltRows = sltRows;
    },
    submitPurconfirm(status) {
      if (!this.inStockBill.inStocId) {
        this.$message.error("先选择入库库房");
        return;
      }
      this.diaLoging = true;
			this.inStockBill.status = status;
			if(this.inStockBill.remark){
			  var ssArr = this.inStockBill.remark.split(",");
			  if(ssArr[0]=='手术包'){
          this.inStockBill.lstDetail.forEach(o=>{
            delete o.dsArrs;
            delete o.lstBatchTemp;
            delete o.lstUniqueCodeTemp;
            delete o.imgAvailable;
          });
          this.axios.post('/spdHERPService/surgeryPkg/in/add',this.inStockBill).then(res => {
            if(res.data.code==0){
              this.diaLoging = false;
              this.$message({message: '生成入库单成功！', type: 'success'});
              var billId = res.data.data.billId;
              this.$msgbox({
                title: "提示",
                message: "操作成功",
                type: "success",
                callback: action => {
                  this.$router.push({
                    path: "inByDeptApplyList",
                    query: { inStockBillId: billId }
                  });
                }
              })
            }else {
              this.diaLoging = false;
              this.$message.error("生成入库单失败!");
            }
          });
          return;
        }
      }

      var params = Object.assign({}, this.inStockBill);
      //处理sltRows 因存在少入库的情况，需要处理批次和唯一码（从lstBatchTemp和lstUniqueCodeTemp 找到对应的数目）

      // this.sltRows.forEach(r => {
      //   let lstB = [];
      //   let count = 0;
      //   for (var i = 0; i < r.lstBatchTemp.length; i++) {
      //     if (r.inQty - count <= r.lstBatchTemp[i].qty) {
      //       r.lstBatchTemp[i].qty = r.inQty;
      //       lstB.push(r.lstBatchTemp[i]);
      //       break;
      //     } else {
      //       count = r.lstBatchTemp[i].qty;
      //       lstB.push(r.lstBatchTemp[i]);
      //     }
      //     r.lstBatch = lstB;
      //   }
      //   if (r.uniqueKind && r.uniqueKind == 3) {
      //     //唯一码管理
      //     let lstU = [];
      //     for (var k = 0; k < r.lstBatch.length; k++) {
      //       for (var j = 0; j < r.lstUniqueCodeTemp.length; j++) {
      //         if (
      //           lstU.length <= r.lstBatch.qty &&
      //           r.lstUniqueCodeTemp[j].goodsBatchId == r.lstBatch[k] &&
      //           r.lstUniqueCodeTemp[j].goodsId == r.lstBatch[k].goodsId
      //         ) {
      //           lstU.push(lstUniqueCodeTemp[j]);
      //         }
      //       }
      //     }
      //   }
      // });

      params.lstDetail = this.sltRows;

			params.lstDetail.forEach(item=>{

				// 非定数包管理的普通耗材(普通耗材生成批次信息)
				if(item.isPacket == '0' && item.uniqueKind =='3'){

					item.lstBatchTemp.forEach(oBatch =>{

							let barCodeBatch = {
								provId: oBatch.provId,
								goodsId: oBatch.goodsId,
								inPrice: oBatch.inPrice,
								goodsBatchId: oBatch.goodsBatchId,
								bigBatchCode: oBatch.bigBatchCode,
								qty: oBatch.qty
							};

							item.lstBatch.push(barCodeBatch);

					});
				}
			});

      var serviceUrl =
        this.opModel == "ADD"
          ? "/spdHERPService/stockMgr/inStock/add"
          : "/spdHERPService/stockMgr/inStock/update";
      var _this = this;
      this.axios.post(serviceUrl, params).then(
        res => {
          _this.diaLoging = false;
          if (res.data.code == "0") {
            _this.$msgbox({
              title: "提示",
              message: "操作成功",
              type: "success"
            });
            var billId = res.data.data.billId;
            _this.$msgbox({
              title: "提示",
              message: "操作成功",
              type: "success",
              callback: action => {
                this.$router.push({
                  path: "inByDeptApplyList",
                  query: { inStockBillId: billId }
                });
              }
            });
          } else {
            this.$msgbox({ title: "提示", message: "操作失败", type: "error" });
          }
        },
        err => {
          this.diaLoging = false;
          this.$msgbox({ title: "提示", message: "操作失败", type: "error" });
        }
      );
    },
    onBarcodeResolved(barKind, data, barcode) {

      if (barKind == BARCOD_RES_TYPE.ZBM) {
        this.checkRow4wym(data);
      } else if (barKind == BARCOD_RES_TYPE.GS1) {
        this.setSendGoods(goodsLst, barcode);
      } else if (barKind == BARCOD_RES_TYPE.DSB) {
        this.changeDsGoodsQty(data, barcode);
      }else if(barKind == BARCOD_RES_TYPE.SSB){
        this.getSSBDate(barcode);
      }
    },
    getSSBDate(barcode){
      var _this = this;
       var param = {
          hosId: this.user.corpId,
          surCode: barcode,
        };
        this.axios.post('/spdHERPService/surgeryPkg/in/getSurveryInfo', param).then(res => {
          _this.changeSsGoodsty(res.data.data,barcode);
        },err=>{
           this.$message.error("解析手术包出错!");
        });
    },
    changeSsGoodsty(ssData,barcode){
      let flag = false;
      var _this = this;
      if (this.ssbArray.find(u => {return u == barcode;})) {
        this.$message.error("该手术包已扫描，且有明细行匹配!");
        return;
      }else {
        this.ssbArray.push(barcode);
      }
      _this.inStockBill.barcodeList = this.ssbArray;
      _this.inStockBill.lstDetail.forEach(o=>{
        ssData.surInfoList.forEach(s=>{
          if(o.goodsId == s.goodsId&&o.batchCode == s.batchCode){
            o.inQty += s.qty;
            if(o.lstBatchTemp.length>0){
              o.lstBatchTemp.forEach(oBatch=>{
                let ob = s.surInfoBatchList.find(sBatch=>{return sBatch.batchId == oBatch.goodsBatchId});
                if(ob){
                  let barCodeBatch = {
                    provId: o.provId,
                    goodsId: ob.goodsId,
                    inPrice: ob.inPrice,
                    goodsBatchId: ob.batchId,
                    bigBatchCode: ob.bigBatchCode,
                    qty: ob.qty
                  };
                  o.lstBatch.push(barCodeBatch);
                  /*
                  if(_this.inStockBill.barcodeList.length>1){
                      o.lstBatch.forEach(item => {
                        if(item.goodsId == ob.goodsId
                          && item.goodsBatchId == ob.batchId){
                          item.qty += ob.qty
                        } else {
                          alert("2");
                          o.lstBatch.push(barCodeBatch);
                        }
                      });
                  }else {
                    o.lstBatch.push(barCodeBatch);
                  }*/
                  /*if(o.lstBatch.length == 0){
                    o.lstBatch.push(barCodeBatch);
                  }else {
                    o.lstBatch.forEach(item => {
                      if(item.goodsId == ob.goodsId
                        && item.goodsBatchId == ob.batchId){
                        item.qty += ob.qty
                      } else {
                        o.lstBatch.push(barCodeBatch);
                      }
                    });
                  }*/
                }
              });
            }
            if(o.lstUniqueCodeTemp.length>0){
              o.lstUniqueCodeTemp.forEach(oUnique=>{
                let ou = s.surInfoUniqueList.find(sUnique=>{return oUnique.uniqueCode == sUnique.uniqueCode});
                if(ou){
                  let barCodeUnique = {
                    provId: o.provId,
                    goodsId: ou.goodsId,
                    inPrice: ou.inPrice,
                    goodsBatchId: ou.batchId,
                    uniqueCode: ou.uniqueCode,
                    qty: ou.qty
                  };
                  o.lstUniqueCode.push(barCodeUnique);
                }
              });
            }
          }
        })
        this.autoSelection(o);
      });
    },
    autoSelection(row){
      this.$nextTick(function () {
        if(row.inQty > 0){
          this.$refs.receListTable.toggleRowSelection(row, true);
        }else {
          this.$refs.receListTable.toggleRowSelection(row, false);
        }
      });
    },
    checkRow4wym(code) {
      let pipeiFlag = false;
      if (
        this.wymArray.find(u => {
          return u == code;
        })
      ) {
        this.$message.error("该唯一码已扫描，且有明细行匹配!");
        return;
      }
      for (var i = 0; i < this.distrBill.lstOutsubVo.length; i++) {
        var item = this.distrBill.lstOutsubVo[i];
        if (item.isUnique == "0") {
          var uniqueCode = item.lstOutUniqueCode.find(o => {
            return o.uniqueCode === code;
          });
          if (uniqueCode && uniqueCode.isUsed != 1) {
            var goodsItem = this.inStockBill.lstDetail.find(o => {
              return (
                o.outBillRow == item.outBillRow &&
                this.inStockBill.outBillId == this.distrBill.billId
              );
            });
            if (goodsItem) {
              pipeiFlag = true;
              this.wymArray.push(code);
              goodsItem.inQty++;
              this.inQtyChange(goodsItem);
              var barCode = {
                provId: goodsItem.provId,
                goodsId: goodsItem.goodsId,
                inPrice: uniqueCode.inPrice,
                uniqueCode: code
              };
              var bachInfo = goodsItem.lstBatch.find(o=>o.goodsBatchId==uniqueCode.goodsBatchId);
              if(bachInfo){
                bachInfo.qty++;
              }else{
                bachInfo ={
                  goodsBatchId:uniqueCode.goodsBatchId,
                  inPrice: uniqueCode.inPrice,
                  qty:1,
                  bigBatchCode:uniqueCode.bigBatchCode
                };

              	goodsItem.lstBatch.push(bachInfo);
              }
              goodsItem.lstUniqueCode.push(barCode);
              this.$refs.receListTable.toggleRowSelection(goodsItem, true);
            }
          }else if(uniqueCode && uniqueCode.isUsed == 1){
            this.$message.error("此唯一码已入库");
            pipeiFlag= true;
          }
        }
        if (pipeiFlag) {
          break;
        }
      }
      if(!pipeiFlag){
        this.$message.error('无法识别的唯一码');
      }
    },
    changeDsGoodsQty(dsReData, barcode) {
      let pipeiFlag = false;
      var _this = this;
      if (this.wymArray.find(u => {return u == barcode;})) {
        this.$message.error("该定数包码已扫描，且有明细行匹配!");
        return;
      }

			_this.inStockBill.barcodeList = this.wymArray;

			_this.inStockBill.lstDetail.forEach(o =>{

				dsReData.forEach(dsData =>{
					if(o.goodsId == dsData.goodsId && o.batchCode == dsData.batchNo){

						let ob = o.lstBatchTemp.find(oBatch =>{
							return (oBatch.goodsBatchId === dsData.batchId);
						});

						if(ob){
							pipeiFlag = true;

							this.wymArray.push(barcode);

							o.inQty += dsData.qty;
							_this.inQtyChange(o);

							let barCodeBatch = {
								provId: ob.provId,
								goodsId: ob.goodsId,
								inPrice: ob.inPrice,
								goodsBatchId: ob.goodsBatchId,
								bigBatchCode: ob.bigBatchCode,
								qty: dsData.qty
							};

							/* 若批次列表中存在相同批号相同批次则数量相加，否则直接加入列表 */
							if(o.lstBatch.length == 0){
								o.lstBatch.push(barCodeBatch);
							} else {
								o.lstBatch.forEach(item => {
									if(item.provId == ob.provId && item.goodsId == ob.goodsId
											&& item.goodsBatchId == ob.goodsBatchId && item.inPrice == ob.inPrice){
										item.qty += dsData.qty
									} else {
										o.lstBatch.push(barCodeBatch);
									}
								});
							}

							_this.$refs.receListTable.toggleRowSelection(o, true);
						}
					}
				});
			});

			if(!pipeiFlag){
				_this.$message.error("无法识别的定数包码");
			};
    },
    findGoods(barCodeInfo) {
      var res = null;
      var flag = false; // 是否找到匹配的商品

      for (var i = 0; i < this.inStockBill.lstDetail.length; i++) {
        var item = this.inStockBill.lstDetail[i];
        let sDate =
          item.expdtEndDate || moment(item.expdtEndDate).format("YYYY-MM-DD");
        let rDate =
          barCodeInfo.expdtEndDate ||
          moment(barCodeInfo.expdtEndDate).format("YYYY-MM-DD");
        if (
          barCodeInfo.goodsId == item.goodsId &&
          barCodeInfo.batchCode == item.batchCode &&
          barCodeInfo.sterilizationCode == item.sterilizationCode &&
          sDate == rDate
        ) {
          flag = true;
          res = item;
          break;
          // if (item.inQty >= 0 && item.inQty < item.outQty) {
          //     if (item.batchCode) {
          //         if (item.batchCode == barCodeInfo.batchNo) {
          //             res = item;
          //             break;
          //         }
          //     } else {
          //         res = item;
          //         break;
          //     }
          // }
        }
      }
      if (!flag) {
        this.barcodeMatch.hosId = this.distrBill.inOrgId; // this.distrBill.purchaseCompanyId;
        this.barcodeMatch.provId = this.distrBill.outOrgId;
        this.barcodeMatch.mfrsCode = barCodeInfo.goodsNo;
        this.barcodeMatchV = true;
      }

      return res;
    },
    setSendGoods(goodsLst) {
      // goodsLst 为扫码结果集，一个码可能对应多个goodsId
      if (!Array.isArray(goodsLst) || goodsLst.length == 0) {
        this.$message.error("此条码无法解析!");
        return;
      }
      var curRow = null;
      var tiaomGood = null;
      for (var i = 0; i < goodsLst.length; i++) {
        curRow = this.findGoods(goodsLst[i]);
        tiaomGood = goodsLst[i];
        if (curRow && curRow.inQty < curRow.outQty) {
          break;
        }
      }
      if (curRow) {
        // 添加是否定数包管理逻辑
        if (curRow.isPacket == 1) {
          // 定数包管理
          //   curRow.intPacketQty++;
          //  curRow.inQty = curRow.intPacketQty*curRow.packetCode;
          this.$message.error("此商品为定数包管理，请扫描定数包码!");
          return;
        } else {
          // 非定数包管理
          curRow.inQty++;
          //    dealBatchAndUnique(curRow);
          // 添加 批次 唯一码的处理逻辑（需要详细处理、找到商品的批号、批次、唯一码）
        }
        this.$refs.receListTable.toggleRowSelection(curRow, true);

        // curRow.expdtEndDate = new Date(goodsLst[i].expiredDate);
        // curRow.batchCode = goodsLst[i].batchNo;
        // curRow.masterCode = goodsLst[i].barcode;
        // curRow.secCode = "" || goodsLst[i].slaverBarcode;
        // if (curRow.uniqueKind && curRow.uniqueKind == 3) {       //非唯一码管理 直接加数量
        //     curRow.inQty++;
        //     this.inQtyChange(curRow);
        // } else {              //唯一码
        //     this.inStockDetail = curRow;
        //     //this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length;
        //     this.lstUniqueCode = this.inStockDetail.lstUniqueCode;
        //     this.UniqueCode = {
        //         provId: this.inStockBill.outOrgId,
        //         goodsId: this.inStockDetail.goodsId,
        //         goodsName: this.inStockDetail.goodsName,
        //         inPrice: this.inStockDetail.inPrice,
        //         uniqueCode: ''
        //     };
        //     this.barcodeV = true;
        //     this.$nextTick(function(){
        //         this.$refs.sbarcode.$el.children[0].focus();
        //         this.$refs.sbarcode.$el.children[0].select();
        //     });
        // }
      }
    },
    editBarcode(row) {
      this.barcodeV = true;
      this.inStockDetail = row;
      this.lstUniqueCode = row.lstUniqueCode;
      this.UniqueCode = {
        provId: this.inStockBill.outOrgId,
        goodsId: row.goodsId,
        goodsName: row.goodsName,
        uniqueCode: ""
      };
      this.$nextTick(function() {
        this.$refs.sbarcode.$el.children[0].focus();
        this.$refs.sbarcode.$el.children[0].select();
      });
    },
    addBarCode() {
      var _this = this;
      if (!_this.UniqueCode.uniqueCode) {
        return;
      }
      _this.checkRow4wym(_this.UniqueCode.uniqueCode);
    },
    delBarCode(barcode) {
      var index = this.inStockDetail.lstUniqueCode.findIndex(
        item => item.uniqueCode == barcode.uniqueCode
      );
      this.inStockDetail.lstUniqueCode.splice(index, 1);
      if (this.inStockDetail.uniqueKind != 3) {
        this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length;
        this.inQtyChange(this.inStockDetail);
      }
    },
    inQtyChange(row) {
      this.$nextTick(function() {
        if (row.inQty > 0) {
					row.intPacketQty = Number(row.inQty)/Number(row.packetCode);
          this.$refs.receListTable.toggleRowSelection(row, true);
        } else {
          this.$refs.receListTable.toggleRowSelection(row, false);
        }
      });
    },
    cancel() {
      this.$router.push({ path: "inByDeptApplyList" });
    },
    getOutBillDetail(rowId) {
      var _this = this;
      this.diaLoging = true;
      this.axios
        .get("/spdHERPService/stockMgr/out4In/getBillDetail4In", {
          params: { id: rowId }
        })
        .then(
          res => {
            _this.diaLoging = false;
            if (res.data.code == "0") {
              _this.distrBill = res.data.data;
              _this.convertDistrToRecBill(_this.distrBill);
            }
          },
          err => {
            this.diaLoging = false;
          }
        );
    },
    // getSelected_Stock(temp) {
    //   this.inStockBill.inStocId = temp[1];
    //   this.inStockBill.inStocCode = temp[0] + "." + temp[1];
    // },
    showGoodsImg(item) {
      this.dlgGoodsImgLoading = true;
      this.goodsImgs.origUrls.splice(0);
      this.goodsImgs.tUrls.splice(0);
      this.axios
        .get(
          "/spdHERPService/productsInfos/hosGoodsImage/get/" +
            encodeURIComponent(item.goodsId)
        )
        .then(res => {
          if (res.data.code < 0) {
            this.$message.error(res.data.msg);
          } else {
            let data = res.data.data;
            for (let index = 1; index <= 6; index++) {
              if (data["filePath" + index] !== "") {
                this.goodsImgs.origUrls.push(
                  this.picURLPrefix + data["filePath" + index]
                );
                let slashIndex = data["filePath" + index].lastIndexOf("/");
                let tUrl =
                  this.picURLPrefix +
                  data["filePath" + index].substring(0, slashIndex) +
                  "/thumb-" +
                  data["filePath" + index].substring(slashIndex + 1);
                this.goodsImgs.tUrls.push(tUrl);
              }
            }
            this.dlgGoodsImgVisible = true;
          }
          this.dlgGoodsImgLoading = false;
        })
        .catch(err => {
          this.$message.error("删除商品图片信息失败，请联系管理员！" + err);
          this.dlgGoodsImgLoading = false;
        });
    },
    imgMouseEnter(event, index) {
      let img = this.$refs["img" + index][0];
      img.setAttribute("src", img.getAttribute("orig-src"));
    }
  },
  mounted() {
    var _this = this;
    var recId = this.$route.query.recId;
    if (recId) {
      this.opModel = "EDIT";
      this.getRecBill(recId);
    } else {
      this.opModel = "ADD";
      var outBillId = this.$route.query.outBillId;
      this.getOutBillDetail(outBillId);
		}

  }
};
</script>

<style>
#in-by-dept-apply-edit-sm .fa {
  margin-right: 5px;
}

#in-by-dept-apply-edit-sm .vue-echarts {
  width: 100%;
  height: 400px;
}

#in-by-dept-apply-edit-sm .mytable {
  font-size: 12px;
  height: calc(100% - 170px);
}

#in-by-dept-apply-edit-sm fieldset {
  border-width: 1px;
  border-style: solid;
  border-color: #1fa0ff;
  border-image: initial;
}

#in-by-dept-apply-edit-sm .icoBtn [class^="fa"] {
  vertical-align: baseline;
  cursor: pointer;
  margin-right: 5px;
}

#in-by-dept-apply-edit-sm .el-dialog--small {
  width: 70%;
}
</style>

<style scoped>
.pic-border {
  padding: 10px;
}

.tooltip-pic {
  max-width: 800px;
}

.pic {
  min-width: 50px;
  min-height: 50px;
  max-width: 178px;
  max-height: 178px;
  display: block;
  border: 1px dashed #9b9b9b;
  border-radius: 6px;
}
</style>
