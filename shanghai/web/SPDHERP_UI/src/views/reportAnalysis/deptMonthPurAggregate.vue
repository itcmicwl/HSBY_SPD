<!--科室月请购产品数汇总-->
<template>
    <div  style="height: 100%;" id="deptMonPurAggregate" @keyup.enter="getGoodsList(1)">
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
                <el-form :inline="true" :model="filters" label-width="110px" ref="goodsName">
                    <el-form-item label="请购日期范围：">
                        <el-date-picker v-model="filters.sDate" unlink-panels type="daterange" align="right"
                         unlink-panels range-separator="至" start-placeholder="开始日期"  @change="selectDateRangChange"
                         end-placeholder="结束日期" :picker-options="pickerOptions2"  :clearable="false" :editable='false'>
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="请购科室：">
                       <el-select v-model="filters.deptIds" placeholder="请选择"  @change="deptChange">
                            <el-option
                                v-for="item in userDepts":key="item.deptId" :label="item.deptName":value="item.deptId">
                            </el-option>
                       </el-select>
                    </el-form-item>

                    <el-form-item label="产品名称/规格：" prop="goodsName">
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
                <el-table ref="goodsTable" :data="goods"  row-key='id'
                            @current-change="handleDeptGoodCurrentChange"
                            highlight-current-row v-loading="loading"  border fit
                            class="deptgoods-tableheight"  :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="40" label="序号"></el-table-column>
                    <el-table-column prop="goodsName" header-align="left" min-width="40" label="产品名称" ></el-table-column>
                    <el-table-column prop="goodsGg" header-align="left" label="规格型号" width="250"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                    <el-table-column prop="mfrsName" align="left" label="生产厂家" show-overflow-tooltip width="200"></el-table-column>
                    <el-table-column prop="made" align="center" label="生产国" width="100"></el-table-column>
                    <el-table-column prop="brand" align="center" label="品牌" width="100"></el-table-column>
                    <el-table-column prop="qtyAggregate" header-align="center" align="center" label="请购总数" width="120"></el-table-column>
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
        <deptMonthPurAggregatePrint ref="purPrintD" ></deptMonthPurAggregatePrint>
    </div>

