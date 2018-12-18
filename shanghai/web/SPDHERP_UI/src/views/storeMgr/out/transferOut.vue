<template>
  <out-component :base-url="baseURL" :org-list="orgList" :out-stock-kind="outStockKind" out-type="调拨"></out-component>
</template>

<script>
import outComponent from "./outComponent";
import { OUTSTOCK_BILL_KIND } from "../../../common/js/constance";

export default {
  components: {
    outComponent
  },
  data() {
    return {
      baseURL: "/spdHERPService/stockMgr/out/transferOut",
      outStockKind: OUTSTOCK_BILL_KIND.TRANSFER.value,
      orgList: []
    };
  },
  mounted() {
    this.getOrgList();
  },
  methods: {
    getOrgList() {
      this.axios
        .get(this.baseURL + "/getOrgList")
        .then(res => {
          if (res.data.code === 0) {
            // 判断查询结果是否为空
            if (res.data.data.length === 0) {
              return;
            }
            this.orgList = res.data.data;
            return this.orgList;
          } else {
            this.handleError(res.data.msg);
          }
        })
        .catch(err => {
          this.handleError(err);
        });
    },
    handleError(err) {
      return new Promise((resolve, reject) => {
        this.$msgbox({ title: "错误", type: "error", message: err })
          .then(action => {
            resolve(action);
          })
          .catch(err => {
            reject(err);
          });
      });
    }
  }
};
</script>

<style scoped="">
</style>
