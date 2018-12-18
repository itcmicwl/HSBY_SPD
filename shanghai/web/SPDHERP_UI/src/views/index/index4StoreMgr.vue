<!--配送管理首页-->
<template>
<div id="storeMgrHomeIndex">
    <el-row :gutter="5">
        <el-col :span="8"> 
            <el-row :gutter="5">
                <el-col :span="24">
                    <el-collapse v-model="activeNames1">
                        <el-collapse-item title="采购未入库" name='1'>
                            <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;">
                                            <i class="fa fa-cart-arrow-down fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;" @click="clickCgEvnt()">
                                            <div style="margin-bottom:-10px;font-size: 45px;" >{{CGNoInStockCount}}</div>
                                            <div>采购未入库</div>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-collapse-item>
                    </el-collapse>
                </el-col>
            </el-row>
        </el-col>

        <el-col :span="8">
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames2">
                            <el-collapse-item title="请购未入库" name='2'>
                                <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;">
                                            <i class="fa fa-cart-plus  fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;" @click="clickQgEvnt()">
                                            <div style="margin-bottom:-10px;font-size: 45px;">{{QGNoInStockCount}}</div>
                                            <div>请购未入库</div>
                                        </div>
                                    </el-col>
                                </el-row>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
        </el-col>

        <el-col :span="8"> 
            <el-row :gutter="5">
                <el-col :span="24">
                    <el-collapse v-model="activeNames3">
                        <el-collapse-item title="调拨未入库" name='3'>
                            <div style="height: 120px;">
                                <el-row :gutter="1">
                                    <el-col :span="10">
                                        <div class="color-box-icon" style="background-color: #409eff;">
                                            <i class="fa fa-bus fa-5x" aria-hidden="true" style="margin-top:10px;"></i> 
                                        </div>
                                    </el-col>
                                    <el-col :span="14">
                                        <div class="color-box" style="background-color: #67C23A;cursor: pointer;"  @click="clickDbEvnt()">
                                            <div style="margin-bottom:-10px;font-size: 45px;" >{{DBNoInStockCount}}</div>
                                            <div>调拨未入库</div>
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

                CGNoInStockCount:0,//采购未入库单据数
                QGNoInStockCount:0,//请购未入库单据数
                DBNoInStockCount:0,//调拨未入库单据数
                goodsNoArrivalBillCount:0,//未到货的单据数量
            }
        },
        components: {
            
        },
        methods:{
              //获取采购入库单列表
            getDistrBillListForCg: function () {
                var param = {
                    orderBy: "",
                    page: 1,
                    rows: 20,
                    queryObject: {
                        purchaseCompanyId: this.hosId,
                        status: 20
                    }
                };
                this.loading = true;
                this.axios.post('/spdHERPService/orderMgr/distrBill/listByPage', param).then(res => {
                    this.loading = false;
                    this.CGNoInStockCount = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },

            // 获取请购未入库的单据列表
            getOutBillList: function () {
                var param = {
                    orderBy: '',
                    queryObject: {
                    inDeptId: this.user.orgId,
                        status: 30 // 30已记帐
                    }
                }
                this.axios.post('/spdHERPService/stockMgr/out4In/getBillList4Instock', param).then(res => {
                    this.QGNoInStockCount = res.data.data.total
                }, err => {
                    this.loading = false
                })
            },

            //获取调拨入库单列表
            getDistrBillList: function () {
                var param = {
                    orderBy: "",
                    page: 1,
                    rows: 20,
                    queryObject: {
                        inOrgId: this.user.corpId,
                        inDeptId: this.user.orgId,
                        outStockKind: 1,
                        status:30
                    }
                };
                this.loading = true;
                this.axios.post('/spdHERPService/stockMgr/out4In/getBillList4Instock', param).then(res => {
                    this.loading = false;
                    this.DBNoInStockCount = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            /**点击采购未入库数量 */
            clickCgEvnt : function(){
                this.$router.push({ path: 'inByReceive'});
            },
            /**点击请购购未入库数量 */
            clickQgEvnt : function(){
                this.$router.push({ path: 'inByDeptApplyList'});
            },
            /**点击调拨未入库数量 */
            clickDbEvnt : function(){
                this.$router.push({ path: 'inByMove'});
            },
            /**未到货订单点击事件 */
            clickUnArrEvt : function(){
                this.$router.push({ path: 'unRecPurBill'});
            }
        },
        mounted:function (){
            this.hosId = this.user.corpId;
            this.getDistrBillListForCg();
            this.getOutBillList();
            this.getDistrBillList();
           // this.getPurchaseList();
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
    #storeMgrHomeIndex .el-collapse-item__header {
        height: 25px;
        line-height: 25px;
        padding-left: 15px;
        color: #48576a;
        cursor: pointer;
        font-size: 13px;
        background:url(../../assets/images/portal_tab_single.gif?V=3_50_2_29);
    }
    #storeMgrHomeIndex .color-box {
        border-radius: 4px;
        height: 120px;
        box-sizing: border-box;
        color: #fff;
        font-size: 14px;
        text-align: center;
    }
    #storeMgrHomeIndex .color-box-icon {
        border-radius: 4px;
        padding: 10px;
        height: 120px;
        box-sizing: border-box;
        color: #fff;
        text-align: center;
    }  
    #storeMgrHomeIndex .value {
        display: inline-block;
        text-align: center;
        padding-top: 10px;
        color: #fff;
    }
</style>