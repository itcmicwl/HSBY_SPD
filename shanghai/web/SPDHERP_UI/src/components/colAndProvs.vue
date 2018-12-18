<template>
    <el-col style="height:100%">
        <el-input placeholder="供应商名称" icon="search" v-model="provName" ></el-input>
        <el-tree ref="provTree" :data="provData" :props="defaultProps" @node-click="provChange" default-expand-all node-key="provId" highlight-current :filter-node-method="search" :current-node-key="currentKey"></el-tree>
    </el-col>
</template>

<script>
export default {
 data() {
        return {
            provData:[],
            currentKey:'',
            loading : true,
            defaultProps:{
                children: 'children',
                label: 'provName'
            },
            provName:""
        };
    },
    props: ['hosId'],
    watch:{
        hosId(newVal,oldVal){
            if(newVal && newVal != oldVal){
                this.getProvs();
            }
        },
        provName(val){
             this.$refs.provTree.filter(val);
        }
    },
    methods: {
         search(value, data) {
            if (!value) return true;
            return data.provName.indexOf(value) !== -1;
        },
        getProvs() {
            var _this = this;
            var param = {
                hosId: this.hosId,
                colName:this.provName,
                provName:this.provName
            };
            this.loading = true;
            this.axios.post('/spdHERPService/provMgr/distriRelation/getHosColProvs', param).then(res => {
                _this.loading = false;
                _this.praseData(res.data.data);
            }, err => {
                _this.loading = false;
            });       
        },
        praseData(lst){
            if(!Array.isArray(lst) || lst.length == 0){
                return ;
            }
            var allCids = lst.map(x=>x.collectorId);
            var arrCid = new Set(allCids);
            for(let key of arrCid){
                var node = {provId:key,provName:''};
                var children = lst.filter(x=>x.collectorId == key);
                node.provName = children[0].colName;
                node.kind = -1;
                node.isUseSubprov = -1;
                node.children = children;                        
                this.provData.push(node);             
            }
            if(this.provData.length >0){
                this.currentKey=this.provData[0].provId;
                this.$emit("provChange", this.currentKey,this.provData[0]);
            }            
        },
       provChange(d1,node){
           var provId = d1.provId;
           this.$emit("provChange",provId,d1);
       }
    },
    mounted() {
        this.getProvs();
    }
}
</script>

<style>

</style>
