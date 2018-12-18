<template>
    <div id="prov-dept-user-relation" style="height: 100%;">
        <el-row :gutter="5" height="100%" style="height: 100%;">
            <!--医院科室信息-->
            <el-col :span="6" style="height: 93.7%;">
                <div id="deptFilter">
                    <el-input placeholder="输入科室名称过滤" v-model="filterText"></el-input>
                </div>
                <el-tree :data="orgInfo"
                         node-key="id" ref="deptTree"
                         :default-expand-all="true"
                         :expand-on-click-node="false"
                         :default-expanded-keys="expadoIds"
                         :props="orgInfoProps"
                         :highlight-current="true"
                         :filter-node-method="filterNode"
                         @node-click="handleNodeClick">
                </el-tree>
            </el-col>
            <!-- 科室人员信息 -->
            <el-col :span="9" style="height: 94.7%;">
                <el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" ref="usersTable"
                          border fit class="staffMgr-tableheight" @current-change="handUserClick">
                    <el-table-column prop="orgName" header-align="center" width="150" label="科室">
                    </el-table-column>
                    <el-table-column prop="ename" header-align="center" width="100" label="职工姓名">
                    </el-table-column>
                    <el-table-column prop="job" header-align="center" label="职务">
                    </el-table-column>
                </el-table>
                <el-col :span="24" class="toolbar staffPage">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 100, 200, 400]"
                                   :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>

            <!-- 关联供应商信息 -->
            <el-col :span="9" style="height: 100%;">
                <el-form :inline="true" :model="provFilters" ref="provRelationForm" label-width="80px">
                    <el-input v-model="provFilters.provName" placeholder="请输入供应商名称"  style="width:150px"></el-input>
                    <el-button-group>
                        <el-button type="primary" v-on:click="getUserProvRelationList(1)" >查询</el-button>
                        <el-button type="success" @click="batchAdd(null)" >批量增加</el-button>
                        <el-button type="danger" @click="batchDelete()" >批量删除</el-button>
                    </el-button-group>
                </el-form>
                <el-table :data="provRelationList" v-loading="provLoading"  highlight-current-row @selection-change="provHandleSelectionChange"
                          ref="provRelationTable" style="width: 100%;" border fit height="outter" class="provRelation-tableHeight">
                    <el-table-column type="selection" align="center" width="55"> </el-table-column>
                    <el-table-column prop="provName" header-align="center" label="供应商名称"></el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="provHandleSizeChange" @current-change="provHandleCurrentChange"
                                   :current-page="provPage" :page-sizes="[20, 50, 100, 200]"
                                   :page-size="provPageSize" layout="total, sizes, prev, pager, next"
                                   :total="provTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>

            <!--批量新增供应商弹框  -->
            <el-dialog title="批量建立用户供应商关系" close="onClose" :visible.sync="addProvRelationVisible" v-loading="addProvLoading" :close-on-click-modal="false" size="checkProvDialog">
                <el-form :inline="true" :model="addProvfilters" label-width="80px" ref="provNameForm">
                    <el-form-item label="供应商名称">
                        <el-input v-model="addProvfilters.provName" placeholder="输入供应商名称" ></el-input>
                    </el-form-item>
                    <el-button type="primary" v-on:click="getUserHosProvRelationList(1)" >查询</el-button>
                </el-form>
                <template>
                    <el-table ref="multipleAddProvListTable" :data="addProvRelationList"  border tooltip-effect="dark" row-key='id'
                            style="width: 100%" @selection-change="addProvHandleSelectionChange">
                        <el-table-column type="selection" align="center" width="55"> </el-table-column>
                        <el-table-column type="index" label="序号" align="center"></el-table-column>
                        <el-table-column prop="provName" header-align="center" label="供应商名称"></el-table-column>
                        <el-table-column prop="contactUser" label="联系人" header-align="center"></el-table-column>
                        <el-table-column prop="contactPattern" label="联系电话" header-align="center"></el-table-column>
                    </el-table>
                    <div style="padding-top: 6px">
                        <el-pagination @size-change="addProvHandleSizeChange" @current-change="addProvHandleCurrentChange"
                                    :current-page="addProvPage" :page-sizes="[10, 20, 50, 100]"
                                    :page-size="addProvPageSize" layout="total, sizes, prev, pager, next"
                                    :total="addProvTotal" style="float:right;" small>
                        </el-pagination>
                        <el-button @click="toggleSelection()" >取消选择</el-button>
                        <el-button @click="provRelationSubmit()" type="success" >确定</el-button>
                        <el-button @click.native="addProvRelationVisible = false" type="primary" >返回</el-button>
                    </div>
                </template>
            </el-dialog>

        </el-row>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                filterText: '',
                unit: {},
                filters: {
                    ename: '',
                    orgCode: '',
                    orgId: '',
                    orgName: ''
                },
                expadoIds: [],
                hosId:'',
                corpName:'',
                orgInfo: [],
                orgInfoProps: {
                    children: 'children',
                    label: 'label',
                    id: 'id',
                    pid: 'pid'
                },
                //用户相关
                total: 0,
                page: 1,
                pageSize: 10,
                loading: false,
                users: [],
                currentUser:{},

                //供应商关系相关
                provLoading:false,
                provFilters: {
                    provName: '',
                },
                provTotal: 0,
                provPage: 1,
                provPageSize: 10,
                provRelationList: [],
                provMultipleSelection:[],

                //批量新增供应商相关
                addProvRelationVisible:false,
                addProvLoading:false,
                addProvfilters: {
                    provName: ''
                },
                addProvTotal: 0,
                addProvPage: 1,
                addProvPageSize: 10,
                addProvRelationList: [],
                addProvMultipleSelection:[],
            }
        },
        watch: {
            filterText(val) {
                this.$refs.deptTree.filter(val);
            }
        },
        methods: {
            //获取用户列表
            getUser: function (pIndex) {
                let o = this;
                o.page = pIndex;
                var param = { 
                    orderBy: "", 
                    queryObject: { 
                        ename: o.filters.ename, 
                        orgId: o.filters.orgId 
                    }, 
                    page: o.page, 
                    rows: o.pageSize 
                    };
                o.loading = true;
                o.axios.post(`/platformService/sys/user/userlistByUnit`, param).then(res => {
                    o.loading = false;
                    o.users = res.data.data.data;
                    o.total = res.data.data.total;
                }, err => {
                    o.loading = false;
                });
            },
            setCurrent(row) {
                this.currentUser = row;
                this.$refs.usersTable.setCurrentRow(row);
            },
            //点击用户关联供应商信息
            handUserClick(row){
                this.currentUser = row;
                this.getUserProvRelationList(1);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getUser(this.page);
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.getUser(this.page);
            },
            handleNodeClick(data) {
                if(data.pid){
                    this.filters.orgId = data.id;
                    this.filters.orgCode= data.code;
                }else{
                    this.filters.orgId = '';
                    this.filters.orgCode= '';
                }  
               //清除关联供应商信息
                this.provRelationList = [];
                this.provTotal = 0;
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
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            searchOrgs:function () {
                var p = this.filters.orgName;
                let param = {cname:p};
                this.loading=true;
                this.axios.post(`/platformService/sys/org/searchOrgs`, param).then(res=>{
                    this.loading = false;
                    var ids=[];
                    for(var i = 0;i < res.data.data.length; i++) {
                        ids[i] =res.data.data[i].id;
                    }
                    this.expadoIds= ids;
                });
                this.loading=false;
            },
            //获取用户供应商关系列表
            getUserProvRelationList: function (pIndex) {
                this.provPage = pIndex;
                var param = { 
                    orderBy: "", 
                    queryObject: { 
                        userId: this.currentUser.userId,
                        provName:this.provFilters.provName,
                    }, 
                    page: this.provPage, 
                    rows: this.provPageSize 
                };
                this.provLoading = true;
                this.axios.post(`/spdHERPService/provMgr/provDeptUserRelation/userProvRelationList`, param).then(res => {
                    this.provLoading = false;
                    this.provRelationList = res.data.data.data;
                    this.provTotal = res.data.data.total;
                }, err => {
                    this.provLoading = false;
                });
            },
            provHandleSelectionChange (val) {
                this.provMultipleSelection = val
            },
            provHandleCurrentChange(val) {
                this.provPage = val;
                this.getUserProvRelationList(this.provPage );
            },
            provHandleSizeChange(val) {
                this.provPageSize = val;
                this.getUserProvRelationList(this.provPage);
            },
            batchAdd: function(){
               if(this.currentUser.userId ==null||this.currentUser.userId==""){
                    this.$message.error('请先点击选择科室人员再进行操作！');
                    return;
                }
                this.addProvRelationVisible = true;
                this.addProvMultipleSelection = [];
                this.getUserHosProvRelationList(1);
            },
            batchDelete: function(){
                if(this.currentUser.userId ==null||this.currentUser.userId==""){
                    this.$message.error('请先点击选择科室人员再进行操作！');
                    return;
                }
                if (this.provMultipleSelection.length <= 0) {
                    this.$message.error('至少选择一条数据！')
                    return
                }
                this.$confirm('确认删除供应商人员关系吗?', '提示', {
                }).then(() => {
                    this.provLoading = true
                    var param = {
                        userId : this.currentUser.userId,
                        provIds: this.provMultipleSelection
                    }
                    this.axios.post('/spdHERPService/provMgr/provDeptUserRelation/batchDelete', param).then(res => {
                    this.provLoading = false
                    if (res.data.code == '0') {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.loading = false
                        this.getUserProvRelationList(1)
                    } else {
                        this.$message.error(res.data.msg)
                    }
                    }, err => {
                    this.provLoading = false
                    })
                }).catch(() => {})
            },
            //新增用户供应商关系列表
            getUserHosProvRelationList: function (pIndex) {
                this.addProvPage = pIndex;
                var param = { 
                    orderBy: "", 
                    queryObject: { 
                        userId: this.currentUser.userId,
                        provName :this.addProvfilters.provName
                    }, 
                    page: this.addProvPage, 
                    rows: this.addProvPageSize 
                };
                this.addProvLoading = true;
                this.axios.post(`/spdHERPService/provMgr/provDeptUserRelation/userHosProvRelationList`, param).then(res => {
                    this.addProvLoading = false;
                    this.addProvRelationList = res.data.data.data;
                    this.addProvTotal = res.data.data.total;
                }, err => {
                    this.addProvLoading = false;
                });
            },
            addProvHandleSelectionChange (val) {
                this.addProvMultipleSelection = val
            },
            toggleSelection (rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleAddProvListTable.toggleRowSelection(row)
                    })
                } else {
                    this.$refs.multipleAddProvListTable.clearSelection()
                }
            },
            addProvHandleCurrentChange(val) {
                this.addProvPage = val;
                this.getUserHosProvRelationList(this.addProvPage);
            },
            addProvHandleSizeChange(val) {
                this.addProvPageSize = val;
                this.getUserHosProvRelationList(this.addProvPage);
            },
            provRelationSubmit:function(){
                if(this.currentUser.userId ==null||this.currentUser.userId==""){
                    this.$message.error('请先点击选择科室人员信息再进行保存操作！');
                    return;
                }
                if (this.addProvMultipleSelection.length <= 0) {
                    this.$message.error('至少选择一条数据！')
                    return
                }
                this.$confirm('确认保存关联关系吗?', '提示', {
                    }).then(() => {
                        this.addProvLoading = true;
                        var param = {
                                userId : this.currentUser.userId,
                                provIds: this.addProvMultipleSelection
                            }
                        this.axios.post('/spdHERPService/provMgr/provDeptUserRelation/provDeptUserRelationSubmit', param).then(res => {
                            this.addProvLoading = false
                            if (res.data.code == '0') {
                                this.$message({ message: '操作成功！', type: 'success' });
                                this.$refs.multipleAddProvListTable.clearSelection();
                                this.addProvRelationVisible = false;
                                this.getUserProvRelationList(1);
                            } else {
                                this.$message.error(res.data.msg)
                            }
                        }, err => {
                            this.addProvLoading = false
                        })
                }).catch(() => {})
            }
        },
        mounted() {
            this.hosId = this.user.corpId;
            this.getUser(1);
            this.getOrgsByUnit();
        }
    };
</script>

<style>
    #prov-dept-user-relation .el-tree-node__label {
        font-size: 12px;
    }
    #prov-dept-user-relation .el-table {
        font-size: 12px;
    }
    #prov-dept-user-relation .el-tree {
        height: 100%;
    }
    #prov-dept-user-relation .staffMgr-tableheight{
        height: 100%;
    }
    #prov-dept-user-relation .staffPage{
        height: 3.7%;
        border: 1px solid #d1dbe5;
        border-top: 0px;
    }
    #deptFilter .el-input__inner{
        margin-bottom: 1px;
        height: 30px;
    }
    #prov-dept-user-relation .provRelation-tableHeight{
        height:  calc(100% - 60px);
        overflow-x:hidden;
    }
    #prov-dept-user-relation .el-dialog--checkProvDialog {
        max-width: 800px;
        min-width: 700px;
        width: 80%;
    }
</style>