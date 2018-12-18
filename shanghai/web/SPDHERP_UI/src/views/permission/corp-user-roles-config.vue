
<template>    
    <div id="corp-user-roles-config" style="height: 100%;padding:0 3px;">
        <el-row :gutter="6" style="height: 100%;">
        <el-col :span="4" style="height: 100%;">
            <div class="my-label">
                <span>操作员用户角色维护</span>
            </div>
            <el-tree ref="orgTree" :data="orgTree.data" :props="defaultProps" @node-click="onClickOrgNode" node-key="id" 
                :default-expanded-keys="orgTree.defaultExpandedKeys" highlight-current style="height:calc(100% - 50px);overflow:auto">
            </el-tree>
        </el-col>
        <el-col :span="10" style="height: 100%;">
            <div class="my-label">
                <span > [{{orgPaths}}]用户列表</span>
            </div>

            <el-table highlight-current-row  v-loading="loading" border tooltip-effect="dark"    style="height:calc(100% - 50px);width: 100%;"
                height="outter" :data="users.data"  ref="users" @row-click="onUsers_rowClick" >
                <el-table-column type="index" label="选择" width="45" align="center">
                    <template slot-scope="scope">
                        <span style="color:green" v-if="users.current.id == scope.row.id">V</span>
                    </template>
                </el-table-column>
                <el-table-column prop="userCode"  label="用户编码" header-align="center" width="100"></el-table-column>
                <el-table-column prop="ename"  label="用户名称" header-align="center" width="120" ></el-table-column>
                <el-table-column prop="orgName" header-align="center" label="机构名称"  width="200"></el-table-column>
                <!-- <el-table-column label="操作" width="120" header-align="center" >
                    <template slot-scope="scope">
                        <el-button-group >
                            <el-button type="primary" size="mini" @click="onQueryUserRight(scope.row)" icon="search"  >查询权限</el-button>
                        </el-button-group>
                    </template>
                </el-table-column> -->
            </el-table>	
        </el-col>
        <el-col :span="10" style="height: 100%;">
            <div class="my-label">
                <span >用户角色分配列表</span>
            </div>
            <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="height:calc(100% - 50px);width: 100%;"
                       height="outter" :data="roles.data"  ref="roles"  row-key="id" 
                        >
                <el-table-column type="index" label="选择" width="45" align="center">
                    <template slot-scope="scope">
                        <span style="color:green" v-if="findArray(roles.selection,scope.row) >=0 ">V</span>
                    </template>
                </el-table-column>
                <el-table-column prop="cname"  label="角色名称" header-align="center" width="150"></el-table-column>                
                <el-table-column label="操作" width="120" header-align="center" >
                    <template slot-scope="scope">
                        <el-button-group >
                            <el-button v-if="findArray(roles.selection,scope.row) >=0 " type="danger" size="mini" @click="onRolesSet(scope.row,'no')" icon="delete"  >回收角色</el-button>
                            <el-button v-else type="primary" size="mini" @click="onRolesSet(scope.row,'yes')" icon="edit" >分配角色</el-button>
                        </el-button-group>
                    </template>
                </el-table-column>
            </el-table>	
        </el-col>
        </el-row>

        <el-dialog :title="dialog.title" :visible.sync="dialog.visible" ref="dialog" :modal="dialog.modal" :show-close="true"
              >

            <el-row :gutter="6">
            <el-col :span="10">
                <div class="my-col">
                    <div class="my-label">
                        <span>系统菜单 <i>[<b>{{dialog.menusTree.current.label}}</b>] </i></span>
                    </div>
                    <el-tree v-loading="loading" node-key="id"  ref="menusTree" style="height:474px;overflow:auto" class="my-tree" 
                        :default-expanded-keys="dialog.menusTree.defaultExpandedKeys" :default-checked-keys="dialog.menusTree.defaultCheckedKeys"
                        :data="dialog.menusTree.data" @node-click="onMenusTree_nodeClick" @check-change="onMenusTree_checkChange"  :render-content="renderContent"
                        >
                    </el-tree>
                </div>
            </el-col>
            <el-col :span="14" >
                <div class="my-col">
                    <div class="my-label">
                        <span>页面元素列表 <i>[<b>{{dialog.menusTree.current.label}}</b>]</i> </span>
                    </div>
                    <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="width: 100%;"
                            :height="474" :data="dialog.pageviewElements.data"   ref="pageviewElements" 
                    >
                        <el-table-column type="index" label="选择" width="45" align="center">
                            <template slot-scope="scope">
                                <span style="color:green" v-if="findArray(dialog.pageviewElements.selection,scope.row) >=0 ">V</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="ename"  label="按钮元素名称" header-align="center" width="150"></el-table-column>
                        <el-table-column prop="code" label="权限编码" header-align="center" width="150" ></el-table-column> 
                        <el-table-column prop="remark" label="说明" header-align="center" width="300" ></el-table-column>                         
                    </el-table>
                </div>
            </el-col>
            </el-row>
        </el-dialog>
    </div>
