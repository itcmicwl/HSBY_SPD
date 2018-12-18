<!--医院高值耗材月消耗汇总-->
<template>
    <div  style="height: 100%;" id="hosMonthHighValueComs">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <!--工具条-->
            <div style="padding-bottom: 1px;">
                <el-form :inline="true" :model="filters" label-width="100px" ref="queryForm">
                    <el-form-item label="汇总月份：">
                        <el-select v-model="applyMonth" prop='applyMonth'  filterable  style="width: 90%" 
                            placeholder="汇总月份" @change="selectMonthChange">
                            <el-option
                                    v-for="item in queryMonth"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="产品类型：">
                        <el-select v-model="goodsType" prop='goodsType'  filterable :clearable="true" style="width:88%"
                            placeholder="请选择产品类型">
                            <el-option
                                v-for="item in goodsTypes"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button-group>
                            <el-button type="primary" v-on:click="getHighValueConsume(1)" >查询</el-button>
                            <el-button type="info"  @click="print">打印</el-button>
                            <el-button :inline="true"  type='info' @click="exportExcel">导出</el-button>
                        </el-button-group>
                    </el-form-item>
                </el-form>
            </div>
            <!--列表-->
            <template>
                <el-table ref="consumeTable" :data="rowDatas"  row-key='id'
                            highlight-current-row v-loading="loading"  border fit 
                            class="deptgoods-tableheight" :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                    <el-table-column prop="goodsClassification" header-align="center" min-width="80" label="物资分类" ></el-table-column>
                    <el-table-column prop="initialAmount" header-align="center" label="期初金额" width="180"></el-table-column>
                    <el-table-column prop="inStocAmount" align="center" label="入库金额" width="180"></el-table-column>
                    <el-table-column prop="outStocAmount" align="center" label="出库金额" width="170"></el-table-column>
                    <el-table-column prop="inventoryAmount" header-align="center" align="center" label="结存金额" width="180"></el-table-column>                    
                </el-table>
                <!--分业工具栏-->
                <el-col :span="24" class="toolbar pageBar">
                    <el-pagination @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange" :current-page="page"
                                    :page-sizes="[20, 100, 200, 400]" :page-size="pageSize" :total="total"
                                    layout="total, sizes, prev, pager, next" style="float:right;" small>
                    </el-pagination>
                </el-col>
                <hosMonthHighValueConsumePrint ref="hosMonthHighValueConsumePrintD" :applyMonth="applyMonth"></hosMonthHighValueConsumePrint>
            </template>
        </el-col>
    </div>

</template>
<script>
import hosMonthHighValueConsumePrint from './components/hosMonthHighValueConsumePrint';
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
                goodsType:'',

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
                goodsTypes:[
                    {label:'高值',value:'1'},
                    {label:'低值',value:'0'}
                ],
                //默认选中当前月
                applyMonth:(new Date().getMonth()+1)<10 ? ("0"+(new Date().getMonth()+1)):(new Date().getMonth()+1)+'',
                
                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                rowDatas: [],
                tableModel:[],
                printDatas:[],
            }
        },
        watch:{
        },
        components: { hosMonthHighValueConsumePrint },
        methods: {
            //医院高值耗材月消耗汇总
            getHighValueConsume: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        goodsType:this.goodsType,
                        hosId:this.user.corpId,
                        month:this.applyMonth
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthHighValueConsume', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.rowDatas = res.data.data.data;
                        this.total = res.data.data.total;
                        this.getHighValueConsumePrint(1);
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            /**导出excel */
            exportExcel:function(){
               let tableHtml = '<table><thead><tr><th>序号</th>'
                +'<th>物资分类</th><th>期初金额</th><th>入库金额</th>'
                +'<th>出库金额</th><th>结存金额</th>/tr></thead><tbody>';
               if(this.printDatas.length>0){
                    this.printDatas.forEach(function(item,index){
                        tableHtml+='<tr>'
                        +'<td>'+(index+1)+'</td>'
                        +'<td>'+item.goodsClassification+'</td>'
                        +'<td>'+item.initialAmount+'</td>'
                        +'<td>'+item.inStocAmount+'</td>'
                        +'<td>'+item.outStocAmount+'</td>'
                        +'<td>'+item.inventoryAmount+'</td>'
                        +'</tr>'
                    });
                    tableHtml+='</tbody></table>';
                    $(tableHtml).tableExport({type:'excel',
                        escape:'false',
                        tableName:'医院高低值耗材财务账面月汇总'
                    });
                }else{
                    this.$message.error('无数据导出！');
                    return false;
               }
            },
            //医院高值耗材月消耗汇总打印
            getHighValueConsumePrint: function (pIndex) {
                var param = {
                    orderBy: "",
                    queryObject: {
                        goodsType:this.goodsType,
                        hosId:this.user.corpId,
                        month:this.applyMonth
                    },
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthHighValueConsume', param).then(res => {
                    if (res.data.code == "0") {
                       this.printDatas = res.data.data.data;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            goBack() {
                this.$router.go(-1);
            },
            /**报表打印*/
            print() {
                let printDatas=this.printDatas;
                this.$refs.hosMonthHighValueConsumePrintD.print(printDatas);
            },
            /**月份选择事件 */
            selectMonthChange:function(){
                this.$refs.consumeTable.clearSelection();
                this.getHighValueConsume(1);
            },
            /**行样式 */
            tableRowClassName (row, index) {
                // return (row.monthQty > row.mQtyUpper) ? 'row-disabled-red-nobg' : ''
            },
            /**分业处理事件 */
            handleCurrentChange : function(val) {
                this.page = val;
                this.getHighValueConsume(this.page);
            },
            handleSizeChange:function(val) {
                this.pageSize = val;
                this.getHighValueConsume(this.page);
            },
        },
        mounted() {
            this.hosId = this.user.corpId;//单位id
            this.getHighValueConsume(1);
        }
    };

</script>
<style>
    #hosMonthHighValueComs .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #hosMonthHighValueComs .el-checkbox-group{
        vertical-align: middle;
    }
    #hosMonthHighValueComs .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #hosMonthHighValueComs .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #hosMonthHighValueComs .deptGoods .el-row{
        height: 36px;
    }
    
    #hosMonthHighValueComs .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
    

</style>