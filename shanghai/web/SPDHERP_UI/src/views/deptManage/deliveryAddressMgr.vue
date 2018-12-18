<template>
    <div id="delivery-address-mgr" style="height: 99.5%;">
       <el-col :span="5" style="height: calc(99% - 31px);">
            <div style="padding-bottom: 7px">
                <el-input 
                    v-model="orgName" 
                    placeholder="请输入科室名称" 
                    icon="search" 
                    >
                </el-input>
            </div>
            <el-tree
                :data="orgInfo" 
                node-key="id"
                :default-expand-all="true"
                :expand-on-click-node="false"
                :default-expanded-keys="expandIds" 
                :props="orgInfoProps" 
                :highlight-current="true" 
                @node-click="handleNodeClick"
                :filter-node-method="filterNode"
                ref = "orgInfoTree">
            </el-tree>
        </el-col>
        <!--工具条-->
        <el-col :span="19" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
            <div style="margin-bottom: 4px;">
                <el-form :inline="true" :model="filters" ref="filterForm">
                    <el-form-item label="">
                        <el-input v-model="filters.lxr" placeholder="收货人" ></el-input>
                    </el-form-item>
                    <el-form-item label="">
                        <el-input v-model="filters.address" placeholder="收货地址" ></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="getDeliveryAddrList(1)" >查询</el-button>
                        <el-button type="primary" @click="deliveryAddrReset('filterForm')" >重置</el-button>
                        <el-button type="success" @click="deliveryAddrEdit(null)"  :disabled="addDisabled">新增</el-button>
                    </el-button-group>
                </el-form>
            </div>
        <!--列表-->
        <template>
            <el-table :data="deliveryInfos" highlight-current-row v-loading="loading" border style="width: 100%;" fit  class="delivery-tableheight">
                <el-table-column type="index" label="序号" align="center"></el-table-column>
                <el-table-column prop="lxr" label="收货人" align="center" width="80"></el-table-column>
                <el-table-column prop="lxrPhone" label="收货人电话" align="center" width="100"></el-table-column>
                <el-table-column prop="province" label="省/直辖市" header-align="center" min-width="100"></el-table-column>
                <el-table-column prop="city" label="市" header-align="center" min-width="80"></el-table-column>
                <el-table-column prop="area" label="区/县" header-align="center" min-width="80"></el-table-column>
                <el-table-column prop="address" label="详细地址" header-align="center" min-width="220" ></el-table-column>
                <el-table-column prop="postCode" label="邮政编码" align="center" width="60" ></el-table-column>
                <el-table-column prop="isDefaulte" :formatter="formatter" width="160" label="是否默认收货地址" align="center"></el-table-column>
                <el-table-column prop="sendSmp" :formatter="formatter" width="160" label="是否已经上传阳光采购平台" align="center"></el-table-column>
                <el-table-column label="操作" width="195" align="center">
                    <template slot-scope="scope">
                        <el-button-group>
                            <el-button type="primary" size="mini" @click="deliveryAddrEdit(scope.row)" icon="edit">编辑</el-button>
                            <el-button type="danger" size="mini" @click="deliveryAddrDel(scope.row)" icon="delete">删除</el-button>
                            <el-button type="primary" size="mini" @click="setDefaultAddr(scope.row)" icon="plus" v-show="scope.row.isDefaulte != '1'">设为默认</el-button>
                            <el-button type="success" size="mini" @click="sendYg(scope.row)" icon="upload" v-show="scope.row.sendSmp != '1'">上传阳光采购平台</el-button>
                        </el-button-group>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页工具条-->
            <el-col :span="24" class="toolbar">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                </el-pagination>
            </el-col>
        </template>
        </el-col>
        <!--编辑界面-->
        <el-dialog :title="modelTitle"  close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false">
            <el-form :model="deliveryInfo" label-width="120px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
                <el-tag type="danger" v-if="this.prompt != ''">{{this.prompt}}</el-tag>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="收货人" prop="lxr">
                            <el-input v-model="deliveryInfo.lxr"  :error="errors.lxrErr"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="收货人电话" prop="lxrPhone">
                            <el-input v-model="deliveryInfo.lxrPhone"  :error="errors.lxrPhoneErr"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="收货地址">
                            <el-select v-model="province" placeholder="请选择省/直辖市"  @change="proChange">
                                <el-option v-for="item in ps" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        <!-- </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item> -->
                            <el-select v-model="city" placeholder="请选择市"  @change="cityChange">
                                <el-option v-for="item in cs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        <!-- </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item> -->
                            <el-select v-model="area" placeholder="请选择区/县" >
                                <el-option v-for="item in as" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        <!-- </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item> -->
                            <el-input v-model="deliveryInfo.address"  placeholder="详细地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="邮政编码" prop="postCode">
                            <el-input v-model="deliveryInfo.postCode" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否默认地址">
                            <el-radio-group v-model="isDefaulte"  @change="isDefaultChange">
                                <el-radio-button label="1">是</el-radio-button>
                                <el-radio-button label="0">否</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <!-- <el-col :span="12">
                        <el-form-item label="是否启用">
                            <el-radio-group v-model="status" >
                                <el-radio-button label="1">启用</el-radio-button>
                                <el-radio-button label="0">停用</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col> -->
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
export default {
    data() {
        return {
            addDisabled:true,
            baseUrl:'/spdHERPService/deptMgr/receiveAddr',
            filters: {
                lxr: '',
                lxrPhone: '',
                address: ''
            },
            total: 0,
            page: 1,
            pageSize: 20,
            loading: false,
            deliveryInfos: [],
            // 编辑
            deliveryInfo: {
                // 阳光采购平台
                sendSmp:'0',
                isDefaulte:'0',
                province:'',
                city:'',
                area:'',
                deptName:'',
                postCode:''
            },
            isDefaulte: '1',
            modelTitle: "",
            editFormVisible: false,
            editLoading: false,
            editFormRules: {
                lxr: [
                    { required: true, message: '请输入收货人姓名', trigger: 'blur' }
                ],
                lxrPhone: [
                    { required: true, message: '请输入收货人电话', trigger: 'blur' }
                ],
                postCode: [
                    { required: true, message: '请输入邮政编码', trigger: 'blur' }
                ]
            },
            province: '',
            city: '',
            area: '',
            ps: [],
            cs: [],
            as: [],
            errors: {
                lxrErr: '',
                lxrPhoneErr:'',
            },
            // 
            hosId:'',
            deptId:'',
             // 树
            expandIds: [],
            orgInfo: [],
            orgInfoProps: {
                children: 'children',
                label: 'label',
                id: 'id',
                pid: 'pid'
            },
            // 快速查询
            orgName:'',
            // 默认上传提示
            prompt:"",
            deptName:""
        }
    },
     watch:{
        orgName(val) {
            this.$refs.orgInfoTree.filter(val);
        }
    },
    methods: {
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
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
        },
        getOrgsByUnit: function (unitId) {
            var param = {};
            this.loading = true;
            this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res => {
                this.orgInfo = res.data.data.children;
                this.expandIds = [this.orgInfo[0].id];
                this.loading = false;
            });
        },
        // 点击即查询
        handleNodeClick:function(item){
            if(item.id.indexOf('org') == -1){
                this.addDisabled = true;
            }else{
                this.addDisabled = false;
            }
            this.deptId = item.id;
            this.deptName = item.label;
            this.getDeliveryAddrList(1);
        },
        //获取列表
        getDeliveryAddrList: function (pIndex) {
            this.page = pIndex;
            var param = {
                orderBy: "",
                queryObject: {
                    hosId:this.hosId,
                    deptId:this.deptId,
                    lxr: this.filters.lxr,
                    address: this.filters.address
                },
                page: this.page,
                rows: this.pageSize
            };
            this.loading = true;
            this.axios.post(this.baseUrl + '/getReceiveAddressList', param).then(res => {
                this.loading = false;
                if (res.data.code == "0") {
                    this.deliveryInfos = res.data.data.data;
                    this.total = res.data.data.total;
                }
                else {
                    this.$message.error(res.data.msg);
                }
            }, err => {
                this.loading = false;
            });
        },
        // 分页事件
        handleCurrentChange(val) {
            this.page = val;
            this.getDeliveryAddrList(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getDeliveryAddrList(this.page);
        },
        formatter(row, column) {
            var strValue = row[column.property] > 0 ? row[column.property].toString() : '';
            if (column.property == 'isDefaulte') {
                strValue == '1' ? strValue = '是' : strValue = '否';
            }else if(column.property == 'sendSmp'){
                strValue == '1' ? strValue = '是' : strValue = '否';
            }
            return strValue;
        },
        proChange(val) {
            // 先清空市区信息,再加载数据
            if(!this.deliveryInfo.city || !this.deliveryInfo.area){
                this.city = '';
                this.area = '';
            }
            this.cs = [];
            this.as = [];
            var param = {pid : val};
            this.axios.post(this.baseUrl + '/queryBaseArea', param).then(res => {
                if (res.data.code == "0") {
                    let addr = res.data.data;
                    for(var i = 0; i < addr.length; i++){
                        this.cs.push({
                            value : addr[i].id,
                            label : addr[i].cname
                        });
                    }
                }
                else {
                    this.$message.error(res.data.msg);
                }
            }, err => {
            });

        },
        // 可以定义一个变量减少循环次数this.province
        cityChange(val) {
            // 清空区信息
            if(!this.deliveryInfo.area){
                this.area = '';
            }
            this.as = [];
            var param = {pid : val};
            this.axios.post(this.baseUrl + '/queryBaseArea', param).then(res => {
                if (res.data.code == "0") {
                    let addr = res.data.data;
                    for(var i = 0; i < addr.length; i++){
                        this.as.push({
                            value : addr[i].id,
                            label : addr[i].cname
                        });
                    }
                }
                else {
                    this.$message.error(res.data.msg);
                }
            }, err => {
            });
        },
        loadAddr(val){
            // 加载三级联动数据
            var param = {pid : val};
            this.axios.post(this.baseUrl + '/queryBaseArea', param).then(res => {
                if (res.data.code == "0") {
                    let addr = res.data.data;
                    for(var i = 0; i < addr.length; i++){
                        this.ps.push({
                            value : addr[i].id,
                            label : addr[i].cname
                        });
                    }
                }
                else {
                    this.$message.error(res.data.msg);
                }
            }, err => {
            });

        },
        // 编辑
        deliveryAddrEdit(item) {
            this.errors.lxrErr = '';
            this.errors.lxrPhoneErr = '';
            if (item) {
                let deliveryInfo = Object.assign({}, item);
                this.deliveryInfo = deliveryInfo;
                this.modelTitle = "修改收货地址";
                this.isDefaulte = this.deliveryInfo.isDefaulte;
                this.province = this.deliveryInfo.province;
                this.city = this.deliveryInfo.city;
                this.area = this.deliveryInfo.area;
                this.prompt = "";
            } else {
                this.deliveryInfo = {};
                this.deliveryInfo.deptName = this.deptName;
                this.isDefaulte = '0';
                this.deliveryInfo.isDefaulte = '0';
                this.deliveryInfo.sendSmp = '0';
                this.deliveryInfo.postCode = "000000";
                this.province = '';
                this.city = '';
                this.area = ''
                this.modelTitle = "添加收货地址";
                this.prompt = "添加收货地址, 默认上传阳光采购平台";
            }
            this.editFormVisible = true;
        },
        isDefaultChange(val){
        },
        // 删除
        deliveryAddrDel(deliveryInfo) {
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.loading = true;
                this.axios.post(this.baseUrl + "/delete", deliveryInfo).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.loading = false;
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        this.$message.error(res.data.msg);

                    }
                    this.getDeliveryAddrList(this.page);
                }, err => {
                    this.loading = false;
                });
            }).catch(() => {

            });
        },
        // 设为默认
        setDefaultAddr(deliveryInfo) {
            this.loading = true;
            this.axios.post(this.baseUrl + "/setDefaultAddr", deliveryInfo).then(res => {
                this.loading = false;
                if (res.data.code == "0") {
                    this.loading = false;
                    this.$message({ message: '操作成功！', type: 'success' });
                } else {
                    this.$message.error(res.data.msg);
                }
                this.getDeliveryAddrList(this.page);
            }, err => {
                this.loading = false;
            });
        },
        // 上传阳光采购平台
        sendYg(deliveryInfo){
            this.loading = true;
            this.axios.post(this.baseUrl + "/sendYg", deliveryInfo).then(res => {
                this.loading = false;
                if (res.data.code == "0" && res.data.data == "00000") {
                    this.loading = false;
                    this.$message({ message: '操作成功！', type: 'success' });
                } else {
                    this.$message.error('上传失败');
                }
                this.getDeliveryAddrList(this.page);
            }, err => {
                this.loading = false;
            });
        },
        // 编辑页面提交
        editSubmit: function () {
            var _this = this;
            var form = this.$refs.editForm;
            const h = this.$createElement;
            var arr = new Array();
            this.deliveryInfo.isDefaulte = this.isDefaulte;
            if(this.province != this.deliveryInfo.province && this.city != this.deliveryInfo.city && this.area != this.deliveryInfo.area){
                this.ps.forEach(pitem => {
                    if(pitem.value == this.province){
                        this.deliveryInfo.province = pitem.label;
                        if(!this.cs.pid){ // 特别行政区
                            this.deliveryInfo.areaCode = pitem.value;
                        }
                    }
                });
                this.cs.forEach(citem => {
                    if(citem.value == this.city){
                        this.deliveryInfo.city = citem.label;
                    }
                });
                this.as.forEach(aitem => {
                    if(aitem.value == this.area){
                        this.deliveryInfo.area = aitem.label;
                        this.deliveryInfo.areaCode = aitem.value;
                    }
                });
            }
            form.validate((valid) => {
                if (valid) {
                    let _this = this;
                    _this.editLoading = true;
                    let serviceUrl = this.deliveryInfo.id ? this.baseUrl + "/update" : this.baseUrl + "/insert";
                    _this.deliveryInfo.deptId = _this.deptId;
                    _this.deliveryInfo.hosId = _this.hosId;
                    this.axios.post(serviceUrl, _this.deliveryInfo).then(res => {
                        _this.editLoading = false;
                        if (res.data.code == "0") {
                            this.$message({ message: '操作成功！', type: 'success' });
                            _this.editFormVisible = false;
                            _this.getDeliveryAddrList(_this.page);
                        } else if (res.data.code == "-122") {
                            let err = res.data.validateErrors.fieldErrors[0];
                            if (err.field == "lxr") {
                                this.errors.lxrErr += err.message;
                                arr.push(h('span', null, err.message));
                            }else if(err.field == 'lxrPhone'){
                                this.errors.lxrPhoneErr += err.message;
                                arr.push(h('span', null, err.message));
                            }else {
                                arr.push(h('span', null, err.message));
                            }
                            _this.$msgbox({ title: "错误", type: "error", message: h('p', null, arr) });
                        }else {
                            this.$message.error('操作失败');
                        }
                    }, err => {
                        _this.editLoading = false;
                    });
                } else {
                    var fields = form.fields;
                    var msg = "";
                    fields.forEach(f => {
                        if (f.validateState == "error") {
                            msg += "</br>" + f.validateMessage;
                            if (arr.length > 0) arr.push(h('br', null, null));
                            arr.push(h('span', null, f.validateMessage));
                        }
                    });
                    _this.$msgbox({ title: "错误", type: "error", message: h('p', null, arr) });
                }
            });
        },
        //  重置
        deliveryAddrReset(filterForm){
            this.filters.lxr = null;
            this.filters.lxrPhone = null;
            this.filters.address = null;
            this.getDeliveryAddrList(1);
        }
    },
    mounted() {
        this.hosId = this.user.corpId;
        this.getOrgsByUnit();
        this.loadAddr(null);
    }
};
</script>
<style>
#delivery-address-mgr .delivery-tableheight {
    height: calc(100% - 60px);
}
#delivery-address-mgr .el-form-item__error {
    display: none !important;
}
</style>