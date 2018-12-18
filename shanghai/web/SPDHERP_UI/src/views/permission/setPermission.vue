<template>    
        <el-row :gutter="6" style="height: 100%;" class="p_permission">
            <el-col :span="8" style="height:100%;">
               <el-card class="mycard">
                   <div slot="header" class="clearfix">
                        <span>角色信息</span>
                        <el-button style="float: right; padding: 3px 0" type="primary" @click="onAddNewRole">添加</el-button>
                    </div>
                    <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"  style="width: 100%;height: calc(100% - 10px);"
                                height="outter" :data="corpRoles.data"  ref="corpRoles"  row-key="id" @row-click="onCorpRoles_rowClick">
                        <el-table-column type="index" label="选择" width="50" align="center">
                            <template slot-scope="scope">
                                <span style="color:green" v-if="corpRoles.currentRow.id == scope.row.id">V</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="cname"  label="角色名称" header-align="center" ></el-table-column>                        
                        <el-table-column label="状态" width="50" header-align="center" >
                            <template slot-scope="scope">
                                <span v-if="scope.row.flag != '0'" style="color:green" >有效</span>
                                <span v-else style="color:red" >无效</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="100" header-align="center" >
                            <template slot-scope="scope">
                                <el-button-group >
                                    <el-button type="primary" size="mini" @click="onRoleEdit(scope.row)" icon="edit"  >编辑</el-button> 
                                    <el-button type="danger" size="mini" @click="onRoleDelete(scope.row)" icon="delete"  >删除</el-button> 
                                </el-button-group>
                            </template>
                        </el-table-column>
                    </el-table>                        
               </el-card>
               <el-dialog :title="dialog.title" :visible.sync="dialog.visible" :modal="dialog.modal" :show-close="false">
                    <div >
                    <el-form ref="roleForm" :model="dialog.roleForm" label-width="80px">
                        <el-form-item label="角色名称" class="my-form-item">
                            <el-input @change="dialog.changed=true" v-model="dialog.roleForm.cname"></el-input>
                        </el-form-item>
                        <el-form-item label="编码" class="my-form-item">
                            <el-input @change="dialog.changed=true" v-model="dialog.roleForm.code"></el-input>
                        </el-form-item>
                        <el-form-item label="说明" class="my-form-item">
                            <el-input @change="dialog.changed=true" v-model="dialog.roleForm.remark"></el-input>
                        </el-form-item>
                        <el-form-item label="类型" class="my-form-item">
                            <el-switch  :width="100"  v-model="dialog.roleForm.kind"  active-text="管理员角色" inactive-text="用户角色" active-color="#13ce66" disabled
                                    @change="dialog.changed=true"  inactive-color="#ff4949" active-value="11" inactive-value="12">
                            </el-switch>
                        </el-form-item>
                        <el-form-item label="状态" class="my-form-item">
                            <el-switch  :width="60"  v-model="dialog.roleForm.flag"  active-text="有效" inactive-text="无效" active-color="#13ce66"
                                    @change="dialog.changed=true" inactive-color="#ff4949" active-value="1" inactive-value="0">
                            </el-switch>
                        </el-form-item>
                    </el-form>
                    <b style="color:red;" v-if="dialog.changed">(数据已经改变*)</b>
                    </div>
                    <span slot="footer" class="dialog-footer">
                        <el-button :type="dialog.changed?'danger':'primary'" @click="dialog.onSave(dialog.root)">保存</el-button>
                        <el-button type="primary" @click="dialog.onClose(dialog.root)">关闭</el-button>
                    </span>
                </el-dialog>
            </el-col>
            <el-col :span="8" style="height:100%;">                
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>菜单权限</span>
                    </div>
                    <el-tree :data="menusTree.data" show-checkbox  node-key="id" ref="menuTree"  
                        highlight-current :props="defaultProps" @node-click="treeNodeClick" @check="treeCheck">
                    </el-tree>
                </el-card>
            </el-col>
            <el-col :span="8" style="height:100%;">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>功能权限</span>
                    </div>
                    <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="width: 100%;height:calc(100% - 50px);"
                            height="outter" :data="viewElements.data"   ref="viewElements" @select="onviewElements_select" @select-all="onviewElements_select"
                    >
                        <el-table-column type="selection"  width="42" align="center"></el-table-column>
                        <el-table-column prop="ename"  label="功能名称" header-align="center"></el-table-column>
                        <el-table-column prop="code" label="编码" header-align="center" width="150" ></el-table-column>                                
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
         
