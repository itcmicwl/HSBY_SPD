<!--医院月高、低值耗材入库单据表查询-->
<template>
    <div style="height: 100%;" id="hosMonthInStockBills">
        <!-- <el-col :span="5" style="height: calc(99% - 30px);">
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
                </el-col> -->

        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; height: 99%;">
            <!--工具条-->
            <div style="padding-bottom: 1px;">
                <el-form :inline="true" :model="filters" ref="queryForm">
                    <el-form-item label="入库单号:" prop="instockBillId">
                        <el-input v-model="filters.instockBillId" placeholder="请输入入库单号" ></el-input>
                    </el-form-item>
                    <el-form-item label="供应商:" prop="provider">
                        <el-input v-model="filters.provider" :clearable="true" placeholder="请输入供应商"  ></el-input>
                    </el-form-item>
                    <el-form-item label="会计期:">
                        <el-select v-model="inStockMonth" prop='inStockMonth'  filterable style="width:88%" placeholder="请选择会计期" @change="selectMonthChange">
                            <el-option v-for="item in queryMonth" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="入库方式:" >
                        <el-select v-model="instockType" prop='instockType' size="mini" filterable :clearable="true" style="width:80%" placeholder="请选择入库方式">
                            <el-option v-for="item in inStockTypes" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="getHosMonthInStockBills(1)" >查询</el-button>
                            <!-- <el-button type="primary" v-on:click="resetForm('queryForm')" >重置</el-button> -->
                            <el-button type="primary"  @click="print">打印</el-button>
                            <el-button   type='primary' @click="exportExcel">导出</el-button>
                        </el-button-group>
                    </el-form-item>
                </el-form>
            </div>
            <!--单据列表-->
            <template>
                <el-table ref="billsTable" :data="tableModel" row-key='id' 
                        @current-change="handleDeptGoodCurrentChange" 
                        highlight-current-row v-loading="loading" border fit 
                        class="deptgoods-tableheight" 
                        :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center" width="50" label="序号"></el-table-column>
                    <el-table-column prop="instockBillId" header-align="center" label="入库单号" width="150"></el-table-column>
                    <el-table-column prop="accountingPeriod" header-align="center" label="会计期" width="60"></el-table-column>
                    <el-table-column prop="highOrLower" align="center" label="入库方式" width="60"></el-table-column>
                    <el-table-column prop="auditUserName" align="center" label="审核人员"></el-table-column>
                    <el-table-column prop="instockDeptName" header-align="center" align="center" label="入库部门" width="80"></el-table-column>
                    <el-table-column prop="goodsName" header-align="center" align="center" label="商品名称" width="230"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" align="center" label="商品规格" width="150"></el-table-column>
                    <el-table-column prop="inPrice" header-align="center" align="center" label="商品单价"></el-table-column>
                    <el-table-column prop="qty" header-align="center" align="center" label="入库数量" ></el-table-column>
                    <el-table-column prop="goodsAmount" header-align="center" align="center" label="商品金额"></el-table-column>
                    <el-table-column prop="provider" header-align="center" align="center" label="供应商" width="130"></el-table-column>
                    <el-table-column prop="fundType" header-align="center" align="center" label="资金来源" :formatter="fundTypeFormat"></el-table-column>
                    <el-table-column prop="goodsClassification" header-align="center" align="center" label="物资分类"></el-table-column>
                    <el-table-column prop="saleBillId" header-align="center" align="center" label="SO单号"></el-table-column>
                    <el-table-column prop="erpCode" header-align="center" align="center" label="JDE编码"></el-table-column>
                    <el-table-column prop="batchCode" header-align="center" align="center" label="批号"></el-table-column>
                    <el-table-column prop="productDate" header-align="center" align="center" label="生产日期"></el-table-column>
                    <el-table-column prop="expdtEndDate" header-align="center" align="center" label="有效期"></el-table-column>
                </el-table>
                <!--分业工具栏-->
                <el-col :span="24" class="toolbar pageBar">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[10,20, 100, 200, 400]" :page-size="pageSize" :total="total" layout="total, sizes, prev, pager, next" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </template>
        </el-col>
        <viewBuyPrint ref="applyBuyPrintD" :inStockMonth="inStockMonth"></viewBuyPrint>
    </div>
