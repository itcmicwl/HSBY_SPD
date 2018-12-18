<template>
  <el-row id="spd-inventory-view" :gutter="10" style="height:100%">
    <el-col :span="24" id="spdInventoryPrintView">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
          <el-form label-width="120px">
            <el-col :span="8">
              <el-form-item label="盘点单号：">
                <span>{{ spdInventorys.id }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="医院名称：">
                <span>{{ spdInventorys.hosName }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="操作人：">
                <span>{{ spdInventorys.operationer }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="操作时间：">
                <span>{{ spdInventorys.operationtime }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="当前状态：">
                <span>{{ spdInventorys.state | state}}</span>
              </el-form-item>
            </el-col>
          </el-form>
        </el-col>
      <template>
        <el-table :data="spdInventoryList" style="width: 100%;" v-loading="sLoading" fit border  class ='outView-tableheight' :row-class-name="tableRowClassName">
          <el-table-column label="序号" type="index" width="60">
          </el-table-column>
          <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
          </el-table-column>
          <el-table-column label="规格" prop="goodsGg" width="180">
          </el-table-column>
          <el-table-column label="货位" prop="goodsStockLocation" width="80">
          </el-table-column>
          <el-table-column label="库存数量" prop="stockSupplyQty" width="50">
          </el-table-column>
          <el-table-column label="盘点数量" prop="inventoryQty" width="50">
          </el-table-column>
          <el-table-column label="盈亏状况" width="50">
            <template slot-scope="scope">
              {{scope.row.inventoryQty==null?'':scope.row.inventoryQty-scope.row.stockSupplyQty}}
            </template>
          </el-table-column>
          <el-table-column label="入库日期" width="90">
            <template slot-scope="scope">
              {{scope.row.instockDate | dateFormat('YYYY-MM-DD')}}
            </template>
          </el-table-column>
          <el-table-column label="批号" prop="batchCode" width="90">
          </el-table-column>
          <el-table-column label="失效日期" width="90">
            <template slot-scope="scope">
              {{scope.row.expirationDate | dateFormat('YYYY-MM-DD')}}
            </template>
          </el-table-column>
          <el-table-column label="生产厂商" prop="mfrsName">
          </el-table-column>
        </el-table>
      </template>
    </el-col>

    <!--分页条-->
    <el-col :span="24" class="toolbar"style="margin-top: 3px">
      <el-pagination @size-change="zSizeChange" @current-change="zCurrentChange" :current-page="zPage" :page-sizes="[30, 100, 200, 400]" :page-size="zPageSize" layout="total, sizes, prev, pager, next" :total="zTotal" style="float:right;" small>
      </el-pagination>
    </el-col>

    <el-col :span="24" class="toolbar" style="margin-top:5px; padding-bottom: 0px;text-align: center;">
      <el-button :plain="true" type="danger"  @click="goBack">返回</el-button>
      <el-button type="info"  @click="print">打印</el-button>
    </el-col>
    <spdInventoryViewPrint ref="spdInventoryPrintD" :pBillId = "spdInventorys.id" ></spdInventoryViewPrint>
  </el-row>
</template>

<script>
  import {INVENTORY_STATUS} from "../../common/js/constance";
  import spdInventoryViewPrint from "./spdInventoryViewPrint"
  export default {
    data(){
      return{
        spdInventorys:{},
        spdInventoryList:[],
        zTotal: 0,
        zPage: 1,
        zPageSize: 30,
        sLoading:false,
        spdList:{
          id:'',
          spdInventorys:{},
          spdInventoryList:[]
        }
      }
    },
    filters:{
      state: function (value) {
        if (value) {
          return INVENTORY_STATUS[value].text;
        } else {
          return 0;
        }
      },
    },
    components:{
      spdInventoryViewPrint
    },
    methods:{
      tableRowClassName({row,rowIndex}){
        if(row.inventoryQty==null){
          return 'success-row';
        }else if (row.stockSupplyQty >row.inventoryQty) {
          return 'warning-row';
        } else if(row.stockSupplyQty < row.inventoryQty){
          return 'full-row';
        }
      },
      print(){
        this.$refs.spdInventoryPrintD.print(this.spdList);
      },
      goBack() {
        this.$emit("close");
      },
      getSpdInventory:function (billId) {
        var param = {
          id:billId
        };
        this.sLoading = true;
        this.axios.post('/spdHERPService/spdOutPtl/spdInventory/getById',param).then(res => {
          this.sLoading = false;
          if(res.data.code == "0"){
            this.spdInventorys = res.data.data;
            this.spdList.spdInventorys = res.data.data;
            this.spdList.id = res.data.data.id;
          }else {
            this.$message.error("获取盘点单列表失败!");
          }
        })
      },
      getSpdInventoryList:function(pIndex,billId){
        this.page = pIndex;
        var param = {
          orderBy: "",
          page: this.page,
          rows: this.pageSize,
          queryObject: {
            pid:billId
          }
        };
        this.sLoading = true;
        this.axios.post('/spdHERPService/spdOutPtl/spdInventoryList/listByPage',param).then(res => {
          this.sLoading = false;
          if(res.data.code == "0"){
            this.spdInventoryList = res.data.data.data;
            this.spdList.spdInventoryList = res.data.data.data;
            this.zTotal = res.data.data.total;
          }else {
            this.$message.error("获取盘点单列表失败!");
          }
        })
      },
      show(billId) {
        if (billId) {
          /* JsBarcode("#barcode", billId, { height: 50, displayValue: false });*/
          this.getSpdInventory(billId);
          this.getSpdInventoryList(1,billId);
        }
      },
      zSizeChange(val){
        this.zPageSize = val;
        this.getSpdInventoryList(this.zPage,this.spdInventorys.id);
      },
      zCurrentChange(val){
        this.zPage = val;
        this.getSpdInventoryList(this.zPage,this.spdInventorys.id);
      },
    },
    mounted(){

    },
  }
</script>

<style>
  #spd-inventory-view .outView-tableheight{
    height: 400px;
  }
  #spd-inventory-view .warning-row {
    background: #F56C6C;
  }

  #spd-inventory-view .success-row {
    background: #f0f9eb;
  }

  #spd-inventory-view .full-row{
    background-color: #57b547;
  }
</style>
