<template>
    <el-row :gutter="10" id="printContent" class="printArea" v-show="false">
        <el-row>
            <el-col :span="24">
                <el-col :span="24" style="padding-bottom:10px" align="center">
                    <span  style="font-size:20px"><b>采购单</b></span><br>
                </el-col>
                <el-col :span="8">
                    <span class="title">采购单号:</span>
                    <span class="content">{{ purOrder.billId }}</span>
                </el-col>
                <el-col :span="8">
                    <span class="title">供应商:</span>
                    <span class="content">{{ purOrder.provName }}</span>
                </el-col>
                <el-col :span="8">
                    <span class="title">请购机构:</span>
                    <span>{{ purOrder.purchaseComName }}</span>
                </el-col>
                <el-col :span="8">
                    <span class="title">请购科室:</span>
                    <span>{{ purOrder.purOrgCname }}</span>
                </el-col>
                <el-col :span="8">
                    <span class="title">收货科室:</span>
                    <span>{{ purOrder.recOrgName }}</span>
                </el-col>                      
                
                <el-col :span="8">
                    <span class="title">制单日期:</span>
                    <span>{{ purOrder.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                </el-col>
                <el-col :span="8">
                    <span class="title">制单人:</span>
                    <span>{{ purOrder.fillterName }}</span>
                </el-col>
                <el-col :span="24">
                    <span class="title">备注</span>
                    <span>{{ purOrder.remark }}</span>
                </el-col>                         
            </el-col>
            <el-col :span="24">
                <svg id="pbarcode"></svg>
            </el-col> 
        </el-row>
        <el-row>
            <table class="gridtable">
                <tr>
                    <th width=30>序号</th><th width=120>请购科室</th><th width=150>产品名称</th><th width=110>JDE编码</th><th width=80>采购数量</th><th width=40>单位</th><th widht=120>规格</th>
                </tr>
                <tr v-for="item in purOrder.purchaseList" :key="item.id">
                    <td align='center'>{{item.rowNumber}}</td>
                    <td>{{item.applyDeptName}}</td>
                    <td>{{item.hosGoodsName}}</td>
                    <td>{{item.erpCode}}</td>
                    <td align='center'>{{item.hosPurQty}}</td>
                    <td align='center'>{{item.hosUnit}}</td>
                    <td>{{item.goodsGg}}</td>
                </tr>
            </table>
        </el-row>
        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-col :span="6">
                <span class="title">收货人:</span>
                <span>{{ purOrder.recLinkman }}</span>
            </el-col>
            <el-col :span="6">
                <span class="title">联系电话:</span>
                <span>{{ purOrder.recLinkmanPhone }}</span>
            </el-col>
            <el-col :span="12">
                <span class="title">地址:</span>
                <span>{{ purOrder.recAddress }}</span>
            </el-col>
        </el-row>
    </el-row>
</template>
<script>
import $ from 'jquery';
import '../../common/third/printThis/printThis';
import '../../common/third/qrcode/jquery.qrcode.min';
import JsBarcode from 'jsbarcode';

export default {
    data() {
        return {
            purOrder: {
                id: '',
                billId: '',
                purchaseComId: '',
                purchaseComName: '',
                purKind: 1,
                recOrgId: '',
                recOrgName: '',
                provId: '',
                provName: '',
                distrRelation: '',
                fillter: '',
                fillterName: '',
                remark: '',
                status: '',
                autitorId: '',
                autitorName: '',
                autitorSumRow: '',
                sumRow: '',
                fillDate: '',
                lastUpdateDt: '',
                version: '',
                purchaseList: [],
                recAddressId: '',
                recLinkman: '',
                recLinkmanPhone: '',
                recAddress: '',
            }
        }
    },
    watch:{
        pBillId:function(newVal,oldVal){
            this.init(newVal);
        }
    },
    props:{pBillId:String},
    methods: {        
        getPurchaseInfo(billId) {
            this.axios.get('/spdHERPService/order/purchase/getPurchase/' + billId).then(res => {
                if (res.data.code == "0") {
                    this.purOrder = res.data.data;
                }
            }, err => {
            });
        },
        print() {
            //this.printD=true;
             $("#printContent").printThis({
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
                JsBarcode("#pbarcode", billId, { height: 30, displayValue: false });
                this.getPurchaseInfo(billId);
                this.$nextTick(function () {
                    $("#perbarcode").qrcode({render: 'table',width: 150, height: 150, text: billId });
                });
            }
        }
    },
    mounted() {
        this.init(this.pBillId);
    }
};

</script>

<style>
#printContent{
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