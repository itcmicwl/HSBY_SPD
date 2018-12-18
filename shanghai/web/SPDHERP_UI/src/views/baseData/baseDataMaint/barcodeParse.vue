<template>
  <div id="barcode-parse" style="height:100%">
    <el-row style="margin-bottom: 5px;">
      <el-input ref="searchInput" placeholder="请输入条码" v-model="searchBarcode"  @keyup.enter.native="searchBtnClick" style="width:30%;">
        <el-button slot="append" icon="search" @click="searchBtnClick"></el-button>
      </el-input>
    </el-row>
    <el-row class="full-height">
      <el-table highlight-current-row ref="parseResultTable" :data="parseResult" style="width: 100%;min-height: 400px;" v-loading.body="loading" border class="table-height" height="outer">
        <el-table-column label="条码" show-overflow-tooltip prop="barcode" fixed="left" min-width="260"></el-table-column>
        <el-table-column label="长度" width="40" show-overflow-tooltip prop="barcodeLen"></el-table-column>
        <el-table-column label="条码前缀" show-overflow-tooltip prop="barleader"></el-table-column>
        <el-table-column label="特征1码" show-overflow-tooltip prop="sign1"></el-table-column>
        <el-table-column label="特征2码" show-overflow-tooltip prop="sign2"></el-table-column>
        <el-table-column label="条码类型" :formatter="formatType" show-overflow-tooltip prop="mainFlag"></el-table-column>
        <el-table-column label="耗材码" show-overflow-tooltip prop="goodsNo" min-width="120"></el-table-column>
        <el-table-column label="耗材名称" show-overflow-tooltip prop="goodsName"></el-table-column>
        <el-table-column label="规格型号" show-overflow-tooltip prop="goodsSpec"></el-table-column>
        <el-table-column label="跟踪码" show-overflow-tooltip prop="trackNo"></el-table-column>
        <el-table-column label="有效期" show-overflow-tooltip prop="expiredDate">
          <template slot-scope="scope">
            <div v-if="scope.row.expiredDate" :class="expdtDateClass(scope.row.expiredDate)">
              {{new Date(scope.row.expiredDate).toLocaleDateString()}}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="生产批号" show-overflow-tooltip prop="batchNo"></el-table-column>
      </el-table>
    </el-row>

  </div>
</template>

<script>
export default {
  data () {
    return {
      baseURL: '/spdHERPService/baseData/baseDataMaint/BarcodeParse',
      searchBarcode: '',
      parseResult: [],
      loading: false
    }
  },
  methods: {
    searchBtnClick () {
      if (this.searchBarcode === '') {
        this.$message.info('请输入条码')
      } else {
        this.loading = true
        this.axios.get(this.baseURL + '/parseBarcodeBase', {
          params: {
            barcodeStr: this.searchBarcode.trim()
          }
        })
          .then(res => {
            if (res.data.code >= 0) {
              this.parseResult = res.data.data
              if (this.parseResult.length > 0) {
                this.searchBarcode = ''
              } else {
                this.$message.error('未找到相应条码解析规则。')
              }
            } else {
              this.$message.error('获取数据失败。' + res.data.msg.substring(0, 50) + '...')
            }
            this.loading = false
          })
          .catch(_ => {
            this.$message.error('数据异常，请联系管理员。')
            this.loading = false
          })
      }
    },
    expdtDateClass (expiredDate) {
      let endDate = Date.parse(expiredDate)
      let today = Date.parse(new Date())
      let days = this.expdtDays
      let expiring = false
      let expired = false
      if (endDate - today < 0) {
        expired = true
      } else if (endDate - today < days) {
        expiring = true
      }
      return {
        'expiring': expiring,
        'expired': expired,
        'valid': !expiring && !expired
      }
    },
    formatDate (row, column) {
      return new Date(row.expiredDate).toLocaleDateString()
    },
    formatType (row, column) {
      return row.mainFlag === '1' ? '主码' : '副码'
    }
  },
  mounted () {
    //            this.$refs['searchInput'].focus();
  },
  computed: {
    expdtDays () {
      return 30 * 24 * 60 * 60 * 1000
    }
  }
}
</script>

<style scoped>
.expiring {
  color: #ff00ff;
}

.expired {
  color: red;
}

.valid {
  color: green;
}

.table-height {
  height: calc(100% - 75px);
}

.full-height {
  height: 100%;
}
</style>
