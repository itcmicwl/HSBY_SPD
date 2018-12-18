<template>
  <div id="staff-mgr" style="height: 100%;">
	<el-row height="auto" style="height: 99%;">
				<el-col :span="6" style="height: 99%;">
				<div style="padding-bottom: 7px">
						<el-input v-model="filterSurDefName" placeholder="请输入手术包类型名称" >
						 </el-input>
				</div> 
			
					<el-tree :data="surKindInfo" 
								node-key="id"
								:default-expanded-keys="expadoIds"
								:props="defaultProps"
								:highlight-current="true"
								@node-click="handleNodeClick"
								:filter-node-method="filterNode"
								ref = "surDefTree">
					</el-tree>
				
				</el-col>
				<el-col :span="18" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
				    <div style="padding-bottom: 5px;">
						<el-form :inline="true" label-width="80px">
							<el-form-item label="">
								<el-input v-model="filters.cname" placeholder="请输入手术包名称" ></el-input>
							</el-form-item>
							<el-button-group>
								<el-button type="primary" v-on:click="getSurPkgDef(1)" >查询</el-button>
								<el-button type="success" @click="surDefEdit(null)" >新增</el-button>
							</el-button-group>
						</el-form>
				   </div>
					<!--列表-->
					
						<el-table :data="surDefList" highlight-current-row v-loading="loading" style="width: 100%;" border fit    class="staffMgr-tableheight">
							<el-table-column prop="cname" label="手术包名"  />
							<el-table-column prop="ename" label="手术包英文" >
							</el-table-column>
							<el-table-column prop="kindName" label="所属分类" >
							</el-table-column>
							<el-table-column prop="remark" label="备注" >
							</el-table-column>
							<el-table-column label="标识">
								<template slot-scope="scope">
          				<span v-if="scope.row.flag=='1'">启用</span>
									<span v-else>停用</span>
								</template>
							</el-table-column>
							<el-table-column label="操作" width="150">
								<template slot-scope="scope">
									<el-button  @click="surDefEdit(scope.row)" type="text">编辑</el-button>
									<el-button  @click="deleteSurDef(scope.row)" type="text">删除</el-button>
								</template>
							</el-table-column>
						</el-table>
						<!--工具条-->
						<el-col :span="24" class="toolbar">
							<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
							</el-pagination>
						</el-col>
				
				</el-col>
					<!--编辑界面-->
				<el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" >
						<el-form :model="surDef" label-width="100px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
							<el-row>
								<el-col :span="12">
									<el-form-item label="手术包名" prop="cname"  :error="errors.cname">
										<el-input v-model="surDef.cname" placeholder="请输入手术包名" ></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12" v-show="false">
									<el-form-item label="kindId" prop="kindId"  >
										<el-input v-model="surDef.kindId"  ></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="手术包英文名" :error="errors.ename">
										<el-input v-model="surDef.ename"  placeholder="请输入手术包英文名"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="12">
									<el-form-item label="所属类型" prop="kindCode"  :error="errors.kindId">
										<el-cascader placeholder="请选择类型"
										v-model="selectedKind"  
										:show-all-levels="false" 
										:options="surKindInfo4cascader" 
										filterable 
										:change-on-select="true"  
										:props="kindSltProps">
										</el-cascader>							
									</el-form-item>
								</el-col>
							</el-row>

							<el-form-item label="备注">
								<el-input type="textarea" v-model="surDef.remark"  placeholder="备注信息"></el-input>
							</el-form-item>

						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click.native="editFormVisible = false">取消</el-button>
							<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
						</div>
				</el-dialog>
	</el-row>
  </div>	
