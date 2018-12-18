<template>
    <div id="car"  style="height:95%">
        <el-row  style="height:100%">
       
            <el-col   style="padding-left: 5px;height:100%">
                <div style="margin-bottom: 0px;">
                    <el-form :inline="true" class="el-form-item-nomsg">
                        <el-form-item label="商品名">
                            <el-input v-model="goodFilters.goodsName" placeholder="请输入商品名"   ></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input v-model="goodFilters.goodsGg" placeholder="请输入规格型号"   > </el-input>
                        </el-form-item>  
                        <!-- 
                        <el-form-item label="供应商"> 
                            <el-input v-model="goodFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>   
                         -->
                        <el-button  type="primary"  @click="searchGoods(null)">查询</el-button>
                        <el-button  type="primary"  @click="createBill(null)">生成预购单</el-button>
                        <el-button  type="primary"  @click="delCar(null)">从预购车中删除</el-button> 
                    </el-form>
                </div>
                <div>
                     <el-form :inline="true" class="el-form-item-nomsg">  
                       <el-row>
                          <el-col :span="18">
                               <el-form-item label="目标科室">
                                    <el-select v-model="buyBill.sourceWarehouseId" filterable  clearable placeholder="请选择"  >
                                        <el-option
                                                v-for="item in relationDepts"
                                                :key="item.stockId"
                                                :label="item.deptName"
                                                :value="item.stockId">
                                        </el-option>
                                    </el-select>
                             </el-form-item>
                          </el-col>
                       </el-row>
                       <el-row>
                        <el-col :span="5">
                            <el-form-item label="收货人：">
                                {{buyBill.recLinkman}}
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="电话：">
                                {{buyBill.recLinkmanPhone}}
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="收货地址：">
                                {{buyBill.recAddress}}
                            </el-form-item>
                        </el-col>
                      
                         <el-col :span="2">
                            <el-form-item label-width="0px">
                               <deliveryAddress btn_text="修改" v-on:addressChange="setAddress" :hosId="this.user.corpId" :deptId="this.user.orgId"></deliveryAddress>
                            </el-form-item>
                        </el-col>
                       </el-row>
                        <!-- <el-button  type="primary"  @click="updateAddress()">修改地址</el-button> -->
                 
                      <!--  <el-button  type="primary"  @click="disConnect(null)">解除关联</el-button> -->
                    </el-form>
                </div>
                
                <el-table highlight-current-row class="pre-carbuy-tableheight" 
                         :data="goodList" style="width: 100%" 
                          ref="multipleTable4Main"
                          row-key='id'  @selection-change="handleSelectionChange4Main"
                          v-loading="goodsLoading" border height="outer"
                          :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}">
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>    
                            <!--供货目录只列出供应商的 商品信息， 对码不在此处实现 -->     
                            <!--   
                            <el-table-column prop="hisCode"   min-idth="85" label="医院商品编号" header-align="center" >
                            </el-table-column>
                             -->
                            <el-table-column prop="code" label="商品编号" header-align="center"  width="80" >
                            </el-table-column>
                          
                            <el-table-column prop="goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="made"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="unit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                             <el-table-column prop="packetCode"   min-idth="85" label="包装数量" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="price"   min-idth="85" label="单价" header-align="center" >
                            </el-table-column>
                             <el-table-column prop="qty"   min-idth="85" label="库存数量" header-align="center" >
                            </el-table-column>
                           
                            <el-table-column label="预购数量" width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量" v-model="scope.row.needQty" size="mini" :min="1" class="col-input-num" @change="qtyChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                           </el-table-column>
                         

                            <!--<el-table-column prop="mfrsName"   width="150" header-align="center" show-overflow-tooltip  label="生产厂商" >
                            </el-table-column>-->
                            
                          
                           <!--
                            <el-table-column prop="provName"  header-align="center" width="150"  label="供应商" show-overflow-tooltip sortable>
                            </el-table-column>
                            -->
                           <!--
                            <el-table-column label="操作" width="120"  header-align="center" fixed="right">
                                <template slot-scope="scope">
                                    <el-button-group>
                                		<el-button type="primary" size="mini" @click="goodEdit(scope.row)" icon="edit" >编辑</el-button>
                                        <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete">删除</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            -->
                            <!--<el-table-column type="selection" width="40"></el-table-column>-->
                        </el-table>
                <el-pagination @size-change="handleGoodsSizeChange" @current-change="handleGoodsCurrentChange" :current-page="goodsPage" :page-sizes="[10, 20, 50, 100]" :page-size="goodsPageSize" layout="total, sizes, prev, pager, next" :total="goodsTotal" style="float:right;" small>
			   </el-pagination>
            </el-col>
        </el-row>
  
    </div>
</template>

