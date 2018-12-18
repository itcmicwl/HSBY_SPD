<template>
    <el-row id="pre-view-buy" :gutter="10" style="height:95%">
            <el-col :span="24" style="height:95%">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;height: 200px;">
                    <el-form label-width="120px">
                        <el-col :span="8">
                            <el-form-item label="预购科室：">
                                <span>{{ buyBill.buyDeptName }}</span>
                            </el-form-item>
                        </el-col>
                       
                        <el-col :span="8">
                            <el-form-item label="目标科室：">
                                <span>{{ buyBill.sourceWarehouseName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="预购笔数：">
                                <span>{{ buyBill.sumRow }}</span>
                            </el-form-item>
                        </el-col>
                       
                       <!--
                        <el-col :span="8">
                            <el-form-item label="采购笔数：">
                                <span>{{ buyBill.whSumrow }}</span>
                            </el-form-item>
                        </el-col>
                        -->
                         <el-col :span="8">
                            <el-form-item label="总金额：">
                                <span>{{ buyBill.totalJe }}</span>
                            </el-form-item>
                        </el-col>
                       <el-col :span="8">
                            <el-form-item label="制单时间:">
                                <span>{{ buyBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="制单人：">
                                <span>{{ buyBill.fillterName }}</span>
                            </el-form-item>
                        </el-col>
                            <el-col :span="8">
                            <el-form-item label="科室审核时间:">
                                <span v-if="buyBill.deptDealTime">{{ buyBill.deptDealTime | dateFormat('YYYY-MM-DD')}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="科室审核人：">
                                <span>{{ buyBill.deptDealManName }}</span>
                            </el-form-item>
                        </el-col>
                        <!--
                        <el-col :span="8">
                            <el-form-item label="供应商：">
                                <span>{{ buyBill.provName }}</span>
                            </el-form-item>
                        </el-col>
                       -->
                       <el-col :span="8">
                            <el-form-item label="收货人：">
                                <span>{{ buyBill.recLinkman }}</span>
                            </el-form-item>
                        </el-col>
                         <el-col :span="8">
                            <el-form-item label="收货人电话：">
                                <span>{{ buyBill.recLinkmanPhone }}</span>
                            </el-form-item>
                        </el-col>
                          <el-col :span="16">
                            <el-form-item label="收货地址：">
                                <span>{{ buyBill.recAddress }}</span>
                            </el-form-item>
                        </el-col>
                         
                       
                        <el-col :span="24">
                            <el-steps :space="100" :active="test" finish-status="success" center>
                                <el-step title="已保存"></el-step>
                                <el-step title="已提交"></el-step>
                                <el-step title="未处理"></el-step>
                                <el-step title="处理中"></el-step>
                                <el-step title="已处理"></el-step>
                            </el-steps>
                        </el-col>
                    </el-form>
                </el-col>
                <!--列表-->
                    <el-table :data="buyBill.subVoList" style="width: 100%; " fit border  class ='viewBuy-tableheight'>
                        <el-table-column prop="rowNumber" label="序号" width="60">
                        </el-table-column>
    
                        <el-table-column prop="goodsName" label="名称" width="180" show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="code" label="编号">
                        </el-table-column>
                         <el-table-column prop="goodsGg" label="规格">
                        </el-table-column>
                        <el-table-column prop="price" label="单价" width="120">
                        </el-table-column>
                       
                         
                        <el-table-column prop="qty" label="预购数量">
                        </el-table-column>
                        <el-table-column prop="je" label="金额">
                        </el-table-column>
                        <el-table-column prop="mfrsName" label="产家">
                        </el-table-column>
                        <el-table-column prop="provName" label="供应商">
                        </el-table-column>
    
                     
                     
                    </el-table>
              
               <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                    <div style="margin-right:50px;" align="right">
                        <el-button  type="primary"   @click="goBack">返回</el-button>
                    </div>
                </el-col>
            </el-col>  
    </el-row>
</template>
<script>
import { DEPTBUY_MAIN_STATUS } from '../applyMgr/common/constance';
import { DEPTBUY_SUB_STATUS } from '../applyMgr/common/constance';
import { DEPTBUY_MAIN_DEPTAUDIT_STATUS } from '../applyMgr/common/constance';
export default {
    data() {
        return {
            buyBill: {
                id: '', billId: '', fillDate: '', returnCompanyId: '', returnCompanyName: '',
                returnOrgId: '', returnOrgCode: '', returnOrgName: '',
                outOrgId: '', outOrgName: '', outOrgCode: '',
                provId: '', provName: '',
                fillter: '', memo: '', isStartInvoice: '', sumRow: '', status: '',
                subVoList: []
            },
          
            test: 0,
        }
    },


    methods: {
        goBack() {
            this.$router.go(-1);
        },
        getBillDetails(billId) {
            let params ={billId:billId};
            this.axios.post('/spdHERPService/applyMgr/deptBuyMain/getBillDetails/' , params).then(res => {
                if (res.data.code == "0") {
                    this.buyBill = res.data.data;
                    this.test = DEPTBUY_MAIN_STATUS[this.buyBill.state].index;
                }
            }, err => {
            });
        },
        
    },
    mounted() {
   
        var billId = this.$route.query.billId;
      //  alert(billId);
        if (billId) {
            this.getBillDetails(billId);

        }
    }
};

</script>

<style>
#pre-view-buy .el-table {
    font-size: 12px;
}
#pre-view-buy .viewBuy-tableheight {        
        height:  calc(100% - 215px);
}
</style>