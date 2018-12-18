<template>
  <div id="in-by-dept-apply-edit">
    <el-row v-loading="diaLoging">
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form label-width="120px">
          <el-col :span="8">
            <el-form-item label="出库单号:">
              <span>{{ inStockBill.outBillId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院：">
              <span>{{ inStockBill.outOrgName }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="入库库房:">
              <sltStockByUser ref="childMethod" @sltChange="getSelected_Stock" :hos-id="user.corpId" :user-id="user.userId" :in-stoc-code="inStockBill.inStocCode" :is-default="true"></sltStockByUser>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="入库部门:">
              <span>{{ inStockBill.inDeptName }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="制单时间:">
              <span>{{ inStockBill.fillDate | dateFormat('YYYY-MM-DD') }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="制单人:">
              <span>{{ inStockBill.fillterName }}</span>
            </el-form-item>
          </el-col>

        </el-form>
      </el-col>

      <el-table ref="receListTable" :data="inStockBill.lstDetail" style="width: 100%;" fit border height="300" stripe>
        <el-table-column label="序号" prop="inBillRow" width="60">
        </el-table-column>
        <el-table-column label="拒收" width="50">
          <template slot-scope="scope">
            <el-button type="danger" icon="el-icon-delete" circle @click.native="reject(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="图片" width="65" header-align="center" align="center">
          <template slot-scope="scope">
            <el-button type="info" :disabled="!scope.row.imgAvailable" size="mini" icon="el-icon-picture" @click.native="showGoodsImg(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="产品名称" prop="goodsName" width="180" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="规格" prop="goodsGg" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="出库数量" prop="outQty" width="90">
        </el-table-column>
        <el-table-column label="出库包数" prop="outPacketQty" width="90">
        </el-table-column>
        <el-table-column label="是否定数包" prop="isPacket" :formatter="isNotFormatter" width="90">
        </el-table-column>
        <el-table-column label="入库数量" width="90">
          <template slot-scope="scope">
            <el-input-number placeholder="请输入数量" v-model="scope.row.inQty" size="mini" :min="0" :max="scope.row.unrecieveQty" class="col-input-num" :disabled = "true" @change="inQtyChange(scope.row)">
            </el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="入库包数" width="90">
          <template slot-scope="scope">
            <el-input-number placeholder="请输入数量" v-model="scope.row.intPacketQty" size="mini" :min="0" :disabled = "true" class="col-input-num" @change="inQtyChange(scope.row)">
            </el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="单位" prop="unit">
        </el-table-column>
        <el-table-column label="批号" prop="batchCode" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="灭菌批号" prop="sterilizationCode" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="灭菌有效日期" width="130" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.sterilizationEndDate | dateFormat('YYYY-MM-DD') }}
          </template>
        </el-table-column>
        <el-table-column label="灭菌日期" width="130" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.sterilizationDate | dateFormat('YYYY-MM-DD') }}
          </template>
        </el-table-column>
        <el-table-column label="有效期" width="130" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.expdtEndDate | dateFormat('YYYY-MM-DD') }}
          </template>
        </el-table-column>
        <el-table-column label="产地" prop="made" width="90" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="generalName" min-idth="30" label="商品俗称" header-align="center" show-overflow-tooltip >
        </el-table-column>

        <el-table-column prop="goodsDesc" min-idth="30" label="商品描述" header-align="center" show-overflow-tooltip >
        </el-table-column>
      </el-table>
      <el-col :span="20" class="toolbar" style="padding-bottom: 0px;text-align: center;">
        <el-button @click.native="cancel">取消</el-button>
        <!-- <el-button type="primary" @click.native="submitPurconfirm(10)" >保存</el-button> -->
        <el-button type="primary" @click.native="submitPurconfirm(20)" >提交</el-button>
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
// import moment from 'moment'
// import { DISTRBILL_STATUS } from './common/constance'
import sltStock from '../../components/sltStock'
import sltStockByUser from '../../components/sltStockByUser'
// import barcodeUtil from '../../common/js/barcodeUtil'

