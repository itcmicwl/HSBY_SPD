<template>
    <el-row  id="uniqueCode" style="  height:100%">
      <el-row :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px;">
          <el-form :inline="true" :models="filters" labelWidth="80px" ref="disBillId">
            <el-form-item label="出库单" prop="billId">
              <el-input v-model="filters.billId" :clearable="true" placeholder="请输入出库单号"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" v-on:click="createCode" >查看/生成唯一码</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" v-on:click="printWym" >RFID标签打印</el-button>
              <el-button type="primary" v-on:click="printW" >普通标签打印</el-button>
            </el-form-item>
          </el-form>
      </el-row>
      <el-row style="height:calc(100% - 80px);" :gutter="10" v-loading='loading'>
        <el-col :span="18" style="height:100%">
          <el-table ref="distrDetail" :data="distrBillVo.distrBillList" style="width: 100%;" fit class="table_content"
          row-key="id" border height="outer" @row-click="rowClick" @select="prSelect" @select-all="pAllSelect"  highlight-current-row>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="rowNumber" label="序号" width="55">
            </el-table-column>
            <el-table-column prop="provGoodsErpName" label="商品名称" width="200">
            </el-table-column>
             <el-table-column prop="hosQty" label="数量" width="90">
            </el-table-column>
            <el-table-column prop="hosUnit" label="单位" width="60">
            </el-table-column>
            <el-table-column prop="goodsGg" label="规格" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="made" label="产地" width="90">
            </el-table-column>

          </el-table>
        </el-col>
        <el-col :span="6" style="height:100%">
          <el-table ref="t_uniqueCode" :data="uniqueVoList" style="width: 100%;" fit class="table_content" row-key="id"
          border height="outer" @selection-change ="codeChange">
            <el-table-column type="selection" width="55" reserve-selection></el-table-column>
            <el-table-column prop="uniqueCode" label="唯一码">
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <!-- <uniqueCodePrint ref ="printcode"></uniqueCodePrint> -->
    </el-row>
</template>
<script>
  import moment from 'moment';
  import printBarcode from './printBarcode'
  export default {
    data(){
      return{
        filters:{
          billId:''
        },
        checkedCodes:[],
        distrBillVo:{},
        uniqueVoList:[],
        pSelectCount:0,
        loading: false,
        forRivaH02:{
          billId:'',
          checkCodes:[]
        }
      }
    },
    // components:{uniqueCodePrint},
    methods:{
      printW() {
        if(this.checkedCodes.length ==0){
           this.$message.error('请至少勾选一条唯一码');
           return false;
        }
        printBarcode(this.checkedCodes);
      },
      printWym:function () {
        if(this.checkedCodes.length ==0){
          this.$message.error('请至少勾选一条唯一码');
          return false;
        }
        this.forRivaH02.billId=this.filters.billId;
        this.forRivaH02.checkCodes = this.checkedCodes;
        this.loading = true;
        this.axios.post('/spdHERPService/forRivaInterface/print/print',this.forRivaH02).then(res=>{
          this.loading = false;
          if(res.data.code === 0){
            this.$message.success("打印条码成功！");
          }else {
            this.$message.error("打印条码失败！");
          }
        },err=>{this.loading = false;this.$message.error("打印条码失败！");});
      },
      pAllSelect(selection){
        var _this = this;
        this.rowClick(this.distrBillVo.distrBillList[0]);
        this.$nextTick(()=>{
          if(selection.length == 0  ){
            this.$refs.t_uniqueCode.clearSelection();
            this.checkedCodes =[];
          }else{
            this.distrBillVo.distrBillList.forEach(item=>{
              item.distrBillUniqueList.forEach(code=>{
                _this.$refs.t_uniqueCode.toggleRowSelection(code,true);
              });
            });
          }
          this.pSelectCount = selection.length;
        });
      },
      prSelect(selection,row,e){
        var _this = this;
        this.rowClick(row);
        this.$nextTick(()=>{
          if(selection.length > _this.pSelectCount){
              row.distrBillUniqueList.forEach(item=>{
              _this.$refs.t_uniqueCode.toggleRowSelection(item,true);
            });
          }else{
             row.distrBillUniqueList.forEach(item=>{
              _this.$refs.t_uniqueCode.toggleRowSelection(item,false);
            });
          }
          this.pSelectCount = selection.length;
        });

      },
      codeChange(rows){
        this.checkedCodes = rows;
      },
      rowClick(row){
        this.uniqueVoList = row.distrBillUniqueList;
      },
      clear(){
        this.distrBillVo = {distrBillList:[]};
        this.uniqueVoList=[];
        this.$refs.t_uniqueCode.clearSelection();
        this.$refs.distrDetail.clearSelection();
        this.checkedCodes =[];
      },
      createCode:function () {
        this.clear();
        var param = {
          billId: this.filters.billId,
        }
        this.loading=true
        let temp =[]
        this.axios.post('/spdHERPService/barcodePrintingCenter/uniqueCode/create',param).then(res=>{
          this.loading=false;
            if(res.data.code=='0'){
                this.distrBillVo =res.data.data
              //获取配送单中需要打印的内容
              this.distrBillVo.distrBillList.forEach(item=>{
                item.distrBillUniqueList.forEach(code=>{
                   code.goodsName =item.hosGoodsName
                   code.goodsGg = item.goodsGg
                   code.expdtEndDate = item.expdtEndDate
                });
              });
            }else{
              this.$message.error(res.data.msg)
            }
        },err =>{
            this.loading=false
          }
        )

      }
    }
  }
</script>
<style>
  .printBarcode{
    font-size: xx-small;
    color: black;
    margin-bottom: 45px;
  }
  .barDesc{
    margin-left: 9px;
    margin-bottom: -5px;
    height: 55px;
  }
  #uniqueCode .table_content{
    height: 100%
  }
</style>