</template>
<script>
function buildTreenode(list,idName,pidName,labelName)
{
    var map ={};
    for(let i = 0; i< list.length;i++)
    {

        map[list[i][idName]] = {id:list[i][idName],pid:list[i][pidName],label:list[i][labelName],tag:list[i]};
    }
    var maproot=[];
    function linknode(node)
    {
        if(!node.pid ||node.pid=="/"){
            maproot.push(node);
            return;
        }
        let pnode = map[node.pid];
        node.pnode = pnode;
        if(!pnode.children)
            pnode.children = [];
        pnode.children.push(node);
    }

    for(var id in map)
    {
        let item = map[id];
        linknode(item);
    }
    for(var id in map)
    {
        let item = map[id];
        if(item.children){
            item.isLeaf= false;
            item.checked = false;
        }
        else{            
            item.isLeaf = true;    
            item.checked = false;
            let pitem = map[item.pid];
            if(pitem){
                pitem.isGroup = true;
            }
        }
    }

    return maproot;
}
function filteredTree(treeRoots)
{
    let roots = [];

    function getChecked(node)
    {
        if(node.data.isLeaf )
            return node.data.checked ? node :null;
        var selected = [];
        for(let i = 0; i< node.childNodes.length;i ++)
        {
            let cnode = node.childNodes[0];
            let snode = getChecked(cnode);
            if(snode != null)
                selected.push(snode);
        }
        if(selected.length ==0)
            return null;
        node.childNodes = selected;
        return node;
    }
}
function checkNodeCheckedInfo(node)
{
    let nodeCnt = node.data.isLeaf? 1:0; // 只统计叶结点
    let checkCnt = (node.data.isLeaf && node.data.checked)?1:0;
    for(let i = 0; i< node.childNodes.length;i ++)
    {
        let cnode = node.childNodes[i];
        let r = checkNodeCheckedInfo(cnode);
        nodeCnt += r.nodeCount;
        checkCnt += r.nodeCheckedCount;
    }
    return {nodeCount : nodeCnt,nodeCheckedCount: checkCnt};
}
function treenodeToMap(node,map)
{
    if(!map)
        map = {};
    if(node instanceof Array)
    {
        for(let i=0;i < node.length;i++){
            let cnode = node[i];
            treenodeToMap(cnode,map);
        }
        return map;
    }

    map[node.id] = node;

    if(node.children){
        for(let i=0;i < node.children.length;i++){
            let cnode = node.children[i];
            treenodeToMap(cnode,map);
        }
    }
    return map;
}
function findTreeFirstLeaf(node)
{
    if(node instanceof Array)
    {
        if(node.length >0){

            let cnode = node[0];
            if(cnode.children && cnode.children.length >0)
                return findTreeFirstLeaf(cnode.children[0]);
        }
        return null;
    }
    if(node.isLeaf)
        return node;
    if(node.children && node.children.length >0)
        return findTreeFirstLeaf(node.children[0]);
    return null;
}
function mergeTreenode()
{
    let args =[];
    for(let i = 0; i < arguments.length;i++)
    {
        args.push(arguments[i]);
    }
    if(args.length ==0)
        return;
    let treenodes = args.reduce((result,node)=>{
        let map = treenodeToMap(result);
        if(node instanceof Array)
        {
            for(let i=0;i < node.length;i++){
                let cnode = node[i];
                let fnode = map[cnode.pid];
                if(fnode){
                    if(!fnode.children)
                        fnode.children = [];
                    fnode.children.push(cnode);
                    fnode.isLeaf = false;
                }
            }
            return result;
        }
        let fnode = map[node.pid];
        if(fnode){
            if(!fnode.children)
                fnode.children = [];
            fnode.children.push(cnode);
            fnode.isLeaf = false;
        }
        return result;
    });
    return treenodes;
}
function findTreenode(treenode,id)
{
    function find(cnode,id)
    {
        if(cnode.id == id)
            return cnode;    
        if(cnode.children && cnode.children.length >0){
            for(let i = 0; i< cnode.children.length;i++){
                let fnode = find(cnode.children[i]);
                if(fnode)
                    return fnode;
            }
        }
    }
    if(treenode instanceof Array)
    {
        if(treenode.length >0){
            for(let i = 0; i< treenode.length;i++){
                let fnode = find(treenode[i],id);
                if(fnode)
                    return fnode; 
            }
        }
        return null;
    }    
    return find(treenode,id);
}