</template>
<script>
import moment from 'moment';
import deptMonthPurAggregatePrint from './components/deptMonthPurAggregatePrint';
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
                equipDepartmentOrgId:'',//设备科orgId
                //默认选中当前月
                filters: {
                    goodsName: '' ,
                    orgName:'',
                    sDate:[],deptIds:[]
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
                goodsForPrint:[],
                good: {mfrsId:''},

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
      /*  watch:{
            orgName(val) {
                this.$refs.orgInfoTree.filter(val);
            }
        },*/
        components: { deptMonthPurAggregatePrint },
        methods: {
             print () {
                this.$refs.purPrintD.print(this.goodsForPrint,this.filters.sDate,this.deptName)
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
            exportExcel:function(){
               let tableHtml = '<table><thead><tr><th>序号</th>'
                +'<th>商品名称</th><th>规格型号</th><th>计量单位</th>'
                +'<th>请购总数</th><th>生产国</th><th>品牌</th><th>生产厂家</th></tr></thead><tbody>';
               if(this.goodsForPrint.length>0){
                    //this.$refs.purPrintD.print(res.data.data.data,this.filters);
                    this.goodsForPrint.forEach(function(item,index){
                        tableHtml+='<tr>'
                        +'<td>'+(index+1)+'</td>'
                        +'<td>'+item.goodsName+'</td>'
                        +'<td>'+item.goodsGg+'</td>'
                        +'<td>'+item.unit+'</td>'
                        +'<td>'+item.qtyAggregate+'</td>'
                        +'<td>'+item.made+'</td>'
                        +'<td>'+item.brand+'</td>'
                        +'<td>'+item.mfrsName+'</td>'
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
                let param = {
                    orderBy: "",
                    queryObject: {
                         userId:this.user.userId
                    },
                };
                let that = this;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getUserDepts', param).then(res => {
                    if (res.data.code == "0") {
                        if(that.deptId == that.equipDepartmentOrgId){
                            that.userDepts.push({deptId:'',deptName:'所有科室'});
                        }
                        for(let o = 0;o<res.data.data.data.length;o++){
                            that.userDepts.push(res.data.data.data[o]);
                        }
                        that.filters.deptIds = this.userDepts[0].deptId;
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
            query : function (){
                this.getHosMonthGoodsReceiptQuery(1);
            },
            // 点击即查询
            handleNodeClick:function(item){
                this.deptId = item.id;
                this.deptName = item.label;
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
                this.getGoodsListForPrint();
            },
            /**月份选择事件 */
            selectDateRangChange:function(){
                this.$refs.goodsTable.clearSelection();
                this.getGoodsList(1);
                this.getGoodsListForPrint();
            },
            /**表格行样式渲染 */
            tableRowClassName (row, index) {
                 return (row.row.monthQty > row.row.mQtyUpper) ? 'red-row' : ''
            },
            //获取列表数据
            getGoodsList: function (pIndex) {
                this.page = pIndex;
                let eDate = null;
                if(this.filters.sDate){
                    eDate = moment(moment(this.filters.sDate[1]).add(1,'days').format('YYYY-MM-DD')).subtract(1,'seconds').format('YYYY-MM-DD HH:mm:ss')
                }
                var param = {
                    orderBy: "",
                    queryObject: {
                        goodsName: this.filters.goodsName,
                        hosId:this.hosId,
                      //  deptId:this.deptId,
                        deptIds : this.filters.deptIds==''?(this.equipDepartmentOrgId==this.deptId?[]:[this.deptId]):[this.filters.deptIds],
                        sDate: this.filters.sDate?moment(this.filters.sDate[0]).format("YYYY-MM-DD"):null,
                        eDate: eDate?eDate:null,
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getDeptApplyGoodsAggregate', param).then(res => {
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

            //获取列表数据打印列表数据
            getGoodsListForPrint: function () {
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        //deptId:this.deptId,
                        deptIds : this.filters.deptIds==''?(this.equipDepartmentOrgId==this.deptId?[]:[this.deptId]):[this.filters.deptIds],
                        sDate: this.filters.sDate?this.filters.sDate[0]:null,
                        eDate: this.filters.sDate?this.filters.sDate[1]:null
                    },
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getDeptApplyGoodsAggregate', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.goodsForPrint = res.data.data.data;
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
                this.getGoodsListForPrint();
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
                return this.axios.get(`/spdHERPService/reportAnalysis/IndexStatisticsController/getEquipDepartmentOrgId`).then(res => {
                    this.equipDepartmentOrgId = res.data;
                });
            }
        },
        mounted() {
            this.deptId = this.user.orgId,//科室Id
            this.hosId = this.user.corpId;//单位id
            this.hosName = this.user.corpName;
            this.deptName = this.user.orgName;
            /**查询时间默认为 上月25号至本月25号 */
            var nowDate = new Date();
            var thisMonth = nowDate.getMonth() + 1;
            var thisDay = nowDate.getDate();
            var start = null;
            if(thisMonth==1){
                start = new Date((nowDate.getFullYear()-1)+'/'+12+'/25 00:00:00');
            }else{
                start = new Date(nowDate.getFullYear()+'/'+nowDate.getMonth()+'/25 00:00:00');
            }
            var end = new Date();
            end.setTime(end.getTime() + 3600 * 1000 * 24 * (25-thisDay));
            this.filters.sDate = [start, end];
            this.getUserDepts();
          //  this.getOrgsByUnit();
            this.getEquipDepartmentOrgId().then(res => this.getGoodsList(1));//selectDateRangChange事件里触发查询
            this.getGoodsListForPrint();
        }
    };

</script>
<style>
    #deptMonPurAggregate .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #deptMonPurAggregate .el-checkbox-group{
        vertical-align: middle;
    }
    #deptMonPurAggregate .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #deptMonPurAggregate .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #deptMonPurAggregate .deptGoods .el-row{
        height: 36px;
    }

    #deptMonPurAggregate .deptMonthPurchaseForm{
        padding-bottom: 10px;
       /* padding-top: 20px;
        padding-right: 19px;*/
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-top:57px;
    }
    #deptMonPurAggregate .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }

    #deptMonPurAggregate .red-row {
         color: #ff2020;
    }
</style>