</template>
<script>
import {appConfig} from '../../config/appConfigs.js';
var axios ;
var baseUrl="";
var user={};
export default {
    filters:{
    }, 
    data() {
        return { 
            dialog:{
                root:this,
                modal:true,
                visible:false,
                changed:false,
                title:'',
                roleForm:{
                    id:'',
                    code:'',
                    cname:'name val',
                    remark:' remark',
                    flag:'0',
                    version:0
                },
                onSave(o){ 
                    axios.post(baseUrl + '/platformService/permission/roleConfig/updateRole/' ,
                        o.dialog.roleForm ).then(res => {
                        o.loading = true;
                        if (res.data.code == 0) {   //成功
                            o.dialog.changed = false; 
                            o.dialog.visible = false;
                            o.dialog.roleForm = res.data.data;
                            o.loadCompanyRoles(res.data.data);
                            o.$message({
                                message: '成功保存角色的配置信息！',
                                type: 'success'
                            });
                        }else
                        {
                            o.$message.error(res.data.msg);
                        }
                    }, err => {
                        o.loading = false;
                    });                         
                },
                onClose(o){
                    if(o.dialog.changed == true){
                        o.$confirm('发现配置需要保存，是否确定要直接退出当前配置界面?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => { 
                            o.dialog.visible = false;
                            o.dialog.changed = false; 
                        }).catch(() => { 
                        });
                    }else
                        o.dialog.visible = false;
                }
            },
            corpId:'',
            corpName:'',
            corpRoles:{
                data:[],
                currentRow:{}
            },
            menusTree:{
                currentNode:'',
                data:[],
                defaultExpandedKeys:[],
                defaultCheckedKeys:[] 
            },
            viewElements:{
                checking:false,
                data:[]
            },
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            loading: false
        }
    },
    methods: {            
        onAddNewRole(){               
            let row = {id:'0',code:'',companyId:this.corpId,kind:'12',cname:'新增角色1',remark:'新增角色',roleTemplateId:'',flag:'1',version:0};
            this.dialog.title = "新增角色1" ;
            this.dialog.roleForm = row;
            this.dialog.changed = false;
            this.dialog.visible=true;
        },
        onRoleEdit(row){
            this.dialog.title = "编辑角色：" + row.cname;
            this.dialog.roleForm = row;
            this.dialog.visible=true;
            this.dialog.changed = false;
        },
        onRoleDelete(row){
            let o = this;
            o.loading = true;
            function cb(row)
            {
                o.loadCompanyRoles();
            }
            axios.post(baseUrl + '/platformService/permission/roleConfig/deleteRole/',row).then(res => {
                o.loading = false;
                if (res.data.code == 0) {   //成功
                    cb(row);
                    o.$message({
                        message: '成功删除角色以及相关的配置信息！',
                        type: 'success'
                    });
                }else{
                    o.$message.error(res.data.msg);
                }
            }, err => {
                o.loading = false;
                o.$message.error("数据加载出错");
            });
        },
        onviewElements_select(selection, row){
            let o = this;
            if(o.viewElements.checking)
                return;

            //let menuCodeId = o.menusTree.currentNode.id;
            let menuId = o.$refs.menuTree.getCurrentKey();
            let funcIds = [];
            selection.forEach((item)=>{ 
                if(item.id)
                funcIds.push(item.id);
            });
            o.$refs.menuTree.setChecked(menuId,funcIds.length > 0);
            // if(funcIds.length == 0)
            // {
            //     o.menusTree.currentNode.checked = false;
            //     // o.$refs.menusTree.setChecked(o.menusTree.currentNode,false); 
            // }else{
            //     o.menusTree.currentNode.checked = true;
            //     // o.$refs.menusTree.setChecked(o.menusTree.currentNode,true); 
            // }
            o.saveElements(menuId,funcIds);

        }, 
        onCorpRoles_rowClick(row, event, column){
            this.corpRoles.currentRow = row;
            this.loadRoleOwnMenuIds();
        }, 
        saveElements(menuId,elementIds){
            let o = this;
            let roleId = o.corpRoles.currentRow.id;
            o.loading = true;

            o.axios.post(baseUrl + `/platformService/permission/roleConfig/saveMenuPermission/` +roleId ,{menuId:menuId,elementIds:elementIds}).then(res => {
                o.loading =false;
                if (res.data.code == 0) {   //成功
                    //o.loadRoleOwnMenuIds();
                } else{
                    o.$message.error(res.data.msg);
                }

            }, err => {
                o.logining = false;
                o.$message.error("数据加载出错");
            });
        },           
        loadCompanyRoles(selectedRole){
            let o = this;
            let corpId = this.corpId;
            if(!corpId)
                return ;
            o.loading = true;
            function cb(roles){
                o.corpRoles.data = roles;
                if(roles.length > 0 ){
                    let sr = null;
                    if(selectedRole)
                    { 
                        roles.forEach((role)=>{
                            if(role.id == selectedRole.id)
                                sr = role;
                        });
                    }
                    if(sr == null){
                        o.corpRoles.currentRow = o.corpRoles.data[0];
                    }else
                        o.corpRoles.currentRow = sr;
                    o.$refs.corpRoles.setCurrentRow(sr);
                    o.loadRoleOwnMenuIds();
                }
            }
            axios.post(baseUrl + '/platformService/permission/roleConfig/getCompanyAllRole/'+ corpId).then(res => {
                o.loading = false;
                if (res.data.code == 0) {   //成功
                    let roles = res.data.data;
                    cb(roles);
                }else{
                    o.$message.error(res.data.msg);
                }
            }, err => {
                o.loading = false;
                o.$message.error("数据加载出错");
            });
        },
        loadRoleOwnMenuIds(){
            let o = this;
            let roleId = o.corpRoles.currentRow.id;
            o.$refs.menuTree.setCheckedKeys([]);
            o.loading = true;
            axios.post(baseUrl + '/platformService/permission/roleConfig/getRoleMenuIds/'+ roleId).then(res => {
                o.loading = false;
                if (res.data.code == 0) {   //成功
                    let menuIds = res.data.data ;
                     o.$refs.menuTree.setCheckedKeys(menuIds);
                } else{
                    o.$message.error(res.data.msg);
                }
            }, err => {
                o.loading = false;
                o.$message.error("数据加载出错");
            });
        },
        treeNodeClick(){
            this.loadMenuElements();
        },
        loadMenuElements(){
            let o = this;
            let menuId = o.$refs.menuTree.getCurrentKey();
            let roleId = o.corpRoles.currentRow.id;
            if(!menuId || !roleId){
                return;
            }
            function cb(viewElements,viewElementPermits){
                if(viewElements == null){
                    o.viewElements.data = [];
                    return;
                }
                o.viewElements.data =viewElements.elements;

                if(!viewElementPermits || !viewElementPermits.menuId || viewElementPermits.elementIds.length ==0)
                    return;
                setTimeout(function(){
                    o.viewElements.checking = true;
                    
                    o.viewElements.data.forEach((row)=>{
                        viewElementPermits.elementIds.forEach((id)=>{
                            if(row.id == id)
                                o.$refs.viewElements.toggleRowSelection(row,true);
                        });
                    });
                    
                    o.viewElements.checking = false;
                },100);
            }
            let getPageviewElements =  axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getPageviewElements/'+ menuId);
            let getRoleMenuElementsPermits = axios.post(baseUrl + '/platformService/permission/roleConfig/getRoleMenuElementsPermits/'+ menuId +'/'+ roleId );
            o.loading = true;
            axios.all([getPageviewElements,getRoleMenuElementsPermits]).then(axios.spread(function (rqElements, rqPermits) {
                o.loading = false;
                let viewElements;
                let viewElementPermits;
                if (rqElements.data.code == 0) {   //成功
                    viewElements = rqElements.data.data;                        
                } else{
                    o.$message.error(rqElements.data.msg);
                    return;
                }
                if (rqPermits.data.code == 0) {   //成功
                    viewElementPermits = rqPermits.data.data;
                } else{
                    o.$message.error(rqPermits.data.msg);
                    return ;
                }
                cb(viewElements,viewElementPermits);

            }),err => {
                o.loading = false;
                o.$message.error("数据加载出错");
            });
        },
        loadMenu(){
            var _this = this;
            var serviceUrl = `/platformService/permission/menuPageviewItemsConfig/getUserMenusByProjectCode/${appConfig.appCode}/${this.user.userId}`;
            this.axios.get(serviceUrl).then(res => {
                if(res.data.code=="0"){
                    var arrMenu = res.data.data.map(o=>{
                        var menu ={id:o.id,code:o.code,pcode:o.pcode,name:o.cname,menuLevel:o.menuLevel };
                        return menu;
                    });
                    _this.buildMenuTree(arrMenu);
                }
                
            }, err => {});
        },
        buildMenuTree(menuData){
            var topMenus=menuData.filter(o=>{return o.menuLevel == 0});
            topMenus.forEach(item=>{
                item.children = menuData.filter(o=>{return o.menuLevel == 1 && o.pcode==item.code});
                item.children.forEach(citem=>{
                    citem.children = menuData.filter(o=>{return o.menuLevel == 2 && o.pcode==citem.code});
                });
            });
            this.menusTree.data = topMenus;
        },       
        formatter(row,column){
            return row[column.property];
        },        
        treeCheck(d1,d2){
            var _this = this;
            let isChecked =this.tools._.contains(d2.checkedKeys,d1.id);
            let ids =[];
            if(d1.menuLevel == 0){
                d1.children.forEach(item=>{
                    ids = ids.concat(item.children.map(o=>{return o.id}));
                })
            }else if(d1.menuLevel==1){
                ids =ids.concat(d1.children.map(o=>{return o.id}));
            }else if(d1.menuLevel ==2){
                ids = [d1.id];
            }
            let roleId = this.corpRoles.currentRow.id;
            let url = "/platformService/permission/roleConfig/" + (isChecked ?"saveRoleMenuPermissions":"deleteRoleMenuPermissions") + "/" + roleId;
             this.loading = true;
                this.axios.post(baseUrl + url,ids).then(res => {
                    _this.loading = false;
                    if (res.data.code == 0) {   //成功
                       //cb();
                    } else{
                        _this.$message.error(res.data.msg);
                    }
                }, err => {
                   _this.loading = false;
                    _this.$message.error("数据处理出错");
                });
        }
    },
    mounted() {
        user = this.user;
        axios = this.axios;
        this.corpId = user.corpId;
        this.corpName = user.corpName;
        this.loadMenu();
        this.loadCompanyRoles();
    }
};

</script>
<style>
 #p_permission .mycard{
     height: 100%;
 }
</style>