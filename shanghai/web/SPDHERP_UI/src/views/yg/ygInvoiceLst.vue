<template>
    <el-col :span="24" style="height:100%;" id="ygInvP" v-loading="loading">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
             <el-form label-width="80px" label-position="left">
                <el-col :span="4">
                    <el-form-item label="企业编码：">
                        <el-input v-model="filters.qybm"  placeholder="企业编码" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="日期：">
                        <el-date-picker v-model="filters.rDate" type="daterange" align="right" placeholder="选择日期范围" :picker-options="pickerOptions2" >
                        </el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="发票代码">
                        <el-input v-model="filters.fpdm" placeholder="配送单号" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="发票号">
                        <el-input v-model="filters.fph" placeholder="配送单号" ></el-input>
                    </el-form-item>
                </el-col>   
                <el-col :span="2">
                    <el-button type="primary" icon="search" @click="getBillLst(false)" >搜索</el-button>
                </el-col>
            </el-form>
        </el-col>
        <!--列表-->
        <el-table :data="billList" highlight-current-row  style="width: 100%;"  fit class="table_content">
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button  @click="showFPYS_f(scope.row)" type="text">验收</el-button>
                    <el-button  @click="sendBillView(scope.row.fpbh)" type="text">查看</el-button>
                </template>
            </el-table-column>
             <el-table-column label="发票编号" prop="fpbh" width="120" ></el-table-column>
            <el-table-column label="发票代码" prop="fpdm" width="120"></el-table-column>
            <el-table-column label="发票号" prop="fph" width="120"></el-table-column>
            <el-table-column label="发票日期" prop="fprq" width="80"></el-table-column>
            <el-table-column label="发票含税总金额" prop="fphszje"></el-table-column>
            <el-table-column label="配送点编码" prop="psdbm"></el-table-column>
            <el-table-column label="采购类型" prop="cglx" :formatter="mfm_purKind"></el-table-column>
            <el-table-column label="发票备注" prop="fpbz"></el-table-column>
            <el-table-column label="发票明细条数" prop="fpmxts"></el-table-column>
            <el-table-column label="发票状态" prop="fpzt" :formatter="mfm_state"></el-table-column>
        </el-table>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <span>记录数：{{billList.length}}条</span>
            <el-button  @click="getBillLst(true)" v-if="isAll=='0'" type="primary" style="float:right">加载更多</el-button>
        </el-col>
         <ygInvoiceView :billid="billId" :sendShow="confirmViewVisible" v-on:viewClose="viewClose"></ygInvoiceView>
         <el-dialog title="发票验收" :visible.sync="checkIn_v" :close-on-click-modal="false" width="40%">
            <el-form ref="f_YY132Req" :model="YY132Req" label-width="110px">
                <el-form-item label="发票ID：">
                    <el-input v-model="YY132Req.fpid" disabled></el-input>
                </el-form-item>
              <el-form-item label="发票代码：">
                    <el-input v-model="YY132Req.fpdm" disabled></el-input>
                </el-form-item>
               <el-form-item label="发票号：">
                    <el-input v-model="YY132Req.fph" disabled></el-input>
                </el-form-item>
                <el-form-item label="企业编码：">
                    <el-input v-model="YY132Req.qybm" disabled></el-input>
                </el-form-item>
                <el-form-item label="发票验收结果：">
                    <el-switch v-model="YY132Req.fpysjg" active-color="#13ce66" inactive-color="#ff4949"
                        active-text="通过"  inactive-text="不通过"
                        active-value="1" inactive-value="0"></el-switch>
                </el-form-item>
                <el-form-item label="发票含税总金额：">
                   <el-input v-model="YY132Req.fphszje"></el-input>
                </el-form-item>                
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="fpCheckIn()"  v-loading="fbLoading">验收</el-button>
                <el-button @click="checkIn_v=false" >关闭</el-button>
            </div>
        </el-dialog>  
    </el-col>    
</template>
<script>
import moment from "moment";
//import {invoiceLst}  from '@/config/mockData';
import { YG_INV_STATUS, YG_PUR_KIND } from "./common/constance";
import ygInvoiceView from "./ygInvoiceView.vue";
export default {
  data() {
    return {
      filters: {
        rDate: [],
        qybm: "",
        fpdm: "",
        fph: ""
      },
      YY132Req: {
        fpid: "",
        fpdm: "",
        fph: "",
        qybm: "",
        fpysjg: "0",
        fphszje: ""
      },
      isAll: "1", //0未完结，1完结
      lastNo: "",
      loading: false,
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
      hasNext: false,
      checkIn_v: false,
      confirmViewVisible: false,
      fbLoading: false
    };
  },
  components: { ygInvoiceView },
  methods: {
    viewClose(flag) {
      this.billId = "";
      this.confirmViewVisible = flag;
    },
    //获取列表
    getBillLst: function(addMore) {
      var _this = this;
      var param = {
        qsrq: moment(this.filters.rDate[0]).format("YYYYMMDD"),
        jzrq: moment(this.filters.rDate[1]).format("YYYYMMDD"),
        qybm: this.filters.qybm,
        fpdm: this.filters.fpdm,
        fph: this.filters.fph
      };
      if (!addMore) {
        _this.billList = [];
      } else {
        param.fpbhcxtj = _this.lastNo;
      }
      this.loading = true;
      this.axios
        .post("/spdHERPService/yg/invoice/getInvoiceBillLst", param)
        .then(
          res => {
            _this.loading = false;
            if (res.data.code == "0") {
              _this.billList =  _this.billList.concat(res.data.data.detail);
              _this.isAll = res.data.data.main.sfwj;
              _this.lastNo = res.data.data.main.dczhfpbh;
            }
          },
          err => {
            _this.loading = false;
          }
        );
    },
    sendBillView: function(billId) {
      this.billId = billId;
      this.confirmViewVisible = true;
    },
    mfm_state(r, c, v) {
      if (v) {
        return YG_INV_STATUS[v.toString()];
      } else {
        return "";
      }
    },
    mfm_purKind(r, c, v) {
      if (v) {
        return YG_PUR_KIND[v.toString()];
      } else {
        return "";
      }
    },
    showFPYS_f(row) {
      this.YY132Req = {
        fpid: row.fpbh,
        fpdm: row.fpdm,
        fph: row.fph,
        qybm: row.qybm,
        fpysjg: "1",
        fphszje: row.fphszje
      };
      this.checkIn_v = true;
    },
    fpCheckIn() {
      var _this = this;
      this.fbLoading = true;
      var param = Object.assign({}, this.YY132Req);
      this.axios.post("/spdHERPService/yg/invoice/invoiceConfirm", param).then(
        res => {
          _this.fbLoading = false;
          if (res.data.code == "0") {
              if(res.data.data.repHead.res !="00000"){
                    _this.$msgbox({type: "error",title: '错误',
                    message: res.data.data.repHead.errMsg
                });
              }else{
                   _this.$msgbox({type: "success",title: '成功',message: '操作成功',callback:function(){
                        _this.checkIn_v=false;
                    }});
              }
          } else {
            _this.fbLoading = false;
            _this.$msgbox({
              title: "错误",
              type: "error",
              message: res.data.msg
            });
          }
        },
        err => {
          _this.fbLoading = false;
        }
      );
    }
  },
  mounted() {
    var end = new Date();
    var start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
    this.filters.rDate = [start, end];
    this.getBillLst(false);
  }
};
</script>
<style>
#ygInvP .table_content {
  font-size: 12px;
  height: calc(100% - 80px);
  overflow-y: auto;
}
</style>

