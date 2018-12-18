<template>
    <el-tabs id="in-by-send-list" type="border-card" v-model="showTab" style="height:95%">
        <el-tab-pane label="我要收货" name="purLst">
            <el-col :span="24" style="height:100%">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="配送单号">
                            <el-input v-model="filters.billId" placeholder="配送单号" @keyup.enter.native="ruku" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getDistrBillList(1)" >查询</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <!--列表-->
                <template>
                    <el-table :data="distrBillList" highlight-current-row v-loading="loading" style="width: 100%;"   fit border  class ='inDeptA-tableheight'>
                        <el-table-column prop="billId" label="配送单编号" sortable>
                        </el-table-column>
                        <el-table-column prop="status" label="状态">
                            <template slot-scope="scope">
                                {{scope.row.status | fStatus}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="purType" label="采购方式">
                            <template slot-scope="scope">
                                {{scope.row.purType == 20 ? "虚采":"实采"}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="provName" label="供应商名称" width="200" >
                        </el-table-column>
                        <el-table-column prop="fillterName" label="制单人" sortable>
                        </el-table-column>
                        <el-table-column prop="fillDate" label="制单时间">
                            <template slot-scope="scope">
                                {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150">
                            <template slot-scope="scope">
                                <el-button  @click="showPurcDialog(scope.row.id)" type="text">入库</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!--工具条-->
                    <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                        </el-pagination>
                    </el-col>                
                </template>
            </el-col>
        </el-tab-pane>
        <el-tab-pane label="采购入库单" name="purconfirmLst" style="height:100%">
            <el-row style="height:100%" v-show="visible.vInBillLst">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters">
                        <el-form-item label="入库单号">
                            <el-input v-model="sFilters.billId" placeholder="单号" ></el-input>
                        </el-form-item>
                        <el-form-item label="制单时间">
                            <el-date-picker v-model="sFilters.startDate" class="data-picker" type="date" placeholder="开始日期" ></el-date-picker>
                            <span>-</span>
                            <el-date-picker v-model="sFilters.endDate" class="data-picker" type="date" placeholder="结束日期" ></el-date-picker>
                        </el-form-item>
                        <el-form-item label="状态">
                            <el-select style="width:120px;" v-model="sFilters.billStatus" clearable placeholder="状态" >
                                <el-option
                                    v-for="item in rkdztStatus"
                                    :key="item.key"
                                    :label="item.value"
                                    :value="item.key"
                                    >
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getRecBillList(1)" >查询</el-button>
                            <el-button @click="resetForm" >重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <!--列表-->
                <el-table :data="receiveBillList" highlight-current-row v-loading="sLoading" style="width: 100%;"  fit border  class ='inDeptA-tableheight'>
                    <el-table-column prop="billId" label="入库单号" sortable width="150">
                    </el-table-column>
                     <el-table-column prop="" label="采购方式">
                        <template slot-scope="scope">
                            {{scope.row.inStockType==10?'实采':'虚采'}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="status" label="状态" :formatter="statusFormat">
                    </el-table-column>
                    <el-table-column prop="outOrgName" label="出库机构" width="250" sortable>
                    </el-table-column>                            
                    <el-table-column prop="inDeptName" label="入库部门" width="150" sortable>
                    </el-table-column> 
                    <el-table-column prop="fillterName" label="制单人" sortable>
                    </el-table-column>
                    <el-table-column prop="fillDate" label="制单时间">
                        <template slot-scope="scope">
                            {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="150">
                        <template slot-scope="scope">
                            <el-button  @click="showBill(scope.row.billId)" type="text">查看</el-button>
                            <el-button  @click="editBill(scope.row.id)" type="text" v-if="scope.row.status==10">编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--分页条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>   
            </el-row>
            <el-row style="height:100%" v-show="visible.vInBillView">
                <inStockView ref="inBillViewer" @close="closeInBillViewer"></inStockView>
            </el-row>
        </el-tab-pane>
    </el-tabs>
</template>
<script>
import { INSTOCK_STATUS,DISTRBILL_STATUS } from './common/constance';
import inStockView from './inStockViewNew'
export default {
    data() {
        return {
            rkdztStatus:[],
            sFilters: {
                billId: '',
                inStockKind:1,
                inOrgId:this.user.corpId,
                inDeptId:this.user.orgId,
                //inStockType:-1,
                startDate: Date.now() - 7 * 24 * 60 * 60 * 1000,
                endDate: Date.now(),
                billStatus: '40',//已提交
                filler:this.user.userId
            },
            showTab:'purLst',
            sTotal: 0,
            sPage: 1,
            sPageSize: 30,
            sLoading: false,
            receiveBillList: [
            ],

            filters: {
                billId: '',
                purchaseCompanyId: this.user.corpId,
                recieveOrgId:this.user.orgId
                
            },
            total: 0,
            page: 1,
            pageSize: 30,
            loading: false,
            distrBillList: [
            ],
            visible:{
                vInBillLst:true,
                vInBillView:false
            }
        }
    },
     components:{inStockView},
    filters: {
        fStatus: function (value) {
            if (value) {
                return DISTRBILL_STATUS[value].text;
            } else {
                return '';
            }
        },
        inStockStatus:function(value){
            if (value) {
                return INSTOCK_STATUS[value].text;
            } else {
                return '';
            }
        }
    },    
    methods: {
        //获取列表
        getRecBillList: function (pIndex) {
            this.sPage = pIndex;
            var param = {
                orderBy: "",
                page: this.sPage,
                rows: this.sPageSize,
                queryObject: {
                    billId: this.sFilters.billId,
                    inStockKind:this.sFilters.inStockKind,
                    inOrgId:this.sFilters.inOrgId,
                    inDeptId:this.sFilters.inDeptId,
                    billStatus:this.sFilters.billStatus,
                    startDate:this.sFilters.startDate,
                    endDate:this.sFilters.endDate,
                    inStockType:this.sFilters.inStockType,
                    filler:this.sFilters.filler
                }
            };
            this.sLoading = true;
            this.axios.post('/spdHERPService/stockMgr/inStock/listByPage', param).then(res => {
                this.sLoading = false;
                this.receiveBillList = res.data.data.data;
                this.sTotal = res.data.data.total;
            }, err => {
                this.sLoading = false;
            });
        },
        sCurrentChange(val) {
            this.sPage = val;
            this.getRecBillList(this.sPage);
        },
        sSizeChange(val) {
            this.sPageSize = val;
            this.getRecBillList(this.sPage);
        },      
        showBill(billId) {
           // this.$router.push({ path: 'inStockView', query: { billId: billId } });
            this.$refs.inBillViewer.show(billId);
            this.visible.vInBillLst = false;
            this.visible.vInBillView=true;
        },
        closeInBillViewer(){
            this.visible.vInBillLst = true;
            this.visible.vInBillView=false;
        },
        editBill(recId){
            this.$router.push({ path: 'inBySendEdit', query: { recId: recId } });
        },
        resetForm(){
            this.sFilters.billId = "";
            this.sFilters.startDate = "";
            this.sFilters.endDate = "";
            this.sFilters.billStatus = "";
        },
        statusFormat(row) {
            return INSTOCK_STATUS[row.status].text;
        },


        //获取列表
        getDistrBillList: function (pIndex) {
            this.page = pIndex;
            var param = {
                orderBy: "",
                page: this.page,
                rows: this.pageSize,
                queryObject: {
                    billId: this.filters.billId,
                    purchaseCompanyId: this.filters.purchaseCompanyId,
                    arrStatus: [20,30]
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/orderMgr/distrBill/listByPage', param).then(res => {
                this.loading = false;
                this.distrBillList = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getDistrBillList(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getDistrBillList(this.page);
        },       
        showPurcDialog(billId) {            
            this.$router.push({ path: 'inBySendEdit', query: { distrBillId: billId } });
        },
        ruku(){
            this.showPurcDialog(this.filters.billId)
        },
    },
    mounted() {
        this.sFilters.purchaseCompanyId = this.user.corpId;   
        this.getRecBillList(1);
        this.filters.purchaseCompanyId = this.user.corpId;
        this.getDistrBillList(1);
        for (let k of Object.keys(INSTOCK_STATUS)) {
            this.rkdztStatus.push({key:k,value:INSTOCK_STATUS[k].text})
        }
    }
}
</script>
<style>
#in-by-send-list .el-table {
    font-size: 12px;
}
#in-by-send-list .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#in-by-send-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
#in-by-send-list .inDeptA-tableheight{
    height:  calc(100% - 70px);
}
</style>

