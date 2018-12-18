<template>
    <el-col :span="24" style="height:100%;" v-loading="loading" >
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
          <el-col :span="5">
              <el-date-picker
                          v-model="filters.rDate"
                          type="daterange"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期" :unlink-panels="true"
                          :default-time="['00:00:00', '23:59:59']">
                </el-date-picker>
          </el-col>                        
          <el-col :span="2">
              <el-button type="primary" icon="search" @click="importSendBill()" >开始导入</el-button>
          </el-col>
          <el-col :span="4">
              <span>共{{stateInfo.total}}条数据，已处理{{stateInfo.cur}}条</span>
          </el-col>
          <el-col :span="13">
               <el-progress :text-inside="true" :stroke-width="18" :percentage="stateInfo.percent"></el-progress>
          </el-col>
        </el-col>
       <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-tag type="primary" v-for="item in stateInfo.billList" :key="item" style="margin-right:5px;">
                    {{item}}
                </el-tag>
            </el-col>
    </el-col>    
</template>
<script>
import moment from "moment";
import { YG_SENDBILLL_STATUS } from "./common/constance";
import ygSendBillView from "./ygSendBillView.vue";
export default {
  data() {
    return {
      invId: null,
      filters: {
        rDate: []
      },
      loading: false,
      stateInfo: {
        cur: 0,
        total: 0,
        empty: true,
        billId: "",
        percent: 0,
        billList: []
      },
      pickerOptions2:{
        firstDayOfWeek:1
      }
    };
  },
  methods: {
    importSendBill: function() {
      var _this = this;
      if (this.filters.rDate.length == 0) {
        this.$message.error("请选择时间段");
      }
      var param = {
        qsrq:moment(this.filters.rDate[0]).format("YYYYMMDD"),
        jzrq:moment(this.filters.rDate[1]).format("YYYYMMDD"),
      };
      this.axios.post("/spdHERPService/yg/ygSend/importSendBill", param).then(
        res => {
          if (res.data.code == "0") {
            if (res.data.data === false) {
              //有正在执行的任务
              _this.$message.error("当前有正在执行的任务，请稍后再试");
            } else {
              _this.startInv();
            }
          }
        },
        err => {}
      );
    },
    mfm_state(r, c, v) {
      if (v) {
        return YG_SENDBILLL_STATUS[v.toString()];
      }
      return "";
    },
    startInv() {
      if (this.invId) {
        return;
      }
      this.invId = setInterval(this.getImportState, 1000 * 5);
    },
    clearInv() {
      clearInterval(this.invId);
      this.invId = null;
    },
    getImportState() {
      var _this = this;
      this.axios.get("/spdHERPService/yg/ygSend/getImportState").then(
        res => {
          if (res.data.code == "0") {
            var state = res.data.data;
            if (state.empty === true) {
              _this.clearInv();
            } else {
              _this.stateInfo.cur = state.cur;
              _this.stateInfo.total = state.total;
              _this.stateInfo.empty = state.empty;
              _this.stateInfo.billId = state.billId;
              _this.stateInfo.percent = Number(
                (
                  _this.stateInfo.cur /
                  (_this.stateInfo.total || 1) *
                  100
                ).toFixed(2)
              );
              _this.stateInfo.billList = state.billLst;
            }
          }
        },
        err => {}
      );
    }
  },
  mounted() {
    var end = new Date();
    var start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
    this.filters.rDate = [start, end];
    this.startInv();
  },
  destroyed: function() {
    this.clearInv();
  }
};
</script>
<style>
/* .t_ygSendBill{
     font-size: 12px;
    height: calc(100% - 85px);
    overflow-y: auto;
} */
</style>

