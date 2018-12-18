<!--医院产品收货信息汇总查询-->
<template>
    <div  style="height: 100%;" id="deptMonthGoodsReceiptQueryDiv" @keyup.enter="getHosMonthGoodsReceiptQuery(1)">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <!--工具条-->
            <div style="padding-bottom: 1px;">
                <el-form :inline="true" :model="filters" label-width="100px" ref="goodsName">
                    <el-form-item label="收货日期范围：">
                        <el-date-picker v-model="filters.sDate" type="daterange" align="right" 
                         :unlink-panels="true" range-separator="至" start-placeholder="开始日期" 
                         @change="selectDateRangChange" :editable="false" :clearable="false"
                         end-placeholder="结束日期" :picker-options="pickerOptions2"  >
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="商品名/规格：">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入商品名或规格" ></el-input>
                    </el-form-item>
                    <el-button :inline="true"  type='primary' @click="query">查询</el-button>
                    <el-button :inline="true"  type='info' @click="print">打印</el-button>
                    <el-button :inline="true"  type='info' @click="exportExcel">导出</el-button>
                </el-form>
            </div>
            <!--列表-->
            <template>
                <el-table ref="thisTable" :data="rowDatas"  row-key='id'
                            highlight-current-row v-loading="loading"  border fit 
                            class="deptgoods-tableheight" :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                    <el-table-column prop="goodsName"  header-align="center"  label="商品名称" min-width="40"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号"  width="230"></el-table-column>
                    <el-table-column prop="unit" header-align="center" align="center" label="计量单位" width="60"></el-table-column> 
                    <el-table-column prop="made" header-align="center" align="center" label="产地" width="100"></el-table-column>
                    <el-table-column prop="receiptCount" align="center" label="收货数量汇总" width="100"></el-table-column>
                    <!--<el-table-column prop="erpCode" header-align="center" align="center" label="ERP编码" width="70"></el-table-column>
                    <el-table-column prop="inPrice" align="center" label="单价" width="50"></el-table-column>
                    <el-table-column prop="totalAmount" align="center" label="总金额" width="70"></el-table-column>
                    -->
                    <el-table-column prop="provName" align="center" label="供应商" width="200"></el-table-column>
                    <!--<el-table-column label="操作"  align="center"  width="100">
                        <template slot-scope="scope">
                            <el-button type="primary" size="mini" @click="showDettail(scope.row)" icon="search" >详情</el-button>
                        </template>
                    </el-table-column>-->
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
            <deptMonthGoodsReceiptQueryPrint ref="purPrintD"></deptMonthGoodsReceiptQueryPrint>
        </el-col>

        <!--部门收货汇总详情弹窗-->
        <el-dialog title="各科室收货汇总" close="onClose" :visible.sync="queryFormVisible" :close-on-click-modal="false" width="20%" size='medium'>
            <el-table :data="deptRowDatas"  row-key='goodsId'
                            highlight-current-row  border fit 
                            class="deptDetail-tableheight" :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                    <el-table-column prop="inDeptName" align="center" header-align="center"  label="收货科室" min-width="150"></el-table-column>
                    <el-table-column prop="deptReceiptCount" header-align="center" align="center" label="收货数量"  width="200"></el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer" style="text-align: right;">
                <el-button @click.native="queryFormVisible = false" type="primary">取消</el-button>
            </div>
        </el-dialog>
    </div>

