<template>
  <el-row id="sick-use" class="full-height">
      <el-row class="toolbar">
          <el-form :inline="true"  ref="sickerBill">
            <el-form-item label="执行科室:" >
                <orgTreeSlt v-model="sickerBill.execDeptId" :hos-id="user.corpId"></orgTreeSlt>
            </el-form-item>
             <el-form-item label="病人标识:" >
                <el-select v-model="sickerBill.patientId" filterable placeholder="请选择病人标识" remote :remote-method="getPatientList">
                    <el-option v-for="item in patientList" :key="item.patientId" :label="item.patientName" :value="item.patientId">
                    </el-option>
                </el-select>
            </el-form-item>
            
            <el-form-item label="登记人:">
                <el-input v-model="sickerBill.fillerName" placeholder="请填写登记人" readonly></el-input> 
            </el-form-item>
            <el-form-item label="使用日期:">
                <el-date-picker v-model="sickerBill.useDate" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="执行医生:">
                <el-input v-model="sickerBill.execDoctor" placeholder="请填写执行医生" ></el-input> 
            </el-form-item>
           
            <el-form-item label="执行护士:">
                <el-input v-model="sickerBill.execNurse" placeholder="请填写执行护士" ></el-input> 
            </el-form-item>
            <el-form-item label="手术室:">
                <el-input v-model="sickerBill.operationRoom" placeholder="请填写手术室" ></el-input> 
            </el-form-item>
            <el-button type="primary" icon="el-icon-success" :disabled="multipleSelection.length===0" @click="handleDlgConfirm()">提交</el-button>

          </el-form>
      </el-row>
      <barcodeResolver :barcodeKind="barcodeKind.BRXH" :hosId="user.corpId"
        :deptId="user.orgId" :userId="user.userId" @resolved="onBarcodeResolved"></barcodeResolver>

      <el-table ref="sickeUserListTable" :data="sickerBill.lstBillDetail" style="width: 100%;" class="mytable" border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="35"></el-table-column>
       <el-table-column label="操作" width="60">
                <template slot-scope="scope">
                    <div class="icoBtn">
                        <i class="fa fa-trash-o fa-lg" @click="delRow(scope.row)" title="删除" style="color:red;margin-left:5px;"></i>
                    </div>
                </template>
            </el-table-column>
      <el-table-column type="index" label="序号" width="35" align="center" ></el-table-column>     
      <el-table-column prop="billId" label="单号" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="goodsId" label="产品id" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="goodsBatchId" label="产品批次" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="batchCode" label="批号" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="provId" label="配送机构" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="uniqueCode" label="唯一码" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="rowNum" label="明细行号" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="purMode" label="采购模式" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="returnQty" label="退货数量" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="outOrgId" label="医院id" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="outBillRow" label="出库单行号" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column prop="outStocId" label="库房id" v-if="false" width="35" align="center" ></el-table-column>     
      <el-table-column label="使用数量"  width="120">
        <template slot-scope="scope">
          <el-input-number style="width:100%;"  v-model="scope.row.qty" :min="0" :max="scope.row.qty"></el-input-number>
        </template>

      </el-table-column>
        <el-table-column prop="goodsName" label="产品名称" width="180" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="goodsGg" label="规格" width="150" show-overflow-tooltip>
        </el-table-column>
        <!-- <el-table-column prop="hisPrice" label="收费单价" width="90">
        </el-table-column> -->
        <el-table-column prop="price" label="单价" width="90">           
        </el-table-column>       
        <el-table-column prop="unit" label="单位" width="90">
        </el-table-column>
        <el-table-column prop="sterilizationDate" label="灭菌日期" width="130">
        </el-table-column>
        <el-table-column prop="sterilizationEndDate" label="灭菌效期" width="130">           
        </el-table-column>
        <el-table-column prop="status" label="状态">           
        </el-table-column>
        </el-table>
        <!-- <el-col :span="24" class="toolbar">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
          </el-pagination>
        </el-col> -->
  </el-row>
</template>

