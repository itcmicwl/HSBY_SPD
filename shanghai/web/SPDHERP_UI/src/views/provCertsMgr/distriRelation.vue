<template>
    <div  style="height: 100%;">
        <el-row height="auto" style="height: 100%;">
            <!-- 集配商管理 -->
            <el-col :span="6" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
                <div style="padding-bottom: 4px;">
                    <el-form :inline="true" :model="filters" ref="distriRelationForm" label-width="80px">
                        <el-form-item label="" prop="cname">
                            <el-input v-model="filters.cname" placeholder="请输入集配商名称" ></el-input>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="queryCollectorList(1)" >查询</el-button>
                            <el-button type="primary" v-on:click="resetCname('distriRelationForm')" >重置</el-button>
                            <el-button type="success" @click="handleImport" >批量增加</el-button>
                            <el-button type="danger" @click="mulDeleteCollectorList()" >批量删除</el-button>
                        </el-button-group>
                    </el-form>
                </div>
                <el-table :data="distriRelationList" @current-change="_queryPartnerList" highlight-current-row
                          @selection-change="handleCollectorSelectionChange"
                          ref="distriRelationTable" style="width: 100%;" border fit class="distriRelation-tableHeight">
                    <!--<el-table-column type="index" label="序号" align="center"></el-table-column>-->
                    <el-table-column type="selection" align="center" width="55"> </el-table-column>
                    <el-table-column label="集配商信息" min-width="250" header-align="center">
                        <template slot-scope="scope">
                            <div slot="reference">
                                <span>{{ scope.row.cname}}</span><br/>
                                <span class="cnameLxrClass">联系人：{{scope.row.provLxr}}</span>&nbsp;
                                <span class="cnameLxrClass">{{scope.row.provLxrTelphonenum}}</span>
                            </div>
                        </template>
                    </el-table-column>

                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                                   :current-page="page" :page-sizes="[20, 50, 100, 200]"
                                   :page-size="pageSize" layout="total, sizes, prev, pager, next"
                                   :total="total" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>

            <!--合作商管理-->
            <el-col :span="8" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%; " >
                <div style="padding-bottom: 4px;">
                    <el-form :inline="true" :model="filters" ref="partnerForm" label-width="80px">
                        <el-form-item label="" prop="pname">
                            <el-input v-model="filters.pname" placeholder="请输入合作商名称" ></el-input>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="queryPartnerList(1)" >查询</el-button>
                            <el-button type="primary" v-on:click="resetPname('partnerForm')" >重置</el-button>
                            <el-button type="success" @click="handleImportPartner()" >批量增加</el-button>
                            <el-button type="danger" @click="mulDeletePartnerList()" >批量删除</el-button>
                        </el-button-group>
                    </el-form>
                </div>
                <el-table :data="partnerList" @row-click="clickPartner" highlight-current-row
                          @selection-change="handlePartnerSelectionChange"
                          ref="partnerTable" style="width: 100%;" border fit height="outter" class="distriRelation-tableHeight">
                    <!--<el-table-column type="index" label="序号" align="center"></el-table-column>-->
                    <el-table-column type="selection" align="center" width="55"> </el-table-column>
                    <el-table-column label="合作商信息" header-align="center">
                        <template slot-scope="scope">
                            <div slot="reference">
                                <span>{{ scope.row.cname}}</span><br/>
                                <span class="cnameLxrClass">联系人：{{scope.row.provLxr}}</span>&nbsp;
                                <span class="cnameLxrClass">{{scope.row.provLxrTelphonenum}}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="是否启用二级供应商" align="center" width="130">
                        <template slot-scope="scope">
                            <!--<el-select v-model="scope.row.kind"   @change="test"  placeholder="请选择">-->
                                <!--<el-option-->
                                        <!--v-for="item in psgxlx"-->
                                        <!--:key="item.val"-->
                                        <!--:label="item.ename"-->
                                        <!--:value="item.val">-->
                                <!--</el-option>-->
                            <!--</el-select>-->
                            <el-select v-model="scope.row.isUseSubprov"   @change="test"  placeholder="请选择">
                                <!--<el-option-->
                                        <!--v-for="item in sf"-->
                                        <!--:key="item.val"-->
                                        <!--:label="item.ename"-->
                                        <!--:value="item.val">-->
                                <!--</el-option>-->
                                <el-option label="是" value="1">是</el-option>
                                <el-option label="否" value="0">否</el-option>
                            </el-select>
                        </template>
                    </el-table-column>
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

            <!--集配商配送商产品管理-->
            <el-col :span="10" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%; " >
                <div style="padding-bottom: 4px;">
                    <el-form :inline="true" :model="filters" ref="goodsFilterForm" label-width="80px">
                        <el-form-item>
                            <el-input v-model="filters.goodsName" placeholder="请输入产品名称" ></el-input>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="queryGoodsList(1)" >查询</el-button>
                            <el-button type="primary" v-on:click="resetGoodsFilterForm('goodsFilterForm')" >重置</el-button>
                            <el-button type="success" @click="provProvGoodsSave()" >保存</el-button>
                        </el-button-group>
                    </el-form>
                </div>
                <el-table :data="goods" highlight-current-row  @selection-change="goodsSelectionChange" ref="goodsTable" style="width: 100%;" border fit height="outter" class="distriRelation-tableHeight">
                    <el-table-column type="selection" align="center" width="55"> </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" label="产品名称" width="150" fixed></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="180"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="price" align="center" label="单价" width="80"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="300"></el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="goodsHandleSizeChange" @current-change="goodsHandleCurrentChange"
                                   :current-page="goodsPage" :page-sizes="[20, 50, 100, 200]"
                                   :page-size="goodsPageSize" layout="total, sizes, prev, pager, next"
                                   :total="goodsTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
        </el-row>

        <el-dialog title="批量导入集配商" close="onClose" :visible.sync="relationImportVisible" :close-on-click-modal="false" >
            <el-form :inline="true" :model="filters" label-width="80px" ref="provNameForm">
                <el-form-item label="供应商名称" prop="provName">
                    <el-input v-model="filters.provName" placeholder="输入供应商名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="getHosProvInfoVoList(1)" >查询</el-button>
                <el-button type="primary" v-on:click="resetProvName('provNameForm')" >重置</el-button>
            </el-form>
            <template>
                <el-table ref="multipleProvListTable" :data="hosProvList"  border tooltip-effect="dark" row-key='id'
                          style="width: 100%" @selection-change="handleProvSelectionChange">
                    <el-table-column type="selection" align="center" width="55"> </el-table-column>
                    <el-table-column type="index" label="序号" align="center"></el-table-column>
                    <el-table-column prop="provName" header-align="center" label="供应商名称"></el-table-column>
                    <el-table-column prop="provLxr" label="联系人" header-align="center"></el-table-column>
                    <el-table-column prop="provLxrTelphonenum" label="联系电话" header-align="center"></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                    <el-pagination @size-change="handleProvSizeChange" @current-change="handleProvCurrentChange"
                                   :current-page="provPage" :page-sizes="[10, 20, 50, 100]"
                                   :page-size="provPageSize" layout="total, sizes, prev, pager, next"
                                   :total="provTotal" style="float:right;" small>
                    </el-pagination>
                    <el-button @click="clearProvSelection()" >取消选择</el-button>
                    <el-button @click="provImprotSubmit()" type="success" >确定</el-button>
                    <el-button @click.native="relationImportVisible = false" type="primary" >返回</el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                baseUrl:'/spdHERPService/provMgr/distriRelation',
                xlqFlag:true,
                collectorOrPartner:'',
                filters: {
                    cname:'',
                    provName:'',
                    pname:'',
                    goodsName:''    // 产品名称
                },
                total: 0,
                page: 1,
                pageSize: 20,

                provTotal: 0,
                provPage: 1,
                provPageSize: 20,
                hosProvList:[],

                multipleProvSelection:[],
                mulCollectorSelection:[],
                // 默认选中产品
                mulGoodsSelection:[],
                // 产品信息
                goods:[],
                goodsTotal:0,
                goodsPage:1,
                goodsPageSize:20,

                relationImportVisible:false,

                currentCollector:{},

                distriRelationList: [],


                pTotal: 0,
                pPage: 1,
                pPageSize: 20,

                mulPartnerSelection:[],

                partnerList: [],

                hosId:'',
                currentPartner:{},
                psgxlx:[],
                sf:[],
            }
        },
        methods: {
            // 查询
            queryGoodsList:function(pIndex){
                this.mulGoodsSelection = [];
                if(this.currentPartner.provId == null || this.currentPartner.provId == ''){
                    return;
                }
                this.goodsPage = pIndex;
                 var param = {
                    orderBy: '',
                    queryObject: {
                      //  provId:this.currentPartner.provId,
                        provId:  this.currentCollector.provId,
                         goodsName:this.filters.goodsName
                    },
                    page: this.goodsPage,
                    rows: this.goodsPageSize
                };
                this.axios.post(this.baseUrl + '/queryGoodsList', param).then(res => {
                    this.goods = res.data.data.data;
                    this.goodsTotal = res.data.data.total;
                    var con = {
                        hosId:this.hosId,
                        collectorId:this.currentCollector.provId,
                        provId:this.currentPartner.provId,
                    };
                    this.axios.post(this.baseUrl + '/getProvProvGoodsId', con).then(res => {
                        if(res.data.data){
                            this.mulGoodsSelection = res.data.data;
                            if(this.mulGoodsSelection.length > 0){
                                var rows = [];
                                for(var key in this.mulGoodsSelection){
                                    for(var index in this.goods){
                                        if(this.goods[index].provGoodsId == this.mulGoodsSelection[key].provGoodsId){
                                            rows.push(this.goods[index]);
                                        }
                                    }
                                }
                                rows.forEach(row => {
                                    this.$refs.goodsTable.toggleRowSelection(row);
                                });
                            }
                        }
                    });
                }, err => {
                });
            },
            // 保存
            provProvGoodsSave:function(){
                if(this.mulGoodsSelection.length <= 0){
                    this.$message.error( "至少选择一条数据！");
                    return;
                }
                var ids = [];
                for(var key in this.mulGoodsSelection){
                    ids.push(this.mulGoodsSelection[key].provGoodsId);
                }
                var param = {
                    hosId:this.hosId,
                    collectorId:this.currentCollector.provId,
                    provId:this.currentPartner.provId,
                    provGoodsIds:ids
                };
                this.axios.post(this.baseUrl + '/provProvGoodsSave', param).then(res => {
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.queryGoodsList(1);
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            // 重置
            resetGoodsFilterForm:function(formName){
                this.$refs[formName].resetFields();
                this.filters.goodsName = '';
                this.queryGoodsList(1);
            },
            goodsHandleSizeChange(val){
                this.goodsPageSize = val;
                this.queryGoodsList(this.goodsPage);
            },
            goodsHandleCurrentChange(val){
                this.goodsPage = val;
                this.queryGoodsList(this.goodsPage);
            },
            goodsSelectionChange(val){
                this.mulGoodsSelection = val;
            },
            // 批量导入集配商
            handleImport:function () {
                this.relationImportVisible=true;
                this.multipleProvSelection = [];
                this.collectorOrPartner = 'collector';
                this.getHosProvInfoVoList(1);
            },
            setCurrent(row) {
                this.$refs.distriRelationTable.setCurrentRow(row);
            },
            /**
             * 查询集配商列表信息
             */
            queryCollectorList:function (page) {
                let o = this;
                o.page = page;
                function cb(templateGoods){
                    o.distriRelationList = templateGoods;
                    if(o.distriRelationList.length>0){
                        o.$nextTick(function () {
                            o.setCurrent(o.distriRelationList[0]);
                        });
                    }
                }
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:o.hosId,
                        cname:o.filters.cname
                    },
                    page: o.page,
                    rows: o.pageSize
                };
                o.axios.post('/spdHERPService/provMgr/distriRelation/queryCollectorList', param).then(res => {
                    var distriRelationList = res.data.data.data;
                    cb(distriRelationList);
                    o.total = res.data.data.total;
                }, err => {
                });
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryCollectorList(this.page);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.queryCollectorList(this.page);
            },
            /**
             * 重置集配商查询
             */
            resetCname:function (formName) {
                this.$refs[formName].resetFields();
                this.queryCollectorList(1);
            },
            /**
             * 查询医院供应商商列表信息
             */
            getHosProvInfoVoList:function (page) {
                let o = this;
                o.provPage = page;
                function cb(hosProvList){
                    o.hosProvList = hosProvList;
                }
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:o.hosId,
                        provName:o.filters.provName,
                        collectorOrPartner:o.collectorOrPartner
                    },
                    page: o.provPage,
                    rows: o.provPageSize
                };
                o.axios.post('/spdHERPService/provManager/hosProvInfo/hosProvInfoVoList', param).then(res => {
                    var hosProvList = res.data.data.data;
                    cb(hosProvList);
                    o.provTotal = res.data.data.total;
                }, err => {
                });
            },
            handleProvSizeChange(val) {
                this.provPageSize = val;
                this.getHosProvInfoVoList(this.provPage);
            },
            handleProvCurrentChange(val) {
                this.provPage = val;
                this.getHosProvInfoVoList(this.provPage);
            },
            /**
             * 重置供应商查询
             */
            resetProvName:function (formName) {
                this.$refs[formName].resetFields();
                this.clearProvSelection();
                this.getHosProvInfoVoList(1);
            },
            handleProvSelectionChange(val) {
                this.multipleProvSelection = val;
            },
            handleCollectorSelectionChange(val) {
                this.mulCollectorSelection = val;
            },
            // 取消选择供应商
            clearProvSelection() {
                this.$refs.multipleProvListTable.clearSelection();
            },

            // 批量导入供应商
            provImprotSubmit:function(){
               if(this.collectorOrPartner === 'collector'){
                   this.collectorImprotSubmit();
               }else {
                   this.partnerImprotSubmit();
               }
            },
            // 批量导入集配商
            collectorImprotSubmit:function(){
                if(this.multipleProvSelection.length<=0){
                    this.$message.error( "至少选择一条数据！");
                    return;
                }
                this.axios.post('/spdHERPService/provMgr/distriRelation/importCollectorList', this.multipleProvSelection).then(res => {
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.relationImportVisible =false;
                        this.clearProvSelection();
                        this.queryCollectorList(1);
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            // 批量移除集配商
            mulDeleteCollectorList(){
                let o = this;
                if(o.mulCollectorSelection.length<=0){
                    o.$message.error( "至少选择一条数据！");
                    return;
                }
                o.$confirm('确认删除选中记录吗?', '提示', {
                }).then(() => {
                    o.axios.post("/spdHERPService/provMgr/distriRelation/mulDeleteCollectorList", this.mulCollectorSelection).then(res => {
                        o.loading = false;
                        if (res.data.code == "0") {
                            o.$message({ message: '操作成功！', type: 'success' });
                        } else {
                            o.$message.error(res.data.msg);
                        }
                        o.queryCollectorList(o.page);
                    }, err => {
                    });
                }).catch(() => {});
            },
            // 集配商列表点击事件
            _queryPartnerList (row, event, column) {
                if (this.currentCollector.cid === row.cid) {// 如果选中的是同一行，则返回
                    return;
                }
                this.currentCollector = row;
                this.queryPartnerList(1);
            },

            /*********************************************************************************************************/
            // 批量导入集配商
            handleImportPartner:function () {
                this.relationImportVisible=true;
                this.multipleProvSelection = [];
                this.collectorOrPartner = 'partner';
                this.getHosProvInfoVoList(1);
            },
            // 集配商列表点击事件，查询合作商
            queryPartnerList:function (page) {
                this.goods = [];
                this.partnerList = [];
                let o = this;
                o.pPage = page;
                function cb(templateGoods){
                    o.partnerList = templateGoods;
                }
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:o.currentCollector.hosId,
                        collectorId:o.currentCollector.provId,
                        cname:o.filters.pname
                    },
                    page: o.pPage,
                    rows: o.pPageSize
                };
                o.axios.post('/spdHERPService/provMgr/distriRelation/queryPartnerList', param).then(res => {
                    var distriRelationList = res.data.data.data;
                    cb(distriRelationList);
                    o.pTotal = res.data.data.total;
                }, err => {
                });
            },
            pHandleSizeChange(val) {
                this.pPageSize = val;
                this.queryPartnerList(this.pPage);
            },
            pHandleCurrentChange(val) {
                this.pPage = val;
                this.queryPartnerList(this.pPage);
            },
            /**
             * 重置合作商查询
             */
            resetPname:function (formName) {
                this.$refs[formName].resetFields();
                this.queryPartnerList(1);
            },
            // 批量导入合作商提交
            partnerImprotSubmit:function(){
                if(this.multipleProvSelection.length<=0){
                    this.$message.error( "至少选择一条数据！");
                    return;
                }
                var param = {
                    provHosInfoVos:this.multipleProvSelection,
                    collectorId:this.currentCollector.provId
                }
                this.axios.post('/spdHERPService/provMgr/distriRelation/importPartnerList', param).then(res => {
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.relationImportVisible =false;
                        this.clearProvSelection();
                        this.queryPartnerList(1);
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            handlePartnerSelectionChange(val) {
                this.mulPartnerSelection = val;
            },
            // 批量删除合作商
            mulDeletePartnerList(){
                let o = this;
                if(o.mulPartnerSelection.length<=0){
                    o.$message.error( "至少选择一条数据！");
                    return;
                }
                o.$confirm('确认删除选中记录吗?', '提示', {
                }).then(() => {
                    o.axios.post("/spdHERPService/provMgr/distriRelation/mulDeletePartnerList", this.mulPartnerSelection).then(res => {
                        o.loading = false;
                        if (res.data.code == "0") {
                            o.$message({ message: '操作成功！', type: 'success' });
                        } else {
                            o.$message.error(res.data.msg);
                        }
                        o.queryPartnerList(o.page);
                    }, err => {
                    });
                }).catch(() => {});
            },
            formateKtest2ind:function (row, column, cellValue) {
                return this.psgxlx.find(item => item.val == cellValue).ename;
            },
            getPSGXLX: function () {
                this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'PSGXLX'}).then(res => {
                    if (res.data.code == "0") {
                        this.psgxlx = res.data.data;
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            getSF: function () {
                this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'SF'}).then(res => {
                    if (res.data.code == "0") {
                        this.sf = res.data.data;
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            test:function (d1,d2,d3) {
                this.currentPartner.kind = d1;
//                this.updatePartnerKind();
                this.updateSubprov();
            },
            clickPartner:function (d1,d2,d3) {
                this.currentPartner = d1;
                this.queryGoodsList(1);
            },
            updatePartnerKind:function () {
                let o = this;
                o.$confirm('确认更改配送关系类型吗?', '提示', {
                }).then(() => {
                    o.axios.post("/spdHERPService/provMgr/distriRelation/updatePartnerKind", this.currentPartner).then(res => {
                        if (res.data.code == "0") {
                            o.$message({ message: '操作成功！', type: 'success' });
                        } else {
                            o.$message.error(res.data.msg);
                        }
                        o.queryPartnerList(o.page);
                    }, err => {
                    });
                }).catch(() => {});
            },
            updateSubprov:function () {
                let o = this;
                o.$confirm('确认启用/不启用二级供应商?', '提示', {
                }).then(() => {
                    o.axios.post("/spdHERPService/provMgr/distriRelation/updateSubprov", this.currentPartner).then(res => {
                        if (res.data.code == "0") {
                            o.$message({ message: '操作成功！', type: 'success' });
                        } else {
                            o.$message.error(res.data.msg);
                        }
                        o.queryPartnerList(o.page);
                    }, err => {
                    });
                }).catch(() => {});
            }
        },
        mounted() {
            this.hosId = this.user.corpId;
//            this.getPSGXLX();
            this.getSF();
            this.queryCollectorList(1);
        }
    }
</script>

<style>
    .fullheight {
        height: calc(100% - 75px);
    }
    .distriRelation-tableHeight{
        height:  calc(100% - 75px);
    }
    .cnameLxrClass{
        color: gray;
        font-style: oblique;
    }
</style>