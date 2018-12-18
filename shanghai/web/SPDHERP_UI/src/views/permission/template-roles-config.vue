<template>    
    <div id="template-roles-config" style="height: 100%;padding:0 3px;">        
        <el-row :gutter="6" style="height: calc(100% - 6px);">
            <el-col :span="10" style="height:calc(100% - 0px);">
                 <el-card class="mycard">
                    <div slot="header" class="clearfix">
                        <span>角色模板</span>
                        <el-button style="float: right; padding: 3px 0" type="primary" @click="onAddNewRole">添加</el-button>
                    </div>
                    <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"  style="height:calc(100% - 10px);width: 100%;"
                                height="outter" :data="corpRoles.data"  ref="corpRoles"  row-key="id" @row-click="onCorpRoles_rowClick">
                        <el-table-column prop="cname"  label="角色名称" header-align="center" width="150"></el-table-column>
                        <el-table-column label="类型" header-align="center" >
                            <template slot-scope="scope"> 
                                <span style="color:red" v-if="scope.row.kind == '01'" >管理员角色</span>
                                <span style="color:green" v-else-if="scope.row.kind == '02'" >用户角色</span>                            
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" width="50" header-align="center" >
                            <template slot-scope="scope">
                                <span v-if="scope.row.flag != '0'" style="color:green" >有效</span>
                                <span v-else style="color:red" >无效</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150" header-align="center" >
                            <template slot-scope="scope">
                                <el-button-group >
                                    <el-button type="primary" size="mini" @click="onRoleEdit(scope.row)" icon="edit"  >编辑</el-button> 
                                    <el-button type="danger" size="mini" @click="onRoleDelete(scope.row)" icon="delete"  >删除</el-button> 
                                </el-button-group>
                            </template>
                        </el-table-column>
                    </el-table>	
                </el-card>
            </el-col>
            <el-col :span="6" style="height: 100%;">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>菜单权限</span>
                    </div>
                    <el-tree :data="menusTree.data" show-checkbox  node-key="id" ref="menuTree"  
                        highlight-current :props="defaultProps" @node-click="treeNodeClick" @check="treeCheck">
                    </el-tree>
                </el-card>
            </el-col>
            <el-col :span="8" style="height:calc(100% - 0px);">
               <el-card>
                    <div slot="header" class="clearfix">
                        <span>功能权限</span>
                    </div>
                    <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="height:calc(100% - 50px);width: 100%;"
                            height="outter" :data="viewElements.data"   ref="viewElements" @select="onviewElements_select" @select-all="onviewElements_select"
                    >
                        <el-table-column type="selection"  width="42" align="center"></el-table-column>
                        <el-table-column prop="ename"  label="功能名称" header-align="center" width="150"></el-table-column>
                        <el-table-column prop="code" label="编码" header-align="center" width="150" ></el-table-column>                                
                    </el-table>
                </el-card>
            </el-col>
        </el-row>     
        <el-dialog :title="dialog.title" :visible.sync="dialog.visible" :modal="dialog.modal" :show-close="false"
            size="tiny"  >
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
                    <el-switch  :width="100"  v-model="dialog.roleForm.kind"  active-text="管理员角色" inactive-text="用户角色" active-color="#13ce66"
                              @change="dialog.changed=true"  inactive-color="#ff4949" active-value="01" inactive-value="02">
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
                <el-button :type="dialog.changed?'danger':'primary'"  @click="dialog.onSave(dialog.root)">保存</el-button>
                <el-button type="primary" @click="dialog.onClose(dialog.root)">关闭</el-button>
            </span>
        </el-dialog> 
    </div>
