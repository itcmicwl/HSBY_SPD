<template>
  <div id="contract-manage" class="full-height">
    <div style="margin-bottom: 5px;" class="inline-display">
      <div class="inline-display">
        <span>合同签订日期：</span>
        <el-date-picker class="data-picker" type="date" placeholder="开始日期" v-model="filter.signDateBegin" ></el-date-picker>
        <span>-</span>
        <el-date-picker class="data-picker" type="date" placeholder="结束日期" v-model="filter.signDateEnd" ></el-date-picker>
      </div>
      <div class="inline-display">
        <el-input placeholder="合同编号、合同名称、供应商" v-model="filter.provName"  @keyup.enter.native="searchBtnClick">
          <el-button slot="append" icon="search" @click="searchBtnClick"></el-button>
        </el-input>
      </div>
      <div class="inline-display" style="margin-left:50px;">
        <el-button  type="primary" @click="addPrepare">
          添加
        </el-button>
      </div>
    </div>

    <el-table highlight-current-row height="outter" ref="contractTable" class="table-height" :data="contractList" style="width: 100%" v-loading.body="loading" border>
      <el-table-column type="index" label="序号">
      </el-table-column>
      <el-table-column label="合同编号" show-overflow-tooltip prop="contractCode">
      </el-table-column>
      <el-table-column label="合同名称" show-overflow-tooltip prop="contractName">
      </el-table-column>
      <el-table-column label="供应商" show-overflow-tooltip prop="provName">
      </el-table-column>
      <el-table-column label="签订日期" show-overflow-tooltip prop="signDate">
        <template slot-scope="scope">
          {{formatDate(scope.row.signDate)}}
        </template>
      </el-table-column>
      <el-table-column label="有效期">
        <template slot-scope="scope">
          <div v-if="scope.row.beginDate && scope.row.endDate" :class="expdtDateClass(scope.row.endDate)">
            {{formatDate(scope.row.beginDate)}}至{{formatDate(scope.row.endDate)}}
          </div>
          <div v-else>
            Error
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="140" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button-group>
            <el-button size="mini" icon="edit" type="primary" @click.native.prevent="handleEdit(scope.row)">编辑
            </el-button>
            <el-button size="mini" icon="delete" type="danger" @click.native.prevent="handleDelete(scope.row)">删除
            </el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="float:right" small @size-change="handleSizeChange" @current-change="handlePageChange" :current-page.sync="pageNum" layout="total, sizes, prev, pager, next" :page-sizes="[10, 15, 20, 30]" :page-size="pageSize" :total="rTotal">
    </el-pagination>

    <el-dialog :title="dlgAddMode?'添加合同信息':'编辑合同信息'" :visible.sync="dlgVisible" >
      <el-form :model="currentContract" ref="contractForm" label-width="100px" :rules="validateRules" class="el-form-item-nomsg">
        <el-form-item label="供应商名称" required prop="provId">
          <el-select v-model="currentContract.provId" filterable :remote="remoteSearch" placeholder="请输入供应商名称" :remote-method="querySearchProvAsync" :loading="provSearchLoading"  clearable @input="handleFreezeRemote" @change="handleProvSelect">
            <el-option v-for="item in provSearchData" :key="item.id" :label="item.provName" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同信息" prop="attachmentPath" required>
          <el-input v-model="currentContract.attachmentPath" style="display:none" readonly disabled></el-input>
          <span style="font-size:12px;color:grey;">注：只允许上传15M大小以下文件。</span>
          <el-upload class="pic-uploader" ref="contractUpload" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!contractImageUrl}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess" :on-change="handleLocalAdd" :before-upload="beforeUpload" :on-error="handleUploadErr">
            <label :class="{'item-status-label':uploadSuccess}">
              <i :class="{'upload-success el-icon-check':uploadSuccess}"></i>
            </label>
            <el-tooltip placement="right" v-if="contractImageUrl" effect="light" :open-delay="300">
              <div slot="content">
                <img class="tooltip-pic" :src="largeContractImageUrl">
              </div>
              <img :src="contractImageUrl" class="pic" @mouseenter="picMouseEnter">
            </el-tooltip>
            <i v-else class="el-icon-plus pic-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="合同有效期" required>
          <el-col :span="6">
            <el-form-item prop="beginDate">
              <el-date-picker type="date" placeholder="生效日期" v-model="currentContract.beginDate" style="width: 100%;" ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col style="text-align:center;" :span="1">-</el-col>
          <el-col :span="6">
            <el-form-item prop="endDate">
              <el-date-picker type="date" placeholder="结束日期" v-model="currentContract.endDate" style="width: 100%;" ></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="合同签订日期" prop="signDate" required>
          <el-col :span="6">
            <el-date-picker type="date" placeholder="签订日期" v-model="currentContract.signDate" style="width: 100%;"  :editable="false"></el-date-picker>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click.native="dlgVisible = false" >取消</el-button>
        <el-button type="primary" @click="handleDlgConfirm"  :loading="btnLoading">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import debounce from 'throttle-debounce/debounce'

