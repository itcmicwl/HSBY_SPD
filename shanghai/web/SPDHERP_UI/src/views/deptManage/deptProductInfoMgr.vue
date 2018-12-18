<template>
    <div id="dept-product-info-mgr" style="height: 100%;">
        <el-col :span="5" style="height: calc(99% - 31px);">
                <div style="padding-bottom: 7px">
                    <el-input
                        v-model="orgName"
                        placeholder="请输入科室名称"
                        icon="search"
                        >
                    </el-input>
                </div>
                <el-tree
                    :data="orgInfo"
                    node-key="id"
                    :default-expand-all="true"
                    :expand-on-click-node="false"
                    :default-expanded-keys="expandIds"
                    :props="orgInfoProps"
                    :highlight-current="true"
                    @node-click="handleNodeClick"
                   :filter-node-method="filterNode"
                    ref = "orgInfoTree">
                </el-tree>
            </el-col>
        <!--工具条-->
        <el-col :span="19" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <div style="padding-bottom: 1px;">
            <el-form :inline="true" :model="filters" label-width="80px" ref="goodsName">
                <el-form-item label="产品名称" prop="goodsName">
                    <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称" ></el-input>
                </el-form-item>
                <el-button-group>
                    <el-button type="primary" v-on:click="getGoodsList(1)" >查询</el-button>
                    <el-button type="primary" v-on:click="resetGoodsName('goodsName')" >重置</el-button>
                    <!--<el-button type="success" @click="handleImport(null)" >批量导入</el-button>-->
                    <el-button type="danger" @click="mulDel()" >批量删除</el-button>
                </el-button-group>
            </el-form>
            </div>
        <!--列表-->
        <template>
            <el-table  ref="multipleTable" :data="goods"  row-key='id'  @selection-change="deptGoodshandleSelectionChange"
                       highlight-current-row v-loading="loading"  border fit class="deptgoods-tableheight"  >
                <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                <el-table-column prop="goodsName" header-align="center" min-width="250" label="产品名称" ></el-table-column>
                <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="160"></el-table-column>
                <el-table-column prop="unit" align="center" label="单位" width="55"></el-table-column>
                <el-table-column prop="packetCode" align="center" label="包装数量" width="60"></el-table-column>
                <el-table-column prop="stocUpper" align="center" label="库存上限" width="60"></el-table-column>
                <el-table-column prop="stocLower" align="center" label="库存下限" width="60"></el-table-column>
                <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="300"></el-table-column>
                <el-table-column prop="brand" header-align="center" label="品牌" width="80"></el-table-column>
                <el-table-column label="操作"  align="center"  width="150">
                    <template slot-scope="scope">
                        <el-button-group>
                            <el-button type="primary" size="mini" @click="goodQuery(scope.row)" icon="search" >详情</el-button>
                            <el-button type="primary" size="mini" @click="goodEdit(scope.row)" icon="edit" >编辑</el-button>
                            <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete" >删除</el-button>
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
        <!--编辑界面-->
        <el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" size="mini">
            <el-form :model="good"  label-width="95px" ref="editForm" class="el-form-item-nomsg deptGoods">
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
                            <el-input v-model="good.stocUpper"  placeholder="请输入库存上限"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-form-item label="业务员Id：" prop="salemanId" v-show="false">
                        <el-input v-model="good.salemanId"  :disabled="true" ></el-input>
                    </el-form-item>
                    <el-col :span="8">
                        <el-form-item label="业务员：" :error="errors.salemanIdErr">
                                <div @click='selectUser(good.goodsId)'>
                                    <el-input v-model="good.salemanName" 
                                    icon="search" placeholder="点击选择业务员" ></el-input>
                                </div>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="业务员编码：">
                            <el-input v-model="good.salemanCode" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="ERP编码：" prop="erpCode" :error="errors.erpCodeErr">
                            <el-input v-model="good.erpCode" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="包装数量：" prop="goodsGg">
                            <el-input v-model="good.packetCode" :disabled="packetCodeDisabled" placeholder="请输入包装数量" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="产地：" prop="goodsGg">
                            <el-input v-model="good.made" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="库存下限：" prop="stocLower">
                            <el-input v-model="good.stocLower"  placeholder="请输入库存下限"></el-input>
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
                        <el-form-item label="品牌：" prop="goodsGg">
                            <el-input v-model="good.brand" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <!-- <el-col :span="8">
                        <el-form-item label="采购模式：" prop="purMode">
                            <el-select v-model="purMode" filterable placeholder="请选择" :clearable="true" >
                                <el-option
                                        v-for="item in purModes"
                                        :key="item.val"
                                        :label="item.ename"
                                        :value="item.val">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col> -->
                </el-row>

                <el-row style="padding-left: 10px;">
                    <el-col :span="8">
                        <el-form-item label="是否定数包管理：" labelWidth="120px" prop="isPacket">
                            <el-radio-group v-model="isPacket"  @change="isPacketChange">
                                <el-radio-button label="1">是</el-radio-button>
                                <el-radio-button label="0">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="定数包是否混批：" labelWidth="120px" prop="isMix">
                            <el-radio-group v-model="isMix" >
                                <el-radio-button label="1">是</el-radio-button>
                                <el-radio-button label="0">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="最小请购：" prop="reqNum">
                            <el-input v-model="good.reqNum"  placeholder="请输入最小请购数量"></el-input>
                        </el-form-item>
                    </el-col>
                    <!--<el-col :span="8" style="margin-left: -3px;">-->
                        <!--<el-form-item label="唯一码策略："  prop="isUnique">-->
                            <!--<el-select v-model="isUnique" style="width: 102%;" filterable placeholder="请选择" :clearable="true" >-->
                                <!--<el-option-->
                                        <!--v-for="item in wymglcl"-->
                                        <!--:key="item.val"-->
                                        <!--:label="item.ename"-->
                                        <!--:value="item.val">-->
                                <!--</el-option>-->
                            <!--</el-select>-->
                        <!--</el-form-item>-->
                    <!--</el-col>-->
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer" style="text-align: center;">
                <el-button @click.native="editFormVisible = false" >取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading" >提交</el-button>
            </div>
        </el-dialog>

        <!-- 选择业务员界面 -->
        <el-dialog title="请选择业务员" close="onClose" :visible.sync="selectUserVisible" :close-on-click-modal="false" >
            <el-form :inline="true" label-width="80px">
                <el-form-item label="业务员名称">
                    <el-input v-model="filters.userEname" placeholder="输入业务员名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="getUserList(1)" >查询</el-button>
                
            </el-form>   
                <template>
                    <el-table
                        ref="multipleSalemanUserTable" :data="userList"  border tooltip-effect="dark" row-key='id'
                        style="width: 100%" @selection-change="handleSalemanUserSelectionChange">
                        <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                        <el-table-column  prop="ename" label="业务员名称" width="200"></el-table-column>
                        <el-table-column prop="corpName" label="所属单位" show-overflow-tooltip> </el-table-column>
                        <el-table-column prop="orgName" label="所属部门" show-overflow-tooltip> </el-table-column>
                    </el-table>
                        <el-pagination @size-change="handleSalemanUserSizeChange" 
                                    @current-change="handleSalemanUserCurrentChange"  
                                    :current-page="page" :page-sizes="[10, 20, 30, 50]" :page-size="pageSize" 
                                    layout="total, sizes, prev, pager, next"
                                    :total="total" style="float:right;" small>
                        </el-pagination>
                    <div style="padding-top: 20px">
                        <el-button @click="toggleSalemanUserSelection()">取消选择</el-button>
                        <el-button @click="selectUserSubmit()">确定</el-button>
                    </div>
                </template> 
        </el-dialog>

        <el-dialog title="产品详情" close="onClose" :visible.sync="queryFormVisible" :close-on-click-modal="false" size="mini">
            <el-form :model="good"  label-width="95px"  class="el-form-item-nomsg deptGoods">
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
                        <el-form-item label="业务员：" prop="goodsGg">
                            <el-input v-model="good.salemanName" disabled  placeholder="业务员"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="业务员编码：" prop="salemanCode">
                            <el-input v-model="good.salemanCode" disabled  placeholder="业务员编码"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="erp编码：" prop="erpCode">
                            <el-input v-model="good.erpCode" disabled ></el-input>
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
                        <el-form-item label="品牌：" prop="goodsGg">
                            <el-input v-model="good.brand" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <!-- <el-col :span="8">
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
                    </el-col> -->
                </el-row>
                <el-row style="padding-left: 10px;">
                    <el-col :span="8">
                        <el-form-item label="是否定数包管理：" labelWidth="120px" prop="isPacket">
                            <el-radio-group v-model="isPacket" readonly >
                                <el-radio-button label="1" :disabled="isPacketYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isPacketNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="定数包是否混批：" labelWidth="120px" prop="isMix">
                            <el-radio-group v-model="isMix" >
                                <el-radio-button label="1" :disabled="isMixYes">是</el-radio-button>
                                <el-radio-button label="0" :disabled="isMixNo">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="最小请购：" prop="reqNum">
                            <el-input disabled v-model="good.reqNum"  placeholder="请输入最小请购数量"></el-input>
                        </el-form-item>
                    </el-col>
                    <!--<el-col :span="8" style="margin-left: -3px;">-->
                        <!--<el-form-item label="唯一码策略：" prop="isUnique">-->
                            <!--<el-select v-model="isUnique" disabled style="width: 102%;" filterable placeholder="请选择" :clearable="true" >-->
                                <!--<el-option-->
                                        <!--v-for="item in wymglcl"-->
                                        <!--:key="item.val"-->
                                        <!--:label="item.ename"-->
                                        <!--:value="item.val">-->
                                <!--</el-option>-->
                            <!--</el-select>-->
                        <!--</el-form-item>-->
                    <!--</el-col>-->
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer" style="text-align: center;">
                <el-button @click.native="queryFormVisible = false" >取消</el-button>
            </div>
        </el-dialog>

        <el-dialog title="批量导入产品" close="onClose" :visible.sync="goodImportVisible" :close-on-click-modal="false" size="hosGoodsDialog">
            <el-form :inline="true" :model="basGoodsFilters" label-width="80px" ref="hosGoodsName">
                <el-form-item label="商品名称" prop="goodsName">
                    <el-input v-model="basGoodsFilters.goodsName" placeholder="输入商品名称" ></el-input>
                </el-form-item>
                <el-form-item label="产品分类" prop="goodsName">
                    <el-input v-model="basGoodsFilters.lbsx" placeholder="输入产品分类" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="getHosGoodsList(1)" >查询</el-button>
                <el-button type="primary" v-on:click="resetHosGoodsName('hosGoodsName')" >重置</el-button>
            </el-form>
            <template>
                <el-table ref="multipleHosGoodsTable" :data="basGoods"  border tooltip-effect="dark" row-key='id'
                        style="width: 100%" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column prop="goodsName" header-align="center" min-width="350" label="产品名称" max-width="300px"></el-table-column>
                    <el-table-column prop="goodsGg" header-align="center" label="规格型号" width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="lbsx" align="center" label="产品分类" width="80"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="80"></el-table-column>
                    <el-table-column prop="mfrsName" header-align="center" label="生产厂商" width="300"></el-table-column>
                    <el-table-column prop="brand" header-align="center" label="品牌" width="200"></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                <el-pagination @size-change="basHandleSizeChange" @current-change="basHandleCurrentChange"
                               :current-page="basPage" :page-sizes="[10, 20, 50, 100]"
                               :page-size="basPageSize" layout="total, sizes, prev, pager, next"
                               :total="basTotal" style="float:right;" small>
                </el-pagination>
                    <el-button @click="toggleSelection()" >取消选择</el-button>
                    <el-button @click="goodImprotSubmit()" type="success" >确定</el-button>
                    <el-button @click.native="goodImportVisible = false" type="primary" >返回</el-button>
                </div>
            </template>
        </el-dialog>

    </div>