</template>
<script>
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
                        o.loading = true;
                        axios.post(baseUrl + '/platformService/permission/roleConfig/updateRole/' ,
                            o.dialog.roleForm ).then(res => {
                            o.loading = false;
                            if (res.data.code == 0) {   //成功
                                o.dialog.roleForm = res.data.data;
                                o.loadCompanyRoles(res.data.data);
                                o.$message({
                                    message: '成功保存角色的配置信息！',
                                    type: 'success'
                                });
                                o.dialog.changed = false;
                                o.dialog.visible = false;
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
                companies:{
                    data:[],
                    selectedCompanyName:'',
                    selectedCompanyId:'',
                },
                corpRoles:{
                    data:[],
                    curId:null
                },                
                viewElements:{
                    checking:false,
                    data:[]
                },
                menusTree:{
                    currentNode:'',
                    data:[],
                    defaultExpandedKeys:[],
                    defaultCheckedKeys:[] 
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
                let row = {id:'0',code:'',companyId:'0',kind:'02',cname:'新增角色1',remark:'新增角色',roleTemplateId:'',flag:'1',version:0};
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
                        o.$message.error("数据处理出错:" + res.data.msg);
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
                let menuId = o.$refs.menuTree.getCurrentKey();
                let funcIds = [];
                selection.forEach((item)=>{ 
                    if(item.id)
                    funcIds.push(item.id);
                });
                o.$refs.menuTree.setChecked(menuId,funcIds.length > 0);            
                o.saveElements(menuId,funcIds);

            }, 
            onCorpRoles_rowClick(row, event, column){
                this.corpRoles.curId = row.id;
                this.loadRoleOwnMenuIds(row.id);
            },             
            saveElements(menuId,elementIds){
                let o = this;
                let roleId = o.corpRoles.curId;
                o.loading = true;

                axios.post(baseUrl + `/platformService/permission/roleConfig/saveMenuPermission/` +roleId ,{menuId:menuId,elementIds:elementIds}).then(res => {
                    o.loading =false;
                    if (res.data.code == 0) {   //成功
                        //o.loadRoleOwnMenuIds();
                    } else{
                        o.$message.error("数据加载出错:" + res.data.msg);
                    }

                }, err => {
                    o.logining = false;
                    o.$message.error("数据加载出错");
                });
            },           
            loadCompanyRoles(selectedRole){
                let o = this;
                o.loading = true;               
               axios.post(baseUrl + '/platformService/permission/roleConfig/getCompanyAllRole/0').then(res => {
                   o.loading = false;
                    if (res.data.code == 0) {   //成功                        
                        if(res.data.data.length>0){
                            o.corpRoles.data =  res.data.data;
                        }                        
                    }else{
                        o.$message.error("数据加载出错:" + res.data.msg);
                    }
                }, err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadRoleOwnMenuIds(roleId){
                let o = this;
                o.$refs.menuTree.setCheckedKeys([]);                
                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/roleConfig/getRoleMenuIds/'+ roleId).then(res => {
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        let menuIds = res.data.data ;
                        o.$refs.menuTree.setCheckedKeys(menuIds);
                    }else{
                        o.$message.error("数据加载出错:" + res.data.msg);
                    } 
                }, err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadMenuElements(){
                let o = this;
                let menuId =  o.$refs.menuTree.getCurrentKey();
                let roleId = o.corpRoles.curId;
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
                        o.$message.error("数据加载出错:" + rqElements.data.msg);
                        return;
                    }
                    if (rqPermits.data.code == 0) {   //成功
                        viewElementPermits = rqPermits.data.data;
                    } else{
                        o.$message.error("数据加载出错:" + rqPermits.data.msg);
                        return ;
                    }
                    cb(viewElements,viewElementPermits);

                }),err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            treeNodeClick(){
                this.loadMenuElements();
            },
            loadMenu(){
                var _this = this;
                var serviceUrl = `/platformService/permission/adminConfig/listMenu`;
                this.axios.post(serviceUrl).then(res => {
                    if(res.data.code=="0"){
                        var arrMenu = res.data.data.map(o=>{
                            var menu ={id:o.id,code:o.code,pcode:o.pcode,name:o.cname,menuLevel:o.menuLevel,proCode:o.projectCode,proName:o.projectName };
                            return menu;
                        });
                        _this.buildMenuTree(arrMenu);
                    }
                    
                }, err => {});
            },
            buildMenuTree(menuData){
                var _this = this;
                var topMenus=menuData.filter(o=>{return o.menuLevel == 0});
                topMenus.forEach(item=>{
                    item.children = menuData.filter(o=>{return o.menuLevel == 1 && o.pcode==item.code});
                    item.children.forEach(citem=>{
                        citem.children = menuData.filter(o=>{return o.menuLevel == 2 && o.pcode==citem.code});
                    });
                });
                let projects = this.tools._.groupBy(topMenus,"proCode");
                for(var item in projects){
                    var pro = {
                        id:item,
                        code:item,
                        pcode:'-1',
                        menuLevel:-1,
                        name: projects[item][0].proName,
                        children: projects[item]
                    }
                    this.menusTree.data.push(pro);
                }
            },                      
            formatter(row,column){
                return row[column.property];
            },
            treeCheck(d1,d2){
                var _this = this;
                let isChecked =this.tools._.contains(d2.checkedKeys,d1.id);
                let ids =[];
                let roleId = this.corpRoles.curId;
                if(!roleId){
                    _this.$message.error("请选择一个角色");
                    return false;
                }
                if(d1.menuLevel == -1){
                   d1.children.forEach(item=>{
                       item.children.forEach(citem=>{
                           ids = ids.concat(citem.children.map(o=>{return o.id}));
                       });                        
                    });
                }else if(d1.menuLevel == 0){
                    d1.children.forEach(item=>{
                        ids = ids.concat(item.children.map(o=>{return o.id}));
                    })
                }else if(d1.menuLevel==1){
                    ids =ids.concat(d1.children.map(o=>{return o.id}));
                }else if(d1.menuLevel ==2){
                    ids = [d1.id];
                }
                let url = "/platformService/permission/roleConfig/" + (isChecked ?"saveRoleMenuPermissions":"deleteRoleMenuPermissions") + "/" + roleId;
                this.loading = true;
                    this.axios.post(baseUrl + url,ids).then(res => {
                        _this.loading = false;
                        if (res.data.code == 0) {   //成功
                            _this.$message.success("操作成功");
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
            this.loadMenu();
            this.loadCompanyRoles();
        }
    };

</script>
<style>
   /* .tableheight {        
        height:  calc(100% - 55px);
    }*/
    *{
        font-size:12px;
    }
    #template-roles-config .el-tree--highlight-current .el-tree-node.is-current>.el-tree-node__content {
        background-color: #8cb8d8;
    }
    #template-roles-config .el-select-dropdown__item.selected{
        
    }
    #template-roles-config .el-table .cell, .el-table th>div {
        padding-left: 10px;
        padding-right: 10px;
    }
    #template-roles-config .admin-flag{
        text-align:center;
        width: 100%;
        display: inline-block; 
        color:red;
    }   
    #template-roles-config .my-select{
        height:32px;
        
    }
    #template-roles-config .el-tree-node__label {
        font-size: 12px;
    }
    #template-roles-config .el-tree {
        border-radius: 4px;
    }
    #template-roles-config .el-tabs__item 
    {
        font-size: 12px;
    }
    /*.el-dialog__header {
        padding: 10px;
    }
    .el-dialog__footer {
        padding: 10px 20px 10px;   
    }*/
    #template-roles-config .my-dialog-body
    {
        padding: 15px;
         /* width:1000px; */
         border:1px solid grey;
         border-radius: 4px;
    }
    /*.el-dialog__body {
        padding: 10px 20px;
        height:280px;
        border: 1px solid lightgray;
    }*/
    #template-roles-config .my-dialog-body .label{
        padding:0 0 4px;
        
    }
    #template-roles-config .my-dialog-body .label *{
        font-size:14px;       
    }
    #template-roles-config .my-col
    {
        padding:2px;
        border: 0px solid lightgray;
        border-radius: 4px;        
        /* height: 550px; */
    }
    #template-roles-config .my-label
    {
        padding:10px;        
    }
    #template-roles-config  .my-label span{
        font-size:14px;
    }
    #template-roles-config .tree-outerline
    {
        border: 0px solid lightgray;
        border-radius: 4px;
        padding:5px 0;
    }
    /*.my-tree{
        border: 0px solid lightgray;
    }*/
    #template-roles-config .my-form-item
    {
        margin-bottom: 16px;
    }
    #template-roles-config .dialog-title
    {
        display:inline-block;
        font-size:16px;
        margin: 0 0 10px;
    }
    #template-roles-config .my-node-icon{
        color: #1d6ce6;
    }
    #template-roles-config .my-node-summary{
        display: inline-block;
        margin-left:5px; 
        color: green;
    }
    #template-roles-config .my-check{
        display: inline-block;
        width: 15px;
        height: 11px;
        border: 1px solid #1d90e6;
        border-radius: 4px;
        padding: 0 2px 6px;
        text-align: center;
        line-height: 18px;
        margin-right: 2px;
   }
   #template-roles-config .my-treenode-label{
       display: inline-block;
   }
</style>