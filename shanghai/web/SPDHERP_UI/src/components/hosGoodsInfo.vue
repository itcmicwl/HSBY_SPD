<template>
    <section id="hos-goods-info">
        <el-dialog title="批量导入产品" show-close close="onClose" :visible.sync ="visiable" :close-on-click-modal="true" size="importDialog">
            <el-form :inline="true" label-width="80px" ref="filterForm">
                <el-form-item label="商品名称" prop="goodsName">
                    <el-input v-model="filters.goodsName" placeholder="输入产品名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="select" >查询</el-button>
                <el-button type="primary" v-on:click="resetHosGoodName('filterForm')" >重置</el-button>
            </el-form>
            <template>
                <el-table ref="hosGoodsInfoTable" :data="hosGoodsInfos" v-loading="loading" highlight-current-row  border fit tooltip-effect="dark" row-key='id' style="width: 100%" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column prop="code" label="商品编号" header-align="center"  width="120" ></el-table-column>
                    <el-table-column prop="goodsName" header-align="center" label="产品名称" max-width="300px"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="280"></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                                   :current-page="page" :page-sizes="[10, 20, 50, 100]"
                                   :page-size="pageSize" layout="total, sizes, prev, pager, next"
                                   :total="total" style="float:right;" small>
                    </el-pagination>
                    <el-button @click="toggleSelection()" >取消选择</el-button>
                    <el-button @click="submit()" type="success" >确定</el-button>
                    <el-button @click="visiable = false" type="primary" >返回</el-button>
                </div>
            </template>
        </el-dialog>
    </section>
</template>
<script>
export default {
    data() {
        return {
            baseUrl:'/spdHERPService/myGoods/hosGoods',
            filters:{
                goodsName:''
            },
            // 当前页分页
            total: 0,
            page: 1,
            pageSize: 20,
            loading: false,
            hosGoodsInfos:[]
        };
    },
    props: ['hosId'],
    computed: {
        visiable :{
            get:function(){
                return this.$store.state.dialog.show;
            },
            set(val){
                if(this.multipleSelection.length > 0){
                    this.multipleSelection = [];
                    this.$refs.hosGoodsInfoTable.clearSelection();
                }
                this.$store.state.dialog.show = val;
            }
        }
    },
    methods: {
        getHosGoodsInfo:function(pIndex){
            this.multipleSelection = [];
            this.page = pIndex;
            var param = {
                orderBy: "",
                queryObject: {
                    hosId:this.hosId,
                    goodsName : this.filters.goodsName
                },
                page: this.page,
                rows: this.pageSize
            };
            this.loading = true;
            this.axios.post(this.baseUrl + '/getGoodsInfos', param).then(res => {
                this.loading = false;
                this.hosGoodsInfos = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        resetHosGoodName:function (formName) {
            this.filters.goodsName = '';
            this.$refs.hosGoodsInfoTable.clearSelection();
            this.getHosGoodsInfo(1);
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getHosGoodsInfo(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getHosGoodsInfo(this.page);
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.hosGoodsInfoTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.hosGoodsInfoTable.clearSelection();
            }
        },
        select(){
            this.$refs.hosGoodsInfoTable.clearSelection();
            this.getHosGoodsInfo(1);
        },
        submit(){
            if(this.multipleSelection.length <= 0){
                    this.$message.error( "至少选择一条数据！");
                    return;
            }
            var ids = [];
            for(var key in this.multipleSelection){
                ids.push(this.multipleSelection[key].id);
            }
            this.$emit('importSubmit', ids, this.multipleSelection);
        }
    },
    mounted() {
        // 查询医院产品信息
        this.getHosGoodsInfo(1);
    }
};
</script>
<style>
#hos-goods-info .el-dialog--importDialog {
        max-width: 1200px;
        min-width: 1118px;
        width: 90%;
    }
#hos-goods-info .el-dialog--importDialog .el-form-item {
    margin-bottom: 7px;
}
</style>