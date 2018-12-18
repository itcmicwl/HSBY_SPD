<template>    
    <el-row :gutter="8" style="height: 100%;" id="admins-config">
        <el-col :span="10" style="height: 100%;">            
            <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                <el-col :span="16">
                    <el-input v-model="filters.cname" placeholder="单位名称" ></el-input>
                </el-col>
                <el-col :span="8">
                    <el-button type="primary" icon="search" v-on:click="loadCompanies(1)" >查询</el-button>
                </el-col>
            </el-col>
                <el-table highlight-current-row  v-loading="loading" border tooltip-effect="dark" style="height:calc(100% - 100px);width: 100%;"
                height="outter" :data="companies"  @row-click="onCom_rowClick">                    
                <el-table-column prop="cname"  label="名称" header-align="center"></el-table-column>
                <el-table-column prop="id"  label="编码" header-align="center" width="60" ></el-table-column>
            </el-table>
                <el-col :span="24" class="toolbar">
                <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange" :current-page="filters.page" 
                :page-sizes="[30, 50, 100, 200]" :page-size="filters.pageSize" 
                layout="total, sizes, prev, pager, next" :total="filters.total" style="float:right;">
                </el-pagination>
            </el-col>
        </el-col>
        <el-col :span="14" style="height: 100%;">
            <el-tabs type="border-card" style="height:calc(100% - 20px);">            
                <el-tab-pane label="公司角色分配">
                    <el-table v-loading="loading" border  style="height:calc(100% - 5px);width: 100%;"
                        :data="allRoles"  ref="corpDefaultRoles"  row-key="id">
                        <el-table-column prop="cname"  label="角色名称" header-align="center" width="150"></el-table-column>                        
                        <el-table-column prop="remark" label="说明" header-align="center"></el-table-column> 
                        <el-table-column label="操作" width="120" header-align="center" >
                            <template slot-scope="scope">
                                <el-button-group >
                                    <el-button v-if="scope.row.flag == '0'" type="primary" size="mini" @click="setCorpDefaultRole(scope.row.id,'yes')" icon="edit"  >分配角色</el-button>
                                    <el-button v-else type="danger" size="mini" @click="setCorpDefaultRole(scope.row.id,'no')" icon="delete" >回收角色</el-button>
                                </el-button-group>
                            </template>
                        </el-table-column>
                    </el-table>	
                </el-tab-pane>
                <el-tab-pane label="用户角色分配">
                    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <orgTreeSlt ref="orgSlter" @change="deptChange"></orgTreeSlt>
                    </el-col>
                    <el-table highlight-current-row  v-loading="loading" border tooltip-effect="dark" style="height:calc(100% - 100px);width: 100%;"
                        height="outter" :data="users">                    
                        <el-table-column prop="userName"  label="用户" header-align="center" width="80"></el-table-column>
                        <el-table-column prop="orgName"  label="部门" header-align="center" width="125" ></el-table-column>
                        <el-table-column prop="roleNames"  label="拥有角色" header-align="center" ></el-table-column>
                        <el-table-column label="操作" width="150">
                            <template slot-scope="scope">
                                <el-button  @click="editRole(scope.row.userId,scope.row.roleIds)" type="text"  width="80">设置角色</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-dialog title="设置用户角色" :visible.sync="d_Roles" >
                        <el-checkbox-group v-model="checkList">
                            <el-col :span="8"  v-for="item in comRoles" :key="item.id" >
                                    <el-checkbox :label="item.id" :key="item.id">{{item.cname}}</el-checkbox>
                            </el-col>
                        </el-checkbox-group>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="d_Roles = false">取 消</el-button>
                            <el-button type="primary" @click="saveRoles" v-loading="d_loading">确 定</el-button>
                        </div>
                    </el-dialog>
                </el-tab-pane>
            </el-tabs>
            
        </el-col>
    </el-row>
</template>
<script>

