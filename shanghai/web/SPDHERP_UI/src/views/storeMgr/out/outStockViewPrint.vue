<template>
    <el-row :gutter="10" id="billPrintContent" class="printArea" v-show='false'>
        <el-row>
            <el-col :span="24" style="padding-bottom:10px" align="center">
                <span  style="font-size:20px"><b>{{ outStockBillPrint.outStockKind | outStockKind }}单</b></span><br>
            </el-col>
            <el-col :span="8">
                <span class="title">出库单号:</span>
                <span>{{ outStockBillPrint.billId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">源单号:</span>
                <span>{{ outStockBillPrint.sourceBillId }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">当前状态:</span>
                <span>{{ outStockBillPrint.status | status }}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">出库类型:</span>
                <span>{{ outStockBillPrint.outStockKind | outStockKind }}</span>
            </el-col>
            <el-col :span="8">
                <span class=" title">制单时间:</span>
                <span>{{ outStockBillPrint.fillDate | dateFormat('YYYY-MM-DD')}}</span>
            </el-col>
            <el-col :span="8">
                <span class="title">制单人:</span>
                <span>{{ outStockBillPrint.fillerName }}</span>
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
                    <th width=80>批号</th>
                    <th width=80>产地</th>
                    <th width=80>出库数量</th>
                    <th width=40>单位</th>
                    <th width=80>货位</th>

                </tr>
                <tr v-for="item in outStockBillPrint.lstOutStock" :key="item.id">
                    <td align='center'>{{item.outBillRow}}</td>
                    <td>{{item.goodsName}}</td>
                    <td>{{item.goodsGg}}</td>
                    <td>{{item.batchCode}}</td>
                    <td>{{item.made}}</td>
                    <td align='center'>{{item.outQty}}</td>
                    <td align='center'>{{item.unit}}</td>
                    <td align='center'>{{item.shelfId}}</td>
                </tr>
            </table>
        </el-row>

        <el-row class="toolbar" style="padding-bottom: 5px;">
            <el-col :span="14">
                <span class="title">出库机构:</span>
                <span>{{ outStockBillPrint.outOrgName }}</span>
            </el-col>
            <el-col :span="10">
                <span class="title">出库部门:</span>
                <span>{{ outStockBillPrint.outDeptName }}</span>
            </el-col>
        </el-row>

        <el-row class="toolbar" style="padding-bottom: 0px;">
            <el-col :span="14">
                <span class="title">入库机构:</span>
                <span>{{ outStockBillPrint.inOrgName }}</span>
            </el-col>
            <el-col :span="10">
                <span class="title">入库部门:</span>
                <span>{{ outStockBillPrint.inDeptName }}</span>
            </el-col>
        </el-row>
    </el-row>
</template>
<script>
import moment from 'moment';
import { OUTSTOCK_KIND,OUTSTOCK_STATUS } from '../../../common/js/constance';
import $ from 'jquery';
import '../../../common/third/printThis/printThis';
import '../../../common/third/qrcode/jquery.qrcode.min';
import JsBarcode from 'jsbarcode';
export default {
    data() {
        return {
            loading: false,
            outStockBillPrint: {},
            confirmVisible: false
        }
    },

    filters: {
        status: function (value) {
            if (value) {
                return OUTSTOCK_STATUS[value].text;
            } else {
                return 0;
            }
        },
      outStockKind:function (value) {
        if(value){
          return OUTSTOCK_KIND[value].text;
        }else {
          return 0;
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
        goBack() {
            this.$router.go(-1);
        },
        getOutBillPrint(billId) {
            this.loading = true;
            let params = {
              billId: billId
            };
          this.axios.post('/spdHERPService/stockMgr/out/listBill/getByBillId', params).then(res => {
            this.loading = false;
            if (res.data.code == "0") {
              this.outStockBillPrint = res.data.data;
            } else {
              this.loading = false;
            }
          }, err => {
            this.$message.error("获取出库单失败!");
          })
        },


        print(outStockBill) {
            this.outStockBillPrint = outStockBill;
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
