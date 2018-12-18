<template>
    <div id="provGoodsHoslist" style="height: 95%;">
        <el-row  height="auto" style="height: 100%;">
            <el-col :span="4" style="height: 98%;" >
                 <div style="margin-bottom: 10px">
                    <el-input v-model="filters.kindName" placeholder="请输入类型名称" icon="search" :on-icon-click="searchs"  @keyup.enter.native="searchs"    >
                    </el-input>
                </div>

                <el-tree :data="kindsValue" node-key="id"
                         :default-expanded-keys="expadoIds"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            </el-col>
            <el-col :span="20" class="toolbar" style="padding-left: 5px; height: 100%; ">
                <div style="margin-bottom: 5px;">
                    <el-form :inline="true">
                        <el-form-item label="商品名称">
                            <el-input v-model="goodFilters.goodsName" placeholder="请输入商品名"   ></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input v-model="goodFilters.goodsGg" placeholder="请输入规格型号"   > </el-input>
                        </el-form-item>
                        <el-form-item label="供应商">
                            <el-input v-model="goodFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>
                        <el-button  type="primary"  @click="searchGoods(null)">查询</el-button>
                    </el-form>
                </div>

                <el-table highlight-current-row class="productInfo-tableheight"
                         :data="goodList" style="width: 100%"
                          ref="multipleTable4Main"
                          row-key='id'  @selection-change="handleSelectionChange4Main"
                          v-loading="goodsLoading" border height="outer"
                          :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
                          <!--
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                            -->
                            <!--供货目录只列出供应商的 商品信息， 对码不在此处实现 -->
                            <!--
                            <el-table-column prop="hisCode"   min-idth="85" label="医院商品编号" header-align="center" >
                            </el-table-column>
                            -->
                            <el-table-column prop="code" label="商品编号" header-align="center"  width="80" >
                            </el-table-column>

                            <el-table-column prop="goodsName"   min-idth="200" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-idth="200" label="规格型号" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="made"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="unit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="mfrsName"  header-align="center" width="150"  label="生产厂商" >
                            </el-table-column>

                            <el-table-column prop="provName"  header-align="center" width="150"  label="供应商" show-overflow-tooltip sortable>
                            </el-table-column>
                            <el-table-column prop="brand"   min-idth="200" label="品牌" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column label="操作" width="180"  header-align="center" fixed="right" align="center">
                                <template slot-scope="scope">
                                    <el-button-group>
                                        <el-button type="danger" size="mini" @click="cancelCode(scope.row)" icon="delete" v-if="scope.row.provId!=null">撤销对码</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            <!--<el-table-column type="selection" width="40"></el-table-column>-->
                </el-table>
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleGoodsSizeChange" @current-change="handleGoodsCurrentChange" :current-page="goodsPage" :page-sizes="[10, 20, 50, 100]" :page-size="goodsPageSize" layout="total, sizes, prev, pager, next" :total="goodsTotal" style="float:right;" small>
                   </el-pagination>
                </el-col>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
  data () {
    return {
      defaultProps: {
        children: 'children',
        label: 'label',
        value: 'id',
        pId: 'pid',
        code: 'code',
        hosId: 'hosId',
        state: 'state'
      },
      kv4cProps: {
        children: 'children',
        label: 'label',
        value: 'id'
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
        lbsx: null,
        flag: '1',
        ids:null
      },
      goodsLoading: false,
      goodList: [],
      goodsTotal: 0,
      goodsPageSize: 10,
      goodsPage: 1,
      multipleSelection4Main: [],
      // pop 参数
      popTitle: null,
      popVisible: false,
      popFilters: {
        goodsName: null,
        goodsGg: null,
        provName: null,
        isAll: '1'
      },
      popGoodList: [],
      popTotal: 0,
      popPageSize: 10,
      popPage: 1,
      multipleSelection4Pop: [],
      // 编辑 参数
      good: {},
      kv4c: [],
    }
  },
  computed: {
    userInfo () {
      return {
        'X-US': this.user.xus
      }
    },
    fl: {
      get: function () {
        let res = []
        if (this.good.hosKindLevelCode) {
          let lcode =
            this.good.hosKindLevelCode.indexOf(':') > 0
              ? this.good.hosKindLevelCode.split(':')[1]
              : this.good.hosKindLevelCode
          //  let lcode= this.good.hosKindLevelCode.split(":")[1];
          let path = lcode.split('.')
          if (path.length > 1) {
            var i = 0
            for (i; i < path.length; i++) {
              let tempArr = path.slice(0, i + 1)
              res.push(tempArr.join('.'))
            }
            //  res.push(this.good.lbsx);
          } else {
            res = path
          }
        }
        return res
      },
      set: function (pathArr) {
        let res = ''
        if (pathArr.length > 0) {
          res = pathArr[pathArr.length - 1]
        }
        // this.good.hosKindLevelCode =res.split("|")[0]+":"+ res;
        let path = res.split('|')[0] + ':'
        pathArr.forEach(v => (path = path + v + '.'))
        //     alert(path.substr(0,path.length-1));
        this.good.lbsx = res
        this.good.hosKindLevelCode = path
      }
    }
  },
  methods: {
    getKinds: function () {
      // this.loading=true;
      let param = {
        orderBy: '',
        queryObject: { hosId: this.filters.hosId },
        page: 0,
        rows: 0
      }
      this.axios
        .post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param)
        .then(res => {
          this.kindsValue = res.data.data
          this.kv4c = res.data.data[0].children
          // this.loading=false;
        })
    },
    searchs: function () {
      this.loading = true
      let param = {
        orderBy: '',
        queryObject: {
          hosId: this.filters.hosId,
          kindName: this.filters.kindName
        },
        page: 0,
        rows: 0
      }
      this.axios
        .post(`/spdHERPService/productsInfos/hosKindcode/query`, param)
        .then(res => {
          var ids = []
          for (var i = 0; i < res.data.data.length; i++) {
            ids[i] = res.data.data[i].id
          }
          this.loading = false
          this.expadoIds = ids
        })
    },
    handleNodeClick (data) {
      var _this = this;
      _this.selectedNode = data
	  var ids = [];
	  _this.getChildrenIds(data,ids);
      //_this.goodFilters.lbsx = data.id
	  _this.goodFilters.ids = ids;
      _this.getHosGoodsList(1)
    },
    getChildrenIds:function(node,ids){//获取子节点的id
      var _this = this;
      ids.push(node.id);
      var children = node.children;
      if(children&&children.length>0){
      	for(var i=0;i<children.length;i++){
      		_this.getChildrenIds(children[i],ids);
      	}
      }
    },
    getHosGoodsList: function (pIndex) {
      let hosId = this.user.corpId
      this.goodsPage = pIndex
      this.loading = true
      var param = {
        orderBy: '',
        queryObject: {
          hosId: hosId,
          goodsName: this.goodFilters.goodsName,
          goodsGg: this.goodFilters.goodsGg,
          lbsx: this.goodFilters.lbsx,
          provName: this.goodFilters.provName,
          flag:this.goodFilters.flag,
          lbsxs:this.goodFilters.ids
        },
        page: this.goodsPage,
        rows: this.goodsPageSize
      }
      this.axios.post('/spdHERPService/myGoods/hosGoods/getHosGoodsByHos', param).then(
        res => {
          this.loading = false
          if (res.data.code == '0') {
            this.goodList = res.data.data.data
            this.goodsTotal = res.data.data.total
          } else {
            this.$message.error(res.data.msg)
          }
        },
        err => {
          this.loading = false
        }
      )
    },
    searchGoods () {
      this.getHosGoodsList(1)
    },
    handleGoodsSizeChange (val) {
      this.goodsPageSize = val
      this.getHosGoodsList(this.goodsPage)
    },
    handleGoodsCurrentChange (val) {
      this.goodsPage = val
      this.getHosGoodsList(this.goodsPage)
    },
    searchPopGoods () {
      this.getGoodsListByPop(1)
    },
    handleSelectionChange4Main (val) {
      this.multipleSelection4Main = val
    },
    handlePopSelectionChange (val) {
      this.multipleSelection4Pop = val
    },
    getGoodsListByPop: function (pIndex) {
      let hosId = this.user.corpId
      this.goodsPage = pIndex
      this.loading = true
      var param = {
        orderBy: '',
        queryObject: {
          hosId: hosId,
          provGoodsName: this.popFilters.goodsName,
          provGoodsGg: this.popFilters.goodsGg,
          provName: this.popFilters.provName,
          isAll: this.popFilters.isAll
        },
        page: this.popPage,
        rows: this.popPageSize
      }
      this.axios
        .post('/spdHERPService/myGoods/provGoodsHos/getProGoods4HosList', param)
        .then(
          res => {
            this.loading = false
            if (res.data.code == '0') {
              this.popGoodList = res.data.data.data
              this.popTotal = res.data.data.total
              //  this.goodsPage = res.data.data.pageNum;
            } else {
              this.$message.error(res.data.msg)
            }
          },
          err => {
            this.loading = false
          }
        )
    },
    cancelCode (item) {
      this.$confirm('确认撤销该商品的对码关系吗?', '提示', {})
        .then(() => {
          this.axios.post('/spdHERPService/myGoods/hosGoods/cancelCode/', item).then(
            res => {
              this.loading = true
              if (res.data.code == '0') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.loading = false
                this.getHosGoodsList(1)
              } else {
                this.$message.error(res.data.msg)
              }
            },
            err => {
              this.loading = false
            }
          )
        })
        .catch(() => {})
    },
  },

  mounted () {
    this.filters.hosId = this.user.corpId
    this.getKinds()
    this.getHosGoodsList(1)
    this.selectedHosKind = null
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}

