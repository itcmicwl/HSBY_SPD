<template>
   <el-row  id="dsCode" style="  height:100%">
         <el-row :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px;">

           
                        <el-form :inline="true" label-width="100px">
                            <el-form-item label="出库单号：">
                                <el-input v-model="filters.billId" placeholder="请输入出库单号" ></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" v-on:click="createCode" >查看/生成定数包</el-button>
                            </el-form-item>
                            <!--
                            <el-form-item>
                                <el-button type="primary" icon="search" v-on:click="getDSGoodsByOutBill(1)" >查询</el-button>
                            </el-form-item>
                            -->
                            <el-form-item>
                                <el-button type="primary" v-on:click="printW" >标签打印</el-button>
                            </el-form-item>
                        </el-form>
        </el-row>          
        <el-row style="height:calc(100% - 80px);" :gutter="10" v-loading='loading'>
            <el-col :span="18" style="height:100%">
                        <el-table :data="dsGoodsLst" highlight-current-row ref="goodsTable" style="height: 100%" 
                            @row-click="rowClick" border row-key="id">
                            <!-- <el-table-column type="index" label="序号" width="35" align="center"></el-table-column> -->
                            <el-table-column label="出库单号" prop="billId" min-width="100" show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column label="名称" prop="goodsName" min-width="100" show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column label="规格型号" prop="goodsGg" width="120" show-overflow-tooltip>
                            </el-table-column>
                           <el-table-column label="总数量" prop="totalQty" width="120" show-overflow-tooltip>
                            </el-table-column>
                             <el-table-column label="定数单位量（一包多少个）" prop="packetCode" width="120" show-overflow-tooltip>
                            </el-table-column>
                        </el-table>
          </el-col>
          <el-col :span="6" style="height:100%">
            <el-table ref="t_dsPacks" :data="dsPackes" style="width: 100%;" fit class="table_content" row-key="packageId"
                border height="outer" @selection-change ="codeChange">
                    <el-table-column type="selection" width="55" reserve-selection></el-table-column>
                    <el-table-column prop="packageId" label="包号">
                    </el-table-column>
          </el-table>
         </el-col>
        </el-row>
   </el-row>

</template>
<script>
    import moment from 'moment';
    import printDScode from './printDScode'

    export default {
        data() {
            return {
                dsGoodsLst:[],
                dsPackes:[],
                filters: {billId: ''},
                checkedCodes:[],
               
                loading: false
            }
        },
        methods: {
      
         
            // 查询请购出库单定数包商品
            getDSGoodsByOutBill: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    page: this.page,
                    rows: this.pageSize,
                    queryObject: {
                        billId:this.filters.billId,
                        outStockKind:40,// 出库类型：请购出库
                        isPacket:'1',// 定数包管理
                        //inDeptId: this.user.orgId,
                        status:30, //30已记帐
                        outOrgId:this.user.corpId
                    }
                };
                this.loading = true;
                this.axios.post('/spdHERPService/stockMgr/out4In/getDSGoodsByOutBill', param).then(res => {
                    this.loading = false;
                    this.dsGoodsLst = res.data.data.data;
                    this.total = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            rowClick(row){
                this.dsPackes=[];
                this.checkedCodes=[];
                let param ={orderBy:"",page:0,rows:0,
                            queryObject:{billId:row.billId,goodsId:row.goodsId}
                            };
                this.loading = true;
                this.axios.post('/spdHERPService/barcodePrintingCenter/hosPackageInfo/listDsPacksByOutBillId', param).then(res => {
                    this.loading = false;
                    res.data.data.data.forEach((d)=>{
                    let u =   this.dsPackes.find((t)=>{ return t.packageId==d.packageId})
                      if(typeof(u)=='undefined')
                        this.dsPackes.push(d);
                    })         
                }, err => {
                    this.loading = false;
                });
                     
             },
            printW() {
                if(this.checkedCodes.length ==0){
                    this.$message.error('请至少勾选一条定数包');
                    return false;
                    }
                    printDScode(this.checkedCodes);
            },
            codeChange(rows){
                    this.checkedCodes = rows;
            },
            createCode(){
                 this.dsPackes=[];
                this.checkedCodes=[];
                this.dsGoodsLst=[];
                if(this.filters.billId==null){
                    this.$message.error('请输入出库单号')
                    return
                }
                let that =this;
                this.axios.get('/spdHERPService/barcodePrintingCenter/hosPackageInfo/createDsCode', {
                    params: {
                        id: this.filters.billId,
                        isPacket:'1'
                    }
                }).then(res => {
                    if (res.data.code === 0) {
                        that.getDSGoodsByOutBill();   
                    } else {
                        res.data.msg;
                    }
                }).catch(err => {
                })


            },


        },
        mounted() {
            this.hosId = this.user.corpId; 
            this.filters.billId = this.$route.query.billId;
            if(this.filters.billId){
                this.createCode();
            }
           
        }
    }
</script>
<style>
 #dsCode .table_content{
    height: 100%
  }
</style>

