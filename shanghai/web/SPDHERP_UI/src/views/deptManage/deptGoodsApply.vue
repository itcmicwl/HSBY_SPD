<template>
    <div id="dept-goods-apply" style="height: 100%;">
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <div style="padding-bottom: 1px;">
            <el-form :inline="true" :model="filters" label-width="80px" ref="goodsName">
                <el-form-item label="产品名称" prop="goodsName">
                    <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称" ></el-input>
                </el-form-item>
                <el-form-item label="状态：" prop="status">
                    <el-select v-model="filters.status"   style="width: 85%" clearable placeholder="请选择">
                        <el-option
                                v-for="item in deptGoodsApplyStatus"
                                      :key="item.val"
                                      :label="item.ename"
                                      :value="item.val">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-button-group>
                    <el-button type="primary" v-on:click="getDeptGoodsApplyList(1)" >查询</el-button>
                    <el-button type="primary" v-on:click="resetGoodsName('goodsName')" >重置</el-button>
                    <el-button type="primary" @click="batchAdd(null)" >批量新增</el-button>
                    <el-button type="success" @click="batchApply()" >批量提交</el-button>
                </el-button-group>
            </el-form>
            </div>
        <!--列表-->
        <template>
            <el-table  ref="multipleDeptGoodsApplyTable" :data="deptGoodsApplyDatas"  row-key='id'  @selection-change="deptGoodsApplyhandleSelectionChange"
                       highlight-current-row v-loading="loading"  border fit height="outter" class="deptgoods-tableheight">
                <el-table-column type="selection" align="center" :reserve-selection="true" width="55" :selectable="isCheck"> </el-table-column>
                <el-table-column prop="goodsName" header-align="center" min-width="200" label="产品名称" ></el-table-column>
                <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="200"></el-table-column>
                <el-table-column prop="packetCode" align="center" label="包装数量" width="80"></el-table-column>
                <el-table-column prop="status" align="center" label="状态" width="80" :formatter="statusFormat"></el-table-column>
                <el-table-column align="center" label="申请描述" width="400">
                    <template slot-scope="scope">
                      <div v-if="scope.row.status == 0">
                      <el-input  :maxlength="256" v-model="scope.row.applyReason" clearable style="width:100%">
                      </el-input>
                      </div>
                      <div v-else>
                        {{scope.row.applyReason}}
                    </div>
                    </template>
                </el-table-column>
                <el-table-column prop="auditView" align="center" label="审核意见" width="200" title="auditView"></el-table-column>
                <el-table-column label="操作"  align="center" fixed="right" width="135">
                    <template slot-scope="scope">
                        <el-button-group>
                            <el-button type="primary" size="mini" @click="goodsApplyRead(scope.row)" icon="search" >已阅</el-button>
                            <el-button type="danger" size="mini" @click="delGoodsApply(scope.row)" icon="delete" >删除</el-button>
                        </el-button-group>
                    </template>
                </el-table-column>
            </el-table>
            <!--工具条-->
            <el-col :span="24" class="toolbar">
                <el-pagination @size-change="handleSizeChange"
                               @current-change="handleCurrentChange" :current-page="page"
                               :page-sizes="[20, 100, 200, 400]" :page-size="pageSize" :total="total"
                               layout="total, sizes, prev, pager, next" style="float:right;" small>
                </el-pagination>
            </el-col>
        </template>
        </el-col>

        <el-dialog title="批量新增产品" close="onClose" :visible.sync="goodsBatchAddVisible" :close-on-click-modal="false"
                   size="hosGoodsDialog" width="78%" top="10vh">
            <el-form :inline="true" :model="basGoodsFilters" label-width="80px" ref="hosGoodsName">
                <el-form-item label="产品名称" prop="goodsName">
                    <el-input v-model="basGoodsFilters.goodsName" placeholder="输入产品名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="getHosGoodsList(1)" >查询</el-button>
                <el-button type="primary" v-on:click="resetHosGoodsName('hosGoodsName')" >重置</el-button>
            </el-form>
            <template>
                <el-table ref="multipleHosGoodsTable" v-loading="basGoodsLoading" :data="basGoods"  border tooltip-effect="dark" row-key='id'
                        style="width: 100%" @selection-change="handleSelectionChange" class="basGoods-tableHeight">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" min-width="250" label="产品名称" max-width="300px"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" min-width="250"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="50"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="60"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                  <el-table-column prop="brand" align="center" label="品牌" width="150"></el-table-column>
                    <el-table-column prop="hosMfrsName" header-align="center" label="生产厂商" width="250"></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                <el-pagination @size-change="basHandleSizeChange" @current-change="basHandleCurrentChange"
                               :current-page="basPage" :page-sizes="[20, 50, 100, 200]"
                               :page-size="basPageSize" layout="total, sizes, prev, pager, next"
                               :total="basTotal" style="float:right;" small>
                </el-pagination>
                    <el-button @click="toggleSelection()" >取消选择</el-button>
                    <el-button @click="goodsBatchAddSubmit()" type="success" >确定</el-button>
                    <el-button @click.native="goodsBatchAddVisible = false;toggleSelection()" type="primary" >返回</el-button>
                </div>
            </template>
        </el-dialog>
    </div>

