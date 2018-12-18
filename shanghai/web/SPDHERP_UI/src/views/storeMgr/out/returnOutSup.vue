<template>
  <out-component-sup :base-url="baseUrl"
                     :base-url-sup="baseUrlSup"
                     :out-stock-kind="outStockKind" out-type="退货"></out-component-sup>
</template>

<script>
  import outComponentSup from './outComponentSup';
  import { OUTSTOCK_BILL_KIND } from '../../../common/js/constance';

  export default {
    components: {
      outComponentSup,
    },
    data() {
      return {
        baseUrl: '/spdHERPService/stockMgr/out/returnOut',
        baseUrlSup:'/spdHERPService/stockMgr/out/returnOutSup',
        outStockKind: OUTSTOCK_BILL_KIND.RETURN.value,
      };
    },
    mounted() {

    },
    methods: {
      handleError(err) {
        return new Promise((resolve, reject) => {
          this.$msgbox({ title: '错误', type: 'error', message: err })
            .then(action => {
              resolve(action);
            })
            .catch(err => {
              reject(err);
            });
        });
      },
    },
  };
</script>

<style scoped="">

</style>
