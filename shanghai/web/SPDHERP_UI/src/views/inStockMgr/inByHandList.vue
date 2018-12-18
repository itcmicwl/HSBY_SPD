<template>
    <div id="in-by-hand-list" style="height: 100%;">
        <el-col :span="24" style="height: 100%;">
               <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px" class="el-form-item-nomsg">
                        <el-form-item label="入库单号">
                            <el-input v-model="sFilters.billId" placeholder="入库单号" ></el-input>
                        </el-form-item>

                            <el-button type="primary" icon="search" v-on:click="getRecBillList(1)" >查询</el-button>
                            <el-button @click="resetForm" >重置</el-button>
                            <el-button type="primary"  v-on:click="addHand()" >入库</el-button>

                    </el-form>
                </el-col>
                  <template>
                        <el-table :data="receiveBillList" highlight-current-row v-loading="sLoading" style="width: 100%;" fit border class="inHand-tableheight">
                              <el-table-column prop="billId" label="入库单号" sortable width="200">
                            </el-table-column>
                            <el-table-column prop="status" label="状态" :formatter="statusFormat">
                            </el-table-column>

                            <el-table-column prop="inDeptName" label="入库部门" sortable>
                            </el-table-column>
                            <el-table-column prop="fillterName" label="制单人" sortable>
                            </el-table-column>
                            <el-table-column prop="fillDate" label="制单时间">
                                <template slot-scope="scope">
                                    {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="150">
                                <template slot-scope="scope">
                                    <el-button  @click="showBill(scope.row.billId)" type="text">查看</el-button>
                                    <el-button  @click="editBill(scope.row.id)" type="text" v-if="scope.row.status==10">编辑</el-button>

                                </template>
                            </el-table-column>
                        </el-table>
                            <!--工具条-->
                            <el-col :span="24" class="toolbar">
                              <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
                             </el-pagination>
                            </el-col>
                </template>
        </el-col>
    </div>

</template>
<script>
import { INSTOCK_STATUS } from './common/constance'
export default {
  data () {
    return {
      sFilters: {
        billId: '',
        inStockKind: 0,
        filler: this.user.userId
      },
      showTab: 'purconfirmLst',
      sTotal: 0,
      sPage: 1,
      sPageSize: 30,
      sLoading: false,
      receiveBillList: [
      ],

      filters: {
        billId: '',
        purchaseCompanyId: ''
      },
      total: 0,
      page: 1,
      pageSize: 30,
      loading: false,
      distrBillList: [
      ]
    }
  },
  filters: {
    inStockStatus: function (value) {
      if (value) {
            return INSTOCK_STATUS[value].text
        } else {
            return ''
        }
    }
  },

  methods: {
    // 获取列表
    getRecBillList: function (pIndex) {
        //alert(this.user.corpId)
        this.sPage = pIndex
        var param = {
            orderBy: '',
            page: this.sPage,
            rows: this.sPageSize,
            queryObject: {
                billId: this.sFilters.billId,
                inStockKind: this.sFilters.inStockKind,
                inDeptId: this.user.orgId,
                inOrgId: this.user.corpId

            }
        }
        this.sLoading = true
        this.axios.post('/spdHERPService/stockMgr/inStock/listByPage', param).then(res => {
            this.sLoading = false
            this.receiveBillList = res.data.data.data
            this.sTotal = res.data.data.sTotal
        }, err => {
            this.sLoading = false
        })
    },
    addHandBill: function () {
      this.$router.push('inByHandEdit')
        },
    sCurrentChange (val) {
      this.sPage = val
            this.getRecBillList(this.sPage)
        },
    sSizeChange (val) {
      this.sPageSize = val
            this.getRecBillList(this.sPage)
        },
    showBill (billId) {
      this.$router.push({ path: 'inStockView', query: { billId: billId } })
        },
    editBill (billId) {
      this.$router.push({ path: 'inByHandEdit', query: { billId: billId } })
        },
    resetForm () {
      this.sFilters.billId = '';
    },
    statusFormat (row) {
      return INSTOCK_STATUS[row.status].text
        },
    addHand () {
      this.$router.push({ path: 'inByHandEdit', query: { billId: '' } })
        }

  },
  mounted () {
    this.sFilters.billId = this.$route.query.inStockBillId
        this.getRecBillList(1)
  
    }
}
</script>
<style>
#in-by-hand-list .el-table {
    font-size: 12px;
}
#in-by-hand-list .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#in-by-hand-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
#in-by-hand-list .el-table {
    font-size: 12px;
}
#in-by-hand-list .inHand-tableheight{
       height:  calc(100% - 75px);
}
</style>
