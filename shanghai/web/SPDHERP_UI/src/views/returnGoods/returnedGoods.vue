<template>
    <div id="returnGoodsDiv">
        <!--工具条-->
        <el-col :span="24" style="height: 100%">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form :inline="true" :model="filters" ref="provInfoForm">
                    <el-form-item label="供应商名称：" prop="provName">
                        <el-input v-model="filters.provName" placeholder="供应商名称" ></el-input>
                    </el-form-item>
                    <el-form-item label="产品名称：" prop="goodsName">
                        <el-input v-model="filters.goodsName" placeholder="供应商名称" ></el-input>
                    </el-form-item>

                    <el-button-group>
                        <el-button type="primary" v-on:click="getReturnGoodsList(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetProvInfo('provInfoForm')" >重置</el-button>
                        <el-button type="success" @click="addReturnGoods()" >新增</el-button>
                    </el-button-group>
                </el-form>
            </el-col>
            <!--列表-->
            <template>
                <el-table :data="returnGoods" highlight-current-row v-loading="loading" style="width: 100%;" border fit height="outter" class="returnGoods-tableheight">
                    <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
                    <el-table-column prop="provName" label="供应商名称"  header-align="center"></el-table-column>
                    <el-table-column prop="goodsName" label="产品名称" header-align="center"></el-table-column>
                    <el-table-column prop="goodsGg" label="规格" align="center"></el-table-column>
                    <el-table-column prop="unit" label="单位" align="center"></el-table-column>
                    <el-table-column prop="made" label="产地" align="center"></el-table-column>
                    <el-table-column prop="batchCode" label="批号" align="center"></el-table-column>
                    <el-table-column prop="sterilizationDate" label="灭菌日期" :formatter="formateSterilizationDate" align="center"></el-table-column>
                    <el-table-column prop="sterilizationCode" label="灭菌批号" align="center"></el-table-column>
                    <el-table-column prop="sterilizationEndDate" label="灭菌效期" :formatter="formateSterilizationEndDate" align="center"></el-table-column>
                    <el-table-column prop="expdtEndDate" label="有效期至" :formatter="formateExpdtEndDate" align="center"></el-table-column>
                    <el-table-column prop="lastUpdateDatetime" label="最后更新时间" :formatter="formateLastUpdateDatetime" align="center" sortable></el-table-column>
                    <!--<el-table-column label="操作" width="200" align="center">
                        <template slot-scope="scope">
                            <el-button-group>
                                <el-button size="mini" @click="deleteSubmit(scope.row)"  type="warning" icon="warning" >解除关系</el-button>
                            </el-button-group>
                        </template>
                    </el-table-column>-->
                </el-table>
                <!-- 工具条 -->
                <el-col :span="24" class="toolbar">
                    <el-pagination v-if="total>0" small @size-change="handlePSizeChange" style="padding-right: 20px;text-align: right;"
                                   @current-change="handleCurrentChange" :current-page="page"
                                   layout="total, sizes, prev, pager, next" :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" :total="total">
                    </el-pagination>
                </el-col>
            </template>
            <!-- 编辑界面 -->
            <el-dialog title="添加退货登记单"   close="onClose"  :visible.sync="editFormVisible" :close-on-click-modal="false" >
                <el-form :model="exchangeGoods" label-width="120px" ref="editForm" class="el-form-item-nomsg">
                    <el-row>
                            <el-form-item label="集配商名称：" prop="provId">
                                <el-select v-model="exchangeGoods.provId" filterable placeholder="请选择" :clearable="true" style="width: 70%;" >
                                    <el-option
                                            v-for="item in collectors"
                                            :key="item.provId"
                                            :label="item.cname"
                                            :value="item.provId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="产品名称：" prop="goodsName">
                            <el-input v-model="exchangeGoods.goodsName" style="width: 70%;"  placeholder="请输入产品名称"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="规格：" prop="goodsGg">
                            <el-input v-model="exchangeGoods.goodsGg" style="width: 70%;"  placeholder="请输入规格"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="单位：" prop="unit">
                            <el-input v-model="exchangeGoods.unit" style="width: 70%;"   placeholder="请输入单位"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="产地：" prop="made">
                            <el-input v-model="exchangeGoods.made" style="width: 70%;"  placeholder="请输入产地"></el-input>
                        </el-form-item>
                    </el-row>

                    <el-row>
                        <el-form-item label="批号：" prop="batchCode">
                            <el-input v-model="exchangeGoods.batchCode" style="width: 70%;"  placeholder="请输入批号"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="灭菌日期：" prop="sterilizationDate">
                            <el-date-picker v-model="exchangeGoods.sterilizationDate" size = "mini" type="date" placeholder="灭菌日期"></el-date-picker>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="灭菌效期：" prop="sterilizationEndDate">
                            <el-date-picker v-model="exchangeGoods.sterilizationEndDate" size = "mini" type="date" placeholder="灭菌效期"></el-date-picker>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="灭菌批号：" prop="sterilizationCode">
                            <el-input v-model="exchangeGoods.sterilizationCode" style="width: 70%;"  placeholder="请输入灭菌批号"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="有效期至：" prop="expdtEndDate">
                            <el-date-picker v-model="exchangeGoods.expdtEndDate" size = "mini" type="date" placeholder="有效期至"></el-date-picker>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="退货数量：" prop="excQty">
                            <el-input v-model="exchangeGoods.excQty"  style="width: 70%;" placeholder="请输入灭菌批号"></el-input>
                        </el-form-item>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer returnGoods-button-align">
                    <el-button @click.native="editFormVisible = false" >取消</el-button>
                    <el-button type="primary" @click.native="editSubmit" :loading="editLoading" >提交</el-button>
                </div>
            </el-dialog>
        </el-col>
    </div>
