<template>
    <el-row style="height:100%" id ="jfth_page">
        <el-row style="height:100%" v-show="visible.vInBillLst">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters">
                    <el-form-item label="入库单号">
                        <el-input v-model="sFilters.billId" placeholder="单号"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="search" v-on:click="getRecBillList(1)" >查询</el-button>
                        <el-button @click="editBill(null)">退货</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <!--列表-->
            <el-table :data="instockList" highlight-current-row v-loading="sLoading" style="width: 100%;" height="outer" fit border class="inStockLst">
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
            <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
            </el-pagination>
        </el-row>
        <el-row style="height:100%" v-show="visible.vInBillView">
            <inStockView ref="inBillViewer" @close="closeInBillViewer"></inStockView>
        </el-row>
         <el-row style="height:100%" v-show="visible.vInBIllEdit">
            <inStockView ref="inBIllEditer" @close="closeInBIllEditer"></inStockView>
        </el-row>
    </el-row>
</template>
<script>
//调拨入库
import { INSTOCK_STATUS } from '../common/constance';
import inStockView from '../inStockViewNew'
export default {
    data() {
        return {
            sFilters: {
                billId: '',
                inOrgId:this.user.corpId,
                inDeptId:this.user.orgId,
                inStockKind:5,
                filler:this.user.userId
            },
            visible:{
                vInBillLst:true,
                vInBillView:false,
                vInBIllEdit:false
            },
            sTotal: 0,
            sPage: 1,
            sPageSize: 30,
            sLoading: false,
            instockList: [
            ]            
        }
    },
    components:{inStockView},  
    methods: {
        closeInBillViewer(){
            this.visible.vInBillLst = true;
            this.visible.vInBillView=false;
            this.visible.vInBIllEdit=false;
        }, 
        closeInBIllEditer(billId){
            this.visible.vInBillLst = false;
            this.visible.vInBillView=false;
            this.visible.vInBIllEdit=true;
            if(billId){
                this.sFilters.billId = billId;
                this.getRecBillList(1);
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
                this.instockList = res.data.data.data;
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
            this.$refs.inBillViewer.show(billId);
            this.visible.vInBillLst = false;
            this.visible.vInBillView=true;
        },
        editBill(recId){
            this.$refs.inBIllEditer.showEditer(recId,"EDIT");
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
        }
    },
    mounted() {
        this.sFilters.billId = this.$route.query.inStockBillId;
        this.sFilters.purchaseCompanyId = this.user.corpId;   
        this.getRecBillList(1);
    }
}
</script>
<style>
#jfth_page{
    height: 100%;
}
#jfth_page .el-table {
    font-size: 12px;
    height:  calc(100% - 100px);
}
#jfth_page .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
</style>

