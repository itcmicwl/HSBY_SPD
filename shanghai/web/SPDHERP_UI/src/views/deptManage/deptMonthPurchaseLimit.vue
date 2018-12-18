<!--科室月请购产品数量上限-->
<template>
    <div  style="height: 100%;" id="deptMonthPurchase">
        <el-col :span="5" style="height: calc(99% - 31px);">
                <div style="padding-bottom: 7px">
                    <el-input 
                        v-model="orgName" 
                        placeholder="请输入科室名称" 
                        icon="search" 
                        >
                    </el-input>
                </div>
                <el-tree
                    :data="orgInfo" node-key="id"
                    :default-expand-all="true"
                    :expand-on-click-node="false"
                    :default-expanded-keys="expandIds" 
                    :props="orgInfoProps" 
                    :highlight-current="true" 
                    @node-click="handleNodeClick"
                    :filter-node-method="filterNode"
                    ref = "orgInfoTree">
                </el-tree>
        </el-col>
        
        <el-col :span="19" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <!--工具条-->
            <div style="padding-bottom: 1px;">
                <el-form :inline="true" :model="filters" label-width="80px" ref="goodsName">
                    <el-form-item label="产品名称" prop="goodsName">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称" ></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="getGoodsList(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetGoodsName('goodsName')" >重置</el-button>
                    </el-button-group>
                    <el-button type="success" :disabled="isDisable" v-on:click="saveDeptMonthPurchaseLimit" :loading="saveLoading" >保存</el-button>
                </el-form>
            </div>
            <!--科室产品列表-->
            <template>
                <el-table ref="goodsTable" :data="goods"  row-key='id'
                            @current-change="handleDeptGoodCurrentChange"
                            highlight-current-row v-loading="loading"  border fit 
                            height="outter" class="deptgoods-tableheight"  >
                    <el-table-column type="index" align="center" :reserve-selection="true" width="50" label="序号"></el-table-column>
                    <el-table-column prop="goodsName" header-align="center" min-width="250" label="产品名称" ></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="180"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="250"></el-table-column>
                    <el-table-column label="操作"  align="center"  width="100">
                        <template slot-scope="scope">
                            <el-button type="primary" size="mini" @click="goodQuery(scope.row)" icon="search" >详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--分业工具栏-->
                <el-col :span="24" class="toolbar pageBar">
                    <el-pagination @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange" :current-page="page"
                                    :page-sizes="[20, 100, 200, 400]" :page-size="pageSize" :total="total"
                                    layout="total, sizes, prev, pager, next" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </template>
            <!--产品月请购数量表单-->
            <template>
                <el-form :model="hosDeptApplyUpperVo"  label-width="100px"
                  ref="editForm"  class="el-form-item-nomsg deptMonthPurchaseForm">
                    <el-row>
                        <el-col :span="24" style="padding-left:10px;padding-top:5px;padding-bottom:12px;">
                            <span font-size="15">请设置科室产品{{hosDeptApplyUpperVo.goodsName}}月请购数量上限</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="一月" prop="m1Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m1Qty" size="mini" :min="0" class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="二月" prop="m2Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m2Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>  
                        <el-col :span="6">
                            <el-form-item label="三月" prop="m3Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m3Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>    
                        <el-col :span="6">
                            <el-form-item label="四月" prop="m4Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m4Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>                               
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="五月" prop="m5Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m5Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="六月" prop="m6Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m6Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>  
                        <el-col :span="6">
                            <el-form-item label="七月" prop="m7Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m7Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>    
                        <el-col :span="6">
                            <el-form-item label="八月" prop="m8Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m8Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>                               
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="九月" prop="m9Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m9Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="十月" prop="m10Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m10Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>  
                        <el-col :span="6">
                            <el-form-item label="十一月" prop="m11Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m11Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>    
                        <el-col :span="6">
                            <el-form-item label="十二月" prop="m12Qty"  >
                                <el-input-number placeholder="请输入数量" :disabled="isDisable" v-model="hosDeptApplyUpperVo.m12Qty" size="mini" :min="0"  class="col-input-num">
                                </el-input-number>  
                            </el-form-item>
                        </el-col>                              
                    </el-row>
                </el-form>
                
            </template>
        </el-col>

        <!--产品详情弹窗-->
        <el-dialog title="产品详情" close="onClose" :visible.sync="queryFormVisible" :close-on-click-modal="false" size="mini">
            <el-form :model="good"  label-width="95px"  class="el-form-item-nomsg deptGoods">
                <el-row>
                    <el-col :span="16">
                        <el-form-item label="产品名称："  prop="goodsName">
                            <el-input v-model="good.goodsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="科室名称：" prop="stocId">
                            <el-input v-model="good.deptName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="规格型号：" prop="goodsGg">
                            <el-input v-model="good.goodsGg" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="单位：" prop="goodsGg">
                            <el-input v-model="good.unit" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="库存上限：" prop="stocUpper">
                            <el-input v-model="good.stocUpper" disabled  placeholder="请输入库存上限"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="包装数量：" prop="goodsGg">
                            <el-input v-model="good.packetCode" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="产地：" prop="goodsGg">
                            <el-input v-model="good.made" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="库存下限：" prop="stocLower">
                            <el-input v-model="good.stocLower" disabled  placeholder="请输入库存下限"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="16">
                        <el-form-item label="生产厂商：" prop="mfrsName">
                            <el-input v-model="good.mfrsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="采购模式：" prop="purMode">
                            <el-select v-model="purMode" disabled filterable placeholder="请选择" :clearable="true" >
                                <el-option
                                        v-for="item in purModes"
                                        :key="item.val"
                                        :label="item.ename"
                                        :value="item.val">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row style="padding-left: 10px;">
                    <el-col :span="8">
                        <el-form-item label="是否定数包管理：" labelWidth="120px" prop="isPacket">
                            <el-radio-group v-model="isPacket" readonly >
                                <el-radio-button label="1" :disabled="isPacketYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isPacketNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="定数包是否混批：" labelWidth="120px" prop="isMix">
                            <el-radio-group v-model="isMix" >
                                <el-radio-button label="1" :disabled="isMixYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isMixNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8" style="margin-left: -3px;">
                        <el-form-item label="唯一码策略：" prop="isUnique">
                            <el-select v-model="isUnique" disabled style="width: 102%;" filterable placeholder="请选择" :clearable="true" >
                                <el-option
                                        v-for="item in wymglcl"
                                        :key="item.val"
                                        :label="item.ename"
                                        :value="item.val">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: right;">
                <el-button @click.native="queryFormVisible = false" type="primary">取消</el-button>
            </div>
        </el-dialog>
    </div>

</template>
<script>
    import { fileServer } from '../../common/js/constance';
    export default {
        data() {
            return {
                packetCodeDisabled:true,
                purModes:[],
                wymglcl:[],
                stocId:'',
                deptStocInfo:[],
                stocInfo:{},
                deptGoodsInfo:{},
                queryFormVisible:false,
                isPacket:'1',
                isUnique:'3',
                isMix:'1',
                isPacketYes:false,
                isPacketNo:false,
                isUniqueYes:false,
                isUniqueNo:false,
                isMixYes:false,
                isMixNo:false,
                purMode:'10',
                hosId:'',
                deptId:'',
                hosName:'',
                deptName:'',

                myPorps:{
                    value:'mfrsId',
                    label:'mfrsName'
                },
                filters: { 
                    goodsName: '' ,
                    orgName:''
                },
                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                goods: [],
                good: {mfrsId:''},

                //上限值设置
                hosDeptApplyUpperVoList:[],
                hosDeptApplyUpperVo:{},
                saveLoading:false,
                isDisable:true,

                // 树
                expandIds: [],
                orgInfo: [],
                orgInfoProps: {
                    children: 'children',
                    label: 'label',
                    id: 'id',
                    pid: 'pid'
                },
                // 快速查询
                orgName:''
            }
        },
        watch:{
            orgName(val) {
                this.$refs.orgInfoTree.filter(val);
            }
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
             // 查询
            searchOrgs: function () {
                this.filters.orgName = this.orgName;
                var p = this.filters.orgName;
                let param = { cname: p };
                this.loading = true;
                this.axios.post(`/platformService/sys/org/searchOrgs`, param).then(res => {
                    this.loading = false;
                    var ids = [];
                    for (var i = 0; i < res.data.data.length; i++) {
                        ids[i] = res.data.data[i].id;
                    }
                    this.expandIds = ids;
                });
                this.loading = false;
            },
            getOrgsByUnit: function (unitId) {
                var param = {};
                this.loading = true;
                this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res => {
                    this.orgInfo = res.data.data.children;
                    this.expandIds = [this.orgInfo[0].id];
                    this.loading = false;
                });
            },
            // 点击即查询
            handleNodeClick:function(item){
                this.deptId = item.id;
                this.getGoodsList(1);
            },
            
            //获取产品列表
            getGoodsList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        goodsName: this.filters.goodsName,
                        hosId:this.hosId,
                        deptId:this.deptId
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoList', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.goods = res.data.data.data;
                        this.total = res.data.data.total;
                        if(this.goods.length>0){
                            this.$nextTick(function () {
                                this.good = this.goods[0];
                                this.$refs.goodsTable.setCurrentRow(this.good);
                            });
                        }
                        
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },

            resetGoodsName:function (formName) {
                this.$refs[formName].resetFields();
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
            },

            /**
             * 获取科室库房信息
             */
            listDeptStocInfo:function () {
                this.stocInfo = {
                    hosId:this.hosId,
                    deptId:this.deptId
                };
                this.axios.post('/spdHERPService/deptMgr/stocInfo/listDeptStocInfo',this.stocInfo).then(res => {
                    this.deptStocInfo = res.data.data;
                });
            },
            /**选中行事件 */
            handleDeptGoodCurrentChange : function(currentRow){
                if(currentRow){
                    this.good = currentRow;
                    this.queryDeptMonthPurchaseLimit();
                }else{
                    this.isDisable = true;
                }
            },
            /***查询科室请购产品 每个月的请购上限值 */
            queryDeptMonthPurchaseLimit : function(){
                var param = {
                    queryObject: {
                        id:this.good.id,
                        hosId:this.good.hosId,
                        deptId:this.good.deptId,
                        hosGoodsId:this.good.goodsId
                    }
                };
                this.axios.post('/spdHERPService/deptMgr/hosDeptApplyUpper/queryHosDeptApplyUpperList',param).then(res => {
                    this.hosDeptApplyUpperVoList = res.data.data.data;
                    if(this.hosDeptApplyUpperVoList.length>0){
                        this.isDisable=false;
                        this.hosDeptApplyUpperVo = this.hosDeptApplyUpperVoList[0];
                    }
                });
            },
            saveDeptMonthPurchaseLimit : function(){
                if(this.hosDeptApplyUpperVo){
                    let hosDeptApplyUpper = {
                            id:this.hosDeptApplyUpperVo.hosDeptApplyUpperId,
                            deptId:this.hosDeptApplyUpperVo.deptId,
                            hosId:this.hosDeptApplyUpperVo.hosId,
                            hosGoodsId:this.hosDeptApplyUpperVo.goodsId,
                            m1Qty:this.hosDeptApplyUpperVo.m1Qty,
                            m2Qty:this.hosDeptApplyUpperVo.m2Qty,
                            m3Qty:this.hosDeptApplyUpperVo.m3Qty,
                            m4Qty:this.hosDeptApplyUpperVo.m4Qty,
                            m5Qty:this.hosDeptApplyUpperVo.m5Qty,
                            m6Qty:this.hosDeptApplyUpperVo.m6Qty,
                            m7Qty:this.hosDeptApplyUpperVo.m7Qty,
                            m8Qty:this.hosDeptApplyUpperVo.m8Qty,
                            m9Qty:this.hosDeptApplyUpperVo.m9Qty,
                            m10Qty:this.hosDeptApplyUpperVo.m10Qty,
                            m11Qty:this.hosDeptApplyUpperVo.m11Qty,
                            m12Qty:this.hosDeptApplyUpperVo.m12Qty,
                            version:this.hosDeptApplyUpperVo.hosDeptApplyUpperVersion
                    };
                    this.saveLoading = true;
                    this.axios.post('/spdHERPService/deptMgr/hosDeptApplyUpper/'+(hosDeptApplyUpper.id?'update':'add'), hosDeptApplyUpper).then(res => {
                        this.saveLoading = false;
                        if (res.data.code == "0") {
                            this.queryDeptMonthPurchaseLimit();
                            this.$message({ message: '操作成功！', type: 'success' });
                        } else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {
                    });
                }
            },
            /**分业处理事件 */
            handleCurrentChange : function(val) {
                this.page = val;
                this.getGoodsList(this.page);
            },
            handleSizeChange:function(val) {
                this.pageSize = val;
                this.getGoodsList(this.page);
            },
            

            /***查看详情 */
            goodQuery:function(item) {
                let good = Object.assign({}, item);
                this.good = good;
                this.stocId  = this.good.stocId;
                this.purMode = this.good.purMode;
                this.isPacket = this.good.isPacket;
                this.isPacketYes = this.isPacket == 1?false:true;
                this.isPacketNo = this.isPacket == 0?false:true;
                this.isUnique = this.good.isUnique;
                this.isUniqueYes = this.isUnique == 1?false:true;
                this.isUniqueNo = this.isUnique == 0?false:true;
                this.isMix = this.good.isMix;
                this.isMixYes = this.isMix == 1?false:true;
                this.isMixNo = this.isMix == 0?false:true;
                this.queryFormVisible = true;
            },

            getCGMS: function () {
                this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'CGMS'}).then(res => {
                    if (res.data.code == "0") {
                        this.purModes = res.data.data;
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            getWYMGLCL: function () {
                this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'WYMGLCL'}).then(res => {
                    if (res.data.code == "0") {
                        this.wymglcl = res.data.data;
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            }
        },
        mounted() {
            this.hosId = this.user.corpId;
            this.hosName = this.user.corpName;
            this.deptName = this.user.orgName;
            this.getOrgsByUnit();
            this.getCGMS();
            this.getWYMGLCL();
        }
    };

</script>
<style>
    #deptMonthPurchase .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #deptMonthPurchase .el-checkbox-group{
        vertical-align: middle;
    }
    #deptMonthPurchase .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 250px);
    }
    #deptMonthPurchase .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #deptMonthPurchase .deptGoods .el-row{
        height: 36px;
    }
    
    #deptMonthPurchase .deptMonthPurchaseForm{
        padding-bottom: 10px;
       /* padding-top: 20px;
        padding-right: 19px;*/
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-top:57px;
    }
    #deptMonthPurchase .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
    

</style>