function copyDataTo(from, to) 
{
    if(!to){
        to = (from.constructor === Array) ? [] : {};
    }
    for (var i in from) {
        
        if(typeof from[i] === 'function')
            continue;
        if (typeof from[i] === 'object') {
            if(from[i] == null)
                continue;
            to[i] = (from[i].constructor === Array) ? [] : {};
            copyDataTo(from[i], to[i]);
        }else{
            to[i] = from[i];
        }
    }
　　return to;
}
function deleteArrayItem(items,item,prop)
{
    let pname = prop || 'id';
    for(let i = 0; i< items.length;i++)
    {
        if(items[i][pname] == item[pname]){
            items.splice(i,1);
            i--;
        }
    }
}
function findArray(items,item,prop)
{
    let pname = prop || 'id';
    for(let i = 0; i< items.length;i++)
    {
        if(items[i][pname] == item[pname]){
            return i;
        }
    }
    return -1;
}
function findArrayItem(items,val,prop)
{
    let pname = prop || 'id';
    for(let i = 0; i< items.length;i++)
    {
        if(items[i][pname] ==val[pname]){
            return {index:i,val:items[i]};
        }
    }
    return null;
}
var axios ;

var baseUrl="";
var user={};
    export default { 
        data() {
            return {
                dialog:{
                    root:this,
                    modal:true,
                    visible:false,
                    title:'',
                    menusTree:{
                        current:{},
                        data:[],
                        defaultExpandedKeys:[],
                        defaultCheckedKeys:[]
                    },
                    pageviewElements:{
                        data:[],
                        selection:[]
                    }
                },
                roleUsersDialog:{
                    root:this,
                    modal:true,
                    visible:false,
                    users:{
                        data:[],
                        selection:[]
                    }
                },
                corpId:'',
                orgTree:{
                    data:[],
                    current:{},
                    defaultExpandedKeys:[],
                    paths:[]
                }, 
                users:{
                    current:{},
                    data:[],
                    selection:[]
                },
                roles:{
                    current:{},
                    data:[],
                    selection:[]
                },
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                loading: false
            }
        },
        computed:{
            orgPaths()
            {
                var paths = this.orgTree.paths;
                if(!paths)
                    return "";                
                return paths.reduce((initv,nextv)=>initv+(initv.length>0? "/":"")+nextv,"");
            }
        },
        methods: {
            nodeCheckedInfo(node)
            {
                let r = checkNodeCheckedInfo(node);

                if(r.nodeCount == r.nodeCheckedCount )
                    return "已全选";
                else if(r.nodeCheckedCount == 0)
                    return "未选";
                return "已选" + r.nodeCheckedCount +"条";
            },          
            renderContent(h, { node, data, store }) {
                let o = this;
                if(data.isLeaf)
                    return (
                    <span>
                        <span>
                        <span ><span on-click={(e)=>{ e.stopPropagation(); e.preventDefault();}} class="my-check"  >
                        <b style={{color:node.data.checked? "black":"white"}} >{node.data.checked?"V":'.'}</b></span><span class="my-treenode-label">{node.label}</span></span>
                        </span>
                    </span>);
                else if(data.isGroup)
                    return   (
                    <span>
                        <span>
                        <i class="fa fa-gear fa-lg my-node-icon" title={node.label}></i>
                        <span >{node.label}</span>
                        </span>
                        <span style="float: right; margin-right: 20px">
                        <span >({checkNodeCheckedInfo(node).nodeCheckedCount}/{checkNodeCheckedInfo(node).nodeCount})</span>                       
                        </span>
                    </span>);
                else if(data.tag)
                    return (
                    <span>
                        <span>
                        <span >{node.label}{node.data.checkName}</span>
                        </span>
                        <span style="float: right; margin-right: 20px">
                        {o.nodeCheckedInfo(node)}
                        </span>
                    </span>);
                else
                    return (
                    <span>
                        <span>
                        <span >{node.label}</span>
                        <span class="my-node-summary">(共有{checkNodeCheckedInfo(node).nodeCount}个功能菜单，已选择{checkNodeCheckedInfo(node).nodeCheckedCount}个)</span>
                        </span>
                    </span>); 
            },
            onMenusTree_checkChange(data,checked,v1){
                data.checked = checked;
            },
            findOrgNode(node,id,paths)
            {
                if(node instanceof Array)
                {
                    for(let i=0; i <node.length; i++)
                    {
                        paths.push(node[i].label);
                        let findedNode = this.findOrgNode(node[i],id,paths );
                        if(findedNode){
                            return findedNode;
                        }
                        paths.pop();
                    } 
                }else{
                    if(node.id == id){
                        return node;
                    }
                    if(node.children){
                        for(let i=0; i <node.children.length; i++)
                        {
                            paths.push(node.children[i].label);
                            let findedNode = this.findOrgNode(node.children[i],id,paths);
                            if(findedNode)
                                return findedNode;
                            paths.pop();    
                        } 
                    }
                }
                return null;
            }, 
            onMenusTree_nodeClick(v,node)
            {
                let o = this;
                o.dialog.menusTree.current = v;
                o.loadMenuElements();
            },
            onQueryUserRight(row)
            {
                let o = this;
                this.dialog.visible=true;
                this.dialog.title="用户：【" + row.ename + "】的操作权限";
                o.loadUserMenu();
            },
            onRolesSet(row,flag){
                let o = this;
                let roleIds = [];
                if(findArray(o.roles.selection,row) <0)
                    o.roles.selection.push(row);
                else
                    deleteArrayItem(o.roles.selection,row);
                o.roles.selection.forEach(role=>roleIds.push(role.id));

                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/roleConfig/saveUserRoles/'+ o.users.current.id ,roleIds).then(res=>{
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        
                    }else{
                        o.$message.error(res.data.msg);
                        o.loadOrgUsers(o.corpId,o.orgTree.current.id);
                    }
                },err=>{
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });

            },
           
            onClickOrgNode(v,node)
            {
                let o = this;
                this.orgTree.current = v; 
                let paths = [];
                o.findOrgNode(o.orgTree.data,o.orgTree.current.id,paths);
                o.orgTree.paths = paths;

                o.loadOrgUsers(o.corpId,o.orgTree.current.id);

            },
            onUsers_rowClick(row, event, column){
                let o = this;
                o.users.current = row;
                o.loadUserRoles(o.users.current.id);
            },
            loadUserMenu(){
                let o = this;
                let userId = o.users.current.id;
                if(o.$refs.menusTree)
                    o.$refs.menusTree.setCheckedKeys([]);
                var nodeids=[];
                var nodes=[];
                function findTagId(node,ids)
                {
                    if(node.tag){
                        for(let i = 0; i< ids.length;i++){
                            if(node.tag.id == ids[i] ){
                                nodeids.push(node.id);
                                nodes.push(node);
                            }
                        }
                    }
                    if(node.children){
                        for(let i = 0; i< node.children.length;i++)
                        {
                            findTagId(node.children[i],ids);
                        }
                    }

                }

                function cb(menuIds)
                {
                    for(let i = 0; i < o.dialog.menusTree.data.length;i++)
                        findTagId(o.dialog.menusTree.data[i],menuIds);
                    if(o.$refs.menusTree)
                        o.$refs.menusTree.setCheckedKeys(nodeids); 
                    if(nodeids.length >0){
                        o.dialog.menusTree.current = nodes[0];
                        o.dialog.menusTree.defaultExpandedKeys = [nodeids[0]];
                    }
                    
                    o.loadMenuElements();
                }
                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getUserMenuIds/'+ userId ).then(res=>{
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        var menuIds = res.data.data;
                        cb(menuIds);
                    }else{
                        o.$message.error(res.data.msg);
                    }
                },err=>{
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadMenuElements(){
                let o = this;
                if(!o.dialog.menusTree.current.tag )
                    return;
                let menuId = o.dialog.menusTree.current.tag.id;
                let userId = o.users.current.id;
                o.dialog.pageviewElements.selection=[];
                function cbEles(viewElements){
                    if(!viewElements || !viewElements.pageview){
                        o.dialog.pageviewElements.data = [];
                        return;
                    }
                    o.dialog.pageviewElements.data =viewElements.elements; 
                }

                function cb(ueleIds)
                {
                    ueleIds.forEach(id=>{
                        o.dialog.pageviewElements.data.forEach(row=>{
                            if(row.id == id)
                                o.dialog.pageviewElements.selection.push(row);
                        });
                    });
                }

                let callEles = axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getPageviewElements/'+ menuId);
                let callUEles = axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getUserMenuElementIds/'+userId +'/'+ menuId);

                o.loading = true;

                axios.all([callEles,callUEles]).then(axios.spread(
                    (rqEles,rqUes)=>{
                        o.loading = false;
                        if (rqEles.data.code == 0) {   //成功
                            var eles = rqEles.data.data;
                            cbEles(eles);
                        }else{
                            o.$message.error(rqEles.data.msg);
                        }
                        if (rqUes.data.code == 0) {   //成功
                            var ueleIds = rqUes.data.data;
                            cb(ueleIds);
                        }else{
                            o.$message.error(rqUes.data.msg);
                        }
                    }
                ),err=>{
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadOrgUsers(companyId,orgId)
            {
                let o = this;
                let sel = null;

                function cb(users)
                {
                    o.users.data = users;
                    if(users.length == 0)
                    {
                        o.users.current={};
                        o.roles.data=[];
                        o.roles.current={};
                    }else{
                        o.users.current = o.users.data[0];
                        o.loadUserRoles(o.users.current.id);
                    }
                }
                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/adminConfig/listOrgUsers/'+ companyId +'/'+ orgId).then(res=>{
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        var users = res.data.data;
                        cb(users);
                    }else{
                        o.$message.error(res.data.msg);
                    }
                },err=>{
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadUserRoles(userId){
                let o = this;
                function cb(roles)
                {
                    o.roles.selection=[];
                    if(roles.length >0)
                    {
                        roles.forEach(function(role) {
                            let f = findArrayItem(o.roles.data,role);
                            if(f)
                                o.roles.selection.push(f.val);
                        });
                    } 
                }
                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/roleConfig/listUserAllRoles/'+ userId ).then(res=>{
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        var roles = res.data.data;
                        cb(roles);
                    }else{
                        o.$message.error(res.data.msg);
                    }
                },err=>{
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });

            },
            loadCompanyAllOrgAndAllRoles(companyId,currentOrgId)
            {
                let o = this;
                o.orgTree.paths = [];
                o.corpId = companyId;
                let paths = [];
                function cbMenus(menus)
                {
                    let map ={};
                    let menunodes=[];
                    let nodes =  buildTreenode(menus,"code","pcode","cname");
                    
                    for(let i = 0; i <nodes.length;i++)
                    {
                        let pronode = map[nodes[i].tag.projectCode];
                        if(pronode )
                        {
                            pronode.children.push(nodes[i]);
                        }else{
                            nodes[i].pid = nodes[i].tag.projectCode;
                            map[nodes[i].tag.projectCode] = {id:nodes[i].tag.projectCode,pid:'',label:nodes[i].tag.projectName,children:[nodes[i]],isLeaf:false};
                            menunodes.push(map[nodes[i].tag.projectCode]);
                        }
                    } 
                    o.dialog.menusTree.data =menunodes; 
                }
                function cbOrgs(orgs){
                    o.orgTree.data = buildTreenode(orgs,"id","pid","ename");
                    o.orgTree.current = findTreenode(o.orgTree.data,currentOrgId);
                    if(o.orgTree.current == null){
                        o.orgTree.current =findTreeFirstLeaf(o.orgTree.data);
                    }
                    o.orgTree.defaultExpandedKeys =[o.orgTree.current.id];
                    o.findOrgNode(o.orgTree.data,o.orgTree.current.id,paths);
                    o.orgTree.paths = paths;
                    o.loadOrgUsers(companyId,o.orgTree.current.tag.id);
                }
                function cbRoles(roles)
                {
                    o.roles.data = roles;
                    o.roles.selection=[];
                }
                o.loading = true;
                let callMenus = axios.post(baseUrl + '/platformService/permission/adminConfig/listMenu');
                let callOrgs =axios.post(baseUrl + '/platformService/permission/adminConfig/listOrg/'+ companyId );                
                let callRoles =axios.post(baseUrl + '/platformService/permission/roleConfig/getCompanyAllRole/'+ companyId );               

                axios.all([callMenus,callOrgs,callRoles]).then(axios.spread(
                    (rqMenus,rqOrgs,rqRoles)=>{
                        o.loading = false;
                        if (rqMenus.data.code == 0) {   //成功
                            var menus = rqMenus.data.data;
                            cbMenus(menus);
                        }else{
                            o.$message.error(rqOrgs.data.msg);
                        }
                        if (rqOrgs.data.code == 0) {   //成功
                            var orgs = rqOrgs.data.data;
                            cbOrgs(orgs);
                        }else{
                            o.$message.error(rqOrgs.data.msg);
                        }
                        if (rqRoles.data.code == 0) {   //成功
                            var roles = rqRoles.data.data;
                            cbRoles(roles);
                        }else{
                            o.$message.error(rqRoles.data.msg);
                        }

                    }
                ),err=>{
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },            
             deleteArrayItem(items,item,prop)
            {
                deleteArrayItem(items,item,prop);
            },
            findArray(items,item,prop)
            {
                return findArray(items,item,prop);
            }
        },
        mounted() {            
            user = this.user;
            axios = this.axios;
            this.loadCompanyAllOrgAndAllRoles(user.corpId,user.orgId);
        }
    };

</script>
<style>

    *{
        font-size:12px;
    }
    #corp-user-roles-config .el-tree--highlight-current .el-tree-node.is-current>.el-tree-node__content {
        background-color: #8cb8d8;
    }
    #corp-user-roles-config .el-table .cell, .el-table th>div {
        padding-left: 10px;
        padding-right: 10px;
    }
    #corp-user-roles-config .alert-flag{
        text-align:center;
        width: 100%;
        display: inline-block; 
        color:red;
    }   
    #corp-user-roles-config .normal-flag{
        text-align:center;
        width: 100%;
        display: inline-block; 
    }  
    #corp-user-roles-config .my-select{
        height:32px;
        
    }
    #corp-user-roles-config .el-tree-node__label {
        font-size: 12px;
    }
    #corp-user-roles-config .el-tree {
        border-radius: 4px;
    }
    #corp-user-roles-config .el-tabs__item 
    {
        font-size: 12px;
    }
    /*.el-dialog__header {
        padding: 5px;
    }
    .el-dialog__footer {
        padding: 10px 20px 10px;   
    }*/
    #corp-user-roles-config .my-dialog-body
    {
        padding: 15px;
         width:1000px;
         border:1px solid grey;
         border-radius: 4px;
    }
    /*.el-dialog__body {
        padding: 10px 20px;
        height:550px;
        border: 1px solid lightgray;
    }*/
    #corp-user-roles-config .my-dialog-body .label{
        padding:0 0 4px;
        
    }
    #corp-user-roles-config .my-dialog-body .label *{
        font-size:14px;       
    }
    #corp-user-roles-config .my-label
    {
        padding:10px; 
    }
    #corp-user-roles-config .my-label span{
        font-size:14px;
    }
    #corp-user-roles-config .my-col
    {
        width:100%;
        margin:0 3px;
        padding:2px;
        border: 1px solid lightgray;
        border-radius: 4px;        
        height: 550px;
    }
    #corp-user-roles-config .tree-outerline
    {
        border: 1px solid lightgray;
        border-radius: 4px;
        padding:5px 0;
    }
    #corp-user-roles-config .dialog-title
    {
        display:inline-block;
        font-size:16px;
        margin: 0 0 10px;
    }
    #corp-user-roles-config .my-node-icon{
        color: #1d6ce6;
    }
    #corp-user-roles-config .my-node-summary{
        display: inline-block;
        margin-left:5px; 
        color: green;
    }
    #corp-user-roles-config .my-check{
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
   #corp-user-roles-config .my-treenode-label{
       display: inline-block;
   }
</style>