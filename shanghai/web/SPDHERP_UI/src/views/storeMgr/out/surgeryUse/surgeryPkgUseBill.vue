<template>
  <el-row id="surgery-pkg-use" class="full-height">
      <el-row class="toolbar">
          <el-form :inline="true"  ref="sickerUseBill">
            <el-form-item label="执行科室:" >
                <orgTreeSlt v-model="sickerUseBill.execDeptId" :hos-id="user.corpId"></orgTreeSlt>
            </el-form-item>
             <el-form-item label="病人标识:" >
                <el-select v-model="sickerUseBill.patientId" ref='slt_sicker' filterable placeholder="请选择病人标识"
                   remote :remote-method="getPatientList" @change="onPatientChane">
                    <el-option v-for="item in patientList" :key="item.patientId" :label="item.patientName" :value="item.patientId">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="使用日期:">
                <el-date-picker v-model="sickerUseBill.useDate" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="执行医生:">
                <el-input v-model="sickerUseBill.execDoctor" placeholder="请填写执行医生" ></el-input> 
            </el-form-item>
           
            <el-form-item label="执行护士:">
                <el-input v-model="sickerUseBill.execNurse" placeholder="请填写执行护士" ></el-input> 
            </el-form-item>
            <el-form-item label="手术室:">
                <el-input v-model="sickerUseBill.operationRoom" placeholder="请填写手术室" ></el-input> 
            </el-form-item>

          </el-form>
      </el-row>
      <barcodeResolver :barcodeKind="barcodeKind.BRXH" :hosId="user.corpId"
        :deptId="user.orgId" :userId="user.userId" @resolved="onBarcodeResolved"></barcodeResolver>
       <el-row style="height:calc(100% - 120px);" :gutter="5">
          <el-col :span="8"  style="height:100%">
                <el-table ref="tbl_surPkg" :data="surgeryBilLst" highlight-current-row style="width: 100%;" @row-click="onSurChange" fit border height="100%">
                    <el-table-column prop="surName" label="手术包名称" ></el-table-column>
                    <el-table-column prop="surCode" label="手术包码" ></el-table-column>
                    <el-table-column label="状态" prop="status" sortable></el-table-column>
                    <el-table-column label="消毒情况" prop="status" sortable></el-table-column>
                </el-table>                
          </el-col>
          <el-col :span="16" style="height:100%">
            <el-table ref="tbl_goodsList" :data="curSur.surgeryPkgListVos"  style="width: 100%;"  fit border height="100%">
                <el-table-column type="expand">
                    <template slot-scope="props">                          
                        <table width="100%">
                            <tr><th width="20%">批次号</th><th  width="20%">批号</th><th width="10%">可用数量</th><th width="10%">使用数量</th><th width="10%">条码</th><th width="20%">有效期</th></tr>
                            <tr v-for="item in props.row.lstGoodsBatch" :key="item.id" >
                                <td>{{item.batchId}}</td><td>{{item.batchCode}}</td><td>{{item.qty}}</td>
                                <td><el-input-number v-model="item.useQty" @change="onBatchQtyChange(item,props.row)" :min="0" :max="item.qty" 
                                        :disabled="props.row.uniqueKind<3"></el-input-number>
                                <td>{{item.uniqueCode}}</td><td>{{item.expdtEndDate | dateFormat}}</td>
                            </tr>
                        </table>    
                    </template>
                </el-table-column>
                <el-table-column prop="goodsName" label="品名" sortable></el-table-column>
                <el-table-column prop="takeQty" label="使用数量"></el-table-column> 
                <el-table-column prop="goodsName" label="规格"></el-table-column> 
                <el-table-column prop="made" label="产地" sortable></el-table-column>
                <el-table-column prop="mfrsName" label="厂家"></el-table-column>                    
            </el-table>
          </el-col>
          <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                <div style="text-align: center;">
                    <el-button  type="success" v-loading="submitLoading"  @click="submitSickeBill" icon="el-icon-sold-out" size="small">提交</el-button>
                </div>
        </el-col>
        </el-row>
  </el-row>
</template>

