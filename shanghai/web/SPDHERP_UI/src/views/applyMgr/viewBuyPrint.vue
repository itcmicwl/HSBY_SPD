<template>
    <el-row :gutter="10" style="height:95%" id="applyBuyPrintContent"  class="printArea" v-show='false'>
        <el-row>
            <el-col :span="24" style="padding-bottom:10px" align="center">
                <span  style="font-size:20px"><b>请购单</b></span><br>
            </el-col> 
            <el-col :span="8">
                <span class="title">请购单号:</span>
                <span>{{ buyBill.billId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">请购科室:</span>
                <span>{{ buyBill.buyDeptName }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">目标科室:</span>
                <span>{{ buyBill.sourceWarehouseName }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">请购笔数:</span>
                <span>{{ buyBill.sumRow }}</span>
            </el-col>
            <!-- <el-col :span="8">
                <span class="title">总金额:</span>
                <span>{{ buyBill.totalJe }}</span>
            </el-col> -->
            <el-col :span="8">
                <span class="title">制单时间:</span>
                <span>{{ buyBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">制单人:</span>
                <span>{{ buyBill.fillterName }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">科室审核时间:</span>
                <span v-if="buyBill.deptDealTime">{{ buyBill.deptDealTime | dateFormat('YYYY-MM-DD')}}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">科室审核人:</span>
                <span>{{ buyBill.deptDealManName }}</span>
            </el-col>
            <el-col :span="24">
                <svg id="pbarcode"></svg>
            </el-col> 
        </el-row>

        <el-row>
            <table class="gridtable">
                <tr>
                    <th width=40>序号</th>
                    <th width=60>状态</th>
                    <th width=350 v-if="mainBillStatus>10">名称</th>
                    <th width=500 v-else>名称</th>
                    <th width=200>规格</th>
                    <!-- <th width=80 v-if="mainBillStatus>=20">JDE编码</th> -->
                    <th width=80>JDE编码</th>
                    <!-- <th v-if="mainBillStatus>10">单价</th> -->
                    <th width=60 v-if="mainBillStatus>10">请购数量</th>
                    <!-- <th v-if="mainBillStatus>10">金额</th> -->
                </tr>
                <tr v-for="(item ,index) in buyBill.subVoList" :key="item.id">
                    <td align="center"> {{index+1}}</td>
                    <td align="center">{{item.subState | fStatus}}</td>
                    <td>{{item.goodsName}}</td>
                    <td>{{item.goodsGg}}</td>
                    <!-- <td v-if="mainBillStatus>=20">{{item.goodsErpCode}}</td> -->
                    <td>{{item.goodsErpCode}}</td>
                    <!-- <td align="center" v-if="mainBillStatus>10">{{item.price}}</td> -->
                    <td align="center" v-if="mainBillStatus>10">{{item.qty}}</td>
                    <!-- <td align="center" v-if="mainBillStatus>10">{{item.je}}</td> -->
                </tr>
            </table>
        </el-row>

        <el-row class="toolbar" style="padding-bottom: 0px;margin-top:5px;">
            <el-col :span="12">
                <span class="title">收货人:</span>
                <span>{{ buyBill.recLinkman }}</span>
            </el-col>

            <el-col :span="12">
                <span class="title">收货人电话:</span>
                <span>{{ buyBill.recLinkmanPhone }}</span>
            </el-col>
            <el-col :span="24">
                <span class="title">收货地址:</span>
                <span>{{ buyBill.recAddress }}</span>
            </el-col>
        </el-row>
    </el-row>
</template>
<script>

import { DEPTBUY_MAIN_STATUS } from './common/constance';
import { DEPTBUY_SUB_STATUS } from './common/constance';
import { DEPTBUY_MAIN_DEPTAUDIT_STATUS } from './common/constance';
import $ from 'jquery';
import '../../common/third/printThis/printThis';
import '../../common/third/qrcode/jquery.qrcode.min';
import JsBarcode from 'jsbarcode';
export default {
    data() {
        return {
            mainBillStatus:0,
            buyBill: {},
            // buyBill: {
            //     id: '', billId: '', fillDate: '', returnCompanyId: '', returnCompanyName: '',
            //     returnOrgId: '', returnOrgCode: '', returnOrgName: '',
            //     outOrgId: '', outOrgName: '', outOrgCode: '',
            //     provId: '', provName: '',
            //     fillter: '', memo: '', isStartInvoice: '', sumRow: '', status: '',
            //     subVoList: []
            // },
          
            // test: 0,
        }
    },
   filters: {
        fStatus: function (value) {
            if (value) {
                return DEPTBUY_SUB_STATUS[value].text;
            } else {
                return '';
            }
        }
    },
    // watch:{
    //     pBillId:function(newVal,oldVal){
    //         this.getBillDetails(newVal);
    //     }
    // },
    props:{
        pBillId:String,
        pBuyBill:Object
    },
    methods: {
        print(status){
            this.buyBill = JSON.parse(JSON.stringify(this.pBuyBill))
            JsBarcode("#pbarcode",this.pBillId, { height: 30, displayValue: false });
            this.mainBillStatus = status;
            $("#applyBuyPrintContent").printThis({
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
        // getBillDetails(billId) {
        //     let params ={billId:billId};
        //     this.axios.post('/spdHERPService/applyMgr/deptBuyMain/getBillDetails/' , params).then(res => {
        //         if (res.data.code == "0") {
        //             this.buyBill = res.data.data;
        //             this.test = DEPTBUY_MAIN_STATUS[this.buyBill.state].index;
        //         }
        //     }, err => {
        //     });
        // },
        
    },
    mounted() {
        // var billId = this.$route.query.billId;
        // if (billId) {
        //     this.getBillDetails(billId);

        // }
    }
};

</script>

<style>
#applyBuyPrintContent{
    padding: 10px 10px 10px 20px;
}
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
    width:'95%'
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