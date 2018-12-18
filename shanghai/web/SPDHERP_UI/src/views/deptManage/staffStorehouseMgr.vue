<template>
    <div id="staff-storehouse-mgr" style="height: 100%;">
        <el-row :gutter="5" height="100%" style="height: 100%;">
            <!--工具条  职员管理-->
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
            <el-col :span="9" style="height: 92%;">
                <el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" ref="usersTable"
                          border fit  class="staffMgr-tableheight" @current-change="queryStaffStock">
                    <el-table-column prop="orgName" header-align="center" width="100" label="科室">
                    </el-table-column>
                    <el-table-column prop="ename" header-align="center" width="150" label="职工姓名">
                    </el-table-column>
                    <el-table-column prop="job" header-align="center" label="职务">
                    </el-table-column>
                </el-table>
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 100, 200, 400]"
                                   :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
            <el-col :span="9" style="height: 93.7%;">
                <div>
                    <el-button-group>
                        <el-button @click="userStockSubmit" style="width: 70px;"  type="primary">提交</el-button>
                        <el-button @click="userStockCancle"  style="width: 70px;"  type="warning">取消</el-button>
                    </el-button-group>
                </div>
                <el-tree :data="stocInfo" show-checkbox
                         :default-expand-all="true"
                         :default-checked-keys="userStock"
                         node-key="id"
                         ref="stocInfoTree"
                         :props="defaultProps"
                         :highlight-current="true"
                         @check-change="stockCheck">
                </el-tree>
            </el-col>

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
                total: 0,
                page: 1,
                pageSize: 20,
                loading: false,
                users: [],
                userStockObj:{},
                hosId:'',
                corpName:'',
                modelTitle: "添加用户",
                editFormVisible: false,
                editLoading: false,
                editFormRules: {
                    userCode: [
                        {required: true, message: '请输入账号', trigger: 'blur'}
                    ],
                    orgCode: [
                        {required: true, message: '请选择机构', trigger: 'blur'}
                    ]
                },
                orgInfo: [],
                orgInfoProps: {
                    children: 'children',
                    label: 'label',
                    id: 'id',
                    pid: 'pid'
                },
                orgInfo4cascader: [],
                orgSltProps: {
                    value: 'code',
                    label: 'label'
                },
                errors: {
                    userCodeErr: '',
                    orgCodeErr: ''
                },
                stocInfo: [{
                    id: null,
                    stocName: '',
                    children: [{
                        id: null,
                        stocName: '一级库房',
                        children: []
                    }, {
                        id: null,
                        stocName: '二级库房',
                        children: []
                    }, {
                        id: null,
                        stocName: '三级库房',
                        children: []
                    }]
                }],
                userStock:[],
                currentUser:{},
                checkedStocks:[],
                defaultProps: {
                    children: 'children',
                    label: 'stocName'
                }
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
                o.userStock = [];
                o.page = pIndex;
                var param = { orderBy: "", queryObject: { ename: o.filters.ename, orgId: o.filters.orgId }, page: o.page, rows: 20 };
                o.loading = true;
                o.axios.post(`/platformService/sys/user/userlistByUnit`, param).then(res => {
                    o.loading = false;
                    o.users = res.data.data.data;
                    o.total = res.data.data.total;
                    if(o.users.length==0){
                        o.userStock = [];
                        this.userStock = [];
                        o.$refs.stocInfoTree.setCheckedKeys([]);
                    }else {
                        o.$nextTick(function () {
                            o.setCurrent(o.users[0]);
                        });
                    }
                }, err => {
                    o.loading = false;
                });
            },
            setCurrent(row) {
                this.currentUser = row;
                this.$refs.usersTable.setCurrentRow(row);
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
            listStocInfo:function (corpId) {
                this.axios.post('/spdHERPService/deptMgr/stocInfo/listStocInfo',corpId).then(res => {
                    res.data.data.forEach((row)=>{
                        if(row.stocLevel =='一级库房'){
                            this.stocInfo[0].children[0].children.push(row);
                        }else if(row.stocLevel =='二级库房'){
                            this.stocInfo[0].children[1].children.push(row);
                        }else {
                            this.stocInfo[0].children[2].children.push(row);
                        }
                    });
                });
            },
            queryStaffStock:function (row, event, column) {
                this.currentUser = row;
                if(this.currentUser){
                    this._queryStaffStock();
                }
            },
            _queryStaffStock:function () {
                let o = this;
                o.userStock = [];
                o.axios.post('/spdHERPService/deptMgr/stocInfo/queryStaffStock',o.currentUser.userId).then(res => {
                    res.data.data.forEach((row)=>{
                        o.userStock.push(row.stockId);
                    });
                    if(o.userStock.length>0){
                        o.$refs.stocInfoTree.setCheckedKeys(o.userStock);
                    } else if (o.userStock.length === 0) {
                        o.$refs.stocInfoTree.setCheckedKeys([]);
                    }
                });
            },
            stockCheck:function (nodeObj,checked,childrenChecked) {
            },
            userStockSubmit:function () {
                let o = this;
                if(!o.currentUser){
                    this.$message.error('没有选择库房管理人员，无法保存！');
                    return;
                }
                o.checkedStocks = this.$refs.stocInfoTree.getCheckedKeys(true);
                o.userStockObj = {
                    userId : o.currentUser.userId,
                    hosId : o.hosId,
                    stockIds : o.checkedStocks
                };
                o.axios.post(`/spdHERPService/deptMgr/stocInfo/userStocSubmit`, o.userStockObj).then(res => {
                    if (res.data.code == "0") {
                        this.$message({message: '操作成功！', type: 'success'});
                    }else {
                        this.$message.error('操作失败！');
                    }
                }, err => {
                });
            },
            userStockCancle:function () {
                this._queryStaffStock();
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
            }
        },
        mounted() {
            this.stocInfo[0].stocName = this.user.corpName;
            this.hosId = this.user.corpId;
            this.listStocInfo(this.hosId);
            this.getUser(1);
            this.getOrgsByUnit();
        }
    };
</script>

<style>
    #staff-storehouse-mgr .el-tree-node__label {
        font-size: 12px;
    }
    #staff-storehouse-mgr .el-table {
        font-size: 12px;
    }
    #staff-storehouse-mgr .el-tree {
        height: 100%;
    }
    #staff-storehouse-mgr .staffMgr-tableheight{
        height: 100%;
    }
    #staff-storehouse-mgr .staffPage{
        height: 3.7%;
        border: 1px solid #d1dbe5;
        border-top: 0px;
    }
    #staff-storehouse-mgr .el-table__body-wrapper{
        overflow-x: hidden;
    }
    #deptFilter .el-input__inner{
        margin-bottom: 1px;
        height: 30px;
    }
</style>