export default {
  components: {sltStock, sltStockByUser},
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

      opModel: 'ADD',
      diaLoging: false,
      barcodeV: false,
      loading: false,
      distrBillList: [
      ],
      distrBill: {
        id: '',
        billId: '',
        spdCode: '',
        purchaseCompanyId: '',
        purchaseCompanyCode: '',
        purchaseCompanyName: '',
        purchaseCompanyErpCode: '',
        applyOrgId: '',
        applyOrgCode: '',
        applyOrgName: '',
        applyOrgScmCode: '',
        applyOrgErpCode: '',
        recieveOrgId: '',
        recieveOrgCode: '',
        recieveOrgName: '',
        recieveOrgScmCode: '',
        recieveOrgErpCode: '',
        provId: '',
        provCode: '',
        provName: '',
        provErpCode: '',
        filler: '',
        remark: '',
        sumRow: '',
        status: '',
        fillDate: '',
        version: '',
        distrBillList: []
      },
      inStockBill: {
        id: '',
        inStocId: null,
        inStocCode: null,
        billId: '',
        inStockKind: 2, // 请领入库
        outBillId: '',
        outOrgId: '',
        outOrgName: '',
        inOrgId: '',
        inOrgName: '',
        inDeptId: '',
        inDeptName: '',
        sendBillid: '',
        filler: '',
        fillterName: '',
        fillDate: '',
        // out_dept_id: '',
        // outDeptName: '',
        // auditor: '',
        // auditDate: '',
        // accounter: '',
        // accountDate: '',
        status: 0,
        lstDetail: [],
        removeRowList: []
      },
      inStockDetail: {},
      constInStockDetail: {
        id: '',
        pid: '',
        billId: '',
        outBillRow: 0,
        inBillRow: 0,
        provId: '',
        provCode: '',
        provName: '',
        goodsId: '',
        goodsName: '',
        goodsGg: '',
        mfrsId: '',
        mfrsName: '',
        made: '',
        packetCode: '',
        isPacket: '',
        uniqueKind: '',
        batchCode: '',
        sterilizationDate: '',
        sterilizationCode: '',
        sterilizationEndDate: '',
        expdtEndDate: '',
        unit: '',
        outQty: 0,
        outPacketQty: '', // 出库包数
        inQty: '', // 入库数量
        intPacketQty: '',
        inPrice: 0, // 入库单价
        masterCode: '', // 主码
        secCode: '', // 副码
        hibcCode: '', // hibc
        version: 0,
        lstBatch: [],
        lstUniqueCode: []
      },
      sltRows: [],
      Batch: {
        id: '',
        pid: '',
        billId: '',
        pRowId: '',
        provId: '',
        goodsId: '',
        goodsBatchId: '',
        inPrice: '',
        inTime: '',
        qty: '',
        version: 0
      },
      UniqueCode: {
        id: '',
        pid: '',
        billId: '',
        goodsName: '',
        pRowId: 0,
        provId: '',
        goodsId: '',
        goodsBatchId: '',
        inPrice: '',
        inTime: '',
        uniqueCode: '',
        version: 0
      },
      lstUniqueCode: [],
      lstBatch: [],
      barcode: '',
      mainBarcode: '',
      fBarcode: '',
      barcodeType: true,
      barcodeMatchV: false,
      barcodeMatch: {
        provId: '',
        hosId: '',
        mfrsCode: ''
      }
    }
  },
  mounted () {
    var _this = this
    var recId = this.$route.query.recId
    if (recId) {
      this.opModel = 'EDIT'
      this.getRecBill(recId)
    } else {
      this.opModel = 'ADD'
      var outBillId = this.$route.query.outBillId
      this.getOutBillDetail(outBillId)
    }
  },

  methods: {

    getRecBill (recId) {
      var _this = this
      this.axios.get('/spdHERPService/stockMgr/inStock/getById?id=' + recId).then(res => {
        if (res.data.code == 0) {
          _this.inStockBill = res.data.data
          for (var i = 0; i < _this.inStockBill.lstDetail.length; i++) {
            _this.inQtyChange(_this.inStockBill.lstDetail[i])
          }
        }
      })
    },

    convertDistrToRecBill (distBill) { // outStockBill
      if (distBill) {
        this.inStockBill.inStockType = distBill.outStockType
        this.inStockBill.id = ''
        this.inStockBill.billId = ''
        this.inStockBill.outBillId = distBill.billId
        this.inStockBill.inStockKind = 2
        this.inStockBill.outOrgId = distBill.outOrgId // 医院
        this.inStockBill.outOrgName = distBill.outOrgName // 医院
        this.inStockBill.inOrgId = distBill.inOrgId

        this.inStockBill.inOrgName = distBill.outOrgName

        this.inStockBill.inDeptId = distBill.inDeptId
        this.inStockBill.inDeptName = distBill.inDeptName
        this.inStockBill.outDeptId = distBill.outDeptId
        this.inStockBill.outDeptName = distBill.outDeptName
        this.inStockBill.filler = this.user.userId
        this.inStockBill.fillterName = this.user.ename
        this.inStockBill.fillDate = new Date()// moment(new Date()).format("YYYY-MM-DD");
        // this.inStockBill.sendBillid = distBill.billId;
        this.inStockBill.status = 0
        this.inStockBill.lstDetail = []
        if (this.distrBill.lstOutsubVo && this.distrBill.lstOutsubVo.length > 0) {
          this.convertDistrLstToRecBillLst(this.distrBill)
        }
      }
    },
    convertDistrLstToRecBillLst (distrBill) {
      var res = []
      distrBill.lstOutsubVo.forEach(function (item, index) {
        var row = Object.assign({}, this.constRecBillDetail)
        row.id = ''
        row.pid = ''
        row.billId = ''
        row.inBillRow = index + 1
        row.outBillId = distrBill.billId;
        row.outBillRow = item.outBillRow
        // row.purBillId = item.purBillId;
        // row.purRowNumber = 0;
        // row.purBillDate = item.purBillDate;
        row.provId = item.provId
        row.provCode = item.provCode
        row.provName = item.provName
        row.goodsId = item.goodsId
        row.goodsName = item.goodsName
        row.goodsGg = item.goodsGg
        row.mfrsId = item.mfrsId
        row.mfrsName = item.mfrsName
        row.made = item.made
        row.packetCode = item.packetCode // item.hosGoodsId;
        row.isPacket = item.isPacket
        row.uniqueKind = item.isUnique
        row.batchCode = item.batchCode
        // row.batchCode = item.isUniqueCode;
        // row.batchNum = item.batchNum;
        row.sterilizationCode = item.sterilizationCode
        row.sterilizationEndDate = item.sterilizationEndDate
        row.sterilizationDate = item.sterilizationDate
        row.expdtEndDate = item.expdtEndDate
        row.unit = item.unit
        row.outQty = item.outQty
        // row.outQty = item.hosQty - item.receiveQty / item.sendScale;
        row.outPacketQty = item.outPacketQty// item.hosQty;
        row.inQty = item.outQty
        //  row.inQty = 0;//row.outQty;
        row.intPacketQty = item.outPacketQty// 验收结论
        row.inPrice = item.price || 0
        row.masterCode = ''
        row.secCode = ''
        row.hibcCode = ''
        row.version = 0
        row.lstBatch = item.lstOutBatch
        row.lstUniqueCode = item.lstOutUniqueCode
        row.surCode = item.surCode;
        row.generalName = item.generalName
        row.goodsDesc = item.goodsDesc
        row.imgAvailable = item.imgAvailable
        res.push(row)
      }, this)
      this.inStockBill.lstDetail = res
    },
    sltRowsChange (sltRows) {
      this.sltRows = sltRows
    },
    submitPurconfirm (status) {
      if (!this.inStockBill.inStocId) {
        this.$message.error('先选择入库库房')
        return
      }
      this.diaLoging = true
      this.inStockBill.status = status
      var params = Object.assign({}, this.inStockBill)
      //  params.lstDetail = this.sltRows;
      var serviceUrl = this.opModel == 'ADD' ? '/spdHERPService/stockMgr/inStock/add' : '/spdHERPService/stockMgr/inStock/update'
      var _this = this
      this.axios.post(serviceUrl, params).then(res => {
        _this.diaLoging = false
        if (res.data.code == '0') {
          _this.$msgbox({ title: '提示', message: '操作成功', type: 'success' })
          var billId = res.data.data.billId
          _this.$msgbox({
            title: '提示',
            message: '操作成功',
            type: 'success',
            callback: action => {
              this.$router.push({ path: 'inByDeptApplyList', query: { inStockBillId: billId }})
            }
          })
        } else {
          this.$msgbox({ title: '提示', message: '操作失败', type: 'error' })
        }
      }, err => {
        this.diaLoging = false
        this.$msgbox({ title: '提示', message: '操作失败', type: 'error' })
      })
    },

    cancel () {
      this.$router.push({ path: 'inByDeptApplyList'})
    },
    getOutBillDetail (rowId) {
      var _this = this
      this.diaLoging = true
      //     this.axios.get('/spdHERPService/stockMgr/out/billMgr/getBillDetail',{
      this.axios.get('/spdHERPService/stockMgr/out4In/getBillDetail4In', {
        params: { id: rowId }
      }).then(res => {
        _this.diaLoging = false
        if (res.data.code == '0') {
          _this.distrBill = res.data.data
          _this.convertDistrToRecBill(_this.distrBill)
        }
      }, err => {
        this.diaLoging = false
      })
    },
    getSelected_Stock (temp) {
      this.inStockBill.inStocId = temp[1]
      this.inStockBill.inStocCode = temp[0] + '.' + temp[1]
      this.axios.post(`/spdHERPService/deptMgr/stocInfo/getOrgsByStock/` + temp[1]).then(res => {
        let ob = res.data.data
        // alert(ob.ename);
        this.inStockBill.inDeptId = ob.id
        this.inStockBill.inDeptName = ob.ename
      })
    },
    isNotFormatter (row, column, cellValue) {
      return cellValue == 1 ? '是' : '否'
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
    },
    reject(row){
      this.$confirm('是否确认拒收商品: ' + row.goodsName + '?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.inStockBill.lstDetail.splice(this.inStockBill.lstDetail.indexOf(row),1);
          var rowNum = 1;
          this.inStockBill.lstDetail.forEach(item => item.inBillRow = rowNum++);
          this.inStockBill.removeRowList.push(row);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
    }
  },
}

</script>

<style>
#in-by-dept-apply-edit .fa {
    margin-right: 5px;
}

#in-by-dept-apply-edit .vue-echarts {
    width: 100%;
    height: 400px;
}

#in-by-dept-apply-edit .el-table {
    font-size: 12px;
}

#in-by-dept-apply-edit fieldset {
    border-width: 1px;
    border-style: solid;
    border-color: #1fa0ff;
    border-image: initial;
}

#in-by-dept-apply-edit .icoBtn [class^=fa] {
    vertical-align: baseline;
    cursor: pointer;
    margin-right: 5px;
}

#in-by-dept-apply-edit .el-dialog--small {
    width: 70%;
}
</style>

<style scoped>
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
