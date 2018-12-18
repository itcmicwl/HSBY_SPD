<template>
    <section>    
        <el-popover ref="p_dAddress" placement="top" width="450" v-model="listV">
            <el-table :data="addressLst" ref="sltGoodsT" highlight-current-row v-loading="loading" style="width: 98%;" fit max-height="300" border row-key="id" @current-change="getSltAddress">
                <el-table-column prop="lxr" label="收货人" width="80" sortable>
                </el-table-column>
                <el-table-column prop="lxrPhone" label="电话" width="85">
                </el-table-column>
                <el-table-column label="地址" sortable show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.province}}{{scope.row.city}}{{scope.row.area}}{{scope.row.address}}
                    </template>
                </el-table-column>
                <el-table-column prop="" label="是否默认" width="60">
                    <template slot-scope="scope">
                        <el-switch v-model="scope.row.isDefaulte" size="mini" active-color="#13ce66" active-value="1" inactive-value="0" active-text="是" inactive-text="否" @change="setDefault(scope.row)"></el-switch>
                    </template>
                </el-table-column>
            </el-table>
        </el-popover>
        <span v-popover:p_dAddress class="s_address">
            <i class="fa fa-map-marker"></i>{{btn_text}}</span>
    </section>
</template>

<script>
export default {
    data() {
        return {
            listV: false,
            loading: false,
            addressLst: []
        };
    },
    props: ['btn_text', 'hosId', 'deptId'],
    methods: {
        getAddress() {
            var param = {
                orderBy: "",
                page: 1,
                rows: 100,
                 queryObject: {
                    hosId: this.hosId,
                    deptId:this.deptId,
                    sendSmp:1
                }
            };
            this.loading = true;
           return this.axios.post('/spdHERPService/deptMgr/receiveAddr/getReceiveAddressList', param);
        },
        setAddress(){
            this.getAddress().then(res => {
                this.loading = false;
                this.addressLst = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        getSltAddress(d1) {
            this.$emit('addressChange', d1);
        },
        setDefault(row) {
            var _this = this;
            var param = {
                hosId: row.hosId,
                deptId:row.deptId,
                id:row.id,
                version:row.version
            };
            return this.axios.post('/spdHERPService/deptMgr/receiveAddr/setDefaultAddr', param).then(res=>{
                _this.setAddress();
            },err=>{
                this.$message.error('操作失败!');
            });
        },
        getDefault() {
            var res = null
            if(this.addressLst.length>0){
                res = this.addressLst.find(item => item.isDefaulte == true) || this.addressLst[0];
            }
            return res;
        }
    },
    mounted() {
        var _this = this;
        this.getAddress().then(res => {
            _this.loading = false;
            _this.addressLst = res.data.data.data;
            _this.total = res.data.data.total;
            var defaultAddress = _this.getDefault();
            _this.$emit('addressChange', defaultAddress);
        }, err => {
            _this.loading = false;
        });       
    }
}

</script>

<style lang="scss" scoped>
.s_address {
    text-decoration: underline;
    cursor: pointer;
    i {
        margin-right: 3px;
    }
}
</style>