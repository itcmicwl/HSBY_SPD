<template>
    <div id="provGoodsHoslist" style="height: 96%;">
        <el-row  height="auto" style="height: 100%;">
            <el-col :span="4" style="height: 100%;" >
                 <div style="margin-bottom: 10px">
                    <el-input
                            v-model="filters.kindName" placeholder="请输入类型名称"
                            icon="search" :on-icon-click="searchs"   >
                    </el-input>
                </div>

                <el-tree :data="kindsValue" node-key="id"
                         :default-expanded-keys="expadoIds"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            </el-col>
            <el-col :span="20" class="toolbar" style="padding-left: 5px; height: 100%; ">
                <div style="margin-bottom: 5px;">
                    <el-form :inline="true">
                        <el-form-item label="商品名">
                            <el-input v-model="goodFilters.goodsName" placeholder="请输入商品名"   ></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input v-model="goodFilters.goodsGg" placeholder="请输入规格型号"   > </el-input>
                        </el-form-item>   
                        <el-form-item label="供应商"> 
                            <el-input v-model="goodFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>   
                        <el-button  type="primary"  @click="searchGoods(null)">查询</el-button>
                        <el-button  type="primary"  @click="addMedicHosGoodsInfo(null)">添加试剂产品信息</el-button>
                        <!--<el-button  type="primary"  @click="batchDelete(null)">批量删除</el-button> -->
                    </el-form>
                </div>
                
                <el-table highlight-current-row height="outter" class="productInfo-tableheight" 
                         :data="goodList" style="width: 100%" 
                          ref="multipleTable4Main"
                          row-key='id'  @current-change="handleCurrentChange4Main" @selection-change="handleSelectionChange4Main"
                          v-loading="goodsLoading" border
                          :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
                            <!--<el-table-column  type="selection" :reserve-selection="true" width="35"> </el-table-column>-->   
                            <el-table-column type="index" label="序号" align="center" width="55"></el-table-column>
                            <el-table-column prop="code" label="商品编号" header-align="center"  width="80" >
                            </el-table-column>
                           
                            <el-table-column prop="goodsName"   min-idth="200" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="temperatureLower"   min-idth="85" label="温度下限（℃）" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="temperatureUpper"   min-idth="85" label="温度上限（℃）" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-width="200" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="made"   min-idth="50" width="50" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="unit"   min-idth="50" width="50" label="单位" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="mfrsName"  header-align="center" width="150"  label="生产厂商" >
                            </el-table-column>
                            <el-table-column prop="provName"  header-align="center" width="150"  label="供应商" show-overflow-tooltip sortable>
                            </el-table-column>
                           
                            <el-table-column label="操作" width="120"  header-align="center" fixed="right">
                                <template slot-scope="scope">
                                    <el-button-group>
                                		<el-button type="primary" size="mini" @click="updateGood(scope.row)" icon="edit" >编辑</el-button>
                                        <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete">删除</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            <!--<el-table-column type="selection" width="40"></el-table-column>-->
                        </el-table>
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleGoodsSizeChange" @current-change="handleGoodsCurrentChange" :current-page="goodsPage" :page-sizes="[10, 20, 50, 100]" :page-size="goodsPageSize" layout="total, sizes, prev, pager, next" :total="goodsTotal" style="float:right;" small>
                   </el-pagination>
                </el-col>
            </el-col>
        </el-row>
       
        <!--编辑界面-->
            <el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" >
            
                <el-form :model="good"  label-width="100px" :rules="editFormRules" ref="editForm"  class="el-form-item-nomsg">
                        <el-row>
                            <el-col>
                                <el-form-item label="医院商品名称" prop="goodsName" :error="errors.goodsNameErr" >
                                    <el-input :disabled="true" v-model="good.goodsName"  placeholder="请输入商品名称"></el-input>
                                </el-form-item>
                            </el-col>                                
                        </el-row>
                        <el-row>
                                <el-col :span="16">
                                    <el-form-item label="供应商名称" prop="provName"  >
                                        <el-input v-model="good.provName" :disabled="true"   placeholder="请输入商品名称"></el-input>
                                    </el-form-item>
                                </el-col> 
                                    <el-col :span="8">
                                                <el-form-item label="商品分类" prop="hosKindName" >
                                                    <el-input v-model="good.hosKindName" :disabled="true"  ></el-input>
                                                </el-form-item>

                                </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="医院商品编号" prop="code" :error="errors.codeErr">
                                            <el-input v-model="good.code" :disabled="true" placeholder="请输入商品编号" ></el-input>
                                </el-form-item>
                            </el-col>
                            
                                <el-col :span="16">
                                    <el-form-item label="生产厂家" prop="mfrsName" >
                                            <el-input v-model="good.mfrsName" :disabled="true" placeholder="请输入商品编号" ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                            
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="产地" prop="made" :error="errors.madeErr">
                                            <el-input v-model="good.made"  :disabled="true" placeholder="请输入产地"></el-input>
                                </el-form-item>
                            </el-col>                                
                                <el-col :span="16">   
                                <el-form-item label="规格型号" prop="goodsGg" :error="errors.goodsGgErr">
                                    <el-input v-model="good.goodsGg"  :disabled="true" placeholder="请输入规格型号"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>                        
                        <el-row>                                
                            <el-col :span="8">
                                <el-form-item label="出厂编码">
                                        <el-input :disabled="true" placeholder="请输入厂家出厂编码" v-model="good.mfrsCode" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">   
                                    <el-form-item label="单位" prop="unit" :error="errors.unitErr">
                                        <el-input :disabled="true" placeholder="请输入单位" v-model="good.unit" ></el-input>
                                    </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="包装" prop="packeage" :error="errors.packeageErr">
                                    <el-input :disabled="true" placeholder="请输入包装" v-model.number="good.packeage" ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">   
                                <el-form-item label="统一码">
                                    <el-input :disabled="true" placeholder="请输入统一码" v-model="good.masterCode" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                    <el-form-item label="68分类" prop="kind68code" >
                                                    <el-select :disabled="true" v-model="kind68code" filterable placeholder="请选择"
                                                    style='width:100%' >
                                                        <el-option
                                                            v-for="item in dict68s"
                                                            :key="item.val"
                                                            :label="item.ename" 
                                                            :value="item.val">
                                                            <span style="float: left">{{ item.ename }}</span>
                                                                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.val }}</span>
                                                        </el-option>
                                                    </el-select>
                                </el-form-item>
                            </el-col>
                            
                        </el-row>
                        
                            <el-row>
                                    <el-col :span="12">
                                                <el-form-item label="价格"  prop="price" :error="errors.priceErr">
                                                    <el-input :disabled="true" placeholder="请输入类别属性" v-model.number="good.price" ></el-input>
                                                </el-form-item>
                                            </el-col>
                                    <el-col :span="12">
                                                <el-form-item label=" 医院厂商名称">
                                                    <el-input :disabled="true" placeholder="请输入厂商名称" v-model="good.hosMfrsName" ></el-input>
                                                </el-form-item>
                                            </el-col>  
                                    </el-row>

                                        <el-row>
                                        <el-col :span="12">
                                                <el-form-item label="阳光采购编码">
                                                    <el-input :disabled="true" placeholder="请输入阳光采购编码" v-model="good.hitCode" ></el-input>
                                                </el-form-item>
                                            </el-col>
                                        <el-col :span="12">
                                                <el-form-item label=" 阳光采购价" prop="hitPrice" :error="errors.hitPriceErr">
                                                    <el-input :disabled="true" placeholder="请输入阳光采购价" v-model.number="good.hitPrice" ></el-input>
                                                </el-form-item>
                                            </el-col>
                                    </el-row>

                                        <el-row>
                                        <el-col :span="12">
                                                <el-form-item label="erp编码">
                                                    <el-input :disabled="true" placeholder="请输入erp编码" v-model="good.erpCode" ></el-input>
                                                </el-form-item>
                                            </el-col>
                                        <el-col :span="12">
                                                    <el-form-item label="平台编码">
                                                    <el-input :disabled="true" placeholder="请输入平台编码" v-model="good.spdGoodsCode" ></el-input>
                                                </el-form-item>
                                        </el-col>
                                    </el-row>
                                <el-row>
                                        <el-col :span="12">
                                                <el-form-item label="HIS销售价"  prop="hisPrice" :error="errors.hisPriceErr">
                                                    <el-input :disabled="true" placeholder="请输入HIS销售价" v-model.number="good.hisPrice" ></el-input>
                                                </el-form-item>
                                            </el-col>
                                                <el-col :span="12">
                                                <el-form-item label="税率" prop="taxRate" >
                                                    <el-select :disabled="true" v-model="taxRate" filterable placeholder="请选择"  style="width:100%">
                                                        <el-option
                                                            v-for="item in SL"
                                                            :key="item.ename"
                                                            :label="item.val" 
                                                            :value="item.ename">
                                                        </el-option>
                                                </el-select>
                                            </el-form-item>
                                            
                                        </el-col>
                                        
                                    </el-row>

                                        <el-row>
                                            <el-col :span="12">
                                                <el-form-item label="唯一码管理策略" prop="uniqueCodeStrategy" >
                                                        <el-select :disabled="true" v-model="uniqueCodeStrategy" filterable placeholder="请选择"   style="width:100%">
                                                            <el-option
                                                                v-for="item in dictUCodeStrategys"
                                                                :key="item.ename"
                                                                :label="item.ename"
                                                                :value="item.val">
                                                            </el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                        
                                    </el-row>
                                    <el-row>
                                        <el-col >
                                            <el-form-item label=" 备注信息">
                                                <el-input :disabled="true" placeholder="请输入备注信息" v-model="good.remark" ></el-input>
                                            </el-form-item>
                                        </el-col>

                                    </el-row>
                                    <el-row>
                                        <el-col :span="12">
                                            <el-form-item label="温度下限(℃)">
                                                <el-input-number v-model="good.temperatureLower" :step="0.01" ></el-input-number>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="12">
                                            <el-form-item label=" 温度上限(℃)">
                                                <el-input-number v-model="good.temperatureUpper" :step="0.01" ></el-input-number>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                </el-form>  
                    
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="editFormVisible = false">取消</el-button>
                    <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
                </div>
            </el-dialog>
                                                                                                                                                                                                                                                                                                                                                                                                                                                        </el-pagination>-->
           
      
    </div>
