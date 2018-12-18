<template>
    <el-row :gutter="10" style="height:95%" id="purconfirmViewPrint"  class="printArea" v-show="fasle">
        <el-row>
            <el-col :span="24" style="padding-bottom:10px" align="center">
                <span style="font-size:20px;"><b>确认单</b></span><br>
            </el-col> 
            <el-col :span="8">
                <span class="title">确认单号:</span>
                <span>{{ purconfirm.billId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">采购单号:</span>
                <span>{{ purconfirm.purBillId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">制单人:</span>
                <span>{{ purconfirm.fillterName }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">笔数:</span>
                <span>{{ purconfirm.sumRow }}</span>
            </el-col>
            
             <el-col :span="8">
                <span class="title">总金额:</span>
                <span>{{ purconfirm.sumAmount }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">不含税金额:</span>
                <span>{{ purconfirm.sumTaxAmount }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">制单时间:</span>
                <span>{{ purconfirm.fillDate | dateFormat('YYYY-MM-DD') }}</span>
            </el-col>
            <el-col :span="24">
                <span class="title">备注:</span>
                <span>{{ purconfirm.remark }}</span>
            </el-col>
        </el-row>
            
        <el-row>
            <table class="gridtable">
                <tr>
                    <th width=30>序号</th>
                    <th width=50>请购科室</th>
                    <th width=150>产品名称</th>
                    <th>请购单价</th>
                    <th>采购数量</th>
                    <th>订单数量</th>
                    <th>配送数量</th>
                    <th>单位</th>
                    <th>含税金额(元)</th>
                    <th>不含税金额(元)</th>
                    <th>科室请购日期</th>
                    <th>预计到货日期</th>
                </tr>
                <tr v-for="(item ,index) in purconfirm.lstDetail" :key="item.id">
                    <td align="center"> {{index+1}}</td>
                    <td>{{item.applyDeptName}}</td>
                    <td>{{item.hosGoodsName}}</td>
                    <td>{{item.provGoodsErpPrice}}</td>
                    <td>{{item.purQty}}</td>
                    <td>{{item.qty}}</td>
                    <td>{{item.sendQty}}</td>
                    <td>{{item.hosUnit}}</td>
                    <td>{{item.qrAmount}}</td>
                    <td>{{item.qrTaxAmount}}</td>
                    <td>{{item.applyDate | dateFormat('YYYY-MM-DD')}}</td>
                    <td>{{item.esArrivalDate | dateFormat('YYYY-MM-DD')}}</td>
                </tr>
            </table>
        </el-row>

        <el-row class="toolbar" style="padding-bottom: 0px;margin-top:5px;">
            <el-col :span="8">
                <span class="title">请购机构:</span>
                <span>{{ purconfirm.purchaseCompanyName }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">请购科室:</span>
                <span>{{ purconfirm.applyOrgName }}</span>
            </el-col>

            <el-col :span="8">
                <span class="title">收货科室:</span>
                <span>{{ purconfirm.recieveOrgName }}</span>
            </el-col>
        </el-row>
    </el-row>
</template>
<script>

import $ from 'jquery';
import '@/common/third/printThis/printThis';
import '@/common/third/qrcode/jquery.qrcode.min';
import JsBarcode from 'jsbarcode';
export default {
    data() {
        return {
           purconfirm: {
                id: '',
                billId: '',
                spdCode: '',
                purBillId: '',
                purchaseCompanyId: '',
                purchaseCompanyCode: '',
                purchaseCompanyName: '',
                purchaseCompanyErpCode: '',
                applyOrgId: '',
                applyOrgCode: '',
                applyOrgName: '',
                applyOrgScmCode: '',
                applyOrgErpCode: '',
                recieveOrgId: '',
                recieveOrgCode: '',
                recieveOrgName: '',
                recieveOrgScmCode: '',
                recieveOrgErpCode: '',
                provId: '',
                provCode: '',
                provName: '',
                provErpCode: '',
                provOrgCode: '',
                provSystemId: '',
                deliveryRelation: '',
                balanceOrgId: '',
                sumAmount: 0,
                sumTaxAmount: 0,
                status: 0,
                fillter: '',
                fillterName: '',
                remark: '',
                sumRow: 0,
                fillDate: '',
                lstDetail: []
            }
        }
    },
   
    methods: {
        print(purconfirmBillId){
            if(purconfirmBillId){
                this.getPurconfirmInfo(purconfirmBillId);
            }
            $("#purconfirmViewPrint").printThis({
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
        getPurconfirmInfo(billId) {
            var _this = this;
            this.axios.get('/spdHDIService/orderMgr/purconfirm/getByBillid/' + billId).then(res => {
                _this.loading = false;
                if (res.data.code == "0") {
                    _this.purconfirm = res.data.data;
                }
            }, err => {
                _this.loading = false;
            });
        },
    },
    mounted() {
        
    }
};

</script>

<style>
#purconfirmViewPrint{
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