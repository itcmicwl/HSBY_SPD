<!--医院低值耗材月核算-->
<template>
    <div  style="height: 100%;" id="hosMonthLowValueComsumeDiv">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <!--工具条-->
            <div style="padding-bottom: 1px;">
                <el-form :inline="true" :model="filters" label-width="100px" ref="goodsName">
                    <el-form-item label="汇总月份：">
                        <el-select v-model="applyMonth" prop='applyMonth'  filterable  style="width: 70%" 
                            placeholder="汇总月份" @change="selectMonthChange">
                            <el-option
                                    v-for="item in queryMonth"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-button :inline="true"  @click="print">打印</el-button>
                    <el-button :inline="true"  type='info' @click="exportExcel">导出</el-button>
                </el-form>
            </div>
            <!--列表-->
            <template>
                <el-table ref="consumeTable" :data="rowDatas"  row-key='id'
                            highlight-current-row v-loading="loading"  border fit 
                            class="deptgoods-tableheight" :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                    <el-table-column prop="deptName" header-align="center" min-width="80" label="科室" ></el-table-column>
                    <el-table-column prop="ownExpense" header-align="center" label="自筹金费" width="120"></el-table-column>
                    <el-table-column prop="financialAppropriation" align="center" label="财政拨款" width="120"></el-table-column>
                    <el-table-column prop="donation" align="center" label="捐赠" width="120"></el-table-column>
                    <el-table-column prop="researchFee" header-align="center" align="center" label="科研金费" width="120"></el-table-column> 
                    <el-table-column prop="total" align="center" label="总计" width="120"></el-table-column>
                   <!-- <el-table-column prop="monthQty" header-align="center" align="center" label="备注" width="140"></el-table-column> -->                   
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
            <monthLowValuePrint ref="purPrintD" :fillMonth = "applyMonth" ></monthLowValuePrint>
        </el-col>
    </div>

</template>
<script>
    import monthLowValuePrint from './components/monthLowValuePrint';
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
                //默认选中当前月
                applyMonth:(new Date().getMonth()+1)<10 ? ("0"+(new Date().getMonth()+1)):(new Date().getMonth()+1)+'',
                
                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                rowDatas: [],
                tableModel:[],
            }
        },
        watch:{
        },
        components: { monthLowValuePrint },
        methods: {
            //获取产品列表
            getHighValueConsume: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        
                       // goodsName: this.filters.goodsName,
                        // hosId:this.hosId,
                        // deptId:this.deptId,
                        fillMonth:this.applyMonth
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthLowValueConsume', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.rowDatas = res.data.data.data;
                        this.total = res.data.data.total;
                        
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            exportExcel:function(){
               let tableHtml = '<table><thead><tr><th>序号</th>'
                +'<th>科室</th><th>自筹金费</th><th>财政拨款</th>'
                +'<th>捐赠</th><th>科研金费</th><th>总计</th></tr></thead><tbody>';
               if(this.rowDatas.length>0){
                    //this.$refs.purPrintD.print(res.data.data.data,this.filters);
                    this.rowDatas.forEach(function(item,index){
                        tableHtml+='<tr>'
                        +'<td>'+(index+1)+'</td>'
                        +'<td>'+item.deptName+'</td>'
                        +'<td>'+item.ownExpense+'</td>'
                        +'<td>'+item.financialAppropriation+'</td>'
                        +'<td>'+item.donation+'</td>'
                        +'<td>'+item.researchFee+'</td>'
                        +'<td>'+item.total+'</td>'
                        +'</tr>'
                    });
                    tableHtml+='</tbody></table>';
                    $(tableHtml).tableExport({type:'excel',
                        escape:'false',
                        fileName:new Date().getFullYear()+'年'+ this.applyMonth+' 月医院低值耗材月核算'
                    });
                }else{
                    this.$message.error('无数据导出！');
                    return false;
               }
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
            print() {
                this.$refs.purPrintD.print();
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
            this.deptId = this.user.orgId,//科室Id
            this.hosId = this.user.corpId;//单位id
            this.hosName = this.user.corpName;
            this.deptName = this.user.orgName;
            this.getHighValueConsume(1);
        }
    };

</script>
<style>
    #hosMonthLowValueComsumeDiv .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #hosMonthLowValueComsumeDiv .el-checkbox-group{
        vertical-align: middle;
    }
    #hosMonthLowValueComsumeDiv .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #hosMonthLowValueComsumeDiv .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #hosMonthLowValueComsumeDiv .deptGoods .el-row{
        height: 36px;
    }
    
    #hosMonthLowValueComsumeDiv .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
    

</style>