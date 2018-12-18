<template>
    <el-row id="settle-bill-view" v-loading="diaLoging">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="120px">
                <el-col :span="12">
                    <el-form-item label="医院:">
                        <span>{{ settleBill.hosName }}</span>
                    </el-form-item>
                </el-col> 
                <el-col :span="12">
                    <el-form-item label="供应商:">
                        <span>{{ settleBill.provName }}</span>
                    </el-form-item>
                </el-col>                
                <el-col :span="12">
                    <el-form-item label="制单时间:">
                        <span>{{ settleBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="制单人:">
                        <span>{{ settleBill.fillterName }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="发票号:">
                        <span>{{ settleBill.invoiceNumber }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="发票编码:">
                        <span>{{ settleBill.invoiceCode }}</span>
                    </el-form-item>
                </el-col>
              <el-col :span="24">
                    <el-form-item label="备注:">
                        <span>{{ settleBill.remark }}</span>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-col>        
        <el-table ref="receListTable" :data="settleBill.lstDetail" style="width: 100%;" fit height="380" stripe border>
            <el-table-column label="序号" prop="rowNum" width="50">
            </el-table-column>
            <el-table-column label="出库单号" prop="outBillid" width="140">
            </el-table-column>
            <el-table-column label="物资名称" prop="hosGoodsName" width="180" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="数量" align='center' prop="settleQty" width="60" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="规格" prop="goodsGg" show-overflow-tooltip width="120">
            </el-table-column>
            <el-table-column label="单位" align='center' prop="hosUnit" width="40">
            </el-table-column>
            <el-table-column label="批号"  prop="batchCode" width="80" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="出库部门" prop="outDeptName" width="120">
            </el-table-column>
            <el-table-column label="灭菌批号" prop="sterilizationCode">
            </el-table-column>
            <!-- <el-table-column label="灭菌有效日期" width="130">
                    <template slot-scope="scope">
                        {{scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD')}}
                    </template>
                </el-table-column>
                <el-table-column label="灭菌日期" width="130">
                    <template slot-scope="scope">
                        {{scope.row.sterilizationDate | dateFormat('YYYY-MM-DD')}}
                    </template>
                </el-table-column> -->
            <el-table-column label="有效期" width="80">
                <template slot-scope="scope">
                    {{scope.row.expdtEndDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>
            <el-table-column label="生产厂家" width="140" show-overflow-tooltip prop="mfrsName">
            </el-table-column>
            <el-table-column label="产地" prop="made" width="50">
            </el-table-column>
            <el-table-column label="发票号" prop="invoiceNumber" width="200">
            </el-table-column>
            <el-table-column label="发票编码" prop="invoiceCode" width="200">
            </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-top: 20px;text-align: center;">
            <div style="text-align: center;">
                <el-button type='primary' size="small" @click.native="cancel" icon="print">取 消</el-button>                 
            </div>
        </el-col>       
    </el-row>
</template>
<script>
import moment from 'moment';
import barcodeUtil from '../../common/js/barcodeUtil';
export default {
    data() {
        return {
            opModel:"ADD",
            diaLoging: false,            
            loading: false,            
            settleBill: {
                id:'',billId:'',spdCode:'',hosId:'',hosName:'',hosErpCode:'',provId:'',provName:'',provErpCode:'',
		        provDeptErpCode:'',filler:'',remark:'',sumRow:'',status:'',fillDate:'',lstDetail:[]
            },
            settleBillDetail: {
                id:'',pid:'',billId:'',rowNum:'',provId:'',provName:'',provErpCode:'',outDeptId:'',
                outDeptName:'',outDeptErpCode:'',outBillid:'',outRowNum:'',outDate:'',hosGoodsId:'',
                hosGoodsName:'',goodsGg:'',mfrsId:'',made:'',packetCode:'',provGoodsId:'',provGoodsName:'',
                batchId:'',batchCode:'',sterilizationCode:'',expdtEndDate:'',produceDatetime:'',hosUnit:'',
                hosUnitQty:'',provUnit:'',hosScale:'',provUnitQty:'',status:'',outKind:'',version:''
            },
            sltRows: []           
        }
    },
    filters: {
        fStatus: function (value) {
            if (value) {
                return DISTRBILL_STATUS[value].text;
            } else {
                return '';
            }
        }
    },    
    methods: {        
        getSettleBillByBillId(recId){
            var _this = this;
            this.axios.get('/spdHERPService/settleCenter/hosOutBalance/getByBillId/' + recId).then(res=>{
                if(res.data.code == 0){
                    _this.settleBill = res.data.data;                    
                }
            });
        },
        cancel(){
            if(this.settleBill.settleType==10){
                 this.$router.push({ path: 'settleBill4Out'});//出库结算
            }else{
                this.$router.push({ path: 'settleBill'});//入库结算
            }
        },
        
    },
    mounted() {
        var _this = this;
        var billId = this.$route.query.billId;
        if(billId){
            this.getSettleBillByBillId(billId);
        }
    }
};

</script>

<style>
#settle-bill-view .fa {
    margin-right: 5px;
}

#settle-bill-view .vue-echarts {
    width: 100%;
    height: 400px;
}

#settle-bill-view .el-table {
    font-size: 12px;
}

#settle-bill-view fieldset {
    border-width: 1px;
    border-style: solid;
    border-color: #1fa0ff;
    border-image: initial;
}

#settle-bill-view .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}

#settle-bill-view .el-dialog--small {
    width: 70%;
}
</style>