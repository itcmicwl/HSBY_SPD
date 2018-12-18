<template>
    <el-row style="height:100%;">
        <el-row :gutter="10" style="height:100%;" v-show="!billViewV">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form :inline="true" label-width="80px">
                    <el-form-item label="采购单号">
                        <el-input v-model="filters.billId" placeholder="采购单号" ></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select style="width:120px;" v-model="filters.status" placeholder="全部" >
                            <el-option
                            v-for="item in statusDropDown"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                        <el-form-item label="制单时间">
                        <el-date-picker v-model="filters.sDate" type="daterange" align="right" placeholder="选择日期范围" :picker-options="pickerOptions2" >
                        </el-date-picker>
                    </el-form-item>                       
                    <el-form-item label="采购方式">
                        <el-select style="width:120px;" v-model="filters.purType"  placeholder="请选择采购方式">
                        <el-option v-for="item in buy_kind"
                            :key="item.value"  :label="item.text" :value="item.value"
                            :disabled="item.flag">
                        </el-option>
                        </el-select>
                    </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getPurchaseList(1)" size="mini">查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="edit" size="mini" v-gy-permission="'add'" @click.native="addPurchase" >我要采购</el-button>
                        </el-form-item>
                </el-form>
            </el-col>                
            <!--列表-->
            <el-table :data="purchaseList" highlight-current-row v-loading="loading" style="width: 100%;" fit class="table_content" border>
                <el-table-column prop="billId" label="采购单号" sortable>
                </el-table-column>
                <el-table-column prop="status" label="状态" sortable :formatter="statusFormat">
                </el-table-column>
                <el-table-column prop="provName" label="供应商名称" sortable>
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
                        <el-button  @click="PurchaseView(scope.row.billId)" type="text">查看</el-button>
                        <el-button  v-if="scope.row.status == 5" @click="PurchaseView(scope.row.billId)" v-gy-permission="'audit'" type="text">审核</el-button>
                        <el-button v-if="scope.row.status == 10 || scope.row.status==4"  @click="editPurchase(scope.row.billId)" type="text">编辑</el-button>
                        <el-button  v-if="scope.row.status == 10" @click="delPurchase(scope.row)" type="text">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--工具条-->
            <el-col :span="24" class="toolbar">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                </el-pagination>
            </el-col>
        </el-row>
        <purBillView ref="purBillView" v-show="billViewV" @cancel="viewCancel"></purBillView>
    </el-row>
</template>
<script>
import purBillView from './purBillView.vue'
import { PURCHASE_STATUS } from './common/constance';
import { PURCHASE_STATUS_SELECT } from './common/constance';
import moment from 'moment';
import { mapGetters } from 'vuex';
import {BUY_KIND} from './common/constance'
export default {
    data() {
        return {
            billViewV:false,
            filters: {
                billId: '',
                purchaseComId: this.user.corpId,
                purType:10,
                status:'',
                sDate:[]
            },
            total: 0,
            page: 1,
            pageSize: 30,
            loading: false,
            purchaseList: [
            ],
            statusDropDown:[],
            pickerOptions2: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            }
        }
    },
    components:{purBillView},
    computed: {
        ...mapGetters(["getLoginUser"]),
        buy_kind:{
            get:function(){return BUY_KIND;}
        }
    },
    methods: {
        addPurchase() {
            this.$router.push({ path: 'addPurchase', query: {purType:this.filters.purType } });
        },
        //获取列表
        getPurchaseList: function (pIndex) {
            this.page = pIndex;
            var date = new Date();
            var param = {
                orderBy: "",
                page: this.page,
                rows: this.pageSize,
                queryObject: {
                    billId: this.filters.billId,
                    purchaseComId: this.filters.purchaseComId,
                    purType:this.filters.purType,
                    flag:1,
                    status:this.filters.status,
                    sDate: this.filters.sDate&&this.filters.sDate[0]!=null?moment(this.filters.sDate[0]).format("YYYY-MM-DD"):null,
                    eDate: this.filters.sDate&&this.filters.sDate[1]!=null?moment(moment(this.filters.sDate[1]).add(1,'days').format('YYYY-MM-DD')).subtract(1,'seconds').format('YYYY-MM-DD HH:mm:ss'):null,
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/order/purchase/purchasePage', param).then(res => {
                this.loading = false;
                this.purchaseList = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getPurchaseList(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getPurchaseList(this.page);
        },
        delPurchase(purchase) {
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.loading = true;
                this.axios.post("/spdHERPService/order/purchase/delPurchase", purchase).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.loading = false;
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        this.$message.error('操作失败！');
                    }
                    this.getPurchaseList(this.page);
                }, err => {
                    this.loading = false;
                });
            }).catch(() => {

            });
        },
        statusFormat(row) {
            return PURCHASE_STATUS[row.status].text;
        },
        editPurchase(billId) {
            this.$router.push({ path: 'addPurchase', query: { billId: billId,purType:10 } });
        },
        // PurchaseView(billId) {
        //     this.$router.push({ path: 'purchaseView', query: { billId: billId } });
        // },
        PurchaseView(billId){
            this.$refs.purBillView.show(billId);
            this.billViewV = true;
        },
        viewCancel(flag){
            if(flag === true){
                this.getPurchaseList(1);
            }
            this.billViewV = false;
        }
    },
    mounted() {
        this.statusDropDown = PURCHASE_STATUS_SELECT;
        this.filters.purchaseComId = this.getLoginUser.corpId;
         this.filters.purType = Number(this.$route.query.purType || 10);
        var billId = this.$route.query.billId;
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
        this.filters.sDate = [start,end];
        if (billId) {
            this.filters.billId = billId;
        }        
        this.getPurchaseList(1);
    }
};

</script>

<style>
.table_content{
    font-size: 12px;
    height: calc(100% - 90px);
    overflow-y: auto;
}
</style>
