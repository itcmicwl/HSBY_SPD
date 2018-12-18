<template>
  <div id="want" style="height:95%">
        <el-row   height="auto"  style="height:50%">
            <el-col :span="4" style="height:100%" >
                 <div style="margin-bottom: 5px">
                    <el-input
                            v-model="filters.kindName" placeholder="请输入类型名称"
                            icon="search" :on-icon-click="searchs"   >
                    </el-input>
                </div>

                <el-tree :data="goodsTemplateNode" node-key="id"
                          :expand-on-click-node="false"
                         :default-expand-all="true"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            
            </el-col>
            <el-col :span="20" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 98%;">
                <div style="margin-bottom: 0px;">
                    <el-form :inline="true">
                        <el-form-item label="商品名">
                            <el-input v-model="goodFilters.goodsName" placeholder="请输入商品名"   @keyup.enter.native="searchGoods(null)"></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input v-model="goodFilters.goodsGg" placeholder="请输入规格型号"   @keyup.enter.native="searchGoods(null)"> </el-input>
                        </el-form-item>
                        <!--
                        <el-form-item label="供应商">
                            <el-input v-model="goodFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>
                         -->
                        <el-button  type="primary"  @click="searchGoods(null)">查询</el-button>
                        <el-button  type="primary"  @click="add2car(null)">添加到请购车</el-button>
                      <!--  <el-button  type="primary"  @click="disConnect(null)">解除关联</el-button> -->
                    </el-form>
                </div>

                <el-table highlight-current-row class="addBuy-tableheight"
                         :data="goodList" style="width: 100%"
                          ref="multipleTable4Main"
                          row-key='tempId'  @selection-change="handleSelectionChange4Main"
                          v-loading="goodsLoading" border height="outer"
                          :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                            <!--供货目录只列出供应商的 商品信息， 对码不在此处实现 -->
                            <!--
                            <el-table-column prop="hisCode"   min-idth="85" label="医院商品编号" header-align="center" >
                            </el-table-column>
                             -->
                             <el-table-column label="请购数量"  width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量"   v-model="scope.row.needQty" size="mini" :min="scope.row.isPacket==0?scope.row.reqNum:scope.row.needPacketQty*scope.row.packetCode" class="col-input-num" :step="scope.row.reqNum?scope.row.reqNum:1" :disabled="scope.row.isPacket==1"  @change="qtyChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                           </el-table-column>
                            <el-table-column prop="unit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                            <el-table-column label="请购包数"  width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量"   v-model="scope.row.needPacketQty" size="mini" :min="scope.row.isPacket==0?0:1" class="col-input-num"  :step="scope.row.reqNum?scope.row.reqNum:1"  :disabled="scope.row.isPacket==0||scope.row.isPacket==null" @change="qtyPacketChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                           </el-table-column>
                            <!-- <el-table-column prop="code" label="商品编号" header-align="center"  width="80" >
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
                             <el-table-column prop="packetCode"   min-idth="10" label="包装数量" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="generalName" min-idth="30" label="商品俗称" header-align="center" show-overflow-tooltip>
                            </el-table-column>

                             <el-table-column prop="goodsDesc" min-idth="30" label="商品描述" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="isPacket"   min-idth="10" label="是否定数包" header-align="center"  :formatter="dsFormatter">
                            </el-table-column>
                            <!-- <el-table-column prop="price"   min-idth="30" label="单价" header-align="center" >
                            </el-table-column> -->
                             <!-- <el-table-column prop="qty"   min-idth="30" label="库存数量" header-align="center" >
                            </el-table-column> -->

                            <el-table-column prop="made"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>

                            <!--<el-table-column prop="mfrsName" width="150" header-align="center" show-overflow-tooltip  label="生产厂商" >
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
                        </el-table>
                <el-pagination @size-change="handleGoodsSizeChange" @current-change="handleGoodsCurrentChange" :current-page="goodsPage" :page-sizes="[10, 20, 50, 100]" :page-size="goodsPageSize" layout="total, sizes, prev, pager, next" :total="goodsTotal" style="float:right;" small>
			       </el-pagination>
            </el-col>
        </el-row>
   <el-row   height="auto"  style="height:50%;padding-top:50px">
            <el-col :span="4" style="height:100%" >            
              <el-tree :data="surDefNode" node-key="id"
                          :expand-on-click-node="false"
                         :default-expand-all="true"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleSurNodeClick"  >
              </el-tree>           
            </el-col>
            <el-col :span="6" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 98%;">
                <el-table highlight-current-row class="addBuy-tableheight" :data="ssList" style="width: 100%"  ref="multipleTable4Sur"
                          row-key='surId'  @selection-change="handleSelectionChange4Sur"  @row-click="fetchSurGoods"
                          v-loading="goodsLoading" border height="outer"  :default-sort="{prop: 'surId', order: 'descending'}">
                            <el-table-column  type="selection" :reserve-selection="true" width="55" :selectable="function(row){return row.canQG;}"> </el-table-column>                                       
                            <el-table-column label="请购包数"  width="100">
                                <template slot-scope="scope">
                                    <el-input-number placeholder="请输入数量" :disabled="!scope.row.canQG"  v-model="scope.row.needSurPacketQty" size="mini" :min="scope.row.isPacket==0?0:1" class="col-input-num"  :step="scope.row.reqNum?scope.row.reqNum:1"   @change="qtyPacketChange($event,scope.row)">
                                    </el-input-number>
                                </template>
                           </el-table-column>
                            <el-table-column prop="surName"   min-idth="85" label="手术包名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column label="说明">
                                 <template slot-scope="scope">
                                    <span title="部门产品中不包含此手术包中所有产品，无法请购">{{scope.row.canQG == false?"无法请购":"可请购"}}</span>
                                </template>
                            </el-table-column>
                </el-table>
       
             
            </el-col>
            <el-col :span="14" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 98%;">  
                <el-table highlight-current-row class="addBuy-tableheight"   :data="ssGoodsList" style="width: 100%"   row-key='goodsId'  :row-class-name='surGoodsCls' 
                  v-loading="goodsLoading" border height="outer"  :default-sort="{prop: 'goodsId', order: 'descending'}">                           
                  <el-table-column prop="goodsId"   min-width="85" label="商品ID" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>
                    <el-table-column prop="hosGoods.goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>
                  <el-table-column prop="hosGoods.goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>
                    <el-table-column prop="qty"   min-idth="85" label="数量" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>
                    <el-table-column prop="hosGoods.unit"   min-idth="85" label="单位" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>
                  <el-table-column prop="hosGoods.mfrsName"   min-idth="85" label="厂家" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>
                  <el-table-column prop="hosGoods.made"   min-idth="85" label="生产国" width="150" header-align="center" show-overflow-tooltip >
                  </el-table-column>    
                  <el-table-column label="可否请购" >
                    <template slot-scope="scope">
                        <span>{{scope.row.canQGByDept == false?"无法请购":"可请购"}}</span>
                    </template>
                  </el-table-column>                         
                </el-table>
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

      hosId: null,
      deptId: null,
      buyKind: 10, // 10实物采购，20虚拟采购
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
      goodsTemplateNode: [{
        children: [],
        label: '',
        id: '',
        pid: ''
      }],
      surTemplateNode:[{
        label:'分类1',id:'kind1',
        children:[{id:'kind2',pid:'kind1',label:'分类1-1',
                   children:[{id:'pkg1',pid:'kind2',label:'包皮环切包',children:[] },
                             {id:'pkg2',pid:'kind2',label:'包皮续接包',children:[] }
                              ]
                  }],
       
      }],
      surDefNode: [{
        children: [],
        label: '',
        id: '',
        pid: ''
      }],
      expadoIds: [null],
      loading: 'false',
      pname: '商品分类',
      selectedNode: null,
      // 以下为右侧参数
      goodFilters: {
        goodsName: null,
        goodsGg: null,
        provName: null,
        tempId: null
      },
      goodsLoading: false,
      goodList: [],
      goodsTotal: 0,
      goodsPageSize: 20,
      goodsPage: 1,
      multipleSelection4Main: [],
      multipleSelection4Sur: [],
      ssList:[],
      ssGoodsList:[]

    }
  },
  methods: {
    getKinds: function () {
      let o = this
      function cb (goodsTemplate) {
        o.goodsTemplate = buildTreenode(goodsTemplate, 'id', 'pid', 'cname')
        o.goodsTemplateNode[0].children = o.goodsTemplate
        o.goodsTemplateNode[0].label = '所有可选商品'
        //   o.queryDeptTemplateGoods(1);
      }
      let param = { hosId: this.filters.hosId, deptId: this.user.orgId}
      this.axios.post(`/spdHERPService/deptMgr/deptGoodsTemplate/deptGoodsTemplateQuery`, param).then(res => {
        var goodsTemplate = res.data.data
        cb(goodsTemplate)
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
      this.goodFilters.tempId = data.id

      this.getHosGoodsListByDept(1)
    },
    // 重写row-key属性数据，防止重复
    formatRowKey: function (row) {
      return row.id + '|' + row.tempId
    },
    getHosGoodsListByDept: function (pIndex,pageChange) {
      // purMode:20 虚拟采购 对应部门商品表中 的 20寄售，30跟台
      // purMode：10 实物采购  对应部门商品表中 的 10采购
      // 华山北院需求，请购不分实物与虚拟，从请购车生成请购单时依据商品属性10和20进行拆单 purMode：0 传0，后台依据0来区分
      let hosId = this.user.corpId
      let deptId = this.user.orgId
      this.goodsPage = pIndex
      this.loading = true
      var param = { orderBy: '',
        queryObject: {hosId: hosId,
          deptId: deptId,
          goodsName: this.goodFilters.goodsName,
          goodsGg: this.goodFilters.goodsGg,
          tempId: this.goodFilters.tempId,
          purMode: 0},
        page: this.goodsPage,
        rows: this.goodsPageSize
      }
      this.axios.post('/spdHERPService/applyMgr/deptBuyCar/getDeptGoodsStVo', param).then(res => {
        this.loading = false
        if (res.data.code == '0') {
          this.goodList = res.data.data.data
          this.goodList.forEach(g => {
            if (g.isPacket == '1') {
              g.needPacketQty = g.needQty
            }
          })

          this.goodsTotal = res.data.data.total
          if(pageChange!=1){
            this.multipleSelection4Main = []
            this.$refs.multipleTable4Main.clearSelection()
          }
           
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    searchGoods () {
      this.getHosGoodsListByDept(1)
    },
    handleGoodsSizeChange (val) {
      this.goodsPageSize = val
      this.getHosGoodsListByDept(this.goodsPage,1)
    },
    handleGoodsCurrentChange (val) {
      this.goodsPage = val
      this.getHosGoodsListByDept(this.goodsPage,1)
    },
    handleSelectionChange4Main (val) {
      this.multipleSelection4Main = val
    },
    handleSelectionChange4Sur (val) {
      this.multipleSelection4Sur = val
    },
    add2car: function () {
 
     
      if (this.multipleSelection4Main.length+this.multipleSelection4Sur.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      } // 10 实采  company-013 青山
      this.multipleSelection4Main.forEach((item) => delete item.needPacketQty)
      this.multipleSelection4Sur.forEach(o=>{
       delete o.needPacketQty;
       delete o.needQty;
       delete o.canQG;
        this.multipleSelection4Main.push(o)})
      
       
      // 将add2car/10改为0，0表明不分实采与虚采，生成请购单时再按实与虚进行拆单
      this.axios.post('/spdHERPService/applyMgr/deptBuyCar/add2car4mixSur/0/' + this.hosId + '/' + this.deptId, this.multipleSelection4Main).then(res => {
        this.loading = true
        if (res.data.code == '0') {
          this.$message({ message: '操作成功！', type: 'success'})
          this.loading = false
            this.multipleSelection4Sur = []
            this.$refs.multipleTable4Sur.clearSelection()
          this.getHosGoodsListByDept(1)
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {
        this.loading = false
      })
    },
    qtyChange (val, row) {
      // row.purQty = val;
      // row.purAmount = (row.hosPrice * row.purQty).toFixed(2);
      // row.purTaxAmount = (row.purTaxPrice * row.purQty).toFixed(2);

    //   if (row.isPacket === 1 || !row.reqNum || row.reqNum === 1) {
    //     return
    //   }
    //   row.needQty = parseInt(val / row.reqNum) * row.reqNum
    },
    qtyPacketChange (val, row) {
      row.needPacketQty = val
      row.needQty = (row.needPacketQty * row.packetCode).toFixed(2)
    },
    dsFormatter (row, column, cellValue) {
      if (cellValue == 1) { return '是' } else return '否'
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
    },
    handleSurNodeClick(data){ 
      if(data.nodeType=="1")  //点击的是分类 ，不是手术包
        return
      if(typeof( this.ssList.find(o=> o.surId==data.id))=='undefined'){
        let ssObj={surId:'',surName:'',needSurPacketQty:''}
            ssObj.surId= data.id
            ssObj.surName =  data.label;
            ssObj.needSurPacketQty +=1;
            ssObj.canQG = false;
            this.ssList.push(ssObj);
            this.fetchSurGoods(ssObj);
      }
    },
    fetchSurGoods(row){
      this.ssGoodsList=[];
      this.loading = true
       var param = {
					surId: row.surId,
          deptId:this.user.orgId,
				//	selSurIds: this.surIds,
					hosId: this.hosId,
      }
       this.axios.post('/spdHERPService/surgery/surgeryPkgDefList/list4QG', param).then(res => {
          row.canQG = res.data.data.filter(o=>o.canQGByDept==false).length == 0;
          this.ssGoodsList = res.data.data;          
          this.loading = false
      }, err => {
        this.loading = false
      })
    },
     getSurKindDefTree: function () {
				var param = {};
				this.loading = true;
				this.axios.post(`/spdHERPService/surgery/surgeryPkgDef/getSurgeryKindDefTreeByHos`, { }).then(res => {
					this.surDefNode = res.data.data.children;
					//this.expadoIds=[this.surKindInfo[0].id];
					this.loading = false;
				});
    },
     surGoodsCls(rowInfo){
            if(rowInfo.row.canQGByDept===false){
              return 'warning-row'
            }
        },
  },

  mounted () {
    this.hosId = this.user.corpId
    this.deptId = this.user.orgId
    this.filters.hosId = this.user.corpId
    this.getKinds()
    this.getSurKindDefTree()
    this.getHosGoodsListByDept(1)
  }
}
</script>

<style>
    .line {
        text-align: center;
    }

    .el-button {
        margin: 0;
    }

    .el-pagination {
        float: right;

    }

    .fullheight {
        height: calc(100% - 10px);

    }
      .el-tree {
		min-height: calc(100% - 55px);

	}

    .splitborder {
        border-right: rgba(0, 70, 255, 0.6) 1px dashed;

    }

    .addBuy-tableheight {
        height: calc(100% - 0px);
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
.el-table th.warning-row, .el-table tr.warning-row{
    background-color: #e6a23c;
    color: #fff;
}
.el-table th.warning-row:hover, .el-table tr.warning-row:hover{
    background-color: #e6a23c;
    color: #000;
}
</style>
