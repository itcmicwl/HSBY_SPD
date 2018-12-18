<template>
    <el-row style="height:100%" id="goods_need_pur">
        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" label-width="95px">
                <el-form-item label="产品名称：">
                    <el-input v-model="filters.goodsName" placeholder="产品名称："></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" v-on:click="search(1)" >查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-document" v-on:click="creatPurBill" >生成补货单</el-button>
                </el-form-item>
            </el-form>
        </el-row>
        <el-row  style="height:calc(100% - 98px);">
            <el-table :data="goodsList" highlight-current-row v-loading="loading" style="width: 100%;height100%" height="outer" 
            fit border row-key="goodsId" class ='pur-goods-table' @selection-change="sltGoodsChange">
                <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>     
                <el-table-column prop="goodsName" label="品名" sortable>
                </el-table-column>
                <el-table-column prop="goodsGg" label="规格" sortable>
                </el-table-column>
                <el-table-column prop="brand" label="品牌" sortable>
                </el-table-column>
                <el-table-column prop="made" label="产地" sortable>
                </el-table-column>                
                <el-table-column label="库存上下限" sortable>
                    <template slot-scope="props">
                        {{props.row.stocLower}}/{{props.row.stocUpper}}
                    </template>
                </el-table-column>
                <el-table-column prop="stocQty" label="库存量" sortable>
                </el-table-column>
                <el-table-column prop="purQty" label="未到货数量" sortable>
                </el-table-column>
                <el-table-column prop="needQty" label="补货数量" sortable>
                </el-table-column>
            </el-table>
            <!--工具条-->
            <el-col :span="24" class="toolbar">
                <el-pagination @size-change="sizeChange" @current-change="pageChange" :current-page="pageInfo.page" :page-sizes="[30, 100, 200, 400]" :page-size="pageInfo.pageSize" layout="total, sizes, prev, pager, next" :total="pageInfo.total" style="float:right;">
                </el-pagination>
            </el-col>    
        </el-row>
    </el-row>   
</template>
<script>
export default {
    data() {
        return {
            filters:{
                    goodsName:''
                },
                pageInfo:{
                    page:1,
                    pageSize:30,
                    total:0
                },
                loading:false,
                goodsList:[],
                sltGoodsList:[]
        }
    },
    methods: {
        sizeChange(pageSize){
            this.pageInfo.pageSize=pageSize;
            this.search(this.pageInfo.page);
        },
        pageChange(pindex){
            this.search(pindex);
        },
        search(pIndex){
            this.goodsList=[];
            this.pageInfo.page = pIndex;
            var param = {
                orderBy: "",
                page: this.pageInfo.page,
                rows: this.pageInfo.pageSize,
                queryObject: {
                    goodsName: this.filters.applyBillId,
                    deptId:this.user.orgId,
                    hosId: this.user.corpId
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/stockPile/getGoodsInfo4AutoPurByPage', param).then(res => {
                this.loading = false;
                this.goodsList = res.data.data.data;
                this.pageInfo.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        sltGoodsChange(rows){
            this.sltGoodsList = rows;
        },
        creatPurBill(){
            this.submitLoading = true;
            if(this.sltGoodsList.length == 0){
                this.$message.error('请选择要补货的品种');
                return;
            }
            this.axios.post('/spdHERPService/order/purchase/creatPurBillByStoc',this.sltGoodsList).then(res=>{
                this.submitLoading = false;
                if(res.data.code == 0){
                    this.$message.success('操作成功');
                    this.search(1);
                }
            },err=>{this.submitLoading = false; this.$message.error('操作失败');});
        },
    },    
    mounted() {
        this.search(1);
    }
}
</script>
<style>
.pur-goods-table{
    height:100%
}
</style>

