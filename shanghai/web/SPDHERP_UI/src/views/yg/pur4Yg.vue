<template>
    <el-col id="pur4-yg" :span="24" style="height:100%;">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-col :span="2">
                <span>采购确认单号</span>
            </el-col>
            <el-col :span="8">
                <el-input v-model="filters.billId" placeholder="单号" ></el-input>
            </el-col>
            <el-col :span="8" :push="1">
                <el-button type="primary" icon="search" v-on:click="getPurchaseList(1)" >查询</el-button>
                <el-button @click="resetForm" >重置</el-button>
            </el-col>
        </el-col>
        <!--列表-->
        <el-table :data="purchaseList" highlight-current-row v-loading="loading" style="width: 100%;" fit class="table_content">
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button  @click="PurchaseView(scope.row.billId)" type="text">查看</el-button>
                    <el-button v-if="scope.row.isConfirm =='0' && !scope.row.ygBillId"  @click="sendToSmp(scope.row.billId)" type="text">上传阳光平台</el-button>
                    <el-button v-if="scope.row.isConfirm =='0' && scope.row.ygBillId"  @click="sendToSmp(scope.row.billId)" type="text">确认</el-button>
                    <el-button v-if="scope.row.isConfirm =='1' && scope.row.ygBillId"  @click="sendToSmp(scope.row.billId)" type="text">阳光平台状态</el-button>
                </template>
            </el-table-column>
             <el-table-column label="供应商" prop="provName" width="180"></el-table-column>         
            <el-table-column label="单号" prop="billId" width="150"></el-table-column>
            <el-table-column label="阳光平台单号" prop="ygBillId" width="150"></el-table-column>
            <el-table-column label="是否确认" width="80">
                <template slot-scope="scope">
                    {{scope.row.ygBillId?(scope.row.isConfirm == "1"?"已确认":"未确认"):"未上传"}}
                </template>
            </el-table-column>
            <el-table-column label="含税金额" prop="sumAmount" width="120">
            </el-table-column>              
            <el-table-column prop="fillDate" label="制单时间" width="120">
                <template slot-scope="scope">
                    {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                </template>
            </el-table-column>                            
        </el-table>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
            </el-pagination>
        </el-col>
        <el-dialog title="采购确认单查看" :visible.sync="confirmViewVisible" :close-on-click-modal="false" width="70%" center>
            <purconfirmView :billid="billid"></purconfirmView>
            <div slot="footer" class="dialog-footer">
                <el-button :plain="true" type="danger"  @click="print">打印</el-button>
                <el-button @click.native="confirmViewVisible = false" >关闭</el-button>
            </div>
        </el-dialog>
        <el-dialog title="上传阳光平台" :visible.sync="sendSMPV" :close-on-click-modal="false" size="large">
           <sendPur ref="cSendPur" :billid="billid" v-on:btnStatus = "setBtnStatus"></sendPur>
            <div slot="footer" class="dialog-footer">
                <el-button v-show="btnFresh" @click.native="getPurYgStatus" >刷新</el-button>
                <el-button @click.native="sendSMPV = false" >关闭</el-button>
                <el-button v-show="btnSend" @click.native="sendPur" >发送</el-button>
                <el-button v-show="btnConfirm" @click.native="confirmToSMP" >确认</el-button>
            </div>
        </el-dialog>
        <purconfirmViewPrint ref="printP"></purconfirmViewPrint>
    </el-col>    
</template>
<script>
import purconfirmView from './purconfirmView';
import sendPur from './sendPur';
import { PURCONFIRM_STATUS } from './common/constance';
import '@/common/third/printThis/printThis';
import purconfirmViewPrint from './purconfirmViewPrint'
export default {
    data() {
        return {
            filters: {
                billId: '',
                hosId: ''
            },
            btnFresh:false,
            btnConfirm:false,
            btnSend:false,
            billid: '',
            sendSMPV:false,
            confirmViewVisible: false,
            showTab: 'purconfirmLst',
            total: 0,
            page: 1,
            pageSize: 30,
            loading: false,
            purchaseList: [
            ]
        }
    },
    methods: {
        //获取列表
        getPurchaseList: function (pIndex) {
            this.page = pIndex;
            var param = {
                orderBy: "",
                page: this.page,
                rows: this.pageSize,
                queryObject: {
                    billId: this.filters.billId,
                    purchaseCompanyId: this.filters.hosId
                }
            };
            this.loading = true;
            this.axios.post('/spdHERPService/orderMgr/purconfirm/listByPage', param).then(res => {
                this.loading = false;
                this.purchaseList = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getPurchaseList(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getPurchaseList(this.page);
        },
        savePurcon(billids) {
            this.$nextTick(function () {
                this.showTab = "purconfirmLst";
                this.filters.billId = billids.join(",");
                this.getPurchaseList(1);
            });
        },
        PurchaseView(billId) {
            this.billid = billId;
            this.confirmViewVisible = true;
            // this.$router.push({ path: 'purchaseView', query: { billId: billId } });
        },
        sendToSmp(billId){
            this.billid = billId;
            this.sendSMPV = true;
        },        
        sendPur(){
            this.$refs.cSendPur.sendPurToYg();
        },
        confirmToSMP(){
            this.$refs.cSendPur.confirmToYg();
        },
        resetForm() {
            this.filters.billId = "";
        },
        statusFormat(row) {
            return PURCONFIRM_STATUS[row.status].text;
        },
        setBtnStatus(status){
            this.btnConfirm = status.btnConfirm;
            this.btnSend = status.btnSend;
            this.btnFresh = status.btnFresh;
        },
        getPurYgStatus(){
             this.$refs.cSendPur.getPurYgStatus();
        },
        print() {
            this.$refs.printP.print(this.billid);
            // $("#printContent").printThis({
            //     debug: false,
            //     importCSS: true,
            //     importStyle: true,
            //     printContainer: true,
            //     loadCSS: './static/style/print.css',
            //     pageTitle: "采购确认单",
            //     removeInline: false,
            //     printDelay: 333,
            //     header: null,
            //     formValues: true
            // });
        }
    },
    mounted() {
        this.filters.hosId = this.user.corpId;
        this.getPurchaseList(1);

    },
    components: { purconfirmView ,sendPur,purconfirmViewPrint }

}
</script>
<style>
#pur4-yg .table_content{
    font-size: 12px;
    height: calc(100% - 80px);
    overflow-y: auto;
}
#pur4-yg .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#pur4-yg .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
</style>

