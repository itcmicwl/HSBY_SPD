<template>
    <div id="storehouse-info" style="height: 100%;">
        <el-row height="auto" style="height: 100%;">
            <el-col :span="6" style="height: 100%;">
                <div style="margin-bottom: 5px">
                    <el-input v-model="filters.stocName" placeholder="请输入库房名称" icon="search" :on-icon-click="searchStocInfo" >
                    </el-input>
                </div>

                <el-tree :data="treeValue" node-key="id" :default-expanded-keys="expandIds" :props="defaultProps" :highlight-current="true" @node-click="handleNodeClick">
                </el-tree>

            </el-col>
            <el-col :span="18" height="auto" style="padding-left:5px;">
                <el-row>
                    <div style="margin-bottom: 7px;">
                        <el-button type="primary"  @click="stocInfoAdd" :disabled="btnFlag">添加</el-button>
                        <el-button type="primary"  @click="stocInfoUpdate">保存</el-button>
                        <el-button type="primary"  @click="stocInfoDel" :disabled="btnFlag">删除</el-button>
                    </div>
                </el-row>
                <el-row>
                    <el-col :span="18">
                        <el-form :model="stocInfo" labelWidth="100px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
                            <el-form-item label="id" v-show="false">
                                <el-input v-model="stocInfo.id" :disabled="true" ></el-input>
                            </el-form-item>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="库房名称：" prop="stocName" :error="errors.stocNameErr">
                                        <el-input v-model="stocInfo.stocName" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="直属科室：" prop="deptName" :error="errors.deptNameErr">
                                        <el-select v-model="deptName" placeholder="请选择直属科室"  @change="deptChange">
                                            <el-option v-for="item in depts" :key="item.value" :label="item.label" :value="item.value"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="负责人：" prop="stocFzr" :error="errors.stocFzrErr">
                                        <el-input v-model="stocInfo.stocFzr" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="联系方式：" prop="stocPhone" :error="errors.stocPhoneErr">
                                        <el-input v-model="stocInfo.stocPhone" ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-form-item label="地址：" prop="address">
                                <el-input v-model="stocInfo.address" ></el-input>
                            </el-form-item>

                            <!--固定信息-->
                            <el-form-item label="所属单位名：" prop="corpName">
                                <el-input v-model="unit.corpName" :disabled="true" ></el-input>
                            </el-form-item>
                            <el-form-item label="所属单位ID：" prop="corpId">
                                <el-input v-model="unit.corpId" :disabled="true" ></el-input>
                            </el-form-item>

                            <el-form-item label="从属库房：" prop="stocLevel" :error="errors.stocLevelErr">
                                <el-select v-model="stocLevel" placeholder="请选择从属库房"  @change="stocChange">
                                    <el-option v-for="item in stocs" :key="item.value" :value="item.value"></el-option>
                                </el-select>
                            </el-form-item>

                            <el-form-item label="货位类型：" prop="shelfType" :error="errors.stocTypeErr">
                              <el-select v-model="stocInfo.shelfType" placeholder="请选择货位类型"  @change="stocShelfChange">
                                <el-option v-for="item in stocTypes" :key="item.val" :label="item.ename" :value="item.val"></el-option>
                              </el-select>
                            </el-form-item>

                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="拼音码：" prop="shortPinyin">
                                        <el-input v-model="stocInfo.shortPinyin" ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col>
                                    <el-form-item label="备注：">
                                        <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="stocInfo.memo"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            baseURL:'/spdHERPService/baseData/stocInfoMgr/stocInfo',
            baseUrl: '/spdHERPService/baseData/stocInfoMgr/stocShelf',
            // 下拉框
            deptName:'',
            stocLevel:'',

            // 查询
            filters: {
                stocName: ''
            },

            // 树组件节点属性
            treeValue: [],
            defaultProps: {
                children: 'children',
                label: 'label',
                id: 'id',
                pid: 'pid',
                code: 'code'
            },
            // 三级库房下拉框
            stocs: [],
            stocNum:{},

            // stocName deptName stocFzr stocPhone
            editFormRules: {
                stocName: [{ required: true, message: '请输入库房名称', trigger: 'blur' }],
                deptName: [{ required: true, message: '请选择直属科室', trigger: 'blur' }],
                stocFzr: [{ required: true, message: '请输入负责人姓名', trigger: 'blur' }],
                stocPhone: [{ required: false, message: '请输入正确的电话号码', trigger: 'blur' }],
                stocLevel: [{ required: true, message: '请选择所属库房', trigger: 'change' }]
            },
            errors: {
                stocNameErr: '',
                deptNameErr: '',
                stocFzrErr: '',
                stocPhoneErr: '',
                stocLevelErr: '',
                stocTypeErr:''
            },
            btnFlag: false,


            // 直属科室
            depts: [],

            stocInfo: {
                deptId: '',
                code:'',
                stocLevel: '',
                deptName: '',
                shelfType:'10',//库房默认货位类型，普通货位
            },

            // 单位信息
            unit: {
                corpId:'',
                corpName:''
            },

            expandIds: [],
            // 货位类型
            stocTypes:[]
        }
    },
    methods: {
        // 加载三级库房
        loadTreeValue:function(nodes){
            for(var i = 0; i < nodes.length; i++){
               if(nodes[i].stocLevel ==  this.stocs[this.stocNum[nodes[i].stocLevel]].value){
                   this.treeValue[0].children[this.stocNum[nodes[i].stocLevel]].children.push({
                        id: nodes[i].id,
                        label: nodes[i].stocName
                   });
               }
            }
        },
        // 库房信息
        selectStocInfo: function () {
            this.axios.post(this.baseUrl + `/selectStocInfo`, {}).then(res => {
                // 树信息
                this.treeValue = res.data.data;
                this.expandIds = [this.treeValue[0].id];
            });
        },
        // 下拉框信息
        getStocs:function(){
            this.axios.post(this.baseUrl + `/selectStocInfo`, {}).then(res => {
                res.data.data[0].children.forEach(item => {
                    this.stocs.push({
                        value:item.label
                    });
                });
                let info = res.data.data[0].children;
                for(var key in info){
                    this.stocNum[info[key].label] = info[key].id;
                }
            });
        },
        // 加载科室信息
        getDeptsInfo: function () {
            let param = {};
            this.loading = true;
            this.axios.post(this.baseURL + `/getDeptsInfo`, param).then(res => {
                let orgs = res.data.data;
                for (var i = 0; i < orgs.length; i++) {
                    this.depts.push({
                        value: orgs[i].id,
                        label: orgs[i].cname
                    });
                }
                this.loading = false;
            });
        },
        stocChange(val) {
            this.stocInfo.stocLevel = val;
        },
        stocShelfChange(val) {
            this.stocInfo.shelfType = val;
        },
        deptChange(val) {
            let data = this.depts;
            for (var i = 0; i < data.length; i++) {
                if (data[i].value == val) {
                    this.stocInfo.deptId = data[i].value;
                    this.stocInfo.deptName = data[i].label;
                }
            }
        },
        handleNodeClick(data) {
            this.clearErr();
            this.deptName = '' ;
            this.stocLevel = '' ;
            this.stocInfo = {};
            if (data.id.length > 1) {
                let param = { id: data.id };
                this.axios.post(this.baseURL + '/getCurrentStocs', param).then(res => {
                    this.stocInfo = res.data.data;
                    this.deptName = this.stocInfo.deptName;
                    this.stocLevel = this.stocInfo.stocLevel;
                    this.btnFlag = false;
                });
            } else {// 根节点
                this.btnFlag = false;
            }
        },
        clearErr:function(){
            this.errors.stocPhoneErr = '';
            this.errors.deptNameErr = '';
            this.errors.stocNameErr = '';
            this.errors.stocFzrErr = '';
            this.errors.stocLevelErr = '';
        },
        clearInfo: function(){
            this.deptName = '';
            this.stocLevel = '';

            this.stocInfo = {
                id: '',
                stocName: '',
                stocFzr: '',
                stocLevel:'',
                stocPhone:'',
                shortPinyin: '',
                address: '',
                memo: '',
                version: 0,
                deptName:'',
                shelfType:'10'//库房默认货位类型，普通货位
            };
        },
        // 添加
        stocInfoAdd: function () {
            this.clearErr();
            this.btnFlag = true;
            this.clearInfo();
            // 根节点
            // 可能的一些判断
        },
        // 保存
        stocInfoUpdate: function () {
            this.clearErr();
            var _this = this;
            var form = this.$refs.editForm;
            const h = this.$createElement;
            var arr = new Array();
            form.validate((valid) => {
                if (valid) {
                    this.axios.post(this.baseURL + '/update', this.stocInfo).then(res => {
                        if (res.data.code == "0") {
                            this.$message({
                                message: '操作成功！',
                                type: 'success'
                            });
                            let id = res.data.data.id;
                            //
                            this.axios.post(this.baseUrl + `/selectStocInfo`, {}).then(res => {
                                // 树信息
                                this.treeValue = res.data.data;
                                this.expandIds = [id];
                            });
                            this.stocInfo = res.data.data;
                            this.deptName = this.stocInfo.deptName;
                            this.stocLevel = this.stocInfo.stocLevel;

                            this.btnFlag = false;
                        }else if (res.data.code == "-122") {
                            let err = res.data.validateErrors.fieldErrors[0];
                            for(var error in this.errors){
                                let name = error.substring(0, error.lastIndexOf('Err'));
                                if(err.field == name){
                                    this.errors.error += err.message;
                                    arr.push(h('span', null, err.message));
                                }
                            }
                            _this.$msgbox({ title: "错误", type: "error", message: h('p', null, arr) });
                        }else if(res.data.code == '-1'){
                            this.$message.error(res.data.msg);
                        }
                        else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {
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
        // 删除
        stocInfoDel: function () {
            let _this = this;
            let id = this.stocInfo.stocLevel;
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.axios.post(this.baseURL + `/delete`, this.stocInfo).then(res => {
                    if (res.data.code == "0") {
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });

                        this.clearInfo();
                        this.axios.post(this.baseUrl + `/selectStocInfo`, {}).then(res => {
                            // 树信息
                            this.treeValue = res.data.data;
                            this.expandIds = [_this.stocNum[id]];
                        });
                        this.btnFlag = true;
                    }
                    else {
                        if (res.data.validateErrors.fieldErrors.length > 0) {
                            this.$message.error(res.data.validateErrors.fieldErrors[0]);
                        }
                        else {
                            this.$message.error(res.data.msg);
                        }
                    }
                });
            })
        },
        // 库房搜索
        searchStocInfo: function () {
            if (this.unit.corpId == null) {
                return;
            }
            var value = this.filters.stocName;
            let param = {stocName: value};
            this.loading = true;
            this.axios.post(this.baseURL + `/getStocsByParam`, param).then(res => {
                var item = res.data.data['stocInfoList'];
                var ids = [];
                for (var i = 0; i < item.length; i++) {
                    ids[i] = item[i].id;
                }
                this.expandIds = ids;
            });
        },
      //字典货位类型
      getStocTypes: function () {
        this.loading = true;
        this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'HWLX'}).then(res => {
          this.loading = false;
          if (res.data.code == "0") {
            this.stocTypes = res.data.data;
          }
          else{
            this.$message.error( res.data.msg);
          }
        }, err => {
          this.loading = false;
        });
      },
    },
    // 生命周期函数
    mounted() {
        this.unit.corpId = this.user.corpId;
        this.unit.corpName = this.user.corpName;
        this.selectStocInfo();
        this.getDeptsInfo();
        this.getStocs();
        this.getStocTypes();
    }
}
</script>

<style>
#storehouse-info .el-tree-node__label {
    font-size: 12px;
}

#storehouse-info .el-table {
    font-size: 12px;
}

#storehouse-info .el-tree {
    min-height: calc(100% - 75px);
}

#storehouse-info .div4Tree {
    border: 1px solid #d1dbe5;
    height: calc(100% - 5px);
    overflow-y: auto;
}

#storehouse-info .fullheight {
    height: calc(100% - 75px);
}
</style>
