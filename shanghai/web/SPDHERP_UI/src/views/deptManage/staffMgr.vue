<template>
  <div id="staff-mgr" style="height: 100%;">
	<el-row height="auto" style="height: 99%;">
				<!--工具条  职员管理-->
				<el-col :span="6" style="height: 99%;">
				<div style="padding-bottom: 7px">
						<el-input v-model="filters.orgName" placeholder="请输入机构名称"  
						    icon="search" :on-icon-click="searchOrgs"   >
						 </el-input>
				</div> 
			
					<el-tree :data="orgInfo" 
								node-key="id"
								:default-expanded-keys="expadoIds"
								:props="defaultProps"
								:highlight-current="true"
								@node-click="handleNodeClick">
					</el-tree>
				
				</el-col>
				<el-col :span="18" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
				    <div style="padding-bottom: 5px;">
						<el-form :inline="true" label-width="80px">
							<el-form-item label="">
								<el-input v-model="filters.ename" placeholder="请输入姓名" ></el-input>
							</el-form-item>
							<!--<el-form-item>-->
								<!--<el-button type="primary" v-on:click="getUser(1)" >查询</el-button>-->
							<!--</el-form-item>-->
							<!--<el-form-item>-->
								<!--<el-button type="primary" @click="userEdit(null)" >新增</el-button>-->
							<!--</el-form-item>-->
							<el-button-group>
								<el-button type="primary" v-on:click="getUser(1)" >查询</el-button>
								<el-button type="success" @click="userEdit(null)" >新增</el-button>
							</el-button-group>
						</el-form>
				   </div>
					<!--列表-->
		
					
						<el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" border fit    class="staffMgr-tableheight">
							
							<el-table-column prop="ename" label="姓名" sortable>
							</el-table-column>
							<el-table-column prop="orgName" label="所属机构" min-width="150" sortable>
							</el-table-column>
							<el-table-column prop="tel" label="电话" sortable>
							</el-table-column>
							<el-table-column prop="job" label="职务" sortable>
							</el-table-column>
							<el-table-column label="状态" width="150">
								<template slot-scope="scope">
									<el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949" active-text="启用" inactive-text="停用" active-value="1" inactive-value="0"  @change="userChange(scope.row)">
									</el-switch>
								</template>
							</el-table-column>
							<el-table-column label="操作" width="150">
								<template slot-scope="scope">
									<el-button  @click="userEdit(scope.row)" type="text">编辑</el-button>
									<el-button  @click="initPassword(scope.row)" type="text">重置密码</el-button>
								</template>
							</el-table-column>
						</el-table>
						<!--工具条-->
						<!--
						<el-col :span="24" class="toolbar">
							<el-pagination  layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
							</el-pagination>
						</el-col>
						-->
						<el-col :span="24" class="toolbar">
							<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 100, 200, 400]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
							</el-pagination>
						</el-col>
				
				</el-col>
					<!--编辑界面-->
				<el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" >
						<el-form :model="user" label-width="80px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
							<el-row>
								<el-col :span="12">
									<el-form-item label="账号" prop="userCode"  :error="errors.userCodeErr">
										<el-input v-model="user.userCode" placeholder="请输入用户账号" ></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="姓名">
										<el-input v-model="user.ename"  placeholder="请输入用户账号"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="12">
									<el-form-item label="职务">
										<el-input placeholder="请输入职务信息" v-model="user.job" ></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="机构" prop="orgCode"  :error="errors.orgCodeErr">
										<el-cascader placeholder="请选择机构"
										v-model="selectedOrg"  
										:show-all-levels="false" 
										:options="orgInfo4cascader" 
										filterable 
										:change-on-select="true"  
										:props="orgSltProps">
										</el-cascader>							
									</el-form-item>
								</el-col>
							</el-row>
							
							<el-form-item label="别名" v-show="false">
								<el-input v-model="user.cname"  placeholder="请输入用户别名"></el-input>
							</el-form-item>
							<!--
							<el-form-item label="类型">
								<el-select v-model="user.kind" placeholder="请选择" >
									<el-option key="1" label="类型1" value="1"></el-option>
									<el-option key="2" label="类型2" value="2"></el-option>
								</el-select>
							</el-form-item>
							-->
							
							<el-row>
								<el-col :span="12">
									<el-form-item label="手机号">
										<el-input v-model="user.tel"  placeholder="请输入手机号"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="电子邮件">
										<el-input v-model="user.email"  placeholder="请输入电子邮件"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="12">
								<el-form-item label="内部编码">
									<el-input v-model="user.erpCode"  placeholder=""></el-input>
								</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="SCM编码">
										<el-input v-model="user.scmCode"  placeholder=""></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="12">
									<el-form-item label="QQ">
										<el-input v-model="user.qq"  placeholder="请输入QQ账号"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="显示排序">
										<el-input-number v-model="user.ordinal" :min="1" :max="100" ></el-input-number>
									</el-form-item>
								</el-col>
							</el-row>
							
							
							<el-form-item label="备注">
								<el-input type="textarea" v-model="user.others"  placeholder="备注信息"></el-input>
							</el-form-item>
							
							<el-form-item label="启用/停用">
								<el-switch v-model="user.status" active-color="#13ce66" inactive-color="#ff4949" active-text="启用" inactive-text="停用" active-value="1" inactive-value="0">
								</el-switch>
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
		
				unit:{},
			
				filters: {
					ename: '',
					orgCode: '',
					orgId: '',
					orgName:''
				},
				expadoIds:[],
				total: 0,
				page: 1,
				pageSize: 20,
				loading: false,
				users: [
				],
				
				user: {
					kind: "1",
					orgCode: "",
					orgId: "",
					status: "1"
				},
				modelTitle: "添加用户",
				editFormVisible: false,
				editLoading: false,
				editFormRules: {
					userCode: [
						{ required: true, message: '请输入账号', trigger: 'blur' },{ min: 6,max: 20,message: '长度在 6 到 20 个字符'}, {pattern: /^(\w){6,20}$/,message: '只能输入6-20个字母、数字、下划线'}
					],
					orgCode: [
						{ required: true, message: '请选择机构', trigger: 'blur' }
					]
				},
			
			
				orgInfo: [],
				defaultProps: {
						children: 'children',
						label: 'label',
						id:'id',
						pid:'pid'
					},
				orgInfo4cascader:[],
				orgSltProps: {
					value: 'code',
					label: 'label'
				},
				errors: {
					userCodeErr:'',
					orgCodeErr:''
				}
			
			}
		},
		computed: {
			selectedOrg: {
			
				get: function () {
					let res = [];
						if(this.filters.orgCode){
							let userCodes = this.filters.orgCode.split('.');
							if (userCodes.length > 1) {
								var i = 0;
								for (i; i < userCodes.length; i++) {
									let tempArr = userCodes.slice(0, i + 1);
									res.push(tempArr.join('.'));
								}
							} else {
								res = userCodes;
						}
						}
						if (this.user.orgCode) {  //通过当前点击的orgId 的orgCode 找到其父code
							let orgCodes = this.user.orgCode.split('.');
							if (orgCodes.length > 1) {
								var i = 0;
								for (i; i < orgCodes.length; i++) {
									let tempArr = orgCodes.slice(0, i + 1);
									res.push(tempArr.join('.'));
								
								}
								
							} else {
								res = orgCodes;
							}
						}
				
					return res;
				},
				set: function (orgCodeArr) {
					let res = "";
					if (orgCodeArr.length > 0) {
						res = orgCodeArr[orgCodeArr.length - 1];
					}
					this.user.orgCode = res;
				}

			}
		},
		methods: {

			//获取用户列表
			getUser: function (pIndex) {
				this.page = pIndex;
				var param = { orderBy: "", queryObject: { ename: this.filters.ename, orgId: this.filters.orgId }, page: this.page, rows: 20 };
				this.loading = true;
				this.axios.post(`/platformService/sys/user/userlistByUnit`, param).then(res => {
					this.loading = false;
					this.users = res.data.data.data;
					this.total = res.data.data.total;
				//	this.page = res.data.data.pageNum;
				}, err => {
					this.loading = false;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getUser(this.page);
			},
			handleSizeChange(val) {
					this.pageSize = val;
					this.getUser(this.page);
			},
			userEdit(item) {
				this.errors.userCodeErr='';
				this.errors.orgCodeErr ='';
				this.editFormVisible = true;
			
				var param = {};
					this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {
						corpId: this.unit.id,
						corpCname: this.unit.cname
					}).then(res => {
						this.orgInfo4cascader = res.data.data.children[0].children;
					});
				if (item) {
					this.user = Object.assign({}, item);
					this.modelTitle = "修改用户";
				} else {
					this.user = { kind: "1", orgCode: "", status: "1" };
					this.modelTitle = "添加用户";
				 //	this.user.orgCode
				}
				 //  this.$refs.editForm.resetFields();    

			},
			initPassword(user){
					this.loading = true;
					this.axios.get("/platformService/sys/login/initPassword/" + user.userCode).then(res => {
						this.loading = false;
						if (res.data.code == "0") {
							this.loading = false;
							this.$msgbox({title:'操作成功', message: '新密码为：'+ res.data.data, type: 'success' });
						} else {
							this.$message.error('操作失败！' + res.data.msg);						
						}
					}, err => {
						this.loading = false;
					});
			},
			userChange(user) {
				this.loading = true;
				this.axios.post("/platformService/sys/user/changeStatus", user).then(res => {
					this.loading = false;
					if (res.data.code == "0") {
						this.loading = false;
						this.$message({ message: '操作成功！', type: 'success' });
					} else {
						this.$message.error('操作失败！');
						user.status = user.status == "1" ? "0" : "1";
					}
				}, err => {
					this.loading = false;
				});
			},
			editSubmit: function () {
				if(!this.user.orgCode)
							this.user.orgCode= this.filters.orgCode
				this.errors.userCodeErr='';
				var _this = this;
					var form =  this.$refs.editForm;
					const h = this.$createElement;
					var arr=new Array();
				form.validate((valid) => {
					if (valid) {
						
						this.editLoading = true;
						let serviceUrl = this.user.userId ? "/platformService/sys/user/update" : "/platformService/sys/user/insert";
						this.axios.post(serviceUrl, this.user).then(res => {
							this.editLoading = false;
							if (res.data.code == "0") {
								this.$message({ message: '操作成功！', type: 'success' });
								this.editFormVisible = false;
								this.getUser(1);
							}else if (res.data.code == "-122") {
										let err = res.data.validateErrors.fieldErrors[0];
											if (err.field == "userCode") {
										
											this.errors.userCodeErr += err.message;
											arr.push(h('span',null,err.message));
											}
											_this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
									} 
							
							else {
								this.$message.error('操作失败！');
							}
						}, err => {
							this.editLoading = false;
						});
					}else{
					
							var fields = form.fields;
							var msg ="";
						fields.forEach(f=>{
							if(f.validateState =="error"){
							
								msg +="</br>"+f.validateMessage;
								if (arr.length>0) arr.push(h('br',null,null));
								arr.push(h('span',null,f.validateMessage));
							}
						});
						_this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
						}
				});
			},
			handleNodeClick(data) {
				if(data.id.indexOf('org')==-1){
	               this.filters.orgId = null;
				}
				else     this.filters.orgId = data.id;
			
				this.filters.orgCode= data.code;
				this.getUser(1);
			},
	
			getOrgsByUnit:function (unitId) {
				var param = {};
				this.loading = true;
				this.axios.post(`/platformService/sys/org/getOrgsByUnit`, { }).then(res => {
					this.orgInfo = res.data.data.children;
					this.expadoIds=[this.orgInfo[0].id];
					this.loading = false;
				});
			},
			searchOrgs:function () {
			
				var p = this.filters.orgName;
				let param = {cname:p};
				this.loading=true;
				this.axios.post(`/platformService/sys/org/searchOrgs`, param).then(res=>{
					var ids=[];
					for(var i = 0;i < res.data.data.length; i++) {
						ids[i] =res.data.data[i].id;
					}
					
					this.expadoIds= ids;
				
				});
					this.loading=false;
			}
		},
		mounted() {
			this.getUser(1);
			this.getOrgsByUnit();
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
#staff-mgr .el-table__body-wrapper{
    overflow-x: hidden;
}
#staff-mgr .staffMgr-tableheight{
	  height:  calc(100% - 60px);
}
</style>