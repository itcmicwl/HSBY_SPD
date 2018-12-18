<template>
    <el-row :gutter="10" id="HosMonthInStock"  class="printArea"  v-show='false'>
            <el-col :span="24" style="padding-bottom:10px" align="center" >
                <span><b>{{new Date().getFullYear()}}年{{ inStockMonth }}月高低值耗材入库单查询</b></span>
            </el-col>
            <!--表格-->
                <table class="gridtable">
                    <tr>
                        <th width=30>序号</th>
                        <th width=80>入库单号</th>
                        <th width=40>会计期</th>
                        <th width=40>入库部门</th>
                        <th width=40>商品名称</th>
                        <th width=40>商品规格</th>
                        <th width=40>商品单价</th>
                        <th width=40>入库数量</th>
                        <th width=40>商品金额</th>
                    </tr>
                    <tr v-for="(item,index) in printDatas" :key="item.id">
                        <td align="center">{{ index+1 }}</td>
                        <td>{{item.instockBillId}}</td>
                        <td align="center">{{item.accountingPeriod}}</td>
                        <td align="center">{{item.instockDeptName}}</td>
                        <td >{{item.goodsName}}</td>
                        <td align="center">{{item.goodsGg}}</td>
                        <td align="center">{{item.inPrice}}</td>
                        <td align="center">{{item.qty}}</td>
                        <td align="center">{{item.goodsAmount}}</td>
                        
                    </tr>
                </table>
    </el-row>
</template>

<script>
    import $ from 'jquery';
    import '../../../common/third/printThis/printThis';
    import '../../../common/third/qrcode/jquery.qrcode.min';
    export default {
        data(){
            return {
                printDatas:[],
            }
        },
        props:['inStockMonth'],
        methods: {
            print(printDatas){
                this.printDatas = printDatas;
                $("#HosMonthInStock").printThis({
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
        },
        mounted(){
        }
    }
</script>

<style>
#HosMonthInStock{
    padding: 10px 10px 10px 10px;
}
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:10px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
}
table.gridtable th {
    border-width: 1px;
    padding: 3px;
    border-style: solid;
    border-color: #666666;
    background-color: #dedede;
}
table.gridtable td {
    border-width: 1px;
    padding: 3px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
}
</style>