.el-button {
  margin: 0;
}

.el-pagination {
  float: right;
}

.splitborder {
  border-right: rgba(0, 70, 255, 0.6) 1px dashed;
}

.productInfo-tableheight {
  height: calc(100% - 60px);
}
.el-tree-node__label {
  font-size: 12px;
}
.el-table {
  font-size: 12px;
}
.el-tree {
  min-height: calc(100% - 75px);
}
.goods-img {
  height: 250px;
  text-align:center
}
</style>

<style>
#provGoodsHoslist .pic-uploader .el-upload {
  border: 1px dashed #9b9b9b;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

#provGoodsHoslist .upload-background .el-upload::after {
  content: '';
  display: block;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-size: 100%;
  background-image: url("../../assets/images/logo_200x200.png");
  background-repeat: no-repeat;
  opacity: 0.20;
}

#provGoodsHoslist .pic-uploader .el-upload:hover {
  border-color: #20a0ff;
}

#provGoodsHoslist .pic-uploader .item-status-label {
  position: absolute;
  right: -15px;
  top: -6px;
  width: 40px;
  height: 24px;
  background: #13ce66;
  text-align: center;
  transform: rotate(45deg);
  box-shadow: 0 0 1pc 1px rgba(0, 0, 0, .2);
}

#provGoodsHoslist .pic-uploader .item-status-label .upload-success {
  font-size: 12px;
  margin-top: 11px;
  transform: rotate(-45deg) scale(.8);
  color: #fff;
}

#provGoodsHoslist .pic-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

#provGoodsHoslist .pic {
  min-width: 50px;
  min-height: 50px;
  max-width: 178px;
  max-height: 178px;
  display: block;
}

#provGoodsHoslist .el-upload-list__item,
.el-upload-list__item:first-child {
  margin-top: 0;
  margin-bottom: 5px;
}
</style>
