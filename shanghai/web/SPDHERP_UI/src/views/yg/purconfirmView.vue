<template>
    <el-row :gutter="10" style="height:100%;">
        <el-col :span="24" id="printContent" style="height:100%;">
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-form label-width="120px" v-loading="loading">
                    <el-col :span="8">
                        <el-form-item label="确认单号">
                            <span>{{ purconfirm.billId }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="采购单号">
                            <span>{{ purconfirm.purBillId }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="请购机构">
                            <span>{{ purconfirm.purchaseCompanyName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="请购科室">
                            <span>{{ purconfirm.applyOrgName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="收货科室">
                            <span>{{ purconfirm.recieveOrgName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="制单人">
                            <span>{{ purconfirm.fillterName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="笔数">
                            <span>{{ purconfirm.sumRow }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="总金额">
                            <span><i class="fa fa-yen"></i>{{ purconfirm.sumAmount }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="不含税金额">
                            <span><i class="fa fa-yen"></i>{{ purconfirm.sumTaxAmount }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="制单时间">
                            <span>{{ purconfirm.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                        </el-form-item>
                    </el-col>                    
                    <el-col :span="15">
                        <el-form-item label="备注">
                            <span>{{ purconfirm.remark }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24" class="noPrint">
                        <el-steps :space="100" :active="getStep(purconfirm.status)" finish-status="success" center>
                            <el-step title="待提交" ></el-step>
                            <el-step title="待配送" ></el-step>
                            <el-step title="部分配送" ></el-step>
                            <el-step title="全部配送" ></el-step>
                        </el-steps>
                    </el-col>
                </el-form>
            </el-col>            
            <!--列表-->
             <el-table :data="purconfirm.lstDetail" style="width: 100%;" fit class="table_content">
                    <el-table-column prop="rowNumber" label="序号" width="60">
                    </el-table-column>
                    <el-table-column prop="applyDeptName" label="请购科室" width="150">
                    </el-table-column>
                    <el-table-column prop="hosGoodsName" label="产品名称" width="180" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="provGoodsErpPrice" label="请购单价">
                    </el-table-column>
                    <el-table-column prop="purQty" label="采购数量" width="90">
                    </el-table-column>
                    <el-table-column prop="qty" label="订单数量" width="90">
                    </el-table-column>    
                    <el-table-column prop="sendQty" label="配送数量">
                    </el-table-column>
                    <el-table-column prop="hosUnit" label="单位">
                    </el-table-column>
                    <el-table-column prop="qrAmount" label="含税金额(元)" width="120">
                    </el-table-column>
                    <el-table-column prop="qrTaxAmount" label="不含税金额(元)" width="120">
                    </el-table-column>
                    <el-table-column prop="applyDate" label="科室请购日期" width="120">
                        <template slot-scope="scope">
                            {{scope.row.applyDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="esArrivalDate" label="预计到货日期" width="120">
                        <template slot-scope="scope">
                            {{scope.row.esArrivalDate | dateFormat('YYYY-MM-DD')}}
                        </template>
                    </el-table-column>
                </el-table>
        </el-col>
    </el-row>
</template>
<script>
import { PURCONFIRM_STATUS } from './common/constance';
export default {
    name: 'purconfirmView',
    data() {
        return {
            loading: false,
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
    props: ["billid"],
    watch: {
        billid(curVal, oldVal) {
            this.getPurconfirmInfo(curVal);
        }
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        getPurconfirmInfo(billId) {
            this.loading = true;
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
        getStep(status){
            if(status == 0){
                return;
            }
            return PURCONFIRM_STATUS[status].index;
        }
    },
    mounted() {
        var billId = this.billid;//this.$route.query.billId;
        if (billId) {
            //JsBarcode("#barcode", billId);
            this.getPurconfirmInfo(billId);
        }
    }
};

</script>

<style>
.table_content{
    font-size: 12px;
    height: calc(100% - 100px);
    overflow-y: auto;
}
</style>