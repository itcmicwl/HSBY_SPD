<template>
    <div id="dept-product-model-mgr" style="height: 100%;">
        <el-row style="height: 99%;padding-bottom: 0px;">
            <el-col :span="6" style="height: calc(100% - 31px);">
                <div style="width: 260px;">
                    <el-input placeholder="请输入套餐名称" v-model="filterText" style="width: 48.5%;"></el-input>
                    <el-button-group style="margin-bottom: 3px;">
                        <el-button type="success" v-on:click="showAddMoal" >添加</el-button>
                        <el-button type="primary" v-on:click="showEditMoal" :disabled="editModalDisabled" >编辑</el-button>
                        <el-button type="danger" v-on:click="deleteModal" :disabled="deleteModalDisabled" >删除</el-button>
                    </el-button-group>
                </div>
                <el-tree :data="goodsTemplateNode"
                         node-key="id" ref="tree2"
                         :default-expand-all="true"
                         :props="defaultProps"
                         :highlight-current="true"
                         :expand-on-click-node="false"
                         :filter-node-method="filterNode"
                         @node-click="handleNodeClick">
                </el-tree>
            </el-col>
            <el-col :span="18" class="toolbar" style="padding-bottom: 0px;height: 98.7%">
                <el-form :inline="true" :model="filters" label-width="80px" ref="deptProductModalForm">
                    <el-form-item label="产品名称" prop="goodsName">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称"  @keyup.enter.native="queryDeptTemplateGoods(1)"></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="queryDeptTemplateGoods(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetGoodsName('deptProductModalForm')" >重置</el-button>
                        <el-button type="success" @click="handleImport"  :disabled="importDisabled">批量导入</el-button>
                        <el-button type="danger" @click="mulDel()" >批量移除</el-button>
                    </el-button-group>
                </el-form>
                <!--列表-->
                <el-table  ref="multipleTable" :data="templateGoods"  row-key='formatRowKey'  @selection-change="handleSelectionChange"
                           highlight-current-row v-loading="loading"  border fit height="outter" class="deptProdunctModal-tableheight">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column prop="cname" align="center" width="150" label="直属套餐" ></el-table-column>
                    <el-table-column label="图片" width="60" header-align="center" align="center">
                        <template slot-scope="scope">
                            <el-button type="info" :disabled="!scope.row.imgAvailable" size="mini" icon="el-icon-picture" @click.native="showGoodsImg(scope.row)"></el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" min-width="200" label="产品名称"   show-overflow-tooltip></el-table-column>
                    <el-table-column header-align="center" min-width="150" label="产品数量/定数包数量" >
                         <template slot-scope="scope">
                            <el-input-number  v-model="scope.row.goodsNum" size="mini" :min="1" class="col-input-num" @change="goodsNumChange($event, scope.row)">
                            </el-input-number>
                            <!--<el-input  v-model="scope.row.goodsNum" size="mini" @change="test(scope.row)">
                            </el-input>-->
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="60"></el-table-column>
                    <el-table-column prop="packetCode" align="center" label="包装数量" width="60"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="70"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="200" show-overflow-tooltip></el-table-column>
                    <el-table-column label="操作"  align="center" fixed="right" width="120">
                        <template slot-scope="scope">
                            <el-button-group>
                                <el-button type="primary" size="mini" @click="goodQuery(scope.row)" icon="search" >查看</el-button>
                                <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete" >移除</el-button>
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleSizeChange"
                                   @current-change="handleCurrentChange" :current-page="page"
                                   :page-sizes="[20, 50, 100, 200]" :page-size="pageSize" :total="total"
                                   layout="total, sizes, prev, pager, next" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
        </el-row>

        <el-dialog title="产品详情" close="onClose" :visible.sync="queryFormVisible" :close-on-click-modal="false" size="mini">
            <el-form :model="good"  label-width="120px"  class="el-form-item-nomsg deptTemplateGoods">
                <el-row>
                    <el-col :span="16">
                        <el-form-item label="产品名称："  prop="goodsName">
                            <el-input v-model="good.goodsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="科室名称：" prop="stocId">
                            <el-input v-model="good.deptName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="规格型号：" prop="goodsGg">
                            <el-input v-model="good.goodsGg" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="单位：" prop="goodsGg">
                            <el-input v-model="good.unit" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="库存上限：" prop="stocUpper">
                            <el-input v-model="good.stocUpper" disabled  placeholder="请输入库存上限"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="包装数量：" prop="goodsGg">
                            <el-input v-model="good.packetCode" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="产地：" prop="goodsGg">
                            <el-input v-model="good.made" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="库存下限：" prop="stocLower">
                            <el-input v-model="good.stocLower" disabled  placeholder="请输入库存下限"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="16">
                        <el-form-item label="生产厂商：" prop="mfrsName">
                            <el-input v-model="good.mfrsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="采购模式：" prop="purMode">
                            <el-select v-model="purMode" disabled filterable placeholder="请选择" :clearable="true" >
                                <el-option
                                        v-for="item in purModes"
                                        :key="item.val"
                                        :label="item.ename"
                                        :value="item.val">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row style="padding-left: 37px;">
                    <el-col :span="8">
                        <el-form-item label="是否定数包管理：" prop="isPacket">
                            <el-radio-group v-model="isPacket" readonly >
                                <el-radio-button label="1" :disabled="isPacketYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isPacketNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="是否唯一码管理：" prop="isUnique">
                            <el-radio-group v-model="isUnique" >
                                <el-radio-button label="1" :disabled="isUniqueYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isUniqueNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="定数包是否混批：" prop="isMix">
                            <el-radio-group v-model="isMix" >
                                <el-radio-button label="1" :disabled="isMixYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isMixNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer" style="text-align: center;">
                <el-button @click.native="queryFormVisible = false" >取消</el-button>
            </div>
        </el-dialog>

        <el-dialog title="批量导入产品" close="onClose" :visible.sync="goodImportVisible" :close-on-click-modal="false" size="deptGoodsDialog">
            <el-form :inline="true" :model="basGoodsFilters" label-width="80px" ref="deptGoodsName">
                <el-form-item label="商品名称" prop="goodsName">
                    <el-input v-model="basGoodsFilters.goodsName" placeholder="输入商品名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="getDeptGoodsList(1)" >查询</el-button>
                <el-button type="primary" v-on:click="resetDeptGoodsName('deptGoodsName')" >重置</el-button>
            </el-form>
            <template>
                <el-table ref="multipleDeptGoodsTable" :data="basGoods"  border tooltip-effect="dark" row-key='id'
                          style="width: 100%" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column label="产品数量/定数包数量"  width="150">
                        <template slot-scope="scope">
                            <el-input-number placeholder="请输入数量"  v-model="scope.row.goodsNum" size="mini" :min="1" class="col-input-num">
                            </el-input-number>
                            <!--<el-input placeholder="请输入数量"  v-model="scope.row.goodsNum" size="mini">
                            </el-input>-->
                        </template>
                    </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" label="产品名称" min-width="300px"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="280"></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                    <el-pagination @size-change="basHandleSizeChange" @current-change="basHandleCurrentChange"
                                   :current-page="basPage" :page-sizes="[10, 20, 50, 100]"
                                   :page-size="basPageSize" layout="total, sizes, prev, pager, next"
                                   :total="basTotal" style="float:right;" small>
                    </el-pagination>
                    <el-button @click="toggleSelection()" >取消选择</el-button>
                    <el-button @click="goodImprotSubmit()" type="success" >确定</el-button>
                    <!--<el-button @click.native="goodImportVisible = false" type="primary" >返回</el-button>-->
                    <el-button @click.native="cancelDig()" type="primary" >返回</el-button>
                </div>
            </template>
        </el-dialog>
        <!-- 产品套餐添加 -->
        <el-dialog title="添加套餐" close="onClose" :visible.sync="deptGoodsModalVisible" :close-on-click-modal="false" size="editModal">
            <el-form :model="deptGoodsModal"  label-width="120px"  class="el-form-item-nomsg deptModal">
                <el-form-item label="科室名称：">
                    <el-input v-model="orgName" style="width: 75%;" disabled ></el-input>
                </el-form-item>
                <el-form-item label="套餐名称：" prop="cname" style="margin-top: 5px;margin-bottom: 5px;">
                    <el-input v-model="deptGoodsModal.cname" placeholder="请输入套餐名称" style="width: 75%;"  ></el-input>
                </el-form-item>
                <el-form-item label="上级套餐：" prop="id">
                    <el-cascader placeholder="请选择上级套餐"
                                 v-model="deptGoodsModal.selectedParentModal"
                                 :show-all-levels="false"
                                 :options="goodsTemplate"
                                 :change-on-select="true"
                                 expand-trigger="hover" clearable
                                 :props="modalProps" filterable>
                    </el-cascader>
                </el-form-item>

  <el-checkbox :indeterminate="isIndeterminate" v-model="checkAllUser" @change="handleCheckAllChange">全选</el-checkbox>
  <div style="margin: 15px 0;"></div>
  <el-checkbox-group v-model="checkedUsers" @change="handleCheckedCitiesChange">
    <el-checkbox v-for="_user in users" :label="_user" :key="_user">{{_user}}</el-checkbox>
  </el-checkbox-group>

            </el-form>

            <div style="text-align: center;line-height: 45px;">
                <el-button @click.native="deptGoodsModalVisible = false" >返回</el-button>
                <el-button @click="goodsModalAdd" type="primary" >提交</el-button>
            </div>
        </el-dialog>
        <!-- 产品套餐编辑 -->
        <el-dialog title="套餐编辑" close="onClose" :visible.sync="deptGoodsModalEditVisible" :close-on-click-modal="false" size="editModal">
            <el-form :model="deptGoodsModal"  label-width="120px"  class="el-form-item-nomsg deptModal">
                <el-form-item label="科室名称：">
                    <el-input v-model="orgName" style="width: 75%;" disabled ></el-input>
                </el-form-item>
                <el-form-item label="套餐名称：" prop="cname" style="margin-top: 5px;margin-bottom: 5px;">
                    <el-input v-model="deptGoodsModal.cname" placeholder="请输入套餐名称" style="width: 75%;"  ></el-input>
                </el-form-item>
                <el-form-item label="上级套餐：" prop="id">
                    <el-cascader placeholder="请选择上级套餐"
                                 v-model="deptGoodsModal.selectedParentModal"
                                 :show-all-levels="false"
                                 :options="goodsTemplate"
                                 :change-on-select="true"
                                 expand-trigger="hover" clearable
                                 :props="modalProps" filterable>
                    </el-cascader>
                </el-form-item>
                  <el-checkbox :indeterminate="isIndeterminate" v-model="checkAllUser" @change="handleCheckAllChange">全选</el-checkbox>
                    <div style="margin: 15px 0;"></div>
                    <el-checkbox-group v-model="checkedUsers" @change="handleCheckedCitiesChange">
                        <el-checkbox v-for="_user in users" :label="_user" :key="_user">{{_user}}</el-checkbox>
                </el-checkbox-group>
            </el-form>
            <div style="text-align: center;line-height: 45px;">
                <el-button @click.native="deptGoodsModalEditVisible = false" >返回</el-button>
                <el-button @click="goodsModalEdit" type="primary" >提交</el-button>
            </div>
        </el-dialog>

        <el-dialog title="商品照片" :visible.sync="dlgGoodsImgVisible" v-loading="dlgGoodsImgLoading">
          <el-row>
            <el-col class="pic-border" :span="8" v-for="(url, index) in goodsImgs.origUrls" :key="index">
              <el-tooltip placement="right" effect="light" :open-delay="300">
                <div slot="content">
                  <img class="tooltip-pic" src="" :orig-src="goodsImgs.origUrls[index]" :ref="'img'+index">
                </div>
                <img :src="goodsImgs.tUrls[index]" class="pic" @mouseenter="imgMouseEnter($event, index)">
              </el-tooltip>
            </el-col>
          </el-row>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="dlgGoodsImgVisible = false">关闭</el-button>
          </div>
        </el-dialog>
    </div>
</template>
<script>
function buildTreenode (list, idName, pidName, labelName) {
  var map = {}
  for (let i = 0; i < list.length; i++) {
    map[list[i][idName]] = {id: list[i][idName], pid: list[i][pidName], label: list[i][labelName], tag: list[i]}
  }
  var maproot = []
  function linknode (node) {
    if (!node.pid) {
      maproot.push(node)
      return
    }
    let pnode = map[node.pid]
    node.pnode = pnode
    if (!pnode.children) { pnode.children = [] }
    pnode.children.push(node)
  }

  for (var id in map) {
    let item = map[id]
    linknode(item)
  }
  for (var id in map) {
    let item = map[id]
    if (item.children) {
      item.isLeaf = false
      item.checked = false
    } else {
      item.isLeaf = true
      item.checked = false
      let pitem = map[item.pid]
      if (pitem) {
        pitem.isGroup = true
      }
    }
  }

  return maproot
}
export default {
  data () {
    return {
      dlgGoodsImgVisible: false,
      goodsImgs: {
        id: '',
        hosGoodsId: '',
        origUrls: Array(6).fill(''),
        tUrls: Array(6).fill('')
      },
      dlgGoodsImgLoading: false,
      picURLPrefix: '/upload/',

      editModalDisabled: true,
      deleteModalDisabled: true,
      isPacket: '1',
      isUnique: '1',
      isMix: '1',
      isPacketYes: false,
      isPacketNo: false,
      isUniqueYes: false,
      isUniqueNo: false,
      isMixYes: false,
      isMixNo: false,
      purMode: '10',
      good: {},
      basTotal: 0,
      basPage: 1,
      basPageSize: 10,
      basGoods: [],
      purModes: [],
      queryFormVisible: false,
      goodImportVisible: false,
      deptGoodsModalEditVisible: false,
      deptGoodsModalVisible: false,
      deptGoodsModalTitle: '',
      deptGoodsModal: {},
      modalOptions: [],
      modalProps: {
        value: 'id',
        label: 'label'
      },
      currentNode: {},
      multipleSelection: [],
      filters: { goodsName: '' },
      basGoodsFilters: {goodsName: ''},
      templateGoodsParam: {
        deptId: '',
        hosId: '',
        tempIds: []
      },
      importDisabled: true,
      templateGoods: [],
      leaves: [],
      modalArray: [],
      filterText: '',
      hosId: '',
      deptId: '',
      orgName: '',
      total: 0,
      page: 1,
      pageSize: 20,
      loading: false,
      goodsTemplate: [],
      goodsTemplateNode: [{
        children: [],
        label: '',
        id: '',
        pid: ''
      }],
      templateGoodsVo: {},
      defaultProps: {
        children: 'children',
        label: 'label',
        id: 'id',
        pid: 'pid'
      },
      // 科室人员
      checkAllUser: false,
      checkedUsers: [],
      users: [],
      isIndeterminate: true,
      tempId: '',
      userNames: [],
      userMap: []
    }
  },
  watch: {
    filterText (val) {
      this.$refs.tree2.filter(val)
    }
  },
  methods: {
    cancelDig(){
      this.goodImportVisible=false;
      this.basGoods=[];
      this.$refs.multipleDeptGoodsTable.clearSelection();
    },
    goodsNumChange (value, row) {
      var param = {
        id: row.tgId,
        goodsNum: value,
        version: row.version
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/updateGoodsNum', param).then(res => {
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    },
    // 重写row-key属性数据，防止重复
    formatRowKey: function (row) {
      return row.id + '|' + row.goodsName
    },
    // 产品套餐添加
    goodsModalAdd: function () {
      var usersTemp = []
      for (let j = 0; j < this.checkedUsers.length; j++) {
        for (let k = 0; k < this.userMap.length; k++) {
          if (this.checkedUsers[j] == this.userMap[k].name) {
            usersTemp.push(this.userMap[k].userId)
          }
        }
      }
      var param = {
        hosId: this.hosId,
        deptId: this.deptId,
        pid: this.deptGoodsModal.selectedParentModal[this.deptGoodsModal.selectedParentModal.length - 1],
        cname: this.deptGoodsModal.cname,
        users: usersTemp
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/deptGoodsTemplateAdd', param).then(res => {
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
          this.deptGoodsModalVisible = false
          this.getDeptGoodsTemplate()
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    },

    showAddMoal: function () {
      this.checkedUsers = [];
      this.isIndeterminate = false;
      this.checkAllUser = false;
      this.deptGoodsModal = {cname: '', selectedParentModal: [], pid: ''}
      this.deptGoodsModalTitle = '添加套餐'
      this.deptGoodsModalVisible = true
    },

    // 产品套餐编辑
    goodsModalEdit: function () {
      var usersTemp = []
      for (let j = 0; j < this.checkedUsers.length; j++) {
        for (let k = 0; k < this.userMap.length; k++) {
          if (this.checkedUsers[j] == this.userMap[k].name) {
            usersTemp.push(this.userMap[k].userId)
          }
        }
      }
      let goodsPurTemplate = this.currentNode.tag
      goodsPurTemplate.cname = this.deptGoodsModal.cname
      goodsPurTemplate.users = usersTemp
      goodsPurTemplate.pid = this.deptGoodsModal.selectedParentModal[this.deptGoodsModal.selectedParentModal.length - 1],
      this.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/deptGoodsTemplateEdit', goodsPurTemplate).then(res => {
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
          this.deptGoodsModalEditVisible = false
          this.getDeptGoodsTemplate()
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    },
    showEditMoal: function () {
      let _this = this
      _this.deptGoodsModal = {cname: _this.currentNode.label, selectedParentModal: [], pid: ''}
      if (_this.currentNode.pnode) {
        _this.getParentModal(_this.currentNode.pnode)
      }
      // 由于先进叶子结点，最后进顶级结点，故需要将数组反转过来，才能正常回显父结点内容
      _this.deptGoodsModal.selectedParentModal = _this.deptGoodsModal.selectedParentModal.reverse()
      _this.deptGoodsModalEditVisible = true

      _this.axios.post('/spdHERPService/deptMgr/goodsPurTemplateUserRelation/selectByTempId/' + _this.tempId).then(res => {
        _this.checkedUsers = []
        if (res.data.code == '0') {
          let temp = res.data.data
          for (let i = 0; i < temp.length; i++) {
            for (let j = 0; j < _this.userMap.length; j++) {
              if (temp[i].userId == _this.userMap[j].userId) {
                _this.checkedUsers.push(_this.userMap[j].name)
              }
            }
          }
          //判断全选框选中状态
          if(_this.checkedUsers.length){
            let checkedCount = _this.checkedUsers.length;
            this.checkAllUser = checkedCount === this.users.length;
            this.isIndeterminate = checkedCount > 0 && checkedCount < this.users.length;
          } else {
            this.isIndeterminate = false;
            this.checkAllUser = false;
          }
        } else {
          _this.$message.error(res.data.msg)
        }
      }, err => {})
    },
    // 获取当前模版Id
    getRelation: function () {

    },
    getParentModal: function (node) {
      this.deptGoodsModal.selectedParentModal.push(node.id)
      if (node.pnode) {
        this.getParentModal(node.pnode)
      }
    },
    // 删除套餐及所有子套餐，以及这些套餐下的所有产品
    deleteModal: function () {
      let currentNode = this.currentNode
      this.getNodes(currentNode)
      this.$confirm('该操作将会删除该套餐及其所有子套餐，同时也将删除这些套餐对应的所有产品，确认继续?', '提示', {
      }).then(() => {
        this.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/deptGoodsTemplateDelete', this.modalArray).then(res => {
          this.loading = false
          if (res.data.code == '0') {
            this.$message({ message: '操作成功！', type: 'success' })
            this.getDeptGoodsTemplate()
          } else {
            this.$message.error(res.data.msg)
          }
        }, err => {})
      }).catch(() => {})
    },
    // 获取当前结点及所有子结点
    getNodes: function (currentNode) {
      let o = this
      if (currentNode.isLeaf) {
        o.modalArray.push({id: currentNode.id})
      } else {
        o.getNodes(currentNode.children)
      }
    },
    // 批量导入产品：科室产品==>套餐产品
    handleImport: function () {
      this.goodImportVisible = true
      this.multipleSelection = []
      this.getDeptGoodsList(1)
    },
    resetDeptGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleDeptGoodsTable.clearSelection()
      this.getDeptGoodsList(1)
    },
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleDeptGoodsTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleDeptGoodsTable.clearSelection()
      }
    },
    // 批量导入产品提交
    goodImprotSubmit: function () {
      if (this.multipleSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      // 去重
      for (var i in this.templateGoods) {
        var id = this.templateGoods[i].goodsId
        for (var j in this.multipleSelection) {
          if (this.multipleSelection[j].goodsId == id) {
            this.multipleSelection.splice(j, 1)
          }
        }
      }
      if (this.multipleSelection.length <= 0) {
        this.$message('产品重复')
        this.goodImportVisible = false
        return
      }
      var param = {
        tempId: this.templateGoodsParam.tempIds[0],
        goodsList: this.multipleSelection
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/importDeptTemplateGoods', param).then(res => {
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
          this.goodImportVisible = false
          this.$refs.multipleDeptGoodsTable.clearSelection()
          this.queryDeptTemplateGoods(1)
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    },

    // 查询科室产品信息
    getDeptGoodsList: function (pIndex) {
      this.basPage = pIndex
      var param = {
        orderBy: '',
        queryObject: {
          hosId: this.hosId,
          deptId: this.deptId,
          filterFlag: this.templateGoodsParam.tempIds[0],
          goodsName: this.basGoodsFilters.goodsName
        },
        page: this.basPage,
        rows: this.basPageSize
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoList', param).then(res => {
        if (res.data.code == '0') {
          this.basGoods = res.data.data.data
          this.basGoods.forEach(row => {
            this.$set(row, 'goodsNum', 1)
          })
          this.basTotal = res.data.data.total
          this.basGoodsFilters.goodsName = '';
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
      })
    },
    basHandleCurrentChange (val) {
      this.basPage = val
      this.getDeptGoodsList(this.basPage)
    },
    basHandleSizeChange (val) {
      this.basPageSize = val
      this.getDeptGoodsList(this.basPage)
    },
    getDeptGoodsTemplate: function () {
      let o = this
      function cb (goodsTemplate) {
        o.goodsTemplate = buildTreenode(goodsTemplate, 'id', 'pid', 'cname')
        o.goodsTemplateNode[0].children = o.goodsTemplate
        o.goodsTemplateNode[0].label = o.orgName
        o.queryDeptTemplateGoods(1)
      }
      var goodsPurTemplate = {
        deptId: this.deptId,
        hosId: this.hosId
      }
      o.loading = true
      o.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/deptGoodsTemplateQuery', goodsPurTemplate).then(res => {
        var goodsTemplate = res.data.data
        cb(goodsTemplate)
        o.loading = false
      })
    },
    handleNodeClick: function (d1, d2, d3) {
      // 给自定义的模版id赋值
      this.tempId = d1.id
      this.currentNode = d1
      this.editModalDisabled = d1.id == ''
      this.deleteModalDisabled = d1.id == ''
      this.templateGoodsParam.tempIds = []
      if (d1.isLeaf) {
        this.importDisabled = false// 只有叶子结点才能导入产品数据
        this.templateGoodsParam.tempIds.push(d1.id)
      } else {
        this.importDisabled = true// 非叶子结点不能导入产品数据
        this.leaves = []// 非叶子结点下所有叶子结点数组
        this.getNodeLeaves(d1.children)// 获取非叶子结点下所有叶子结点数组
        this.templateGoodsParam.tempIds = this.leaves
      }
      // 查询套餐产品信息
      this.queryDeptTemplateGoods(this.page)
    },
    getNodeLeaves: function (children) {
      let o = this
      children.forEach(item => {
        if (item.isLeaf) {
          o.leaves.push(item.id)
        } else {
          o.getNodeLeaves(item.children)
        }
      })
    },
    /**
             * 查询套餐产品信息
             */
    queryDeptTemplateGoods: function (page) {
      let o = this
      o.page = page
      o.multipleSelection = []// 每次查询，将选中的数据清空
      // this.$refs.multipleTable.clearSelection();
      function cb (templateGoods) {
        o.templateGoods = templateGoods
      }
      o.templateGoodsParam.deptId = this.deptId
      o.templateGoodsParam.hosId = this.hosId
      o.templateGoodsParam.goodsName = this.filters.goodsName
      var param = {
        orderBy: '',
        queryObject: o.templateGoodsParam,
        page: page,
        rows: this.pageSize
      }
      o.loading = true
      o.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/queryDeptTemplateGoods', param).then(res => {
        var templateGoods = res.data.data.data
        cb(templateGoods)
        o.total = res.data.data.total
        o.loading = false
      }, err => {
        this.loading = false
      })
    },
    /**
             * 重置套餐产品查询
             */
    resetGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleTable.clearSelection()
      this.queryDeptTemplateGoods(1)
    },

    handleSizeChange (val) {
      this.pageSize = val
      this.queryDeptTemplateGoods(this.page)
    },

    handleCurrentChange (val) {
      this.page = val
      this.queryDeptTemplateGoods(this.page)
    },

    handleSelectionChange (val) {
      this.multipleSelection = val
    },

    /**
             * 产品详情查询
             * @param item
             */
    goodQuery (item) {
      let good = Object.assign({}, item)
      this.good = good
      this.purMode = this.good.purMode
      this.isPacket = this.good.isPacket
      this.isPacketYes = this.isPacket != 1
      this.isPacketNo = this.isPacket != 0
      this.isUnique = this.good.isUnique
      this.isUniqueYes = this.isUnique != 1
      this.isUniqueNo = this.isUnique != 0
      this.isMix = this.good.isMix
      this.isMixYes = this.isMix != 1
      this.isMixNo = this.isMix != 0
      this.getCGMS()// 采购模式查询
      this.queryFormVisible = true
    },

    // 移除套餐产品
    delGood (good) {
      let o = this
      var bean = {id: good.tgId}
      o.$confirm('确认移除该商品吗?', '提示', {
      }).then(() => {
        o.loading = true
        o.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/deleteDetpTemplateGoods', bean).then(res => {
          o.loading = false
          if (res.data.code == '0') {
            o.loading = false
            o.$message({ message: '操作成功！', type: 'success' })
          } else {
            o.$message.error(res.data.msg)
          }
          o.queryDeptTemplateGoods(o.page)
        }, err => {
          o.loading = false
        })
      }).catch(() => {})
    },

    // 批量移除套餐产品
    mulDel () {
      let o = this
      if (o.multipleSelection.length <= 0) {
        o.$message.error('至少选择一条数据！')
        return
      }
      o.$confirm('确认删除选中记录吗?', '提示', {
      }).then(() => {
        o.loading = true
        o.axios.post('/spdHERPService/deptMgr/deptGoodsTemplate/mulDeleteDetpTemplateGoods', this.multipleSelection).then(res => {
          o.loading = false
          if (res.data.code == '0') {
            o.loading = false
            o.$message({ message: '操作成功！', type: 'success' })
          } else {
            o.$message.error(res.data.msg)
          }
          o.queryDeptTemplateGoods(o.page)
        }, err => {
          o.loading = false
        })
      }).catch(() => {})
    },

    searchOrgs: function () {

    },
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    getCGMS: function () {
      this.axios.post('/platformService/sys/dict/getDictValueByDictEname', {dictName: 'CGMS'}).then(res => {
        if (res.data.code == '0') {
          this.purModes = res.data.data
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    },

    // 复选框的方法
    handleCheckAllChange (val) {
      this.checkedUsers = val ? this.users : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange (value) {
      let checkedCount = value.length
      this.checkAllUser = checkedCount === this.users.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.users.length
    },
    // 获取当前登录用户下所有人员
    getAllUser () {
      let temp = this.users
      let r_userMap = this.userMap
      this.axios.post(`/platformService/sys/user/getAllUser`).then(res => {
        let userList = res.data.data
        for (let i = 0; i < userList.length; i++) {
          let ename = userList[i].ename
          let j = {}
          j.name = ename
          j.userId = userList[i].userId
          r_userMap.push(j)
          temp[i] = ename
        }
      }, err => {
      })
    },
    showGoodsImg (item) {
      this.dlgGoodsImgLoading = true
      this.goodsImgs.origUrls.splice(0)
      this.goodsImgs.tUrls.splice(0)
      this.axios.get('/spdHERPService/productsInfos/hosGoodsImage/get/' + encodeURIComponent(item.goodsId))
        .then(res => {
          if (res.data.code < 0) {
            this.$message.error(res.data.msg)
          } else {
            let data = res.data.data
            for (let index = 1; index <= 6; index++) {
              if (data['filePath' + index] !== '') {
                this.goodsImgs.origUrls.push(this.picURLPrefix + data['filePath' + index])
                let slashIndex = data['filePath' + index].lastIndexOf('/')
                let tUrl = this.picURLPrefix + data['filePath' + index].substring(0, slashIndex) + '/thumb-' + data['filePath' + index].substring(slashIndex + 1)
                this.goodsImgs.tUrls.push(tUrl)
              }
            }
            this.dlgGoodsImgVisible = true
          }
          this.dlgGoodsImgLoading = false
        }).catch(err => {
          this.$message.error('查看商品图片信息失败，请联系管理员！' + err)
          this.dlgGoodsImgLoading = false
        })
    },
    imgMouseEnter (event, index) {
      let img = this.$refs['img' + index][0]
      img.setAttribute('src', img.getAttribute('orig-src'))
    }
  },
  mounted () {
    this.hosId = this.user.corpId
    this.deptId = this.user.orgId
    this.orgName = this.user.orgName
    this.getDeptGoodsTemplate()
    this.getAllUser()
  }
}
</script>

<style>
    #dept-product-model-mgr .el-tree-node__label {
        font-size: 12px;
    }
    #dept-product-model-mgr .el-table {
        font-size: 12px;
    }
    #dept-product-model-mgr .el-tree {
        min-height: calc(100% - 80px);
    }
    #dept-product-model-mgr .deptProdunctModal-tableheight {
        width: 100%;
        height:  calc(100% - 58px);
    }

    #dept-product-model-mgr .el-input__inner{
        height: 26px;
    }

    #dept-product-model-mgr .deptTemplateGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 45px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #dept-product-model-mgr .deptTemplateGoods .el-row{
        height: 36px;
    }

    #dept-product-model-mgr .el-dialog--deptGoodsDialog {
        max-width: 1200px;
        min-width: 1118px;
        width: 90%;
    }
    #dept-product-model-mgr .el-dialog--deptGoodsDialog .el-form-item {
        margin-bottom: 7px;
    }
    #dept-product-model-mgr .el-cascader--small{
        width: 75%;
    }
    #dept-product-model-mgr .el-dialog--editModal {
        max-width: 600px;
        width: 50%;
    }
    #dept-product-model-mgr .deptModal{
        border-radius: 4px;
        border: 1px solid #d1dbe5;
        margin-left: 20px;
        margin-right: 20px;
        padding-top: 11px;
        padding-bottom: 11px;
    }
</style>