</template>
<script>
    import { fileServer } from '../../common/js/constance';
    export default {
        data() {
            return {
                exchangeGoods:{
                    provId:'',
                    excQty:1,
                    expdtEndDate:'',
                    sterilizationEndDate:'',
                    sterilizationDate:'',
                    provName:''
                },
                returnGoods:[],
                hosId:'',
                filters: {
                    provName: '',
                    goodsName: ''
                },
                total: 0,
                page: 1,
                pageSize:20,
                loading: false,
                collectors:[],
                editFormVisible: false,
                editLoading: false
            }
        },
        methods: {
            formateSterilizationDate:function (row) {
                return row.sterilizationDate == null ? '': row.sterilizationDate.substr(0,10);
            },
            formateSterilizationEndDate:function (row) {
                return row.sterilizationEndDate == null ? '': row.sterilizationEndDate.substr(0,10);
            },
            formateExpdtEndDate:function (row) {
                return row.expdtEndDate == null ? '': row.expdtEndDate.substr(0,10);
            },
            formateLastUpdateDatetime:function (row) {
                return row.lastUpdateDatetime == null ? '': row.lastUpdateDatetime.substr(0,10);
            },

            // 获取医院退货登记单列表
            getReturnGoodsList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        provName:this.filters.provName,
                        goodsName:this.filters.goodsName
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/returnGoods/returnedGoodsList/listByPage', param).then(res => {
                    this.loading = false;
                    this.returnGoods = res.data.data.data;
                    this.total = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getReturnGoodsList(this.page);
            },
            handlePSizeChange(val) {
                this.pageSize = val;
                this.getReturnGoodsList(this.page);
            },
            /**
             * 重置供应商查询
             */
            resetProvInfo:function (formName) {
                this.$refs[formName].resetFields();
                this.getReturnGoodsList(1);
            },

            // 弹出 添加退货登记单对话框
            addReturnGoods() {
                this.exchangeGoods = {
                    provId:'',
                    excQty:1,
                    expdtEndDate:'',
                    sterilizationEndDate:'',
                    sterilizationDate:'',
                    provName:''
                };
                this.getProvInfoVoList();//　查询医院所有集配商列表
                this.editFormVisible = true;
            },

            // 添加退货登记单
            editSubmit: function () {
                var obj = this.collectors.find(item=>item.provId == this.exchangeGoods.provId);
                this.exchangeGoods.provName = obj.cname;
                this.axios.post("/spdHERPService/returnGoods/returnedGoodsList/add", this.exchangeGoods).then(res => {
                    this.editLoading = false;
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.editFormVisible = false;
                        this.getReturnGoodsList(this.page);
                    } else {
                        this.$message.error('操作失败！');
                    }
                }, err => {
                    this.editLoading = false;
                });
            },

            // 获取供应商下拉列表
            getProvInfoVoList: function () {
                var param = {queryObject: { hosId:this.hosId }};
                this.axios.post(`/spdHERPService/provMgr/distriRelation/queryCollectorList`, param).then(res => {
                    this.collectors = res.data.data.data;
                }, err => {
                });
            }
        },
        mounted() {
            this.hosName = this.user.corpName;
            this.hosId = this.user.corpId;
            this.imgBasePath = fileServer.imgBasePath;
            this.getReturnGoodsList(1);
        }
    };

</script>
<style>
    #returnGoodsDiv .returnGoods-tableheight {
        height: calc(100% - 85px);
    }
    #returnGoodsDiv .returnGoods-button-align{
        text-align: center;
    }
    #returnGoodsDiv{
        height: 100%;
    }
    #returnGoodsDiv .el-dialog--small {
        width: 658px;
    }
</style>
