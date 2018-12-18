<template>
<el-row style="height:100%">
    <el-tabs id="in-by-returns-list" type="border-card" v-model="showTab">
        <el-tab-pane label="退货入库单列表" name="moveLst">
            <el-row style="height:100%" v-show="visible.vInBillLst">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters">
                        <el-form-item label="入库单号">
                            <el-input v-model="sFilters.billId" placeholder="单号" @keyup.enter.native="ruku"  ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getRecBillList(1)" >查询</el-button>
                            <el-button @click="resetForm" >重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <!--列表-->
                <el-table :data="receiveBillList" highlight-current-row v-loading="sLoading" style="width: 100%;" height="outer" fit border class="inStockLst">
                    <el-table-column prop="billId" label="入库单号" sortable width="150">
                    </el-table-column>
                    <el-table-column prop="status" label="状态" width="60" :formatter="statusFormat">
                    </el-table-column>
                    <el-table-column prop="outOrgName" label="出库机构" width="200" show-overflow-tooltip sortable>
                    </el-table-column>
                    <el-table-column prop="outDeptName" label="出库部门" width="150" show-overflow-tooltip sortable>
                    </el-table-column>
                    <el-table-column prop="inDeptName" label="入库部门" width="150" show-overflow-tooltip sortable>
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
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                </el-pagination>
            </el-row>
            <el-row style="height:100%" v-show="visible.vInBillView">
                <inStockView ref="inBillViewer" @close="closeInBillViewer"></inStockView>
            </el-row>
        </el-tab-pane>
        <el-tab-pane label="退货入库">
            <el-row style="height:100%">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="单号">
                            <el-input v-model="filters.billId" placeholder="单号" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getDistrBillList(1)" >查询</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <!--列表-->
                <template>
                    <el-table :data="distrBillList" highlight-current-row v-loading="loading" style="width: 100%;" fit>
                        <el-table-column prop="billId" label="退货出库单号" sortable>
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
                        <el-table-column label="操作" width="160">
                            <template slot-scope="scope">
                                <el-button  @click="showPurcDialog(scope.row.id)" type="text">入一级库</el-button>
                                <el-button  @click="showPurcSupDialog(scope.row.id)" type="text">退至供货商</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!--工具条-->
                    <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
                        </el-pagination>
                    </el-col>
                </template>
            </el-row>
        </el-tab-pane>
    </el-tabs>
    <inByReturnsEdit ref ="returnEdtier" @close="EditerClose"></inByReturnsEdit>
    <inByReturnSupEdit ref ="returnSupEdtier" @close="EditerClose"></inByReturnSupEdit>
</el-row>
</template>
<script>
//调拨入库
import { INSTOCK_STATUS } from './common/constance';
import inByReturnsEdit from './inByReturnsEdit';
import inStockView from './inStockViewNew';
import inByReturnSupEdit from './inByReturnsSupEdit';
export default {
    data() {
        return {
            sFilters: {
                billId: '',
                inOrgId:this.user.corpId,
                inDeptId:this.user.orgId,
                inStockKind:4,
                filler:this.user.userId
            },
            visible:{
                vInBillLst:true,
                vInBillView:false
            },
            showTab:'moveLst',
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
                outStockKind: 50,
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
    components:{inByReturnsEdit,inStockView,inByReturnSupEdit},
    methods: {
        closeInBillViewer(){
            this.visible.vInBillLst = true;
            this.visible.vInBillView=false;
        },
        EditerClose(param){
            if(param && param.type == "ADD"){
                this.getRecBillList(1);
                this.getDistrBillList(1);
            }
        },
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
                this.total = res.data.data.total;
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
            //this.$router.push({ path: 'inStockView', query: { billId: billId } });
            this.$refs.inBillViewer.show(billId);
            this.visible.vInBillLst = false;
            this.visible.vInBillView=true;
        },
        editBill(recId){
            //this.$router.push({ path: 'returnsEdit', query: { recId: recId } });
            this.$refs.returnEdtier.showEditer(recId,"EDIT");
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
                    //inDeptId: this.filters.inDeptId,
                    outStockKind:this.filters.outStockKind,
                    status:this.filters.status
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/stockMgr/out4In/getBillList4Instock', param).then(res => {
                this.loading = false;
                this.distrBillList = res.data.data.data;
                this.sTotal = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getRecBillList(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getRecBillList(this.page);
        },
        showPurcDialog(billId) {
            //this.$router.push({ path: 'returnsEdit', query: { outBillId: billId } });
            this.$refs.returnEdtier.showEditer(billId,"ADD");
        },
      showPurcSupDialog(billId){
          this.$refs.returnSupEdtier.showEditerSup(billId,"ADD");
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
#in-by-returns-list{
    height: 100%;
}
#in-by-returns-list .el-table {
    font-size: 12px;
    height:  calc(100% - 130px);
}
#in-by-returns-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
</style>

