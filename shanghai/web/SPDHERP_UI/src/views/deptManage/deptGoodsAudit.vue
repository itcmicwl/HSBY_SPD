<template>
    <div id="dept-goods-audit" style="height: 100%;">
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <div style="padding-bottom: 1px;">
            <el-form :inline="true" :model="filters" label-width="80px" ref="goodsName">
                <el-form-item label="产品名称" prop="goodsName">
                    <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称" ></el-input>
                </el-form-item>
                <el-button-group>
                    <el-button type="primary" v-on:click="getDeptGoodsApplyList(1)" >查询</el-button>
                    <el-button type="primary" v-on:click="resetGoodsName('goodsName')" >重置</el-button>
                    <el-button type="success" v-loading="batchApplyLoading" @click="batchApply(null)" >批量通过</el-button>
                </el-button-group>
            </el-form>
            </div>
        <!--列表-->
        <template>
            <el-table  ref="multipleDeptGoodsApplyTable" :data="deptGoodsApplyDatas"  row-key='id'  @selection-change="deptGoodsApplyhandleSelectionChange"
                       highlight-current-row v-loading="loading"  border fit height="outter" class="deptgoods-tableheight">
                <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                <el-table-column prop="deptName" align="center" width="70" label="申请科室" ></el-table-column>
                <el-table-column prop="goodsName" header-align="center" min-width="200" label="产品名称" ></el-table-column>
                <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="200"></el-table-column>
                <el-table-column prop="packetCode" align="center" label="包装数量" width="80"></el-table-column>
                <el-table-column prop="status" align="center" label="状态" width="80" :formatter="statusFormat"></el-table-column>
                <el-table-column prop="applyReason" align="center" label="申请描述" width="200"></el-table-column>
                <el-table-column align="center" label="审核意见" width="200">
                    <template slot-scope="scope">
                        <el-input  :maxlength="128" v-model="scope.row.auditView" clearable style="width:100%">
                        </el-input>
                    </template>
                </el-table-column>
                <el-table-column label="操作"  align="center" fixed="right" width="135">
                    <template slot-scope="scope">
                        <el-button-group>
                            <el-button type="primary" size="mini" @click="agreenGoods(scope.row)" icon="search" >通过</el-button>
                            <el-button type="danger" size="mini" @click="rejectGoods(scope.row)" icon="delete" >驳回</el-button>
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
    </div>

</template>
<script>
import { APPLY_STATUS } from '../../common/js/constance';
export default {
  data () {
    return {
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
        batchApplyLoading: false,
        multipleDeptGoodsApplySelection: [],

        goodsBatchAddVisible: false,
        basGoodsFilters: {
            goodsName: ''
        },
        multipleSelection: [],
        basGoods:[],
        basTotal: 0,
        basPage: 1,
        basPageSize: 10,
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
      this.axios.post('/spdHERPService/deptMgr/deptGoodsAudit/findDeptGoodsAuditList', param).then(res => {
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
    //重置
    resetGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleDeptGoodsApplyTable.clearSelection()
      this.getDeptGoodsApplyList(1)
    },
    statusFormat(row) {
        return APPLY_STATUS[row.status].text;
    },
    //批量新增
    batchAdd: function () {
      this.goodImportVisible = true
      this.multipleSelection = []
      this.getHosGoodsList(1)
    },
    //批量提交
    batchApply: function () {
      if (this.multipleDeptGoodsApplySelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      this.batchApplyLoading=true;
      this.axios.post('/spdHERPService/deptMgr/deptGoodsAudit/deptGoodsAuditList', this.multipleDeptGoodsApplySelection).then(res => {
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
            this.resetGoodsName('goodsName');
        } else {
          this.$message.error(res.data.msg)
        }
        this.batchApplyLoading=false;
      }, err => {
        this.batchApplyLoading=false;
      })
    },
    agreenGoods: function(row){
        var selectRow = [];
        selectRow.push(row);
        this.axios.post('/spdHERPService/deptMgr/deptGoodsAudit/deptGoodsAuditList', selectRow).then(res => {
            this.loading = false
            if (res.data.code == '0') {
              this.$message({ message: '操作成功！', type: 'success' });
              this.resetGoodsName('goodsName');
            } else {
            this.$message.error(res.data.msg)
            }
        }, err => {
            this.loading = false
        })
    },
    rejectGoods: function(row){
        if(row.auditView){
            this.axios.post('/spdHERPService/deptMgr/deptGoodsAudit/deptGoodsReject', row).then(res => {
                this.loading = false
                if (res.data.code == '0') {
                    this.$message({ message: '操作成功！', type: 'success' })
                    this.resetGoodsName('goodsName');
                } else {
                    this.$message.error(res.data.msg)
                }
            }, err => {
                this.loading = false
            })
        }else{
            this.$message.error("请填写审核意见");
        }
    },
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
    }
  },
  mounted () {
    this.getDeptGoodsApplyList(1);
  }
}

</script>
<style>
    #dept-goods-audit .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #dept-goods-audit .el-checkbox-group{
        vertical-align: middle;
    }
    #dept-goods-audit .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 70px);
    }
    #dept-goods-audit .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 19px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #dept-goods-audit .deptGoods .el-row{
        height: 36px;
    }
    #dept-goods-audit .el-dialog--hosGoodsDialog {
        max-width: 1200px;
        min-width: 1118px;
        width: 90%;
    }
    #dept-goods-audit .el-dialog--hosGoodsDialog .el-form-item {
        margin-bottom: 7px;
    }

</style>
