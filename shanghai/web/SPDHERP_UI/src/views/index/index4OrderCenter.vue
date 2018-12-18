<!--订单首页显示内容-->
<template>
<div id="orderHomeIndex">
    <el-row :gutter="5">
        <el-col :span="12"> 
            <el-row :gutter="5">
                <el-col :span="24">
                    <el-collapse v-model="activeNames1">
                        <el-collapse-item title="护士长未审核请购单" name='1'>
                            <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;cursor: pointer;"  @click="linkToEvent()">
                                            <i class="fa fa-pencil fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;" @click="clickEvnt()">
                                            <div style="margin-bottom:-10px;font-size: 45px;" >{{headNurseNoAuditCount}}</div>
                                            <div>护士长未审核</div>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-collapse-item>
                    </el-collapse>
                </el-col>
            </el-row>
        </el-col>

        <el-col :span="12">
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames2">
                            <el-collapse-item title="设备科未审核请购单" name='2'>
                                <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;">
                                            <i class="fa fa-paint-brush  fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;" @click="clickEvnt()">
                                            <div style="margin-bottom:-10px;font-size: 45px;">{{sbkNoAuditCount}}</div>
                                            <div>设备科未审核</div>
                                        </div>
                                    </el-col>
                                </el-row>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
        </el-col>
    </el-row>

    <el-row :gutter="5">
        <el-col :span="12"> 
            <el-row :gutter="5">
                <el-col :span="24">
                    <el-collapse v-model="activeNames3">
                        <el-collapse-item title="未处理请购单" name='3'>
                            <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;">
                                            <i class="fa fa-pencil-square-o fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;"  @click="clickEvnt()">
                                            <div style="margin-bottom:-10px;font-size: 45px;" >{{noDealCount}}</div>
                                            <div>未处理</div>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-collapse-item>
                    </el-collapse>
                </el-col>
            </el-row>
        </el-col>

        <el-col :span="12">
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames4">
                            <el-collapse-item title="采购未到货提醒" name='4'>
                                <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;">
                                            <i class="fa fa-ambulance fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;" @click='clickUnArrEvt()'>
                                            <div style="margin-bottom:-10px;font-size: 45px;">{{goodsNoArrivalBillCount }}</div>
                                            <div>采购未到货</div>
                                        </div>
                                    </el-col>
                                </el-row>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
        </el-col>
    </el-row>

    
   </div> 
</template>

<script>
    export default {
        data () {   
            return {
                activeNames1: ['1'],
                activeNames2: ['2'],
                activeNames3: ['3'],
                activeNames4: ['4'],

                deptBuyList:[],
                headNurseNoAuditCount:0,//护士长未审核请购单数
                sbkNoAuditCount:0,//设备科未审核请购单数
                noDealCount:0,//未处理请购单数
                goodsNoArrivalBillCount:0,//未到货的单据数量
            }
        },
        components: {
            
        },
        methods:{
             //获取列表，除去已经处理的单据
            getDeptBuyList: function () {
    
                var param = {
                    orderBy: "",
                    queryObject: {
                        buyKind:0, // 10 实采 20 虚采 ，0华山北院需求，后台进行判断查询所有单据
                        hosId:this.user.corpId,buyDeptId:this.user.orgId,
                        stateArr:[10,11,20,30] 
                        /*  "10": { text: '已保存(护士)', index: 1 },
                            "11": { text: '已审核(护士长)', index: 2 },
                            "20": { text: '已审核(设备科)', index: 3 },
                            "30": { text: '处理中', index: 4 },
                            "40": { text: '已处理', index: 5 }
                        */
                    }
                };
                this.loading = true;
                let _this = this;
                this.axios.post('/spdHERPService/applyMgr/deptBuyMain/listVoByPage', param).then(res => {
                    this.loading = false;
                    if(res.data.data.data && res.data.data.data.length>0){
                        for(var i = 0 ;i < res.data.data.data.length;i++){
                             var item = res.data.data.data[i];
                             if(item.state==10){
                                _this.headNurseNoAuditCount++ ;
                             }else if(item.state==11){
                                 _this.sbkNoAuditCount++;
                             }else if(item.state==20){
                                 _this.noDealCount++;
                             }
                        }
                       
                    }
                    
                }, err => {
                    this.loading = false;
                });
            },

             //获取未到货单据列表
            getPurchaseList: function () {
                var param = {
                    orderBy: "",
                    queryObject: {
                        purchaseComId: this.hosId,                   
                        arrStatus:[30,40],
                        flag:1  
                    }
                };
                this.loading = true;
                this.axios.post('/spdHERPService/order/purchase/purchasePage', param).then(res => {
                    this.loading = false;
                    //this.purchaseList = res.data.data.data;
                    this.goodsNoArrivalBillCount = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            /**未处理订单点击事件 */
            clickEvnt : function(){
                this.$router.push({ path: 'listBuy'});
            },
            /**未到货订单点击事件 */
            clickUnArrEvt : function(){
                this.$router.push({ path: 'unRecPurBill'});
            },
            /**跳转至可是审核 */
            linkToEvent : function(){
                this.$router.push({ path: 'purchaseAudit'});
            }
        },
        mounted:function (){
            this.hosId = this.user.corpId;
            this.getDeptBuyList();
            this.getPurchaseList();
        }
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 5px;
        &:last-child {
        margin-bottom: 0;
    }
  }
  .el-col {
        border-radius: 4px;
}
</style>

<style>
    #orderHomeIndex .el-collapse-item__header {
        height: 25px;
        line-height: 25px;
        padding-left: 15px;
        color: #48576a;
        cursor: pointer;
        font-size: 13px;
        background:url(../../assets/images/portal_tab_single.gif?V=3_50_2_29);
    }
    #orderHomeIndex .color-box {
        border-radius: 4px;
        height: 120px;
        box-sizing: border-box;
        color: #fff;
        font-size: 14px;
        text-align: center;
    }
    #orderHomeIndex .color-box-icon {
        border-radius: 4px;
        padding: 10px;
        height: 120px;
        box-sizing: border-box;
        color: #fff;
        text-align: center;
    }  
    #orderHomeIndex .value {
        display: inline-block;
        text-align: center;
        padding-top: 10px;
        color: #fff;
    }
</style>