</template>
<script>
import { APPLY_STATUS } from '../../common/js/constance';
export default {
  data () {
    return {
        basGoodsLoading:false,
        filters: {
            goodsName: '',
            status:''
        },
        deptGoodsApplyStatus:[],
        deptGoodsApplyDatas:[],
        total: 0,
        page: 1,
        pageSize: 20,
        loading: false,
        multipleDeptGoodsApplySelection: [],

        goodsBatchAddVisible: false,
        basGoodsFilters: {
            goodsName: ''
        },
        multipleSelection: [],
        basGoods:[],
        basTotal: 0,
        basPage: 1,
        basPageSize: 20
    }
  },
  methods: {
    // 获取科室产品申请列表
    getDeptGoodsApplyList: function (pIndex) {
      this.page = pIndex
      var param = {
        orderBy: '',
        queryObject: {
          goodsName: this.filters.goodsName,
          status: this.filters.status,
        },
        page: this.page,
        rows: this.pageSize
      }
      this.loading = true
      this.axios.post('/spdHERPService/deptMgr/deptGoodsApply/deptGoodsApplyList', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.deptGoodsApplyDatas = res.data.data.data
          this.total = res.data.data.total
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    statusFormat(row) {
          return APPLY_STATUS[row.status].text;
      },
    isCheck(row,index) {
        if(row.status === 0){
          return true;
        }
        return false;
      },
    //重置
    resetGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleDeptGoodsApplyTable.clearSelection()
      this.getDeptGoodsApplyList(1)
    },
    //批量新增
    batchAdd: function () {
      this.goodsBatchAddVisible = true
      this.multipleSelection = []
      this.getHosGoodsList(1)
    },
    //批量提交
    batchApply: function () {
      if (this.multipleDeptGoodsApplySelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      var param = {
        deptGoodsApplys: this.multipleDeptGoodsApplySelection
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsApply/deptGoodsApplyBatchSubmit', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' });
          this.$refs.multipleDeptGoodsApplyTable.clearSelection();
          this.multipleDeptGoodsApplySelection = [];
          this.getDeptGoodsApplyList(1);
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    //已阅
    goodsApplyRead: function (goods) {
      if(goods.status != 20 && goods.status != 30){
        this.$message.error('只允许操作状态为已批准或已驳回的商品数据信息！');
        return;
      }
      this.$confirm('确认已阅吗?', '提示', {
      }).then(() => {
        this.loading = true
        this.axios.post('/spdHERPService/deptMgr/deptGoodsApply/deptGoodsApplyRead', goods).then(res => {
          this.loading = false
          if (res.data.code == '0') {
            this.loading = false
            this.$message({ message: '操作成功！', type: 'success' })
            this.getDeptGoodsApplyList(this.page)
          } else {
            this.$message.error(res.data.msg)
          }
        }, err => {
          this.loading = false
        })
      }).catch(() => {})
    },
    //删除
    delGoodsApply: function (goods) {
      if(goods.status != 0){
        this.$message.error('只允许删除状态为初始的商品数据信息！');
        return;
      }
      this.$confirm('确认删除这条记录吗?', '提示', {
      }).then(() => {
        this.loading = true
        this.axios.post('/spdHERPService/deptMgr/deptGoodsApply/deleteDeptGoodsApply', goods).then(res => {
          this.loading = false
          if (res.data.code == '0') {
            this.loading = false
            this.$message({ message: '操作成功！', type: 'success' })
            this.getDeptGoodsApplyList(this.page)
          } else {
            this.$message.error(res.data.msg)
          }
        }, err => {
          this.loading = false
        })
      }).catch(() => {})
    },
    //重置
    resetHosGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleHosGoodsTable.clearSelection()
      this.getHosGoodsList(1)
    },
    handleCurrentChange (val) {
      this.page = val
      this.getDeptGoodsApplyList(this.page)
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.getDeptGoodsApplyList(this.page)
    },

    // 获取医院产品列表
    getHosGoodsList: function (pIndex) {
      this.basPage = pIndex
      var param = {
        orderBy: '',
        queryObject: {
          goodsName: this.basGoodsFilters.goodsName
        },
        page: this.basPage,
        rows: this.basPageSize
      }
      this.basGoodsLoading = true;
      this.axios.post('/spdHERPService/deptMgr/deptGoodsApply/deptGoodsApplyBatchAddQuery', param).then(res => {
        if (res.data.code == '0') {
          this.basGoods = res.data.data.data
          this.basTotal = res.data.data.total
        } else {
          this.$message.error(res.data.msg)
        }
          this.basGoodsLoading = false;
      }, err => {
          this.basGoodsLoading = false;
      })
    },
    //确认提交
    goodsBatchAddSubmit: function () {
      if (this.multipleSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      var param = {
        hosGoodsInfos: this.multipleSelection
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsApply/deptGoodsApplyBatchAdd', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.goodsBatchAddVisible = false
          this.$message({ message: '操作成功！', type: 'success' })
          this.$refs.multipleHosGoodsTable.clearSelection()
          this.getDeptGoodsApplyList(1)
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    basHandleCurrentChange (val) {
      this.basPage = val
      this.getHosGoodsList(this.basPage)
    },
    basHandleSizeChange (val) {
      this.basPageSize = val
      this.getHosGoodsList(this.basPage)
    },
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleHosGoodsTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleHosGoodsTable.clearSelection()
      }
    },
    deptGoodsApplyhandleSelectionChange (val) {
      this.multipleDeptGoodsApplySelection = val
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    //查询医院科室产品申请状态字典
    getGoodsApplyStatusDict: function () {
      this.axios.post('/platformService/sys/dict/getDictValueByDictEname', {dictName: 'SQZT'}).then(res => {
        if (res.data.code == '0') {
          this.deptGoodsApplyStatus = res.data.data
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    },
  },
  mounted () {
    this.getGoodsApplyStatusDict()
    this.getDeptGoodsApplyList(1)
  }
}

</script>
<style>
    #dept-goods-apply .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #dept-goods-apply .el-checkbox-group{
        vertical-align: middle;
    }
    #dept-goods-apply .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 70px);
    }
    .basGoods-tableHeight {
        width: 100%;
        height:  400px;
    }
    #dept-goods-apply .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #dept-goods-apply .deptGoods .el-row{
        height: 36px;
    }
    #dept-goods-apply .el-dialog--hosGoodsDialog {
        max-width: 1200px;
        min-width: 1118px;
        width: 90%;
    }
    #dept-goods-apply .el-dialog--hosGoodsDialog .el-form-item {
        margin-bottom: 7px;
    }

    #dept-goods-apply .el-form-item--mini.el-form-item, .el-form-item--small.el-form-item {
      margin-bottom: 0px;
    }

</style>
