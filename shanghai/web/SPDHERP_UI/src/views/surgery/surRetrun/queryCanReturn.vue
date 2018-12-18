<template>
    <div id="queryCanReturn"  style="height:95%">
      <el-row>
          <div class="inline-display" style="width:100%">
            
              <div class="inline-display">
                <el-tooltip :disabled="filters.length>2" effect="light" content="请最少输入3个字符" placement="bottom-start">
                  <el-input ref="filters" v-model="filters.sickerName" @keyup.native.enter="handleSearchBtn" placeholder="请输入患者名、住院号、手术号"></el-input>
                </el-tooltip>
              </div>
              <div  class="inline-display">
                <el-input ref="masterBarcode" v-model="masterBarcode" @keyup.native.enter="handleMasterBarcodeEnter" placeholder="请录入手术包码"></el-input>
              </div>
              <span class="inline-display">{{ outType }}库房：</span>
              <el-select v-model="currentStock" class="stock-select" @change="handleStockChange" placeholder="请选择房库">
                <el-option v-for="item in ownStockList" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
              <div class="inline-display" v-if="outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value">
                <span class="inline-display" style="margin-left:10px;">{{ outType }}至：</span>
                <el-select v-model="currentDeptId" class="stock-select" placeholder="请选择科室">
                  <el-option v-for="item in orgList" :key="item.id" :label="item.name" :value="item.id"></el-option>
                </el-select>
              </div>
              <div class="inline-display">
                <el-button  @click="showOutTempBill" type="info">
                  预览{{ outType }}单
                </el-button>
              </div>
          </div>
      </el-row>
      <el-row style="height:95%">
            <el-col :span="8" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 100%;">
                <el-table highlight-current-row class="qcr-tableheight"
                         :data="surPkgLst" style="width: 100%"
                          ref="multipleTable4Sur"
                          row-key='surCode'  @selection-change="handleSelectionChange4Sur"
                          @row-click="fetchSurGoods"
                           border height="outer">
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>                                       
                            <el-table-column prop="surName"   min-idth="85" label="手术包名" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="surCode"   min-idth="85" label="手术包码" width="200" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="sickerName"   min-idth="50" label="患者名" width="100" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="inHosCode"   min-idth="50" label="患者住院号"  header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="surScheduleNo"   min-idth="50" label="手术排班号"  header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            
                </el-table>
            </el-col>
            <el-col :span="16" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 100%;">
                <el-table highlight-current-row class="qcr-tableheight"
                         :data="ssGoodsList" style="width: 100%"
                          row-key='goodsId' 
                           border height="outer"
                          :default-sort="{prop: 'goodsId', order: 'descending'}">
                           <el-table-column type="expand">
                              <template slot-scope="props">
                                  <table width="100%">
                                    <tr><th width="25%">批次号</th><th  width="25%">批号</th><th width="25%">数量</th><th width="25%">使用数量</th></tr>
                                    <tr v-for="item in props.row.lstGoodsBatch" :key="item.id" >
                                        <td>{{item.batchId}}</td><td>{{item.batchCode}}</td><td>{{item.qty}}</td><td>{{item.useQty}}</td>
                                    </tr>
                                  </table>
                              </template>
                            </el-table-column>
                            <el-table-column prop="goodsId"   min-idth="85" label="商品ID" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                             <el-table-column prop="hosGoods.goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="hosGoods.goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                             <el-table-column prop="qty"   min-idth="85" label="数量" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                             <el-table-column prop="hosGoods.unit"   min-idth="85" label="单位" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="hosGoods.mfrsName"   min-idth="85" label="厂家" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="hosGoods.made"   min-idth="85" label="生产国" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            
                </el-table>
            </el-col>
      </el-row>

      <el-dialog :visible.sync="dlgVisible" title="预览出库单" width="95%" top="10vh">
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
              <el-form-item label="出库部门">
                  <!--
                    <el-select v-model="outStockBill.outDeptId" disabled>
                      <el-option v-for="item in orgList" :key="item.id" :label="item.ename" :value="item.id"></el-option>
                    </el-select>
                    -->
                     <el-input v-model="outStockBill.outDeptName" disabled></el-input>
              </el-form-item>
            </el-col>
              <el-col :span="8">
              <el-form-item label="目标部门">
                    <!--
                    <el-select v-model="outStockBill.inDeptId" disabled>
                      <el-option v-for="item in orgList" :key="item.id" :label="item.ename" :value="item.id"></el-option>
                    </el-select>
                    -->
                    <el-input v-model="outStockBill.inDeptName" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8"></el-col>
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
                种类：{{  }}，总数量：{{  }}，总金额：{{  }}
              </div>
            </el-col>
          </el-row>
           <el-row :gutter="20" v-if="outStockKind !== OUTSTOCK_BILL_KIND.MANUAL.value && outStockKind !== OUTSTOCK_BILL_KIND.CONSUME.value">
                  <el-col :span="8">
                    <el-form-item label="收货地址" required prop="recAddress">
                      <el-input v-model="address.recAddress" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="联系人" required prop="recLinkman">
                      <el-input v-model="address.recLinkman" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-col :span="18">
                      <el-form-item label="联系电话" required prop="recLinkmanPhone">
                        <el-input v-model="address.recLinkmanPhone" disabled></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <deliveryAddress :dept-id="currentDeptId" :hos-id="user.corpId" @addressChange="setAddress" style="font-size:12px;line-height:25px;" btn_text="修改"></deliveryAddress>
                    </el-col>
                  </el-col>
           </el-row>
          <el-row style="height:95%">
           
            <el-col  class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 100%;">
                <el-table highlight-current-row class="qcr-tableheight"
                         :data="outStockBill.goodsList" style="width: 100%"
                           border height="outer"
                          :default-sort="{prop: 'goodsId', order: 'descending'}">
                            <el-table-column prop="surCode"   min-idth="85" label="手术包码" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="surName"   min-idth="85" label="手术包名" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <!--
                            <el-table-column prop="goodsId"   min-idth="85" label="商品ID" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>-->
                            <el-table-column prop="qty"   min-idth="50" label="数量" width="80" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="batchNo"   min-idth="85" label="批号" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="batchId"   min-idth="85" label="批次" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>                         
                            <el-table-column prop="unit"   min-idth="50" label="单位" width="50" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="mfrsName"   min-idth="85" label="厂家" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="made"   min-idth="85" label="生产国" width="85" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            
                </el-table>
            </el-col>
          </el-row>

        </el-form>
     
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="dlgVisible = false">取消</el-button>
          <el-button :disabled="outStockBill.goodsList.length === 0" :loading="confirmBtnLoading" @click="handleDlgConfirm(true)" type="primary">提交</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
