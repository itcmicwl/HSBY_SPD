<!--
    import cascaderOrg from '../../components/cascaderOrg.vue';
    components:{cascaderOrg},
    <cascaderOrg label_text = "好气哦" v-on:orgChange="orgChangeEvt"  :deptId="'org-73'"></cascaderOrg>
// obj为sysOrgVo
orgChangeEvt(obj){}
-->
<template>
    <section>
        <el-form-item v-bind:label="label_text">
            <el-cascader placeholder="请选择科室"
                v-model="selectedOrg"  
                :show-all-levels="false" 
                :options="orgInfo4cascader" 
                filterable 
                :change-on-select="true"  
                :props="orgProps"
                @change='test'>
            </el-cascader>
        </el-form-item>
    </section>
</template>
<script>
var temp = [];
var treevalue = [];
function getTreeArray(data, id)
{
    if(treevalue.length == 0){
        treevalue = data;
    }
    for (var i in data) {
        if (data[i].id == id) {
            if(data[i].pid){
                temp.push(data[i].pid);
                temp.push(id);
                getTreeArray(treevalue, data[i].pid);
                break; 
            }else{
                temp.push(data[i].id);
                break;
            }
        } else {
            getTreeArray(data[i].children, id);
        }
    }
}
export default {
    data() {
        return {
            orgInfo4cascader:[],
            orgProps: {
                value: 'id',
                label: 'label'
            },
            selectedOrgId:'',
            sysOrgVo:{}
        };
    },
    props: ['label_text', 'hosId', 'deptId'],
    methods: {
        getOrgsByUnit:function () {
            var param = {};
            this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res=>{
                this.orgInfo4cascader = res.data.data.children[0].children;
            });
        },
        test(){
            this.axios.post(`/platformService/sys/org/getTheOrg`, {id : this.selectedOrgId}).then(res=>{
                this.sysOrgVo = res.data.data;
                this.$emit('orgChange', this.sysOrgVo);                
            });
        }
    },
    computed: {
        selectedOrg: {
                get: function () {
                    let res = [];
                    getTreeArray(this.orgInfo4cascader,this.deptId);
                    if(temp.length > 1){
                        res.push(temp[temp.length - 1]);
                        for(var i = 1; i <= (temp.length - 1) / 2; i++){
                            res.push(temp[temp.length - i * 2]);
                        }
                    }else{
                        res.push(temp[temp.length - 1]);
                    }
                    return res;
                },
                set: function (orgId) {
                    let res = '';
                    if (orgId.length > 0) {
                        res = orgId[orgId.length - 1];
                    }
                    this.selectedOrgId = res;
                }
            }
    },
    mounted() {
        this.getOrgsByUnit();        
    }
};
</script>