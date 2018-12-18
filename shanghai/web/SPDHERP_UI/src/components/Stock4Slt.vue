<template>
    <el-select v-model="stockIdM" placeholder="请选择" :size="sSize || 'mini'">
        <el-option-group v-for="group in stckInfos" :key="group.label" :label="group.label">
            <el-option v-for="item in group.children" :key="item.id" :label="item.stocName" :value="item.id">
            </el-option>
        </el-option-group>
    </el-select>
</template>

<script>
export default {
    data() {
        return {
            loading: false,
            stckInfos: [
                { id: 0, value: 0, label: "一级库房", children: [] },
                { id: 1, value: 1, label: "二级库房", children: [] },
                { id: 2, value: 2, label: "三级库房", children: [] }
            ],
            stockList:[],
            stockIdM:this.value
        };
    },
    watch: {
        deptId(newVal, oldVal) {
            if (newVal && newVal != oldVal) {
                this.getStockByDeptChange();
            }
        },       
        stockIdM(newVal, oldVal){
            var stock = this.stockList.find(item=>item.id == newVal);
             this.setCurrentSlt(stock);
        }
    },
    props: ['hosId', 'deptId','value','sSize'],
    methods: {        
        getStocsByParam: function(modelVal) {
            var _this = this;            
            let param = { hosId: this.hosId, deptId: this.deptId };
            this.axios.post(`/spdHERPService/baseData/stocInfoMgr/stocInfo/getStocsByParam`, param).then(res => {
                _this.stockList = res.data.data.stocInfoList;
                if(_this.stockList.length >0){
                    _this.stckInfos.forEach(item=>{item.children = _this.stockList.filter(o=>o.stocLevel == item.label);});
                    if(modelVal){
                        _this.stockIdM = modelVal;
                    }else{
                        _this.stockIdM = _this.stockList[0].id
                    }
                }               
            });
            
        },
        setCurrentSlt(stock){
            this.$emit('input', stock.id);
            this.$emit('change', stock);
        },
        getStockByDeptChange() {
            this.getStocsByParam();           
        }

    },    
    mounted() {
        this.getStocsByParam(this.value);
    }
}
</script>

