<template>
    <el-tabs id="in-by-dept-apply-list" type="border-card" v-model="showTab"  style="height:95%">
        <el-tab-pane label="请购出库单" name="purLst" style="height:100%">
            <el-col :span="24"  style="height:100%">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-col :span="8">
                        <el-input v-model="filters.billId" placeholder="出库单号"  @keyup.enter.native="ruku" ></el-input>
                     </el-col>
                    <el-col :span="2" style="margin-left:5px;">
                        <el-button type="primary" icon="search"  v-on:click="getOutBillList(1)" >查询</el-button>   
                    </el-col>                 
                </el-col>
                <!--列表-->
                <template>
                    <el-table :data="outBillList"   @row-dblclick="handleRowDblClick" highlight-current-row v-loading="loading" style="width: 100%; " height="outter"  fit border  class ='inDeptA-tableheight'>
                            <el-table-column prop="id" label="出库单号" width="150"></el-table-column>
                            <el-table-column prop="goodsCount" label="品种数量"></el-table-column>
                            <el-table-column prop="goodsSum" label="商品总数量"></el-table-column>
                            <el-table-column prop="goodsSum" label="商品总数量"></el-table-column>
                            <el-table-column prop="" label="采购方式">
                                <template slot-scope="scope">
                                    {{scope.row.outStockType==10?'实采':'虚采'}}
                                </template>
                            </el-table-column>
                            <!-- <el-table-column prop="status" label="状态">
                                <template slot-scope="scope">
                                    {{scope.row.status | fStatus}}
                                </template>
                            </el-table-column> -->
                            <el-table-column prop="outDeptName" label="出库部门" width="150" sortable>
                            </el-table-column> 
                            <!-- <el-table-column prop="fillterName" label="制单人" sortable>
                            </el-table-column> -->
                            <el-table-column prop="fillDate" label="制单时间">
                                <template slot-scope="scope">
                                    {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="150">
                            <template slot-scope="scope">
                                <el-button v-if="scope.row.status == '30'"  @click="showPurcDialog(scope.row.id)" type="text">整单验收</el-button>
                             
                                <el-button @click="showSMDialog(scope.row.id)" type="text">扫码验收</el-button>
                             
                            </template>
                        </el-table-column>
                    </el-table>
                    <!--工具条-->
                    <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[30, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                        </el-pagination>
                    </el-col>
                </template>
            </el-col>

             <el-dialog title="出库单" :visible.sync="dlgVisible">
                    <el-form :model="currentOutStockBill" label-width="70px" ref="editForm" class="el-form-item-nomsg" label-position="left">

                        <el-row :gutter="20">
                        <el-col :span="8">
                            <el-form-item label="出库人员">
                            <el-input  v-model="currentOutStockBill.filler" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="制单时间">
                            <el-input  v-model="currentOutStockBill.fillDate" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <div class="text-style">
                            种类：{{goodsCount}}，总数量：{{goodsSum}}，总金额：{{amount}}
                            </div>
                        </el-col>
                        </el-row>
                        <el-row :gutter="20">
                        <el-col :span="8">
                            <el-form-item label="收货地址">
                            <el-input  v-model="currentOutStockBill.recAddress" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="联系人">
                            <el-input  v-model="currentOutStockBill.recLinkman" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="联系电话">
                            <el-input  v-model="currentOutStockBill.recLinkmanPhone" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        </el-row>
                        <el-row :gutter="10" v-if="!!currentOutStockBill.auditor && !!currentOutStockBill.accounter">
                        <el-col :span="6">
                            <el-form-item label="审核人员">
                            <el-input  v-model="currentOutStockBill.auditor" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="审核时间">
                            <el-input  v-model="currentOutStockBill.auditDate" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="记帐人员">
                            <el-input  v-model="currentOutStockBill.accounter" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="记帐时间">
                            <el-input  v-model="currentOutStockBill.accountDate" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        </el-row>
                    </el-form>
                    <el-table :data="currentOutStockBill.lstOutsubVo" highlight-current-row ref="billTable" border row-key="id">
                        <el-table-column type="index" label="序号" width="35" align="center"></el-table-column>
                        <el-table-column label="名称" prop="goodsName" width="150" show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column label="规格型号" prop="goodsGg" width="120" show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column label="请领数量" prop="reqQty" align="center">
                        </el-table-column>
                        <el-table-column label="已发数量" prop="sentQty" align="center">
                        </el-table-column>
                        <el-table-column label="出库数量" width="60" prop="outQty" class-name="table-col-bg" align="center">
                        </el-table-column>
                        <el-table-column label="单价" prop="price" align="center">
                        </el-table-column>
                        <el-table-column label="单位" prop="unit" align="center">
                        </el-table-column>
                        <el-table-column label="金额" align="center">
                        <template slot-scope="scope">
                            {{scope.row.outQty * scope.row.price}}
                        </template>
                        </el-table-column>
                        <el-table-column label="产地" prop="made" align="center">
                        </el-table-column>
                        <el-table-column label="供应商" prop="provName" align="center">
                        </el-table-column>
                        <el-table-column label="生产商" prop="mfrsName" align="center">
                        </el-table-column>
                    </el-table>
                    <div slot="footer" class="dialog-footer">
                        <el-button  @click.native="dlgVisible = false">取消</el-button>
                    </div>
            </el-dialog>
        </el-tab-pane>
        <el-tab-pane label="入库单列表" name="purconfirmLst" style="height:100%">
                <el-col :span="24" style="height:100%">
                    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <el-form :inline="true" label-width="80px" ref="searchForm" v-model="sFilters">
                            <el-form-item label="入库单号：">
                                <el-input v-model="sFilters.billId" placeholder="单号"  @keyup.enter.native="getRecBillList"></el-input>
                            </el-form-item>
                            <el-form-item label="制单时间">
                                <el-date-picker v-model="sFilters.startDate" class="data-picker" type="date" placeholder="开始日期" ></el-date-picker>
                                <span>-</span>
                                <el-date-picker v-model="sFilters.endDate" class="data-picker" type="date" placeholder="结束日期" ></el-date-picker>
                            </el-form-item>
                            <el-form-item label="状态">
                                <el-select style="width:120px;" v-model="sFilters.billStatus" clearable placeholder="全部" >
                                    <el-option
                                        v-for="item in rkdztStatus"
                                        :key="item.key"
                                        :label="item.value"
                                        :value="item.key"
                                        >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" icon="search" v-on:click="getRecBillList(1)" >查询</el-button>
                                <el-button @click="resetForm" >重置</el-button>
                            </el-form-item>

                        </el-form>
                    </el-col>
                    <!--列表-->
                        <el-table :data="receiveBillList" highlight-current-row v-loading="sLoading" style="width: 100%; " height="outter"  fit border  class ='inDeptA-tableheight' >
                            <el-table-column prop="billId" label="入库单号" sortable width="200">
                            </el-table-column>
                            <el-table-column prop="outBillId" label="出库单号" sortable width="200">
                            </el-table-column>
                            <el-table-column prop="status" label="状态" :formatter="statusFormat">
                            </el-table-column>
                            <el-table-column prop="outOrgName" width="90" label="出库机构" sortable>
                            </el-table-column>
                            <el-table-column prop="outDeptName" width="90" label="出库部门" sortable>
                            </el-table-column>
                            <el-table-column prop="inDeptName" width="90" label="入库部门" sortable>
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
                        <!--分页条-->
                       <el-col :span="24" class="toolbar">
                        <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="sPage" :page-sizes="[30, 100, 200, 400]" :page-size="sPageSize" layout="total, sizes, prev, pager, next" :total="sTotal" style="float:right;" small>
                        </el-pagination>
                       </el-col>

                </el-col>
        </el-tab-pane>
    </el-tabs>
</template>
<script>
import { INSTOCK_STATUS, DISTRBILL_STATUS } from './common/constance'
export default {
  data () {
    return {
      rkdztStatus:[],
      sFilters: {
        billId: '',
        inStockKind: 2,
        startDate: Date.now() - 7 * 24 * 60 * 60 * 1000,
        endDate: Date.now(),
        billStatus: null,//已提交
        filler: this.user.userId
      },
      showTab: 'purLst',
      sTotal: 0,
      sPage: 1,
      sPageSize: 30,
      sLoading: false,
      receiveBillList: [],

      filters: {
        billId: ''
      },
      total: 0,
      page: 1,
      pageSize: 30,
      loading: false,
      outBillList: [],
      dlgVisible: false,
      currentOutStockBill: { lstOutsubVo: []}

    }
  },
  filters: {
    fStatus: function (value) {
      if (value) {
        return DISTRBILL_STATUS[value].text
            } else {
        return ''
            }
    },
    inStockStatus: function (value) {
      if (value) {
        return INSTOCK_STATUS[value].text
            } else {
        return ''
            }
    }
  },
  computed: {

    goodsCount () {
      return new Set(this.currentOutStockBill.lstOutsubVo.map(o => o.goodsId)).size
    },
    goodsSum () {
      return this.currentOutStockBill.lstOutsubVo.reduce((acc, cur) => acc + cur.outQty, 0)
    },
    amount () {
      let je = 0
            this.currentOutStockBill.lstOutsubVo.forEach(sub => {
        je = je + sub.lstOutBatch.reduce((acc, cur) => acc + (cur.qty * cur.inPrice), 0)
      })
      return je
    }
  },
  methods: {
    // 获取列表
    getRecBillList: function (pIndex) {
      this.sPage = pIndex
            var param = {
        orderBy: '',
        page: this.sPage,
        rows: this.sPageSize,
        queryObject: {
          billId: this.sFilters.billId,
          inStockKind: this.sFilters.inStockKind,
          inDeptId: this.user.orgId,
          inOrgId: this.user.corpId,
          billStatus:this.sFilters.billStatus,
          startDate:this.sFilters.startDate,
          endDate:this.sFilters.endDate,
          filler:this.user.userId
        }
      }
            this.sLoading = true
            this.axios.post('/spdHERPService/stockMgr/inStock/listByPage', param).then(res => {
        this.sLoading = false
                this.receiveBillList = res.data.data.data
                this.sTotal = res.data.data.total
            }, err => {
        this.sLoading = false
            })
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
    editBill (recId) {
      this.$router.push({ path: 'inByDeptApplyEdit', query: { recId: recId } })
        },
    resetForm () {
      this.sFilters.billId = '';
      this.sFilters.startDate = "";
      this.sFilters.endDate = "";
      this.sFilters.billStatus = "";
    },
    statusFormat (row) {
      return INSTOCK_STATUS[row.status].text
        },

    // 获取列表
    getOutBillList: function (pIndex) {
      this.page = pIndex
               var param = {
        orderBy: '',
        page: this.page,
        rows: this.pageSize,
        queryObject: {
          billId: this.filters.billId,
          outStockKind:40,//请购出库类型
          inDeptId: this.user.orgId,
          arrStatus: [30,69] // 30已记帐 69部分入库
        }
      }
            this.loading = true
            this.axios.post('/spdHERPService/stockMgr/out4In/getBillList4Instock', param).then(res => {
        this.loading = false
                this.outBillList = res.data.data.data
                this.total = res.data.data.total
            }, err => {
        this.loading = false
            })
        },

    handleRowDblClick (row, event) {
      this.getBillDetail(row, event).then(res => {
        this.currentOutStockBill = res
        this.dlgVisible = true
      }).catch(err => {
        this.handleError(err)
      })
    },
    getBillDetail (row, event) {
      return new Promise((resolve, reject) => {
        //     this.axios.get('/spdHERPService/stockMgr/out/billMgr/getBillDetail', {
        this.axios.get('/spdHERPService/stockMgr/out4In/getBillDetail4In', {
          params: {
            id: row.id
          }
        }).then(res => {
          if (res.data.code === 0) {
            resolve(res.data.data)
          } else {
            reject(res.data.msg)
          }
        }).catch(err => {
          reject(err)
        })
      })
    },

    handleCurrentChange (val) {
      this.page = val
            this.getOutBillList(this.page)
        },
    handleSizeChange (val) {
      this.pageSize = val
            this.getOutBillList(this.page)
        },
    showPurcDialog (outBillId) {
      this.$router.push({ path: 'inByDeptApplyEdit', query: { outBillId: outBillId } })
        },
    showSMDialog (outBillId) {
      this.$router.push({ path: 'inByDeptApplyEditSM', query: { outBillId: outBillId } })
        },
      ruku(){
        this.showPurcDialog(this.filters.billId)
      }

  },
  mounted () {
        this.getRecBillList(1);
        this.getOutBillList(1);
        for (let k of Object.keys(INSTOCK_STATUS)) {
            this.rkdztStatus.push({key:k,value:INSTOCK_STATUS[k].text})
        }
    }
}
</script>
<style>
#in-by-dept-apply-list .el-table {
    font-size: 12px;
}
#in-by-dept-apply-list .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#in-by-dept-apply-list .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
#in-by-dept-apply-list .inDeptA-tableheight{
    height:  calc(100% - 70px);
}
</style>
