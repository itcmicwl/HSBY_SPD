<template>
    <el-row id="add-purchase-order" :gutter="20" style="height:90%">
      <el-col :span="10"><goodsSelect v-model="newGoodsId" title="新品种" @onSelect="onSelectNew"></goodsSelect></el-col>
      <el-col :span="10"><goodsSelect v-model="oldGoodsId" title="旧品种" ref="goodsSlt" @onSelect="onSelectOld"></goodsSelect></el-col>
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;text-align: center;">
            <el-button type="primary" size="small" :disabled="canChange()" @click.native="changeGoods()" >替换</el-button>
        </el-col>
    </el-row>
</template>
<script>
import util from "@/common/js/util.js";
import goodsSelect from './goodsSelect'
export default {
  data() {
    return {
      loading:false,
      newGoodsId:'',
      newErpCode:'',
      oldGoodsId:'',
      newGoodsInfo:null,
    };
  },
  components: {goodsSelect },
   methods: {
    canChange(){
      return !(this.newGoodsId.length>0 && this.oldGoodsId.length>0);
    },
    onSelectNew(goodsInfo){
       this.newGoodsInfo = {};
       this.newErpCode = '';
       this.newGoodsId='';
      if(goodsInfo){
        this.newGoodsInfo = goodsInfo;
        this.newErpCode = goodsInfo.erpCode;
        this.$refs.goodsSlt.compare(this.newGoodsInfo);
      }      
    },
    onSelectOld(){
      this.$refs.goodsSlt.compare(this.newGoodsInfo);
    },
    changeGoods(){
      if(this.newGoodsId && this.oldGoodsId && this.newErpCode){
        this.$confirm('确定要替换吗？', '提示', { confirmButtonText: '确定',  cancelButtonText: '取消',  type: 'warning'}).then(() => {
          this.loading = true;
          this.axios.post(`/spdHERPService/myGoods/hosGoods/changeHosGoods/${this.newGoodsId}/${this.oldGoodsId}/${this.newErpCode}`).then(res=>{
            this.loading = false;
            if(res.data.code == 0 && res.data.data>0){
              this.$message.success('替换成功!');
            }else{
              this.$message.error('操作失败!');
            }
          },err=>{
            this.loading = false;
            this.$message.error('操作失败!');
          });
          
        }).catch(() => { });
      }
    }
  },
  mounted() {
  }
};
</script>

<style lang="scss">
.middle-i{
  height:100%;
  width:100%;
}
.middle-i i{
    height:200px;
    width:200px;
    position: absolute;
    top: 30%;
    left:40%
  }
</style>