<script>
import orgTreeSlt from "@/components/orgTreeSlt";
import barcodeResolver from '@/views/inStockMgr/common/barcodeResolver';
import {BARCODEKIND,BARCOD_RES_TYPE} from '@/views/inStockMgr/common/constance';
export default {
  data() {
    return {
      barcode: "",
      barcodeKind:BARCODEKIND,
      patientList:[],
      sickerUseBill:{
        hosId:this.user.corpId,
        execDeptId:this.user.orgId,
        patientId:'',
        patientName:'',
        execDoctor:'',
        execNurse:'',
        operationRoom:'',
        useDate:null,
        status:20,
        lstDetail:[]
      },
      surgeryBilLst:[],
      curSur:{},
      submitLoading:false
    };
  },
  components: { orgTreeSlt,barcodeResolver },
  methods: {
    onBarcodeResolved(barKind,data,barcode){
      if(barKind == BARCOD_RES_TYPE.ZBM){
        this.checkUniqueCode(barcode);
      }else if(barKind == BARCOD_RES_TYPE.SSB){
        if(this.surgeryBilLst.find(o=>o.surCode == barcode)){
          this.$message.error('此手术包码已扫描');
          return;
        }
        this.getSurgerPkg(barcode);
      }
    },
    onPatientChane(val){
      this.surgeryBilLst=[];
      this.curSur={};
      if(val){
        this.sickerUseBill.patientName = this.$refs.slt_sicker.selectedLabel;
      }     
    },
    onSurChange(row){
      this.curSur = row;
    },
    onBatchQtyChange(batch,surGoods){
      surGoods.takeQty = surGoods.lstGoodsBatch.map(o=>o.useQty).reduce((cur,next)=>{return cur+next;});
    },
    getPatientList(query) {
      var param = {
          orderBy: "",
          page: 1,
          rows: 20,
          queryObject: {
              hosId: this.user.corpId,
             patientName: query
          }
      };
      this.axios.post("/spdHERPService/sickerInfo/sickerInfo/listByPage", param).then(
        res => {
           this.states = res.data.data.data;
           this.patientList = this.states;
        },
        err => {}
      );
    },
    getSurgerPkg(surCode){
      if(!this.user.corpId || !this.sickerUseBill.patientName || !this.sickerUseBill.execDeptId){
        this.$message.error('   ');
        return;
      }
      var param={
        hosId:this.user.corpId,
        deptId:this.sickerUseBill.execDeptId,
        sickerName:this.sickerUseBill.patientName,
        surCode:surCode
      };
      this.axios.post(`/spdHERPService/surgeryPkg/list4Use`,param).then(res => {
        if(res.data.code == 0 && res.data.data.length > 0)
          this.surgeryBilLst.push(res.data.data[0]);
        },
        err => {}
      );
    },
    checkUniqueCode(barcode){
      for(var i=0;i<this.surgeryBilLst.length;i++){
        var surPkg=this.surgeryBilLst[i];
        for(var j=0;j<surPkg.surgeryPkgListVos.length;j++){
          if(surPkg.surgeryPkgListVos[j].uniqueKind<3){//唯一码管理
            var batch = surPkg.surgeryPkgListVos[j].lstGoodsBatch.find(o=>o.uniqueCode == barcode);
            if(batch){
              this.$refs.tbl_surPkg.setCurrentRow(surPkg);
              this.curSur = surPkg;
              if(batch.useQty>0){
                this.$message.error('此条码已经扫描过了');
                return;
              }else{
                batch.useQty =1;
                surPkg.surgeryPkgListVos[j].takeQty= surPkg.surgeryPkgListVos[j].lstGoodsBatch.map(o=>o.useQty).reduce((cur,next)=>{return cur+next;});
                return;
              }
            }
          }
        }
      }
      this.$message.error('此条码无法解析');
    },
    getSubmitData(){
      var lstDetail=[];
      this.surgeryBilLst.forEach(surPkg=>{
        surPkg.surgeryPkgListVos.forEach(surGoods=>{
          if(surGoods.takeQty>0){
            var detailList=surGoods.lstGoodsBatch.filter(o=>o.useQty>0).map(o=>{
              return {                
                outBillId:o.outBillId,
                outBillRow:o.outBillRow,
                purMode:o.purMode,
                goodsId:surGoods.goodsId,
                goodsName:surGoods.goodsName,
                goodsGg:surGoods.goodsGg,
                price:o.inPrice,
                batchCode:o.batchCode,
                goodsBatchId:o.batchId,
                unit:surGoods.unit,
                returnQty:0,
                useQty:o.useQty,
                sterilizationDate:o.sterilizationDate,
                sterilizationCode:o.sterilizationCode,
                sterilizationEndDate:o.sterilizationEndDate,
                expdtEndDate:o.expdtEndDate,
                provId:o.provId,
                uniqueCode:o.uniqueCode,
                isUsed:0,
                bigBatchCode:o.bigBatchCode,
                surId:surGoods.surCode,
                selfCode:o.uniqueCode
              }
            });
            lstDetail = lstDetail.concat(detailList);
          }
        });
      });
      return lstDetail;
    },
    submitSickeBill(){
      this.submitLoading = true;
      var subimtData = this.getSubmitData();
      this.sickerUseBill.lstDetail = subimtData;
      this.axios.post('/spdHERPService/stockout/sickerUse/add',this.sickerUseBill).then(res=>{
        if(res.data.code == 0){
          this.$message.success('操作成功');
        }else{
          this.$message.error('操作失败');
        }
         this.submitLoading = false;
      },err=>{
         this.$message.error('操作失败');
        this.submitLoading = false;
      });
    }
  },
  mounted() {

  }
};
</script>

<style scoped>
.full-height {
  height: 100%;
}
.text-style {
  padding-left: 20px;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  padding-top: 2px;
}
.el-cascader--mini {
  width: 178px;
}
</style>