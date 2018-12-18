<template>
    <div id="car"  style="height:95%">
        <el-row   style="height:100%">

            <el-col   style="padding-left: 5px;height:100%">
                <div style="margin-bottom: 0px;">
                    <el-form :inline="true" class="el-form-item-nomsg">
                        <el-form-item label="商品名">
                            <el-input v-model="goodFilters.goodsName" placeholder="请输入商品名"   ></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input v-model="goodFilters.goodsGg" placeholder="请输入规格型号"   > </el-input>
                        </el-form-item>
                        <!--
                        <el-form-item label="供应商">
                            <el-input v-model="goodFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>
                         -->
                        <el-button  type="primary"  @click="searchGoods(null)">查询</el-button>
                        <el-button  type="primary"  @click="createBill(null)">生成请购单</el-button>
                        <el-button  type="primary"  @click="delCar(null)">从请购车中删除</el-button>
                    </el-form>
                </div>
                <div  style="margin-top: 0px;">
                     <el-form :inline="true" class="el-form-item-nomsg">
                         <el-row>
                          <el-col :span="18">
                               <el-form-item label="目标科室">
                                    <el-select v-model="buyBill.sourceWarehouseId" filterable  clearable placeholder="请选择"  >
                                        <el-option
                                                v-for="item in relationDepts"
                                                :key="item.stockId"
                                                :label="item.deptName"
                                                :value="item.stockId">
                                        </el-option>
                                    </el-select>
                             </el-form-item>
                          </el-col>
                         </el-row>
                         <el-row>
                        <el-col :span="5">
                            <el-form-item label="收货人：">
                                {{buyBill.recLinkman}}
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="电话：">
                                {{buyBill.recLinkmanPhone}}
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="收货地址：">
                                {{buyBill.recAddress}}
                            </el-form-item>
                        </el-col>

                         <el-col :span="2">
                            <el-form-item label-width="0px">
                               <deliveryAddress btn_text="修改" v-on:addressChange="setAddress" :hosId="this.user.corpId" :deptId="this.user.orgId"></deliveryAddress>
                            </el-form-item>
                        </el-col>
                         </el-row>
                        <!-- <el-button  type="primary"  @click="updateAddress()">修改地址</el-button> -->

                      <!--  <el-button  type="primary"  @click="disConnect(null)">解除关联</el-button> -->
                    </el-form>
                </div>

                <el-table highlight-current-row  class="carbuy-tableheight"
                         :data="goodList" style="width: 100%"
                          ref="multipleTable4Main"
                          row-key='id'  @selection-change="handleSelectionChange4Main"
                          v-loading="goodsLoading" border height="outer"
                          :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                            <!--供货目录只列出供应商的 商品信息， 对码不在此处实现 -->
                            <!--
                            <el-table-column prop="hisCode"   min-idth="85" label="医院商品编号" header-align="center" >
                            </el-table-column>
                             -->
                             <el-table-column label="请购数量" width="100" >
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量" v-model="scope.row.needQty" size="mini" :min="scope.row.reqNum?scope.row.reqNum:0" :step="scope.row.reqNum?scope.row.reqNum:1" class="col-input-num" :disabled="scope.row.isPacket==1"  @change="qtyChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                           </el-table-column>
                            <el-table-column label="请购包数"  width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量"   v-model="scope.row.needPacketQty" size="mini" :min="scope.row.isPacket==0?0:1" :step="scope.row.reqNum?scope.row.reqNum:1" class="col-input-num" :disabled="scope.row.isPacket==0||scope.row.isPacket==null" @change="qtyPacketChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                           </el-table-column>
                            <!-- <el-table-column prop="code" label="商品编号" header-align="center"  width="120" >
                            </el-table-column> -->
                            <el-table-column label="图片" width="60" header-align="center" align="center">
                                <template slot-scope="scope">
                                  <el-button type="info" :disabled="!scope.row.imgAvailable" size="mini" icon="el-icon-picture" @click.native="showGoodsImg(scope.row)"></el-button>
                                </template>
                            </el-table-column>
                            <el-table-column prop="goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                              </el-table-column>
                            <el-table-column prop="brand"   min-idth="85" label="品牌" width="85" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="packetSpec"   min-idth="85" label="件包" width="85" header-align="center" show-overflow-tooltip >
                              </el-table-column>
                            <el-table-column prop="unit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                             <el-table-column prop="packetCode"   min-idth="85" label="包装数量" header-align="center" >
                            </el-table-column>
                            <!-- <el-table-column prop="price"   min-idth="85" label="单价" header-align="center" >
                            </el-table-column> -->
                             <!-- <el-table-column prop="qty"   min-idth="85" label="库存数量" header-align="center" >
                            </el-table-column> -->
                            <el-table-column prop="made"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="isPacket"   min-idth="10" label="是否定数包" header-align="center"  :formatter="dsFormatter">
                            </el-table-column>

