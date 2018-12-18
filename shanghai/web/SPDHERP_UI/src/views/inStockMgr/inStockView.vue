<template>
    <!--<el-col :span="24">-->
    <el-row id="in-stock-view" :gutter="10" style="height:100%">
        <el-col :span="24" id="inStockPrintView">
            <el-col :span="18" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="120px" v-loading="loading">
                    <el-col :span="8">
                        <el-form-item label="入库单号">
                            <span>{{ inStockBill.billId }}</span>
                        </el-form-item>
                    </el-col>
                     <el-col :span="8">
                        <el-form-item label="出库单号">
                            <span>{{ inStockBill.outBillId }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="当前状态">
                            <span>{{ inStockBill.status | status }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="入库类型">
                            <span>{{ inStockBill.inStockKind | inStockKind }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="出库机构">
                            <span>{{ inStockBill.outOrgName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="出库部门">
                            <span>{{ inStockBill.outDeptName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="入库机构:">
                            <span>{{ inStockBill.inOrgName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="入库部门:">
                            <span>{{ inStockBill.inDeptName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="入库库房:">
                            <span>{{ inStockBill.inStocName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="制单时间">
                            <span>{{ inStockBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="制单人">
                            <span>{{ inStockBill.fillterName }}</span>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-col>
            <el-col :span="6">
                <svg id="barcode"></svg>
                <div id="erbarcode"></div>
            </el-col>
            <!--列表-->
            <template>
                <el-table :data="inStockBill.lstDetail" style="width: 100%;" fit border  class ='inView-tableheight' stripe>                   
                    <el-table-column label="序号" prop="inBillRow" width="60">
                    </el-table-column>
                    <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column label="规格" prop="goodsGg">
                    </el-table-column>                   
                    <el-table-column label="入库数量" prop="inQty" width="90">                        
                    </el-table-column>
                    <el-table-column label="单位" prop="unit">
                    </el-table-column>
                    <el-table-column label="货位" prop="shelfId">                       
                    </el-table-column>
                    <el-table-column label="批号" prop="batchCode">
                    </el-table-column>
                    <el-table-column label="灭菌批号" prop="sterilizationCode">
                    </el-table-column>
                    <!-- <el-table-column label="灭菌有效日期" width="130">
                        <template slot-scope="scope">
                            {{scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column> -->
                    <el-table-column label="灭菌日期" width="130">
                        <template slot-scope="scope">
                            {{scope.row.sterilizationDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column>
                    <el-table-column label="有效期" width="130">
                        <template slot-scope="scope">
                            {{scope.row.expdtEndDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column>
                    <el-table-column label="产地" prop="made" width="90">
                    </el-table-column>
                </el-table>
            </template>
        </el-col>
        <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;text-align: center;">
            <el-button :plain="true" type="danger"  @click="goBack">返回</el-button>
            <el-button type="info"  @click="autid(1)" v-if="inStockBill.status == 20">审核</el-button>
            <el-button type="danger"  @click="autid(0)" v-if="inStockBill.status == 30">取消审核</el-button>
            <el-button type="warning"  @click="chargeBill" v-if="inStockBill.status == 30">入库</el-button>
            <el-button type="info"  @click="print">打印</el-button>
        </el-col>
        <inStockViewPrint ref="purPrintD" :pBillId = "inStockBill.billId" ></inStockViewPrint>
    </el-row>
    <!--</el-col>-->
</template>
<script>
import moment from 'moment';
import { INSTOCK_KIND,INSTOCK_STATUS } from './common/constance';
import $ from 'jquery';
import inStockViewPrint from './inStockViewPrint'
import '../../common/third/printThis/printThis';
import '../../common/third/qrcode/jquery.qrcode.min';
import JsBarcode from 'jsbarcode';
export default {
    data() {
        return {
            loading: false,
            distrBillList: [
            ],
            inStockBill: {
                id: '',
                billId: '',
                inStockKind: 1,
                outBillId: '',
                outOrgId: '',
                outOrgName: '',
                inOrgId: '',
                inOrgName: '',
                inDeptId: '',
                inDeptName: '',
                sendBillid: '',
                filler: '',
                fillterName: '',
                fillDate: '',
                outDeptId: '',
                outDeptName: '',
                auditor: '',
                auditDate: '',
                accounter: '',
                accountDate: '',
                status: 0,
                lstDetail: []
            },
            confirmVisible: false
        }
    },
    components: { inStockViewPrint },
    filters: {
        status: function (value) {
            if (value) {
                return INSTOCK_STATUS[value].text;
            } else {
                return 0;
            }
        },
        inStockKind: val => {
            return INSTOCK_KIND[val].text;
        }
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        getReceBill(billId) {
            this.loading = true;
            var _this = this;
            this.axios.get('/spdHERPService/stockMgr/inStock/getById?id=' + billId).then(res=>{
                _this.loading = false;
                if(res.data.code == 0){
                    _this.inStockBill = res.data.data;
                }
            }, err => {
                _this.loading = false;
            });
        },
        autid(flag){   //审核直接记账
            var _this = this;
            var serviceUrl = flag?'/spdHERPService/stockMgr/inStock/audit':'/spdHERPService/stockMgr/inStock/unAudit';
            var param={
                id:this.inStockBill.id,
                version:this.inStockBill.version,
                auditor:this.user.userId,
                billId:this.inStockBill.billId,
                accounter:this.user.userId

            }
             this.axios.post(serviceUrl,param).then(res=>{
                _this.loading = false;
                if(res.data.code == 200){
                    this.$message({ message: "操作成功", type: "success" });
                    _this.init(res.data.data);
                }else{
                    this.$message({ message: "操作失败", type: "error" });
                }
            }, err => {
                _this.loading = false;
            });
        },
        chargeBill(){
            var _this=this;
             var serviceUrl ='/spdHERPService/stockMgr/inStock/charge';
            var param={
                billId:this.inStockBill.billId,
                id : this.inStockBill.id,
                version:this.inStockBill.version,
                accounter:this.user.userId
            }
             this.axios.post(serviceUrl,param).then(res=>{
                _this.loading = false;
                if(res.data.code == 200){
                    _this.$message({ message: "操作成功", type: "success" });
                    _this.init(_this.inStockBill.billId);
                }else{
                    _this.$message({ message: "操作失败", type: "error" });
                }
            }, err => {
                _this.loading = false;
            });
        },
        print() {
            /*$("#inStockPrintView").printThis({
                debug: false,
                importCSS: true,
                importStyle: true,
                printContainer: true,
                loadCSS: './static/style/print.css',
                pageTitle: "入库单",
                removeInline: false,
                printDelay: 333,
                header: null,
                formValues: true
            });*/
            this.$refs.purPrintD.print(this.inStockBill);
        },
        init(billId){
            if (billId) {
            JsBarcode("#barcode", billId, { height: 50, displayValue: false });
            this.getReceBill(billId);
            // this.$nextTick(function () {
            //     $("#erbarcode").qrcode({ width: 100, height: 100, text: billId });
            // });
        }
        }
    },
    mounted() {
        var billId = this.$route.query.billId;
        this.init(billId);
    }
};

</script>

<style>
#in-stock-view .fa {
    margin-right: 5px;
}

#in-stock-view .el-table {
    font-size: 12px;
}

#in-stock-view fieldset {
    border-width: 1px;
    border-style: solid;
    border-color: #1fa0ff;
    border-image: initial;
}

#in-stock-view .el-dialog--small {
    width: 70%;
}
#in-stock-view .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}
#in-stock-view .inView-tableheight{
    height:  300px;
}
</style>