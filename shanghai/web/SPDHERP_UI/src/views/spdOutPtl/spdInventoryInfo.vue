<template>
  <div style="height: 100%;">
    <el-row style="height: 100%" v-show="visible.vSpdInventory">
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;margin-top: 5px" >
        <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters" style="margin: 2px;">
          <el-form-item label="盘点单号">
            <el-input v-model="sFilters.billId" placeholder="盘点单号"></el-input>
          </el-form-item>

          <el-form-item >
            <el-button type="primary" icon="search" v-on:click="getSpdInventoryHandle(1)" >查询</el-button>
            <el-button v-on:click="resetForm('searchForm')" >重置</el-button>
            <el-button type="info" v-on:click="insert" :disabled="inventDisable">生成盘点单</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!--列表-->
      <el-table :data="spdInventory" highlight-current-row  style="width: 100%;" v-loading="loading" fit border  class ='inDeptA-tableheight'>
        <el-table-column prop="id" label="盘点单号" sortable width="200">
        </el-table-column>
        <el-table-column prop="hosName" label="医院名称" sortable width="200">
        </el-table-column>
        <el-table-column prop="operationer" label="操作人" sortable width="150">
        </el-table-column>
        <el-table-column prop="operationtime" label="操作时间" sortable width="150">
        </el-table-column>
        <el-table-column prop="state" label="状态" :formatter="statusFormat">
        </el-table-column>
        <el-table-column label="操作" width="260">
          <template slot-scope="scope">
            <el-button  @click="showDetailList(scope.row.id)" type="text">查看</el-button>
            <el-button  @click="batchOperation(scope.row.id)" type="text" v-if="scope.row.state==10">批量盘点</el-button>
            <el-button  @click="closeSpdInventory(scope.row.id)" type="danger text" size="mini" v-if="scope.row.state==10">关单操作</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog
        title="提示"
        :visible.sync="visible.dialogVisible"
        width="30%">
        <span>确认关闭当前盘点单吗？</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="visible.dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="handleClose":loading="closeBillLoading">确 定</el-button>
  </span>
      </el-dialog>

      <el-dialog :title="'jde库存信息'" :loading="confirmBtnLoading":visible.sync="visible.dlgVisible" width="80%" top="10vh">
        <el-table ref="detailTable" :data="jdeGoodsStockInfo" highlight-current-row border height="400px">
          <el-table-column type="index" label="序号" width="45" align="center"></el-table-column>
          <el-table-column label="名称" prop="goodsName" show-overflow-tooltip></el-table-column>
          <el-table-column label="规格" prop="goodsGg" show-overflow-tooltip></el-table-column>
          <el-table-column label="生产批号" prop="batchCode"></el-table-column>
          <el-table-column label="库位" width="200" prop="goodsStockLocation" align="center">
          </el-table-column>
          <el-table-column label="库存数量" width="60" prop="stockSupplyQty"></el-table-column>
          <el-table-column label="效期" width="150">
            <template slot-scope="scope">
              {{scope.row.expirationDate | dateFormat('YYYY-MM-DD')}}
            </template>
          </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="visible.dlgVisible = false">取消</el-button>
          <el-button :loading="confirmBtnLoading" @click="handleDlgConfirm()" type="primary">提交</el-button>
        </div>
      </el-dialog>

      <!--分页条-->
      <el-col :span="24" class="toolbar">
        <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
        </el-pagination>
      </el-col>
    </el-row>

    <el-row style="height: 100%" v-show="visible.vSpdInventoryList">
      <spdInventoryInfoView ref="spdInventoryInfoViewr" @close="closeInventoryInfoView"></spdInventoryInfoView>
    </el-row>
  </div>
</template>

