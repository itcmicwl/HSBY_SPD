<template>
  <div id="operation-packinfo" style="height: 100%;">
	<el-row height="auto" style="height: 100%;">
			<el-col :span="5" style="height: 100%;">
				<div style="padding-bottom: 5px">
						<el-input v-model="kindName" placeholder="请输入手术类型" icon="search" :on-icon-click="getPackKind"  style='width:60%;'></el-input>
                         <el-button-group style="margin-bottom: 3px;">
                            <el-button type="success" v-on:click="packKindEdit" >添加</el-button>
                            <el-button type="danger" v-on:click="packKindDel"  >删除</el-button>
                        </el-button-group>
				</div> 
					<el-tree 
                        :data="kindTreeValue" 
                        node-key="id" 
                        :default-expanded-keys="kindExpandIds" 
                        :props="defaultProps" 
                        :highlight-current="true" 
                        @node-click="kindClick"
                        :filter-node-method="filterNode"
                        ref = "packKindTree">
                    </el-tree>
			</el-col>

			<el-col :span="5" style="height: 100%;">
				<div style="padding-bottom: 5px">
						<el-input v-model="packName" placeholder="请输入手术包名称" icon="search" :on-icon-click="getPackInfo"  style='width:60%;'></el-input>
                         <el-button-group style="margin-bottom: 3px;">
                            <el-button type="success" v-on:click="packInfoEdit" >添加</el-button>
                            <el-button type="danger" v-on:click="packInfoDel"  >删除</el-button>
                        </el-button-group>
				</div> 
					<el-tree 
                        :data="treeValue" 
                        node-key="id" 
                        :default-expanded-keys="expandIds" 
                        :props="defaultProps" 
                        :highlight-current="true" 
                        @node-click="handleNodeClick"
                        :filter-node-method="filterNode"
                        ref = "packInfoTree">
                    </el-tree>
			</el-col>


            <el-col :span="14" class="toolbar" style="padding-bottom: 0px;height: 98.7%; padding-left: 5px;" >
                <el-form :inline="true" :model="filters" label-width="80px" ref="goodsForm" style="margin-bottom:5px;">
                    <el-form-item label="产品名称" prop="goodsName">
                        <el-input v-model="filters.goodsName" :clearable="true" placeholder="输入产品名称" ></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="getPackGoodsInfo(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetGoodsName('goodsForm')" >重置</el-button>
                        <el-button type="success" @click="handleImport"  :disabled="importDisabled">批量导入</el-button>
                        <el-button type="danger" @click="mulDel()" >批量移除</el-button>
                    </el-button-group>
                </el-form>

                <!--当前页列表-->
                <el-table  ref="packGoodsInfoTable" :data="packGoods"  row-key='id'  @selection-change="handleSelectionChange"
                           highlight-current-row v-loading="loading"  border fit height="outter"  class="pack-tableheight" >
                    <el-table-column type="selection" align="center" :reserve-selection="true" width="55"> </el-table-column>
                    <!--<el-table-column prop="cname" align="center" width="150" label="手术包名称" ></el-table-column>-->
                    <el-table-column prop="goodsName" align="center" label="产品名称" width="195"></el-table-column>
                    <el-table-column prop="goodsGg" align="center" label="规格型号"width="200"></el-table-column>
                    <el-table-column prop="unit" align="center" label="单位" width="60"></el-table-column>
                    <el-table-column prop="packeage" align="center" label="包装数量" width="60"></el-table-column>
                    <el-table-column prop="price" align="center" label="单价" width="60"></el-table-column>
                    <el-table-column prop="made" align="center" label="产地" width="60"></el-table-column>
                    <el-table-column prop="mfrsName" align="center" label="生产厂商" width="195"></el-table-column>
                    <el-table-column label="操作"  align="center"  width="120">
                        <template slot-scope="scope">
                            <el-button-group>
                                <el-button type="primary" size="mini" @click="packGoodShow(scope.row)" icon="search" >查看</el-button>
                                <el-button type="danger" size="mini" @click="packGoodDel(scope.row)" icon="delete" >移除</el-button>
                            </el-button-group>
                        </template>
                    </el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 50, 100, 200]" :page-size="pageSize" :total="total" layout="total, sizes, prev, pager, next" style="float:right;" small>
                    </el-pagination>
                </el-col>
            </el-col>

        <!-- 手术包类型 -->
       <el-dialog :title="kindModelTitle"  :visible.sync="kindEditFormVisible" :close-on-click-modal="false" >
            <el-form :model="kindInfo" label-width="100px" :rules="kindEditFormRules" ref="editForm" class="el-form-item-nomsg">
                <el-row>
                    <el-col>
                        <el-form-item label="所属单位名">
                            <el-input v-model="this.filters.hosName"  disabled></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="上级节点">
                            <el-input v-model="this.filters.kindName ? this.filters.kindName : this.filters.hosName"  disabled></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="手术包类型" prop="kindName" :error="errors.kindNameErr">
                            <el-input v-model="kindInfo.kindName" placeholder="请输入手术包类型"  ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="kindEditFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="kindEditSubmit" :loading="kindEditLoading">提交</el-button>
            </div>
        </el-dialog>
        
        <!-- 手术包添加 -->
       <el-dialog :title="modelTitle"  v-model="editFormVisible" :close-on-click-modal="false" >
            <el-form :model="packInfo" label-width="100px" :rules="editFormRules" ref="editForm" class="el-form-item-nomsg">
                <el-row>
                    <el-col>
                        <el-form-item label="所属单位名">
                            <el-input v-model="this.filters.hosName"  disabled></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="手术包类型">
                            <el-input v-model="this.filters.kindName" placeholder="请选择手术包类型"  disabled></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="手术包名称" prop="cname" :error="errors.cnameErr">
                            <el-input v-model="packInfo.cname" placeholder="请输入手术包名称"  ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!-- 批量导入 -->
        <hosGoodsInfo v-on:importSubmit = "importSubmit" :hosId="this.user.corpId" ></hosGoodsInfo>
        <!-- 产品详情 -->
        <goodsInfoShow :goods-id = "currentGoodsId"></goodsInfoShow>

        </el-row>
    </div>
