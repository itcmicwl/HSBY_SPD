<template>
    <el-row id="in-by-hand-edit" v-loading="diaLoging">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="120px">
                <el-col :span="8">
                            <el-form-item label="入库医院">
                                    <el-input v-model="inStockBill.inOrgName " readonly placeholder="入库医院" ></el-input>
                                </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="入库库房:">
                    <sltStockByUser  ref="childMethod" v-on:sltChange="getSelected_Stock" :hosId="this.user.corpId" :userId="this.user.userId" :inStocCode="inStockBill.inStocCode"></sltStockByUser>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                            <el-form-item label="入库科室">
                                    <el-input v-model="inStockBill.inDeptName" readonly placeholder="入库科室" ></el-input>
                                </el-form-item>
                </el-col>
                <el-col :span="8">
                      <el-form-item label="入库类型："  >
                            <el-radio class="radio" :disabled ='radioFlag' v-model="inStockBill.inStockType" label="10" >实物入库</el-radio>
                            <el-radio class="radio" :disabled ='radioFlag' v-model="inStockBill.inStockType" label="20">虚拟入库</el-radio>
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
              
            </el-form>
        </el-col>
        <el-col :span="2" class="toolbar" style="padding-bottom: 0px;">
            <el-switch v-model="barcodeType" active-text="仅主码" inactive-text="主副码" :width="80" @change="barcodeTypeChange">
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
        </el-col>
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <el-button-group>
                            <el-button type="primary" class="el-icon-plus" size="mini" @click.native="addManyRow">添加行</el-button>
                        </el-button-group>
                    </el-col>
                                 <!--   @select="gwSltChange" --> 

        <el-table ref="receListTable" :data="inStockBill.lstDetail" style="width: 100%;" fit border height="300" 
                stripe @selection-change="sltRowsChange" 
                row-key="inBillRow"
         >
            <el-table-column type="selection" width="30">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <div class="icoBtn">
                        <i class="fa fa-copy fa-lg" @click="copyDetail(scope.row)" title="复制"></i>
                   <!--     <i class="fa fa-edit fa-lg" @click="editOthers(scope.row)" title="编辑其他信息"></i>  -->
                        <i class="fa fa-trash-o fa-lg" @click="delRow(scope.row)" title="删除" style="color:red;margin-left:5px;"></i>
                        <i class="fa fa-barcode fa-lg" @click="editBarcode(scope.row)" title="编辑条码" v-if="scope.row.uniqueKind<3"></i>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="序号" prop="inBillRow" width="60">
            </el-table-column>
            <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
            </el-table-column>
              <el-table-column label="规格" prop="goodsGg" width="90">
            </el-table-column>
            <el-table-column label="储运条件" prop="stCondition" width="90" :formatter="formateStCondition">
            </el-table-column>
            <el-table-column label="产地" prop="made" width="90">
            </el-table-column>
             <el-table-column label="单位" prop="unit">
            </el-table-column>
             <el-table-column label="包装数量" prop="packetCode">
            </el-table-column>
              <el-table-column label="是否定数包管理" prop="isPacket" :formatter="isNotFormatter">
            </el-table-column>
             <el-table-column label="唯一码管理" prop="uniqueKind">
            </el-table-column>
            <el-table-column label="批号" prop="batchCode">
                 <template slot-scope="scope">
                    <el-input placeholder="请输入批号" v-model="scope.row.batchCode" size="mini">
                    </el-input>
                </template>
            </el-table-column>
            <el-table-column label="入库数量" width="90">
                <template slot-scope="scope">
                    <el-input-number placeholder="请输入数量" v-model="scope.row.inQty" size="mini" :min="0" class="col-input-num" :disabled="scope.row.isPacket==1"  @change="inQtyChange(scope.row)">
                    </el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="入库包数" width="90">
                <template slot-scope="scope">
                    <el-input-number placeholder="请输入数量" v-model="scope.row.intPacketQty" size="mini" :min="0"  class="col-input-num" :disabled="scope.row.isPacket==0"  @change="intPacketQtyChange(scope.row)">
                    </el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="灭菌批号" prop="sterilizationCode">
                <template slot-scope="scope">
                            <el-input v-model="scope.row.sterilizationCode" placeholder="灭菌批号" ></el-input>
                        </template>
            </el-table-column>
            <el-table-column label="灭菌有效日期" width="130">
                <template slot-scope="scope">
              
                     <el-date-picker v-model="scope.row.sterilizationEndDate" type="datetime" placeholder="选择日期" >
                     </el-date-picker>
                </template>
            </el-table-column>
            <el-table-column label="灭菌日期" width="130">
                <template slot-scope="scope">
                 
                     <el-date-picker v-model="scope.row.sterilizationDate" type="datetime" placeholder="选择日期" >
                     </el-date-picker>
                </template>
            </el-table-column>
            <el-table-column label="有效期至" width="130">
                <template slot-scope="scope">
                            <el-date-picker v-model="scope.row.expdtEndDate" type="datetime" placeholder="选择日期" >
                            </el-date-picker>
                </template>
            </el-table-column>
             <el-table-column label="货位" prop="shelfId">
            </el-table-column>
              <el-table-column label="入库单价Batch" width="100">
                <template slot-scope="scope">
                    <el-input-number placeholder="请输入单价" v-model="scope.row.inPrice" size="mini"  class="col-input-num"  >
                    </el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="生产厂家" prop="mfrsName" width="180">
            </el-table-column>
            <!-- 后台生成批次
            <el-table-column label="产品批次Batch" width="90">
                <template slot-scope="scope">
                    <el-input placeholder="请输入产品批次" v-model="scope.row.goodsBatchId" size="mini" class="col-input-num"  @change="inQtyChange(scope.row)">
                    </el-input>
                </template>
            </el-table-column>
            -->
        </el-table>
         <el-col :span="20" class="toolbar" style="padding-bottom: 0px;text-align: center;">
            <el-button   @click.native="cancel">取消</el-button>
            <el-button type="primary" @click.native="submitHandBill(10)" >保存</el-button>
            <el-button type="primary" @click.native="submitHandBill(20)" >查验入库</el-button>

        </el-col>
     
        <el-dialog title="请选择商品" @close="onClose" :visible.sync="goodsListVisible" :close-on-click-modal="false" >
                    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <el-form>
                            <el-col :span="12">
                                <el-form-item label="">
                                    <el-input placeholder="商品名称、拼音缩写" v-model="filters.goodsName"  class="input-search">
                                        <el-button slot="append" icon="search" v-on:click="getGoodsInfo(1)"></el-button>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <!--
                            <el-col :span="12">
                            <el-form-item label="供应商">
                                <el-select v-model="provId" filterable placeholder="请选择" no-data-text="没有对应的供应商" >
                                    <template v-if="ProvsInfo.length > 0">
                                        <el-option v-for="item in ProvsInfo" :key="item.provId" :label="item.provName" :value="item.provId">
                                        </el-option>
                                    </template>
                                </el-select>
                            </el-form-item>
                        </el-col>
                          -->
                        </el-form>
                    </el-col>
    
                    <el-table :data="goodsList" ref="sltGoodsT" highlight-current-row v-loading="loading" style="width: 100%;min-height:300px;" fit max-height="300" border row-key="id" @selection-change="goodsSltChange">
                        <el-table-column type="selection" :reserve-selection="true" width="30">
                        </el-table-column>
                        <el-table-column prop="code" label="编码" show-overflow-tooltip width="100">
                        </el-table-column>
                        <el-table-column prop="goodsName" label="名称" width="150" sortable show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="purPrice" label="单价" width="60">
                        </el-table-column>
                        <el-table-column prop="packeage" label="包装" width="60">
                        </el-table-column>
                        <el-table-column prop="goodsGg" label="规格" width="100" show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="unit" label="单位" width="100" show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="mfrsName" label="生产厂家" show-overflow-tooltip>
                        </el-table-column>
                    </el-table>
                    <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="SizeChange" @current-change="CurrentChange" :current-page="goodsPage.page" :page-sizes="[15,30, 50]" :page-size="goodsPage.pageSize" layout="total, sizes, prev, pager, next" :total="goodsPage.total" style="float:right;" small>
                        </el-pagination>
                    </el-col>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click.native="goodsListVisible = false" >取消</el-button>
                        <el-button type="primary" @click.native="getSltGoods" >确定</el-button>
                    </div>
        </el-dialog>
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
                            <el-input class="input-barcode-small" ref="sbarcode" v-model="UniqueCode.uniqueCode" placeholder="请录入唯一码"   @keyup.native.enter="addBarCode"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item>
                            <el-button type="primary" @click.native="addBarCode" >添加</el-button>
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
        <el-dialog title="其他信息设置" v-model="otherV" :close-on-click-modal="false"  @close="detailClose">
                <el-form label-width="120px" class="el-form-item-nomsg">
                  <el-col :span="8">
                        <el-form-item label="商品名称:">
                            <el-input v-model="Batch.goodsName" :disabled="true" placeholder="商品名称" ></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="批次号">
                            <el-input v-model="Batch.goodsBatchId" placeholder="批次号" ></el-input>
                        </el-form-item>
                    </el-col>
                     <el-col :span="8">
                        <el-form-item label="入库单价">
                            <el-input v-model="Batch.inPrice" placeholder="入库单价" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="otherV = false" >取消</el-button>
                    <el-button type="primary" @click.native="addBatch" >确定</el-button>
                </div>
        </el-dialog>
        
    </el-row>
