<template>
  <div id="goods-allocation-mgr" style="height: 100%;">
	<el-row height="auto" style="height: 100%;">
			<el-col :span="6" style="height: 99%">
				<div style="padding-bottom: 7px">
                    <el-input v-model="filters.stocName" placeholder="请输入库房名称" icon="search" :on-icon-click="searchStocInfo" >
                    </el-input>
                </div>

                <el-tree
                    class="goodsAllMgr-tableheight"
                    :data="treeValue"
                    node-key="id"
                    :current-node-key="currrentKey"
                    :default-expanded-keys="expandIds"
                    :props="defaultProps"
                    :highlight-current="true"
                    @node-click="handleNodeClick">
                </el-tree>

            </el-col>
            <!--区位管理-->
            <el-col :span="9" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
                <div style="padding-bottom: 4px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="">
                            <el-input v-model="filters.areaCode" placeholder="请输入区位" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" v-on:click="getStocArea(1)" >查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="success" @click="stocAreaEdit(null)" >新增区位</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <!--区位列表-->
                <el-table :data="stocAreas" @row-click="queryShelfList" highlight-current-row v-loading="areaLoading" ref="areaTable" style="width: 100%;" border fit height="outter" class="goodsAllMgr-tableheight">
                    <el-table-column type="index" label="序号" align="center"></el-table-column>
                    <el-table-column prop="areaCode" label="区位编码" header-align="center"></el-table-column>
                    <!--<el-table-column prop="areaName" label="区位名称" header-align="center"></el-table-column>-->
                    <el-table-column prop="shelfType" :formatter="shelfTypeFormatter" label="类型"></el-table-column>
                    <el-table-column label="操作" width="90" align="center" fixed="right">
                        <template slot-scope="scope">
                            <el-button-group>
                                <el-button type="info" size="mini" @click="stocAreaEdit(scope.row)" icon="edit">编辑</el-button>
                                <el-button type="danger" size="mini" @click="stocAreaDel(scope.row)" icon="delete">删除</el-button>
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="areaHandleSizeChange" @current-change="areaHandleCurrentChange" :current-page="areaPage" :page-sizes="[20, 100, 200, 400]" :page-size="areaPageSize" layout="total, sizes, prev, pager, next" :total="areaTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
            <!--货位管理-->
            <el-col :span="9" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;" >
                <div style="padding-bottom: 4px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="">
                            <el-input v-model="filters.shelfCode" placeholder="请输入货位" ></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" v-on:click="getStocShelf(1)" >查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="success" @click="stocShelfEdit(null)" >新增货位</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <!--货位列表-->
                <el-table :data="stocShelfs" highlight-current-row v-loading="shelfLoading" ref="shelfTable" style="width: 100%;" border fit height="outter" class="goodsAllMgr-tableheight">
                    <el-table-column type="index" label="序号" align="center"></el-table-column>
                    <el-table-column prop="shelfCode" label="货位编码" header-align="center"></el-table-column>
                    <!--<el-table-column prop="shelfName" label="货位名称" header-align="center"></el-table-column>-->
                    <el-table-column prop="shelfType" :formatter="shelfTypeFormatter" label="类型"></el-table-column>
                    <el-table-column label="操作" width="90" align="center" fixed="right">
                        <template slot-scope="scope">
                            <el-button-group>
                                <el-button type="info" size="mini" @click="stocShelfEdit(scope.row)" icon="edit">编辑</el-button>
                                <el-button type="danger" size="mini" @click="stocShelfDel(scope.row)" icon="delete">删除</el-button>
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="shelfHandleSizeChange" @current-change="shelfHandleCurrentChange" :current-page="shelfPage" :page-sizes="[20, 100, 200, 400]" :page-size="shelfPageSize" layout="total, sizes, prev, pager, next" :total="shelfTotal" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>
            <!--区位编辑界面-->
            <el-dialog :title="areaModelTitle"  :visible.sync="areaEditFormVisible" :close-on-click-modal="false" size="mini">
                <el-form :model="stocArea" label-width="80px" :rules="areaEditFormRules" ref="areaEditForm" class="el-form-item-nomsg">
                    <el-row>
                        <el-col>
                            <el-form-item label="区位编码" prop="areaCode" :error="errors.areaCodeErr">
                                <el-input v-model="stocArea.areaCode" placeholder="请输入区位"  ></el-input>
                            </el-form-item>
                        </el-col>
                      <el-col>
                        <el-form-item label="货位类型：" prop="shelfType" :error="errors.shelfTypeErr">
                          <el-select v-model="stocArea.shelfType" placeholder="请选择货位类型"  @change="stocShelfChange">
                            <el-option v-for="item in stocTypes" :key="item.val" :label="item.ename" :value="item.val"></el-option>
                          </el-select>
                        </el-form-item>
                      </el-col>
                        <el-col>
                            <el-form-item label="所属库房" prop="provId" :error="errors.provIdErr">
                                <el-cascader placeholder="请选择库房" v-model="selectedStoc"   :options="stocInfo4cascader" filterable  :props="stocProps">
                                </el-cascader>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="areaEditFormVisible = false">取消</el-button>
                    <el-button type="primary" @click.native="areaEditSubmit" :loading="editAreaLoading">提交</el-button>
                </div>
            </el-dialog>
            <!--货位编辑页面-->
            <el-dialog :title="shelfModelTitle"  :visible.sync="shelfEditFormVisible" :close-on-click-modal="false" >
                <el-form :model="stocShelf" label-width="80px" :rules="shelfEditFormRules" ref="shelfEditForm" class="el-form-item-nomsg">
                    <el-row>
                        <el-col>
                            <el-form-item label="货位编码" prop="shelfCode" :error="errors.shelfCodeErr">
                                <el-input v-model="stocShelf.shelfCode" placeholder="请输入货位"  ></el-input>
                            </el-form-item>
                        </el-col>
                      <el-col>
                        <el-form-item label="货位类型：" prop="shelfType" :error="errors.shelfTypeErr">
                          <el-select v-model="stocShelf.shelfType" placeholder="请选择货位类型"  @change="stocShelfChange">
                            <el-option v-for="item in stocTypes" :key="item.val" :label="item.ename" :value="item.val"></el-option>
                          </el-select>
                        </el-form-item>
                      </el-col>
                        <el-col>
                            <el-form-item label="所属区位" prop="pid" :error="errors.pidErr">
                                <el-select v-model="currentArea" placeholder="请选择所属区位" >
                                    <el-option v-for="item in areaInfos" :key="item.value" :label="item.label" :value="item.value"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="shelfEditFormVisible = false">取消</el-button>
                    <el-button type="primary" @click.native="shelfEditSubmit" :loading="editShelfLoading">提交</el-button>
                </div>
            </el-dialog>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            baseURL:'/spdHERPService/baseData/stocInfoMgr/stocInfo',
            baseUrl: '/spdHERPService/baseData/stocInfoMgr/stocShelf',
            filters: {
                pid:'',
                stocName:'',
                stocId: '',
                areaCode: '',
                shelfCode:''
            },

            // 当前选中的code
            currentStoc:{
                areaCode:'',
                shelfCode:''
            },

            areaTotal: 0,
            areaPage: 1,
            areaPageSize: 20,
            areaLoading: false,
            shelfTotal: 0,
            shelfPage: 1,
            shelfPageSize: 20,
            shelfLoading: false,

            stocAreas:[],
            stocShelfs: [],
            stocArea:{
                kind:'10',
                provId:'',
                shelfType:'',
            },
            stocShelf: {
                pid:'',
                provId:'',
                kind:'20',
                shelfType:''
            },
            // kind: '10',
            areaModelTitle: "",
            areaEditFormVisible: false,
            editAreaLoading: false,
            areaEditFormRules: {
                areaCode: [{ required: true, message: '请输入区位', trigger: 'blur' }],
                provId: [{  required: true, message: '请选择所属库房', trigger: 'blur' }]
            },

            shelfModelTitle: "",
            shelfEditFormVisible: false,
            editShelfLoading:false,
            shelfEditFormRules:{
                shelfCode: [{ required: true, message: '请输入货位', trigger: 'blur' }],
                pid: [{ required: true, message: '请选择所属区位', trigger: 'blur' }]
            },
            errors: {
                areaCodeErr:'',
                shelfCodeErr:'',
                provIdErr: '',
                pidErr:'',
                shelfTypeErr:''
            },

            // 树组件节点属性
            defaultProps: {
                children: 'children',
                label: 'label',
                id: 'id',
            },
            treeValue: [],
            currrentKey:'',
            currentPid:'',
            currentStockId:'',

            // cascade
            stocInfo4cascader: [], // data
            stocProps: {  // attribute
                value: 'id',
                label: 'label',
            },

            // select
            currentArea:'',
            areaInfos:[],
            temp:[],

            expandIds: [],
            stocTypes:[]
        }
    },
    computed: {
          selectedStoc: {
            get: function () {
                let res = [];
                if(this.stocArea.provId){
                    this.stocInfo4cascader.forEach(item => {
                        item.children.forEach(row => {
                            if(row.id == this.stocArea.provId){
                                res.push(item.id);
                            }
                        });
                    });
                    res.push(this.stocArea.provId);
                }
                return res;
            },
            set:function(val){
                this.stocArea.provId = val[val.length - 1];
            }
         }
    },
    methods: {
        //获取区位列表
        getStocArea: function (pIndex) {
            //
            this.stocShelfs = [];
            this.areaPage = pIndex;
            var param = {
                orderBy: "",
                queryObject: {
                    provId: this.filters.stocId,
                    areaCode: this.filters.areaCode
                },
                page: this.areaPage,
                rows: this.areaPageSize
            };
            this.areaLoading = true;
            this.axios.post(this.baseUrl + `/getStocArea`, param).then(res => {
                this.areaLoading = false;
                this.stocAreas = res.data.data.data;
                this.areaTotal = res.data.data.total;

                if(this.stocAreas.length > 0){
                    if(this.currentStoc.areaCode){
                        this.stocAreas.forEach(item => {
                            if(this.currentStoc.areaCode == item.areaCode){
                                this.setCurrent(item);
                                this.currentStoc.areaCode = '';
                            }
                        });
                    }else{
                        this.setCurrent(this.stocAreas[0]);
                    }
                }
            }, err => {
                this.areaLoading = false;
            });
        },
        //获取货位列表
        getStocShelf: function (pIndex) {
            this.shelfPage = pIndex;
            var param = {
                orderBy: "",
                queryObject: {
                    pid:this.filters.pid,
                    provId: this.filters.stocId,
                    shelfCode: this.filters.shelfCode
                },
                page: this.shelfPage,
                rows: this.shelfPageSize
            };
            this.shelfLoading = true;
            this.axios.post(this.baseUrl + `/getStocShelf`, param).then(res => {
                this.shelfLoading = false;
                this.stocShelfs = res.data.data.data;
                this.shelfTotal = res.data.data.total;
            }, err => {
                this.shelfLoading = false;
            });
        },
        setCurrent:function(row){
            this.currrentKey = row.provId;
            this.currentPid = row.id;
            this.expandIds = [this.currrentKey];
            this.filters.stocId = row.provId;
            this.filters.pid = row.id;
        },
        queryShelfList:function(row, event, column){
            this.currentPid = row.id;
            this.filters.stocId = row.provId;
            this.filters.pid = row.id;
            this.getStocShelf(1);
        },
        areaHandleCurrentChange(val) {
            this.areaPage = val;
            this.getStocArea(this.areaPage);
        },
        areaHandleSizeChange(val) {
            this.areaPageSize = val;
            this.getStocArea(this.areaPage);
        },
        shelfHandleCurrentChange(val) {
            this.shelfPage = val;
            this.getStocShelf(this.shelfPage);
        },
        shelfHandleSizeChange(val) {
            this.shelfPageSize = val;
            this.getStocShelf(this.shelfPage);
        },
        //
        stocAreaEdit(item) {
            this.errors.provIdErr = '';
            this.errors.areaCodeErr = '';
            var param = {};
            this.axios.post(this.baseUrl + `/selectStocInfo`, {}).then(res => {
                let item = res.data.data[0].children;
                for(var key in item){
                    if(!item[key].children){
                        item.splice(key, 1);
                    }
                }
                this.stocInfo4cascader = res.data.data[0].children;
            });
            if (item) {
                this.stocArea = Object.assign({}, item);
                this.areaModelTitle = "修改区位";
                // this.kind = this.stocShelf.kind;
            } else {
                this.stocArea = { kind:'10', provId:this.currentStockId };
                // this.kind = '10';
                this.areaModelTitle = "添加区位";
            }
            this.areaEditFormVisible = true;

        },
        stocShelfEdit(item){
            this.errors.pidErr = '';
            this.errors.shelfCodeErr = '';
            // 加载区位
            var vo = {
                provId: this.filters.stocId
            }
            this.axios.post(this.baseUrl + `/getAreaList`, vo).then(res => {
                res.data.data.forEach(item => {
                    this.temp.push({
                        value:item.id,
                        label:item.areaCode
                    });
                });

                this.areaInfos = this.temp;
                this.temp = [];

            });
            //
            if(item){
                this.stocShelf = Object.assign({}, item);
                this.shelfModelTitle = "修改货位";
                // this.currentArea = this.stocArea.areaCode;
                this.currentArea = this.currentPid;

            }else{
                this.stocShelf = {pid:'', provId:'', kind:'20' };
                this.currentArea = this.currentPid;
                this.shelfModelTitle = "添加货位";
            }
            this.shelfEditFormVisible = true;
        },
        // 删除
        stocAreaDel(stocArea) {
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.areaLoading = true;
                // this.stocArea = stocArea;
                this.axios.post(this.baseUrl + "/delete", stocArea).then(res => {
                    this.areaLoading = false;
                    if (res.data.code == "0") {
                        this.areaLoading = false;
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        this.$message.error(res.data.msg);

                    }
                    this.getStocArea(this.areaPage);
                }, err => {
                    this.areaLoading = false;
                });
            }).catch(() => {

            });
        },
        // 删除货位
        stocShelfDel(stocShelf) {

            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.shelfLoading = true;
                this.axios.post(this.baseUrl + "/delete", stocShelf).then(res => {
                    this.shelfLoading = false;
                    if (res.data.code == "0") {
                        this.shelfLoading = false;
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        this.$message.error(res.data.msg);

                    }
                    this.getStocShelf(this.shelfPage);
                }, err => {
                    this.shelfLoading = false;
                });
            }).catch(() => {

            });
        },
        areaEditSubmit: function () {
            var _this = this;
            var form = this.$refs.areaEditForm;
            const h = this.$createElement;
            var arr = new Array();
            form.validate((valid) => {
                if (valid) {
                    this.editAreaLoading = true;

                    let serviceUrl = this.stocArea.id ? this.baseUrl + "/update" : this.baseUrl + "/insert";
                    this.axios.post(serviceUrl, this.stocArea).then(res => {

                        this.editAreaLoading = false;
                        if (res.data.code == "0") {
                            this.$message({ message: '操作成功！', type: 'success' });
                            this.areaEditFormVisible = false;
                            _this.filters.stocId = _this.stocArea.provId;
                            _this.currentStoc.areaCode = _this.stocArea.areaCode;
                            this.getStocArea(1);
                        } else if (res.data.code == "-122") {
                            let err = res.data.validateErrors.fieldErrors[0];
                            if (err.field == "provId") {
                               this.errors.provIdErr += err.message;
                               arr.push(h('span', null, err.message));
                            }else if(err.field == 'areaCode'){
                                this.errors.areaCodeErr += err.message;
                                arr.push(h('span', null, err.message));

                            }else {
                                arr.push(h('span', null, err.message));
                            }
                            _this.$msgbox({ title: "错误", type: "error", message: h('p', null, arr) });
                        }
                        else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {
                        this.editAreaLoading = false;
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
        //
        shelfEditSubmit: function () {
            var _this = this;
            var form = this.$refs.shelfEditForm;
            const h = this.$createElement;
            var arr = new Array();
            this.stocShelf.pid = this.currentArea;
            this.stocShelf.provId = this.filters.stocId;
            form.validate((valid) => {
                if (valid) {
                    this.editShelfLoading = true;

                    let serviceUrl = this.stocShelf.id ? this.baseUrl + "/update" : this.baseUrl + "/insert";
                    this.axios.post(serviceUrl, this.stocShelf).then(res => {

                        this.editShelfLoading = false;
                        if (res.data.code == "0") {
                            this.$message({ message: '操作成功！', type: 'success' });
                            this.shelfEditFormVisible = false;

                            //
                            _this.filters.pid = _this.stocShelf.pid;
                            _this.filters.stocId = _this.stocShelf.provId;
                            // _this.currentStoc.shelfCode = _this.stocShelf.shelfCode;
                            this.getStocShelf(1);
                        } else if (res.data.code == "-122") {
                            let err = res.data.validateErrors.fieldErrors[0];
                            if (err.field == "pid") {
                               this.errors.pidErr += err.message;
                               arr.push(h('span', null, err.message));
                            }else if(err.field == 'shelfCode'){
                                this.errors.shelfCodeErr += err.message;
                                arr.push(h('span', null, err.message));

                            }else {
                                arr.push(h('span', null, err.message));
                            }
                            _this.$msgbox({ title: "错误", type: "error", message: h('p', null, arr) });
                        }
                        else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {
                        this.editShelfLoading = false;
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
        handleNodeClick(data,d2,d3) {
            if(data.id.length <= 2){
                this.stocAreas = [];
                this.stocShelfs = [];
                return;
            }
            this.filters.stocId = '';
            if (data.id.indexOf('stoc') > -1) {
                this.filters.stocId = data.id;
                this.currentStockId = data.id;
            }else {
              this.currentStockId = '';
            }
            this.stocShelfs = [];
            this.getStocArea(1);
        },
        // 库房信息
        selectStocInfo: function () {
            var param = {};
            this.areaLoading = true;
            this.axios.post(this.baseUrl + `/selectStocInfo`, {}).then(res => {
                this.treeValue = res.data.data;
                this.expandIds = [this.treeValue[0].id];
                this.areaLoading = false;
            });
        },
        // 库房搜索
        searchStocInfo: function () {
            var value = this.filters.stocName;
            let param = { stocName: value };
            this.axios.post(this.baseURL + `/getStocsByParam`, param).then(res => {
                var item = res.data.data['stocInfoList'];
                var ids = [];
                for (var i = 0; i < item.length; i++) {
                    ids[i] = item[i].id;
                }
                this.expandIds = ids;
            });
        },

      shelfTypeFormatter:function (row) {
        var tempObj = this.stocTypes.find(o=>o.val == row.shelfType);
        return tempObj.ename;
      },
      stocShelfChange(val) {
        this.stocArea.shelfType = val;
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
      }
    },
    mounted() {
        this.selectStocInfo();
        this.getStocTypes();
        // this.getStocArea(1);
    }
};
</script>

<style>
#goods-allocation-mgr .el-tree-node__label {
    font-size: 12px;
}

#goods-allocation-mgr .el-table {
    font-size: 12px;
}

#goods-allocation-mgr .el-tree {
    min-height: calc(100% - 82px);
}

#goods-allocation-mgr .div4Tree {
    border: 1px solid #d1dbe5;
    height: calc(100% - 5px);
    overflow-y: auto;
}

#goods-allocation-mgr .fullheight {
    height: calc(100% - 75px);
}
#goods-allocation-mgr .goodsAllMgr-tableheight{
	  height:  calc(100% - 75px);
}
</style>