</template>
<script>
import hosGoodsInfo from '../../components/hosGoodsInfo.vue';
import goodsInfoShow from '../../components/goodsInfoShow.vue';
export default {
    data() {
        return {
            baseUrl : '/spdHERPService/productsInfos/operationPackInfo',
            filters: {
                hosId : '',
                hosName:'',
                packId:'',
                kindId:'',
                kindName:'',
                packName:'',
                goodsName:''
            },
            kindName:'',
            packName:'', // 快速查询

            // 当前页分页
            total: 0,
            page: 1,
            pageSize: 20,
            loading: false,

            packGoods: [],
            multipleSelection:[],

            // 树组件节点属性
            defaultProps: {
                children: 'children',
                label: 'label',
                id: 'id',
            },
            treeValue: [],
            expandIds: [],
            kindTreeValue:[],
            kindExpandIds:[],
            
            // 添加手术包类型
            kindInfo:{
                hosId:'',
                pId:''
            },
            kindModelTitle: "添加手术包类型",
            kindEditFormVisible: false,
            kindEditLoading: false,
            kindEditFormRules: {
                kindName: [{ required: true, message: '请输入手术包类型', trigger: 'blur' }]
            },

            // 添加手术包
            packInfo:{
                hosId : '',
                kindId :''
            },
            modelTitle: "添加手术包",
            editFormVisible: false,
            editLoading: false,
            editFormRules: {
                cname: [{ required: true, message: '请输入手术包名称', trigger: 'blur' }]
            },
            errors: {
                cnameErr:'',
                kindNameErr:''
            },

            // 批量导入
            importDisabled : true,
            // 详情
            currentGoodsId:''
        }
    },
    watch: {
        kindName(val){
            this.$refs.packKindTree.filter(val);
        },
        packName(val) {
            this.$refs.packInfoTree.filter(val);
        }
    },
    components:{hosGoodsInfo, goodsInfoShow},
    methods: {
        // 详情
        packGoodShow(packGood){
            this.currentGoodsId = packGood.goodsId;
            this.$store.state.dialog.visiable = true;
        },
        // 批量导入
        handleImport(){
            this.$store.state.dialog.show = true;
        },
        importSubmit(ids){
            // 去重
            for(var key in this.packGoods){
                var index = ids.indexOf(this.packGoods[key].goodsId)
                if(index > -1){
                    ids.splice(index, 1);
                }
            }
            var param = {
                tempId : this.filters.packId,
                goodsIds : ids
            };
            this.axios.post(this.baseUrl + '/importHosGoods', param).then(res => {
                if (res.data.code == "0") {
                    this.$message({ message: '操作成功！', type: 'success' });
                    this.$store.state.dialog.show = false;
                    this.getPackGoodsInfo(1);
                } else{
                    this.$message.error( res.data.msg);
                }
            }, err => {});
        },
        // 移除
        packGoodDel(good){
            var bean = {id : good.id};
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.loading = true;
                this.axios.post(this.baseUrl +"/deleteGood", bean).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.loading = false;
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        this.$message.error( res.data.msg);
                    }
                    this.getPackGoodsInfo(this.page);
                }, err => {
                    this.loading = false;
                });
            }).catch(() => {});
        },
        // 批量删除
        mulDel(){
            if(this.multipleSelection.length <= 0){
                this.$message.error( "至少选择一条数据！");
                return;
            }
            this.$confirm('确认删除选中记录吗?', '提示', {
            }).then(() => {
                this.loading = true;
                this.axios.post(this.baseUrl + '/mulDelete', this.multipleSelection).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.loading = false;
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        this.$message.error( res.data.msg);
                    }
                    this.getPackGoodsInfo(this.page);
                }, err => {
                    this.loading = false;
                });
            }).catch(() => {});
        },
        resetGoodsName:function (formName) {
            this.$refs[formName].resetFields();
            this.$refs.packGoodsInfoTable.clearSelection();
            this.getPackGoodsInfo(1);
        },
            // 获取手术包产品详情
        getPackGoodsInfo: function (pIndex) {
            this.multipleSelection = [];
            this.$refs.packGoodsInfoTable.clearSelection();
            this.page = pIndex;
            var param = {
                orderBy: "",
                queryObject: {
                    hosId:this.filters.hosId,
                    tempId:this.filters.packId,
                    goodsName : this.filters.goodsName
                },
                page: this.page,
                rows: this.pageSize
            };
            this.loading = true;
            this.axios.post(this.baseUrl + `/getPackGoodsInfo`, param).then(res => {
                this.loading = false;
                this.packGoods = res.data.data.data;
                this.total = res.data.data.total;
            }, err => {
                this.loading = false;
            });
        },
        handleCurrentChange(val) {
            this.page = val;
            this.getPackGoodsInfo(this.page);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getPackGoodsInfo(this.page);
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        // 手术包类型添加
        packKindEdit(){
            this.errors.kindNameErr = '';
            this.kindInfo = {hosId: this.filters.hosId};
            if(this.filters.kindId){
                this.kindInfo.pId = this.filters.kindId;
            }
            this.kindModelTitle = "添加手术包类型";
            this.kindEditFormVisible = true;
        },
        kindEditSubmit(){
            this.errors.kindNameErr = '';
            var _this = this;
            var form =  this.$refs.editForm;
            const h = this.$createElement;
            var arr = new Array();
            form.validate((valid) => {
                if (valid) {
                    this.kindEditLoading = true;
                    this.axios.post(this.baseUrl + '/insertKind', this.kindInfo).then(res => {
                        this.kindEditLoading = false;
                        if (res.data.code == "0") {
                            this.$message({ message: '操作成功！', type: 'success' });
                            this.kindEditFormVisible = false;
                            this.getPackKind();
                        }else if (res.data.code == "-122") {
                            let err = res.data.validateErrors.fieldErrors[0];
                            if (err.field == "kindName") {
                                this.errors.kindNameErr += err.message;
                                arr.push(h('span',null,err.message));
                            }
                            _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                        }else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {
                        this.kindEditLoading = false;
                    });
                }else{
                    var fields = form.fields;
                    var msg = "";
                    fields.forEach(f=>{
                        if(f.validateState =="error"){
                            msg +="</br>"+f.validateMessage;
                            if (arr.length>0) arr.push(h('br',null,null));
                            arr.push(h('span',null,f.validateMessage));
                        }
                    });
                    _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                }
            });
        },
        // 手术包添加
        packInfoEdit() {
            this.errors.cnameErr = '';
            if(!this.filters.kindId){
                this.$message("请选择手术包类型");
                return;
            }
            this.packInfo = {hosId: this.filters.hosId, kindCode:this.filters.kindId};
            this.modelTitle = "添加手术包";
            this.editFormVisible = true;
        },
        // 手术包提交
        editSubmit: function () {
            this.errors.cnameErr = '';
            var _this = this;
            var form =  this.$refs.editForm;
            const h = this.$createElement;
            var arr = new Array();
            form.validate((valid) => {
                if (valid) {
                    this.editLoading = true;
                    this.axios.post(this.baseUrl + '/insert', this.packInfo).then(res => {
                        this.editLoading = false;
                        if (res.data.code == "0") {
                            this.$message({ message: '操作成功！', type: 'success' });
                            this.editFormVisible = false;
                            this.getPackInfo();
                            // this.getPackGoodsInfo(1);
                        }else if (res.data.code == "-122") {
                            let err = res.data.validateErrors.fieldErrors[0];
                            if (err.field == "cname") {
                                this.errors.cnameErr += err.message;
                                arr.push(h('span',null,err.message));
                            }
                            _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                        }else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {
                        this.editLoading = false;
                    });
                }else{
                    var fields = form.fields;
                    var msg = "";
                    fields.forEach(f=>{
                        if(f.validateState =="error"){
                            msg +="</br>"+f.validateMessage;
                            if (arr.length>0) arr.push(h('br',null,null));
                            arr.push(h('span',null,f.validateMessage));
                        }
                    });
                    _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                }
            });
        },
        packKindDel(){
            if(!this.filters.kindId){
                this.$message("请选择一条记录");
                return;
            }
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.axios.post(this.baseUrl + '/deleteKind', {id : this.filters.kindId}).then(res => {
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.getPackKind();
                    } else{
                        this.$message.error(res.data.msg);
                    }
                }, err => {});
            }).catch(() => {});
        },
        packInfoDel(){
            if(!this.filters.packId){
                this.$message("请选择一条记录");
                return;
            }
            this.$confirm('确认删除这条记录吗?', '提示', {
            }).then(() => {
                this.axios.post(this.baseUrl + '/delete', {id : this.filters.packId}).then(res => {
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.treeValue = [];
                        this.expandIds = [];
                        this.packGoods = [];
                        this.getPackKind();
                        this.getPackInfo();
                    } else{
                        this.$message.error(res.data.msg);
                    }
                }, err => {});
            }).catch(() => {});
        },
        kindClick(data){
            this.treeValue = [];
            this.expandIds = [];
            this.packGoods = [];
            this.importDisabled = false;
            if(data.id.indexOf('pack-kind') == -1){
                this.filters.kindId = '';
                this.filters.kindName = '';
                return;
            }
            if(data.id.indexOf('pack-kind') > -1){
                this.filters.kindId = data.id;
                this.filters.kindName = data.label;
            }
            this.getPackInfo();
        },
        // 
        handleNodeClick(data) {
            if(data.id.indexOf('pack-kind') > -1){
                this.packGoods = [];
                return;
            }
            this.filters.packId = data.id;
            this.importDisabled = false;
            this.getPackGoodsInfo(1);
        },
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        // 查询手术包
        getPackInfo:function () {
            this.filters.packName  = this.packName;
            var param = {
                cname : this.filters.packName,
                kindCode:this.filters.kindId,
                hosId: this.filters.hosId
            };
            this.axios.post(this.baseUrl + '/queryPackInfo', param).then(res => {
                this.treeValue = res.data.data;
                this.expandIds = [this.treeValue[0].id];
            });
        },
        // 查询手术包类型
        getPackKind:function () {
            this.filters.kindName  = this.kindName;
            var param = {
                kindName : this.filters.kindName,
                hosId: this.filters.hosId
            };
            this.axios.post(this.baseUrl + '/queryPackKind', param).then(res => {
                this.kindTreeValue = res.data.data;
                this.kindExpandIds = [this.kindTreeValue[0].id];
            });
        }
    },
    mounted() {
        this.filters.hosId = this.user.corpId;
        this.filters.hosName = this.user.corpName;
        this.getPackKind();
    }
};
</script>
<style>
#operation-packinfo .el-tree-node__label {
	font-size: 12px;
}
#operation-packinfo .el-table {
	font-size: 12px;
}
#operation-packinfo .el-tree {
	min-height: calc(100% - 80px);
}
#operation-packinfo .pack-tableheight{
	  height:  calc(100% - 75px);
}
</style>