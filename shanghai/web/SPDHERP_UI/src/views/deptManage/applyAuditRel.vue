<template>
    <div id="apply-audit-rel" style="height: 100%;">
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
            <el-col :span="9" style="height: 94.7%;">
                <el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" ref="usersTable"
                          border fit  height="outter" class="staffMgr-tableheight" @current-change="queryStaffStock">
                    <el-table-column prop="orgName" header-align="center" width="100" label="科室">
                    </el-table-column>
                    <el-table-column prop="ename" header-align="center" width="150" label="职工姓名">
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
            <el-col :span="9" style="height: 93.7%;">
                <div>
                    <el-button-group>
                        <el-button @click="userStockSubmit" style="width: 70px;"  type="primary">提交</el-button>
                        <el-button @click="userStockCancle"  style="width: 70px;"  type="warning">取消</el-button>
                    </el-button-group>
                </div>
                <el-checkbox-group v-model="checkList">
                    <el-checkbox label="10" >普耗</el-checkbox>
                    <el-checkbox label="20"  >高值</el-checkbox>
                    <el-checkbox label="30"  >办公</el-checkbox>
                    <el-checkbox label="40"  >手术包</el-checkbox>
                </el-checkbox-group>
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
                relation:[],
                rel:{
                    id:null,
                    userId:null,
                    purMode:null
                },
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
                deptGoodsAudit:[],
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
                checkList:['10'],
                stocInfo:[],
                userStock:[],
                currentUser:{},
                checkedStocks:[],
                defaultProps: {
                    children: 'children',
                    label: 'label',
                    id: 'id',
                    pid: 'pid'
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
                this.filters.orgId = data.id;
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
            getOrgsByUser:function (userId) {
                var param = {};
                this.loading = true;
                this.axios.post(`/platformService/sys/org/getOrgsByUnit`, { }).then(res => {
                    this.stocInfo = res.data.data.children;
                    this.stocInfo[0].disabled = true;
                    this.expadoIds=[this.stocInfo[0].id];
                    this.loading = false;
                });
            },
            listStocInfo:function (corpId) {
            },
            queryStaffStock:function (row, event, column) {
                this.relation=[];
                this.currentUser = row;
                this._queryStaffStock();
            },
            _queryStaffStock:function () {
             //   let o = this;

//                o.userStock = [];
//                o.deptGoodsAudit = [];
//                var param={};
//                param.auditer=o.currentUser.userId;
//                o.axios.post('/spdHERPService/deptMgr/deptGoodsAuditAelation/select',param).then(res => {
//                    res.data.data.forEach((row)=>{
//                        o.userStock.push(row.deptId);
//                    });
//				//	o.$refs.stocInfoTree.setCheckedKeys(o.userStock);
//                });

                this.checkList = []

                var param={};
               // param.auditer=o.currentUser.userId;
                param.userId =this.currentUser.userId
                this.axios.post('/spdHERPService/deptMgr/applyAuditRel/list',param).then(res => {
                    this.checkList = []
                    res.data.data.forEach((row)=>{
                        this.checkList.push(""+row.purMode);
                    });

                });

            },
            iterationNode:function(nodeObj,checked,childrenChecked){
                
            },
            stockCheck:function (nodeObj,checked,childrenChecked) {
                if(checked){
                //判断relation的第一个元素的科室ID是否为空
                if(this.relation.length===1 && this.relation[0].deptId===null){
                    this.relation.pop();
                }
                var temp={};
                temp.hosId=nodeObj.code.substring(0,nodeObj.code.lastIndexOf(":"));
                temp.deptId=nodeObj.id;
                temp.auditer=this.currentUser.userId; 
                    this.relation.push(temp);
                }
                else{
                    for(var i=0;i<this.relation.length;i++){
                        if(this.relation[i].deptId===nodeObj.id){
                            this.relation.splice(i,1);
                        }
                    }
                }
            },
            userStockSubmit:function () {
                //如果relation为空 则设置
                var relList =[];
                this.checkList.forEach(v=>{
                    var rel = Object.assign({}, this.rel)
                     rel.userId= this.currentUser.userId
                    rel.purMode=Number(v);
                    relList.push(rel)
                })

                if(relList.length==0){
                    this.$message.error('请勾选相应的类型！')
                    return
                }
                this.axios.post(`/spdHERPService/deptMgr/applyAuditRel/addRels`,relList).then(res => {
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
            this.hosId = this.user.corpId;
            this.getUser(1);
            this.getOrgsByUnit();
            this.getOrgsByUser();
        }
    };
</script>

<style>
    #apply-audit-rel .el-tree-node__label {
        font-size: 12px;
    }
    #apply-audit-rel .el-table {
        font-size: 12px;
    }
    #apply-audit-rel .el-tree {
        height: 100%;
    }
    #apply-audit-rel .staffMgr-tableheight{
        height: 100%;
    }
    #apply-audit-rel .staffPage{
        height: 3.7%;
        border: 1px solid #d1dbe5;
        border-top: 0px;
    }
    #deptFilter .el-input__inner{
        margin-bottom: 1px;
        height: 30px;
    }
</style>