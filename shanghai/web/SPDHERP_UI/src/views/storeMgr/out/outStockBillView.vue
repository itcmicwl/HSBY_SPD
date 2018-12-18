<template>
  <el-row id="out-stock-view" :gutter="10" style="height:100%">
    <el-col :span="24" id="outStockPrintView">
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form label-width="120px">
          <el-col :span="8">
            <el-form-item label="出库单号">
              <span>{{ outStockBill.billId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="源单号">
              <span>{{ outStockBill.sourceBillId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="当前状态">
              <span>{{ outStockBill.status | status}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出库类型">
              <span>{{ outStockBill.outStockKind | outStockKind}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出库机构">
              <span>{{ outStockBill.outOrgName }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出库部门">
              <span>{{ outStockBill.outDeptName }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
              <el-form-item label="入库机构:">
                <span>{{ outStockBill.inOrgName }}</span>
              </el-form-item>
            </el-col>
          <el-col :span="8">
              <el-form-item label="入库部门:">
                <span>{{ outStockBill.inDeptName }}</span>
              </el-form-item>
            </el-col>
          <el-col :span="8">
              <el-form-item label="制单时间">
                <span>{{ outStockBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
              </el-form-item>
            </el-col>
          <el-col :span="8">
              <el-form-item label="制单人">
                <span>{{ outStockBill.fillerName }}</span>
              </el-form-item>
            </el-col>
        </el-form>
      </el-col>
      <template>
        <el-table :data="outStockBill.lstOutStock" style="width: 100%;" v-loading="sLoading" fit border  class ='outView-tableheight' stripe>
          <el-table-column label="序号" prop="outBillRow" width="60">
          </el-table-column>
          <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
          </el-table-column>
          <el-table-column label="规格" prop="goodsGg">
          </el-table-column>
          <el-table-column label="出库数量" prop="outQty" width="90">
          </el-table-column>
          <el-table-column label="单位" prop="unit">
          </el-table-column>
          <el-table-column label="货位" prop="shelfId">
          </el-table-column>
          <el-table-column label="批号" prop="batchCode">
          </el-table-column>
          <el-table-column label="灭菌批号" prop="sterilizationCode">
          </el-table-column>
          <el-table-column label="灭菌效期" width="130">
            <template slot-scope="scope">
              {{scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD')}}
            </template>
          </el-table-column>
          <el-table-column label="有效期" width="130">
            <template slot-scope="scope">
              {{scope.row.expdtEndDate | dateFormat('YYYY-MM-DD')}}
            </template>
          </el-table-column>
          <el-table-column label="产地" prop="made" width="90">
          </el-table-column>
        </el-table>
      </template>
    </el-col>

    <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;text-align: center;">
      <el-button :plain="true" type="danger"  @click="goBack">返回</el-button>
      <!--<el-button type="info"  @click="autid(1)" v-if="inStockBill.status == 20">审核</el-button>
      <el-button type="danger"  @click="autid(0)" v-if="inStockBill.status == 30">取消审核</el-button>
      <el-button type="warning"  @click="chargeBill" v-if="inStockBill.status == 30">入库</el-button>-->
      <el-button type="info"  @click="print">打印</el-button>
    </el-col>
    <outStockViewPrint ref="purPrintD" :pBillId = "outStockBill.billId" ></outStockViewPrint>
  </el-row>
</template>

<script>
  import JsBarcode from 'jsbarcode';
  import $ from 'jquery';
  import outStockViewPrint from './outStockViewPrint'
  import {OUTSTOCK_STATUS} from "../../../common/js/constance";
  import {OUTSTOCK_KIND} from "../../../common/js/constance";
    export default {
      data(){
        return{
          sLoading:false,
          outStockBill:{},
          zTotal: 0,
          zPage: 1,
          zPageSize: 30,
        }
      },
      components:{
        outStockViewPrint
      },
      filters:{
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
      methods: {
        goBack() {
          this.$emit("close");
        },
        show(billId) {
          if (billId) {
            /* JsBarcode("#barcode", billId, { height: 50, displayValue: false });*/
            this.getOutBillByBillId(billId);
          }
        },
        zSizeChange(val) {
          this.zPageSize = val;
          //this.getOutBillList(this.zPage);
        },
        zCurrentChange() {

        },
        print(){
          this.$refs.purPrintD.print(this.outStockBill);
        },
        getOutBillByBillId(billId) {
          var _this = this;
          let params = {
            billId: billId
          };
          this.sLoading = true;
          this.axios.post('/spdHERPService/stockMgr/out/listBill/getByBillId', params).then(res => {
            this.sLoading = false;
            if (res.data.code == "0") {
              this.outStockBill = res.data.data;
            } else {
              this.sLoading = false;
            }
          }, err => {
            this.$message.error("获取出库单失败!");
          })
        },
      }
    };
</script>

<style>
  #out-stock-view .outView-tableheight{
      height: 350px;
  }
</style>