</template>

<script>
  
    export default {
        data () {   
            return {
                defaultProps: {
                    children: 'children',
                    label: 'label',
                    id:'id',
                    pId:'pid',
                    code:'code',
                    hosId:'hosId',
                    state:'state',           
                },
                filters:{
                    kindName:null,
                    hosId:null,
                },
                kindsValue:[],
                expadoIds:[null],
                loading:'false',
                pname:'商品分类',
                selectedNode:null,
                //以下为右侧参数
                goodFilters:{
                    goodsName:null,
                    goodsGg:null,
                    provName:null,
                    lbsx:null
                },
                goodsLoading:false,
                goodList:[],
                goodsTotal:0, goodsPageSize:10,goodsPage:1,
                currentRow:null,//当前行
                isUpdate:false,//是否为作更新操作
                multipleSelection4Main:[],//多选 行集合
                // 编辑 参数
                modelTitle:"",
                editLoading:false,
                good:{},
                editFormVisible:false,
                dict68s:[],
                kind68code:'',
                uniqueCodeStrategy:'',
                dictUCodeStrategys:[],
                hosKindsCodes:[],
                kindCode:'',
                SL:[],
                taxRate:'',
            
                editFormRules: {
                  goodsName: [
                        { required: true, message: '请输入商品名称', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入商品编号', trigger: 'blur' }
                    ],
                    goodsGg: [
                        { required: true, message: '请输入商品规格', trigger: 'blur' }
                    ],
                    unit: [
                        { required: true, message: '请输入商品单位', trigger: 'blur' }
                    ],
                    mfrsId: [
                        { required: true, message: '请选择生产厂商', trigger: 'blur' }
                    ],
                     made: [
                        { required: true, message: '请输入产地', trigger: 'blur' }
                    ],
                     packeage: [
                        {type:"number", required: false, message: '请输入包装，必须为数字', trigger: 'blur' }  //
                    ],
                     hitPrice: [
                        {type:"number", required: false, message: '请输入阳光采购价，必须为数字', trigger: 'blur' }  //hitPrice
                    ],
                     price: [
                        {type:"number", required: false, message: '请输入价格，必须为数字', trigger: 'blur' }  //
                    ],
                     hisPrice: [
                        {type:"number", required: false, message: '请输入HIS销售价，必须为数字', trigger: 'blur' }  //
                    ]
                },
                errors: {
                    goodsNameErr: '',
                    codeErr: '',
                    goodsGgErr:'',
                    unitErr:'',
                    mfrsIdErr:'',
                    madeErr:'',
                    packeageErr:'',
                    hitPriceErr:'',
                    hisPriceErr:'',
                    priceErr:''
                }

              
            }
        },
        methods: {
            getKinds:function () { 
                // this.loading=true;
                    let param = { orderBy: "", queryObject: {hosId:this.filters.hosId}, page:0, rows: 0 };
                    this.axios.post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param).then(res=>{
                        this.kindsValue=res.data.data;
                        //this.loading=false;
                    });
            },
            searchs:function () {
                 this.loading=true;
                let param = { orderBy: "", queryObject: {hosId: this.filters.hosId,kindName: this.filters.kindName}, page:0, rows: 0 };
                 this.axios.post(`/spdHERPService/productsInfos/hosKindcode/query`, param).then(res=>{
                    var ids=[];
                    for(var i = 0;i < res.data.data.length; i++) {
                        ids[i] =res.data.data[i].id;
                    }
                    this.loading=false;
                    this.expadoIds= ids;
                });
               
            },
            handleNodeClick(data) {
                this.selectedNode = data;
                this.goodFilters.lbsx= data.id;
           
                this.getHosGoodsList(1);
               
            },
         
            getHosGoodsList:function(pIndex){
             
                let hosId=this.user.corpId;
                this.goodsPage = pIndex;
                this.loading = true;
                var param = { orderBy: "", 
                              queryObject: 
                                            {hosId:hosId,
                                            goodsName:this.goodFilters.goodsName,
                                            goodsGg:this.goodFilters.goodsGg,
                                            lbsx:this.goodFilters.lbsx,
                                            provName:this.goodFilters.provName
                                        },
                            page: this.goodsPage,
                             rows: this.goodsPageSize 
                };
                 this.axios.post('/spdHERPService/medicMgr/medicHosGoods/getMedicHosGoodsByHos', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                         this.goodList = res.data.data.data;
                         this.goodsTotal = res.data.data.total;
                     
                    }
                    else{
                        this.$message.error( res.data.msg);
					}
                }, err => {
                        this.loading = false;
                    });
            },
            searchGoods(){
           
                this.getHosGoodsList(1);
            },
            handleGoodsSizeChange(val) {
               this.goodsPageSize = val;
               this.getHosGoodsList( this.goodsPage);
            },
            handleGoodsCurrentChange(val) {
                this.goodsPage = val;
                this.getHosGoodsList( this.goodsPage);
            },
            addMedicHosGoodsInfo:function(){
                this.isUpdate = false;
                if(this.currentRow==null){ 
                   this.$message.error( "请选择一条数据！");
                  return;
              }
              this.goodEdit(this.currentRow);
            },
          /*  batchDelete:function(){
                 if(this.multipleSelection4Main.length==0){
                       this.$message.error("请选择一条数据！");
                      return;
                 }
                 //todo... 

            },*/
            
            handleSelectionChange4Main(val) {
                this.multipleSelection4Main = val;
            },
            handleCurrentChange4Main(val){
                this.currentRow = val;
            },
            /**
             *  修改试剂产品信息
             */
            updateGood(item){
                this.isUpdate = true;
                this.goodEdit(item) ;
            },
            goodEdit(item) {
                if (item ) {
                    let good = Object.assign({}, item);
                    this.good = good;
                    this.taxRate=this.good.taxRate;
                    this.kindCode=this.good.kindCode;
                    this.kind68code=this.good.kind68code;
                    this.uniqueCodeStrategy= this.good.uniqueCodeStrategy;
                    if(this.isUpdate){
                        this.modelTitle = "修改试剂产品信息";
                    }else{
                        this.modelTitle = "添加试剂产品信息";
                    }
                    this.selectedId =item.mfrsId;
                    this.selectedName=item.mfrsName;

                } else {
                    this.good={};
                    this.kind68code='';
                    this.uniqueCodeStrategy='';
                    this.kindCode='';
                    this.taxRate='';
                    this.modelTitle = "添加试剂产品信息";
                    this.selectedId='';
                    this.selectedName='';
                }
                this.editFormVisible = true; 
              //  this.$refs.editForm.resetFields();   

            },
            /**
             *删除试剂产品信息
             */
            delGood: function (medicHosGoodsInfoVo) {
                this.$confirm('您确认删除试剂产品信息吗?', '提示', {}).then(() => {
                    this.loading = true;
                    this.axios.post("/spdHERPService/medicMgr/medicHosGoods/delMedicGoodsInfo", medicHosGoodsInfoVo).then(res => {
                        this.loading = false;
                        if (res.data.code == "0") {
                            this.loading = false;
                            this.$message({message: '操作成功！', type: 'success'});
                            this.getHosGoodsList(this.goodsPage);
                        } else {
                            this.$message.error(res.data.msg);
                        }
                    }, err => {
                        this.loading = false;
                    });
                }).catch(() => {
                });
            },
            getSL: function () {
                this.loading = true;
                  this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'SL'}).then(res => {                    this.loading = false;
                    if (res.data.code == "0") {
						this.SL = res.data.data;
                        /*
						if(this.mfrs) 
                        this.mfr=this.mfrs[0];
                       */
                    }
                    else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            getDict68s: function () {
                this.loading = true;
                  this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'68FL'}).then(res => {                    this.loading = false;
                    if (res.data.code == "0") {
						this.dict68s = res.data.data;
                    }
                    else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            getDictUCodeStrategys:function(){
                 this.loading = true;
                  this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'WYMGLCL'}).then(res => {

                    if (res.data.code == "0") {
						this.dictUCodeStrategys = res.data.data;
                    }
                    else{
                        this.$message.error( res.data.msg);
                    }
                      this.loading = false;
                }, err => {
                    this.loading = false;
                });
            },
            getHosKindCodes:function () {
                this.loading =true;
                this.axios.post('/spdHDIService/baseData/hosKindcode/list',{hosId:this.user.corpId}).then(res => {

                    if (res.data.code == "0") {
                        this.hosKindsCodes = res.data.data;

                    }
                    else{
                        this.$message.error( res.data.msg);
                    }
                    this.loading = false;
                }, err => {
                    this.loading = false;
                });

            },
            editSubmit: function () {
                this.good.mfrsId=this.selectedId;
                this.good.taxRate = this.taxRate;
                this.good.kindCode = this.kindCode;
                this.good.kind68code = this.kind68code;
                this.good.uniqueCodeStrategy= this.uniqueCodeStrategy;
              
                let _this = this;
                let form =  this.$refs.editForm;
                const h = this.$createElement;
                let arr=new Array();
                form.validate((valid) => {
                    if (valid) {
                        this.editLoading = true;
                        let serviceUrl = _this.good.goodsId ? "/spdHERPService/medicMgr/medicHosGoods/update" : "/spdHERPService/medicMgr/medicHosGoods/insert";
                        this.axios.post(serviceUrl, _this.good).then(res => {
                            _this.editLoading = false;
                            if (res.data.code == "0") {
                                _this.$message({ message: '操作成功！', type: 'success' });
                                _this.editFormVisible = false;
                                _this.getHosGoodsList(this.goodsPage);
                            } else if (res.data.code == "-122") {
                                    let err = res.data.validateErrors.fieldErrors[0];
                                        if (err.field == "code") {
                                        this.errors.codeErr += err.message;
                                           arr.push(h('span',null,err.message));
                                        }
                                         if (err.field == "mfrsId") {
                                        this.errors.mfrsIdErr += err.message;
                                           arr.push(h('span',null,err.message));
                                        }
                                          _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                                        
                                 }
                            else {
                                this.$message.error('操作失败！');
                            }
                        }, err => {
                            this.editLoading = false;
                        });
                     }else{
                        let fields = form.fields;
                        let msg ="";
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
           
                   
             
        },
        
        mounted () {
             this.filters.hosId = this.user.corpId;
             this.getKinds();
             this.getHosGoodsList(1);
             this.getSL();
             this.getDict68s();
             this.getDictUCodeStrategys();
             this.getHosKindCodes();
        }
    }
</script>

<style scoped>
    .line {
        text-align: center;
    }
    
    .el-button {
        margin: 0;
    }
    
    .el-pagination {
        float: right;
    
    }
    
    .fullheight {
        height: calc(100% - 0px);
       

    }
    
    .splitborder {
        border-right: rgba(0, 70, 255, 0.6) 1px dashed;
      
    }
    
    .productInfo-tableheight {
        height: calc(100% - 75px);
    }
    .el-tree-node__label {
	font-size: 12px;
    }
    .el-table {
	font-size: 12px;
    }
    .el-tree {
	min-height: calc(100% - 75px);
    }  
</style>