</template>
<script>
import { fileServer } from '../../common/js/constance'
export default {
  data () {
    return {
      packetCodeDisabled: true,
      purModes: [],
      wymglcl: [],
      stocId: '',
      deptStocInfo: [],
      stocInfo: {},
      deptGoodsInfo: {},
      queryFormVisible: false,
      editFormVisible: false,
      isPacket: '1',
      isUnique: '3',
      isMix: '1',
      isPacketYes: false,
      isPacketNo: false,
      isUniqueYes: false,
      isUniqueNo: false,
      isMixYes: false,
      isMixNo: false,
      purMode: '10',

      hosId: '',
      deptId: '',
      hosName: '',
      deptName: '',
      selectedId: '', // 选中的厂家Id
      selectedName: '', // 选中的厂家name
      myPorps: {
        value: 'mfrsId',
        label: 'mfrsName'
      },
      filters: {
        goodsName: '',
        orgName: ''
      },
      basGoodsFilters: {goodsName: '',lbsx:''},
      total: 0,
      page: 1,
      pageSize: 20,
      loading: false,
      goods: [],
      good: {
          mfrsId: '',
      },
      basGoods: [],
      basTotal: 0,
      basPage: 1,
      basPageSize: 10,
      modelTitle: '',
      editLoading: false,
      multipleSelection: [],
      multipleDeptGoodsSelection: [],
      goodImportVisible: false,
      // 树
      expandIds: [],
      orgInfo: [],
      orgInfoProps: {
        children: 'children',
        label: 'label',
        id: 'id',
        pid: 'pid'
      },
      // 快速查询
      orgName: '',
      //业务员操作先关属性
        multipleSalemanUserSelection: [],
        editFormRules: {
            erpCode: [{ required: true, message: '请输入ERP编码', trigger: 'blur' }],
            salemanId: [{ required: true, message: '请选择业务员', trigger: 'blur' }]
        },
        errors: {
            erpCodeErr: '',
            salemanIdErr: ''
        },
        selectUserVisible:false,
        userList:[],

        // 用于查询业务员
        hosGoodsId:''

    }
  },
  watch: {
    orgName (val) {
      this.$refs.orgInfoTree.filter(val)
    }
  },
  methods: {

    //选择业务员
    selectUser:function(goodsId){
        if(goodsId == null || goodsId.length == 0){
            return;
        }
        this.hosGoodsId = goodsId;
        this.selectUserVisible=true;
        this.getUserList(1);
    },
    handleSalemanUserSelectionChange(val) {
        this.multipleSalemanUserSelection = val;
    },
    toggleSalemanUserSelection(rows) {
        if (rows) {
        rows.forEach(row => {
            this.$refs.multipleSalemanUserTable.toggleRowSelection(row);
        });
        } else {
        this.$refs.multipleSalemanUserTable.clearSelection();
        }
    },
    handleSalemanUserCurrentChange(val) {
    this.page = val;
    this.getUserList(this.page);
    },
    handleSalemanUserSizeChange(val) {
        this.pageSize = val;
        this.getUserList(this.page);
    },
    getUserList:function(pIndex){
        this.page = pIndex;
        var param = { 
            orderBy: "", 
            queryObject: { 
                ename: this.filters.userEname,
                hosId:this.hosId,
                hosGoodsId:this.hosGoodsId
            }, 
            page: this.page, 
            rows: 10 
        };
        this.loading = true;
        this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/getSaleManList', param).then(res => {
            this.loading = false;
            this.userList = res.data.data.data;
            this.total = res.data.data.total;
        }, err => {
            this.loading = false;
        });
    },
    selectUserSubmit:function(){
        if(this.multipleSalemanUserSelection.length!=1){
            this.$message.error( "只能选择一个业务员！");
            return;
        }
        this.good.salemanId =  this.multipleSalemanUserSelection[0].userId;
        this.good.salemanName = this.multipleSalemanUserSelection[0].ename;
        this.good.salemanCode = this.multipleSalemanUserSelection[0].erpCode;
        this.$refs.multipleSalemanUserTable.clearSelection();
        this.selectUserVisible= false;
    },
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 查询
    searchOrgs: function () {
      this.filters.orgName = this.orgName
      var p = this.filters.orgName
      let param = { cname: p }
      this.loading = true
      this.axios.post(`/platformService/sys/org/searchOrgs`, param).then(res => {
        this.loading = false
        var ids = []
        for (var i = 0; i < res.data.data.length; i++) {
          ids[i] = res.data.data[i].id
        }
        this.expandIds = ids
      })
      this.loading = false
    },
    getOrgsByUnit: function (unitId) {
      var param = {}
      this.loading = true
      this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res => {
        this.orgInfo = res.data.data.children
        this.expandIds = [this.orgInfo[0].id]
        this.loading = false
      })
    },
    // 点击即查询
    handleNodeClick: function (item) {
      this.deptId = item.id;
      this.deptName = item.label;
      this.getGoodsList(1);
    },
    /* 输入框自动补全**/
    querySearch (queryString, cb) {
      var restaurants = this.mfrs
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter (queryString) {
      return (restaurant) => {
        return (restaurant.mfrsName.indexOf(queryString.toLowerCase()) >= 0)
      }
    },
    handleSelect (item) {
      this.selectedId = item.id
      this.selectedName = item.mfrsName
    },
    // 获取产品列表
    getGoodsList: function (pIndex) {
      this.page = pIndex
      var param = {
        orderBy: '',
        queryObject: {
          goodsName: this.filters.goodsName,
          hosId: this.hosId,
          deptId: this.deptId
        },
        page: this.page,
        rows: this.pageSize
      }
      this.loading = true
      this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoList', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.goods = res.data.data.data
          this.total = res.data.data.total
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },

    resetGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleTable.clearSelection()
      this.getGoodsList(1)
    },

    resetHosGoodsName: function (formName) {
      this.$refs[formName].resetFields()
      this.$refs.multipleHosGoodsTable.clearSelection()
      this.getHosGoodsList(1)
    },

    /**
             * 获取科室库房信息
             */
    listDeptStocInfo: function () {
      this.stocInfo = {
        hosId: this.hosId,
        deptId: this.deptId
      }
      this.axios.post('/spdHERPService/deptMgr/stocInfo/listDeptStocInfo', this.stocInfo).then(res => {
        this.deptStocInfo = res.data.data
      })
    },

    handleCurrentChange (val) {
      this.page = val
      this.getGoodsList(this.page)
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.getGoodsList(this.page)
    },
    goodEdit (item) {
      let good = Object.assign({}, item)
      this.good = good
      this.good.stocUpper = 1000
      this.good.stocLower = 10
      this.stocId = this.good.stocId
      this.purMode = this.good.purMode == null ? this.purMode : this.good.purMode
      this.isPacket = this.good.isPacket == null ? this.isPacket : this.good.isPacket
      this.packetCodeDisabled = this.isPacket != '1'
      this.isUnique = this.good.isUnique == null ? this.isUnique : this.good.isUnique
      this.isMix = this.good.isMix == null ? this.isMix : this.good.isMix
      this.modelTitle = '编辑产品'
      this.editFormVisible = true
      //                this.listDeptStocInfo();// 查询科室库房信息
    },

    goodQuery (item) {
      let good = Object.assign({}, item)
      this.good = good
      this.stocId = this.good.stocId
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
      this.queryFormVisible = true
    },
    isPacketChange: function (d) {
      if (d == '0') {
        this.good.packetCode = ''
        this.packetCodeDisabled = true
      } else {
        this.packetCodeDisabled = false
      }
    },
    delGood (good) {
      delete good.stocName;
      delete good.filterFlag;
      delete good.goodsNum;
      delete good.salemanName;
      delete good.salemanCode;
      delete good.lbsx;
      this.$confirm('确认删除这条记录吗?', '提示', {
      }).then(() => {
        this.loading = true
        this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoDelete', good).then(res => {
          this.loading = false
          if (res.data.code == '0') {
            this.loading = false
            this.$message({ message: '操作成功！', type: 'success' })
            this.getGoodsList(this.page)
          } else {
            this.$message.error(res.data.msg)
          }
        }, err => {
          this.loading = false
        })
      }).catch(() => {})
    },

    mulDel () {
      if (this.multipleDeptGoodsSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      this.multipleDeptGoodsSelection.forEach(item => {
        delete item.stocName;
        delete item.filterFlag;
        delete item.goodsNum;
        delete item.salemanName;
        delete item.salemanCode;
        delete item.lbsx;
      })
      this.$confirm('确认删除选中记录吗?', '提示', {
      }).then(() => {
        this.loading = true
        this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoMulDelete', this.multipleDeptGoodsSelection).then(res => {
          this.loading = false
          if (res.data.code == '0') {
            this.loading = false
            this.$message({ message: '操作成功！', type: 'success' })
            this.$refs.multipleTable.clearSelection()
          } else {
            this.$message.error(res.data.msg)
          }
          this.getGoodsList(this.page)
        }, err => {
          this.loading = false
        })
      }).catch(() => {})
    },

    editSubmit: function () {
      delete this.good.filterFlag
      this.good.stocId = this.stocId
      this.good.purMode = this.purMode
      this.good.isPacket = this.isPacket
      this.good.isUnique = this.isUnique
      this.good.isMix = this.isMix
      let tempGood = this.good
      delete tempGood.stocName
      // no useful field goodsNum
      delete tempGood.goodsNum
      delete tempGood.salemanName;
      // delete tempGood.salemanCode;
        delete tempGood.lbsx;
      var _this = this
      this.editLoading = true
      this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoEdit', tempGood).then(res => {
        this.editLoading = false
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
          this.editFormVisible = false
          this.getGoodsList(this.page)
        } else {
          this.$message.error('操作失败！')
        }
      }, err => {
        this.editLoading = false
      })
    },

    handleImport: function () {

      this.goodImportVisible = true
      this.multipleSelection = []
        this.$refs.multipleHosGoodsTable.clearSelection()

        this.getHosGoodsList(1)
    },

    // 获取医院产品列表
    getHosGoodsList: function (pIndex) {
      this.basPage = pIndex
      var param = {
        orderBy: '',
        queryObject: {
          hosId: this.hosId,
          deptId: this.deptId,
          goodsName: this.basGoodsFilters.goodsName,
          lbsx:this.basGoodsFilters.lbsx
        },
        page: this.basPage,
        rows: this.basPageSize
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoImportQuery', param).then(res => {
        if (res.data.code == '0') {
          this.basGoods = res.data.data.data
          this.basTotal = res.data.data.total
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
      })
    },

    goodImprotSubmit: function () {
      if (this.multipleSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      var param = {
        hosId: this.hosId,
        deptId: this.deptId,
        hosName: this.hosName,
        deptName: this.deptName,
        hosGoodsInfos: this.multipleSelection
      }
      this.axios.post('/spdHERPService/deptMgr/deptGoodsInfo/deptGoodsInfoImport', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.goodImportVisible = false
          this.$message({ message: '操作成功！', type: 'success' })
          this.getGoodsList(1)
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
    deptGoodshandleSelectionChange (val) {
      this.multipleDeptGoodsSelection = val
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
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
    getWYMGLCL: function () {
      this.axios.post('/platformService/sys/dict/getDictValueByDictEname', {dictName: 'WYMGLCL'}).then(res => {
        if (res.data.code == '0') {
          this.wymglcl = res.data.data
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    }
  },
  mounted () {
    this.hosId = this.user.corpId
    // this.deptId = this.user.orgId;
    this.hosName = this.user.corpName
    this.deptName = this.user.orgName
    // this.getGoodsList(1);
    this.getOrgsByUnit()
    this.getCGMS()
    this.getWYMGLCL()
  }
}

</script>
<style>
    #dept-product-info-mgr .el-checkbox__label{
        font-size: 12px;
        vertical-align: middle;
    }
    #dept-product-info-mgr .el-checkbox-group{
        vertical-align: middle;
    }
    #dept-product-info-mgr .deptgoods-tableheight {
        width: 100%;
        height:  calc(100% - 65px);
    }
    #dept-product-info-mgr .deptGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 10px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 5px;
        margin-right: 5px;
    }
    #dept-product-info-mgr .deptGoods .el-row{
        height: 36px;
    }
    #dept-product-info-mgr .el-dialog--hosGoodsDialog {
        max-width: 1200px;
        min-width: 1118px;
        width: 90%;
    }
    #dept-product-info-mgr .el-dialog--hosGoodsDialog .el-form-item {
        margin-bottom: 7px;
    }

</style>
