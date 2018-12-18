<template>
<div style="height: 100%">
  <el-row id="sick-use" class="full-height">
      <el-row class="toolbar" :gutter = "10">
        <el-col :span="24" id="sickerUseV">
        <el-col :span="18" class="toolbar" style="padding-bottom: 0px;" >
          <el-form :inline="true"  ref="sickerUseViewVo">
               <el-col :span="8">
                    <el-form-item label="病人姓名:">
                        <span>{{sickerUseViewVo.patientName}}</span>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                    <el-form-item label="病人标识号:">
                        <span>{{sickerUseViewVo.patientId}}</span>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                    <el-form-item label="执行科室:">
                        <span>{{sickerUseViewVo.deptName}}</span>
                    </el-form-item>
                </el-col>
               
                <el-col :span="8">
                    <el-form-item label="执行医生:">
                        <span>{{sickerUseViewVo.execDoctor}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="执行护士:">
                        <span>{{sickerUseViewVo.execNurse}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="手术室:">
                        <span>{{sickerUseViewVo.operationRoom}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="使用日期:">
                        <span>{{sickerUseViewVo.useDate}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="登记人:">
                        <span>{{sickerUseViewVo.fillerName}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="登记日期:">
                        <span>{{sickerUseViewVo.fillDate}}</span>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-col>
        </el-col>
      </el-row>
      <el-table :data="sickerUseViewVo.lstDetail" style="width: 100%;" class="mytable" border> 
            <el-table-column type="index" label="序号" width="90" align="center" ></el-table-column>     
            <el-table-column prop="id" label="id" v-if="false" width="35" align="center" ></el-table-column>     
            <el-table-column prop="goodsName" label="产品名称" width="180" align="center" ></el-table-column>     
            <el-table-column prop="goodsGg" label="产品规格"  width="90" align="center" ></el-table-column>     
            <el-table-column prop="hisPrice" label="收费单价"  width="90" align="center" ></el-table-column>     
            <el-table-column prop="price" label="单价" v-if="false" width="90" align="center" ></el-table-column>     
            <el-table-column prop="batchCode" label="批号" width="180" ></el-table-column>
            <el-table-column prop="goodsBatchId" label="批次" width="180" ></el-table-column>
            <el-table-column prop="unit" label="单位" width="90"></el-table-column>       
            <el-table-column prop="returnQty" label="退货数量" width="90"></el-table-column>       
            <el-table-column prop="useQty" label="使用数量"></el-table-column>
    </el-table>
        <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;text-align: center;">
            <el-button :plain="true" type="danger"  @click="print()">打印</el-button>
            <el-button :plain="true" type="danger"  @click="goBack">返回</el-button>
        </el-col>
  </el-row>
     <sickerUseVprint ref="printD"></sickerUseVprint>
</div>
</template>

<script>
import orgTreeSlt from "@/components/orgTreeSlt";
import sickerUseVprint from './sickerUseVprint';

export default {
  data() {
    return {
      baseURL: "/spdHERPService/stockout/sickerUse",
      sickerUseViewVo: {
        id: "",
        hosId: "",
        patientId: "",
        patientInHosId: "",
        useDate: "",
        execNurse: "",
        execDeptId: "",
        deptName: "",
        operationRoom: "",
        status: "",
        filler: "",
        fillerName: "",
        fillDate: "",
        lastUpdateDatetime: "",
        version: "",
        lstDetail:[]
      }
    };
  },
  components: { orgTreeSlt,sickerUseVprint },
  methods: {
      print(){
          var billId = this.sickerUseViewVo.id;
           this.$refs.printD.print(billId);
        //    this.$refs.printD.print(Object.assign({},this.sickerUseViewVo.id));
      },
      goBack() {
            this.$emit("close");
        },
      show(billId){
          var _this = this;
         this.axios.post(this.baseURL + "/getById?id="+billId).then(
        res => {
          this.sickerUseViewVo = res.data.data;
        },
        err => {}
      );
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
#sick-use .mytable {
  height: calc(90% - 150px);
}
</style>