<script>
import orgTreeSlt from "@/components/orgTreeSlt";
import barcodeResolver from '@/views/inStockMgr/common/barcodeResolver';
import {BARCODEKIND,BARCOD_RES_TYPE} from '@/views/inStockMgr/common/constance';
export default {
  data() {
    return {
      baseURL: "/spdHERPService/stockMgr/out4In",
      barcode: "",
      barcodeKind:BARCODEKIND,
      multipleSelection: [],
      // total: 100,
      // page: 1,
      // pageSize: 30,
      lstDetail: [],
      resultRows:{},
      patientList: [],
      sickerBill: {
        id: this.user.corpId,
        execDeptId: this.user.orgId,
        patientId: "",
        patientName: "",
        execDoctor: "",
        execNurse: "",
        useDate: new Date(),
        operationRoom: "",
        fillerName: this.user.ename,
        filler: this.user.userId,
        lstBillDetail: []
      }
    };
  },
  components: { orgTreeSlt,barcodeResolver },
  methods: {
    onBarcodeResolved(barKind,data){
      if(barKind == BARCOD_RES_TYPE.ZBM){
          this.getOutBill4UseList(data);
      }
    },

    delRow(pd) {
            var indexRow = this.sickerBill.lstBillDetail.findIndex(item => { return item.index == pd.index });
            this.sickerBill.lstBillDetail.splice(indexRow, 1);
            this.sickerBill.lstBillDetail.forEach((item, i) => item.index = i + 1);
        },

    getOutBill4UseList(ucode) {   
          var uniqueCd = this.sickerBill.lstBillDetail.findIndex(item => {return item.uniqueCode == ucode});
            if(uniqueCd!=-1){
              this.handleError("该唯一码已被扫描");
            }else{
          var param = {
            queryObject :{
                uniqueCode :ucode
            },
          };
          this.axios.post(this.baseURL + "/getOutBill4Use", param).then(
            res => {
              if(res.data.code == "0"){
                if (res.data.data == null||res.data.data.length ==0) {
                this.handleError("无法识别的唯一码");
              } else{
              this.resultRows = res.data.data;
              this.sickerBill.lstBillDetail.push(this.resultRows);
              this.$refs.sickeUserListTable.toggleRowSelection(this.resultRows)
              }
              }else{
            this.handleError(res.data.msg);
              };
            },
            err => {       
            }
          );
        }    
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    rowsToparam() {
      var sletrows = JSON.parse(JSON.stringify(this.multipleSelection));
      if (sletrows && sletrows.length > 0) {
        var res = [];
        sletrows.forEach(function(item) {
          var row = {};
          row.outBillId = item.billId;
          row.outBillRow = item.outBillRow;
          row.goodsId = item.goodsId;
          row.outOrgId = item.outOrgId;
          row.outStocId = item.outStocId;
          row.provId = item.provId;
          row.purMode = item.purMode;
          row.rowNum = item.rowNum;
          row.returnQty = item.returnQty;
          row.goodsName = item.goodsName;
          row.goodsGg = item.goodsGg;
          row.hisPrice = item.hisPrice;
          row.price = item.price;
          row.batchCode = item.batchCode;
          row.goodsBatchId = item.goodsBatchId;
          row.unit = item.unit;
          row.useQty = item.qty;
          row.sterilizationDate = item.sterilizationDate;
          row.sterilizationCode = item.sterilizationCode;
          row.sterilizationEndDate = item.sterilizationEndDate;
          row.expdtEndDate = item.expdtEndDate;
          row.masterCode = item.masterCode;
          row.secCode = item.secCode;
          row.hibcCode = item.hibcCode;
          row.epcCode = item.epcCode;
          row.selfCode = item.uniqueCode;
          row.uniqueCode = item.uniqueCode;
          res.push(row);
        });
        this.lstDetail = res;
      } 
    },
    handleDlgConfirm() {
      this.rowsToparam();
      var params = {
        hosId: this.user.corpId,
        patientId: this.sickerBill.patientId,
        patientInHosId: "",
        useDate: new Date(),
        execDoctor: this.sickerBill.execDoctor,
        execNurse: this.sickerBill.execNurse,
        execDeptId: this.sickerBill.execDeptId,
        operationRoom: this.sickerBill.operationRoom,
        status: 20,
        filler: this.sickerBill.filler,
        fillDate: new Date(),
        lastUpdateDatetime: new Date(),
        version: 0,
        lstDetail: ([] = this.lstDetail)
      };

      this.axios
        .post("/spdHERPService/stockout/sickerUse/add", params)
        .then(res => {
          if (res.data.code == "0") {
            this.$message({ message: "操作成功！", type: "success" });
            this.$router.push({ path: 'sickerUseList'})
          } else {
            this.handleError(res.data.msg);
          }
        });
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
    getPatientList(query) {
      var param = {
        hosId: this.user.corpId,
        patientName: query
      };
      this.axios.post("/spdHERPService/sickerInfo/sickerInfo/list", param).then(
        res => {
           this.states = res.data.data;
           this.patientList = this.states;
        },
        err => {}
      );
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
#sick-use .mytable {
  height: calc(100% - 150px);
}
#sick-use .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}
</style>