export default {
  components: {},
  data () {
    let validateBeginDate = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('开始日期不能为空'))
      } else {
        if (this.currentContract.beginDate) {
          this.$refs.contractForm.validateField('endDate')
        }
        callback()
      }
    }
    let validateEndDate = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('结束日期不能为空'))
      } else {
        if (Date.parse(this.currentContract.beginDate) > Date.parse(this.currentContract.endDate)) {
          return callback(new Error('结束日期必须大于开始日期'))
        }
        callback()
      }
    }
    let validateSignDate = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('合同签订日期不能为空'))
      } else {
        if (!(new Date(value) instanceof Date)) {
          return callback(new Error('非日期格式'))
        } else {
          callback()
        }
      }
    }
    return {
      validateRules: {
        provId: [
          { required: true, message: '请选择供应商', trigger: 'change' }
        ],
        beginDate: [
          { validator: validateBeginDate, trigger: 'change' }
        ],
        endDate: [
          { validator: validateEndDate, trigger: 'change' }
        ],
        signDate: [
          { validator: validateSignDate, trigger: 'change' }
        ],
        attachmentPath: [
          { required: true, message: '请上传合同附件', trigger: 'change' }
        ]
      },
      baseURL: '/spdHERPService/baseData/baseDataMaint/contManage',
      uploadURL: '/platformService/sys/upload/file/certs/',
      picURLPrefix: '/upload/',
      EXPDT_DAYS: 30 * 24 * 60 * 60 * 1000,

      filter: {
        signDateBegin: '',
        signDateEnd: '',
        searchText: ''
      },

      loading: false,
      provSearchLoading: false,

      pageNum: 1,
      pageSize: 30,
      rTotal: 0,

      dlgAddMode: false,
      dlgVisible: false,
      remoteSearch: true,
      uploadSuccess: false,
      showBigPic: false,
      btnLoading: false,

      contractImageUrl: '',
      currentContract: {},
      contractList: [],
      provSearchData: [],

      allowedFileTypes: ['image/bmp', 'image/jpeg', 'image/png', 'application/pdf']
    }
  },
  computed: {
    largeContractImageUrl () {
      if (this.currentContract.attachmentPath && this.showBigPic && this.uploadSuccess) {
        return this.picURLPrefix + this.currentContract.attachmentPath
      } else {
        return this.contractImageUrl
      }
    },
    handleData () {
      let contractData = JSON.parse(JSON.stringify(this.currentContract))
      contractData.signDate = Date.parse(contractData.signDate)
      contractData.beginDate = Date.parse(contractData.beginDate)
      contractData.endDate = Date.parse(contractData.endDate)
      delete contractData.provName
      return contractData
    },
    userInfo () {
      return {
        'X-US': this.user.xus
      }
    }
  },
  methods: {
    formatDate (item) {
      return new Date(item).toLocaleDateString()
    },
    expdtDateClass (date) {
      let endDate = Date.parse(date)
      let today = Date.parse(new Date())
      let expiring = false
      let expired = false
      if (endDate - today < 0) {
        expired = true
      } else if (endDate - today < this.EXPDT_DAYS) {
        expiring = true
      }
      return {
        'expiring': expiring,
        'expired': expired
      }
    },
    searchBtnClick () {
      this.getContractList()
    },
    addPrepare () {
      this.btnLoading = false
      this.contractImageUrl = ''
      this.provSearchData = []
      this.showBigPic = false
      this.uploadSuccess = false
      this.currentContract = this.newContract()
      this.dlgAddMode = true
      this.dlgVisible = true
      if (this.$refs['contractForm']) {
        this.$refs['contractForm'].resetFields()
        this.$refs['contractUpload'].clearFiles()
      }
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.getContractList()
    },
    handlePageChange (val) {
      this.pageNum = val
      this.getContractList()
    },
    handleEdit (item) {
      this.btnLoading = false
      this.showBigPic = false
      if (item.attachmentPath) {
        this.uploadSuccess = true
        let slashIndex = item.attachmentPath.lastIndexOf('/')
        if (slashIndex !== -1) {
          this.contractImageUrl = this.picURLPrefix + item.attachmentPath.substring(0, slashIndex) + '/thumb-' + item.attachmentPath.substring(slashIndex + 1)
        } else {
          this.contractImageUrl = ''
        }
      } else {
        this.uploadSuccess = false
        this.contractImageUrl = ''
      }
      this.currentContract = JSON.parse(JSON.stringify(item))
      this.provSearchData = [
        { id: this.currentContract.provId, provName: this.currentContract.provName }
      ]
      this.dlgAddMode = false
      if (this.$refs['contractForm']) {
        this.$refs['contractUpload'].clearFiles()
      }
      this.dlgVisible = true
      this.handleFreezeRemote()
    },
    handleDelete (item) {
      this.$confirm('是否删除合同信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        this.axios.delete(this.baseURL + '/delContract', {
          params: {
            'id': item.id,
            'version': item.version
          }
        }).then(res => {
          if (res.data.code >= 0) {
            this.$message({
              type: 'success',
              message: '删除合同信息成功。'
            })
            let removeIndex = this.contractList.findIndex(o => o.id === item.id)
            this.contractList.splice(removeIndex, 1)
            this.rTotal--
          } else {
            this.$message.error(res.data.msg)
          }
        }).catch(err => {
          this.handleError(err)
        })
      })
    },
    debounceQuerySearchProvAsync: debounce(500, function (queryString) {
      this.axios.get(this.baseURL + '/getProvList', {
        params: {
          'provName': queryString
        }
      }).then(res => {
        if (res.data.code >= 0) {
          this.provSearchData = res.data.data
          this.provSearchLoading = false
        } else {
          this.$message.error('获取数据失败。' + res.data.msg)
          this.provSearchLoading = false
        }
      }).catch(err => {
        this.handleError(err, this.provSearchLoading)
      })
    }),
    querySearchProvAsync (queryString) {
      if (queryString !== '') {
        this.provSearchLoading = true
        this.debounceQuerySearchProvAsync(queryString)
      }
    },
    handleFreezeRemote () {
      this.$set(this, 'remoteSearch', false)
      this.$nextTick(function () {
        this.$set(this, 'remoteSearch', true)
      })
    },
    handleProvSelect (item) {
      if (item) {
        const prov = this.provSearchData.find(o => o.id === item)
        if (prov) {
          this.currentContract.provName = prov.provName
        }
      }
    },
    beforeUpload (file) {
      if (!this.allowedFileTypes.includes(file.type)) {
        this.$message.error('文件格式不符合要求，请按要求上传' + this.allowedFileTypes.join(',') + '文件！')
        return false
      }
      if (file.size / 1024 / 1024 > 15) {
        this.$message.error('上传图片大小不能超过15M！')
        return false
      }
    },
    handleLocalAdd (file, fileList) {
      this.uploadSuccess = false
      fileList.splice(0)
      fileList.push(file)
      this.contractImageUrl = file.url
    },
    handleUploadSuccess (response, file, fileList) {
      if (response.code < 0) {
        this.$message({
          type: 'error',
          showClose: 'true',
          duration: 0,
          message: file.name + '上传失败，' + response.msg
        })
      } else {
        this.$message.success(file.name + '上传成功！')
        let { ext, id, path } = response.data[0]
        this.currentContract.attachmentPath = path + '/' + id + '.' + ext
        this.$refs.contractForm.validateField('attachmentPath')
        this.$nextTick(() => {
          this.uploadSuccess = true
        })
      }
    },
    handleUploadErr (_, file, fileList) {
      this.$message.error('上传图片失败，请联系管理员！')
    },
    handleDlgConfirm () {
      this.$refs['contractForm'].validate((valid) => {
        if (valid) {
          this.btnLoading = true
          if (this.dlgAddMode) { // new
            this.axios.post(this.baseURL + '/addContract', this.handleData).then(res => {
              if (res.data.code >= 0) {
                this.$message.success('新增合同信息成功。')
                this.currentContract.id = res.data.data
                this.contractList.push(this.currentContract)
                this.rTotal++
                this.currentContract = this.newContract()
                this.dlgVisible = false
              } else {
                this.$message.error('新增合同失败，' + res.data.msg)
                this.btnLoading = false
              }
            }).catch(err => {
              this.btnLoading = false
              this.handleError(err, this.btnLoading)
            })
          } else { // modify
            this.axios.put(this.baseURL + '/modContract', this.handleData).then(res => {
              if (res.data.code >= 0) {
                this.$message.success('修改合同成功。')
                this.currentContract.version = res.data.data
                this.changeTableData(this.currentContract)
                this.dlgVisible = false
              } else {
                this.$message.error('修改合同失败，' + res.data.msg)
                this.btnLoading = false
              }
            }).catch(err => {
              this.btnLoading = false
              this.handleError(err, this.btnLoading)
            })
          }
        } else {
          this.$message.error('数据验证失败，请更正！')
        }
      })
    },
    handleError (err, loading) {
      this.$msgbox({ title: '错误', type: 'error', message: err })
      if (loading) {
        loading = false
      }
    },
    picMouseEnter (event) {
      this.showBigPic = true
    },
    getContractList () {
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        ...this.filter
      }
      this.loading = true
      this.axios.get(this.baseURL + '/getContractList', {
        params
      }).then(res => {
        if (res.data.code >= 0) {
          this.contractList = res.data.data.data
          this.rTotal = res.data.data.total
        } else {
          this.$message.error(res.data.msg)
        }
      }).catch(err => {
        this.handleError(err, this.loading)
      })
      this.loading = false
    },
    changeTableData (item) {
      let contract = this.contractList.find(o => o.id === item.id)
      Object.assign(contract, item)
    },
    newContract () {
      return {
        id: '',
        provId: '',
        provName: '',
        signDate: '',
        beginDate: '',
        endDate: '',
        attachmentPath: '',
        version: 0
      }
    }
  },
  mounted () {
    this.getContractList()
  }
}