</template>
<script>
import viewBuyPrint from './components/hosMonthInStockBillQueryPrint';
import $ from 'jquery';
import '../../common/third/tableExport/tableExport';
import '../../common/third/tableExport/jquery.base64';
export default {
    data() {
        return {
            hosId: '',
            hosName: '',
            deptName: '',
            fundTypeDicts: [],
            printDatas:[],

            queryMonth: [
                { label: '一月', value: '01' },
                { label: '二月', value: '02' },
                { label: '三月', value: '03' },
                { label: '四月', value: '04' },
                { label: '五月', value: '05' },
                { label: '六月', value: '06' },
                { label: '七月', value: '07' },
                { label: '八月', value: '08' },
                { label: '九月', value: '09' },
                { label: '十月', value: '10' },
                { label: '十一月', value: '11' },
                { label: '十二月', value: '12' }
            ],
            inStockTypes: [
                { label: '实物采购', value: '10' },
                { label: '虚物采购', value: '20' }
            ],
            //默认选中当前月
            inStockMonth: (new Date().getMonth() + 1) < 10 ? ("0" + (new Date().getMonth() + 1)) : (new Date().getMonth() + 1) + '',
            instockType: '',
            filters: {
                instockBillId: '',
                provider: ''
            },
            total: 0,
            page: 1,
            pageSize: 10,
            loading: false,
            tableModel: [],

            // // 树
            // expandIds: [],
            // orgInfo: [],
            // orgInfoProps: {
            //     children: 'children',
            //     label: 'label',
            //     id: 'id',
            //     pid: 'pid'
            // },
            // // 快速查询
            // orgName:''
        }
    },
    components: { viewBuyPrint },
    watch: {
        // orgName(val) {
        //     this.$refs.orgInfoTree.filter(val);
        // }
    },
    methods: {
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        /**打印 */
        print() {
            let printDatas=this.printDatas;
            for(var e of printDatas){
                e.fundType = this.fundTypeFormat(e);
            }
            this.$refs.applyBuyPrintD.print(printDatas);
        },
        fundTypeFormat(row) {
            let fundType = row.fundType;
            let fundTypes = this.fundTypeDicts;
            for (var e of fundTypes) {
                if (fundType === e.val) {
                    return e.ename;
                }
            }
        },
        // getOrgsByUnit: function (unitId) {
        //     var param = {};
        //     this.loading = true;
        //     this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res => {
        //         this.orgInfo = res.data.data.children;
        //         this.expandIds = [this.orgInfo[0].id];
        //         this.$nextTick(function () {
        //            // this.$refs.orgInfoTree.setChecked(this.orgInfo[0].id,true);
        //         });

        //         this.loading = false;
        //     });
        // },
        // // 点击即查询
        // handleNodeClick:function(item){
        //     this.deptId = item.id;
        //     this.$refs.billsTable.clearSelection();
        //     this.getHosMonthInStockBills(1);
        // },
        /**月份选择事件 */
        selectMonthChange: function() {
            this.$refs.billsTable.clearSelection();
            this.getHosMonthInStockBills(1);
        },
        tableRowClassName(row, index) {
            return (row.monthQty > row.mQtyUpper) ? 'row-disabled-red-nobg' : ''
        },
        //获取入库单据列表
        getHosMonthInStockBills: function(pIndex) {
            this.page = pIndex;
            var param = {
                orderBy: "",
                queryObject: {
                    instockBillId: this.filters.instockBillId,
                    provider: this.filters.provider,
                    hosId: this.hosId,
                    instockType: this.instockType,
                    accountingPeriod: this.inStockMonth
                },
                page: this.page,
                rows: this.pageSize
            };
            this.loading = true;
            this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthInStockBillQuery', param).then(res => {
                this.loading = false;
                if (res.data.code == "0") {
                    this.tableModel = res.data.data.data;
                    this.total = res.data.data.total;
                    this.getHosMonthInStockBillsPrint(1);
                } else {
                    this.$message.error(res.data.msg);
                }
            }, err => {
                this.loading = false;
            });
        },
         //获取入库单据列表
        getHosMonthInStockBillsPrint: function(pIndex) {
            var param = {
                orderBy: "",
                queryObject: {
                    instockBillId: this.filters.instockBillId,
                    provider: this.filters.provider,
                    hosId: this.hosId,
                    instockType: this.instockType,
                    accountingPeriod: this.inStockMonth
                },
            };
            this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthInStockBillQuery', param).then(res => {
                if (res.data.code == "0") {
                    this.printDatas = res.data.data.data;
                } else {
                    this.$message.error(res.data.msg);
                }
            }, err => {
                this.loading = false;
            });
        },
        /**重构表格绑定模型 */
        resetForm: function(formName) {
            this.$refs[formName].resetFields();
            this.$refs.billsTable.clearSelection();
            this.getHosMonthInStockBills(1);
        },
        /**选中行事件 */
        handleDeptGoodCurrentChange: function(currentRow) {
            if (currentRow) {
                this.good = currentRow;
                // this.queryDeptMonthPurchaseLimit();
            } else {
                this.isDisable = true;
            }
        },
        /**分业处理事件 */
        handleCurrentChange: function(val) {
            this.page = val;
            this.getHosMonthInStockBills(this.page);
        },
        handleSizeChange: function(val) {
            this.pageSize = val;
            this.getHosMonthInStockBills(this.page);
        },
        //查询资金来源数据字典
        getFundTypeDict: function() {
            this.axios.post('/platformService/sys/dict/getDictValueByDictEname', { dictName: 'ZJLY' }).then(res => {
                if (res.data.code == '0') {
                    this.fundTypeDicts = res.data.data
                } else {
                    this.$message.error(res.data.msg)
                }
            }, err => { })
        },
        /**导出excel */
        exportExcel:function(){
            let tableHtml ='<table><thead><tr>'
            +'<th>序号</th>'
            +'<th>入库单号</th><th>会计期</th><th>入库方式</th>'
            +'<th>审核人员</th><th>入库部门</th><th>商品名称</th><th>商品规格</th>'
            +'<th>商品单价</th><th>入库数量</th><th>金额</th>'
            +'<th>供应商</th><th>资金来源</th>'
            +'<th>物资分类</th><th>SO单号</th><th>JDE编码</th>'
            +'<th>批号</th><th>生产日期</th><th>有效期</th>'
            +'</tr></thead><tbody>';
            if(this.printDatas.length>0){
                let printDatas=this.printDatas;
                for(var e of printDatas){
                    e.fundType = this.fundTypeFormat(e);
                }
                printDatas.forEach(function(item,index){
                    tableHtml+='<tr>'
                    +'<td>'+(index+1)+'</td>'
                    +'<td>'+item.instockBillId+'</td>'
                    +'<td>'+item.accountingPeriod+'</td>'
                    +'<td>'+item.highOrLower+'</td>'
                    +'<td>'+(item.auditUserName?item.auditUserName:"")+'</td>'
                    +'<td>'+item.instockDeptName+'</td>'
                    +'<td>'+item.goodsName+'</td>'
                    +'<td>'+(item.goodsGg?item.goodsGg:"")+'</td>'
                    +'<td>'+item.inPrice+'</td>'
                    +'<td>'+item.qty+'</td>'
                    +'<td>'+item.goodsAmount+'</td>'
                    +'<td>'+item.provider+'</td>'
                    +'<td>'+item.fundType+'</td>'
                    +'<td>'+(item.goodsClassification?item.goodsClassification:"")+'</td>'
                    +'<td>'+(item.saleBillId?item.saleBillId:"")+'</td>'
                    +'<td>'+(item.erpCode?item.erpCode:"")+'</td>'
                    +'<td>'+(item.batchCode?item.batchCode:"")+'</td>'
                    +'<td>'+(item.productDate?item.productDate:"")+'</td>'
                    +'<td>'+(item.expdtEndDate?item.expdtEndDate:"")+'</td>'
                    +'</tr>'
                });
                tableHtml+='</tbody></table>';
                $(tableHtml).tableExport({type:'excel',
                    escape:'false',
                    fileName:'医院高低值耗材入库单'
                });
            }else{
                this.$message.error('无数据导出！');
                return false;
            }
        },
    },
    mounted() {
        this.deptId = this.user.orgId,//科室Id
            this.hosId = this.user.corpId;//单位id
        this.hosName = this.user.corpName;
        this.deptName = this.user.orgName;
        //this.getOrgsByUnit();
        this.getHosMonthInStockBills(1);
        this.getFundTypeDict()
    }
};

</script>
<style>
#hosMonthInStockBills .el-checkbox__label {
    font-size: 12px;
    vertical-align: middle;
}

#hosMonthInStockBills .el-checkbox-group {
    vertical-align: middle;
}

#hosMonthInStockBills .deptgoods-tableheight {
    width: 100%;
    height: calc(100% - 90px);
}

#hosMonthInStockBills .deptGoods {
    border-radius: 6px;
    padding-bottom: 10px;
    padding-top: 12px;
    padding-right: 19px;
    border: 1px solid #d1dbe5;
    padding-left: 0px;
    margin-left: 15px;
    margin-right: 15px;
}

#hosMonthInStockBills .deptGoods .el-row {
    height: 36px;
}

#hosMonthInStockBills .pageBar {
    border-bottom: 1px solid #d1dbe5;
    border-left: 1px solid #d1dbe5;
    border-right: 1px solid #d1dbe5;
}
</style>