var axios ;
var baseUrl="";
import orgTreeSlt from '../../components/orgTreeSlt';
export default {        
    data() {
        return {
            filters:{
                cname:'',
                page:1,
                pageSize:30,
                total:0
            },
            userFilters:{
                orgCode:'',
                hosId:''
            },

            companies:[],            
            users:[],
            comRoles:[],
            hosRoles:[],
            checkList:[],
            allRoles:[],
            curUser:'',
            loading: false,
            d_loading:false,
            d_Roles:false
        }
    },
    components:{orgTreeSlt},
    methods: {
        sCurrentChange (val) {
            this.filters.page = val
            this.loadCompanies(this.filters.page);
        },
        sSizeChange (val) {
            this.filters.pageSize = val
            this.loadCompanies(this.page);
        },
        onCom_rowClick(row){
            this.userFilters.orgCode = '';
            this.userFilters.hosId=row.id;
            this.$refs.orgSlter.getOrgInfo(row.id);  
            this.loadUserRoles();  
            this.loadComRoles(this.userFilters.hosId);
        },     
              
        loadCompanies(page){
            let o = this;
            this.filters.page = page;
            var param = {
                orderBy: '',
                page: this.filters.page ||　1,
                rows: this.filters.pageSize,
                queryObject: {
                    cname: this.filters.cname
                }
            };
            axios.post(baseUrl + `/platformService/bas/company/getCompanys`,param).then(res => {
                if (res.data.code == 0) {   //成功
                    if(res.data.data.data.length > 0){
                        o.companies = res.data.data.data;
                        o.selectedCompanyId = res.data.data.data[0].id;
                        this.filters.total = res.data.data.total;
                    }                    
                } else{
                    o.$message.error("数据加载出错:" + res.data.msg);
                }

            }, err => {
                o.logining = false;
                o.$message.error("数据加载出错");
            });
        },
        deptChange (dept) {
            this.userFilters.orgCode = dept.code;
            this.loadUserRoles();
        },     
        loadUserRoles(){
            var o = this;
            var param={
                hosId:this.userFilters.hosId,
                orgCode:this.userFilters.orgCode
            };
            axios.post(baseUrl + '/platformService/permission/userRole/userRoleList/',param).then(rqUsers=>{
                o.logining = false;
                if (rqUsers.data.code == 0) {
                    o.users = rqUsers.data.data;
                }else{
                    o.$message.error(rqUsers.data.msg);
                }  
            },err=>{
                o.logining = false;
                o.$message.error("数据加载出错");
            });
        },
        loadComRoles(comId){
            var o = this;
             o.logining = true;
            axios.get(baseUrl + '/platformService/permission/adminConfig/listCompanyDefaultRolesConfig/'+ comId ).then(res=>{
                 o.logining = false;
                 if(res.data.code == 0){
                     o.allRoles = res.data.data;
                 }
            },err=>{
                 o.logining = false;
            });
        },
        setCorpDefaultRole(roleId,flag) {
            let corpId = this.userFilters.hosId;
            let o = this;
            axios.post(baseUrl + '/platformService/permission/adminConfig/setCompanyDefaultRolesConfig/'+ corpId +'/'+ roleId +'/' + flag ).then(res => {
                if (res.data.code == 0) {   //成功
                    o.loadComRoles(corpId);
                }else{

                }
            }, err => {
                o.logining = false;
            }); 
        },
        editRole(userId,roleIds){
            this.curUser = userId;
            this.checkList = roleIds ? roleIds.split(',') :[];
            var o = this;
            this.d_Roles = true;
            axios.get(`/platformService/permission/userRole/hosUserRoles/${o.userFilters.hosId}`).then(rqUsers=>{
                o.logining = false;
                if (rqUsers.data.code == 0) {
                    o.comRoles = rqUsers.data.data;
                }else{
                    o.$message.error(rqUsers.data.msg);
                }  
            },err=>{
                o.logining = false;
                o.$message.error("数据加载出错");
            });
        },
        saveRoles(){
            var o = this;            
            o.d_loading = true;
            axios.post(`/platformService/permission/userRole/setUserRoles/${o.curUser}`,this.checkList).then(res=>{
                o.d_loading = false;
                if (res.data.code == 0 && res.data.data > 0) {
                    o.d_Roles = false;
                   o.loadUserRoles();
                   o.$message.success("操作成功");
                }else{
                    o.$message.error("操作失败");
                }  
            },err=>{
                o.d_loading = false;
                o.$message.error("数据加载出错");
            });
        }
    },
    mounted() {
        axios = this.axios;
        this.loadCompanies();
    }
};

</script>
<style>
   
    #admins-config .el-tabs__content
    {
        height:calc(100% - 50px);
    }
</style>