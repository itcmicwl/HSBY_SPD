<template>
    <div id="stoc-shelf-goods-info" style="height: 100%;">
        <el-row height="auto" :gutter="1" style="height: 100%;">
            <!-- 供应商列表 -->
            <el-col :span="4" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
                <div style="padding-bottom: 3px;">
                    <el-form :inline="true" :model="filters" ref="provInfoForm" style="margin-bottom:-2px">
                        <el-form-item prop="stocName">
                            <el-input v-model="filters.stocName" style="height:30px;" placeholder="请输入库房名称" ></el-input>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="listStocInfo(1)" >查询</el-button>
                            <el-button type="primary" v-on:click="resetProvName('provInfoForm')" >重置</el-button>
                        </el-button-group>
                    </el-form>
                </div>
                <el-table :data="stocInfo" ref="stockListTable" @current-change="_queryStockShelfList" :showHeader="false" style="width: 100%;"
                          :loading="loading" highlight-current-row  border fit height="outter" class="provgoods-tableheight">
                    <el-table-column prop="stocName" label="库房名称"  header-align="center"></el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="sHandleSizeChange" @current-change="sHandleCurrentChange"
                                   :current-page="sPage" :page-sizes="[20, 50, 100, 200]"
                                   :page-size="sPageSize" layout="total, sizes, prev, next"
                                   :total="sTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
            <el-col :span="4" style="height: calc(99% - 66px);">
                <div id="shelfFilter" style="margin-bottom: 6px;">
                    <el-input placeholder="输入货位名称过滤" v-model="filterText" ></el-input>
                </div>
                <el-tree :data="stockShelvesTree"
                         node-key="id" ref="stockShelvesTree"
                         :default-expand-all="true"
                         :expand-on-click-node="false"
                         :props="stockShelvesProps"
                         :highlight-current="true"
                         :filter-node-method="filterNode"
                         @node-click="handleNodeClick">
                </el-tree>
            </el-col>
            <el-col :span="16" class="toolbar" style="padding-bottom: 0px;height: 98.7%">
                <el-form :inline="true" :model="filters" label-width="80px" ref="deptProductModalForm">
                    <el-form-item label="产品名称" prop="goodsName">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称" ></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="queryStockShelfGoodsList(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetGoodsName('deptProductModalForm')" >重置</el-button>
                        <el-button type="success" @click="handleImport" >批量导入</el-button>
                        <el-button type="danger" @click="mulDel()" >批量移除</el-button>
                    </el-button-group>
                </el-form>
                <!--列表-->
                <el-table ref="stockShelfGoodsTable" :data="stockShelfGoods"  border tooltip-effect="dark"
                          style="width: 100%" @selection-change="handleSelectionChange" class="stockShelfGoodsTable-tableheight" height="outer">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" label="产品名称" min-width="300px"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <!--<el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>-->
                    <!--<el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="280"></el-table-column>-->
                    <!--<el-table-column label="操作"  align="center" fixed="right" width="120">-->
                        <!--<template slot-scope="scope">-->
                            <!--<el-button-group>-->
                                <!--<el-button type="primary" size="mini" @click="goodQuery(scope.row)" icon="search" >查看</el-button>-->
                                <!--<el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete" >移除</el-button>-->
                            <!--</el-button-group>-->
                        <!--</template>-->
                    <!--</el-table-column>-->
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleSizeChange"
                                   @current-change="handleCurrentChange" :current-page="page"
                                   :page-sizes="[20, 50, 100, 200]" :page-size="pageSize" :total="total"
                                   layout="total, sizes, prev, pager, next" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>

        </el-row>
        <el-dialog title="批量导入产品" close="onClose" :visible.sync="goodImportVisible" :close-on-click-modal="false" size="deptGoodsDialog">
            <el-form :inline="true" :model="basGoodsFilters" label-width="80px" ref="deptGoodsName">
                <el-form-item label="商品名称" prop="goodsName">
                    <el-input v-model="basGoodsFilters.goodsName" placeholder="输入商品名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="listByPage(1)" >查询</el-button>
                <el-button type="primary" v-on:click="resetDeptGoodsName('deptGoodsName')" >重置</el-button>
            </el-form>
            <template>
                <el-table ref="multipleDeptGoodsTable" :data="basGoods"  border tooltip-effect="dark" row-key='id'
                          style="width: 100%" @selection-change="handleImportSelectionChange">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" label="产品名称" min-width="300px"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="280"></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                    <el-pagination @size-change="basHandleSizeChange" @current-change="basHandleCurrentChange"
                                   :current-page="basPage" :page-sizes="[10, 20, 50, 100]"
                                   :page-size="basPageSize" layout="total, sizes, prev, pager, next"
                                   :total="basTotal" style="float:right;" small>
                    </el-pagination>
                    <el-button @click="toggleSelection()" >取消选择</el-button>
                    <el-button @click="goodImprotSubmit()" type="success" >确定</el-button>
                    <!--<el-button @click.native="goodImportVisible = false" type="primary" >返回</el-button>-->
                    <el-button @click.native="cancelDig()" type="primary" >返回</el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>
<script>
    function buildTreenode(list,idName,pidName,labelName)
    {
        var map ={};
        for(let i = 0; i< list.length;i++)
        {

            map[list[i][idName]] = {id:list[i][idName],pid:list[i][pidName],label:list[i][labelName],tag:list[i]};
        }
        var maproot=[];
        function linknode(node)
        {
            if(!node.pid){
                maproot.push(node);
                return;
            }
            let pnode = map[node.pid];
            node.pnode = pnode;
            if(!pnode.children)
                pnode.children = [];
            pnode.children.push(node);
        }

        for(var id in map)
        {
            let item = map[id];
            linknode(item);
        }
        for(var id in map)
        {
            let item = map[id];
            if(item.children){
                item.isLeaf= false;
                item.checked = false;
            }
            else{
                item.isLeaf = true;
                item.checked = false;
                let pitem = map[item.pid];
                if(pitem){
                    pitem.isGroup = true;
                }
            }
        }

        return maproot;
    }
    export default {
        data() {
            return {
                filterText: '',
                filters: {goodsName:'',stocName:''},
                stocInfo:[],
                stockShelves:[],
                stockShelvesTree:[],


                stockShelfGoods:[],
                total: 0,
                page: 1,
                pageSize: 20,

                sTotal: 0,
                sPage: 1,
                sPageSize: 20,

                loading: false,
                hosId:'',

                templateGoods:[],
                currentStoc:{},

                stockShelvesProps: {
                    children: 'children',
                    label: 'label',
                    id: 'id',
                    pid: 'pid'
                },

                defaultProps: {
                    children: 'children',
                    label: 'stocName'
                },
                /***************************批量导入查询<begin>**************************/
                basTotal: 0,
                basPage: 1,
                basPageSize: 10,
                basGoods:[],
                basGoodsFilters:{goodsName:''},
                goodImportVisible:false,
                multipleImportSelection:[],
                currentStockShelf:{},
                /***************************批量导入查询<end>***************************/

                multipleSelection:[],
            }
        },
        watch: {
            filterText(val) {
                this.$refs.stockShelvesTree.filter(val);
            }
        },
        methods: {
            cancelDig(){
              this.goodImportVisible=false;
              this.basGoods=[];
              this.$refs.multipleDeptGoodsTable.clearSelection();
            },
            /******************************************批量导入查询<begin>***************************************/
            // 批量导入产品：(库房对应科室)产品==>货位产品
            handleImport:function () {
                this.goodImportVisible=true;
                this.multipleImportSelection = [];
                this.listByPage(1);
            },
            // 查询库房对应科室的产品信息列表
            listByPage:function (page) {
                let o = this;
                o.basPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:o.hosId,
                        stocId:o.currentStoc.id,
                        goodsName:o.basGoodsFilters.goodsName
                    },
                    page: o.basPage,
                    rows: o.basPageSize
                };
                o.axios.post('/spdHERPService/baseData/stocInfoMgr/stocShelfGoodsInfo/listByPage',param).then(res => {
                    o.basGoods = res.data.data.data;
                    o.basTotal = res.data.data.total;
                });
                this.basGoodsFilters.goodsName = '';
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryStockShelfGoodsList(this.page);
            },

            handleCurrentChange(val) {
                this.page = val;
                this.queryStockShelfGoodsList(this.page);
            },
            sHandleSizeChange(val) {
                this.sPageSize = val;
                this.listStocInfo(this.sPage);
            },

            sHandleCurrentChange(val) {
                this.sPage = val;
                this.listStocInfo(this.sPage);
            },
            resetDeptGoodsName:function (formName) {
                this.$refs[formName].resetFields();
                this.$refs.multipleDeptGoodsTable.clearSelection();
                this.listByPage(1);
            },
            basHandleCurrentChange(val) {
                this.basPage = val;
                this.listByPage(this.basPage);
            },
            basHandleSizeChange(val) {
                this.basPageSize = val;
                this.listByPage(this.basPage);
            },
            toggleSelection(rows) {
                this.$refs.multipleDeptGoodsTable.clearSelection();
            },
            handleImportSelectionChange(val) {
                this.multipleImportSelection = val;
            },
            // 批量导入产品提交
            goodImprotSubmit:function(){
                if(this.multipleImportSelection.length<=0){
                    this.$message.error("至少选择一条数据");
                    return;
                }
                var param = {
                    stockShelfVo: this.currentStockShelf,
                    stockShelfGoods: this.multipleImportSelection,
                };
                this.axios.post('/spdHERPService/baseData/stocInfoMgr/stocShelfGoodsInfo/importStockShelfGoods', param).then(res => {
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.goodImportVisible =false;
                        this.$refs.multipleDeptGoodsTable.clearSelection();
                        this.queryStockShelfGoodsList(1);
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {});
            },
            /******************************************批量导入查询<end>****************************************/
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 批量删除货位产品
            mulDel(){
                let o = this;
                if(o.multipleSelection.length<=0){
                    o.$message.error( "至少选择一条数据！");
                    return;
                }
                o.$confirm('确认删除选中记录吗?', '提示', {
                }).then(() => {
                    o.loading = true;
                    o.axios.post("/spdHERPService/baseData/stocInfoMgr/stocShelfGoodsInfo/deleteStockShelfGoods", o.multipleSelection).then(res => {
                        o.loading = false;
                        if (res.data.code == "0") {
                            o.loading = false;
                            o.$message({ message: '操作成功！', type: 'success' });
                        } else {
                            o.$message.error( res.data.msg);
                        }
                        o.queryStockShelfGoodsList(o.page);
                    }, err => {
                        o.loading = false;
                    });
                }).catch(() => {});
            },

            // 查询库房列表
            listStocInfo:function (page) {
                let o = this;
                o.sPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:o.hosId,
                        stocName:o.filters.stocName
                    },
                    page: o.sPage,
                    rows: o.sPageSize
                };
                o.axios.post('/spdHERPService/deptMgr/stocInfo/listStocInfo2',param).then(res => {
                    o.stocInfo = res.data.data.data;
                    o.sTotal = res.data.data.total;
                    if(o.stocInfo.length>0){
                        o.$nextTick(function () {
                            o.setCurrent(o.stocInfo[0]);
                        });
                    }
                });
            },
            setCurrent(row) {
//                this.currentStoc = row;
                this.$refs.stockListTable.setCurrentRow(row);
            },

            _queryStockShelfList(row){
                this.currentStoc = row;
                var stockShelf = {hosId:this.hosId,provId:this.currentStoc.id};
                this.queryStockShelfList(stockShelf);
            },

            // 查询区位、货位列表
            queryStockShelfList:function (stockShelf) {
                let o = this;
                function cb(stockShelves){
                    o.stockShelvesTree = buildTreenode(stockShelves,"id","pid","code");
                }
                o.axios.post('/spdHERPService/baseData/stocInfoMgr/stocShelfGoodsInfo/queryStockShelfList',stockShelf).then(res => {
                    o.stockShelves = res.data.data;
                    cb(o.stockShelves);
                });
            },

            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            handleNodeClick(d1,d2,d3) {
                this.currentStockShelf = d1.tag;
                this.currentStockShelf.areaCode = d1.pnode.label;
                this.queryStockShelfGoodsList(1);
            },
            // 查询货位产品信息
            queryStockShelfGoodsList:function (page) {
                let o = this;
                o.page = page;
                this.$refs.stockShelfGoodsTable.clearSelection();
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:o.hosId,
                        provId:o.currentStockShelf.provId,// 库房ID
                        areaCode:o.currentStockShelf.areaCode,
                        shelfCode:o.currentStockShelf.shelfCode,
                        goodsName:o.filters.goodsName
                    },
                    page: o.page,
                    rows: o.pageSize
                };
                o.axios.post('/spdHERPService/baseData/stocInfoMgr/stocShelfGoodsInfo/queryStockShelfGoodsList',param).then(res => {
                    o.stockShelfGoods = res.data.data.data;
                    o.total = res.data.data.total;
                });
            },
            /**
             * 重置货位产品查询
             */
            resetGoodsName:function (formName) {
                this.$refs[formName].resetFields();
                this.$refs.stockShelfGoodsTable.clearSelection();
                this.queryStockShelfGoodsList(1);
            },

        },
        mounted() {
            this.hosId = this.user.corpId;
            this.listStocInfo();
            this.listByPage();
        }
    };
</script>

<style>
    #stoc-shelf-goods-info .el-tree-node__label {
        font-size: 12px;
    }
    #stoc-shelf-goods-info .el-table {
        font-size: 12px;
    }
    #stoc-shelf-goods-info .el-tree {
        height: 100%;
    }
    #stoc-shelf-goods-info .staffMgr-tableheight{
        height: 100%;
    }
    #stoc-shelf-goods-info .staffPage{
        height: 3.7%;
        border: 1px solid #d1dbe5;
        border-top: 0px;
    }
    #deptFilter .el-input__inner{
        margin-bottom: 1px;
        height: 30px;
    }
    #stoc-shelf-goods-info .provgoods-tableheight{
        height:  calc(100% - 65px);
    }
    #stoc-shelf-goods-info .stockShelfGoodsTable-tableheight{
        height:  calc(100% - 62px);
    }
</style>
