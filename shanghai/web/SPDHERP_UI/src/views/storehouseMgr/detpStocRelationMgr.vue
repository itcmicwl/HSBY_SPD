<template>
    <div id="dept-stoc-relation-mgr" style="height: 100%;">
        <el-row :gutter="5" height="auto" style="height: 100%;">
            <el-col :span="6" style="height: 100%;">
                <div style="padding-bottom: 7px">
                    <el-input 
                        v-model="orgName" 
                        placeholder="请输入科室名称" 
                        icon="search" 
                        >
                    </el-input>
                </div>
                <!--:on-icon-click="searchOrgs"-->
                <el-tree 
                    :data="orgInfo" 
                    node-key="id" 
                    :default-expanded-keys="expandIds" 
                    :props="orgInfoProps" 
                    :highlight-current="true" 
                    @node-click="handleNodeClick"
                    :filter-node-method="filterNode"
                    ref="orgInfoTree">
                </el-tree>
            </el-col>
            <el-col :span="18" style="height: 100%;">
                <div style="padding-bottom: 4px">
                    <el-button-group>
                        <el-button @click="deptRelSave" style="width: 70px;"  type="primary" :disabled="showSave">保存</el-button>
                        <el-button @click="deptRelCancle" style="width: 70px;"  type="warning">取消</el-button>
                    </el-button-group>
                </div>
                <el-tree 
                    :data="orgInfo" 
                    show-checkbox 
                    :check-strictly = "true"
                    :default-expand-all="true" 
                    :default-checked-keys="deptIds" 
                    node-key="id" 
                    ref="deptTree" 
                    :props="orgInfoProps" 
                    :highlight-current="true"
                    :expand-on-click-node="false"
                    :render-content="renderContent">
                </el-tree>
            </el-col>

        </el-row>
    </div>
</template>
<script>
export default {
    data() {
        return {
            baseUrl:'/spdHERPService/baseData/stocInfoMgr/hosDeptPurStock',
            filters: {
                orgName: '',
                corpId:''
            },
            expandIds: [],
            orgInfo: [],
            orgInfoProps: {
                children: 'children',
                label: 'label',
                id: 'id',
                pid: 'pid'
            },
            checkedDept: [],
            currentDeptId:'',
            deptIds:[],
            showSave:true,
            // 默认请购科室id
            defaultDeptId:'',
            // 快速过滤
            orgName:''
        }
    },
    watch:{
        orgName(val) {
            this.$refs.orgInfoTree.filter(val);
        }
    },
    methods: {
        renderContent(h, { node, data, store }) {
            if(data.id.indexOf('org') == -1 || this.deptIds.indexOf(data.id) == -1){
                // 当不是请购科室时，也应该不显示设置默认按钮
                return (<span>{node.label}</span>);
            }
            if(data.id == this.defaultDeptId){
                return (
                <span style="flex: 1; display: flex; align-items: center; justify-content: space-between; padding-right: 8px;width:100%;">
                    <span style = "color:red">
                        {node.label}
                    </span>
                    <span>默认请购科室</span>
                </span>);
            }
            return (
            <span style="flex: 1; display: flex; align-items: center; justify-content: space-between; padding-right: 8px;width:100%;">
                <span>
                    <span>{node.label}</span>
                </span>
                <span>
                    <el-button style="font-size: 12px;" type="text" on-click={ () => this.setDefaultDept(data) }><font  color="red">设为默认</font></el-button>
                </span>
            </span>);
        },
        setDefaultDept(data){
            if(!this.currentDeptId){
                this.$message("请选择目标科室！");
                return;
            }
            var param = {
                hosId: this.filters.corpId,
                deptId : this.currentDeptId,
                stockId : data.id
            };
            this.axios.post(this.baseUrl + '/setDefaultDept', param).then(res => {
                if (res.data.code == "0") {
                    this.$message({ message: '操作成功！', type: 'success' });
                    this.queryDeptRelation();
                } else {
                    this.$message.error('操作失败！');
                }
            }, err => {
            });
        },
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        getOrgsByUnit: function (unitId) {
            var param = {};
            this.loading = true;
            this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res => {
                this.orgInfo = res.data.data.children;
                this.orgInfo[0].disabled = true;
                this.expandIds = [this.orgInfo[0].id];
                this.loading = false;
            });
        },
        // 点击即查询
        handleNodeClick:function(item){
            // 说明当前结点不是医院或者供应商
            if(item.id.indexOf('org') == -1){
                this.showSave = true;
            }else{
                this.showSave = false;
            }
            this.deptIds = [];
            this.currentDeptId = item.id;
            this.queryDeptRelation();
            
        },
        queryDeptRelation:function(){
            this.deptIds = [];
            this.defaultDeptId = '';
            var param = { hosId: this.filters.corpId, deptId: this.currentDeptId }
            this.axios.post(this.baseUrl + '/queryDeptRelation', param).then(res => {
                res.data.data.forEach((row)=>{
                    this.deptIds.push(row.stockId);
                    if(row.isDefault == "1"){
                        this.defaultDeptId = row.stockId;
                    }
                });
                this.$refs.deptTree.setCheckedKeys(this.deptIds);
            });
        },
        // 保存
        deptRelSave: function () {
            this.checkedDept = this.$refs.deptTree.getCheckedKeys();
            // for(var key in this.checkedDept){
            //     if(this.checkedDept[key].indexOf('company') > -1){
            //         this.checkedDept.splice(key, 1);
            //     }
            // }
            var param = {
                hosId: this.filters.corpId,
                deptId : this.currentDeptId,
                stockIds : this.checkedDept,
                defaultDeptId:this.defaultDeptId
            };
            this.axios.post(this.baseUrl + '/deptRelationSave', param).then(res => {
                if (res.data.code == "0") {
                    this.$message({ message: '操作成功！', type: 'success' });
                    this.queryDeptRelation();
                } else {
                    this.$message.error('操作失败！');
                }
            }, err => {
            });
        },
        // 取消
        deptRelCancle: function () {
            this.queryDeptRelation();
        },
        // 查询
        searchOrgs: function () {
            this.filters.orgName = this.orgName;
            var p = this.filters.orgName;
            let param = { cname: p };
            this.loading = true;
            this.axios.post(`/platformService/sys/org/searchOrgs`, param).then(res => {
                this.loading = false;
                var ids = [];
                for (var i = 0; i < res.data.data.length; i++) {
                    ids[i] = res.data.data[i].id;
                }
                this.expandIds = ids;
            });
            this.loading = false;
        }
    },
    mounted() {
        this.filters.corpId = this.user.corpId;
        this.getOrgsByUnit();
    }
};
</script>

<style>
#dept-stoc-relation-mgr .el-tree-node__label {
    font-size: 12px;
}

#dept-stoc-relation-mgr .el-table {
    font-size: 12px;
}

#dept-stoc-relation-mgr .el-tree {
    min-height: calc(100% - 80px);
}
#dept-stoc-relation-mgr .el-tree-node__content{
    display:flex;
}
</style>