import deliveryAddress from "@/components/deliveryAddress.vue";
import {
  OUTSTOCK_BILL_STATUS,
  OUTSTOCK_BILL_KIND,
  OUTSTOCK_TYPE
} from "@/common/js/constance";
import cloneDeep from "lodash/cloneDeep";
export default {
   components: {
    deliveryAddress
  },
    
  data () {
    return { 
      baseURL: "/spdHERPService/stockMgr/out/returnOut",
      outStockKind: OUTSTOCK_BILL_KIND.RETURN.value,
      orgList: [],
      currentDeptId: "",
      outStockType: 20,
      outType:'退还',
      outStockBill:{goodsList:[]},
      CKLXDicts: {},
      filters: {
              length:0,
              surCode:"",
              sickerName:""
      },
      loading: 'false',  
      masterBarcode: "",
      currentStock: "",
      ownStockList: [],
      surPkgLst:[],
      ssGoodsList:[],
      page:1,pageSize:20,total:0,
      multipleSelection4sur:[],
      multipleTable4Sur:[],
      dlgVisible:false,
      btnLoading:false,
      confirmBtnLoading: false,
      address:{
        recAddressId:'',
        recLinkman:'',
        recLinkmanPhone:'',
        recAddress:''

      }
    };
   
  },
  computed: {
    OUTSTOCK_TYPE() {
      return OUTSTOCK_TYPE;
    },
    OUTSTOCK_BILL_KIND() {
      return OUTSTOCK_BILL_KIND;
    }
  },
 watch: {
    orgList: function(oldValue, newValue) {
      if (!this.currentDeptId && newValue) {
        this.currentDeptId = this.orgList[0].id;
      }
    }
  },
  methods: {
      handleSearchBtn(){
        this.querySurPkgLst(1);
      },
      handleMasterBarcodeEnter(){
         this.querySurPkgLst(1);

      },
      querySurPkgLst(pIndex){
            this.page = pIndex;
            var param = {
              orderBy: "",
              page: this.page,
              rows: this.pageSize,
              queryObject: {
                surCode: this.filters.surCode,
                surName: this.filters.surName,
                hosId: this.user.corpId,
                deptId:this.user.orgId,
                statusArr: [32,40,41,51,52]
              }
            };
          this.loading = true;
          this.axios.post("/spdHERPService/surgery/return/querySurPkgLstByPage", param).then(
            res => {
              this.loading = false;
              this.surPkgLst = res.data.data.data;
              this.total = res.data.data.total;
            },
            err => {
              this.loading = false;
            }
          );
      },
      getStockList() {
          return this.axios.get( "/spdHERPService/stockMgr/out/manualOut/getStockList").then(res => {
              if (res.data.code === 0) {
                let result = res.data.data;
                this.ownStockList = result;
                if (result && result.length !== 0) {
                  this.currentStock = result[0].id;
                }
              } else {
                this.handleError(res.data.msg);
              }
            })
            .catch(err => {
              this.handleError(err);
            });
    },
    handleStockChange() {
      this.surPkgLst.splice(0);
      this.handleSearchBtn();
    },
    handleSelectionChange4Sur (val) {
      this.multipleSelection4sur = val
    },
    fetchSurGoods(row){
      this.ssGoodsList=row.surgeryPkgListVos;    
    },
    showOutTempBill(){
      if(this.multipleSelection4sur.length<=0){
         this.$message.error("请勾选手术包");
         return
      }
       this.dlgVisible = true
      this.cvt2outBill(this.multipleSelection4sur)
     
    },
    cvt2outBill(sur){

      let tempBill ={goodsList:[]};
      tempBill.outStockKind = this.outStockKind
      tempBill.outDeptId = this.user.orgId //出库部门
      tempBill.outDeptName = this.user.orgName //出库部门

      tempBill.inDeptId = this.currentDeptId // 退还至 设备科
      tempBill.inDeptName = this.orgList.find(o => o.id === this.currentDeptId).name;
      tempBill.filler = this.user.ename
      tempBill.fillDate ='';
      tempBill.outOrgId=this.user.corpId
      tempBill.outOrgName= this.user.corpName
      tempBill.inOrgId=this.user.corpId
      tempBill.inOrgName= this.user.corpName
      tempBill.outStocId =this.currentStock
      tempBill.outStockType = this.outStockType
      tempBill.recAddressId = this.address.recAddressId
      tempBill.recLinkmanPhone = this.address.recLinkmanPhone
      tempBill.recLinkman = this.address.recLinkman
      tempBill.recAddress = this.address.recAddress
      sur.forEach(sur=>{
        sur.surgeryPkgListVos.forEach(detail=>{
          detail.lstGoodsBatch.forEach(batch=>{
            if(batch.qty-batch.useQty>0){
                let row ={};
                row.qty = batch.qty-batch.useQty;  //退还数量
                row.batchNo= batch.batchCode;//批号
                row.batchId= batch.batchId;//批次
                row.uniqueCode =batch.uniqueCode;//唯一码
                row.goodsId = batch.goodsId;
                row.goodsName = detail.hosGoods.goodsName;
                row.goodsGg = detail.hosGoods.goodsGg;
                row.isUnique=detail.uniqueKind;//.hosGoods.uniqueCodeStrategy //需要查询补全当前为null  provId provName provCode
                
                row.unit= detail.unit;
                row.mfrsId = detail.hosGoods.mfrsId;
                row.mfrsName =detail.hosGoods.mfrsName;
                row.made = detail.hosGoods.made;
                row.sourceBillId=sur.surCode;
                row.price = batch.inPrice;
                row.surId = sur.surId;
                row.surName = sur.surName;
                row.surCode = sur.surCode;
                
                row.sterilizationDate = batch.sterilizationDate;
                row.sterilizationCode= batch.sterilizationCode;
                row.sterilizationEndDate= batch.sterilizationEndDate;
                row.expdtEndDate = batch.expdtEndDate;
                row.provId = batch.provId;
                row.provName = batch.provName;
                row.provCode = batch.provCode;
                row.bigBatchCode = batch.bigBatchCode;
                tempBill.goodsList.push(row)

            }
          })              
        })
      })

      this.outStockBill= tempBill

    },
    handleTypeChange() {
      this.multipleSelection4sur.splice(0);
      this.handleSearchBtn();
    },
    handleStockChange() {
      this.multipleSelection4sur.splice(0);
      this.handleSearchBtn();
    },
    handleLabelClick(){

    },
    handleDlgConfirm() {  //提交 生成退货单
      // 验证商品
      this.outStockBill.recAddressId=this.address.recAddressId
      this.outStockBill.recLinkman=this.address.recLinkman
      this.outStockBill.recLinkmanPhone=this.address.recLinkmanPhone
      this.outStockBill.recAddress = this.address.recAddress
      if (
      
        (!this.outStockBill.recAddressId ||
          !this.outStockBill.recLinkman ||
          !this.outStockBill.recLinkmanPhone ||
          !this.outStockBill.recAddress)
      ) {
         this.$message.error("请选择收货地址");
        return;
      }
      this.confirmBtnLoading = true;
     let paramObje= this.outStockBillData2Post(true)
      this.axios.post("/spdHERPService/stockMgr/out/surReturnOut/saveOutStockBill",
          paramObje
        )
        .then(res => {
          if (res.data.code === 0) {
            this.$message.success("提交" + this.outType + "单成功");
            this.outStockBill.goodsList.splice(0);
            this.multipleSelection4sur.splice(0);
            this.handleSearchBtn();
          } else {
            this.$message.error(res.data.msg);
          }
          this.confirmBtnLoading = false;
        })
        .catch(err => {
          this.$message.error(err);
          this.confirmBtnLoading = false;
        });
      this.dlgVisible = false;
    },
    outStockBillData2Post(submit) {
      let data = cloneDeep(this.outStockBill);
      /*
      for (let goods of data.goodsList) {
        goods.stockTableId = goods.selected.id;
        goods.certificateCode = goods.selected.certificateCode;
        goods.shelfCode = goods.selected.shelfCode;
        delete goods.avlQty;
        delete goods.selected;
      }
      */
      delete data.fillDate;
      delete data.filler;
        data.goodsList.forEach(g=>{
          delete g.surId
          delete g.surName
          delete g.surCode
        })
      if (submit) {
        data.status = OUTSTOCK_BILL_STATUS.SUBMIT.value;
      } else {
        data.status = OUTSTOCK_BILL_STATUS.SAVE.value;
      }
/*
      data.outStocId = this.currentStock;
      if (this.currentDeptId) {
        data.inDeptId = this.currentDeptId;
        data.inDeptName = this.orgList.find(
          o => o.id === this.currentDeptId
        ).name;
      }
      */
      data.st = "100"; //二级库退货出库标识，区别一级库出库
      return data;
    },
    getCKLXDict() {
      this.getDict("CKLX").then(res => {
          this.CKLXDicts = res;
        }).catch(err => {
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
     getOrgList() {
      this.axios.get(this.baseURL + "/getOrgList").then(res => {
          if (res.data.code === 0) {
            // 判断查询结果是否为空
            if (res.data.data.length === 0) {
              return;
            }
            this.orgList = res.data.data;
            return this.orgList;
          } else {
            this.handleError(res.data.msg);
          }
        })
        .catch(err => {
          this.handleError(err);
        });
        if (this.orgList.length > 0) {
        this.currentDeptId = this.orgList[0].id;
      }
  },
    setAddress(d) {
      this.address.recAddressId = d.id;
      this.address.recLinkman = d.lxr;
      this.address.recLinkmanPhone = d.lxrPhone;
      this.address.recAddress = (
        "" +
        d.province +
        d.city +
        d.area +
        d.address
      ).replace(/null/g, "");
    },
     

  },

  mounted () {

      this.getStockList();
      this.querySurPkgLst(1);
      this.getCKLXDict();
      this.getOrgList();

  },
 
}
</script>

<style scoped>
  .inline-display {
    display: inline-block;
  }
  .barcode-input {
    width: 20%;
  }
  .qcr-tableheight{
    height: calc(100% - 30px);
 }
</style>