</template>
<script>
export default {
  data() {
    return {
      filterSurDefName: "",

      filters: {
        cname: "",
        kindId: "",
        kindName: ""
      },
      expadoIds: [],
      total: 0,
      page: 1,
      pageSize: 30,
      loading: false,
      surDefList: [],

      surDef: {
        cname: "",
        ename: "",
        kindId: "",
        remark: "",
        hosId: ""
      },
      modelTitle: "添加手术包",
      editFormVisible: false,
      editLoading: false,
      editFormRules: {
        cname: [
          { required: true, message: "请输入手术包名", trigger: "blur" },
          { min: 1, max: 36, message: "长度在 1 到 36 个字符" }
        ],
        kindCode: [
          { required: true, message: "请选择所属分类", trigger: "blur" }
        ]
      },

      surKindInfo: [],
      defaultProps: {
        children: "children",
        label: "label",
        id: "id",
        code: "code",
        pid: "pid"
      },
      surKindInfo4cascader: [],
      kindSltProps: {
        value: "code",
        label: "label"
      },
      errors: {
				cname: "",
				ename: "",
        kindId: ""
      }
    };
  },
  computed: {
    selectedKind: {
      get: function() {
        let res = [];
        if (this.surDef.kindCode) {
          //通过当前的手术包类型编码 找到其父code
          let kindCodes = this.surDef.kindCode.split(".");
          if (kindCodes.length > 1) {
            var i = 0;
            for (i; i < kindCodes.length; i++) {
              let tempArr = kindCodes.slice(0, i + 1);
              res.push(tempArr.join("."));
            }
          } else {
            res = kindCodes;
          }
        }

        return res;
      },
      set: function(kindCodeArr) {
        let res = "";
        if (kindCodeArr.length > 0) {
          res = kindCodeArr[kindCodeArr.length - 1];
        }
        this.surDef.kindCode = res;
      }
    }
  },
  watch: {
    filterSurDefName(val) {
      this.$refs.surDefTree.filter(val);
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },

    //获取手术包列表
    getSurPkgDef: function(pIndex) {
      this.page = pIndex;
      var param = {
        orderBy: "",
        queryObject: {
          cname: this.filters.cname,
          kindId: this.filters.kindId,
          hosId: this.user.corpId,
          flag: 1
        },
        page: this.page,
        rows: 30
      };
      this.loading = true;
      this.axios
        .post(`/spdHERPService/surgery/surgeryPkgDef/listByPage`, param)
        .then(
          res => {
            this.loading = false;
            this.surDefList = res.data.data.data;
            this.total = res.data.data.total;
          },
          err => {
            this.loading = false;
          }
        );
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getSurPkgDef(this.page);
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getSurPkgDef(this.page);
    },
    deleteSurDef(item) {
      let param = {
        id: item.id,
        kindCode: item.kindCode,
        version: item.version,
        flag: 0
      };
      this.loading = true;
      this.axios
        .post(`/spdHERPService/surgery/surgeryPkgDef/update`, param)
        .then(
          res => {
            if (res.data.code == "0") {
              this.loading = true;
              this.$message({ message: "操作成功！", type: "success" });
              this.editFormVisible = false;
              this.getSurPkgDef(1);
            } else {
              this.$message.error("操作失败！");
            }
          },
          err => {
            this.loading = false;
          }
        );
    },
    surDefEdit(item) {
      this.errors.cnameErr = "";
      this.errors.kindErr = "";
      this.editFormVisible = true;

      var param = {};
      this.axios
        .post(`/spdHERPService/surgery/surgeryPkgKind/getSurgeryKindByHos`, {})
        .then(res => {
          this.surKindInfo4cascader = res.data.data.children;
        });
      if (item) {
        this.surDef = Object.assign({}, item);
        this.modelTitle = "修改手术包";
      } else {
        this.surDef = { flag: "1", hosId: this.user.corpId };
        this.modelTitle = "添加手术包";
      }
    },
    editSubmit: function() {
			this.errors.cname = "";
			this.errors.ename = "";
			this.errors.kindId = "";
      var _this = this;
      var form = this.$refs.editForm;
      const h = this.$createElement;
      var arr = new Array();
      form.validate(valid => {
        if (valid) {
          this.editLoading = true;
          let serviceUrl = this.surDef.id
            ? "/spdHERPService/surgery/surgeryPkgDef/update"
            : "/spdHERPService/surgery/surgeryPkgDef/add";
          this.axios.post(serviceUrl, this.surDef).then(
            res => {
              this.editLoading = false;
              if (res.data.code == "0") {
                this.$message({ message: "操作成功！", type: "success" });
                this.editFormVisible = false;
                this.getSurPkgDef(1);
              } else if (res.data.code == "-122") {
                let err = res.data.validateErrors.fieldErrors[0];
                if (err.field == "cname") {
									//this.errors.cname = err.message;
									err.message = "手术包名：" + err.message;
                  arr.push(h("span", null, err.message));
                }
                if (err.field == "ename") {
									//this.errors.ename = err.message;
									err.message = "手术包英文名：" + err.message;
                  arr.push(h("span", null, err.message));
								}
								if (err.field == "kindId") {
									//this.errors.kindId = err.message;
                  arr.push(h("span", null, err.message));
                }
                _this.$msgbox({
                  title: "错误",
                  type: "error",
                  message: h("p", null, arr)
                });
              } else {
                this.$message.error("操作失败！");
              }
            },
            err => {
              this.editLoading = false;
            }
          );
        } else {
          var fields = form.fields;
          var msg = "";
          fields.forEach(f => {
            if (f.validateState == "error") {
              msg += "</br>" + f.validateMessage;
              if (arr.length > 0) arr.push(h("br", null, null));
              arr.push(h("span", null, f.validateMessage));
            }
          });
          _this.$msgbox({
            title: "错误",
            type: "error",
            message: h("p", null, arr)
          });
        }
      });
    },
    handleNodeClick(data) {
      this.filters.kindId = data.id;
      this.getSurPkgDef(1);
    },

    getSurgeryKindByHos: function() {
      var param = {};
      this.loading = true;
      this.axios
        .post(`/spdHERPService/surgery/surgeryPkgKind/getSurgeryKindByHos`, {})
        .then(res => {
          this.surKindInfo = res.data.data.children;
          this.expadoIds = [this.surKindInfo[0].id];
          this.loading = false;
        });
    }
  },
  mounted() {
    this.surDef.hosId = this.user.corpId;
    this.getSurgeryKindByHos();
    this.getSurPkgDef(1);
  }
};
</script>

<style>
#staff-mgr .el-tree-node__label {
  font-size: 12px;
}
#staff-mgr .el-table {
  font-size: 12px;
}
#staff-mgr .el-tree {
  min-height: calc(100% - 80px);
}
#staff-mgr .el-table__body-wrapper {
  overflow-x: hidden;
}
#staff-mgr .staffMgr-tableheight {
  height: calc(100% - 60px);
}
</style>