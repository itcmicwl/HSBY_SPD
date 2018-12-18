<template>
    <el-row :gutter="10" id="surBillPrinter" class="printArea" v-show='false'>
        <el-row>
            <el-col :span="24" style="padding-bottom:10px" align="center">
                <span  style="font-size:20px"><b>{{ surBill.surName }}</b></span><br>
            </el-col> 
        </el-row>
        <el-row>
            <el-col :span="16">
                <el-row>
                    <el-col :span="12">
                        <span class="title">请购单号:</span>
                        <span>{{ surBill.applyBillId }}</span>
                    </el-col>
                    <el-col :span="12">
                        <span class="title">要货科室:</span>
                        <span>{{ surBill.deptName }}</span>
                    </el-col>
                    <el-col :span="12">
                        <span class="title">库房:</span>
                        <span>{{ surBill.stocName }}</span>
                    </el-col>
                    <el-col :span="12">
                        <span class="title">打包人:</span>
                        <span>{{ surBill.packerName }}</span>
                    </el-col>
                </el-row>
            </el-col>
            <el-col :span="8">
                 <svg id="bar-surCode"></svg>
            </el-col>                            
        </el-row>
         <el-row>
             <table class="gridtable" v-if="surBill.status>0">
                <tr>
                    <th width=100>品名</th>
                    <th width=150>规格</th>
                    <th widht=80>数量</th>
                    <th width=80>批次号</th>                    
                    <th width=70>批号</th>
                    <th width=80>有效期</th>
                    <th width=80>品牌</th>
                </tr>
                <template v-for="goods in surBill.surgeryPkgListVos">
                    <tr v-for="item in goods.lstGoodsBatch" :key="item.id">
                        <td>{{goods.goodsName}}</td>
                        <td>{{goods.goodsGg}}</td>
                        <td>{{item.qty}}/{{goods.qty}}({{goods.unit}})</td>
                        <td align='center'>{{item.batchId}}</td>
                        <td align='center'>{{item.batchCode}}</td>
                        <td align='center'>{{item.expdtEndDate | dateFormat}}</td>
                        <td align='center'>{{goods.brand}}</td>
                    </tr>
                </template>
            </table>
            <table class="gridtable" v-else>
                <tr>
                    <th width=150>品名</th>
                    <th width=150>规格</th>
                    <th widht=80>数量</th>
                    <th width=80>品牌</th>
                    <th width=200>厂家</th>
                </tr>
                <tr v-for="goods in surBill.surgeryPkgListVos">
                    <td>{{goods.goodsName}}</td>
                    <td>{{goods.goodsGg}}</td>
                    <td>{{goods.qty}}({{goods.unit}})</td>
                    <td align='center'>{{goods.brand}}</td>
                    <td>{{goods.mfrsName}}</td>
                </tr>
            </table>
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
            pageLoading:false,
            surBill:{}
         }
    },
    methods: {
        clear(){
            this.surBill={};
        },
        print(billId){
            this.pageLoading = true;
            this.clear();
            var _this = this;
            JsBarcode("#bar-surCode", billId, { height: 50, displayValue: false });
            this.axios.get(`/spdHERPService/surgeryPkg/getById/${billId}`).then(res => {
                _this.pageLoading = false;
                _this.surBill = res.data.data;
                _this.showPrinter();
            }, err => {
                _this.pageLoading = false;
            });
        },   
        showPrinter(){
            $("#surBillPrinter").printThis({
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
        cancel(flag){
            this.clear();
            this.$emit("cancel");
        },
    },
    mounted() {
        
    }
}
</script>
<style>
#surBillPrinter{
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