<script>
  import deliveryAddress from '../../components/deliveryAddress.vue';
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
                multipleSelection4Main:[],
                buyBill:{
                    id:null,
                    subList:[],
                    carList:[],
                    recAddressId: '',
                    recLinkman: '',
                    recLinkmanPhone: '',
                    recAddress: '',
                    sourceWarehouseId:''
                },
                address:{id:null,lxr:null,lxrPhone:null,provinceName:null,cityName:null,areaName:null,address:null,areaCode:null,isDefaulte:null},
                addressList:[],
             
                modelTitle:null,
                radio: '1',
                relationDepts:[]
              

              
            }
    },
        components: { deliveryAddress },
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
              //  alert(data.id);
                this.getDeptCarGoodsStVo(1);
               
            },
         
            getDeptCarGoodsStVo:function(pIndex){

                  var this_ = this;
                  this.goodsPage = pIndex;
                  this.loading = true;
                 var param = { orderBy: "", 
                              queryObject: {hosId:this.user.corpId,deptId:this.user.orgId,goodsName:this.goodFilters.goodsName,
                                  goodsGg:this.goodFilters.goodsGg,buyKind:20}, 
                              page: this.goodsPage, rows: this.goodsPageSize 
                            };
                 this.axios.post('/spdHERPService/applyMgr/deptBuyCar/getDeptCarGoodsStVo', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                         this.goodList = res.data.data.data;
                         this.goodsTotal = res.data.data.total;
                         this.multipleSelection4Main=[];
                         this_.$refs.multipleTable4Main.clearSelection();
                       
                     
                    }
                    else{
                        this.$message.error( res.data.msg);
					}
                }, err => {
                        this.loading = false;
                    });
            },
            searchGoods(){
              //  alert("按条件查询");
                this.getDeptCarGoodsStVo(1);
            },
            handleGoodsSizeChange(val) {
               this.goodsPageSize = val;
               this.getDeptCarGoodsStVo( this.goodsPage);
            },
            handleGoodsCurrentChange(val) {
                this.goodsPage = val;
                this.getDeptCarGoodsStVo( this.goodsPage);
            },
            handleSelectionChange4Main(val) {
                this.multipleSelection4Main = val;
            },
           createBill:function(){
                if(!this.buyBill.sourceWarehouseId){
                           this.$message.error( "请选择目标科室");
                            return;
                }
                if(this.multipleSelection4Main.length<=0){ 
                   this.$message.error( "至少选择一条数据！");
                  return;
                }  //20 虚采  company-013 青山
                 this.buyBill.subList=[];
                 this.buyBill.carList=[];
                 this.buyBill.buyKind=20;  this.buyBill.hosId=this.user.corpId; this.buyBill.buyDeptId=this.user.orgId,this.buyBill.state=10;
            
                 this.multipleSelection4Main.forEach(v =>{
                    let s = {};
                    s.buyDeptId=v.deptId; s.goodsId= v.goodsId; s.hosId=v.hosId;s.qty=v.needQty;s.uniqueKind =v.isUnique;
                    this.buyBill.subList.push(s);
                }); 
                this.buyBill.carList= this.multipleSelection4Main;        
                
                     this.axios.post('/spdHERPService/applyMgr/deptBuyMain/create/'+this.user.userId, this.buyBill).then(res => {
                     this.loading = true;
                     if (res.data.code == "0") {
                           this.$message({ message: '操作成功！',type: 'success'});
                           this.loading = false;   
                           this.getDeptCarGoodsStVo(1);

                     }
                     else{
                         this.$message.error( res.data.msg);
				 	}
                 }, err => {
                     this.loading = false;
                 });
           },
           delCar:function(){

              
                 if(this.multipleSelection4Main.length<=0){ 
                     this.$message.error( "至少选择一条数据！");
                     return;
                  } 
                  this.axios.post('/spdHERPService/applyMgr/deptBuyMain/delCar/', this.multipleSelection4Main).then(res => {
                     this.loading = true;
                     if (res.data.code == "0") {
                           this.$message({ message: '操作成功！',type: 'success'});
                           this.loading = false;   
                           this.multipleSelection4Main=[];
                           this.getDeptCarGoodsStVo(1);
                     }
                     else{
                         this.$message.error( res.data.msg);
				 	}
                 }, err => {
                     this.loading = false;
                 });
           },
            qtyChange(val, row) {
            // row.purQty = val;
            // row.purAmount = (row.hosPrice * row.purQty).toFixed(2);
            // row.purTaxAmount = (row.purTaxPrice * row.purQty).toFixed(2);
          //  alert(row.dx);

           },
      
           updateAddress(){
              this.updateAddressVisible =true;
              this.modelTitle='修改收货地址';
           },
           test(row){
           //    alert(row.id);
           },
           deliveryAddrEdit(row){
              this.address = row;
              this.updateAddressVisible =false;
           },
           setDfaulte(row){

           },
           setAddress(d) {
            
            this.buyBill.recAddressId = d.id;
            this.buyBill.recLinkman = d.lxr;
            this.buyBill.recLinkmanPhone = d.lxrPhone;
            this.buyBill.recAddress = (""+d.province + d.city + d.area + d.address).replace(/null/g,"");
        
          },
          //获取可请领科室集合
          getRelationDepts: function () {
                this.loading = true;
                  this.axios.post('/spdHERPService/baseData/stocInfoMgr/hosDeptPurStock/queryDeptRelation',
                                   {hosId:this.user.corpId,deptId:this.user.orgId}).then(res => {       
                    this.loading = false;
                    if (res.data.code == "0") {
						this.relationDepts = res.data.data;
                    }
                    else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            }  
        },
        
        mounted () {
             this.filters.hosId = this.user.corpId;
             this.getKinds();
             this.getDeptCarGoodsStVo(1);
             this.getRelationDepts();
            
        }
    }
</script>

<style scoped>
   .el-table {
    font-size: 12px;
    }
    .el-form {
    font-size: 12px;
    }
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
    
    .pre-carbuy-tableheight {
        height: calc(100% - 100px);
    }
</style>