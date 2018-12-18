<template>
    <el-col :span="24" style="height:100%;" v-loading="loading" >
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
               <el-col :span="6">
                       <el-date-picker
                          v-model="filters.rDate"
                          type="daterange"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期" :unlink-panels="true"
                          :default-time="['00:00:00', '23:59:59']">
                        </el-date-picker>
                </el-col>  
                <el-col :span="5">                    
                    <el-select v-model="filters.psdzt">
                      <el-option key="10" label="未验收" :value="10"></el-option>
                      <el-option key="25" label="验收中" :value="25"></el-option>
                      <el-option key="30" label="已验收" :value="30"></el-option>                    
                    </el-select>
                </el-col>  
                <el-col :span="4">
                    <el-input v-model="filters.qybm" placeholder="企业编码或企业名称" ></el-input>
                </el-col>   
                <el-col :span="4">
                    <el-input v-model="filters.psdh" placeholder="配送单号" ></el-input>
                </el-col>                
                <el-col :span="2">
                    <el-button type="primary" icon="search" @click="getBillLst(1)" >搜索</el-button>
                </el-col>
        </el-col>
        <!--列表-->
        <el-table :data="billList" highlight-current-row style="width: 100%;"  fit class="t_ygSendBill">
            <el-table-column label="操作" width="50">
                <template slot-scope="scope">
                    <el-button  @click="sendBillView(scope.row.psdbh)" type="text">查看</el-button>
                </template>
            </el-table-column>
             <el-table-column label="配送单编号" prop="psdbh" ></el-table-column>
            <el-table-column label="配送单号" prop="psdh" ></el-table-column>
            <el-table-column label="企业编码" prop="qybm" ></el-table-column>
            <el-table-column label="企业名称" prop="qymc" width="250" >
                <template slot-scope="scope">
                    <span v-if="scope.row.qymc">{{scope.row.qymc}}</span>
                    <a v-else href="javascript:void(0)" title="点击更新供应商信息" @click="showComInfo(scope.row.qybm)">{{scope.row.qybm}}</a>
                </template>
            </el-table-column>
            <el-table-column label="行数" prop="psmxts" ></el-table-column>
            <el-table-column label="状态" prop="psdzt" :formatter="mfm_state"></el-table-column>
        </el-table>
       <!--分页条-->
        <el-col :span="24" class="toolbar">
            <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="filters.page" :page-sizes="[30, 100, 200, 400]" :page-size="filters.pageSize" layout="total, sizes, prev, pager, next" :total="filters.total" style="float:right;" small>
            </el-pagination>
        </el-col>  
         <ygSendBillView :billid="billId" :sendShow="confirmViewVisible" v-on:viewClose="viewClose"></ygSendBillView>
         <el-dialog title="企业信息维护" :visible.sync="dialogVisible" >
            <saveProInfo :com-code="qybm"></saveProInfo>
        </el-dialog>
    </el-col>    
</template>
<script>
import moment from "moment";
//import {sendBill}  from '@/config/mockData';
import { YG_SENDBILLL_STATUS } from "./common/constance";
import ygSendBillView from "./ygSendBillView.vue";
import saveProInfo from './saveProInfo';
export default {
  data() {
    return {
      filters: {
        rDate: [],
        psdzt:10,
        qsrq: "",
        jzrq: "",
        qybm: "",
        psdh: "",
        total: 0,
        page: 1,
        pageSize: 30
      },
      loading: false,
      dialogVisible:false,
      pickerOptions2: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      billId: "",
      billList: [],
      comInfo:[],
      sltloading:false,
      confirmViewVisible: false,
      qybm: ""
    };
  },
  components: { ygSendBillView ,saveProInfo},
  methods: {
      showComInfo(qybm){
          this.qybm = qybm;
          this.dialogVisible =true;
      },
    querySearchAsync(queryString, cb) {
        if(!queryString){
            return;
        }
        this.sltloading = true;
        var _this = this;
        this.axios.get("/spdHERPService/yg/ygSend/getYgComLst/"+queryString).then(res=>{         
        if(res.data.code == "0"){
            _this.sltloading = false;
            _this.comInfo = res.data.data;
        }
      },err=>{this.detailLoading = false;});
      
    },
    handleSelect() {},
    viewClose(flag) {
      this.billId = "";
      this.confirmViewVisible = flag;
    },
    //获取列表
    getBillLst: function(index) {
      var _this = this;
      _this.billList = [];
      var param = {
        orderBy: "",
        page: this.filters.page,
        rows: this.filters.pageSize,
        queryObject: {
          qsrq:moment(this.filters.rDate[0]).format("YYYYMMDD"),
          jzrq:moment(this.filters.rDate[1]).format("YYYYMMDD"),
          qybm: this.filters.qybm,
          psdh: this.filters.psdh,
          psdzt:this.filters.psdzt
        }, 
      };

      this.loading = true;
      this.axios.post("/spdHERPService/yg/ygSend/getSendBillLst", param).then(
        res => {
          _this.loading = false;
          if (res.data.code == "0") {
            _this.billList = res.data.data.data;
            this.filters.total = res.data.data.total;
          }
        },
        err => {
          _this.loading = false;
        }
      );
    },
    sCurrentChange(val) {
      this.filters.page = val;
      this.getBillLst(this.filters.page);
    },
    sSizeChange(val) {
      this.filters.pageSize = val;
      this.getBillLst(this.filters.page);
    },
    sendBillView: function(billId) {
      this.billId = billId;
      this.confirmViewVisible = true;
    },
    mfm_state(r, c, v) {
      if (v) {
        return YG_SENDBILLL_STATUS[v.toString()];
      }
      return "";
    }
  },
  mounted() {
    var end = new Date();
    var start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
    this.filters.rDate = [start, end];
    this.getBillLst(1);
  }
};
</script>
<style>
.t_ygSendBill {
  font-size: 12px;
  height: calc(100% - 85px);
  overflow-y: auto;
}
</style>

