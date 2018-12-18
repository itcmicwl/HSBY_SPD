<template>
    <div id="provGoodsHoslist" style="height:95%">
        <el-row  height="auto"  style="height:100%">
            <el-col :span="4" style="height:100%" >
                 <div style="margin-bottom: 5px">
                    <el-input
                            v-model="filters.kindName" placeholder="请输入类型名称"
                            icon="search" :on-icon-click="searchs"  @keyup.enter.native="searchs"    >
                    </el-input>
                </div>

                <el-tree :data="kindsValue" node-key="id"
                         :default-expanded-keys="expadoIds"
                         :default-expand-all="true"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            </el-col>
            <el-col :span="20"  class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 100%;">
                 
                        <div style="margin-bottom: 0px;">
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
                            
                            <!--  <el-button  type="primary"  @click="disConnect(null)">解除关联</el-button> -->
                            </el-form>
                        </div>
                  
                <el-table highlight-current-row height="outter" class="feeCode-tableheight" 
                         :data="goodList" style="width: 100%" 
                          ref="multipleTable4Main"
                          row-key='id'  @selection-change="handleSelectionChange4Main"
                          v-loading="goodsLoading" border
                          :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
                           <!--
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                            -->    
                            <!--供货目录只列出供应商的 商品信息， 对码不在此处实现 -->     
                            <!--   
                            <el-table-column prop="hisCode"   min-idth="85" label="医院商品编号" header-align="center" >
                            </el-table-column>
                            -->
                            <!--
                            <el-table-column prop="code" label="商品编号" header-align="center"  width="120" >
                            </el-table-column>
                           -->
                            <el-table-column prop="goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="made"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="unit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="mfrsName"  width="150" header-align="center" show-overflow-tooltip  label="生产厂商" >
                            </el-table-column>
                             <el-table-column prop="costCode"  header-align="center" width="150"  label="收费代码" >
                            </el-table-column>
                             <el-table-column prop="costName"  header-align="center" width="150"  label="收费名称" >
                            </el-table-column>
                             <el-table-column prop="costPrice"  header-align="center" width="150"  label="收费价格" >
                            </el-table-column>
                            
                          
                           
                            
                           
                            <el-table-column label="操作" width="60"  header-align="center" fixed="right">
                                <template slot-scope="scope">
                                    <el-button-group>
                                		<el-button type="primary" size="mini" @click="goodEdit(scope.row)" icon="edit" >编辑</el-button>
                                    
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            <!--<el-table-column type="selection" width="40"></el-table-column>-->
                        </el-table>
                <el-pagination @size-change="handleGoodsSizeChange" @current-change="handleGoodsCurrentChange" :current-page="goodsPage" :page-sizes="[10, 20, 50, 100]" :page-size="goodsPageSize" layout="total, sizes, prev, pager, next" :total="goodsTotal" style="float:right;" small>
			   </el-pagination>
            </el-col>
        </el-row>
      
        	<!--编辑界面-->
				<el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" >
                
                    <el-form :model="good"  label-width="100px" :rules="editFormRules" ref="editForm"  class="el-form-item-nomsg">
                            <el-row>
                                <el-col>
                                    <el-form-item label="医院商品名称" prop="goodsName"  >
                                        <el-input v-model="good.goodsName"  :disabled="true" placeholder="请输入商品名称"></el-input>
                                    </el-form-item>
                                </el-col>                                
                            </el-row>
                
                            <el-row>
                               <el-col :span="8">
                                        <el-form-item label="单位" prop="unit" >
                                                    <el-input placeholder="请输入单位" v-model="good.unit"  :disabled="true" ></el-input>
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
                                    <el-form-item label="产地" prop="made" >
                                                <el-input v-model="good.made"  :disabled="true" placeholder="请输入产地"></el-input>
                                    </el-form-item>
                                </el-col>                                
                                 <el-col :span="16">   
                                    <el-form-item label="规格型号" prop="goodsGg" >
                                        <el-input v-model="good.goodsGg"  :disabled="true" placeholder="请输入规格型号"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>  
                              <el-row>                                
                                  <el-col :span="8">
                                        <el-form-item label="收费代码" prop="costCode" >
                                                    <el-input placeholder="请输入收费代码" v-model="good.costCode"   ></el-input>
                                        </el-form-item>
                                </el-col>
                                    <el-col :span="8">
                                        <el-form-item label="收费名称" prop="costName" >
                                                    <el-input placeholder="请输入收费名称" v-model="good.costName"   ></el-input>
                                        </el-form-item>
                                </el-col>
                                    <el-col :span="8">
                                        <el-form-item label="收费价格" prop="costPrice" >
                                                    <el-input placeholder="请输入收费价格" v-model.number="good.costPrice"   ></el-input>
                                        </el-form-item>
                                </el-col>
                               
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
        data () {   
            return {
                defaultProps: {
                    children: 'children', label: 'label', id:'id', pId:'pid', code:'code',hosId:'hosId',state:'state',           
                },
                filters:{
                    kindName:null,hosId:null,
                },
                kindsValue:[],
                expadoIds:[null],
                loading:'false',
                pname:'商品分类',
                selectedNode:null,
                //以下为右侧参数
                goodFilters:{ goodsName:null,goodsGg:null,provName:null,lbsx:null},
                goodsLoading:false,
                goodList:[],
                goodsTotal:0, goodsPageSize:10,goodsPage:1,
                multipleSelection4Main:[],
                // 编辑 参数
                modelTitle:"",
                editLoading:false,
                good:{},
                editFormVisible:false,
                costCodePO:{},
                editFormRules: {
                    costCode: [
                        { required: true, message: '请输入收费代码', trigger: 'blur' }
                    ],                   
                },
                errors: {
                    costCodeErr: '',
                    costNameErr: '',
                    costPriceErr:''
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
                 var param = { orderBy: "", queryObject: {hosId:hosId,goodsName:this.goodFilters.goodsName,goodsGg:this.goodFilters.goodsGg,lbsx:this.goodFilters.lbsx}, page: this.goodsPage, rows: this.goodsPageSize };
                 this.axios.post('/spdHERPService/productsInfos/hosCostCode/listVoByPage', param).then(res => {
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
          
          
             handleSelectionChange4Main(val) {
                this.multipleSelection4Main = val;
            },

            goodEdit(item) {

                if (item) {
                    let good = Object.assign({}, item);
                    this.good = good;
                
                } 
                this.editFormVisible = true; 
           

            },
        
           
            editSubmit: function () {
            
                let _this = this;
                let form =  this.$refs.editForm;
                const h = this.$createElement;
                let arr=new Array();
                form.validate((valid) => {
                    if (valid) {
                        this.editLoading = true;
                        this.costCodePO.id = this.good.id;
                        this.costCodePO.hosId =this.good.hosId;
                        this.costCodePO.hosGoodsId =this.good.hosGoodsId;
                        this.costCodePO.costCode = this.good.costCode;
                        this.costCodePO.costName = this.good.costName;
                        this.costCodePO.costPrice =this.good.costPrice;
                        //this.costCodePO.version =

                        let serviceUrl = this.good.id ? "/spdHERPService/productsInfos/hosCostCode/update" : "/spdHERPService/productsInfos/hosCostCode/add";
                        this.axios.post(serviceUrl, this.costCodePO).then(res => {
                            this.editLoading = false;
                            if (res.data.code == "0") {
                                this.$message({ message: '操作成功！', type: 'success' });
                                this.editFormVisible = false;
                                this.getHosGoodsList(this.goodsPage);
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
    .feeCode-tableheight {
        height: calc(100% - 60px);
    }

  .el-tree {
		min-height: calc(100% - 75px);
     
	}

</style>