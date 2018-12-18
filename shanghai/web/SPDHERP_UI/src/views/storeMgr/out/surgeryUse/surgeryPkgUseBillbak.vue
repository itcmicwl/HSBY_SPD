<template>
  <el-row id="surgery-pkg-use" class="full-height">
      <el-row class="toolbar">
          <el-form :inline="true"  ref="surgeryPkgBill">
            <el-form-item label="执行科室:" >
                <orgTreeSlt v-model="surgeryPkgBill.execDeptId" :hos-id="user.corpId"></orgTreeSlt>
            </el-form-item>
             <el-form-item label="病人标识:" >
                <el-select v-model="surgeryPkgBill.patientId" filterable placeholder="请选择病人标识"
                   remote :remote-method="getPatientList" @change="getSurgerPkg">
                    <el-option v-for="item in patientList" :key="item.patientId" :label="item.patientName" :value="item.patientId">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="登记人:">
                <el-input v-model="surgeryPkgBill.fillerName" placeholder="请填写登记人" readonly></el-input> 
            </el-form-item>
            <el-form-item label="使用日期:">
                <el-date-picker v-model="surgeryPkgBill.useDate" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="执行医生:">
                <el-input v-model="surgeryPkgBill.execDoctor" placeholder="请填写执行医生" ></el-input> 
            </el-form-item>
           
            <el-form-item label="执行护士:">
                <el-input v-model="surgeryPkgBill.execNurse" placeholder="请填写执行护士" ></el-input> 
            </el-form-item>
            <el-form-item label="手术室:">
                <el-input v-model="surgeryPkgBill.operationRoom" placeholder="请填写手术室" ></el-input> 
            </el-form-item>
            <el-button type="primary" icon="el-icon-success" :disabled="pkgMultipleSelection.length===0" @click="handleDlgConfirm()">提交</el-button>

          </el-form>
      </el-row>
      <barcodeResolver :barcodeKind="barcodeKind.BRXH" :hosId="user.corpId"
        :deptId="user.orgId" :userId="user.userId" @resolved="onBarcodeResolved"></barcodeResolver>

      <el-table ref="sickeUserListTable" :data="surgeryPkgBill.lstBillDetail" style="width: 100%;" class="sickeUserListTable" border
       @selection-change="handleSickerPkgSelectionChange" 
       @select-all="pkgSelectdAllRow" @select="pkgSelectedRow">
      <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column prop="surCode" label="手术包号" width="200"></el-table-column>
        <el-table-column prop="surName" label="手术包名称" width="300" show-overflow-tooltip></el-table-column>
        <el-table-column prop="sickerName" label="消耗病人" ></el-table-column>       
        <el-table-column prop="status" label="状态"  width="80" align="center"></el-table-column>
        <el-table-column prop="packerName" label="打包人" ></el-table-column> 
        <el-table-column prop="packageDate" label="打包时间" ></el-table-column>     
        </el-table-column>
      </el-table>

      <el-table ref="surgeryPkg4UseListVoTable" :data="surgeryPkg4UseListVo" style="width: 100%;" class='surgeryPkg4UseListVoTable'
        border @selection-change="subTableRowSelectionChange" @select="subTableRowSelected"
         @select-all="pkgGoodsSelectdAllRow" ><!--:span-method="rowSpanMethod"-->
      <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column prop="surCode" label="手术包号" width="150"></el-table-column>
        <el-table-column prop="goodsName" label="商品名称"   show-overflow-tooltip></el-table-column>
        <el-table-column prop="goodsGg" label="规格型号"  width="220"  show-overflow-tooltip></el-table-column>
        <el-table-column prop="batchCode" label="批号" width="200" ></el-table-column> 
        <el-table-column prop="qty" label="包数量" width="80" align="center"></el-table-column>
        <el-table-column prop="consumedQty" label="已消耗数" width="80" align="center"></el-table-column>
        <el-table-column prop="useQty" label="可消耗数" width="120" align="center">
          <template slot-scope="scope">
            <el-input-number placeholder="请输入数量"  v-model="scope.row.useQty"  :min=0  :max="scope.row.qty-scope.row.consumedQty" 
              class="col-input-num">
            </el-input-number>
          </template> 
        </el-table-column>     
        <el-table-column prop="unit" label="单位" width="80" align="center"></el-table-column> 
      </el-table>
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
      pkgMultipleSelection: [],//已经选中的手术包 
      pakGoodsSelecttion:[],//已经选中的手术包商品明细
      pkgLstDetail: [],//提交的包列表
      resultRows:{},
      patientList: [],
      surgeryPkgBill: {
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
      },
      surgeryPkg4UseListVo:[],
      surgeryPkg4UseBatchVo:[],

    };
  },
  components: { orgTreeSlt,barcodeResolver },
  methods: {
    onBarcodeResolved(barKind,data,barcode){
      if(barKind == BARCOD_RES_TYPE.ZBM){
          this.getOutBill4UseList(data);
      }else if(barKind == BARCOD_RES_TYPE.SSB){

      }
    },

    getOutBill4UseList(ucode) {   
          var uniqueCd = this.surgeryPkgBill.lstBillDetail.findIndex(item => {return item.uniqueCode == ucode});
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
              this.surgeryPkgBill.lstBillDetail.push(this.resultRows);
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
    //手术包选中行变更事件
    handleSickerPkgSelectionChange(val) {
      this.pkgMultipleSelection = val;
    },
    //手术包全选事件
    pkgSelectdAllRow(allRows){
      let that = this;
      if (allRows.length == 0) {
        //取消全选
          this.surgeryPkg4UseListVo.forEach(pkg =>{
          this.$refs.surgeryPkg4UseListVoTable.toggleRowSelection(pkg, false);
        })
      }else{
        this.$refs.surgeryPkg4UseListVoTable.toggleAllSelection();
      }
    },
    //手术包单行选中或取消选中事件
    pkgSelectedRow(selection, row){
      this.$nextTick(function() {
        let flag = selection.find(o => o.surCode == row.surCode); //判断是否选中
        if (flag) {
          row.surgeryPkg4UseListVo.forEach(item => {
            this.$refs.surgeryPkg4UseListVoTable.toggleRowSelection(item, true);
          });
        } else {
          row.surgeryPkg4UseListVo.forEach(item => {
            this.$refs.surgeryPkg4UseListVoTable.toggleRowSelection(item, false);
          });
        }
      });
    },
    //手术包商品明细 选中行 变更事件
    subTableRowSelectionChange(selection){
      this.pakGoodsSelecttion = selection;
    },
    //手术包商品明细行选中或取消选中 事件
    subTableRowSelected(selection, row){
      let surCodeStr = '';//选中明细的包号
      this.pakGoodsSelecttion.forEach(pkgGoods=>{surCodeStr += pkgGoods.surCode+','});
      let flag = selection.find(o => o.id == row.id); //判断是否选中
      if (flag) {//子表任意一行选中，父表对应手术包行选中
        this.surgeryPkgBill.lstBillDetail.forEach(pkg =>{
          if(surCodeStr.indexOf(pkg.surCode)>-1){
            this.$refs.sickeUserListTable.toggleRowSelection(pkg, true);
          }
        })
      } else {//子表某一包里的所有明细取消选中，父级对应手术包行取消选中
        this.pkgMultipleSelection.forEach(pkg =>{
          if(surCodeStr.indexOf(pkg.surCode)==-1){
            this.$refs.sickeUserListTable.toggleRowSelection(pkg, false);
          }
        })
      }
    },
    //手术包商品消耗行全选事件
    pkgGoodsSelectdAllRow(allRows){
      if (allRows.length == 0) {
        //取消全选
        this.surgeryPkgBill.lstBillDetail.forEach(pkg =>{
          this.$refs.sickeUserListTable.toggleRowSelection(pkg, false);
        })
      }else{
        this.surgeryPkgBill.lstBillDetail.forEach(pkg =>{
          this.$refs.sickeUserListTable.toggleRowSelection(pkg, true);
        })
      }
    },
    //合并行事件
    rowSpanMethod({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 1) {
        if (rowIndex % 2 === 0) {
          return {
            rowspan: 2,
            colspan: 1
          };
        } else {
          return {
            rowspan: 0,
            colspan: 0
          };
        }
      }
    },
    //构建参数
    rowsToparam() {
      let sletPkgRows = JSON.parse(JSON.stringify(this.pkgMultipleSelection));
      let sletPkgGoodsListRows = JSON.parse(JSON.stringify(this.pakGoodsSelecttion));
      if (sletPkgRows && sletPkgRows.length > 0) {
        let res = [];
        sletPkgRows.forEach(function(item) {
          item.surgeryPkg4UseListVo = [];
          sletPkgGoodsListRows.forEach(pkgGoods=>{
            if(item.surCode===pkgGoods.surCode){
              item.surgeryPkg4UseListVo.push(pkgGoods);
            }
          });
          res.push(item);
        });
        this.pkgLstDetail = res;
      } 
    },
    //提交手术包病人消耗单
    handleDlgConfirm() {
      this.rowsToparam();
      var params = {
        hosId: this.user.corpId,
        patientId: this.surgeryPkgBill.patientId,
        patientInHosId: "",
        useDate: new Date(),
        execDoctor: this.surgeryPkgBill.execDoctor,
        execNurse: this.surgeryPkgBill.execNurse,
        execDeptId: this.surgeryPkgBill.execDeptId,
        operationRoom: this.surgeryPkgBill.operationRoom,
        status: 20,
        filler: this.surgeryPkgBill.filler,
        fillDate: new Date(),
        lastUpdateDatetime: new Date(),
        version: 0,
        pkgLstDetail: ([] = this.pkgLstDetail)
      };
      this.axios.post("/spdHERPService/stockMgr/out/surgeryPkgOut/add", params)
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
    },
    getSurgerPkg(val){
      let patientName = '' ;
      if(this.patientList.length>0){
         patientName = this.patientList.find(item => item.patientId==val).patientName;
      }else{
        patientName = val;
      }
      let surgeryPkg4UseVo = {
          hosId: this.user.corpId,
          sickerName: patientName,
          deptId:this.user.orgId,
          //status:40  //科室已经出库
      };
      this.axios.post("/spdHERPService/stockMgr/out/surgeryPkgOut/querySickerSurPkg", surgeryPkg4UseVo).then(
        res => {
          this.surgeryPkg4UseListVo = [];
          this.surgeryPkgBill.lstBillDetail = res.data.data;
          this.surgeryPkgBill.lstBillDetail.forEach(item=>
             {item.surgeryPkg4UseListVo.forEach(vo=>this.surgeryPkg4UseListVo.push(vo))
          });
        },
        err => {}
      );
    },

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
#surgery-pkg-use .sickeUserListTable {
  height: calc(100% - 400px);
}
#surgery-pkg-use .surgeryPkg4UseListVoTable {
  height: calc(100% - 250px);
}
#surgery-pkg-use .surgeryPkg4UseBatchVoTable {
  height: calc(100% - 400px);
}
#surgery-pkg-use .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}
</style>