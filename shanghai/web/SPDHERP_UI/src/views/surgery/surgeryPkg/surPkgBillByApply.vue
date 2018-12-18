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
          <el-col :span="8"  style="height:100%">
                <el-button type="primary" icon="el-icon-printer">打印捡货码</el-button>
                <el-table ref="surGoods_tbl" :data="applyBill.lstSurPkg" highlight-current-row style="width: 100%;" @row-click="onSurChange" fit border class="tab_surPkg" height="outer">
                    <el-table-column prop="surName" label="手术包名称" sortable></el-table-column>
                    <el-table-column prop="surCode" label="手术包码" sortable></el-table-column>
                    <el-table-column label="打包情况" sortable>
                        <template slot-scope="scope">
                            <el-tag type="warning" v-if="scope.row.status==0">未打包</el-tag>
                            <el-tag type="info" v-else>已打包</el-tag>
                            <el-tag :type="scope.row.remark ==0?'success':'danger'" v-if="scope.row.status==0">{{packStatus[scope.row.remark]}}</el-tag>
                        </template>
                    </el-table-column>
                </el-table>
                
          </el-col>
           <el-col :span="16" style="height:100%">
            <el-table ref="tbl_goodsList" :data="curSur.surgeryPkgListVos"  style="width: 100%;" class="tab_surPkg-view" 
                :row-class-name='goodsCls' fit border height="outer">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <table width="100%" v-if="curSur.status == 0">
                                <tr><th width="20%">批次号</th><th  width="20%">批号</th><th width="10%">条码</th><th width="20%">有效期</th><th width="20%">占用/库存</th><th width="10%">数量</th></tr>
                                <tr v-for="item in goodsStoreQty[props.row.goodsId].batchLst" :key="item.id" >
                                    <td>{{item.batchId}}</td><td>{{item.batchNo}}</td><td>{{item.uniqueCode}}</td><td>{{item.expdtEndDate | dateFormat}}</td>
                                    <td>{{item.takeQty || 0}}/{{item.qty}}</td>
                                    <td>
                                        <el-input-number v-model="props.row.batchMap[item.id]" @change="onBatchQtyChange(item,props.row)" :min="0" :max="getBatchMaxQty(item,props.row)" 
                                            :disabled="props.row.uniqueKind<3"
                                        ></el-input-number>
                                    </td>
                                </tr>
                            </table>
                            <table width="100%" v-else>
                                <tr><th width="20%">批次号</th><th  width="20%">批号</th><th width="10%">条码</th><th width="20%">有效期</th><th width="10%">数量</th></tr>
                                <tr v-for="item in props.row.lstGoodsBatch" :key="item.id" >
                                    <td>{{item.batchId}}</td><td>{{item.batchCode}}</td><td>{{item.uniqueCode}}</td><td>{{item.expdtEndDate | dateFormat}}</td><td>{{item.qty}}</td>
                                </tr>
                            </table>    
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsName" label="品名" sortable></el-table-column>
                    <el-table-column prop="goodsGg" label="规格"></el-table-column>                    
                    <el-table-column label="分配数量/需求数量" sortable>
                        <template slot-scope="scope">
                            {{scope.row.takeQty}}/{{scope.row.qty}}
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
            curSur:{},
            goodsStoreQty:{},
            sickerName:'',
            inHosCode:'',
            surScheduleNo:'',
            pageLoading:false,
            submitLoading:false,
            packStatus:['可打包','数量不足','库存不足']
         }
    },
    components:{sltStockByUser,barcodeResolver},
    filters: {       
    },    
    methods: {
        clear(){
            this.applyBill = {};
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
            this.axios.get(`/spdHERPService/surgeryPkg/unPackApplyBIll/${billId}/${this.stockId}`).then(res => {
                _this.pageLoading = false;
                _this.applyBill = res.data.data;
                _this.setGoodsStoreQty();
            }, err => {
                _this.pageLoading = false;
            });
        },
        setGoodsStoreQty(){
            var _this = this;
            var goodsIds = _this.applyBill.goodsIds;
            goodsIds.forEach(item=>{
                var bachLst = _this.applyBill.lstStocInfo.filter(o=>o.hosGoodsId==item);
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
            this.applyBill.lstSurPkg.forEach(item=>{
                item.remark = 1;
                item.surgeryPkgListVos.forEach(citem=>{
                    citem.takeQty=0;
                    citem.storeQty=_this.goodsStoreQty[citem.goodsId].storeQty;
                    var batchMap={};
                    _this.goodsStoreQty[citem.goodsId].batchLst.forEach(store=>{batchMap[store.id]=0;});
                    citem.batchMap=batchMap;
                    if(citem.qty>citem.storeQty){
                        item.remark=2;
                    }
                });                
            });
        },
        getGoodsBatchLst(row){
            return _this.goodsStoreQty[row.goodsId];
        },
        getBatchMaxQty(store,row){
            var curVal = row.batchMap[store.id];
            var storeQty = Number(store.qty)-Number(store.takeQty);
            var needQty = row.qty-row.takeQty;
            return Math.min(storeQty,needQty)+curVal;
        },
        onBatchQtyChange(store,row){
            this.$nextTick(()=>{
                var total =0;
                for(var key in row.batchMap){
                    total +=row.batchMap[key];
                }
                row.takeQty = total;
                store.takeQty=this.getGoodsBatchTakeQty(row.goodsId,store.id);//row.batchMap[store.id];
            });
        },
        getGoodsBatchTakeQty(goodsId,storeId){
            var takeQty = 0;
            this.applyBill.lstSurPkg.forEach(item=>{
                var row = item.surgeryPkgListVos.find(o=>o.goodsId==goodsId);
                if(row){
                    takeQty+=row.batchMap[storeId];
                }
                var count = item.surgeryPkgListVos.filter(o=>o.takeQty<o.qty);
                if(count && count.length>0){
                    item.remark =item.remark < 1?1:item.remark;
                }else{
                    item.remark =item.remark == 1? 0:item.remark;
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
        goodsCls(rowInfo){
            var res = '';
            if(this.curSur.status >0){
                return '';
            }
            if(rowInfo.row.takeQty < rowInfo.row.qty){
                res = 'warning-row'
                if(this.curSur.remark==0){
                    this.curSur.remark=1;
                }
            }
            if(Number(rowInfo.row.qty) > Number(this.goodsStoreQty[rowInfo.row.goodsId].storeQty)){
                res = 'red-row';
            }
            return res;
        },
        onBarcodeResolved(barKind,code){
            var _this =this;
           if(code.substr(0,1).toUpperCase()=='E'){    //扫描的是唯一码
                var goodsBatchInfo =null;
                var canPack =true;//标识此条码本次扫描是否可被消耗
                for(var key in _this.goodsStoreQty){
                    var storeInfo = _this.goodsStoreQty[key].batchLst.find(o=>o.uniqueCode==code);
                    if(storeInfo){
                        goodsBatchInfo= storeInfo
                        if(storeInfo.takeQty>0){
                            canPack = false;
                            this.$message.error('此条码已扫描');
                            return;
                        }
                        break;
                    }
                }
                if(goodsBatchInfo){
                    for(var i = 0;i<this.applyBill.lstSurPkg.length;i++){
                        var surBill = this.applyBill.lstSurPkg[i];
                        if(surBill.status > 0){
                            continue;
                        }
                        var surGoods = surBill.surgeryPkgListVos.find(o=>o.goodsId == goodsBatchInfo.hosGoodsId);
                        if(surGoods.takeQty==surGoods.qty){
                            continue;
                        }
                        if(surGoods.batchMap[goodsBatchInfo.id]==0){
                            surGoods.batchMap[goodsBatchInfo.id] = 1;
                            _this.onBatchQtyChange(goodsBatchInfo,surGoods);
                            return;
                        };
                    }
                }else{
                    this.$message.error('无法解析此条码');
                }
           }else{//扫描的是手术包
                var curSur=this.applyBill.lstSurPkg.find(o=>o.surCode==code);
                if(curSur){
                    this.curSur = curSur;
                    this.$refs.surGoods_tbl.setCurrentRow(this.curSur);
                }else{
                    this.$message.error('无此手术包');
                    this.curSur={};
                }
           }
        },
        getSubmitData(){
            var _this = this;
            var submitLst=[];
            //var errMsgs=[];
            var surLst = this.applyBill.lstSurPkg.filter(o=>o.status==0 && o.remark ==0);
            surLst.forEach(item=>{
                var surBag={
                    id:item.id,
                    surCode:item.surCode,
                    version:item.version,
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
                for(var index=0;index<item.surgeryPkgListVos.length;index++){
                    var goods = item.surgeryPkgListVos[index];
                    if(goods.qty != goods.takeQty){
                        canSubmit=false;
                        //errMsgs.push({surName:item.surName,goodsName:goods.goodsName,needQty:goods.qty,takeQty:goods.takeQty});
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
                //errMsgs:errMsgs,
                data:submitLst
            };
        },
        packSur(){
            var _this = this;
            var submitInfo = this.getSubmitData();
            if(submitInfo.data.length == 0){
                 _this.$message.error('无可打手术包');
                return;
            }
            _this.pageLoading = true;
            this.axios.post(`/spdHERPService/surgeryPkg/sumitSurPack`,submitInfo.data).then(res=>{
                _this.pageLoading = false;
                if(res.data.code==0){
                    var res = res.data.data;
                    var htmlMsg = '';
                    res.forEach(item=>{
                        var color = item.status?"#67C23A":"#F56C6C";
                        htmlMsg +=`<font color="${color}">${item.surName}</font>${item.msg}</br>`;
                    });
                    _this.$alert(htmlMsg, '打包结果', {type:'success',dangerouslyUseHTMLString:true,callback:function(action, instance, done){
                        _this.cancel(true);
                        done();
                    },showClose:false,})
                    
                }
            },err=>{
                 _this.$message.error('打包失败！');
                _this.pageLoading = false;
            });
        },
        cancel(){
            this.applyBill = {};
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