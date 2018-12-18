<template>
  <el-dialog title="配送单查看" :visible.sync="sendShow" :close-on-click-modal="false" @close="onClose"  width="90%" >
    <el-row :gutter="10" style="height:100%;">
        <el-table ref="ygSendView" :data="sendBill.lstDetail" v-loading="detailLoading" style="width: 100%;" fit class="t_ygsendView" row-key="psmxbh" @selection-change="sltChange">
          <el-table-column type="selection" width="30" reserve-selection :selectable="rowCanSlt">
            </el-table-column>
            <el-table-column prop="psmxbh" label="配送明细编号" width="160">
            </el-table-column>
            <el-table-column prop="sxh" label="序号" width="50" >
            </el-table-column>           
            <el-table-column prop="xsddh" label="销售订单号">
            </el-table-column>  
            <el-table-column prop="pm" label="品名/医保编码" width="150" show-overflow-tooltip>
              <template slot-scope="scope">
                <span v-if="scope.row.pm" :title="scope.row.hctbdm">{{scope.row.pm}}</span>
                <span v-else>{{scope.row.hctbdm}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="psl" label="配送数量" width="60" :formatter="numFomat">
            </el-table-column> 
            <el-table-column prop="psmxzt" label="状态" :formatter="fm_state"  width="60">
            </el-table-column>
            <!-- <el-table-column prop="ysytgs" label="验收通过数"  width="80" :formatter="numFomat">
            </el-table-column>
            <el-table-column prop="ysybgs" label="验收不通过数"  width="85" :formatter="numFomat">
            </el-table-column> -->
            <el-table-column label="本次验收通过数">
              <template slot-scope="scope">
                <el-input-number v-model="scope.row.ystgs"  :min="0" :max="scope.row.psl-scope.row.ysytgs" label="请输入数字" class="col-input-num"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="本次验收不通过数">
                 <template slot-scope="scope">
                  <el-input-number v-model="scope.row.ysbgs"  :min="0" :max="scope.row.psl-scope.row.ysytgs" label="请输入数字" class="col-input-num" size="mini"></el-input-number>
                </template>
            </el-table-column>           
            <el-table-column label="验收备注">
                <template slot-scope="scope">
                <el-input v-model="scope.row.ysbzsm"   label="请输入数字" ></el-input>
              </template>
            </el-table-column>
            <!--
            <el-table-column prop="ddmxbh" label="采购单明细编号" width="150">
            </el-table-column>
            <el-table-column prop="gg" label="规格" width="90">
            </el-table-column>    
            <el-table-column prop="xh" label="型号">
            </el-table-column>
             <el-table-column prop="dw" label="单位">
            </el-table-column> 
            <el-table-column prop="scqy" label="生产企业名称" width="120">
            </el-table-column>-->
                              
        </el-table>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button @click.native="ysrk()" v-loading="SendCheckLoading" >验收入库</el-button>
      <el-button @click="closeMe()" >关闭</el-button>
    </div>
</el-dialog>       
   
</template>
<script>
//import {billView}  from '@/config/mockData';
import {YG_SENDBILL_DETAIL_STATUS} from './common/constance'
var Child = {
  template:
    '<div><p v-for="item in errMsg" v-html="item"></p></div>',
  props: ["errMsg", "vGoOn"]
};
export default {
  data() {
    return {
      detailLoading: false,
      sendBill: {
        lstDetail:[]
      },
      billId:'',
      sltedRows:[],
      SendCheckLoading:false
    };
  },
  props: ["billid","sendShow"],
  watch: {
    billid(curVal, oldVal) {
      if(curVal){
        this.getSendBill(curVal);
      }
    }
  },
  methods: {   
    numFomat(r,c,val){
      if(val){
        return Number(val).toFixed(2);
      }
      return "0";
    },   
    sltChange(rows) {
      this.sltedRows = rows;
    },
    getSendBill(billId) {
      this.billId = billId;
      this.sendBill={lstDetail:[]};
      this.detailLoading = true;
      var _this = this;
      var resDetail=[];
      var resStatus = [];
      this.axios.get("/spdHERPService/yg/ygSend/getByBillId/"+billId).then(res=>{         
        if(res.data.code == "0"){
          _this.setBillLst(res.data.data.lstDeatil,res.data.data.lstState);
        }
      },err=>{this.detailLoading = false;});
    },    
   
    setBillLst(lstDetail,lstStatus){
      this.detailLoading = false;
      lstDetail.forEach(item=>{
        var state = lstStatus.find(o=>o.psmxbh == item.psmxbh);
        if(state){
          item.psmxzt = state.psmxzt;
          item.ysybgs = state.ysybgs;
          item.ysytgs = state.ysytgs;
          item.yyygbs = state.yyygbs;
          item.yyytgs = state.yyytgs;
          item.ystgs = item.psl;
          item.ysbgs = 0;
        }
      });
      this.sendBill.lstDetail = lstDetail;
    },
    fm_state(r,c,v){
      if(v){
        return YG_SENDBILL_DETAIL_STATUS[v.toString()];
      }else{
        return "";
      }
    },
    rowCanSlt(r,i){
      return r.psmxzt=='10' || r.psmxzt=='20';
    },
    ysrk(){     
      var errMsg=[];
      var ysLst=[];
       const h = this.$createElement;
      var _this = this;
      if(this.sltedRows.length==0){
        _this.$msgbox({title: "错误",type: "error", message: "请选择要验收的条目" });
        return;
      }     
      this.sltedRows.forEach((item,index)=>{
          var ysItem = {
            psmxbh:item.psmxbh,
            hctbdm:item.hctbdm,
            scph:item.scph,
            psl:item.psl,
            ystgs:item.ystgs,
            ysbgs:item.ysbgs,
            ysbzsm:item.ysbzsm,
            isSend:0
          }
          ysLst.push(ysItem);
      }); 
      this.SendCheckLoading = true;
        this.axios.post('/spdHERPService/yg/ygSend/recConfirm/'+this.billId, ysLst).then(res => {
            _this.SendCheckLoading = false;  
          if(res.data.code == "0" && res.data.data === true){
              _this.$msgbox({type: "success",title: '成功',message: "操作成功！",callback:function(){
                _this.closeMe();
              }});
            // if(res.data.data.repHead.res !="00000"){
            //     if(res.data.data.detail){
            //         errMsg =  res.data.data.detail.map(item=>{return "<font color='red'>"+item.psmxbh+"</font>,"+item.clqkms});
            //         _this.$msgbox({type: "error",title: res.data.data.repHead.errMsg,
            //         message: h(Child, { props: { errMsg: errMsg } })
            //       });
            //     }else{
            //         _this.$msgbox({type: "error",title: '失败',message: res.data.data.repHead.errMsg,callback:function(){
            //         _this.closeMe();
            //       }});
            //     }
            // }else{
            //   _this.$msgbox({type: "success",title: '成功',message: res.data.data.repHead.errMsg,callback:function(){
            //         _this.closeMe();
            //       }});
            // }
          }else{
            _this.$msgbox({
              title: "错误",
              type: "error",
              message: res.data.msg
            });
          }
        }, err => {
            _this.SendCheckLoading = false;
        });      
    },
    onClose(){
      this.sendBill={lstDetail:[]};
      this.sltedRows=[];
      this.$refs.ygSendView.clearSelection();
    },
    closeMe(){
      this.$emit("viewClose",false);
    }
  },
  mounted() {   
  }
};
</script>

<style>
.t_ygsendView {
  font-size: 12px;
  height: calc(100% - 100px);
  overflow-y: auto;
}
</style>