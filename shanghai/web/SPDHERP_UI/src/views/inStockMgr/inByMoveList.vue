<template>
    <el-tabs id="in-by-move-list" type="border-card" v-model="showTab">
        <el-tab-pane label="调拨入库" name="movingLst">
            <el-col :span="24">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="单号">
                            <el-input v-model="filters.billId" placeholder="单号"  @keyup.enter.native="ruku" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getDistrBillList(1)" >查询</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <!--列表-->
                <template>
                    <el-table :data="distrBillList" highlight-current-row v-loading="loading" style="width: 100%;" fit height="400">
                        <el-table-column prop="billId" label="调拨出库单号" sortable>
                        </el-table-column>                        
                        <el-table-column prop="outOrgName" label="出库部门" sortable>
                        </el-table-column>
                        <el-table-column prop="inOrgName" label="入库部门" sortable>
                        </el-table-column>
                        <el-table-column prop="filler" label="制单人" sortable>
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
        <el-tab-pane label="调拨入库单列表" name="moveLst">
            <section>
                <el-col :span="24">
                    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters">
                            <el-form-item label="入库单号">
                                <el-input v-model="sFilters.billId" placeholder="单号" ></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" icon="search" v-on:click="getRecBillList(1)" >查询</el-button>
                                <el-button @click="resetForm" >重置</el-button>
                            </el-form-item>
                        </el-form>
                    </el-col>
                    <!--列表-->
                    <template>
                        <el-table :data="receiveBillList" highlight-current-row v-loading="sLoading" style="width: 100%;" fit height="400" border>
                            <el-table-column prop="billId" label="入库单号" sortable width="150">
                            </el-table-column>
                            <el-table-column prop="status" label="状态" :formatter="statusFormat">
                            </el-table-column>
                            <el-table-column prop="outOrgName" label="出库机构" sortable>
                            </el-table-column>
                            <el-table-column prop="outDeptName" label="出库部门" sortable>
                            </el-table-column>
                            <el-table-column prop="inDeptName" label="入库部门" sortable>
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
                        <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
                        </el-pagination>
                    </template>
                </el-col>
            </section>
        </el-tab-pane>
    </el-tabs>
</template>
<script>
//调拨入库
import { INSTOCK_STATUS } from './common/constance';
export default {
    data() {
        return {
            sFilters: {
                billId: '',
                inOrgId:this.user.corpId,
                inDeptId:this.user.orgId,
                inStockKind:3,
                filler:this.user.userId
            },
            showTab:'movingLst',
            sTotal: 0,
            sPage: 1,
            sPageSize: 30,
            sLoading: false,
            receiveBillList: [
            ],

            filters: {
                billId: '',
                inOrgId: this.user.corpId,
                inDeptId: this.user.orgId,
                outStockKind: 10,
                status:30
            },
            total: 0,
            page: 1,
            pageSize: 30,
            loading: false,
            distrBillList: [
            ]        
        }
    },
    filters: {
        
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
                    inDeptId:this.sFilters.inDeptId
                }
            };
            this.sLoading = true;
            this.axios.post('/spdHERPService/stockMgr/inStock/listByPage', param).then(res => {
                this.sLoading = false;
                this.receiveBillList = res.data.data.data;
                this.sTotal = res.data.data.sTotal;
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
            this.$router.push({ path: 'inStockView', query: { billId: billId } });
        },
        editBill(recId){
            this.$router.push({ path: 'moveEdit', query: { recId: recId } });
        },
        resetForm(){
            this.sFilters.billId = "";
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
                    inOrgId: this.filters.inOrgId,
                    inDeptId: this.filters.inDeptId,
                    outStockKind:this.filters.outStockKind,
                    status:this.filters.status
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/stockMgr/out4In/getBillList4Instock', param).then(res => {
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
            this.$router.push({ path: 'moveEdit', query: { outBillId: billId } });
        },
        ruku(){
            this.showPurcDialog(this.filters.billId)
        }
    },
    mounted() {
        this.sFilters.billId = this.$route.query.inStockBillId;
        this.sFilters.purchaseCompanyId = this.user.corpId;   
        this.getRecBillList(1);
        this.filters.purchaseCompanyId = this.user.corpId;
        this.getDistrBillList(1);
    }
}
</script>
<style>
#in-by-move-list .el-table {
    font-size: 12px;
}
#in-by-move-list .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#in-by-move-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
</style>

