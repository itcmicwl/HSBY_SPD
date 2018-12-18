<!--科室月请购产品数与请购上限查询-->
<template>
    <div  style="height: 100%;" id="deptMonPurAnalysis" @keyup.enter="getGoodsList(1)">
        <!--
        <el-col :span="5" style="height: calc(99% - 30px);">
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
        -->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <!--工具条-->
            <div style="padding-bottom: 1px;">
                <el-form :inline="true" :model="filters" label-width="100px" ref="goodsName">
                    <el-form-item label="请购月份：">
                        <el-select v-model="applyMonth" prop='applyMonth'  filterable  
                            placeholder="请选择请购月份" @change="selectMonthChange">
                            <el-option
                                    v-for="item in queryMonth"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    
                    <el-form-item label="请购科室：">
                       <el-select v-model="filters.deptIds" placeholder="请选择"  @change="deptChange">
                            <el-option
                                v-for="item in userDepts":key="item.deptId" :label="item.deptName":value="item.deptId">
                            </el-option>
                       </el-select>
                    </el-form-item>

                    <el-form-item label="产品名/规格：" prop="goodsName">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称或规格" ></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="getGoodsList(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetGoodsName('goodsName')" >重置</el-button>
                        <el-button type="info"  @click="print">打印</el-button>
                        <el-button type="info"  @click="exportExcel">导出</el-button>
                    </el-button-group>
                    
                </el-form>
            </div>
            <!--科室月请购产品列表-->
            <template>
                <el-table ref="goodsTable" :data="tableModel"  row-key='id'
                            @current-change="handleDeptGoodCurrentChange"
                            highlight-current-row v-loading="loading"  border fit 
                            class="deptgoods-tableheight"  :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                    <el-table-column prop="goodsName" header-align="center" min-width="40" label="产品名称" ></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="350"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                    <el-table-column prop="fillYearMonth" align="center" label="请购月份" width="70"></el-table-column>
                    <el-table-column prop="monthQty" header-align="center" align="center" label="月请购数" width="80"></el-table-column>
                    <el-table-column prop="mQtyUpper" header-align="center" align="center" label="请购上限" width="80"></el-table-column>
                    
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
        </el-col>
        <deptMonthPurAnalysisPrint ref="purPrintD" ></deptMonthPurAnalysisPrint>
    </div>

</template>
<script>
import deptMonthPurAnalysisPrint from './components/deptMonthPurAnalysisPrint';
import $ from 'jquery';
import '../../common/third/tableExport/tableExport';
import '../../common/third/tableExport/jquery.base64';
    export default {
        data() {
            return {
                hosId:'',
                deptId:'',
                hosName:'',
                deptName:'',
                userDepts:[],
                isShow:{m1qtyShow:false,m2qtyShow:false,m3qtyShow:false,m4qtyShow:false,m5qtyShow:false,m6qtyShow:false,
                        m7qtyShow:false,m8qtyShow:false,m9qtyShow:false,m10qtyShow:false,m11qtyShow:false,m12qtyShow:false},
                queryMonth:[
                    {label:'一月',value:'01'},
                    {label:'二月',value:'02'},
                    {label:'三月',value:'03'},
                    {label:'四月',value:'04'},
                    {label:'五月',value:'05'},
                    {label:'六月',value:'06'},
                    {label:'七月',value:'07'},
                    {label:'八月',value:'08'},
                    {label:'九月',value:'09'},
                    {label:'十月',value:'10'},
                    {label:'十一月',value:'11'},
                    {label:'十二月',value:'12'}
                ],
                equipDepartmentOrgId:'',//设备科orgId
                //默认选中当前月
                applyMonth:(new Date().getMonth()+1)<10 ? ("0"+(new Date().getMonth()+1)):(new Date().getMonth()+1)+'',
                filters: { 
                    goodsName: '' ,
                    orgName:'',deptIds:[]
                },
                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                goods: [],
                good: {mfrsId:''},

                tableModel:[],
                printTableModel:[],
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
        components: { deptMonthPurAnalysisPrint },
       /* watch:{
            orgName(val) {
                this.$refs.orgInfoTree.filter(val);
            }
        },*/
        methods: {
            print () {
                this.$refs.purPrintD.print(this.printTableModel,this.applyMonth,this.deptName)
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
             
            getOrgsByUnit: function (unitId) {
                var param = {};
                this.loading = true;
                this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res => {
                    this.orgInfo = res.data.data.children;
                    this.expandIds = [this.orgInfo[0].id];
                    this.$nextTick(function () {
                       // this.$refs.orgInfoTree.setChecked(this.orgInfo[0].id,true);
                    });
                    
                    this.loading = false;
                });
            },
            // 点击即查询
            handleNodeClick:function(item){
                this.deptId = item.id;
                this.deptName = item.label;
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
                this.getGoodsListForPrint();
            },
            exportExcel:function(){
               let tableHtml = '<table><thead><tr><th>序号</th>'
                +'<th>商品名称</th><th>规格型号</th><th>计量单位</th>'
                +'<th>请购月份</th><th>月请购数</th><th>请购上限</th></tr></thead><tbody>';
               if(this.printTableModel.length>0){
                    //this.$refs.purPrintD.print(res.data.data.data,this.filters);
                    this.printTableModel.forEach(function(item,index){
                        tableHtml+='<tr>'
                        +'<td>'+(index+1)+'</td>'
                        +'<td>'+item.goodsName+'</td>'
                        +'<td>'+item.goodsGg+'</td>'
                        +'<td>'+item.unit+'</td>'
                        +'<td>'+item.fillYearMonth+'</td>'
                        +'<td>'+item.monthQty+'</td>'
                        +'<td>'+(item.mQtyUpper==null?'':item.mQtyUpper)+'</td>'
                        +'</tr>'
                    });
                    tableHtml+='</tbody></table>';
                    $(tableHtml).tableExport({type:'excel',
                        escape:'false',
                       // tableName:this.deptName+'收货汇总'
                    });
                }else{
                    this.$message.error('无数据导出！');
                    return false;
               }
            },
            getEquipDepartmentOrgId : function(){
                let that = this;
                this.axios.get(`/spdHERPService/reportAnalysis/IndexStatisticsController/getEquipDepartmentOrgId`).then(res => {
                    that.equipDepartmentOrgId = res.data;
                });
            },
            /**获取当前用户对应可见科室 */
            getUserDepts:function(){
                var param = {
                    orderBy: "",
                    queryObject: {
                         userId:this.user.userId
                    },
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getUserDepts', param).then(res => {
                    if (res.data.code == "0") {
                        if(this.deptId == this.equipDepartmentOrgId){
                            this.userDepts.push({deptId:'',deptName:'所有科室'});
                        }
                        for(let o = 0;o<res.data.data.data.length;o++){
                            this.userDepts.push(res.data.data.data[o]);
                        }
                         that.filters.deptIds = this.userDepts[0].deptId;
                       // this.userDepts = res.data.data.data;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                });
            },
            deptChange :function(deptId){
                this.deptId = deptId;
                if(this.userDepts.length>0){
                    this.deptName =this.userDepts.find((dept,index)=>{return dept.deptId==deptId}).deptName;
                }
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
                this.getGoodsListForPrint();
            },
            /**月份选择事件 */
            selectMonthChange:function(){
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
                this.getGoodsListForPrint();
            },
            tableRowClassName (row, index) {
                 return (row.row.monthQty > row.row.mQtyUpper) ? 'red-row' : ''
            },
            //获取产品列表
            getGoodsList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        goodsName: this.filters.goodsName,
                        hosId:this.hosId,
                        //deptId:this.deptId,
                        deptIds : this.filters.deptIds==''?[]:[this.filters.deptIds],
                        fillMonth:this.applyMonth
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/deptApplyGoodsUpperAnalysis', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.goods = res.data.data.data;
                        this.total = res.data.data.total;
                        this.makeTableData(this.goods);
                        /*if(this.goods.length>0){
                            this.$nextTick(function () {
                                this.good = this.goods[0];
                                this.$refs.goodsTable.setCurrentRow(this.good);
                            });
                        }*/
                        
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },

            //获取产品列表
            getGoodsListForPrint: function () {
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        //deptId:this.deptId,
                        deptIds : this.filters.deptIds==''?[]:[this.filters.deptIds],
                        fillMonth:this.applyMonth
                    },
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/deptApplyGoodsUpperAnalysis', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.makeTableData(res.data.data.data,true);
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            /**重构表格绑定模型 */
            makeTableData:function(list,isForPrint){
                let applyMonth = this.applyMonth;
                let _this = this;
                if(isForPrint){
                    _this.printTableModel = [];
                }else{
                    _this.tableModel = [];
                }
                list.forEach(function(item){
                    if(applyMonth=='01'){
                        var newRow1 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m1Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow1)
                        }else{
                            _this.tableModel.push(newRow1);
                        }
                    }else if(applyMonth=='02'){
                        var newRow2 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m2Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow2)
                        }else{
                            _this.tableModel.push(newRow2);
                        }
                    }else if(applyMonth=='03'){
                        var newRow3 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m3Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow3)
                        }else{
                            _this.tableModel.push(newRow3);
                        }
                    }else if(applyMonth=='04'){
                        var newRow4 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty,mQtyUpper:item.m4Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow4)
                        }else{
                            _this.tableModel.push(newRow4);
                        }
                    }else if(applyMonth=='05'){
                        var newRow5 ={deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty,mQtyUpper:item.m5Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow5)
                        }else{
                            _this.tableModel.push(newRow5);
                        }
                    }else if(applyMonth=='06'){
                        var newRow6 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty,mQtyUpper:item.m6Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow6)
                        }else{
                            _this.tableModel.push(newRow6);
                        }
                    }else if(applyMonth=='07'){
                        var newRow7 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m7Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow7)
                        }else{
                            _this.tableModel.push(newRow7);
                        }
                    }else if(applyMonth=='08'){
                        var newRow8 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m8Qty,unit:item.unit
                        };
                         if(isForPrint){
                            _this.printTableModel.push(newRow8)
                        }else{
                        _this.tableModel.push(newRow8);
                        }
                    }else if(applyMonth=='09'){
                        var newRow9 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty,mQtyUpper:item.m9Qty,unit:item.unit
                        };
                         if(isForPrint){
                            _this.printTableModel.push(newRow9)
                        }else{
                        _this.tableModel.push(newRow9);
                        }
                    }else if(applyMonth=='10'){
                        var newRow10 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m10Qty,unit:item.unit
                        };
                         if(isForPrint){
                            _this.printTableModel.push(newRow10)
                        }else{
                        _this.tableModel.push(newRow10);
                        }
                    }else if(applyMonth=='11'){
                        var newRow11 ={deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty, mQtyUpper:item.m11Qty,unit:item.unit
                        };
                         if(isForPrint){
                            _this.printTableModel.push(newRow11)
                        }else{
                        _this.tableModel.push(newRow11);
                        }
                    }else if(applyMonth=='12'){
                        var newRow12 = {deptId:item.deptId,fillMonth:item.fillMonth,fillYearMonth:item.fillYearMonth,goodsGg:item.goodsGg,
                                     goodsId:item.goodsId, goodsName:item.goodsName,hosGoodsId:item.hosGoodsId, hosId:item.hosId, 
                                    monthQty:item.monthQty,mQtyUpper:item.m12Qty,unit:item.unit
                        };
                        if(isForPrint){
                            _this.printTableModel.push(newRow12)
                        }else{
                        _this.tableModel.push(newRow12);
                        }
                    }     
                });
                
            },
            resetGoodsName:function (formName) {
                this.$refs[formName].resetFields();
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
            },
            /**选中行事件 */
            handleDeptGoodCurrentChange : function(currentRow){
                if(currentRow){
                    this.good = currentRow;
                   // this.queryDeptMonthPurchaseLimit();
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
            
            /**分业处理事件 */
            handleCurrentChange : function(val) {
                this.page = val;
                this.getGoodsList(this.page);
            },
            handleSizeChange:function(val) {
                this.pageSize = val;
                this.getGoodsList(this.page);
            },
        },
        mounted() {
            this.deptId = this.user.orgId,//科室Id
            this.hosId = this.user.corpId;//单位id
            this.hosName = this.user.corpName;
            this.deptName = this.user.orgName;
            this.getEquipDepartmentOrgId();
            // this.getOrgsByUnit();
            this.getUserDepts();
            this.getGoodsList(1);
            this.getGoodsListForPrint();
        }
    };

</script>
<style>
    #deptMonPurAnalysis .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #deptMonPurAnalysis .el-checkbox-group{
        vertical-align: middle;
    }
    #deptMonPurAnalysis .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #deptMonPurAnalysis .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #deptMonPurAnalysis .deptGoods .el-row{
        height: 36px;
    }
    
    #deptMonPurAnalysis .deptMonthPurchaseForm{
        padding-bottom: 10px;
       /* padding-top: 20px;
        padding-right: 19px;*/
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-top:57px;
    }
    #deptMonPurAnalysis .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
    #deptMonPurAnalysis .red-row {
         color: #ff2020;
    }
</style>