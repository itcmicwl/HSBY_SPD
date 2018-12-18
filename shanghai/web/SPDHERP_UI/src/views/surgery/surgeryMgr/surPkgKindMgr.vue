<template>
	<div id="surPkgKind-mgr" style="height: 100%;">
		<el-row height="auto" style="height: 99%;">
			<el-col :span="6"  style="height: 100%;">
				<div style="margin-bottom: 5px">
					<el-input 
							v-model="filterKindName" placeholder="请输入分类名称过滤" >
					</el-input>
				</div>			
				<el-tree :data="treeValue" node-key="id"
								:default-expanded-keys="expadoIds"
								:props="defaultProps"
								:highlight-current="true"
								@node-click="handleNodeClick"  
								:filter-node-method="filterNode"
								ref = "surKindTree">
				</el-tree>
			</el-col>
			<el-col :span="18" height="auto"  style="padding-left:5px;height: 99%"  v-loading="loading">
				<el-row >
						<div style="margin-bottom: 7px;">
							<el-button-group>
								<el-button type="success" @click="addSurKind" :disabled="addDisFlag">添加</el-button>
								<el-button type="primary" @click="updateSurKind" :disabled="saveDisFlag">保存</el-button>
								<el-button type="danger" @click="delSurKind" :disabled="delDisFlag">删除</el-button>
							</el-button-group>
						</div>
				</el-row>
				<el-row>
					<el-col :span="18"  style="height: 99%">
						<el-form :model="formValue" labelWidth="100px" :rules="editFormRules" ref="editForm"  class="el-form-item-nomsg">
							<el-form-item label="id" v-show="false" >
								<el-input v-model="formValue.id" :disabled="true" ></el-input>
							</el-form-item>
							<el-form-item label="pid" v-show="false" >
								<el-input v-model="formValue.pid" :disabled="true" ></el-input>
							</el-form-item>
							<el-form-item label="hosId" v-show="false" >
								<el-input v-model="formValue.hosId" :disabled="true" ></el-input>
							</el-form-item>
							<el-form-item label="levelCode" v-show="false">
								<el-input v-model="formValue.levelCode" :disabled="true"></el-input>
							</el-form-item>
							<el-row>
								<el-col>
									<el-form-item label="分类名称：" prop="kindName" :error="errors.kindName">
										<el-input v-model="formValue.kindName" ></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-form-item label="上级分类" >			
								<el-cascader placeholder="请选择上级分类"
										v-model="selectedKindParent"  
										:show-all-levels="false" 
										:options="kindInfo4cascader" 
										filterable 
										clearable
										:change-on-select="true" 
										:props="orgSltProps" >
								</el-cascader>								
							</el-form-item>
						</el-form>					
					</el-col>
				</el-row>
			</el-col>
		</el-row>
	</div>
</template>

