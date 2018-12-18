<template>    
    <div id="menu-services-binding" style="height: 100%;padding:0 3px">
        <el-row :gutter="2" style="height: calc(100% - 6px);">
        <el-col :span="8" style="height: 100%;">
            <div class="my-col" style="height: 100%;">
                <div class="my-label">
                    <span>系统菜单 <i>[<b>{{checkMenusTree.currentNode.label}}</b>] </i></span>
                </div>
                <el-tree  v-loading="loading" node-key="id" style="height:calc(100% - 50px);;overflow:auto" class="my-tree"
                    :default-expanded-keys="checkMenusTree.defaultExpandedKeys" highlight-current
                    :data="checkMenusTree.data" @node-click="oncheckMenusTree_nodeClick" ref="checkMenusTree"
                    >
                </el-tree>
            </div>
        </el-col>
        <el-col :span="16" style="height: 100%;">
            <div class="my-col" style="height: 100%;">
                <div class="my-label">
                    <span>页面服务绑定<i>[<b>{{checkMenusTree.currentNode.label}}</b>]</i> </span>
                </div>
                <div>
                    <el-button  type="primary" @click="onAddNewPageviewBinding">新增页面绑定</el-button>
                    <el-button  v-if="pageviewBindings.changed" @click="onUpdatePageviewBinding" type="danger">保存页面绑定</el-button>
                    <el-button  v-else @click="onUpdatePageviewBinding" type="primary" >保存页面绑定</el-button>
                    <b style="color:red;" v-if="pageviewBindings.changed">(*数据已经改变*)</b>
                </div>
                <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"  style="height: calc(50% - 75px);width: 100%;"
                        height="outter" :data="pageviewBindings.data"   ref="dialogElementBindings" 
                   >
                    <el-table-column type="index" label="序号" width="55" align="center">
                    </el-table-column>
                    <el-table-column prop="functionName" label="功能方法名称" header-align="center" width="150" ></el-table-column> 
                    <el-table-column prop="functionRemark"  label="说明" header-align="center" width="150"></el-table-column>
                    <el-table-column prop="url" label="url" header-align="center" width="420" ></el-table-column> 
                    <el-table-column label="操作" width="120" header-align="center" >
                        <template slot-scope="scope">
                            <el-button-group >
                                <el-button type="danger" size="mini" @click="onPageviewBindingDelete(scope.row)" icon="delete"  >删除</el-button> 
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="my-label">
                    <span>页面元素列表 <i>[<b>{{checkMenusTree.currentNode.label}}</b>]</i> </span>
                </div>
                <div>
                    <el-button  type="primary" @click="onAddNewElement">新增页面元素</el-button>
                </div>
                <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="width: 100%;height:calc(50% - 70px)"
                        height="outter" :data="viewElements.data"   ref="viewElements" 
                   >
                    <el-table-column type="index" label="序号" width="55" align="center">
                    </el-table-column>
                    <el-table-column prop="ename"  label="按钮元素名称" header-align="center" width="150"></el-table-column>
                    <el-table-column prop="code" label="权限编码" header-align="center" width="150" ></el-table-column> 
                    <el-table-column prop="remark" label="说明" header-align="center" width="300" ></el-table-column> 
                    <el-table-column label="绑定" width="80" header-align="center" >
                        <template slot-scope="scope">
                            <span v-if="!scope.row.funcIds" style="color:red">未绑定</span>
                        </template>
                    </el-table-column>
                            
                    <el-table-column label="操作" width="150" header-align="center" >
                        <template slot-scope="scope">
                            <el-button-group >
                                <el-button type="primary" size="mini" @click="onElementEdit(scope.row)" icon="edit"  >编辑</el-button> 
                                <el-button type="danger" size="mini" @click="onElementDelete(scope.row)" icon="delete"  >删除</el-button> 
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-col>
        </el-row>
        <el-dialog :title="dialog.title" :visible.sync="dialog.visible" :modal="dialog.modal" :show-close="false" 
              ><!--draggable='true' @dragstart='drag($event)' @dragend="dragend"-->
            <div>
                <el-form ref="dialogElementForm" :model="dialog.elementForm" label-width="120px">
                    <el-form-item label="页面元素名称" class="my-form-item">
                        <el-input v-model="dialog.elementForm.ename" @change="dialog.changed=true"></el-input>
                    </el-form-item>
                    <el-form-item label="权限编码" class="my-form-item">
                        <el-input v-model="dialog.elementForm.code" @change="dialog.changed=true"></el-input>
                    </el-form-item>
                    <el-form-item label="元素说明" class="my-form-item">
                        <el-input v-model="dialog.elementForm.remark" @change="dialog.changed=true"></el-input>
                    </el-form-item> 
                </el-form>
                <div class="my-label">
                    <span>绑定的功能方法列表：</span><b style="color:red;" v-if="dialog.changed">(数据已经改变*)</b>
                </div>
                <div >
                    <el-button  @click="dialog.onAddNewBinding(dialog)">新增绑定</el-button> 
                </div>
                <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="width: 100%;"
                        :height="200" :data="dialog.elementBindings.data"   ref="dialogElementBindings" 
                   >
                    <el-table-column type="index" label="序号" width="55" align="center">
                    </el-table-column>
                    <el-table-column prop="moduleName"  label="模块名称" header-align="center" width="150"></el-table-column>
                    <el-table-column prop="functionName" label="接口方法" header-align="center" width="150" ></el-table-column> 
                    <el-table-column prop="url" label="url" header-align="center" width="400" ></el-table-column>   
                    <el-table-column label="操作" width="100" header-align="center" >
                        <template slot-scope="scope">
                            <el-button-group >
                                <el-button type="danger" size="mini" @click="dialog.onElementBindingDelete(scope.row,dialog)" icon="delete" >删除</el-button> 
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button  :type="dialog.changed?'danger':'primary'" @click="dialog.onSave(dialog)">保存</el-button>
                <el-button  type="primary" @click="dialog.onClose(dialog.root)">关闭</el-button>
            </span>
        </el-dialog>
        <el-dialog :title="moduleSelectDialog.title" :visible.sync="moduleSelectDialog.visible" :modal="moduleSelectDialog.modal" :show-close="true"
              >
            <el-row>
                <el-col :span="8">
                    <div class="my-col">
                        <div class="my-label">
                            <span>系统模块 <i>[<b>{{moduleTree.currentNode.label}}</b>] </i></span>
                        </div>
                        <el-tree  node-key="id" style="height:380px;overflow:auto" class="my-tree" 
                            :default-expanded-keys="moduleTree.defaultExpandedKeys" highlight-current
                            :data="moduleTree.data" @node-click="onModuleTree_nodeClick" ref="moduleTree"
                            >
                        </el-tree>
                    </div>
                </el-col>
                <el-col :span="16">
                    <div class="my-col">
                        <div class="my-label">
                            <span>功能方法列表 <i>[<b>当前模块:{{moduleTree.currentNode.label}}</b>]</i> </span>
                        </div>                      
                        <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="width: 100%;" 
                            :height="170" :data="moduleFunctions.data"   ref="moduleFunctions" @row-click="onModuleFunctions_rowClick"
                        >
                            <el-table-column type="index" label="选择" width="55" align="center">
                                <template slot-scope="scope">
                                    <span style="color:green" v-if="findArray(moduleFunctions.selection,scope.row) >=0 ">V</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="functionName" label="功能方法名称" header-align="center" width="150" ></el-table-column> 
                            <el-table-column prop="functionRemark"  label="说明" header-align="center" width="150"></el-table-column>
                            <el-table-column prop="url" label="url" header-align="center" width="350" ></el-table-column> 
                        </el-table>
                        <div class="my-label">
                            <span>已经选择列表 <i>[<b>总数:{{moduleFunctions.selection.length}}</b>]</i> </span>
                        </div> 
                        <el-table highlight-current-row   v-loading="loading" border tooltip-effect="dark"    style="width: 100%;" 
                            :height="190" :data="moduleFunctions.selection"   
                        >
                            <el-table-column label="操作" width="60" header-align="center" >
                                <template slot-scope="scope">
                                    <el-button-group >
                                        <el-button type="danger" size="mini" @click="deleteArrayItem(moduleFunctions.selection,scope.row)" icon="delete"  >删除</el-button> 
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            <el-table-column prop="functionName" label="功能方法名称" header-align="center" width="150" ></el-table-column> 
                            <el-table-column prop="functionRemark"  label="说明" header-align="center" width="150"></el-table-column>
                            <el-table-column prop="url" label="url" header-align="center" width="350" ></el-table-column> 
                            
                        </el-table>
                    </div>
                </el-col>
            </el-row>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="moduleSelectDialog.onOk(moduleSelectDialog.root)" >确定</el-button>
            </span>
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
        if(!node.pid ){
            maproot.push(node);
            return;
        }
        let pnode = map[node.pid];
        if(!pnode){
            maproot.push(node);
            return;
        }
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
        if(item.children && item.children.length >0)
            item.isLeaf= false;
        else
            item.isLeaf = true;
    }
    return maproot;
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
                    elementForm:{
                        id:'',
                        code:'',
                        ename:'name val',
                        version:0
                    },
                    elementRef:null,
                    elementBindings:{
                        data:[]
                    },
                    onAddNewBinding(o){
                        function cb(moduleFunction)
                        {
                            moduleFunction.forEach(row=>{
                                if(findArray(o.elementBindings.data,row)<0)
                                {
                                    o.elementBindings.data.push(row);
                                    if(!o.changed)
                                        o.changed = true;
                                }
                            });
                        }
                        o.root.moduleFunctions.selection=[];
                        o.root.moduleSelectDialog.title = "新增页面绑定" ;
                        o.root.moduleSelectDialog.cb = cb;
                        o.root.moduleSelectDialog.visible=true;
                    },
                    onElementBindingDelete(row,o){
                        let rows = o.elementBindings.data;
                        for(let i = 0;i < rows.length;i++)
                        {
                            if(rows[i].id == row.id)
                                rows.splice(i,1);
                        }
                        o.changed = true;
                    },
                    onSave(o){ 
                        function cb(row){
                            copyDataTo(row,o.elementForm); 
                            
                            if(o.elementRef == null) // 新增数据，刷新元素信息
                                o.root.loadMenuElements();
                        }
                        let funcIds ="";
                        o.elementBindings.data.forEach(row=>{
                            if(funcIds.length>0)
                                funcIds +=",";
                            funcIds += row.id;
                        });
                        let row = o.elementForm;
                        
                        row.funcIds = funcIds;
                        o.root.loading = true;
                        axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/updateSysPageviewElement',row).then(res => {
                            o.root.loading = false;
                            if (res.data.code == 0) {   //成功
                                o.changed = false; 
                                o.visible = false;
                                row = res.data.data;
                                cb(row);
                                o.root.$message({
                                    message: '成功保存页面元素信息！',
                                    type: 'success'
                                });
                            }else{
                                o.root.$message.error("数据处理出错:" + res.data.msg);
                            }
                        }, err => {
                            o.root.loading = false;
                            o.root.$message.error("数据处理出错");
                        });
                    },
                    onClose(o){
                        if(o.dialog.changed == true){
                            o.$confirm('有改变的数据需要保存，确定不保存而直接退出吗?', '提示', {
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
                moduleSelectDialog:{
                    root:this,
                    modal:true,
                    visible:false,
                    changed:false,
                    title:'',
                    cb:null,
                    onOk(o){
                        if(this.cb && o.moduleFunctions.selection.length >0){
                            this.cb(o.moduleFunctions.selection);
                        }
                        this.visible = false;
                        o.moduleFunctions.selection=[];
                    }
                },
                moduleTree:{
                    currentNode:'',
                    data:[],
                    defaultExpandedKeys:[],
                    defaultCheckedKeys:[] 
                },
                moduleFunctions:{
                    data:[],
                    currentNode:null,
                    selection:[]
                },
                checkMenusTree:{
                    currentNode:'',
                    data:[],
                    defaultExpandedKeys:[],
                    defaultCheckedKeys:[] 
                },             
                pageviewBindings:{
                    pageviewId:'0',
                    version:0,
                    changed:false,
                    data:[]
                },
                viewElements:{
                    data:[]
                },
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                loading: false
            }
        },     
        methods: { 
            onAddNewPageviewBinding()
            {
                let o = this;
                function cb(moduleFunction)
                {
                     moduleFunction.forEach(row=>{
                        if(findArray(o.pageviewBindings.data,row)<0){
                            o.pageviewBindings.data.push(row);
                            if(!o.pageviewBindings.changed) 
                                o.pageviewBindings.changed = true;
                        }
                    });
                    
                }
                o.moduleFunctions.selection=[];
                this.moduleSelectDialog.title = "新增页面绑定" ;
                this.moduleSelectDialog.cb = cb;
                this.moduleSelectDialog.visible=true;

            },
            onUpdatePageviewBinding()
            {
                let o = this;
                function cb(pageview){
                    o.pageviewBindings.pageviewId = pageview.id;
                    o.pageviewBindings.version = pageview.version;
                    o.pageviewBindings.changed = false;
                }
                let funcIds ="";
                o.pageviewBindings.data.forEach(row=>{
                    if(funcIds.length>0)
                        funcIds +=",";
                    funcIds += row.id;
                });

                let pageview = {
                    id:o.pageviewBindings.pageviewId,
                    menuId:o.checkMenusTree.currentNode.tag.id,
                    ename:o.checkMenusTree.currentNode.label,
                    funcIds:funcIds,
                    version:o.pageviewBindings.version };

                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/updateSysPageview', pageview).then(function (rq){
                    o.loading = false;
                    if (rq.data.code == 0) {   //成功
                        pageview = rq.data.data;
                        cb(pageview);
                    } else{
                        o.$message.error("数据加载出错");
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
                
            },
            onPageviewBindingDelete(row)
            {
                let o = this;
                let rows = o.pageviewBindings.data;
                deleteArrayItem(rows,row);
                o.pageviewBindings.changed = true;
            },
            onAddNewElement()
            {
                let o = this;
                if(o.pageviewBindings.pageviewId == "0" || o.pageviewBindings.changed)
                {
                    o.$message({
                            message: '页面绑定未保存，或是新建的页面关联，请先保存页面绑定！',
                            type: 'warning'
                        });
                    return ;
                }
                let row = {id:'0',pageId:o.pageviewBindings.pageviewId,
                    ename:'新增元素1',code:'00',funcIds:'',remark:'',version:0};
                this.dialog.title = "新增元素1" ;
                this.dialog.elementForm = row;
                this.dialog.elementRef = null;
                this.dialog.visible=true;
                this.dialog.changed = false;
                this.dialog.elementBindings.data = [];
            },
            onElementEdit(row){
                this.dialog.title = "编辑元素：" + row.ename;
                this.dialog.elementForm = row;
                this.dialog.elementRef = row;
                this.dialog.visible=true;
                this.dialog.changed = false;
                this.loadElementBindings(row.id);
            },
            onElementDelete(row){                
                let o = this;
                o.loading = true;
                function cb(){
                    let rows = o.viewElements.data;
                    deleteArrayItem(rows,row);
                }
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/deleteSysPageviewElement',row).then(res => {
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        cb();
                    }else{
                        o.$message.error("数据处理出错:" + res.data.msg);
                    }
                }, err => {
                    o.loading = false;
                    o.$message.error("数据处理出错");
                });
            },
            onElementBind(row){

            },
            onModuleTree_nodeClick(v){
                this.moduleTree.currentNode = v;
                this.loadModuleFunctions();
            },
            onModuleFunctions_rowClick(row, event, column){
                this.moduleFunctions.currentNode = row;
                if(findArray(this.moduleFunctions.selection,row) <0)
                    this.moduleFunctions.selection.push(row);
                else
                    deleteArrayItem(this.moduleFunctions.selection,row);

            },
            oncheckMenusTree_nodeClick(v)
            {
                this.checkMenusTree.currentNode = v;
                this.loadMenuElements();
            },
            loadModuleFunctions()
            {
                let o = this;
                if(!o.moduleTree.currentNode || !o.moduleTree.currentNode.id)
                    return;
                function cb(rows)
                {
                    rows.forEach(row=>row.id = row.functionId);
                    o.moduleFunctions.data = rows;
                    o.moduleFunctions.currentNode=null;
                    if(rows.length >0)
                        o.moduleFunctions.currentNode = o.moduleFunctions.data[0];
                }
                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getModuleFunctions/' + o.moduleTree.currentNode.id+'.do').then(function (rq){
                    o.loading = false;
                    if (rq.data.code == 0) {   //成功
                        let rows = rq.data.data;
                        cb(rows);
                    } else{
                        o.$message.error("数据加载出错:" + rq.data.msg);
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadAllModulesAndFunctions()
            {
                let o = this;

                function cb(map)
                {
                    let projects = map['subProjects'];
                    let moduleGroups = map['moduleGroups'];
                    let modules = map['modules'];
                    let treenode = mergeTreenode(
                        buildTreenode(projects,"id","pid","cname"),
                        buildTreenode(moduleGroups,"id","projectId","cname"),
                        buildTreenode(modules,"id","groupId","cname")
                        );
                    o.moduleTree.data = treenode;
                    let firstNode = findTreeFirstLeaf(treenode);
                    o.moduleTree.currentNode = firstNode;
                    o.moduleTree.defaultExpandedKeys =[firstNode.id];
                    o.loadModuleFunctions();
                }

                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getAllProjects').then(function (rq){
                    o.loading = false;
                    if (rq.data.code == 0) {   //成功
                        let map = rq.data.data;
                        cb(map);
                    } else{
                        o.$message.error("数据加载出错:" + rq.data.msg);
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadElementBindings(elementId)
            {
                let o = this;
                function cb(bindings){
                    bindings.forEach(row=>row.id = row.functionId);
                    o.dialog.elementBindings.data= bindings;
                }

                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getPageviewElementBindings/'+ elementId).then(function (rqBindings){
                    o.loading = false;
                    if (rqBindings.data.code == 0) {   //成功
                        let bindings = rqBindings.data.data;
                        cb(bindings);
                    } else{
                        o.$message.error("数据加载出错:" + rqBindings.data.msg);
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadPageviewBindings(pageviewId)
            {
                let o = this;
                if(!pageviewId)
                {
                    o.pageviewBindings.data= [] ;
                    o.pageviewBindings.pageviewId = '0';
                    o.pageviewBindings.version = 0;
                    return;
                }
                function cb(bindings){
                    o.pageviewBindings.data= bindings;
                    let rows = o.pageviewBindings.data;
                    rows.forEach(row=>row.id = row.functionId);
                }

                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getPageviewBindings/'+ pageviewId).then(function (rqBindings){
                    o.loading = false;
                    if (rqBindings.data.code == 0) {   //成功
                        let bindings = rqBindings.data.data;
                        cb(bindings);
                    } else{
                        o.$message.error("数据加载出错:" + rqBindings.data.msg);
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });
            },
            loadPageviewElementBindings(elementId,bindingData)
            {
                let o = this;
                o.loading = true;
                function cb(bindings){
                    bindingData.data = bindings;
                }
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getPageviewElementBindings/'+ elementId).then(function (rqBindings){
                    o.loading = false;
                    if (rqBindings.data.code == 0) {   //成功
                        let bindings = rqBindings.data.data;
                        cb(bindings);
                    } else{
                        o.$message.error("数据加载出错:" + res.data.msg);
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });                
            },
            loadMenuElements(){
                let o = this;

                if(!o.checkMenusTree.currentNode.tag )
                    return;
                let menuId = o.checkMenusTree.currentNode.tag.id;

                function cb(viewElements){
                    o.pageviewBindings.changed = false;
                    if(!viewElements || !viewElements.pageview){
                        o.viewElements.data = [];
                        o.pageviewBindings.data= [] ;
                        o.pageviewBindings.pageviewId='0';
                        o.pageviewBindings.version = 0;
                        return;
                    }
                    o.viewElements.data =viewElements.elements; 
                    o.pageviewBindings.pageviewId=viewElements.pageview.id;
                    o.pageviewBindings.version = viewElements.pageview.version;
                    o.loadPageviewBindings(viewElements.pageview.id);
                    
                }

                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/menuPageviewItemsConfig/getPageviewElements/'+ menuId).then(function (rqElements){
                    o.loading = false;
                    if (rqElements.data.code == 0) {   //成功
                        let viewElements = rqElements.data.data;
                        cb(viewElements);
                    } else{
                        o.$message.error("数据加载出错:" + rqElements.data.msg);
                    }
                },err => {
                    o.loading = false;
                    o.$message.error("数据加载出错");
                });                
                
            },
            loadMenu()
            {
                let o = this;
                function cb(menus)
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
                   
                    o.checkMenusTree.data =menunodes; 
                    if(nodes.length>0) {
                        let firstNode = findTreeFirstLeaf(nodes);
                        o.checkMenusTree.currentNode = firstNode;
                        o.checkMenusTree.defaultExpandedKeys = [firstNode.id];    
                        o.loadMenuElements();
                    }

                }
                o.loading = true;
                axios.post(baseUrl + '/platformService/permission/adminConfig/listMenu').then(res => {
                    o.loading = false;
                    if (res.data.code == 0) {   //成功
                        let menus = res.data.data;
                        cb(menus);
                    }else{
                        o.$message.error("数据加载出错:" + res.data.msg);
                    }
                }, err => {
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
            this.loadMenu();
            this.loadAllModulesAndFunctions();
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
    #menu-services-binding .el-tree--highlight-current .el-tree-node.is-current>.el-tree-node__content {
        background-color: #8cb8d8;
    }
    #menu-services-binding .el-select-dropdown__item.selected{
        
    }
    #menu-services-binding .el-table .cell, .el-table th>div {
        padding-left: 10px;
        padding-right: 10px;
    }
    #menu-services-binding .admin-flag{
        text-align:center;
        width: 100%;
        display: inline-block; 
        color:red;
    }   
    #menu-services-binding .my-select{
        height:32px;
        
    }
    #menu-services-binding .el-tree-node__label {
        font-size: 12px;
    }
    #menu-services-binding .el-tree {
        border-radius: 4px;
    }
    #menu-services-binding .el-tabs__item 
    {
        font-size: 12px;
    }
    /*.el-dialog__header {
        padding: 10px;
    }
    .el-dialog__footer {
        padding: 10px 20px 10px;   
    }*/
    #menu-services-binding .my-dialog-body
    {
        padding: 15px;
         /* width:1000px; */
         border:1px solid grey;
         border-radius: 4px;
    }
    /*.el-dialog__body {
        padding: 10px 20px;
        height:530px;
        border: 1px solid lightgray;
    }*/
    #menu-services-binding .my-dialog-body .label{
        padding:0 0 4px;
        
    }
    #menu-services-binding .my-dialog-body .label *{
        font-size:14px;       
    }
    #menu-services-binding .my-col
    {
        padding:2px;
        border: 0px solid lightgray;
        border-radius: 4px;        
        /* height: 550px; */
    }
    #menu-services-binding .my-label
    {
        padding:10px;        
    }
    #menu-services-binding .my-label span{
        font-size:14px;
    }
    #menu-services-binding .tree-outerline
    {
        border: 0px solid lightgray;
        border-radius: 4px;
        padding:5px 0;
    }
    
    #menu-services-binding .my-form-item
    {
        margin-bottom: 16px;
    }
    #menu-services-binding .dialog-title
    {
        display:inline-block;
        font-size:16px;
        margin: 0 0 10px;
    }
</style>