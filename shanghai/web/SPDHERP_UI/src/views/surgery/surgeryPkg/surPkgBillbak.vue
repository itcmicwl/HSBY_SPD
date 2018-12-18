<template>
    <el-row style="height:100%" v-loading="pageLoading">
        <el-row>
          <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="80px">
                <el-col :span="8">
                    <el-form-item label="请购单号">
                        <span>{{ applyBill.billId }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="要货科室">
                        <span>{{ applyBill.buyDeptName }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="库房">
                        <sltStockByUser  ref="childMethod" v-model='stockId' :hosId="this.user.corpId" :userId="this.user.userId" isDefault=true></sltStockByUser>
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                     <barcodeResolver :barcodeKind="barcodeKind.SSB" :hosId="user.corpId"
                        :deptId="user.orgId" :userId="user.userId" @resolved="onBarcodeResolved"></barcodeResolver>
                </el-col>
            </el-form>
         </el-col>
        </el-row>
        <el-row style="height:calc(100% - 120px);" :gutter="5">
          <el-col :span="6"  style="height:100%">
            <el-table :data="surPkgList" highlight-current-row style="width: 100%;" @row-click="onSurChange" fit border class="tab_surPkg" height="outer">
                    <el-table-column prop="surName" label="手术包名称" sortable></el-table-column>
                    <el-table-column label="打包情况" >
                        <template slot-scope="scope">
                            已打{{scope.row.qty}}包/共{{scope.row.applyQty}}包
                        </template>
                    </el-table-column>
                    <el-table-column label="本次打包数" >
                        <template slot-scope="scope">
                            <el-input-number placeholder="请输入数量" v-model="scope.row.packageNum" :min="0" :max="scope.row.applyQty - scope.row.qty" 
                            class="col-input-num" @change="onPackageQtyChange(scope.row)">
                        </el-input-number>
                        </template>
                    </el-table-column>
                </el-table>
          </el-col>
           <el-col :span="18" style="height:100%">
            <el-table ref="tbl_goodsList" :data="curSur.lstGoodsInfo"  style="width: 100%;" class="tab_surPkg-view" 
                :row-class-name='goodsCls' fit border height="outer">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <table width="100%">
                                <tr><th width="20%">批次号</th><th  width="20%">批号</th><th width="10%">条码</th><th width="20%">有效期</th><th width="20%">占用/库存</th><th width="10%">数量</th></tr>
                                <tr v-for="item in goodsStoreQty[props.row.goodsId].batchLst" :key="item.id" >
                                    <td>{{item.batchId}}</td><td>{{item.batchNo}}</td><td>{{item.uniqueCode}}</td><td>{{item.expdtEndDate | dateFormat}}</td>
                                    <td>{{item.takeQty || 0}}/{{item.qty}}</td>
                                    <td>
                                        <el-input-number v-model="props.row.batchMap[item.id]" @change="onBatchQtyChange(item,props.row)" :min="0" :max="getBatchMaxQty(item,props.row)" ></el-input-number>
                                    </td>
                                </tr>
                            </table>
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsName" label="品名" sortable></el-table-column>
                    <el-table-column prop="goodsName" label="规格"></el-table-column>                    
                    <el-table-column prop="needQty" label="分配数量/需求数量" sortable>
                        <template slot-scope="scope">
                            {{scope.row.takeQty}}/{{scope.row.needQty}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="storeQty" label="总库存" sortable>
                        <template slot-scope="scope">
                            {{goodsStoreQty[scope.row.goodsId].storeQty}}{{scope.row.unit}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="made" label="产地" sortable></el-table-column>
                    <el-table-column prop="mfrsName" label="厂家"></el-table-column>                    
                </el-table>
           </el-col>
           <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                <div style="text-align: center;">
                    <el-button  type="danger"  @click="cancel" icon="el-icon-close" size="small">取消</el-button>
                    <el-button  type="success"  @click="packSur" icon="el-icon-sold-out" size="small">打包</el-button>
                </div>
        </el-col>
        </el-row>
    </el-row>
</template>
<script>
import sltStockByUser from "@/components/sltStockByUser";
import barcodeResolver from '../../inStockMgr/common/barcodeResolver';
import {BARCODEKIND,BARCOD_RES_TYPE} from '../../inStockMgr/common/constance';
export default {
    data() {
        return {
            stockId:'',
            barcodeKind:BARCODEKIND,
            applyBill:{},
            surPkgList:[],
            lstGoodsStore:[],
            curSur:{},
            goodsStoreQty:{},
            sickerName:'',
            inHosCode:'',
            surScheduleNo:'',
            pageLoading:false,
            submitLoading:false,
         }
    },
    components:{sltStockByUser,barcodeResolver},
    filters: {       
    },    
    methods: {
        clear(){
            this.applyBill = {};
            this.surPkgList=[];
            this.lstGoodsStore=[];
            this.curSur={};
            this.goodsStoreQty={};
            this.sickerName='';
            this.inHosCode='';
            this.surScheduleNo='';
        },
        show(billId){
            this.pageLoading = true;
            this.clear();
            var _this = this;
            this.axios.get(`/spdHERPService/applyMgr/deptBuyMain/unPackApplyBIll/${billId}/${this.stockId}`).then(res => {
                _this.pageLoading = false;
                _this.applyBill = res.data.data.main;
                _this.lstGoodsStore = res.data.data.lstDetail;
                _this.surPkgList = res.data.data.lstPkg;
                _this.setGoodsStoreQty();
            }, err => {
                _this.pageLoading = false;
            });
        },
        setGoodsStoreQty(subVoList){
            var _this = this;
            var goodsIds = new Set(_this.applyBill.subVoList.map(item=>item.goodsId));
            goodsIds.forEach(item=>{
                var bachLst = _this.lstGoodsStore.filter(o=>o.hosGoodsId==item);
                if(Array.isArray(bachLst) && bachLst.length>0){
                    _this.goodsStoreQty[item]={
                        storeQty:bachLst.map(o=>o.qty).reduce((cur,next)=>{return cur+next}),
                        takeQty:bachLst.map(o=>o.takeQty).reduce((cur,next)=>{return cur+next}),
                        batchLst:bachLst
                    }
                }else{
                    _this.goodsStoreQty[item]={
                        storeQty:0,
                        takeQty:0,
                        batchLst:[]
                    }
                }               
                
            });
            this.surPkgList.forEach(item=>{item.lstGoodsInfo.forEach(citem=>{
                citem.needQty=0;
                citem.allTakeQty=0;
                citem.storeQty=_this.goodsStoreQty[citem.goodsId].storeQty;
                var batchMap={};
                _this.goodsStoreQty[citem.goodsId].batchLst.forEach(store=>{
                        batchMap[store.id]=0;
                    })
                citem.batchMap=batchMap;
                });                
            });
        },
        getGoodsBatchLst(row){
            return _this.goodsStoreQty[row.goodsId];
        },
        getBatchMaxQty(store,row){
            var curVal = row.batchMap[store.id];
            var storeQty = Number(store.qty)-Number(store.takeQty);
            var needQty = row.needQty-row.takeQty;
            return Math.min(storeQty,needQty)+curVal;
        },
        onBatchQtyChange(store,row){
            this.$nextTick(()=>{
                var total =0;
                for(var key in row.batchMap){
                    total +=row.batchMap[key];
                    //this.goodsStoreQty[row.goodsId].batchLst.find(o=>o.id==key).takeQty = row.batchMap[key];
                }
                row.takeQty = total;
                //this.goodsStoreQty[row.goodsId].takeQty = row.takeQty;
                store.takeQty=this.getGoodsBatchTakeQty(row.goodsId,store.id);//row.batchMap[store.id];
            });
        },
        getGoodsBatchTakeQty(goodsId,storeId){
            var takeQty = 0;
            this.surPkgList.forEach(item=>{
                var row = item.lstGoodsInfo.find(o=>o.goodsId==goodsId);
                if(row){
                    takeQty+=row.batchMap[storeId];
                }
            });
            return takeQty;
        },
        onSurChange(row){
            var _this = this;
            if(row){
                this.curSur =row;                
            }
        },
        onPackageQtyChange(row){
            var _this = this;
            if(row){
                this.curSur = {};
                this.$nextTick(()=>{
                    row.lstGoodsInfo.forEach(item=>{item.needQty=row.packageNum*item.qty});
                    _this.curSur = row;
                });
                
            }
        },
        goodsCls(rowInfo){
            if(Number(rowInfo.row.storeQty) < Number(rowInfo.row.allTakeQty)){
                return 'red-row';
            }
            if(rowInfo.row.takeQty < rowInfo.row.needQty){
                return 'warning-row'
            }
        },
        onBarcodeResolved(barKind,code){
            
        },
        getSubmitData(){
            var _this = this;
            var submitLst=[];
            var errMsgs=[];
            var surLst = _this.surPkgList.filter(o=>o.packageNum>0);
            surLst.forEach(item=>{
                var surBag={
                    hosId:_this.applyBill.hosId,
                    curStockId: this.stockId,
                    deptId:_this.applyBill.buyDeptId,
                    surId:item.surId,
                    surName:item.surName,
                    sickerName:_this.sickerName,
                    inHosCode:_this.inHosCode,
                    surScheduleNo:_this.inHosCode,
                    applyBillId:_this.applyBill.billId,
                    packer:_this.user.userId,
                    surgeryPkgListVos:[]
                };
                var canSubmit=true;
                for(var index=0;index<item.lstGoodsInfo.length;index++){
                    var goods = item.lstGoodsInfo[index];
                    if(goods.needQty != goods.takeQty){
                        canSubmit=false;
                        errMsgs.push({surName:item.surName,goodsName:goods.goodsName,needQty:goods.needQty,takeQty:goods.takeQty});
                        break;
                    }
                    var surGoods={
                        goodsId:goods.goodsId,
                        uniqueKind:goods.uniqueKind,
                        qty:goods.takeQty,
                        unit:goods.unit,
                        shouldSterilize:goods.shouldSterilize,
                        lstGoodsBatch:[]
                    }
                    for(var key in goods.batchMap){
                        if(goods.batchMap[key]>0){
                            var batchInfo = _this.goodsStoreQty[goods.goodsId].batchLst.find(o=>o.id==key);
                            var goodsBatch={
                                goodsId:goods.goodsId,
                                batchId:batchInfo.batchId,
                                bigBatchCode:batchInfo.bigBatchCode,
                                uniqueCode:batchInfo.uniqueCode,
                                batchCode:batchInfo.batchNo,
                                qty:goods.batchMap[key],
                                inPrice:batchInfo.price,
                                sterilizationDate:batchInfo.sterilizationDate,
                                sterilizationEndDate:batchInfo.sterilizationEndDate,
                                sterilizationCode:batchInfo.sterilizationCode,
                                expdtEndDate:batchInfo.expdtEndDate,
                                unit:goods.unit,
                                storeId:batchInfo.id,
                                version:batchInfo.version
                            };
                            surGoods.uniqueKind = batchInfo.uniqueKind;
                            surGoods.lstGoodsBatch.push(goodsBatch);
                        }
                    }
                    surBag.surgeryPkgListVos.push(surGoods);
                }
                if(canSubmit === true){
                    submitLst.push(surBag);
                }
            });
            return {
                errMsgs:errMsgs,
                data:submitLst
            };
        },
        packSur(){
            var _this = this;
            var submitInfo = this.getSubmitData();
           if(submitInfo.errMsgs.length > 0){
                _this.$message.error(submitInfo.errMsgs.join(','));
            }
            if(submitInfo.data.length == 0){
                return;
            }
            _this.pageLoading = true;
            this.axios.post(`/spdHERPService/surgeryPkg/sumitSurPack`,submitInfo.data).then(res=>{
                _this.pageLoading = false;
                if(res.data.code==0){
                    _this.$message.success('打包成功');
                    _this.cancel(true);
                }
            },err=>{
                 _this.$message.error('打包失败！');
                _this.pageLoading = false;
            });
        },
        cancel(){
            this.applyBill = {};
            this.surPkgList=[];
            this.curSur={};
            this.$emit("cancel");
        },
    },
    mounted() {
        
    }
}
</script>
<style>
.tab_surPkg-view{
    height:100%;
}
.el-table th.red-row, .el-table tr.red-row{
    background-color: #f56c6c;
    color: #fff;
}
.el-table th.warning-row, .el-table tr.warning-row{
    background-color: #e6a23c;
    color: #fff;
}
.el-table th.red-row:hover, .el-table tr.red-row:hover{
    background-color: #f56c6c;
    color: #000;
}
.el-table th.warning-row:hover, .el-table tr.warning-row:hover{
    background-color: #e6a23c;
    color: #000;
}
</style>