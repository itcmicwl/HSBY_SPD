<template>
    <div  style="height: 100%;">
        <el-row height="auto" style="height: 100%;">
            <!-- 供应商列表 -->
            <el-col :span="5" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
                <div style="padding-bottom: 3px;">
                    <el-form :inline="true" :model="filters" ref="provInfoForm">
                        <el-form-item prop="provName">
                            <el-input v-model="filters.provName" placeholder="请输入供应商名称" ></el-input>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="getHosProvInfoVoList(1)" >查询</el-button>
                            <el-button type="primary" v-on:click="resetProvName('provInfoForm')" >重置</el-button>
                        </el-button-group>
                    </el-form>
                </div>
                <el-table :data="provHosInfos"  ref="provListTable" @current-change="_queryProvGoodsList" :showHeader="false" style="width: 100%;"
                           :loading="loading" highlight-current-row  border fit height="outter" class="provgoods-tableheight">
                    <el-table-column prop="provName" label="供应商名称"  header-align="center"></el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                                   :current-page="page" :page-sizes="[20, 50, 100, 200]"
                                   :page-size="pageSize" layout="total, sizes, prev, next"
                                   :total="total" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
            <!--供应商产品信息-->
            <el-col :span="19" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
                <div>
                    <el-form :inline="true" :model="filters" ref="provGoodsForm" label-width="80px">
                        <el-form-item label="产品名称" prop="goodsName">
                            <el-input v-model="filters.goodsName" placeholder="请输入产品名称" ></el-input>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="queryProvGoodsList(1)" >查询</el-button>
                            <el-button type="primary" v-on:click="resetGoodsName('provGoodsForm')" >重置</el-button>
                        </el-button-group>
                    </el-form>
                </div>
                <el-table :data="provGoodsInfos"  highlight-current-row border fit class="provgoods-tableheight" >
                    <el-table-column prop="goodsName" header-align="center" label="产品名称" ></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="300"></el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="pHandleSizeChange" @current-change="pHandleCurrentChange"
                                   :current-page="pPage" :page-sizes="[20, 50, 100, 200]"
                                   :page-size="pPageSize" layout="total, sizes, prev, pager, next"
                                   :total="pTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                currentProv:{},
                hosId:'',
                filters: {
                    provName:'',
                    goodsName:''
                },
                loading:false,
                provHosInfos:[],
                total: 0,
                page: 1,
                pageSize: 20,

                provGoodsInfos:[],
                pTotal: 0,
                pPage: 1,
                pPageSize: 20,
            }
        },
        methods: {
            // 医院获取供应商列表
            getHosProvInfoVoList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provName: this.filters.provName
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                let o = this;
                o.axios.post('/spdHERPService/provManager/hosProvInfo/hosProvInfoVoList', param).then(res => {
                    o.loading = false;
                    o.provHosInfos = res.data.data.data;
                    o.total = res.data.data.total;
                    if(o.provHosInfos.length>0){
                        o.$nextTick(function () {
                            o.setCurrent(o.provHosInfos[0]);
                        });
                    }
                }, err => {
                    this.loading = false;
                });
            },
            setCurrent(row) {
                this.currentProv = row;
                this.$refs.provListTable.setCurrentRow(row);
            },

            handleSizeChange(val) {
                this.pageSize = val;
                this.getHosProvInfoVoList(this.page);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getHosProvInfoVoList(this.page);
            },

            /**
             * 重置供应商查询
             */
            resetProvName:function (formName) {
                this.$refs[formName].resetFields();
                this.getHosProvInfoVoList(1);
            },

            /***************************************************************************************************/

            // 点击供应商事件
            _queryProvGoodsList: function (row) {
                this.currentProv = row;
                this.queryProvGoodsList(1);
            },
            // 医院查询供应商产品列表
            queryProvGoodsList: function (pIndex) {
                this.pPage = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        goodsName: this.filters.goodsName
                    },
                    page: this.pPage,
                    rows: this.pPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provGoods/queryProvGoodsList', param).then(res => {
                    o.provGoodsInfos = res.data.data.data;
                    o.pTotal = res.data.data.total;
                }, err => {});
            },

            pHandleSizeChange(val) {
                this.pPageSize = val;
                this.queryProvGoodsList(this.pPage);
            },
            pHandleCurrentChange(val) {
                this.pPage = val;
                this.queryProvGoodsList(this.pPage);
            },

            /**
             * 重置供应商查询
             */
            resetGoodsName:function (formName) {
                this.$refs[formName].resetFields();
                this.queryProvGoodsList(1);
            }
        },
        mounted() {
            this.hosId = this.user.corpId;
            this.getHosProvInfoVoList(1);
        }
    }
</script>

<style>
    .provgoods-tableheight{
        height:  calc(100% - 75px);
    }
</style>