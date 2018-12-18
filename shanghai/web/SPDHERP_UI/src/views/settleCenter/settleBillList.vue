<template>
    <el-tabs id="settle-bill-list" type="border-card" v-model="showTab" style="padding-bottom: 0px; height: 94%;">
        <el-tab-pane label="我要结算" name="wantSettle">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form :inline="true" label-width="70px">
                    <el-col :span="8">
                        <el-form-item label="集配商:">
                            <el-select v-model="filters.colId" filterable placeholder="请选择"  @change="provChange">
                                <el-option v-for="item in colProvLst" :key="item.id" :label="item.cname" :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="入库日期:">
                            <el-date-picker v-model="filters.startDate" type="daterange" align="right" 
                            :editable="false" :clearable="false" @change="selectDateRangChange" unlink-panels
                             placeholder="选择日期范围" :picker-options="pickerOptions2" >
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item>
                            <el-button type="primary" icon="search" style="width:70px"  v-on:click="getInBillList(1)" >查询</el-button>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-col>
            <!--列表-->
            <el-table :data="outBillDetailList" highlight-current-row v-loading="loading"   stripe border 
                 style="width: 100%;" fit class="table_2_content" row-key="id" @selection-change="sltRowsChange">
                <el-table-column type="selection"  width="55" reserve-selection></el-table-column>
                <el-table-column prop="billId" label="编号" width=150 ></el-table-column>
                <el-table-column prop="goodsName" label="物资名称" show-overflow-tooltip width=180></el-table-column>
                <el-table-column prop="outQty" label="数量"  width=50 align='center'></el-table-column>
                <el-table-column prop="price" label="单价"  width=80 align='center'></el-table-column>
                <!--<el-table-column prop="outQty" label="结算金额"  width=80></el-table-column>  -->              
                <el-table-column prop="unit" label="单位" align='center' width=80 show-overflow-tooltip></el-table-column>
                <el-table-column prop="goodsGg" label="规格"  show-overflow-tooltip width=80></el-table-column>
                <el-table-column prop="provName" label="供应商" show-overflow-tooltip width=150></el-table-column>
                <el-table-column prop="made" label="产地" align='center' width=60 ></el-table-column>
                <el-table-column prop="mfrsName" label="生产厂商"  show-overflow-tooltip ></el-table-column>                            
            </el-table>
            <!--工具条-->
            <el-col :span="24" class="toolbar pageBar">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[50, 100, 200, 500]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                </el-pagination>
            </el-col>  
            <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
            <div style="text-align: center;">
                <el-button  type="info"  @click="createSettleBill" icon="print">生成结算单</el-button>                    
            </div>
            </el-col>              
        </el-tab-pane>
        <el-tab-pane label="结算单列表" name="settleList" style="height:100%">
            <el-col :span="24" style="height:100%">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters">
                        <el-form-item label="入库单号">
                            <el-input v-model="sFilters.billId" placeholder="单号" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="search"  v-on:click="getSettleBillList(1)" >查询</el-button>
                            <el-button @click="resetForm" >重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
                <!--列表-->
                <el-table :data="settleBillList" highlight-current-row v-loading="sLoading" style="width: 100%;" fit class="table_content">
                    <el-table-column prop="billId" label="结算单号" sortable width="150">
                    </el-table-column>
                    <el-table-column prop="status" label="状态" :formatter="statusFormat">
                    </el-table-column>
                    <el-table-column prop="hosName" label="付款单位" sortable>
                    </el-table-column>                           
                    <el-table-column prop="provName" label="结算单位" sortable>
                    </el-table-column>
                    <el-table-column prop="provDeptErpCode" label="结算部门" sortable>
                    </el-table-column> 
                    <el-table-column prop="sumRow" label="笔数" sortable>
                    </el-table-column> 
                    <el-table-column prop="fillterName" label="制单人" sortable>
                    </el-table-column>
                    <el-table-column prop="fillDate" label="制单时间">
                        <template slot-scope="scope">
                            {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="150">
                        <template slot-scope="scope">
                            <el-button  @click="showBill(scope.row.billId)" type="text">查看</el-button>
                            <!--<el-button  @click="editBill(scope.row.id)" type="text" v-if="scope.row.status==10">编辑</el-button>-->
                        </template>
                    </el-table-column>
                </el-table>
                <!--分页条-->
                <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
                </el-pagination>
            </el-col>
        </el-tab-pane>
    </el-tabs>
</template>
<script>
import colAndProvs from '../../components/colAndProvs';
import { SETTLE_STATUS,OUTSTOCK_STATUS } from '../../common/js/constance';
import { mapActions } from 'vuex';
export default {
    data() {
        return {
            sFilters: {
                billId: '',                
                hosId:this.user.corpId
            },
            showTab:'wantSettle',
            sTotal: 0,
            sPage: 1,
            sPageSize: 30,
            sLoading: false,
            settleBillList: [
            ],

            filters: {
                colId:'',
                hosId:this.user.corpId,
                provId:'',
                startDate:'',
                endDate:''
                
            },
            total: 0,
            page: 1,
            pageSize: 50,
            loading: false,
            outBillDetailList: [
            ],
            colProvLst:[],
            sltProv:{},
            sltRows:[],
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
        }  
        }
    },
    filters: {
        fStatus: function (value) {
            if (value) {
                return OUTSTOCK_STATUS[value].text;
            } else {
                return '';
            }
        },
        SStatus:function(value){
            if (value) {
                return SETTLE_STATUS[value].text;
            } else {
                return '';
            }
        }
    },   
    components: { colAndProvs}, 
    methods: {
        ...mapActions([
            'setSettleBill'
        ]),
        //获取列表
        getSettleBillList: function (pIndex) {
            this.sPage = pIndex;
            var param = {
                orderBy: "",
                page: this.sPage,
                rows: this.sPageSize,
                queryObject: {
                    billId: this.sFilters.billId,
                    hosId:this.sFilters.hosId ,
                     settleType:20 //入库结算                   
                }
            };
            this.sLoading = true;
            this.axios.post('/spdHERPService/settleCenter/hosOutBalance/listByPage', param).then(res => {
                this.sLoading = false;
                if(res.data.code == 0 && res.data.data.data.length > 0){
                    this.settleBillList = res.data.data.data;
                    this.sTotal = res.data.data.sTotal;
                }
            }, err => {
                this.sLoading = false;
            });
        },
        sCurrentChange(val) {
            this.sPage = val;
            this.getSettleBillList(this.sPage);
        },
        sSizeChange(val) {
            this.sPageSize = val;
            this.getSettleBillList(this.sPage);
        },      
        showBill(billId) {
            this.$router.push({ path: 'settleBillView', query: { billId: billId} });
        },
        editBill(billId){
            this.$router.push({ path: 'settleEdit', query: { settleBillId: billId } });
        },
        resetForm(){
            this.sFilters.billId = "";
        },
        statusFormat(row) {
            return SETTLE_STATUS[row.status].text;
        },
        selectDateRangChange(){
            this.getInBillList(1);
        }, 
        //获取入库单列表
        getInBillList: function (pIndex) {
            this.outBillDetailList = [];
            this.page = pIndex;
            var param = {
                orderBy: "",
                page: this.page,
                rows: this.pageSize,
                queryObject: {
                    hosId: this.filters.hosId,
                    colId: this.filters.colId,
                    provId: this.filters.provId,
                    startDate:this.filters.startDate[0],
                    endDate:this.filters.startDate[1],
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/stockMgr/inStock/inBill4settle', param).then(res => {
                this.loading = false;
                this.outBillDetailList = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getInBillList(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getInBillList(this.page);
        },       
        showPurcDialog(billId) {            
            this.$router.push({ path: 'settleEdit', query: { outBillId: billId } });
        },
        provChange(provId){
            this.sltProv = this.colProvLst.find(item=>item.id == provId);
            this.getInBillList(1);
        },
        getColProvLst(){           
            this.loading = true;
            this.axios.post('/spdHERPService/provMgr/distriRelation/getCollectorList/' + this.filters.hosId).then(res => {
                this.loading = false;
                this.colProvLst = res.data.data;
                this.filters.colId=res.data.data[0].id;
                this.sltProv = res.data.data[0];
                this.getInBillList(1);
            }, err => {
                this.loading = false;
            });
        },
        sltRowsChange(rows){
            this.sltRows = rows;
        },
        createSettleBill(){
            if(!Array.isArray(this.sltRows) || this.sltRows.length == 0){
                this.$message.error('请至少选择一条记录。');
                return;
            }
            var settleBill = {
                hosId: this.user.corpId,hosName:this.user.corpName,hosErpCode:this.user.corpCode,
                provId:this.sltProv.id,provName:this.sltProv.cname,provErpCode:this.sltProv.erpCode,
                provDeptErpCode:'',filler:this.user.userId,fillterName:this.user.ename,fillDate:new Date(),
                remark:'',sumRow:'',status:10,lstDetail:[],invoiceNumber:'',invoiceCode:'',
                settleAmount:0,invoiceAmount:0,settleType:20//入库结算
            };
            this.sltRows.forEach((item,index)=>{
                settleBill.hosErpCode = item.hosErpCode;
                var inStockDetail = {
                    id:'',pid:'',billId:'',rowNum:index + 1,provId:item.provId,provName:item.provName,provErpCode:item.provCode,outDeptId:item.outDeptId,
                    outDeptName:item.outDeptName,outDeptErpCode:item.outDeptErpCode,outBillid:item.billId,outRowNum:item.outBillRow,outDate:'',hosGoodsId:item.goodsId,
                    hosGoodsName:item.goodsName,goodsGg:item.goodsGg,mfrsId:item.mfrsId,mfrsName:item.mfrsName,made:item.made,packetCode:item.packetCode,provGoodsId:item.provGoodsId,provGoodsName:item.provGoodsName,
                    batchId:'',batchCode:item.batchCode,sterilizationCode:item.sterilizationCode,expdtEndDate:item.expdtEndDate,produceDatetime:'',hosUnit:item.unit,
                    hosUnitQty:item.outQty,provUnit:item.provUnit,hosScale:item.hosScale,provUnitQty:item.outQty * item.hosScale,status:10,outKind:item.outKing,invoiceNumber:'',invoiceCode:'',
                    sterilizationDate:item.sterilizationDate,sterilizationEndDate:item.sterilizationEndDate,saleMan:item.saleMan,price:item.price,settleAmount:item.settleAmount,invoiceAmount:0
                };
                settleBill.lstDetail.push(inStockDetail);
            });
            
            settleBill.sumRow = settleBill.lstDetail.length;
            this.setSettleBill(settleBill);
            this.$router.push({ path: 'settleEdit'});
        }
    },
    mounted() {
        this.sFilters.billId = this.$route.query.inStockBillId;
        this.sFilters.purchaseCompanyId = this.user.corpId;  
        var end = new Date();
        var start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
        this.filters.startDate = [start, end];
        this.getSettleBillList(1);    
        this.getColProvLst();   
    }
}
</script>
<style>
#settle-bill-list .table_content{
    font-size: 12px;
    height: calc(100% - 80px);
    overflow-y: auto;
}
#settle-bill-list .table_2_content{
    font-size: 12px;
    height: calc(100% - 120px);
    overflow-y: auto;
}
#settle-bill-list .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#settle-bill-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
#settle-bill-list .pageBar{
    border-bottom: 1px solid #d1dbe5;
    border-left: 1px solid #d1dbe5;
    border-right: 1px solid #d1dbe5;
}
</style>

