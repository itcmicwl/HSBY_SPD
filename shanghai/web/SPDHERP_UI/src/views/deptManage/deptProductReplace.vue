<template>
  <el-row id="replaceGoodsInfo" style="height: 100%">
    <el-row :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px;">
      <el-col :span="13">
      <!--查询条件工具栏old-->
      <el-form :inline="true" :models="filters" labelWidth="80px" ref="goodsErpCode">
        <el-form-item label="产品编码" prop="erpCode">
          <el-input v-model="filters.erpCode" :clearable="true" placeholder="请输入产品erpCode"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getGoods" >查询</el-button>
          <el-button type="primary" v-on:click="resetFilter" >重置</el-button>
        </el-form-item>
      </el-form>
      </el-col>
      <el-col :span="11">
        <!--查询条件工具栏new-->
        <el-form :inline="true" :models="filtersNew" labelWidth="80px" ref="goodsErpCode">
          <el-form-item label="产品编码" prop="erpCode">
            <el-input v-model="filtersNew.erpCodeNew" :clearable="true" placeholder="请输入产品erpCode"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="getNewGoods" >查询</el-button>
            <el-button type="primary" v-on:click="resetFilterNew" >重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <el-row style="height:calc(100% - 80px);" :gutter="10">
      <el-col :span="11" style="height:100%">
        <el-table
          :data="goodsModify"
          :row-key="getRowKeys"
          :expand-row-keys="expands"
          style="width: 100%"
          border highlight-current-row>
          <el-table-column type="expand" style="width: 10px">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="产品ID">
                  <span>{{ props.row.id }}</span>
                </el-form-item>

                <el-form-item label="产品规格">
                  <span>{{ props.row.goodsGg }}</span>
                </el-form-item>

                <el-form-item label="供应商ID">
                  <span>{{ props.row.provId }}</span>
                </el-form-item>

                <el-form-item label="生产商ID">
                  <span>{{ props.row.mfrsId }}</span>
                </el-form-item>

                <el-form-item label="供应商名称">
                  <span>{{ props.row.provName }}</span>
                </el-form-item>

                <el-form-item label="生产商名称">
                  <span>{{ props.row.mfrsName }}</span>
                </el-form-item>

                <el-form-item label="供应商产品ID">
                  <span>{{ props.row.provGoodsId }}</span>
                </el-form-item>

                <el-form-item label="生产商产品ID">
                  <span>{{ props.row.mfrsGoodsCode }}</span>
                </el-form-item>

                <el-form-item label="产品his编码">
                  <span>{{ props.row.hisCode }}</span>
                </el-form-item>

                <el-form-item label="产品his单位">
                  <span>{{ props.row.hisUnit }}</span>
                </el-form-item>

                <el-form-item label="产品单价">
                  <span>{{ props.row.price }}</span>
                </el-form-item>

                <el-form-item label="产品售价">
                  <span>{{ props.row.salePrice }}</span>
                </el-form-item>

                <el-form-item label="产地">
                  <span>{{ props.row.made }}</span>
                </el-form-item>

                <el-form-item label="税率">
                  <span>{{ props.row.taxRate }}</span>
                </el-form-item>

                <el-form-item label="阳光采购编码">
                  <span>{{ props.row.hitCode }}</span>
                </el-form-item>

                <el-form-item label="阳光采购价">
                  <span>{{ props.row.hitPrice }}</span>
                </el-form-item>

                <el-form-item label="医保编码">
                  <span>{{ props.row.miCode }}</span>
                </el-form-item>

                <el-form-item label="包装规格">
                  <span>{{ props.row.packeage }}</span>
                </el-form-item>

                <el-form-item label="品牌">
                  <span>{{ props.row.brand }}</span>
                </el-form-item>

                <el-form-item label="证照编码">
                  <span>{{ props.row.certificateCode }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="产品名称"
            prop="goodsName">
          </el-table-column>
<!--          <el-table-column
            label="产品规格"
            prop="goodsGg">
          </el-table-column>-->
          <el-table-column
            label="erp编码"
            prop="erpCode">
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="2" style="height: 100%">
        <el-button type="primary" v-on:click="replaceInfo" style="margin:calc(100% + 180px) 20px">替换</el-button>
      </el-col>
      <el-col :span="11" style="height:100%">
        <el-table
          :data="goodsModifyNew"
          :row-key="getRowKeysNew"
          :expand-row-keys="expandsNew"
          style="width: 100%"
          border
          highlight-current-row>
          <el-table-column type="expand" style="width: 10px">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="产品ID">
                  <span>{{ props.row.id }}</span>
                </el-form-item>

                <el-form-item label="产品规格">
                  <span>{{ props.row.goodsGg }}</span>
                </el-form-item>

                <el-form-item label="供应商ID">
                  <span>{{ props.row.provId }}</span>
                </el-form-item>

                <el-form-item label="生产商ID">
                  <span>{{ props.row.mfrsId }}</span>
                </el-form-item>

                <el-form-item label="供应商名称">
                  <span>{{ props.row.provName }}</span>
                </el-form-item>

                <el-form-item label="生产商名称">
                  <span>{{ props.row.mfrsName }}</span>
                </el-form-item>

                <el-form-item label="供应商产品ID">
                  <span>{{ props.row.provGoodsId }}</span>
                </el-form-item>

                <el-form-item label="生产商产品ID">
                  <span>{{ props.row.mfrsGoodsCode }}</span>
                </el-form-item>

                <el-form-item label="产品his编码">
                  <span>{{ props.row.hisCode }}</span>
                </el-form-item>

                <el-form-item label="产品his单位">
                  <span>{{ props.row.hisUnit }}</span>
                </el-form-item>

                <el-form-item label="产品单价">
                  <span>{{ props.row.price }}</span>
                </el-form-item>

                <el-form-item label="产品售价">
                  <span>{{ props.row.salePrice }}</span>
                </el-form-item>

                <el-form-item label="产地">
                  <span>{{ props.row.made }}</span>
                </el-form-item>

                <el-form-item label="税率">
                  <span>{{ props.row.taxRate }}</span>
                </el-form-item>

                <el-form-item label="阳光采购编码">
                  <span>{{ props.row.hitCode }}</span>
                </el-form-item>

                <el-form-item label="阳光采购价">
                  <span>{{ props.row.hitPrice }}</span>
                </el-form-item>

                <el-form-item label="医保编码">
                  <span>{{ props.row.miCode }}</span>
                </el-form-item>

                <el-form-item label="包装规格">
                  <span>{{ props.row.packeage }}</span>
                </el-form-item>

                <el-form-item label="品牌">
                  <span>{{ props.row.brand }}</span>
                </el-form-item>

                <el-form-item label="证照编码">
                  <span>{{ props.row.certificateCode }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="产品名称"
            prop="goodsName">
          </el-table-column>

          <el-table-column
            label="erp编码"
            prop="erpCode">
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </el-row>
</template>
<script>
export default {
  data () {
    return {
      filters:{
        erpCode:'',
      },
      filtersNew:{
        erpCodeNew:''
      },
      hosId:'',
      goodsModify:[],
      goodsModifyNew:[],
      goods:{},
      goodsNew:{},
      // 要展开的行，数值的元素是row的key值
      expands: [],
      expandsNew: [],
      // 获取row的key值
      getRowKeys(row) {
        return row.id;
      },
      // 获取row的key值
      getRowKeysNew(row) {
        return row.id;
      }
    }
  },
    methods:
    {
      getGoods(){
        this.goodsModify = []
        this.axios.get('/spdHERPService/deptMgr/deptGoodsInfo/getGoodsByErpCode',
          {params:{
              erpCode:this.filters.erpCode,
              hosId:this.hosId,
            }
          }).then(res=>{
          if(res.data.code=='0'){
            this.goods = res.data.data
            this.goodsModify.push(this.goods)
            this.expands.push(this.goodsModify[0].id);
          }
        })
      },
      getNewGoods(){
        this.goodsModifyNew = []
        this.axios.get('/spdHERPService/deptMgr/deptGoodsInfo/getGoodsByErpCode',
          {params:{
              erpCode:this.filtersNew.erpCodeNew,
              hosId:this.hosId,
            }
          }).then(res=>{
          if(res.data.code=='0'){
            this.goodsNew = res.data.data
            this.goodsModifyNew.push(this.goodsNew)
            this.expandsNew.push(this.goodsModifyNew[0].id);
          }
        })
      },
      resetFilter(){
        this.filters.erpCode = ''
      },
      resetFilterNew(){
        this.filtersNew.erpCodeNew= ''
      },
      replaceInfo(){

      }
    },
    mounted(){
      this.hosId = this.user.corpId;
    }
}

</script>
<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
