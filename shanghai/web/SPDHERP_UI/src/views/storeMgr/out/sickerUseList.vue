<template>
  <el-row id="sickerUse-view" class="full-height">
    <el-row style="height:100%" v-show="!dialogTableVisible">
      <el-row class="toolbar">
          <el-form :inline="true" ref="sickerUseView"> 
            <el-form-item label="执行科室:">
                <orgTreeSlt v-model="sickerUseView.execDeptId" :hos-id="user.corpId"></orgTreeSlt>
            </el-form-item>
           <el-form-item label="登记时间:">
             <div class="block">
                <el-date-picker
                  v-model="sickerUseView.startDate"
                  type="daterange"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  :default-time="['00:00:00', '23:59:59']">
                </el-date-picker>
              </div>
           </el-form-item>
            <el-form-item label="执行医生:">
                <el-input v-model="sickerUseView.execDoctor" placeholder="请填写执行医生" ></el-input> 
            </el-form-item>
           
            <el-form-item label="执行护士:">
                <el-input v-model="sickerUseView.execNurse" placeholder="请填写执行护士" ></el-input> 
            </el-form-item>
            <el-form-item label="手术室:">
                <el-input v-model="sickerUseView.operationRoom" placeholder="请填写手术室" ></el-input> 
            </el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleDlgConfirm()">查询</el-button>
            <el-button type="primary" @click="searchReset('sickerUseView')" >重置</el-button>
          </el-form>
      </el-row>
      <el-table :data="list" style="width: 100%;" class="mytable" border>
        <el-table-column label="操作" width="100">
            <template slot-scope="scope">
                <el-button  @click="getSickerDetail(scope.row.id)" type="text">查看</el-button>
            </template>
        </el-table-column>
        <el-table-column type="index" label="序号" width="35" align="center" ></el-table-column>     
        <el-table-column prop="id" label="id" v-if="false" width="35" align="center" ></el-table-column>     
        <el-table-column prop="hosId" label="医院id" v-if="false" width="35" align="center" ></el-table-column>     
        <el-table-column prop="patientName" label="病人姓名" width="90" align="center" ></el-table-column>     
        <el-table-column prop="patientId" label="病人标识号" width="90" align="center" ></el-table-column>
        <!-- <el-table-column prop="patientInHosId" label="病人本次住院标识号" width="120" align="center" ></el-table-column>      --> -->
        <el-table-column prop="deptName" label="执行科室" width="90"></el-table-column>
        <el-table-column prop="execDoctor" label="执行医生" width="90"></el-table-column>
        <el-table-column prop="execNurse" label="执行护士" width="90"></el-table-column>       
        <el-table-column prop="operationRoom" label="手术室"></el-table-column>
        <el-table-column prop="useDate" label="使用日期" align="center" ></el-table-column>     
        <el-table-column prop="fillerName" label="登记人" width="90"></el-table-column>
        <el-table-column prop="fillDate" label="登记时间"></el-table-column>
        <!-- <el-table-column prop="lastUpdateDatetime" label="最后更新时间" width="90"></el-table-column> -->
      </el-table>
        <el-col :span="24" class="toolbar">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
          </el-pagination>
        </el-col>
    </el-row>
    <el-row style="height:100%" v-show="dialogTableVisible">
        <sickUseV ref="inBillViewer" @close="closeInBillViewer"></sickUseV>
    </el-row>    
  </el-row>
</template>

<script>
import orgTreeSlt from "@/components/orgTreeSlt";
import sickUseV from './sickerUseV';
import moment from 'moment';

export default {
  data() {
    return {
      dialogTableVisible: false,
      baseURL: "/spdHERPService/stockout/sickerUse",
      total: 0,
      page: 1,
      pageSize: 30,
      list: [],
      patientList: [],
      sickerUseView: {
        id: "",
        hosId: "",
        patientId: "",
        patientInHosId: "",
        startDate: "",
        endDate: "",
        useDate: "",
        execNurse: "",
        execDeptId:this.user.orgId,
        operationRoom: "",
        status: "",
        filler: "",
        fillDate: "",
        lastUpdateDatetime: "",
        version: "",
        operationRoom: ""
      }
    };
  },
  components: { orgTreeSlt,sickUseV },
  methods: {
    handleCurrentChange(val) {
      this.page = val;
      this.getSickerUseView(this.page);
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getSickerUseView(this.page);
    },
    searchReset(sickerUseView){
      this.sickerUseView.execDeptId = null;
      this.sickerUseView.patientId = null;
      this.sickerUseView.startDate= "";
      this.sickerUseView.endDate= "";
      this.sickerUseView.execDoctor= null;
      this.sickerUseView.execNurse= null;
      this.sickerUseView.operationRoom= null;
    },
    getSickerDetail(billId){
      this.$refs.inBillViewer.show(billId);
      this.dialogTableVisible = true;
    },
    closeInBillViewer(){
    this.dialogTableVisible = false;
    },
    getSickerUseView(pIndex) {
      this.page = pIndex;
      var param = {
        page: this.page,
        rows: this.pageSize,
        queryObject:{
        operationRoom: this.sickerUseView.operationRoom,
        patientId: this.sickerUseView.patientId,
        execDeptId: this.sickerUseView.execDeptId,
        fillerName: this.sickerUseView.fillerName,
        useDate: this.sickerUseView.useDate,
        sDate: this.sickerUseView.startDate,
        eDate: this.sickerUseView.endDate,
        sDate: this.sickerUseView.startDate[0],
        eDate: this.sickerUseView.startDate[1],
        // useDate: this.sickerUseView.useDate!=null?moment(this.sickerUseView.useDate).format("YYYY-MM-DD"):null,
        execDoctor: this.sickerUseView.execDoctor,
        execNurse: this.sickerUseView.execNurse,
        }
      };
      this.axios.post(this.baseURL + "/list", param).then(
        res => {
          this.list = res.data.data.data;
          this.total = res.data.data.total;
        },
        err => {}
      );
    },
    handleDlgConfirm(){
        this.getSickerUseView(1);
    },
    getPatientList(query) {
      var param = {
        hosId: this.user.corpId,
        patientName: query
      };
      this.axios.post("/spdHERPService/sickerInfo/sickerInfo/list", param).then(
        res => {
           this.patientList = res.data.data;
        },
        err => {}
      );
    }
  },
  mounted() {
    this.getSickerUseView();
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
#sickerUse-view .mytable {
  height: calc(100% - 120px);
}
</style>