</template>
<script>
import moment from 'moment';
import { DISTRBILL_STATUS } from './common/constance';
import barcodeUtil from '../../common/js/barcodeUtil';
import { mapGetters } from 'vuex';
import util from "../../common/js/util.js";
import sltStock from '../../components/sltStock.vue';
import sltStockByUser from '../../components/sltStockByUser.vue';
   
export default {
    data() {
        return {
        	stConditions:[], //储运条件集合
            radioFlag:false,
            tiaomaFlag:null,
            tiaomaGoods:[],
            pageLoading:false,
            provId:'',
            ProvsInfo: [],              //供应商列表
            orgInfo: [],
            orgProps: {
                value: "code",
                label: "label"
            },
            opModel:'ADD',
            goodsListVisible:false,
            goodsPage: {
                total: 0,
                page: 1,
                pageSize: 15,
            },
            filters:{
                 goodsName:'',
                 code:'',
                 provId:'',
                 provName:''
            },
            goodsList: [],  
            sltGoodsList: [],           //已选中商品    
            otherV:false,
            diaLoging: false,            
            barcodeV: false,
            loading: false,
         
            inStockBill: {
                inStocId:null,
                inStocCode:null,
                id: '',
                billId: '',
                inStockKind: 0,
                inStockType: 10,
                outBillId: '',
                outOrgId: '',
                outOrgName: '',
                inOrgId: '',
                inOrgName: '',
              
                inDeptId: '',
                inDeptName: '',
                inDeptCode: '',
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
            inStockDetail: {
                packageId:'',
                id:'',
                pid:'',
                billId:'',
                outBillRow:'',
                inBillRow:'',
                provId:'',
                provCode:'',
                provName:'',
                goodsId:'',
                goodsName:'',
                goodsGg:'',
                mfrsId:'',
                mfrsName:'',
                made:'',
                packetCode:'',
                isPacket:'',
                uniqueKind:'',
                batchCode:'',
                sterilizationDate:'',
                sterilizationCode:'',
                sterilizationEndDate:'',
                expdtEndDate:'',
                unit:'',
                outQty:'',
                outPacketQty:'',
                inQty:'',
                intPacketQty:'',
                masterCode:'',
                secCode:'',
                hibcCode:'',
                version:'',
                inPrice:'',
                lstBatch:[],
                lstUniqueCode:[]
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
                goodsName:'',
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
    components: { sltStock,sltStockByUser }, 
    methods: {
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
        sltRowsChange(sltRows) {
            this.sltRows = sltRows;
        },
        submitHandBill(status) {
           this.inStockBill.status = status;
            if(!this.inStockBill.inStocId){
                this.$message.error('先选择入库库房');
                return;
            };
            this.diaLoging = true;
         
            var params = Object.assign({}, this.inStockBill);
            if(this.opModel=='EDIT'){
                 //处理入库单价
               this.sltRows.forEach((item)=>item.lstBatch[0].inPrice = item.inPrice);           
            }
           this.sltRows.forEach((item)=> delete item.packageId );           

            params.lstDetail = this.sltRows;
            var serviceUrl  = this.opModel=="ADD"? '/spdHERPService/stockMgr/inStock/add' : '/spdHERPService/stockMgr/inStock/update';
            var _this = this;
              this.axios.post(serviceUrl, params).then(res => {
                  _this.diaLoging = false;
                  if (res.data.code == "0") {
                      _this.$msgbox({ title: "提示", message: "操作成功", type: "success" });
                      var billId = res.data.data.billId;
                      _this.$msgbox({
                          title: "提示",
                          message: "操作成功",
                          type: "success",
                           callback: action => {
                               this.$router.push({ path: 'inByHandList', query: { inStockBillId: billId }});
                           }
                      });
                  } else {
                      this.$msgbox({ title: "提示", message: "操作失败", type: "error" });
                  }
              }, err => {
                  this.diaLoging = false;
                  this.$msgbox({ title: "提示", message: "操作失败", type: "error" });
              });
        },
        barcodeNext() {
            this.fBarcode = "";
            this.$refs.fbarcode.$el.children[0].focus();
            this.$refs.fbarcode.$el.children[0].select();
        },
        barcodePre() {
            if(!this.inStockBill.inDeptId){
                this.$message.error('先选择入库库房');
                return;
            }
            barcodeUtil.parseCombinedBarcode(this.mainBarcode, this.fBarcode).then(res => {
                var goodsLst = res.data.data;
                this.tiaomaGoods= res.data.data;
                if (!Array.isArray(goodsLst) || goodsLst.length == 0) {
                       this.$message.error('此条码无法解析!');
                       return;
                }
                 //弹出商品选择窗口
                   this.getGoodsInfo(0);
                   this.goodsListVisible=true;
                this.mainBarcode = "";
                this.fBarcode = "";
            });
        },
        barcodeTypeChange(val) {
            this.$nextTick(function () {
                if (val) {
                    this.$refs.mbarcode.$el.children[0].focus();
                    this.$refs.mbarcode.$el.children[0].select();
                } else {
                    this.$refs.mainBarcode.$el.children[0].focus();
                    this.$refs.mainBarcode.$el.children[0].select();

                }
            });
        },
        editOthers(row) {
            this.otherV = true;
            this.inStockDetail = row;//Object.assign({}, row);
            this.Batch ={
                provId: row.provId,
                goodsId:row.goodsId,
                goodsName: row.goodsName,
                inPrice:'',
                goodsBatchId:''
            };
        },
        copyDetail(row) {
            var crow = Object.assign({}, row);
                          
            this.inStockBill.lstDetail.splice(row.inBillRow, 0, crow); // 
            this.inStockBill.lstDetail.forEach((item, i) => item.inBillRow = i + 1);
        },
        delRow(pd) {
            var index = this.inStockBill.lstDetail.findIndex(item => { return item.inBillRow == pd.inBillRow });
            this.inStockBill.lstDetail.splice(index, 1);
            this.inStockBill.lstDetail.forEach((item, i) => item.inBillRow = i + 1);
        },
        detailClose() {
            this.inStockDetail = {};
          //  this.distrUnique = {};
          //  this.distrUniqueList = [];
        },
        xx(){
              //  let dsGoodsIds = new Set(data.map(o => o.goodsId))
               //    alert(dsGoodsIds.size);
       },
       changeDsGoodsQty(res){
           
       },
     
        addBybarcode() {
          //  alert(this.barcode);
            if(!this.inStockBill.inDeptId){
                this.$message.error('先选择入库库房');
                return;
            }
            this.tiaomaFlag=true;
            barcodeUtil.parseCombinedBarcode(this.barcode, null, this.user.corpId).then(res => {
                var goodsLst = res.data.data;
                this.tiaomaGoods= res.data.data;
                  if (!Array.isArray(goodsLst) || goodsLst.length == 0) {
                       this.$message.error('此条码无法解析!');
                       return;
                   }
                // this.setSendGoods(goodsLst);
                // this.$refs.mbarcode.$el.children[0].focus();
                // this.$refs.mbarcode.$el.children[0].select();
                if(this.barcode.toLowerCase().indexOf('ds') === 0){  //扫码定数包
               //     alert("DS");
                    this.changeDsGoodsQty(res)


                }
                else{
                         //弹出商品选择窗口
                   this.getGoodsInfo(0);
                   this.goodsListVisible=true;
                }
            });
        },
        findGoods(barCodeInfo) {
            var res = null;
            var flag = false;//是否找到匹配的商品
            for (var i = 0; i < this.inStockBill.lstDetail.length; i++) {
                var item = this.inStockBill.lstDetail[i];
                if (barCodeInfo.goodsId == item.goodsId) {
                    flag = true;
                    if (item.inQty >= 0 && item.inQty < item.outQty) {
                        if (item.batchCode) {
                            if (item.batchCode == barCodeInfo.batchNo) {
                                res = item;
                                break;
                            }
                        } else {
                            res = item;
                            break;
                        }
                    }
                }
            }
            if (!flag) {
                this.barcodeMatch.hosId = this.user.corpId;//this.distrBill.purchaseCompanyId;
             //   this.barcodeMatch.provId = this.distrBill.outOrgId;
                this.barcodeMatch.mfrsCode = barCodeInfo.goodsNo;
                this.barcodeMatchV = true;
            }
            return res;
        },

    
       editBarcode(row) {
            this.barcodeV = true;
            this.inStockDetail = row;
            this.lstUniqueCode = row.lstUniqueCode;
            this.UniqueCode = {
                provId: this.inStockBill.outOrgId,
                goodsId: row.goodsId,
                goodsName: row.goodsName,
                uniqueCode: ''
            }
            this.$nextTick(function(){
                this.$refs.sbarcode.$el.children[0].focus();
                this.$refs.sbarcode.$el.children[0].select();
            });
        },
         addBarCode(){
            var _this =this;
            if(!_this.UniqueCode.uniqueCode){
                return;
            }
            var index =  this.inStockDetail.lstUniqueCode.findIndex(item =>item.uniqueCode == _this.UniqueCode.uniqueCode);
            if(index >=0){
                this.$message.error('此唯一码已使用!');
                return;
            }
            var barCode = {
                provId : this.UniqueCode.provId,
                goodsId : this.UniqueCode.goodsId,
                inPrice : this.UniqueCode.inPrice,
                uniqueCode : this.UniqueCode.uniqueCode
            }
            this.inStockDetail.lstUniqueCode.push(barCode);
            if(this.inStockDetail.uniqueKind != 3){
                this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length;
            }
            this.inQtyChange(this.inStockDetail);
            this.$nextTick(function(){
                this.$refs.sbarcode.$el.children[0].focus();
                this.$refs.sbarcode.$el.children[0].select();
            });
        },
      
        delBarCode(barcode){
            var index =  this.inStockDetail.lstUniqueCode.findIndex(item =>item.uniqueCode == barcode.uniqueCode);
            this.inStockDetail.lstUniqueCode.splice(index, 1);
            if(this.inStockDetail.uniqueKind != 3){
                this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length;
                this.inQtyChange(this.inStockDetail);
            }
        },
        inQtyChange(row){
            this.$nextTick(function(){
                if(row.inQty>0){
                this.$refs.receListTable.toggleRowSelection(row,true);
                }else{
                    this.$refs.receListTable.toggleRowSelection(row,false);
                }
            });            
        },
        intPacketQtyChange(row){
           this.$nextTick(function(){
                if(row.intPacketQty>0){
                    row.inQty =  row.intPacketQty* row.packetCode;
                this.$refs.receListTable.toggleRowSelection(row,true);
                }else{
                    this.$refs.receListTable.toggleRowSelection(row,false);
                }
            });   
        },
        addBatch(){
            let batch = {
                          inPrice:this.Batch.inprice,
                          goodsId:this.Batch.goodsId,
                          provId :this.Batch.provId,
                          goodsBatchId:this.Batch.goodsBatchId
                        };
            this.inStockDetail.lstBatch.push(batch);
            this.otherV =false;
        },
        cancel(){
             this.$router.push({ path: 'inByHandList'});
        },
        getProv() {
            //var params = { kind: "2" };
            //return this.axios.post('/platformService/bas/company/companysList', params);
            var param = { orderBy: "", queryObject: {}, page: 1, rows: 200 };
            return this.axios.post('/spdHDIService/provManager/hosProvInfo/hosProvInfoVoList', param);
        },
        getOrg() {  //改为通过库房Id查询库房所属机构
            var params = {};
            let res = this.axios.post('/platformService/sys/org/getOrgsByUnit', params);
            return res;
        },
       
        addManyRow() {
           this.tiaomaFlag=false;
            if(!this.inStockBill.inDeptId){
                this.$message.error('先选择入库库房');
                return;
            }
            this.goodsListVisible = true;
            this.getGoodsInfo(1);
        },
        getGoodsInfo(pIndex) {
            this.goodsListVisible = true;
            this.goodsPage.page = pIndex;
             let arrGoodsIDs=[];
            if(this.tiaomaFlag){  //通过条码查询
                this.tiaomaGoods.forEach((item, i) => arrGoodsIDs.push(item.goodsId) );
             }

             var param = {
                orderBy: "",
                page: this.goodsPage.page,
                rows: this.goodsPage.pageSize,
                queryObject: {
                    goodsName: this.filters.goodsName,
                    
                  //  shortPinyin: this.filters.goodsName,
                   // code: this.filters.code,
                  //  provId: this.filters.provId,
                  //  isHisCodeNotNull: '1'
                  hosId:this.user.corpId,
                  deptId:this.inStockBill.inDeptId,
                  purMode:this.inStockBill.inStockType,
                  arrGoodsIDs:arrGoodsIDs
                  
              
                 
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsList4InStock', param).then(res => {
                this.loading = false;
                if (res.data.code == "0") {
                    this.loading = false;
                    this.goodsList = res.data.data.data;
                    this.goodsPage.total = res.data.data.total;
                }
            }, err => {
                this.loading = false;
            });

        },
        onClose() {
            this.$refs.sltGoodsT.clearSelection(this.sltGoodsList);
            this.sltGoodsList = [];
            //this.inStockDetail.applyDeptId = "";
            // this.inStockDetail.applyDeptCode = "";
            //this.inStockDetail.applyDeptName = "";
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
            if(this.tiaomaFlag&&this.sltGoodsList.length > 1){
                 this.$message.error('扫码时只能选择一项商品!');
                 return;
            }
            if (this.sltGoodsList.length > 0) {
                // var date = new Date();
                // var applyDate = util.formatDate.format(date, "yyyy-MM-dd");
                // date.setDate(date.getDate() + 3);
                // var receiveDate = util.formatDate.format(date, "yyyy-MM-dd");
                this.sltGoodsList.forEach(item => {
                    let row = Object.assign({}, this.inStockDetail);
                 
                    // row.hosGoodsCode = item.hisCode;
                    row.goodsId = item.goodsId;
                    row.goodsName = item.hisName ||item.goodsName;
                    row.goodsGg =item.goodsGg;
                    row.mfrsId =item.mfrsId;
                    row.mfrsName =item.mfrsName;
                    row.made =item.made;
                    row.packetCode=item.packeage;
                    row.unit=item.unit;
                    row.isPacket = item.isPacket; //是否定数包管理
                    row.provId = item.provId;
                    row.provName= item.provName;
                    row.uniqueKind =item.isUnique;  //唯一码管理策略
                    row.inQty=0;
                    row.stCondition = item.stCondition;
                    
                    // row.hosPrice = item.purPrice || 0;
                    // row.purTax = item.taxRate || 1;
                    // row.hosScale = item.unitRate || 1;
                    // row.hosUnit = item.hisUnit;
                    // row.provId = this.purOrder.provId;
                    // row.applyDate = applyDate;
                    // row.receiveDate = receiveDate;
                    // row.purTaxPrice = (row.hosPrice / (1 + row.purTax)).toFixed(6);
                    
                    //判断this.inStockBill.lstDetail 里是否已有匹配的商品goodsId，没有 就push ，有 将数量加一，不push
                    this.set2Row(row);
                   
                });
                this.goodsListVisible = false;
            } else {
                this.$message.error('请选择商品!');
            }
        },
        set2Row(temp){
            if(this.tiaomaFlag){  //条码添加
                 let sltTiaoMa = null;
                   this.tiaomaGoods.forEach(tiaoMaGood=>{
                            if(tiaoMaGood.goodsId==temp.goodsId)
                                sltTiaoMa =tiaoMaGood;
                   });
              //查询是入库明细行中否有已有匹配项
               let curRow = this.findGoodsFromLstDetail(temp);
            
               if(curRow){  //找到匹配项
                    
                    curRow.expdtEndDate = new Date(sltTiaoMa.expiredDate);
                    curRow.batchCode = sltTiaoMa.batchNo;
                    curRow.masterCode = sltTiaoMa.barcode;
                    curRow.secCode = "" || sltTiaoMa.slaverBarcode;
                    if (curRow.uniqueKind && curRow.uniqueKind == 3) {       //非唯一码管理 直接加数量

                      //  curRow.inQty++;
                    //    this.inQtyChange(curRow);
                          if(curRow.isPacket==1){ //定数包管理 
                                curRow.intPacketQty++;
                                curRow.inQty = curRow.intPacketQty*curRow.packetCode;
                            }  
                            else{    //非定数包管理
                                curRow.inQty++;
                            }
                     } 
                    else {       //唯一码
                        
                               
                            if(curRow.isPacket==1){ //定数包管理 
                                curRow.intPacketQty++;
                                curRow.inQty = curRow.intPacketQty*temp.packetCode;
                            }  
                            else{    //非定数包管理
                                curRow.inQty++;
                            }
                        this.inStockDetail = curRow;
                        //this.inStockDetail.inQty = this.inStockDetail.lstUniqueCode.length;
                        this.lstUniqueCode = this.inStockDetail.lstUniqueCode;
                        this.UniqueCode = {
                            provId: this.inStockBill.outOrgId,
                            goodsId: this.inStockDetail.goodsId,
                            goodsName: this.inStockDetail.goodsName,
                            inPrice: this.inStockDetail.inPrice,
                            uniqueCode: ''
                           };  

                        }
                    
             
            }
            else{  //没有匹配项
                   
                        temp.expdtEndDate = new Date(sltTiaoMa.expiredDate);
                        temp.batchCode = sltTiaoMa.batchNo;
                        temp.masterCode = sltTiaoMa.barcode;
                        temp.secCode = "" || sltTiaoMa.slaverBarcode;
                        if(temp.isPacket==1){ //定数包管理 
                          temp.intPacketQty++;
                          temp.inQty = temp.intPacketQty*temp.packetCode;
                        }  
                        else{    //非定数包管理
                            temp.inQty++;
                        }

                      this.inStockBill.lstDetail.push(temp);
                      temp.inBillRow = this.inStockBill.lstDetail.length;
            }
          

        }
          else{   //手动添加行
                 this.inStockBill.lstDetail.push(temp);
                 temp.inBillRow = this.inStockBill.lstDetail.length;
            }
        },
        findGoodsFromLstDetail(temp){
            let res =null;
            let sltTiaoMa = null;   //扫条码可能匹配多个商品，确定 是选择的那个商品，
            this.tiaomaGoods.forEach(tiaoMaGood=>{
                if(tiaoMaGood.goodsId==temp.goodsId)
                    sltTiaoMa =tiaoMaGood;
            });
             let flag = false;// 通过 goodsId  batchNo 厂家批号  expiredDate 有效期
            this.inStockBill.lstDetail.forEach(detail=>{
                  if(detail.batchCode==sltTiaoMa.batchNo&&detail.goodsId==sltTiaoMa.goodsId){
                       flag =true;
                       res=detail;
                   
                  }
            });
            return res
            

        },
        getSelected_Stock(temp){
         
          this.inStockBill.inStocId = temp[1];
          this.inStockBill.inStocCode=temp[0]+'.'+temp[1];
          
            this.axios.post( `/spdHERPService/deptMgr/stocInfo/getOrgsByStock/`+ temp[1]).then(res => {
                let ob = res.data.data;
                this.inStockBill.inDeptId=ob.id;
                this.inStockBill.inDeptName=ob.ename;
             });
        },
        getBill(billId){
            var _this = this;
            this.axios.get('/spdHERPService/stockMgr/inStock/getById4Instock?id=' + billId).then(res=>{
                if(res.data.code == 0){
                    _this.inStockBill = res.data.data;
                  _this.inStockBill.inStockType =res.data.data.inStockType+"";
                    for(var i = 0;i< _this.inStockBill.lstDetail.length;i++){
                        _this.inQtyChange( _this.inStockBill.lstDetail[i]);
                    }
                }
            });
        },
         isNotFormatter (row, column, cellValue) {
          return cellValue==1 ? "是" :"否"

        }
    },
    computed: {
        ...mapGetters(["getLoginUser"]),
 
      },
    mounted() {
      
        var _this = this;
        this.pageLoading = true;
        var billId = _this.$route.query.billId;
        if(billId){
            this.opModel="EDIT";
            this.radioFlag=true;
            this.getBill(billId);
            this.getStCondition();
        }else{
            this.opModel="ADD";
            this.getStCondition();
           
            _this.inStockBill.inOrgId = _this.getLoginUser.corpId;  //入库医院
            _this.inStockBill.inOrgName = _this.getLoginUser.corpName;
           // _this.inStockBill.inDeptId = _this.getLoginUser.orgId;   //入库科室
           // _this.inStockBill.inOrgCode = _this.getLoginUser.orgCode;
           // _this.inStockBill.inOrgCname = _this.getLoginUser.orgName;
            _this.inStockBill.inStockType ="10";  //新增默认值为实物入库
            _this.inStockBill.fillterName = _this.getLoginUser.ename;
            _this.inStockBill.filler = _this.getLoginUser.userId;
            //_this.inStockBill.fillDate = util.formatDate.format(new Date(), "yyyy-MM-dd");
            _this.inStockBill.fillDate = new Date();
        }


   
        
    }
};

</script>

<style>
#in-by-hand-edit .fa {
    margin-right: 5px;
}

#in-by-hand-edit .vue-echarts {
    width: 100%;
    height: 400px;
}

#in-by-hand-edit .el-table {
    font-size: 12px;
}

#in-by-hand-edit fieldset {
    border-width: 1px;
    border-style: solid;
    border-color: #1fa0ff;
    border-image: initial;
}

#in-by-hand-edit .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}

#in-by-hand-edit .el-dialog--small {
    width: 70%;
}
</style>