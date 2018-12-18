<template>
    <el-row :gutter="10" style="height:100%;">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="120px" v-loading="loading">
                <el-col :span="8">
                    <el-form-item label="医院计划单号">
                        <span>{{ YY111.main.applyBillId }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="采购单类型">
                        <span>{{ YY111.main.opType }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="最晚到货日期">
                        <span>{{ YY111.main.lastSendDate }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="记录数">
                        <span>{{ YY111.main.sumRowCount }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="配送地点">
                      <span>{{rAddress.recAddress}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="2">
                        <el-form-item label-width="0px">
                            <deliveryAddress btn_text="修改" v-on:addressChange="setAddress" :hosId="user.corpId" :deptId="user.orgId"></deliveryAddress>
                        </el-form-item>
                    </el-col>
            </el-form>
        </el-col>            
        <!--列表-->
        <el-table v-show="!showStataus" :data="YY111.detail" style="width: 100%;" border fit class="table_content">
            <el-table-column prop="rowNum" label="序号" width="60">
            </el-table-column>
            <el-table-column prop="purType" label="采购类型" width="80">
                 <template slot-scope="scope">
                    <el-select v-model="scope.row.purType" placeholder="请选择" size="mini">
                        <el-option  label="招标采购" value="1"></el-option>
                        <el-option  label="带量采购" value="2"></el-option>
                        <el-option  label="挂网采购" value="2"></el-option>
                        <el-option  label="其他" value="2"></el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column prop="goodsHosId" label="商品编码" width="80" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="goodsCode" label="耗材统编码" width="100" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="goodsDetailCode" label="耗材细分编码" width="100" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="goodsGG" label="规格型号" width="80" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="purPrice" label="请购单价">
            </el-table-column>
            <el-table-column prop="purQty" label="采购数量" width="90">
            </el-table-column>                     
            <el-table-column prop="deliverRemark" label="配送说明">
            </el-table-column>
            <!-- <el-table-column prop="provCode" label="企业编码"  width="150">
            </el-table-column> -->
            <el-table-column prop="isFast" label="是否加急" width="80">
                <template slot-scope="scope">
                    <el-switch  v-model="scope.row.isFast"  active-color="#13ce66" inactive-color="#ff4949"
                        active-text="不加急" inactive-text="加急" :active-value="0"  :inactive-value="1"> 
                    </el-switch>
                </template>
            </el-table-column>
            <el-table-column prop="deliverRequire" label="配送要求" width="80">
                <template slot-scope="scope">
                    <el-select v-model="scope.row.deliverRequire" placeholder="请选择" size="mini">
                        <el-option  label="按单配送" value="1"></el-option>
                        <el-option  label="按需配送" value="2"></el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column prop="isDeliverMany" label="多次配送" width="80">
                <template slot-scope="scope">
                    <el-switch  v-model="scope.row.isDeliverMany" active-color="#13ce66" inactive-color="#ff4949" active-text="允许" inactive-text="不允许" :active-value="1"  :inactive-value="0"> 
                        </el-switch> 
                </template>               
            </el-table-column>
            <el-table-column prop="shelfInfo" label="仓位信息" width="80">                        
            </el-table-column>
            <el-table-column prop="remark" label="备注" width="60">                        
            </el-table-column>
        </el-table>
        <el-table v-show="showStataus" :data="detailStatus" style="width: 100%;" fit class="table_content" border>
            <el-table-column prop="rowNum" label="序号" width='60'>
            </el-table-column>
            <el-table-column prop="ygBillId" label="单号">
            </el-table-column>            
            <el-table-column prop="goodsHosId" label="商品编码" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="goodsCode" label="耗材统编码"show-overflow-tooltip>
            </el-table-column>
             <el-table-column prop="qykc" label="企业库存">
            </el-table-column>                      
            <el-table-column prop="state" label="状态">
            </el-table-column>
            <el-table-column prop="mxsm" label="状态说明">                        
            </el-table-column>
        </el-table>
    </el-row>
</template>
<script>
import { PURCONFIRM_STATUS } from './common/constance';
import deliveryAddress from '@/components/deliveryAddress.vue';
export default {
    data() {
        return {
            loading: false,
            rAddress:{},
            showStataus:false,
            detailStatus:[],
            YY111: {
                main:{},
                detail:[]
            }
        }
    },
    props: ["billid"],
    components: { deliveryAddress },
    watch: {
        billid(curVal, oldVal) {
            this.getPurconfirmInfo(curVal);
        }
    },
    methods: {   
       setAddress(d) {
            //this.YY111.main.addressCode = d.id;
            this.rAddress.id = d.id;
            this.rAddress.lxr = d.lxr;
            this.rAddress.lxrPhone = d.lxrPhone;
            this.rAddress.recAddress = (""+d.province + d.city + d.area + d.address).replace(/null/g,"");
        },    
        
        getPurconfirmInfo(billId) {
            this.loading = true;
            var _this = this;
            _this.showStataus = false;
            _this.detailStatus = [];
            _this.YY111 = {
                main:{},
                detail:[]
            }
            this.axios.get('/spdHERPService/orderMgr/purconfirm/getByBillid/' + billId).then(res => {
                _this.loading = false;
                if (res.data.code == "0") {
                    //_this.purconfirm = res.data.data;
                    _this.praseToYY111(res.data.data);
                }
            }, err => {
                _this.loading = false;
            });
        },
        praseToYY111(pur){
          var _this = this;  
          this.YY111.detail=[];          
            if(Array.isArray(pur.lstDetail)){
              pur.lstDetail.forEach((item,index)=>{
                 var YY111Detail = {
                   rowNum:index +1,
                   purType:1,
                   goodsCode:item.miCode,
                   goodsDetailCode:"",
                   goodsHosId:item.hosGoodsCode,
                   goodsGG:item.goodsGg,
                   deliverRemark:"配送说明",
                   purQty:item.qty,
                   purPrice:item.provGoodsErpPrice,
                   provCode:"QY000000000000000346",
                   isFast:0,
                   deliverRequire:item.miCode?1:2,
                   isDeliverMany:0,
                   shelfInfo:"",
                   remark:""
                 }
                 _this.YY111.detail.push(YY111Detail);
                 var state = {
                    ygBillId:item.ygBillId,
                    rowNum:index +1,
                    purType:1,
                    goodsCode:item.miCode,
                    goodsDetailCode:"",
                    goodsHosId:item.hosGoodsCode,
                    goodsGG:item.goodsGg,
                    deliverRemark:"配送说明",
                    purQty:item.qty,
                    purPrice:item.provGoodsErpPrice,
                    provCode:"QY000000000000000346",
                    isFast:0,
                    deliverRequire:item.miCode?1:2,
                    isDeliverMany:0,
                    shelfInfo:"",
                    remark:"",
                    state:'',
                    mxsm:'',
                    qykc:''
                }
                 _this.detailStatus.push(state);
              });
            }  
            this.YY111.main.opType = 1;
            this.YY111.main.hosCode = "56806983600";
            this.YY111.main.addressCode = "test00001";
            this.YY111.main.purType = 1;
            this.YY111.main.purBillId = pur.ygBillId;
            this.YY111.main.applyBillId = pur.billId;
            this.YY111.main.lastSendDate = '20180501';
            this.YY111.main.sumRowCount = this.YY111.detail.length;   
            if(!pur.ygBillId){
                this.$emit("btnStatus",{btnSend:true,btnConfirm:false,btnFresh:false});
            }else{
                this.$emit("btnStatus",{btnSend:false,btnConfirm:false,btnFresh:true});
                if(pur.isConfirm == 0){
                     this.$emit("btnStatus",{btnSend:false,btnConfirm:true,btnFresh:true});
                }
            }
        },
        sendPurToYg(){
            this.loading = true;
            var _this = this;
            if(this.YY111.main.purBillId){
                this.$message.error(`此单已经上传过啦！`);
                return;
            }
            this.axios.post('/spdHERPService/orderMgr/purconfirm/sendPurToYg',this.YY111).then(res => {
                _this.loading = false;
                if (res.data.code == "0") {
                    if(res.data.data.repHead.res == "0000"){
                        _this.$message.success('发送成功!');
                        _this.getPurconfirmInfo(_this.billid);
                    }else{
                        _this.$message.error(res.data.data.repHead.errMsg);
                    }
                }
            }, err => {
                _this.loading = false;
            });
        },
        confirmToYg(){
            this.loading = true;
            var _this = this;
            this.axios.get(`/spdHERPService/orderMgr/purconfirm/confirmPurToYg/${_this.billid}`).then(res => {
                _this.loading = false;
                if (res.data.code == "0") {
                    if(res.data.data.repHead.res == "00000"){
                        _this.$message.success('确认成功!');
                    }else{
                        _this.$message.error(res.data.data.repHead.errMsg);
                    }
                }
            }, err => {
                _this.loading = false;
            });
        },
        getPurYgStatus(){
            this.loading = true;
            var _this = this;
            this.axios.get(`/spdHERPService/orderMgr/purconfirm/getPurYgStatusByYgBillId/${_this.YY111.main.purBillId}`).then(res => {
                _this.loading = false;
                if (res.data.code == "0") {
                    if(res.data.data.repHead.res == "00000"){
                       _this.detailStatus.forEach(item=>{
                           var state = res.data.data.detail.find(o=>{return o.ddmxbh == item.ygBillId});
                           if(state != null){
                                item.state = state.cgmxshyj;
                                item.mxsm = state.cgdclsm;
                                item.qykc = state.qykc;
                           }                          
                       });
                       _this.showStataus = true;
                    }else{
                        _this.$message.error(res.data.data.repHead.errMsg);
                    }
                }
            }, err => {
                _this.loading = false;
            });
        }       
    },
    mounted() {
        var billId = this.billid;//this.$route.query.billId;
        if (billId) {
            this.getPurconfirmInfo(billId);
        }
    }
};

</script>

<style>
.table_content{
    font-size: 12px;
    height: calc(100% - 100px);
    overflow-y: auto;
}
</style>