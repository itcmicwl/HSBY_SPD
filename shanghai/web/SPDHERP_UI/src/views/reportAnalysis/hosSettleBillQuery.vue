<!--医院产品收货信息汇总查询-->
<template>
    <el-tabs type="border-card"  style="padding-bottom: 0px; height: 94%;">
        <el-tab-pane label="直销结算">
            <div  style="height: 100%;" id="hosSettleDiv" @keyup.enter="getHosSettleForZX(1)">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 90%;">
                <!--工具条-->
                <div style="padding-bottom: 1px;">
                    <el-form :inline="true" :model="filters" label-width="100px" ref="goodsName">
                        <el-form-item label="结算日期范围">
                            <el-date-picker v-model="filters.sDate" type="daterange" align="right" 
                            :unlink-panels="true" range-separator="至" start-placeholder="开始日期" 
                            @change="selectDateRangChange" :editable="false" :clearable="false"
                            end-placeholder="结束日期" :picker-options="pickerOptions2"  >
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="结算单号">
                            <el-input v-model="filters.billId" :clearable="true" placeholder="结算单号" ></el-input>
                        </el-form-item>
                        <el-button :inline="true"  type='primary' @click="query">查询</el-button>
                        <!--<el-button :inline="true"  type='info' @click="print">打印</el-button>
                        <el-button :inline="true"  type='info' @click="exportExcel">导出</el-button>-->
                        <br/>
                        <el-form-item>
                            <span style="margin-left:15px;margin-top:5px;">
                            <font size=2>{{this.spanMesg}}</font>
                            </span>
                        </el-form-item>
                    </el-form>

                    
                </div>
                <!--列表-->
                <template>
                    <el-table ref="thisTable" :data="rowDatas"  row-key='id' :row-class-name="tableRowClassNameBill"
                            highlight-current-row v-loading="loading"  border fit  class="deptgoods-tableheight">
                        <el-table-column type="index" align="center"  width="50" label="序号"></el-table-column>
                        <el-table-column prop="billId"  header-align="center"  label="结算单号" width="180">
                          <template slot-scope="scope">
                                <el-button type="text" size="mini" @click="showDettail(scope.row)" icon="search" >{{scope.row.billId}}</el-button>
                          </template>
                        </el-table-column>
                        <el-table-column prop="status" align="center" label="配送状态"  width="100">
                            <template slot-scope="scope">
                                {{scope.row.status|fStatus}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="purchaseCompanyName" header-align="center" align="center" show-overflow-tooltip label="请购机构" width="180"></el-table-column> 
                        <el-table-column prop="fillterName" header-align="center" align="center" label="制单人" width="100"></el-table-column>
                        <el-table-column prop="fillDate" align="center" label="制单时间" width="150"></el-table-column>
                     <!--   <el-table-column prop="provName" align="center" label="配送机构" min-width="50"></el-table-column>
                        -->
                        <el-table-column prop="settleCount" header-align="center" align="center" label="已结算" width="60"></el-table-column> 
                        <el-table-column prop="noSettleCount" header-align="center" align="center" label="未结算" width="60">
                            
                        </el-table-column>
                        <el-table-column prop="settleBillDetailCount" align="center" label="结算明细总行" width="100"></el-table-column>
                        <el-table-column prop="invoiceCodes" align="center" label="发票编号" min-width="40"></el-table-column>
                        <!--<el-table-column label="操作" fixed="right" align="center"  width="80">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="showDettail(scope.row)" icon="search" >详情</el-button>
                            </template>
                        </el-table-column>-->
                    </el-table>
                    <!--分业工具栏-->
                    <el-col :span="24" class="toolbar pageBar">
                        <el-pagination @size-change="handleSizeChange"
                                        @current-change="handleCurrentChange" :current-page="page"
                                        :page-sizes="[15, 30, 100, 400]" :page-size="pageSize" :total="total"
                                        layout="total, sizes, prev, pager, next" style="float:right;" small>
                        </el-pagination>
                    </el-col>
                </template>
                <deptMonthGoodsReceiptQueryPrint ref="purPrintD"></deptMonthGoodsReceiptQueryPrint>
            </el-col>

             <!--结算明细详情-->
            <el-dialog title="开票详情" close="onClose" :visible.sync="queryFormVisible" :close-on-click-modal="true" width="75%">
                <el-table :data="settleDetails"  row-key='goodsId'
                                highlight-current-row  border fit 
                                class="deptDetail-tableheight" :row-class-name="tableRowClassName">
                    <el-table-column type="index" align="center"  width="40" label="序号"></el-table-column>
                    <el-table-column prop="hosGoodsName" align="center" header-align="center" show-overflow-tooltip label="名称" min-width="100"></el-table-column>
                    <el-table-column prop="provGoodsErpCode" header-align="center" align="center" label="ERP编码"  width="80"></el-table-column>
                    <el-table-column prop="goodsGg" align="center" header-align="center" show-overflow-tooltip label="规格" min-width="100"></el-table-column>
                    <el-table-column prop="batchCode" header-align="center" align="center" label="批号" show-overflow-tooltip width="80"></el-table-column>
                    <el-table-column prop="saleBillId" align="center" header-align="center"  label="销售单号" ></el-table-column>
                    <el-table-column prop="invoicesCode" align="center" header-align="center"  label="是否开票">
                        <template slot-scope="scope">
                             {{(scope.row.invoicesCode!=''&&scope.row.invoicesCode!='unundefined'&&scope.row.invoicesCode!=null)?'已开票':'未开票'}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="invoicesCode" header-align="center" align="center" label="发票号" show-overflow-tooltip width="80"></el-table-column>
                    <el-table-column prop="invoicesAmount" align="center" header-align="center"  label="发票金额"></el-table-column>
                    <el-table-column prop="invoicesMan" header-align="center" align="center" label="开票人" show-overflow-tooltip width="80"></el-table-column>
                    <el-table-column prop="invoicesDate" header-align="center" align="center" label="开票日期" show-overflow-tooltip width="80">
                        <template slot-scope="scope">
                             {{scope.row.invoicesCode?scope.row.invoicesDate:''}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="invoicesType" header-align="center" align="center" label="发票类型" show-overflow-tooltip width="80"></el-table-column>
                </el-table>
                
            </el-dialog>
            
        </div>
        </el-tab-pane>
        <!--<el-tab-pane label="定向结算">
            <hosSettleBillDXQuery></hosSettleBillDXQuery>
        </el-tab-pane>-->
    </el-tabs>

</template>
<script>
    import deptMonthGoodsReceiptQueryPrint from './components/deptMonthGoodsReceiptQueryPrint';
    import hosSettleBillDXQuery from './hosSettleBillDXQuery';
    
    import $ from 'jquery';
    import '../../common/third/tableExport/tableExport';
    import '../../common/third/tableExport/jquery.base64';
    import moment from 'moment';
    import { DISTRBILL_STATUS } from '../../common/js/constance';
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
                    billId:'',provName:'',inDeptName:''
                },

                total: 0,
                page: 1,
                pageSize: 15,
                loading: false,
                rowDatas: [],
                printDatas:[],
                tableModel:[],

                queryFormVisible:false,
                settleDetails:[],
                spanMesg:'',
                msgData:{},
                msgDataTotal:''
            }
        },
        watch:{
        },
        components: { deptMonthGoodsReceiptQueryPrint ,hosSettleBillDXQuery},
        filters: {
            fStatus: function (value) {
                if (value) {
                    return DISTRBILL_STATUS[value].text;
                } else {
                    return '';
                }
            }
        },
        methods: {
            //获商品收货汇总列表
            getHosSettleForZX: function (pIndex) {
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
                        purchaseCompanyId: this.hosId,
                        billId:this.filters.billId
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosSettleForZX', param).then(res => {
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
                this.getHosSettleForZX(1);
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
                //this.settleDetails = [];
                this.queryFormVisible = true;
                this.getHosSettleDetailForZX(rowData);
            },
            getHosSettleDetailForZX: function (rowData) {
                var param = {
                    orderBy: "",
                    queryObject: {
                        pid:rowData.id
                    },
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosSettleDetailForZX', param).then(res => {
                    if (res.data.code == "0") {
                        this.settleDetails = res.data.data.data;
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
                    page: this.page,
                    rows: this.pageSize
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosSettleForZX', param).then(res => {
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
                this.getHosSettleForZX(1);
                this.initMessge();
               // this.getPrintData();
            },

            /**行样式 */
            tableRowClassName (rowData, index) {
                 return (rowData.row.invoicesCode!=''
                    &&rowData.row.invoicesCode!='undefined'
                    &&rowData.row.invoicesCode!=null)? '' : 'red-row';
            },
            tableRowClassNameBill (rowData, index) {
                 return (rowData.row.noSettleCount>0)? 'red-row':'';
            },
            /**分业处理事件 */
            handleCurrentChange : function(val) {
                this.page = val;
                this.getHosSettleForZX(this.page);
            },
            handleSizeChange:function(val) {
                this.pageSize = val;
                this.getHosSettleForZX(this.page);
            },
           initMessge:function(){
               this.spanMesg = moment(this.filters.sDate[0]).format("YYYY-MM-DD")+'到'+
                             moment(this.filters.sDate[1]).format("YYYY-MM-DD")+'期间';
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
                        purchaseCompanyId: this.hosId
                    }
                };
                this.axios.post('/spdHERPService/reportAnalysis/reportStatistics/getHosSettleForZXMsg', param).then(res => {
                    if (res.data.code == "0") {
                        if(res.data.data.data && res.data.data.data.length>0){
                             this.msgData = res.data.data.data[0];
                             this.spanMesg+="总共提交结算"+this.msgData.settleBillCount+"条，"+
                             '已经结算完成'+(this.msgData.settleBillCount-this.msgData.noSettleCount)+'条，结算总金额为：'+
                             (this.msgData.invoicesAmount?this.msgData.invoicesAmount:0)+'(元)，'+'还有'
                             +this.msgData.noSettleCount+'条未结算完成'
                        }
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
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
                start = new Date((nowDate.getFullYear()-1)+'/'+12+'/26 00:00:00');
            }else{
                start = new Date(nowDate.getFullYear()+'/'+nowDate.getMonth()+'/26 00:00:00');
            }
            var end = new Date();
            end.setTime(end.getTime() + 3600 * 1000 * 24 * (25-thisDay));
            this.filters.sDate = [start, end];
            this.filters.inDeptName = this.deptName;
            this.getHosSettleForZX(1);
            this.initMessge();
            
           // this.getPrintData();
        }
    };

</script>
<style>
    #hosSettleDiv .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #hosSettleDiv .el-checkbox-group{
        vertical-align: middle;
    }
    #hosSettleDiv .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 90px);
    }
    #hosSettleDiv .deptDetail-tableheight {
        width: 100%;
        height:  400px;
    }
    
    #hosSettleDiv .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #hosSettleDiv .deptGoods .el-row{
        height: 36px;
    }
    
    #hosSettleDiv .pageBar{
        border-bottom: 1px solid #d1dbe5;
        border-left: 1px solid #d1dbe5;
        border-right: 1px solid #d1dbe5;
    }
    #hosSettleDiv .red-row {
         color: #ff2020;
    }

</style>
