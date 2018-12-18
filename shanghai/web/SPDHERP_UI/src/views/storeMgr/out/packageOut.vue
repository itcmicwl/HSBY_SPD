<template>
  <out-pkg-component :base-pkg-url="basePkgUrl" :out-stock-kind="outStockKind" out-type="出库"></out-pkg-component>
</template>

<script>
  import outPkgComponent from "./outComponentPkg";
  import { OUTSTOCK_BILL_KIND } from "../../../common/js/constance";
  export default {
    components: {
      outPkgComponent
    },
    data() {
      return {
        basePkgUrl:"/spdHERPService/stockMgr/out/packageOut",
        outStockKind:OUTSTOCK_BILL_KIND.PACKAGE.value,
      };
    },
    methods:{
    },
    mounted(){
    }
  }
</script>

<style>

</style>