<script>
export default {
  data() {
    return {
      baseURL: "/spdHERPService/surgery/surgeryPkgKind",

      delDisFlag: true,
			saveDisFlag: true,
			addDisFlag: false,

      editFormRules: {
        kindName: [
          { required: true, message: "请输入分类名称", trigger: "blur" }
        ]
      },
      treeValue: [],
      defaultProps: {
        children: "children",
        label: "label",
        id: "id",
        pid: "pid",
        code: "code"
      },
      loading: 'false',

      kindInfo4cascader: [],
      orgSltProps: {
        value: "code",
        label: "label"
      },
      filterKindName: "",
      filters: {
        kindName: ""
      },
      
      formValue: {
        id: "",
        pid: "",
        hosId: "",
        kindName: "",
        levelCode: ""
      },
      //selectedKindId: "",
			selectedLevelCode: "",
			selectedKindParent: [],
      expadoIds: [],
      errors: {
        kindName: ""
      }
    };
  },

  watch: {
    filterKindName(val) {
      this.$refs.surKindTree.filter(val);
		},
		selectedLevelCode: function(newValue, oldValue) {
			let res = [];
			if (newValue) {
				//通过当前点击的分类 的levelCode 找到其父code
				let levelCodes = newValue.split(".");
				if (levelCodes.length > 1) {						
					var i = 0;
					for (i; i < levelCodes.length; i++) {
						let tempArr = levelCodes.slice(0, i + 1);
						res.push(tempArr.join("."));
					}
				} else {
					res = levelCodes;
				}
			}
			res.pop(); //移除最后一个				
			this.selectedKindParent = res;
		},
		selectedKindParent:function(newValue, oldValue){
			if(newValue && newValue.length > 0){
				this.formValue.pid = newValue[newValue.length - 1];
			} else {
				this.formValue.pid = "";
			}
		}
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    getSurKindByHos: function() {
      var param = {};
      this.loading = true;
      this.axios
        .post(this.baseURL + "/getSurgeryKindByHos", { param })
        .then(res => {
          this.treeValue = res.data.data.children;
          this.kindInfo4cascader = res.data.data.children;
       /*    if (this.treeValue) {
            this.expadoIds = [this.treeValue[0].id];
          } */

          this.loading = false;
        });
    },

    handleNodeClick(data) {
      this.errors.kindName = "";

      //this.$refs.editForm.resetFields();

      this.formValue = {};
			
      let params = { id: data.id };

      this.axios.post(this.baseURL + "/getSurKind", params).then(res => {
        this.formValue = res.data.data;

				this.selectedLevelCode = res.data.data.levelCode;
        
				this.addDisFlag = false;
				this.saveDisFlag = false;
				this.delDisFlag = false;	
      });
    },
    addSurKind: function() {
      //this.$refs.editForm.resetFields();
      this.errors.kindName = "";

			this.addDisFlag = true;
			this.saveDisFlag = false;
			this.delDisFlag = true;	

			this.formValue = { hosId: this.user.corpId };
			
			this.selectedLevelCode = "";								
			this.selectedKindParent = [];
    },

    // 更新手术包类型
    updateSurKind: function() {
      this.errors.kindName = "";
      this.formValue.hosId = this.user.corpId;

      var _this = this;
      var form = this.$refs.editForm;
      const h = this.$createElement;

      var arr = new Array();
      form.validate(valid => {
        if (valid) {
           this.loading = true
          let serviceUrl = this.formValue.id
            ? this.baseURL + "/update"
            : this.baseURL + "/add";
          this.axios.post(serviceUrl, _this.formValue).then(
            res => {
              if (res.data.code == "0") {
                this.$message({
                  message: "操作成功！",
                  type: "success"
                });

              	this.formValue = {};
                this.selectedLevelCode = "";
								this.selectedKindParent = [];

                this.axios
                  .post(this.baseURL + "/getSurgeryKindByHos", {})
                  .then(res => {
                    this.treeValue = res.data.data.children;
                    this.kindInfo4cascader = res.data.data.children;
                    this.loading = false;

                    //this.expadoIds = [this.selectedKindId];
                  });

  

								this.addDisFlag = false;
								this.saveDisFlag = true;
								this.delDisFlag = true;	

              } else if (res.data.code == "-122") {
                let err = res.data.validateErrors.fieldErrors[0];
                if (err.field == "kindName") {
                  arr.push(h("span", null, err.message));
                }
								if (err.field == "levelCode") {
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
            err => {}
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
    delSurKind: function() {
      //删除成功后树应该定位到删除节点的父亲
      var point = this.formValue.pid;
      this.axios.post(this.baseURL + "/delete", this.formValue).then(res => {
        if (res.data.code == "0") {
          this.$message({
            message: "操作成功！",
            type: "success"
          });
          this.formValue = {};
					this.selectedLevelCode = "";
					this.selectedKindParent = [];

          this.axios
            .post(this.baseURL + "/getSurgeryKindByHos", {})
            .then(res => {
              this.treeValue = res.data.data.children;
              this.loading = false;

              //this.selectedKindId = point;
              //this.expadoIds = [this.selectedKindId];
              if (res.data.data.children[0].children) {
                this.kindInfo4cascader = res.data.data.children;
              }
            });

					this.addDisFlag = false;
					this.saveDisFlag = false;
					this.delDisFlag = false;	

        } else {
          if (res.data.validateErrors.fieldErrors.length > 0) {
            this.$message.error(res.data.validateErrors.fieldErrors[0]);
          } else {
            this.$message.error(res.data.msg);
          }
        }
      });
    }
  },
  mounted() {
    this.formValue.hosId = this.user.corpId;
    this.getSurKindByHos();
  }
};
</script>

<style>
#surPkgKind-mgr .el-tree-node__label {
  font-size: 12px;
}
#surPkgKind-mgr .el-table {
  font-size: 12px;
}
#surPkgKind-mgr .el-tree {
  min-height: calc(100% - 75px);
}
#surPkgKind-mgr .div4Tree {
  border: 1px solid #d1dbe5;
  height: calc(100% - 5px);
  overflow-y: auto;
}
#surPkgKind-mgr .fullheight {
  height: calc(100% - 75px);
}
</style>