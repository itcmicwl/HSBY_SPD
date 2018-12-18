<template>
    <div id="sur-def-list-mgr" style="height: 100%;">
        <el-row style="height: 99%;padding-bottom: 0px;">
            <el-col :span="6" style="height: calc(100% - 31px);">
                <div style="width: 260px;">
                    <el-input placeholder="请输入手术包名称" v-model="filterText" ></el-input>
                </div>
                <el-tree :data="surDefNode"
                         node-key="id" ref="tree2"
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
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称"  @keyup.enter.native="querySurGoods(1)"></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="querySurGoods(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetGoodsName('deptProductModalForm')" >重置</el-button>
                        <el-button type="success" @click="handleImport"  :disabled="importDisabled">批量导入</el-button>
                        <el-button type="danger" @click="mulDel()" :disabled="importDisabled">批量移除</el-button>
                    </el-button-group>
                </el-form>
                <!--列表-->
                <el-table key="goodsListTable" ref="multipleTable" :data="surGoods"    @selection-change="handleSelectionChange" row-key="id"
                           highlight-current-row v-loading="loading"  border fit class="goods-tableheight">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
										<el-table-column prop="surName" align="center" width="150" label="直属手术包" show-overflow-tooltip></el-table-column>
  <!--                   <el-table-column label="图片" width="60" header-align="center" align="center">
                        <template slot-scope="scope">
														<el-button type="info"  size="mini" icon="el-icon-picture" @click.native="showGoodsImg(scope.row)"></el-button>
                        </template>
                    </el-table-column> -->
                    <el-table-column prop="hosGoods.goodsName" header-align="center" min-width="200" label="产品名称" show-overflow-tooltip></el-table-column>
                    <el-table-column header-align="center" label="数量" width="100">
                         <template slot-scope="scope">
														<el-input-number  v-model="scope.row.qty" :disabled="scope.row.hasUse" size="mini" :min="1" class="col-input-num" @change="goodsQtyChange($event, scope.row)">
                            </el-input-number>
                        </template>
                    </el-table-column>
										<el-table-column label="是否需要消毒" width="110" prop="hosGoods.shouldSterilize">
												<template slot-scope="scope">
													<!-- <el-switch  v-model="scope.row.shouldSterilize" active-color="#13ce66" inactive-color="#ff4949" active-text="是" inactive-text="否" :active-value=1 :inactive-value=0  @change="sterilizeChange($event, scope.row)">
													</el-switch> -->
													<el-radio-group v-model="scope.row.shouldSterilize" size="mini"  @change="sterilizeChange($event, scope.row)">
														<el-radio-button label="1" style="padding:1px;" :disabled="scope.row.hasUse">是</el-radio-button>
														<el-radio-button label="0" style="padding:1px;" :disabled="scope.row.hasUse">否</el-radio-button>
													</el-radio-group>
												</template>
										</el-table-column>
                    <el-table-column prop="hosGoods.goodsGg" header-align="center" label="规格型号" width="200" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="hosGoods.unit" align="center" label="单位" width="60"></el-table-column>
                    <el-table-column prop="hosGoods.packeage" align="center" label="包装数量" width="60"></el-table-column>
                    <el-table-column prop="hosGoods.made" align="center" label="产地" width="70"></el-table-column>
                    <el-table-column prop="hosGoods.mfrsName" header-align="center" label="生产厂商" width="200" show-overflow-tooltip></el-table-column>
                    <el-table-column label="操作"  align="center" fixed="right" width="120">
                        <template slot-scope="scope">
                            <el-button-group>
                                <el-button type="primary" size="mini" @click="goodsDetailInfoQuery(scope.row)" icon="search" >查看</el-button>
                                <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete" :disabled="scope.row.hasUse">移除</el-button>
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

        <el-dialog title="产品详情" close="onClose" :visible.sync="detailFormVisible" :close-on-click-modal="false" size="mini">
            <el-form :model="goodsDetailInfo"  label-width="120px"  class="el-form-item-nomsg deptsurGoods">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="产品名称：" >
                            <el-input v-model="goodsDetailInfo.goodsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="规格型号：" prop="goodsGg">
                            <el-input v-model="goodsDetailInfo.goodsGg" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="单位：" prop="goodsGg">
                            <el-input v-model="goodsDetailInfo.unit" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="包装数量：" prop="goodsGg">
                            <el-input v-model="goodsDetailInfo.packeage" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="产地：" prop="goodsGg">
                            <el-input v-model="goodsDetailInfo.made" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="生产厂商：" prop="mfrsName">
                            <el-input v-model="goodsDetailInfo.mfrsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
								<el-row>
                    <el-col :span="24">
                        <el-form-item label="集配商名称：" >
                            <el-input v-model="goodsDetailInfo.provName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="采购模式：" prop="purMode">
													<el-input v-model="purModeName" disabled ></el-input>													
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否唯一码管理：" prop="uniqueCodeStrategy">
													<el-input v-model="uniqueCodeStrategyName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer" style="text-align: center;">
                <el-button @click.native="detailFormVisible = false" >取消</el-button>
            </div>
        </el-dialog>

        <el-dialog title="批量导入产品" close="onClose" :visible.sync="goodImportVisible" :close-on-click-modal="false" width="75%" >
            <el-form :inline="true" :model="hosGoodsFilters" label-width="80px" ref="surGoodsImportForm">
                <el-form-item label="商品名称" prop="goodsName">
                    <el-input v-model="hosGoodsFilters.goodsName" placeholder="输入商品名称" ></el-input>
                </el-form-item>
                <el-button type="primary" v-on:click="getSurDefGoodsNoImportList(1)" >查询</el-button>
                <el-button type="primary" v-on:click="resetSurDefGoodsName('surGoodsImportForm')" >重置</el-button>
            </el-form>
            <template>
                <el-table key="goodsImportTable" ref="multipleImportGoodsTable" :data="basGoods"  border tooltip-effect="dark" row-key="goodsId"
                          style="width: 100%" @selection-change="handleImportSelectionChange" class="importGoods-tableheight">
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <el-table-column label="数量"  width="100">
                        <template slot-scope="scope">
                            <el-input-number name="importQty" placeholder="请输入数量"  v-model="scope.row.qty" size="mini" :min="1" class="col-input-num">
                            </el-input-number>
                        </template>
                    </el-table-column>
										<el-table-column label="是否需要消毒"  width="120">
                        <template slot-scope="scope">
									<!-- 				<el-switch name="importSterilize" v-model="scope.row.shouldSterilize" active-color="#13ce66" inactive-color="#ff4949" active-text="是" inactive-text="否" :active-value="1" :inactive-value="0" >
													</el-switch> -->
													<el-radio-group v-model="scope.row.shouldSterilize" size="mini" >
														<el-radio-button label="1" style="padding:1px;">是</el-radio-button>
														<el-radio-button label="0" style="padding:1px;">否</el-radio-button>
													</el-radio-group>
                        </template>
                    </el-table-column>

                    <el-table-column prop="hosGoods.goodsName" header-align="center" label="产品名称" min-width="250px" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="hosGoods.goodsGg" header-align="center" label="规格型号" width="200" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="hosGoods.unit" align="center" label="单位" width="80"></el-table-column>
                    <el-table-column prop="hosGoods.packeage" align="center" label="包装数量" width="80"></el-table-column>
                    <el-table-column prop="hosGoods.made" align="center" label="产地" width="80" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="hosGoods.mfrsName" header-align="center" label="生产厂商" width="250" show-overflow-tooltip></el-table-column>
                </el-table>
                <div style="padding-top: 6px">
                    <el-pagination @size-change="basHandleSizeChange" @current-change="basHandleCurrentChange"
                                   :current-page="basPage" :page-sizes="[10, 20, 50, 100]"
                                   :page-size="basPageSize" layout="total, sizes, prev, pager, next"
                                   :total="basTotal" style="float:right;" small>
                    </el-pagination>
                    <el-button @click="toggleSelection()" >取消选择</el-button>
                    <el-button @click="goodImprotSubmit()" type="success" >确定</el-button>
                    <el-button @click.native="cancelDig()" type="primary" >返回</el-button>
                </div>
            </template>
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

			goodsDetailInfo: '',

      basTotal: 0,
      basPage: 1,
      basPageSize: 10,
      basGoods: [],
			purModes: [],
			dictUCodeStrategys: [],
      detailFormVisible: false,
      goodImportVisible: false,

      multipleSelection: [],
			multipleImportSelection:[],
      filters: { goodsName: '' },
      hosGoodsFilters: {goodsName: ''},

      importDisabled: true,
      surGoods: [],
      filterText: '',
      hosId: '',
      total: 0,
      page: 1,
      pageSize: 20,
      loading: false,
      surDefNode: [{
        children: [],
        label: '',
        id: '',
        pid: ''
      }],
      surGoodsVo: {},
      defaultProps: {
        children: 'children',
        label: 'label',
        id: 'id',
        pid: 'pid'
      },

			// 已经使用了的手术包，如存在该手术包的请购记录
			surPkgUses:[],
			// 手术包类型：2：手术包，1：分类
			selNodeType:'',
			surIds:[]
    }
	},
	computed: {
		purModeName : function(){
			if(this.purModes.length === 0){
					return "";
			}
			let purMode = this.purModes.find(item => item.val == this.goodsDetailInfo.purMode);
			if(purMode){
					return purMode.ename;
			} else {
					return "";
			}
		},
		uniqueCodeStrategyName: function(){
			if(this.dictUCodeStrategys.length === 0){
					return "";
			}
			let strategy = this.dictUCodeStrategys.find(item => item.val == this.goodsDetailInfo.uniqueCodeStrategy);
			if(strategy){
					return strategy.ename;
			} else {
					return "";
			}
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
      this.$refs.multipleImportGoodsTable.clearSelection();
    },
		sterilizeChange(value, row){
      var param = {
        id: row.id,
        shouldSterilize: value,
        version: row.version
      }
      this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/update', param).then(res => {
        if (res.data.code == '0') {
					let count = res.data.data;
					if(count > 0){
          	this.$message({ message: '操作成功！', type: 'success' })
						this.querySurGoods(1)
					}
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
			
		},
    goodsQtyChange (value, row) {
      var param = {
        id: row.id,
        qty: value,
        version: row.version
      }
      this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/update', param).then(res => {
        if (res.data.code == '0') {
					let count = res.data.data;
					if(count > 0){
          	this.$message({ message: '操作成功！', type: 'success' })
						this.querySurGoods(1)
					}
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})

    },

    // 批量导入产品：
    handleImport: function () {
      this.goodImportVisible = true
			this.multipleImportSelection = []
      this.getSurDefGoodsNoImportList(1)
    },
    resetSurDefGoodsName: function (formName) {
			this.$refs[formName].resetFields()
      this.$refs.multipleImportGoodsTable.clearSelection()
      this.getSurDefGoodsNoImportList(1)
    },
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleImportGoodsTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleImportGoodsTable.clearSelection()
      }
    },
    // 批量导入产品提交
    goodImprotSubmit: function () {
      if (this.multipleImportSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
   		// 去重
      for (var i in this.surGoods) {
        var id = this.surGoods[i].goodsId
        for (var j in this.multipleImportSelection) {
          if (this.multipleImportSelection[j].goodsId == id) {
            this.multipleImportSelection.splice(j, 1)
          }
        }
      }
      if (this.multipleImportSelection.length <= 0) {
        this.$message('产品重复')
        this.goodImportVisible = false
        return
      }
			this.multipleImportSelection.forEach(o=>{
				o.surId = this.surIds[0];
				o.hosId = this.user.corpId;
			});
      this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/importGoods', this.multipleImportSelection).then(res => {
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success' })
          this.goodImportVisible = false
          this.$refs.multipleImportGoodsTable.clearSelection()
          this.querySurGoods(1)
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
			
    },

    // 查询未导入的手术包商品信息
    getSurDefGoodsNoImportList: function (pIndex) {
      this.basPage = pIndex
      var param = {
        orderBy: '',
        queryObject: {
          hosId: this.hosId,
          surId: this.surIds[0],
          goodsName: this.hosGoodsFilters.goodsName
        },
        page: this.basPage,
        rows: this.basPageSize
      }
      this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/listNotImportGoodsByPage', param).then(res => {
        if (res.data.code == '0') {
          this.basGoods = res.data.data.data
          this.basGoods.forEach(row => {
            this.$set(row, 'qty', 1);
						this.$set(row, 'shouldSterilize', 1)
          })
          this.basTotal = res.data.data.total
          this.hosGoodsFilters.goodsName = '';
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
      })
    },
    basHandleCurrentChange (val) {
      this.basPage = val
      this.getSurDefGoodsNoImportList(this.basPage)
    },
    basHandleSizeChange (val) {
      this.basPageSize = val
      this.getSurDefGoodsNoImportList(this.basPage)
    },
    getSurKindDefTree: function () {
				var param = {};
				this.loading = true;
				this.axios.post(`/spdHERPService/surgery/surgeryPkgDef/getSurgeryKindDefTreeByHos`, { }).then(res => {
					this.surDefNode = res.data.data.children;
					this.loading = false;
				});
    },
    handleNodeClick: function (d1, d2, d3) {
      // 给自定义的手术包id赋值
			this.surIds = [];
			this.surGoods = [];
			this.surPkgUses = [];
			
			this.multipleSelection = []
			this.$refs.multipleTable.clearSelection()

			this.selNodeType = d1.nodeType

			// 手术包节点
      if (d1.nodeType == '2') {
				this.surIds.push(d1.id);
      } else {
				this.importDisabled = true// 非手术包结点不能导入产品数据
        this.getNodeSubSurDefs(d1.children)// 手术包分类节点下所有手术包结点数组
				 
			}
			
			// 节点存在手术包
			if(this.surIds && this.surIds.length > 0){
				// 查询手术包商品信息
				this.querySurGoods(this.page)				
			}
    },
    getNodeSubSurDefs: function (children) {
      children.forEach(item => {
        if (item.nodeType == '2') {
					this.surIds.push(item.id);
        } else {
          this.getNodeSubSurDefs(item.children)
        }
      })
		},
		
    /**
		* 查询手术包商品信息，并判断手术包是否能更新明细。
		*（只有手术包结点，且该手术包没有单据才能导入产品数据更新手术包明细）
		*/
    querySurGoods: function (page) {
      let o = this
      this.page = page
			this.surGoods = [];
			this.surPkgUses = [{hasUse:false}]

			this.loading = true

			this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/hasUseSurPkg', {selSurIds:this.surIds}).then(res => {
				this.surPkgUses = res.data.data
				
				// 手术包节点
				if(this.selNodeType == '2'){
					this.importDisabled = this.surPkgUses[0].hasUse
				} else {
					this.importDisabled = true
				}

				var param = {
					orderBy: '',
					queryObject: {
						selSurIds: this.surIds,
						hosId: this.hosId,
						goodsName: this.filters.goodsName
					},
					page: page,
					rows: this.pageSize
				}

				this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/listByPage', param).then(res => {
					this.surGoods = res.data.data.data
					this.total = res.data.data.total

					this.surGoods.forEach(goods=>{
						this.surPkgUses.forEach(useGoods=>{
							if(goods.surId == useGoods.surId){
								goods.hasUse = useGoods.hasUse
							}
						})
					})

					this.loading = false
				}, err => {
					this.loading = false
				})
      }, err => {
			})
		
    },
    /**
		* 重置手术包商品查询
		*/
    resetGoodsName: function (formName) {
      this.$refs[formName].resetFields();
      this.$refs.multipleTable.clearSelection();
      this.querySurGoods(1)
    },

    handleSizeChange (val) {
      this.pageSize = val
      this.querySurGoods(this.page)
    },

    handleCurrentChange (val) {
      this.page = val
      this.querySurGoods(this.page)
    },

    handleSelectionChange (val) {
      this.multipleSelection = val
    },

		handleImportSelectionChange (val) {
      this.multipleImportSelection = val
    },

    /**
		* 产品详情查询
		*/
    goodsDetailInfoQuery (item) {
			this.goodsDetailInfo = {};

			this.getCGMS()
			this.getDictUCodeStrategys()

      let param = {
        goodsId:item.goodsId
      }
      this.loading = true
      this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/getGoodsInfo', param).then(res => {
        if(res.data.data){
					this.goodsDetailInfo = res.data.data[0];
					this.detailFormVisible = true
				}
				
        this.loading = false
      }, err => {
        this.loading = false
      })

    },

    // 移除手术包产品
    delGood (surGood) {
      let o = this
      let param = {id: surGood.id}
      o.$confirm('确认移除该商品吗?', '提示', {
      }).then(() => {
        o.loading = true
        o.axios.post('/spdHERPService/surgery/surgeryPkgDefList/delete', param).then(res => {
          o.loading = false
          if (res.data.code == '0') {
            o.loading = false
            o.$message({ message: '操作成功！', type: 'success' })
          } else {
            o.$message.error(res.data.msg)
          }
          o.querySurGoods(o.page)
        }, err => {
          o.loading = false
        })
      }).catch(() => {})
    },

    // 批量移除手术包产品
    mulDel () {
      if (this.multipleSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      this.$confirm('确认删除选中记录吗?', '提示', {
      }).then(() => {
        this.loading = true
        this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/deleteByBatch', this.multipleSelection).then(res => {
          this.loading = false
          if (res.data.code == '0') {
            this.loading = false
            this.$message({ message: '操作成功！', type: 'success' })
          } else {
            this.$message.error(res.data.msg)
          }
					this.$refs.multipleTable.clearSelection();
          this.querySurGoods(this.page)
        }, err => {
          this.loading = false
        })
      }).catch(() => {})
    },

    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
		// 获取采购模式数据字典
    getCGMS: function () {
      this.axios.post('/platformService/sys/dict/getDictValueByDictEname', {dictName: 'CGMS'}).then(res => {
        if (res.data.code == '0') {
          this.purModes = res.data.data
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
		},
		// 唯一码管理策略
		getDictUCodeStrategys: function () {
      this.loading = true
      this.axios
        .post('/platformService/sys/dict/getDictValueByDictEname', {
          dictName: 'WYMGLCL'
        })
        .then(
          res => {
            if (res.data.code == '0') {
              this.dictUCodeStrategys = res.data.data
            } else {
              this.$message.error(res.data.msg)
            }
            this.loading = false
          },
          err => {
            this.loading = false
          }
        )
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
						if(data && data.length > 0){
							for (let index = 1; index <= 6; index++) {
								if (data['filePath' + index] !== '') {
									this.goodsImgs.origUrls.push(this.picURLPrefix + data['filePath' + index])
									let slashIndex = data['filePath' + index].lastIndexOf('/')
									let tUrl = this.picURLPrefix + data['filePath' + index].substring(0, slashIndex) + '/thumb-' + data['filePath' + index].substring(slashIndex + 1)
									this.goodsImgs.tUrls.push(tUrl)
								}
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
    this.hosId = this.user.corpId;

    this.getSurKindDefTree()
  }
}
</script>

<style>
    #sur-def-list-mgr .el-tree-node__label {
        font-size: 12px;
    }
    #sur-def-list-mgr .el-table {
        font-size: 12px;
    }
    #sur-def-list-mgr .el-tree {
        min-height: calc(100% - 80px);
    }
    #sur-def-list-mgr .goods-tableheight {
        width: 100%;
        height:  calc(100% - 58px);
    }
    #sur-def-list-mgr .importGoods-tableheight {
        width: 100%;
        /* height:  calc(100% - 158px); */
    }
    #sur-def-list-mgr .el-input__inner{
        height: 26px;
    }

    #sur-def-list-mgr .deptsurGoods{
        border-radius: 6px;
        padding-bottom: 10px;
        padding-top: 12px;
        padding-right: 45px;
        border: 1px solid #d1dbe5;
        padding-left: 0px;
        margin-left: 15px;
        margin-right: 15px;
    }
    #sur-def-list-mgr .deptsurGoods .el-row{
        height: 36px;
    }

    #sur-def-list-mgr .el-cascader--small{
        width: 75%;
    }
    #sur-def-list-mgr .el-dialog--editModal {
        max-width: 600px;
        width: 50%;
    }
		#sur-def-list-mgr .el-radio-button__inner {
			padding-top: 4px;
			padding-bottom: 4px;
    }

/* 		#sur-def-list-mgr .el-dialog {
			max-height: calc(100% - 150px);
			height: calc(100% - 150px);
			max-width: calc(100% - 30px);
			display: flex;
    	flex-direction: column;
		}

		#sur-def-list-mgr .el-dialog .el-dialog__body{
			overflow: auto;
		} */



</style>
