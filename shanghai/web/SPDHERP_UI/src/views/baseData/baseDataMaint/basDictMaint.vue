<template>
  <div id="base-dict-maint" class="full-height">
    <el-row class="full-height">
      <el-col :span="6" class="full-height">

        <el-input  placeholder="搜索" icon="search" v-model="searchParam" :on-icon-click="handleSearchIconClick" class="search-input">
        </el-input>
        <el-button-group class="btn-grp">
          <el-button type="primary" @click="addDict(null)" >新增</el-button>
          <el-button type="primary" @click="updateDict()" :disabled="!selectedDictId" >编辑</el-button>
          <el-button type="primary" @click="deleteDict()" :disabled="!selectedDictId" >删除</el-button>
        </el-button-group>

        <el-table :data="dictListData" highlight-current-row @current-change="handleCurrentChange" style="width: 100%" height="outter" class="table-height">
          <el-table-column type="index" width="50">
          </el-table-column>
          <el-table-column property="ename" label="字典名">
          </el-table-column>
          <el-table-column property="cname" label="别名" show-overflow-tooltip>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="18" style="padding-left: 10px" class="full-height">
        <el-button-group class="btn-grp">
          <el-button type="primary" @click="addDictValue" :disabled="!selectedDictId" >新增</el-button>
          <el-button type="primary" @click="updateDictValue" :disabled="!selectedDictValueId" >编辑</el-button>
          <el-button type="primary" @click="deleteDictValue" :disabled="!selectedDictValueId" >删除</el-button>
        </el-button-group>

        <el-table :data="dictValueListData" highlight-current-row @current-change="handleCurrentChange4Right" style="width: 100%" class="table-height">
          <el-table-column type="index">
          </el-table-column>
          <el-table-column property="dictId" label="字典ID">
          </el-table-column>
          <el-table-column property="dictName" label="字典名">
          </el-table-column>
          <el-table-column property="dictCname" label="字典别名">
          </el-table-column>
          <el-table-column property="val" label="值编码">
          </el-table-column>
          <el-table-column property="ename" label="值名称" show-overflow-tooltip>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <el-dialog :title="popTitle" :visible.sync="editFormVisible">
      <el-form :model="dict" :inline="true" label-width="80px" :rules="editFormRules" ref="editDictForm">
        <el-form-item label="字典KEY" prop="ename">
          <el-input v-model="dict.ename"  placeholder="请输入字典key（英文）"></el-input>
        </el-form-item>
        <el-form-item label="字典名" prop="cname">
          <el-input v-model="dict.cname"  placeholder="请输入字典名（中文）"></el-input>
        </el-form-item>
        <el-form-item label="排序号" prop="ordinal">
          <el-input-number v-model="dict.ordinal" :min="1" :max="100" ></el-input-number>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="dict.remark"  placeholder="请输入字典描述（中文）"></el-input>
        </el-form-item>
        <el-form-item label="类别" prop="kind">

          <el-select v-model="dict.kind" placeholder="请选择" >
            <el-option key=0 label="系统类" value=0></el-option>
            <el-option key=1 label="业务类" value=1></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editDictSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="popTitle" :visible.sync="editForm4RightVisible">
      <el-form :model="dictValue" :inline="true" label-width="80px" :rules="editForm4RightRules" ref="editDictValueForm">
        <el-form-item label="字典ID" prop="dictId" v-show="false">
          <el-input v-model="dictValue.dictId"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="字典KEY" prop="dictName">
          <el-input v-model="dictValue.dictName"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="字典名" prop="dictCname">
          <el-input v-model="dictValue.dictCname"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="值名" prop="ename">
          <el-input v-model="dictValue.ename"  :disabled="false" placeholder="请输入字典值"></el-input>
        </el-form-item>
        <el-form-item label="值编码" prop="val">
          <el-input v-model="dictValue.val"  :disabled="false" placeholder="请输入字典编码"></el-input>
        </el-form-item>
        <el-form-item label="排序号" prop="ordinal">
          <el-input-number v-model="dictValue.ordinal" :min="1" :max="100" ></el-input-number>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="dictValue.remark"  placeholder="请输入字典描述（中文）"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editForm4RightVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editDictValueSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ElCol from 'element-ui/packages/col/src/col'

