<template>
    <el-row :gutter="10" id="billPrintContent" class="printArea" v-show='false'>
        <el-row>
            <el-col :span="24" style="padding-bottom:10px" align="center">
                <span  style="font-size:20px"><b>{{ inStockBill.inStockKind | inStockKind }}单</b></span><br>
            </el-col> 
            <el-col :span="8">
                <span class="title">入库单号:</span>
                <span>{{ inStockBill.billId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">出库单号:</span>
                <span>{{ inStockBill.outBillId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">当前状态:</span>
                <span>{{ inStockBill.status | status }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">入库类型:</span>
                <span>{{ inStockBill.inStockKind | inStockKind }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">入库库房:</span>
                <span>{{ inStockBill.inStocName}}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">制单时间:</span>
                <span>{{ inStockBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">制单人:</span>
                <span>{{ inStockBill.fillterName }}</span>
            </el-col>
            <el-col :span="24">
                <svg id="p_barcode"></svg>
            </el-col> 
        </el-row>
        <el-row>
             <table class="gridtable">
                <tr>
                    <th width=40>序号</th>
                    <th width=250>产品名称</th>
                    <th widht=150>规格</th>
                    <th width=80>配送数量</th>
                    <th width=80>入库数量</th>                    
                    <th width=40>单位</th>
                    <th width=80>货位</th>
                    
                </tr>
                <tr v-for="item in inStockBill.lstDetail" :key="item.id">
                    <td align='center'>{{item.inBillRow}}</td>
                    <td>{{item.goodsName}}</td>
                    <td>{{item.goodsGg}}</td>
                    <td align='center'>{{item.outQty}}</td>
                    <td align='center'>{{item.inQty}}</td>
                    <td align='center'>{{item.unit}}</td>
                    <td align='center'>{{item.shelfId}}</td>
                </tr>
            </table>
        </el-row>

        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-col :span="14">
                <span class="title">入库机构:</span>
                <span>{{ inStockBill.inOrgName }}</span>
            </el-col>
            <el-col :span="10">
                <span class="title">入库部门:</span>
                <span>{{ inStockBill.inDeptName }}</span>
            </el-col>
        </el-row>

        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-col :span="14">
                <span class="title">出库机构:</span>
                <span>{{ inStockBill.outOrgName }}</span>
            </el-col>
            <el-col :span="10">
                <span class="title">出库部门:</span>
                <span>{{ inStockBill.outDeptName }}</span>
            </el-col>
        </el-row>
    </el-row>
</template>
<script>
import moment from 'moment';
import { INSTOCK_KIND,INSTOCK_STATUS } from './common/constance';
import $ from 'jquery';
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
                out_dept_id: '',
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

    watch:{
        pBillId:function(newVal,oldVal){
            this.init(newVal);
        }
    },

    props:{pBillId:String},

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
        
        
        print(inStockBill) {
            this.inStockBill = inStockBill;
            $("#billPrintContent").printThis({
                debug: false,
                importCSS: true,
                importStyle: true,
                printContainer: true,
                loadCSS: './static/style/print.css',
                pageTitle: this.user.corpName,
                removeInline: false,
                printDelay: 333,
                header: null,
                formValues: true
            });
        },
        init(billId){
            if (billId) {
                JsBarcode("#p_barcode", billId, { height: 50, displayValue: false });
                //this.getReceBill(billId);
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
#billPrintContent{
    padding: 10px 10px 10px 20px;
}
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
}
table.gridtable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #dedede;
}
table.gridtable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
}
</style>