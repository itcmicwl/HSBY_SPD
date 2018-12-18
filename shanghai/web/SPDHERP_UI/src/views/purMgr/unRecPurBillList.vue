<template>
    <el-row id="un-rec-pur-bill-list" :gutter="10">
        <section>
            <el-col :span="24">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="采购单号">
                            <el-input v-model="filters.billId" placeholder="采购单号" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search" v-on:click="getPurchaseList(1)"  >查询</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-button-group>
                        <el-button type="primary" icon="edit" size="mini" @click.native="addPurchase" >我要采购</el-button>
                    </el-button-group>
                </el-col>
                <!--列表-->
                <template>
                    <el-table :data="purchaseList" highlight-current-row v-loading="loading" style="width: 100%;" fit height="400">
                        <el-table-column prop="billId" label="采购单号" sortable>
                        </el-table-column>
                        <el-table-column prop="status" label="状态" sortable :formatter="statusFormat">
                        </el-table-column>
                        <el-table-column prop="purKind" label="采购方式" sortable :formatter="purKindFormat">
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
                                <el-button v-if="scope.row.status == 10"  @click="editPurchase(scope.row.billId)" type="text">编辑</el-button>
                                <el-button  v-if="scope.row.status == 10" @click="delPurchase(scope.row)" type="text">删除</el-button>
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
        </section>
    </el-row>
</template>
<script>
import { PURCHASE_STATUS,PURCHASE_KIND } from './common/constance';
import { mapGetters } from 'vuex';
export default {
    data() {
        return {
            filters: {
                billId: '',
                purchaseComId: '',
                arrStatus:[30,40],
                flag:1              
            },
            total: 0,
            page: 1,
            pageSize: 30,
            loading: false,
            purchaseList: [
            ]
        }
    },
    computed: {
        ...mapGetters(["getLoginUser"])
    },
    methods: {
        addPurchase() {
            this.$router.push("addPurchase");
        },
        //获取列表
        getPurchaseList: function (pIndex) {
            this.page = pIndex;
            var param = {
                orderBy: "",
                page: this.page,
                rows: this.pageSize,
                queryObject: {
                    billId: this.filters.billId,
                    purchaseComId: this.filters.purchaseComId,                   
                    arrStatus:this.filters.arrStatus,
                    flag:this.filters.flag
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
        purKindFormat(row){
            row.purKind = row.purKind || 0;
            return PURCHASE_KIND[row.purKind].text;
        },
        editPurchase(billId) {
            this.$router.push({ path: 'addPurchase', query: { billId: billId } });
        },
        PurchaseView(billId) {
            this.$router.push({ path: 'purchaseView', query: { billId: billId } });
        }
    },
    mounted() {
        this.filters.purchaseComId = this.getLoginUser.corpId;
        var billId = this.$route.query.billId;
        if (billId) {
            this.filters.billId = billId;
        }        
        this.getPurchaseList(1);
    }
};

</script>

<style>
#un-rec-pur-bill-list .el-table {
    font-size: 12px;
}
</style>