<script>
  import {INVENTORY_STATUS} from "../../common/js/constance";
  import spdInventoryInfoView from './spdInventoryInfoView'
  export default {
    data(){
      return{
        visible:{
          vSpdInventory:true,
          vSpdInventoryList:false,
          dlgVisible:false,
          dialogVisible:false,
        },
        spdInventory:[],
        sFilters:{
          billId:'',
        },
        total: 0,
        page: 1,
        pageSize:30,
        loading:false,
        spdInventoryVo:{
          spdInventoryLists:[],
          spdOutPtlList:[],
          id:'',
          hosId:'',
          hosName:'',
          operationer:'',
          operationtime:'',
        },
        jdeGoodsStockInfo:[],
        confirmBtnLoading:false,
        inventDisable:true,
        closeBillLoading:false,
      }
    },
    components:{spdInventoryInfoView},
    methods:{
      handleClose(){
        this.closeBillLoading = true;
        var param = {
          hosId:this.user.corpId,
        };
        this.axios
          .post('/spdHERPService/spdOutPtl/spdInventory/closeBill',param)
          .then(res => {
            this.closeBillLoading = false;
            if (res.data.code === 0) {
              this.$message.success("关闭盘点单成功！");
              this.visible.dialogVisible = false;
              this.getSpdInventory(1);
            }else {
              this.handleError(res.data.msg);
            }
          });
      },
      closeSpdInventory(billId){
       this.visible.dialogVisible = true;
      },
      //提交状态生成盘点单
      handleDlgConfirm() {
        this.confirmBtnLoading = true;
        var param = {
          hosId:this.user.corpId,
          hosName:this.user.corpName,
          operationer:this.user.ename,
        };
        this.axios
          .post('/spdHERPService/spdOutPtl/spdInventory/add',param)
          .then(res => {
            this.visible.dlgVisible = false;
            if (res.data.code === 0) {
              this.$message.success("生成盘点单成功！");
              this.visible.dlgVisible = false;
              this.getSpdInventory(1);
            } else {
              this.handleError(res.data.msg);
            }
            this.confirmBtnLoading = false;
          });
        this.dlgVisible = false;
      },
      handleError(err) {
        return new Promise((resolve, reject) => {
          this.$msgbox({ title: "错误", type: "error", message: err })
            .then(action => {
              resolve(action);
            })
            .catch(err => {
              reject(err);
            });
        });
      },
      getSpdInventory:function (pIndex) {
        this.page = pIndex;
        var param = {
          orderBy: "",
          page: this.page,
          rows: this.pageSize,
          queryObject: {

          }
        };
        this.loading =true;
        this.axios.post('/spdHERPService/spdOutPtl/spdInventory/listByPage',param).then(res => {
          this.loading =false;
          if(res.data.code == "0"){
            this.spdInventory = res.data.data.data;
            this.total = res.data.data.total;
            let comspdInventory = this.spdInventory.find(o=>{
              return o.state === '10'
            })
            if(comspdInventory){
              this.inventDisable = true;
            }else {
              this.inventDisable = false;
            }
          }else {
            this.$message.error("获取盘点单列表失败!");
          }
        })
      },
      getSpdInventoryHandle:function(pIndex){
        if(this.sFilters.billId==''){
          this.getSpdInventory(1);
        }else {
          this.page = pIndex;
          var param = {
            orderBy: "",
            page: this.page,
            rows: this.pageSize,
            queryObject: {
              id:this.sFilters.billId,
            }
          };
          this.loading =true;
          this.axios.post('/spdHERPService/spdOutPtl/spdInventory/listByPage',param).then(res => {
            this.loading =false;
            if(res.data.code == "0"){
              this.spdInventory = res.data.data.data;
              this.total = res.data.data.total;
              this.sFilters.billId = '';
            }else {
              this.$message.error("获取盘点单列表失败!");
            }
          })
        }
      },
      getJdeInfo(){
        var param ={
          hosId:this.user.corpId
        };
        this.axios.post('/spdHERPService/jde/jdeGoodsStockInfo/list',param).then(res => {
          if(res.data.code == "0"){
            this.jdeGoodsStockInfo = res.data.data;
          }else {
            this.$message.error("获取jde库存失败!");
          }
        });
      },
      resetForm:function () {
        this.sFilters.billId = "";
        this.getSpdInventory(1);
      },
      statusFormat(row){
        return INVENTORY_STATUS[row.state].text;
      },
      showDetailList:function (billId) {
        this.$refs.spdInventoryInfoViewr.show(billId);
        this.visible.vSpdInventory = false;
        this.visible.vSpdInventoryList=true;
      },
      batchOperation:function (billId) {
          var param = {
            id:billId
          };
          this.axios.post('/spdHERPService/spdOutPtl/spdInventory/update',param).then(
            res => {
              if (res.data.code === 0) {
                this.$message.success("批量盘点成功！")
                this.getSpdInventory(1);
              }else {
                this.handleError(res.data.msg);
              }
            }
          );
      },
      sizeChange(val){
        this.pageSize = val;
        this.getSpdInventory(this.page);
      },
      currentChange(val){
        this.page = val;
        this.getSpdInventory(this.page);
      },
      closeInventoryInfoView(){
        this.visible.vSpdInventory = true;
        this.visible.vSpdInventoryList=false;
      },
      //新增盘点单待完善
      insert(){
        this.visible.dlgVisible = true;
      },
    },
    mounted(){
      this.getSpdInventory(1);
      this.getJdeInfo();
    },
  }
</script>

<style>
  .inDeptA-tableheight{
    height:  calc(100% - 90px);
  }
</style>
