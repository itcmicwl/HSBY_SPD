<template>
  <el-row id="re_money" class="full-height" v-loading="diaLoging">
      <el-row class="toolbar">
           <el-form label-width="120px">
                <el-col :span="8">
                    <el-form-item label="医院：">
                        <span>{{ inStockBill.outOrgName }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="退货库房:">
                    <sltStockByUser  ref="childMethod"  v-on:sltChange="getSelected_Stock" :hosId="this.user.corpId" :userId="this.user.userId" :inStocCode="inStockBill.inStocCode"></sltStockByUser>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="退货部门:">
                        <span>{{ inStockBill.inDeptName }}</span>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                    <el-form-item label="制单时间:">
                        <span>{{ inStockBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="制单人:">
                        <span>{{ inStockBill.fillterName }}</span>
                    </el-form-item>
                </el-col>
            </el-form>
      </el-row>
     <barcodeResolver :barcodeKind="barcodeKind.JFTH" :hosId="user.corpId"
        :deptId="user.orgId" :userId="user.userId" @resolved="onBarcodeResolved"></barcodeResolver>
      <el-table :data="inStockBill.lstDetail" style="width: 100%;" class="mytable" border v-loading='tloading'>
        <el-table-column label="操作" width="60">
          <template slot-scope="scope">
              <div class="icoBtn">
                  <i class="fa fa-trash-o fa-lg" @click="delRow(scope.row)" title="删除" style="color:red;margin-left:5px;"></i>
              </div>
          </template>
        </el-table-column>
        <el-table-column label="序号" prop="inBillRow" width="60">
        </el-table-column>
        <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="规格" prop="goodsGg">
        </el-table-column>                   
        <el-table-column label="退货数量" prop="inQty" width="90">  
          <template slot-scope="scope">
                {{scope.row.inQty | filterReturn}}
            </template>                      
        </el-table-column>
        <el-table-column label="单位" prop="unit">
        </el-table-column>
        <el-table-column label="批号" prop="batchCode">
        </el-table-column>
        <el-table-column label="灭菌批号" prop="sterilizationCode">
        </el-table-column>
        <!-- <el-table-column label="灭菌有效日期" width="130">
            <template slot-scope="scope">
                {{scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD')}}
            </template>
        </el-table-column> -->
        <el-table-column label="灭菌日期" width="130">
            <template slot-scope="scope">
                {{scope.row.sterilizationDate | dateFormat('YYYY-MM-DD')}}
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
        <el-col :span="20" class="toolbar" style="padding-bottom: 0px;text-align: center;">
            <el-button   @click.native="cancel()">取消</el-button>
            <el-button type="primary" @click.native="submitBill(10)" >保存</el-button>
            <el-button type="primary" @click.native="submitBill(20)" >提交</el-button>
        </el-col>
  </el-row>
</template>

<script>
import orgTreeSlt from "@/components/orgTreeSlt";
import barcodeResolver from '../common/barcodeResolver';
import sltStockByUser from "@/components/sltStockByUser";
import {
  DISTRBILL_STATUS,
  BARCODEKIND,
  BARCOD_RES_TYPE
} from "../common/constance";
var Child = {
  template: '<div><p v-for="item in errMsg" v-html="item"></p></div>',
  props: ['errMsg']
};
export default {
  data() {
    return {
        barcodeKind: BARCODEKIND,
        tloading:false,
        diaLoging:false,
        opModel:'ADD',
        inStockBill:{
            inStockType:20,
            inStockKind:5,
            outOrgName :'',
            outOrgId :'',              
            inDeptName  :'',            
            inDeptId :'',
            fillDate :'',
            fillterName :'',
            lstDetail:[]
        }
    };
  },
  filters:{
    filterReturn(data){
      return Number(data)*-1;
    }
  },
  components: { orgTreeSlt,barcodeResolver,sltStockByUser },
  methods: {
    getSelected_Stock(temp) {
      this.inStockBill.inStocId = temp[1];      
    },
    onBarcodeResolved(barKind,data){
      if(barKind == BARCOD_RES_TYPE.ZBM){
        this.checkRow4wym(data);
      }
    },
    checkRow4wym(code){
      let  pipeiFlag =false;
      let wymArray = [];
      if(this.inStockBill.lstDetail.length>0){
        this.inStockBill.lstDetail.map(o=>wymArray = wymArray.concat(o.lstUniqueCode.map(item=>item.uniqueCode)));
      }
      if(wymArray.find(u=>{return u==code} )){
        this.$message.error('该唯一码已扫描，且有明细行匹配!')
        return
      }
      this.tloading = true;
      let _this = this;
      this.getSickerUserListByUniqueCode(code).then(res=>{
        _this.tloading = false;
        if(res.data.code==0 && res.data.data){
          _this.getInstockDetail(res.data.data);
        }
      },err=>{_this.tloading = false;});
    },
    getSickerUserListByUniqueCode(code){
      var param={
        hosId:this.inStockBill.outOrgId,
        deptId:this.inStockBill.inDeptId,
        uniqueCode:code
      };
      return  this.axios.post(`/spdHERPService/stockMgr/inStock/getInstockLstByUniqueCode`,param);
    },
    getInstockDetail(sikerLstItem){
      sikerLstItem.inQty = -1;
      sikerLstItem.lstBatch.forEach(item=>{
        item.qty = -1;
      });
      this.inStockBill.lstDetail.push(sikerLstItem);
      this.setDetailRowIndex();
    },
    setDetailRowIndex(){
      this.inStockBill.lstDetail.forEach((item,index)=>{
        item.inBillRow = index+1;
      });
    },
    delRow(pd){
      this.inStockBill.lstDetail
      var index = this.inStockBill.lstDetail.findIndex(item => { return item.inBillRow == pd.inBillRow });
      this.inStockBill.lstDetail.splice(index, 1)
      this.setDetailRowIndex();
    },
    submitBill(state){
      this.diaLoging = true;
      const h = this.$createElement;
      this.inStockBill.status = state;
      var serviceUrl = this.opModel == 'ADD' ? '/spdHERPService/stockMgr/inStock/add4Return' : '/spdHERPService/stockMgr/inStock/update4Return'
      var _this = this
      this.axios.post(serviceUrl, this.inStockBill).then(res => {
        _this.diaLoging = false;
        if (res.data.code == '0') {
          _this.$msgbox({ title: '提示', message: '操作成功', type: 'success' })
          var billId = res.data.data.billId
          _this.$msgbox({
            title: '提示',
            message: '操作成功',
            type: 'success',
            callback: action => {
              _this.$router.push({ path: 'reMBilllList', query: { inStockBillId: billId }})
            }
          })
        } else if (res.data.code == '-122') {
          var msg = res.data.validateErrors.fieldErrors.map(x => x.message)
          _this.$msgbox({
            title: '错误',
            type: 'error',
            message: h(Child, {props: {errMsg: msg}})
          })
        } else {
          this.$msgbox({ title: '提示', message: '操作失败', type: 'error' })
        }
      }, err => {
        this.diaLoging = false
        this.$msgbox({ title: '提示', message: '操作失败', type: 'error' })
      })
    },
    getReceBill(billId) {
        this.loading = true;
        var _this = this;
        this.axios.get('/spdHERPService/stockMgr/inStock/getById?id=' + billId).then(res=>{
            _this.loading = false;
            if(res.data.code == 0){
                _this.inStockBill = res.data.data;
            }
        }, err => {
            _this.loading = false;
        });
    },
    showEditer(billId){
      if(billId){
        this.getReceBill(billId);
      }else{
        this.inStockBill.outOrgName = this.user.corpName;
        this.inStockBill.outOrgId = this.user.corpId;
        this.inStockBill.outDeptId = this.user.orgId;
        this.inStockBill.outDeptName = this.user.orgName;
        this.inStockBill.inOrgId = this.user.corpId;
        this.inStockBill.inOrgName = this.user.corpName;
        this.inStockBill.inDeptName = this.user.orgName;
        this.inStockBill.inDeptId = this.user.orgId;
        this.inStockBill.fillDate = new Date();
        this.inStockBill.filler=this.user.userId;
        this.inStockBill.fillterName = this.user.ename;
      }
    },
    cancel(billId){
      this.$emit("close",billId);
    }
  },
  mounted() {
      
  }
};
</script>

<style scoped>
.full-height {
  height: 100%;
}
.text-style {
  padding-left: 20px;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  padding-top: 2px;
}
.el-cascader--mini {
  width: 178px;
}
#re_money .mytable {
  height: calc(100% - 150px);
}
#re_money .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}
</style>