</script>

<style scoped>
.table-height {
  height: calc(100% - 75px);
}

.full-height {
  height: 100%;
}

.inline-display {
  display: inline-block;
}

.data-picker {
  width: 110px;
}

.expiring {
  color: #ff00ff;
}

.expired {
  color: red;
}

.is-error {
  margin-bottom: 22px;
}

.el-form-item__error {
  display: block;
}

.tooltip-pic {
  max-width: 800px;
}
</style>

<style>
#contract-manage .pic-uploader .el-upload {
  border: 1px dashed #9b9b9b;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

#contract-manage .upload-background .el-upload::after {
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
  background-image: url("../../../assets/images/logo_200x200.png");
  background-repeat: no-repeat;
  opacity: 0.20;
}

#contract-manage .pic-uploader .el-upload:hover {
  border-color: #20a0ff;
}

#contract-manage .pic-uploader .item-status-label {
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

#contract-manage .pic-uploader .item-status-label .upload-success {
  font-size: 12px;
  margin-top: 11px;
  transform: rotate(-45deg) scale(.8);
  color: #fff;
}

#contract-manage .pic-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

#contract-manage .pic {
  min-width: 50px;
  min-height: 50px;
  max-width: 178px;
  max-height: 178px;
  display: block;
}

#contract-manage .el-upload-list__item,
.el-upload-list__item:first-child {
  margin-top: 0;
  margin-bottom: 5px;
}
</style>
