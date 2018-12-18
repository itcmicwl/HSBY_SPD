<template>
    <div id="want" style="height:95%">
        <el-row  height="auto"  style="height:100%">
            <el-col :span="4" style="height:100%" >
                 <div style="margin-bottom: 5px">
                    <el-input
                            v-model="filters.kindName" placeholder="请输入类型名称"
                            icon="search" :on-icon-click="searchs"   >
                    </el-input>
                </div>

                <el-tree :data="goodsTemplateNode" node-key="id"
                          :expand-on-click-node="false"
                         :default-expand-all="true"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            </el-col>
            <el-col :span="20" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 100%;">
                <div style="margin-bottom: 0px;">
                    <el-form :inline="true">
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
                        <el-button  type="primary"  @click="add2car(null)">添加到预购车</el-button>
                      <!--  <el-button  type="primary"  @click="disConnect(null)">解除关联</el-button> -->
                    </el-form>
                </div>
                
                <el-table highlight-current-row class="pre-addBuy-tableheight"  
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
                             <el-table-column prop="packetCode"   min-idth="10" label="包装数量" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="price"   min-idth="30" label="单价" header-align="center" >
                            </el-table-column>
                             <el-table-column prop="qty"   min-idth="30" label="库存数量" header-align="center" >
                            </el-table-column>
                           
                            <el-table-column label="预购数量" width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量" v-model="scope.row.needQty" size="mini" :min="1" class="col-input-num" @change="qtyChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                           </el-table-column>
                         

                            <!--<el-table-column prop="mfrsName"  header-align="center" show-overflow-tooltip width="150"  label="生产厂商" >
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
    function buildTreenode(list,idName,pidName,labelName)
    {
        var map ={};
        for(let i = 0; i< list.length;i++)
        {

            map[list[i][idName]] = {id:list[i][idName],pid:list[i][pidName],label:list[i][labelName],tag:list[i]};
        }
        var maproot=[];
        function linknode(node)
        {
            if(!node.pid){
                maproot.push(node);
                return;
            }
            let pnode = map[node.pid];
            node.pnode = pnode;
            if(!pnode.children)
                pnode.children = [];
            pnode.children.push(node);
        }

        for(var id in map)
        {
            let item = map[id];
            linknode(item);
        }
        for(var id in map)
        {
            let item = map[id];
            if(item.children){
                item.isLeaf= false;
                item.checked = false;
            }
            else{
                item.isLeaf = true;
                item.checked = false;
                let pitem = map[item.pid];
                if(pitem){
                    pitem.isGroup = true;
                }
            }
        }

        return maproot;
    }
    export default {
        data () {   
            return {
                hosId:null,
                deptId:null,
                buyKind:20,     //10实物采购，20虚拟采购
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
                goodsTemplateNode:[{
                        children: [],
                        label: '',
                        id: '',
                        pid: ''
                }],
                expadoIds:[null],
                loading:'false',
                pname:'商品分类',
                selectedNode:null,
                //以下为右侧参数
                goodFilters:{
                    goodsName:null,
                    goodsGg:null,
                    provName:null,
                    tempId:null
                },
                goodsLoading:false,
                goodList:[],
                goodsTotal:0, goodsPageSize:10,goodsPage:1,
                multipleSelection4Main:[],
              

              
            }
        },
        methods: {
            getKinds:function () { 
                    let o = this;
                    function cb(goodsTemplate){
                  
                        o.goodsTemplate=buildTreenode(goodsTemplate,"id","pid","cname");
                        o.goodsTemplateNode[0].children = o.goodsTemplate;
                        o.goodsTemplateNode[0].label = "所有可选商品";
                      //   o.queryDeptTemplateGoods(1);
                     }
                    let param = { hosId:this.filters.hosId,deptId:this.user.orgId};
                    this.axios.post(`/spdHERPService/deptMgr/deptGoodsTemplate/deptGoodsTemplateQuery4DeptBy`, param).then(res=>{
                         var goodsTemplate = res.data.data;
                          cb(goodsTemplate);
                     
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
                this.goodFilters.tempId= data.id;
                this.getHosGoodsListByDept(1);
               
            },
         
            getHosGoodsListByDept:function(pIndex){
                  //purMode:20 虚拟采购 对应部门商品表中 的 20寄售，30跟台
                  //purMode：10 实物采购  对应部门商品表中 的 10采购
                  this.goodsPage = pIndex;
                  this.loading = true;
                 var param = { orderBy: "",
                                queryObject: {hosId:this.hosId,deptId:this.deptId,goodsName:this.goodFilters.goodsName,
                                               goodsGg:this.goodFilters.goodsGg,tempId:this.goodFilters.tempId,purMode:20}, 
                              page: this.goodsPage, rows: this.goodsPageSize };
                 this.axios.post('/spdHERPService/applyMgr/deptBuyCar/getDeptGoodsStVo', param).then(res => {
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
                this.getHosGoodsListByDept(1);
            },
            handleGoodsSizeChange(val) {
               this.goodsPageSize = val;
               this.getHosGoodsListByDept( this.goodsPage);
            },
            handleGoodsCurrentChange(val) {
                this.goodsPage = val;
                this.getHosGoodsListByDept( this.goodsPage);
            },
            handleSelectionChange4Main(val) {
                this.multipleSelection4Main = val;
            },
           add2car:function(){
             //  alert(this.multipleSelection4Main.length);
             //  alert(this.multipleSelection4Main[0].needQty);
                if(this.multipleSelection4Main.length<=0){ 
                   this.$message.error( "至少选择一条数据！");
                  return;
                }  //10 实采 20 虚采  company-013 青山
              this.axios.post('/spdHERPService/applyMgr/deptBuyCar/add2car/20/'+this.hosId+'/'+this.deptId, this.multipleSelection4Main).then(res => {
            //    this.axios.post('/spdHERPService/applyMgr/deptBuyCar/add2car/10/company-013/org-70', this.multipleSelection4Main).then(res => {
                    this.loading = true;
                    if (res.data.code == "0") {
                          this.$message({ message: '操作成功！',type: 'success'});
                          this.loading = false;   
                          this.getHosGoodsListByDept(1);

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
         //   alert(val);

           }
          
           
           
           
         
         
          
         
          
           
                   
             
        },
        
        mounted () {

             this.filters.hosId = this.user.corpId;
             this.hosId = this.user.corpId;
             this.deptId= this.user.orgId;
             this.getKinds();
             this.getHosGoodsListByDept(1);
            
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
    
    .pre-addBuy-tableheight {
        height: calc(100% - 35px);
    }
    .el-tree {
		min-height: calc(100% - 55px);
     
	}
</style>