<template>
    <el-row style="height:100%" v-loading="pageLoading">
        <el-row>
          <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="80px">
                <el-col :span="8">
                    <el-form-item label="请购单号">
                        <span>{{ surBill.applyBillId }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="要货科室">
                        <span>{{ surBill.deptName }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="库房">
                         <span>{{ surBill.stocName }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="手术包码">
                        <span>{{ surBill.surCode }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="手术包名">
                        <span>{{ surBill.surName }}</span>
                    </el-form-item>
                </el-col>                
                <el-col :span="8">
                    <el-form-item label="打包人">
                         <span>{{ surBill.packerName }}</span>
                    </el-form-item>
                </el-col>
            </el-form>
         </el-col>
        </el-row>
        <el-row style="height:calc(100% - 120px);">
            <el-col :span="24"  style="height:100%">
                <el-table :data="surBill.surgeryPkgListVos" highlight-current-row style="width: 100%;"  fit border class="tab_surPkg" height="outer">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <table width="100%">
                                <tr><th width="20%">批次号</th><th  width="20%">批号</th><th width="10%">条码</th><th width="20%">有效期</th><th width="10%">数量</th></tr>
                                <tr v-for="item in props.row.lstGoodsBatch" :key="item.id" >
                                    <td>{{item.batchId}}</td><td>{{item.batchCode}}</td><td>{{item.uniqueCode}}</td><td>{{item.expdtEndDate | dateFormat}}</td><td>{{item.qty}}</td>
                                </tr>
                            </table>                              
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsName" label="品名" sortable></el-table-column>
                    <el-table-column prop="goodsGg" label="规格" sortable></el-table-column>
                    <el-table-column prop="qty" label="数量" sortable></el-table-column>
                    <el-table-column prop="unit" label="单位" sortable></el-table-column>
                    <el-table-column prop="brand" label="品牌" sortable></el-table-column>
                    <el-table-column prop="made" label="产地" sortable></el-table-column>
                    <el-table-column prop="mfrsName" label="厂家" sortable></el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                <div style="text-align: center;">
                    <el-button  type="danger"  @click="cancel" icon="el-icon-close" size="small">关闭</el-button>
                    <el-button  type="success"  @click="print(surBill.id)" icon="el-icon-printer" size="small" >打印</el-button>
                </div>
            </el-col>
        </el-row>
        <surPkgBillPrint ref="surBIllPrinter"></surPkgBillPrint>
    </el-row>
</template>
<script>
import surPkgBillPrint from './surPkgBillPrint.vue'
export default {
    data() {
        return {
            pageLoading:false,
            surBill:{}
         }
    },
    components:{surPkgBillPrint},
    methods: {
        clear(){
            this.surBill={};
        },
        show(billId){
            this.pageLoading = true;
            this.clear();
            var _this = this;
            this.axios.get(`/spdHERPService/surgeryPkg/getById/${billId}`).then(res => {
                _this.pageLoading = false;
                _this.surBill = res.data.data;
            }, err => {
                _this.pageLoading = false;
            });
        },   
        print(billId){
            this.$refs.surBIllPrinter.print(billId);
        },  
        cancel(flag){
            this.clear();
            this.$emit("cancel",flag);
        },
    },
    mounted() {
        
    }
}
</script>
<style>
.tab_surPkg{
    height:100%;
}
.red-tooltip{
    color:orangered;
}
.red-row{
    background-color: orangered;
}
</style>

