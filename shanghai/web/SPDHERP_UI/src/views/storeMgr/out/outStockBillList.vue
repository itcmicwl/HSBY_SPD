<template>
  <div style="height: 100%;">
  <el-row style="height: 100%" v-show="visible.vOutBillLst">
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;margin-top: 5px" >
      <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters" style="margin: 2px;">
        <el-form-item label="制单时间">
          <el-date-picker v-model="sFilters.startDate" class="data-picker" type="date" placeholder="开始日期" ></el-date-picker>
          <span>-</span>
          <el-date-picker v-model="sFilters.endDate" class="data-picker" type="date" placeholder="结束日期" ></el-date-picker>
        </el-form-item>
        <el-form-item label="出库单号">
          <el-input v-model="sFilters.billId" placeholder="出库单号"></el-input>
        </el-form-item>
        <el-form-item label="产品名称">
          <el-input v-model="sFilters.goodsNames" placeholder="产品名称" ></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="sFilters.billStatus" clearable placeholder="状态" >
            <el-option
              v-for="item in ckdztStatus"
              :key="item.val"
              :label="item.ename"
              :value="item.val"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
        <el-switch v-model="outStockType" :active-text="OUTSTOCK_TYPE.PURCHASE.text" :active-value="OUTSTOCK_TYPE.PURCHASE.value" :inactive-text="OUTSTOCK_TYPE.PREORDER.text" :inactive-value="OUTSTOCK_TYPE.PREORDER.value" class="switch" @change="handleTypeChange" inactive-color="#13ce66"></el-switch>
        </el-form-item>
        <el-form-item>

        </el-form-item>
        <el-form-item style="margin-left: 375px">
          <el-button type="primary" icon="search" v-on:click="getOutBillList(1)" >查询</el-button>
          <el-button v-on:click="resetForm('searchForm')" >重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table :data="outBillList" highlight-current-row  style="width: 100%;" v-loading="loading" fit border  class ='inDeptA-tableheight'>
      <el-table-column prop="billId" label="出库单号" sortable width="150">
      </el-table-column>
      <el-table-column prop="outStockType" label="采购方式">
        <template slot-scope="scope">
          {{scope.row.outStockType==10?'实采':'虚采'}}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" :formatter="statusFormat">
      </el-table-column>
      <el-table-column prop="outOrgName" label="出库机构" width="200" sortable>
      </el-table-column>
      <el-table-column prop="outDeptName" label="出库部门" width="180" sortable>
      </el-table-column>
      <el-table-column prop="fillerName" label="制单人" width="200" sortable>
      </el-table-column>
      <el-table-column prop="fillDate" label="制单时间">
        <template slot-scope="scope">
          {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button  @click="showBill(scope.row.billId)" type="text">查看</el-button>
          <!--<el-button  @click="editBill(scope.row.id)" type="text" v-if="scope.row.status==10">编辑</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <!--分页条-->
    <el-col :span="24" class="toolbar">
      <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
      </el-pagination>
    </el-col>
  </el-row>
  <el-row style="height: 100%" v-show="visible.vOutBillView">
    <outStockView ref="outBillViewer" @close="closeOutBillViewer"></outStockView>
  </el-row>
  </div>
</template>

<script>
    import {OUTSTOCK_STATUS} from "../../../common/js/constance";
    import {OUTSTOCK_TYPE } from '../../../common/js/constance';
    import outStockView from './outStockBillView'
    export default {
      data(){
        return{
          loading:false,
          outBillList:[],
          ckdztStatus:[],
          outStockType:'',
          sFilters:{
            billId:'',
            goodsNames:'',
            startDate:Date.now() - 7 * 24 * 60 * 60 * 1000,
            outOrgId:this.user.corpId,
            outDeptId:this.user.orgId,
            endDate:Date.now(),
            billStatus:'',
          },
          total: 0,
          page: 1,
          pageSize: 30,
          visible:{
            vOutBillLst:true,
            vOutBillView:false
          },
        }
      },
      components:{outStockView},
      methods:{
        getOutBillList:function(pIndex){
          this.page = pIndex;
          var param = {
            orderBy: "",
            page: this.page,
            rows: this.pageSize,
            queryObject: {
              billId: this.sFilters.billId,
              goodsNames:this.sFilters.goodsNames,
              outStockType:this.outStockType,
              outOrgId:this.sFilters.outOrgId,
              outDeptId:this.sFilters.outDeptId,
              billStatus:this.sFilters.billStatus,
              startDate:this.sFilters.startDate,
              endDate:this.sFilters.endDate,
            }
          };
          this.loading =true;
          this.axios.post('/spdHERPService/stockMgr/out/listBill/listByPage',param).then(res => {
            this.loading =false;
            if(res.data.code == "0"){
              this.outBillList = res.data.data.data;
              this.total = this.outBillList.length;
            }else {
              this.loading =false;
              this.$message.error("获取出库单列表失败!");
            }
          },err=>{
            this.loading =false;
          })
        },
        resetForm:function () {
          this.sFilters.billId = "";
          //this.sFilters.startDate = "";
          //this.sFilters.endDate = "";
          this.sFilters.billStatus = "";
          this.sFilters.goodsNames = "";
          this.getOutBillList(1);
        },
        handleTypeChange(){
          this.getOutBillList(1);
        },
        showBill(billId){
          this.$refs.outBillViewer.show(billId);
          this.visible.vOutBillLst = false;
          this.visible.vOutBillView=true;
        },
        //editBill(){},
        closeOutBillViewer(){
          this.visible.vOutBillLst = true;
          this.visible.vOutBillView=false;
        },
        statusFormat(row){
          return OUTSTOCK_STATUS[row.status].text;
        },
        sizeChange(val){
          this.pageSize = val;
          this.getOutBillList(this.page);
        },
        currentChange(val){
          this.page = val;
          this.getOutBillList(this.page);
        },
        getOutStatus(){
          this.axios.get('/spdHERPService/stockMgr/out/listBill/getOutStatus',null).then(res => {
            if(res.data.code == "0"){
              this.ckdztStatus = res.data.data;
            }else {
              this.$message.error("获取出库单状态失败!");
            }
          },err=>{});
            },
          },
      computed:{
        OUTSTOCK_TYPE() {
          return OUTSTOCK_TYPE;
        },
      },
      mounted(){
        this.getOutStatus();
        this.getOutBillList(1);
      }
    };
</script>

<style>
  .inDeptA-tableheight{
    height:  calc(100% - 120px);
  }
</style>
