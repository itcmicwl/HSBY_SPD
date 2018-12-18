<template>
    <el-row style="height:100%">
        <el-tabs id="sur-List" type="border-card" v-show="pageVisible" v-model="showTab" style="height:98%">
            <el-tab-pane label="已打手术包" name="surPkgTab"  style="height:100%">
                <el-row class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="请购单号">
                            <el-input v-model="surPkg.filters.applyBillId" placeholder="请购单号"></el-input>
                        </el-form-item>
                        <el-form-item label="手术包名">
                            <el-input v-model="surPkg.filters.surName" placeholder="手术包名"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="searchSurPkg(1)" >查询</el-button>
                        </el-form-item>
                    </el-form>
                </el-row>
                <el-row  style="height:calc(100% - 98px);">
                    <el-table :data="surPkg.surPkgList" highlight-current-row v-loading="surPkg.loading" style="width: 100%;" fit border  class ='sur-List-table'>
                        <el-table-column prop="applyBillId" label="请购单号" sortable>
                        </el-table-column>
                        <el-table-column prop="surCode" label="手术包码" sortable>
                        </el-table-column>
                        <el-table-column prop="surName" label="手术包名" sortable>
                        </el-table-column>
                        <el-table-column prop="deptName" label="科室" sortable>
                        </el-table-column>
                        <el-table-column prop="stocName" label="库房" sortable>
                        </el-table-column>
                        <el-table-column prop="status" label="状态" sortable>
                             <template slot-scope="scope">
                                {{surStatusMap[scope.row.status].text}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="packerName" label="打包人" sortable>
                        </el-table-column>
                        <el-table-column prop="packageDate" label="制单时间">
                            <template slot-scope="scope">
                                {{scope.row.packageDate | dateFormat('YYYY-MM-DD')}}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="200">
                            <template slot-scope="scope">
                                <el-button  @click="surBillView(scope.row.id)" type="text">查看</el-button>
                                <el-button  @click="surBillPrint(scope.row.id)" type="text">打印</el-button>
                                <el-button v-if="scope.row.status == 0"  @click="surBillPack(scope.row.id)" type="text">打包</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!--工具条-->
                    <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="surSizeChange" @current-change="surPageChange" :current-page="surPkg.pageInfo.page" :page-sizes="[30, 100, 200, 400]" :page-size="surPkg.pageInfo.pageSize" layout="total, sizes, prev, pager, next" :total="surPkg.pageInfo.total" style="float:right;">
                        </el-pagination>
                    </el-col>    
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="待打手术包" name="applyTab" style="height:100%">
                <el-row>
                    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <el-form :inline="true" label-width="80px">
                            <el-form-item label="请购单号">
                                <el-input v-model="applyObj.filters.billId" placeholder="请购单号"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" icon="search" v-on:click="searchApplyBIllList(1)" >查询</el-button>
                            </el-form-item>
                        </el-form>
                    </el-col>
                </el-row>
                <el-row  style="height:calc(100% - 98px);">
                    <el-table :data="applyObj.applyBillList" highlight-current-row v-loading="applyObj.loading" style="width: 100%;" fit border  class ='sur-List-table'>
                        <el-table-column prop="billId" label="请购单号" sortable>
                        </el-table-column>
                        <el-table-column prop="state" label="状态" sortable :formatter="statusFormat"></el-table-column>
                        <el-table-column prop="buyDeptName" label="请购科室" sortable> </el-table-column>
                        <el-table-column prop="fillterName" label="制单人" sortable></el-table-column>
                        <el-table-column prop="fillDate" label="制单时间">
                            <template slot-scope="scope">
                                {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150">
                            <template slot-scope="scope">
                                <el-button  @click="applyBillView(scope.row.id)" type="text">查看</el-button>
                                <el-button  @click="surPack(scope.row.id)" type="text">打包</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!--工具条-->
                    <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="applySizeChange" @current-change="applyPageChange" :current-page="applyObj.pageInfo.page" :page-sizes="[30, 100, 200, 400]" :page-size="applyObj.pageInfo.pageSize" layout="total, sizes, prev, pager, next" :total="applyObj.pageInfo.total" style="float:right;">
                        </el-pagination>
                    </el-col>  
                </el-row>                
            </el-tab-pane>
        </el-tabs>
        <surPkgBill ref="surPkgBill" v-show="packVisible" @cancel="packCancel"></surPkgBill>
         <surPkgBillByApply ref="surPkgBillByApply" v-show="packByApplyVisible" @cancel="packCancel"></surPkgBillByApply>
        <surPkgBillView ref="surPkgBillView" v-show="viewVisible" @cancel="onViewClose"></surPkgBillView>
    </el-row>   
</template>
<script>
import { DEPTBUY_MAIN_STATUS } from '@/views/applyMgr/common/constance';
import surPkgBill from './surPkgBillBySur.vue';
import surPkgBillByApply from './surPkgBillByApply.vue';
import surPkgBillView from './surPkgBillView.vue';
import {SURGERY_PKG_STATUS} from '../common/constance'
export default {
    data() {
        return {
            showTab:'surPkgTab',
            surPkg:{
                filters:{
                    applyBillId:'',
                    surName:''
                },
                pageInfo:{
                    page:1,
                    pageSize:30,
                    total:0
                },
                loading:false,
                surPkgList:[],
            },
            applyObj:{
                filters:{
                    billId:'',
                },
                pageInfo:{
                    page:1,
                    pageSize:30,
                    total:0
                },
                loading:false,
                applyBillList:[],
            },
            surStatusMap:SURGERY_PKG_STATUS,
            packVisible:false,
            viewVisible:false,
            pageVisible:true,
            packByApplyVisible:false,
        }
    },
    components:{surPkgBill,surPkgBillView,surPkgBillByApply},
    methods: {
        surSizeChange(pageSize){
            this.surPkg.pageInfo.pageSize=pageSize;
            this.searchSurPkg(this.surPkg.pageInfo.page);
        },
        surPageChange(pindex){
            this.searchSurPkg(pindex);
        },
        searchSurPkg(pIndex){
            this.surPkg.surPkgList=[];
            this.surPkg.pageInfo.page = pIndex;
            var param = {
                orderBy: "",
                page: this.surPkg.pageInfo.page,
                rows: this.surPkg.pageInfo.pageSize,
                queryObject: {
                    applyBillId: this.surPkg.filters.applyBillId,
                    surName:this.surPkg.filters.surName,
                    hosId: this.user.corpId
                }
            };
            this.surPkg.loading = true;
            this.axios.post('/spdHERPService/surgeryPkg/listByPage', param).then(res => {
                this.surPkg.loading = false;
                this.surPkg.surPkgList = res.data.data.data;
                this.surPkg.pageInfo.total = res.data.data.total;
            }, err => {
                this.surPkg.loading = false;
            });
        },
        surBillView(surBillId){
            this.pageVisible = false;
            this.packVisible = false;
            this.viewVisible = true;
            this.packByApplyVisible=false;
            this.$refs.surPkgBillView.show(surBillId);
        },
        surBillPrint(billId){
            this.$refs.surPkgBillView.print(billId);
        },
        surBillPack(billId){
            this.packVisible = true;
            this.pageVisible = false;
            this.viewVisible = false;
            this.packByApplyVisible=false;
            this.$refs.surPkgBill.show(billId);
        },
        surPack(billId){
            this.packVisible = false;
            this.pageVisible = false;
            this.viewVisible = false;
            this.packByApplyVisible=true;
            this.$refs.surPkgBillByApply.show(billId);
        },
        packCancel(flag){
            if(flag===true){
                this.searchApplyBIllList(this.applyObj.pageInfo.page);
                this.searchSurPkg(this.surPkg.pageInfo.page);
            }
            this.packVisible = false;
            this.pageVisible = true;
            this.viewVisible = false;
            this.packByApplyVisible=false;
        },
        onViewClose(){
            this.packVisible = false;
            this.pageVisible = true;
            this.viewVisible = false;
            this.packByApplyVisible=false;
        },
        applySizeChange(pageSize){
            this.applyObj.pageInfo.pageSize=pageSize;
            this.searchApplyBIllList(this.applyObj.pageInfo.page);
        },
        applyPageChange(pindex){
            this.searchApplyBIllList(pindex);
        },
        searchApplyBIllList(pIndex){
            this.applyObj.pageInfo.page = pIndex;
            var param = {
                orderBy: "",
                page: this.applyObj.pageInfo.page,
                rows: this.applyObj.pageInfo.pageSize,
                queryObject: {
                    billId: this.applyObj.filters.billId,
                    hosId: this.user.corpId
                }
            };
            this.applyObj.loading = true;
            this.axios.post('/spdHERPService/applyMgr/deptBuyMain/unPackApplyBIllList', param).then(res => {
                this.applyObj.loading = false;
                this.applyObj.applyBillList = res.data.data.data;
                this.applyObj.pageInfo.total = res.data.data.total;
            }, err => {
                this.applyObj.loading = false;
            });
        },
        applyBillView(surBillId){
            this.$refs['viewBuy'].open(billId);
        },
        statusFormat(row) {
            return DEPTBUY_MAIN_STATUS[row.state].text;
        },
    },
    mounted() {
        this.searchSurPkg(1);
        this.searchApplyBIllList(1);
    }
}
</script>
<style>
#sur-List .el-table {
    font-size: 12px;
}
#sur-List .el-tabs,.el-tabs .el-tabs__content{
    height:99%;
}
#sur-List .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
#sur-List .sur-List-table{
    height:  100%
}
</style>