</template>
<script>
    import deptMonthGoodsReceiptQueryPrint from './components/deptMonthGoodsReceiptQueryPrint';
    import orgTreeSlt from '../../components/orgTreeSlt';
    import $ from 'jquery';
    import '../../common/third/tableExport/tableExport';
    import '../../common/third/tableExport/jquery.base64';
    import moment from 'moment';
    export default {
        data() {
            return {
                hosId:'',
                deptId:'',
                hosName:'',
                deptName:'',
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
                /**过滤条件 */
                filters: { 
                    sDate:[],
                    inDeptId:'',provName:'',inDeptName:'',goodsName:''
                },

                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                rowDatas: [],
                printDatas:[],
                tableModel:[],

                queryFormVisible:false,
                deptRowDatas:[],
            }
        },
        watch:{
        },
        components: { deptMonthGoodsReceiptQueryPrint,orgTreeSlt },
        methods: {
            //获商品收货汇总列表
            getHosMonthGoodsReceiptQuery: function (pIndex) {
                this.page = pIndex;
                let eDate = null;
                if(this.filters.sDate){
                    //eDate = new Date(this.filters.sDate[1]);
                    eDate = moment(moment(this.filters.sDate[1]).add(1,'days').format('YYYY-MM-DD')).subtract(1,'seconds').format('YYYY-MM-DD HH:mm:ss')
                }
                var param = {
                    orderBy: "",
                    queryObject: {
                        sDate: this.filters.sDate?moment(this.filters.sDate[0]).format("YYYY-MM-DD"):null,
                        eDate: eDate?eDate:null,
                        hosId: this.hosId,
                      //  inDeptId : this.deptId,
                        deptIds : [this.deptId],
                        goodsName : this.filters.goodsName
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthGoodsReceiptQuery', param).then(res => {
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
            query : function (){
                this.getHosMonthGoodsReceiptQuery(1);
            },
            exportExcel:function(){
               let tableHtml = '<table><thead><tr><th>序号</th>'
                +'<th>商品名称</th><th>规格型号</th><th>计量单位</th>'
                +'<th>产地</th><th>收货数量</th></tr></thead><tbody>';
               if(this.printDatas.length>0){
                    //this.$refs.purPrintD.print(res.data.data.data,this.filters);
                    this.printDatas.forEach(function(item,index){
                        tableHtml+='<tr>'
                        +'<td>'+(index+1)+'</td>'
                        +'<td>'+item.goodsName+'</td>'
                        +'<td>'+item.goodsGg+'</td>'
                        +'<td>'+item.unit+'</td>'
                        +'<td>'+item.made+'</td>'
                        +'<td>'+item.receiptCount+'</td>'
                        +'</tr>'
                    });
                    tableHtml+='</tbody></table>';
                    $(tableHtml).tableExport({type:'excel',
                        escape:'false',
                        tableName:this.deptName+'收货汇总'
                    });
                }else{
                    this.$message.error('无数据导出！');
                    return false;
               }
            },
            /**查看详情 */
            showDettail:function(rowData){
                //this.deptRowDatas = [];
                this.queryFormVisible = true;
                this.getHosMonthGoodsReceiptDetail(rowData);
            },
            getHosMonthGoodsReceiptDetail: function (rowData) {
                var param = {
                    orderBy: "",
                    queryObject: {
                        sDate: this.filters.sDate?this.filters.sDate[0]:null,
                        eDate: this.filters.sDate?new Date(this.filters.sDate[1].setDate(this.filters.sDate[1].getDate()+1)):null,
                        goodsId:rowData.goodsId,
                        hosId:this.hosId
                    },
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthGoodsReceiptDetail', param).then(res => {
                    if (res.data.code == "0") {
                        this.deptRowDatas = res.data.data.data;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                });
            },
            print() {
                if(this.printDatas.length>0){
                    this.$refs.purPrintD.print(this.printDatas,this.filters);
                }else{
                    this.$message.error('无数据打印！');
                    return false;
                }
            },
            /**获取打印，以及导出的数据 */
            getPrintData : function(){
                let eDate = null;
                if(this.filters.sDate){
                    //eDate = new Date(this.filters.sDate[1]);
                    eDate = moment(moment(this.filters.sDate[1]).add(1,'days').format('YYYY-MM-DD')).subtract(1,'seconds').format('YYYY-MM-DD HH:mm:ss')
                }
                var param = {
                    orderBy: "",
                    queryObject: {
                        sDate: this.filters.sDate?moment(this.filters.sDate[0]).format("YYYY-MM-DD"):null,
                        eDate: eDate?eDate:null,
                        hosId: this.hosId,
                        //inDeptId : this.deptId,
                        deptIds : [this.deptId],
                        goodsName : this.filters.goodsName
                    },
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosMonthGoodsReceiptQuery', param).then(res => {
                    if (res.data.code == "0") {
                        this.printDatas = res.data.data.data;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                });
            },
            /**日期变化事件 */
            selectDateRangChange:function(){
                this.$refs.thisTable.clearSelection();
                this.getHosMonthGoodsReceiptQuery(1);
                this.getPrintData();
            },
            
            /**行样式 */
            tableRowClassName (row, index) {
                // return (row.monthQty > row.mQtyUpper) ? 'row-disabled-red-nobg' : ''
            },
            /**分业处理事件 */
            handleCurrentChange : function(val) {
                this.page = val;
                this.getHosMonthGoodsReceiptQuery(this.page);
            },
            handleSizeChange:function(val) {
                this.pageSize = val;
                this.getHosMonthGoodsReceiptQuery(this.page);
            },
           
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
            this.filters.inDeptName = this.deptName;
            this.getHosMonthGoodsReceiptQuery(1);
            this.getPrintData();
        }
    };

</script>
<style>
    #deptMonthGoodsReceiptQueryDiv .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #deptMonthGoodsReceiptQueryDiv .el-checkbox-group{
        vertical-align: middle;
    }
    #deptMonthGoodsReceiptQueryDiv .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #deptMonthGoodsReceiptQueryDiv .deptDetail-tableheight {
        width: 100%;
        height:  calc(100% - 2px);
    }
    
    #deptMonthGoodsReceiptQueryDiv .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #deptMonthGoodsReceiptQueryDiv .deptGoods .el-row{
        height: 36px;
    }
    
    #deptMonthGoodsReceiptQueryDiv .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
    

</style>