export default {

  components: { ElCol },
  data () {
    return {
      searchParam: '',

      dictListData: [],
      dictListOrigData: [],
      dictValueListData: [],
      popTitle: '',
      editFormVisible: false,
      editForm4RightVisible: false,

      dict: {
        id: '',
        ename: '',
        cname: '',
        kind: 1
      },
      dictValue: {
        ename: '',
        val: '',
        dictName: ''
      },
      editFormRules: {
        ename: [
          { required: true, message: '请输入字典key', trigger: 'blur' }
        ]
      },
      editForm4RightRules: {
        ename: [{ required: true, message: '请输入字典值名称', trigger: 'blur' }],
        val: [{ required: true, message: '请输入字典值编码', trigger: 'blur' }]
      },
      editLoading: false,
      selectedDictId: '',
      selectedDictValueId: '',
      currentRow: {},
      currentRow4Right: {},
      deleteDialogVisible: false,
      popover4DelVisible: false

    }
  },
  computed: {

  },
  methods: {
    handleSearchIconClick () {
      if (this.searchParam) {
        this.dictListData = JSON.parse(JSON.stringify(this.dictListOrigData.filter(item => {
          return item.ename.indexOf(this.searchParam) !== -1 || item.cname.indexOf(this.searchParam) !== -1
        })))
      } else {
        this.dictListData = JSON.parse(JSON.stringify(this.dictListOrigData))
      }
    },
    updateDict: function () {
      this.dict = {}
      this.popTitle = '修改字典'
      if (this.selectedDictId.length === 0) {
        alert('请先选中一行！')
      } else {
        this.editFormVisible = true
        this.getTheDict()
      }
    },
    addDict: function () {
      this.editFormVisible = true
      this.popTitle = '添加字典'
      this.dict = {}
      this.dict.kind = '1'
    },
    deleteDict: function () {
      if (this.selectedDictId.length === 0) {
        alert('请先选中一行！')
      } else {
        this.$confirm('确认是否删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(_ => {
          this.executeDel()
        })
      }
    },
    executeDel: function () {
      this.axios.post(`/platformService/sys/dict/delete`, { id: this.currentRow.id }).then(res => {
        this.editLoading = false
        if (res.data.code === 0) {
          this.$message({ message: '操作成功！', type: 'success' })
          this.deleteDialogVisible = false
          this.selectedDictId = ''
          this.currentRow = {}
          this.dictListOrigData = res.data.data.data
          this.dictListData = JSON.parse(JSON.stringify(this.dictListOrigData))
        } else {
          this.$message.error(res.data.msg + '！')

          this.deleteDialogVisible = false
        }
      }, err => {
        this.handleError(err, this.editLoading)
      })
    },
    editDictSubmit: function () {
      this.$refs.editDictForm.validate((valid) => {
        if (valid) {
          this.editLoading = true

          this.axios.post(`/platformService/sys/dict/update`, this.dict).then(res => {
            this.editLoading = false
            if (res.data.code === 0) {
              this.$message({ message: '操作成功！', type: 'success' })
              this.editFormVisible = false
              this.selectedDictId = ''
              this.currentRow = {}
              this.dictListOrigData = res.data.data.data
              this.dictListData = JSON.parse(JSON.stringify(this.dictListOrigData))
            } else {
              if (res.data.validateErrors.fieldErrors.length > 0) {
                this.$message.error(res.data.validateErrors.fieldErrors[0])
              } else {
                this.$message.error(res.data.msg)
              }
            }
          }, err => {
            this.handleError(err, this.editLoading)
          })
        }
      })
    },

    showDictList: function () {
      // gyqxadmin  0000
      let kind = (this.user.userId == '0000') ? null : 1
      this.axios.post(`/platformService/sys/dict/getDicts`, { kind: kind }).then(res => {
        if (res.data.code === 0) {
          this.dictListOrigData = res.data.data.data
          this.dictListData = JSON.parse(JSON.stringify(this.dictListOrigData))
        } else {
          this.$message.error('查询失败！')
        }
      }, err => {
        this.handleError(err, this.editLoading)
      })
    },
    getTheDict: function () {
      this.axios.post(`/platformService/sys/dict/getTheDict`, { id: this.selectedDictId }).then(res => {
        if (res.data.code === 0) {
          this.dict = res.data.data
        } else {
          this.$message.error('查询失败！')
        }
      }, err => {
        this.handleError(err, this.editLoading)
      })
    },
    handleCurrentChange (val) {
      this.selectedDictValueId = ''
      if (val != null) {
        this.currentRow = val
        this.selectedDictId = val.id
      }
      // 加载右侧数据
      this.axios.post(`/platformService/sys/dict/getDictValueVos`, { dictId: this.selectedDictId }).then(res => {
        if (res.data.code === 0) {
          this.dictValueListData = res.data.data.data
        } else {
          this.$message.error('查询失败！')
        }
      }, err => {
        this.handleError(err, this.editLoading)
      })
    },

    handleCurrentChange4Right (val) {
      if (val != null) {
        this.currentRow4Right = val
        this.selectedDictValueId = val.id
      }
    },

    addDictValue: function () {
      //    alert('addDictValue:'+this.currentRow.id+":"+this.currentRow.ename+this.currentRow.cname);
      if (this.selectedDictId.length === 0) {
        alert('请在左侧选中一行！')
      } else {
        this.editForm4RightVisible = true
        this.popTitle = '添加字典' + this.currentRow.ename + '的属性'
        this.dictValue = {}
        this.dictValue.dictName = this.currentRow.ename
        this.dictValue.dictCname = this.currentRow.cname
        this.dictValue.dictId = this.currentRow.id
      }
    },

    updateDictValue: function () {
      this.dictValue = {}
      this.popTitle = '修改字典值'
      if (this.selectedDictValueId.length === 0) {
        alert('请先选中一行！')
      } else {
        this.editForm4RightVisible = true
        this.getTheDictValue()
      }
    },
    deleteDictValue: function () {

      if (this.selectedDictValueId.length === 0) {
        alert('请先选中一行！')
      } else {
        this.$confirm('确认是否删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(_ => {
          this.executeDel4Right()
        })
      }
    },
    executeDel4Right: function () {
      this.axios.post(`/platformService/sys/dict/deleteDictValue`, { id: this.currentRow4Right.id, dictId: this.currentRow4Right.dictId }).then(res => {
        this.editLoading = false
        if (res.data.code === 0) {
          this.$message({ message: '操作成功！', type: 'success' })
          this.popover4DelVisible = false
          this.selectedDictValueId = ''
          this.currentRow4Right = {}
          this.dictValueListData = res.data.data.data
        } else {
          this.$message.error('操作失败！')
          this.popover4DelVisible = false
        }
      }, err => {
        this.handleError(err, this.editLoading)
      })
    },

    getTheDictValue: function () {
      this.axios.post(`/platformService/sys/dict/getTheDictValue`, { id: this.currentRow4Right.id, dictId: this.currentRow4Right.dictId }).then(res => {
        if (res.data.code === 0) {
          this.dictValue = res.data.data
        } else {
          this.$message.error('查询失败！')
        }
      }, err => {
        this.handleError(err, this.editLoading)
      })
    },
    editDictValueSubmit: function () {
      this.$refs.editDictValueForm.validate((valid) => {
        if (valid) {
          this.editLoading = true

          this.axios.post(`/platformService/sys/dict/updateDictValue`, this.dictValue).then(res => {
            this.editLoading = false
            if (res.data.code === 0) {
              this.$message({ message: '操作成功！', type: 'success' })
              this.editForm4RightVisible = false
              this.selectedDictValueId = ''
              this.currentRow4Right = {}
              this.dictValueListData = res.data.data.data
            } else {
              // this.$message.error('操作失败！');

              this.$message.error(res.data.msg)
            }
          }, err => {
            this.handleError(err, this.editLoading)
          })
        }
      })
    },
    handleError (err, loading) {
      this.$msgbox({ title: '错误', type: 'error', message: err })
      if (loading != null && loading !== undefined) {
        loading = false
      }
    }
  },

  mounted () {
    this.showDictList()
  }
}

</script>

<style scoped>
.btn-grp {
  margin-bottom: 5px;
}

.table-height {
  height: calc(100% - 50px)
}

.full-height {
  height: 100%;
}

.search-input {
  width: 40%;
  margin-right: 10px;
  top: -2px;
}
</style>
