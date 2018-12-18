<template>
    <el-row :gutter="10" style="height:100%">
        <el-col :span="24" style="height:100%">
            <el-col :span="18" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="90px" class="order_view" label-position="left">
                    <el-col :span="8">
                        <el-form-item label="采购单号:">
                            <span>{{ purOrder.billId }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="采购类型:">
                            <span>{{ purOrder.purKind | fPurKind}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="供应商:">
                            <span>{{ purOrder.provName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="请购机构:">
                            <span>{{ purOrder.purchaseComName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="请购科室:">
                            <span>{{ purOrder.purOrgCname }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="收货科室:">
                            <span>{{ purOrder.recOrgName }}</span>
                        </el-form-item>
                    </el-col>                      
                    
                        <el-col :span="8">
                        <el-form-item label="制单时间:">
                            <span>{{ purOrder.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="制单人:">
                            <span>{{ purOrder.fillterName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="备注:">
                            <span>{{ purOrder.remark }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24" class="noPrint">
                        <orderStatus :status="purOrder.status | fStatus"></orderStatus>
                    </el-col>
                </el-form>
            </el-col>
            <el-col :span="6">
                <svg id="barcode"></svg>
                <div id="erbarcode"></div>
            </el-col>
            <!--列表-->
            <el-table :data="purOrder.purchaseList" style="width: 100%;" fit border class="t_pivew_content">
                <el-table-column prop="rowNumber" label="序号" width="60">
                </el-table-column>
                <el-table-column prop="applyDeptName" label="请购科室" width="200">
                </el-table-column>
                <el-table-column prop="hosGoodsName" label="产品名称" width="180" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="erpCode" label="JDE编码" width="120">
                </el-table-column>
                <el-table-column prop="hosPrice" label="请购单价">
                </el-table-column>
                <el-table-column prop="hosApplyQty" label="请购数量" width="90">
                </el-table-column>
                <el-table-column prop="hosPurQty" label="采购数量" width="90">
                </el-table-column>                        
                <el-table-column prop="hosUnit" label="单位">
                </el-table-column>
                <el-table-column prop="goodsGg" label="规格">
                </el-table-column>
                    <!-- <el-table-column prop="purAmount" label="含税金额(元)" width="120">
                </el-table-column>
                <el-table-column prop="purTaxAmount" label="不含税金额(元)" width="120">
                </el-table-column>
                <el-table-column prop="applyDate" label="科室请购日期" width="120" class-name="noPrint">
                    <template slot-scope="scope">
                        {{scope.row.applyDate | dateFormat('YYYY-MM-DD')}}
                    </template>
                </el-table-column>
                <el-table-column prop="receiveDate" label="预计到货日期" width="120" class-name="noPrint">
                    <template slot-scope="scope">
                        {{scope.row.receiveDate | dateFormat('YYYY-MM-DD')}}
                    </template>
                </el-table-column>
                    <el-table-column label="状态" width="60" class-name="noPrint">
                    <template slot-scope="scope">
                        <el-tag type="danger" v-if="scope.row.status==1">已确认</el-tag>
                        <el-tag type="success" v-if="scope.row.status==0">未确认</el-tag>
                    </template>
                </el-table-column> --> 
            </el-table>
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="80px" class="order_view" label-position="left">
                <el-col :span="6">
                    <el-form-item label="收货人:">
                        <span>{{ purOrder.recLinkman }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="联系电话:">
                        <span>{{ purOrder.recLinkmanPhone }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="地址:">
                        <span>{{ purOrder.recAddress }}</span>
                    </el-form-item>
                </el-col>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
            <div style="text-align: center;">
                <el-button :plain="true" type="danger"  @click="print" icon="print">打印</el-button>
                <el-button :plain="true" type="danger"  @click="goBack">返回</el-button>
            </div>
        </el-col>
        </el-col>             
        
        <purchasePrint ref="purPrintD" :pBillId = "purOrder.billId"></purchasePrint>       
    </el-row>
</template>
<script>
import orderStatus from './orderStatus';
import { PURCHASE_STATUS, PURCHASE_KIND } from './common/constance';
import purchasePrint from './purchasePrint'
import JsBarcode from 'jsbarcode';

export default {
    data() {
        return {
            printDia:false,
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
    components: { orderStatus,purchasePrint },
    filters: {
        fStatus: function (value) {
            if (value) {
                return PURCHASE_STATUS[value].index;
            } else {
                return 0;
            }
        },
        fPurKind: val => {
            return PURCHASE_KIND[val].text;
        }
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        getPurchaseInfo(billId) {
            this.axios.get('/spdHERPService/order/purchase/getPurchase/' + billId).then(res => {
                if (res.data.code == "0") {
                    this.purOrder = res.data.data;
                }
            }, err => {
            });
        },
        showPrint(){
           
           this.print();
        },
        print() {
            // $("#printContent").printThis({
            //     debug: false,
            //     importCSS: true,
            //     importStyle: true,
            //     printContainer: true,
            //     loadCSS: './static/style/print.css',
            //     pageTitle: "采购单",
            //     removeInline: false,
            //     printDelay: 333,
            //     header: '采购单',
            //     formValues: true
            // });
            this.$nextTick(()=>{
                this.$refs.purPrintD.print();
            })
            
            
        }
    },
    mounted() {
        var billId = this.$route.query.billId;
        if (billId) {
            this.getPurchaseInfo(billId);
        }
    }
};

</script>

<style>
.t_pivew_content{
    font-size: 12px;
    height: calc(100% - 320px);
    overflow-y: auto;
}
</style>