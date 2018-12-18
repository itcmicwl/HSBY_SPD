<template>
  <el-row>
    <el-col :span="3" class="toolbar" style="padding-bottom: 0px; line-height: 25px;">
            <el-switch v-model="barcodeType" active-text="仅主码" inactive-text="主副码" @change="barcodeTypeChange">
            </el-switch>
    </el-col>
    <el-col :span="20" class="toolbar" v-show="barcodeType" style="padding-bottom: 0px;">
        <el-input type="text" class="input-barcode" ref="mbarcode" v-model="barcode" @keyup.native.enter="barcodeResolve" autosize placeholder="请录入条形码">
        </el-input>
    </el-col>
    <el-col :span="20" class="toolbar" v-show="!barcodeType" style="padding-bottom: 0px;">
      <el-col :span="10">
          <el-input type="text" class="input-barcode" ref="mainBarcode" v-model="mainBarcode" @keyup.native.enter="barcodeNext" autosize placeholder="请录入条形码主码">
          </el-input>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="10">
          <el-input type="text" class="input-barcode" ref="fbarcode" v-model="fBarcode" @keyup.native.enter="barcodePre" autosize placeholder="请录入条形码副码">
          </el-input>
      </el-col>
    </el-col>
  </el-row>
</template>
<script>
import barcodeUtil from '@/common/js/barcodeUtil'
import {BARCODEKIND,BARCOD_RES_TYPE} from './constance';
  export default {
    data() {
        return {
          barcodeType:true,
          barcode:'',
          mainBarcode:'',
          fBarcode:''
        }
    },
    props: ['barcodeKind', 'hosId', 'deptId','userId'],
    filters: {

    },
    methods: {
      barcodeTypeChange (val) {
        this.$nextTick(function () {
          if (val) {
            this.$refs.mbarcode.$el.children[0].focus()
            this.$refs.mbarcode.$el.children[0].select()
          } else {
            this.$refs.mainBarcode.$el.children[0].focus()
            this.$refs.mainBarcode.$el.children[0].select()
          }
        })
      },
      barcodeResolve () {
        var _this = this;
        if(this.barcodeKind == BARCODEKIND.SSB){
            this.$emit('resolved',BARCOD_RES_TYPE.ZBM,this.barcode);
            this.$refs.mbarcode.$el.children[0].focus();
            this.$refs.mbarcode.$el.children[0].select();
            return;
        }
        if(this.barcode.indexOf('E') == 0){     //唯一码，由于采购入库根据配送单生成，自行处理
              this.$emit('resolved',BARCOD_RES_TYPE.ZBM,this.barcode,this.barcode);
              this.$refs.mbarcode.$el.children[0].focus();
              this.$refs.mbarcode.$el.children[0].select();
        }else if(this.barcode.indexOf('B') == 0){
              barcodeUtil.parseCombinedBarcode(this.barcode, null, this.hosId,null).then(res => {
                var goodsLst = res.data.data
                _this.$refs.mbarcode.$el.children[0].focus();
                _this.$refs.mbarcode.$el.children[0].select();
                if(res.data.code == 0){
                  if(res.data.data.length > 0){
                    _this.$emit('resolved',BARCOD_RES_TYPE.DSB,goodsLst,_this.barcode);
                  }else{
                    _this.$message.error("无效条码!");
                  }
                }
              });
        }else if(this.barcode.indexOf('A') == 0){
              this.$emit('resolved',BARCOD_RES_TYPE.SSB,null,this.barcode);
              this.$refs.mbarcode.$el.children[0].focus();
              this.$refs.mbarcode.$el.children[0].select();         
        }else{
          barcodeUtil.parseCombinedBarcode(this.barcode, null, this.hosId,null).then(res => {
            var goodsLst = res.data.data
            // this.setSendGoods(goodsLst)
            _this.$refs.mbarcode.$el.children[0].focus();
            _this.$refs.mbarcode.$el.children[0].select();
            _this.$emit('resolved',BARCOD_RES_TYPE.GS1,goodsLst,this.barcode)
          });
        }
      },

      barcodeNext () {
        this.fBarcode = ''
        this.$refs.fbarcode.$el.children[0].focus()
        this.$refs.fbarcode.$el.children[0].select()
      },
      barcodePre () {
        barcodeUtil.parseCombinedBarcode(this.mainBarcode, this.fBarcode, this.distrBill.purchaseCompanyId, this.distrBill.provId).then(res => {
          var goodsLst = res.data.data
          this.setSendGoods(goodsLst)
          this.$refs.mainBarcode.$el.children[0].focus()
          this.$refs.mainBarcode.$el.children[0].select()
          this.mainBarcode = ''
          this.fBarcode = ''
        })
      },
    },
    mounted() {

    }
}
</script>
