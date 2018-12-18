<!--科室本周请购低于平均请购产品数分析-->
<template>
    <div  style="height: 100%;" id="deptWeekPurUnderAvg" @keyup.enter="getGoodsList(1)">
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
                    <el-form-item label="请购科室">
                       <el-select v-model="filters.deptIds" placeholder="请选择"  @change="deptChange">
                            <el-option
                                v-for="item in userDepts":key="item.deptId" :label="item.deptName":value="item.deptId">
                            </el-option>
                       </el-select>
                    </el-form-item>

                    <el-form-item label="产品名称/规格" prop="goodsName">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称/或规格" ></el-input>
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
                <el-table ref="goodsTable" :data="goods"  row-key='id'
                            @current-change="handleDeptGoodCurrentChange"
                            highlight-current-row v-loading="loading"  border fit 
                            class="deptgoods-tableheight"  :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                    <el-table-column prop="goodsName" header-align="center" min-width="30" label="产品名称" ></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="350"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                    <el-table-column prop="curWeekQty" header-align="center" align="center" label="本周请购数" width="120"></el-table-column>
                    <el-table-column prop="weekQtyAvg" header-align="center" align="center" label="周均请购数" width="120"></el-table-column>
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
         <deptWeekPurUnderAvgPrint ref="purPrintD" ></deptWeekPurUnderAvgPrint>
    </div>

</template>
<script>
import deptWeekPurUnderAvgPrint from './components/deptWeekPurUnderAvgPrint';
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
                equipDepartmentOrgId:'',
                //默认选中当前月
                filters: { 
                    goodsName: '' ,
                    orgName:'',
                    deptIds:[]
                },
                pickerOptions2: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },

                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                goods: [],
                good: {mfrsId:''},
                printGoods:[],
                tableModel:[],
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
       /* watch:{
            orgName(val) {
                this.$refs.orgInfoTree.filter(val);
            }
        },*/
        components: { deptWeekPurUnderAvgPrint },
        methods: {
            print () {
                this.$refs.purPrintD.print(this.printGoods,this.deptName)
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
                        //this.$refs.orgInfoTree.setChecked(this.orgInfo[0].id,true);
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
                +'<th>本周请购数</th><th>周均请购数</th></tr></thead><tbody>';
               if(this.printGoods.length>0){
                    //this.$refs.purPrintD.print(res.data.data.data,this.filters);
                    this.printGoods.forEach(function(item,index){
                        tableHtml+='<tr>'
                        +'<td>'+(index+1)+'</td>'
                        +'<td>'+item.goodsName+'</td>'
                        +'<td>'+item.goodsGg+'</td>'
                        +'<td>'+item.unit+'</td>'
                        +'<td>'+item.curWeekQty+'</td>'
                        +'<td>'+item.weekQtyAvg+'</td>'
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
             /**获取当前用户对应可见科室 */
            getUserDepts:function(){
                var param = {
                    orderBy: "",
                    queryObject: {
                         userId:this.user.userId
                    },
                };
                let that= this;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getUserDepts', param).then(res => {
                    if (res.data.code == "0") {
                        if(that.deptId = that.equipDepartmentOrgId){
                            that.userDepts.push({deptId:'',deptName:'所有科室'});
                        }
                        for(let o = 0;o<res.data.data.data.length;o++){
                            that.userDepts.push(res.data.data.data[o]);
                        }
                    }else{
                        that.$message.error( res.data.msg);
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
            
            /**表格行样式渲染 */
            tableRowClassName (row, index) {
                return (row.row.weekQtyAvg > row.row.curWeekQty) ? 'red-row' : ''
            },
            //获取列表数据
            getGoodsList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        goodsName: this.filters.goodsName,
                        hosId:this.hosId,
                        //deptId:this.deptId,
                        deptIds : this.filters.deptIds==''?[]:[this.filters.deptIds],
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getDeptApplyGoodsUnderAvg', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.goods = res.data.data.data;
                        this.total = res.data.data.total;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
           
            //获取列表数据打印数据
            getGoodsListForPrint: function () {
                var param = {
                    orderBy: "",
                    queryObject: {
                      //  goodsName: this.filters.goodsName,
                        hosId:this.hosId,
                        //deptId:this.deptId,
                        deptIds : this.filters.deptIds==''?[]:[this.filters.deptIds],
                    },
                   
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getDeptApplyGoodsUnderAvg', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.printGoods = res.data.data.data;
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
            getEquipDepartmentOrgId : function(){
                let that = this;
                this.axios.get(`/spdHERPService/reportAnalysis/IndexStatisticsController/getEquipDepartmentOrgId`).then(res => {
                    that.equipDepartmentOrgId = res.data;
                });
            }
        },
        mounted() {
            this.deptId = this.user.orgId,//科室Id
            this.hosId = this.user.corpId;//单位id
            this.hosName = this.user.corpName;
            this.deptName = this.user.orgName;
            this.getEquipDepartmentOrgId();
            this.getUserDepts();
            //this.getOrgsByUnit();
            this.getGoodsList(1);//selectDateRangChange事件里触发查询
            this.getGoodsListForPrint();
        }
    };

</script>
<style>
    #deptWeekPurUnderAvg .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #deptWeekPurUnderAvg .el-checkbox-group{
        vertical-align: middle;
    }
    #deptWeekPurUnderAvg .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #deptWeekPurUnderAvg .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #deptWeekPurUnderAvg .deptGoods .el-row{
        height: 36px;
    }
    
    #deptWeekPurUnderAvg .deptMonthPurchaseForm{
        padding-bottom: 10px;
       /* padding-top: 20px;
        padding-right: 19px;*/
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-top:57px;
    }
    #deptWeekPurUnderAvg .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
     #deptWeekPurUnderAvg .red-row {
        color: #ff2020;
    }
</style>