<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>{{title}}</span>
        </div>
        <el-form label-width="120px">
            <el-col :span="24">
                <el-form-item label="请选择产品">
                    <el-select size="small" style="width:100%" v-model="hosGoodsId" filterable remote reserve-keyword placeholder="ERP编码/品名/规格" :remote-method="getGoodsList"  
                    :loading="loading" @change="sltChange">
                        <el-option  v-for="item in goodsList" :key="item.id" :label="item.goodsName" :value="item.id">
                            <span style="float: left">{{ item.goodsName }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.erpCode }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="供应商:">
                    <span :class="{ err: !itemCls.isProv }">{{ goodsInfo.provName }}</span>
                </el-form-item>
            </el-col>
             <el-col :span="12">
                <el-form-item label="ERP编码:">
                    <span :class="{ err: !itemCls.isErp }">{{ goodsInfo.erpCode }}</span>
                </el-form-item>
            </el-col>   
            <el-col :span="12">
                <el-form-item label="医院:">
                    <span :class="{ err: !itemCls.isHos }">{{ goodsInfo.hosName }}</span>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="his编码:">
                    <span :class="{ err: !itemCls.isHis }">{{ goodsInfo.hitCode }}</span>
                </el-form-item>
            </el-col>   
            <el-col :span="12">
                <el-form-item label="品名:">
                    <span :class="{ err: !itemCls.isGoodsName }">{{ goodsInfo.goodsName }}</span>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="规格:">
                    <span :class="{ err: !itemCls.isGoodsGg }">{{ goodsInfo.goodsGg }}</span>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="单价:">
                    <span :class="{ err: !itemCls.isSalePrice }">{{ goodsInfo.salePrice }}</span>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="单位:">
                    <span :class="{ err: !itemCls.isUnit }">{{ goodsInfo.unit }}</span>
                </el-form-item>
            </el-col>                    
            <el-col :span="12">
                <el-form-item label="产地:">
                    <span :class="{ err: !itemCls.isMade }">{{ goodsInfo.made }}</span>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="厂家:">
                    <span :class="{ err: !itemCls.isMfrs }">{{ goodsInfo.mfrsName }}</span>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="注册证:">
                    <span :class="{ err: !itemCls.isCer }">{{ goodsInfo.certificateCode }}</span>
                </el-form-item>
            </el-col>
        </el-form>
    </el-card>
</template>
<script>
import moment from "moment";

export default {
  data() {
    return {
        loading:false,
        hosGoodsId:'',
        goodsInfo:{},
        goodsList:[],
        itemCls:{
            isProv:true,
            isErp:true,
            isHos:true,
            isHis:true,
            isGoodsName:true,
            isGoodsGg:true,
            isSalePrice:true,
            isUnit:true,
            isMade:true,
            isMfrs:true,
            isCer:true
        }
    };
  },
  components: { },
  props:{
      title:String
  },
  methods: {
      getGoodsList(query){
          if(query != ''){
              this.goodsList = [];
              this.loading = true;
              this.axios.get(`/spdHERPService/myGoods/hosGoods/getGoodsInfov/${query}`).then(res=>{
                  this.loading = false;
                  if(res.data.code == 0 && res.data.data.length>0){
                      this.goodsList = res.data.data;
                  }
              },err=>{
                  this.loading = false;
              })
          }
      },
      sltChange(hosGoodsId){
          this.goodsInfo ={};
          var goodsInfo = this.goodsList.find(item=>item.id==hosGoodsId);
          if(goodsInfo){
              this.goodsInfo = goodsInfo;
              this.$emit("onSelect",goodsInfo);
              this.$emit("input",hosGoodsId);
          }
      },
      compare(newGoodsInfo){
          if(newGoodsInfo && this.goodsInfo){
              this.itemCls.isProv = newGoodsInfo.provId == this.goodsInfo.provId;
              this.itemCls.isErp = newGoodsInfo.erpCode == this.goodsInfo.erpCode;
              this.itemCls.isHos = newGoodsInfo.hosId == this.goodsInfo.hosId;
              this.itemCls.isHis = newGoodsInfo.hitCode == this.goodsInfo.hitCode;
              this.itemCls.isGoodsName = newGoodsInfo.goodsName == this.goodsInfo.goodsName;
              this.itemCls.isGoodsGg = newGoodsInfo.goodsGg == this.goodsInfo.goodsGg;
              this.itemCls.isSalePrice = newGoodsInfo.salePrice == this.goodsInfo.salePrice;
              this.itemCls.isUnit = newGoodsInfo.unit == this.goodsInfo.unit;
              this.itemCls.isMade = newGoodsInfo.made == this.goodsInfo.made;
              this.itemCls.isMfrs = newGoodsInfo.mfrsName == this.goodsInfo.mfrsName;
              this.itemCls.isCer = newGoodsInfo.certificateCode == this.goodsInfo.certificateCode;
          }else{
              this.itemCls.isProv = true;
              this.itemCls.isErp = true;
              this.itemCls.isHos = true;
              this.itemCls.isHis = true;
              this.itemCls.isGoodsName = true;
              this.itemCls.isGoodsGg = true;
              this.itemCls.isSalePrice = true;
              this.itemCls.isUnit = true;
              this.itemCls.isMade = true;
              this.itemCls.isMfrs = true;
              this.itemCls.isCer = true;
          }
      }
  },
  mounted() {
  }
};
</script>

<style lang="scss">
.err{
    color:#F56C6C;
    font-weight: bold;
}
</style>