<!--
                            <el-table-column prop="mfrsName"  header-align="center" width="150" show-overflow-tooltip label="生产厂商" >
                            </el-table-column>-->

                           <!--
                            <el-table-column prop="provName"  header-align="center" width="150"  label="供应商" show-overflow-tooltip sortable>
                            </el-table-column>
                            -->
                           <!--
                            <el-table-column label="操作" width="120"  header-align="center" fixed="right">
                                <template slot-scope="scope">
                                    <el-button-group>
                                		<el-button type="primary" size="mini" @click="goodEdit(scope.row)" icon="edit" >编辑</el-button>
                                        <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete">删除</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            -->
                            <!--<el-table-column type="selection" width="40"></el-table-column>-->
                            <el-table-column prop="generalName" min-idth="30" label="商品俗称" header-align="center" show-overflow-tooltip >
                            </el-table-column>

                             <el-table-column prop="goodsDesc" min-idth="30" label="商品描述" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                        </el-table>
                <el-pagination @size-change="handleGoodsSizeChange" @current-change="handleGoodsCurrentChange" :current-page="goodsPage" :page-sizes="[10, 20, 50, 100]" :page-size="goodsPageSize" layout="total, sizes, prev, pager, next" :total="goodsTotal" style="float:right;" small>
			   </el-pagination>
            </el-col>
        </el-row>

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
import deliveryAddress from '../../components/deliveryAddress.vue'
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

      defaultProps: {
        children: 'children',
        label: 'label',
        id: 'id',
        pId: 'pid',
        code: 'code',
        hosId: 'hosId',
        state: 'state'
      },
      filters: {
        kindName: null,
        hosId: null
      },
      kindsValue: [],
      expadoIds: [null],
      loading: 'false',
      pname: '商品分类',
      selectedNode: null,
      // 以下为右侧参数
      goodFilters: {
        goodsName: null,
        goodsGg: null,
        provName: null,
        lbsx: null
      },
      goodsLoading: false,
      goodList: [],
      goodsTotal: 0,
      goodsPageSize: 20,
      goodsPage: 1,
      multipleSelection4Main: [],
      buyBill: {
        id: null,
        subList: [],
        carList: [],
        recAddressId: '',
        recLinkman: '',
        recLinkmanPhone: '',
        recAddress: '',
        sourceWarehouseId: ''
      },
      address: {id: null, lxr: null, lxrPhone: null, provinceName: null, cityName: null, areaName: null, address: null, areaCode: null, isDefaulte: null},
      addressList: [],

      modelTitle: null,
      radio: '1',
      relationDepts: []

    }
  },
  components: { deliveryAddress },
  methods: {
    getKinds: function () {
      // this.loading=true;
      let param = { orderBy: '', queryObject: {hosId: this.filters.hosId}, page: 0, rows: 0 }
      this.axios.post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param).then(res => {
        this.kindsValue = res.data.data
        // this.loading=false;
      })
    },
    searchs: function () {
      this.loading = true
      let param = { orderBy: '', queryObject: {hosId: this.filters.hosId, kindName: this.filters.kindName}, page: 0, rows: 0 }
      this.axios.post(`/spdHERPService/productsInfos/hosKindcode/query`, param).then(res => {
        var ids = []
        for (var i = 0; i < res.data.data.length; i++) {
          ids[i] = res.data.data[i].id
        }
        this.loading = false
        this.expadoIds = ids
      })
    },
    handleNodeClick (data) {
      this.selectedNode = data
      this.goodFilters.lbsx = data.id

      this.getDeptCarGoodsStVo(1)
    },

    getDeptCarGoodsStVo: function (pIndex, cmd) {
      // 华山北院需求，请购不分实物与虚拟，从请购车生成请购单时依据商品属性10和20进行拆单 purMode即buyKind:0 传0，后台依据0来区分
      var this_ = this
      this.goodsPage = pIndex
      this.loading = true
      var param = { orderBy: '',
        queryObject: {hosId: this.user.corpId,
          deptId: this.user.orgId,
          goodsName: this.goodFilters.goodsName,
          goodsGg: this.goodFilters.goodsGg,
          buyKind: 0},
        page: this.goodsPage,
        rows: this.goodsPageSize
      }
      this.axios.post('/spdHERPService/applyMgr/deptBuyCar/getDeptCarGoodsStVo', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.goodList = res.data.data.data
          this.goodList.forEach(g => {
            g.needPacketQty = g.isPacket == 1 ? g.needQty / g.packetCode : 0
          })
          this.goodsTotal = res.data.data.total
          if (cmd === 'del' || cmd === 'create') {
		  this.multipleSelection4Main = []
            this_.$refs.multipleTable4Main.clearSelection()
		  }
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    searchGoods () {
      this.getDeptCarGoodsStVo(1, 'query')
    },
    handleGoodsSizeChange (val) {
      this.goodsPageSize = val
      this.getDeptCarGoodsStVo(this.goodsPage, 'query')
    },
    handleGoodsCurrentChange (val) {
      this.goodsPage = val
      this.getDeptCarGoodsStVo(this.goodsPage, 'query')
    },
    handleSelectionChange4Main (val) {
      this.multipleSelection4Main = val
    },
    createBill: function () {
      if (!this.buyBill.sourceWarehouseId) {
        this.$message.error('请选择目标科室')
        return
      }
      if (this.multipleSelection4Main.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      } // 10 实采  company-013 青山
      this.multipleSelection4Main.forEach((item) => delete item.needPacketQty)
      this.buyBill.subList = []
      this.buyBill.carList = []
      //  this.buyBill.buyKind=10;   华山北院，根据10，20 进行拆单
      this.buyBill.hosId = this.user.corpId; this.buyBill.buyDeptId = this.user.orgId, this.buyBill.state = 10

      this.multipleSelection4Main.forEach(v => {
        let s = {}
        // alert(v.isUnique);
        s.buyDeptId = v.deptId; s.goodsId = v.goodsId; s.hosId = v.hosId; s.qty = v.needQty; s.uniqueKind = v.isUnique
        this.buyBill.subList.push(s)
      })
      this.buyBill.carList = this.multipleSelection4Main

      this.axios.post('/spdHERPService/applyMgr/deptBuyMain/create/' + this.user.userId, this.buyBill).then(res => {
        this.loading = true
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success'})
          this.loading = false
          this.getDeptCarGoodsStVo(1, 'create')
        } else {
          this.$message.error(res.data.msg)
				 	}
      }, err => {
        this.loading = false
      })
    },
    delCar: function () {
      if (this.multipleSelection4Main.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      this.multipleSelection4Main.forEach((item) => delete item.needPacketQty)

      this.axios.post('/spdHERPService/applyMgr/deptBuyMain/delCar/', this.multipleSelection4Main).then(res => {
        this.loading = true
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success'})
          this.loading = false
          this.multipleSelection4Main = []
          this.getDeptCarGoodsStVo(1, 'del')
        } else {
          this.$message.error(res.data.msg)
				 	}
      }, err => {
        this.loading = false
      })
    },
    qtyChange (val, row) {
      this.loading = true
      this.axios.get('/spdHERPService/applyMgr/deptBuyCar/get/' + encodeURIComponent(row.carId)).then(res => {
        if (res.data.code == '0') {
          res.data.data.needQty = val
          this.axios.post('/spdHERPService/applyMgr/deptBuyCar/update', res.data.data).then(res => {
            this.loading = false
            if (res.data.code == '0') {
              this.$message.success('保存成功')
            } else {
              this.$message.error(res.data.msg)
            }
          }, err => {
            this.loading = false
          })
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    qtyPacketChange (val, row) {
      row.needPacketQty = val
      row.needQty = (row.needPacketQty * row.packetCode).toFixed(2)
    },
    dsFormatter (row, column, cellValue) {
      if (cellValue == 1) { return '是' } else return '否'
    },

    updateAddress () {
      this.updateAddressVisible = true
      this.modelTitle = '修改收货地址'
    },
    test (row) {
      //      alert(row.id);
    },
    deliveryAddrEdit (row) {
      this.address = row
      this.updateAddressVisible = false
    },
    setDfaulte (row) {

    },
    setAddress (d) {
      this.buyBill.recAddressId = d.id
      this.buyBill.recLinkman = d.lxr
      this.buyBill.recLinkmanPhone = d.lxrPhone
      this.buyBill.recAddress = ('' + d.province + d.city + d.area + d.address).replace(/null/g, '')
    },
    // 获取可请领科室集合
    getRelationDepts: function () {
      this.loading = true
      this.axios.post('/spdHERPService/baseData/stocInfoMgr/hosDeptPurStock/queryDeptRelation',
        {hosId: this.user.corpId, deptId: this.user.orgId}).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.relationDepts = res.data.data
          this.relationDepts.forEach(d => {
            if (d.isDefault === '1') { this.buyBill.sourceWarehouseId = d.stockId }
          })
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
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
          this.$message.error('删除商品图片信息失败，请联系管理员！' + err)
          this.dlgGoodsImgLoading = false
        })
    },
    imgMouseEnter (event, index) {
      let img = this.$refs['img' + index][0]
      img.setAttribute('src', img.getAttribute('orig-src'))
    }
  },

  mounted () {
    this.filters.hosId = this.user.corpId
    this.getKinds()
    this.getDeptCarGoodsStVo(1, 'query')
    this.getRelationDepts()
  }
}
</script>

<style scoped>
    .line {
        text-align: center;
    }
    .el-table {
    font-size: 12px;
    }
    .el-form {
    font-size: 12px;
    }
    .el-button {
        margin: 0;
    }

    .el-pagination {
        float: right;

    }

    .fullheight {
        height: calc(100% - 0px);

    }

    .splitborder {
        border-right: rgba(0, 70, 255, 0.6) 1px dashed;

    }

    .carbuy-tableheight {
        height: calc(100% - 110px);
    }

.pic-border {
  padding: 10px;
}

.tooltip-pic {
  max-width: 800px;
}

.pic {
  min-width: 50px;
  min-height: 50px;
  max-width: 178px;
  max-height: 178px;
  display: block;
  border: 1px dashed #9b9b9b;
  border-radius: 6px;
}
</style>
