<template>
    <el-row :gutter="10" id="sickerUseVprint" class="printArea" v-show="false">
        <el-row>
                <el-col :span="24" style="padding-bottom:10px" align="center">
                    <span  style="font-size:20px"><b align='center'>入职医用材料验收登记表</b></span><br>
                </el-col>
        </el-row>
         <el-row>
            <table class="gridtable">
                <tr>
                    <th >病人姓名</th>
                    <th >住院号</th>
                    <th >床位号</th>
                    <th >性别</th>
                    <th >年龄</th>
                    <th colspan="3">手术日期</th>
                    <th colspan="3">医生签字</th>
                </tr>
                <tr>
                    <td align="center">{{sickerUseViewVo.patientName}}</td>
                    <td >{{sickerUseViewVo.bedNum}}</td>
                    <td >{{sickerUseViewVo.patientId}}</td>
                    <td align="center">{{sickerUseViewVo.patientSex}}</td>
                    <td align="center">{{sickerUseViewVo.patientAge}}</td>
                    <td colspan="3">{{sickerUseViewVo.oprationDate | dateFormat('YYYY-MM-DD')}}</td>
                    <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <th align="center"width="12%">序号</th>
                    <th width="15%">代理公司名称</th>
                    <th width="10%">经营许可证</th>
                    <th width="10%">产品名称</th>
                    <th width="10%">产品编号/批号</th>
                    <th width="10%">规格/型号</th>
                    <th width="5%">单位</th>
                    <th align="center"width="5%">数量</th>
                    <th width="10%">产品注册号</th>
                    <th width="6%">有效日期</th>
                    <th width="7%">到货日期</th>
                </tr>
                <tr v-for="(item ,index) in sickerUseViewVo.detaill">
                    <td align="center"> {{index+1}}</td>
                    <td>{{item.provName}}</td>
                    <td>{{item.jyxkCode}}</td>
                    <td>{{item.goodsName}}</td>
                    <td>{{item.batchCode}}</td>
                    <td>{{item.goodsGg}}</td>
                    <td>{{item.unit}}</td>
                    <td>{{item.useQty}}</td>
                    <td>{{item.certificateCode}}</td>
                    <td>{{item.expdtEndDate | dateFormat('YYYY-MM-DD')}}</td>
                    <td></td>
                </tr>

                <tr>
                    <td style="height:300px;width:100%;" colspan="11">产品资料粘贴处:</td>
                </tr> 
                <tr >
                <td colspan="2" >产品公司代理公司盖章：</td>
                <td colspan="2" >&nbsp;</td>
                <td colspan="2" >经办人：</td>
                <td colspan="2" >&nbsp;</td>
                <td colspan="1" >验收：</td>
                <td colspan="2" >&nbsp;</td>
                </tr> 
            </table>
        </el-row>
    </el-row>
</template>
<script>
import $ from 'jquery';
import '@/common/third/printThis/printThis';
import '@/common/third/qrcode/jquery.qrcode.min';
export default {
    data() {
        return {
            baseURL: "/spdHERPService/stockout/sickerUse",
            sickerUseViewVo: {
                id: "",
                hosId: "",
                patientId: "",
                patientInHosId: "",
                patientName:"",
                operationRoom: "",
                patientSex: "",
                patientAge: "",
                bedNum: "",
                oprationDate: "",
                detail:[]
            }
        }
    },

    methods: {        
        print(billId) {
            this.axios.post(this.baseURL + "/getSickuserList4Print?billId="+billId).then(
                res => {
                this.sickerUseViewVo = res.data.data;
                this.doStart();
                },
                err => {}
            );   
        },
        doStart(){
            this.$nextTick(function(){
                 $("#sickerUseVprint").printThis({
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
            });
        }
    },
    mounted() {
    }
};

</script>

<style>
#sickerUseVprint{
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