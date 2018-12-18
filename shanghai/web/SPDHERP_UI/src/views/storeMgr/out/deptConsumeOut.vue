<template>
  <out-component :base-url="baseURL" :org-list="orgList" :out-stock-kind="outStockKind" out-type="出库"></out-component>
</template>

<script>
import outComponent from './outComponent';
import { OUTSTOCK_BILL_KIND } from '../../../common/js/constance';

export default {
  components: {
    outComponent,
  },
  data() {
    return {
      baseURL: '/spdHERPService/stockMgr/out/manualOut',
      outStockKind: OUTSTOCK_BILL_KIND.CONSUME.value,
      orgList: [],
    };
  },
  mounted() {
  },
  methods: {
  }
};
</script>

<style scoped="">

</style>
